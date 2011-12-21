package mobile.web.webxt_mvc.client.gridtools;

import com.extjs.gxt.ui.client.widget.form.CheckBox;
import com.extjs.gxt.ui.client.widget.grid.CellEditor;
import com.extjs.gxt.ui.client.widget.grid.CheckColumnConfig;

public class CheckColumn extends CheckColumnConfig {
	public CheckColumn(ColumnDataInterface columnData) {
		super(columnData.getId(), columnData.getName(), columnData.getWidth());

		CellEditor checkBoxEditor = new CellEditor(new CheckBox());
		setEditor(checkBoxEditor);
	}
}
