package mobile.web.webxt.client.form.validations;

public enum ValidationTypes {
	ALPHABET("^[a-zA-Z_]+$", "TEXTO"), ALPHANUMERIC("^[a-zA-Z0-9_]+$",
			"ALFANUMERICO"), NUMERIC("^[+0-9]+$", "NUMERICO"), EMAIL(
			"^(\\w+)([-+.][\\w]+)*@(\\w[-\\w]*\\.){1,5}([A-Za-z]){2,4}$",
			"EMAIL");
	String regex;
	String name;

	ValidationTypes(String regex, String name) {
		this.regex = regex;
		this.name = name;
	}
}
