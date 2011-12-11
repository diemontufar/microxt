package mobile.web.webxt_mvc.client.form;

import mobile.web.webxt_mvc.client.cgrid.MyEditorSupport;

import com.extjs.gxt.ui.client.data.ModelData;
import com.extjs.gxt.ui.client.store.ListStore;
import com.extjs.gxt.ui.client.widget.grid.ColumnModel;
import com.extjs.gxt.ui.client.widget.grid.EditorGrid;
import com.extjs.gxt.ui.client.widget.grid.EditorSupport;

public class EntityEditorGrid extends EditorGrid<ModelData> {
	@SuppressWarnings("rawtypes")
	public EntityEditorGrid(ListStore store, ColumnModel cm) {
		super(store, cm);
		setBorders(false);
		getView().setEmptyText("No hay datos");
		setLoadMask(true);
		setStripeRows(true);
	}
	
	@Override
	protected EditorSupport<ModelData> getEditSupport() {
		return new MyEditorSupport();
	}
}
