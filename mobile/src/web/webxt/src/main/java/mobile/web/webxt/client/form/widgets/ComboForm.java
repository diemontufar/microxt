package mobile.web.webxt.client.form.widgets;


public class ComboForm extends MyComboBox implements PersistentField {

	public ComboForm(int width) {
		super();
		this.setWidth(width);
	}

	public ComboForm(String label) {
		super();
		this.setFieldLabel(label);
	}

	public ComboForm(int width, String displayField) {
		this(width);
		setDisplayField(displayField);
	}

	public ComboForm(String label, String displayField) {
		this(label);
		setDisplayField(displayField);
	}

}
