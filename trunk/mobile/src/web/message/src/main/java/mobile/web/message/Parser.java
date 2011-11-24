package mobile.web.message;

import com.google.gwt.json.client.JSONObject;
import com.google.gwt.json.client.JSONParser;
import com.google.gwt.xml.client.Document;
import com.google.gwt.xml.client.XMLParser;

public class Parser {

	private String messageType;

	private Document document;

	private JSONObject json;

	public final static String XML = "XML";

	public final static String JSON = "JSON";

	public Parser(String data, String type) throws Exception {
		messageType = type;
		if (type.compareTo(XML) == 0) {
			document = XMLParser.parse(data);
		}
		if (type.compareTo(JSON) == 0) {
			json = (JSONObject) JSONParser.parseStrict(data).isObject();
		}
	}

	public String getMessageType() {
		return messageType;
	}

	public Document getDocument() {
		return document;
	}

	public JSONObject getJSON() {
		return json;
	}
}
