package mobile.tools.common.msg;

import java.io.StringReader;
import java.util.Iterator;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import mobile.message.message.Data;
import mobile.message.message.Field;
import mobile.message.message.Item;
import mobile.message.message.Message;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JSONSerializer;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

public class Parser {

	@SuppressWarnings("rawtypes")
	public Message parseMsg(String messageData, String messageType)
			throws Exception {
		Message message = new Message(messageType);
		if (messageType.compareTo(Message.XML) == 0) {
			DocumentBuilder documentBuilder = DocumentBuilderFactory
					.newInstance().newDocumentBuilder();
			Document document = documentBuilder.parse(new InputSource(
					new StringReader(messageData)));
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
			JSONObject jsonMessage = (JSONObject) JSONSerializer
					.toJSON(messageData);
			JSONObject jsonObject = jsonMessage.getJSONObject("message");
			JSONArray jsonList = jsonObject.getJSONArray("data");
			for (Iterator i = jsonList.iterator(); i.hasNext();) {
				JSONObject json = (JSONObject) i.next();
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
				data.setId(id.trim());
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
		for (Iterator i = jsonObject.keys(); i.hasNext();) {
			String key = (String) i.next();
			if (key.compareTo("-id") == 0) {
				String id = jsonObject.getString(key);
				if (id != null && id.compareTo("") != 0) {
					data.setId(id.trim());
				}
			} else {
				if (key.compareTo("item") != 0) {
					Field field = new Field(key, jsonObject.getString(key));
					data.getFieldList().add(field);
				} else {
					JSONArray jsonList = JSONArray.fromObject(jsonObject
							.get(key));
					for (Iterator j = jsonList.iterator(); j.hasNext();) {
						JSONObject json = (JSONObject) j.next();
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
		for (Iterator i = jsonObject.keys(); i.hasNext();) {
			String key = (String) i.next();
			if (key.compareTo("-number") == 0) {
				String number = jsonObject.getString(key);
				if (number != null && number.compareTo("") != 0) {
					item.setNumber(Integer.valueOf(number.trim()));
				}
			} else {
				Field field = new Field(key, jsonObject.getString(key));
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
			String value = element.getTextContent();
			if (value != null && value.compareTo("") != 0) {
				field.setValue(value.trim());
			}
		}
		return field;
	}
}
