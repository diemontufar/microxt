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

	public void setNewItem(Boolean newItem) {
		setValue(Item.NEW_ITEM, newItem);
	}

	public Boolean isNewItem() {
		return (Boolean) getValue(Item.NEW_ITEM);
	}

	public void setExpireItem(Boolean expireItem) {
		setValue(Item.EXPIRE_ITEM, expireItem);
	}

	public Boolean isExpireItem() {
		return (Boolean) getValue(Item.EXPIRE_ITEM);
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
