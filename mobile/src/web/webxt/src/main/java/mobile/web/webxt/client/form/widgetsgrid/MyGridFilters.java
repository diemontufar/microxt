package mobile.web.webxt.client.form.widgetsgrid;

import java.util.List;

import mobile.web.webxt.client.data.MyProcessConfig;

import com.extjs.gxt.ui.client.data.FilterConfig;
import com.extjs.gxt.ui.client.data.FilterPagingLoadConfig;
import com.extjs.gxt.ui.client.data.LoadEvent;
import com.extjs.gxt.ui.client.data.Loader;
import com.extjs.gxt.ui.client.data.ModelData;
import com.extjs.gxt.ui.client.store.ListStore;
import com.extjs.gxt.ui.client.store.Store;

public class MyGridFilters extends MyAbstractGridFilters {
	@Override
	public boolean isLocal() {
		return super.isLocal();
	}

	@Override
	public void setLocal(boolean local) {
		super.setLocal(local);
	}

	@Override
	protected Loader<?> getLoader(Store<ModelData> store) {
		if (store instanceof ListStore<?>) {
			return ((ListStore<?>) store).getLoader();
		}
		return null;
	}

	@Override
	protected Store<ModelData> getStore() {
		return grid.getStore();
	}

	@Override
	protected void onBeforeLoad(LoadEvent le) {
		FilterPagingLoadConfig config = le.getConfig();
		cleanParams(config);
		MyProcessConfig myConfig = (MyProcessConfig) config;
		List<FilterConfig> filterConfigs = buildQuery(getFilterData());
		myConfig.addFilters(filterConfigs);
	}

	@Override
	public void cleanParams(FilterPagingLoadConfig config) {
		List<FilterConfig> filterConfigs = buildQuery(getFilterArray());
		((MyProcessConfig) config).removeFilters(filterConfigs);
	}

}
