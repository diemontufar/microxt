package mobile.common.tools;

public enum ProcessorTypes {
	MNT("MNT", "MAINTENANCE"), QRY("QRY", "QUERY");

	String shortName;
	String name;

	ProcessorTypes(String shortName, String name) {
		this.shortName = shortName;
		this.name = name;
	}

	public String getShortName() {
		return shortName;
	}
	
	public String getName() {
		return name;
	}

}
