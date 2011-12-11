package mobile.web.webxt_mvc.client.form;

import com.extjs.gxt.ui.client.widget.grid.ColumnData;


public class MyColumnData extends ColumnData implements ColumnDataInterface{
	
	public enum ColumnType{
		DESC,		// for description columns: not persistent, visible 
		HIDDEN		// for hidden columns: persistent, hidden 	
	}
	
	private int width;
	
	private int maxLength;
	
	private boolean allowBlank;
	
	private ColumnType columnType;
	
	private String descEntity;
	
	private String descField;
	
	private String descCriterion;
	
	private String associatedField;
	
	public MyColumnData(String id) {
		this.id = id;
	}
	
	public MyColumnData(String id, ColumnType type) {
		this.id = id;
		this.columnType = type;
	}
	
	public MyColumnData(String id, String name, int width) {
		this.id = id;
		this.name = name;
		this.width = width;
	}
	
	public MyColumnData(String id, String name, int width, ColumnType type) {
		this(id, name, width);
		this.columnType = type;
	}
	
	public MyColumnData(String id, String header, int width, int maxLength, boolean allowBlank) {
		this(id, header, width);
		this.maxLength = maxLength;
		this.allowBlank = allowBlank;
	}
	
	public MyColumnData(String id, String header, int width, int maxLength, boolean allowBlank, ColumnType type) {
		this(id, header, width, maxLength, allowBlank);
		this.columnType = type;
	}
	
	public void setDescriptionFields(String entity, String field, String criterion){
		this.descEntity = entity;
		this.descField = field;
		this.descCriterion = criterion;
	}
	
	public String getDescriptionEntity(){
		return this.descEntity;
	}
	
	public String getDescriptionField(){
		return this.descField;
	}
	
	public String getDescriptionCriterion(){
		return this.descCriterion;
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

	public ColumnType getColumnType() {
		return columnType;
	}

	public void setColumnType(ColumnType columnType) {
		this.columnType = columnType;
	}

	public String getAssociatedField() {
		return associatedField;
	}

	public void setAssociatedField(String associatedField) {
		this.associatedField = associatedField;
	}
	
	

}
