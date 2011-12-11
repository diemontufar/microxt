package mobile.tools.common.param;

import java.util.HashMap;
import java.util.Map;

public class LocalParameter {
	private static LocalParameter INSTANCE = null;
	private Map<ParameterEnum, Object> parameter;

	// Private constructor suppresses
	private LocalParameter() {
		parameter = new HashMap<ParameterEnum, Object>();
	}

	// creador sincronizado para protegerse de posibles problemas multi-hilo
	// otra prueba para evitar instanciación múltiple
	private synchronized static void createInstance() {
		if (INSTANCE == null) {
			INSTANCE = new LocalParameter();
		}
	}

	public static LocalParameter getInstance() {
		if (INSTANCE == null)
			createInstance();
		return INSTANCE;
	}

	@SuppressWarnings("unchecked")
	public static <T> T get(ParameterEnum key, Class<T> resultClass) {
		return (T) getInstance().parameter.get(key);
	}

	public static void set(ParameterEnum key, Object value) {
		getInstance().parameter.put(key, value);
	}
}