package mobile.web.webxt.client.form.widgetsgrid;

import com.extjs.gxt.ui.client.Style.HorizontalAlignment;
import com.extjs.gxt.ui.client.widget.form.NumberField;
import com.extjs.gxt.ui.client.widget.grid.CellEditor;
import com.extjs.gxt.ui.client.widget.grid.ColumnConfig;

public class NumericColumn extends ColumnConfig {
	
	public static final String INTEGER_FORMAT = "#,##0";
	public static final String DECIMAL_FORMAT = "#,##0.00";

	public NumericColumn(MyColumnData columnData) {
		super();
		setId(columnData.getId());
		setHeader(columnData.getName());
		setWidth(columnData.getWidth());
		
		setAlignment(HorizontalAlignment.RIGHT);
		NumberField numberField = new NumberField();
		numberField.setAllowBlank(columnData.isAllowBlank());
		numberField.setMaxLength(columnData.getMaxLength());
		setEditor(new CellEditor(numberField));  

		// Hidden
		setHidden(!columnData.isVisible());
	}

	public NumericColumn(String id, String header, int width, int maxLength,
			boolean allowBlank) {
		super();
		setId(id);
		setHeader(header);
		setWidth(width);
		
		setAlignment(HorizontalAlignment.RIGHT);
		NumberField numberField = new NumberField();
		numberField.setAllowBlank(allowBlank);
		numberField.setMaxLength(maxLength);
		setEditor(new CellEditor(numberField));
	}

}
