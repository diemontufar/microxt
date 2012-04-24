package mobile.web.webxt.client.devform;

import java.util.ArrayList;
import java.util.List;

import mobile.web.webxt.client.data.form.DataSourceType;
import mobile.web.webxt.client.data.form.Reference;
import mobile.web.webxt.client.form.EntityContentPanel;
import mobile.web.webxt.client.form.MyFormPanel;
import mobile.web.webxt.client.form.MyGeneralForm;
import mobile.web.webxt.client.form.widgetsgrid.ArrayColumnData;
import mobile.web.webxt.client.form.widgetsgrid.ComboColumn;
import mobile.web.webxt.client.form.widgetsgrid.EntityEditorGrid;
import mobile.web.webxt.client.form.widgetsgrid.ExpireColumnConfig;
import mobile.web.webxt.client.form.widgetsgrid.GridPagingToolBar;
import mobile.web.webxt.client.form.widgetsgrid.GridToolBar;
import mobile.web.webxt.client.form.widgetsgrid.MyColumnData;
import mobile.web.webxt.client.form.widgetsgrid.NormalColumn;
import mobile.web.webxt.client.form.widgetsgrid.NumericColumn;

import com.extjs.gxt.ui.client.data.ModelData;
import com.extjs.gxt.ui.client.event.SelectionChangedEvent;
import com.extjs.gxt.ui.client.event.SelectionChangedListener;
import com.extjs.gxt.ui.client.widget.grid.ColumnConfig;
import com.extjs.gxt.ui.client.widget.grid.ColumnModel;
import com.extjs.gxt.ui.client.widget.layout.FlowLayout;
import com.google.gwt.user.client.Element;

public class B102 extends MyGeneralForm {

	private final static String PROCESS = "B102";
	private final static String ENTITY = "PersonAddress";

	final Integer PAGE_SIZE = 10;

	public B102() {
		super(PROCESS, true);
		setReference(new Reference("perAdd", ENTITY));
	}

	@Override
	protected void onRender(Element parent, int index) {
		super.onRender(parent, index);

		// Constants
		final int FORM_WIDTH = 620;
		final int GRID_HEIGHT = 250;

		// Form panel
		final MyFormPanel form = new MyFormPanel(this, "Direcciones", FORM_WIDTH);
		form.setLayout(new FlowLayout());

		// Header
		PersonHeader header = new PersonHeader("perAdd", "pk_personId", DataSourceType.CRITERION);
		form.add(header);

		// Configuration
		final ArrayColumnData cdata = new ArrayColumnData();
		cdata.add(new MyColumnData("pk_addressSequence", "Sec.", 50, 3, false));
		cdata.add(new MyColumnData("addressTypeId", "Tipo", 60, 6, false));
		cdata.add(new MyColumnData("addressDescription", "Descripcion", 100, 50, true));
		cdata.add(new MyColumnData("countryId", "Pais", 50, 50, true));
		cdata.add(new MyColumnData("provinceId", "Provincia", 50, 50, true));
		cdata.add(new MyColumnData("cityId", "Canton", 50, 50, true));
		cdata.add(new MyColumnData("districtId", "Parroquia", 50, 50, true));
		getConfig().setlDataSource(cdata.getDataSources());

		// Column model
		List<ColumnConfig> configs = new ArrayList<ColumnConfig>();

		NumericColumn a = new NumericColumn(cdata.get(0));
		// a.getEditor().disable();
		configs.add(a);

		ComboColumn aTypeComboColumn = new ComboColumn(cdata.get(1));
		Reference refaType = new Reference("adt", "AddressType");
		ArrayColumnData cdataComboAType = new ArrayColumnData();
		cdataComboAType.add(new MyColumnData("adt", "pk_addressTypeId", "Id", 40));
		cdataComboAType.add(new MyColumnData("adt", "name", "Nombre", 150));
		aTypeComboColumn.setQueryData(refaType, cdataComboAType);
		configs.add(aTypeComboColumn);

		configs.add(new NormalColumn(cdata.get(2)));

		final ComboColumn countryCombo = new ComboColumn(cdata.get(3));
		Reference refCountry = new Reference("cou", "Country");
		ArrayColumnData combodata = new ArrayColumnData();
		combodata.add(new MyColumnData("cou", "pk_countryId", "Id", 50));
		combodata.add(new MyColumnData("cou", "name", "Nombre", 150));
		countryCombo.setQueryData(refCountry, combodata);
		configs.add(countryCombo);

		final ComboColumn provinceCombo = new ComboColumn(cdata.get(4));
		Reference refProvince = new Reference("pro", "Province");
		ArrayColumnData combodata2 = new ArrayColumnData();
		combodata2.add(new MyColumnData("pro", "pk_provinceId", "Id", 50));
		combodata2.add(new MyColumnData("pro", "name", "Nombre", 150));
		provinceCombo.setQueryData(refProvince, combodata2);
		provinceCombo.addDependency(countryCombo, "pk_countryId");
		configs.add(provinceCombo);

		final ComboColumn cityCombo = new ComboColumn(cdata.get(5));
		Reference refCity = new Reference("cit", "City");
		final ArrayColumnData combodata3 = new ArrayColumnData();
		combodata3.add(new MyColumnData("cit", "pk_cityId", "Id", 50));
		combodata3.add(new MyColumnData("cit", "name", "Nombre", 150));
		cityCombo.setQueryData(refCity, combodata3);
		cityCombo.addDependency(provinceCombo, "pk_provinceId");
		configs.add(cityCombo);

		final ComboColumn districtCombo = new ComboColumn(cdata.get(6));
		Reference refDistrict = new Reference("dis", "District");
		final ArrayColumnData combodata4 = new ArrayColumnData();
		combodata4.add(new MyColumnData("dis", "pk_districtId", "Id", 50));
		combodata4.add(new MyColumnData("dis", "name", "Nombre", 150));
		districtCombo.setQueryData(refDistrict, combodata4);
		districtCombo.addDependency(cityCombo, "pk_cityId");
		configs.add(districtCombo);

		configs.add(new ExpireColumnConfig());

		ColumnModel cm = new ColumnModel(configs);

		// Content panel
		EntityContentPanel cp = new EntityContentPanel(FORM_WIDTH - 35, GRID_HEIGHT);

		// Grid
		final EntityEditorGrid addressGrid = new EntityEditorGrid(getStore(), cm);
		addressGrid.setBorders(true);
		addressGrid.setAutoExpandColumn("addressDescription");
		addressGrid.addDependency(header.getPersonCombo());
		cp.add(addressGrid);

		// Top tool bar
		GridToolBar toolBar = new GridToolBar(addressGrid, getStore());
		// toolBar.initColumnIndex=2;
		cp.setTopComponent(toolBar);

		// Paging tool bar
		final GridPagingToolBar pagingToolBar = new GridPagingToolBar(addressGrid, PAGE_SIZE);
		cp.setBottomComponent(pagingToolBar);

		// Events
		header.getPersonCombo().addSelectionChangedListener(new SelectionChangedListener<ModelData>() {
			@Override
			public void selectionChanged(SelectionChangedEvent<ModelData> se) {
				pagingToolBar.refresh();
			}
		});

		form.add(cp);

		add(form);
	}
}