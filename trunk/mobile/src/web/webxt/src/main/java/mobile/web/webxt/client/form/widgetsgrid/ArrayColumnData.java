package mobile.web.webxt.client.form.widgetsgrid;

import java.util.ArrayList;
import java.util.List;

import mobile.web.webxt.client.data.form.DataSource;
import mobile.web.webxt.client.data.form.DataSourceType;

public class ArrayColumnData extends ArrayList<MyColumnData> {
	private static final long serialVersionUID = 1L;
	
	public ArrayColumnData() {
		super();
	}

	public List<String> getIdFields() {
		List<String> lIdFields = new ArrayList<String>();
		for (int i = 0; i < this.size(); i++) {
			lIdFields.add(get(i).getId());
		}
		return lIdFields;
	}

	public List<DataSource> getDataSources() {
		List<DataSource> lFields = new ArrayList<DataSource>();
		for (int i = 0; i < this.size(); i++) {
			MyColumnData column = get(i);

			if (column.getDataSource() != null && column.getDataSource().getType() != DataSourceType.NONE) {
				lFields.add(column.getDataSource());
			}
		}
		return lFields;
	}

}
