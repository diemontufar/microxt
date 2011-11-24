package mobile.core.message;

import java.io.StringReader;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import net.sf.json.JSONObject;
import net.sf.json.JSONSerializer;

import org.w3c.dom.Document;
import org.xml.sax.InputSource;

public class Parser {

	private String messageType;

	private Document document;

	private JSONObject json;

	public final static String XML = "XML";

	public final static String JSON = "JSON";

	public Parser(String data, String type) throws Exception {
		messageType = type;
		if (type.compareTo(XML) == 0) {
			DocumentBuilder documentBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
			document = documentBuilder.parse(new InputSource(new StringReader(data)));
		}
		if (type.compareTo(JSON) == 0) {
			json = (JSONObject) JSONSerializer.toJSON(data);
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
