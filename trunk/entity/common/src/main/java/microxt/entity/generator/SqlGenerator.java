package microxt.entity.generator;

import java.util.List;

import javax.persistence.TypedQuery;

import microxt.entity.entity.Entity;
import microxt.entity.entity.EntityField;
import microxt.entity.util.JpaManager;

public class SqlGenerator {
	private final String COMPANY = "HNDT";
	private String ENTITY_SQL = 
		"Select e from Entity e where e.companyId=:company";
	private String FIELD_SQL = 
		"Select f from EntityField f where " +
		"f.companyId=:company and f.pk.entityId=:entityId " +
		"order by f.fieldOrder";
	private StringBuffer sbSql;
	private final String NEW_LINE = "\n";
	private final String TAB = "\t";
	
	public SqlGenerator(){
		sbSql = new StringBuffer();
	}
	
	public void execute(){
		generateTables();
		System.out.println(sbSql);
	}
	
	public void generateTables(){
		List<Entity> lEntity;
		
		TypedQuery<Entity> query = JpaManager.getEntityManager().createQuery(ENTITY_SQL, Entity.class);
		query.setParameter("company", COMPANY);
		//query.setLockMode(LockModeType.READ);
		lEntity = query.getResultList();
		
		for (Entity ent : lEntity) {
			sbSql.append("CREATE TABLE "+ent.getName()+" ("+NEW_LINE);
			System.out.println(ent);
			generateFields(ent);
			sbSql.append(");"+NEW_LINE);
			sbSql.append(NEW_LINE);
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
		
		for (int i = 0; i < lField.size(); i++) {
			EntityField f = lField.get(i);
			System.out.println(f);
			
			// Name
			sbSql.append(TAB);
			sbSql.append(f.getName());
			// Data type
			sbSql.append(" "+f.getDataTypeId());
			if (f.getDataTypeId().compareTo("String")==0 
					|| f.getDataTypeId().compareTo("Integer")==0){
				sbSql.append("("+f.getDataSize()+")");
			}else if (f.getDataTypeId().compareTo("BigDecimal")==0){
				sbSql.append("("+f.getDataSize()+","+f.getDataScale()+")");
			}
			// Nullable
			if(f.getNullable()!=null && f.getNullable().compareTo("0")==0){
				sbSql.append(" NOT NULL");
			}
			// Default
			if(f.getDefaultValue()!=null){
				sbSql.append(" DEFAULT '"+f.getDefaultValue()+"'");
			}
			// End 
			sbSql.append(","+NEW_LINE);
			
			// Pks
			if (f.getPrimaryKey()!=null && f.getPrimaryKey().compareTo("1")==0){
				if(!pks.isEmpty()){
					pks = pks.concat(", "); 
				}
				pks = pks.concat(f.getName());
			}
		}
		
		sbSql.append(TAB+"PRIMARY KEY ("+pks+")"+NEW_LINE);
	}
}
