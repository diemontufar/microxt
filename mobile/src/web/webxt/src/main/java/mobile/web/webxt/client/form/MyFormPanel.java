package mobile.web.webxt.client.form;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import mobile.common.message.Item;
import mobile.web.webxt.client.data.MyHttpProxy;
import mobile.web.webxt.client.data.MyProcessConfig;
import mobile.web.webxt.client.form.widgets.ComboForm;
import mobile.web.webxt.client.form.widgets.InputBox;
import mobile.web.webxt.client.form.widgets.PersistentField;
import mobile.web.webxt.client.mvc.AppEvents;
import mobile.web.webxt.client.windows.AlertDialog;

import com.extjs.gxt.ui.client.data.ModelData;
import com.extjs.gxt.ui.client.mvc.Dispatcher;
import com.extjs.gxt.ui.client.widget.form.Field;
import com.extjs.gxt.ui.client.widget.form.FormPanel;
import com.extjs.gxt.ui.client.widget.form.NumberField;
import com.extjs.gxt.ui.client.widget.form.TextArea;
import com.google.gwt.user.client.rpc.AsyncCallback;

public class MyFormPanel extends FormPanel {

	private MyProcessConfig config;
	private Map<String, String> mField;
	private MyHttpProxy proxy = new MyHttpProxy();
	private boolean isNewItem = true;

	public MyFormPanel(String title, int width) {
		setHeading(title);
		setFrame(true);
		setWidth(width);
	}
	
	public MyFormPanel(String process, String title, int width) {
		setHeading(title);
		setWidth(width);
		setFrame(true);

		config = new MyProcessConfig(process);
	}
	
	public boolean isNewItem() {
		return isNewItem;
	}

	public void setNewItem(boolean isNewItem) {
		this.isNewItem = isNewItem;
	}

	private void fieldsToMap() {
		// Set map of fields and values
		mField = new HashMap<String, String>();
		List<Field<?>> lfields = getFields();
		for (Field<?> f : lfields) {
			if (f instanceof PersistentField) {
				PersistentField pf = (PersistentField) f;
				String persistentInfo = pf.getPersistentInfo();
				if (persistentInfo != null && persistentInfo.compareTo("") != 0) {
					if(f.getValue() == null){
						mField.put(persistentInfo, null);
					}else if( !( f.getValue() instanceof ModelData) ){
						mField.put(persistentInfo, f.getValue().toString());
					}else{
						mField.put(persistentInfo, f.getRawValue());
					}
				}
			}
		}
	}
	
	private void mapToFields(Map<String, String> rmfields) {
		// Set response values
		try {
			List<Field<?>> lfields = getFields();
			for (Field<?> f : lfields) {
				if (f instanceof PersistentField) {
					PersistentField pf = (PersistentField) f;
					String persistentInfo = pf.getPersistentInfo();
					if (persistentInfo != null && persistentInfo.compareTo("") != 0) {
						//if(f.getValue() == null){
							if(f instanceof InputBox){
								InputBox input = (InputBox) f;
								input.setValue(rmfields.get(persistentInfo));
							}else if(f instanceof NumberField){
								NumberField number = (NumberField) f;
								number.setValue(7897);
								//number.setValue((Number)rmfields.get(persistentInfo));
							}else if(f instanceof TextArea){
								TextArea textArea = (TextArea) f;
								textArea.setValue(rmfields.get(persistentInfo));
							}else if(f instanceof ComboForm){
								System.out.println("Combo " + persistentInfo);
								ComboForm combo = (ComboForm) f;
								combo.setRawValue(rmfields.get(persistentInfo));
								combo.setLoaded(false);
							}
						//}
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void fillIsNewFields() {
		// Set isNewItem
		if(isNewItem){
			Map<String,String> mNewFields = new HashMap<String, String>();
			for (String key : mField.keySet()) {
				String [] pk = key.split(":");
				mNewFields.put(pk[0] + ":" + Item.NEW_ITEM + ":" + pk[2], "1");
			}
			mField.putAll(mNewFields);
		}
	}
	
	public void commitForm() {
		Dispatcher.forwardEvent(AppEvents.UserNotification,
				"Procesando mantenimiento");
		
		fieldsToMap();
		
		fillIsNewFields();
		
		// Commit changes
		System.out.println("MyFormPanel.commitChanges");
		for (String key : mField.keySet()) {
			System.out.println(key + ":" + mField.get(key));
		}
		
		AsyncCallback<Map<String, String>> callback = new AsyncCallback<Map<String, String>>() {
			public void onSuccess(Map<String, String> rmfields) {
				System.out.println("MyFormPanel.response");
				for (String key : rmfields.keySet()) {
					System.out.println(key + ":" + rmfields.get(key));
				}

				mapToFields(rmfields);
				
				Dispatcher.forwardEvent(AppEvents.UserNotification,
						"Mantenimiento exitoso");
			}

			public void onFailure(Throwable caught) {
				new AlertDialog("MyFormPanel", caught.getMessage()).show();
				caught.printStackTrace();
			}
		};

		proxy.commitForm(config, mField, callback);
	}
	
	public void queryForm() {
		Dispatcher.forwardEvent(AppEvents.UserNotification,
				"Procesando consulta");

		
		fieldsToMap();
		
		System.out.println("MyFormPanel.queryForm");
		for (String key : mField.keySet()) {
			System.out.println(key + ":" + mField.get(key));
		}

		AsyncCallback<Map<String, String>> callback = new AsyncCallback<Map<String, String>>() {
			public void onSuccess(Map<String, String> rmfields) {
				mapToFields(rmfields);
				
				Dispatcher.forwardEvent(AppEvents.UserNotification,
						"Consulta exitosa");
			}

			public void onFailure(Throwable caught) {
				new AlertDialog("MyFormPanel", caught.getMessage()).show();
				caught.printStackTrace();
			}
		};

		proxy.queryForm(config, mField, callback);
	}

	public void resetForm() {
		this.clear();
	}
	
	
}
