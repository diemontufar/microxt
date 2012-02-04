package mobile.core.processor;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.Query;
import javax.persistence.TypedQuery;

import mobile.common.message.EntityData;
import mobile.common.message.Field;
import mobile.common.message.Item;
import mobile.common.message.Message;
import mobile.common.tools.ProcessType;
import mobile.entity.common.EntityField;
import mobile.entity.common.EntityRelationship;
import mobile.entity.common.EntityTable;
import mobile.entity.manager.JPManager;
import mobile.tools.common.Log;
import mobile.tools.common.convertion.Converter;
import mobile.tools.common.convertion.FormatDates;
import mobile.tools.common.param.LocalParameter;
import mobile.tools.common.param.ParameterEnum;
import mobile.tools.common.param.Timer;
import mobile.tools.common.structure.GeneralProcessor;

import org.apache.log4j.Logger;

public class QueryProcessor implements GeneralProcessor {

	private final String ORDER_DIR_DESC = "DESC";
	private final Logger log = Log.getInstance();
	private String entity;
	Map<String, Map<String, String>> superMtypes = new HashMap<String, Map<String, String>>();
	boolean normalQuery = true;

	@Override
	public Message process(Message msg) throws Exception {
		for (EntityData data : msg.getEntityDataList()) {
			if (data.getProcessType() != null && data.getProcessType().compareTo(ProcessType.QUERY.getShortName()) == 0) {
				processQuery(data);
			}
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

		// Build query
		sql.append("Select ");

		// Query fields
		List<String> queryFields = new ArrayList<String>();
		boolean hasExpire = false;
		int fieldCounter = 0;
		if (data.getQueryFields() != null) {
			for (String strField : data.getQueryFields().split(";")) {
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
				queryFields.add(strField);
				if (fieldCounter > 0) {
					sql.append(", ");
				}

				if (normalQuery)
					sql.append("a." + strField.replaceAll("pk_", "pk."));
				else
					sql.append("a." + toSqlName(strField.replaceAll("pk_", "")));
				fieldCounter++;
			}
		}

		// From
		if (normalQuery)
			sql.append(" from " + data.getDataId() + " a");
		else
			sql.append(" from " + toSqlName(data.getDataId()) + " a");

		// Fill query types
		Map<String, String> mtypes = getMapTypeFields(entity);
		superMtypes.put(entity, mtypes);

		// Filters
		List<Object> lParameters = new ArrayList<Object>();
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
				if (comparator == null || (comparator != null && comparator.compareTo("") == 0)) {
					if (normalQuery) {
						sql.append("a." + field.replaceAll("pk_", "pk.") + " like ?" + (filtersCounter + 1));
						lParameters.add(value + "%");
					} else {
						sql.append("a." + toSqlName(field.replaceAll("pk_", "")) + " like ?" + (filtersCounter + 1));
						lParameters.add(value + "%");
					}

				} else {
					if (normalQuery)
						sql.append("a." + field.replaceAll("pk_", "pk.") + comparator + " ?" + (filtersCounter + 1));
					else
						sql.append("a." + toSqlName(field.replaceAll("pk_", "")) + comparator + " ?"
								+ (filtersCounter + 1));
					//Object cValue = convertParameter(value, mtypes.get(field));
					Object cValue = value;
					lParameters.add(cValue);
				}
				filtersCounter++;
			}
		}

		// Automatic filters
		EntityTable entityTable = JPManager.getEntityTable(data.getDataId());
		if (entityTable.getHistoricalData()) {
			if (filtersCounter == 0) {
				sql.append(" where ");
			}
			if (filtersCounter > 0) {
				sql.append(" and ");
			}
			if (normalQuery)
				sql.append("a.pk.expired = ?" + (filtersCounter + 1));
			else
				sql.append("a.EXPIRED = ?" + (filtersCounter + 1));
			lParameters.add(Timer.getExpiredTime());
			filtersCounter++;
		}
		if (entityTable.getMultiCompany()) {
			if (filtersCounter == 0) {
				sql.append(" where ");
			}
			if (filtersCounter > 0) {
				sql.append(" and ");
			}
			if (normalQuery)
				sql.append("a.pk.companyId = ?" + (filtersCounter + 1));
			else
				sql.append("a.COMPANY_ID = ?" + (filtersCounter + 1));
			lParameters.add(LocalParameter.get(ParameterEnum.COMPANY, String.class));
			filtersCounter++;
		}

		// Ordering
		if (data.getOrderBy() != null) {
			if (normalQuery)
				sql.append(" order by " + "a." + data.getOrderBy().replaceAll("pk_", "pk."));
			else
				sql.append(" order by " + "a." + toSqlName(data.getOrderBy().replaceAll("pk_", "")));
			if (data.getOrderDir() != null && data.getOrderDir().compareTo(ORDER_DIR_DESC) == 0) {
				sql.append(" DESC");
			}
		}

		// Query
		log.info("Query: " + sql.toString());

		Query query = null;

		if (normalQuery)
			query = JPManager.getEntityManager().createQuery(sql.toString());
		else
			query = JPManager.getEntityManager().createNativeQuery(sql.toString());

		// Set parameters
		filtersCounter = 0;
		for (Object object : lParameters) {
			query.setParameter(filtersCounter + 1, object);
			filtersCounter++;
		}

		// Get result
		List results = query.getResultList();

		// Pagination:
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

		// -----------------------------------------------------------------------
		// Fill items
		// -----------------------------------------------------------------------
		int itemCounter = 1;
		for (int i = offset; i < limit; i++) {
			Item item = new Item(itemCounter++);

			if (queryFields.size() > 1) {
				Object[] result = (Object[]) results.get(i);

				fieldCounter = 0;
				for (String qryField : queryFields) {
					Object resField = result[fieldCounter++];
					Field field = new Field(qryField);
					if (resField != null) {
						String completedValue = completeValue(resField, qryField);
						field.setValue(completedValue);
					}
					item.addField(field);
				}
			} else {
				String qryField = queryFields.get(0);
				Object result = (Object) results.get(i);
				Field field = new Field(qryField);
				if (result != null) {
					String completedValue = completeValue(result, qryField);
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

	// private boolean isDescriptionField(String field) {
	// boolean isDescription = false;
	// if (field.indexOf(".") > 0) {
	// isDescription = true;
	// }
	// return isDescription;
	// }

	private String completeValue(Object value, String qryField) {
		String cValue = null;

		if (normalQuery) {
			if (value.getClass().getSimpleName().compareTo("String") == 0) {
				cValue = value.toString();
			} else if (value.getClass().getSimpleName().compareTo("Integer") == 0) {
				cValue = "((Integer))" + value.toString();
			} else if (value.getClass().getSimpleName().compareTo("BigDecimal") == 0) {
				BigDecimal bd = (BigDecimal) value;
				bd = bd.setScale(2);
				cValue = "((BigDecimal))" + bd.toString();
			} else if (value.getClass().getSimpleName().compareTo("Boolean") == 0) {
				cValue = "((Boolean))" + value.toString();
			} else if (value.getClass().getSimpleName().compareTo("Long") == 0) {
				cValue = "((Long))" + value.toString();
			} else if (value.getClass().getSimpleName().compareTo("Date") == 0) {
				cValue = "((Date))" + FormatDates.getDateFormat().format(value);
			} else if (value.getClass().getSimpleName().compareTo("Timestamp") == 0) {
				cValue = "((Timestamp))" + FormatDates.getTimestampFormat().format(value);
			} else {
				cValue = value.toString();
			}
		} else {
			String entityKey = entity;
			String fieldKey = qryField;
			if (qryField.indexOf(".") > 0) {
				int end = qryField.indexOf(".");
				entityKey = qryField.substring(0, end);
				fieldKey = qryField.substring(end + 1);
			}
			Map<String, String> mtypes = superMtypes.get(entityKey);
			if (mtypes == null) {
				mtypes = getMapTypeFields(entityKey);
				superMtypes.put(entityKey, mtypes);
			}
			if (mtypes.get(fieldKey) != null) {
				if (mtypes.get(fieldKey).compareTo("String") == 0) {
					cValue = value.toString();
				} else if (mtypes.get(fieldKey).compareTo("Integer") == 0) {
					cValue = "((Integer))" + value.toString();
				} else if (mtypes.get(fieldKey).compareTo("BigDecimal") == 0) {
					BigDecimal bd = (BigDecimal) value;
					bd = bd.setScale(2);
					cValue = "((BigDecimal))" + bd.toString();
				} else if (mtypes.get(fieldKey).compareTo("Boolean") == 0) {
					cValue = "((Boolean))" + value.toString();
				} else if (mtypes.get(fieldKey).compareTo("Long") == 0) {
					cValue = "((Long))" + value.toString();
				} else if (mtypes.get(fieldKey).compareTo("Date") == 0) {
					cValue = "((Date))" + FormatDates.getDateFormat().format(value);
				} else if (mtypes.get(fieldKey).compareTo("Timestamp") == 0) {
					cValue = "((Timestamp))" + FormatDates.getTimestampFormat().format(value);
				} else {
					cValue = value.toString();
				}
			} else {
				cValue = value.toString();
			}

		}

		return cValue;
	}

	private Object convertParameter(String value, String type) {
		Object cValue = null;
		if (type.compareTo("String") == 0) {
			cValue = value;
		} else if (type.compareTo("Integer") == 0) {
			cValue = Converter.convertObject(value, Integer.class);
		} else if (type.compareTo("BigDecimal") == 0) {
			cValue = Converter.convertObject(value, BigDecimal.class);
		} else if (type.compareTo("Boolean") == 0) {
			cValue = Converter.convertObject(value, Boolean.class);
		} else if (type.compareTo("Long") == 0) {
			cValue = Converter.convertObject(value, Long.class);
		} else if (type.compareTo("Date") == 0) {
			cValue = Converter.convertObject(value, Date.class);
		} else if (type.compareTo("Timestamp") == 0) {
			cValue = Converter.convertObject(value, Timestamp.class);
		}
		return cValue;
	}

	private Map<String, String> getMapTypeFields(String entity) {
		String SQL_FIELDS = "Select f from EntityField f where f.pk.tableId=:tableId";

		TypedQuery<EntityField> queryFields = JPManager.getEntityManager().createQuery(SQL_FIELDS, EntityField.class);
		queryFields.setParameter("tableId", toSqlName(entity));
		List<EntityField> result = queryFields.getResultList();

		Map<String, String> mtypefields = new HashMap<String, String>();
		for (EntityField entityField : result) {
			String key = "";
			if (entityField.getPrimaryKey()) {
				key = "pk_";
			}
			key = key + toLowerCamelCase(entityField.getPk().getFieldId());
			mtypefields.put(key, entityField.getDataTypeId());
		}

		return mtypefields;
	}

	// private String geType(String entity, String field) throws Exception {
	// EntityFieldPk pk = new EntityFieldPk(toSqlName(entity),
	// toSqlName(field));
	// EntityField entityField = JPManager.find(EntityField.class, pk);
	//
	// return entityField.getDataTypeId();
	// }

	public String toSqlName(String name) {
		String sqlName = "";

		for (int i = 0; i < name.length(); i++) {
			String letter = name.substring(i, i + 1);
			if (letter.matches("[A-Z]") && i > 0) {
				letter = "_" + letter;
			}
			sqlName = sqlName + letter;
		}

		return sqlName.toUpperCase();
	}

	private String toLowerCamelCase(String string) {
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

	@SuppressWarnings("unchecked")
	private void createInnerSelect(StringBuilder sql, List<String> queryFields, int fieldCounter, String strField)
			throws Exception {
		queryFields.add(strField);

		String[] part = strField.split("\\.");
		String entity = part[0];
		String field = part[1];

		if (fieldCounter > 0) {
			sql.append(", ");
		}

		sql.append("(Select ");

		sql.append(toSqlName(field.replaceAll("pk_", "")));

		// From
		sql.append(" from " + toSqlName(entity));

		// Filters
		String QL_RELATIONSHIP = "Select r from EntityRelationship r where r.tableFrom = :tableFrom and r.tableTo = :tableTo";
		Query query = JPManager.getEntityManager().createQuery(QL_RELATIONSHIP, EntityRelationship.class);
		query.setParameter("tableFrom", toSqlName(this.entity));
		String tableTo = null;
		if (JPManager.getEntityTable(entity).getHasTableId()) {
			tableTo = toSqlName(entity) + "_ID";
		}
		query.setParameter("tableTo", tableTo);
		List<EntityRelationship> list = query.getResultList();

		sql.append(" where ");
		int filtersCounter = 0;
		for (EntityRelationship rel : list) {
			if (filtersCounter > 0) {
				sql.append(" and ");
			}
			sql.append(rel.getFieldTo() + "=a." + rel.getFieldFrom());
			filtersCounter++;
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

		sql.append(")");
	}
}