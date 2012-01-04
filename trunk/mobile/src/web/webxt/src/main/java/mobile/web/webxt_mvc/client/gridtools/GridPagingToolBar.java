package mobile.web.webxt_mvc.client.gridtools;

import mobile.web.webxt_mvc.client.data.MyPagingLoader;

import com.extjs.gxt.ui.client.widget.toolbar.PagingToolBar;

public class GridPagingToolBar extends PagingToolBar {
	public GridPagingToolBar(int pageSize,
			MyPagingLoader loader) {
		super(pageSize);
		this.bind(loader);
		this.setReuseConfig(false);
	}
}
