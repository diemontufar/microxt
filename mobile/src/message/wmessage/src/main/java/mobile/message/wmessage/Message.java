package mobile.message.wmessage;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.json.client.JSONArray;
import com.google.gwt.json.client.JSONObject;
import com.google.gwt.xml.client.Node;
import com.google.gwt.xml.client.NodeList;

public class Message {

	private List<Data> dataList = new ArrayList<Data>();

	public Message() {
	}

	public Message(Parser parser) throws Exception {
		if (parser.getMessageType().compareTo(Parser.XML) == 0) {
			Node node = parser.getDocument().getFirstChild();
			NodeList nodeList = node.getChildNodes();
			for (int i = 0; i < nodeList.getLength(); i++) {
				Node n = nodeList.item(i);
				if (n.getParentNode().getNodeName().compareTo("message") == 0
						&& n.getNodeType() == Node.ELEMENT_NODE) {
					Data data = new Data(n);
					addData(data);
				}
			}
		} else if (parser.getMessageType().compareTo(Parser.JSON) == 0) {
			JSONObject jsonObject = parser.getJSON().get("message").isObject();
			JSONArray jsonList = jsonObject.get("data").isArray();
			for (int i = 0; i < jsonList.size(); i++) {
				JSONObject json = jsonList.get(i).isObject();
				Data data = new Data(json);
				addData(data);
			}
		}
	}

	public String toXML() {
		String message = "<message>";
		for (Data data : getDataList()) {
			message += data.toXML();
		}
		message += "</message>";
		return message;
	}

	public String toJSON() {
		String message = "{\"message\": {";
		if (getDataList().size() > 0) {
			message += "\"data\": [";
		}
		int i = 1;
		int dsize = getDataList().size();
		for (Data data : getDataList()) {
			message += data.toJSON();
			if (dsize > i++) {
				message += ",";
			}
		}
		if (getDataList().size() > 0) {
			message += "]";
		}
		message += "}}";

		return message;
	}

	public void addData(Data data) {
		if (getData(data.getId()) == null) {
			dataList.add(data);
		}
	}

	public Data getData(String id) {
		for (Data data : dataList) {
			if ((data.getId().compareTo(id)) == 0) {
				return data;
			}
		}
		return null;
	}

	public void removeData(String id) {
		for (Data data : dataList) {
			if ((data.getId().compareTo(id)) == 0) {
				dataList.remove(data);
			}
		}
	}

	public List<Data> getDataList() {
		return dataList;
	}
}
