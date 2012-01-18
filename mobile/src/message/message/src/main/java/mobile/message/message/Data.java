package mobile.message.message;

import java.util.ArrayList;
import java.util.List;

public class Data {

	private String id;

	private List<Field> fieldList = new ArrayList<Field>();

	private List<Item> itemList = new ArrayList<Item>();

	public Data() {
		setId(null);
	}

	public Data(String id) {
		setId(id);
	}

	protected String toXML() {
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

	protected String toJSON() {
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

	protected String getId() {
		return id;
	}

	protected void addField(String name, String value) {
		Field field = new Field(name, value);
		addField(field);
	}

	protected void addField(Field field) {
		if (getField(field.getName()) == null) {
			fieldList.add(field);
		}
	}

	protected Field getField(String name) {
		for (Field field : fieldList) {
			if ((field.getName().compareTo(name)) == 0) {
				return field;
			}
		}
		
		return null;
	}

	protected void removeField(String name) {
		for (Field field : fieldList) {
			if ((field.getName().compareTo(name)) == 0) {
				fieldList.remove(field);
			}
		}
	}

	public List<Field> getFieldList() {
		return fieldList;
	}
	
	protected void setFieldList(List<Field> fieldList) {
		this.fieldList = fieldList;
	}

	public void addItem(Item item) {
		if (getItem(item.getNumber()) == null) {
			itemList.add(item);
		}
	}

	protected Item getItem(Integer number) {
		for (Item item : itemList) {
			if ((item.getNumber().compareTo(number)) == 0) {
				return item;
			}
		}
		return null;
	}

	protected void removeItem(Integer number) {
		for (Item item : itemList) {
			if ((item.getNumber().compareTo(number)) == 0) {
				itemList.remove(item);
			}
		}
	}

	protected void setItemList(List<Item> itemList) {
		this.itemList = itemList;
	}
	
	public List<Item> getItemList() {
		return itemList;
	}

	protected Field getCreateField(String name) {
		Field field = getField(name);
		if (field != null) {
			return field;
		} else {
			field = new Field(name);
			addField(field);
			return field;
		}
	}
	
	protected void setFieldValue(String name, String value) {
		Field field = getCreateField(name);
		field.setValue(value);
	}
	
	protected String getFieldValue(String name) {
		Field field = getField(name);
		if (field != null) {
			return field.getValue();
		} else {
			return null;
		}
	}
}
