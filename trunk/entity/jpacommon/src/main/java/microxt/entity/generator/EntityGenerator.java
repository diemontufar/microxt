package microxt.entity.generator;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import javax.persistence.Table;
import javax.persistence.TypedQuery;

import microxt.entity.entity.DataTypeByDb;
import microxt.entity.entity.DataTypeByDbPK;
import microxt.entity.entity.Entity;
import microxt.entity.entity.EntityField;
import microxt.entity.util.JpaManager;
import microxt.tools.common.file.FileUtil;

public class EntityGenerator {
	//private final String COMPANY = "HNDT";
	//private final String DATA_BASE = "MYSQL";
	
	private String company;
	private String pathToSave;
	
	private String ENTITY_SQL = 
		"Select e from Entity e where e.companyId=:company";
	private String FIELD_SQL = 
		"Select f from EntityField f where " +
		"f.companyId=:company and f.pk.entityId=:entityId " +
		"order by f.fieldOrder";
	
	private final String NEW_LINE = "\n";
	private final String TAB = "\t";
	private final String END_LINE = ";\n"; // Semicolon
	
	private StringBuffer buffer0;
	
	public EntityGenerator(String company, String path){
		this.company = company;
		this.pathToSave = path;
		buffer0 = new StringBuffer();
	}
	
	public void execute() throws Exception{
		generateEntities();
		//System.out.println(buffer0);
		//System.out.println("Writing file...");
		//FileUtil.writeFile("/home/ronald/create.sql", buffer0.toString());
	}
	
	public void generateEntities(){
		List<Entity> lEntity;
		
		TypedQuery<Entity> query = JpaManager.getEntityManager().createQuery(ENTITY_SQL, Entity.class);
		query.setParameter("company", company);
		//query.setLockMode(LockModeType.READ);
		lEntity = query.getResultList();
		
		for (Entity ent : lEntity) {
			generateFields(ent);
		}		
	}

	private void generateFields(Entity ent) {
		List<EntityField> lField;
		
		TypedQuery<EntityField> query = JpaManager.getEntityManager().createQuery(FIELD_SQL, EntityField.class);
		query.setParameter("company", company);
		query.setParameter("entityId", ent.getEntityId());
		//query.setLockMode(LockModeType.READ);
		lField = query.getResultList();
		
		String pks = "";
		
		if (lField.size() == 0) {
			System.out.println("==========================================================");
			System.out.println("ERROR. TABLE: "+ent.getName()+". NO FIELDS DEFINED");
			System.out.println("==========================================================");
		} else {
			List<String> lGeneralImports = new ArrayList<String>();
			List<String> lPersistenceImports = new ArrayList<String>();
			List<String> lEntityCommonImports = new ArrayList<String>();
			
			List<String> lExtends = new ArrayList<String>();
			List<String> lImplements = new ArrayList<String>();
			
			lPersistenceImports.add("java.io.Serializable");
			lPersistenceImports.add("javax.persistence.*");
			
			//buffer0.append("@Entity"+NEW_LINE);
			//buffer0.append("@Table(name=\""+ent.getName()+"\")"+NEW_LINE);
			String className = upperCamelCase(ent.getName());
			
			lEntityCommonImports.add("microxt.entity.common.AbstractEntity");
			lEntityCommonImports.add("microxt.entity.common.GeneralEntity");
			lExtends.add("AbstractEntity");
			lImplements.add("GeneralEntity");
			
			
			/*
			
			
			for (int i = 0; i < lField.size(); i++) {
				EntityField f = lField.get(i);
				System.out.println(f);

				// Name
				buffer0.append(TAB);
				buffer0.append(f.getName());
				// Data type
				DataTypeByDbPK pk = new DataTypeByDbPK();
				pk.setDataTypeId(f.getDataTypeId());
				pk.setDataBase(DATA_BASE);
				DataTypeByDb dataType = JpaManager.getEntityManager().find(
						DataTypeByDb.class, pk);
				if (dataType != null) {
					buffer0.append(" " + dataType.getDataBaseValue());
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
						buffer0.append("(" + dataTypeSize + ")");
					}
				} else {
					buffer0.append(" XXXXX");
				}
				// Nullable
				if (f.getNullable() != null
						&& f.getNullable().compareTo("0") == 0) {
					buffer0.append(" NOT NULL");
				}
				// Default
				if (f.getDefaultValue() != null
						&& !(f.getDefaultValue().compareToIgnoreCase("null") == 0 || f
								.getDefaultValue()
								.compareToIgnoreCase("(null)") == 0)) {
					buffer0.append(" DEFAULT '" + f.getDefaultValue() + "'");
				}
				// End
				buffer0.append("," + NEW_LINE);

				// Pks
				if (f.getPrimaryKey() != null
						&& f.getPrimaryKey().compareTo("1") == 0) {
					if (!pks.isEmpty()) {
						pks = pks.concat(", ");
					}
					pks = pks.concat(f.getName());
				}
			}
			
			buffer0.append(TAB+"PRIMARY KEY ("+pks+")"+NEW_LINE);
			buffer0.append(");" + NEW_LINE);
			buffer0.append(NEW_LINE);
		*/
		}
		
		
	}
	
	private String upperCamelCase(String string){
		StringBuilder sb = new StringBuilder();
		for( String s : string.split("_") ) {
		    sb.append( s.substring(0,1).toUpperCase() );
		    sb.append( s.substring(1).toLowerCase() );
		}		
		return sb.toString();
	} 
	
	private String lowerCamelCase(String string){
		StringBuilder sb = new StringBuilder();
		Boolean primera = true;
		for( String s : string.split("_") ) {
			if(primera){
				sb.append( s.substring(0,1).toLowerCase() );
				primera=false;
			}else{
				sb.append( s.substring(0,1).toUpperCase() );
			}
			sb.append( s.substring(1).toLowerCase() );
		}		
		return sb.toString();
	}
}
