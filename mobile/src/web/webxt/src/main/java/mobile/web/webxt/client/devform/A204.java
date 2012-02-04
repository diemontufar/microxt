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

import com.extjs.gxt.ui.client.Style.SortDir;
import com.extjs.gxt.ui.client.event.BaseEvent;
import com.extjs.gxt.ui.client.event.Events;
import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.widget.grid.ColumnConfig;
import com.extjs.gxt.ui.client.widget.grid.ColumnModel;
import com.extjs.gxt.ui.client.widget.grid.filters.GridFilters;
import com.extjs.gxt.ui.client.widget.grid.filters.StringFilter;
import com.google.gwt.user.client.Element;

public class A204 extends MyGeneralForm {

	private final static String PROCESS = "A204";
	private final static String ENTITY = "UserProfile";
	private final Integer PAGE_SIZE = 5;

	public A204() {
		super(PROCESS, true);
		setReference(ENTITY);
	}

	@Override
	protected void onRender(Element parent, int index) {
		super.onRender(parent, index);

		// Configuration
		final ArrayColumnData cdata = new ArrayColumnData();
		cdata.add(new MyColumnData("pk_userId", "Usuario", 100, 40, false));
		cdata.add(new MyColumnData("pk_profileId", "Perfil", 150, 40, false));
		getConfig().setlDataSource(cdata.getDataSources());

		// Column model
		List<ColumnConfig> configs = new ArrayList<ColumnConfig>();

		ComboColumn personComboColumn = new ComboColumn(cdata.get(0));
		Reference refUserAcc = new Reference("usrAcc", "UserAccount");
		ArrayColumnData usCdata = new ArrayColumnData();
		usCdata.add(new MyColumnData("usrAcc", "pk_userId", "Id", 40));
		usCdata.add(new MyColumnData("usrAcc", "name", "Nombre", 150));
		personComboColumn.setQueryData(refUserAcc, usCdata);
		configs.add(personComboColumn);

		ComboColumn profileComboColumn = new ComboColumn(cdata.get(1));
		Reference refProfile = new Reference("pro", "Profile");
		ArrayColumnData cdataComboProfile = new ArrayColumnData();
		cdataComboProfile.add(new MyColumnData("pro", "pk_profileId", "ID", 40));
		cdataComboProfile.add(new MyColumnData("pro", "name", "Nombre", 150));
		profileComboColumn.setQueryData(refProfile, cdataComboProfile);
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
		EntityContentPanel cp = new EntityContentPanel("Roles por Usuario", 400, 300);

		// Grid
		final EntityEditorGrid grid = new EntityEditorGrid(getStore(), cm);
		grid.setAutoExpandColumn("pk_userId");
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