package mobile.core.processor;

import java.io.StringReader;
import java.io.StringWriter;
import java.util.Date;
import java.util.List;

import javax.persistence.TypedQuery;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

import mobile.core.common.Log;
import mobile.core.message.Data;
import mobile.core.message.Field;
import mobile.core.message.Message;
import mobile.core.structure.processor.GeneralProcessor;
import mobile.entity.manager.JPManager;
import mobile.entity.manager.util.FormatDates;
import mobile.entity.manager.util.LocalParameter;
import mobile.entity.manager.util.ParameterEnum;
import mobile.entity.security.ProcessComponent;

import org.apache.log4j.Logger;

public class CoreProcessor {

	private final Logger log = Log.getInstance();

	private final String QRY_PROCESSES = "Select p from ProcessComponent p "
			+ "where p.pk.companyId = :companyId "
			+ "and p.pk.channelId = :channelId "
			+ "and p.pk.subsystemId = :subsystemId "
			+ "and p.pk.moduleId = :moduleId "
			+ "and p.pk.processId = :processId " + "and p.enable = true "
			+ "order by p.pk.processSequence";

	public Message process(Message msg) {

		log.info("Input message: \n"
				+ formatXml(msg.toXML(), 2));

		try {
			// Create entity manager
			JPManager.createEntityManager();
			// Begin transaction
			JPManager.beginTransaction();

			// Execute associated processes
			executeProcesses(msg);

			// Commit
			JPManager.commitTransaction();

			// Set response
			Data response = new Data("response");
			response.addField(new Field("code", "000"));
			response.addField(new Field("message", "OK"));
			msg.addData(response);
		} catch (Exception e) {
			e.printStackTrace();

			// Rollback
			JPManager.rollbackTransaction();

			// Set error response
			Data response = new Data("response");
			response.addField(new Field("code", "001"));
			String errorMessage = e.getMessage().replaceAll("(\t|\r)","");
			errorMessage = errorMessage.replaceAll("\n","^NL");
			response.addField(new Field("message", errorMessage));
			//response.addField(new Field("message", stackToString(e)));
			msg.addData(response);
		} finally {
			JPManager.close();
		}

		log.info("Output message: \n"
				+ formatXml(msg.toXML(), 2));
		return msg;
	}

	private void executeProcesses(Message msg) throws Exception {
		String strProcess = msg.getData("header").getField("proc").getValue();
		log.info("Process: " + strProcess);
		String subsystem = strProcess.substring(0, 1);
		String module = strProcess.substring(1, 2);
		String process = strProcess.substring(2);

		TypedQuery<ProcessComponent> query = JPManager.getEntityManager()
				.createQuery(QRY_PROCESSES, ProcessComponent.class);
		query.setParameter("companyId",
				LocalParameter.get(ParameterEnum.COMPANY, String.class));
		query.setParameter("channelId", "PC");
		query.setParameter("subsystemId", subsystem);
		query.setParameter("moduleId", module);
		query.setParameter("processId", process);

		List<ProcessComponent> lProcesses = query.getResultList();
		JPManager.detachList(lProcesses);
		
		if(lProcesses.size()==0){
			new MaintenanceProcessor().process(msg);
			new QueryProcessor().process(msg);
		}
		
		for (ProcessComponent processComponent : lProcesses) {
			log.info("Processing: " + processComponent.getComponentId());
			
			GeneralProcessor proc;
			try {
				proc = (GeneralProcessor) Class.forName(
						processComponent.getComponentId()).newInstance();
			} catch (ClassNotFoundException e) {
				log.error("ProccesComponent not found: "
						+ processComponent.getComponentId());
				throw new Exception("ProcessComponent not found: "
						+ processComponent.getComponentId());
			}

			long init = System.currentTimeMillis();
			proc.process(msg);
			log.info("Processed " 
					+ processComponent.getComponentId() 
					+ ". Processing time: " 
					+ FormatDates
							.getInstance()
							.getTimeCountFormat()
							.format(new Date(System.currentTimeMillis() - init)));

		}
	}

//	private String stackToString(Exception e) {
//		if (e != null) {
//			try {
//				StringWriter sw = new StringWriter();
//				PrintWriter pw = new PrintWriter(sw);
//				e.printStackTrace(pw);
//				return sw.toString();
//			} catch (Exception ex) {
//				return "Could not show the stackTrace in String\n"
//						+ ex.getMessage();
//			}
//		}
//		return "";
//	}
	
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