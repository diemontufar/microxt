package mobile.web.webxt.client.gridtools;

import java.util.ArrayList;
import java.util.List;

import mobile.web.webxt.client.gridtools.MyColumnData.ColumnType;

public class ArrayColumnData extends ArrayList<MyColumnData> {
	public ArrayColumnData() {
		super();
	}

	private static final long serialVersionUID = 1L;

	public List<String> getIdFields() {
		List<String> lIdFields = new ArrayList<String>();
		for (int i = 0; i < this.size(); i++) {
			lIdFields.add(get(i).getId());
		}
		return lIdFields;
	}

	public List<String> getRqFields() {
		List<String> lQryFields = new ArrayList<String>();
		for (int i = 0; i < this.size(); i++) {
			MyColumnData column = get(i);

			if (column.getColumnType() == null
					|| (column.getColumnType() != null && column
							.getColumnType() != ColumnType.DESC)) {
				lQryFields.add(column.getId());
			} else {
				lQryFields.add("d:" + column.getId() + ":" 
						+ column.getDescriptionEntity() + ":"
						+ column.getDescriptionField() + ":"
						+ column.getDescriptionCriterion());
			}
		}
		return lQryFields;
	}

}
