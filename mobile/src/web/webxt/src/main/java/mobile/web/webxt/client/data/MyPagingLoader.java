package mobile.web.webxt.client.data;

import java.util.List;

import mobile.web.webxt.client.windows.MobileError;

import com.extjs.gxt.ui.client.data.BasePagingLoader;
import com.extjs.gxt.ui.client.data.DataProxy;
import com.extjs.gxt.ui.client.data.ModelData;
import com.extjs.gxt.ui.client.data.PagingLoadResult;
import com.google.gwt.user.client.rpc.AsyncCallback;

public class MyPagingLoader extends BasePagingLoader<PagingLoadResult<ModelData>> {

	private final MyProcessConfig config;

	public MyPagingLoader(DataProxy<PagingLoadResult<ModelData>> proxy, MyProcessConfig config) {
		super(proxy);
		this.setRemoteSort(true);
		this.config = config;
		setReuseLoadConfig(true);
	}

	@Override
	protected Object newLoadConfig() {
		return config;
	}

	public void commitChanges(List<ModelData> lModified, final AsyncCallback<PagingLoadResult<ModelData>> callback) {
		((MyHttpProxy) this.proxy).commit(config, lModified, callback);
	}

	protected void onLoadSuccess(Object loadConfig, PagingLoadResult<ModelData> result) {
		super.onLoadSuccess(loadConfig, result);
	};

	@Override
	protected void onLoadFailure(Object loadConfig, Throwable t) {
		super.onLoadFailure(loadConfig, t);
		MobileError.report(t);
	}

	public MyProcessConfig getConfig() {
		return config;
	}
}