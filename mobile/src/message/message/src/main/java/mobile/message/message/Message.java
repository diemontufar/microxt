package mobile.message.message;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class Message {

	private List<Data> dataList = new ArrayList<Data>();

	public Message() {
	}

	@SuppressWarnings("rawtypes")
	public Message(Parser parser) throws Exception {
		if (parser.getMessageType().compareTo(Parser.XML) == 0) {
			Node node = parser.getDocument().getFirstChild();
			NodeList nodeList = node.getChildNodes();
			for (int i = 0; i < nodeList.getLength(); i++) {
				Node n = nodeList.item(i);
				if (n.getParentNode().getNodeName().compareTo("message") == 0 && n.getNodeType() == Node.ELEMENT_NODE) {
					Data data = new Data(n);
					addData(data);
				}
			}
		} else if (parser.getMessageType().compareTo(Parser.JSON) == 0) {
			JSONObject jsonObject = parser.getJSON().getJSONObject("message");
			JSONArray jsonList = jsonObject.getJSONArray("data");
			for (Iterator i = jsonList.iterator(); i.hasNext();) {
				JSONObject json = (JSONObject) i.next();
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
