package mobile.core.simulator;

import java.io.PrintWriter;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.Date;

import javax.xml.transform.OutputKeys;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

import mobile.common.message.Message;
import mobile.common.message.ResponseData;
import mobile.core.simulator.micro.Instrumentation;
import mobile.tools.common.Log;
import mobile.tools.common.convertion.FormatDates;
import mobile.tools.common.enums.ObjectionCode;

import org.apache.log4j.Logger;


public class SimulatorProcessor {

	private final Logger log = Log.getInstance();

	public Message process(Message msg) {
		log.info("Input message: \n" + formatXml(msg.toXML(), 2));

		try {
			// Execute processes
			executeProcesses(msg);
			setOkResponseInfo(msg);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			// Set error response
			setErrorResponseInfo(e, msg);
		}

		log.info("Output message: \n" + formatXml(msg.toXML(), 2));

		return msg;
	}

	private void executeProcesses(Message msg) throws Exception {
		long init = System.currentTimeMillis();
		log.info("Processing instrumentation: ");

		Instrumentation proc = new Instrumentation();
		proc.process(msg);

		log.info("Instrumentation finished. Processing time: "
				+ FormatDates.getMinuteFormat().format(new Date(System.currentTimeMillis() - init)));

	}

	private String stackToString(Exception e) {
		if (e != null) {
			try {
				StringWriter sw = new StringWriter();
				PrintWriter pw = new PrintWriter(sw);
				e.printStackTrace(pw);
				return sw.toString();
			} catch (Exception ex) {
				return "Could not show the stackTrace in String\n" + ex.getMessage();
			}
		}
		return "";
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
			throw new RuntimeException(e);
		}
	}

	private void setOkResponseInfo(Message msg) {
		msg.getResponse().setCode(ResponseData.RESPONSE_CODE_OK);
		msg.getResponse().setMessage("OK");
	}

	private void setErrorResponseInfo(Exception e, Message msg) {
		msg.getResponse().setCode(ObjectionCode.FAILED.getCode());

		if (e instanceof NullPointerException) {
			msg.getResponse().setMessage("ENVIO DE VALORES NULOS");
		} else if (e.getMessage() != null) {
			String errorMessage = e.getMessage();
			errorMessage = replaceWrongCharacters(errorMessage);
			msg.getResponse().setMessage(errorMessage);
		} else if (e.getCause() != null) {
			msg.getResponse().setMessage(e.getCause().toString());
		} else {
			msg.getResponse().setMessage(stackToString(e));
		}
	}

	private String replaceWrongCharacters(String message) {
		String out = message.replaceAll("\t", "  ");
		out = out.replaceAll("(\r|\n)", "<br/>");
		out = out.replaceAll("\"", "'");
		return out;
	}
}