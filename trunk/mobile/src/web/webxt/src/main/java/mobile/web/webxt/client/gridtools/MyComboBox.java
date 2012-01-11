package mobile.web.webxt.client.gridtools;

import com.extjs.gxt.ui.client.data.ModelData;
import com.extjs.gxt.ui.client.data.PagingLoader;
import com.extjs.gxt.ui.client.event.Events;
import com.extjs.gxt.ui.client.event.FieldEvent;
import com.extjs.gxt.ui.client.widget.form.ComboBox;

public class MyComboBox extends ComboBox<ModelData>{
	private boolean isLoaded = false;
	
	public MyComboBox() {
	}
	
	@SuppressWarnings("rawtypes")
	public void doQuery(String q, boolean forceAll) {
		FieldEvent fe = new FieldEvent(this);
	    fe.setValue(q);
	    if (!fireEvent(Events.BeforeQuery, fe)) {
	      return;
	    }
	    
		if(!isLoaded){
			((PagingLoader)getStore().getLoader()).load(0, getPageSize());
			isLoaded=true;
		}
		expand();
	}
	
	@Override
	protected ModelData findModel(String property, String value) {
		return super.findModel(property, value);
	}

	public boolean isLoaded() {
		return isLoaded;
	}

	public void setLoaded(boolean isLoaded) {
		this.isLoaded = isLoaded;
	}
}
