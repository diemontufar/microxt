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
import mobile.web.webxt.client.form.widgetsgrid.NormalColumn;
import mobile.web.webxt.client.form.widgetsgrid.NumericColumn;
import mobile.web.webxt.client.util.NumberType;

import com.extjs.gxt.ui.client.Style.HorizontalAlignment;
import com.extjs.gxt.ui.client.data.BaseModelData;
import com.extjs.gxt.ui.client.data.ModelData;
import com.extjs.gxt.ui.client.event.SelectionChangedEvent;
import com.extjs.gxt.ui.client.event.SelectionChangedListener;
import com.extjs.gxt.ui.client.widget.grid.ColumnConfig;
import com.extjs.gxt.ui.client.widget.grid.ColumnModel;
import com.extjs.gxt.ui.client.widget.layout.FlowLayout;
import com.google.gwt.user.client.Element;

public class A107 extends MyGeneralForm {

	private final static String PROCESS = "A107";
	private final static String ENTITY = "ProcessComponent";
	private final Integer PAGE_SIZE = 5;

	public A107() {
		super(PROCESS, true);
		setReference(ENTITY);
	}

	@Override
	protected void onRender(Element parent, int index) {
		super.onRender(parent, index);

		// Constants
		final int FORM_WIDTH = 480;
		final int LABEL_WIDTH = 80;

		// Super Form
		final MyFormPanel form = new MyFormPanel(this, "Componentes por Procesos", FORM_WIDTH);
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

		// Filter: process
		row = new RowContainer();
		row.setStyleAttribute("margin-bottom", "10px");

		label = new MyLabel("Proceso:", LABEL_WIDTH);
		row.add(label);

		// Process combo
		final ComboForm processId = new ComboForm(60);
		processId.setDataSource(new DataSource("pk_processId", DataSourceType.CRITERION));

		Reference refProcess = new Reference("proc", "Process");
		final ArrayColumnData procCdata = new ArrayColumnData();
		procCdata.add(new MyColumnData("proc", "pk_processId", "Id", 60));
		procCdata.add(new MyColumnData("proc", "name", "Nombre", 150));
		processId.setQueryData(refProcess, procCdata);
		processId.setDisplayField("pk_processId");

		// Process description
		final InputBox processName = new InputBox(150);
		processName.setDataSource(new DataSource("Process", "name", DataSourceType.CRITERION_DESCRIPTION));
		processName.setReadOnly(true);

		processId.linkWithField(processName, "name");

		processId.addDependency(moduleId, "pk_moduleId");

		row.add(processId);
		row.add(processName);

		form.add(row);

		// Grid Configurations
		final ArrayColumnData cdata = new ArrayColumnData();
		cdata.add(new MyColumnData("pk_processSequence", "Sec", 50, 2, false));
		cdata.add(new MyColumnData("componentId", "Componente", 250, 150, false));
		cdata.add(new MyColumnData("typeId", "Tipo", 50, true));
		cdata.add(new MyColumnData("enable", "Activo", 60, 4, false));
		getConfig().setlDataSource(cdata.getDataSources());

		// Columns
		List<ColumnConfig> configs = new ArrayList<ColumnConfig>();

		configs.add(new NumericColumn(cdata.get(0), NumberType.INTEGER));

		ComboColumn componentColumn = new ComboColumn(cdata.get(1));
		Reference refComponent = new Reference("cpt", "Component");
		ArrayColumnData compCdata = new ArrayColumnData();
		compCdata.add(new MyColumnData("cpt", "pk_componentId", "Cod componente", 250));
		compCdata.add(new MyColumnData("cpt", "pk_typeId", "Tipo", 50));
		componentColumn.setQueryData(refComponent, compCdata);
		configs.add(componentColumn);

		NormalColumn nc = new NormalColumn(cdata.get(2));
		configs.add(nc);

		componentColumn.linkWithColumn("pk_typeId", nc);

		configs.add(new CheckColumn(cdata.get(3)));
		configs.add(new ExpireColumnConfig());

		ColumnModel cm = new ColumnModel(configs);

		// Grid panel
		EntityContentPanel gridPanel = new EntityContentPanel(FORM_WIDTH - 30, 230);

		// Grid
		final EntityEditorGrid grid = new EntityEditorGrid(getStore(), cm);
		grid.setAutoExpandColumn("componentId");
		grid.setBorders(true);
		grid.addDependency(subsystemId);
		grid.addDependency(moduleId);
		grid.addDependency(processId);

		gridPanel.add(grid);

		// Top tool bar
		ModelData newItem = new BaseModelData();
		newItem.set(cdata.get(0).getId(), null);
		newItem.set(cdata.get(1).getId(), null);
		newItem.set(cdata.get(2).getId(), null);
		newItem.set(cdata.get(3).getId(), true);

		GridToolBar toolBar = new GridToolBar(grid, getStore(), newItem);
		gridPanel.setTopComponent(toolBar);

		// Paging tool bar
		final GridPagingToolBar pagingToolBar = new GridPagingToolBar(grid, PAGE_SIZE);
		gridPanel.setBottomComponent(pagingToolBar);

		// Operations
		processId.addSelectionChangedListener(new SelectionChangedListener<ModelData>() {
			@Override
			public void selectionChanged(SelectionChangedEvent<ModelData> se) {
				if (((ComboForm) se.getSource()).isSomeSelected()) {
					pagingToolBar.refresh();
				} else {
					grid.getStore().removeAll();
				}
			}
		});

		form.add(gridPanel);
		add(form);
	}
}