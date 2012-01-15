package mobile.message.message;

import java.util.ArrayList;
import java.util.List;

public class Item {

	public final static String NEW_ITEM = "_new_item";
	public final static String EXPIRE_ITEM = "_expire_item";

	private Integer number;

	private List<Field> fieldList = new ArrayList<Field>();

	public Item() {
		setNumber(null);
	}

	public Item(Integer number) {
		setNumber(number);
	}

	public String toXML() {
		String item = "<item";
		if (getNumber() != null) {
			item += " number=\"" + getNumber() + "\"";
		}
		item += ">";
		for (Field fields : getFieldList()) {
			item += fields.toXML();
		}
		item += "</item>";
		return item;
	}

	public String toJSON() {
		String item = "{";
		if (getNumber() != null) {
			item += "\"-number\":\"" + getNumber() + "\"";
		}
		if (getFieldList().size() > 0) {
			item += ",";
		}
		int i = 1;
		int fsize = getFieldList().size();
		for (Field fields : getFieldList()) {
			item += fields.toJSON();
			if (fsize > i++) {
				item += ",";
			}
		}
		item += "}";
		return item;
	}

	public void setNumber(Integer itemNumber) {
		number = 0;
		if (itemNumber != null) {
			number = itemNumber;
		}
	}

	public Integer getNumber() {
		return number;
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
		// return new Field(name, null);
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

	public void setNewItem(Boolean newItem) {
		String value = "0";
		if (newItem) {
			value = "1";
		}
		setFieldValue(Item.NEW_ITEM, value);
	}

	public Boolean isNewItem() {
		if (getFieldValue(Item.NEW_ITEM) != null) {
			return parseBoolean((String) getFieldValue(Item.NEW_ITEM));
		}else{
			return false;
		}
	}

	public void setExpireItem(Boolean expireItem) {
		String value = "0";
		if (expireItem) {
			value = "1";
		}
		setFieldValue(Item.EXPIRE_ITEM, value);
	}

	public Boolean isExpireItem() {
		if (getFieldValue(Item.EXPIRE_ITEM) != null) {
			return parseBoolean(getFieldValue(Item.EXPIRE_ITEM));
		}else{
			return false;
		}
	}

	private Boolean parseBoolean(String input) {
		Boolean result = false;
		if (input.compareToIgnoreCase("true") == 0 || input.compareTo("1") == 0 ) {
			result = true;
		}
		return result;
	}

}
