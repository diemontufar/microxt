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
import mobile.web.webxt.client.form.widgets.InputBox;
import mobile.web.webxt.client.form.widgetsgrid.ArrayColumnData;
import mobile.web.webxt.client.form.widgetsgrid.ComboColumn;
import mobile.web.webxt.client.form.widgetsgrid.EntityEditorGrid;
import mobile.web.webxt.client.form.widgetsgrid.ExpireColumnConfig;
import mobile.web.webxt.client.form.widgetsgrid.GridPagingToolBar;
import mobile.web.webxt.client.form.widgetsgrid.GridToolBar;
import mobile.web.webxt.client.form.widgetsgrid.MyColumnData;
import mobile.web.webxt.client.form.widgetsgrid.NormalColumn;
import mobile.web.webxt.client.util.DatesManager;
import mobile.web.webxt.client.util.TextType;

import com.extjs.gxt.ui.client.Style.SortDir;
import com.extjs.gxt.ui.client.data.ModelData;
import com.extjs.gxt.ui.client.event.BaseEvent;
import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.Events;
import com.extjs.gxt.ui.client.event.GridEvent;
import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.store.ListStore;
import com.extjs.gxt.ui.client.store.Record;
import com.extjs.gxt.ui.client.widget.BoxComponent;
import com.extjs.gxt.ui.client.widget.Info;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.form.TextField;
import com.extjs.gxt.ui.client.widget.grid.CellEditor;
import com.extjs.gxt.ui.client.widget.grid.ColumnConfig;
import com.extjs.gxt.ui.client.widget.grid.ColumnData;
import com.extjs.gxt.ui.client.widget.grid.ColumnModel;
import com.extjs.gxt.ui.client.widget.grid.Grid;
import com.extjs.gxt.ui.client.widget.grid.GridCellRenderer;
import com.google.gwt.event.logical.shared.AttachEvent;
import com.google.gwt.user.client.Element;

/**
 * PROCESS FOR RESETING PASWORDS (ADMIN)
 */
public class A206 extends MyGeneralForm {
	private final static String PROCESS = "A206";
	private final static String ENTITY = "UserAccess";
	private final Integer PAGE_SIZE = 5;

	InputBox defaultKey, parameterCode;
	NormalColumn dateColumn;
	GridToolBar toolBar;

	ColumnConfig columnEncoded;

	private final static String ENTITY_PARAMETER = "Parameter";
	MyFormPanel formParameter;
	MyGeneralForm formContainerParameter;

	boolean isReseted = false;

	EntityEditorGrid grid;

	CellEditor cellEdito1;

	public A206() {
		super(PROCESS, true);
		setReference(ENTITY);

		formContainerParameter = new MyGeneralForm(PROCESS);
		formContainerParameter.setReference(new Reference("par", ENTITY_PARAMETER));
		formContainerParameter.setVisible(false);

		formParameter = new MyFormPanel(formContainerParameter, "", 200) {
			@Override
			protected void postQuery() {
				grid.getStore().getLoader().load();
			}
		};
		formParameter.setVisible(false);
	}

	@Override
	protected void onRender(Element parent, int index) {
		super.onRender(parent, index);

		// Auxiliary form
		parameterCode = new InputBox();
		parameterCode.setDataSource(new DataSource("par", "pk_parameterId", DataSourceType.CRITERION));
		parameterCode.setValue("DEFAULT_PASSWORD");
		formParameter.add(parameterCode);

		defaultKey = new InputBox();
		defaultKey.setDataSource(new DataSource("par", "parameterValue", DataSourceType.RECORD));
		defaultKey.setType(Validate.PASSWORD);
		formParameter.add(defaultKey);

		formParameter.addAttachHandler(new AttachEvent.Handler() {
			public void onAttachOrDetach(AttachEvent event) {
				formParameter.queryForm();
			}
		});

		// Grid form
		final ArrayColumnData cdata = new ArrayColumnData();
		cdata.add(new MyColumnData("pk_userId", "ID", 70, 20, false));
		cdata.add(new MyColumnData("userKey", "Clave", 200, 300, false));
		cdata.add(new MyColumnData("lastChange", "Ultimo Cambio", 100, 10, false));
		getConfig().setlDataSource(cdata.getDataSources());

		// Column model
		List<ColumnConfig> configs = new ArrayList<ColumnConfig>();

		// Combo Assessor
		ComboColumn comboAssessor = new ComboColumn(cdata.get(0));
		Reference refProfile = new Reference("ase", "UserAccount");
		ArrayColumnData cdataCombo = new ArrayColumnData();
		cdataCombo.add(new MyColumnData("ase", "pk_userId", "ID", 70));
		cdataCombo.add(new MyColumnData("ase", "name", "Nombre", 200));
		comboAssessor.setQueryData(refProfile, cdataCombo);
		comboAssessor.getComboBox().setPageSize(5);

		configs.add(comboAssessor);

		GridCellRenderer<ModelData> buttonRenderer = new GridCellRenderer<ModelData>() {

			private boolean init;

			public Object render(final ModelData model, String property, ColumnData config, final int rowIndex,
					final int colIndex, final ListStore<ModelData> store, final Grid<ModelData> grid) {

				if (!init) {
					init = true;
					grid.addListener(Events.ColumnResize, new Listener<GridEvent<ModelData>>() {

						public void handleEvent(GridEvent<ModelData> be) {
							for (int i = 0; i < be.getGrid().getStore().getCount(); i++) {
								if (be.getGrid().getView().getWidget(i, be.getColIndex()) != null
										&& be.getGrid().getView().getWidget(i, be.getColIndex()) instanceof BoxComponent) {
									((BoxComponent) be.getGrid().getView().getWidget(i, be.getColIndex())).setWidth(be
											.getWidth() - 10);
								}
							}
						}
					});

				}

				Button b = new Button("Reset", new SelectionListener<ButtonEvent>() {
					@Override
					public void componentSelected(ButtonEvent ce) {
						if (defaultKey.getValue() != null) {
							try {
								Record rec = store.getRecord(model);
								rec.setValid("userKey", true);
								rec.set("userKey", defaultKey.getValue());
								rec.setValid("lastChange", true);
								rec.set("lastChange", DatesManager.getCurrentDate());
							} catch (Exception e) {
								e.printStackTrace();
							}
						} else {
							Info.display("Error", "No hay Contraseña por defecto");
						}
					}
				});

				b.setWidth(grid.getColumnModel().getColumnWidth(colIndex) - 10);
				b.setHeight("100%");
				b.setToolTip("Resetear Contraseña");

				return b;
			}
		};

		columnEncoded = new ColumnConfig();
		columnEncoded.setId("userKey");
		columnEncoded.setHeader("Clave");
		columnEncoded.setWidth(200);
		cellEdito1 = new CellEditor(new TextField<String>());
		columnEncoded.setEditor(cellEdito1);
		columnEncoded.setEditor(null);
		configs.add(columnEncoded);

		dateColumn = new NormalColumn(cdata.get(2), TextType.DATE, null);
		dateColumn.setEditor(null);
		configs.add(dateColumn);

		ColumnConfig column = new ColumnConfig();
		column.setId("preview");
		column.setHeader("Resetear");
		column.setWidth(50);
		column.setRenderer(buttonRenderer);
		configs.add(column);

		configs.add(new ExpireColumnConfig());

		ColumnModel cm = new ColumnModel(configs);

		// Content panel
		EntityContentPanel cp = new EntityContentPanel("Reseteo de Contraseñas", 500, 230);

		// Grid
		grid = new EntityEditorGrid(getStore(), cm);
		grid.setAutoExpandColumn("userKey");

		cp.add(grid);
		grid.addListener(Events.Attach, new Listener<BaseEvent>() {
			public void handleEvent(BaseEvent be) {
				getStore().sort(cdata.getIdFields().get(0), SortDir.ASC);
			}
		});

		// Top tool bar
		toolBar = new GridToolBar(grid, getStore());
		cp.setTopComponent(toolBar);

		// Paging tool bar
		final GridPagingToolBar pagingToolBar = new GridPagingToolBar(grid, PAGE_SIZE);
		cp.setBottomComponent(pagingToolBar);

		cp.add(formParameter);
		add(cp);
	}
}