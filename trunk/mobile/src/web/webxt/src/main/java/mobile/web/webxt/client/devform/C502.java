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
import mobile.web.webxt.client.form.widgets.RowContainer;
import mobile.web.webxt.client.form.widgetsgrid.ArrayColumnData;
import mobile.web.webxt.client.form.widgetsgrid.DateColumn;
import mobile.web.webxt.client.form.widgetsgrid.EntityGrid;
import mobile.web.webxt.client.form.widgetsgrid.GridPagingToolBar;
import mobile.web.webxt.client.form.widgetsgrid.GridToolBar;
import mobile.web.webxt.client.form.widgetsgrid.MyColumnData;
import mobile.web.webxt.client.form.widgetsgrid.NormalColumn;
import mobile.web.webxt.client.form.widgetsgrid.NumericColumn;
import mobile.web.webxt.client.util.NumberType;

import com.extjs.gxt.ui.client.Style.HorizontalAlignment;
import com.extjs.gxt.ui.client.data.ModelData;
import com.extjs.gxt.ui.client.event.SelectionChangedEvent;
import com.extjs.gxt.ui.client.event.SelectionChangedListener;
import com.extjs.gxt.ui.client.store.ListStore;
import com.extjs.gxt.ui.client.widget.grid.ColumnConfig;
import com.extjs.gxt.ui.client.widget.grid.ColumnData;
import com.extjs.gxt.ui.client.widget.grid.ColumnModel;
import com.extjs.gxt.ui.client.widget.grid.Grid;
import com.extjs.gxt.ui.client.widget.grid.GridCellRenderer;
import com.extjs.gxt.ui.client.widget.layout.FlowLayout;
import com.google.gwt.i18n.client.NumberFormat;
import com.google.gwt.user.client.Element;

public class C502 extends MyGeneralForm {

	private final static String PROCESS = "C502";
	private final static String ENTITY = "MicroAccountQuota";
	private final Integer PAGE_SIZE = 10;

	public C502() {
		super(PROCESS, true);
		setReference(ENTITY);
	}

	@Override
	protected void onRender(Element parent, int index) {
		super.onRender(parent, index);

		// Constants
		final int FORM_WIDTH = 650;
		final int LABEL_WIDTH = 80;

		// Super Form
		final MyFormPanel form = new MyFormPanel(this, "Consulta de Cuotas", FORM_WIDTH);
		form.setLayout(new FlowLayout());
		form.setButtonAlign(HorizontalAlignment.CENTER);

		// Header
		// Filter: ACcount
		RowContainer row = new RowContainer();
		row.setStyleAttribute("margin-bottom", "10px");

		MyLabel label = new MyLabel("Cuenta:", LABEL_WIDTH);
		label.setWidth(60);
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

		// Subsystem description
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
				return NumberFormat.getFormat(Format.DECIMAL).format(fixedQuota);
			}
		};
		GridCellRenderer<ModelData> statRenderer = new GridCellRenderer<ModelData>() {
			public String render(ModelData model, String property, ColumnData config, int rowIndex, int colIndex,
					ListStore<ModelData> store, Grid<ModelData> grid) {
				Date paymentDate = (Date) model.get("paymentDate");
				String status = "";
				if(paymentDate!=null){
					status = "PAGADA";
				}else{
					status = "PENDIENTE";
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

		ColumnModel cm = new ColumnModel(configs);

		// Grid panel
		EntityContentPanel gridPanel = new EntityContentPanel(FORM_WIDTH - 30, 230);

		// Grid
		final EntityGrid grid = new EntityGrid(getStore(), cm);
		grid.setAutoExpandColumn("status");
		grid.setBorders(true);
		grid.addDependency(accountId);

		gridPanel.add(grid);

		// Top tool bar
		GridToolBar toolBar = new GridToolBar(grid, getStore());
		toolBar.setVisible(false);
		gridPanel.setTopComponent(toolBar);

		// Paging tool bar
		final GridPagingToolBar pagingToolBar = new GridPagingToolBar(grid, PAGE_SIZE);
		gridPanel.setBottomComponent(pagingToolBar);

		// Operations
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

		form.add(gridPanel);
		add(form);
	}
}