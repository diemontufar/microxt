package mobile.tools.common;

import java.net.URL;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.apache.log4j.helpers.Loader;

/**
 * Manage Log configuration
 */
public final class Log {

	public static Logger INSTANCE = null;

	public Log() {
		try {
            String properties = Config.getInstance().get("log4j.properties");
            URL url = Loader.getResource(properties);
            PropertyConfigurator.configure(url);
            INSTANCE = Logger.getLogger("mobile");	
		} catch (Exception e) {
            BasicConfigurator.configure();
            INSTANCE = Logger.getLogger("mobile");
		}
		
	}

	public synchronized static Logger getInstance() {
		if (INSTANCE == null) {
			new Log();
		}
		return INSTANCE;
	}
}
