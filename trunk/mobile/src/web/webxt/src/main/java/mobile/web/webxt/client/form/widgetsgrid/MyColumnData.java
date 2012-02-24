package mobile.web.webxt.client.form.widgetsgrid;

import mobile.web.webxt.client.data.form.DataSource;
import mobile.web.webxt.client.data.form.DataSourceType;

import com.extjs.gxt.ui.client.widget.grid.ColumnData;

public class MyColumnData extends ColumnData {

	private int width;

	private int maxLength;

	private boolean allowBlank;

	private String associatedField;

	private boolean readOnly = false;

	private boolean visible = true;

	private DataSource dataSource;

	/**
	 * Creates a data source RECORD with the alias in <code>null</code> and the
	 * provided field
	 */
	public MyColumnData(String field) {
		setId(field);
		setDataSource(new DataSource(field));
		setVisible(false);
	}

	/**
	 * Creates a data source RECORD with the provided alias and field<br/>
	 * without visibility
	 */
	public MyColumnData(String alias, String field) {
		setId(field);
		setDataSource(new DataSource(alias, field, DataSourceType.RECORD));
		setVisible(false);
	}

	/**
	 * Creates a data source with the provided field
	 */
	public MyColumnData(String field, String title, int width) {
		setId(field);
		setDataSource(new DataSource(null, field, DataSourceType.RECORD));
		setName(title);
		setWidth(width);
	}
	
	/**
	 * Creates a data source with the provided field 
	 * For read only columns
	 */
	public MyColumnData(String field, String title, int width, boolean readOnly) {
		setId(field);
		setDataSource(new DataSource(null, field, DataSourceType.RECORD));
		setName(title);
		setWidth(width);
		setReadOnly(readOnly);
	}

	/**
	 * Creates a data source with the provided alias, field
	 */
	public MyColumnData(String alias, String field, String title, int width) {
		setId(field);
		setDataSource(new DataSource(alias, field, DataSourceType.RECORD));
		setName(title);
		setWidth(width);
	}

	/**
	 * Creates a data source RECORD with the provided alias and field<br/>
	 * and the provided visibility
	 */
	public MyColumnData(String alias, String field, boolean visible) {
		setId(field);
		setDataSource(new DataSource(alias, field, DataSourceType.RECORD));
		setVisible(visible);
	}

	/**
	 * Creates a data source RECORD with alias in null and the provided field
	 */
	public MyColumnData(String field, String title, int width, int maxLength, boolean allowBlank) {
		setId(field);
		setName(title);
		setWidth(width);
		setMaxLength(maxLength);
		setAllowBlank(allowBlank);
		setDataSource(new DataSource(null, field, DataSourceType.RECORD));
	}

	/**
	 * If type is DESCRIPTION applies a different model data property structure
	 */
	public MyColumnData(DataSource ds, String title, int width, boolean allowBlank) {
		if (ds.getType() != DataSourceType.DESCRIPTION) {
			setId(ds.getField());
		} else {
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

	public boolean isReadOnly() {
		return readOnly;
	}

	public void setReadOnly(boolean readOnly) {
		this.readOnly = readOnly;
	}

	public DataSource getDataSource() {
		return dataSource;
	}

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

}
