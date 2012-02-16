package mobile.web.webxt.client.devform;

import mobile.common.message.Item;
import mobile.web.webxt.client.data.form.DataSource;
import mobile.web.webxt.client.data.form.DataSourceType;
import mobile.web.webxt.client.data.form.Reference;
import mobile.web.webxt.client.form.MyFormPanel;
import mobile.web.webxt.client.form.MyGeneralForm;
import mobile.web.webxt.client.form.validations.Validate;
import mobile.web.webxt.client.form.validations.ValidateIdentification;
import mobile.web.webxt.client.form.validations.ValidateIdentification.IdType;
import mobile.web.webxt.client.form.widgets.ComboForm;
import mobile.web.webxt.client.form.widgets.InputBox;
import mobile.web.webxt.client.form.widgets.MyDateField;
import mobile.web.webxt.client.form.widgets.MyLabel;
import mobile.web.webxt.client.form.widgets.RowContainer;
import mobile.web.webxt.client.form.widgetsgrid.ArrayColumnData;
import mobile.web.webxt.client.form.widgetsgrid.MyColumnData;
import mobile.web.webxt.client.util.DatesManager;
import mobile.web.webxt.client.windows.AlertDialog;

import com.extjs.gxt.ui.client.Style.HorizontalAlignment;
import com.extjs.gxt.ui.client.Style.Orientation;
import com.extjs.gxt.ui.client.data.ModelData;
import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.Events;
import com.extjs.gxt.ui.client.event.FieldEvent;
import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.event.SelectionChangedEvent;
import com.extjs.gxt.ui.client.event.SelectionChangedListener;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.util.Margins;
import com.extjs.gxt.ui.client.widget.LayoutContainer;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.form.FieldSet;
import com.extjs.gxt.ui.client.widget.layout.FlowLayout;
import com.extjs.gxt.ui.client.widget.layout.RowData;
import com.extjs.gxt.ui.client.widget.layout.RowLayout;
import com.google.gwt.user.client.Element;

public class B101 extends MyGeneralForm {

	private final static String PROCESS = "C101";
	private final static String ENTITY = "Person";

	// Constants
	final int FORM_WIDTH = 690;
	final int FORM_HEIGHT = 260;
	final int LABEL_WIDTH = 115;
	final int LABEL_WIDTH_2 = 90;
	final int FIELD_WIDTH = 200;
	final int FIELD_WIDTH_2 = 150;

	IdType type = null;
	boolean isValidId = false;

	public B101() {
		super(PROCESS, true);
		setReference(new Reference("per", ENTITY));
	}

	@Override
	protected void onRender(Element parent, int index) {
		super.onRender(parent, index);

		// Form panel
		final MyFormPanel form = new MyFormPanel(this, "Personas Naturales", FORM_WIDTH);
		form.setLayout(new FlowLayout());

		// Header
		// Person id
		RowContainer row = new RowContainer();
		MyLabel label = new MyLabel("Persona:", LABEL_WIDTH);
		row.add(label);

		final ComboForm personIdCombo = new ComboForm(100);
		personIdCombo.setDataSource(new DataSource("per", "pk_personId", DataSourceType.CRITERION));

		Reference refPerson = new Reference("per1", "Person");
		final ArrayColumnData perCdata = new ArrayColumnData();
		perCdata.add(new MyColumnData("per1", "pk_personId", "Id", 100));
		personIdCombo.setQueryData(refPerson, perCdata);
		personIdCombo.setDisplayField("pk_personId");

		personIdCombo.addSelectionChangedListener(new SelectionChangedListener<ModelData>() {
			@Override
			public void selectionChanged(SelectionChangedEvent<ModelData> se) {
				if (((ComboForm) se.getSource()).isSomeSelected()) {
					form.queryForm();
				}
			}

		});
		row.add(personIdCombo);
		form.add(row);

		// GeneratedId
		final InputBox generatedId = new InputBox();
		generatedId.setDataSource(new DataSource(Item.GENERATED_ID, DataSourceType.CONTROL));
		generatedId.setVisible(false);
		generatedId.setFireChangeEventOnSetValue(true);
		generatedId.addListener(Events.Change, new Listener<FieldEvent>() {
			public void handleEvent(FieldEvent e) {
				if (e.getValue() != null) {
					personIdCombo.setRawValue((String) e.getValue());
					personIdCombo.setLoaded(false);
				}
			}
		});
		form.add(generatedId);

		// Container
		LayoutContainer panelPrincipal = new LayoutContainer();
		panelPrincipal.setSize(FORM_WIDTH, FORM_HEIGHT);
		panelPrincipal.setLayout(new RowLayout(Orientation.HORIZONTAL));

		LayoutContainer left = new LayoutContainer();

		LayoutContainer right = new LayoutContainer();

		FieldSet fieldSet = new FieldSet();
		fieldSet.setHeading("Datos Personales");
		fieldSet.setHeight(244);

		row = new RowContainer();

		// Names
		label = new MyLabel("Nombres:", LABEL_WIDTH);
		row.add(label);

		InputBox name = new InputBox(FIELD_WIDTH, 40, Validate.TEXT);
		name.setDataSource(new DataSource("per", "name", DataSourceType.RECORD));
		name.setAllowBlank(false);
		row.add(name);

		fieldSet.add(row);

		row = new RowContainer();

		// Last Name
		label = new MyLabel("Apellido Paterno:", LABEL_WIDTH);
		row.add(label);

		InputBox lastName = new InputBox(FIELD_WIDTH, 40, Validate.TEXT);
		lastName.setDataSource(new DataSource("per", "lastName", DataSourceType.RECORD));
		lastName.createValidator(Validate.REQUIRED);
		row.add(lastName);

		fieldSet.add(row);

		row = new RowContainer();

		// Second Last Name
		label = new MyLabel("Apellido Materno:", LABEL_WIDTH);
		row.add(label);

		InputBox lastName2 = new InputBox(FIELD_WIDTH, 40, Validate.TEXT);
		lastName2.setDataSource(new DataSource("per", "secondLastName", DataSourceType.RECORD));
		row.add(lastName2);

		fieldSet.add(row);

		row = new RowContainer();

		// Date of Birth:
		label = new MyLabel("Fecha Nacimiento:", LABEL_WIDTH);
		row.add(label);

		final MyDateField birthDate = new MyDateField();
		birthDate.setDataSource(new DataSource("per", "dateOfBirth", DataSourceType.RECORD));
		birthDate.setWidth(FIELD_WIDTH);
		birthDate.setAllowBlank(false);
		birthDate.setMaxValue(DatesManager.getCurrentDate());
		birthDate.setMaxLength(10);

		row.add(birthDate);

		fieldSet.add(row);

		// Gender:
		row = new RowContainer();
		label = new MyLabel("Género:", LABEL_WIDTH);
		row.add(label);

		// Gender combo
		final ComboForm genderCombo = new ComboForm(50);
		genderCombo.setDataSource(new DataSource("per", "genderTypeId", DataSourceType.RECORD));

		Reference refProduct = new Reference("gen", "GenderType");
		final ArrayColumnData pcdata = new ArrayColumnData();
		pcdata.add(new MyColumnData("gen", "pk_genderTypeId", "Id", 70));
		pcdata.add(new MyColumnData("gen", "name", "Nombre", 150));
		genderCombo.setQueryData(refProduct, pcdata);
		genderCombo.setDisplayField("pk_genderTypeId");
		row.add(genderCombo);

		final InputBox descGender = new InputBox(FIELD_WIDTH - 60);
		descGender.setReadOnly(true);
		descGender.setDataSource(new DataSource("GenderType", "name", DataSourceType.DESCRIPTION));
		row.add(descGender);

		genderCombo.linkWithField(descGender, "name");

		fieldSet.add(row);

		// Civil Status:
		row = new RowContainer();
		label = new MyLabel("Estado Civil:", LABEL_WIDTH);
		row.add(label);

		// Civil Status combo
		final ComboForm civilStatusCombo = new ComboForm(50);
		civilStatusCombo.setDataSource(new DataSource("per", "civilStatusId", DataSourceType.RECORD));

		Reference refCivilStatus = new Reference("civ", "CivilStatus");
		final ArrayColumnData csdata = new ArrayColumnData();
		csdata.add(new MyColumnData("civ", "pk_civilStatusId", "Id", 70));
		csdata.add(new MyColumnData("civ", "name", "Nombre", 150));
		civilStatusCombo.setQueryData(refCivilStatus, csdata);
		civilStatusCombo.setDisplayField("pk_civilStatusId");
		row.add(civilStatusCombo);

		final InputBox descCivilStatus = new InputBox(FIELD_WIDTH - 60);
		descCivilStatus.setReadOnly(true);
		descCivilStatus.setDataSource(new DataSource("CivilStatus", "name", DataSourceType.DESCRIPTION));
		row.add(descCivilStatus);

		civilStatusCombo.linkWithField(descCivilStatus, "name");

		fieldSet.add(row);

		// Profession:
		row = new RowContainer();
		label = new MyLabel("Profesión:", LABEL_WIDTH);
		row.add(label);

		// Profession combo
		final ComboForm profesionCombo = new ComboForm(50);
		profesionCombo.setDataSource(new DataSource("per", "professionTypeId", DataSourceType.RECORD));

		Reference refProfession = new Reference("pro", "ProfessionType");
		final ArrayColumnData prdata = new ArrayColumnData();
		prdata.add(new MyColumnData("pro", "pk_professionTypeId", "Id", 70));
		prdata.add(new MyColumnData("pro", "name", "Nombre", 150));
		profesionCombo.setQueryData(refProfession, prdata);
		profesionCombo.setDisplayField("pk_professionTypeId");
		profesionCombo.setPageSize(10);
		row.add(profesionCombo);

		final InputBox descProfession = new InputBox(FIELD_WIDTH - 60);
		descProfession.setReadOnly(true);
		descProfession.setDataSource(new DataSource("ProfessionType", "name", DataSourceType.DESCRIPTION));
		descProfession.setToolTipValue(true);
		row.add(descProfession);

		profesionCombo.linkWithField(descProfession, "name");

		fieldSet.add(row);

		left.add(fieldSet);

		// ID Type:
		FieldSet fieldSet2 = new FieldSet();
		fieldSet2.setHeading("Identificación");
		//fieldSet2.setWidth(350);
		//fieldSet2.setWidth(LABEL_WIDTH_2 + FIELD_WIDTH_2 + 40);

		row = new RowContainer();
		label = new MyLabel("Tipo ID:", LABEL_WIDTH_2);
		row.add(label);

		// Identification Combo:
		final ComboForm idTypeCombo = new ComboForm(50);
		idTypeCombo.setDataSource(new DataSource("per", "identificationTypeId", DataSourceType.RECORD));

		Reference refIdentification = new Reference("idt", "IdentificationType");
		final ArrayColumnData iddata = new ArrayColumnData();
		iddata.add(new MyColumnData("idt", "pk_identificationTypeId", "Id", 70));
		iddata.add(new MyColumnData("idt", "name", "Nombre", 150));
		idTypeCombo.setQueryData(refIdentification, iddata);
		idTypeCombo.setDisplayField("pk_identificationTypeId");
		row.add(idTypeCombo);

		final InputBox descTipoId = new InputBox(FIELD_WIDTH_2 - 60);
		descTipoId.setDataSource(new DataSource("IdentificationType", "name", DataSourceType.DESCRIPTION));
		descTipoId.setReadOnly(true);
		descTipoId.setToolTipValue(true);
		row.add(descTipoId);

		idTypeCombo.linkWithField(descTipoId, "name");

		fieldSet2.add(row);

		// Identification
		row = new RowContainer();
		label = new MyLabel("Identificación:", LABEL_WIDTH_2);
		row.add(label);

		final InputBox identification = new InputBox(FIELD_WIDTH_2, 40, Validate.ALPHANUMERIC);
		identification.setDataSource(new DataSource("per", "identificationNumber", DataSourceType.RECORD));
		identification.setAllowBlank(false);
		row.add(identification);

		identification.addListener(Events.OnBlur, new Listener<FieldEvent>() {
			public void handleEvent(FieldEvent fe) {

				if (type == IdType.CED || type == IdType.RUC) {
					isValidId = new ValidateIdentification().isValid(identification.getValue(), type);
				}else{
					isValidId=true;
				}				
				
				if (!isValidId) {
					identification.isValid(false);
					identification.markInvalid("Identificacion Incorrecta");
				} else {
					identification.isValid(true);
				}

			}
		});

		idTypeCombo.addSelectionChangedListener(new SelectionChangedListener<ModelData>() {
			@Override
			public void selectionChanged(SelectionChangedEvent<ModelData> se) {

				String valorId = idTypeCombo.getValue().get("pk_identificationTypeId").toString();

				if (valorId.compareTo("CED") == 0) {
					type = IdType.CED;
				} else if (valorId.compareTo("RUC") == 0) {
					type = IdType.RUC;
				} else {
					type = null;
				}
			}
		});

		fieldSet2.add(row);

		right.add(fieldSet2);
		right.add(new Location(LABEL_WIDTH_2, 50, FIELD_WIDTH_2-60));

		panelPrincipal.add(left, new RowData(LABEL_WIDTH + FIELD_WIDTH + 60, 1, new Margins(5,10,0,0)));
		panelPrincipal.add(right, new RowData(LABEL_WIDTH_2 + FIELD_WIDTH_2 + 50, 1, new Margins(5,0,0,0)));

		form.add(panelPrincipal);

		form.setButtonAlign(HorizontalAlignment.CENTER);
		form.addButton(new Button("Guardar", new SelectionListener<ButtonEvent>() {

			@Override
			public void componentSelected(ButtonEvent ce) {

				if (!isValidId) {
					new AlertDialog("Error", "Identificacion incorrecta").show();
					identification.forceInvalid("Identificacion Incorrecta");
				}

				form.commitForm();
			}
		}));

		add(form);
	}
}