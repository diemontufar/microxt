package mobile.web.webxt_forms.client.data;

import java.util.List;

import mobile.web.webxt_forms.shared.AlertDialog;



import com.extjs.gxt.ui.client.data.BasePagingLoader;
import com.extjs.gxt.ui.client.data.DataProxy;
import com.extjs.gxt.ui.client.data.ModelData;
import com.extjs.gxt.ui.client.data.PagingLoadResult;
import com.google.gwt.user.client.rpc.AsyncCallback;

public class MyPagingLoader<D extends PagingLoadResult<?>> extends
		BasePagingLoader<D> {

	private final MyProcessConfig config;

	public MyPagingLoader(DataProxy<D> proxy, MyProcessConfig config) {
		super(proxy);
		this.setRemoteSort(true);
		this.config = config;
	}

	@Override
	protected Object newLoadConfig() {
		// BasePagingLoadConfig config = new BaseFilterPagingLoadConfig();
		//config.setProcessType(ProcessType.QUERY);
		return config;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void commitChanges(List<ModelData> lModified,
			final AsyncCallback<D> callback) {
		//config.setProcessType(ProcessType.MAINTENANCE);
		((MyHttpProxy) this.proxy).commit(config, lModified, callback);
	}

	protected void onLoadSuccess(Object loadConfig, D result) {
		super.onLoadSuccess(loadConfig, result);
	};

	@Override
	protected void onLoadFailure(Object loadConfig, Throwable t) {
		super.onLoadFailure(loadConfig, t);
		new AlertDialog("Load exception", t.getMessage()).show();
	}
	
}