package mobile.message.message;

public class Field {

	public final static String REQUEST_USER = "user";
	public final static String REQUEST_HOST = "host";
	public final static String REQUEST_COMPANY = "company";
	public final static String REQUEST_LANGUAGE = "language";
	public final static String REQUEST_SESSION = "session";
	public final static String REQUEST_PROCESS = "process";
	public final static String REQUEST_CHANNEL = "channel";
	public final static String REQUEST_PROFILE = "profile";
	public final static String REQUEST_EDITABLE = "editable";

	public final static String RESPONSE_CODE = "code";
	public final static String RESPONSE_MESSAGE = "description";
	public final static String RESPONSE_ERROR = "error";
	public final static String RESPONSE_NOTES = "notes";

	private String name;

	private String value;

	public Field() {
		setName(null);
		setValue(null);
	}

	public Field(String name, String value) {
		setName(name);
		setValue(value);
	}

	public String toXML() {
		String field = "<" + getName();
		if (getValue() != null && getValue().compareTo("") != 0) {
			field += ">" + getValue() + "</" + getName() + ">";
		} else {
			field += "/>";
		}
		return field;
	}

	public String toJSON() {
		String field = "\"" + getName() + "\":";
		if (getValue() != null && getValue().compareTo("") != 0) {
			field += "\"" + getValue() + "\"";
		} else {
			field += "\"\"";
		}
		return field;
	}

	public void setName(String fieldName) {
		name = "noname";
		if (fieldName != null) {
			name = fieldName.trim();
		}
	}

	public String getName() {
		return name;
	}

	public void setValue(String fieldValue) {
		value = null;
		if (fieldValue != null) {
			value = fieldValue.trim();
		}
	}

	public String getValue() {
		return value;
	}
}
