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
import mobile.web.webxt.client.form.widgetsgrid.NormalColumn;
import mobile.web.webxt.client.form.widgetsgrid.SpecialComboColumn;
import mobile.web.webxt.client.form.widgetsgrid.MyColumnData.ColumnType;

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

public class C103 extends LayoutContainer {

private final Integer PAGE_SIZE = 5;
	
	private final String process = "C103";
	

	@Override
	protected void onRender(Element parent, int index) {
		super.onRender(parent, index);
		setLayout(new CenterLayout());
		getAriaSupport().setPresentation(true);
				
		// Config
		String entity = "ZoneAsessor";

		final ArrayColumnData cdata = new ArrayColumnData();
		cdata.add(new MyColumnData("pk_userId", "Asesor", 150, 20,false));
		cdata.add(new MyColumnData("pk_geographicZoneId", "Zona", 150, 6, false));
		cdata.add(new MyColumnData("observations", "Observaciones", 200, 50, true));
		
		MyProcessConfig config = new MyProcessConfig(process, entity,cdata.getIdFields());
		
		// Proxy - loader - store
		MyHttpProxy proxy = new MyHttpProxy();
		final MyPagingLoader loader = new MyPagingLoader(proxy, config);
		final MyListStore store = new MyListStore(loader);
		
		// Column model
		List<ColumnConfig> configs = new ArrayList<ColumnConfig>();

		// Combo Asessor
		SpecialComboColumn spcComboCol = new SpecialComboColumn(cdata.get(0));
		spcComboCol.setPageSize(10);
		ArrayColumnData cdataCombo = new ArrayColumnData();
		cdataCombo = new ArrayColumnData();
		cdataCombo.add(new MyColumnData("pk_userId", "Asesor", 100));
		cdataCombo.add(new MyColumnData("name", "Nombre	", 220));
		
		MyColumnData hidden = new MyColumnData("userTypeId",ColumnType.HIDDEN);
		hidden.setAssociatedField("userTypeId");
		cdataCombo.add(hidden);
		
		spcComboCol.setRqData("UserAccount", cdataCombo);
		spcComboCol.setFilter("userTypeId", "ASE");
		configs.add(spcComboCol);
		
		ComboColumn profileComboColumn = new ComboColumn(cdata.get(1));
		ArrayColumnData cdataComboProfile = new ArrayColumnData();
		cdataComboProfile.add(new MyColumnData("pk_geographicZoneId", "ID", 40));
		cdataComboProfile.add(new MyColumnData("description", "Descripcion", 150));
		profileComboColumn.setRqData("GeographicZone", cdataComboProfile);
		configs.add(profileComboColumn);
		
		configs.add(new NormalColumn(cdata.get(2)));		

		configs.add(new ExpireColumnConfig());
		
		//configs.add(new ShowColumnConfig("Ver","",50));
	
		ColumnModel cm = new ColumnModel(configs);
		
		// Filters
		GridFilters filters = new GridFilters();
		StringFilter parameterIdFilter = new StringFilter(cdata.getIdFields().get(0));
		StringFilter subsystemFilter = new StringFilter(cdata.getIdFields().get(1));
		filters.addFilter(parameterIdFilter);
		filters.addFilter(subsystemFilter);
		
		// Content panel
		EntityContentPanel cp = new EntityContentPanel("Asignacion de Zonas",500,300);

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