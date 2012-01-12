package mobile.web.webxt.client.gridtools;

import mobile.web.webxt.client.gridtools.MyColumnData.ColumnType;
import mobile.web.webxt.client.gridtools.MyColumnData.NumberType;

import com.extjs.gxt.ui.client.Style.HorizontalAlignment;
import com.extjs.gxt.ui.client.widget.form.NumberField;
import com.extjs.gxt.ui.client.widget.grid.CellEditor;
import com.extjs.gxt.ui.client.widget.grid.ColumnConfig;
import com.google.gwt.i18n.client.NumberFormat;

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

		if(columnData.getNumberType()!=null){
			if(columnData.getNumberType() == NumberType.INTEGER){
				setNumberFormat(NumberFormat.getFormat(INTEGER_FORMAT));
			}else if(columnData.getNumberType() == NumberType.DECIMAL){
				setNumberFormat(NumberFormat.getFormat(DECIMAL_FORMAT));
			}
		}else{
			setNumberFormat(NumberFormat.getFormat(DECIMAL_FORMAT));
		}
		
		
		// Hidden
		setHidden(columnData);
	}

	private void setHidden(MyColumnData columnData) {
		if (columnData.getColumnType() == ColumnType.HIDDEN) {
			setHidden(true);
		}
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
