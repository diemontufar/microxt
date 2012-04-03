package mobile.web.webxt.client.devform;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import mobile.common.tools.Format;
import mobile.web.webxt.client.data.form.DataSource;
import mobile.web.webxt.client.data.form.DataSourceType;
import mobile.web.webxt.client.data.form.Reference;
import mobile.web.webxt.client.form.EntityContentPanel;
import mobile.web.webxt.client.form.MyFormPanel;
import mobile.web.webxt.client.form.MyGeneralForm;
import mobile.web.webxt.client.form.widgets.ComboForm;
import mobile.web.webxt.client.form.widgets.InputBox;
import mobile.web.webxt.client.form.widgets.MyLabel;
import mobile.web.webxt.client.form.widgets.MyNumberField;
import mobile.web.webxt.client.form.widgets.RowContainer;
import mobile.web.webxt.client.form.widgetsgrid.ArrayColumnData;
import mobile.web.webxt.client.form.widgetsgrid.CheckColumn;
import mobile.web.webxt.client.form.widgetsgrid.DateColumn;
import mobile.web.webxt.client.form.widgetsgrid.EntityEditorGrid;
import mobile.web.webxt.client.form.widgetsgrid.GridPagingToolBar;
import mobile.web.webxt.client.form.widgetsgrid.GridToolBar;
import mobile.web.webxt.client.form.widgetsgrid.MyColumnData;
import mobile.web.webxt.client.form.widgetsgrid.NormalColumn;
import mobile.web.webxt.client.form.widgetsgrid.NumericColumn;
import mobile.web.webxt.client.util.DatesManager;
import mobile.web.webxt.client.util.NumberType;

import com.extjs.gxt.ui.client.Style.HorizontalAlignment;
import com.extjs.gxt.ui.client.data.LoadEvent;
import com.extjs.gxt.ui.client.data.ModelData;
import com.extjs.gxt.ui.client.event.LoadListener;
import com.extjs.gxt.ui.client.event.SelectionChangedEvent;
import com.extjs.gxt.ui.client.event.SelectionChangedListener;
import com.extjs.gxt.ui.client.store.ListStore;
import com.extjs.gxt.ui.client.store.Record;
import com.extjs.gxt.ui.client.store.StoreEvent;
import com.extjs.gxt.ui.client.store.StoreListener;
import com.extjs.gxt.ui.client.widget.grid.ColumnConfig;
import com.extjs.gxt.ui.client.widget.grid.ColumnData;
import com.extjs.gxt.ui.client.widget.grid.ColumnModel;
import com.extjs.gxt.ui.client.widget.grid.Grid;
import com.extjs.gxt.ui.client.widget.grid.GridCellRenderer;
import com.extjs.gxt.ui.client.widget.layout.FlowLayout;
import com.google.gwt.i18n.client.NumberFormat;
import com.google.gwt.user.client.Element;

public class C503 extends MyGeneralForm {
	private final static String PROCESS = "C503";
	private final static String ENTITY = "MicroAccountQuota";
	private final Integer PAGE_SIZE = 10;

	private MyNumberField total;
	private EntityEditorGrid grid;

	// private CheckColumnConfig payCheckConfig;

	public C503() {
		super(PROCESS, true);
		setReference(ENTITY);
	}

	@Override
	protected void onRender(Element parent, int index) {
		super.onRender(parent, index);

		// Constants
		final int FORM_WIDTH = 700;
		final int LABEL_WIDTH = 60;

		// Super Form
		final MyFormPanel form = new MyFormPanel(this, "Pago de Cuotas", FORM_WIDTH);
		form.setLayout(new FlowLayout());
		form.setButtonAlign(HorizontalAlignment.CENTER);

		// Special widgets

		// Header
		// Filter: ACcount
		RowContainer row = new RowContainer();
		row.setStyleAttribute("margin-bottom", "10px");

		MyLabel label = new MyLabel("Préstamo:", LABEL_WIDTH);
		row.add(label);

		// ACcount combo
		final ComboForm accountId = new ComboForm(100);
		accountId.setDataSource(new DataSource("pk_accountId", DataSourceType.CRITERION));

		Reference refAccount = new Reference("acc", "MicroAccount");
		final ArrayColumnData accCdata = new ArrayColumnData();
		accCdata.add(new MyColumnData("acc", "pk_accountId", "Cuenta", 100));
		accCdata.add(new MyColumnData("acc", "clientName", "Nombre", 250));
		accountId.setQueryData(refAccount, accCdata);
		accountId.setDisplayField("pk_accountId");

		// Client description
		final InputBox accountName = new InputBox(200);
		accountName.setDataSource(new DataSource("Account", "clientName", DataSourceType.CRITERION_DESCRIPTION));
		accountName.setReadOnly(true);

		accountId.linkWithField(accountName, "clientName");

		row.add(accountId);
		row.add(accountName);

		form.add(row);

		// Grid Configurations
		final ArrayColumnData cdata = new ArrayColumnData();
		cdata.add(new MyColumnData("pk_subaccount", "No", 40, true));
		cdata.add(new MyColumnData("capital", "Capital", 60, true));
		cdata.add(new MyColumnData("interest", "Interes", 60, true));
		MyColumnData quotaColumn = new MyColumnData();
		quotaColumn.setId("fixedQuota");
		quotaColumn.setName("Cuota");
		quotaColumn.setWidth(60);
		cdata.add(quotaColumn);
		cdata.add(new MyColumnData("reducedCapital", "Cap. reducido", 60, true));
		cdata.add(new MyColumnData("fromDate", "F. Desde", 70, true));
		cdata.add(new MyColumnData("expirationDate", "F. Hasta", 70, true));
		MyColumnData statColumn = new MyColumnData();
		statColumn.setId("status");
		statColumn.setName("Estado");
		statColumn.setWidth(80);
		cdata.add(statColumn);
		cdata.add(new MyColumnData("paymentDate", "F. Pago", 70, true));

		getConfig().setlDataSource(cdata.getDataSources());

		// Columns
		GridCellRenderer<ModelData> quotaRenderer = new GridCellRenderer<ModelData>() {
			public String render(ModelData model, String property, ColumnData config, int rowIndex, int colIndex,
					ListStore<ModelData> store, Grid<ModelData> grid) {
				BigDecimal capital = (BigDecimal) model.get("capital");
				BigDecimal interest = (BigDecimal) model.get("interest");
				BigDecimal fixedQuota = capital.add(interest);
				model.set(property, fixedQuota);
				return NumberFormat.getFormat(Format.DECIMAL).format(fixedQuota);
			}
		};
		GridCellRenderer<ModelData> statRenderer = new GridCellRenderer<ModelData>() {
			public String render(ModelData model, String property, ColumnData config, int rowIndex, int colIndex,
					ListStore<ModelData> store, Grid<ModelData> grid) {
				Date paymentDate = (Date) model.get("paymentDate");
				String status = "";
				if (paymentDate != null) {
					status = "PAGADA";
				} else {
					Date actual = DatesManager.getCurrentDate();
					Date expirationDate = (Date) model.get("expirationDate");
					if (actual.before(expirationDate) || actual.equals(expirationDate)) {
						status = "PENDIENTE";
					} else if (actual.after(expirationDate)) {
						status = "VENCIDA";
					}
				}

				return status;
			}
		};

		List<ColumnConfig> configs = new ArrayList<ColumnConfig>();

		configs.add(new NormalColumn(cdata.get(0)));
		configs.add(new NumericColumn(cdata.get(1), NumberType.DECIMAL));
		configs.add(new NumericColumn(cdata.get(2), NumberType.DECIMAL));
		NumericColumn quotaCol = new NumericColumn(cdata.get(3), NumberType.DECIMAL);
		quotaCol.setRenderer(quotaRenderer);
		configs.add(quotaCol);
		configs.add(new NumericColumn(cdata.get(4), NumberType.DECIMAL));
		configs.add(new DateColumn(cdata.get(5)));
		configs.add(new DateColumn(cdata.get(6)));
		NormalColumn statCol = new NormalColumn(cdata.get(7));
		statCol.setRenderer(statRenderer);
		configs.add(statCol);
		configs.add(new DateColumn(cdata.get(8)));
		final CheckColumn payCheckConfig = new CheckColumn("pay", "Pagar", 60) {
			@Override
			protected String getCheckState(ModelData model, String property, int rowIndex, int colIndex) {
				String on = null;
				if (model.get("pay2") != null && ((Boolean) model.get("pay2")) == false) {
					on = "-disabled";
				} else {
					Boolean v = model.get(property);
					on = (v != null && v) ? "-on" : "";
				}
				return on;
			}
		};
		configs.add(payCheckConfig);

		NormalColumn payCheckConfig2 = new NormalColumn("pay2", "", 60, 10, true);
		payCheckConfig2.setHidden(true);
		configs.add(payCheckConfig2);

		ColumnModel cm = new ColumnModel(configs);

		// Grid panel
		EntityContentPanel gridPanel = new EntityContentPanel(FORM_WIDTH - 30, 230);

		// Grid
		grid = new EntityEditorGrid(getStore(), cm);
		grid.setAutoExpandColumn("status");
		grid.setBorders(true);
		grid.addDependency(accountId);

		gridPanel.add(grid);

		// Top tool bar
		GridToolBar toolBar = new GridToolBar(grid, getStore());
		toolBar.getAddButton().setVisible(false);
		gridPanel.setTopComponent(toolBar);

		// Paging tool bar
		final GridPagingToolBar pagingToolBar = new GridPagingToolBar(grid, PAGE_SIZE);
		gridPanel.setBottomComponent(pagingToolBar);

		// Operations
		// Autoquery
		accountId.addSelectionChangedListener(new SelectionChangedListener<ModelData>() {
			@Override
			public void selectionChanged(SelectionChangedEvent<ModelData> se) {
				if (((ComboForm) se.getSource()).isSomeSelected()) {
					pagingToolBar.refresh();
				} else {
					grid.getStore().removeAll();
				}
			}
		});

		// On load
		grid.getStore().getLoader().addLoadListener(new LoadListener() {
			@Override
			public void loaderLoad(LoadEvent le) {
				super.loaderLoad(le);
				System.out.println(">>>>>Load!!!");
				for (int i = 0; i < grid.getStore().getCount(); i++) {
					ModelData model = grid.getStore().getAt(i);
					if (model.get("paymentDate") != null) {
						model.set("pay2", false);
					} else {
						model.set("pay2", true);
					}
				}
				grid.getView().refresh(true);
				total.reset();
			}
		});
		// After edit
		grid.getStore().addStoreListener(new StoreListener<ModelData>() {
			@Override
			public void storeUpdate(StoreEvent<ModelData> se) {
				super.storeUpdate(se);
				payCheckChange(se.getModel());
			}
		});

		form.add(gridPanel);

		// Total to pay
		row = new RowContainer();
		row.setStyleAttribute("margin-top", "10px");

		label = new MyLabel("Total:", LABEL_WIDTH);
		row.add(label);

		total = new MyNumberField(100);
		total.setEditable(false);
		total.setPropertyEditorType(BigDecimal.class);
		total.setValue(0);
		row.add(total);

		form.add(row);

		add(form);
	}

	private void payCheckChange(ModelData model) {
		Record rec = grid.getStore().getRecord(model);
		if (((Boolean) model.get("pay")) != null && ((Boolean) model.get("pay")) == true) {
			rec.set("paymentDate", DatesManager.getCurrentDate());
		} else {
			rec.reject(true);
		}

		// Sum total for pay
		BigDecimal totalSum = BigDecimal.ZERO;

		for (int i = 0; i < grid.getStore().getCount(); i++) {
			ModelData model2 = grid.getStore().getAt(i);
			Boolean pay = (Boolean) model2.get("pay");
			if (pay == null) {
				pay = false;
			}
			if (pay == true) {
				totalSum = totalSum.add((BigDecimal) model2.get("fixedQuota"));
			}
		}
		total.setValue(totalSum);
	}
}