package microxt.entity.generator;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.persistence.TypedQuery;

import microxt.entity.entity.Entity;
import microxt.entity.entity.EntityField;
import microxt.entity.util.JpaManager;
import microxt.tools.common.file.FileUtil;

public class EntityGenerator {
	
	private String company;
	
	private String pathToSave;

	private String ENTITY_SQL = "Select e from Entity e where e.pk.companyId=:companyId";
	
	private String FIELD_SQL = "Select f from EntityField f where "
			+ "f.pk.companyId=:companyId and f.pk.entityId=:entityId "
			+ "order by f.fieldOrder";

	private final String NEW_LINE = "\n";
	
	private final String TAB = "\t";
	
	private final String END_LINE = ";\n"; // Semicolon

	private StringBuffer buffer0;

	public EntityGenerator(String company, String path) {
		this.company = company;
		this.pathToSave = path;
		buffer0 = new StringBuffer();
	}

	public void execute() throws Exception {
		generateEntities();
		// System.out.println(buffer0);
		// System.out.println("Writing file...");
		// FileUtil.writeFile("/home/ronald/create.sql", buffer0.toString());
	}

	public void generateEntities() throws Exception {
		List<Entity> lEntity;

		TypedQuery<Entity> query = JpaManager.getEntityManager().createQuery(
				ENTITY_SQL, Entity.class);
		query.setParameter("companyId", company);
		lEntity = query.getResultList();

		for (Entity ent : lEntity) {
			generateFields(ent);
		}
		
		// ------------------------------------------------------------
		// Code Formatting
		// ------------------------------------------------------------
		//String[] formatParams = {};
		/*		String[] formatParams = {
				"--options=none", 
				"--style=java", 
				"--mode=java",
				"--indent=tab",
				"--fill-empty-lines",
				"--break-blocks", 
				"--pad-paren",  
				pathToSave+"/*" };
		Main.main(formatParams); 
		*/
	}

	/**
	 * @param ent
	 * @throws Exception 
	 */
	private void generateFields(Entity ent) throws Exception {
		List<EntityField> lField;

		TypedQuery<EntityField> query = JpaManager.getEntityManager()
				.createQuery(FIELD_SQL, EntityField.class);
		query.setParameter("companyId", company);
		query.setParameter("entityId", ent.getPk().getEntityId());
		// query.setLockMode(LockModeType.READ);
		lField = query.getResultList();

		if (lField.size() == 0) {
			System.out
					.println("==========================================================");
			System.out.println("ERROR. TABLE: " + ent.getPk().getEntityId()
					+ ". NO FIELDS DEFINED");
			System.out
					.println("==========================================================");
		} else {
			//--------------------------------
			//Generate the entity class data
			//--------------------------------
			
			EntityData entityData = new EntityData();

			entityData.getlGeneralImports().add("java.io.Serializable");
			entityData.getlPersistenceImports().add("javax.persistence.*");

			entityData.setClassName(upperCamelCase(ent.getPk().getEntityId()));

			//Set extend
			if( isTrue(ent.getHistoricalData()) &&
					!isTrue(ent.getMultiCompany()) &&
					!isTrue(ent.getMultiLanguage()) ){
				entityData.getlEntityCommonImports().add("microxt.entity.common.AbstractHistorical");
				entityData.setStrExtends("AbstractHistorical");
				//TODO falta AbstractHistoricalLocking
				//TODO falta OptimisticLocking
			}else{
				//If no extends
				entityData.getlEntityCommonImports().add("microxt.entity.common.AbstractEntity");
				entityData.setStrExtends("AbstractEntity");
			}
			
			//Set implements
			entityData.getlImplements().add("Serializable");
			entityData.getlImplements().add("Cloneable");
			if(isTrue(ent.getMultiCompany())){
				entityData.getlEntityCommonImports().add("microxt.entity.common.Multicompany");
				entityData.getlImplements().add("Multicompany");
			}
			if(isTrue(ent.getMultiLanguage())){
				entityData.getlEntityCommonImports().add("microxt.entity.common.Multilanguage");
				entityData.getlImplements().add("Multilanguage");
			}
			if(isTrue(ent.getHistoricalData())){
				entityData.getlEntityCommonImports().add("microxt.entity.common.Historical");
				entityData.getlImplements().add("Historical");
			}
			//TODO Optimistic

			//--------------------------
			//Generate properties
			//--------------------------
			// Generate pk
			// Evaluate if embeddedId es necesary
			List<EntityField> lPks = getPks(lField);
			
			if(lPks.size()==0){ 
				// No pk defined
				throw new Exception("NO PK DEFINED FOR TABLE: "+ent.getPk().getEntityId());
			}else if (lPks.size()==1){ 
				// Single pk field
				entityData.getlProperties().add(createSinglePkProperty(lPks.get(0)));
			}else if(lPks.size()>1){ //multiple pk 
				// Embedded pk
				entityData.getlProperties().add(createEmbeddedPkProperty(entityData));
				//Create the Pk Class
				//TODO metodo para crear la clase pk, createPkClass
			}

			for (int i = 0; i < lField.size(); i++) {
				EntityField f = lField.get(i);

				if (!isTrue(f.getPrimaryKey())) {
					entityData.getlProperties().add(createProperty(f));
				}

			}
			
			//-----------------------------------------------
			// Generate entity class file
			//-----------------------------------------------
			StringBuilder bClassFile = new StringBuilder();

			// Package
			bClassFile.append("package " + ent.getPackageName() + END_LINE);
			bClassFile.append(NEW_LINE);
			
			// Imports
			for (String imp : entityData.getlGeneralImports()) {
				bClassFile.append("import " + imp + END_LINE);
			}
			bClassFile.append(NEW_LINE);
			for (String imp : entityData.getlPersistenceImports()) {
				bClassFile.append("import " + imp + END_LINE);
			}
			bClassFile.append(NEW_LINE);
			for (String imp : entityData.getlEntityCommonImports()) {
				bClassFile.append("import " + imp + END_LINE);
			}
			bClassFile.append(NEW_LINE);
			bClassFile.append(NEW_LINE);
			
			// General Java Doc
			bClassFile.append("/**" + NEW_LINE);
			bClassFile.append("* The persistent class for the " 
					+ ent.getPk().getEntityId() + " database table." + NEW_LINE);
			bClassFile.append("*/" + NEW_LINE);

			// Class annotations
			bClassFile.append("@Entity" + NEW_LINE);
			bClassFile.append("@Table(name=\"" + ent.getPk().getEntityId() + "\")" + NEW_LINE);
			
			// Class definition
			bClassFile.append("public class " + entityData.getClassName());
			// Extends
			bClassFile.append(" extends " + entityData.getStrExtends() );
			// Implements
			bClassFile.append(" implements " + concatItems(entityData.getlImplements()) );
			bClassFile.append("{" + NEW_LINE );
			
			// SerialVersionUID
			bClassFile.append(TAB);
			bClassFile.append("private static final long serialVersionUID = 1L" + END_LINE );
			bClassFile.append(NEW_LINE);
			
			// Properties
			for (PropertyData property : entityData.getlProperties()) {
				bClassFile.append(property.getAnnotation());
				bClassFile.append("private " + property.getType() + " "
						+ property.getName() + END_LINE);
				bClassFile.append(NEW_LINE);
			}
			
			// Constructor
			bClassFile.append("public " + entityData.getClassName() + "() {"
					+ NEW_LINE + "}");
			bClassFile.append(NEW_LINE);
			
			// Getters and setters
			for (PropertyData property : entityData.getlProperties()) {
				//Getter
				bClassFile.append("public " + property.getType() 
						+ " get" + upperCamelCase(property.getName()));
				bClassFile.append("() {"+NEW_LINE);
				bClassFile.append(TAB);
				bClassFile.append("return this." + property.getName() + END_LINE);
				bClassFile.append("}");
				bClassFile.append(NEW_LINE);
				
				//Setter
				bClassFile.append("public void set" + upperCamelCase(property.getName()));
				bClassFile.append("(" 
						+ property.getType() + " "
						+ property.getName() + ") {"+NEW_LINE);
				bClassFile.append(TAB);
				bClassFile.append("this." + property.getName() 
						+ " = " + property.getName() + END_LINE);
				bClassFile.append("}");
				bClassFile.append(NEW_LINE);
			}
			
			// toString Method
			bClassFile.append("@Override" + NEW_LINE);
			bClassFile.append("public String toString() {" + NEW_LINE);
			bClassFile.append("return \"[\" +" + NEW_LINE);
			for (int i = 0; i < entityData.getlProperties().size(); i++) {
				PropertyData property =  entityData.getlProperties().get(i);
				bClassFile.append("this.getPk().toString() + \", \" +" + NEW_LINE);
				bClassFile.append("this.get"+upperCamelCase(property.getName())+"()");
				if( i < entityData.getlProperties().size() - 1){
					bClassFile.append("+ \", \" +"+NEW_LINE);
				}
			}
			bClassFile.append("+ \"]\"" + END_LINE);
			bClassFile.append("}" + NEW_LINE + "}" + NEW_LINE);

			//System.out.println(bClassFile);
			
			//-------------------------------------------------------------
			// Create class file(s) 
			//-------------------------------------------------------------
			System.out.println("Writing file...");
			String completePath = pathToSave;
			FileUtil.writeFile(pathToSave+"/"+entityData.getClassName()+".java", bClassFile.toString());
			
			//return;
		}
	}
	
	private boolean isTrue(String strBoolean){
		return strBoolean.trim().compareTo("1")==0; 
	}
	
	private List<EntityField> getPks(List<EntityField> fields) {
		List<EntityField> lPks = new ArrayList<EntityField>();
		
		for (EntityField f : fields) {
			if(isTrue(f.getPrimaryKey())){
				lPks.add(f);
			}
		}
		
		return lPks;
	}
	
	private PropertyData createSinglePkProperty(EntityField pkField) {
		// Pk Property
		PropertyData pk = new PropertyData();
		// Define annotations
		StringBuilder annotations = new StringBuilder();
		annotations.append("@Id"+NEW_LINE);
		annotations.append("@Column(name=\""+pkField.getPk().getEntityId()+"\"" +
				((isTrue(pkField.getUniqueKey()))?", unique=true":"")+
				((isTrue(pkField.getNullable()))?", nullable=true":"nullable=false")+")"+NEW_LINE);
		pk.setAnnotation(annotations);
		// Define data type
		pk.setType(pkField.getDataType().getDataTypeId());
		// Define name
		pk.setName("pk");
		return pk;
	}
	
	private PropertyData createEmbeddedPkProperty(EntityData entityData) {
		// Define the property
		PropertyData pk = new PropertyData();
		// Define annotations
		StringBuilder annotations = new StringBuilder();
		annotations.append("@EmbeddedId"+NEW_LINE);
		pk.setAnnotation(annotations);
		// Define data type
		pk.setType(entityData.getClassName()+"Pk");
		// Define name
		pk.setName("pk");
		return pk;
	}
	
	private PropertyData createProperty(EntityField field) {
		PropertyData property = new PropertyData();
		// Define annotations
		StringBuilder annotations = new StringBuilder();
		annotations.append("@Column(name=\""
				+ field.getPk().getEntityId()
				+ "\""
				+ ((isTrue(field.getUniqueKey())) ? ", unique=true" : "")
				+ ((isTrue(field.getNullable())) ? ", nullable=true" : "nullable=false") 
				+ ")" + NEW_LINE);
		property.setAnnotation(annotations);
		// Define data type
		property.setType(field.getDataType().getDataTypeId());
		// Define name
		property.setName("pk");
		return property;
	}
	
	private String concatItems(List<String> list) {
		String union = "";
		for (String item : list) {
			if(!union.isEmpty()){
				union = union.concat(", ");
			}
			union = union.concat(item);
		}
		return union;
	}

	private String upperCamelCase(String string) {
		StringBuilder sb = new StringBuilder();
		for (String s : string.split("_")) {
			sb.append(s.substring(0, 1).toUpperCase());
			sb.append(s.substring(1).toLowerCase());
		}
		return sb.toString();
	}

	private String lowerCamelCase(String string) {
		StringBuilder sb = new StringBuilder();
		Boolean primera = true;
		for (String s : string.split("_")) {
			if (primera) {
				sb.append(s.substring(0, 1).toLowerCase());
				primera = false;
			} else {
				sb.append(s.substring(0, 1).toUpperCase());
			}
			sb.append(s.substring(1).toLowerCase());
		}
		return sb.toString();
	}
}
