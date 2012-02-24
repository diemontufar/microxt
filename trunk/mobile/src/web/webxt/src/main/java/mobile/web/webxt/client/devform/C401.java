package mobile.web.webxt.client.devform;

import java.util.ArrayList;
import java.util.List;

import mobile.web.webxt.client.form.EntityContentPanel;
import mobile.web.webxt.client.form.MyGeneralForm;
import mobile.web.webxt.client.form.widgetsgrid.ArrayColumnData;
import mobile.web.webxt.client.form.widgetsgrid.EntityGrid;
import mobile.web.webxt.client.form.widgetsgrid.GridPagingToolBar;
import mobile.web.webxt.client.form.widgetsgrid.GridToolBar;
import mobile.web.webxt.client.form.widgetsgrid.MyColumnData;
import mobile.web.webxt.client.form.widgetsgrid.NormalColumn;

import com.extjs.gxt.ui.client.Style.SortDir;
import com.extjs.gxt.ui.client.event.BaseEvent;
import com.extjs.gxt.ui.client.event.Events;
import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.widget.grid.ColumnConfig;
import com.extjs.gxt.ui.client.widget.grid.ColumnModel;
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

		// Configuration
		final ArrayColumnData cdata = new ArrayColumnData();
		cdata.add(new MyColumnData("pk_solicitudeId", "Cod", 50, true));
		cdata.add(new MyColumnData("assessor", "Asesor", 100, true));
		cdata.add(new MyColumnData("solicitudeDate", "F. solicitud", 70, true));
		cdata.add(new MyColumnData("productId", "Prod", 50, true));
		cdata.add(new MyColumnData("statusId", "Estat", 50, true));
		cdata.add(new MyColumnData("amount", "Monto", 80, true));
		cdata.add(new MyColumnData("term", "Plazo", 50, true));
		cdata.add(new MyColumnData("quotaTypeId", "T. Cuota", 60, true));
		cdata.add(new MyColumnData("numberQuotas", "Nro Cuotas", 60, true));
		cdata.add(new MyColumnData("paymentFrequencyId", "Freq pago", 60, true));
		cdata.add(new MyColumnData("fundsDestinationId", "Destino", 80, true));
		getConfig().setlDataSource(cdata.getDataSources());

		// Columns
		List<ColumnConfig> configs = new ArrayList<ColumnConfig>();

		configs.add(new NormalColumn(cdata.get(0)));
		configs.add(new NormalColumn(cdata.get(1)));
		configs.add(new NormalColumn(cdata.get(2)));
		configs.add(new NormalColumn(cdata.get(3)));
		configs.add(new NormalColumn(cdata.get(4)));
		configs.add(new NormalColumn(cdata.get(5)));
		configs.add(new NormalColumn(cdata.get(6)));
		configs.add(new NormalColumn(cdata.get(7)));
		configs.add(new NormalColumn(cdata.get(8)));
		configs.add(new NormalColumn(cdata.get(9)));
		configs.add(new NormalColumn(cdata.get(10)));

		ColumnModel cm = new ColumnModel(configs);

		// Content panel
		EntityContentPanel cp = new EntityContentPanel("Consulta de Solicitudes", 700, 340);

		// Grid
		final EntityGrid grid = new EntityGrid(getStore(), cm);
		grid.setAutoExpandColumn("assessor");
		cp.add(grid);
		grid.addListener(Events.Attach, new Listener<BaseEvent>() {
			public void handleEvent(BaseEvent be) {
				getStore().sort(cdata.getIdFields().get(0), SortDir.ASC);
			}
		});

		// Top tool bar
		GridToolBar toolBar = new GridToolBar(grid, getStore());
		toolBar.setEnabled(false);
		toolBar.setVisible(false);
		cp.setTopComponent(toolBar);

		// Paging tool bar
		final GridPagingToolBar pagingToolBar = new GridPagingToolBar(grid, PAGE_SIZE);
		cp.setBottomComponent(pagingToolBar);

		add(cp);
	}
}