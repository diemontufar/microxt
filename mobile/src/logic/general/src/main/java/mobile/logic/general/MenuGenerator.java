package mobile.logic.general;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Query;
import javax.persistence.TypedQuery;

import mobile.common.message.EntityData;
import mobile.common.message.Field;
import mobile.common.message.Item;
import mobile.common.message.Message;
import mobile.entity.manager.JpManager;
import mobile.entity.security.Module;
import mobile.entity.security.Process;
import mobile.entity.security.Subsystem;
import mobile.tools.common.param.LocalParameter;
import mobile.tools.common.param.ParameterEnum;
import mobile.tools.common.param.Timer;
import mobile.tools.common.structure.QueryProcessor;

public class MenuGenerator implements QueryProcessor {

	private final String SUBSYSTEMS_QL = "Select s from Subsystem s where "
			+ "s.pk.companyId = :companyId and s.pk.languageId = :languageId " + "and s.pk.subsystemId in :lsubsystem "
			+ "order by s.pk.subsystemId";

	private final String MODULES_QL = "Select m from Module m where "
			+ "m.pk.companyId = :companyId and m.pk.languageId = :languageId "
			+ "and concat(m.pk.subsystemId,m.pk.moduleId) in :lmodule " + "order by m.pk.subsystemId, m.pk.moduleId";

	private final String PROCESSES_ADMIN_QL = "Select p from Process p where p.enable = 1 " + "and p.menu = 1 "
			+ "and p.pk.companyId = :companyId " + "and p.pk.languageId = :languageId "
			+ "and p.pk.expired = :expired ";

	private final String PROCESSES_QRY = "Select p.* from PROCESS p "
			+ "inner join ROLE r on r.COMPANY_ID=p.COMPANY_ID and r.expired=p.expired and r.SUBSYSTEM_ID = p.SUBSYSTEM_ID and r.MODULE_ID=p.MODULE_ID and r.PROCESS_ID=p.PROCESS_ID "
			+ "where p.enable = 1 and p.menu = 1  "
			+ "and p.company_Id = ?1 and p.language_Id = ?2 and p.expired = ?3 " + "and r.PROFILE_ID=?4 ";

	private List<Process> lprocess;

	@Override
	public Message process(Message msg) throws Exception {
		setLprocess(msg);
		fillSubsystems(msg);
		fillModules(msg);
		fillProcesses(msg);
		return msg;
	}

	@SuppressWarnings("unchecked")
	private void setLprocess(Message msg) {
		String profile = msg.getRequest().getProfile();

		if (profile == null || (profile != null && profile.compareTo("ADM") == 0)) {
			TypedQuery<Process> query = JpManager.getEntityManager().createQuery(PROCESSES_ADMIN_QL, Process.class);
			query.setParameter("companyId", LocalParameter.get(ParameterEnum.COMPANY, String.class));
			query.setParameter("languageId", LocalParameter.get(ParameterEnum.LANGUAGE, String.class));
			query.setParameter("expired", Timer.getExpiredTime());

			lprocess = query.getResultList();
		} else {
			Query query = JpManager.getEntityManager().createNativeQuery(PROCESSES_QRY,
					mobile.entity.security.Process.class);
			query.setParameter(1, LocalParameter.get(ParameterEnum.COMPANY, String.class));
			query.setParameter(2, LocalParameter.get(ParameterEnum.LANGUAGE, String.class));
			query.setParameter(3, Timer.getExpiredTime());
			query.setParameter(4, profile);

			lprocess = query.getResultList();
		}
	}

	private void fillSubsystems(Message msg) throws Exception {
		TypedQuery<Subsystem> query = JpManager.getEntityManager().createQuery(SUBSYSTEMS_QL, Subsystem.class);
		query.setParameter("companyId", LocalParameter.get(ParameterEnum.COMPANY, String.class));
		query.setParameter("languageId", LocalParameter.get(ParameterEnum.LANGUAGE, String.class));
		query.setParameter("lsubsystem", getSubsystem(lprocess));

		List<Subsystem> lSubsystems = query.getResultList();

		EntityData subsystemData = new EntityData("Subsystem");
		int counter = 1;
		for (Subsystem subsystem : lSubsystems) {
			Item item = new Item(counter++);
			item.addField(new Field("id", subsystem.getPk().getSubsystemId()));
			item.addField(new Field("name", subsystem.getName()));
			subsystemData.addItem(item);
		}

		msg.addData(subsystemData);
	}

	private List<String> getSubsystem(List<Process> lprocess) {
		List<String> lsubsystem = new ArrayList<String>();

		for (Process proc : lprocess) {
			if (!lsubsystem.contains(proc.getPk().getSubsystemId())) {
				lsubsystem.add(proc.getPk().getSubsystemId());
			}
		}

		return lsubsystem;
	}

	private void fillModules(Message msg) throws Exception {
		TypedQuery<Module> query = JpManager.getEntityManager().createQuery(MODULES_QL, Module.class);
		query.setParameter("companyId", LocalParameter.get(ParameterEnum.COMPANY, String.class));
		query.setParameter("languageId", LocalParameter.get(ParameterEnum.LANGUAGE, String.class));
		query.setParameter("lmodule", getModule(lprocess));

		List<Module> lModules = query.getResultList();

		EntityData moduleData = new EntityData("Module");
		int counter = 1;
		for (Module module : lModules) {
			Item item = new Item(counter++);
			item.addField(new Field("id", module.getPk().getSubsystemId() + module.getPk().getModuleId()));
			item.addField(new Field("name", module.getName()));
			moduleData.addItem(item);
		}

		msg.addData(moduleData);
	}

	private List<String> getModule(List<Process> lprocess2) {
		List<String> lmodule = new ArrayList<String>();

		for (Process proc : lprocess) {
			if (!lmodule.contains(proc.getPk().getSubsystemId() + proc.getPk().getModuleId())) {
				lmodule.add(proc.getPk().getSubsystemId() + proc.getPk().getModuleId());
			}
		}

		return lmodule;
	}

	private void fillProcesses(Message msg) throws Exception {
		EntityData processData = new EntityData("Process");
		int counter = 1;
		for (Process process : lprocess) {
			Item item = new Item(counter++);
			item.addField(new Field("id", process.getPk().getSubsystemId() + process.getPk().getModuleId()
					+ process.getPk().getProcessId()));
			item.addField(new Field("name", process.getName()));
			processData.addItem(item);
		}

		msg.addData(processData);
	}

}
