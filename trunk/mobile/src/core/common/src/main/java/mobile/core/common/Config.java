package mobile.core.common;

import java.util.ResourceBundle;

/**
 * Manage configuration parameters
 */
public class Config {

	public static Config INSTANCE = null;
	private ResourceBundle bundle = null;

	public Config() {
		bundle = ResourceBundle.getBundle("config");
	}

	public static Config getInstance() {
		if (INSTANCE == null) {
			INSTANCE = new Config();
		}
		return INSTANCE;
	}

	public String get(String key) {
		return bundle.getString(key);
	}
}
