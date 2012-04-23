package mobile.web.webxt.client.form.widgets;

import mobile.web.webxt.client.data.form.DataSource;
import mobile.web.webxt.client.form.validations.Validate;
import mobile.web.webxt.client.form.validations.ValidationTypes;
import mobile.web.webxt.client.form.validations.ValidationTypesValidator;

import com.extjs.gxt.ui.client.event.ComponentEvent;
import com.extjs.gxt.ui.client.event.KeyListener;
import com.extjs.gxt.ui.client.widget.form.TextField;
import com.google.gwt.user.client.Element;

public class InputBox extends TextField<String> implements PersistentField {

	DataSource dataSource;
	Validate type;
	boolean toolTip = false;
	boolean toUpperCase = true;

	public InputBox() {
		super();
	}

	public InputBox(int width) {
		this.setWidth(width);
	}

	public InputBox(int width, int maxLenght) {
		this.setWidth(width);
		this.setMaxLength(maxLenght);
	}

	public InputBox(int width, int maxLenght, Validate type) {
		this.setWidth(width);
		this.setMaxLength(maxLenght);
		setType(type);
	}

	public InputBox(String lbl, String field, int width, int maxLenght, Validate type) {
		setDataSource(new DataSource(field));
		this.setFieldLabel(lbl.trim());
		this.setWidth(width);
		this.setMaxLength(maxLenght);
		setType(type);
	}

	private void createValidator() {

		if (type == Validate.REQUIRED) {
			setAllowBlank(false);
		}

		if (type == Validate.TEXT) {
			setValidator(new ValidationTypesValidator(ValidationTypes.ALPHABET));
		}

		if (type == Validate.ALPHANUMERIC) {
			setValidator(new ValidationTypesValidator(ValidationTypes.ALPHANUMERIC));
			// setAllowBlank(true);
		}

		if (type == Validate.NUMERIC) {
			setValidator(new ValidationTypesValidator(ValidationTypes.NUMERIC));
			// setAllowBlank(true);
		}

		if (type == Validate.PASSWORD) {
			setPassword(true);
		}
		if (isPassword()) {
			toUpperCase = false;
		}

		if (type == Validate.EMAIL) {
			toUpperCase = false;
			setValidator(new ValidationTypesValidator(ValidationTypes.EMAIL));
		}

		if (type == Validate.DATE) {
			setValidator(new ValidationTypesValidator(ValidationTypes.DATE));
			setToolTip("Formato: dd-mm-aaaa");
			setMaxLength(10);
		}

		if (type == Validate.OTHER) {
			toUpperCase = false;
		}

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

		createValidator();

		if (toolTip) {
			setToolTip(getValue());
		}

		this.addKeyListener(new KeyListener() {
			@Override
			public void handleEvent(ComponentEvent e) {
				super.handleEvent(e);
				if (getValue() != null && toUpperCase) {
					setValue(getValue().toString().toUpperCase());
				}
			}
		});

		// this.addListener(Events.OnBlur, new Listener<FieldEvent>() {
		// public void handleEvent(FieldEvent e) {
		// if (getValue() != null && toUpperCase) {
		// setValue(getValue().toString().toUpperCase());
		// }
		// }
		// });
	}

	public DataSource getDataSource() {
		return dataSource;
	}

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	public boolean getIsToolTip() {
		return toolTip;
	}

	public void setToolTip(boolean toolTip) {
		this.toolTip = toolTip;
	}

	@Override
	public String getValue() {
		// if (super.getValue() != null && getType() != Validate.EMAIL &&
		// getType() != Validate.PASSWORD) {
		// // return super.getValue().toUpperCase();
		// return super.getValue();
		// } else {
		// return super.getValue();
		// }
		return super.getValue();
	}

	public Validate getType() {
		return type;
	}

	public void setType(Validate type) {
		this.type = type;
	}

	@Override
	public void setReadOnly(boolean readOnly) {
		super.setReadOnly(readOnly);
		setTabIndex(-1);
		if (isReadOnly()) {
			setStyleAttribute("color", "gray");
		}
	}
}