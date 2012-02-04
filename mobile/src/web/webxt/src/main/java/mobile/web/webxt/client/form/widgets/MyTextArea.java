package mobile.web.webxt.client.form.widgets;


import mobile.web.webxt.client.data.form.DataSource;

import com.extjs.gxt.ui.client.widget.form.TextArea;
import com.google.gwt.user.client.Element;

public class MyTextArea extends TextArea implements PersistentField{

	DataSource dataSource;

	public MyTextArea(String label){
		setFieldLabel(label);
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

	public DataSource getDataSource() {
		return dataSource;
	}

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}


}
