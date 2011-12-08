package mobile.entity.manager.util;

//System parameters
public enum ParameterEnum {
	COMPANY("COMPANY"), LANGUAGE("LANGUAGE");

	String parameter;

	ParameterEnum(String parameter) {
		this.parameter = parameter;
	}

	public String getParameter() {
		return parameter;
	}
}