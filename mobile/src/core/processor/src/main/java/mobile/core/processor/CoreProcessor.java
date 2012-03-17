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

import mobile.common.message.Message;
import mobile.common.tools.ProcessType;
import mobile.core.security.RoleValidator;
import mobile.core.security.SessionValidator;
import mobile.entity.manager.JpManager;
import mobile.entity.security.ProcessComponent;
import mobile.tools.common.Config;
import mobile.tools.common.Log;
import mobile.tools.common.Objection;
import mobile.tools.common.convertion.FormatDates;
import mobile.tools.common.enums.ObjectionCode;
import mobile.tools.common.param.LocalParameter;
import mobile.tools.common.param.ParameterEnum;
import mobile.tools.common.structure.GeneralProcessor;

import org.apache.log4j.Logger;
import org.eclipse.persistence.config.HintValues;
import org.eclipse.persistence.config.QueryHints;
import org.eclipse.persistence.exceptions.DatabaseException;

public class CoreProcessor {

	private final Logger log = Log.getInstance();

	private final String QRY_PROCESSES = "Select p from ProcessComponent p " + "where p.pk.companyId = :companyId "
			+ "and p.pk.subsystemId = :subsystemId " + "and p.pk.moduleId = :moduleId "
			+ "and p.pk.processId = :processId " + "and p.typeId = :processType " + "and p.enable = true "
			+ "order by p.pk.processSequence";

	public Message process(Message msg) {
		log.info("Input message: \n" + formatXml(msg.toXML(), 2));

		try {
			// Create entity manager
			JpManager.createEntityManager();
			// Begin transaction
			JpManager.beginTransaction();
			// Security verifications
			executeValidations(msg);
			// Execute associated processes
			executeProcesses(msg);
			// Commit
			JpManager.commitTransaction();
			// Set response
			setResponse(msg, null);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			// Rollback
			JpManager.rollbackTransaction();
			// Set error response
			setResponse(msg, e);
		} finally {
			JpManager.close();
		}

		log.info("Output message: \n" + formatXml(msg.toXML(), 2));

		return msg;
	}

	private void executeValidations(Message msg) throws Exception {
		if (Config.is("core-validate-session")) {
			SessionValidator sessionValidator = new SessionValidator();
			sessionValidator.execute(msg);
		}
		if (Config.is("core-validate-role")) {
			RoleValidator roleValidator = new RoleValidator();
			roleValidator.execute(msg);
		}
	}

	private void executeProcesses(Message msg) throws Exception {
		String strProcess = msg.getRequest().getProcess();
		String strProcessType = msg.getRequest().getProcessType();

		log.info("Process: " + strProcess + " (" + strProcessType + ")");
		String subsystem = strProcess.substring(0, 1);
		String module = strProcess.substring(1, 2);
		String process = strProcess.substring(2);

		TypedQuery<ProcessComponent> query = JpManager.getEntityManager().createQuery(QRY_PROCESSES,
				ProcessComponent.class);
		query.setHint(QueryHints.READ_ONLY, HintValues.TRUE);
		query.setParameter("companyId", LocalParameter.get(ParameterEnum.COMPANY, String.class));
		query.setParameter("subsystemId", subsystem);
		query.setParameter("moduleId", module);
		query.setParameter("processId", process);
		query.setParameter("processType", strProcessType);

		List<ProcessComponent> lProcesses = query.getResultList();

		if (lProcesses.size() == 0) {
			if (ProcessType.QUERY.getShortName().compareTo(strProcessType) == 0) {
				new GeneralQuery().process(msg);
			} else if (ProcessType.MAINTENANCE.getShortName().compareTo(strProcessType) == 0) {
				new GeneralMaintenance().process(msg);
			}
		}

		for (ProcessComponent processComponent : lProcesses) {
			log.info("Processing: " + processComponent.getComponentId());

			GeneralProcessor proc;
			try {
				proc = (GeneralProcessor) Class.forName(processComponent.getComponentId()).newInstance();
			} catch (ClassNotFoundException e) {
				throw new Objection(ObjectionCode.NO_CLASS, processComponent.getComponentId());
			}

			long init = System.currentTimeMillis();
			proc.process(msg);
			log.info("Processed " + processComponent.getComponentId() + ". Processing time: "
					+ FormatDates.getMinuteFormat().format(new Date(System.currentTimeMillis() - init)));

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
			throw new RuntimeException(e);
		}
	}

	public Message setResponse(Message msg, Throwable throwable) {
		if (throwable == null) {
			msg.getResponse().setCode(ObjectionCode.SUCCESS.getCode());
			msg.getResponse().setMessage(ObjectionCode.SUCCESS.getMessage());
		} else {
			Objection objection = null;
			if (throwable instanceof Objection) {
				objection = (Objection) throwable;
			}
			if (throwable.getCause() instanceof Objection) {
				objection = (Objection) throwable.getCause();
			}
			if (objection != null) {
				msg.getResponse().setCode(objection.getCode());
				msg.getResponse().setMessage(objection.getMessage());
				msg.getResponse().setError(getStackTrace(throwable));
			} else {
				String message = "";
				if (throwable instanceof NullPointerException) {
					message = "ENVIO DE VALORES NULOS";
				} else if (throwable instanceof PersistenceException) {
					DatabaseException dbe = (DatabaseException) throwable.getCause();
					message = "CÓDIGO: " + dbe.getDatabaseErrorCode() + "<br/>";
					message = message + dbe.getMessage();
					message = replaceWrongCharacters(message);
				} else if (throwable.getMessage() != null) {
					message = throwable.getMessage();
					message = replaceWrongCharacters(message);
				} else if (throwable.getCause() != null) {
					message = throwable.getCause().toString();
				} else {
					message = ObjectionCode.FAILED.getMessage();
				}

				msg.getResponse().setCode(ObjectionCode.FAILED.getCode());
				msg.getResponse().setMessage(message);
				msg.getResponse().setError(getStackTrace(throwable));
			}
		}
		return msg;
	}

	// private String getStackTrace(Throwable throwable) {
	// Writer writer = new StringWriter();
	// PrintWriter printWriter = new PrintWriter(writer);
	// throwable.printStackTrace(printWriter);
	// return "<![CDATA[" + writer.toString() + "]]>";
	// }

	private String getStackTrace(Throwable throwable) {
		if (throwable != null) {
			try {
				StringWriter sw = new StringWriter();
				PrintWriter pw = new PrintWriter(sw);
				throwable.printStackTrace(pw);
				return sw.toString();
			} catch (Exception ex) {
				return "COULD NOT SHOW THE STACKTRACE IN STRING \n" + ex.getMessage();
			}
		}
		return "";
	}

	private String replaceWrongCharacters(String message) {
		String out = message.replaceAll("\t", "  ");
		out = out.replaceAll("(\r|\n)", "<br/>");
		out = out.replaceAll("\"", "'");
		return out;
	}
}