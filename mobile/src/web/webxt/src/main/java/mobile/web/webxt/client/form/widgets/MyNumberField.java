package mobile.web.webxt.client.form.widgets;

import mobile.web.webxt.client.data.form.DataSource;

import com.extjs.gxt.ui.client.widget.form.NumberField;

public class MyNumberField extends NumberField implements PersistentField {

	private DataSource dataSource; 
	
	public MyNumberField() {
		super();
	}

	public MyNumberField(int width) {
		super();
		setWidth(width);
	}
	
	public DataSource getDataSource() {
		return dataSource;
	}

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

}