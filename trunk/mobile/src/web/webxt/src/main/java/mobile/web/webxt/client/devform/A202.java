package mobile.web.webxt.client.devform;

import java.util.ArrayList;
import java.util.List;

import mobile.web.webxt.client.data.MyHttpProxy;
import mobile.web.webxt.client.data.MyListStore;
import mobile.web.webxt.client.data.MyPagingLoader;
import mobile.web.webxt.client.data.MyProcessConfig;
import mobile.web.webxt.client.form.EntityContentPanel;
import mobile.web.webxt.client.form.MyGeneralForm;
import mobile.web.webxt.client.form.widgetsgrid.ArrayColumnData;
import mobile.web.webxt.client.form.widgetsgrid.CheckColumn;
import mobile.web.webxt.client.form.widgetsgrid.ComboColumn;
import mobile.web.webxt.client.form.widgetsgrid.EntityEditorGrid;
import mobile.web.webxt.client.form.widgetsgrid.ExpireColumnConfig;
import mobile.web.webxt.client.form.widgetsgrid.GridPagingToolBar;
import mobile.web.webxt.client.form.widgetsgrid.GridToolBar;
import mobile.web.webxt.client.form.widgetsgrid.MyColumnData;
import mobile.web.webxt.client.form.widgetsgrid.MyColumnData.ColumnType;
import mobile.web.webxt.client.form.widgetsgrid.NormalColumn;
import mobile.web.webxt.client.form.widgetsgrid.SpecialComboColumn;

import com.extjs.gxt.ui.client.Style.SortDir;
import com.extjs.gxt.ui.client.event.BaseEvent;
import com.extjs.gxt.ui.client.event.Events;
import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.widget.grid.CheckColumnConfig;
import com.extjs.gxt.ui.client.widget.grid.ColumnConfig;
import com.extjs.gxt.ui.client.widget.grid.ColumnModel;
import com.google.gwt.user.client.Element;

/**
 * Processes by role
 */
public class A202 extends MyGeneralForm {

	private final String PROCESS = "A202";
	private final String ENTITY = "Role";
	private final Integer PAGE_SIZE = 10;

	@Override
	protected void onRender(Element parent, int index) {
		super.onRender(parent, index);

		// Process configuration
		final ArrayColumnData cdata = new ArrayColumnData();
		cdata.add(new MyColumnData("pk_profileId", "Rol", 70, 4, false));
		MyColumnData procDesc = new MyColumnData("process_url", "Proceso", 70,
				4, false, ColumnType.DESC);
		procDesc.setDescriptionFields("Process", "url",
				"pk_subsystemId:pk_moduleId:pk_processId");
		cdata.add(procDesc);
		// cdata.add(new MyColumnData("pk_subsystemId", "Sub", 30, 2, false));
		// cdata.add(new MyColumnData("pk_moduleId", "Mod", 30, 2, false));
		// cdata.add(new MyColumnData("pk_processId", "Pro", 30, 2, false));
		cdata.add(new MyColumnData("pk_subsystemId", ColumnType.HIDDEN));
		cdata.add(new MyColumnData("pk_moduleId", ColumnType.HIDDEN));
		cdata.add(new MyColumnData("pk_processId", ColumnType.HIDDEN));
		cdata.add(new MyColumnData("pk_dayId", "Día", 50, 3, false));
		cdata.add(new MyColumnData("hourFrom", "Hora desde", 100, 150, true));
		cdata.add(new MyColumnData("hourTo", "Hora hasta", 100, 150, true));
		cdata.add(new MyColumnData("editable", "Editable", 60, 10, false));

		MyProcessConfig config = new MyProcessConfig(PROCESS, ENTITY,
				cdata.getRqFields());

		// Proxy - loader - store
		MyHttpProxy proxy = new MyHttpProxy();
		final MyPagingLoader loader = new MyPagingLoader(proxy, config);
		final MyListStore store = new MyListStore(loader);

		// Column model
		List<ColumnConfig> configs = new ArrayList<ColumnConfig>();

		// Combo profile
		ComboColumn comboCol = new ComboColumn(cdata.get(0));
		ArrayColumnData cdataCombo = new ArrayColumnData();
		cdataCombo.add(new MyColumnData("pk_profileId", "Rol", 70));
		cdataCombo.add(new MyColumnData("name", "Nombre", 200));
		comboCol.setRqData("Profile", cdataCombo);
		configs.add(comboCol);

		// Combo process
		SpecialComboColumn spcComboCol = new SpecialComboColumn(cdata.get(1));
		spcComboCol.setPageSize(10);
		cdataCombo = new ArrayColumnData();
		cdataCombo.add(new MyColumnData("url", "Proceso", 70));
		cdataCombo.add(new MyColumnData("name", "Nombre	", 200));
		MyColumnData hidden = new MyColumnData("pk_subsystemId",
				ColumnType.HIDDEN);
		hidden.setAssociatedField("pk_subsystemId");
		cdataCombo.add(hidden);
		hidden = new MyColumnData("pk_moduleId", ColumnType.HIDDEN);
		hidden.setAssociatedField("pk_moduleId");
		cdataCombo.add(hidden);
		hidden = new MyColumnData("pk_processId", ColumnType.HIDDEN);
		hidden.setAssociatedField("pk_processId");
		cdataCombo.add(hidden);
		spcComboCol.setRqData("Process", cdataCombo);
		spcComboCol.setFilter("enable", "1");
		spcComboCol.setFilter("menu", "1");
		configs.add(spcComboCol);

		configs.add(new NormalColumn(cdata.get(2)));
		configs.add(new NormalColumn(cdata.get(3)));
		configs.add(new NormalColumn(cdata.get(4)));

		configs.add(new NormalColumn(cdata.get(5)));
		configs.add(new NormalColumn(cdata.get(6)));
		configs.add(new NormalColumn(cdata.get(7)));
		
		CheckColumnConfig a = new CheckColumn(cdata.get(8)); 
		configs.add(a);

		configs.add(new ExpireColumnConfig());

		ColumnModel cm = new ColumnModel(configs);

		// Content panel
		EntityContentPanel cp = new EntityContentPanel("Procesos por rol", 500,
				340);

		// Grid
		final EntityEditorGrid grid = new EntityEditorGrid(store, cm);
		grid.setAutoExpandColumn("pk_profileId");
		cp.add(grid);
		grid.addListener(Events.Attach, new Listener<BaseEvent>() {
			public void handleEvent(BaseEvent be) {
				store.sort(cdata.getIdFields().get(0), SortDir.ASC);
			}
		});
		
		// Top tool bar
		GridToolBar toolBar = new GridToolBar(grid, store);
		cp.setTopComponent(toolBar);

		// Paging tool bar
		final GridPagingToolBar pagingToolBar = new GridPagingToolBar(
				PAGE_SIZE, loader);
		cp.setBottomComponent(pagingToolBar);

		add(cp);
	}
}