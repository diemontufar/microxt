package mobile.core.processor;

import java.io.StringReader;
import java.io.StringWriter;

import javax.xml.transform.OutputKeys;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

import mobile.core.common.FileUtil;
import mobile.core.common.Log;
import mobile.core.message.Message;
import mobile.core.message.Parser;
import mobile.entity.manager.JPManagerFactory;
import mobile.entity.manager.util.LocalParameter;
import mobile.entity.manager.util.ParameterEnum;

import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class XmlProcessorTest{

	private String input  = "/home/ronald/Escritorio/input.xml";
	private String output = "/home/ronald/Escritorio/output.xml";
	
	Logger log = Log.getInstance();

	@Before
	public void setUp() throws Exception {
		JPManagerFactory.createEntityManagerFactory();
		LocalParameter.set(ParameterEnum.COMPANY, "MXT");
		LocalParameter.set(ParameterEnum.LANGUAGE, "ES");
	}

	@After
	public void tearDown() throws Exception {
		JPManagerFactory.close();
	}

	@Test
	public void test() {
		try {
			// Message
			String strMsg = FileUtil.readFile(input);
			Parser parser = new Parser(strMsg, Parser.XML);
			Message msg = new Message(parser);
			
			// Processor
			CoreProcessor proc = new CoreProcessor();

			// Process
			Message outputMsg = proc.process(msg);
			FileUtil.writeFile(output,formatXml(outputMsg.toXML(), 2)); 
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private String formatXml(String input, int indent) {
	    try {
	        Source xmlInput = new StreamSource(new StringReader(input));
	        StringWriter stringWriter = new StringWriter();
	        StreamResult xmlOutput = new StreamResult(stringWriter);
	        TransformerFactory transformerFactory = TransformerFactory.newInstance();
	        transformerFactory.setAttribute("indent-number", indent);
	        Transformer transformer = transformerFactory.newTransformer(); 
	        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
	        transformer.transform(xmlInput, xmlOutput);
	        return xmlOutput.getWriter().toString();
	    } catch (Exception e) {
	        throw new RuntimeException(e); // simple exception handling, please review it
	    }
	}	

}