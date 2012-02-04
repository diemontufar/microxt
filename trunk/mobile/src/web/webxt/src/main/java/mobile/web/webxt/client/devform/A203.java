package mobile.web.webxt.client.devform;

import java.util.ArrayList;
import java.util.List;

import mobile.web.webxt.client.data.form.Reference;
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

	private final static String PROCESS = "A203";
	private final static String ENTITY = "UserAccount";
	private final Integer PAGE_SIZE = 5;
	
	public A203() {
		super(PROCESS, true);
		setReference(ENTITY);
	}

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
		getConfig().setlDataSource(cdata.getDataSources());

		// Column model
		List<ColumnConfig> configs = new ArrayList<ColumnConfig>();

		configs.add(new NormalColumn(cdata.get(0)));
		configs.add(new NormalColumn(cdata.get(1)));
		
		ComboColumn usertypesComboColumn = new ComboColumn(cdata.get(2));
		Reference refUserType = new Reference("usrTyp", "UserType");
		ArrayColumnData utCdata = new ArrayColumnData();
		utCdata.add(new MyColumnData("usrTyp", "pk_userTypeId", "Tipo", 40));
		utCdata.add(new MyColumnData("usrTyp", "name", "Nombre", 150));
		usertypesComboColumn.setQueryData(refUserType, utCdata);
		configs.add(usertypesComboColumn);
		
		ComboColumn userstatusComboColumn = new ComboColumn(cdata.get(3));
		Reference refUserStatus = new Reference("usrSta", "UserStatus");
		ArrayColumnData usCdata = new ArrayColumnData();
		usCdata.add(new MyColumnData("usrSta", "pk_userStatusId", "Tipo", 40));
		usCdata.add(new MyColumnData("usrSta", "name", "Nombre", 150));
		userstatusComboColumn.setQueryData(refUserStatus, usCdata);
		configs.add(userstatusComboColumn);
		
		ComboColumn languageComboColumn = new ComboColumn(cdata.get(4));
		Reference refLanuage = new Reference("lang","Language");
		ArrayColumnData langCdata = new ArrayColumnData();
		langCdata.add(new MyColumnData("lang","languageId", "ID", 40));
		langCdata.add(new MyColumnData("lang","name", "Nombre", 150));
		languageComboColumn.setQueryData(refLanuage, langCdata);
		configs.add(languageComboColumn);
		configs.add(new NormalColumn(cdata.get(5)));
		
		ComboColumn personComboColumn = new ComboColumn(cdata.get(6));
		Reference refPerson = new Reference("per","Person");
		ArrayColumnData perCdata = new ArrayColumnData();
		perCdata.add(new MyColumnData("per","pk_personId", "ID", 40));
		perCdata.add(new MyColumnData("per","name", "Nombre", 150));
		perCdata.add(new MyColumnData("per","lastName", "Apellido", 150));
		personComboColumn.setQueryData(refPerson, perCdata);
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
		final EntityEditorGrid grid = new EntityEditorGrid(getStore(), cm);
		grid.setAutoExpandColumn("name");
		grid.addPlugin(filters);
		cp.add(grid);
		grid.addListener(Events.Attach, new Listener<BaseEvent>() {
			public void handleEvent(BaseEvent be) {
				getStore().sort(cdata.getIdFields().get(0), SortDir.ASC);
			}
		});

		// Top tool bar
		GridToolBar toolBar = new GridToolBar(grid, getStore());
		cp.setTopComponent(toolBar);

		// Paging tool bar
		final GridPagingToolBar pagingToolBar = new GridPagingToolBar(grid, PAGE_SIZE);
		cp.setBottomComponent(pagingToolBar);

		add(cp);
	}
	
	
}