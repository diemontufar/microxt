package mobile.web.webxt.client.devform;

import java.util.ArrayList;
import java.util.List;

import mobile.web.webxt.client.data.MyHttpProxy;
import mobile.web.webxt.client.data.MyListStore;
import mobile.web.webxt.client.data.MyPagingLoader;
import mobile.web.webxt.client.data.MyProcessConfig;
import mobile.web.webxt.client.gridtools.ArrayColumnData;
import mobile.web.webxt.client.gridtools.ComboColumn;
import mobile.web.webxt.client.gridtools.EntityContentPanel;
import mobile.web.webxt.client.gridtools.EntityEditorGrid;
import mobile.web.webxt.client.gridtools.ExpireColumnConfig;
import mobile.web.webxt.client.gridtools.GridPagingToolBar;
import mobile.web.webxt.client.gridtools.GridToolBar;
import mobile.web.webxt.client.gridtools.MyColumnData;
import mobile.web.webxt.client.gridtools.MyColumnData.NumberType;
import mobile.web.webxt.client.gridtools.NormalColumn;
import mobile.web.webxt.client.gridtools.NumericColumn;

import com.extjs.gxt.ui.client.Style.SortDir;
import com.extjs.gxt.ui.client.event.BaseEvent;
import com.extjs.gxt.ui.client.event.Events;
import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.widget.LayoutContainer;
import com.extjs.gxt.ui.client.widget.grid.ColumnConfig;
import com.extjs.gxt.ui.client.widget.grid.ColumnModel;
import com.extjs.gxt.ui.client.widget.layout.CenterLayout;
import com.google.gwt.user.client.Element;

public class C104 extends LayoutContainer {

	private final String PROCESS = "C104";

	private final String ENTITY = "ProductMicrocredit";

	private final Integer PAGE_SIZE = 5;

	@Override
	protected void onRender(Element parent, int index) {
		super.onRender(parent, index);
		setLayout(new CenterLayout());
		getAriaSupport().setPresentation(true);

		// Configuration
		final ArrayColumnData cdata = new ArrayColumnData();
		cdata.add(new MyColumnData("pk_productId", "Cod", 50, 3, false));
		cdata.add(new MyColumnData("description", "Descripcion", 150, 50, false));
		cdata.add(new MyColumnData("currencyId", "Moneda", 50, 3, false));
		cdata.add(new MyColumnData("minAmount", "Monto Min.", 80, 20, false, NumberType.DECIMAL));
		cdata.add(new MyColumnData("maxAmount", "Monto Max.", 80, 20, false, NumberType.DECIMAL));
		cdata.add(new MyColumnData("minPeriod", "Per Min.", 80, 20, false, NumberType.INTEGER));
		cdata.add(new MyColumnData("maxPeriod", "Per Max.", 80, 20, false, NumberType.INTEGER));
		cdata.add(new MyColumnData("rate", "Tasa", 80, 20, false, NumberType.DECIMAL));
		
		MyProcessConfig config = new MyProcessConfig(PROCESS, ENTITY,
				cdata.getIdFields());

		// Proxy - loader - store
		MyHttpProxy proxy = new MyHttpProxy();
		final MyPagingLoader loader = new MyPagingLoader(proxy, config);
		final MyListStore store = new MyListStore(loader);

		// Columns
		List<ColumnConfig> configs = new ArrayList<ColumnConfig>();

		configs.add(new NormalColumn(cdata.get(0)));
		configs.add(new NormalColumn(cdata.get(1)));
		
		// Currency combo
		ComboColumn currencyComboCol = new ComboColumn(cdata.get(2));
		ArrayColumnData cdataCombo = new ArrayColumnData();
		cdataCombo.add(new MyColumnData("pk_currencyId", "Moneda", 70));
		cdataCombo.add(new MyColumnData("description", "Descripcion", 200));
		currencyComboCol.setRqData("Currency", cdataCombo);
		configs.add(currencyComboCol);
		
		configs.add(new NumericColumn(cdata.get(3)));
		configs.add(new NumericColumn(cdata.get(4)));
		configs.add(new NumericColumn(cdata.get(5)));
		configs.add(new NumericColumn(cdata.get(6)));
		configs.add(new NumericColumn(cdata.get(7)));
		configs.add(new ExpireColumnConfig());

		ColumnModel cm = new ColumnModel(configs);

		// Content panel
		EntityContentPanel cp = new EntityContentPanel("Productos de Microcredito",
				700, 230);

		// Grid
		final EntityEditorGrid grid = new EntityEditorGrid(store, cm);
		grid.setAutoExpandColumn("description");
		cp.add(grid);
		grid.addListener(Events.Attach, new Listener<BaseEvent>() {
			public void handleEvent(BaseEvent be) {
				store.sort(cdata.getIdFields().get(0), SortDir.ASC);
			}
		});

		// Top tool bar
		GridToolBar toolBar = new GridToolBar(grid, store);
		cp.setTopComponent(toolBar);

		// Paging tool bar
		final GridPagingToolBar pagingToolBar = new GridPagingToolBar(
				PAGE_SIZE, loader);
		cp.setBottomComponent(pagingToolBar);

		add(cp);
	}
}