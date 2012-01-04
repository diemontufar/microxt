package mobile.web.webxt.client.widgets;


import mobile.web.webxt.client.formtools.PersistentField;
import mobile.web.webxt.client.validations.Validate;
import mobile.web.webxt.client.validations.ValidationTypes;
import mobile.web.webxt.client.validations.ValidationTypesValidator;

import com.extjs.gxt.ui.client.widget.form.TextField;

public class InputBox extends TextField<String> implements PersistentField {

	public CustomFormPanel frmPanel;

	String label;
	
	String fieldInfo;
	
	int minLenght;
	int maxLenght;
	int width;
	int posY;
	Validate type;
	
	public InputBox(String lbl, String fieldInfo, int width, Validate type) {
		this.setFieldInfo(fieldInfo);
		
		this.label = lbl.trim();
		this.type = type;
		this.width = width;
		
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
		
		setFieldLabel(label);
		setMinLength(4);
		setWidth(width);
	}

	public String getFieldInfo() {
		return fieldInfo;
	}

	public void setFieldInfo(String field) {
		this.fieldInfo=field;
	}


}