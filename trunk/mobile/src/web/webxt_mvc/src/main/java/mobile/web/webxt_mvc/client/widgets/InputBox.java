package mobile.web.webxt_mvc.client.widgets;


import mobile.web.webxt_mvc.client.form.PersistentField;
import mobile.web.webxt_mvc.client.validations.Validate;
import mobile.web.webxt_mvc.client.validations.ValidationTypes;
import mobile.web.webxt_mvc.client.validations.ValidationTypesValidator;

import com.extjs.gxt.ui.client.widget.form.TextField;

public class InputBox extends TextField<String> implements PersistentField {

	public CustomFormPanel frmPanel;

	String label;
	
	String entity;
	String field;
	int register;
	
	int minLenght;
	int maxLenght;
	int width;
	int posY;
	Validate type;
	
	public InputBox(String lbl, String entity, String field, int register, int wdth, Validate type) {
		
		this.setEntity(entity);
		this.setField(field);
		this.setRegister(register);
		
		this.label = lbl.trim();
		this.type = type;
		this.width = wdth;
		
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

	public String getEntity() {
		return entity;
	}

	public String getField() {
		return field;
	}

	public int getRegister() {
		return register;
	}

	public String setEntity(String entity) {
		this.entity=entity;
		return entity;
	}

	public String setField(String field) {
		this.field=field;
		return null;
	}

	public int setRegister(int register) {
		this.register=register;
		return 0;
	}


}