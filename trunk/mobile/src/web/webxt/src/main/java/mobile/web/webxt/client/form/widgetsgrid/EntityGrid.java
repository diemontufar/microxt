package mobile.web.webxt.client.form.widgetsgrid;

import com.extjs.gxt.ui.client.data.ModelData;
import com.extjs.gxt.ui.client.store.ListStore;
import com.extjs.gxt.ui.client.widget.grid.ColumnModel;
import com.extjs.gxt.ui.client.widget.grid.Grid;

public class EntityGrid extends Grid<ModelData> {

	public EntityGrid(ListStore<ModelData> store, ColumnModel cm) {
		super(store, cm);
		setBorders(false);
		getView().setEmptyText("No hay datos");
		setLoadMask(true);
		setStripeRows(true);
		setColumnLines(true);
	}
}