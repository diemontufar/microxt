package mobile.core.processor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.Query;

import mobile.common.message.EntityData;
import mobile.common.message.Field;
import mobile.common.message.Item;
import mobile.common.message.Message;
import mobile.entity.common.EntityRelationship;
import mobile.entity.common.EntityTable;
import mobile.entity.manager.JPManager;
import mobile.entity.manager.util.QueryUtil;
import mobile.tools.common.convertion.CoreConverter;
import mobile.tools.common.param.LocalParameter;
import mobile.tools.common.param.ParameterEnum;
import mobile.tools.common.param.Timer;
import mobile.tools.common.structure.QueryProcessor;

public class GeneralQuery implements QueryProcessor {

	private final String ORDER_DIR_DESC = "DESC";
	private String entity;
	private boolean normalQuery = true;

	private QueryUtil util;

	@Override
	public Message process(Message msg) throws Exception {
		for (EntityData data : msg.getEntityDataList()) {
			processQuery(data);
		}

		return msg;
	}

	@SuppressWarnings("rawtypes")
	private void processQuery(EntityData data) throws Exception {
		StringBuilder sql = new StringBuilder();

		// Main entity for querying
		entity = data.getDataId();

		// Decide if native query is necessary
		if (data.getQueryFields() != null && data.getQueryFields().indexOf(".") > 0) {
			normalQuery = false;
		}

		// Util
		util = new QueryUtil(normalQuery);

		// Build query
		sql.append("Select ");

		// ------------------------------------
		// Query fields
		// ------------------------------------
		List<String> queryFields = new ArrayList<String>();
		int fieldCounter = 0;
		boolean hasExpire = false;
		if (data.getQueryFields() != null) {
			for (String strField : data.getQueryFields().split(";")) {
				// Expire item
				if (strField.compareTo(Item.EXPIRE_ITEM) == 0) {
					hasExpire = true;
					continue;
				}
				// Description field
				if (strField.indexOf(".") > 0) {
					createInnerSelect(sql, queryFields, fieldCounter, strField);
					fieldCounter++;
					continue;
				}
				// Normal field
				queryFields.add(strField);
				if (fieldCounter > 0) {
					sql.append(", ");
				}

				if (normalQuery)
					sql.append("a." + strField.replaceAll("pk_", "pk."));
				else
					sql.append("a." + JPManager.toSqlName(strField.replaceAll("pk_", "")));
				fieldCounter++;
			}
		}

		// Filter fields
		if (data.getFilters() != null && !normalQuery) {
			String strFilters = data.getFilters();
			String[] lFilters = strFilters.split(";");
			for (String filter : lFilters) {
				String[] part = filter.split(":");
				String field = part[0];

				// Normal field
				// queryFields.add(field);
				if (fieldCounter > 0) {
					sql.append(", ");
				}

				if (normalQuery){
					//sql.append("a." + field.replaceAll("pk_", "pk."));
				}else{
					sql.append("a." + JPManager.toSqlName(field.replaceAll("pk_", "")));
				}
				//fieldCounter++;
			}
		}

		// From
		if (normalQuery)
			sql.append(" from " + data.getDataId() + " a");
		else
			sql.append(" from " + JPManager.toSqlName(data.getDataId()) + " a");

		// Set data types map
		// util.fillQueryTypes(entity);

		// ---------------------------------------------
		// Filters
		// ---------------------------------------------
		int parametersCounter = 0;
		List<Object> lParameters = new ArrayList<Object>();

		// Automatic filters
		int autoFiltersCounter = 0;
		EntityTable entityTable = JPManager.getEntityTable(data.getDataId());
		if (entityTable.getMultiCompany()) {
			if (autoFiltersCounter == 0) {
				sql.append(" where ");
			}
			if (autoFiltersCounter > 0) {
				sql.append(" and ");
			}
			if (normalQuery)
				sql.append("a.pk.companyId = ?" + (parametersCounter + 1));
			else
				sql.append("a.COMPANY_ID = ?" + (parametersCounter + 1));
			lParameters.add(LocalParameter.get(ParameterEnum.COMPANY, String.class));
			autoFiltersCounter++;
			parametersCounter++;
		}
		if (entityTable.getMultiLanguage()) {
			if (autoFiltersCounter == 0) {
				sql.append(" where ");
			}
			if (autoFiltersCounter > 0) {
				sql.append(" and ");
			}
			if (normalQuery)
				sql.append("a.pk.languageId = ?" + (parametersCounter + 1));
			else
				sql.append("a.LANGUAGE_ID = ?" + (parametersCounter + 1));
			lParameters.add(LocalParameter.get(ParameterEnum.LANGUAGE, String.class));
			autoFiltersCounter++;
			parametersCounter++;
		}
		if (entityTable.getHistoricalData()) {
			if (autoFiltersCounter == 0) {
				sql.append(" where ");
			}
			if (autoFiltersCounter > 0) {
				sql.append(" and ");
			}
			if (normalQuery)
				sql.append("a.pk.expired = ?" + (parametersCounter + 1));
			else
				sql.append("a.EXPIRED = ?" + (parametersCounter + 1));
			lParameters.add(Timer.getExpiredTime());
			autoFiltersCounter++;
			parametersCounter++;
		}


		// Request filters
		if (data.getFilters() != null) {
			if (normalQuery) {
				int filtersCounter = autoFiltersCounter;
				if (filtersCounter == 0) {
					sql.append(" where ");
				}

				String strFilters = data.getFilters();
				String[] lFilters = strFilters.split(";");
				for (String filter : lFilters) {
					if (filtersCounter > 0) {
						sql.append(" and ");
					}
					String[] part = filter.split(":");
					String field = part[0];
					String comparator = part[1];
					String value = part[2];
					Object cValue = CoreConverter.convertToType(value);

					comparator = util.analizeComparator(comparator);

					if (comparator.compareTo("like") == 0 && !comparator.endsWith("%")) {
						cValue = cValue + "%";
					}
					sql.append("a." + field.replaceAll("pk_", "pk.") + " " + comparator + " ?"
							+ (parametersCounter + 1));
					lParameters.add(cValue);
					filtersCounter++;
					parametersCounter++;
				}
			} else {
				sql.insert(0, "Select * from (");
				sql.append(") as alias0");

				int filtersCounter = 0;
				if (data.getFilters() != null) {
					sql.append(" where ");
					String strFilters = data.getFilters();
					String[] lFilters = strFilters.split(";");
					for (String filter : lFilters) {
						if (filtersCounter > 0) {
							sql.append(" and ");
						}
						String[] part = filter.split(":");
						String field = part[0];
						String comparator = part[1];
						String value = part[2];
						Object cValue = CoreConverter.convertToType(value);

						comparator = util.analizeComparator(comparator);
						if (comparator.compareTo("like") == 0 && !comparator.endsWith("%")) {
							cValue = value + "%";
						}
						sql.append(JPManager.toSqlName(field.replaceAll("pk_", "").replaceAll("\\.", "_")) + " "
								+ comparator + " ?" + (parametersCounter + 1));
						lParameters.add(cValue);
						filtersCounter++;
						parametersCounter++;
					}
				}
			}
		}

		// ---------------------------------------------
		// Ordering
		// ---------------------------------------------
		if (data.getOrderBy() != null) {
			if (normalQuery) {
				sql.append(" order by " + "a." + data.getOrderBy().replaceAll("pk_", "pk."));
			} else {
				sql.append(" order by "
						+ JPManager.toSqlName(data.getOrderBy().replaceAll("pk_", "").replaceAll("\\.", "_")));
			}

			if (data.getOrderDir() != null && data.getOrderDir().compareTo(ORDER_DIR_DESC) == 0) {
				sql.append(" DESC");
			}
		}

		// ---------------------------------------------
		// Query
		// ---------------------------------------------
		log.info("Query: " + sql.toString());

		Query query = null;

		if (normalQuery)
			query = JPManager.getEntityManager().createQuery(sql.toString());
		else
			query = JPManager.getEntityManager().createNativeQuery(sql.toString());

		// Set parameters
		parametersCounter = 0;
		for (Object object : lParameters) {
			query.setParameter(parametersCounter + 1, object);
			parametersCounter++;
		}

		// Get result
		List results = query.getResultList();

		// ---------------------------------------------
		// Pagination:
		// ---------------------------------------------
		int totalLength = results.size();

		int offset = 0;
		if (data.getOffset() != null) {
			offset = data.getOffset();
		}

		int limit = totalLength;
		if (data.getLimit() != null && data.getLimit() > 0) {
			limit = data.getLimit();
		}

		if (limit > 0) {
			limit = Math.min(offset + limit, totalLength);
		}

		// ---------------------------------------------
		// Fill items
		// ---------------------------------------------
		int itemCounter = 1;
		for (int i = offset; i < limit; i++) {
			Item item = new Item(itemCounter++);

			if (queryFields.size() > 1) {
				Object[] result = (Object[]) results.get(i);

				fieldCounter = 0;
				for (String qryField : queryFields) {
					Object resField = result[fieldCounter++];
					Field field = null;
					if (qryField.indexOf("=") < 0) {
						field = new Field(qryField);
					} else {
						int end = qryField.indexOf(":");
						field = new Field(qryField.substring(0, end));
					}

					if (resField != null) {
						String completedValue = CoreConverter.completeValue(resField);
						field.setValue(completedValue);
					}
					item.addField(field);
				}
			} else {
				String qryField = queryFields.get(0);
				Object result = results.get(i);
				Field field = new Field(qryField);
				if (result != null) {
					String completedValue = CoreConverter.completeValue(result);
					field.setValue(completedValue);
				}
				item.addField(field);
			}

			if (hasExpire) {
				item.addField(new Field(Item.EXPIRE_ITEM, "((Boolean))false"));
			}
			data.addItem(item);
		}

		data.setTotal(totalLength);
	}

	@SuppressWarnings("unchecked")
	private void createInnerSelect(StringBuilder sql, List<String> queryFields, int fieldCounter, String strField)
			throws Exception {
		String entity = null;
		String field = null;
		Map<String, List<String>> mdependency = null;

		if (strField.indexOf("=") < 0) {
			String[] part = strField.split("\\.");
			entity = part[0];
			field = part[1];
		} else {
			String[] superPart = strField.split(":");
			String[] basic = superPart[0].split("\\.");
			entity = basic[0];
			field = basic[1];
			String adeps = superPart[1];
			String[] dep = adeps.split("\\^");
			for (String d : dep) {
				String[] p = d.split("=");
				List<String> ldep = new ArrayList<String>();
				String filteredField = p[0];
				ldep.add(p[1]);
				ldep.add(p[2]);
				if (mdependency == null) {
					mdependency = new HashMap<String, List<String>>();
				}
				mdependency.put(filteredField, ldep);
			}
		}
		queryFields.add(entity + "_" + field);

		if (fieldCounter > 0) {
			sql.append(", ");
		}

		sql.append("(Select ");

		sql.append(JPManager.toSqlName(field.replaceAll("pk_", "")));

		// From
		sql.append(" from " + JPManager.toSqlName(entity));

		// Filters
		int filtersCounter = 0;
		if (mdependency == null) {
			// Filters from relationship
			String QL_RELATIONSHIP = "Select r from EntityRelationship r where r.tableFrom = :tableFrom and r.tableTo = :tableTo";
			Query query = JPManager.getEntityManager().createQuery(QL_RELATIONSHIP, EntityRelationship.class);
			query.setParameter("tableFrom", JPManager.toSqlName(this.entity));
			String tableTo = null;
			if (JPManager.getEntityTable(entity).getHasTableId()) {
				tableTo = JPManager.toSqlName(entity) + "_ID";
			}
			query.setParameter("tableTo", tableTo);
			List<EntityRelationship> list = query.getResultList();

			sql.append(" where ");
			for (EntityRelationship rel : list) {
				if (filtersCounter > 0) {
					sql.append(" and ");
				}
				sql.append(rel.getFieldTo() + "=a." + rel.getFieldFrom());
				filtersCounter++;
			}
		} else {
			// Filters from dependency
			sql.append(" where ");
			for (String filteredField : mdependency.keySet()) {
				List<String> toDep = mdependency.get(filteredField);
				if (filtersCounter > 0) {
					sql.append(" and ");
				}
				sql.append(JPManager.toSqlName(filteredField.replaceAll("pk_", "")) + "=a."
						+ JPManager.toSqlName(toDep.get(1).replaceAll("pk_", "")));
				filtersCounter++;
			}
		}

		// Automatic filters
		EntityTable entityTable = JPManager.getEntityTable(entity);
		if (entityTable.getHistoricalData()) {
			if (filtersCounter > 0) {
				sql.append(" and ");
			}
			sql.append("EXPIRED = '9999-12-31' ");
			filtersCounter++;
		}
		if (entityTable.getMultiCompany()) {
			if (filtersCounter > 0) {
				sql.append(" and ");
			}
			sql.append("COMPANY_ID = '" + LocalParameter.get(ParameterEnum.COMPANY, String.class) + "' ");
			filtersCounter++;
		}

		if (entity != null && field != null) {
			sql.append(") as " + JPManager.toSqlName(entity) + "_" + JPManager.toSqlName(field));
		} else {
			sql.append(") ");
		}
	}
}