package mobile.message.message;

import java.util.ArrayList;
import java.util.List;

public class Data {

	public final static String PROCESS_TYPE = "_type";
	public final static String QRY_FIELDS = "_qry_fields";
	public final static String OFFSET_PAGE = "_offset_page";
	public final static String LIMIT_PAGE = "_limit_page";
	public final static String TOTAL_PAGE = "_total_page";
	public final static String ORDER_BY = "_order_by";
	public final static String ORDER_DIR = "_order_dir";
	public final static String FILTERS = "_filters";

	private String id;

	private List<Field> fieldList = new ArrayList<Field>();

	private List<Item> itemList = new ArrayList<Item>();

	public Data() {
		setId(null);
	}

	public Data(String id) {
		setId(id);
	}

	public String toXML() {
		String data = "<" + "data";
		if (getId() != null && getId().compareTo("") != 0) {
			data += " id=\"" + getId() + "\"";
		}
		data += ">";
		for (Field field : getFieldList()) {
			data += field.toXML();
		}
		for (Item item : getItemList()) {
			data += item.toXML();
		}
		data += "</data>";
		return data;
	}

	public String toJSON() {
		String data = "{";
		if (getId() != null && getId().compareTo("") != 0) {
			data += "\"-id\":\"" + getId() + "\"";
		}
		if (getFieldList().size() > 0 || getItemList().size() > 0) {
			data += ",";
		}
		int i = 1;
		int fsize = getFieldList().size();
		for (Field field : getFieldList()) {
			data += field.toJSON();
			if (fsize > i++) {
				data += ",";
			}
		}
		if (getFieldList().size() > 0 && getItemList().size() > 0) {
			data += ",";
		}
		if (getItemList().size() > 0) {
			data += "\"item\": [";
		}
		int j = 1;
		int isize = getItemList().size();
		for (Item item : getItemList()) {
			data += item.toJSON();
			if (isize > j++) {
				data += ",";
			}
		}
		if (getItemList().size() > 0) {
			data += "]";
		}
		data += "}";
		return data;
	}

	public void setId(String dataId) {
		id = "noname";
		if (dataId != null) {
			id = dataId.trim();
		}
	}

	public String getId() {
		return id;
	}

	public void addField(String name, String value) {
		Field field = new Field(name, value);
		addField(field);
	}

	public void addField(Field field) {
		if (getField(field.getName()) == null) {
			fieldList.add(field);
		}
	}

	public Field getField(String name) {
		for (Field field : fieldList) {
			if ((field.getName().compareTo(name)) == 0) {
				return field;
			}
		}
		
		return null;
	}

	public void removeField(String name) {
		for (Field field : fieldList) {
			if ((field.getName().compareTo(name)) == 0) {
				fieldList.remove(field);
			}
		}
	}

	public List<Field> getFieldList() {
		return fieldList;
	}
	
	public void setFieldList(List<Field> fieldList) {
		this.fieldList = fieldList;
	}

	public void addItem(Item item) {
		if (getItem(item.getNumber()) == null) {
			itemList.add(item);
		}
	}

	public Item getItem(Integer number) {
		for (Item item : itemList) {
			if ((item.getNumber().compareTo(number)) == 0) {
				return item;
			}
		}
		return null;
	}

	public void removeItem(Integer number) {
		for (Item item : itemList) {
			if ((item.getNumber().compareTo(number)) == 0) {
				itemList.remove(item);
			}
		}
	}

	public List<Item> getItemList() {
		return itemList;
	}

	public Field getCreateField(String name) {
		Field field = getField(name);
		if (field != null) {
			return field;
		} else {
			field = new Field(name);
			addField(field);
			return field;
		}
	}
	
	public void setFieldValue(String name, String value) {
		Field field = getCreateField(name);
		field.setValue(value);
	}
	
	public String getFieldValue(String name) {
		Field field = getField(name);
		if (field != null) {
			return field.getValue();
		} else {
			return null;
		}
	}
	
	//----------------------------------------------------
	// Special fields
	//----------------------------------------------------

	public void setProcessType(String processId) {
		setFieldValue(Data.PROCESS_TYPE, processId);
	}

	public String getProcessType() {
		return getFieldValue(Data.PROCESS_TYPE);
	}

	public void setQueryFields(String queryFields) {
		setFieldValue(Data.QRY_FIELDS, queryFields);
	}

	public String getQueryFields() {
		return getFieldValue(Data.QRY_FIELDS);
	}

	public void setOffset(Integer offset) {
		setFieldValue(Data.OFFSET_PAGE, offset.toString());
	}

	public Integer getOffset() {
		return Integer.parseInt(getFieldValue(Data.OFFSET_PAGE));
	}

	public void setLimit(Integer limit) {
		setFieldValue(Data.LIMIT_PAGE, limit.toString());
	}

	public Integer getLimit() {
		return Integer.parseInt(getFieldValue(Data.LIMIT_PAGE));
	}

	public void setTotal(Integer total) {
		setFieldValue(Data.TOTAL_PAGE, total.toString());
	}

	public Integer getTotal() {
		return Integer.parseInt(getFieldValue(Data.TOTAL_PAGE));
	}
	
	public void setOrderBy(String order) {
		setFieldValue(Data.ORDER_BY, order);
	}

	public String getOrderBy() {
		return getFieldValue(Data.ORDER_BY);
	}
	
	public void setOrderDir(String orderDir) {
		setFieldValue(Data.ORDER_DIR, orderDir);
	}

	public String getOrderDir() {
		return getFieldValue(Data.ORDER_DIR);
	}
	
	public void setFilter(String filters) {
		setFieldValue(Data.FILTERS, filters);
	}

	public String getFilters() {
		return getFieldValue(Data.FILTERS);
	}
}
