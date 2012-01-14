package mobile.web.webxt.client.devform;

import java.util.ArrayList;
import java.util.List;

import mobile.web.webxt.client.data.MyHttpProxy;
import mobile.web.webxt.client.data.MyListStore;
import mobile.web.webxt.client.data.MyPagingLoader;
import mobile.web.webxt.client.data.MyProcessConfig;
import mobile.web.webxt.client.form.EntityContentPanel;
import mobile.web.webxt.client.form.MyGeneralPanel;
import mobile.web.webxt.client.form.widgets.ComboForm;
import mobile.web.webxt.client.form.widgetsgrid.ArrayColumnData;
import mobile.web.webxt.client.form.widgetsgrid.CheckColumn;
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
import com.extjs.gxt.ui.client.event.GridEvent;
import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.event.SelectionChangedEvent;
import com.extjs.gxt.ui.client.event.SelectionChangedListener;
import com.extjs.gxt.ui.client.store.Record;
import com.extjs.gxt.ui.client.widget.Info;
import com.extjs.gxt.ui.client.widget.form.FormPanel;
import com.extjs.gxt.ui.client.widget.grid.ColumnConfig;
import com.extjs.gxt.ui.client.widget.grid.ColumnModel;
import com.google.gwt.user.client.Element;

public class A105 extends MyGeneralPanel {

	private final String PROCESS = "A105";

	private final String ENTITY = "Process";

	private final Integer PAGE_SIZE = 5;

	@Override
	protected void onRender(Element parent, int index) {
		super.onRender(parent, index);
		
		// Configuration
		final ArrayColumnData cdata = new ArrayColumnData();
		cdata.add(new MyColumnData("pk_processId", "Proc", 70, 2, false));
		cdata.add(new MyColumnData("name", "Nombre", 130, 40, false));
		cdata.add(new MyColumnData("url", "Url", 60, 4, false));
		cdata.add(new MyColumnData("enable", "Activo", 60, 4, false));
		cdata.add(new MyColumnData("menu", "Menu", 60, 4, false));

		MyProcessConfig config = new MyProcessConfig(PROCESS, ENTITY,
				cdata.getIdFields());

		// Proxy - loader - store
		MyHttpProxy proxy = new MyHttpProxy();
		final MyPagingLoader loader = new MyPagingLoader(proxy, config);
		final MyListStore store = new MyListStore(loader);

		// Columns
		List<ColumnConfig> configs = new ArrayList<ColumnConfig>();

		configs.add(new NormalColumn(cdata.get(0)));
		configs.add(new NormalColumn(cdata.get(1)));
		NormalColumn urlColumn = new NormalColumn(cdata.get(2));
		urlColumn.setEditor(null);
		configs.add(urlColumn);
		configs.add(new CheckColumn(cdata.get(3)));
		configs.add(new CheckColumn(cdata.get(4)));
		configs.add(new ExpireColumnConfig());

		ColumnModel cm = new ColumnModel(configs);

		// Grid panel
		EntityContentPanel gridPanel = new EntityContentPanel(400, 230);

		// Grid
		final EntityEditorGrid grid = new EntityEditorGrid(store, cm);
		grid.setAutoExpandColumn("name");
		gridPanel.add(grid);

		// Top tool bar
		ModelData newItem = new BaseModelData();
		newItem.set(cdata.get(0).getId(),null);
		newItem.set(cdata.get(1).getId(),null);
		newItem.set(cdata.get(2).getId(),null);
		newItem.set(cdata.get(3).getId(),true);
		newItem.set(cdata.get(4).getId(),true);
				
		GridToolBar toolBar = new GridToolBar(grid, store, newItem);
		gridPanel.setTopComponent(toolBar);

		// Paging tool bar
		final GridPagingToolBar pagingToolBar = new GridPagingToolBar(
				PAGE_SIZE, loader);
		gridPanel.setBottomComponent(pagingToolBar);
		pagingToolBar.setWaitingFilter(true);
		pagingToolBar.setMessage("Seleccione un Subsistema y un Modulo");

		// Father panel
		EntityContentPanel panel = new EntityContentPanel("Procesos", 440, 360);

		// Subsystem combo
		final ComboForm subCombo = new ComboForm("Subsistema", "name");
		final ArrayColumnData combodata = new ArrayColumnData();
		combodata.add(new MyColumnData("pk_subsystemId", "Sub", 70));
		combodata.add(new MyColumnData("name", "Nombre", 150));
		subCombo.setRqData("Subsystem", combodata);

		// Module combo
		final ComboForm modCombo = new ComboForm("Modulo", "name");
		final ArrayColumnData combodata2 = new ArrayColumnData();
		combodata2.add(new MyColumnData("pk_moduleId", "Mod", 70));
		combodata2.add(new MyColumnData("name", "Nombre", 150));
		modCombo.setRqData("Module", combodata2);

		// Combo boxes and table interaction
		subCombo.addSelectionChangedListener(new SelectionChangedListener<ModelData>() {
			@Override
			public void selectionChanged(SelectionChangedEvent<ModelData> se) {
				String filterField = "pk_subsystemId";

				FilterConfig filter = new BaseStringFilterConfig();
				filter.setField(filterField);
				filter.setComparison("=");
				filter.setValue(subCombo.getValue().get(filterField).toString());

				modCombo.addFilter(filter);
				modCombo.setLoaded(false);
				modCombo.setValue(null);

				store.addFilter(filter);
			}
		});
		modCombo.addListener(Events.BeforeQuery, new Listener<BaseEvent>() {
			public void handleEvent(BaseEvent be) {
				MyProcessConfig config = (MyProcessConfig) ((MyPagingLoader) modCombo
						.getStore().getLoader()).getConfig();
				if (config.getFilterConfigs() == null) {
					be.setCancelled(true);
					Info.display(PROCESS, "Seleccione un subsistema");
				}
			}
		});
		modCombo.addSelectionChangedListener(new SelectionChangedListener<ModelData>() {
			@Override
			public void selectionChanged(SelectionChangedEvent<ModelData> se) {
				if (modCombo.getValue() == null) {
					return;
				}

				String filterField = "pk_moduleId";

				FilterConfig filter = new BaseStringFilterConfig();
				filter.setField(filterField);
				filter.setComparison("=");
				filter.setValue(modCombo.getValue().get(filterField).toString());
				store.addFilter(filter);

				pagingToolBar.setWaitingFilter(false);
			}
		});

		grid.addListener(Events.AfterEdit, new Listener<BaseEvent>() {
			public void handleEvent(BaseEvent be) {
				@SuppressWarnings("unchecked")
				GridEvent<ModelData> ge = (GridEvent<ModelData>) be;
				Record rec = ge.getRecord();
				String prop = ge.getProperty();
				String val = (String) ge.getValue();
				System.out.println(rec + " " + prop + " " + val);
				if (prop.compareTo("pk_processId") == 0) {
					String sub = subCombo.getValue().get("pk_subsystemId");
					String mod = modCombo.getValue().get("pk_moduleId");
					rec.set("url", sub + mod + val);
				}
			}
		});

		FormPanel headerPanel = new FormPanel();
		headerPanel.setPadding(10);
		headerPanel.setHeaderVisible(false);
		headerPanel.setBodyBorder(true);
		headerPanel.setFieldWidth(150);
		headerPanel.add(subCombo);
		headerPanel.add(modCombo);

		panel.setTopComponent(headerPanel);
		panel.add(gridPanel);
		add(panel);
	}
}