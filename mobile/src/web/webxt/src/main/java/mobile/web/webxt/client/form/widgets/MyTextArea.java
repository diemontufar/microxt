package mobile.web.webxt.client.form.widgets;


import com.extjs.gxt.ui.client.widget.form.TextArea;
import com.google.gwt.user.client.Element;

public class MyTextArea extends TextArea implements PersistentField{

	String fieldInfo;
	
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
	
	public MyTextArea(int width, int maxLength){
		this.setWidth(width);
		this.setMaxLength(maxLength);
	}
	
	public MyTextArea(int width, int height, int maxLength){
		this.setWidth(width);
		this.setHeight(height);
		this.setMaxLength(maxLength);
	}

	public MyTextArea(String fieldLabel, String fieldInfo, int width, int maxLength){
		this.setFieldInfo(fieldInfo);
		this.setFieldLabel(fieldLabel);
		this.setWidth(width);
		this.setMaxLength(maxLength);
	}

	public MyTextArea(String fieldLabel, String fieldInfo, int width, int height, int maxLength){
		this.setFieldInfo(fieldInfo);
		this.setFieldLabel(fieldLabel);
		this.setWidth(width);
		this.setHeight(height);
		this.setMaxLength(maxLength);
	}

	public String getFieldInfo() {
		return fieldInfo;
	}

	public void setFieldInfo(String field) {
		this.fieldInfo=field;
	}

}
