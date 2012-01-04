package mobile.web.webxt.client.gridtools;

import mobile.web.webxt.client.gridtools.MyColumnData.ColumnType;

public interface ColumnDataInterface {

	public String getId();

	public void setId(String id);

	public String getName();

	public void setName(String name);

	public int getWidth();

	public void setWidth(int width);

	public int getMaxLength();

	public void setMaxLength(int maxLength);

	public boolean isAllowBlank();

	public void setAllowBlank(boolean allowBlank);
	
	public ColumnType getColumnType();

	public void setColumnType(ColumnType columnType);
	
	public String getDescriptionEntity();
	
	public String getDescriptionField();
	
	public String getDescriptionCriterion();

	public String getAssociatedField();

	public void setAssociatedField(String associatedField);
}
