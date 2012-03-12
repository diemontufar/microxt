package mobile.common.tools;

public class CommonConverter {

	// Boolean methods
	public static Boolean parseBoolean(Object in) {
		return parseBoolean(in.toString());
	}

	public static Boolean parseBoolean(String input) {
		Boolean result = false;
		if (input != null && input.compareToIgnoreCase("true") == 0 || input.compareTo("1") == 0) {
			result = true;
		}
		return result;
	}

	public static String booleanToString(Boolean input) {
		String result = "0";
		if (input) {
			result = "1";
		}
		return result;
	}
}
