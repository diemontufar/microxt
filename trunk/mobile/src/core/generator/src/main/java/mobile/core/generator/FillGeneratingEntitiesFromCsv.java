package mobile.core.generator;

import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Query;

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

/**
 * Fills generating tables: <br>
 * <ul>
 * <li>EntityTable, EntityTableId</li>
 * <li>EtityField, EntityFieldId</li>
 * <li>EntityRelationship</li>
 * </ul>
 * These are used for generating parameterizing sql scripts and entity classes
 * from data in 3 CSV files (entity, field, relationship)
 */
public class FillGeneratingEntitiesFromCsv {

	private final String COMPANY = "COMPANY_ID";
	private final String LANGUAGE = "LANGUAGE_ID";
	private final String EXPIRED = "EXPIRED";
	private final String CREATED = "CREATED";

	private String entityFilePath = "/home/ronald/Escritorio/entity.csv";
	//private String entityFilePath = "C:/Users/diogonal/Desktop/entity.csv";
	private String fieldFilePath = "/home/ronald/Escritorio/field.csv";
	//private String fieldFilePath = "C:/Users/diogonal/Desktop/field.csv";
	private String relationshipFilePath = "/home/ronald/Escritorio/relationship.csv";
	//private String relationshipFilePath = "C:/Users/diogonal/Desktop/relationship.csv";

	public FillGeneratingEntitiesFromCsv() {
		System.out.println("Load persistence...");
		JPManagerFactory.createEntityManagerFactory("generator");
		JPManager.createEntityManager();
		JPManager.beginTransaction();
	}

	protected void finalize() throws Throwable {
		System.out.println("Commit changes...");
		JPManager.commitTransaction();
		System.out.println("Close persistence...");
		JPManager.close();
		JPManagerFactory.close();
	}

	public void fillTables() throws Exception {
		CsvReader csvReader = null;

		try {
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
			System.out.println("-------Guardar tablas ID-------");
			for (EntityTable entity : lentity) {
				EntityTableId tableId = null;
				EntityTableId table = null;

				if (entity.getHasTableId()) {
					tableId = new EntityTableId(entity.getPk().getTableId()
							+ "_ID");
				}

				table = new EntityTableId(entity.getPk().getTableId());

				System.out.println(tableId);
				System.out.println(table);
				if (tableId != null) {
					JPManager.persist(tableId);
				}
				JPManager.persist(table);
			}
			JPManager.getEntityManager().flush();

			// Special fields (companyId, languageId, expired, ...)
			System.out.println("-------Guardar campos especial-------");
			for (EntityTable entity : lentity) {
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

			JPManager.getEntityManager().flush();
		} catch (Exception e) {
			throw e;
		} finally {
			System.out.println("-------Fin-------");
			if (csvReader != null) {
				csvReader.close();
			}
		}
	}

	public void fillFields() throws Exception {
		CsvReader csvReader = null;

		try {
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

			JPManager.getEntityManager().flush();
		} catch (Exception e) {
			throw e;
		} finally {
			System.out.println("-------Fin-------");
			if (csvReader != null) {
				csvReader.close();
			}
		}
	}

	public void fillRelationships() throws Exception {
		CsvReader csvReader = null;

		try {
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
				Integer relationshipOrder = Integer.parseInt(csvReader
						.get("RELATIONSHIP_ORDER"));
				String tableFrom = csvReader.get("TABLE_FROM");
				String fieldFrom = csvReader.get("FIELD_FROM");
				String tableTo = csvReader.get("TABLE_TO");
				String fieldTo = csvReader.get("FIELD_TO");

				EntityRelationshipPk pk = new EntityRelationshipPk(
						relationshipId, relationshipOrder);
				pk.setCompanyId(companyId);

				EntityRelationship relationship = new EntityRelationship(pk,
						tableFrom, fieldFrom, tableTo, fieldTo);

				lrelationship.add(relationship);

				System.out.println(relationship);
			}

			// Relationships
			System.out.println("-------Guardar relaciones-------");
			for (EntityRelationship rel : lrelationship) {
				System.out.println(rel);
				JPManager.persist(rel);
			}
		} catch (Exception e) {
			throw e;
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

	public static void main(String[] args) {
		// Fill
		try {
			FillGeneratingEntitiesFromCsv filler = new FillGeneratingEntitiesFromCsv();
			filler.fillTables();
			filler.fillFields();
			filler.fillRelationships();
			filler.finalize();
		} catch (Throwable e) {
			e.printStackTrace();
			JPManager.rollbackTransaction();
		}

		// Remove
//		try {
//			FillGeneratingEntitiesFromCsv filler = new FillGeneratingEntitiesFromCsv();
//			filler.removeRelationships();
//			filler.removeFieldsAndEntities();
//			filler.finalize();
//		} catch (Throwable e) {
//			e.printStackTrace();
//			JPManager.rollbackTransaction();
//		}
	}

	private void removeRelationships() throws Exception {
		CsvReader csvReader = null;

		try {
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

			// Read relationships
			System.out.println("-------Relaciones-------");
			List<String> lrelationship = new ArrayList<String>();

			while (csvReader.readRecord()) {
				String relationshipId = csvReader.get("RELATIONSHIP_ID");
				Integer relationshipOrder = Integer.parseInt(csvReader
						.get("RELATIONSHIP_ORDER"));

				if (relationshipOrder == 1) {
					lrelationship.add(relationshipId);
				}
			}

			// Remove relationships
			System.out.println("-------Eliminar relaciones-------");
			String sql = "delete from EntityRelationship r WHERE r.pk.relationshipId in :lrelationship";
			Query query = JPManager.getEntityManager().createQuery(sql);
			query.setParameter("lrelationship", lrelationship);
			query.executeUpdate();
			JPManager.getEntityManager().flush();
		} catch (Exception e) {
			throw e;
		} finally {
			System.out.println("-------Fin-------");
			if (csvReader != null) {
				csvReader.close();
			}
		}

	}

	private void removeFieldsAndEntities() throws Exception {
		CsvReader csvReader = null;

		try {
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
			List<String> ltable = new ArrayList<String>();
			while (csvReader.readRecord()) {
				String tableId = csvReader.get("TABLE_ID");
				boolean hasTableId = isTrue(csvReader.get("HAS_TABLE_ID"));

				if (hasTableId == true) {
					ltable.add(tableId + "_ID");
				}
				ltable.add(tableId);
			}

			// Remove fields
			System.out.println("-------Eliminar relaciones-------");
			String sql = "delete from EntityField f WHERE f.pk.tableId in :ltable";
			Query query = JPManager.getEntityManager().createQuery(sql);
			query.setParameter("ltable", ltable);
			query.executeUpdate();
			JPManager.getEntityManager().flush();

			sql = "delete from EntityFieldId f WHERE f.pk.tableId in :ltable";
			query = JPManager.getEntityManager().createQuery(sql);
			query.setParameter("ltable", ltable);
			query.executeUpdate();
			JPManager.getEntityManager().flush();

			// Remove tables
			System.out.println("-------Eliminar tablas-------");
			sql = "delete from EntityTable a WHERE a.pk.tableId in :ltable";
			query = JPManager.getEntityManager().createQuery(sql);
			query.setParameter("ltable", ltable);
			query.executeUpdate();
			JPManager.getEntityManager().flush();

			sql = "delete from EntityTableId a WHERE a.tableId in :ltable";
			query = JPManager.getEntityManager().createQuery(sql);
			query.setParameter("ltable", ltable);
			query.executeUpdate();
			JPManager.getEntityManager().flush();

		} catch (Exception e) {
			throw e;
		} finally {
			System.out.println("-------Fin-------");
			if (csvReader != null) {
				csvReader.close();
			}
		}
	}
}