package mobile.web.webxt.client.devform;

import mobile.common.tools.Format;
import mobile.web.webxt.client.MobileError;
import mobile.web.webxt.client.data.form.DataSource;
import mobile.web.webxt.client.data.form.DataSourceType;
import mobile.web.webxt.client.data.form.Reference;
import mobile.web.webxt.client.form.MyFormPanel;
import mobile.web.webxt.client.form.MyGeneralForm;
import mobile.web.webxt.client.form.validations.Validate;
import mobile.web.webxt.client.form.widgets.ComboForm;
import mobile.web.webxt.client.form.widgets.InputBox;
import mobile.web.webxt.client.form.widgets.MyDateField;
import mobile.web.webxt.client.form.widgets.MyLabel;
import mobile.web.webxt.client.form.widgets.RowContainer;
import mobile.web.webxt.client.form.widgetsgrid.ArrayColumnData;
import mobile.web.webxt.client.form.widgetsgrid.MyColumnData;
import mobile.web.webxt.client.util.DatesManager;

import com.extjs.gxt.ui.client.Style.HorizontalAlignment;
import com.extjs.gxt.ui.client.data.ModelData;
import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.SelectionChangedEvent;
import com.extjs.gxt.ui.client.event.SelectionChangedListener;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.form.FieldSet;
import com.extjs.gxt.ui.client.widget.layout.FlowLayout;
import com.google.gwt.user.client.Element;
import com.googlecode.gwt.crypto.bouncycastle.DataLengthException;
import com.googlecode.gwt.crypto.bouncycastle.InvalidCipherTextException;
import com.googlecode.gwt.crypto.client.TripleDesCipher;

public class A205 extends MyGeneralForm {

	//PROCESS FOR CHANGING PASSWORD (USER)
	
	private final static String PROCESS = "C201";
	private final static String ENTITY = "UserAccess";

	// Constants
	final int FORM_WIDTH = 350;
	final int LABEL_WIDTH = 60;

	MyFormPanel form;

	RowContainer row;
	MyLabel label;
	Button save, clear;

	// fields:
	InputBox retypePassword, newPassword,pass;
	ComboForm user;
	String encrypted;
	TripleDesCipher  cipher;

	public A205() {
		super(PROCESS);
		setReference(new Reference("acc", ENTITY));
	}

	@Override
	protected void onRender(Element parent, int index) {
		super.onRender(parent, index);
		cipher = new TripleDesCipher ();
		cipher.setKey(Format.GWT_DES_KEY);
		createForm();
	}

	private void createForm() {

		// Form panel
		final MyFormPanel form = new MyFormPanel(this, "Cambio de Contraseña", FORM_WIDTH);
		form.setLayout(new FlowLayout());

		FieldSet fieldSet = new FieldSet();
		fieldSet.setHeading("");
		fieldSet.setCollapsible(false);
		fieldSet.setBorders(false);
		fieldSet.setWidth(270);

		row = new RowContainer();
		label = new MyLabel("Usuario:", LABEL_WIDTH);
		row.add(label);
		user = new ComboForm(100);
		user.setDataSource(new DataSource("acc", "pk_userId", DataSourceType.CRITERION));

		Reference refUser = new Reference("usa", "UserAccount");
		final ArrayColumnData perCdata = new ArrayColumnData();
		perCdata.add(new MyColumnData("usa", "pk_userId", "Usuario", 100));
		perCdata.add(new MyColumnData("usa", "name", "Nombre", 100));
		user.setQueryData(refUser, perCdata);
		user.setDisplayField("pk_userId");
		
		user.addSelectionChangedListener(new SelectionChangedListener<ModelData>() {
			@Override
			public void selectionChanged(SelectionChangedEvent<ModelData> se) {
				if (((ComboForm) se.getSource()).isSomeSelected()) {
					form.queryForm();
				}
			}
		});

		row.add(user);
		fieldSet.add(row);

		row = new RowContainer();
		label = new MyLabel("Contraseña:", LABEL_WIDTH);
		row.add(label);
		
		pass = new InputBox(150, 10);
		pass.setType(Validate.PASSWORD);
		pass.setPassword(true);
		pass.setAllowBlank(false);
		pass.setFireChangeEventOnSetValue(true);
		
		newPassword = new InputBox(150, 300);
		newPassword.setType(Validate.PASSWORD);
		newPassword.setDataSource(new DataSource("acc", "userKey", DataSourceType.RECORD));
		newPassword.setAllowBlank(false);
		newPassword.setVisible(false);
			
		row.add(pass);
		row.add(newPassword);
		fieldSet.add(row);

		row = new RowContainer();
		label = new MyLabel("Repita contraseña:", LABEL_WIDTH);
		row.add(label);
		retypePassword = new InputBox(150, 10);
		retypePassword.setType(Validate.PASSWORD);
		retypePassword.setPassword(true);
		retypePassword.setAllowBlank(false);
		row.add(retypePassword);
		fieldSet.add(row);

		final MyDateField lastChange = new MyDateField(150, 11);
		lastChange.setDataSource(new DataSource("acc", "lastChange", DataSourceType.RECORD));
		lastChange.setValue(DatesManager.getCurrentDate());
		lastChange.setEditable(false);
		lastChange.setVisible(false);
		row.add(lastChange);
		fieldSet.add(row);
				
		form.add(fieldSet);

		save = new Button("Guardar", new SelectionListener<ButtonEvent>() {
			@Override
			public void componentSelected(ButtonEvent ce) {
				if (isValidForm()){
					
					try {
						encrypted = cipher.encrypt(pass.getValue());
					} catch (DataLengthException e) {
					  e.printStackTrace();
					} catch (IllegalStateException e) {
					  e.printStackTrace();
					} catch (InvalidCipherTextException e) {
					  e.printStackTrace();
					}					
					
					lastChange.setValue(DatesManager.getCurrentDate());
					newPassword.setValue(encrypted);
					form.commitForm();
				}
			}
		});

		clear = new Button("Limpiar", new SelectionListener<ButtonEvent>() {
			@Override
			public void componentSelected(ButtonEvent ce) {
				form.clear();
			}
		});

		form.setButtonAlign(HorizontalAlignment.CENTER);
		form.addButton(save);
		form.addButton(clear);

		add(form);
	}

	public boolean isValidForm() {
		boolean result = true;
		
		if (pass.getValue().compareTo(retypePassword.getValue()) != 0) {
			result = false;
			MobileError.report("Contraseñas no coinciden");
		}

		return result;
	}

}