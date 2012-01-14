package mobile.web.webxt.client.devform;

import mobile.web.webxt.client.form.MyFormPanel;
import mobile.web.webxt.client.form.validations.Validate;
import mobile.web.webxt.client.form.widgets.InputBox;
import mobile.web.webxt.client.windows.AlertDialog;

import com.extjs.gxt.ui.client.Style.HorizontalAlignment;
import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.widget.button.Button;

public class A205 extends MyFormPanel {

	// fields:
	private InputBox user;
	private InputBox retypePassword;
	private InputBox newPassword;

	public A205() {
		super("A205", "Ingreso de contraseña", 350);

		// Fields
		InputBox isNew = new InputBox("", "UserAccess:_isNew:1", 50,5,Validate.TEXT);
		isNew.setMinLength(1);
		isNew.setValue("1");
		isNew.setVisible(false);

		user = new InputBox("Usuario", "UserAccess:pk_userId:1", 50,10, Validate.TEXT);
		newPassword = new InputBox("Contraseña nueva", "UserAccess:userKey:1",50,20, Validate.PASSWORD);
		retypePassword = new InputBox("Repita Contraseña", "", 50,20,Validate.PASSWORD);

		InputBox lastChange = new InputBox("", "UserAccess:lastChange:1", 50,20,Validate.TEXT);
		lastChange.setValue("2011-10-11");
		lastChange.setVisible(false);

		add(isNew);
		add(user);
		add(newPassword);
		add(retypePassword);
		add(lastChange);

		setButtonAlign(HorizontalAlignment.CENTER);
		addButton(new Button("Limpiar", new SelectionListener<ButtonEvent>() {
			@Override
			public void componentSelected(ButtonEvent ce) {
				user.clear();
				retypePassword.clear();
				newPassword.clear();
			}
		}));

		addButton(new Button("Guardar", new SelectionListener<ButtonEvent>() {
			@Override
			public void componentSelected(ButtonEvent ce) {
				commitForm();
			}
		}));

	}

	public boolean validateForm() {
		boolean result = false;
		if (user.getValue().compareTo("") == 0
				|| retypePassword.getValue().compareTo("") == 0
				|| newPassword.getValue().compareTo("") == 0) {
			result = true;
			new AlertDialog("Advertencia",
					"Algun campo vacio, ingrese los datos correctos");
		}

		return result;
	}

}