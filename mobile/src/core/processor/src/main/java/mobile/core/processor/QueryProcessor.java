package mobile.core.processor;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Query;

import mobile.core.common.Log;
import mobile.core.message.Data;
import mobile.core.message.Field;
import mobile.core.message.Item;
import mobile.core.message.Message;
import mobile.core.structure.processor.GeneralProcessor;
import mobile.entity.common.EntityTable;
import mobile.entity.manager.JPManager;
import mobile.entity.manager.util.LocalParameter;
import mobile.entity.manager.util.ParameterEnum;
import mobile.entity.manager.util.PersistenceTime;

import org.apache.log4j.Logger;

public class QueryProcessor implements GeneralProcessor {
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
	private void processQuery(Data data) throws Exception {
		StringBuilder sql = new StringBuilder();

		// Build query
		sql.append("Select ");

		// Query fields
		List<String> queryFields = new ArrayList<String>();
		boolean hasExpire = false;
		int fieldCounter = 0;
		if (data.getField(F_QRY_FIELDS) != null
				&& data.getField(F_QRY_FIELDS).getValue() != null) {
			for (String strField : data.getField(F_QRY_FIELDS).getValue()
					.split(";")) {
				if (strField.compareTo("_expire") == 0) {
					hasExpire = true;
					continue;
				}
				queryFields.add(strField);
				if (fieldCounter > 0) {
					sql.append(", ");
				}
				sql.append("e." + strField.replaceAll("pk_", "pk."));
				fieldCounter++;
			}
		}

		// From
		sql.append(" from " + data.getId() + " e");

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
					// sql.append("e." + part[0].replaceAll("pk_", "pk.") +
					// " like '" + part[2] + "%'");
					sql.append("e." + part[0].replaceAll("pk_", "pk.")
							+ " like ?" + (filtersCounter + 1));
					lParameters.add(part[2] + "%");
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
			sql.append("e.pk.expired = ?" + (filtersCounter + 1));
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
			sql.append("e.pk.companyId = ?" + (filtersCounter + 1));
			lParameters.add(LocalParameter.get(ParameterEnum.COMPANY,
					String.class));
			filtersCounter++;
		}

		// Ordering
		if (data.getField(F_ORDER) != null
				&& data.getField(F_ORDER).getValue() != null) {
			sql.append(" ");
			sql.append("order by "
					+ "e."
					+ data.getField(F_ORDER).getValue()
							.replaceAll("pk_", "pk."));
			if (data.getField(F_ORDER_DIR) != null
					&& data.getField(F_ORDER_DIR).getValue() != null
					&& data.getField(F_ORDER_DIR).getValue()
							.compareTo(ORDER_DIR_DESC) == 0) {
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

		int itemCounter = 1;
		for (int i = offset; i < limit; i++) {
			Item item = new Item(itemCounter++);

			Object[] result = (Object[]) results.get(i);

			fieldCounter = 0;
			for (String qryField : queryFields) {
				Object resField = result[fieldCounter++];
				Field field = new Field(qryField, resField.toString());
				item.addField(field);
			}
			if (hasExpire) {
				item.addField(new Field("_expire", ""));
			}
			data.addItem(item);
		}

		if (data.getField(F_TOTAL_LENGTH) != null) {
			data.getField(F_TOTAL_LENGTH).setValue(String.valueOf(totalLength));
		} else {
			data.addField(new Field(F_TOTAL_LENGTH, String.valueOf(totalLength)));
		}
	}
}
