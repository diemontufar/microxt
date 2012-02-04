package mobile.web.webxt.client.devform;

import java.util.ArrayList;
import java.util.List;

import mobile.web.webxt.client.data.MyPagingLoader;
import mobile.web.webxt.client.data.MyProcessConfig;
import mobile.web.webxt.client.data.form.Reference;
import mobile.web.webxt.client.form.EntityContentPanel;
import mobile.web.webxt.client.form.MyGeneralForm;
import mobile.web.webxt.client.form.widgets.ComboForm;
import mobile.web.webxt.client.form.widgetsgrid.ArrayColumnData;
import mobile.web.webxt.client.form.widgetsgrid.EntityEditorGrid;
import mobile.web.webxt.client.form.widgetsgrid.ExpireColumnConfig;
import mobile.web.webxt.client.form.widgetsgrid.GridPagingToolBar;
import mobile.web.webxt.client.form.widgetsgrid.GridToolBar;
import mobile.web.webxt.client.form.widgetsgrid.MyColumnData;
import mobile.web.webxt.client.form.widgetsgrid.NormalColumn;

import com.extjs.gxt.ui.client.data.BaseModelData;
import com.extjs.gxt.ui.client.data.BaseStringFilterConfig;
import com.extjs.gxt.ui.client.data.FilterConfig;
import com.extjs.gxt.ui.client.data.ModelData;
import com.extjs.gxt.ui.client.event.BaseEvent;
import com.extjs.gxt.ui.client.event.Events;
import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.event.SelectionChangedEvent;
import com.extjs.gxt.ui.client.event.SelectionChangedListener;
import com.extjs.gxt.ui.client.store.Store;
import com.extjs.gxt.ui.client.store.StoreEvent;
import com.extjs.gxt.ui.client.store.StoreListener;
import com.extjs.gxt.ui.client.widget.Info;
import com.extjs.gxt.ui.client.widget.form.FormPanel;
import com.extjs.gxt.ui.client.widget.grid.ColumnConfig;
import com.extjs.gxt.ui.client.widget.grid.ColumnModel;
import com.google.gwt.user.client.Element;

public class G303 extends MyGeneralForm {

	private final static String PROCESS = "G303";
	private final static String ENTITY = "City";
	private final Integer PAGE_SIZE = 5;

	public G303() {
		super(PROCESS, true);
		setReference(ENTITY);
	}

	@Override
	protected void onRender(Element parent, int index) {
		super.onRender(parent, index);

		// Configuration
		final ArrayColumnData cdata = new ArrayColumnData();
		cdata.add(new MyColumnData("pk_countryId", "Pais", 70, 2, false));
		cdata.add(new MyColumnData("pk_provinceId", "Prov", 70, 2, false));
		cdata.add(new MyColumnData("pk_cityId", "Codigo", 70, 2, false));
		cdata.add(new MyColumnData("name", "Nombre", 70, 40, false));
		getConfig().setlDataSource(cdata.getDataSources());

		// Columns
		List<ColumnConfig> configs = new ArrayList<ColumnConfig>();

		configs.add(new NormalColumn(cdata.get(0)));
		configs.add(new NormalColumn(cdata.get(1)));
		configs.add(new NormalColumn(cdata.get(2)));
		configs.add(new NormalColumn(cdata.get(3)));
		configs.add(new ExpireColumnConfig());

		ColumnModel cm = new ColumnModel(configs);

		// Grid panel
		EntityContentPanel gridPanel = new EntityContentPanel(450, 230);

		// Grid
		final EntityEditorGrid grid = new EntityEditorGrid(getStore(), cm);
		grid.setAutoExpandColumn("name");
		grid.getColumnModel().getColumn(0).getEditor().disable();
		grid.getColumnModel().getColumn(1).getEditor().disable();
		gridPanel.add(grid);

		// Top tool bar
		ModelData newItem = new BaseModelData();
		newItem.set(cdata.get(0).getId(), null);
		newItem.set(cdata.get(1).getId(), null);
		newItem.set(cdata.get(2).getId(), null);
		newItem.set(cdata.get(3).getId(), null);
		GridToolBar toolBar = new GridToolBar(grid, getStore(), newItem);
		toolBar.initColumnIndex = 2;
		gridPanel.setTopComponent(toolBar);

		// Paging tool bar
		final GridPagingToolBar pagingToolBar = new GridPagingToolBar(grid, PAGE_SIZE);
		gridPanel.setBottomComponent(pagingToolBar);

		// Father panel
		EntityContentPanel panel = new EntityContentPanel("Cantones", 440, 360);

		// Country combo
		final ComboForm countryCombo = new ComboForm("Pais");
		Reference refCountry = new Reference("cou", "Country");
		final ArrayColumnData combodata = new ArrayColumnData();
		combodata.add(new MyColumnData("cou", "pk_countryId", "Pais", 70));
		combodata.add(new MyColumnData("cou", "name", "Nombre", 150));
		countryCombo.setQueryData(refCountry, combodata);
		countryCombo.setDisplayField("name");

		// Province combo
		final ComboForm provinceCombo = new ComboForm("Provincia");
		Reference refProvince = new Reference("prov", "Province");
		final ArrayColumnData combodata2 = new ArrayColumnData();
		combodata2.add(new MyColumnData("prov", "pk_provinceId", "Provincia", 70));
		combodata2.add(new MyColumnData("prov", "name", "Nombre", 150));
		// combodata2.add(new MyColumnData("prov", "pk_countryId", "Pais", 120,
		// ColumnType.HIDDEN));
		provinceCombo.setQueryData(refProvince, combodata2);
		provinceCombo.setDisplayField("name");

		// Combo boxes and table interaction
		countryCombo.addSelectionChangedListener(new SelectionChangedListener<ModelData>() {
			@Override
			public void selectionChanged(SelectionChangedEvent<ModelData> se) {
				String filterField = "pk_countryId";

				FilterConfig filter = new BaseStringFilterConfig();
				filter.setField(filterField);
				filter.setComparison("=");
				filter.setValue(countryCombo.getValue().get(filterField).toString());

				//provinceCombo.addFilter(filter);
				provinceCombo.setLoaded(false);
				provinceCombo.setValue(null);

				getStore().addFilter(filter);
			}
		});

		provinceCombo.addListener(Events.BeforeQuery, new Listener<BaseEvent>() {
			public void handleEvent(BaseEvent be) {
				MyProcessConfig config = (MyProcessConfig) ((MyPagingLoader) provinceCombo.getStore().getLoader())
						.getConfig();
				if (config.getFilterConfigs() == null) {
					be.setCancelled(true);
					Info.display(PROCESS, "Seleccione un Pais");
				}
			}
		});
		provinceCombo.addSelectionChangedListener(new SelectionChangedListener<ModelData>() {
			@Override
			public void selectionChanged(SelectionChangedEvent<ModelData> se) {
				if (provinceCombo.getValue() == null) {
					return;
				}

				String filterField = "pk_provinceId";

				FilterConfig filter = new BaseStringFilterConfig();
				filter.setField(filterField);
				filter.setComparison("=");
				filter.setValue(provinceCombo.getValue().get(filterField).toString());
				getStore().addFilter(filter);

			}
		});

		getStore().addStoreListener(new StoreListener<ModelData>() {
			public void handleEvent(StoreEvent<ModelData> se) {

				String codCountry = countryCombo.getValue().get("pk_countryId").toString();
				String codProvince = provinceCombo.getValue().get("pk_provinceId").toString();

				if (se.getType() == Store.Add) {
					grid.getStore().getAt(getStore().getCount() - 1).set("pk_countryId", codCountry);
					grid.getStore().getAt(getStore().getCount() - 1).set("pk_provinceId", codProvince);
				}
			}
		});

		FormPanel headerPanel = new FormPanel();
		headerPanel.setPadding(10);
		headerPanel.setHeaderVisible(false);
		headerPanel.setBodyBorder(true);
		headerPanel.setFieldWidth(150);
		headerPanel.add(countryCombo);
		headerPanel.add(provinceCombo);

		panel.setTopComponent(headerPanel);
		panel.add(gridPanel);
		add(panel);
	}
}