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
import mobile.web.webxt.client.form.widgetsgrid.ComboColumn;
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

public class A106 extends MyGeneralForm {

	private final static String PROCESS = "A106";
	private final static String ENTITY = "Component";
	private final Integer PAGE_SIZE = 5;

	public A106() {
		super(PROCESS, true);
		setReference(ENTITY);
	}

	@Override
	protected void onRender(Element parent, int index) {
		super.onRender(parent, index);

		// Constants
		final int FORM_WIDTH = 700;
		final int LABEL_WIDTH = 80;

		// Super Form
		final MyFormPanel form = new MyFormPanel(this, "Componentes", FORM_WIDTH);
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
		subsystemId.setDataSource(new DataSource("subsystemId", DataSourceType.CRITERION));

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
		cdata.add(new MyColumnData("pk_componentId", "Componente", 250, 150, false));
		cdata.add(new MyColumnData("pk_typeId", "Tipo", 50, 3, false));
		cdata.add(new MyColumnData("className", "Nombre clase", 150, 100, false));
		cdata.add(new MyColumnData("description", "Descripción", 250, 100, false));
		getConfig().setlDataSource(cdata.getDataSources());

		// Columns
		List<ColumnConfig> configs = new ArrayList<ColumnConfig>();
		NormalColumn componentCol = new NormalColumn(cdata.get(0));
		componentCol.getInputbox().setType(Validate.OTHER);
		configs.add(componentCol);
		
		ComboColumn typeColumn = new ComboColumn(cdata.get(1));
		Reference refType = new Reference("ct", "ComponentType");
		ArrayColumnData typeCdata = new ArrayColumnData();
		typeCdata.add(new MyColumnData("ct", "componentTypeId", "Tipo", 50));
		typeCdata.add(new MyColumnData("ct", "description", "Descripción", 150));
		typeColumn.setQueryData(refType, typeCdata);
		configs.add(typeColumn);

		NormalColumn componentName = new NormalColumn(cdata.get(2));
		componentName.getInputbox().setType(Validate.OTHER);
		configs.add(componentName);
		
		configs.add(new NormalColumn(cdata.get(3), Validate.TEXT));
		configs.add(new ExpireColumnConfig());
		ColumnModel cm = new ColumnModel(configs);

		// Content panel
		EntityContentPanel gridPanel = new EntityContentPanel(FORM_WIDTH-30, 230);

		// Grid
		final EntityEditorGrid grid = new EntityEditorGrid(getStore(), cm);
		//grid.setAutoExpandColumn("pk_componentId");
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