package mobile.web.webxt.client.devform;

import java.util.ArrayList;
import java.util.List;

import mobile.web.webxt.client.data.MyPagingLoader;
import mobile.web.webxt.client.data.MyProcessConfig;
import mobile.web.webxt.client.form.EntityContentPanel;
import mobile.web.webxt.client.form.MyGeneralForm;
import mobile.web.webxt.client.form.widgetsgrid.ArrayColumnData;
import mobile.web.webxt.client.form.widgetsgrid.EntityEditorGrid;
import mobile.web.webxt.client.form.widgetsgrid.ExpireColumnConfig;
import mobile.web.webxt.client.form.widgetsgrid.GridPagingToolBar;
import mobile.web.webxt.client.form.widgetsgrid.MyColumnData;
import mobile.web.webxt.client.form.widgetsgrid.NormalColumn;

import com.extjs.gxt.ui.client.Style.SortDir;
import com.extjs.gxt.ui.client.data.BaseStringFilterConfig;
import com.extjs.gxt.ui.client.data.FilterConfig;
import com.extjs.gxt.ui.client.data.LoadEvent;
import com.extjs.gxt.ui.client.event.BaseEvent;
import com.extjs.gxt.ui.client.event.Events;
import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.event.LoadListener;
import com.extjs.gxt.ui.client.widget.grid.ColumnConfig;
import com.extjs.gxt.ui.client.widget.grid.ColumnModel;
import com.google.gwt.user.client.Element;

public class C101 extends MyGeneralForm {

	private final static String PROCESS = "C101";
	private final static String ENTITY = "UserAccount";
	private final Integer PAGE_SIZE = 5;

	public C101() {
		super(PROCESS, true);
		setReference(ENTITY);
	}

	@Override
	protected void onRender(Element parent, int index) {
		super.onRender(parent, index);

		// Configuration
		final ArrayColumnData cdata = new ArrayColumnData();
		cdata.add(new MyColumnData("pk_userId", "Asesor", 80, 20, false));
		cdata.add(new MyColumnData("personId", "Persona", 80, 20, false));
		cdata.add(new MyColumnData("userTypeId", "Tipo", 80, 4, false));
		getConfig().setlDataSource(cdata.getDataSources());

		// Column model
		List<ColumnConfig> configs = new ArrayList<ColumnConfig>();

		configs.add(new NormalColumn(cdata.get(0)));
		configs.add(new NormalColumn(cdata.get(1)));
		configs.add(new NormalColumn(cdata.get(2)));

		configs.add(new ExpireColumnConfig());

		ColumnModel cm = new ColumnModel(configs);

		// Content panel
		EntityContentPanel cp = new EntityContentPanel("Asesores de Microcredito", 400, 300);

		// Grid
		final EntityEditorGrid grid = new EntityEditorGrid(getStore(), cm);
		grid.setAutoExpandColumn("pk_userId");
		cp.add(grid);

		grid.addListener(Events.Attach, new Listener<BaseEvent>() {
			public void handleEvent(BaseEvent be) {
				getStore().sort(cdata.getIdFields().get(0), SortDir.ASC);
			}
		});

		// Paging tool bar
		final GridPagingToolBar pagingToolBar = new GridPagingToolBar(grid, PAGE_SIZE);
		cp.setBottomComponent(pagingToolBar);

		LoadListener filterListener = new LoadListener() {
			public void loaderBeforeLoad(LoadEvent le) {

				MyProcessConfig config = le.getConfig();
				String ffield = "userTypeId";

				List<FilterConfig> filters = config.getFilterConfigs();
				if (filters == null) {
					filters = new ArrayList<FilterConfig>();
				}

				boolean existe = false;
				for (FilterConfig fil : filters) {
					if (fil.getField().compareTo(ffield) == 0) {
						existe = true;
						fil.setValue("ASE");
					}
				}

				if (!existe) {
					FilterConfig filter = new BaseStringFilterConfig();
					filter.setField(ffield);
					filter.setComparison("=");
					filter.setValue("ASE");
					filters.add(filter);
				}

				config.setFilterConfigs(filters);
			}
		};

		getLoader().addListener(MyPagingLoader.BeforeLoad, filterListener);

		add(cp);
	}
}