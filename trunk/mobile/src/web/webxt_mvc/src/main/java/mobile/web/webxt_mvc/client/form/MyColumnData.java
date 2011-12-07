package mobile.web.webxt_mvc.client.form;

import com.extjs.gxt.ui.client.widget.grid.ColumnData;


public class MyColumnData extends ColumnData{
	private int width;
	
	private int maxLength;
	
	private boolean allowBlank;
	
	public MyColumnData(String id, String name, int width) {
		this.id = id;
		this.name = name;
		this.width = width;
	}
	
	public MyColumnData(String id, String header, int width, int maxLength, boolean allowBlank) {
		this(id, header, width);
		this.maxLength = maxLength;
		this.allowBlank = allowBlank;
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

}
