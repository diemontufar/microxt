package mobile.web.webxt.client.widgets;


import mobile.web.webxt.client.formtools.PersistentField;
import mobile.web.webxt.client.validations.Validate;
import mobile.web.webxt.client.validations.ValidationTypes;
import mobile.web.webxt.client.validations.ValidationTypesValidator;

import com.extjs.gxt.ui.client.widget.form.TextField;

public class InputBox extends TextField<String> implements PersistentField {

	String fieldInfo;
	Validate type;
	
	public InputBox(String lbl, String fieldInfo, int width,int maxLenght, Validate type) {
		
		this.setFieldInfo(fieldInfo);
		this.setFieldLabel(lbl.trim());
		this.setWidth(width);
		this.setMaxLength(maxLenght);
		this.type = type;
		createInput();
	}

	private void createInput() {

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

	public String getFieldInfo() {
		return fieldInfo;
	}

	public void setFieldInfo(String field) {
		this.fieldInfo=field;
	}


}