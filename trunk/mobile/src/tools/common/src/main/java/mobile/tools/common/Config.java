package mobile.tools.common;

import java.util.ResourceBundle;

import mobile.tools.common.convertion.CoreConverter;

public class Config {

	private static Config INSTANCE;
	private ResourceBundle bundle;

	private Config() {
		bundle = ResourceBundle.getBundle("config");
	}

	private synchronized static Config getInstance() {
		if (INSTANCE == null) {
			INSTANCE = new Config();
		}
		return INSTANCE;
	}

	public static String get(String key) {
		return getInstance().bundle.getString(key);
	}

	public static boolean is(String key) throws Exception {
		return CoreConverter.convertObject(getInstance().bundle.getString(key), Boolean.class);
	}
}
