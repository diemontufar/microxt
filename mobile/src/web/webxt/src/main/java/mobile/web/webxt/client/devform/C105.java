package mobile.web.webxt.client.devform;

import java.util.ArrayList;
import java.util.List;

import mobile.web.webxt.client.data.MyHttpProxy;
import mobile.web.webxt.client.data.MyListStore;
import mobile.web.webxt.client.data.MyPagingLoader;
import mobile.web.webxt.client.data.MyProcessConfig;
import mobile.web.webxt.client.form.EntityContentPanel;
import mobile.web.webxt.client.form.widgetsgrid.ArrayColumnData;
import mobile.web.webxt.client.form.widgetsgrid.ComboColumn;
import mobile.web.webxt.client.form.widgetsgrid.EntityEditorGrid;
import mobile.web.webxt.client.form.widgetsgrid.ExpireColumnConfig;
import mobile.web.webxt.client.form.widgetsgrid.GridPagingToolBar;
import mobile.web.webxt.client.form.widgetsgrid.GridToolBar;
import mobile.web.webxt.client.form.widgetsgrid.MyColumnData;
import mobile.web.webxt.client.form.widgetsgrid.NumericColumn;

import com.extjs.gxt.ui.client.Style.SortDir;
import com.extjs.gxt.ui.client.event.BaseEvent;
import com.extjs.gxt.ui.client.event.Events;
import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.widget.LayoutContainer;
import com.extjs.gxt.ui.client.widget.grid.ColumnConfig;
import com.extjs.gxt.ui.client.widget.grid.ColumnModel;
import com.extjs.gxt.ui.client.widget.layout.CenterLayout;
import com.google.gwt.user.client.Element;

public class C105 extends LayoutContainer {

	private final String PROCESS = "C105";

	private final String ENTITY = "ProductAsessor";

	private final Integer PAGE_SIZE = 5;

	@Override
	protected void onRender(Element parent, int index) {
		super.onRender(parent, index);
		setLayout(new CenterLayout());
		getAriaSupport().setPresentation(true);

		// Configuration
		final ArrayColumnData cdata = new ArrayColumnData();
		cdata.add(new MyColumnData("pk_asessorId", "Asesor", 50, 3, false));
		cdata.add(new MyColumnData("pk_productId", "Producto", 50, 50, false));
		cdata.add(new MyColumnData("observations", "Observaciones", 150, 50, true));
		
		MyProcessConfig config = new MyProcessConfig(PROCESS, ENTITY,
				cdata.getIdFields());

		// Proxy - loader - store
		MyHttpProxy proxy = new MyHttpProxy();
		final MyPagingLoader loader = new MyPagingLoader(proxy, config);
		final MyListStore store = new MyListStore(loader);

		// Columns
		List<ColumnConfig> configs = new ArrayList<ColumnConfig>();

		ComboColumn profileComboColumn = new ComboColumn(cdata.get(0));
		ArrayColumnData cdataComboProfile = new ArrayColumnData();
		cdataComboProfile.add(new MyColumnData("pk_personId", "Persona", 40));
		cdataComboProfile.add(new MyColumnData("identificationNumber", "Identificacion", 100));
		cdataComboProfile.add(new MyColumnData("name", "Nombre", 110));
		cdataComboProfile.add(new MyColumnData("lastName", "Apellido", 120));
		profileComboColumn.setRqData("Person", cdataComboProfile);
		configs.add(profileComboColumn);
		
		ComboColumn productComboColumn = new ComboColumn(cdata.get(1));
		ArrayColumnData cdataComboPrduct = new ArrayColumnData();
		cdataComboPrduct.add(new MyColumnData("pk_productId", "Producto", 40));
		cdataComboPrduct.add(new MyColumnData("description", "Descripcion", 100));
		productComboColumn.setRqData("Person", cdataComboPrduct);
		configs.add(productComboColumn);
		
		configs.add(new NumericColumn(cdata.get(2)));
		
		configs.add(new ExpireColumnConfig());

		ColumnModel cm = new ColumnModel(configs);

		// Content panel
		EntityContentPanel cp = new EntityContentPanel("Productos por Asesor",
				500, 230);

		// Grid
		final EntityEditorGrid grid = new EntityEditorGrid(store, cm);
		grid.setAutoExpandColumn("pk_asessorId");
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