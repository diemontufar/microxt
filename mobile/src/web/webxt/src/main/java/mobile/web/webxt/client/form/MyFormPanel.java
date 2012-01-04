package mobile.web.webxt.client.form;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import mobile.web.webxt.client.data.MyHttpProxy;
import mobile.web.webxt.client.data.MyProcessConfig;
import mobile.web.webxt.client.formtools.PersistentField;
import mobile.web.webxt.client.mvc.AppEvents;
import mobile.web.webxt.client.windows.AlertDialog;

import com.extjs.gxt.ui.client.mvc.Dispatcher;
import com.extjs.gxt.ui.client.widget.form.Field;
import com.extjs.gxt.ui.client.widget.form.FormPanel;
import com.google.gwt.user.client.rpc.AsyncCallback;

public class MyFormPanel extends FormPanel {

	private MyProcessConfig config;

	private Map<String, String> mfield = new HashMap<String, String>();

	private MyHttpProxy proxy = new MyHttpProxy();

	public MyFormPanel(String process, String title, int width) {
		setHeading(title);
		setWidth(width);
		setFrame(true);

		config = new MyProcessConfig(process);
	}

	public void commitForm() {
		List<Field<?>> lfields = getFields();
		for (Field<?> f : lfields) {
			if (f instanceof PersistentField) {
				PersistentField pf = (PersistentField) f;
				String key = pf.getFieldInfo();
				if (key.compareTo("") != 0) {
					mfield.put(key, f.getValue().toString());
				}
			}
		}

		System.out.println("MyForm.commitChanges");
		for (String key : mfield.keySet()) {
			System.out.println(key + ":" + mfield.get(key));
		}

		AsyncCallback<Boolean> callback = new AsyncCallback<Boolean>() {
			public void onSuccess(Boolean result) {
				Dispatcher.forwardEvent(AppEvents.UserNotification,
						"Mantenimiento exitoso");
			}

			public void onFailure(Throwable caught) {
				new AlertDialog("MyFormPanel", caught.getMessage()).show();
			}
		};

		proxy.commitForm(config, mfield, callback);
	}
}
