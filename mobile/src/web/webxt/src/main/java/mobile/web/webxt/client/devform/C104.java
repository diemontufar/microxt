package mobile.web.webxt.client.devform;

import java.util.ArrayList;
import java.util.List;

import mobile.web.webxt.client.data.form.Reference;
import mobile.web.webxt.client.form.EntityContentPanel;
import mobile.web.webxt.client.form.MyGeneralForm;
import mobile.web.webxt.client.form.widgetsgrid.ArrayColumnData;
import mobile.web.webxt.client.form.widgetsgrid.ComboColumn;
import mobile.web.webxt.client.form.widgetsgrid.EntityEditorGrid;
import mobile.web.webxt.client.form.widgetsgrid.ExpireColumnConfig;
import mobile.web.webxt.client.form.widgetsgrid.GridPagingToolBar;
import mobile.web.webxt.client.form.widgetsgrid.GridToolBar;
import mobile.web.webxt.client.form.widgetsgrid.MyColumnData;
import mobile.web.webxt.client.form.widgetsgrid.NormalColumn;
import mobile.web.webxt.client.form.widgetsgrid.NumericColumn;
import mobile.web.webxt.client.util.NumberType;

import com.extjs.gxt.ui.client.Style.SortDir;
import com.extjs.gxt.ui.client.event.BaseEvent;
import com.extjs.gxt.ui.client.event.Events;
import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.widget.grid.ColumnConfig;
import com.extjs.gxt.ui.client.widget.grid.ColumnModel;
import com.google.gwt.user.client.Element;

public class C104 extends MyGeneralForm {

	private final static String PROCESS = "C104";
	private final static String ENTITY = "ProductMicrocredit";
	private final Integer PAGE_SIZE = 5;

	public C104() {
		super(PROCESS, true);
		setReference(ENTITY);
	}

	@Override
	protected void onRender(Element parent, int index) {
		super.onRender(parent, index);

		// Configuration
		final ArrayColumnData cdata = new ArrayColumnData();
		cdata.add(new MyColumnData("pk_productId", "Cod", 50, 3, false));
		cdata.add(new MyColumnData("description", "Descripcion", 150, 50, false));
		cdata.add(new MyColumnData("currencyId", "Moneda", 50, 3, false));
		cdata.add(new MyColumnData("minAmount", "Monto Min.", 80, 20, false));
		cdata.add(new MyColumnData("maxAmount", "Monto Max.", 80, 20, false));
		cdata.add(new MyColumnData("minPeriod", "Per Min.", 80, 20, false));
		cdata.add(new MyColumnData("maxPeriod", "Per Max.", 80, 20, false));
		cdata.add(new MyColumnData("rate", "Tasa", 80, 20, false));
		getConfig().setlDataSource(cdata.getDataSources());

		// Columns
		List<ColumnConfig> configs = new ArrayList<ColumnConfig>();

		configs.add(new NormalColumn(cdata.get(0)));
		configs.add(new NormalColumn(cdata.get(1)));

		// Currency combo
		ComboColumn currencyComboCol = new ComboColumn(cdata.get(2));
		Reference refCurrency = new Reference("cur", "Currency");
		ArrayColumnData cdataCombo = new ArrayColumnData();
		cdataCombo.add(new MyColumnData("cur", "pk_currencyId", "Moneda", 70));
		cdataCombo.add(new MyColumnData("cur", "description", "Descripcion", 200));
		currencyComboCol.setQueryData(refCurrency, cdataCombo);
		configs.add(currencyComboCol);

		configs.add(new NormalColumn(cdata.get(3),NumberType.DECIMAL,null));
		configs.add(new NormalColumn(cdata.get(4),NumberType.DECIMAL,null));
		configs.add(new NumericColumn(cdata.get(5)));
		configs.add(new NumericColumn(cdata.get(6)));
		configs.add(new NumericColumn(cdata.get(7)));
		configs.add(new ExpireColumnConfig());

		ColumnModel cm = new ColumnModel(configs);

		// Content panel
		EntityContentPanel cp = new EntityContentPanel("Productos de Microcredito", 700, 230);

		// Grid
		final EntityEditorGrid grid = new EntityEditorGrid(getStore(), cm);
		grid.setAutoExpandColumn("description");
		cp.add(grid);
		grid.addListener(Events.Attach, new Listener<BaseEvent>() {
			public void handleEvent(BaseEvent be) {
				getStore().sort(cdata.getIdFields().get(0), SortDir.ASC);
			}
		});

		// Top tool bar
		GridToolBar toolBar = new GridToolBar(grid, getStore());
		cp.setTopComponent(toolBar);

		// Paging tool bar
		final GridPagingToolBar pagingToolBar = new GridPagingToolBar(grid, PAGE_SIZE);
		cp.setBottomComponent(pagingToolBar);

		add(cp);
	}
}