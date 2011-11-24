package mobile.core.processor;

public enum ProcessorTypes {
	MNT("MAINTENANCE"), QRY("QUERY");

	String parameter;

	ProcessorTypes(String parameter) {
		this.parameter = parameter;
	}

	public String getParameter() {
		return parameter;
	}
}
