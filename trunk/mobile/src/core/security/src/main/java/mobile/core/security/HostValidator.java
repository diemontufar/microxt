package mobile.core.security;

import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import mobile.common.message.Message;
import mobile.entity.manager.JpManager;
import mobile.entity.security.Host;
import mobile.entity.security.HostPk;
import mobile.tools.common.Objection;
import mobile.tools.common.enums.ObjectionCode;
import mobile.tools.common.param.LocalParameter;
import mobile.tools.common.param.ParameterEnum;
import mobile.tools.common.param.Timer;

public class HostValidator {

	private final String HOST_QL = "SELECT o FROM Host o WHERE o.pk.companyId = :companyId AND o.pk.expired = :expired AND o.address = :address";

	public Message validate(Message msg) throws Exception {
		String hostId = msg.getRequest().getHost();
		
		HostPk hostPk = new HostPk(hostId);
		Host host = JpManager.find(Host.class, hostPk);

		if(host == null){
			throw new Objection(ObjectionCode.HOST_ADDRESS, hostId);
		}
		
		return msg;
	}

	
	public Message setHostId(Message msg) throws Exception {

		String address = msg.getRequest().getAddress();
		try {
			Host host = getHost(address);
			msg.getRequest().setHost(host.getPk().getHostId());
			msg.getRequest().setChannel(host.getChannelId());
		} catch (NoResultException e) {
			throw new Objection(ObjectionCode.HOST_ADDRESS, address);
		}
		return msg;
	}

	private Host getHost(String address) throws Exception {
		TypedQuery<Host> query = JpManager.getEntityManager().createQuery(HOST_QL, Host.class);
		query.setParameter("companyId", LocalParameter.get(ParameterEnum.COMPANY, String.class));
		query.setParameter("expired", Timer.getExpiredTime());
		query.setParameter("address", address);
		return query.getSingleResult();
	}
}
