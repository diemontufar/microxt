package mobile.core.generator;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.persistence.Query;
import javax.persistence.TypedQuery;

import mobile.entity.common.DatabaseType;
import mobile.entity.common.EntityField;
import mobile.entity.common.EntityFieldPk;
import mobile.entity.common.EntityRelationship;
import mobile.entity.common.EntityTable;
import mobile.entity.manager.JPManager;


public class SqlGeneratorAllinOneFile {

	// Company
	private final String COMPANY = "HNDT";
	
	// Name of the data base, for syntaxes conventions 
	// Actually supported: ORACLE: MYSQL
	private String dataBaseName;
	
	// For the output file
	private String outputFolder;
	
	// Sql for entities
	private String ENTITY_SQL = 
		"Select e from EntityTable e where e.pk.companyId in ('ALL',:company) ";
	
	// Sql for fields
	private String FIELD_SQL = 
			"Select f from EntityField f where "
			+ "f.pk.companyId in ('ALL',:company) and f.pk.tableId=:tableId "
			+ "order by f.fieldOrder";
	
	// NATIVE SQL for distinct relationships (drop fks)
	String RELATIONSHIP_IDS_SQL = 
			"select distinct r.RELATIONSHIP_ID, r.TABLE_FROM from ENTITY_RELATIONSHIP r where "
					+ "r.COMPANY_ID in ('ALL', :company) "
					+ "order by r.TABLE_FROM, r.RELATIONSHIP_ID";
	
	private String RELATIONSHIP_SQL =
		"Select r.* from ENTITY_RELATIONSHIP r where "
			+ "r.COMPANY_ID in ('ALL',:company) "
			+ "order by r.TABLE_FROM, r.RELATIONSHIP_ID, r.RELATIONSHIP_ORDER";
	
	// New line
	private final String NEW_LINE = "\n";
	
	// Tab
	private final String TAB = "\t";
	
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
	private Map<String,String> mDataType;
	
	// Concatenate symbol
	private final String UNION = "&"; 
	
	// Sql for data type
	private String DATA_TYPE_SQL = 
			"Select d from DatabaseType d where "
				+ "d.pk.databaseId = :databaseId ";

	/**
	 * Constructor
	 * 
	 * @param dbName the name of the database (MYSQL, ORACLE)
	 * @param outputFolder for the output file
	 */
	public SqlGeneratorAllinOneFile(String dbName, String outputFolder){
		this.dataBaseName = dbName;
		this.outputFolder = outputFolder;
		
		// Define company field
		companyField = new EntityField();
		EntityFieldPk cPk = new EntityFieldPk();
		cPk.setFieldId("COMPANY_ID");
		companyField.setPk(cPk);
		companyField.setFieldOrder(0);
		companyField.setDataTypeId("String");
		companyField.setDataSize(4);
		companyField.setDataScale(0);
		companyField.setPrimaryKey(true);
		companyField.setUniqueKey(false);
		companyField.setNullable(false);
		companyField.setDescription("Company id");
		companyField.setLabel("company");
		
		// Define language field
		languageField = new EntityField();
		EntityFieldPk lPk = new EntityFieldPk();
		lPk.setFieldId("LANGUAGE_ID");
		languageField.setPk(lPk);
		languageField.setFieldOrder(0);
		languageField.setDataTypeId("String");
		languageField.setDataSize(2);
		languageField.setDataScale(0);
		languageField.setPrimaryKey(true);
		languageField.setUniqueKey(false);
		languageField.setNullable(false);
		languageField.setDescription("Language id");
		languageField.setLabel("language");
		
		// Define expired field
		expiredField = new EntityField();
		EntityFieldPk ePk = new EntityFieldPk();
		ePk.setFieldId("EXPIRED");
		expiredField.setPk(ePk);
		expiredField.setFieldOrder(0);
		expiredField.setDataTypeId("Date");
		expiredField.setDataSize(0);
		expiredField.setDataScale(0);
		expiredField.setPrimaryKey(true);
		expiredField.setUniqueKey(false);
		expiredField.setNullable(false);
		expiredField.setDescription("Expiration date");
		expiredField.setLabel("expired");

		// Define created field
		createdField = new EntityField();
		EntityFieldPk dPk = new EntityFieldPk();
		dPk.setFieldId("CREATED");
		createdField.setPk(dPk);
		createdField.setFieldOrder(0);
		createdField.setDataTypeId("Date");
		createdField.setDataSize(0);
		createdField.setDataScale(0);
		createdField.setPrimaryKey(false);
		createdField.setUniqueKey(false);
		createdField.setNullable(false);
		createdField.setDescription("Creation date");
		createdField.setLabel("created");

		// Define created field
		versionField = new EntityField();
		EntityFieldPk vPk = new EntityFieldPk();
		vPk.setFieldId("VERSION");
		versionField.setPk(vPk);
		versionField.setFieldOrder(0);
		versionField.setDataTypeId("Long");
		versionField.setDataSize(0);
		versionField.setDataScale(0);
		versionField.setPrimaryKey(false);
		versionField.setUniqueKey(false);
		versionField.setNullable(false);
		versionField.setDescription("For optimistic locking");
		versionField.setLabel("version");
		versionField.setDefaultValue("0");
		
		// Fill data type map
		// Query data types
		List<DatabaseType> lTypes;
		TypedQuery<DatabaseType> query = JPManager.getEntityManager().createQuery(DATA_TYPE_SQL, DatabaseType.class);
		query.setParameter("databaseId", dataBaseName);
		lTypes = query.getResultList();
		
		mDataType = new HashMap<String, String>();
		for (DatabaseType d : lTypes) {
			String dataBaseType = "";
			dataBaseType = d.getDatabaseType();
			mDataType.put(d.getPk().getDataTypeId() + UNION + d.getPk().getDataSize(), dataBaseType);
		}
		
	}
	
	public void execute() throws Exception{
		// Query packages
		
		// Initialize builders
		sbSql = new StringBuilder();
		sbDropSql = new StringBuilder();

		// Drop script
		dropForeignKeys();
		dropTables();

		// Create script
		createTables();
		createRelationships();

		// Write files
		System.out.println("Writing file create.sql...");
		FileUtil.writeFile(outputFolder + "/create.sql", sbSql.toString());
		System.out.println("Writing file drop.sql...");
		FileUtil.writeFile(outputFolder + "/drop.sql", sbDropSql.toString());
	}
	
	@SuppressWarnings("rawtypes")
	public void dropForeignKeys(){
		// Query relationship ids
		List lRelationship;
		String finalQuery = RELATIONSHIP_IDS_SQL.replaceAll(":company", "'"+COMPANY+"'");
		Query query = JPManager.getEntityManager().createNativeQuery(finalQuery);
		lRelationship = query.getResultList();
		
		for (Iterator i = lRelationship.iterator(); i.hasNext();) {
			Object[] values = (Object[]) i.next();
			sbDropSql.append("ALTER TABLE ");
			sbDropSql.append(values[1]);
			sbDropSql.append(" DROP FOREIGN KEY ");
			sbDropSql.append(values[0]);
			sbDropSql.append(";" + NEW_LINE);
		}
	}
	
	public void dropTables(){
		// Query entities
		List<EntityTable> lEntity;
		TypedQuery<EntityTable> query = JPManager.getEntityManager().createQuery(ENTITY_SQL, EntityTable.class);
		query.setParameter("company", COMPANY);
		lEntity = query.getResultList();
		
		sbDropSql.append( NEW_LINE );
		for (EntityTable ent : lEntity) {
			if( ent.getHasTableId() ){
				sbDropSql.append("DROP TABLE " + ent.getPk().getTableId() + "_ID;" + NEW_LINE );
			}
			sbDropSql.append("DROP TABLE " + ent.getPk().getTableId() + ";" +NEW_LINE );
		}
	}
	
	public void createTables() throws Exception{
		// Query entities
		List<EntityTable> lEntity;
		TypedQuery<EntityTable> query = JPManager.getEntityManager().createQuery(ENTITY_SQL, EntityTable.class);
		query.setParameter("company", COMPANY);
		lEntity = query.getResultList();
		
		for (EntityTable ent : lEntity) {
			generateFields(ent);
		}		
	}

	private void generateFields(EntityTable ent) throws Exception{
		// Query fields
		List<EntityField> lFields;
		TypedQuery<EntityField> query = JPManager.getEntityManager().createQuery(FIELD_SQL, EntityField.class);
		query.setParameter("company", COMPANY);
		query.setParameter("tableId", ent.getPk().getTableId());
		lFields = query.getResultList();
		
		if (lFields.size() == 0) {
			//sbSql.append("--ERROR. TABLE: " + ent.getPk().getTableId() + ". NO FIELDS DEFINED" + NEW_LINE+NEW_LINE);
			throw new Exception("ERROR. TABLE: " + ent.getPk().getTableId() + ". NO FIELDS DEFINED" );
		} else {
			// Create ID TABLE
			if( ent.getHasTableId() ){
				sbSql.append(NEW_LINE);
				sbSql.append("CREATE TABLE " + ent.getPk().getTableId() + "_ID (" + NEW_LINE );
				// Fields
				for (EntityField f : lFields) {
					if( f.getPrimaryKey() ){
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
			
			sbSql.append("CREATE TABLE " + ent.getPk().getTableId() + " (" + NEW_LINE);
			
			// For completing all fields including inherited fields
			List<EntityField> lFieldsComplete = new ArrayList<EntityField>();
			// Define missing fields (company, tableId) for predefined fields
			if( ent.getMultiCompany() ){
				companyField.getPk().setCompanyId(ent.getPk().getCompanyId());
				companyField.getPk().setTableId(ent.getPk().getTableId());
			}
			if( ent.getMultiLanguage() ){
				languageField.getPk().setCompanyId(ent.getPk().getCompanyId());
				languageField.getPk().setTableId(ent.getPk().getTableId());
			}
			if( ent.getHistoricalData() ){
				expiredField.getPk().setCompanyId(ent.getPk().getCompanyId());
				expiredField.getPk().setTableId(ent.getPk().getTableId());
				createdField.getPk().setCompanyId(ent.getPk().getCompanyId());
				createdField.getPk().setTableId(ent.getPk().getTableId());
			}
			if( ent.getOptimisticLocking() ){
				versionField.getPk().setCompanyId(ent.getPk().getCompanyId());
				versionField.getPk().setTableId(ent.getPk().getTableId());
			}
			
			// Add special fields
			if( ent.getMultiCompany() ){
				lFieldsComplete.add(companyField);
			}
			if( ent.getMultiLanguage() ){
				lFieldsComplete.add(languageField);
			}
			
			if( ent.getHistoricalData() ){
				lFieldsComplete.add(expiredField);
			}
			// Add pk fields
			lFieldsComplete.addAll(getPkFields(lFields));
			// Add created field
			if( ent.getHistoricalData() ){
				lFieldsComplete.add(createdField);
			}
			// Add normal fields
			lFieldsComplete.addAll(getNormalFields(lFields));
			// Add version field
			if( ent.getOptimisticLocking() ){
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
		if( t.compareTo("BigDecimal") == 0 
				|| t.compareTo("Blov") == 0
				|| t.compareTo("Boolean") == 0
				|| t.compareTo("Clob") == 0
				|| t.compareTo("Date") == 0
				|| t.compareTo("Timestamp") == 0
				|| t.compareTo("String") == 0){
			dataTypeKey = field.getDataTypeId() + UNION + '0';
		}else{
			dataTypeKey = field.getDataTypeId() + UNION + field.getDataSize();
		}
		
		String dataBaseType = mDataType.get(dataTypeKey);
		
		if (dataBaseType != null) {
			sbField.append(" " + dataBaseType);
			// Data type size-scale
			String dataTypeSizeScale = "";
			if( field.getDataTypeId().compareTo("String")==0
					|| field.getDataTypeId().compareTo("BigDecimal")==0
					|| field.getDataTypeId().compareTo("Boolean")==0 ){
				if (field.getDataSize() != null
						&& field.getDataSize() != 0L) {
					dataTypeSizeScale = dataTypeSizeScale + field.getDataSize().toString();
				}
				if (field.getDataScale() != null
						&& field.getDataScale() != 0L) {
					dataTypeSizeScale = dataTypeSizeScale + "," + field.getDataScale().toString();
				}
			}
			
			if (dataTypeSizeScale != "") {
				sbField.append("(" + dataTypeSizeScale + ")");
			}
		} else {
			//sbField.append(" XXXXX");
			throw new Exception(
					"DATA TYPE ERROR. TABLE " + field.getPk().getTableId() + 
					" FIELD " + field.getPk().getFieldId());
		}
		
		// Nullable
		if (field.getNullable() != null
				&& !field.getNullable() ) {
			sbField.append(" NOT NULL");
		}
		
		// Default
		if (field.getDefaultValue() != null
				&& !(field.getDefaultValue().compareToIgnoreCase("null") == 0 || field
						.getDefaultValue()
						.compareToIgnoreCase("(null)") == 0)) {
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

	@SuppressWarnings("unchecked")
	public void createRelationships(){
		// Query relationships
		List<EntityRelationship> lRelationship;
		String finalQuery = RELATIONSHIP_SQL.replaceAll(":company", "'"+COMPANY+"'");
		Query query = JPManager.getEntityManager().createNativeQuery(finalQuery, EntityRelationship.class);
		//query.setParameter("company", COMPANY);
		//query.setParameter("packageName", packageName);
		lRelationship = (List<EntityRelationship>)query.getResultList();
		
		sbSql.append(NEW_LINE);
		for (int i = 0; i < lRelationship.size(); i++) {
			EntityRelationship rel = lRelationship.get(i);
			
			// Build fields (from and to)
			String strFieldsFrom = rel.getFieldFrom();
			String strFieldsTo = rel.getFieldTo();
			
			EntityRelationship nextRel;
			while( (i+1)<lRelationship.size()  
					&& (nextRel = lRelationship.get(i+1)).getPk().getRelationshipOrder()>1 ){
				strFieldsFrom = strFieldsFrom + ", " + nextRel.getFieldFrom();
				strFieldsTo = strFieldsTo + ", " + nextRel.getFieldTo();
				i++;
			}
			
			sbSql.append("ALTER TABLE " + rel.getTableFrom() + 
					" ADD CONSTRAINT " + rel.getPk().getRelationshipId() +
					" FOREIGN KEY (" + strFieldsFrom +
					") REFERENCES " + rel.getTableTo() + 
					" (" + strFieldsTo +
					");" + NEW_LINE
					);
		}		
	}

	private List<EntityField> getPkFields( List<EntityField> lFields ){
		List<EntityField> lPks = new ArrayList<EntityField>();
		
		for (EntityField field : lFields) {
			if(field.getPrimaryKey()){
				lPks.add(field);
			}
		}
		
		return lPks;
	}

	private List<EntityField> getNormalFields( List<EntityField> lFields ){
		List<EntityField> lNormal = new ArrayList<EntityField>();
		
		for (EntityField field : lFields) {
			if(!field.getPrimaryKey()){
				lNormal.add(field);
			}
		}
		
		return lNormal;
	}
}