package mobile.web.webxt.client.form.widgetsgrid;

import mobile.common.tools.Format;
import mobile.web.webxt.client.form.validations.Validate;
import mobile.web.webxt.client.form.widgets.InputBox;
import mobile.web.webxt.client.util.NumberType;

import com.extjs.gxt.ui.client.widget.grid.CellEditor;
import com.extjs.gxt.ui.client.widget.grid.ColumnConfig;
import com.google.gwt.i18n.client.DateTimeFormat;

public class NormalColumn extends ColumnConfig {

	InputBox inputbox;

	public NormalColumn(MyColumnData columnData) {
		setId(columnData.getId());
		setHeader(columnData.getName());
		setWidth(columnData.getWidth());

		inputbox = new InputBox(columnData.getWidth(),columnData.getMaxLength(), Validate.TEXT);
		inputbox.setValidateOnBlur(true);
		setEditor(new CellEditor(inputbox));

		// Visible
		if (!columnData.isVisible()) {
			setHidden(true);
		}

	}

	public NormalColumn(MyColumnData columnData, NumberType type, Validate vType) {
		super();
		setId(columnData.getId());
		setHeader(columnData.getName());
		setWidth(columnData.getWidth());

		// Create field and formatters
		createField(columnData, type, vType);
		inputbox.setValidateOnBlur(true);
		setEditor(new CellEditor(inputbox));

		// Visible
		if (!columnData.isVisible()) {
			setHidden(true);
		}
	}

	public NormalColumn(String id, String header, int width, int maxLength,
			boolean allowBlank) {
		setId(id);
		setHeader(header);
		setWidth(width);
		
		// Create field and formatters
		inputbox = new InputBox(width, maxLength, Validate.TEXT);
		inputbox.setValidateOnBlur(true);
		inputbox.setAllowBlank(allowBlank);
		inputbox.setMaxLength(maxLength);
		setEditor(new CellEditor(inputbox));
	}
	
	private void createField(MyColumnData columnData, NumberType type, Validate vType) {

		if (type == NumberType.TEXT && vType == null) {
			inputbox = new InputBox(columnData.getWidth(), columnData.getMaxLength(), Validate.TEXT);
		}

		if (type == NumberType.TEXT && vType != null) {
			inputbox = new InputBox(columnData.getWidth(), columnData.getMaxLength(), vType);
		}

		if (type == NumberType.DATE && vType == null) {
			
			inputbox = new InputBox(columnData.getWidth(), columnData.getMaxLength());
			DateTimeFormat dtFormat = DateTimeFormat.getFormat(Format.DATE_PRESENTATION);
			setDateTimeFormat(dtFormat);

		}
	}


}
