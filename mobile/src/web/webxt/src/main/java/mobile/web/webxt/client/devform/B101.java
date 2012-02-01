package mobile.web.webxt.client.devform;

import java.util.ArrayList;
import java.util.List;

import mobile.web.webxt.client.data.MyHttpProxy;
import mobile.web.webxt.client.data.MyListStore;
import mobile.web.webxt.client.data.MyPagingLoader;
import mobile.web.webxt.client.data.MyProcessConfig;
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
import mobile.web.webxt.client.form.widgets.SpecialComboForm;
import mobile.web.webxt.client.form.widgetsgrid.ArrayColumnData;
import mobile.web.webxt.client.form.widgetsgrid.ComboColumn;
import mobile.web.webxt.client.form.widgetsgrid.EntityEditorGrid;
import mobile.web.webxt.client.form.widgetsgrid.ExpireColumnConfig;
import mobile.web.webxt.client.form.widgetsgrid.GridPagingToolBar;
import mobile.web.webxt.client.form.widgetsgrid.GridToolBar;
import mobile.web.webxt.client.form.widgetsgrid.MyColumnData;
import mobile.web.webxt.client.form.widgetsgrid.MyColumnData.ColumnType;
import mobile.web.webxt.client.form.widgetsgrid.NormalColumn;
import mobile.web.webxt.client.util.DatesManager;
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
import com.extjs.gxt.ui.client.widget.Info;
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
	private final String PROCESS = "B101";
	// Constants
	final int FORM_WIDTH = 600;
	final int TAB_HEIGHT = 300;
	final int LABEL_WIDTH = 70;
	ComboForm personIdCombo = new ComboForm(100);
	EntityEditorGrid addressGrid;
	MyListStore addressStore;
	EntityEditorGrid phoneGrid;
	MyListStore phoneStore;
	
	boolean isValidId=false;
	IdType type;

	ContentPanel panelPrincipal = new ContentPanel();

	@Override
	protected void onRender(Element parent, int index) {
		super.onRender(parent, index);
		createForm();
	}

	private void createForm() {

		ContentPanel left = new ContentPanel();
		left.setHeaderVisible(false);
		left.setWidth(270);
		left.setHeight(300);
		left.setBorders(false);

		ContentPanel right = new ContentPanel();
		right.setHeaderVisible(false);
		right.setWidth(270);
		right.setHeight(300);
		right.setBorders(false);

		panelPrincipal.setFrame(false);
		panelPrincipal.setHeaderVisible(false);
		panelPrincipal.setSize(FORM_WIDTH, TAB_HEIGHT);
		panelPrincipal.setLayout(new RowLayout(Orientation.HORIZONTAL));

		// Form panel
		final MyFormPanel form = new MyFormPanel(PROCESS, "Ingreso de personas Naturales", FORM_WIDTH);
		form.setLayout(new FlowLayout());

		// Header
		// Person id
		RowContainer row = new RowContainer();
		MyLabel label = new MyLabel("Persona:", LABEL_WIDTH);
		row.add(label);

		personIdCombo.setPersistentInfo("Person:pk_personId:1");
		personIdCombo.setDisplayField("pk_personId");
		final ArrayColumnData solCdata = new ArrayColumnData();
		solCdata.add(new MyColumnData("pk_personId", "Id", 100));
		personIdCombo.setRqData("Person", solCdata);
		row.add(personIdCombo);

		form.add(row);

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
		fieldSet.setHeight(TAB_HEIGHT-47);
		
		row = new RowContainer();

		// Names
		label = new MyLabel("Nombres:", LABEL_WIDTH);
		row.add(label);

		InputBox name = new InputBox("", "Person:name:1", 150, 40, Validate.TEXT);
		name.setAllowBlank(false);
		row.add(name);

		fieldSet.add(row);

		row = new RowContainer();

		// Last Name
		label = new MyLabel("Apellido Paterno:", LABEL_WIDTH);
		row.add(label);
		fieldSet.add(row);

		InputBox lastName = new InputBox("", "Person:lastName:1", 150, 40, Validate.TEXT);
		row.add(lastName);

		row = new RowContainer();

		// Second Last Name
		label = new MyLabel("Apellido Materno:", LABEL_WIDTH);
		row.add(label);
		fieldSet.add(row);

		InputBox lastName2 = new InputBox("", "Person:secondLastName:1", 150, 40, Validate.TEXT);
		row.add(lastName2);

		row = new RowContainer();

		// Date of Birth:
		label = new MyLabel("Fecha Nacimiento:", LABEL_WIDTH);
		row.add(label);

		final MyDateField birthDate = new MyDateField(100, 40);
		row.add(birthDate);
		fieldSet.add(row);

		// Gender:
		row = new RowContainer();
		label = new MyLabel("Genero:", LABEL_WIDTH);
		row.add(label);

		final ComboForm genderCombo = new ComboForm(50);
		genderCombo.setPersistentInfo("Person:genderTypeId:1");
		genderCombo.setDisplayField("pk_genderTypeId");
		final ArrayColumnData combodata = new ArrayColumnData();
		combodata.add(new MyColumnData("pk_genderTypeId", "Codigo", 70));
		combodata.add(new MyColumnData("name", "Nombre", 150));
		genderCombo.setRqData("GenderType", combodata);
		row.add(genderCombo);
		
		// Description
		final InputBox descGender = new InputBox(90);
		descGender.setReadOnly(true);
		row.add(descGender);
		
		genderCombo.addSelectionChangedListener(new SelectionChangedListener<ModelData>() {
			@Override
			public void selectionChanged(SelectionChangedEvent<ModelData> se) {
				ModelData selected = se.getSelectedItem();
				descGender.setValue(selected.get("name").toString());
			}
		});
		
		fieldSet.add(row);

		// Civil Status:
		row = new RowContainer();
		label = new MyLabel("Estado Civil:", LABEL_WIDTH);
		row.add(label);

		final ComboForm civilStatusCombo = new ComboForm(50);
		civilStatusCombo.setPersistentInfo("Person:civilStatusId:1");
		civilStatusCombo.setDisplayField("pk_civilStatusId");
		final ArrayColumnData combodata2 = new ArrayColumnData();
		combodata2.add(new MyColumnData("pk_civilStatusId", "Codigo", 70));
		combodata2.add(new MyColumnData("name", "Nombre", 150));
		civilStatusCombo.setRqData("CivilStatus", combodata2);
		row.add(civilStatusCombo);
		
		// Description
		final InputBox descCivilStatus = new InputBox(90);
		descCivilStatus.setReadOnly(true);
		row.add(descCivilStatus);
		
		civilStatusCombo.addSelectionChangedListener(new SelectionChangedListener<ModelData>() {
			@Override
			public void selectionChanged(SelectionChangedEvent<ModelData> se) {
				ModelData selected = se.getSelectedItem();
				descCivilStatus.setValue(selected.get("name").toString());
			}
		});
		
		fieldSet.add(row);
		
		// Profession:
		row = new RowContainer();
		label = new MyLabel("Profesión:", LABEL_WIDTH);
		row.add(label);

		// Combo Profesion
		SpecialComboForm profesionCombo = new SpecialComboForm(50);
		profesionCombo.setPersistentInfo("Person:professionTypeId:1");
		profesionCombo.setDisplayField("pk_professionTypeId");
		profesionCombo.setPageSize(10);
		ArrayColumnData cdataCombo = new ArrayColumnData();
		cdataCombo.add(new MyColumnData("pk_professionTypeId", "Codigo", 50));
		cdataCombo.add(new MyColumnData("name", "Nombre", 200));
		profesionCombo.setRqData("ProfessionType", cdataCombo);
		row.add(profesionCombo);
		
		// Description
		final InputBox descProfession = new InputBox(90);
		descProfession.setMaxLength(300);
		descProfession.setReadOnly(true);
		descProfession.setToolTipValue(true);
		row.add(descProfession);
		
		profesionCombo.addSelectionChangedListener(new SelectionChangedListener<ModelData>() {
			@Override
			public void selectionChanged(SelectionChangedEvent<ModelData> se) {
				ModelData selected = se.getSelectedItem();
				descProfession.setValue(selected.get("name").toString());
			}
		});
		
		fieldSet.add(row);
		
		left.add(fieldSet);
		row = new RowContainer();

		// ID Type:

		FieldSet fieldSet2 = new FieldSet();
		fieldSet2.setHeading("Identificacion");
		fieldSet2.setCollapsible(false);

		label = new MyLabel("Tipo ID:", LABEL_WIDTH);
		row.add(label);

		final ComboForm idTypeCombo = new ComboForm(50);
		idTypeCombo.setPersistentInfo("Person:identificationTypeId:1");
		idTypeCombo.setDisplayField("pk_identificationTypeId");
		final ArrayColumnData combodata4 = new ArrayColumnData();
		combodata4.add(new MyColumnData("pk_identificationTypeId", "Codigo", 70));
		combodata4.add(new MyColumnData("name", "Nombre", 150));
		idTypeCombo.setRqData("IdentificationType", combodata4);
		row.add(idTypeCombo);
		
		// Description
		final InputBox descTipoId = new InputBox(90);
		descTipoId.setReadOnly(true);
		row.add(descTipoId);
		
		idTypeCombo.addSelectionChangedListener(new SelectionChangedListener<ModelData>() {
			@Override
			public void selectionChanged(SelectionChangedEvent<ModelData> se) {
				ModelData selected = se.getSelectedItem();
				descTipoId.setValue(selected.get("name").toString());
			}
		});
		
		fieldSet2.add(row);

		row = new RowContainer();

		// Identification
		label = new MyLabel("Identificacion:", LABEL_WIDTH);
		row.add(label);

		final InputBox identification = new InputBox("", "Person:identificationNumber:1", 150, 40, Validate.ALFANUMERICO);
		row.add(identification);
		
		identification.addListener(Events.OnBlur, new Listener<FieldEvent>() {
            public void handleEvent(FieldEvent fe) {
                
            	if(type==IdType.CED || type==IdType.RUC){
            		isValidId=new ValidateIdentification().isValid(identification.getValue(),type);
            	}
            	
            	if (!isValidId){
            		identification.isValid(false);
                    identification.markInvalid("Identificacion Incorrecta");
            	}else{
            		identification.isValid(true);
            	}
            	
            }
        });
		
		idTypeCombo.addSelectionChangedListener(new SelectionChangedListener<ModelData>() {
			@Override
			public void selectionChanged(SelectionChangedEvent<ModelData> se) {
		
				String valorId=idTypeCombo.getValue().get("pk_identificationTypeId").toString();
				
				if (valorId.compareTo("CED")==0){
					type=IdType.CED;
				}else if(valorId.compareTo("RUC")==0){
					type=IdType.RUC;
				}else{
					type=null;
				}
			}
		});
		
		fieldSet2.add(row);

		right.add(fieldSet2);
		VerticalPanel aux = new VerticalPanel();
		aux.setHeight(70);
		right.add(fieldSet2);
		right.add(aux);
		right.add(new Location("Person"));

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
		
		//Hidden Date field:
		final InputBox birthDateAux = new InputBox();
		birthDateAux.setVisible(false);
		birthDateAux.setPersistentInfo("Person:birthDate:1");
		form.add(birthDateAux);

		form.add(tabPanel);
		form.setButtonAlign(HorizontalAlignment.CENTER);
		form.addButton(new Button("Guardar", new SelectionListener<ButtonEvent>() {

			@Override
			public void componentSelected(ButtonEvent ce) {
				String dateString="";	
				AlertDialog message=new AlertDialog("Error","Campos mal ingresados");;
				
				if (birthDate.getValue() != null) {
					dateString = birthDate.getRawValue().toString().substring(6, 10);
					dateString = dateString + "-" + birthDate.getRawValue().toString().substring(3, 5) + "-";
					dateString = dateString + birthDate.getRawValue().toString().substring(0, 2);
					birthDateAux.setValue(dateString);
				}
				
				if (!validateDateField(dateString)){
					message = new AlertDialog("Error","Fecha Incorrecta");
					birthDate.forceInvalid("Fecha Incorrecta");
				} 
				if (!isValidId) {
					message = new AlertDialog("Error","Identificacion incorrecta");
					identification.forceInvalid("Identificacion Incorrecta");
				}
				
				if (form.isValid()){
					message = new AlertDialog("Alerta","Es validisimo!!");
					message.show();
				}else{
					message = new AlertDialog("Alerta","Es incorrecto!!");
					message.show();
				}
				
//				if (isValidId && validateDateField(dateString)) {
//					form.commitForm();
//					form.isValid();
//					//saveAddresses();
//					//savePhones();
//	
//				}else{
//					message.show();
//				}

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

		MyProcessConfig config = new MyProcessConfig(process, entity, cdata.getIdFields());

		// Proxy - loader - store
		MyHttpProxy proxy = new MyHttpProxy();
		final MyPagingLoader loader = new MyPagingLoader(proxy, config);
		addressStore = new MyListStore(loader);

		// Column model
		List<ColumnConfig> configs = new ArrayList<ColumnConfig>();

		configs.add(new NormalColumn(cdata.get(0)));
		configs.add(new NormalColumn(cdata.get(1)));

		ComboColumn aTypeComboColumn = new ComboColumn(cdata.get(2));
		ArrayColumnData cdataComboAType = new ArrayColumnData();
		cdataComboAType.add(new MyColumnData("pk_addressTypeId", "ID", 40));
		cdataComboAType.add(new MyColumnData("name", "Nombre", 150));
		aTypeComboColumn.setRqData("AddressType", cdataComboAType);
		configs.add(aTypeComboColumn);

		configs.add(new NormalColumn(cdata.get(3)));

		final ComboColumn countryCombo = new ComboColumn(cdata.get(4));
		ArrayColumnData combodata = new ArrayColumnData();
		combodata.add(new MyColumnData("pk_countryId", "Pais", 70));
		combodata.add(new MyColumnData("name", "Nombre", 150));
		countryCombo.setRqData("Country", combodata);
		configs.add(countryCombo);

		final ComboColumn provinceCombo = new ComboColumn(cdata.get(5));
		ArrayColumnData combodata2 = new ArrayColumnData();
		combodata2.add(new MyColumnData("pk_provinceId", "Provincia", 70, 20, false));
		combodata2.add(new MyColumnData("name", "Nombre", 150, 40, false));
		combodata2.add(new MyColumnData("pk_countryId", ColumnType.HIDDEN));
		provinceCombo.setRqData("Province", combodata2);
		configs.add(provinceCombo);

		final ComboColumn cityCombo = new ComboColumn(cdata.get(6));
		final ArrayColumnData combodata3 = new ArrayColumnData();
		combodata3.add(new MyColumnData("pk_cityId", "Codigo", 70, 20, false));
		combodata3.add(new MyColumnData("name", "Nombre", 150, 40, false));
		combodata3.add(new MyColumnData("pk_countryId", ColumnType.HIDDEN));
		combodata3.add(new MyColumnData("pk_provinceId", ColumnType.HIDDEN));
		cityCombo.setRqData("City", combodata3);
		configs.add(cityCombo);

		final ComboColumn districtCombo = new ComboColumn(cdata.get(7));
		final ArrayColumnData combodata4 = new ArrayColumnData();
		combodata4.add(new MyColumnData("pk_districtId", "Codigo", 70, 20, false));
		combodata4.add(new MyColumnData("name", "Nombre", 150, 40, false));
		combodata4.add(new MyColumnData("pk_countryId", ColumnType.HIDDEN));
		combodata4.add(new MyColumnData("pk_provinceId", ColumnType.HIDDEN));
		combodata4.add(new MyColumnData("pk_cityId", ColumnType.HIDDEN));
		districtCombo.setRqData("District", combodata4);
		configs.add(districtCombo);

		configs.add(new ExpireColumnConfig());
		
		
		//LISTENERS:
		countryCombo.getComboBox().addSelectionChangedListener(new SelectionChangedListener<ModelData>() {
			@Override
			public void selectionChanged(SelectionChangedEvent<ModelData> se) {

				if (countryCombo.getComboBox().getValue() == null) {
					return;
				}
				
				String filterFieldId = "pk_countryId";
				String filterValue = countryCombo.getComboBox().getValue().get(filterFieldId).toString();

				countryCombo.setFilter(filterFieldId,filterValue);
				provinceCombo.getComboBox().setLoaded(false);
				provinceCombo.getComboBox().setValue(null);
				cityCombo.getComboBox().setLoaded(false);
				cityCombo.getComboBox().setValue(null);
				districtCombo.getComboBox().setLoaded(false);
				districtCombo.getComboBox().setValue(null);
			}
		});
		
		provinceCombo.getComboBox().addSelectionChangedListener(new SelectionChangedListener<ModelData>() {
			@Override
			public void selectionChanged(SelectionChangedEvent<ModelData> se) {

				if (provinceCombo.getComboBox().getValue() == null) {
					return;
				}
				
				String filterField = "pk_countryId";
				String filterField2 = "pk_provinceId";
				String filterValue1 = countryCombo.getComboBox().getValue().get(filterField).toString();
				String filterValue2 = provinceCombo.getComboBox().getValue().get(filterField2).toString();

				cityCombo.setFilter(filterField,filterValue1);
				cityCombo.setFilter(filterField2,filterValue2);
				cityCombo.getComboBox().setLoaded(false);
				cityCombo.getComboBox().setValue(null);
				districtCombo.getComboBox().setLoaded(false);
				districtCombo.getComboBox().setValue(null);
			}
		});
		
		districtCombo.getComboBox().addSelectionChangedListener(new SelectionChangedListener<ModelData>() {
			@Override
			public void selectionChanged(SelectionChangedEvent<ModelData> se) {

				if (districtCombo.getComboBox().getValue() == null) {
					return;
				}
				
				String filterField = "pk_countryId";
				String filterField2 = "pk_provinceId";
				String filterField3 = "pk_cityId";
				
				String filterValue1 = countryCombo.getComboBox().getValue().get(filterField).toString();
				String filterValue2 = provinceCombo.getComboBox().getValue().get(filterField2).toString();
				String filterValue3 = cityCombo.getComboBox().getValue().get(filterField3).toString();

				districtCombo.setFilter(filterField,filterValue1);
				districtCombo.setFilter(filterField2,filterValue2);
				districtCombo.setFilter(filterField3,filterValue3);
				districtCombo.getComboBox().setLoaded(false);
				districtCombo.getComboBox().setValue(null);
			}
		});
		
		provinceCombo.getComboBox().addListener(Events.BeforeQuery, new Listener<BaseEvent>() {
			public void handleEvent(BaseEvent be) {
				MyProcessConfig config = (MyProcessConfig) ((MyPagingLoader) provinceCombo.getComboBox()
						.getStore().getLoader()).getConfig();
				if (config.getFilterConfigs() == null) {
					be.setCancelled(true);
					Info.display("Advertencia", "Seleccione un Pais");
				}
			}
		});
		
		cityCombo.getComboBox().addListener(Events.BeforeQuery, new Listener<BaseEvent>() {
			public void handleEvent(BaseEvent be) {
				MyProcessConfig config = (MyProcessConfig) ((MyPagingLoader) cityCombo.getComboBox()
						.getStore().getLoader()).getConfig();
				if (config.getFilterConfigs() == null) {
					be.setCancelled(true);
					Info.display("Advertencia", "Seleccione una Provincia");
				}
			}
		});
		
		districtCombo.getComboBox().addListener(Events.BeforeQuery, new Listener<BaseEvent>() {
			public void handleEvent(BaseEvent be) {
				MyProcessConfig config = (MyProcessConfig) ((MyPagingLoader) districtCombo.getComboBox()
						.getStore().getLoader()).getConfig();
				if (config.getFilterConfigs() == null) {
					be.setCancelled(true);
					Info.display("Advertencia", "Seleccione una Ciudad");
				}
			}
		});

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
		toolBar.initColumnIndex=2;
		cp.setTopComponent(toolBar);

		// Paging tool bar
		final GridPagingToolBar pagingToolBar = new GridPagingToolBar(PAGE_SIZE, loader);
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
		cdata.add(new MyColumnData("phoneNumber", "Numero", 100, 40, false));

		MyProcessConfig config = new MyProcessConfig(process, entity, cdata.getIdFields());

		// Proxy - loader - store
		MyHttpProxy proxy = new MyHttpProxy();
		final MyPagingLoader loader = new MyPagingLoader(proxy, config);
		phoneStore = new MyListStore(loader);

		// Column model
		List<ColumnConfig> configs = new ArrayList<ColumnConfig>();

		configs.add(new NormalColumn(cdata.get(0)));
		configs.add(new NormalColumn(cdata.get(1)));

		ComboColumn pTypeComboColumn = new ComboColumn(cdata.get(2));
		ArrayColumnData cdataComboAType = new ArrayColumnData();
		cdataComboAType.add(new MyColumnData("pk_phoneTypeId", "ID", 40));
		cdataComboAType.add(new MyColumnData("name", "Nombre", 150));
		pTypeComboColumn.setRqData("PhoneType", cdataComboAType);
		configs.add(pTypeComboColumn);

		configs.add(new NormalColumn(cdata.get(3)));
		configs.add(new NormalColumn(cdata.get(4)));

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
		toolBar.initColumnIndex=2;
		cp.setTopComponent(toolBar);

		// Paging tool bar
		final GridPagingToolBar pagingToolBar = new GridPagingToolBar(PAGE_SIZE, loader);
		cp.setBottomComponent(pagingToolBar);

		lc.add(cp);

		return lc;

	}

	private void saveAddresses(){
		
		for (int i = 0; i < addressStore.getCount(); i++) {
			// personId =
			// personIdCombo.getValue().get("pk_personId").toString();
			String personId = "001";
			addressGrid.getStore().getAt(i).set("pk_personId", personId);
			addressStore.fireEvent(Events.Update);
			addressGrid.getView().refresh(true);
		}
		
	}
	
	private void savePhones(){
		
		for (int i = 0; i < phoneStore.getCount(); i++) {
			String personId = "001";
			//personId = personIdCombo.getValue().get("pk_personId").toString();
			phoneGrid.getStore().getAt(i).set("pk_personId", personId);
			phoneStore.fireEvent(Events.Update);
			phoneGrid.getView().refresh(true);
		}
		
	}
	
	private boolean validateDateField(String date){
		
		if(DatesManager.isAfterToday(date)){
			return false;
		}
		
		if(DatesManager.isToday(date) ){
			return false;
		}else{
			return true;
		}
		
	}

}