package mobile.web.webxt.client.form.widgets;


import mobile.web.webxt.client.data.form.DataSource;
import mobile.web.webxt.client.form.validations.Validate;
import mobile.web.webxt.client.form.validations.ValidationTypes;
import mobile.web.webxt.client.form.validations.ValidationTypesValidator;

import com.extjs.gxt.ui.client.event.BaseEvent;
import com.extjs.gxt.ui.client.event.Events;
import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.widget.form.TextField;
import com.google.gwt.user.client.Element;

public class InputBox extends TextField<String> implements PersistentField {

	DataSource dataSource;
	Validate type;
	boolean toolTip=false;
	
	public InputBox(){
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
		createValidator(type);
	}

	public InputBox(String lbl, String field, int width,int maxLenght, Validate type) {
		setDataSource(new DataSource(field));
		this.setFieldLabel(lbl.trim());
		this.setWidth(width);
		this.setMaxLength(maxLenght);
		createValidator(type);
	}

	public void createValidator(Validate type) {

		if (type == Validate.REQUIRED){
			setAllowBlank(false);
		}
		
		if (type == Validate.TEXT) {
			setValidator(new ValidationTypesValidator(ValidationTypes.ALPHABET));
		}

		if (type == Validate.ALPHANUMERIC) {
			setValidator(new ValidationTypesValidator(ValidationTypes.ALPHANUMERIC));
			setAllowBlank(true);
		}

		if (type == Validate.NUMERIC) {
			setValidator(new ValidationTypesValidator(ValidationTypes.NUMERIC));
			setAllowBlank(true);
		}

		if (type == Validate.PASSWORD) {
			setPassword(true);
		}

		if (type == Validate.EMAIL) {
			setValidator(new ValidationTypesValidator(ValidationTypes.EMAIL));
		}
		
		if (type == Validate.DATE) {
			setValidator(new ValidationTypesValidator(ValidationTypes.DATE));
			setToolTip("Formato: dd-mm-aaaa");
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
		
		this.addListener(Events.OnMouseOver, new Listener<BaseEvent>() {
		    public void handleEvent(BaseEvent be) {
		    	if(toolTip){
		    		setToolTip(getValue());
		    	}
		    }
		});
	}

	public DataSource getDataSource() {
		return dataSource;
	}

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	public boolean getToolTipValue() {
		return toolTip;
	}

	public void setToolTipValue(boolean value) {
		this.toolTip=value;
	}
	
}