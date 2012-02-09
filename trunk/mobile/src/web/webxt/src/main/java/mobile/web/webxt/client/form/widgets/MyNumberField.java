package mobile.web.webxt.client.form.widgets;

import mobile.common.tools.Format;
import mobile.web.webxt.client.data.form.DataSource;
import mobile.web.webxt.client.form.validations.Validate;
import mobile.web.webxt.client.form.validations.ValidationTypes;
import mobile.web.webxt.client.form.validations.ValidationTypesValidator;
import mobile.web.webxt.client.util.NumberType;

import com.extjs.gxt.ui.client.event.ComponentEvent;
import com.extjs.gxt.ui.client.widget.form.NumberField;
import com.google.gwt.i18n.client.NumberFormat;
import com.google.gwt.user.client.Element;

public class MyNumberField extends NumberField implements PersistentField {

	private DataSource dataSource; 
	NumberType type;
	
	public MyNumberField() {
		super();
	}
	
	public MyNumberField(NumberType type) {
		super();
		this.type = type;
	}
	
	public void createValidator(Validate type) {

		if (type == Validate.REQUIRED) {
			setAllowBlank(false);
		}

		if (type == Validate.NUMERIC) {
			setValidator(new ValidationTypesValidator(ValidationTypes.NUMERIC));
			setAllowBlank(true);
		}
	}
	
	@Override
	protected void onBlur(ComponentEvent arg0) {
		if (getRawValue() != null && !getRawValue().equals("")) {
			try {

				if (type == NumberType.CURRENCY) {
					Double rawvalue = NumberFormat.getFormat(Format.CURRENCY).parse(getRawValue());
					setRawValue("$"+NumberFormat.getFormat(Format.CURRENCY).format(rawvalue));
				} 
				if (type == NumberType.DECIMAL) {
					Double rawvalue = NumberFormat.getFormat(Format.DECIMAL).parse(getRawValue());
					setRawValue(NumberFormat.getFormat(Format.DECIMAL).format(rawvalue));
				}
				if (type == NumberType.INTEGER) {
					Double rawvalue = NumberFormat.getFormat(Format.INTEGER).parse(getRawValue());
					setRawValue(NumberFormat.getFormat(Format.INTEGER).format(rawvalue));
				}
				
			} catch (NumberFormatException e) {
			}
		}
		super.onBlur(arg0);
	}
	
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
	}
	
	public MyNumberField(int width) {
		super();
		setWidth(width);
	}
	
	public NumberType getType() {
		return type;
	}

	public void setType(NumberType type) {
		this.type = type;
	}
	
	public DataSource getDataSource() {
		return dataSource;
	}

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

}