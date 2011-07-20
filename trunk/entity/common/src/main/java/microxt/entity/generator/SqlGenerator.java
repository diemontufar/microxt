package microxt.entity.generator;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.TypedQuery;

import org.eclipse.persistence.internal.jpa.metadata.accessors.mappings.RelationshipAccessor;

import microxt.entity.entity.DataTypeByDb;
import microxt.entity.entity.DataTypeByDbPK;
import microxt.entity.entity.Entity;
import microxt.entity.entity.EntityField;
import microxt.entity.entity.EntityFieldPK;
import microxt.entity.entity.EntityRelationship;
import microxt.entity.entity.EntityRelationshipField;
import microxt.entity.util.JpaManager;
import microxt.tools.common.file.FileUtil;

public class SqlGenerator {
	
	private String company;
	
	private final String dataBase;
	
	private String ENTITY_SQL = 
		"Select e from Entity e where e.companyId=:company";
	
	private String FIELD_SQL = 
		"Select f from EntityField f where " +
		"f.companyId=:company and f.pk.entityId=:entityId " +
		"order by f.fieldOrder";
	
	private String RELATIONSHIP_SQL = 
			"Select r from EntityRelationship r where " +
			"r.pk.companyId=:company ";
	
	private String RELATIONSHIP_FIELD_SQL = 
			"Select f from EntityRelashionshipField f where " +
			"f.pk.relationshipId=:relationshipId " +
			"order by f.pk.relationshipOrder";
	
	private final String NEW_LINE = "\n";
	
	private final String TAB = "\t";
	
	private StringBuffer buffer;
	
	/**
	 * Constructor
	 * 
	 * @param company
	 * @param dataBase
	 */
	public SqlGenerator(String company, String dataBase){
		this.company = company;
		this.dataBase = dataBase;
		buffer = new StringBuffer();
	}
	
	public void execute() throws Exception{
		generateTables();
		generateRelationships();
		System.out.println(buffer);
		System.out.println("Writing file...");
		FileUtil.writeFile("/home/ronald/create.sql", buffer.toString());
	}
	
	public void generateTables(){
		List<Entity> lEntity;
		
		TypedQuery<Entity> query = JpaManager.getEntityManager().createQuery(ENTITY_SQL, Entity.class);
		query.setParameter("company", company);
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
		query.setParameter("company", company);
		query.setParameter("entityId", ent.getPk().getEntityId());
		//query.setLockMode(LockModeType.READ);
		lField = query.getResultList();
		
		String pks = "";
		
		if (lField.size() == 0) {
			buffer.append("--ERROR. TABLE: " + ent.getPk().getEntityId() + ". NO FIELDS DEFINED" + NEW_LINE+NEW_LINE);
		} else {
			buffer.append("CREATE TABLE " + ent.getPk().getEntityId() + " (" + NEW_LINE);
			
			for (int i = 0; i < lField.size(); i++) {
				EntityField f = lField.get(i);
				System.out.println(f);

				// Name
				buffer.append(TAB);
				buffer.append(f.getPk().getFieldId());
				// Data type
				DataTypeByDbPK pk = new DataTypeByDbPK();
				pk.setDataTypeId(f.getDataType().getDataTypeId());
				pk.setDataBase(dataBase);
				DataTypeByDb dataType = JpaManager.getEntityManager().find(
						DataTypeByDb.class, pk);
				if (dataType != null) {
					buffer.append(" " + dataType.getDataBaseValue());
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
						buffer.append("(" + dataTypeSize + ")");
					}
				} else {
					buffer.append(" XXXXX");
				}
				// Nullable
				if (f.getNullable() != null
						&& f.getNullable().compareTo("0") == 0) {
					buffer.append(" NOT NULL");
				}
				// Default
				if (f.getDefaultValue() != null
						&& !(f.getDefaultValue().compareToIgnoreCase("null") == 0 || f
								.getDefaultValue()
								.compareToIgnoreCase("(null)") == 0)) {
					buffer.append(" DEFAULT '" + f.getDefaultValue() + "'");
				}
				// End
				buffer.append("," + NEW_LINE);

				// Pks
				if (f.getPrimaryKey() != null
						&& f.getPrimaryKey().compareTo("1") == 0) {
					if (!pks.isEmpty()) {
						pks = pks.concat(", ");
					}
					pks = pks.concat(f.getPk().getFieldId());
				}
			}
			
			buffer.append(TAB+"PRIMARY KEY ("+pks+")"+NEW_LINE);
			buffer.append(");" + NEW_LINE);
			buffer.append(NEW_LINE);
		}
	}
	
	private void generateRelationships() {
		List<EntityRelationship> lRel;

		TypedQuery<EntityRelationship> query = JpaManager.getEntityManager().createQuery(
				RELATIONSHIP_SQL, EntityRelationship.class);
		query.setParameter("company", company);
		// query.setLockMode(LockModeType.READ);
		lRel = query.getResultList();

		for (EntityRelationship rel : lRel) {
			System.out.println(rel);
			generateRelationshipFields(rel);
		}
	}

	private void generateRelationshipFields(EntityRelationship rel) {
		List<EntityRelationshipField> lField;

		TypedQuery<EntityRelationshipField> query = JpaManager
				.getEntityManager().createQuery(RELATIONSHIP_FIELD_SQL,
						EntityRelationshipField.class);
		query.setParameter("company", company);
		query.setParameter("realationshipId", rel.getPk().getRelationshipId());
		//query.setLockMode(LockModeType.READ);
		lField = query.getResultList();
		
		if (lField.size() == 0) {
			buffer.append("--ERROR. RELATIONSHIP: " + rel.getPk().getName() + ". NO FIELDS DEFINED" + NEW_LINE+NEW_LINE);
		} else {
//ALTER TABLE ENTITY_FIELD ADD CONSTRAINT ENTITY_FIELD_FK FOREIGN KEY (ENTITY_ID) REFERENCES ENTITY (ENTITY_ID);			
			buffer.append("ALTER TABLE " + rel.getEntityFrom().getPk().getEntityId() + " ADD CONSTRAINT " +
					rel.getPk().getName() + " FOREIGN KEY (");
	
			//Relationship fields
			String fromFields = "";
			String toFields = "";
			for (int i = 0; i < lField.size(); i++) {
				EntityRelationshipField f = lField.get(i);
				System.out.println(f);

				/*
				EntityFieldPK pkFrom = new EntityFieldPK();
				pkFrom.setEntityId(f.getEntityFrom());
				pkFrom.setFieldId(f.getFieldFrom());
				EntityField fieldFrom = JpaManager.getEntityManager().find(EntityField.class, pkFrom);
				EntityFieldPK pkTo = new EntityFieldPK();
				pkFrom.setEntityId(f.getEntityTo());
				pkFrom.setFieldId(f.getFieldTo());
				EntityField fieldTo = JpaManager.getEntityManager().find(EntityField.class, pkTo);
				
				
				if (!fromFields.isEmpty()) {
					fromFields = fromFields.concat(", ");
				}
				fromFields = fromFields.concat(fieldFrom.getName());
				if (!toFields.isEmpty()) {
					toFields = toFields.concat(", ");
				}
				toFields = toFields.concat(fieldTo.getName());
				*/
			}
			
			buffer.append(fromFields+") " +
					"REFERENCES "+rel.getEntityTo().getPk().getEntityId()+" ("+toFields+");" + NEW_LINE);
			buffer.append(NEW_LINE);
		}
	}
}
