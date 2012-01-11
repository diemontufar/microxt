package mobile.web.webxt.client.widgets;

import mobile.web.webxt.client.formtools.PersistentField;

import com.extjs.gxt.ui.client.widget.form.TextArea;

public class TextBox extends TextArea implements PersistentField{

	String fieldInfo;
	
	public TextBox(String fieldLabel, String fieldInfo, int width, int maxLength){
		
		this.setFieldInfo(fieldInfo);
		this.setFieldLabel(fieldLabel);
		this.setWidth(width);
		this.setMaxLength(maxLength);
		
	}
	
	public String getFieldInfo() {
		return fieldInfo;
	}

	public void setFieldInfo(String field) {
		this.fieldInfo=field;
	}

}
