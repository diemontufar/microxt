package mobile.core.processor;

import java.io.PrintWriter;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.Date;
import java.util.List;

import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

import mobile.core.structure.processor.GeneralProcessor;
import mobile.entity.manager.JPManager;
import mobile.entity.security.ProcessComponent;
import mobile.message.message.Message;
import mobile.message.message.ResponseData;
import mobile.tools.common.Log;
import mobile.tools.common.convertion.FormatDates;
import mobile.tools.common.param.LocalParameter;
import mobile.tools.common.param.ParameterEnum;

import org.apache.log4j.Logger;
import org.eclipse.persistence.exceptions.DatabaseException;


public class CoreProcessor {

	private final Logger log = Log.getInstance();

	private final String QRY_PROCESSES = "Select p from ProcessComponent p "
			+ "where p.pk.companyId = :companyId "
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
			setOkResponseInfo(msg);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			// Rollback
			JPManager.rollbackTransaction();
			// Set error response
			setErrorResponseInfo(e, msg);
		} finally {
			JPManager.close();
		}

		log.info("Output message: \n"
				+ formatXml(msg.toXML(), 2));

		return msg;
	}

	private void executeProcesses(Message msg) throws Exception {
		String strProcess = msg.getRequest().getProcess();
		
		log.info("Process: " + strProcess);
		String subsystem = strProcess.substring(0, 1);
		String module = strProcess.substring(1, 2);
		String process = strProcess.substring(2);

		TypedQuery<ProcessComponent> query = JPManager.getEntityManager()
				.createQuery(QRY_PROCESSES, ProcessComponent.class);
		query.setParameter("companyId",
				LocalParameter.get(ParameterEnum.COMPANY, String.class));
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

	private String stackToString(Exception e) {
		if (e != null) {
			try {
				StringWriter sw = new StringWriter();
				PrintWriter pw = new PrintWriter(sw);
				e.printStackTrace(pw);
				return sw.toString();
			} catch (Exception ex) {
				return "Could not show the stackTrace in String\n"
						+ ex.getMessage();
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
		msg.getResponse().setCode(ResponseData.RESPONSE_CODE_ERROR);
		
		if(e instanceof NullPointerException){
			msg.getResponse().setMessage("ENVIO DE VALORES NULOS");
		}else if(e instanceof PersistenceException){
			DatabaseException dbe = (DatabaseException) e.getCause();
			String desc = "CÓDIGO: " + dbe.getDatabaseErrorCode() + "<br/>";
			desc = desc +  dbe.getMessage();
			desc = desc.replaceAll("(\t|\r)",">");
			desc = desc.replaceAll("\n","<br/>");
			msg.getResponse().setMessage(desc);
		}else if(e.getMessage() != null){
			String errorMessage = e.getMessage().replaceAll("(\t|\r)",">");
			errorMessage = errorMessage.replaceAll("\n","<br/>");
			msg.getResponse().setMessage(errorMessage);
		}else if (e.getCause() != null){
			msg.getResponse().setMessage(e.getCause().toString());
		}else{
			msg.getResponse().setMessage(stackToString(e));
		}	
	}
}