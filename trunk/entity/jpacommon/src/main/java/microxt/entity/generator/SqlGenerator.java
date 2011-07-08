package microxt.entity.generator;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.TypedQuery;

import microxt.entity.entity.DataTypeByDb;
import microxt.entity.entity.DataTypeByDbPK;
import microxt.entity.entity.Entity;
import microxt.entity.entity.EntityField;
import microxt.entity.util.JpaManager;
import microxt.tools.common.file.FileUtil;

public class SqlGenerator {
	private final String COMPANY = "HNDT";
	private final String DATA_BASE = "MYSQL";
	
	private String ENTITY_SQL = 
		"Select e from Entity e where e.companyId=:company";
	private String FIELD_SQL = 
		"Select f from EntityField f where " +
		"f.companyId=:company and f.pk.entityId=:entityId " +
		"order by f.fieldOrder";
	
	private final String NEW_LINE = "\n";
	private final String TAB = "\t";
	
	private StringBuffer sbSql;
	
	public SqlGenerator(){
		sbSql = new StringBuffer();
	}
	
	public void execute() throws Exception{
		generateTables();
		System.out.println(sbSql);
		System.out.println("Writing file...");
		FileUtil.writeFile("/home/ronald/create.sql", sbSql.toString());
	}
	
	public void generateTables(){
		List<Entity> lEntity;
		
		TypedQuery<Entity> query = JpaManager.getEntityManager().createQuery(ENTITY_SQL, Entity.class);
		query.setParameter("company", COMPANY);
		//query.setLockMode(LockModeType.READ);
		lEntity = query.getResultList();
		
		for (Entity ent : lEntity) {
			//sbSql.append("CREATE TABLE "+ent.getName()+" ("+NEW_LINE);
			System.out.println(ent);
			generateFields(ent);
			//sbSql.append(");"+NEW_LINE);
			//sbSql.append(NEW_LINE);
		}		
	}

	private void generateFields(Entity ent) {
		List<EntityField> lField;
		
		TypedQuery<EntityField> query = JpaManager.getEntityManager().createQuery(FIELD_SQL, EntityField.class);
		query.setParameter("company", COMPANY);
		query.setParameter("entityId", ent.getEntityId());
		//query.setLockMode(LockModeType.READ);
		lField = query.getResultList();
		
		String pks = "";
		
		if (lField.size() == 0) {
			sbSql.append("--ERROR. TABLE: " + ent.getName() + ". NO FIELDS DEFINED" + NEW_LINE+NEW_LINE);
		} else {
			sbSql.append("CREATE TABLE " + ent.getName() + " (" + NEW_LINE);
			
			for (int i = 0; i < lField.size(); i++) {
				EntityField f = lField.get(i);
				System.out.println(f);

				// Name
				sbSql.append(TAB);
				sbSql.append(f.getName());
				// Data type
				DataTypeByDbPK pk = new DataTypeByDbPK();
				pk.setDataTypeId(f.getDataTypeId());
				pk.setDataBase(DATA_BASE);
				DataTypeByDb dataType = JpaManager.getEntityManager().find(
						DataTypeByDb.class, pk);
				if (dataType != null) {
					sbSql.append(" " + dataType.getDataBaseValue());
					// Data type size-scale
					String dataTypeSize = "";
					if (f.getDataSize() != null
							&& f.getDataSize().compareTo(BigDecimal.ZERO) == 1) {
						dataTypeSize = dataTypeSize.concat(f.getDataSize()
								.toString());
					}
					if (f.getDataScale() != null
							&& f.getDataScale().compareTo(BigDecimal.ZERO) == 1) {
						dataTypeSize = dataTypeSize.concat(","
								+ f.getDataScale().toString());
					}
					if (dataTypeSize != "") {
						sbSql.append("(" + dataTypeSize + ")");
					}
				} else {
					sbSql.append(" XXXXX");
				}
				// Nullable
				if (f.getNullable() != null
						&& f.getNullable().compareTo("0") == 0) {
					sbSql.append(" NOT NULL");
				}
				// Default
				if (f.getDefaultValue() != null
						&& !(f.getDefaultValue().compareToIgnoreCase("null") == 0 || f
								.getDefaultValue()
								.compareToIgnoreCase("(null)") == 0)) {
					sbSql.append(" DEFAULT '" + f.getDefaultValue() + "'");
				}
				// End
				sbSql.append("," + NEW_LINE);

				// Pks
				if (f.getPrimaryKey() != null
						&& f.getPrimaryKey().compareTo("1") == 0) {
					if (!pks.isEmpty()) {
						pks = pks.concat(", ");
					}
					pks = pks.concat(f.getName());
				}
			}
			
			sbSql.append(TAB+"PRIMARY KEY ("+pks+")"+NEW_LINE);
			sbSql.append(");" + NEW_LINE);
			sbSql.append(NEW_LINE);
		}
	}
}
