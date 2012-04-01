package mobile.web.webxt.client.devform;

import mobile.common.tools.Format;
import mobile.web.webxt.client.data.form.DataSource;
import mobile.web.webxt.client.data.form.DataSourceType;
import mobile.web.webxt.client.form.MyFormPanel;
import mobile.web.webxt.client.form.MyGeneralForm;
import mobile.web.webxt.client.form.validations.Validate;
import mobile.web.webxt.client.form.widgets.InputBox;
import mobile.web.webxt.client.form.widgets.MyLabel;
import mobile.web.webxt.client.form.widgets.RowContainer;
import mobile.web.webxt.client.windows.MobileError;

import com.extjs.gxt.ui.client.Style.HorizontalAlignment;
import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.form.FieldSet;
import com.extjs.gxt.ui.client.widget.layout.FlowLayout;
import com.google.gwt.user.client.Element;
import com.googlecode.gwt.crypto.client.TripleDesCipher;

/**
 * PROCESS FOR CHANGING PASSWORD (USER)
 */
public class A205 extends MyGeneralForm {
	private final static String PROCESS = "A205";

	// Constants
	final int FORM_WIDTH = 360;
	final int LABEL_WIDTH = 120;

	MyFormPanel form;

	RowContainer row;
	MyLabel label;
	Button save, clear;

	InputBox oldPassword, oldPassword_, newPassword, newPassword_, retypePassword;
	TripleDesCipher cipher;

	public A205() {
		super(PROCESS);
	}

	@Override
	protected void onRender(Element parent, int index) {
		super.onRender(parent, index);
		cipher = new TripleDesCipher();
		cipher.setKey(Format.GWT_DES_KEY);
		createForm();
	}

	private void createForm() {
		// Form panel
		form = new MyFormPanel(this, "Cambio de Contraseña", FORM_WIDTH);
		form.setLayout(new FlowLayout());

		FieldSet fieldSet = new FieldSet();
		fieldSet.setHeading("");
		fieldSet.setCollapsible(false);
		fieldSet.setBorders(false);

		row = new RowContainer();
		label = new MyLabel("Contraseña anterior:", LABEL_WIDTH);
		row.add(label);
		oldPassword = new InputBox(150, 15);
		oldPassword.setType(Validate.PASSWORD);
		oldPassword.setPassword(true);
		oldPassword.setAllowBlank(false);
		row.add(oldPassword);
		fieldSet.add(row);

		row = new RowContainer();
		label = new MyLabel("Nueva contraseña:", LABEL_WIDTH);
		row.add(label);
		newPassword = new InputBox(150, 15);
		newPassword.setType(Validate.PASSWORD);
		newPassword.setPassword(true);
		newPassword.setAllowBlank(false);
		row.add(newPassword);
		fieldSet.add(row);

		row = new RowContainer();
		label = new MyLabel("Repita contraseña:", LABEL_WIDTH);
		row.add(label);
		retypePassword = new InputBox(150, 15);
		retypePassword.setType(Validate.PASSWORD);
		retypePassword.setPassword(true);
		retypePassword.setAllowBlank(false);
		row.add(retypePassword);
		fieldSet.add(row);

		form.add(fieldSet);

		// Control fields
		oldPassword_ = new InputBox();
		oldPassword_.setVisible(false);
		oldPassword_.setDataSource(new DataSource("oldPassword", DataSourceType.CONTROL));
		form.add(oldPassword_);

		newPassword_ = new InputBox();
		newPassword_.setVisible(false);
		newPassword_.setDataSource(new DataSource("newPassword", DataSourceType.CONTROL));
		form.add(newPassword_);

		// Save button
		save = new Button("Guardar", new SelectionListener<ButtonEvent>() {
			@Override
			public void componentSelected(ButtonEvent ce) {
				if (form.isValid()) {
					boolean error = false;
					String oldPass = oldPassword.getValue();
					String newPass = newPassword.getValue();
					String newPass2 = retypePassword.getValue();

					if (oldPass.compareTo(newPass) == 0) {
						error = true;
						MobileError.report("La nueva contraseña debe ser diferente de la anterior");
					}

					if (newPass.compareTo(newPass2) != 0) {
						error = true;
						MobileError.report("Contraseñas no coinciden");
					}

					if (!error) {
						try {
							String oldEncrypted = cipher.encrypt(oldPassword.getValue());
							String newEncrypted = cipher.encrypt(newPassword.getValue());

							oldPassword_.setValue(oldEncrypted);
							newPassword_.setValue(newEncrypted);

							form.commitForm();
						} catch (Exception e) {
							e.printStackTrace();
							MobileError.report(e);
						}
					}
				}
			}
		});

		form.setButtonAlign(HorizontalAlignment.CENTER);
		form.addButton(save);

		add(form);
	}
}