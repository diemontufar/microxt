package mobile.web.webxt.client.form.widgetsgrid;


import mobile.web.webxt.client.data.form.DataSource;
import mobile.web.webxt.client.data.form.DataSourceType;

import com.extjs.gxt.ui.client.widget.grid.ColumnData;


public class MyColumnData extends ColumnData{

	private int width;
	
	private int maxLength;
	
	private boolean allowBlank;
	
	private String associatedField;
	
	private boolean visible = true;
	
	private DataSource dataSource;
	
	public MyColumnData(String field) {
		setId(field);
		setDataSource(new DataSource(field));
		setVisible(false);
	}
	
	public MyColumnData(String alias, String field) {
		setId(field);
		setDataSource(new DataSource(alias, field));
		setVisible(false);
	}
	
	public MyColumnData(String alias, String field, String title, int width) {
		setId(field);
		setDataSource(new DataSource(alias, field, DataSourceType.RECORD));
		setName(title);
		setWidth(width);
	}
	
	public MyColumnData(String alias, String field, boolean visible) {
		setId(field);
		setDataSource(new DataSource(alias, field, DataSourceType.RECORD));
		setVisible(visible);
	}

	public MyColumnData(String field, String title, int width, int maxLength, boolean allowBlank) {
		setId(field);
		setName(title);
		setWidth(width);
		setMaxLength(maxLength);
		setAllowBlank(allowBlank);
		setDataSource(new DataSource(null, field, DataSourceType.RECORD));
	}
	
	public MyColumnData(String field, DataSourceType type, String header, int width, int maxLength, boolean allowBlank) {
		setId(field);
		setDataSource(new DataSource(field, DataSourceType.RECORD));
		setName(header);
		setMaxLength(maxLength);
		setAllowBlank(allowBlank);
	}

	public MyColumnData(DataSource ds, String title, int width, boolean allowBlank) {
		if(ds.getType() != DataSourceType.DESCRIPTION ){
			setId(ds.getField());
		}else{
			setId(ds.getAlias() + "_" + ds.getField());
		}
		setDataSource(ds);
		setName(title);
		setWidth(width);
		setAllowBlank(allowBlank);
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getMaxLength() {
		return maxLength;
	}

	public void setMaxLength(int maxLength) {
		this.maxLength = maxLength;
	}

	public boolean isAllowBlank() {
		return allowBlank;
	}

	public void setAllowBlank(boolean allowBlank) {
		this.allowBlank = allowBlank;
	}

	public String getAssociatedField() {
		return associatedField;
	}

	public void setAssociatedField(String associatedField) {
		this.associatedField = associatedField;
	}

	public boolean isVisible() {
		return visible;
	}

	public void setVisible(boolean visible) {
		this.visible = visible;
	}

	public DataSource getDataSource() {
		return dataSource;
	}

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

}
