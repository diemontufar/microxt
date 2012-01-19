package mobile.web.webxt.client.form.widgets;


import mobile.web.webxt.client.form.validations.Validate;
import mobile.web.webxt.client.form.validations.ValidationTypes;
import mobile.web.webxt.client.form.validations.ValidationTypesValidator;

import com.extjs.gxt.ui.client.widget.form.TextField;
import com.google.gwt.user.client.Element;

public class InputBox extends TextField<String> implements PersistentField {

	String fieldInfo;
	Validate type;
	
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
	
	public InputBox(){
		super();
	}
	
	public InputBox(int width) {
		this.setWidth(width);
	}
	
	public InputBox(int width, int maxLenght) {
		this.setWidth(width);
		this.setMaxLength(maxLenght);
		createValidator();
	}
	
	public InputBox(int width, int maxLenght, Validate type) {
		this.setWidth(width);
		this.setMaxLength(maxLenght);
		this.type = type;
		createValidator();
	}

	public InputBox(String lbl, String fieldInfo, int width,int maxLenght, Validate type) {
		
		this.setPersistentInfo(fieldInfo);
		this.setFieldLabel(lbl.trim());
		this.setWidth(width);
		this.setMaxLength(maxLenght);
		this.type = type;
		createValidator();
	}

	private void createValidator() {

		if (type == Validate.NUMERICO) {
			setValidator(new ValidationTypesValidator(ValidationTypes.ALPHABET));
		}

		if (type == Validate.ALFANUMERICO) {
			setValidator(new ValidationTypesValidator(ValidationTypes.ALPHANUMERIC));
			setAllowBlank(true);
		}

		if (type == Validate.NUMERICO) {
			setValidator(new ValidationTypesValidator(ValidationTypes.NUMERIC));
			setAllowBlank(true);
		}

		if (type == Validate.PASSWORD) {
			setPassword(true);
		}

		if (type == Validate.EMAIL) {
			setValidator(new ValidationTypesValidator(ValidationTypes.EMAIL));
		}
		
	}

	public String getPersistentInfo() {
		return fieldInfo;
	}

	public void setPersistentInfo(String field) {
		this.fieldInfo=field;
	}


}