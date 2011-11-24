package mobile.web.message;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.google.gwt.json.client.JSONObject;
import com.google.gwt.xml.client.Element;
import com.google.gwt.xml.client.Node;
import com.google.gwt.xml.client.NodeList;

public class Item {

	private Integer number;

	private List<Field> fieldList = new ArrayList<Field>();

	public Item(Integer number) {
		setNumber(number);
	}

	public Item(Node node) {
		if (node.getNodeType() == Node.ELEMENT_NODE) {
			Element element = (Element) node;
			String number = element.getAttribute("number");
			if (number != null && number.compareTo("") != 0) {
				setNumber(Integer.valueOf(number.trim()));
			}
			NodeList nodeList = element.getChildNodes();
			for (int i = 0; i < nodeList.getLength(); i++) {
				Node n = nodeList.item(i);
				if (n.getParentNode().getNodeName().compareTo("item") == 0 && n.getNodeType() == Node.ELEMENT_NODE) {
					Field field = new Field(n);
					fieldList.add(field);
				}
			}
		}
	}

	@SuppressWarnings("rawtypes")
	public Item(JSONObject jsonObject) {
		for (Iterator i = jsonObject.keySet().iterator(); i.hasNext();) {
			String key = (String) i.next();
			if (key.compareTo("-number") == 0) {
				// FIXME
				//String number = jsonObject.get(key).toString();
				String number = jsonObject.get(key).toString().replace("\"", "");
				if (number != null && number.compareTo("") != 0) {
					setNumber(Integer.valueOf(number.trim()));
				}
			} else {
				// FIXME
				Field field = new Field(key, jsonObject.get(key).toString().replace("\"", ""));
				fieldList.add(field);
			}
		}
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
		number = itemNumber;
	}

	public Integer getNumber() {
		return number;
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
}
