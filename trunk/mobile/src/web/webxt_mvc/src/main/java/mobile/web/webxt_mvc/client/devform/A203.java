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
import mobile.web.webxt_mvc.client.form.ComboColumn;
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
import com.extjs.gxt.ui.client.widget.grid.filters.GridFilters;
import com.extjs.gxt.ui.client.widget.grid.filters.StringFilter;
import com.extjs.gxt.ui.client.widget.layout.CenterLayout;
import com.google.gwt.user.client.Element;

public class A203 extends LayoutContainer {

	private final Integer PAGE_SIZE = 5;
	
	private final String process = "A203";
	

	@Override
	protected void onRender(Element parent, int index) {
		super.onRender(parent, index);
		setLayout(new CenterLayout());
		getAriaSupport().setPresentation(true);
				
		// Config
		String entity = "UserAccount";

		final ArrayColumnData cdata = new ArrayColumnData();
		cdata.add(new MyColumnData("pk_userId", "ID Usuario", 70, 40,false));
		cdata.add(new MyColumnData("name", "Nombre", 150, 40, false));
		cdata.add(new MyColumnData("userTypeId", "Tipo", 80, 40, false));
		cdata.add(new MyColumnData("userStatusId", "Estado", 70, 40, false));
		cdata.add(new MyColumnData("languageId", "Idioma", 70, 40,false));
		cdata.add(new MyColumnData("email", "Email", 70, 40,false));
		cdata.add(new MyColumnData("personId", "ID persona", 70, 40,false));

		
		MyProcessConfig config = new MyProcessConfig(process, entity,cdata.getIdFields());
		
		// Proxy - loader - store
		MyHttpProxy<PagingLoadResult<ModelData>> proxy = new MyHttpProxy<PagingLoadResult<ModelData>>();
		final MyPagingLoader<PagingLoadResult<ModelData>> loader = new MyPagingLoader<PagingLoadResult<ModelData>>(
				proxy, config);
		final MyListStore<ModelData> store = new MyListStore<ModelData>(loader);

		// Column model
		List<ColumnConfig> configs = new ArrayList<ColumnConfig>();

		configs.add(new NormalColumn(cdata.get(0)));
		configs.add(new NormalColumn(cdata.get(1)));
		
		ComboColumn usertypesComboColumn = new ComboColumn(cdata.get(2));
		ArrayColumnData cdataComboUsertypes = new ArrayColumnData();
		cdataComboUsertypes.add(new MyColumnData("pk_userTypeId", "Tipo", 40));
		cdataComboUsertypes.add(new MyColumnData("name", "Nombre", 150));
		usertypesComboColumn.setRqData("UserType", cdataComboUsertypes);
		configs.add(usertypesComboColumn);
		
		ComboColumn userstatusComboColumn = new ComboColumn(cdata.get(3));
		ArrayColumnData cdataComboUsertatus = new ArrayColumnData();
		cdataComboUsertatus.add(new MyColumnData("pk_userStatusId", "Tipo", 40));
		cdataComboUsertatus.add(new MyColumnData("name", "Nombre", 150));
		userstatusComboColumn.setRqData("UserStatus", cdataComboUsertatus);
		configs.add(userstatusComboColumn);
		
		ComboColumn languageComboColumn = new ComboColumn(cdata.get(4));
		ArrayColumnData cdataComboLanguage = new ArrayColumnData();
		cdataComboLanguage.add(new MyColumnData("languageId", "ID", 40));
		cdataComboLanguage.add(new MyColumnData("name", "Nombre", 150));
		languageComboColumn.setRqData("Language", cdataComboLanguage);
		configs.add(languageComboColumn);
		
		configs.add(new NormalColumn(cdata.get(5)));
		
		ComboColumn personComboColumn = new ComboColumn(cdata.get(6));
		ArrayColumnData cdataComboPerson = new ArrayColumnData();
		cdataComboPerson.add(new MyColumnData("pk_personId", "ID", 40));
		cdataComboPerson.add(new MyColumnData("name", "Nombre", 150));
		cdataComboPerson.add(new MyColumnData("lastName", "Apellido", 150));
		personComboColumn.setRqData("Person", cdataComboPerson);
		configs.add(personComboColumn);
		
		configs.add(new ExpireColumnConfig());
		
		ColumnModel cm = new ColumnModel(configs);
		
		// Filters
		GridFilters filters = new GridFilters();
		StringFilter parameterIdFilter = new StringFilter(cdata.getIdFields().get(0));
		StringFilter subsystemFilter = new StringFilter(cdata.getIdFields().get(1));
		filters.addFilter(parameterIdFilter);
		filters.addFilter(subsystemFilter);
		
		// Content panel
		EntityContentPanel cp = new EntityContentPanel("Usuarios",800,300);

		// Grid
		final EntityEditorGrid grid = new EntityEditorGrid(store, cm);
		grid.setAutoExpandColumn("name");
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