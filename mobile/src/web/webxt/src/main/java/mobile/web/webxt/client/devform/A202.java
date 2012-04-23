package mobile.web.webxt.client.devform;

import java.util.ArrayList;
import java.util.List;

import mobile.web.webxt.client.data.MyPagingLoader;
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
import mobile.web.webxt.client.form.widgetsgrid.CheckColumn;
import mobile.web.webxt.client.form.widgetsgrid.ComboColumn;
import mobile.web.webxt.client.form.widgetsgrid.EntityEditorGrid;
import mobile.web.webxt.client.form.widgetsgrid.ExpireColumnConfig;
import mobile.web.webxt.client.form.widgetsgrid.GridPagingToolBar;
import mobile.web.webxt.client.form.widgetsgrid.GridToolBar;
import mobile.web.webxt.client.form.widgetsgrid.MyColumnData;
import mobile.web.webxt.client.form.widgetsgrid.MyGridFilters;
import mobile.web.webxt.client.form.widgetsgrid.NormalColumn;

import com.extjs.gxt.ui.client.Style.HorizontalAlignment;
import com.extjs.gxt.ui.client.data.BaseModelData;
import com.extjs.gxt.ui.client.data.BaseStringFilterConfig;
import com.extjs.gxt.ui.client.data.FilterConfig;
import com.extjs.gxt.ui.client.data.ModelData;
import com.extjs.gxt.ui.client.event.BaseEvent;
import com.extjs.gxt.ui.client.event.Events;
import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.event.SelectionChangedEvent;
import com.extjs.gxt.ui.client.event.SelectionChangedListener;
import com.extjs.gxt.ui.client.widget.grid.CheckColumnConfig;
import com.extjs.gxt.ui.client.widget.grid.ColumnConfig;
import com.extjs.gxt.ui.client.widget.grid.ColumnModel;
import com.extjs.gxt.ui.client.widget.grid.filters.StringFilter;
import com.extjs.gxt.ui.client.widget.layout.FlowLayout;
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

		// Constants
		final int FORM_WIDTH = 540;
		final int LABEL_WIDTH = 30;

		// Super Form
		final MyFormPanel form = new MyFormPanel(this, "Procesos por rol", FORM_WIDTH);
		form.setLayout(new FlowLayout());
		form.setButtonAlign(HorizontalAlignment.CENTER);

		// Header
		// Filter: subsystem
		RowContainer row = new RowContainer();
		row.setStyleAttribute("margin-bottom", "10px");

		MyLabel label = new MyLabel("Rol:", LABEL_WIDTH);
		row.add(label);

		// Profile combo
		final ComboForm profileId = new ComboForm(60);
		profileId.setDataSource(new DataSource("pk_profileId", DataSourceType.CRITERION));

		Reference refProfile0 = new Reference("pro", "Profile");
		final ArrayColumnData profileCdata = new ArrayColumnData();
		profileCdata.add(new MyColumnData("pro", "pk_profileId", "Id", 80));
		profileCdata.add(new MyColumnData("pro", "name", "Nombre", 150));
		profileId.setQueryData(refProfile0, profileCdata);
		profileId.setDisplayField("pk_profileId");

		// Profile description
		final InputBox profileName = new InputBox(140);
		profileName.setDataSource(new DataSource("Profile", "name", DataSourceType.CRITERION_DESCRIPTION));
		profileName.setReadOnly(true);

		profileId.linkWithField(profileName, "name");

		row.add(profileId);
		row.add(profileName);

		form.add(row);

		// Main grid
		// Grid configuration
		final ArrayColumnData cdata = new ArrayColumnData();
		DataSource ds1 = new DataSource("Process", "url", DataSourceType.DESCRIPTION);
		cdata.add(new MyColumnData(ds1, "Proceso", 100, false));
		cdata.add(new MyColumnData("pk_subsystemId"));
		cdata.add(new MyColumnData("pk_moduleId"));
		cdata.add(new MyColumnData("pk_processId"));
		ds1 = new DataSource("Process", "name", DataSourceType.DESCRIPTION);
		cdata.add(new MyColumnData(ds1, "Descripción", 250, false));
		cdata.add(new MyColumnData("editable", "Editable", 100, 10, false));
		getConfig().setlDataSource(cdata.getDataSources());

		// Column model
		List<ColumnConfig> configs = new ArrayList<ColumnConfig>();

		// Combo process
		ComboColumn comboProcess = new ComboColumn(cdata.get(0));
		Reference refProcess = new Reference("proc", "Process");
		ArrayColumnData cdataCombo = new ArrayColumnData();
		cdataCombo.add(new MyColumnData("proc", "url", "Proceso", 70));
		cdataCombo.add(new MyColumnData("proc", "name", "Nombre	", 200));
		cdataCombo.add(new MyColumnData("proc", "pk_subsystemId"));
		cdataCombo.add(new MyColumnData("proc", "pk_moduleId"));
		cdataCombo.add(new MyColumnData("proc", "pk_processId"));
		comboProcess.setQueryData(refProcess, cdataCombo);
		comboProcess.getComboBox().setPageSize(10);
		
//		FilterConfig filter = new BaseStringFilterConfig();
//		filter.setField("enable");
//		filter.setValue("1");
//		((MyPagingLoader) comboProcess.getComboBox().getStore().getLoader()).getConfig().addFilter(filter);
		
		comboProcess.getComboBox().addFilter("enable", "1");
		comboProcess.getComboBox().addFilter("menu", "1");
		//comboProcess.getComboBox().setEditable(true);
		configs.add(comboProcess);

		NormalColumn subCol = new NormalColumn(cdata.get(1));
		configs.add(subCol);
		NormalColumn modCol = new NormalColumn(cdata.get(2));
		configs.add(modCol);
		NormalColumn proCol = new NormalColumn(cdata.get(3));
		configs.add(proCol);

		NormalColumn descCol = new NormalColumn(cdata.get(4), Validate.TEXT);
		descCol.setEditor(null);
		configs.add(descCol);

		comboProcess.linkWithColumn("pk_subsystemId", subCol);
		comboProcess.linkWithColumn("pk_moduleId", modCol);
		comboProcess.linkWithColumn("pk_processId", proCol);
		comboProcess.linkWithColumn("name", descCol);

		CheckColumnConfig a = new CheckColumn(cdata.get(5));
		configs.add(a);

		configs.add(new ExpireColumnConfig());

		ColumnModel cm = new ColumnModel(configs);

		// Filters
		MyGridFilters filters = new MyGridFilters();
		StringFilter procCodeFilter = new StringFilter("Process_url");
		StringFilter procNameFilter = new StringFilter("Process_name");
		filters.addFilter(procCodeFilter);
		filters.addFilter(procNameFilter);

		// Content panel
		EntityContentPanel cp = new EntityContentPanel(FORM_WIDTH - 30, 340);

		// Grid
		final EntityEditorGrid grid = new EntityEditorGrid(getStore(), cm);
		grid.setAutoExpandColumn("Process_name");
		grid.setBorders(true);
		grid.addDependency(profileId);
		grid.addPlugin(filters);
		cp.add(grid);
		grid.addListener(Events.Attach, new Listener<BaseEvent>() {
			public void handleEvent(BaseEvent be) {
				// getStore().sort(cdata.getIdFields().get(0), SortDir.ASC);
			}
		});

		// Top tool bar
		ModelData initModel = new BaseModelData();
		initModel.set(cdata.get(0).getId(), null);
		initModel.set(cdata.get(1).getId(), null);
		initModel.set(cdata.get(2).getId(), null);
		initModel.set(cdata.get(3).getId(), null);
		initModel.set(cdata.get(4).getId(), null);
		initModel.set(cdata.get(5).getId(), false);
		GridToolBar toolBar = new GridToolBar(grid, getStore(), initModel);
		cp.setTopComponent(toolBar);

		// Paging tool bar
		final GridPagingToolBar pagingToolBar = new GridPagingToolBar(grid, PAGE_SIZE);
		cp.setBottomComponent(pagingToolBar);

		form.add(cp);

		profileId.addSelectionChangedListener(new SelectionChangedListener<ModelData>() {
			@Override
			public void selectionChanged(SelectionChangedEvent<ModelData> se) {
				if (((ComboForm) se.getSource()).isSomeSelected()) {
					pagingToolBar.refresh();
				} else {
					grid.getStore().removeAll();
				}
			}
		});

		add(form);
	}
}