package mobile.web.webxt.client.devform;

import java.util.ArrayList;
import java.util.List;

import mobile.web.webxt.client.data.form.DataSource;
import mobile.web.webxt.client.data.form.DataSourceType;
import mobile.web.webxt.client.data.form.Reference;
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
import mobile.web.webxt.client.form.widgetsgrid.NormalColumn;

import com.extjs.gxt.ui.client.Style.SortDir;
import com.extjs.gxt.ui.client.data.BaseModelData;
import com.extjs.gxt.ui.client.data.ModelData;
import com.extjs.gxt.ui.client.event.BaseEvent;
import com.extjs.gxt.ui.client.event.Events;
import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.widget.grid.CheckColumnConfig;
import com.extjs.gxt.ui.client.widget.grid.ColumnConfig;
import com.extjs.gxt.ui.client.widget.grid.ColumnModel;
import com.google.gwt.user.client.Element;

/**
 * Processes per role
 */
public class A202 extends MyGeneralForm {

	private final static String PROCESS = "A202";
	private final static String ENTITY = "Role";
	private final Integer PAGE_SIZE = 10;

	public A202() {
		super(PROCESS, true);
		setReference(ENTITY);
	}

	@Override
	protected void onRender(Element parent, int index) {
		super.onRender(parent, index);

		// Grid configuration
		final ArrayColumnData cdata = new ArrayColumnData();
		cdata.add(new MyColumnData("pk_profileId", "Rol", 100, 4, false));
		DataSource ds1 = new DataSource("Process", "url", DataSourceType.DESCRIPTION);
		cdata.add(new MyColumnData(ds1, "Proceso", 100, false));
		cdata.add(new MyColumnData("pk_subsystemId"));
		cdata.add(new MyColumnData("pk_moduleId"));
		cdata.add(new MyColumnData("pk_processId"));
		cdata.add(new MyColumnData("editable", "Editable", 100, 10, false));
		getConfig().setlDataSource(cdata.getDataSources());

		// Column model
		List<ColumnConfig> configs = new ArrayList<ColumnConfig>();

		// Combo profile
		ComboColumn comboProfile = new ComboColumn(cdata.get(0));
		Reference refProfile = new Reference("prof", "Profile");
		ArrayColumnData cdataCombo = new ArrayColumnData();
		cdataCombo.add(new MyColumnData("prof", "pk_profileId", "Rol", 70));
		cdataCombo.add(new MyColumnData("prof", "name", "Nombre", 200));
		comboProfile.setQueryData(refProfile, cdataCombo);
		configs.add(comboProfile);

		// Combo process
		ComboColumn comboProcess = new ComboColumn(cdata.get(1));
		Reference refProcess = new Reference("proc", "Process");
		comboProcess.getComboBox().setPageSize(10);
		cdataCombo = new ArrayColumnData();
		cdataCombo.add(new MyColumnData("proc", "url", "Proceso", 70));
		cdataCombo.add(new MyColumnData("proc", "name", "Nombre	", 200));
		cdataCombo.add(new MyColumnData("proc", "pk_subsystemId"));
		cdataCombo.add(new MyColumnData("proc", "pk_moduleId"));
		cdataCombo.add(new MyColumnData("proc", "pk_processId"));
		comboProcess.setQueryData(refProcess, cdataCombo);
		comboProcess.getComboBox().setPageSize(5);
		comboProcess.getComboBox().addFilter("enable", "1");
		comboProcess.getComboBox().addFilter("menu", "1");
		configs.add(comboProcess);

		NormalColumn subCol = new NormalColumn(cdata.get(2));
		configs.add(subCol);
		NormalColumn modCol = new NormalColumn(cdata.get(3));
		configs.add(modCol);
		NormalColumn proCol = new NormalColumn(cdata.get(4));
		configs.add(proCol);

		comboProcess.linkWithColumn("pk_subsystemId", subCol);
		comboProcess.linkWithColumn("pk_moduleId", modCol);
		comboProcess.linkWithColumn("pk_processId", proCol);

		CheckColumnConfig a = new CheckColumn(cdata.get(5));
		configs.add(a);

		configs.add(new ExpireColumnConfig());

		ColumnModel cm = new ColumnModel(configs);

		// Content panel
		EntityContentPanel cp = new EntityContentPanel("Procesos por rol", 400, 340);

		// Grid
		final EntityEditorGrid grid = new EntityEditorGrid(getStore(), cm);
		grid.setAutoExpandColumn("pk_profileId");
		cp.add(grid);
		grid.addListener(Events.Attach, new Listener<BaseEvent>() {
			public void handleEvent(BaseEvent be) {
				getStore().sort(cdata.getIdFields().get(0), SortDir.ASC);
			}
		});

		// Top tool bar
		ModelData initModel = new BaseModelData();
		initModel.set(cdata.get(0).getId(), null);
		initModel.set(cdata.get(1).getId(), null);
		initModel.set(cdata.get(2).getId(), null);
		initModel.set(cdata.get(3).getId(), null);
		initModel.set(cdata.get(4).getId(), null);
		initModel.set(cdata.get(5).getId(), true);
		GridToolBar toolBar = new GridToolBar(grid, getStore(), initModel);
		cp.setTopComponent(toolBar);

		// Paging tool bar
		final GridPagingToolBar pagingToolBar = new GridPagingToolBar(grid, PAGE_SIZE);
		cp.setBottomComponent(pagingToolBar);

		add(cp);
	}
}