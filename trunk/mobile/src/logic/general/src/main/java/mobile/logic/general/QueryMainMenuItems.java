package mobile.logic.general;

import java.util.List;

import javax.persistence.Query;
import javax.persistence.TypedQuery;

import mobile.core.structure.processor.GeneralProcessor;
import mobile.entity.manager.JPManager;
import mobile.entity.manager.util.LocalParameter;
import mobile.entity.manager.util.ParameterEnum;
import mobile.entity.manager.util.PersistenceTime;
import mobile.entity.security.Module;
import mobile.entity.security.Subsystem;
import mobile.message.cmessage.Data;
import mobile.message.cmessage.Field;
import mobile.message.cmessage.Item;
import mobile.message.cmessage.Message;

public class QueryMainMenuItems implements GeneralProcessor {

	private final String QRY_SUBSYSTEMS = "Select s from Subsystem s where "
			+ "s.pk.subsystemId in "
			+ "( Select distinct p.pk.subsystemId from Process p where p.enable = 1 "
			+ "and p.menu = 1 "
			+ "and p.pk.companyId = :companyId "
			+ "and p.pk.languageId = :languageId "
			+ "and p.pk.expired = :expired ) "
			+ "and s.pk.companyId = :companyId "
			+ "and s.pk.languageId = :languageId "
			+ "order by s.pk.subsystemId";

	private final String N_QRY_MODULES = "Select m.* from MODULE m where "
			+ "CONCAT(m.SUBSYSTEM_ID,m.MODULE_ID) in "
			+ "( Select distinct CONCAT(p.SUBSYSTEM_ID,p.MODULE_ID) from PROCESS p where p.ENABLE = :enable "
			+ "and p.menu = 1 "
			+ "and p.COMPANY_ID = :companyId "
			+ "and p.LANGUAGE_ID = :languageId "
			+ "and p.EXPIRED = :expired ) " + "and m.COMPANY_ID = :companyId "
			+ "and m.LANGUAGE_ID = :languageId "
			+ "order by m.SUBSYSTEM_ID, m.MODULE_ID";

	private final String QRY_PROCESSES = "Select p from Process p where p.enable = 1 "
			+ "and p.menu = 1 "
			+ "and p.pk.companyId = :companyId "
			+ "and p.pk.languageId = :languageId "
			+ "and p.pk.expired = :expired ";

	@Override
	public Message process(Message msg) throws Exception {
		obtainSubsystems(msg);
		obtainModules(msg);
		obtainProcesses(msg);

		return msg;
	}

	private void obtainSubsystems(Message msg) throws Exception {
		TypedQuery<Subsystem> query = JPManager.getEntityManager().createQuery(
				QRY_SUBSYSTEMS, Subsystem.class);
		query.setParameter("companyId",
				LocalParameter.get(ParameterEnum.COMPANY, String.class));
		query.setParameter("languageId",
				LocalParameter.get(ParameterEnum.LANGUAGE, String.class));
		query.setParameter("expired", PersistenceTime.getExpiredTime());

		List<Subsystem> lSubsystems = query.getResultList();

		Data subsystemData = new Data("Subsystem");
		int counter = 1;
		for (Subsystem subsystem : lSubsystems) {
			Item item = new Item(counter++);
			item.addField(new Field("id", subsystem.getPk().getSubsystemId()));
			item.addField(new Field("name", subsystem.getName()));
			subsystemData.addItem(item);
		}

		msg.addData(subsystemData);
	}

	@SuppressWarnings({ "unchecked" })
	private void obtainModules(Message msg) throws Exception {
		String COMPANY = LocalParameter
				.get(ParameterEnum.COMPANY, String.class);
		String LANGUAGE = LocalParameter.get(ParameterEnum.LANGUAGE,
				String.class);
		String finalQuery = N_QRY_MODULES
				.replaceAll(":enable", "'1'")
				.replaceAll(":companyId", "'" + COMPANY + "'")
				.replaceAll(":languageId", "'" + LANGUAGE + "'")
				.replaceAll(":expired", "'9999-12-31'");

		Query query = JPManager.getEntityManager().createNativeQuery(
				finalQuery, Module.class);

		List<Module> lModules = query.getResultList();

		Data moduleData = new Data("Module");
		int counter = 1;
		for (Module module : lModules) {
			Item item = new Item(counter++);
			item.addField(new Field("id", module.getPk().getSubsystemId()
					+ module.getPk().getModuleId()));
			item.addField(new Field("name", module.getName()));
			moduleData.addItem(item);
		}

		msg.addData(moduleData);
	}

	private void obtainProcesses(Message msg) throws Exception {
		TypedQuery<mobile.entity.security.Process> query = JPManager
				.getEntityManager().createQuery(QRY_PROCESSES,
						mobile.entity.security.Process.class);
		query.setParameter("companyId",
				LocalParameter.get(ParameterEnum.COMPANY, String.class));
		query.setParameter("languageId",
				LocalParameter.get(ParameterEnum.LANGUAGE, String.class));
		query.setParameter("expired", PersistenceTime.getExpiredTime());

		List<mobile.entity.security.Process> lProcesses = query.getResultList();

		Data processData = new Data("Process");
		int counter = 1;
		for (mobile.entity.security.Process process : lProcesses) {
			Item item = new Item(counter++);
			item.addField(new Field("id", process.getPk().getSubsystemId()
					+ process.getPk().getModuleId()
					+ process.getPk().getProcessId()));
			item.addField(new Field("name", process.getName()));
			processData.addItem(item);
		}

		msg.addData(processData);
	}

}
