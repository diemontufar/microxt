package mobile.core.generator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.TypedQuery;

import mobile.core.generator.util.SpecialField;
import mobile.entity.common.DatabaseType;
import mobile.entity.common.EntityField;
import mobile.entity.common.EntityRelationship;
import mobile.entity.common.EntityTable;
import mobile.entity.manager.JPManager;
import mobile.tools.common.FileUtil;

public class SqlGenerator {

	// Company
	private final String COMPANY = "MXT";

	// Name of the data base, for syntaxes convention. Actually supported: MYSQL
	private String DATABASE_NAME = "MYSQL";

	// For the output file
	private String outputFolder;

	// Sql for data type
	private String DATA_TYPE_SQL = "Select d from DatabaseType d where "
			+ "d.pk.databaseId = :databaseId ";

	// QL for entities
	private String ENTITY_QL = "Select e from EntityTable e where e.pk.companyId in ('ALL',:company) "
			+ "order by e.packageName, e.pk.tableId";

	// QL for a list of entities
	private String ENTITY2_QL = "Select e from EntityTable e where e.pk.companyId in ('ALL',:company) "
			+ "and e.pk.tableId in :tables order by e.packageName, e.pk.tableId";

	// QL for fields
	private String FIELD_QL = "Select f from EntityField f where "
			+ "f.pk.companyId in ('ALL',:company) and f.pk.tableId=:tableId "
			+ "order by f.fieldOrder";

	// QL for relationships
	private String RELATIONSHIP2_QL = "select r from EntityRelationship r where "
			+ "r.pk.companyId in ('ALL', :company) "
			+ "and r.pk.relationshipOrder = 1 "
			+ "and r.tableFrom in :tables "
			+ "or r.tableTo in :tables "
			+ "order by r.tableFrom, r.pk.relationshipId";

	// String constants
	private final String NEW_LINE = "\n";
	private final String TAB = "\t";
	private final String UNION = "&";

	// For building the creation script
	private StringBuilder sbSql;

	// For building the drop script
	private StringBuilder sbDropSql;

	// Special Fields
	private EntityField companyField;
	private EntityField languageField;
	private EntityField expiredField;
	private EntityField createdField;
	private EntityField versionField;

	// Map for data types
	private Map<String, String> mDataType;

	/**
	 * Constructor
	 * 
	 * @param dbName
	 *            the name of the database (MYSQL, ORACLE)
	 * @param outputFolder
	 *            for the output file
	 */
	public SqlGenerator(String outputFolder) {
		this.outputFolder = outputFolder;

		// Define special fields
		SpecialField sf = new SpecialField();
		companyField = sf.getCompanyField();
		languageField = sf.getLanguageField();
		expiredField = sf.getExpiredField();
		createdField = sf.getCreatedField();
		versionField = sf.getVersionField();

		// Initialize builders
		sbSql = new StringBuilder();
		sbDropSql = new StringBuilder();

		// Fill data type map
		fillDataTypes();
	}

	private void fillDataTypes() {
		// Query data types
		List<DatabaseType> lTypes;
		TypedQuery<DatabaseType> query = JPManager.getEntityManager()
				.createQuery(DATA_TYPE_SQL, DatabaseType.class);
		query.setParameter("databaseId", DATABASE_NAME);
		lTypes = query.getResultList();

		mDataType = new HashMap<String, String>();
		for (DatabaseType d : lTypes) {
			String dataBaseType = "";
			dataBaseType = d.getDatabaseType();
			mDataType.put(d.getPk().getDataTypeId() + UNION
					+ d.getPk().getDataSize(), dataBaseType);
		}

	}

	public void generateAllTables() throws Exception {
		// Query all entities
		TypedQuery<EntityTable> query = JPManager.getEntityManager()
				.createQuery(ENTITY_QL, EntityTable.class);
		query.setParameter("company", COMPANY);
		List<EntityTable> lEntity = query.getResultList();

		List<String> ltables = new ArrayList<String>();
		for (EntityTable entity : lEntity) {
			ltables.add(entity.getPk().getTableId());
		}

		// drop foreign keys
		generate(ltables);
	}

	public void generate(List<String> ltables) throws Exception {
		// Drop script
		dropForeignKeys(ltables);
		dropTables(ltables);

		// Create script
		createTables(ltables);
		createForeignKeys(ltables);

		// Write files
		System.out.println("Writing file create.sql...");
		FileUtil.writeFile(outputFolder + "/create.sql", sbSql.toString());
		System.out.println("Writing file drop.sql...");
		FileUtil.writeFile(outputFolder + "/drop.sql", sbDropSql.toString());
	}

	private void dropForeignKeys(List<String> ltables) {
		// Query relationships
		List<EntityRelationship> lRelationship;
		TypedQuery<EntityRelationship> query = JPManager.getEntityManager()
				.createQuery(RELATIONSHIP2_QL, EntityRelationship.class);
		query.setParameter("company", COMPANY);
		query.setParameter("tables", completeIdTables(ltables));
		lRelationship = query.getResultList();

		for (EntityRelationship rel : lRelationship) {
			sbDropSql.append("ALTER TABLE ");
			sbDropSql.append(rel.getTableFrom());
			sbDropSql.append(" DROP FOREIGN KEY ");
			sbDropSql.append(rel.getPk().getRelationshipId());
			sbDropSql.append(";" + NEW_LINE);
		}
	}

	private List<String> completeIdTables(List<String> ltables) {
		List<String> lIdTables = new ArrayList<String>();
		for (String table : ltables) {
			lIdTables.add(table+"_ID");
		}
		List<String> finalList = new ArrayList<String>();
		finalList.addAll(ltables);
		finalList.addAll(lIdTables);
		return finalList;
	}

	private void dropTables(List<String> ltables) {
		// Query entities
		TypedQuery<EntityTable> query = JPManager.getEntityManager()
				.createQuery(ENTITY2_QL, EntityTable.class);
		query.setParameter("company", COMPANY);
		query.setParameter("tables", ltables);
		List<EntityTable> lEntity = query.getResultList();

		sbDropSql.append(NEW_LINE);
		for (EntityTable ent : lEntity) {
			if (ent.getHasTableId()) {
				sbDropSql.append("DROP TABLE " + ent.getPk().getTableId()
						+ "_ID;" + NEW_LINE);
			}
			sbDropSql.append("DROP TABLE " + ent.getPk().getTableId() + ";"
					+ NEW_LINE);
		}
	}

	private void createTables(List<String> ltables) throws Exception {
		// Query entities
		List<EntityTable> lEntity;
		TypedQuery<EntityTable> query = JPManager.getEntityManager()
				.createQuery(ENTITY2_QL, EntityTable.class);
		query.setParameter("company", COMPANY);
		query.setParameter("tables", ltables);
		lEntity = query.getResultList();

		for (EntityTable ent : lEntity) {
			generateFields(ent);
		}
	}

	private void generateFields(EntityTable ent) throws Exception {
		// Query fields
		List<EntityField> lFields;
		TypedQuery<EntityField> query = JPManager.getEntityManager()
				.createQuery(FIELD_QL, EntityField.class);
		query.setParameter("company", COMPANY);
		query.setParameter("tableId", ent.getPk().getTableId());
		lFields = query.getResultList();

		if (lFields.size() == 0) {
			// sbSql.append("--ERROR. TABLE: " + ent.getPk().getTableId() +
			// ". NO FIELDS DEFINED" + NEW_LINE+NEW_LINE);
			throw new Exception("ERROR. TABLE: " + ent.getPk().getTableId()
					+ ". NO FIELDS DEFINED");
		} else {
			// Create ID TABLE
			if (ent.getHasTableId()) {
				sbSql.append(NEW_LINE);
				sbSql.append("CREATE TABLE " + ent.getPk().getTableId()
						+ "_ID (" + NEW_LINE);
				// Fields
				for (EntityField f : lFields) {
					if (f.getPrimaryKey()) {
						sbSql.append(buildSqlField(f));
					}
				}
				// Pks
				sbSql.append(buildSqlPrimaryKey(lFields));
				// End
				sbSql.append(");" + NEW_LINE);
			}

			// Create NORMAL TABLE
			sbSql.append(NEW_LINE);

			sbSql.append("CREATE TABLE " + ent.getPk().getTableId() + " ("
					+ NEW_LINE);

			// For completing all fields including inherited fields
			List<EntityField> lFieldsComplete = new ArrayList<EntityField>();
			// Define missing fields (company, tableId) for predefined fields
			if (ent.getMultiCompany()) {
				companyField.getPk().setCompanyId(ent.getPk().getCompanyId());
				companyField.getPk().setTableId(ent.getPk().getTableId());
			}
			if (ent.getMultiLanguage()) {
				languageField.getPk().setCompanyId(ent.getPk().getCompanyId());
				languageField.getPk().setTableId(ent.getPk().getTableId());
			}
			if (ent.getHistoricalData()) {
				expiredField.getPk().setCompanyId(ent.getPk().getCompanyId());
				expiredField.getPk().setTableId(ent.getPk().getTableId());
				createdField.getPk().setCompanyId(ent.getPk().getCompanyId());
				createdField.getPk().setTableId(ent.getPk().getTableId());
			}
			if (ent.getOptimisticLocking()) {
				versionField.getPk().setCompanyId(ent.getPk().getCompanyId());
				versionField.getPk().setTableId(ent.getPk().getTableId());
			}

			// Add special fields
			if (ent.getMultiCompany()) {
				lFieldsComplete.add(companyField);
			}
			if (ent.getMultiLanguage()) {
				lFieldsComplete.add(languageField);
			}

			if (ent.getHistoricalData()) {
				lFieldsComplete.add(expiredField);
			}
			// Add pk fields
			lFieldsComplete.addAll(getPkFields(lFields));
			// Add created field
			if (ent.getHistoricalData()) {
				lFieldsComplete.add(createdField);
			}
			// Add normal fields
			lFieldsComplete.addAll(getNormalFields(lFields));
			// Add version field
			if (ent.getOptimisticLocking()) {
				lFieldsComplete.add(versionField);
			}

			// Build all fields
			for (EntityField f : lFieldsComplete) {
				sbSql.append(buildSqlField(f));
			}

			// Pks
			sbSql.append(buildSqlPrimaryKey(lFieldsComplete));

			// End
			sbSql.append(");" + NEW_LINE);
		}
	}

	private StringBuilder buildSqlField(EntityField field) throws Exception {
		StringBuilder sbField = new StringBuilder();

		sbField.append(TAB);

		// Name
		sbField.append(field.getPk().getFieldId());

		// Data type
		String dataTypeKey = "";

		// Special types
		String t = field.getDataTypeId();
		if (t.compareTo("BigDecimal") == 0 || t.compareTo("Blov") == 0
				|| t.compareTo("Boolean") == 0 || t.compareTo("Clob") == 0
				|| t.compareTo("Date") == 0 || t.compareTo("Timestamp") == 0
				|| t.compareTo("String") == 0) {
			dataTypeKey = field.getDataTypeId() + UNION + '0';
		} else {
			dataTypeKey = field.getDataTypeId() + UNION + field.getDataSize();
		}

		String dataBaseType = mDataType.get(dataTypeKey);

		if (dataBaseType != null) {
			sbField.append(" " + dataBaseType);
			// Data type size-scale
			String dataTypeSizeScale = "";
			if (field.getDataTypeId().compareTo("String") == 0
					|| field.getDataTypeId().compareTo("BigDecimal") == 0
					|| field.getDataTypeId().compareTo("Boolean") == 0) {
				if (field.getDataSize() != null && field.getDataSize() != 0L) {
					dataTypeSizeScale = dataTypeSizeScale
							+ field.getDataSize().toString();
				}
				if (field.getDataScale() != null && field.getDataScale() != 0L) {
					dataTypeSizeScale = dataTypeSizeScale + ","
							+ field.getDataScale().toString();
				}
			}

			if (dataTypeSizeScale != "") {
				sbField.append("(" + dataTypeSizeScale + ")");
			}
		} else {
			// sbField.append(" XXXXX");
			throw new Exception("DATA TYPE ERROR. TABLE "
					+ field.getPk().getTableId() + " FIELD "
					+ field.getPk().getFieldId());
		}

		// Nullable
		if (field.getNullable() != null && !field.getNullable()) {
			sbField.append(" NOT NULL");
		}

		// Default
		if (field.getDefaultValue() != null
				&& !(field.getDefaultValue().compareToIgnoreCase("null") == 0 || field
						.getDefaultValue().compareToIgnoreCase("(null)") == 0)) {
			sbField.append(" DEFAULT '" + field.getDefaultValue() + "'");
		}

		// End
		sbField.append("," + NEW_LINE);
		return sbField;
	}

	public StringBuilder buildSqlPrimaryKey(List<EntityField> lFields) {
		StringBuilder sbPk = new StringBuilder();
		sbPk.append(TAB);
		sbPk.append("PRIMARY KEY (");

		String pks = "";
		for (EntityField f : lFields) {
			if (f.getPrimaryKey() != null && f.getPrimaryKey()) {
				if (!pks.isEmpty()) {
					pks = pks.concat(", ");
				}
				pks = pks.concat(f.getPk().getFieldId());
			}
		}

		sbPk.append(pks);
		sbPk.append(")" + NEW_LINE);

		return sbPk;
	}

	private void createForeignKeys(List<String> ltables) {
		// Query relationships
		List<EntityRelationship> lRelationship;
		TypedQuery<EntityRelationship> query = JPManager.getEntityManager()
				.createQuery(RELATIONSHIP2_QL, EntityRelationship.class);
		query.setParameter("company", COMPANY);
		query.setParameter("tables", completeIdTables(ltables));
		lRelationship = query.getResultList();

		sbSql.append(NEW_LINE);
		for (int i = 0; i < lRelationship.size(); i++) {
			EntityRelationship rel = lRelationship.get(i);

			// Build fields (from and to)
			String strFieldsFrom = rel.getFieldFrom();
			String strFieldsTo = rel.getFieldTo();

			EntityRelationship nextRel;
			while ((i + 1) < lRelationship.size()
					&& (nextRel = lRelationship.get(i + 1)).getPk()
							.getRelationshipOrder() > 1) {
				strFieldsFrom = strFieldsFrom + ", " + nextRel.getFieldFrom();
				strFieldsTo = strFieldsTo + ", " + nextRel.getFieldTo();
				i++;
			}

			sbSql.append("ALTER TABLE " + rel.getTableFrom()
					+ " ADD CONSTRAINT " + rel.getPk().getRelationshipId()
					+ " FOREIGN KEY (" + strFieldsFrom + ") REFERENCES "
					+ rel.getTableTo() + " (" + strFieldsTo + ");" + NEW_LINE);
		}
	}

	private List<EntityField> getPkFields(List<EntityField> lFields) {
		List<EntityField> lPks = new ArrayList<EntityField>();

		for (EntityField field : lFields) {
			if (field.getPrimaryKey()) {
				lPks.add(field);
			}
		}

		return lPks;
	}

	private List<EntityField> getNormalFields(List<EntityField> lFields) {
		List<EntityField> lNormal = new ArrayList<EntityField>();

		for (EntityField field : lFields) {
			if (!field.getPrimaryKey()) {
				lNormal.add(field);
			}
		}

		return lNormal;
	}
}