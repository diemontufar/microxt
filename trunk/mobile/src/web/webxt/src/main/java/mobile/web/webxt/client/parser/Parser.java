package mobile.web.webxt.client.parser;

import java.util.Iterator;

import mobile.common.message.Data;
import mobile.common.message.Field;
import mobile.common.message.Item;
import mobile.common.message.Message;

import com.google.gwt.json.client.JSONArray;
import com.google.gwt.json.client.JSONObject;
import com.google.gwt.json.client.JSONParser;
import com.google.gwt.xml.client.Document;
import com.google.gwt.xml.client.Element;
import com.google.gwt.xml.client.Node;
import com.google.gwt.xml.client.NodeList;
import com.google.gwt.xml.client.XMLParser;

public class Parser {

	public Message parseMsg(String messageData, String messageType)
			throws Exception {

		Message message = new Message();
		if (messageType.compareTo(Message.XML) == 0) {
			Document document = XMLParser.parse(messageData);
			Node node = document.getFirstChild();
			NodeList nodeList = node.getChildNodes();
			for (int i = 0; i < nodeList.getLength(); i++) {
				Node n = nodeList.item(i);
				if (n.getParentNode().getNodeName().compareTo("message") == 0
						&& n.getNodeType() == Node.ELEMENT_NODE) {
					message.addData(createData(n));
				}
			}
		} else if (messageType.compareTo(Message.JSON) == 0) {
			JSONObject jsonMessage = (JSONObject) JSONParser.parseStrict(
					messageData).isObject();
			JSONObject jsonObject = jsonMessage.get("message").isObject();
			JSONArray jsonList = jsonObject.get("data").isArray();
			for (int i = 0; i < jsonList.size(); i++) {
				JSONObject json = jsonList.get(i).isObject();
				message.addData(createData(json));
			}
		}

		return message;
	}

	private Data createData(Node node) throws Exception {
		Data data = new Data();
		if (node.getNodeType() == Node.ELEMENT_NODE) {
			Element element = (Element) node;
			String id = element.getAttribute("id");
			if (id != null && id.compareTo("") != 0) {
				data.setDataId(id.trim());
			}
			NodeList nodeList = element.getChildNodes();
			for (int i = 0; i < nodeList.getLength(); i++) {
				Node n = nodeList.item(i);
				if (n.getParentNode().getNodeName().compareTo("data") == 0
						&& n.getNodeType() == Node.ELEMENT_NODE) {
					if (n.getNodeName().compareTo("item") != 0) {
						data.getFieldList().add(createField(n));
					} else {
						data.getItemList().add(createItem(n));
					}
				}
			}
		}
		return data;
	}

	@SuppressWarnings("rawtypes")
	private Data createData(JSONObject jsonObject) {
		Data data = new Data();
		for (Iterator i = jsonObject.keySet().iterator(); i.hasNext();) {
			String key = (String) i.next();
			if (key.compareTo("-id") == 0) {
				String id = jsonObject.get(key).toString().replace("\"", "");
				if (id != null && id.compareTo("") != 0) {
					data.setDataId(id.trim());
				}
			} else {
				if (key.compareTo("item") != 0) {
					Field field = new Field(key, jsonObject.get(key).toString()
							.replace("\"", ""));
					data.getFieldList().add(field);
				} else {
					JSONArray jsonList = jsonObject.get(key).isArray();
					for (int j = 0; j < jsonList.size(); j++) {
						JSONObject json = jsonList.get(j).isObject();
						data.addItem(createItem(json));
					}
				}
			}
		}
		return data;
	}

	private Item createItem(Node node) {
		Item item = new Item();
		if (node.getNodeType() == Node.ELEMENT_NODE) {
			Element element = (Element) node;
			String number = element.getAttribute("number");
			if (number != null && number.compareTo("") != 0) {
				item.setNumber(Integer.valueOf(number.trim()));
			}
			NodeList nodeList = element.getChildNodes();
			for (int i = 0; i < nodeList.getLength(); i++) {
				Node n = nodeList.item(i);
				if (n.getParentNode().getNodeName().compareTo("item") == 0
						&& n.getNodeType() == Node.ELEMENT_NODE) {
					item.getFieldList().add(createField(n));
				}
			}
		}
		return item;
	}

	@SuppressWarnings("rawtypes")
	private Item createItem(JSONObject jsonObject) {
		Item item = new Item();
		for (Iterator i = jsonObject.keySet().iterator(); i.hasNext();) {
			String key = (String) i.next();
			if (key.compareTo("-number") == 0) {
				String number = jsonObject.get(key).toString()
						.replace("\"", "");
				if (number != null && number.compareTo("") != 0) {
					item.setNumber(Integer.valueOf(number.trim()));
				}
			} else {
				Field field = new Field(key, jsonObject.get(key).toString()
						.replace("\"", ""));
				item.getFieldList().add(field);
			}
		}
		return item;
	}

	private Field createField(Node node) {
		Field field = new Field();
		if (node.getNodeType() == Node.ELEMENT_NODE) {
			Element element = (Element) node;
			String name = element.getNodeName();
			if (name != null && name.compareTo("") != 0) {
				field.setName(name.trim());
			}
			String value = element.getNodeValue();
			if (value != null && value.compareTo("") != 0) {
				field.setValue(value.trim());
			}
		}
		return field;
	}

}