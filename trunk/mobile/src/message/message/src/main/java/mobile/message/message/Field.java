package mobile.message.message;

public class Field {

	private String name;

	private String value;

	public Field() {
		setName(null);
		setValue(null);
	}
	
	public Field(String name) {
		setName(name);
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
