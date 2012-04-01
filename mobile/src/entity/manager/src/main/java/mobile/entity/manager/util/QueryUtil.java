package mobile.entity.manager.util;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Timestamp;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import mobile.common.message.EntityData;
import mobile.common.message.Field;
import mobile.common.message.Item;
import mobile.entity.common.EntityField;
import mobile.entity.common.EntityFieldPk;
import mobile.entity.manager.JpManager;
import mobile.tools.common.convertion.CoreConverter;

public class QueryUtil {
	// private final Logger log = Log.getInstance();
	// private Map<String, Map<String, String>> superMtypes = new
	// HashMap<String, Map<String, String>>();
	private boolean normalQuery;

	public QueryUtil(boolean jpqlQuery) {
		this.normalQuery = jpqlQuery;
	}

	// public void fillQueryTypes(String entity) {
	// Map<String, String> mtypes = getMapTypeFields(entity);
	// superMtypes.put(entity, mtypes);
	// }

	// public String getType(String entity, String field) {
	// return superMtypes.get(entity).get(field);
	// }

	// private Map<String, String> getMapTypeFields(String entity) {
	// String SQL_FIELDS =
	// "Select f from EntityField f where f.pk.tableId=:tableId";
	//
	// TypedQuery<EntityField> queryFields =
	// JPManager.getEntityManager().createQuery(SQL_FIELDS, EntityField.class);
	// queryFields.setParameter("tableId", JPManager.toSqlName(entity));
	// List<EntityField> result = queryFields.getResultList();
	//
	// Map<String, String> mtypefields = new HashMap<String, String>();
	// for (EntityField entityField : result) {
	// String key = "";
	// if (entityField.getPrimaryKey()) {
	// key = "pk_";
	// }
	// key = key + toLowerCamelCase(entityField.getPk().getFieldId());
	// mtypefields.put(key, entityField.getDataTypeId());
	// }
	//
	// return mtypefields;
	// }

	// public Object convertParameter(String entity, String field, String value)
	// {
	// Object cValue = null;
	// String type = getType(entity, field);
	// if (type.compareTo("String") == 0) {
	// cValue = value;
	// } else if (type.compareTo("Integer") == 0) {
	// cValue = CoreConverter.convertObject(value, Integer.class);
	// } else if (type.compareTo("BigDecimal") == 0) {
	// cValue = CoreConverter.convertObject(value, BigDecimal.class);
	// } else if (type.compareTo("Boolean") == 0) {
	// cValue = CoreConverter.convertObject(value, Boolean.class);
	// } else if (type.compareTo("Long") == 0) {
	// cValue = CoreConverter.convertObject(value, Long.class);
	// } else if (type.compareTo("Date") == 0) {
	// cValue = CoreConverter.convertObject(value, Date.class);
	// } else if (type.compareTo("Timestamp") == 0) {
	// cValue = CoreConverter.convertObject(value, Timestamp.class);
	// }
	// return cValue;
	// }

	// public String completeValue(Object value, String entity, String qryField)
	// {
	// String cValue = null;
	//
	// if (normalQuery) {
	// if (value.getClass().getSimpleName().compareTo("String") == 0) {
	// cValue = value.toString();
	// } else if (value.getClass().getSimpleName().compareTo("Integer") == 0) {
	// cValue = "((Integer))" + value.toString();
	// } else if (value.getClass().getSimpleName().compareTo("BigDecimal") == 0)
	// {
	// BigDecimal bd = (BigDecimal) value;
	// bd = bd.setScale(2);
	// cValue = "((BigDecimal))" + bd.toString();
	// } else if (value.getClass().getSimpleName().compareTo("Boolean") == 0) {
	// cValue = "((Boolean))" + value.toString();
	// } else if (value.getClass().getSimpleName().compareTo("Long") == 0) {
	// cValue = "((Long))" + value.toString();
	// } else if (value.getClass().getSimpleName().compareTo("Date") == 0) {
	// cValue = "((Date))" + FormatDates.getDateFormat().format(value);
	// } else if (value.getClass().getSimpleName().compareTo("Timestamp") == 0)
	// {
	// cValue = "((Timestamp))" +
	// FormatDates.getTimestampFormat().format(value);
	// } else {
	// cValue = value.toString();
	// }
	// } else {
	// String entityKey = entity;
	// String fieldKey = qryField;
	// if (qryField.indexOf(".") > 0) {
	// int end = qryField.indexOf(".");
	// entityKey = qryField.substring(0, end);
	// fieldKey = qryField.substring(end + 1);
	// }
	// Map<String, String> mtypes = superMtypes.get(entityKey);
	// if (mtypes == null) {
	// mtypes = getMapTypeFields(entityKey);
	// superMtypes.put(entityKey, mtypes);
	// }
	// if (mtypes.get(fieldKey) != null) {
	// if (mtypes.get(fieldKey).compareTo("String") == 0) {
	// cValue = value.toString();
	// } else if (mtypes.get(fieldKey).compareTo("Integer") == 0) {
	// cValue = "((Integer))" + value.toString();
	// } else if (mtypes.get(fieldKey).compareTo("BigDecimal") == 0) {
	// BigDecimal bd = (BigDecimal) value;
	// bd = bd.setScale(2);
	// cValue = "((BigDecimal))" + bd.toString();
	// } else if (mtypes.get(fieldKey).compareTo("Boolean") == 0) {
	// cValue = "((Boolean))" + value.toString();
	// } else if (mtypes.get(fieldKey).compareTo("Long") == 0) {
	// cValue = "((Long))" + value.toString();
	// } else if (mtypes.get(fieldKey).compareTo("Date") == 0) {
	// cValue = "((Date))" + FormatDates.getDateFormat().format(value);
	// } else if (mtypes.get(fieldKey).compareTo("Timestamp") == 0) {
	// cValue = "((Timestamp))" +
	// FormatDates.getTimestampFormat().format(value);
	// } else {
	// cValue = value.toString();
	// }
	// } else {
	// cValue = value.toString();
	// }
	//
	// }
	//
	// return cValue;
	// }

	// private String toLowerCamelCase(String string) {
	// StringBuilder sb = new StringBuilder();
	// Boolean primera = true;
	// for (String s : string.split("_")) {
	// if (primera) {
	// sb.append(s.substring(0, 1).toLowerCase());
	// primera = false;
	// } else {
	// sb.append(s.substring(0, 1).toUpperCase());
	// }
	// sb.append(s.substring(1).toLowerCase());
	// }
	// return sb.toString();
	// }

	public String analizeComparator(String comparator) {
		String comparator2 = null;

		if (comparator == null || (comparator != null && comparator.compareTo("") == 0)
				|| (comparator != null && comparator.compareToIgnoreCase("like") == 0)) {
			comparator2 = "like";
		} else if (comparator.compareTo("=") == 0)
			comparator2 = comparator;
		else if (comparator.compareTo("lt") == 0)
			comparator2 = "<";
		else if (comparator.compareTo("gt") == 0)
			comparator2 = ">";
		else if (comparator.compareTo("eq") == 0)
			comparator2 = "=";
		else if (comparator.compareTo("before") == 0)
			comparator2 = "<";
		else if (comparator.compareTo("after") == 0)
			comparator2 = ">";
		else if (comparator.compareTo("on") == 0)
			comparator2 = "=";

		return comparator2;
	}

	public String completeSQL(final String SQL, EntityData data) throws ParseException {
		StringBuilder sql = new StringBuilder("");
		sql.append("Select * from (" + SQL + ") as alias0");
		// Request filters
		if (data.getFilters() != null) {
			if (!normalQuery) {
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

						comparator = analizeComparator(comparator);
						if (comparator.compareTo("like") == 0 && !comparator.endsWith("%")) {
							cValue = value + "%";
						}
						sql.append(JpManager.toSqlName(field.replaceAll("pk_", "").replaceAll("\\.", "_")) + " "
								+ comparator + " '" + cValue + "'");
						filtersCounter++;
					}
				}
			}
		}

		// Ordering
		if (data.getOrderBy() != null) {
			if (!normalQuery) {
				sql.append(" order by "
						+ JpManager.toSqlName(data.getOrderBy().replaceAll("pk_", "").replaceAll("\\.", "_")));
			}

			if (data.getOrderDir() != null && data.getOrderDir().compareTo("DESC") == 0) {
				sql.append(" DESC");
			}
		}

		return sql.toString();
	}

	public void listToData(List<Object[]> list, EntityData data) {
		// Query fields
		List<String> queryFields = new ArrayList<String>();
		if (data.getQueryFields() != null) {
			for (String strField : data.getQueryFields().split(";")) {
				queryFields.add(strField);
			}
		}

		// Pagination information
		int totalLength = list.size();

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

		// Fill items
		int itemCounter = 1;
		for (int i = offset; i < limit; i++) {
			Item item = new Item(itemCounter++);

			if (queryFields.size() > 1) {
				Object[] result = (Object[]) list.get(i);

				int fieldCounter = 0;
				for (String qryField : queryFields) {
					Object resField = result[fieldCounter++];
					Field field = new Field(qryField);

					if (resField != null) {
						String completedValue = CoreConverter.completeValue(resField);
						field.setValue(completedValue);
					}
					item.addField(field);
				}
			} else {
				String qryField = queryFields.get(0);
				Object result = (Object) list.get(i);
				Field field = new Field(qryField);
				if (result != null) {
					String completedValue = CoreConverter.completeValue(result);
					field.setValue(completedValue);
				}
				item.addField(field);
			}

			data.addItem(item);
		}

		data.setTotal(totalLength);
	}

	public Object convertParameter(String entity, String field, Object value) throws Exception {
		Object cValue = null;
		EntityFieldPk pk = new EntityFieldPk(JpManager.toSqlName(entity), JpManager.toSqlName(field));
		pk.setCompanyId("ALL");
		EntityField entityField = JpManager.find(EntityField.class, pk);

		if(entityField == null){
			return value;
		}
		
		String type = entityField.getDataTypeId();

		if (type.compareTo("String") == 0) {
			cValue = value;
		} else if (type.compareTo("Integer") == 0) {
			cValue = CoreConverter.convertObject(value, Integer.class);
		} else if (type.compareTo("BigDecimal") == 0) {
			cValue = CoreConverter.convertObject(value, BigDecimal.class);
		} else if (type.compareTo("Boolean") == 0) {
			cValue = CoreConverter.convertObject(value, Boolean.class);
		} else if (type.compareTo("Long") == 0) {
			cValue = CoreConverter.convertObject(value, Long.class);
		} else if (type.compareTo("Date") == 0) {
			cValue = CoreConverter.convertObject(value, Date.class);
		} else if (type.compareTo("Timestamp") == 0) {
			cValue = CoreConverter.convertObject(value, Timestamp.class);
		}

		return cValue;
	}
}
