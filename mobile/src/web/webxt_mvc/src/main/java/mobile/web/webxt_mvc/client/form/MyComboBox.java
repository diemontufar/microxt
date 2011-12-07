package mobile.web.webxt_mvc.client.form;

import com.extjs.gxt.ui.client.data.ModelData;
import com.extjs.gxt.ui.client.data.PagingLoader;
import com.extjs.gxt.ui.client.widget.form.ComboBox;

public class MyComboBox<D extends ModelData> extends ComboBox<D>{
	private boolean isLoaded = false;
	
	public MyComboBox() {
	}
	@SuppressWarnings("rawtypes")
	public void doQuery(String q, boolean forceAll) {
		if(!isLoaded){
			((PagingLoader)getStore().getLoader()).load(0, getPageSize());
			isLoaded=true;
		}
		expand();
	}

}
