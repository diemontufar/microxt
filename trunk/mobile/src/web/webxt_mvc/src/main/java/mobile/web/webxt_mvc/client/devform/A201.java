package mobile.web.webxt_mvc.client.devform;

import java.util.ArrayList;
import java.util.List;

import mobile.web.webxt_mvc.client.cgrid.ExpireColumnConfig;
import mobile.web.webxt_mvc.client.cgrid.GridPagingToolBar;
import mobile.web.webxt_mvc.client.cgrid.GridToolBar;
import mobile.web.webxt_mvc.client.data.MyHttpProxy;
import mobile.web.webxt_mvc.client.data.MyListStore;
import mobile.web.webxt_mvc.client.data.MyPagingLoader;
import mobile.web.webxt_mvc.client.data.MyProcessConfig;
import mobile.web.webxt_mvc.client.form.ArrayColumnData;
import mobile.web.webxt_mvc.client.form.EntityContentPanel;
import mobile.web.webxt_mvc.client.form.EntityEditorGrid;
import mobile.web.webxt_mvc.client.form.MyColumnData;
import mobile.web.webxt_mvc.client.form.NormalColumn;

import com.extjs.gxt.ui.client.Style.SortDir;
import com.extjs.gxt.ui.client.data.ModelData;
import com.extjs.gxt.ui.client.data.PagingLoadResult;
import com.extjs.gxt.ui.client.event.BaseEvent;
import com.extjs.gxt.ui.client.event.Events;
import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.widget.LayoutContainer;
import com.extjs.gxt.ui.client.widget.grid.ColumnConfig;
import com.extjs.gxt.ui.client.widget.grid.ColumnModel;
import com.extjs.gxt.ui.client.widget.layout.CenterLayout;
import com.google.gwt.user.client.Element;


/**
 * Roles
 */
public class A201 extends LayoutContainer {

	private final String process = "A201";
	
	private final String entity = "Profile";

	private final Integer PAGE_SIZE = 10;
	
	@Override
	protected void onRender(Element parent, int index) {
		super.onRender(parent, index);
		setLayout(new CenterLayout());
		getAriaSupport().setPresentation(true);

		// Process configuration
		final ArrayColumnData cdata = new ArrayColumnData();
		cdata.add(new MyColumnData("pk_profileId", "Rol", 70, 4, false));
		cdata.add(new MyColumnData("name", "Nombre", 150, 40, false));
		cdata.add(new MyColumnData("description", "Descripcion", 200, 150, true));

		MyProcessConfig config = new MyProcessConfig(process, entity,
				cdata.getIdFields());

		// Proxy - loader - store
		MyHttpProxy<PagingLoadResult<ModelData>> proxy = new MyHttpProxy<PagingLoadResult<ModelData>>();
		final MyPagingLoader<PagingLoadResult<ModelData>> loader = new MyPagingLoader<PagingLoadResult<ModelData>>(
				proxy, config);
		final MyListStore<ModelData> store = new MyListStore<ModelData>(loader);

		// Columns
		List<ColumnConfig> configs = new ArrayList<ColumnConfig>();

		configs.add(new NormalColumn(cdata.get(0)));
		configs.add(new NormalColumn(cdata.get(1)));
		configs.add(new NormalColumn(cdata.get(2)));
		configs.add(new ExpireColumnConfig());

		ColumnModel cm = new ColumnModel(configs);
		
		// Content panel
		EntityContentPanel cp = new EntityContentPanel("Roles de usuario",600,340);
		
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
		final GridPagingToolBar pagingToolBar = new GridPagingToolBar(PAGE_SIZE,loader);
		cp.setBottomComponent(pagingToolBar);

		add(cp);
	}
}