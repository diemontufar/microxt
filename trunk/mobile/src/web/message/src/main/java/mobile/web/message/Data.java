package mobile.web.message;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.google.gwt.json.client.JSONArray;
import com.google.gwt.json.client.JSONObject;
import com.google.gwt.json.client.JSONValue;
import com.google.gwt.xml.client.Element;
import com.google.gwt.xml.client.Node;
import com.google.gwt.xml.client.NodeList;

public class Data {

	private String id;

	private List<Field> fieldList = new ArrayList<Field>();

	private List<Item> itemList = new ArrayList<Item>();

	public Data() {

	}

	public Data(String id) {
		setId(id.trim());
	}

	public Data(Node node) throws Exception {
		if (node.getNodeType() == Node.ELEMENT_NODE) {
			Element element = (Element) node;
			String id = element.getAttribute("id");
			if (id != null && id.compareTo("") != 0) {
				setId(id.trim());
			}
			NodeList nodeList = element.getChildNodes();
			for (int i = 0; i < nodeList.getLength(); i++) {
				Node n = nodeList.item(i);
				if (n.getParentNode().getNodeName().compareTo("data") == 0 && n.getNodeType() == Node.ELEMENT_NODE) {
					if (n.getNodeName().compareTo("item") != 0) {
						Field field = new Field(n);
						fieldList.add(field);
					} else {
						Item item = new Item(n);
						itemList.add(item);
					}
				}
			}
		}
	}

	@SuppressWarnings("rawtypes")
	public Data(JSONObject jsonObject) {
		for (Iterator i = jsonObject.keySet().iterator(); i.hasNext();) {
			String key = (String) i.next();
			if (key.compareTo("-id") == 0) {
				// FIXME
				String id = jsonObject.get(key).toString().replace("\"", "");
				if (id != null && id.compareTo("") != 0) {
					setId(id.trim());
				}
			} else {
				if (key.compareTo("item") != 0) {
					// FIXME
					JSONValue jv = jsonObject.get(key);
					Field field = new Field(key, jv.toString().replace("\"", ""));
					fieldList.add(field);
				} else {
					JSONArray jsonList = jsonObject.get(key).isArray();
					for (int j = 0; j <= jsonList.size() - 1; j++) {
						JSONObject json = jsonList.get(j).isObject();
						Item item = new Item(json);
						addItem(item);
					}
				}
			}
		}
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
		id = dataId;
	}

	public String getId() {
		return id;
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
}
