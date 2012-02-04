package mobile.web.webxt.client.form.widgets;

import mobile.web.webxt.client.data.form.DataSource;


public interface PersistentField {

	public DataSource getDataSource();
	
	public void setDataSource(DataSource dataSource);
}
