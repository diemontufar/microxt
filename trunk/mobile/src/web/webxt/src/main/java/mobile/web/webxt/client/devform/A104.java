package mobile.web.webxt.client.devform;

import java.util.ArrayList;
import java.util.List;

import mobile.web.webxt.client.data.MyHttpProxy;
import mobile.web.webxt.client.data.MyListStore;
import mobile.web.webxt.client.data.MyPagingLoader;
import mobile.web.webxt.client.data.MyProcessConfig;
import mobile.web.webxt.client.formtools.ComboForm;
import mobile.web.webxt.client.gridtools.ArrayColumnData;
import mobile.web.webxt.client.gridtools.EntityContentPanel;
import mobile.web.webxt.client.gridtools.EntityEditorGrid;
import mobile.web.webxt.client.gridtools.ExpireColumnConfig;
import mobile.web.webxt.client.gridtools.GridPagingToolBar;
import mobile.web.webxt.client.gridtools.GridToolBar;
import mobile.web.webxt.client.gridtools.MyColumnData;
import mobile.web.webxt.client.gridtools.NormalColumn;

import com.extjs.gxt.ui.client.data.BaseStringFilterConfig;
import com.extjs.gxt.ui.client.data.FilterConfig;
import com.extjs.gxt.ui.client.data.LoadEvent;
import com.extjs.gxt.ui.client.event.BaseEvent;
import com.extjs.gxt.ui.client.event.Events;
import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.event.LoadListener;
import com.extjs.gxt.ui.client.widget.Info;
import com.extjs.gxt.ui.client.widget.LayoutContainer;
import com.extjs.gxt.ui.client.widget.form.FormPanel;
import com.extjs.gxt.ui.client.widget.grid.ColumnConfig;
import com.extjs.gxt.ui.client.widget.grid.ColumnModel;
import com.extjs.gxt.ui.client.widget.layout.CenterLayout;
import com.google.gwt.user.client.Element;

public class A104 extends LayoutContainer {

	private final String process = "A104";

	private final Integer PAGE_SIZE = 5;

	@Override
	protected void onRender(Element parent, int index) {
		super.onRender(parent, index);
		setLayout(new CenterLayout());
		getAriaSupport().setPresentation(true);
	      
	    // Configuration
		String entity = "Module";

		final ArrayColumnData cdata = new ArrayColumnData();
		cdata.add(new MyColumnData("pk_moduleId", "Mod", 70, 2, false));
		cdata.add(new MyColumnData("name", "Nombre", 150, 40, false));

		MyProcessConfig config = new MyProcessConfig(process, entity, cdata.getIdFields());
		
		// Proxy - loader - store
		MyHttpProxy proxy = new MyHttpProxy();
		final MyPagingLoader loader = new MyPagingLoader(proxy, config);
		final MyListStore store = new MyListStore(loader);

		// Columns
		List<ColumnConfig> configs = new ArrayList<ColumnConfig>();

		configs.add(new NormalColumn(cdata.get(0)));
		configs.add(new NormalColumn(cdata.get(1)));
		configs.add(new ExpireColumnConfig());
		
		ColumnModel cm = new ColumnModel(configs);
		
		// Content panel
		EntityContentPanel gridPanel = new EntityContentPanel(400, 230);
		
		// Grid
		final EntityEditorGrid grid = new EntityEditorGrid(store, cm);
		grid.setAutoExpandColumn("name");
		grid.addListener(Events.Attach, new Listener<BaseEvent>() {
			public void handleEvent(BaseEvent be) {
				//store.sort(cdata.getIdFields().get(0), SortDir.ASC);
			}
		});
		gridPanel.add(grid);
		
		// Top tool bar
		GridToolBar toolBar = new GridToolBar(grid, store);
		gridPanel.setTopComponent(toolBar);
		
		// Paging tool bar
		final GridPagingToolBar pagingToolBar = new GridPagingToolBar(PAGE_SIZE,loader);
		gridPanel.setBottomComponent(pagingToolBar);
		
		// Father panel
		EntityContentPanel panel = new EntityContentPanel("Modulos", 400, 300);
		
		// Subsystem combo
	    final ComboForm combo = new ComboForm("Subsistema", "name");
	    final ArrayColumnData combodata = new ArrayColumnData();
		combodata.add(new MyColumnData("pk_subsystemId", "Sub", 70));
		combodata.add(new MyColumnData("name", "Nombre", 150));
	    combo.setRqData("Subsystem", combodata);
	    
	    FormPanel headerPanel = new FormPanel();
	    headerPanel.setPadding(10);  
	    headerPanel.setHeaderVisible(false);  
	    headerPanel.setBodyBorder(true);  
	    headerPanel.setFieldWidth(150);
	    headerPanel.add(combo);
	    
	    LoadListener filterListener = new LoadListener() {
	        public void loaderBeforeLoad(LoadEvent le) {
	        	// Validate combo selected
	        	if(combo.getValue()==null){
	        		le.setCancelled(true);
	        		Info.display("A104", "Debe seleccionar un Subsistema");
	        	}
	        	
	        	MyProcessConfig config = le.getConfig();
	        	String ffield = "pk_subsystemId";
	        	
	        	List<FilterConfig> filters = config.getFilterConfigs(); 
	        	if(filters==null){
	        		filters  = new ArrayList<FilterConfig>();
	        	}
	        	
	        	boolean existe = false;
	        	for (FilterConfig fil : filters) {
					if (fil.getField().compareTo(ffield)==0){
						existe = true;
						fil.setValue(combo.getValue().get(ffield));
					}
				}
	        	
	        	if(!existe){
	        		FilterConfig filter = new BaseStringFilterConfig();
		        	filter.setField(ffield);
		        	filter.setComparison("=");
		        	filter.setValue(combo.getValue().get(ffield));
		        	filters.add(filter);
	        	}
	        	
	        	config.setFilterConfigs(filters);
	        }
	    };
	        
	    loader.addListener(MyPagingLoader.BeforeLoad, filterListener);
	    
	    panel.setTopComponent(headerPanel);
		panel.add(gridPanel);
		add(panel);
	}
}