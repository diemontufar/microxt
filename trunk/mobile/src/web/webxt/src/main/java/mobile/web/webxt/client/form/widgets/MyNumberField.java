package mobile.web.webxt.client.form.widgets;

import com.extjs.gxt.ui.client.widget.form.NumberField;

public class MyNumberField extends NumberField implements PersistentField{

	private String persistentInfo;
	
	public MyNumberField() {
		super();
	}
	
	public String getPersistentInfo() {
		return persistentInfo;
	}

	public void setPersistentInfo(String persistentInfo) {
		this.persistentInfo = persistentInfo;
	}

}
