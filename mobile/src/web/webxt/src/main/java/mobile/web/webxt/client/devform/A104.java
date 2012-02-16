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

import com.extjs.gxt.ui.client.Style.HorizontalAlignment;
import com.extjs.gxt.ui.client.data.ModelData;
import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.SelectionChangedEvent;
import com.extjs.gxt.ui.client.event.SelectionChangedListener;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.grid.ColumnConfig;
import com.extjs.gxt.ui.client.widget.grid.ColumnModel;
import com.extjs.gxt.ui.client.widget.layout.FlowLayout;
import com.google.gwt.user.client.Element;

public class A104 extends MyGeneralForm {

	private final static String PROCESS = "A104";
	private final static String ENTITY = "Module";
	private final Integer PAGE_SIZE = 5;

	public A104() {
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
		final MyFormPanel form = new MyFormPanel(this, "Módulos", FORM_WIDTH);
		form.setLayout(new FlowLayout());
		form.setButtonAlign(HorizontalAlignment.CENTER);

		// Header
		// Filter: subsystem
		RowContainer row = new RowContainer();
		row.setStyleAttribute("margin-bottom", "10px");

		MyLabel label = new MyLabel("Subsistema:", LABEL_WIDTH);
		row.add(label);

		// Subsystem combo
		final ComboForm subsystemId = new ComboForm(60);
		subsystemId.setDataSource(new DataSource("pk_subsystemId", DataSourceType.CRITERION));

		Reference refSolicitude = new Reference("sub", "Subsystem");
		final ArrayColumnData solCdata = new ArrayColumnData();
		solCdata.add(new MyColumnData("sub", "pk_subsystemId", "Id", 40));
		solCdata.add(new MyColumnData("sub", "name", "Nombre", 150));
		subsystemId.setQueryData(refSolicitude, solCdata);
		subsystemId.setDisplayField("pk_subsystemId");

		// Subsystem description
		final InputBox subsystemName = new InputBox(150);
		subsystemName.setDataSource(new DataSource("Subsystem", "name", DataSourceType.CRITERION_DESCRIPTION));
		subsystemName.setReadOnly(true);

		subsystemId.linkWithField(subsystemName, "name");

		row.add(subsystemId);
		row.add(subsystemName);

		form.add(row);

		// Main grid
		// Configuration
		final ArrayColumnData cdata = new ArrayColumnData();
		cdata.add(new MyColumnData("pk_moduleId", "Mod", 70, 2, false));
		cdata.add(new MyColumnData("name", "Nombre", 150, 40, false));
		getConfig().setlDataSource(cdata.getDataSources());

		// Columns
		List<ColumnConfig> configs = new ArrayList<ColumnConfig>();
		configs.add(new NormalColumn(cdata.get(0), Validate.TEXT));
		configs.add(new NormalColumn(cdata.get(1), Validate.TEXT));
		configs.add(new ExpireColumnConfig());
		ColumnModel cm = new ColumnModel(configs);

		// Content panel
		EntityContentPanel gridPanel = new EntityContentPanel(400, 230);

		// Grid
		final EntityEditorGrid grid = new EntityEditorGrid(getStore(), cm);
		grid.setAutoExpandColumn("name");
		grid.setBorders(true);
		grid.addDependency(subsystemId);
		gridPanel.add(grid);

		// Top tool bar
		GridToolBar toolBar = new GridToolBar(grid, getStore());
		gridPanel.setTopComponent(toolBar);

		// Paging tool bar
		final GridPagingToolBar pagingToolBar = new GridPagingToolBar(grid, PAGE_SIZE);
		gridPanel.setBottomComponent(pagingToolBar);

		form.add(gridPanel);

		subsystemId.addSelectionChangedListener(new SelectionChangedListener<ModelData>() {
			@Override
			public void selectionChanged(SelectionChangedEvent<ModelData> se) {
				if (((ComboForm) se.getSource()).isSomeSelected()) {
					pagingToolBar.refresh();
				} else {
					grid.getStore().removeAll();
				}
			}
		});

		form.addButton(new Button("Limpiar", new SelectionListener<ButtonEvent>() {
			@Override
			public void componentSelected(ButtonEvent ce) {
				form.clear();
				grid.getStore().removeAll();
			}
		}));

		add(form);
	}
}