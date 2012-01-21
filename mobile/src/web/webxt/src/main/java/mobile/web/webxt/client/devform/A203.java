package mobile.web.webxt.client.devform;

import java.util.ArrayList;
import java.util.List;

import mobile.web.webxt.client.data.MyHttpProxy;
import mobile.web.webxt.client.data.MyListStore;
import mobile.web.webxt.client.data.MyPagingLoader;
import mobile.web.webxt.client.data.MyProcessConfig;
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

import com.extjs.gxt.ui.client.Style.SortDir;
import com.extjs.gxt.ui.client.event.BaseEvent;
import com.extjs.gxt.ui.client.event.Events;
import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.widget.grid.ColumnConfig;
import com.extjs.gxt.ui.client.widget.grid.ColumnModel;
import com.extjs.gxt.ui.client.widget.grid.filters.GridFilters;
import com.extjs.gxt.ui.client.widget.grid.filters.StringFilter;
import com.google.gwt.user.client.Element;

public class A203 extends MyGeneralForm {

	private final String PROCESS = "A203";
	private final String ENTITY = "UserAccount";
	private final Integer PAGE_SIZE = 5;
	
	@Override
	protected void onRender(Element parent, int index) {
		super.onRender(parent, index);
				
		// Configuration
		final ArrayColumnData cdata = new ArrayColumnData();
		cdata.add(new MyColumnData("pk_userId", "ID Usuario", 70, 20,false));
		cdata.add(new MyColumnData("name", "Nombre", 120, 40, false));
		cdata.add(new MyColumnData("userTypeId", "Tipo", 70, 4, false));
		cdata.add(new MyColumnData("userStatusId", "Estado", 70, 4, false));
		cdata.add(new MyColumnData("languageId", "Idioma", 70, 4,false));
		cdata.add(new MyColumnData("email", "Email", 120, 100,false));
		cdata.add(new MyColumnData("personId", "ID persona", 70, 10,true));

		
		MyProcessConfig config = new MyProcessConfig(PROCESS, ENTITY,cdata.getIdFields());
		
		// Proxy - loader - store
		MyHttpProxy proxy = new MyHttpProxy();
		final MyPagingLoader loader = new MyPagingLoader(proxy, config);
		final MyListStore store = new MyListStore(loader);
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
		EntityContentPanel cp = new EntityContentPanel("Usuarios",650,300);

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