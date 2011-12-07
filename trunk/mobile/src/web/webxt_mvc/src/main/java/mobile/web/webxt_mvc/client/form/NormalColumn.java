package mobile.web.webxt_mvc.client.form;

import com.extjs.gxt.ui.client.widget.form.TextField;
import com.extjs.gxt.ui.client.widget.grid.CellEditor;
import com.extjs.gxt.ui.client.widget.grid.ColumnConfig;

public class NormalColumn extends ColumnConfig {
	
	public NormalColumn(MyColumnData columnData) {
		super();
		setId(columnData.getId());
		setHeader(columnData.getName());
		setWidth(columnData.getWidth());
		TextField<String> text = new TextField<String>();
		text.setAllowBlank(columnData.isAllowBlank());
		text.setMaxLength(columnData.getMaxLength());
		setEditor(new CellEditor(text));
	}

	public NormalColumn(String id, String header, int width, int maxLength, boolean allowBlank) {
		super();
		setId(id);
		setHeader(header);
		setWidth(width);
		TextField<String> text = new TextField<String>();
		text.setAllowBlank(allowBlank);
		text.setMaxLength(maxLength);
		setEditor(new CellEditor(text));
	}

}