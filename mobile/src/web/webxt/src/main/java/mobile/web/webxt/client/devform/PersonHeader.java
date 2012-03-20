package mobile.web.webxt.client.devform;

import mobile.web.webxt.client.data.form.DataSource;
import mobile.web.webxt.client.data.form.DataSourceType;
import mobile.web.webxt.client.data.form.Reference;
import mobile.web.webxt.client.form.widgets.ComboForm;
import mobile.web.webxt.client.form.widgets.InputBox;
import mobile.web.webxt.client.form.widgets.MyLabel;
import mobile.web.webxt.client.form.widgets.RowContainer;
import mobile.web.webxt.client.form.widgetsgrid.ArrayColumnData;
import mobile.web.webxt.client.form.widgetsgrid.MyColumnData;

import com.extjs.gxt.ui.client.data.ModelData;
import com.extjs.gxt.ui.client.event.SelectionChangedEvent;
import com.extjs.gxt.ui.client.event.SelectionChangedListener;
import com.extjs.gxt.ui.client.util.Margins;
import com.extjs.gxt.ui.client.widget.layout.HBoxLayoutData;
import com.google.gwt.user.client.Element;

public class PersonHeader extends RowContainer {
	private String alias;
	private String idField;
	private DataSourceType dsType;

	private ComboForm personCombo;

	final int LABEL_WIDTH = 80;
	final int COMBO_PAGE_SIZE = 10;

	public PersonHeader(String alias, String idField, DataSourceType type) {
		super();
		this.alias = alias;
		this.idField = idField;
		this.dsType = type;

		personCombo = new ComboForm(70);
	}

	@Override
	protected void onRender(Element parent, int pos) {
		super.onRender(parent, pos);
		setStyleAttribute("margin-bottom", "10px");

		// Person id
		MyLabel label = new MyLabel("Persona:", LABEL_WIDTH);
		add(label, new HBoxLayoutData(new Margins(0, 0, 0, 0)));

		personCombo.setDataSource(new DataSource(alias, idField, dsType));
		personCombo.setEditable(true);
		Reference refPerson = new Reference("per1", "Person");
		final ArrayColumnData cdataPerson = new ArrayColumnData();
		cdataPerson.add(new MyColumnData("per1", "pk_personId", "Id", 50));
		cdataPerson.add(new MyColumnData("per1", "identificationNumber", "Identificación", 80));
		cdataPerson.add(new MyColumnData("per1", "name", "Nombre", 100));
		cdataPerson.add(new MyColumnData("per1", "lastName", "Apellido", 100));
		personCombo.setQueryData(refPerson, cdataPerson);
		personCombo.setDisplayField("pk_personId");
		personCombo.setData("mobile-type", Long.class);
		personCombo.setPageSize(COMBO_PAGE_SIZE);
		personCombo.setFilteredField("identificationNumber");
		add(personCombo, new HBoxLayoutData(new Margins(0, 0, 0, 0)));

		// Identification
		InputBox identification = new InputBox(80);
		identification.setReadOnly(true);
		add(identification, new HBoxLayoutData(0, 0, 0, 10));

		// Name
		final InputBox completeName = new InputBox(200);
		completeName.setReadOnly(true);
		add(completeName, new HBoxLayoutData(0, 0, 0, 10));

		final InputBox name = new InputBox();
		name.setVisible(false);
		add(name);
		final InputBox lastName = new InputBox();
		lastName.setVisible(false);
		add(lastName);

		personCombo.linkWithField(identification, "identificationNumber");
		personCombo.linkWithField(name, "name");
		personCombo.linkWithField(lastName, "lastName");

		// Events
		personCombo.addSelectionChangedListener(new SelectionChangedListener<ModelData>() {
			@Override
			public void selectionChanged(SelectionChangedEvent<ModelData> se) {
				completeName.setValue(lastName.getValue() + " " + name.getValue());
			}
		});

	}

	public ComboForm getPersonCombo() {
		return personCombo;
	}
}
