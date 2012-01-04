package mobile.web.webxt.client.validations;

import com.extjs.gxt.ui.client.widget.form.Field;
import com.extjs.gxt.ui.client.widget.form.Validator;

public class ValidationTypesValidator implements Validator {

	private ValidationTypes type;

	public ValidationTypesValidator(ValidationTypes type) {
		this.type = type;
	}

	public String validate(Field<?> field, String value) {
		String res = null;
		if (!value.matches(type.regex)) {
			res = value + " Es un valor:  " + type.name + " incorrecto";
		}
		return res;
	}

}
