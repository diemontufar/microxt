package mobile.web.webxt.client.form.widgets;

import java.util.Date;

import mobile.common.tools.Format;
import mobile.web.webxt.client.util.DatesManager;

import com.extjs.gxt.ui.client.event.DatePickerEvent;
import com.extjs.gxt.ui.client.event.Events;
import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.widget.form.DateField;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.user.client.Element;

public class MyDateField extends DateField implements PersistentField {

	String fieldInfo;

	@Override
	public void setMaxLength(int m) {
		super.setMaxLength(m);
		if (rendered) {
			getInputEl().setElementAttribute("maxLength", m);
		}
	}

	@Override
	protected void onRender(Element target, int index) {
		super.onRender(target, index);

		getInputEl().setElementAttribute("maxLength", getMaxLength());

		this.getDatePicker().addListener(Events.Select, new Listener<DatePickerEvent>() {

			public void handleEvent(DatePickerEvent be) {
				Date date = be.getDate();

				DateTimeFormat dformat = DateTimeFormat.getFormat(Format.DATE_PRESENTATION);
				getPropertyEditor().setFormat(dformat);
				Date dDate = dformat.parse(DatesManager.dateToString(date, Format.DATE_PRESENTATION));
				setValue(dDate);
			}

		});
	}

	public MyDateField(int width, int maxLength) {
		this.setWidth(width);
		this.setMaxLength(maxLength);
	}

	public MyDateField(int width, int height, int maxLength) {
		this.setWidth(width);
		this.setHeight(height);
		this.setMaxLength(maxLength);
	}

	public MyDateField(String fieldLabel, String fieldInfo, int width, int maxLength) {
		this.setPersistentInfo(fieldInfo);
		this.setFieldLabel(fieldLabel);
		this.setWidth(width);
		this.setMaxLength(maxLength);
	}

	public MyDateField(String fieldLabel, String fieldInfo, int width, int height, int maxLength) {
		this.setPersistentInfo(fieldInfo);
		this.setFieldLabel(fieldLabel);
		this.setWidth(width);
		this.setHeight(height);
		this.setMaxLength(maxLength);
	}

	public String getPersistentInfo() {
		return fieldInfo;
	}

	public void setPersistentInfo(String field) {
		this.fieldInfo = field;
	}

}
