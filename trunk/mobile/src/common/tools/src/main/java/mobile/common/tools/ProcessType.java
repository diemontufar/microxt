package mobile.common.tools;

public enum ProcessType {
	MAINTENANCE("MNT", "MAINTENANCE"), QUERY("QRY", "QUERY");

	String shortName;
	String name;

	ProcessType(String shortName, String name) {
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
