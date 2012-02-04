package mobile.web.webxt.client.form.widgetsgrid;


import com.extjs.gxt.ui.client.widget.form.TextField;
import com.extjs.gxt.ui.client.widget.grid.CellEditor;
import com.extjs.gxt.ui.client.widget.grid.ColumnConfig;

public class NormalColumn extends ColumnConfig {
	
	public NormalColumn(MyColumnData columnData) {
		setId(columnData.getId());
		setHeader(columnData.getName());
		setWidth(columnData.getWidth());
		TextField<String> text = new TextField<String>();
		text.setAllowBlank(columnData.isAllowBlank());
		text.setMaxLength(columnData.getMaxLength());
		setEditor(new CellEditor(text));
		
		//Visible
		if(!columnData.isVisible()){
			setHidden(true);
		}
		
	}
	
	public NormalColumn(String id, String header, int width, int maxLength, boolean allowBlank) {
		setId(id);
		setHeader(header);
		setWidth(width);
		TextField<String> text = new TextField<String>();
		text.setAllowBlank(allowBlank);
		text.setMaxLength(maxLength);
		setEditor(new CellEditor(text));
	}

}
