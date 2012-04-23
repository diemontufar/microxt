package mobile.web.webxt.client.data;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import mobile.web.webxt.client.data.form.DataSource;
import mobile.web.webxt.client.data.form.DataSourceType;
import mobile.web.webxt.client.form.widgetsgrid.DependentGrid;
import mobile.web.webxt.client.mvc.AppEvents;

import com.extjs.gxt.ui.client.data.BaseStringFilterConfig;
import com.extjs.gxt.ui.client.data.FilterConfig;
import com.extjs.gxt.ui.client.data.LoadEvent;
import com.extjs.gxt.ui.client.data.ModelData;
import com.extjs.gxt.ui.client.data.PagingLoadResult;
import com.extjs.gxt.ui.client.mvc.Dispatcher;
import com.extjs.gxt.ui.client.store.ListStore;
import com.extjs.gxt.ui.client.store.Record;
import com.extjs.gxt.ui.client.widget.Info;
import com.google.gwt.user.client.rpc.AsyncCallback;

public class MyListStore extends ListStore<ModelData> {

	private MyPagingLoader myloader;

	public MyListStore(MyPagingLoader loader) {
		super(loader);
		myloader = loader;
	}

	@Override
	public void commitChanges() {
		// Get modified registers
		System.out.println("Modified registers...");
		List<ModelData> lModified = new ArrayList<ModelData>();
		for (Record r : getModifiedRecords()) {
			lModified.add(r.getModel());
			for (String prop : r.getModel().getPropertyNames()) {
				System.out.print(prop + ":" + r.getModel().get(prop) + ";");
			}
			System.out.println();
		}

		AsyncCallback<PagingLoadResult<ModelData>> callback = new AsyncCallback<PagingLoadResult<ModelData>>() {
			public void onSuccess(PagingLoadResult<ModelData> result) {
				// Quit modified state
				for (Record r : getModifiedRecords()) {
					r.commit(false);
				}
				modified = new ArrayList<Record>();
				Dispatcher.forwardEvent(AppEvents.UserNotification, "Mantenimiento exitoso");
			}

			public void onFailure(Throwable caught) {
				caught.printStackTrace();
				Dispatcher.forwardEvent(AppEvents.Error, caught.getMessage());
			}
		};

		myloader.commitChanges(lModified, callback);
	}

	@Override
	protected void onLoad(LoadEvent le) {
		super.onLoad(le);
		Dispatcher.forwardEvent(AppEvents.UserNotification, "Consulta exitosa");
	}

	public void addFilter(FilterConfig filter) {
		MyProcessConfig config = ((MyPagingLoader) getLoader()).getConfig();

		List<FilterConfig> filters = config.getFilterConfigs();
		if (filters == null) {
			filters = new ArrayList<FilterConfig>();
		}

		boolean exists = false;
		for (FilterConfig fil : filters) {
			if (fil.getField().compareTo(filter.getField()) == 0) {
				exists = true;
				fil.setValue(filter.getValue());
			}
		}

		if (!exists) {
			filters.add(filter);
		}

		config.setFilterConfigs(filters);
	}
}