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
import mobile.web.webxt.client.form.widgetsgrid.CheckColumn;
import mobile.web.webxt.client.form.widgetsgrid.EntityEditorGrid;
import mobile.web.webxt.client.form.widgetsgrid.ExpireColumnConfig;
import mobile.web.webxt.client.form.widgetsgrid.GridPagingToolBar;
import mobile.web.webxt.client.form.widgetsgrid.GridToolBar;
import mobile.web.webxt.client.form.widgetsgrid.MyColumnData;
import mobile.web.webxt.client.form.widgetsgrid.NormalColumn;

import com.extjs.gxt.ui.client.Style.HorizontalAlignment;
import com.extjs.gxt.ui.client.data.BaseModelData;
import com.extjs.gxt.ui.client.data.ModelData;
import com.extjs.gxt.ui.client.event.BaseEvent;
import com.extjs.gxt.ui.client.event.Events;
import com.extjs.gxt.ui.client.event.GridEvent;
import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.event.SelectionChangedEvent;
import com.extjs.gxt.ui.client.event.SelectionChangedListener;
import com.extjs.gxt.ui.client.store.Record;
import com.extjs.gxt.ui.client.widget.grid.ColumnConfig;
import com.extjs.gxt.ui.client.widget.grid.ColumnModel;
import com.extjs.gxt.ui.client.widget.layout.FlowLayout;
import com.google.gwt.user.client.Element;

public class A105 extends MyGeneralForm {

	private final static String PROCESS = "A105";
	private final static String ENTITY = "Process";
	private final Integer PAGE_SIZE = 5;

	public A105() {
		super(PROCESS, true);
		setReference(ENTITY);
	}

	@Override
	protected void onRender(Element parent, int index) {
		super.onRender(parent, index);

		// Constants
		final int FORM_WIDTH = 430;
		final int LABEL_WIDTH = 80;

		// Super Form
		final MyFormPanel form = new MyFormPanel(this, "Procesos", FORM_WIDTH);
		form.setLayout(new FlowLayout());
		form.setButtonAlign(HorizontalAlignment.CENTER);

		// Header
		// Filter: subsystem
		RowContainer row = new RowContainer();

		MyLabel label = new MyLabel("Subsistema:", LABEL_WIDTH);
		row.add(label);

		// Subsystem combo
		final ComboForm subsystemId = new ComboForm(60);
		subsystemId.setDataSource(new DataSource("pk_subsystemId", DataSourceType.CRITERION));

		Reference refSolicitude = new Reference("sub", "Subsystem");
		final ArrayColumnData subCdata = new ArrayColumnData();
		subCdata.add(new MyColumnData("sub", "pk_subsystemId", "Id", 60));
		subCdata.add(new MyColumnData("sub", "name", "Nombre", 150));
		subsystemId.setQueryData(refSolicitude, subCdata);
		subsystemId.setDisplayField("pk_subsystemId");

		// Subsystem description
		final InputBox subsystemName = new InputBox(150);
		subsystemName.setDataSource(new DataSource("Subsystem", "name", DataSourceType.CRITERION_DESCRIPTION));
		subsystemName.setReadOnly(true);

		subsystemId.linkWithField(subsystemName, "name");

		row.add(subsystemId);
		row.add(subsystemName);

		form.add(row);

		// Filter: module
		row = new RowContainer();
		row.setStyleAttribute("margin-bottom", "10px");

		label = new MyLabel("Módulo:", LABEL_WIDTH);
		row.add(label);

		// Module combo
		final ComboForm moduleId = new ComboForm(60);
		moduleId.setDataSource(new DataSource("pk_moduleId", DataSourceType.CRITERION));

		Reference refModule = new Reference("mod", "Module");
		final ArrayColumnData modCdata = new ArrayColumnData();
		modCdata.add(new MyColumnData("mod", "pk_moduleId", "Id", 60));
		modCdata.add(new MyColumnData("mod", "name", "Nombre", 150));
		moduleId.setQueryData(refModule, modCdata);
		moduleId.setDisplayField("pk_moduleId");

		// Module description
		final InputBox moduleName = new InputBox(150);
		moduleName.setDataSource(new DataSource("Module", "name", DataSourceType.CRITERION_DESCRIPTION));
		moduleName.setReadOnly(true);

		moduleId.linkWithField(moduleName, "name");

		moduleId.addDependency(subsystemId, "pk_subsystemId");

		row.add(moduleId);
		row.add(moduleName);

		form.add(row);

		// Grid Configurations
		final ArrayColumnData cdata = new ArrayColumnData();
		cdata.add(new MyColumnData("pk_processId", "Proc", 70, 2, false));
		cdata.add(new MyColumnData("name", "Nombre", 130, 40, false));
		cdata.add(new MyColumnData("url", "Url", 60, 4, false));
		cdata.add(new MyColumnData("enable", "Activo", 60, 4, false));
		cdata.add(new MyColumnData("menu", "Menu", 60, 4, false));
		getConfig().setlDataSource(cdata.getDataSources());

		// Columns
		List<ColumnConfig> configs = new ArrayList<ColumnConfig>();

		configs.add(new NormalColumn(cdata.get(0), Validate.TEXT));
		configs.add(new NormalColumn(cdata.get(1), Validate.TEXT));
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
		final EntityEditorGrid grid = new EntityEditorGrid(getStore(), cm);
		grid.setAutoExpandColumn("name");
		grid.setBorders(true);
		grid.addDependency(subsystemId);
		grid.addDependency(moduleId);

		gridPanel.add(grid);

		// Top tool bar
		ModelData newItem = new BaseModelData();
		newItem.set(cdata.get(0).getId(), null);
		newItem.set(cdata.get(1).getId(), null);
		newItem.set(cdata.get(2).getId(), null);
		newItem.set(cdata.get(3).getId(), true);
		newItem.set(cdata.get(4).getId(), true);

		GridToolBar toolBar = new GridToolBar(grid, getStore(), newItem);
		gridPanel.setTopComponent(toolBar);

		// Paging tool bar
		final GridPagingToolBar pagingToolBar = new GridPagingToolBar(grid, PAGE_SIZE);
		gridPanel.setBottomComponent(pagingToolBar);

		// Operations
		moduleId.addSelectionChangedListener(new SelectionChangedListener<ModelData>() {
			@Override
			public void selectionChanged(SelectionChangedEvent<ModelData> se) {
				if (((ComboForm) se.getSource()).isSomeSelected()) {
					pagingToolBar.refresh();
				} else {
					grid.getStore().removeAll();
				}
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
					String sub = subsystemId.getValue().get("pk_subsystemId");
					String mod = moduleId.getValue().get("pk_moduleId");
					rec.set("url", sub + mod + val);
				}
			}
		});

		form.add(gridPanel);
		add(form);
	}
}