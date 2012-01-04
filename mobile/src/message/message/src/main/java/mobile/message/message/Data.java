package mobile.message.message;

import java.util.ArrayList;
import java.util.List;

public class Data {

	public final static String READ_ONLY = "_readonly";
	public final static String OFFSET_PAGE = "_offset_page";
	public final static String LIMIT_PAGE = "_limit_page";
	public final static String TOTAL_PAGE = "_total_page";
	public final static String ORDER_BY = "_order_by";
	public final static String ORDER_DIR = "_order_dir";

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
		if (getField(field.getName()) == null) {
			fieldList.add(field);
		}
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
		//return new Field(name, null);
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

	public void setReadOnly(Boolean readonly) {
		setValue(Data.READ_ONLY, readonly);
	}

	public Boolean isReadonly() {
		return (Boolean) getValue(Data.READ_ONLY);
	}

	public void setOffset(Integer offset) {
		setValue(Data.OFFSET_PAGE, offset);
	}

	public Integer getOffset() {
		return (Integer) getValue(Data.OFFSET_PAGE);
	}

	public void setLimit(Integer limit) {
		setValue(Data.LIMIT_PAGE, limit);
	}

	public Integer getLimit() {
		return (Integer) getValue(Data.LIMIT_PAGE);
	}

	public void setTotal(Integer total) {
		setValue(Data.TOTAL_PAGE, total);
	}

	public Integer getTotal() {
		return (Integer) getValue(Data.TOTAL_PAGE);
	}

	public void setOrderBy(String orderby) {
		setValue(Data.ORDER_BY, orderby);
	}

	public String getOrderBy() {
		return (String) getValue(Data.ORDER_BY);
	}

	public void setOrderDir(String orderdir) {
		setValue(Data.ORDER_DIR, orderdir);
	}

	public String getOrderDir() {
		return (String) getValue(Data.ORDER_DIR);
	}

	private void setValue(String name, Object object) {
		Field parameter = new Field(name, object.toString());
		for (Field field : fieldList) {
			if ((field.getName().compareTo(parameter.getName())) == 0) {
				field.setValue(parameter.getValue());
			} else {
				fieldList.add(parameter);
			}
		}
	}

	private Object getValue(String name) {
		Field parameter = getField(name);
		if (parameter != null) {
			return parameter.getValue();
		} else {
			return null;
		}
	}
}
