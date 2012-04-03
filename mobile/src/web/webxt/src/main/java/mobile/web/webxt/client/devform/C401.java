package mobile.web.webxt.client.devform;

import java.util.ArrayList;
import java.util.List;

import mobile.web.webxt.client.form.EntityContentPanel;
import mobile.web.webxt.client.form.MyGeneralForm;
import mobile.web.webxt.client.form.widgetsgrid.ArrayColumnData;
import mobile.web.webxt.client.form.widgetsgrid.DateColumn;
import mobile.web.webxt.client.form.widgetsgrid.EntityGrid;
import mobile.web.webxt.client.form.widgetsgrid.GridPagingToolBar;
import mobile.web.webxt.client.form.widgetsgrid.MyColumnData;
import mobile.web.webxt.client.form.widgetsgrid.NormalColumn;
import mobile.web.webxt.client.form.widgetsgrid.NumericColumn;
import mobile.web.webxt.client.util.NumberType;

import com.extjs.gxt.ui.client.Style.SortDir;
import com.extjs.gxt.ui.client.data.BaseModelData;
import com.extjs.gxt.ui.client.data.ModelData;
import com.extjs.gxt.ui.client.event.BaseEvent;
import com.extjs.gxt.ui.client.event.Events;
import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.store.ListStore;
import com.extjs.gxt.ui.client.widget.grid.ColumnConfig;
import com.extjs.gxt.ui.client.widget.grid.ColumnModel;
import com.extjs.gxt.ui.client.widget.grid.filters.DateFilter;
import com.extjs.gxt.ui.client.widget.grid.filters.GridFilters;
import com.extjs.gxt.ui.client.widget.grid.filters.ListFilter;
import com.extjs.gxt.ui.client.widget.grid.filters.NumericFilter;
import com.extjs.gxt.ui.client.widget.grid.filters.StringFilter;
import com.google.gwt.user.client.Element;

public class C401 extends MyGeneralForm {

	private final static String PROCESS = "C401";
	private final static String ENTITY = "Solicitude";
	private final Integer PAGE_SIZE = 10;

	public C401() {
		super(PROCESS, true);
		setReference(ENTITY);
	}

	@Override
	protected void onRender(Element parent, int index) {
		super.onRender(parent, index);

		// Columns configuration
		final ArrayColumnData cdata = new ArrayColumnData();
		cdata.add(new MyColumnData("solicitudeId", "Cod", 50, true));
		cdata.add(new MyColumnData("assessor", "Asesor", 80, true));
		cdata.add(new MyColumnData("tipoCliente", "Tipo cliente", 75, true));
		cdata.add(new MyColumnData("nombreCliente", "Nombre", 250, true));
		cdata.add(new MyColumnData("solicitudeDate", "F. solicitud", 80, true));
		cdata.add(new MyColumnData("productId", "Prod", 50, true));
		cdata.add(new MyColumnData("status", "Estatus", 75, true));
		cdata.add(new MyColumnData("instrumented", "Instrumentada?", 75, true));
		cdata.add(new MyColumnData("amount", "Monto", 60, true));
		cdata.add(new MyColumnData("term", "Plazo", 60, true));
		cdata.add(new MyColumnData("quotaTypeId", "T. Cuota", 60, true));
		cdata.add(new MyColumnData("freq", "Freq pago", 75, true));
		cdata.add(new MyColumnData("fundsDestination", "Destino", 90, true));
		getConfig().setlDataSource(cdata.getDataSources());

		// Columns
		List<ColumnConfig> configs = new ArrayList<ColumnConfig>();

		configs.add(new NormalColumn(cdata.get(0)));
		configs.add(new NormalColumn(cdata.get(1)));
		configs.add(new NormalColumn(cdata.get(2)));
		configs.add(new NormalColumn(cdata.get(3)));
		configs.add(new DateColumn(cdata.get(4)));
		configs.add(new NormalColumn(cdata.get(5)));
		configs.add(new NormalColumn(cdata.get(6)));
		configs.add(new NormalColumn(cdata.get(7)));
		configs.add(new NumericColumn(cdata.get(8), NumberType.DECIMAL));
		configs.add(new NumericColumn(cdata.get(9), NumberType.INTEGER));
		configs.add(new NormalColumn(cdata.get(10)));
		configs.add(new NormalColumn(cdata.get(11)));
		configs.add(new NormalColumn(cdata.get(12)));

		ColumnModel cm = new ColumnModel(configs);

		// Filters
		GridFilters filters = new GridFilters();
		StringFilter idFilter = new StringFilter("solicitudeId");
		StringFilter nameFilter = new StringFilter("nombreCliente");
		DateFilter dateFilter = new DateFilter("solicitudeDate");
		NumericFilter amountFilter = new NumericFilter("amount");
		NumericFilter termFilter = new NumericFilter("term");

		ListStore<ModelData> clientTypeStore = new ListStore<ModelData>();
		ModelData model = new BaseModelData();
		model.set("tipoCliente", "INDIVIDUAL");
		clientTypeStore.add(model);
		model = new BaseModelData();
		model.set("tipoCliente", "GRUPAL");
		clientTypeStore.add(model);
		ListFilter typeListFilter = new ListFilter("tipoCliente", clientTypeStore);
		typeListFilter.setDisplayProperty("tipoCliente");
		
		ListStore<ModelData> statusStore = new ListStore<ModelData>();
		model = new BaseModelData();
		model.set("status", "SOLICITADA");
		statusStore.add(model);
		model = new BaseModelData();
		model.set("status", "APROVADA");
		statusStore.add(model);
		model = new BaseModelData();
		model.set("status", "DENEGADA");
		statusStore.add(model);
		ListFilter statusListFilter = new ListFilter("status", statusStore);
		statusListFilter.setDisplayProperty("status");
		
		ListStore<ModelData> instrumentedStore = new ListStore<ModelData>();
		model = new BaseModelData();
		model.set("instrumented", "SI");
		instrumentedStore.add(model);
		model = new BaseModelData();
		model.set("instrumented", "NO");
		instrumentedStore.add(model);
		ListFilter instrumentedFilter = new ListFilter("instrumented", instrumentedStore);
		instrumentedFilter.setDisplayProperty("instrumented");

		filters.addFilter(idFilter);
		filters.addFilter(nameFilter);
		filters.addFilter(dateFilter);
		filters.addFilter(amountFilter);
		filters.addFilter(termFilter);
		filters.addFilter(typeListFilter);
		filters.addFilter(statusListFilter);
		filters.addFilter(instrumentedFilter);

		// Content panel
		EntityContentPanel cp = new EntityContentPanel("Consulta de Solicitudes", 700, 340);

		// Grid
		final EntityGrid grid = new EntityGrid(getStore(), cm);
		// grid.setAutoExpandColumn("assessor");
		grid.addPlugin(filters);
		cp.add(grid);
		grid.addListener(Events.Attach, new Listener<BaseEvent>() {
			public void handleEvent(BaseEvent be) {
				getStore().sort(cdata.getIdFields().get(0), SortDir.ASC);
			}
		});

		// Paging tool bar
		final GridPagingToolBar pagingToolBar = new GridPagingToolBar(grid, PAGE_SIZE);
		cp.setBottomComponent(pagingToolBar);

		add(cp);

	}
}