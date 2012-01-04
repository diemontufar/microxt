package mobile.web.webxt.client.gridtools;

import mobile.web.webxt.client.data.MyPagingLoader;

import com.extjs.gxt.ui.client.widget.toolbar.PagingToolBar;

public class GridPagingToolBar extends PagingToolBar {
	public GridPagingToolBar(int pageSize,
			MyPagingLoader loader) {
		super(pageSize);
		this.bind(loader);
		this.setReuseConfig(false);
	}
}
