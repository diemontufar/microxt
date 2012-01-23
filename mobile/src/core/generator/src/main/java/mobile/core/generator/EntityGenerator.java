package mobile.core.generator;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.TypedQuery;

import mobile.core.generator.util.EntityData;
import mobile.core.generator.util.PropertyData;
import mobile.entity.common.EntityField;
import mobile.entity.common.EntityTable;
import mobile.entity.common.EntityTablePk;
import mobile.entity.manager.JPManager;
import mobile.tools.common.FileUtil;
import mobile.tools.common.Log;

import org.apache.log4j.Logger;

enum EntityType {
	ENTITY, ENTITY_ID
}

public class EntityGenerator {

	// Company
	private final String COMPANY = "MXT";
	private final String ALL_COMPANY = "ALL";
	// Package for generated entities.
	private final String OUTPUT_PACKAGE = "mobile.entity";
	// Path to save the generated files and folders
	private String pathToSave;
	// Package of basic entity schema
	private String PATH_ENTITY_SCHEMA = "mobile.entity.schema";

	// QL to query entities
	private String ENTITY_QL = "Select e from EntityTable e where e.pk.companyId in ('ALL',:companyId)";
	// QL to query some entities
	private String ENTITY2_QL = "Select e from EntityTable e where e.pk.companyId in ('ALL',:companyId) "
			+ "and e.pk.tableId in :tables";
	// QL to query fields
	private String FIELD_SQL = "Select f from EntityField f where "
			+ "f.pk.companyId in ('ALL',:companyId) and f.pk.tableId=:tableId " + "order by f.fieldOrder";

	// Builder for entity
	private StringBuilder entityBuilder;
	// Builder for entity pk
	private StringBuilder pkEntityBuilder;
	// Builder for id
	private StringBuilder idEntityBuilder;
	// Builder for id pk
	private StringBuilder pkIdEntityBuilder;

	// New line
	private final String NEW_LINE = "\n";
	// Semicolon + new line
	private final String END_LINE = ";\n";
	
	// Logger
	Logger log = Log.getInstance();

	public EntityGenerator(String path) {
		this.pathToSave = path;
	}

	public void generateAllEntities() throws Exception {
		// Query all entities
		TypedQuery<EntityTable> queryEntities = JPManager.getEntityManager().createQuery(ENTITY_QL, EntityTable.class);
		queryEntities.setParameter("companyId", COMPANY);
		List<EntityTable> lEntity = queryEntities.getResultList();

		List<String> ltables = new ArrayList<String>();
		for (EntityTable entityTable : lEntity) {
			ltables.add(entityTable.getPk().getTableId());
		}

		generate(ltables);

	}

	public void generate(List<String> ltables) throws Exception {
		// Query entities
		TypedQuery<EntityTable> queryEntities = JPManager.getEntityManager().createQuery(ENTITY2_QL, EntityTable.class);
		queryEntities.setParameter("companyId", COMPANY);
		queryEntities.setParameter("tables", ltables);
		List<EntityTable> lEntity = queryEntities.getResultList();

		if (lEntity.size() < 1) {
			throw new Exception("NO ENTITIES DEFINED");
		}

		// For each entity generate the associated fields
		for (EntityTable entity : lEntity) {
			generateEntity(entity);
		}
	}

	public void generateOneEntity(String tableId) throws Exception {
		// Find the entity
		EntityTablePk pk = new EntityTablePk(tableId);
		pk.setCompanyId(ALL_COMPANY);

		EntityTable entity = JPManager.find(EntityTable.class, pk);

		if (entity == null) {
			throw new Exception("NO ENTITY INFORMATION");
		}

		generateEntity(entity);
	}

	private void generateEntity(EntityTable entity) throws Exception {
		// Query fields
		TypedQuery<EntityField> queryFields = JPManager.getEntityManager().createQuery(FIELD_SQL, EntityField.class);
		queryFields.setParameter("companyId", COMPANY);
		queryFields.setParameter("tableId", entity.getPk().getTableId());
		List<EntityField> lField = queryFields.getResultList();

		// Check fields definition
		if (lField.size() == 0) {
			throw new Exception("ERROR. ENTITY: " + entity.getPk().getTableId() + ". NO FIELDS DEFINED");
		} else {
			// Generate the builders
			generateBuilders(entity, lField);
			writeFiles(entity);
		}
	}

	private void writeFiles(EntityTable entity) throws Exception {
		// Entity
		String packageName = entity.getPackageName();
		String strFile = pathToSave + "/" + packageName + "/" + this.OUTPUT_PACKAGE.replace(".", "/") + "/"
				+ entity.getPackageName().replace(".", "/") + "/" + upperCamelCase(entity.getPk().getTableId())
				+ ".java";
		log.info("Writing " + strFile + "...");
		FileUtil.writeFile(strFile, entityBuilder.toString());

		// Entity Pk
		if (pkEntityBuilder != null) {
			String strPkFile = pathToSave + "/" + packageName + "/" + this.OUTPUT_PACKAGE.replace(".", "/") + "/"
					+ entity.getPackageName().replace(".", "/") + "/" + upperCamelCase(entity.getPk().getTableId())
					+ "Pk" + ".java";
			log.info("Writing " + strPkFile + "...");
			FileUtil.writeFile(strPkFile, pkEntityBuilder.toString());
		}

		// Entity Id
		if (idEntityBuilder != null) {
			String strIdFile = pathToSave + "/" + packageName + "/" + this.OUTPUT_PACKAGE.replace(".", "/") + "/"
					+ entity.getPackageName().replace(".", "/") + "/" + upperCamelCase(entity.getPk().getTableId())
					+ "Id" + ".java";
			log.info("Writing " + strIdFile + "...");
			FileUtil.writeFile(strIdFile, idEntityBuilder.toString());

			// Entity Id Pk
			if (pkIdEntityBuilder != null) {
				String strIdPkFile = pathToSave + "/" + packageName + "/" + this.OUTPUT_PACKAGE.replace(".", "/") + "/"
						+ entity.getPackageName().replace(".", "/") + "/" + upperCamelCase(entity.getPk().getTableId())
						+ "IdPk" + ".java";
				log.info("Writing " + strIdPkFile + "...");
				FileUtil.writeFile(strIdPkFile, pkIdEntityBuilder.toString());
			}
		}
	}

	private void generateBuilders(EntityTable entity, List<EntityField> lField) throws Exception {
		// Initialize builders
		entityBuilder = null;
		pkEntityBuilder = null;
		idEntityBuilder = null;
		pkIdEntityBuilder = null;

		// Entity
		EntityData entityData = generateEntityData(entity, lField, EntityType.ENTITY);
		entityBuilder = generateEntity(entity, lField, entityData, EntityType.ENTITY);

		// Generate entity id
		if (entity.getHasTableId()) {
			EntityTable idEntity = (EntityTable) entity.clone();
			idEntity.getPk().setTableId(entity.getPk().getTableId() + "_ID");
			idEntity.setHasTableId(false);
			idEntity.setMultiCompany(false);
			idEntity.setMultiLanguage(false);
			idEntity.setHistoricalData(false);
			idEntity.setOptimisticLocking(false);
			idEntity.setDescription(null);

			EntityData idEntityData = generateEntityData(idEntity, lField, EntityType.ENTITY_ID);
			idEntityBuilder = generateEntity(idEntity, lField, idEntityData, EntityType.ENTITY_ID);
		}
	}

	private EntityData generateEntityData(EntityTable entity, List<EntityField> lField, EntityType type)
			throws Exception {
		// --------------------------------
		// Generate the entity class data
		// --------------------------------
		EntityData entityData = new EntityData();
		entityData.getlPersistenceImports().add("javax.persistence.*");
		entityData.setClassName(upperCamelCase(entity.getPk().getTableId()));

		// Set entity extend
		if (type.compareTo(EntityType.ENTITY) == 0) {
			if (entity.getHistoricalData() && !entity.getOptimisticLocking()) {
				entityData.getlSchemaEntityImports().add("AbstractHistorical");
				entityData.setStrExtends("AbstractHistorical");
			} else if (!entity.getHistoricalData() && entity.getOptimisticLocking()) {
				entityData.getlSchemaEntityImports().add("AbstractOptimisticLocking");
				entityData.setStrExtends("AbstractOptimisticLocking");
			} else if (entity.getHistoricalData() && entity.getOptimisticLocking()) {
				entityData.getlSchemaEntityImports().add("AbstractHistoricalLocking");
				entityData.setStrExtends("AbstractHistoricalLocking");
			} else if (!entity.getHistoricalData() && !entity.getOptimisticLocking()) {
				entityData.getlSchemaEntityImports().add("AbstractEntity");
				entityData.setStrExtends("AbstractEntity");
			}
		} else {
			entityData.getlSchemaEntityImports().add("AbstractEntityId");
			entityData.setStrExtends("AbstractEntityId");
		}

		// Set implements
		if (type.compareTo(EntityType.ENTITY) == 0) {
			if (entity.getMultiCompany()) {
				entityData.getlSchemaEntityImports().add("Multicompany");
				entityData.getlImplements().add("Multicompany");
			}
			if (entity.getMultiLanguage()) {
				entityData.getlSchemaEntityImports().add("Multilanguage");
				entityData.getlImplements().add("Multilanguage");
			}
			if (entity.getHistoricalData()) {
				entityData.getlSchemaEntityImports().add("Historical");
				entityData.getlImplements().add("Historical");
			}
			if (entity.getOptimisticLocking()) {
				entityData.getlSchemaEntityImports().add("OptimisticLocking");
				entityData.getlImplements().add("OptimisticLocking");
			}
			if (!entity.getMultiCompany() && !entity.getMultiLanguage() && !entity.getHistoricalData()
					&& !entity.getOptimisticLocking()) {
				entityData.getlSchemaEntityImports().add("GeneralEntity");
				entityData.getlImplements().add("GeneralEntity");
			}
		} else {
			entityData.getlSchemaEntityImports().add("GeneralEntityId");
			entityData.getlImplements().add("GeneralEntityId");
		}

		// --------------------------
		// Generate properties
		// --------------------------
		// Generate pk
		// Evaluate if embeddedId is necessary
		List<EntityField> lPks = getPks(lField);

		switch (type) {
		case ENTITY:
			if (lPks.size() == 0) {
				// No pk defined
				throw new Exception("NO PK DEFINED FOR TABLE: " + entity.getPk().getTableId());
			} else if (lPks.size() == 1 && !entity.getMultiCompany() && !entity.getMultiLanguage()
					&& !entity.getHistoricalData()) {
				// Single pk field
				entityData.getlProperties().add(createSinglePkProperty(lPks.get(0)));
			} else if (lPks.size() > 1 || entity.getMultiCompany() || entity.getMultiLanguage()
					|| entity.getHistoricalData()) {
				// Embedded pk
				entityData.setHasEmbeddedPk(true);
				PropertyData pkData = createEmbeddedPkProperty(entityData);
				entityData.getlProperties().add(pkData);
				// Create pk class file
				pkEntityBuilder = createPkClassFile(entity, pkData, lPks, type);
			}
			break;
		case ENTITY_ID:
			if (lPks.size() == 1) {
				// Single pk field
				entityData.getlProperties().add(createSinglePkProperty(lPks.get(0)));
			} else if (lPks.size() > 1) {
				entityData.setHasEmbeddedPk(true);
				// Embedded pk
				PropertyData pkData = createEmbeddedPkProperty(entityData);
				entityData.getlProperties().add(pkData);
				// Create pk class file
				pkIdEntityBuilder = createPkClassFile(entity, pkData, lPks, type);
			}
			break;
		}

		// Generate properties
		switch (type) {
		case ENTITY:
			for (int i = 0; i < lField.size(); i++) {
				EntityField f = lField.get(i);
				if (!f.getPrimaryKey()) {
					entityData.getlProperties().add(createProperty(f));
					addGeneralImports(entityData, f);
				}
			}
			break;
		default:
			break;
		}

		return entityData;
	}

	private StringBuilder generateEntity(EntityTable entity, List<EntityField> lField, EntityData entityData,
			EntityType type) {
		StringBuilder builder;
		builder = new StringBuilder();

		// -----------------------------------------------
		// Generate entity file
		// -----------------------------------------------
		// Package
		builder.append("package " + this.OUTPUT_PACKAGE + "." + entity.getPackageName() + END_LINE);
		builder.append(NEW_LINE);

		// Imports
		// General imports
		if (entityData.getlGeneralImports().size() > 0) {
			for (String imp : entityData.getlGeneralImports()) {
				builder.append("import " + imp + END_LINE);
			}
			builder.append(NEW_LINE);
		}
		// Persistence imports
		if (entityData.getlPersistenceImports().size() > 0) {
			for (String imp : entityData.getlPersistenceImports()) {
				builder.append("import " + imp + END_LINE);
			}
			builder.append(NEW_LINE);
		}
		// Common imports
		if (entityData.getlSchemaEntityImports().size() > 0) {
			for (String imp : entityData.getlSchemaEntityImports()) {
				builder.append("import " + this.PATH_ENTITY_SCHEMA + "." + imp + END_LINE);
			}
			builder.append(NEW_LINE);
		}

		// General Java Doc
		builder.append("/**" + NEW_LINE);
		builder.append("* The persistent class for the " + entity.getPk().getTableId() + " database table." + NEW_LINE);
		if (entity.getDescription() != null) {
			builder.append("* " + entity.getDescription() + NEW_LINE);
		}
		builder.append("*/" + NEW_LINE);

		// Class annotations
		builder.append("@Entity" + NEW_LINE);
		builder.append("@Table(name=\"" + entity.getPk().getTableId() + "\")" + NEW_LINE);

		// Class definition
		builder.append("public class " + entityData.getClassName());
		// Extends
		builder.append(" extends " + entityData.getStrExtends());
		// Implements
		builder.append(" implements " + concatItems(entityData.getlImplements()));
		builder.append("{" + NEW_LINE);

		// SerialVersionUID
		builder.append("private static final long serialVersionUID = 1L" + END_LINE);
		builder.append(NEW_LINE);

		// Properties
		for (PropertyData property : entityData.getlProperties()) {
			if (property.getDescription() != null) {
				builder.append("/**" + NEW_LINE + "* " + property.getDescription() + NEW_LINE + "*/" + NEW_LINE);
			}
			builder.append(property.getAnnotation());
			builder.append("private " + property.getType() + " " + property.getName() + END_LINE);
			builder.append(NEW_LINE);
		}

		// Constructor (Empty parameters)
		builder.append("public " + entityData.getClassName() + "() {" + NEW_LINE + "}");
		builder.append(NEW_LINE);

		// Constructor (pk)
		builder.append("public " + entityData.getClassName() + "(" + entityData.getlProperties().get(0).getType() + " "
				+ entityData.getlProperties().get(0).getName() + ") {" + NEW_LINE);
		builder.append("this." + entityData.getlProperties().get(0).getName() + " = "
				+ entityData.getlProperties().get(0).getName() + END_LINE + "}" + NEW_LINE);

		// Constructor (pk, mandatory fields)
		if (type == EntityType.ENTITY) {
			List<EntityField> lMandatories = this.getMandatoryFields(lField);
			lMandatories.removeAll(getPks(lField));
			if (!lMandatories.isEmpty()) {
				String mandatoryParams = "";
				String assigned = "";
				for (EntityField field : lMandatories) {
					mandatoryParams = mandatoryParams + "," + field.getDataTypeId() + " "
							+ lowerCamelCase(field.getPk().getFieldId());
					assigned = assigned + "this." + lowerCamelCase(field.getPk().getFieldId()) + " = "
							+ lowerCamelCase(field.getPk().getFieldId() + END_LINE);
				}
				builder.append("public " + entityData.getClassName() + "("
						+ entityData.getlProperties().get(0).getType() + " "
						+ entityData.getlProperties().get(0).getName() + mandatoryParams + ") {" + NEW_LINE);
				builder.append("this." + entityData.getlProperties().get(0).getName() + " = "
						+ entityData.getlProperties().get(0).getName() + END_LINE + assigned + "}" + NEW_LINE);
			}
		}

		// Getters and setters
		for (PropertyData property : entityData.getlProperties()) {
			// Getter
			builder.append("public " + property.getType() + " get" + property.getName().substring(0, 1).toUpperCase()
					+ property.getName().substring(1));
			builder.append("() {" + NEW_LINE);
			builder.append("return this." + property.getName() + END_LINE);
			builder.append("}");
			builder.append(NEW_LINE);

			// Setter
			builder.append("public void set" + property.getName().substring(0, 1).toUpperCase()
					+ property.getName().substring(1));
			builder.append("(" + property.getType() + " " + property.getName() + ") {" + NEW_LINE);
			builder.append("this." + property.getName() + " = " + property.getName() + END_LINE);
			builder.append("}");
			builder.append(NEW_LINE);
		}

		// Override methods:
		// getPk
		if (entityData.getlProperties().get(0).getName().compareTo("pk") != 0) {
			builder.append(NEW_LINE);
			builder.append("@Override" + NEW_LINE);
			builder.append("public Object getPk() {" + NEW_LINE);
			builder.append("return this." + entityData.getlProperties().get(0).getName() + END_LINE);
			builder.append("}" + NEW_LINE);
		}

		// setPk
		builder.append(NEW_LINE);
		builder.append("@Override" + NEW_LINE);
		builder.append("public void setPk(Object pk) {" + NEW_LINE);
		builder.append("this." + entityData.getlProperties().get(0).getName() + "=" + "("
				+ entityData.getlProperties().get(0).getType() + ") pk" + END_LINE);
		builder.append("}" + NEW_LINE);

		// clone
		if (entityData.getHasEmbeddedPk()) {
			builder.append(NEW_LINE);
			builder.append("@Override" + NEW_LINE);
			builder.append("public Object clone() throws CloneNotSupportedException {" + NEW_LINE);
			builder.append(entityData.getClassName() + " copy = (" + entityData.getClassName() + ") super.clone()"
					+ END_LINE);
			builder.append("copy.setPk((" + entityData.getClassName() + "Pk) this.pk.clone())" + END_LINE);
			builder.append("return copy" + END_LINE);
			builder.append("}" + NEW_LINE);
		}

		// toString Method
		builder.append(NEW_LINE);
		builder.append("@Override" + NEW_LINE);
		builder.append("public String toString() {" + NEW_LINE);
		builder.append("return \"" + entity.getPk().getTableId() + ":[\" +" + NEW_LINE);
		builder.append("this.getPk().toString()");
		// toString Method - ExpiredField
		if (entity.getHistoricalData()) {
			builder.append(" + \", \" +" + NEW_LINE);
			builder.append("this.getCreated()");
		}
		if (entityData.getlProperties().size() > 1) {
			builder.append(" + \", \" +" + NEW_LINE);
			for (int i = 1; i < entityData.getlProperties().size(); i++) {
				PropertyData property = entityData.getlProperties().get(i);
				builder.append("this.get" + property.getName().substring(0, 1).toUpperCase()
						+ property.getName().substring(1) + "()");
				if (i < entityData.getlProperties().size() - 1) {
					builder.append(" + \", \" +" + NEW_LINE);
				}
			}
		}
		// toString Method - Optimistic locking field
		if (entity.getOptimisticLocking()) {
			builder.append(" + \", \" +" + NEW_LINE);
			builder.append("this.getVersion()");
		}
		builder.append(" + \"]\"" + END_LINE);
		builder.append("}" + NEW_LINE);
		// End toString method

		// End class
		builder.append("}" + NEW_LINE);

		return builder;
	}

	/**
	 * Checks entityField for some necessary import. If an import is required,
	 * it is added to entityData
	 * 
	 * @param entityData
	 * @param entityField
	 */
	private void addGeneralImports(EntityData entityData, EntityField entityField) {
		if (entityField.getDataTypeId().compareTo("BigDecimal") == 0) {
			addItem(entityData.getlGeneralImports(), "java.math.BigDecimal");
		} else if (entityField.getDataTypeId().compareTo("Date") == 0) {
			addItem(entityData.getlGeneralImports(), "java.sql.Date");
		} else if (entityField.getDataTypeId().compareTo("Blob") == 0) {
			addItem(entityData.getlGeneralImports(), "java.sql.Blob");
		} else if (entityField.getDataTypeId().compareTo("Clob") == 0) {
			addItem(entityData.getlGeneralImports(), "java.sql.Clob");
		}
		
		if(entityField.getPrimaryKey() && entityField.getSequentialId() != null){
			addItem(entityData.getlImplements(), "SequentialKey");
			addItem(entityData.getlSchemaEntityImports(), "SequentialKey");
		}
	}

	private void addItem(List<String> list, String item) {
		boolean exist = false;
		for (String string : list) {
			if (string.compareTo(item) == 0) {
				exist = true;
				break;
			}
		}
		if (!exist) {
			list.add(item);
		}
	}

	private StringBuilder createPkClassFile(EntityTable fatherEntity, PropertyData pkData, List<EntityField> lPks,
			EntityType type) {
		// --------------------------------
		// Generate the entity PK class data
		// --------------------------------
		EntityData entityPkData = new EntityData();

		entityPkData.getlPersistenceImports().add("javax.persistence.*");

		entityPkData.setClassName(pkData.getType());

		// Set entity extend
		// Use to identify the extends combination (MultiCompany MultiLanguage
		// Historical)
		String extendCode = "000";
		if (type.compareTo(EntityType.ENTITY) == 0) {
			if (fatherEntity.getMultiCompany()) {
				extendCode = "1";
			} else {
				extendCode = "0";
			}
			if (fatherEntity.getMultiLanguage()) {
				extendCode = extendCode + "1";
			} else {
				extendCode = extendCode + "0";
			}
			if (fatherEntity.getHistoricalData()) {
				extendCode = extendCode + "1";
			} else {
				extendCode = extendCode + "0";
			}
		}

		if (extendCode.compareTo("000") == 0) {
			entityPkData.getlSchemaEntityImports().add("AbstractEntityKey");
			entityPkData.setStrExtends("AbstractEntityKey");
		} else if (extendCode.compareTo("100") == 0) {
			entityPkData.getlSchemaEntityImports().add("AbstractCompanyKey");
			entityPkData.setStrExtends("AbstractCompanyKey");
		} else if (extendCode.compareTo("010") == 0) {
			entityPkData.getlSchemaEntityImports().add("AbstractLanguageKey");
			entityPkData.setStrExtends("AbstractLanguageKey");
		} else if (extendCode.compareTo("001") == 0) {
			entityPkData.getlSchemaEntityImports().add("AbstractHistoricalKey");
			entityPkData.setStrExtends("AbstractHistoricalKey");
		} else if (extendCode.compareTo("110") == 0) {
			entityPkData.getlSchemaEntityImports().add("AbstractCompanyLanguageKey");
			entityPkData.setStrExtends("AbstractCompanyLanguageKey");
		} else if (extendCode.compareTo("101") == 0) {
			entityPkData.getlSchemaEntityImports().add("AbstractCompanyHistoricalKey");
			entityPkData.setStrExtends("AbstractCompanyHistoricalKey");
		} else if (extendCode.compareTo("011") == 0) {
			entityPkData.getlSchemaEntityImports().add("AbstractLanguageHistoricalKey");
			entityPkData.setStrExtends("AbstractLanguageHistoricalKey");
		} else if (extendCode.compareTo("111") == 0) {
			entityPkData.getlSchemaEntityImports().add("AbstractCompanyLanguageHistoricalKey");
			entityPkData.setStrExtends("AbstractCompanyLanguageHistoricalKey");
		}

		// Set implements
		if (extendCode.substring(0, 1).compareTo("1") == 0) {
			entityPkData.getlSchemaEntityImports().add("MulticompanyKey");
			entityPkData.getlImplements().add("MulticompanyKey");
		}
		if (extendCode.substring(1, 2).compareTo("1") == 0) {
			entityPkData.getlSchemaEntityImports().add("MultilanguageKey");
			entityPkData.getlImplements().add("MultilanguageKey");
		}
		if (extendCode.substring(2, 3).compareTo("1") == 0) {
			entityPkData.getlSchemaEntityImports().add("HistoricalKey");
			entityPkData.getlImplements().add("HistoricalKey");
		}
		if (extendCode.compareTo("000") == 0) {
			entityPkData.getlSchemaEntityImports().add("GeneralEntityKey");
			entityPkData.getlImplements().add("GeneralEntityKey");
		}

		// --------------------------
		// Generate properties
		// --------------------------
		for (EntityField entityField : lPks) {
			entityPkData.getlProperties().add(createProperty(entityField));
			addGeneralImports(entityPkData, entityField);
		}

		// -----------------------------------------------
		// Generate entity class file
		// -----------------------------------------------
		StringBuilder pkClassFile = new StringBuilder();

		// Package
		pkClassFile.append("package " + this.OUTPUT_PACKAGE + "." + fatherEntity.getPackageName() + END_LINE);
		pkClassFile.append(NEW_LINE);

		// Imports
		// General imports
		if (entityPkData.getlGeneralImports().size() > 0) {
			for (String imp : entityPkData.getlGeneralImports()) {
				pkClassFile.append("import " + imp + END_LINE);
			}
			pkClassFile.append(NEW_LINE);
		}
		// Persistence imports
		if (entityPkData.getlPersistenceImports().size() > 0) {
			for (String imp : entityPkData.getlPersistenceImports()) {
				pkClassFile.append("import " + imp + END_LINE);
			}
			pkClassFile.append(NEW_LINE);
		}
		// Common imports
		if (entityPkData.getlSchemaEntityImports().size() > 0) {
			for (String imp : entityPkData.getlSchemaEntityImports()) {
				pkClassFile.append("import " + this.PATH_ENTITY_SCHEMA + "." + imp + END_LINE);
			}
			pkClassFile.append(NEW_LINE);
		}

		// General Java Doc
		pkClassFile.append("/**" + NEW_LINE);
		pkClassFile.append("* The primary key class for the " + fatherEntity.getPk().getTableId() + " database table."
				+ NEW_LINE);
		pkClassFile.append("*/" + NEW_LINE);

		// Class annotations
		pkClassFile.append("@Embeddable" + NEW_LINE);

		// Class definition
		pkClassFile.append("public class " + entityPkData.getClassName());
		// Extends
		pkClassFile.append(" extends " + entityPkData.getStrExtends());
		// Implements
		pkClassFile.append(" implements " + concatItems(entityPkData.getlImplements()));
		pkClassFile.append("{" + NEW_LINE);

		// SerialVersionUID
		pkClassFile.append("private static final long serialVersionUID = 1L" + END_LINE);
		pkClassFile.append(NEW_LINE);

		// Properties
		for (PropertyData property : entityPkData.getlProperties()) {
			if (property.getDescription() != null) {
				pkClassFile.append("/**" + NEW_LINE + "* " + property.getDescription() + NEW_LINE + "*/" + NEW_LINE);
			}
			pkClassFile.append(property.getAnnotation());
			pkClassFile.append("private " + property.getType() + " " + property.getName() + END_LINE);
			pkClassFile.append(NEW_LINE);
		}

		// Constructor (empty)
		pkClassFile.append("public " + entityPkData.getClassName() + "() {" + NEW_LINE + "}");
		pkClassFile.append(NEW_LINE);

		// Constructor (fields)
		String params = "";
		String assigned = "";
		for (EntityField field : lPks) {
			if (params.length() != 0) {
				params = params + ",";
			}
			params = params + field.getDataTypeId() + " " + lowerCamelCase(field.getPk().getFieldId());
			assigned = assigned + "this." + lowerCamelCase(field.getPk().getFieldId()) + " = "
					+ lowerCamelCase(field.getPk().getFieldId() + END_LINE);
		}
		pkClassFile.append("public " + entityPkData.getClassName() + "(" + params + ") {" + NEW_LINE + assigned + "}");
		pkClassFile.append(NEW_LINE);

		// Getters and setters
		for (PropertyData property : entityPkData.getlProperties()) {
			// Getter
			pkClassFile.append("public " + property.getType() + " get"
					+ property.getName().substring(0, 1).toUpperCase() + property.getName().substring(1));
			pkClassFile.append("() {" + NEW_LINE);
			pkClassFile.append("return this." + property.getName() + END_LINE);
			pkClassFile.append("}");
			pkClassFile.append(NEW_LINE);

			// Setter
			pkClassFile.append("public void set" + property.getName().substring(0, 1).toUpperCase()
					+ property.getName().substring(1));
			pkClassFile.append("(" + property.getType() + " " + property.getName() + ") {" + NEW_LINE);
			pkClassFile.append("this." + property.getName() + " = " + property.getName() + END_LINE);
			pkClassFile.append("}");
			pkClassFile.append(NEW_LINE);
		}
		
		// Sequential methods
		boolean hasSequential = false;
		PropertyData sequentialProp = null;
		
		for (PropertyData prop : entityPkData.getlProperties()) {
			if (prop.hasSequential()) {
				hasSequential = true;
				sequentialProp = prop;
				break;
			}
		}
		if(hasSequential){
			// Getter
			pkClassFile.append(NEW_LINE);
			pkClassFile.append("@Override" + NEW_LINE);
			pkClassFile.append("public Integer getId() {" + NEW_LINE);
			pkClassFile.append("return this." + sequentialProp.getName() + END_LINE);
			pkClassFile.append("}");
			pkClassFile.append(NEW_LINE);

			// Setter
			pkClassFile.append("@Override" + NEW_LINE);
			pkClassFile.append("public void setId(Integer sequentialNumber) {" + NEW_LINE);
			pkClassFile.append("this." + sequentialProp.getName() + " = sequentialNumber"+ END_LINE);
			pkClassFile.append("}");
			pkClassFile.append(NEW_LINE);
		}

		// toString Method
		pkClassFile.append(NEW_LINE);
		pkClassFile.append("@Override" + NEW_LINE);
		pkClassFile.append("public String toString() {" + NEW_LINE);
		pkClassFile.append("return \"[\" +" + NEW_LINE);

		if (extendCode.substring(0, 1).compareTo("1") == 0) {
			pkClassFile.append("this.getCompanyId()");
			pkClassFile.append(" + \", \" +" + NEW_LINE);
		}
		if (extendCode.substring(1, 2).compareTo("1") == 0) {
			pkClassFile.append("this.getLanguageId()");
			pkClassFile.append(" + \", \" +" + NEW_LINE);
		}
		if (extendCode.substring(2, 3).compareTo("1") == 0) {
			pkClassFile.append("this.getExpired()");
			pkClassFile.append(" + \", \" +" + NEW_LINE);
		}
		for (int i = 0; i < lPks.size(); i++) {
			PropertyData property = entityPkData.getlProperties().get(i);
			pkClassFile.append("this.get" + property.getName().substring(0, 1).toUpperCase()
					+ property.getName().substring(1) + "()");
			if (i < entityPkData.getlProperties().size() - 1) {
				pkClassFile.append(" + \", \" +" + NEW_LINE);
			}
		}

		pkClassFile.append(" + \"]\"" + END_LINE);
		pkClassFile.append("}" + NEW_LINE + "}" + NEW_LINE);

		return pkClassFile;
	}

	private List<EntityField> getPks(List<EntityField> fields) {
		List<EntityField> lPks = new ArrayList<EntityField>();

		for (EntityField f : fields) {
			if (f.getPrimaryKey()) {
				lPks.add(f);
			}
		}

		return lPks;
	}

	private List<EntityField> getMandatoryFields(List<EntityField> fields) {
		List<EntityField> lMandatory = new ArrayList<EntityField>();

		for (EntityField f : fields) {
			if (!f.getNullable()) {
				lMandatory.add(f);
			}
		}

		return lMandatory;
	}

	private PropertyData createSinglePkProperty(EntityField pkField) {
		// Pk Property
		PropertyData pk = new PropertyData();
		// Define annotations
		StringBuilder annotations = new StringBuilder();
		annotations.append("@Id" + NEW_LINE);
		annotations.append("@Column(name=\"" + pkField.getPk().getFieldId() + "\""
				+ ((pkField.getUniqueKey()) ? ", unique=true" : "")
				+ ((pkField.getNullable()) ? ", nullable=true" : ", nullable=false") + ")" + NEW_LINE);
		pk.setAnnotation(annotations);
		// Define data type
		pk.setType(pkField.getDataTypeId());
		// Define name
		pk.setName(this.lowerCamelCase(pkField.getPk().getFieldId()));
		pk.setDescription(pkField.getDescription());
		return pk;
	}

	private PropertyData createEmbeddedPkProperty(EntityData entityData) {
		// Define the property
		PropertyData pk = new PropertyData();
		// Define annotations
		StringBuilder annotations = new StringBuilder();
		annotations.append("@EmbeddedId" + NEW_LINE);
		pk.setAnnotation(annotations);
		// Define data type
		pk.setType(entityData.getClassName() + "Pk");
		// Define name
		pk.setName("pk");
		return pk;
	}

	private PropertyData createProperty(EntityField field) {
		PropertyData property = new PropertyData();
		// Define annotations
		StringBuilder annotations = new StringBuilder();
		annotations.append("@Column(name=\"" + field.getPk().getFieldId() + "\""
				+ ((field.getUniqueKey()) ? ", unique=true" : "")
				+ ((field.getNullable()) ? ", nullable=true" : ", nullable=false") + ")" + NEW_LINE);
		property.setAnnotation(annotations);
		// Define data type
		property.setType(field.getDataTypeId());
		// Define name
		property.setName(this.lowerCamelCase(field.getPk().getFieldId()));
		property.setDescription(field.getDescription());
		
		if(field.getPrimaryKey() && field.getSequentialId() != null){
			property.setHasSequential(true);
		}
		
		return property;
	}

	private String concatItems(List<String> list) {
		String union = "";
		for (String item : list) {
			if (!union.isEmpty()) {
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
