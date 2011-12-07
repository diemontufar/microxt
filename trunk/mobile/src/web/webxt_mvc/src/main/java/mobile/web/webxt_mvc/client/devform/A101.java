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
import mobile.web.webxt_mvc.client.form.NormalColumn;
import mobile.web.webxt_mvc.client.resources.Resources;

import com.extjs.gxt.ui.client.Style.HorizontalAlignment;
import com.extjs.gxt.ui.client.data.ModelData;
import com.extjs.gxt.ui.client.data.PagingLoadResult;
import com.extjs.gxt.ui.client.event.BaseEvent;
import com.extjs.gxt.ui.client.event.Events;
import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.LayoutContainer;
import com.extjs.gxt.ui.client.widget.grid.ColumnConfig;
import com.extjs.gxt.ui.client.widget.grid.ColumnModel;
import com.extjs.gxt.ui.client.widget.grid.EditorGrid;
import com.extjs.gxt.ui.client.widget.layout.CenterLayout;
import com.extjs.gxt.ui.client.widget.layout.FitLayout;
import com.google.gwt.user.client.Element;

public class A101 extends LayoutContainer {

	private final String process = "A101";
	
	private final String entity = "UserStatus";
	
	private final Integer PAGE_SIZE = 10;

	@Override
	protected void onRender(Element parent, int index) {
		super.onRender(parent, index);
		setLayout(new CenterLayout());
		getAriaSupport().setPresentation(true);

		// Process configuration
		final List<String> lfields = new ArrayList<String>();
		lfields.add("pk_userStatusId");
		lfields.add("name");
		lfields.add("_expire");
		MyProcessConfig config = new MyProcessConfig(process, entity, lfields);
		
		// Proxy - loader - store
		MyHttpProxy<PagingLoadResult<ModelData>> proxy = new MyHttpProxy<PagingLoadResult<ModelData>>();
		final MyPagingLoader<PagingLoadResult<ModelData>> loader = new MyPagingLoader<PagingLoadResult<ModelData>>(
				proxy, config);
		final MyListStore<ModelData> store = new MyListStore<ModelData>(loader);

		// Columns
		List<ColumnConfig> configs = new ArrayList<ColumnConfig>();
		configs.add(new NormalColumn(lfields.get(0), "Estado", 100, 15, false));
		configs.add(new NormalColumn(lfields.get(1), "Nombre", 150, 40, false));
		configs.add(new ExpireColumnConfig());
		ColumnModel cm = new ColumnModel(configs);
		
		// Content panel
		ContentPanel cp = new ContentPanel();
		cp.setHeading("Estados de Usuarios");
		cp.setBodyBorder(true);  
	    cp.setIcon(Resources.ICONS.table());
	    cp.setButtonAlign(HorizontalAlignment.CENTER);
	    cp.setLayout(new FitLayout());
		cp.setSize(600, 300);
		
		// Grid
		final EditorGrid<ModelData> grid = new EditorGrid<ModelData>(store, cm);
		grid.setBorders(false);  
		grid.setAutoExpandColumn("name");
		grid.getView().setEmptyText("No hay datos");
		grid.setLoadMask(true);
		grid.setStripeRows(true);
		cp.add(grid);
		grid.addListener(Events.Attach, new Listener<BaseEvent>() {
			public void handleEvent(BaseEvent be) {
				// loader.load(0, PAGE_SIZE);
				// store.sort(lfields.get(1), SortDir.ASC);
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