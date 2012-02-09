package mobile.web.webxt.client.form;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import mobile.common.message.Item;
import mobile.common.tools.Format;
import mobile.web.webxt.client.data.MyHttpProxy;
import mobile.web.webxt.client.data.MyMessageReader;
import mobile.web.webxt.client.data.form.DataSource;
import mobile.web.webxt.client.data.form.DataSourceType;
import mobile.web.webxt.client.form.widgets.ComboForm;
import mobile.web.webxt.client.form.widgets.InputBox;
import mobile.web.webxt.client.form.widgets.PersistentField;
import mobile.web.webxt.client.mvc.AppEvents;
import mobile.web.webxt.client.util.DatesManager;
import mobile.web.webxt.client.windows.AlertDialog;

import com.extjs.gxt.ui.client.data.BaseModelData;
import com.extjs.gxt.ui.client.data.ModelData;
import com.extjs.gxt.ui.client.mvc.Dispatcher;
import com.extjs.gxt.ui.client.widget.Info;
import com.extjs.gxt.ui.client.widget.form.DateField;
import com.extjs.gxt.ui.client.widget.form.Field;
import com.extjs.gxt.ui.client.widget.form.FormPanel;
import com.extjs.gxt.ui.client.widget.form.NumberField;
import com.extjs.gxt.ui.client.widget.form.TextArea;
import com.google.gwt.user.client.rpc.AsyncCallback;

public class MyFormPanel extends FormPanel {

	private MyGeneralForm parent;
	private Map<String, String> rqField;
	private Map<String, Object> rsField;
	private MyHttpProxy proxy = new MyHttpProxy();
	private boolean isNewItem = true;

	public MyFormPanel(String title, int width) {
		setHeading(title);
		setFrame(true);
		setWidth(width);
	}

	public MyFormPanel(MyGeneralForm parent, String title) {
		this.parent = parent;
		setHeading(title);
		setFrame(true);
	}

	public MyFormPanel(MyGeneralForm parent, String title, int width) {
		this.parent = parent;
		setHeading(title);
		setFrame(true);
		setWidth(width);
	}
	
	public MyFormPanel(MyGeneralForm parent, String title, String width) {
		this.parent = parent;
		setHeading(title);
		setFrame(true);
		setWidth(width);
	}

	public boolean isNewItem() {
		return isNewItem;
	}

	public void setNewItem(boolean isNewItem) {
		this.isNewItem = isNewItem;
	}

	public Map<String, String> getmField() {
		return rqField;
	}

	public void setmField(Map<String, String> mField) {
		this.rqField = mField;
	}

	public Map<String, Object> getRmField() {
		return rsField;
	}

	public void setRmField(Map<String, Object> rmField) {
		this.rsField = rmField;
	}

	private List<Field<?>> getPersistentFields() {
		List<Field<?>> lfields = getFields();
		List<Field<?>> lfields2 = new ArrayList<Field<?>>();
		for (Field<?> f : lfields) {
			if (f instanceof PersistentField) {
				lfields2.add(f);
			}
		}
		return lfields2;
	}

	public Field<?> findFieldById(String id) {
		for (Field<?> f : getFields()) {
			if (f.getId().compareTo(id) == 0) {
				return f;
			}
		}
		return null;
	}

	private void validateOverwrite(String key) {
		if (rqField.containsKey(key)) {
			new RuntimeException("Trying to overwrite key: " + key);
		}
	}

	public static String getValueFromField(Field<?> field) {
		String value = null;

		if (field.getValue() instanceof ModelData) {
			value = field.getRawValue();
		} else {
			if (field.getValue() == null) {
				value = null;
			} else {
				if (field.getValue() instanceof Date) {
					value = DatesManager.dateToString((Date) field.getValue(), Format.DATE);
				} else {
					value = field.getValue().toString();
				}
			}
		}
		return value;
	}

	private void fieldsToMap(boolean ignoreDescriptions) {
		System.out.println("::fieldsToMap");
		rqField = new HashMap<String, String>();
		for (Field<?> f : getPersistentFields()) {
			PersistentField pf = (PersistentField) f;
			DataSource ds = pf.getDataSource();
			if (ds != null) {
				if (ignoreDescriptions && ds.getType() == DataSourceType.DESCRIPTION) {
					continue;
				}
				String key = MyMessageReader.buildKey(ds);
				validateOverwrite(key);
				String value = getValueFromField(f);
				rqField.put(key, value);
			}
		}

		// Show map
		for (String key : rqField.keySet()) {
			System.out.println(key + ">" + rqField.get(key));
		}
	}

	public static void setValueToField(Field<?> f, Object newObject, boolean diff) {
		if (f instanceof InputBox) {
			InputBox field = (InputBox) f;
			String newValue = null;
			if (newObject != null) {
				newValue = newObject.toString();
			}

			if (!diff || field.getValue() == null || (newValue != null && !field.getValue().equals(newValue))) {
				field.setValue(newValue);
			}
		} else if (f instanceof NumberField) {
			NumberField field = (NumberField) f;
			Number newValue = null;
			if (newObject != null) {
				if (newObject instanceof Number) {
					newValue = (Number) newObject;
				} else {
					newValue = field.getPropertyEditor().convertStringValue(newObject.toString());
				}
			}

			if (!diff || field.getValue() == null || (newValue != null && !field.getValue().equals(newValue))) {
				field.setValue(newValue);
			}

		} else if (f instanceof DateField) {
			DateField field = (DateField) f;
			Date newValue = null;
			if (newObject instanceof Date) {
				newValue = (Date) newObject;
			} else {
				newValue = DatesManager.stringToDate(newObject.toString(), Format.DATE);
			}

			if (!diff || field.getValue() == null || (newValue != null && !field.getValue().equals(newValue))) {
				field.setValue(newValue);
			}
		} else if (f instanceof TextArea) {
			TextArea field = (TextArea) f;
			String newValue = null;
			if (newObject != null) {
				newValue = newObject.toString();
			}
			if (!diff || field.getValue() == null || (newValue != null && !field.getValue().equals(newValue))) {
				field.setValue(newValue);
			}
		} else if (f instanceof ComboForm) {
			ComboForm field = (ComboForm) f;
			String newValue = null;
			ModelData newModel = null;

			if (newObject != null) {
				newValue = newObject.toString();
				newModel = new BaseModelData();
				newModel.set(field.getDisplayField(), newValue);
			}

			if (!diff || field.getValue() == null || (newValue != null && !field.getValue().equals(newValue))) {
				field.setValue(newModel);
				field.setRawValue(newValue);
				field.setLoaded(false);
			}
		}
	}

	private void mapToFields(Map<String, Object> rmfields, boolean diff) {
		System.out.println("::mapToFields");

		// Show map
		for (String key : rmfields.keySet()) {
			System.out.println(key + ">" + rmfields.get(key));
		}

		// Query something?
		boolean atLeastOneItem = (Boolean) rmfields.get("ONE_ITEM");
		if (atLeastOneItem) {
			setNewItem(false); // It's a maintenance
		} else {
			setNewItem(true); // It's a new creation
		}

		// Mapping
		try {
			for (Field<?> f : getPersistentFields()) {
				PersistentField pf = (PersistentField) f;
				DataSource ds = pf.getDataSource();
				if (ds != null && ds.getType() != DataSourceType.CRITERION) {
					String key = MyMessageReader.buildKey(ds);
					if(ds.getType() == DataSourceType.DESCRIPTION){
						key = uncompleteDescriptionKey(key);
					}
					Object newObject = rmfields.get(key);
					setValueToField(f, newObject, diff);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private String uncompleteDescriptionKey(String key) {
		String key2 = key;
		String [] part = key.split(":");
		if(part.length>3){
			key2 = part[0] + ":" + part[1] + ":" + part[2];
		}
		return key2;
	}

	private void fillIsNewFields() {
		// Set isNewItem
		if (isNewItem) {
			Map<String, String> mNewFields = new HashMap<String, String>();
			for (String key : rqField.keySet()) {
				String[] pk = key.split(":");
				if (DataSourceType.valueOf(pk[2]) != DataSourceType.CRITERION) {
					mNewFields.put(pk[0] + ":" + Item.NEW_ITEM + ":" + pk[2], "1");
				}
			}
			rqField.putAll(mNewFields);
		}
	}

	public void commitForm() {
		System.out.println("::MyFormPanel.commitForm");

		// Validate
		if (!isValid()) {
			Dispatcher.forwardEvent(AppEvents.UserNotification, "Existe errores de validacion");
			return;
		}

		// Begin maintenance process
		Dispatcher.forwardEvent(AppEvents.UserNotification, "Procesando mantenimiento");

		// Set map values
		fieldsToMap(true);

		// Fill isNew values
		fillIsNewFields();

		// Commit changes
		AsyncCallback<Map<String, Object>> callback = new AsyncCallback<Map<String, Object>>() {
			public void onSuccess(Map<String, Object> rmfields) {
				System.out.println("::MyFormPanel.onSuccess");
				rsField = rmfields;
				mapToFields(rmfields, true);
				try {
					postMaintenance();
				} catch (Exception e) {
					Info.display("Post-Mantenimiento", e.getMessage());
				}
				Dispatcher.forwardEvent(AppEvents.UserNotification, "Mantenimiento exitoso");
			}

			public void onFailure(Throwable caught) {
				new AlertDialog("MyFormPanel", caught.getMessage()).show();
				caught.printStackTrace();
			}
		};

		if (preMaintenance()) {
			proxy.commitForm(parent.getConfig(), rqField, callback);
		}
	}

	public void queryForm() {
		System.out.println("::MyFormPanel.queryForm");
		Dispatcher.forwardEvent(AppEvents.UserNotification, "Procesando consulta");

		fieldsToMap(false);

		AsyncCallback<Map<String, Object>> callback = new AsyncCallback<Map<String, Object>>() {
			public void onSuccess(Map<String, Object> rmfields) {
				System.out.println("Consulta de formulario exitosa");
				setRmField(rmfields);
				mapToFields(rmfields, false);
				try {
					postQuery();
				} catch (Exception e) {
					Info.display("Post-Consulta", e.getMessage());
				}
				Dispatcher.forwardEvent(AppEvents.UserNotification, "Consulta exitosa");
			}

			public void onFailure(Throwable caught) {
				new AlertDialog("MobileForm", caught.getMessage()).show();
				caught.printStackTrace();
			}
		};

		if (preQuery()) {
			proxy.queryForm(parent.getConfig(), rqField, callback);
		}
	}

	protected boolean preQuery() {
		return true;
	}

	protected boolean postQuery() {
		return true;
	}

	protected boolean preMaintenance() {
		return true;
	}

	protected boolean postMaintenance() {
		return true;
	}
}