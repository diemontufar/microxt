package mobile.web.webxt_mvc.client.data;

import java.util.ArrayList;
import java.util.List;

import mobile.web.webxt_mvc.client.mvc.AppEvents;
import mobile.web.webxt_mvc.client.windows.AlertDialog;

import com.extjs.gxt.ui.client.Style.SortDir;
import com.extjs.gxt.ui.client.data.ListLoader;
import com.extjs.gxt.ui.client.data.LoadEvent;
import com.extjs.gxt.ui.client.data.ModelData;
import com.extjs.gxt.ui.client.data.PagingLoadResult;
import com.extjs.gxt.ui.client.mvc.Dispatcher;
import com.extjs.gxt.ui.client.store.ListStore;
import com.extjs.gxt.ui.client.store.Record;
import com.google.gwt.user.client.rpc.AsyncCallback;

public class MyListStore<M extends ModelData> extends ListStore<M> {

	@SuppressWarnings("rawtypes")
	public MyListStore(ListLoader loader) {
		super(loader);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public void commitChanges() {
		// Get modified registers
		System.out.println("Modified registers...");
		List<ModelData> lModified = new ArrayList<ModelData>();
		for (Record r : getModifiedRecords()) {
			System.out.print(r.getModel().get("pk_parameterId")+">");
			lModified.add(r.getModel());
			for (String prop : r.getModel().getPropertyNames()) {
				System.out.print(prop+":"+r.getModel().get(prop)+";");
			}
			System.out.println();
		}
		
		AsyncCallback<PagingLoadResult<ModelData>> callback = new AsyncCallback<PagingLoadResult<ModelData>>() {
			public void onSuccess(PagingLoadResult<ModelData> result) {
				// Quit modified state
				for (Record r : getModifiedRecords()) {
					r.commit(false);
				}
				modified = new ArrayList<Record>();
				Dispatcher.forwardEvent(AppEvents.UserNotification,"Mantenimiento exitoso");
			}

			public void onFailure(Throwable caught) {
				new AlertDialog("MyListStore", caught.getMessage()).show();
			}
		};

		((MyPagingLoader) loader).commitChanges(lModified, callback);
	}
	
	@Override
	public void sort(String field, SortDir sortDir) {
		System.out.println("MyListStore. start sorting");
		super.sort(field, sortDir);
		System.out.println("MyListStore. end sorting");
	}
	
	@Override
	public void rejectChanges() {
		for (ModelData model : this.getModels()) {
			if (model.get("_isNew") != null
					&& ((Boolean) model.get("_isNew")) == true) {
				this.getModels().remove(model);
			}
		}
		super.rejectChanges();
	}
	
	@Override
	protected void onLoad(LoadEvent le) {
		super.onLoad(le);
		Dispatcher.forwardEvent(AppEvents.UserNotification,"Consulta exitosa");
	}
}