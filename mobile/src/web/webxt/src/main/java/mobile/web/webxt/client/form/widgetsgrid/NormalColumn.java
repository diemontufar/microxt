package mobile.web.webxt.client.form.widgetsgrid;

import mobile.common.tools.Format;
import mobile.web.webxt.client.form.validations.Validate;
import mobile.web.webxt.client.form.widgets.InputBox;
import mobile.web.webxt.client.util.NumberType;

import com.extjs.gxt.ui.client.widget.form.TextField;
import com.extjs.gxt.ui.client.widget.grid.CellEditor;
import com.extjs.gxt.ui.client.widget.grid.ColumnConfig;
import com.google.gwt.i18n.client.DateTimeFormat;

public class NormalColumn extends ColumnConfig {

	InputBox inputbox;

	public NormalColumn(MyColumnData columnData) {
		setId(columnData.getId());
		setHeader(columnData.getName());
		setWidth(columnData.getWidth());

		inputbox = new InputBox(columnData.getWidth(),
				columnData.getMaxLength(), Validate.TEXT);
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
		TextField<String> text = new TextField<String>();
		text.setAllowBlank(allowBlank);
		text.setMaxLength(maxLength);
		setEditor(new CellEditor(text));
	}
	
	private void createField(MyColumnData columnData, NumberType type, Validate vType) {

		if (type == NumberType.TEXT && vType == null) {
			inputbox = new InputBox(columnData.getWidth(), columnData.getMaxLength(), Validate.TEXT);
			setEditor(new CellEditor(inputbox));
		}

		if (type == NumberType.TEXT && vType != null) {
			inputbox = new InputBox(columnData.getWidth(), columnData.getMaxLength(), vType);
			setEditor(new CellEditor(inputbox));
		}

		if (type == NumberType.DATE && vType == null) {
			
			inputbox = new InputBox(columnData.getWidth(), columnData.getMaxLength());
			setEditor(new CellEditor(inputbox));

			DateTimeFormat dtFormat = DateTimeFormat.getFormat(Format.DATE_PRESENTATION);
			setDateTimeFormat(dtFormat);

		}
	}


}
