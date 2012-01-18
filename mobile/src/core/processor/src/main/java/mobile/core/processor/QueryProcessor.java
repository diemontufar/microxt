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
import mobile.message.message.EntityData;
import mobile.message.message.Field;
import mobile.message.message.Item;
import mobile.message.message.Message;
import mobile.tools.common.Log;
import mobile.tools.common.convertion.ConvertionManager;
import mobile.tools.common.param.LocalParameter;
import mobile.tools.common.param.ParameterEnum;
import mobile.tools.common.param.PersistenceTime;

import org.apache.log4j.Logger;

public class QueryProcessor implements GeneralProcessor {

	private final String ORDER_DIR_DESC = "DESC";
	
	private final Logger log = Log.getInstance();

	@Override
	public Message process(Message msg) throws Exception {
		for (EntityData data : msg.getEntityDataList()) {
			if (data.getProcessType() != null 
					&& data.getProcessType().compareTo("QRY") == 0) {
				if(decideProcessor(data)==0){
					processQuery(data);
				}else{
					SpecialQueryProcessor specialProc = new SpecialQueryProcessor();
					specialProc.processQuery(data);
				}
			}
		}

		return msg;
	}
		
	private int decideProcessor(EntityData data) {
		if(data.getQueryFields()!=null
				&& data.getQueryFields().indexOf("d:")>0){
			return 1;
		}
		return 0;
	}

	@SuppressWarnings("rawtypes")
	private void processQuery(EntityData data) throws Exception {
		StringBuilder sql = new StringBuilder();

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
				queryFields.add(strField);
				if (fieldCounter > 0) {
					sql.append(", ");
				}
				sql.append("a." + strField.replaceAll("pk_", "pk."));
				fieldCounter++;
			}
		}

		// From
		sql.append(" from " + data.getId() + " a");

		//Get fields information
		Map<String,String> mtypes = getMapTypeFields(data.getId());
		
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
				if (part[1] == null
						|| (part[1] != null && part[1].compareTo("") == 0)) {
					sql.append("a." + part[0].replaceAll("pk_", "pk.")
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
			sql.append("a.pk.expired = ?" + (filtersCounter + 1));
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
			sql.append("a.pk.companyId = ?" + (filtersCounter + 1));
			lParameters.add(LocalParameter.get(ParameterEnum.COMPANY,
					String.class));
			filtersCounter++;
		}

		// Ordering
		if (data.getOrderBy() != null) {
			sql.append(" order by "
					+ "a."
					+ data.getOrderBy().replaceAll("pk_", "pk."));
			if (data.getOrderDir() != null
					&& data.getOrderDir().compareTo(ORDER_DIR_DESC) == 0) {
				sql.append(" DESC");
			}
		}

		// Query
		log.info("Query: " + sql.toString());
		Query query = JPManager.getEntityManager().createQuery(sql.toString());

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
		if (data.getLimit()!= null
				&& data.getLimit() > 0) {
			limit = data.getLimit();
		}

		if (limit > 0) {
			limit = Math.min(offset + limit, totalLength);
		}

		int itemCounter = 1;
		for (int i = offset; i < limit; i++) {
			Item item = new Item(itemCounter++);

			if(queryFields.size()>1){
				Object[] result = (Object[]) results.get(i);

				fieldCounter = 0;
				for (String qryField : queryFields) {
					Object resField = result[fieldCounter++];
					Field field = new Field(qryField);
					if(resField!=null) {
						if(	resField.getClass().getSimpleName().compareTo("Boolean")==0 ){
							field.setValue("((Boolean))" + resField.toString());
						}else{
							field.setValue(resField.toString());
						}
					}
					item.addField(field);
				}
			}else{
				String qryField = queryFields.get(0);
				Object result = (Object) results.get(i);

				Field field = null;
				if (result.getClass().getSimpleName().compareTo("Boolean") == 0) {
					field = new Field(qryField, "((Boolean))"
							+ result.toString());
				} else {
					field = new Field(qryField, result.toString());
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