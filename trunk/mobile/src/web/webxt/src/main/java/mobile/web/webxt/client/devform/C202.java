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

public class C202 extends LayoutContainer {

private final Integer PAGE_SIZE = 5;
	
	private final String process = "C202";
	

	@Override
	protected void onRender(Element parent, int index) {
		super.onRender(parent, index);
		setLayout(new CenterLayout());
		getAriaSupport().setPresentation(true);
				
		// Config
		String entity = "Partner";

		
		final ArrayColumnData cdata = new ArrayColumnData();
		cdata.add(new MyColumnData("pk_partnerId", "Cliente", 50, 11,false));
		cdata.add(new MyColumnData("personId", "Persona", 50, 11, false));
		cdata.add(new MyColumnData("activity", "Actividad", 200, 300, false));
		cdata.add(new MyColumnData("asessorId", "Asesor", 50, 11, false));
		cdata.add(new MyColumnData("meetingFrequencyId", "Frec. Reunion", 50, 3, false));
		cdata.add(new MyColumnData("meetingDay", "Dia Reunion", 50, 3, false));
		
		MyProcessConfig config = new MyProcessConfig(process, entity,cdata.getIdFields());
		
		// Proxy - loader - store
		MyHttpProxy proxy = new MyHttpProxy();
		final MyPagingLoader loader = new MyPagingLoader(proxy, config);
		final MyListStore store = new MyListStore(loader);
		
		// Column model
		List<ColumnConfig> configs = new ArrayList<ColumnConfig>();

		configs.add(new NormalColumn(cdata.get(0)));
		
		ComboColumn profileComboColumn = new ComboColumn(cdata.get(1));
		ArrayColumnData cdataComboProfile = new ArrayColumnData();
		cdataComboProfile.add(new MyColumnData("pk_personId", "ID", 40));
		cdataComboProfile.add(new MyColumnData("identificationNumber", "Identificacion", 100));
		cdataComboProfile.add(new MyColumnData("name", "Nombre", 110));
		cdataComboProfile.add(new MyColumnData("lastName", "Apellido", 120));
		profileComboColumn.setRqData("Person", cdataComboProfile);
		configs.add(profileComboColumn);
		
		configs.add(new NormalColumn(cdata.get(2)));
		
		ComboColumn personComboColumn = new ComboColumn(cdata.get(3));
		ArrayColumnData cdataComboPerson = new ArrayColumnData();
		cdataComboPerson.add(new MyColumnData("pk_asessorId", "ID", 40));
		cdataComboPerson.add(new MyColumnData("personId", "Persona", 40));
		personComboColumn.setRqData("Asessor", cdataComboPerson);
		configs.add(personComboColumn);
		
		ComboColumn freqComboColumn = new ComboColumn(cdata.get(4));
		ArrayColumnData cdataCombofreq = new ArrayColumnData();
		cdataCombofreq.add(new MyColumnData("pk_meetingFrequencyId", "Codigo", 40));
		cdataCombofreq.add(new MyColumnData("frequency", "Frecuencia", 60));
		freqComboColumn.setRqData("MeetingFrequency", cdataCombofreq);
		configs.add(freqComboColumn);
		
		configs.add(new NormalColumn(cdata.get(5)));

		configs.add(new ExpireColumnConfig());
		
		ColumnModel cm = new ColumnModel(configs);
		
		// Filters
		GridFilters filters = new GridFilters();
		StringFilter parameterIdFilter = new StringFilter(cdata.getIdFields().get(0));
		StringFilter subsystemFilter = new StringFilter(cdata.getIdFields().get(1));
		filters.addFilter(parameterIdFilter);
		filters.addFilter(subsystemFilter);
		
		// Content panel
		EntityContentPanel cp = new EntityContentPanel("Mantenimiento de clientes Individuales",600,300);

		// Grid
		final EntityEditorGrid grid = new EntityEditorGrid(store, cm);
		grid.setAutoExpandColumn("pk_partnerId");
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