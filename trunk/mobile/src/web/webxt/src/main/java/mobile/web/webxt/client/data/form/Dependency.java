package mobile.web.webxt.client.data.form;

/**
 * Represents dependencies between references
 */
public class Dependency {

	private String field = "";
	private String comparator = "=";
	private String fromAlias = "";
	private String fromField = "";
	private String immediateValue = "";

	public Dependency() {
	}

	public Dependency(String field, String fromAlias, String fromField) {
		this.field = field;
		this.fromAlias = fromAlias;
		this.fromField = fromField;
	}

	public Dependency(String field, String immediateValue) {
		this.field = field;
		this.immediateValue = immediateValue;
	}

	public String getField() {
		return field;
	}

	public void setField(String field) {
		this.field = field;
	}

	public String getComparator() {
		return comparator;
	}

	public void setComparator(String comparator) {
		this.comparator = comparator;
	}

	public String getFromAlias() {
		return fromAlias;
	}

	public void setFromAlias(String fromAlias) {
		this.fromAlias = fromAlias;
	}

	public String getFromField() {
		return fromField;
	}

	public void setFromField(String fromField) {
		this.fromField = fromField;
	}

	public String getImmediateValue() {
		return immediateValue;
	}

	public void setImmediateValue(String immediateValue) {
		this.immediateValue = immediateValue;
	}

}
