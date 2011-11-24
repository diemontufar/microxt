package mobile.web.webxt_mvc.client.cgrid;

import mobile.web.webxt_mvc.client.data.MyPagingLoader;

import com.extjs.gxt.ui.client.data.ModelData;
import com.extjs.gxt.ui.client.data.PagingLoadResult;
import com.extjs.gxt.ui.client.widget.toolbar.PagingToolBar;

public class GridPagingToolBar extends PagingToolBar {
	public GridPagingToolBar(int pageSize,
			MyPagingLoader<PagingLoadResult<ModelData>> loader) {
		super(pageSize);
		this.bind(loader);
		this.setReuseConfig(false);
	}
}
