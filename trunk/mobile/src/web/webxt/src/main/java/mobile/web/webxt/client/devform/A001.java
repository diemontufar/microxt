package mobile.web.webxt.client.devform;

import mobile.common.tools.Format;
import mobile.web.webxt.client.MobileConstants;
import mobile.web.webxt.client.data.form.DataSource;
import mobile.web.webxt.client.data.form.DataSourceType;
import mobile.web.webxt.client.form.MyFormPanel;
import mobile.web.webxt.client.form.MyGeneralForm;
import mobile.web.webxt.client.form.validations.Validate;
import mobile.web.webxt.client.form.widgets.InputBox;
import mobile.web.webxt.client.form.widgets.MyLabel;
import mobile.web.webxt.client.form.widgets.RowContainer;
import mobile.web.webxt.client.mvc.AppEvents;

import com.extjs.gxt.ui.client.Registry;
import com.extjs.gxt.ui.client.Style.HorizontalAlignment;
import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.ComponentEvent;
import com.extjs.gxt.ui.client.event.Events;
import com.extjs.gxt.ui.client.event.KeyListener;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.mvc.Dispatcher;
import com.extjs.gxt.ui.client.util.IconHelper;
import com.extjs.gxt.ui.client.widget.Dialog;
import com.extjs.gxt.ui.client.widget.Info;
import com.extjs.gxt.ui.client.widget.Status;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.form.FieldSet;
import com.extjs.gxt.ui.client.widget.toolbar.FillToolItem;
import com.googlecode.gwt.crypto.client.TripleDesCipher;

public class A001 extends Dialog {
	private final static String PROCESS = "A001";

	// Constants
	final int FORM_WIDTH = 350;
	final int LABEL_WIDTH = 70;

	// Forms
	MyGeneralForm formContainerLogin;
	MyFormPanel formLogin;

	// Fields
	protected InputBox user, password, encPassword, responseCode, responseMsg, host, channel, session, profileCounter;
	protected Button loginButton;
	protected Status status;

	String encrypted;
	TripleDesCipher cipher;

	public A001() {
		cipher = new TripleDesCipher();
		cipher.setKey(Format.GWT_DES_KEY);

		// General form
		formContainerLogin = new MyGeneralForm(PROCESS);
		formContainerLogin.setBorders(false);

		// Loggin form
		formLogin = new MyFormPanel(formContainerLogin, "", FORM_WIDTH) {
			@Override
			protected boolean postQuery() {
				onResponse();
				return true;
			}
		};
		formLogin.setHeaderVisible(false);
		formLogin.setFrame(false);
		formLogin.setBorders(false);
		formLogin.setBodyBorder(false);
		formLogin.setStyleAttribute("padding", "0px");

		// Create form
		createForm();
	}

	public void createForm() {
		setClosable(false);
		setButtonAlign(HorizontalAlignment.LEFT);
		setButtons("");
		setIcon(IconHelper.createStyle("user"));
		setHeading("Inicio de sesión");
		setModal(true);
		setBodyBorder(true);
		setBodyStyle("padding: 8px;background: none");
		setWidth(300);
		setResizable(false);

		FieldSet fieldSet = new FieldSet();
		fieldSet.setHeading("");
		fieldSet.setCollapsible(false);
		fieldSet.setBorders(false);
		fieldSet.setWidth(270);

		RowContainer row = new RowContainer();
		MyLabel label = new MyLabel("Usuario:", LABEL_WIDTH);
		row.add(label);

		user = new InputBox(150, 20, Validate.TEXT);
		user.setAllowBlank(false);
		user.setDataSource(new DataSource("user", DataSourceType.CONTROL));
		row.add(user);

		fieldSet.add(row);

		row = new RowContainer();
		label = new MyLabel("Contraseña:", LABEL_WIDTH);
		row.add(label);

		password = new InputBox(150, 10);
		password.setType(Validate.PASSWORD);
		password.setPassword(true);
		password.setAllowBlank(false);

		row.add(password);

		fieldSet.add(row);

		password.addKeyListener(new KeyListener() {
			@Override
			public void componentKeyPress(ComponentEvent event) {
				if (event.getKeyCode() == 13) {
					loginButton.fireEvent(Events.Select);
				}
				super.componentKeyPress(event);
			}
		});
		setFocusWidget(user);

		formLogin.add(fieldSet);

		// Encrypted password
		encPassword = new InputBox();
		encPassword.setVisible(false);
		encPassword.setDataSource(new DataSource("password", DataSourceType.CONTROL));
		formLogin.add(encPassword);

		// Response code
		responseCode = new InputBox();
		responseCode.setVisible(false);
		responseCode.setDataSource(new DataSource("responseCode", DataSourceType.CONTROL));
		formLogin.add(responseCode);

		// Response message
		responseMsg = new InputBox();
		responseMsg.setVisible(false);
		responseMsg.setDataSource(new DataSource("responseMessage", DataSourceType.CONTROL));
		formLogin.add(responseMsg);
		
		// Host
		host = new InputBox();
		host.setVisible(false);
		host.setDataSource(new DataSource("host", DataSourceType.CONTROL));
		formLogin.add(host);
		
		// Channel
		channel = new InputBox();
		channel.setVisible(false);
		channel.setDataSource(new DataSource("channel", DataSourceType.CONTROL));
		formLogin.add(channel);

		// Session
		session = new InputBox();
		session.setVisible(false);
		session.setDataSource(new DataSource("session", DataSourceType.CONTROL));
		formLogin.add(session);
		
		add(formLogin);
	}

	@Override
	protected void createButtons() {
		super.createButtons();
		status = new Status();
		status.setBusy("Verificando...");
		status.hide();
		status.setAutoWidth(true);
		getButtonBar().add(status);

		getButtonBar().add(new FillToolItem());

		loginButton = new Button("Ingresar");
		loginButton.addSelectionListener(new SelectionListener<ButtonEvent>() {
			public void componentSelected(ButtonEvent ce) {
				onSubmit();
			}
		});

		addButton(loginButton);
	}

	protected void onSubmit() {
		// Encrypt password
		String encrypted = "";
		TripleDesCipher cipher = new TripleDesCipher();
		cipher.setKey(Format.GWT_DES_KEY);
		try {
			encrypted = cipher.encrypt(password.getValue());
		} catch (Exception e) {
			e.printStackTrace();
		}
		encPassword.setValue(encrypted);

		// Remote loggin
		status.show();
		getButtonBar().disable();
		formLogin.queryForm();
	}

	private void onResponse() {
		if (responseCode.getValue() != null && responseCode.getValue().compareTo("1") == 0) {
			hide();
			if (responseMsg.getValue() != null) {
				Info.display("Autenticación", responseMsg.getValue());
			}
			
			Registry.register(MobileConstants.USER, user.getValue());
			Registry.register(MobileConstants.HOST, host.getValue());
			Registry.register(MobileConstants.CHANNEL, channel.getValue());
			Registry.register(MobileConstants.SESSION, session.getValue());
			//Registry.register(MobileConstants.PROFILE, profile.getValue());
			Dispatcher.forwardEvent(AppEvents.UIReady);
			
			System.out.println("Loggin>> " + user.getValue());
			System.out.println("Loggin>> " + host.getValue());
			System.out.println("Loggin>> " + channel.getValue());
			System.out.println("Loggin>> " + session.getValue());
		} else {
			if (responseMsg.getValue() != null) {
				String msg = responseMsg.getValue();
				Info.display("Autenticación", msg);
			} else {
				Info.display("Autenticación", "Ingreso fallido");
			}
			A001.this.show();
			A001.this.formLogin.reset();
			user.focus();
			status.hide();
			getButtonBar().enable();
			loginButton.enable();
		}
	}
}