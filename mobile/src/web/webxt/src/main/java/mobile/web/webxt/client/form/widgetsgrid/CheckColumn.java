package mobile.web.webxt.client.form.widgetsgrid;

import com.extjs.gxt.ui.client.widget.grid.CheckColumnConfig;

public class CheckColumn extends CheckColumnConfig {
	public CheckColumn(MyColumnData columnData) {
		super(columnData.getId(), columnData.getName(), columnData.getWidth());
	}

	public CheckColumn(String arg0, String arg1, Integer arg2) {
		super(arg0, arg1, arg2);
	}
}
