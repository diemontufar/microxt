package mobile.core.generator;

import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

import mobile.entity.common.EntityField;
import mobile.entity.common.EntityFieldId;
import mobile.entity.common.EntityFieldIdPk;
import mobile.entity.common.EntityFieldPk;
import mobile.entity.common.EntityRelationship;
import mobile.entity.common.EntityRelationshipPk;
import mobile.entity.common.EntityTable;
import mobile.entity.common.EntityTableId;
import mobile.entity.common.EntityTablePk;
import mobile.entity.manager.JPManager;
import mobile.entity.manager.JPManagerFactory;

import com.csvreader.CsvReader;

public class FillGeneratingEntitiesFromCsv {

	private final String COMPANY = "COMPANY_ID";
	private final String LANGUAGE = "LANGUAGE_ID";
	private final String EXPIRED = "EXPIRED";
	private final String CREATED = "CREATED";

	private String entityFilePath = "/home/ronald/Escritorio/entity.csv";

	private String fieldFilePath = "/home/ronald/Escritorio/field.csv";

	private String relationshipFilePath = "/home/ronald/Escritorio/relationship.csv";

	public FillGeneratingEntitiesFromCsv() {
		System.out.println("Load persistence...");
		JPManagerFactory.createEntityManagerFactory("generator");
		JPManager.createEntityManager();
	}

	protected void finalize() throws Throwable {
		System.out.println("Close persistence...");
		JPManager.close();
		JPManagerFactory.close();
	}

	public void fillTables() {
		CsvReader csvReader = null;

		try {
			JPManager.beginTransaction();

			// Csv reader
			File fichero = new File(entityFilePath);
			FileReader freader = new FileReader(fichero);
			csvReader = new CsvReader(freader);

			// Header
			System.out.println("-------Cabecera-------");
			csvReader.readHeaders();
			for (String header : csvReader.getHeaders()) {
				System.out.print(header + " ");
			}
			System.out.println();

			// Read entities
			System.out.println("-------Entidades-------");
			List<EntityTable> lentity = new ArrayList<EntityTable>();
			while (csvReader.readRecord()) {
				String companyId = csvReader.get("COMPANY_ID");
				String tableId = csvReader.get("TABLE_ID");
				boolean hasTableId = isTrue(csvReader.get("HAS_TABLE_ID"));
				String packageName = csvReader.get("PACKAGE_NAME");
				boolean multiCompany = isTrue(csvReader.get("MULTI_COMPANY"));
				boolean multiLanguage = isTrue(csvReader.get("MULTI_LANGUAGE"));
				boolean historical = isTrue(csvReader.get("HISTORICAL_DATA"));
				boolean optimistic = isTrue(csvReader.get("OPTIMISTIC_LOCKING"));
				boolean enumeratedTypes = isTrue(csvReader
						.get("ENUMERATED_TYPES"));
				boolean cacheMemory = isTrue(csvReader.get("CACHE_MEMORY"));
				String description = csvReader.get("DESCRIPTION");

				EntityTablePk pk = new EntityTablePk(tableId);
				pk.setCompanyId(companyId);

				EntityTable entity = new EntityTable(pk, hasTableId,
						packageName, multiCompany, multiLanguage, historical,
						optimistic, enumeratedTypes, cacheMemory, description);

				lentity.add(entity);

				System.out.println(entity);
			}

			// Ids
			System.out.println("-------Guardara tablas ID y campos ID-------");
			for (EntityTable entity : lentity) {
				EntityTableId id = null;
				EntityTableId table = null;

				if (entity.getHasTableId()) {
					id = new EntityTableId(entity.getPk().getTableId() + "_ID");
				}

				table = new EntityTableId(entity.getPk().getTableId());

				System.out.println(id);
				System.out.println(table);
				if (id != null) {
					JPManager.persist(id);
				}
				JPManager.persist(table);

				// Fields
				if (entity.getMultiCompany()) {
					EntityFieldIdPk pk = new EntityFieldIdPk(entity.getPk()
							.getTableId(), COMPANY);
					EntityFieldId fieldId = new EntityFieldId(pk);
					JPManager.persist(fieldId);
				}
				if (entity.getMultiLanguage()) {
					EntityFieldIdPk pk = new EntityFieldIdPk(entity.getPk()
							.getTableId(), LANGUAGE);
					EntityFieldId fieldId = new EntityFieldId(pk);
					JPManager.persist(fieldId);
				}
				if (entity.getHistoricalData()) {
					EntityFieldIdPk pk = new EntityFieldIdPk(entity.getPk()
							.getTableId(), EXPIRED);
					EntityFieldId fieldId = new EntityFieldId(pk);
					JPManager.persist(fieldId);
					pk = new EntityFieldIdPk(entity.getPk().getTableId(),
							CREATED);
					fieldId = new EntityFieldId(pk);
					JPManager.persist(fieldId);
				}
			}
			JPManager.getEntityManager().flush();

			// Entities
			System.out.println("-------Guardar tablas normales-------");
			for (EntityTable entity : lentity) {
				System.out.println(entity);
				JPManager.persist(entity);
			}

			// Commit transaction
			JPManager.commitTransaction();
		} catch (Exception e) {
			e.printStackTrace();
			JPManager.rollbackTransaction();
		} finally {
			System.out.println("-------Fin-------");
			if (csvReader != null) {
				csvReader.close();
			}
		}
	}

	public void fillFields() {
		CsvReader csvReader = null;

		try {
			JPManager.beginTransaction();

			// Csv reader
			File fichero = new File(fieldFilePath);
			FileReader freader = new FileReader(fichero);
			csvReader = new CsvReader(freader);
			csvReader.setSkipEmptyRecords(true);

			// Header
			System.out.println("-------Cabecera-------");
			csvReader.readHeaders();
			for (String header : csvReader.getHeaders()) {
				System.out.print(header + " ");
			}
			System.out.println();

			// Read fields
			System.out.println("-------Campos-------");
			List<EntityField> lfield = new ArrayList<EntityField>();
			while (csvReader.readRecord()) {
				String companyId = csvReader.get("COMPANY_ID");
				String tableId = csvReader.get("TABLE_ID");
				String fieldId = csvReader.get("FIELD_ID");
				Integer fieldOrder = Integer.valueOf(csvReader
						.get("FIELD_ORDER"));
				String dataType = csvReader.get("DATA_TYPE_ID");
				Integer dataSize = Integer.valueOf(csvReader.get("DATA_SIZE"));
				Integer dataScale = Integer
						.valueOf(csvReader.get("DATA_SCALE"));
				Boolean primaryKey = isTrue(csvReader.get("PRIMARY_KEY"));
				Boolean uniqueKey = isTrue(csvReader.get("UNIQUE_KEY"));
				Boolean nullable = isTrue(csvReader.get("NULLABLE"));
				String defaultValue = checkNull(csvReader.get("DEFAULT_VALUE"));
				String sequentialId = checkNull(csvReader.get("SEQUENTIAL_ID"));
				String minimumValue = checkNull(csvReader.get("MINIMUN_VALUE"));
				String maximumValue = checkNull(csvReader.get("MAXIMUM_VALUE"));
				String description = csvReader.get("DESCRIPTION");

				EntityFieldPk pk = new EntityFieldPk(tableId, fieldId);
				pk.setCompanyId(companyId);

				EntityField field = new EntityField(pk, fieldOrder, dataType,
						dataSize, dataScale, primaryKey, uniqueKey, nullable);
				field.setDefaultValue(defaultValue);
				field.setSequentialId(sequentialId);
				field.setMinimumValue(minimumValue);
				field.setMaximumValue(maximumValue);
				field.setDescription(description);

				lfield.add(field);

				System.out.println(field);
			}

			// Ids
			System.out
					.println("-------Guardar campos ID de la tabla ID-------");
			for (EntityField field : lfield) {
				if (field.getPrimaryKey() == true) {
					EntityFieldIdPk pk = new EntityFieldIdPk(field.getPk()
							.getTableId() + "_ID", field.getPk().getFieldId());
					EntityFieldId fieldId = new EntityFieldId(pk);

					System.out.println(fieldId);

					JPManager.persist(fieldId);
				}

			}
			System.out.println("-------Guardar campos ID-------");
			for (EntityField field : lfield) {
				EntityFieldIdPk pk = new EntityFieldIdPk(field.getPk()
						.getTableId(), field.getPk().getFieldId());
				EntityFieldId fieldId = new EntityFieldId(pk);

				System.out.println(fieldId);

				JPManager.persist(fieldId);
			}
			JPManager.getEntityManager().flush();

			// Entities
			System.out.println("-------Guardar campos normales-------");
			for (EntityField field : lfield) {
				System.out.println(field);
				JPManager.persist(field);
			}

			// Commit transaction
			JPManager.commitTransaction();
		} catch (Exception e) {
			e.printStackTrace();
			JPManager.rollbackTransaction();
		} finally {
			System.out.println("-------Fin-------");
			if (csvReader != null) {
				csvReader.close();
			}
		}
	}

	public void fillRelationships() {
		CsvReader csvReader = null;

		try {
			JPManager.beginTransaction();

			// CSV reader
			File fichero = new File(relationshipFilePath);
			FileReader freader = new FileReader(fichero);
			csvReader = new CsvReader(freader);
			csvReader.setSkipEmptyRecords(true);

			// Header
			System.out.println("-------Cabecera-------");
			csvReader.readHeaders();
			for (String header : csvReader.getHeaders()) {
				System.out.print(header + " ");
			}
			System.out.println();

			// Read fields
			System.out.println("-------Campos-------");
			List<EntityRelationship> lrelationship = new ArrayList<EntityRelationship>();
			
			while (csvReader.readRecord()) {
				String companyId = csvReader.get("COMPANY_ID");
				String relationshipId = csvReader.get("RELATIONSHIP_ID");
				Integer relationshipOrder = Integer.parseInt(csvReader.get("RELATIONSHIP_ORDER"));
				String tableFrom = csvReader.get("TABLE_FROM");
				String fieldFrom = csvReader.get("FIELD_FROM");
				String tableTo = csvReader.get("TABLE_TO");
				String fieldTo = csvReader.get("FIELD_TO");

				EntityRelationshipPk pk = new EntityRelationshipPk(relationshipId,relationshipOrder);
				pk.setCompanyId(companyId);

				EntityRelationship relationship = new EntityRelationship(pk, tableFrom, fieldFrom, tableTo, fieldTo);
				
				lrelationship.add(relationship);

				System.out.println(relationship);
			}

			// Relationships
			System.out.println("-------Guardar relaciones-------");
			for (EntityRelationship rel : lrelationship) {
				System.out.println(rel);
				JPManager.persist(rel);
			}

			// Commit transaction
			JPManager.commitTransaction();
		} catch (Exception e) {
			e.printStackTrace();
			JPManager.rollbackTransaction();
		} finally {
			System.out.println("-------Fin-------");
			if (csvReader != null) {
				csvReader.close();
			}
		}
	}

	private String checkNull(String input) {
		String res = null;

		if (!(input.trim().compareTo("") == 0
				|| input.compareToIgnoreCase("null") == 0 || input
					.compareToIgnoreCase("(null)") == 0)) {
			res = input;
		}

		return res;
	}

	private boolean isTrue(String in) {
		boolean res = false;
		if (in.compareTo("1") == 0 || in.compareToIgnoreCase("true") == 0) {
			res = true;
		}
		return res;
	}

	public static void main(String[] args){
		FillGeneratingEntitiesFromCsv filler = new FillGeneratingEntitiesFromCsv();

		try {
			filler.fillTables();
			filler.fillFields();
			filler.fillRelationships();
			filler.finalize();
		} catch (Throwable e) {
			e.printStackTrace();
		}
	}

}