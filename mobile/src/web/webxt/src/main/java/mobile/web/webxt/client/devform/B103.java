package mobile.web.webxt.client.devform;

import java.util.ArrayList;
import java.util.List;

import mobile.web.webxt.client.data.form.DataSource;
import mobile.web.webxt.client.data.form.DataSourceType;
import mobile.web.webxt.client.data.form.Reference;
import mobile.web.webxt.client.form.EntityContentPanel;
import mobile.web.webxt.client.form.MyFormPanel;
import mobile.web.webxt.client.form.MyGeneralForm;
import mobile.web.webxt.client.form.widgets.ComboForm;
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

import com.extjs.gxt.ui.client.Style.SortDir;
import com.extjs.gxt.ui.client.data.ModelData;
import com.extjs.gxt.ui.client.event.BaseEvent;
import com.extjs.gxt.ui.client.event.Events;
import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.event.SelectionChangedEvent;
import com.extjs.gxt.ui.client.event.SelectionChangedListener;
import com.extjs.gxt.ui.client.widget.LayoutContainer;
import com.extjs.gxt.ui.client.widget.grid.ColumnConfig;
import com.extjs.gxt.ui.client.widget.grid.ColumnModel;
import com.extjs.gxt.ui.client.widget.grid.filters.GridFilters;
import com.extjs.gxt.ui.client.widget.grid.filters.StringFilter;
import com.extjs.gxt.ui.client.widget.layout.FlowLayout;
import com.google.gwt.user.client.Element;

public class B103 extends MyGeneralForm {

	private final static String PROCESS = "C101";
	private final static String ENTITY = "PersonAddress";

	// Constants
	final Integer PAGE_SIZE = 5;
	final int FORM_WIDTH = 620;
	final int TAB_HEIGHT = 300;
	final int LABEL_WIDTH = 60;

	public B103() {
		super(PROCESS, true);
		setReference(new Reference("perAdd", ENTITY));
	}

	@Override
	protected void onRender(Element parent, int index) {
		super.onRender(parent, index);
		createForm();
	}

	private void createForm() {

		// Form panel
		final MyFormPanel form = new MyFormPanel(this, "Direcciones", FORM_WIDTH);
		form.setLayout(new FlowLayout());

		// Header
		// Person id
		RowContainer row = new RowContainer();
		MyLabel label = new MyLabel("Persona:", LABEL_WIDTH);
		row.add(label);

		ComboForm personIdCombo = new ComboForm(100);
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
		
		form.add(createAddressGrid());
		
		add(form);
	}

	private LayoutContainer createAddressGrid() {

		// Configuration
		final ArrayColumnData cdata = new ArrayColumnData();
		cdata.add(new MyColumnData("pk_addressSequence", "Sec.", 40, 20, false));
		cdata.add(new MyColumnData("addressTypeId", "Tipo", 40, 6, false));
		cdata.add(new MyColumnData("addressDescription", "Descripcion", 100, 50, true));
		cdata.add(new MyColumnData("countryId", "Pais", 50, 50, true));
		cdata.add(new MyColumnData("provinceId", "Provincia", 50, 50, true));
		cdata.add(new MyColumnData("cityId", "Canton", 50, 50, true));
		cdata.add(new MyColumnData("districtId", "Parroquia", 50, 50, true));
		getConfig().setlDataSource(cdata.getDataSources());

		// Column model
		List<ColumnConfig> configs = new ArrayList<ColumnConfig>();
		
		NormalColumn a = new NormalColumn(cdata.get(0));
		a.getEditor().disable();
		configs.add(a);
		
		ComboColumn aTypeComboColumn = new ComboColumn(cdata.get(1));
		Reference refaType = new Reference("adt", "AddressType");
		ArrayColumnData cdataComboAType = new ArrayColumnData();
		cdataComboAType.add(new MyColumnData("adt", "pk_addressTypeId", "ID", 40));
		cdataComboAType.add(new MyColumnData("adt", "name", "Nombre", 150));
		aTypeComboColumn.setQueryData(refaType, cdataComboAType);
		configs.add(aTypeComboColumn);

		configs.add(new NormalColumn(cdata.get(2)));

		final ComboColumn countryCombo = new ComboColumn(cdata.get(3));
		Reference refCountry = new Reference("cou", "Country");
		ArrayColumnData combodata = new ArrayColumnData();
		combodata.add(new MyColumnData("cou", "pk_countryId", "Pais", 70));
		combodata.add(new MyColumnData("cou", "name", "Nombre", 150));
		countryCombo.setQueryData(refCountry, combodata);
		configs.add(countryCombo);

		final ComboColumn provinceCombo = new ComboColumn(cdata.get(4));
		Reference refProvince = new Reference("pro", "Province");
		ArrayColumnData combodata2 = new ArrayColumnData();
		combodata2.add(new MyColumnData("pro", "pk_provinceId", "Provincia", 70));
		combodata2.add(new MyColumnData("pro", "name", "Nombre", 150));
		combodata2.add(new MyColumnData("pro", "pk_countryId", "Provincia", 50));
		provinceCombo.setQueryData(refProvince, combodata2);
		configs.add(provinceCombo);

		final ComboColumn cityCombo = new ComboColumn(cdata.get(5));
		Reference refCity = new Reference("cit", "City");
		final ArrayColumnData combodata3 = new ArrayColumnData();
		combodata3.add(new MyColumnData("cit", "pk_cityId", "Codigo", 70));
		combodata3.add(new MyColumnData("cit", "name", "Nombre", 150));
		combodata3.add(new MyColumnData("cit", "pk_countryId", "Pais", 50));
		combodata3.add(new MyColumnData("cit", "pk_provinceId", "Ciudad", 50));
		cityCombo.setQueryData(refCity, combodata3);
		configs.add(cityCombo);

		final ComboColumn districtCombo = new ComboColumn(cdata.get(6));
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
		EntityContentPanel cp = new EntityContentPanel(FORM_WIDTH - 35, TAB_HEIGHT - 26);
		cp.setHeaderVisible(false);

		// Grid
		EntityEditorGrid addressGrid = new EntityEditorGrid(getStore(), cm);
		addressGrid.setAutoExpandColumn("addressDescription");
		addressGrid.setBorders(true);
		addressGrid.addPlugin(filters);
//		addressGrid.getColumnModel().getColumn(0).getEditor().disable();
//		addressGrid.getColumnModel().getColumn(1).getEditor().disable();
		cp.add(addressGrid);

		addressGrid.addListener(Events.Attach, new Listener<BaseEvent>() {
			public void handleEvent(BaseEvent be) {
				getStore().sort(cdata.getIdFields().get(0), SortDir.ASC);
			}
		});

		// Top tool bar
		GridToolBar toolBar = new GridToolBar(addressGrid, getStore());
		// toolBar.initColumnIndex=2;
		cp.setTopComponent(toolBar);

		// Paging tool bar
		final GridPagingToolBar pagingToolBar = new GridPagingToolBar(addressGrid, PAGE_SIZE);
		cp.setBottomComponent(pagingToolBar);

		return cp;
	}
}