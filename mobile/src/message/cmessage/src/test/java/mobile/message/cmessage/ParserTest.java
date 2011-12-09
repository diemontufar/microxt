package mobile.message.cmessage;

import junit.framework.TestCase;
import mobile.tools.common.Config;
import mobile.tools.common.FileUtil;

public class ParserTest extends TestCase {

	public void testXMLMessage() throws Exception {
		try {
			String data = FileUtil.readFile(Config.getInstance().get("mobile.core.message.parser-test") + "mobile.xml");
			Message xmlMessage = new Message(new Parser(data, Parser.XML));
			FileUtil.writeFile(Config.getInstance().get("mobile.core.message.parser-test") + "out.xml", xmlMessage.toXML());
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public void testJSONMessage() throws Exception {
		try {
			String data = FileUtil.readFile(Config.getInstance().get("mobile.core.message.parser-test") + "mobile.json");
			Message jsonMessage = new Message(new Parser(data, Parser.JSON));
			FileUtil.writeFile(Config.getInstance().get("mobile.core.message.parser-test") + "out.json", jsonMessage.toJSON());
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public void testMessage() throws Exception {
		try {
			Message message = new Message();
			Data data = new Data("login");
			data.addField(new Field("user", "jalmeida"));
			data.addField(new Field("pwd", "juan2012"));
			message.addData(data);
			FileUtil.writeFile(Config.getInstance().get("mobile.core.message.parser-test") + "out.txt", message.toXML());
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

}
