package mobile.web.webxt.client.form.validations;

public enum ValidationTypes {
	
	ALPHABET("^[a-zA-Z\\wáéíóäëiöúàèììùñÑÁÉÍÓÚ()/\\s_]+$", "TEXT"), 
	ALPHANUMERIC("^[a-zA-Z0-9_]+$","ALPHANUMERIC"), 
	NUMERIC("^[+0-9]+$", "NUMERIC"), 
	EMAIL("^(\\w+)([-+.][\\w]+)*@(\\w[-\\w]*\\.){1,5}([A-Za-z]){2,4}$","EMAIL"),
	DATE("^(((((0[1-9])|" +
			"(1\\d)|(2[0-8]))-((0[1-9])|(1[0-2])))|((31-((0[13578])|(1[02])))|" +
			"((29|30)-((0[1,3-9])|(1[0-2])))))-((20[0-9][0-9]))|" +
			"(29-02-20(([02468][048])|([13579][26]))))$","DATE");
	
	String regex;
	String name;

	ValidationTypes(String regex, String name) {
		this.regex = regex;
		this.name = name;
	}
	
}

