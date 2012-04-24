package mobile.web.webxt.client.devform;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import mobile.common.tools.Format;
import mobile.web.webxt.client.data.form.DataSource;
import mobile.web.webxt.client.data.form.DataSourceType;
import mobile.web.webxt.client.form.EntityContentPanel;
import mobile.web.webxt.client.form.MyFormPanel;
import mobile.web.webxt.client.form.MyGeneralForm;
import mobile.web.webxt.client.form.widgets.MyDateField;
import mobile.web.webxt.client.form.widgets.MyLabel;
import mobile.web.webxt.client.form.widgets.MyNumberField;
import mobile.web.webxt.client.form.widgets.RowContainer;
import mobile.web.webxt.client.form.widgetsgrid.ArrayColumnData;
import mobile.web.webxt.client.form.widgetsgrid.EntityGrid;
import mobile.web.webxt.client.form.widgetsgrid.GridPagingToolBar;
import mobile.web.webxt.client.form.widgetsgrid.GridToolBar;
import mobile.web.webxt.client.form.widgetsgrid.MyColumnData;
import mobile.web.webxt.client.form.widgetsgrid.NormalColumn;
import mobile.web.webxt.client.form.widgetsgrid.NumericColumn;
import mobile.web.webxt.client.util.NumberType;

import com.extjs.gxt.ui.client.Style.HorizontalAlignment;
import com.extjs.gxt.ui.client.data.LoadEvent;
import com.extjs.gxt.ui.client.data.ModelData;
import com.extjs.gxt.ui.client.event.Events;
import com.extjs.gxt.ui.client.event.FieldEvent;
import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.event.LoadListener;
import com.extjs.gxt.ui.client.store.ListStore;
import com.extjs.gxt.ui.client.widget.grid.ColumnConfig;
import com.extjs.gxt.ui.client.widget.grid.ColumnData;
import com.extjs.gxt.ui.client.widget.grid.ColumnModel;
import com.extjs.gxt.ui.client.widget.grid.Grid;
import com.extjs.gxt.ui.client.widget.grid.GridCellRenderer;
import com.extjs.gxt.ui.client.widget.layout.FlowLayout;
import com.google.gwt.event.logical.shared.AttachEvent;
import com.google.gwt.i18n.client.NumberFormat;
import com.google.gwt.user.client.Element;

public class C504 extends MyGeneralForm {

	private final static String PROCESS = "C504";
	private final static String ENTITY = "MicroAccountQuota";
	private final Integer PAGE_SIZE = 10;

	MyNumberField total;
	
	public C504() {
		super(PROCESS, true);
		setReference(ENTITY);
	}

	@Override
	protected void onRender(Element parent, int index) {
		super.onRender(parent, index);

		// Constants
		final int FORM_WIDTH = 550;
		final int LABEL_WIDTH = 80;

		// Super Form
		final MyFormPanel form = new MyFormPanel(this, "Pagos realizados por día", FORM_WIDTH);
		form.setLayout(new FlowLayout());
		form.setButtonAlign(HorizontalAlignment.CENTER);

		// Header
		// Filter: Payment Date
		RowContainer row = new RowContainer();
		row.setStyleAttribute("margin-bottom", "10px");

		MyLabel label = new MyLabel("Fecha:", LABEL_WIDTH);
		label.setWidth(60);
		row.add(label);

		// Account combo
		final MyDateField paymentDate = new MyDateField(100);
		paymentDate.setFireChangeEventOnSetValue(true);
		paymentDate.setDataSource(new DataSource("paymentDate", DataSourceType.CRITERION));
		//paymentDate.setValue(new Date());

		row.add(paymentDate);

		form.add(row);

		// Grid Configurations
		final ArrayColumnData cdata = new ArrayColumnData();
		cdata.add(new MyColumnData("pk_accountId", "Prestamo", 70, true));
		cdata.add(new MyColumnData("pk_subaccount", "No", 40, true));
		
		DataSource ds = new DataSource("MicroAccount", "clientName", DataSourceType.DESCRIPTION);
		MyColumnData clientColumn = new MyColumnData(ds, "Cliente", 200);
		clientColumn.setReadOnly(true);
		cdata.add(clientColumn);
		
		cdata.add(new MyColumnData("capital", "Capital", 60, true));
		cdata.add(new MyColumnData("interest", "Interes", 60, true));
		MyColumnData quotaColumn = new MyColumnData();
		quotaColumn.setId("fixedQuota");
		quotaColumn.setName("Cuota");
		quotaColumn.setWidth(60);
		cdata.add(quotaColumn);
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

		List<ColumnConfig> configs = new ArrayList<ColumnConfig>();

		configs.add(new NormalColumn(cdata.get(0)));
		configs.add(new NormalColumn(cdata.get(1)));
		configs.add(new NormalColumn(cdata.get(2)));
		configs.add(new NumericColumn(cdata.get(3), NumberType.DECIMAL));
		configs.add(new NumericColumn(cdata.get(4), NumberType.DECIMAL));
		NumericColumn quotaCol = new NumericColumn(cdata.get(5), NumberType.DECIMAL);
		quotaCol.setRenderer(quotaRenderer);
		configs.add(quotaCol);

		ColumnModel cm = new ColumnModel(configs);

		// Grid panel
		EntityContentPanel gridPanel = new EntityContentPanel(FORM_WIDTH - 30, 230);

		// Grid
		final EntityGrid grid = new EntityGrid(getStore(), cm);
		grid.setAutoExpandColumn("pk_accountId");
		grid.setBorders(true);
		grid.addDependency(paymentDate);
		
		grid.addAttachHandler(new AttachEvent.Handler() {
			public void onAttachOrDetach(AttachEvent event) {
				paymentDate.setValue(new Date());
			}
		});

		gridPanel.add(grid);

		// Top tool bar
		GridToolBar toolBar = new GridToolBar(grid, getStore());
		toolBar.setVisible(false);
		gridPanel.setTopComponent(toolBar);

		// Paging tool bar
		
		final GridPagingToolBar pagingToolBar = new GridPagingToolBar(grid, PAGE_SIZE);
		gridPanel.setBottomComponent(pagingToolBar);

		// Operations
		paymentDate.addListener(Events.Change, new Listener<FieldEvent>() {
			public void handleEvent(FieldEvent be) {
				if (((MyDateField) be.getSource()).getValue() != null) {
					pagingToolBar.refresh();
				} else {
					grid.getStore().removeAll();
				}
			};
		});

		// On load
		grid.getStore().getLoader().addLoadListener(new LoadListener() {
			@Override
			public void loaderLoad(LoadEvent le) {
				super.loaderLoad(le);
				BigDecimal totalSum = BigDecimal.ZERO;
				for (int i = 0; i < grid.getStore().getCount(); i++) {
					ModelData model = grid.getStore().getAt(i);
					BigDecimal capital = model.get("capital");
					BigDecimal interest = model.get("interest");
					BigDecimal quota = capital.add(interest);
					quota.setScale(2, RoundingMode.HALF_UP);
					totalSum = totalSum.add(quota);
				}
				total.setValue(totalSum);
			}
		});

		form.add(gridPanel);

		// Total to pay
		row = new RowContainer();
		row.setStyleAttribute("margin-top", "10px");

		label = new MyLabel("Total:", 60);
		row.add(label);

		total = new MyNumberField(100);
		total.setEditable(false);
		total.setPropertyEditorType(BigDecimal.class);
		total.setValue(0);
		row.add(total);

		form.add(row);

		add(form);
	}
}