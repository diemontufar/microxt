package mobile.web.webxt.client.form.widgetsgrid;

import mobile.common.tools.Format;
import mobile.web.webxt.client.form.widgets.MyDateField;

import com.extjs.gxt.ui.client.widget.grid.CellEditor;
import com.extjs.gxt.ui.client.widget.grid.ColumnConfig;
import com.google.gwt.i18n.client.DateTimeFormat;

public class DateColumn extends ColumnConfig {

	private MyDateField editor;

	public DateColumn() {
	}

	public DateColumn(MyColumnData columnData) {
		this();
		setId(columnData.getId());
		setHeader(columnData.getName());
		setWidth(columnData.getWidth());

		DateTimeFormat dtFormat = DateTimeFormat.getFormat(Format.DATE_PRESENTATION);
		setDateTimeFormat(dtFormat);

		// Read-only
		if (!columnData.isReadOnly()) {
			editor = new MyDateField(columnData.getWidth(), columnData.getMaxLength());
			setEditor(new CellEditor(editor));
		}

		// Visible
		if (!columnData.isVisible()) {
			setHidden(true);
		}

	}

}
