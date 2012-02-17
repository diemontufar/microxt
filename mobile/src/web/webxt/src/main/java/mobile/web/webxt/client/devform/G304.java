package mobile.web.webxt.client.devform;

import java.util.ArrayList;
import java.util.List;

import mobile.web.webxt.client.data.form.DataSource;
import mobile.web.webxt.client.data.form.DataSourceType;
import mobile.web.webxt.client.data.form.Reference;
import mobile.web.webxt.client.form.EntityContentPanel;
import mobile.web.webxt.client.form.MyFormPanel;
import mobile.web.webxt.client.form.MyGeneralForm;
import mobile.web.webxt.client.form.validations.Validate;
import mobile.web.webxt.client.form.widgets.ComboForm;
import mobile.web.webxt.client.form.widgets.InputBox;
import mobile.web.webxt.client.form.widgets.MyLabel;
import mobile.web.webxt.client.form.widgets.RowContainer;
import mobile.web.webxt.client.form.widgetsgrid.ArrayColumnData;
import mobile.web.webxt.client.form.widgetsgrid.EntityEditorGrid;
import mobile.web.webxt.client.form.widgetsgrid.ExpireColumnConfig;
import mobile.web.webxt.client.form.widgetsgrid.GridPagingToolBar;
import mobile.web.webxt.client.form.widgetsgrid.GridToolBar;
import mobile.web.webxt.client.form.widgetsgrid.MyColumnData;
import mobile.web.webxt.client.form.widgetsgrid.NormalColumn;
import mobile.web.webxt.client.util.TextType;

import com.extjs.gxt.ui.client.Style.HorizontalAlignment;
import com.extjs.gxt.ui.client.data.BaseModelData;
import com.extjs.gxt.ui.client.data.ModelData;
import com.extjs.gxt.ui.client.event.SelectionChangedEvent;
import com.extjs.gxt.ui.client.event.SelectionChangedListener;
import com.extjs.gxt.ui.client.widget.grid.ColumnConfig;
import com.extjs.gxt.ui.client.widget.grid.ColumnModel;
import com.extjs.gxt.ui.client.widget.layout.FlowLayout;
import com.google.gwt.user.client.Element;

public class G304 extends MyGeneralForm {

	private final static String PROCESS = "G304";
	private final static String ENTITY = "District";
	private final Integer PAGE_SIZE = 5;

	public G304() {
		super(PROCESS, true);
		setReference(ENTITY);
	}

	@Override
	protected void onRender(Element parent, int index) {
		super.onRender(parent, index);
		
		// Constants
		final int FORM_WIDTH = 430;
		final int LABEL_WIDTH = 50;
		
		// Super Form
		final MyFormPanel form = new MyFormPanel(this, "Parroquias", FORM_WIDTH);
		form.setLayout(new FlowLayout());
		form.setButtonAlign(HorizontalAlignment.CENTER);
		
		// Header
		// Filter: country
		RowContainer row = new RowContainer();

		MyLabel label = new MyLabel("Pais:", LABEL_WIDTH);
		row.add(label);
		
		// Country combo
		final ComboForm countryCombo = new ComboForm(60);
		countryCombo.setDataSource(new DataSource("pk_countryId", DataSourceType.CRITERION));
				
		Reference refCountry = new Reference("cou", "Country");
		final ArrayColumnData combodata = new ArrayColumnData();
		combodata.add(new MyColumnData("cou", "pk_countryId", "Codigo", 70));
		combodata.add(new MyColumnData("cou", "name", "Nombre", 150));
		countryCombo.setQueryData(refCountry, combodata);
		
		// Country description
		final InputBox countryName = new InputBox(150);
		countryName.setDataSource(new DataSource("Country", "name", DataSourceType.CRITERION_DESCRIPTION));
		countryName.setReadOnly(true);

		countryCombo.linkWithField(countryName, "name");

		row.add(countryCombo);
		row.add(countryName);
		
		form.add(row);
		
		// Filter: province
		row = new RowContainer();

		label = new MyLabel("Provincia:", LABEL_WIDTH);
		row.add(label);
		
		// Province combo
		final ComboForm provinceCombo = new ComboForm(60);
		provinceCombo.setDataSource(new DataSource("pk_provinceId", DataSourceType.CRITERION));
				
		Reference refProvince = new Reference("pro", "Province");
		final ArrayColumnData combodata2 = new ArrayColumnData();
		combodata2.add(new MyColumnData("pro", "pk_provinceId", "Codigo", 70));
		combodata2.add(new MyColumnData("pro", "name", "Nombre", 150));
		provinceCombo.setQueryData(refProvince, combodata2);
		
		// Country description
		final InputBox provinceName = new InputBox(150);
		provinceName.setDataSource(new DataSource("Province", "name", DataSourceType.CRITERION_DESCRIPTION));
		provinceName.setReadOnly(true);

		provinceCombo.linkWithField(provinceName, "name");
		
		provinceCombo.addDependency(countryCombo, "pk_countryId");

		row.add(provinceCombo);
		row.add(provinceName);
		
		form.add(row);
		
		// Filter: City
		row = new RowContainer();

		label = new MyLabel("Cantón:", LABEL_WIDTH);
		row.add(label);
		
		// City combo
		final ComboForm cityCombo = new ComboForm(60);
		cityCombo.setDataSource(new DataSource("pk_cityId", DataSourceType.CRITERION));
				
		Reference refCity = new Reference("cit", "City");
		final ArrayColumnData combodata3 = new ArrayColumnData();
		combodata3.add(new MyColumnData("cit", "pk_cityId", "Codigo", 70));
		combodata3.add(new MyColumnData("cit", "name", "Nombre", 150));
		cityCombo.setQueryData(refCity, combodata3);
		
		// Country description
		final InputBox cityName = new InputBox(150);
		cityName.setDataSource(new DataSource("City", "name", DataSourceType.CRITERION_DESCRIPTION));
		cityName.setReadOnly(true);

		cityCombo.linkWithField(cityName, "name");
		
		cityCombo.addDependency(provinceCombo, "pk_provinceId");

		row.add(cityCombo);
		row.add(cityName);
		
		form.add(row);	
		

		// Configuration
		final ArrayColumnData cdata = new ArrayColumnData();
		cdata.add(new MyColumnData("pk_countryId", "Pais", 50, 2, false));
		cdata.add(new MyColumnData("pk_provinceId", "Prov", 50, 2, false));
		cdata.add(new MyColumnData("pk_cityId", "Canton", 50, 2, false));
		cdata.add(new MyColumnData("pk_districtId", "Codigo", 50, 2, false));
		cdata.add(new MyColumnData("name", "Nombre", 80, 40, false));
		getConfig().setlDataSource(cdata.getDataSources());

		// Columns
		List<ColumnConfig> configs = new ArrayList<ColumnConfig>();

		configs.add(new NormalColumn(cdata.get(0),TextType.TEXT,Validate.TEXT));
		configs.add(new NormalColumn(cdata.get(1),TextType.TEXT,Validate.TEXT));
		configs.add(new NormalColumn(cdata.get(2),TextType.TEXT,Validate.TEXT));
		configs.add(new NormalColumn(cdata.get(3),TextType.TEXT,Validate.TEXT));
		configs.add(new NormalColumn(cdata.get(4),TextType.TEXT,Validate.TEXT));
		configs.add(new ExpireColumnConfig());

		ColumnModel cm = new ColumnModel(configs);

		// Grid panel
		EntityContentPanel gridPanel = new EntityContentPanel(400, 230);

		// Grid
		final EntityEditorGrid grid = new EntityEditorGrid(getStore(), cm);
		grid.setAutoExpandColumn("name");
		grid.setBorders(true);
		grid.addDependency(countryCombo);
		grid.addDependency(provinceCombo);
		grid.addDependency(cityCombo);
		
		gridPanel.add(grid);
		
		// Top tool bar
		ModelData newItem = new BaseModelData();
		newItem.set(cdata.get(0).getId(), null);
		newItem.set(cdata.get(1).getId(), null);
		newItem.set(cdata.get(2).getId(), null);
		newItem.set(cdata.get(3).getId(), null);
		newItem.set(cdata.get(4).getId(), null);

		GridToolBar toolBar = new GridToolBar(grid, getStore(), newItem);
		gridPanel.setTopComponent(toolBar);

		// Paging tool bar
		final GridPagingToolBar pagingToolBar = new GridPagingToolBar(grid, PAGE_SIZE);
		gridPanel.setBottomComponent(pagingToolBar);

		// Operations
		cityCombo.addSelectionChangedListener(new SelectionChangedListener<ModelData>() {
			@Override
			public void selectionChanged(SelectionChangedEvent<ModelData> se) {
				if (((ComboForm) se.getSource()).isSomeSelected()) {
					pagingToolBar.refresh();
				}else{
					grid.getStore().removeAll();
				}
			}
		});
	
		form.add(gridPanel);
		add(form);
	}
}