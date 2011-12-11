package mobile.core.processor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.Query;
import javax.persistence.TypedQuery;

import mobile.core.structure.processor.GeneralProcessor;
import mobile.entity.common.EntityField;
import mobile.entity.common.EntityTable;
import mobile.entity.manager.JPManager;
import mobile.entity.manager.util.LocalParameter;
import mobile.entity.manager.util.ParameterEnum;
import mobile.entity.manager.util.PersistenceTime;
import mobile.message.cmessage.Data;
import mobile.message.cmessage.Field;
import mobile.message.cmessage.Item;
import mobile.message.cmessage.Message;
import mobile.tools.common.Log;
import mobile.tools.common.convertion.ConvertionManager;

import org.apache.log4j.Logger;

public class SpecialQueryProcessor implements GeneralProcessor {
	private final Logger log = Log.getInstance();

	private final String F_QRY_FIELDS = "_qry_fields";
	
	private final String F_TOTAL_LENGTH = "_pag_total_length";

	private final String F_OFFSET = "_pag_offset";

	private final String F_LIMIT = "_pag_limit";

	private final String F_ORDER = "_ord_field";

	private final String F_ORDER_DIR = "_ord_dir";

	private final String F_FILTERS = "_filters";

	private final String ORDER_DIR_DESC = "DESC";

	@Override
	public Message process(Message msg) throws Exception {
		for (Data data : msg.getDataList()) {
			if (data.getField("_type") != null
					&& data.getField("_type").getValue().compareTo("QRY") == 0) {
				processQuery(data);
			}
		}

		return msg;
	}
		
	@SuppressWarnings("rawtypes")
	public void processQuery(Data data) throws Exception {
		StringBuilder sql = new StringBuilder();

		// Build query
		sql.append("SELECT ");

		// Query fields
		List<String> queryFields = new ArrayList<String>();
		boolean hasExpire = false;
		int fieldCounter = 0;
		if (data.getField(F_QRY_FIELDS) != null
				&& data.getField(F_QRY_FIELDS).getValue() != null) {
			for (String strField : data.getField(F_QRY_FIELDS).getValue()
					.split(";")) {
				// Expire fields
				if (strField.compareTo("_expire") == 0) {
					hasExpire = true;
					continue;
				}
				// Description field
				if (strField.startsWith("d:")) {
					createInnerSelect(sql, queryFields, fieldCounter, strField);
					continue;
				}
				
				queryFields.add(strField);
				if (fieldCounter > 0) {
					sql.append(", ");
				}
				sql.append("a." + toSqlName(strField.replaceAll("pk_", "")) );
				fieldCounter++;
			}
		}

		// From
		sql.append(" from " + toSqlName(data.getId()) + " a");

		//Get fields information
		Map<String,String> mtypes = getMapTypeFields(data.getId());
				
		// Filters
		List<Object> lParameters = new ArrayList<Object>();
		int filtersCounter = 0;
		if (data.getField(F_FILTERS) != null
				&& data.getField(F_FILTERS).getValue() != null) {
			sql.append(" where ");
			String strFilters = data.getField(F_FILTERS).getValue();
			String[] lFilters = strFilters.split(";");
			for (String filter : lFilters) {
				if (filtersCounter > 0) {
					sql.append(" and ");
				}
				String[] part = filter.split(":");
				if (part[1] == null
						|| (part[1] != null && part[1].compareTo("") == 0)) {
					sql.append("a." + part[0].replaceAll("pk_", "")
							+ " like ?" + (filtersCounter + 1));
					lParameters.add(part[2] + "%");
				}else{
					if(mtypes.get(part[0])!=null && mtypes.get(part[0]).compareTo("Boolean")==0){
						sql.append("a." + part[0].replaceAll("pk_", "pk.")
								+ part[1] + " ?" + (filtersCounter + 1));
						lParameters.add(ConvertionManager.convertObject(part[2], Boolean.class));
					}else{
						sql.append("a." + part[0].replaceAll("pk_", "pk.")
								+ part[1] + " ?" + (filtersCounter + 1));
						lParameters.add(part[2]);
					}
				}
				filtersCounter++;
			}
		}

		// Automatic filters
		EntityTable entityTable = JPManager.getEntityTable(data.getId());
		if (entityTable.getHistoricalData()) {
			if (filtersCounter == 0) {
				sql.append(" where ");
			}
			if (filtersCounter > 0) {
				sql.append(" and ");
			}
			sql.append("a.EXPIRED = ?" + (filtersCounter + 1));
			lParameters.add(PersistenceTime.getExpiredTime());
			filtersCounter++;
		}
		if (entityTable.getMultiCompany()) {
			if (filtersCounter == 0) {
				sql.append(" where ");
			}
			if (filtersCounter > 0) {
				sql.append(" and ");
			}
			sql.append("a.COMPANY_ID = ?" + (filtersCounter + 1));
			lParameters.add(LocalParameter.get(ParameterEnum.COMPANY,
					String.class));
			filtersCounter++;
		}

		// Ordering
		if (data.getField(F_ORDER) != null
				&& data.getField(F_ORDER).getValue() != null) {
			sql.append(" order by "
					+ "a."
					+ toSqlName(data.getField(F_ORDER).getValue().replaceAll("pk_", ""))
							.replaceAll("pk_", ""));
			if (data.getField(F_ORDER_DIR) != null
					&& data.getField(F_ORDER_DIR).getValue() != null
					&& data.getField(F_ORDER_DIR).getValue()
							.compareTo(ORDER_DIR_DESC) == 0) {
				sql.append(" DESC");
			}
		}

		// Query
		log.info("Query: " + sql.toString());
		Query query = JPManager.getEntityManager().createNativeQuery(sql.toString());

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
		if (data.getField(F_OFFSET) != null
				&& data.getField(F_OFFSET).getValue() != null) {
			offset = Integer.valueOf(data.getField(F_OFFSET).getValue());
		}

		int limit = totalLength;
		if (data.getField(F_LIMIT) != null
				&& data.getField(F_LIMIT).getValue() != null
				&& Integer.valueOf(data.getField(F_LIMIT).getValue()) > 0) {
			limit = Integer.valueOf(data.getField(F_LIMIT).getValue());
		}

		if (limit > 0) {
			limit = Math.min(offset + limit, totalLength);
		}

		//-----------------------------------------------------------------------
		// Fill items
		//-----------------------------------------------------------------------
		int itemCounter = 1;
		
		for (int i = offset; i < limit; i++) {
			Item item = new Item(itemCounter++);

			Object[] result = (Object[]) results.get(i);

			fieldCounter = 0;
			for (String qryField : queryFields) {
				Object resField = result[fieldCounter++];
				Field field = null;
				
				if(mtypes.get(qryField)!=null && mtypes.get(qryField).compareTo("Boolean")==0){
					field = new Field(qryField, "((Boolean))" + "false");
				}else{
					field = new Field(qryField, resField.toString());
				}
				item.addField(field);
			}
			if (hasExpire) {
				item.addField(new Field("_expire", "((Boolean))false"));
			}
			data.addItem(item);
		}

		if (data.getField(F_TOTAL_LENGTH) != null) {
			data.getField(F_TOTAL_LENGTH).setValue(String.valueOf(totalLength));
		} else {
			data.addField(new Field(F_TOTAL_LENGTH, String.valueOf(totalLength)));
		}
	}

	private Map<String, String> getMapTypeFields(String id) {
		String SQL_FIELDS = "Select f from EntityField f where f.pk.tableId=:tableId";
		
		TypedQuery<EntityField> queryFields = 
				JPManager.getEntityManager().createQuery(SQL_FIELDS,EntityField.class);
		queryFields.setParameter("tableId", toSqlName(id));
		List<EntityField> result = queryFields.getResultList();
		
		Map<String,String> mtypefields = new HashMap<String, String>();
		for (EntityField entityField : result) {
			String key = new String();
			if (entityField.getPrimaryKey()){
				key = "pk_";
			}
			key = key + toLowerCamelCase(entityField.getPk().getFieldId());
			mtypefields.put(key, entityField.getDataTypeId());
		}
		
		return mtypefields;
	}

	private void createInnerSelect(StringBuilder sql, List<String> queryFields,
			int fieldCounter, String strField) throws Exception {
		
		String[] props =  strField.split(":");
		String id = props[1];
		String entity = props[2];
		String field = props[3];
		List<String> lfilter = new ArrayList<String>();
		for (int i = 4; i < props.length; i++) {
			lfilter.add(props[i]);
		}
		
		if(fieldCounter>0){
			sql.append(", ");
		}
		fieldCounter++;
		
		sql.append("(Select ");
		
		queryFields.add(id);
		sql.append("b." + toSqlName(field.replaceAll("pk_", "")));
		
		// From
		sql.append(" from " + toSqlName(entity) + " b");

		// Filters
		sql.append(" where ");
		int filtersCounter = 0;
		for (String filter : lfilter) {
			if (filtersCounter > 0) {
				sql.append(" and ");
			}
			sql.append("b." + toSqlName(filter.replaceAll("pk_", "")) + "=a." + toSqlName(filter.replaceAll("pk_", "")));
			filtersCounter++;
		}

		// Automatic filters
		EntityTable entityTable = JPManager.getEntityTable(entity);
		if (entityTable.getHistoricalData()) {
			if (filtersCounter > 0) {
				sql.append(" and ");
			}
			sql.append("b.EXPIRED = '9999-12-31' ");
			filtersCounter++;
		}
		if (entityTable.getMultiCompany()) {
			if (filtersCounter > 0) {
				sql.append(" and ");
			}
			sql.append("b.COMPANY_ID = '" + LocalParameter.get(ParameterEnum.COMPANY, String.class) + "' ");
			filtersCounter++;
		}
		
		sql.append(")");
	}
	
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

}
