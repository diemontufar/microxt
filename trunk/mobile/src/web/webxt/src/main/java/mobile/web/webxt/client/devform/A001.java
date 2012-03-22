package mobile.web.webxt.client.devform;

import mobile.common.tools.Format;
import mobile.web.webxt.client.MobileConstants;
import mobile.web.webxt.client.data.form.DataSource;
import mobile.web.webxt.client.data.form.DataSourceType;
import mobile.web.webxt.client.data.form.Reference;
import mobile.web.webxt.client.form.MyFormPanel;
import mobile.web.webxt.client.form.MyGeneralForm;
import mobile.web.webxt.client.form.validations.Validate;
import mobile.web.webxt.client.form.widgets.ComboForm;
import mobile.web.webxt.client.form.widgets.InputBox;
import mobile.web.webxt.client.form.widgets.MyComboBox;
import mobile.web.webxt.client.form.widgets.MyLabel;
import mobile.web.webxt.client.form.widgets.RowContainer;
import mobile.web.webxt.client.form.widgetsgrid.ArrayColumnData;
import mobile.web.webxt.client.form.widgetsgrid.MyColumnData;
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
import com.extjs.gxt.ui.client.widget.LayoutContainer;
import com.extjs.gxt.ui.client.widget.Status;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.form.FieldSet;
import com.extjs.gxt.ui.client.widget.layout.CardLayout;
import com.extjs.gxt.ui.client.widget.layout.FlowLayout;
import com.extjs.gxt.ui.client.widget.toolbar.FillToolItem;
import com.googlecode.gwt.crypto.client.TripleDesCipher;

public class A001 extends Dialog {
	private final static String PROCESS = "A001";

	// Constants
	private final int FORM_WIDTH = 350;
	private final int FORM_HEIGHT = 90;
	private final int LABEL_WIDTH = 70;

	// Forms
	private final MyGeneralForm formContainerLogin;
	private final MyFormPanel formLogin;
	private final CardLayout cardLayout = new CardLayout();
	private final LayoutContainer loginContainer = new LayoutContainer(new FlowLayout());
	private final LayoutContainer profileContainer = new LayoutContainer(new FlowLayout());

	// Fields
	private InputBox user, password, encPassword, responseCode, responseMsg, host, channel, session;
	private InputBox profileCounter, profile;
	private MyComboBox comboProfile;
	private Button loginButton;
	private Status status;

	private TripleDesCipher cipher;

	public A001() {
		cipher = new TripleDesCipher();
		cipher.setKey(Format.GWT_DES_KEY);

		// General form
		formContainerLogin = new MyGeneralForm(PROCESS);
		formContainerLogin.setBorders(false);

		// Login form
		formLogin = new MyFormPanel(formContainerLogin, "", FORM_WIDTH) {
			@Override
			protected boolean postQuery() {
				onResponse();
				return true;
			}
			@Override
			protected void postError() {
				onError();
			}
		};
		formLogin.setHeaderVisible(false);
		formLogin.setFrame(false);
		formLogin.setBorders(false);
		formLogin.setBodyBorder(false);
		formLogin.setStyleAttribute("padding", "0px");

		// Login container
		formLogin.setLayout(cardLayout);
		formLogin.add(loginContainer);
		formLogin.add(profileContainer);
		formLogin.setHeight(FORM_HEIGHT);
		
		cardLayout.setActiveItem(loginContainer);

		// Create form
		createForm();
	}

	public void createForm() {
		// Login container
		setClosable(false);
		setButtonAlign(HorizontalAlignment.LEFT);
		setButtons("");
		setIcon(IconHelper.createStyle("user"));
		setHeading("Inicio de sesión");
		setModal(true);
		setBodyBorder(true);
		//setBodyStyle("padding: 8px;background: none");
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

		// formLogin.add(fieldSet);
		loginContainer.add(fieldSet);

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

		// Profile/profiles
		profileCounter = new InputBox();
		profileCounter.setVisible(false);
		profileCounter.setDataSource(new DataSource("profileCounter", DataSourceType.CONTROL));
		formLogin.add(profileCounter);

		profile = new InputBox();
		profile.setVisible(false);
		profile.setDataSource(new DataSource("profile", DataSourceType.CONTROL));
		formLogin.add(profile);

		// Profile container
		FieldSet fieldSet2 = new FieldSet();
		fieldSet2.setHeading("");
		fieldSet2.setCollapsible(false);
		fieldSet2.setBorders(false);
		fieldSet2.setWidth(270);

		row = new RowContainer();
		label = new MyLabel("Rol:", LABEL_WIDTH);
		row.add(label);
		
		comboProfile = new ComboForm(60);
		comboProfile.setProcess("G203");

		Reference refProfile = new Reference("prof", "Profile");
		final ArrayColumnData cdata = new ArrayColumnData();
		cdata.add(new MyColumnData("prof", "profileId", "Id", 60));
		cdata.add(new MyColumnData("prof", "description", "Descripción", 150));
		comboProfile.setQueryData(refProfile, cdata);
		comboProfile.setDisplayField("profileId");
		comboProfile.addDependency(user, "userId");
		row.add(comboProfile);
		
		fieldSet2.add(row);
		profileContainer.add(fieldSet2);

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
		if (cardLayout.getActiveItem() == loginContainer) {
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

			// Remote login
			status.show();
			getButtonBar().disable();
			formLogin.queryForm();
		} else {
			String profile = comboProfile.getRawValue();
			onProfileSelected(profile);
		}
	}

	private void onResponse() {
		status.hide();
		getButtonBar().enable();
		loginButton.enable();
		
		if (responseCode.getValue() != null && responseCode.getValue().compareTo("1") == 0) {
			// Register global variables
			Registry.register(MobileConstants.USER, user.getValue());
			Registry.register(MobileConstants.HOST, host.getValue());
			Registry.register(MobileConstants.CHANNEL, channel.getValue());
			Registry.register(MobileConstants.SESSION, session.getValue());
			System.out.println("Loggin>> " + user.getValue());
			System.out.println("Loggin>> " + host.getValue());
			System.out.println("Loggin>> " + channel.getValue());
			System.out.println("Loggin>> " + session.getValue());
			
			if (Integer.parseInt(profileCounter.getValue()) == 1) {
				onProfileSelected(profile.getValue());
			} else {
				cardLayout.setActiveItem(profileContainer);
			}
			
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
		}
	}
	
	private void onError() {
		status.hide();
		getButtonBar().enable();
		loginButton.enable();
	}

	private void onProfileSelected(String profile) {
		Registry.register(MobileConstants.PROFILE, profile);
		System.out.println("Loggin>> " + profile);
		
		hide();

		if (responseMsg.getValue() != null) {
			Info.display("Autenticación", responseMsg.getValue());
		}
		Dispatcher.forwardEvent(AppEvents.UIReady);
	}
}