package mobile.web.webxt.client.devform;

import java.util.ArrayList;
import java.util.List;

import mobile.common.message.Item;
import mobile.common.tools.Format;
import mobile.web.webxt.client.data.MyListStore;
import mobile.web.webxt.client.data.form.DataSource;
import mobile.web.webxt.client.data.form.DataSourceType;
import mobile.web.webxt.client.data.form.Reference;
import mobile.web.webxt.client.form.EntityContentPanel;
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
import mobile.web.webxt.client.form.widgetsgrid.ComboColumn;
import mobile.web.webxt.client.form.widgetsgrid.EntityEditorGrid;
import mobile.web.webxt.client.form.widgetsgrid.ExpireColumnConfig;
import mobile.web.webxt.client.form.widgetsgrid.GridPagingToolBar;
import mobile.web.webxt.client.form.widgetsgrid.GridToolBar;
import mobile.web.webxt.client.form.widgetsgrid.MyColumnData;
import mobile.web.webxt.client.form.widgetsgrid.NormalColumn;
import mobile.web.webxt.client.util.DatesManager;
import mobile.web.webxt.client.util.NumberType;
import mobile.web.webxt.client.windows.AlertDialog;

import com.extjs.gxt.ui.client.Style.HorizontalAlignment;
import com.extjs.gxt.ui.client.Style.Orientation;
import com.extjs.gxt.ui.client.Style.SortDir;
import com.extjs.gxt.ui.client.data.ModelData;
import com.extjs.gxt.ui.client.event.BaseEvent;
import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.Events;
import com.extjs.gxt.ui.client.event.FieldEvent;
import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.event.SelectionChangedEvent;
import com.extjs.gxt.ui.client.event.SelectionChangedListener;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.util.Margins;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.LayoutContainer;
import com.extjs.gxt.ui.client.widget.TabItem;
import com.extjs.gxt.ui.client.widget.TabPanel;
import com.extjs.gxt.ui.client.widget.VerticalPanel;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.form.FieldSet;
import com.extjs.gxt.ui.client.widget.grid.ColumnConfig;
import com.extjs.gxt.ui.client.widget.grid.ColumnModel;
import com.extjs.gxt.ui.client.widget.grid.filters.GridFilters;
import com.extjs.gxt.ui.client.widget.grid.filters.StringFilter;
import com.extjs.gxt.ui.client.widget.layout.CenterLayout;
import com.extjs.gxt.ui.client.widget.layout.FlowLayout;
import com.extjs.gxt.ui.client.widget.layout.RowData;
import com.extjs.gxt.ui.client.widget.layout.RowLayout;
import com.google.gwt.user.client.Element;

public class B101 extends MyGeneralForm {

	private final static String PROCESS = "C101";
	private final static String ENTITY = "Person";

	// Constants
	final int FORM_WIDTH = 620;
	final int TAB_HEIGHT = 300;
	final int LABEL_WIDTH = 60;

	ComboForm personIdCombo;
	EntityEditorGrid addressGrid;
	MyListStore addressStore;
	EntityEditorGrid phoneGrid;
	MyListStore phoneStore;

	boolean isValidId = false;
	IdType type;

	ContentPanel panelPrincipal = new ContentPanel();

	public B101() {
		super(PROCESS, true);
		setReference(new Reference("per", ENTITY));
	}

	@Override
	protected void onRender(Element parent, int index) {
		super.onRender(parent, index);
		createForm();
	}

	private void createForm() {

		ContentPanel left = new ContentPanel();
		left.setHeaderVisible(false);
		left.setWidth(280);
		left.setHeight(300);
		left.setBorders(false);

		ContentPanel right = new ContentPanel();
		right.setHeaderVisible(false);
		right.setWidth(290);
		right.setHeight(300);
		right.setBorders(false);

		panelPrincipal.setFrame(false);
		panelPrincipal.setHeaderVisible(false);
		panelPrincipal.setSize(FORM_WIDTH, TAB_HEIGHT);
		panelPrincipal.setLayout(new RowLayout(Orientation.HORIZONTAL));

		// Form panel
		final MyFormPanel form = new MyFormPanel(this, "Mantenimiento Personas Naturales", FORM_WIDTH);
		form.setLayout(new FlowLayout());

		// Header
		// Person id
		RowContainer row = new RowContainer();
		MyLabel label = new MyLabel("Persona:", LABEL_WIDTH);
		row.add(label);

		personIdCombo = new ComboForm(100);
		// personIdCombo.setId("personId");
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
		// generatedId.setId("generatedId");
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

		// Tab panel
		final TabPanel tabPanel = new TabPanel();
		tabPanel.setHeight(TAB_HEIGHT);

		// Basic tab
		TabItem basic = new TabItem();
		basic.setStyleAttribute("padding", "10px");
		basic.setText("Datos Generales");
		basic.setLayout(new FlowLayout());
		basic.setBorders(true);

		FieldSet fieldSet = new FieldSet();
		fieldSet.setHeading("Datos Personales");
		fieldSet.setCollapsible(false);
		fieldSet.setHeight(TAB_HEIGHT - 47);

		row = new RowContainer();

		// Names
		label = new MyLabel("Nombres:", LABEL_WIDTH);
		row.add(label);

		InputBox name = new InputBox(150, 40, Validate.TEXT);
		name.setDataSource(new DataSource("per", "name", DataSourceType.RECORD));
		name.setAllowBlank(false);
		row.add(name);

		fieldSet.add(row);

		row = new RowContainer();

		// Last Name
		label = new MyLabel("Apellido Paterno:", LABEL_WIDTH);
		row.add(label);
		fieldSet.add(row);

		InputBox lastName = new InputBox(150, 40, Validate.TEXT);
		lastName.setDataSource(new DataSource("per", "lastName", DataSourceType.RECORD));
		lastName.createValidator(Validate.REQUIRED);
		row.add(lastName);

		row = new RowContainer();

		// Second Last Name
		label = new MyLabel("Apellido Materno:", LABEL_WIDTH);
		row.add(label);
		fieldSet.add(row);

		InputBox lastName2 = new InputBox("", "Person:secondLastName:1", 150, 40, Validate.TEXT);
		lastName2.setDataSource(new DataSource("per", "secondLastName", DataSourceType.RECORD));
		row.add(lastName2);

		row = new RowContainer();

		// Date of Birth:
		label = new MyLabel("Fecha Nacimiento:", LABEL_WIDTH);
		row.add(label);

		final MyDateField birthDate = new MyDateField();
		birthDate.createValidator(Validate.REQUIRED);
		birthDate.setDataSource(new DataSource("per", "dateOfBirth", DataSourceType.RECORD));
		row.add(birthDate);
		fieldSet.add(row);

		// Gender:
		row = new RowContainer();
		label = new MyLabel("Genero:", LABEL_WIDTH);
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

		final InputBox descGender = new InputBox(90);
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

		final InputBox descCivilStatus = new InputBox(90);
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

		final InputBox descProfession = new InputBox(90);
		descProfession.setReadOnly(true);
		descProfession.setDataSource(new DataSource("ProfessionType", "name", DataSourceType.DESCRIPTION));
		row.add(descProfession);

		profesionCombo.linkWithField(descProfession, "name");

		fieldSet.add(row);

		left.add(fieldSet);
		row = new RowContainer();

		// ID Type:

		FieldSet fieldSet2 = new FieldSet();
		fieldSet2.setHeading("Identificacion");
		fieldSet2.setWidth(270);
		fieldSet2.setCollapsible(false);

		label = new MyLabel("Tipo ID:", LABEL_WIDTH + 10);
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

		final InputBox descTipoId = new InputBox(90);
		descTipoId.setReadOnly(true);
		descTipoId.setDataSource(new DataSource("IdentificationType", "name", DataSourceType.DESCRIPTION));
		row.add(descTipoId);

		idTypeCombo.linkWithField(descTipoId, "name");

		fieldSet2.add(row);

		row = new RowContainer();

		// Identification
		label = new MyLabel("Identificacion:", LABEL_WIDTH + 10);
		row.add(label);

		final InputBox identification = new InputBox(150, 40, Validate.ALPHANUMERIC);
		identification.setDataSource(new DataSource("per", "identificationNumber", DataSourceType.RECORD));
		row.add(identification);

		identification.addListener(Events.OnBlur, new Listener<FieldEvent>() {
			public void handleEvent(FieldEvent fe) {

				if (type == IdType.CED || type == IdType.RUC) {
					isValidId = new ValidateIdentification().isValid(identification.getValue(), type);
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
		VerticalPanel aux = new VerticalPanel();
		aux.setHeight(70);
		right.add(fieldSet2);
		right.add(aux);
		right.add(new Location());

		panelPrincipal.add(left, new RowData(.45, 1, new Margins(4)));
		panelPrincipal.add(right, new RowData(.45, 1, new Margins(4)));

		basic.add(panelPrincipal);

		// AddressTab
		TabItem addressTab = new TabItem();
		addressTab.setStyleAttribute("padding", "10px");
		addressTab.setText("Direcciones");
		addressTab.setLayout(new CenterLayout());
		addressTab.setBorders(true);
		addressTab.add(createAddressGrid());

		// AddressTab
		TabItem phoneTab = new TabItem();
		phoneTab.setStyleAttribute("padding", "10px");
		phoneTab.setText("Telefonos");
		phoneTab.setLayout(new CenterLayout());
		phoneTab.setBorders(true);
		phoneTab.add(createPhoneGrid());

		// Add div components
		tabPanel.add(basic);
		tabPanel.add(addressTab);
		tabPanel.add(phoneTab);

		// Hidden Date field:
		// final InputBox birthDateAux = new InputBox();
		// birthDateAux.setVisible(false);
		// birthDateAux.setPersistentInfo("Person:birthDate:1");
		// form.add(birthDateAux);

		form.add(tabPanel);
		form.setButtonAlign(HorizontalAlignment.CENTER);
		form.addButton(new Button("Guardar", new SelectionListener<ButtonEvent>() {

			@Override
			public void componentSelected(ButtonEvent ce) {
				String dateString = "";
				AlertDialog message = new AlertDialog("Error", "Campos mal ingresados");
				;

				if (birthDate.getValue() != null) {
					dateString = DatesManager.dateToString(birthDate.getValue(), Format.DATE);

					if (!validateDateField(dateString)) {
						message = new AlertDialog("Error", "Fecha Incorrecta");
						birthDate.forceInvalid("Fecha Incorrecta");
					}
				}

				if (!isValidId) {
					message = new AlertDialog("Error", "Identificacion incorrecta");
					identification.forceInvalid("Identificacion Incorrecta");
				}

				// if (form.isValid()){
				// message = new AlertDialog("Alerta","Es validisimo!!");
				// message.show();
				// }else{
				// message = new AlertDialog("Alerta","Es incorrecto!!");
				// message.show();
				// }

				if (isValidId && validateDateField(dateString)) {
					form.commitForm();
					// //saveAddresses();
					// //savePhones();
				}
				// }else{
				// message.show();
				// }

			}
		}));

		add(form);
	}

	private LayoutContainer createAddressGrid() {

		LayoutContainer lc = new LayoutContainer();
		lc.setLayout(new CenterLayout());
		lc.getAriaSupport().setPresentation(true);

		// Config
		String entity = "PersonAddress";
		final Integer PAGE_SIZE = 5;
		final String process = "B101";

		final ArrayColumnData cdata = new ArrayColumnData();
		cdata.add(new MyColumnData("pk_personId", "Persona", 40, 20, false));
		cdata.add(new MyColumnData("pk_addressSequence", "Sec.", 40, 20, false));
		cdata.add(new MyColumnData("addressTypeId", "Tipo", 40, 6, false));
		cdata.add(new MyColumnData("addressDescription", "Descripcion", 100, 50, true));
		cdata.add(new MyColumnData("countryId", "Pais", 50, 50, true));
		cdata.add(new MyColumnData("provinceId", "Provincia", 50, 50, true));
		cdata.add(new MyColumnData("cityId", "Canton", 50, 50, true));
		cdata.add(new MyColumnData("districtId", "Parroquia", 50, 50, true));
		getConfig().setlDataSource(cdata.getDataSources());

		// MyProcessConfig config = new MyProcessConfig(process, entity,
		// cdata.getIdFields());

		// Proxy - loader - store
		// MyHttpProxy proxy = new MyHttpProxy();
		// final MyPagingLoader loader = new MyPagingLoader(proxy, config);
		addressStore = new MyListStore(getLoader());

		// Column model
		List<ColumnConfig> configs = new ArrayList<ColumnConfig>();

		configs.add(new NormalColumn(cdata.get(0)));
		configs.add(new NormalColumn(cdata.get(1)));

		ComboColumn aTypeComboColumn = new ComboColumn(cdata.get(2));
		Reference refaType = new Reference("adt", "AddressType");
		ArrayColumnData cdataComboAType = new ArrayColumnData();
		cdataComboAType.add(new MyColumnData("adt", "pk_addressTypeId", "ID", 40));
		cdataComboAType.add(new MyColumnData("adt", "name", "Nombre", 150));
		aTypeComboColumn.setQueryData(refaType, cdataComboAType);
		configs.add(aTypeComboColumn);

		configs.add(new NormalColumn(cdata.get(3)));

		final ComboColumn countryCombo = new ComboColumn(cdata.get(4));
		Reference refCountry = new Reference("cou", "Country");
		ArrayColumnData combodata = new ArrayColumnData();
		combodata.add(new MyColumnData("cou", "pk_countryId", "Pais", 70));
		combodata.add(new MyColumnData("cou", "name", "Nombre", 150));
		countryCombo.setQueryData(refCountry, combodata);
		configs.add(countryCombo);

		final ComboColumn provinceCombo = new ComboColumn(cdata.get(5));
		Reference refProvince = new Reference("pro", "Province");
		ArrayColumnData combodata2 = new ArrayColumnData();
		combodata2.add(new MyColumnData("pro", "pk_provinceId", "Provincia", 70));
		combodata2.add(new MyColumnData("pro", "name", "Nombre", 150));
		combodata2.add(new MyColumnData("pro", "pk_countryId", "Provincia", 50));
		provinceCombo.setQueryData(refProvince, combodata2);
		configs.add(provinceCombo);

		final ComboColumn cityCombo = new ComboColumn(cdata.get(6));
		Reference refCity = new Reference("cit", "City");
		final ArrayColumnData combodata3 = new ArrayColumnData();
		combodata3.add(new MyColumnData("cit", "pk_cityId", "Codigo", 70));
		combodata3.add(new MyColumnData("cit", "name", "Nombre", 150));
		combodata3.add(new MyColumnData("cit", "pk_countryId", "Pais", 50));
		combodata3.add(new MyColumnData("cit", "pk_provinceId", "Ciudad", 50));
		cityCombo.setQueryData(refCity, combodata3);
		configs.add(cityCombo);

		final ComboColumn districtCombo = new ComboColumn(cdata.get(7));
		Reference refDistrict = new Reference("dis", "District");
		final ArrayColumnData combodata4 = new ArrayColumnData();
		combodata4.add(new MyColumnData("dis", "pk_districtId", "Codigo", 70));
		combodata4.add(new MyColumnData("dis", "name", "Nombre", 150));
		combodata4.add(new MyColumnData("dis", "pk_countryId", "Pais", 50));
		combodata4.add(new MyColumnData("dis", "pk_provinceId", "Provincia", 50));
		combodata4.add(new MyColumnData("dis", "pk_cityId", "City", 50));
		districtCombo.setQueryData(refDistrict, combodata4);
		configs.add(districtCombo);

		configs.add(new ExpireColumnConfig());

		ColumnModel cm = new ColumnModel(configs);

		// Filters
		GridFilters filters = new GridFilters();
		StringFilter parameterIdFilter = new StringFilter(cdata.getIdFields().get(0));
		StringFilter subsystemFilter = new StringFilter(cdata.getIdFields().get(1));
		filters.addFilter(parameterIdFilter);
		filters.addFilter(subsystemFilter);

		// Content panel
		EntityContentPanel cp = new EntityContentPanel("Direcciones", FORM_WIDTH - 35, TAB_HEIGHT - 26);
		cp.setHeaderVisible(false);

		// Grid

		addressGrid = new EntityEditorGrid(addressStore, cm);
		addressGrid.setAutoExpandColumn("addressDescription");
		addressGrid.addPlugin(filters);
		addressGrid.getColumnModel().getColumn(0).getEditor().disable();
		addressGrid.getColumnModel().getColumn(1).getEditor().disable();
		cp.add(addressGrid);

		addressGrid.addListener(Events.Attach, new Listener<BaseEvent>() {
			public void handleEvent(BaseEvent be) {
				addressStore.sort(cdata.getIdFields().get(0), SortDir.ASC);
			}
		});

		// Top tool bar
		GridToolBar toolBar = new GridToolBar(addressGrid, addressStore);
		toolBar.initColumnIndex = 2;
		cp.setTopComponent(toolBar);

		// Paging tool bar
		final GridPagingToolBar pagingToolBar = new GridPagingToolBar(addressGrid, PAGE_SIZE);
		cp.setBottomComponent(pagingToolBar);

		lc.add(cp);

		return lc;

	}

	private LayoutContainer createPhoneGrid() {

		LayoutContainer lc = new LayoutContainer();
		lc.setLayout(new CenterLayout());
		lc.getAriaSupport().setPresentation(true);

		// Config
		String entity = "PersonPhone";
		final Integer PAGE_SIZE = 5;
		final String process = "B101";

		final ArrayColumnData cdata = new ArrayColumnData();
		cdata.add(new MyColumnData("pk_personId", "Persona", 30, 11, false));
		cdata.add(new MyColumnData("pk_phoneSequence", "Sec.", 80, 4, false));
		cdata.add(new MyColumnData("phoneTypeId", "Tipo", 80, 4, false));
		cdata.add(new MyColumnData("areaCode", "Cod. Area", 100, 4, false));
		cdata.add(new MyColumnData("phoneNumber", "Numero", 100, 40, true));
		getConfig().setlDataSource(cdata.getDataSources());

		// MyProcessConfig config = new MyProcessConfig(process, entity,
		// cdata.getIdFields());
		//
		// // Proxy - loader - store
		// MyHttpProxy proxy = new MyHttpProxy();
		// final MyPagingLoader loader = new MyPagingLoader(proxy, config);
		phoneStore = new MyListStore(getLoader());

		// Column model
		List<ColumnConfig> configs = new ArrayList<ColumnConfig>();

		configs.add(new NormalColumn(cdata.get(0)));
		configs.add(new NormalColumn(cdata.get(1)));

		ComboColumn pTypeComboColumn = new ComboColumn(cdata.get(2));
		Reference refPhoneType = new Reference("pht", "PhoneType");
		ArrayColumnData cdataComboAType = new ArrayColumnData();
		cdataComboAType.add(new MyColumnData("pht", "pk_phoneTypeId", "ID", 40));
		cdataComboAType.add(new MyColumnData("pht", "name", "Nombre", 150));
		pTypeComboColumn.setQueryData(refPhoneType, cdataComboAType);
		configs.add(pTypeComboColumn);

		configs.add(new NormalColumn(cdata.get(3), NumberType.DECIMAL, null));
		configs.add(new NormalColumn(cdata.get(4), NumberType.TEXT, Validate.EMAIL));

		configs.add(new ExpireColumnConfig());

		ColumnModel cm = new ColumnModel(configs);

		// Filters
		GridFilters filters = new GridFilters();
		StringFilter parameterIdFilter = new StringFilter(cdata.getIdFields().get(0));
		StringFilter subsystemFilter = new StringFilter(cdata.getIdFields().get(1));
		filters.addFilter(parameterIdFilter);
		filters.addFilter(subsystemFilter);

		// Content panel
		EntityContentPanel cp = new EntityContentPanel("Telefonos", FORM_WIDTH - 35, TAB_HEIGHT - 26);
		cp.setHeaderVisible(false);

		// Grid
		phoneGrid = new EntityEditorGrid(phoneStore, cm);
		phoneGrid.setAutoExpandColumn("pk_personId");
		phoneGrid.addPlugin(filters);
		phoneGrid.getColumnModel().getColumn(0).getEditor().disable();
		phoneGrid.getColumnModel().getColumn(1).getEditor().disable();
		cp.add(phoneGrid);
		phoneGrid.addListener(Events.Attach, new Listener<BaseEvent>() {
			public void handleEvent(BaseEvent be) {
				phoneStore.sort(cdata.getIdFields().get(0), SortDir.ASC);
			}
		});

		// Top tool bar
		GridToolBar toolBar = new GridToolBar(phoneGrid, phoneStore);
		toolBar.initColumnIndex = 2;
		cp.setTopComponent(toolBar);

		// Paging tool bar
		final GridPagingToolBar pagingToolBar = new GridPagingToolBar(phoneGrid, PAGE_SIZE);
		cp.setBottomComponent(pagingToolBar);

		lc.add(cp);

		return lc;

	}

	private void saveAddresses() {

		for (int i = 0; i < addressStore.getCount(); i++) {
			// personId =
			// personIdCombo.getValue().get("pk_personId").toString();
			String personId = "001";
			addressGrid.getStore().getAt(i).set("pk_personId", personId);
			addressStore.fireEvent(Events.Update);
			addressGrid.getView().refresh(true);
		}

	}

	private void savePhones() {

		for (int i = 0; i < phoneStore.getCount(); i++) {
			String personId = "001";
			// personId =
			// personIdCombo.getValue().get("pk_personId").toString();
			phoneGrid.getStore().getAt(i).set("pk_personId", personId);
			phoneStore.fireEvent(Events.Update);
			phoneGrid.getView().refresh(true);
		}

	}

	private boolean validateDateField(String date) {

		if (DatesManager.isAfterToday(date)) {
			return false;
		}

		if (DatesManager.isToday(date)) {
			return false;
		} else {
			return true;
		}

	}

}