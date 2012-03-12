package mobile.core.simulator;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import mobile.entity.manager.JPManagerFactory;
import mobile.tools.common.Log;
import mobile.tools.common.param.LocalParameter;
import mobile.tools.common.param.ParameterEnum;

import org.apache.log4j.Logger;

public class PersistenceListener implements ServletContextListener,
		HttpSessionListener {

	ServletContext servletContext;
	
	private final static Logger log = Log.getInstance();

	/* A listener class must have a zero-argument constructor: */
	public PersistenceListener() {
		log.info(this.getClass().getName() + " constructor");
	}

	/* Methods from the ServletContextListener */
	public void contextInitialized(ServletContextEvent sce) {
		log.info("Context initialized");
		servletContext = sce.getServletContext();

		// Load persistence:
		log.info("Initialize persistence");
		JPManagerFactory.createEntityManagerFactory();
		//JPManager.createEntityManager();

		log.info("Initialize global parameters");
		LocalParameter.set(ParameterEnum.COMPANY, "MXT");
		LocalParameter.set(ParameterEnum.LANGUAGE, "ES");
	}

	public void contextDestroyed(ServletContextEvent sce) {
		log.info("Context destroyed");
		log.info("Close persistence");
		//JPManager.close();
		JPManagerFactory.close();
	}

	/* Methods for the HttpSessionListener */
	public void sessionCreated(HttpSessionEvent hse) {
		String _ID = hse.getSession().getId();
		log.info("SessionID:" + _ID + " created");
	}

	public void sessionDestroyed(HttpSessionEvent hse) {
		log.info("SessionID: " + hse.getSession().getId() + " destroyed");
		HttpSession _session = hse.getSession();
		long _start = _session.getCreationTime();
		long _end = _session.getLastAccessedTime();
		String _counter = (String) _session.getAttribute("counter");
		log.info("Session Duration:" + (_end - _start) + "(ms) Counter:"
				+ _counter);
	}
}