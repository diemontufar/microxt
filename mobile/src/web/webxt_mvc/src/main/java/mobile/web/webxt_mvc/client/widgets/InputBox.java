package mobile.web.webxt_mvc.client.widgets;


import mobile.web.webxt_mvc.client.validations.ValidationTypes;
import mobile.web.webxt_mvc.client.validations.ValidationTypesValidator;

import com.extjs.gxt.ui.client.widget.form.TextField;

public class InputBox extends TextField<String> {

	public CustomFormPanel frmPanel;

	String label;
	String nameEntity;
	int minLenght;
	int maxLenght;
	int width;
	int posY;
	String type;

	public InputBox(String lbl,String name, int wdth, String Type) {
		
		this.nameEntity=name.trim();
		this.label = lbl.trim();
		this.type = Type;
		this.width = wdth;
		createInput();
	}

	private void createInput() {

		if (type.compareTo("txt") == 0) {
			setValidator(new ValidationTypesValidator(ValidationTypes.ALPHABET));
		}

		if (type.compareTo("txt-alfanum") == 0) {
			setValidator(new ValidationTypesValidator(ValidationTypes.ALPHANUMERIC));
			setAllowBlank(true);
		}

		if (type.compareTo("num") == 0) {
			setValidator(new ValidationTypesValidator(ValidationTypes.NUMERIC));
			setAllowBlank(true);
		}

		if (type.compareTo("pass") == 0) {
			setPassword(true);
		}

		if (type.compareTo("email") == 0) {
			setValidator(new ValidationTypesValidator(ValidationTypes.EMAIL));
		}
		
		setName(name);
		setFieldLabel(label);
		setMinLength(4);
		setWidth(width);
	}
}