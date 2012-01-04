package mobile.web.webxt_mvc.client.devform;

import java.util.ArrayList;
import java.util.List;

import mobile.web.webxt_mvc.client.data.MyHttpProxy;
import mobile.web.webxt_mvc.client.data.MyListStore;
import mobile.web.webxt_mvc.client.data.MyPagingLoader;
import mobile.web.webxt_mvc.client.data.MyProcessConfig;
import mobile.web.webxt_mvc.client.form.EntityContentPanel;
import mobile.web.webxt_mvc.client.gridtools.ArrayColumnData;
import mobile.web.webxt_mvc.client.gridtools.ComboColumn;
import mobile.web.webxt_mvc.client.gridtools.EntityEditorGrid;
import mobile.web.webxt_mvc.client.gridtools.ExpireColumnConfig;
import mobile.web.webxt_mvc.client.gridtools.GridPagingToolBar;
import mobile.web.webxt_mvc.client.gridtools.GridToolBar;
import mobile.web.webxt_mvc.client.gridtools.MyColumnData;

import com.extjs.gxt.ui.client.Style.SortDir;
import com.extjs.gxt.ui.client.event.BaseEvent;
import com.extjs.gxt.ui.client.event.Events;
import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.widget.LayoutContainer;
import com.extjs.gxt.ui.client.widget.grid.ColumnConfig;
import com.extjs.gxt.ui.client.widget.grid.ColumnModel;
import com.extjs.gxt.ui.client.widget.grid.filters.GridFilters;
import com.extjs.gxt.ui.client.widget.grid.filters.StringFilter;
import com.extjs.gxt.ui.client.widget.layout.CenterLayout;
import com.google.gwt.user.client.Element;

public class A204 extends LayoutContainer {

private final Integer PAGE_SIZE = 5;
	
	private final String process = "A204";
	

	@Override
	protected void onRender(Element parent, int index) {
		super.onRender(parent, index);
		setLayout(new CenterLayout());
		getAriaSupport().setPresentation(true);
				
		// Config
		String entity = "UserProfile";

		final ArrayColumnData cdata = new ArrayColumnData();
		cdata.add(new MyColumnData("pk_userId", "Usuario", 100, 40,false));
		cdata.add(new MyColumnData("pk_profileId", "Perfil", 150, 40, false));
		
		MyProcessConfig config = new MyProcessConfig(process, entity,cdata.getIdFields());
		
		// Proxy - loader - store
		MyHttpProxy proxy = new MyHttpProxy();
		final MyPagingLoader loader = new MyPagingLoader(proxy, config);
		final MyListStore store = new MyListStore(loader);
		
		// Column model
		List<ColumnConfig> configs = new ArrayList<ColumnConfig>();

		ComboColumn personComboColumn = new ComboColumn(cdata.get(0));
		ArrayColumnData cdataComboPerson = new ArrayColumnData();
		cdataComboPerson.add(new MyColumnData("pk_userId", "ID", 40));
		cdataComboPerson.add(new MyColumnData("name", "Nombre", 150));
		personComboColumn.setRqData("UserAccount", cdataComboPerson);
		configs.add(personComboColumn);
		
		ComboColumn profileComboColumn = new ComboColumn(cdata.get(1));
		ArrayColumnData cdataComboProfile = new ArrayColumnData();
		cdataComboProfile.add(new MyColumnData("pk_profileId", "ID", 40));
		cdataComboProfile.add(new MyColumnData("name", "Nombre", 150));
		profileComboColumn.setRqData("Profile", cdataComboProfile);
		configs.add(profileComboColumn);
		

		configs.add(new ExpireColumnConfig());
		
		ColumnModel cm = new ColumnModel(configs);
		
		// Filters
		GridFilters filters = new GridFilters();
		StringFilter parameterIdFilter = new StringFilter(cdata.getIdFields().get(0));
		StringFilter subsystemFilter = new StringFilter(cdata.getIdFields().get(1));
		filters.addFilter(parameterIdFilter);
		filters.addFilter(subsystemFilter);
		
		// Content panel
		EntityContentPanel cp = new EntityContentPanel("Roles por Usuario",400,300);

		// Grid
		final EntityEditorGrid grid = new EntityEditorGrid(store, cm);
		grid.setAutoExpandColumn("pk_userId");
		grid.addPlugin(filters);
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