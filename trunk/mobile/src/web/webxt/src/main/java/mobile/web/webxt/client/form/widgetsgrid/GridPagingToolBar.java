package mobile.web.webxt.client.form.widgetsgrid;

import mobile.web.webxt.client.data.MyPagingLoader;

import com.extjs.gxt.ui.client.widget.Info;
import com.extjs.gxt.ui.client.widget.toolbar.PagingToolBar;

public class GridPagingToolBar extends PagingToolBar {
	
	private boolean waitingFilter = false;
	
	private String message;
	
	public GridPagingToolBar(int pageSize,
			MyPagingLoader loader) {
		super(pageSize);
		this.bind(loader);
		this.setReuseConfig(false);
	}
	
	@Override
	protected void doLoadRequest(int offset, int limit) {
		if(!waitingFilter){
			super.doLoadRequest(offset, limit);
		}else{
			Info.display("Requerido", message);
		}
	}

	public boolean isWaitingFilter() {
		return waitingFilter;
	}

	public void setWaitingFilter(boolean waitingFilter) {
		this.waitingFilter = waitingFilter;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
}
