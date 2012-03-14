package mobile.logic.microxt.query;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Query;

import mobile.common.message.EntityData;
import mobile.common.message.Field;
import mobile.common.message.Item;
import mobile.common.message.Message;
import mobile.entity.manager.JpManager;
import mobile.tools.common.structure.QueryProcessor;

public class QueryPartnerInfo implements QueryProcessor {

	private final String PARTNER_QL = "Select a.partner_id, b.identification_number, "
			+ "if(b.second_last_name is not null, CONCAT(b.last_name, ' ', b.second_last_name, ' ', b.name), CONCAT(b.last_name, ' ', b.name)) name "
			+ "from PARTNER a "
			+ "inner join PERSON b on a.company_id = b.company_id and a.expired = b.expired and a.person_id = b.person_id "
			+ "where a.expired = fncexpired() order by partner_id;";

	@SuppressWarnings("unchecked")
	@Override
	public Message process(Message msg) throws Exception {
		EntityData data = msg.getEntityData("Partner");

		Query query = JpManager.getEntityManager().createNativeQuery(PARTNER_QL);
		List<Object[]> results = query.getResultList();

		listToData(results, data);

		return msg;
	}

	private void listToData(List<Object[]> list, EntityData data) {
		// Query fields
		List<String> queryFields = new ArrayList<String>();
		int fieldCounter = 0;
		if (data.getQueryFields() != null) {
			for (String strField : data.getQueryFields().split(";")) {
				// Description field
				queryFields.add(strField);
				fieldCounter++;
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

				fieldCounter = 0;
				for (String qryField : queryFields) {
					Object resField = result[fieldCounter++];
					Field field = new Field(qryField);

					if (resField != null) {
						// String completedValue = completeValue(resField,
						// qryField);
						// field.setValue(completedValue);
						field.setValue(resField.toString());
					}
					item.addField(field);
				}
			} else {
				String qryField = queryFields.get(0);
				Object result = (Object) list.get(i);
				Field field = new Field(qryField);
				if (result != null) {
					// String completedValue = completeValue(result, qryField);
					// field.setValue(completedValue);
					field.setValue(result.toString());
				}
				item.addField(field);
			}

			data.addItem(item);
		}

		data.setTotal(totalLength);
	}
}
