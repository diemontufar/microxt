package mobile.web.webxt.client.gridtools;


import mobile.web.webxt.client.gridtools.MyColumnData.ColumnType;

import com.extjs.gxt.ui.client.widget.form.TextField;
import com.extjs.gxt.ui.client.widget.grid.CellEditor;
import com.extjs.gxt.ui.client.widget.grid.ColumnConfig;

public class NumericColumn extends ColumnConfig {
	
	public NumericColumn(ColumnDataInterface columnData) {
		super();
		setId(columnData.getId());
		setHeader(columnData.getName());
		setWidth(columnData.getWidth());
		TextField<String> text = new TextField<String>();
		text.setAllowBlank(columnData.isAllowBlank());
		text.setMaxLength(columnData.getMaxLength());
		setEditor(new CellEditor(text));
		
		//Hidden
		setHidden(columnData);
		
	}
	
	private void setHidden(ColumnDataInterface columnData) {
		if(columnData.getColumnType()==ColumnType.HIDDEN){
			setHidden(true);
		}
	}

	public NumericColumn(String id, String header, int width, int maxLength, boolean allowBlank) {
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
