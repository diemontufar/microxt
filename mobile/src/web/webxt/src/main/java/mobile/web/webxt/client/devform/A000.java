package mobile.web.webxt.client.devform;

import mobile.common.tools.Format;
import mobile.web.webxt.client.data.form.DataSource;
import mobile.web.webxt.client.data.form.DataSourceType;
import mobile.web.webxt.client.data.form.Reference;
import mobile.web.webxt.client.form.MyFormPanel;
import mobile.web.webxt.client.form.MyGeneralForm;
import mobile.web.webxt.client.form.validations.Validate;
import mobile.web.webxt.client.form.widgets.InputBox;
import mobile.web.webxt.client.form.widgets.MyLabel;
import mobile.web.webxt.client.form.widgets.RowContainer;

import com.extjs.gxt.ui.client.Style.HorizontalAlignment;
import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.ComponentEvent;
import com.extjs.gxt.ui.client.event.Events;
import com.extjs.gxt.ui.client.event.FieldEvent;
import com.extjs.gxt.ui.client.event.KeyListener;
import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.util.IconHelper;
import com.extjs.gxt.ui.client.widget.Dialog;
import com.extjs.gxt.ui.client.widget.Info;
import com.extjs.gxt.ui.client.widget.LayoutContainer;
import com.extjs.gxt.ui.client.widget.Status;
import com.extjs.gxt.ui.client.widget.Viewport;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.form.FieldSet;
import com.extjs.gxt.ui.client.widget.form.TextField;
import com.extjs.gxt.ui.client.widget.toolbar.FillToolItem;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.ui.RootPanel;
import com.googlecode.gwt.crypto.bouncycastle.DataLengthException;
import com.googlecode.gwt.crypto.bouncycastle.InvalidCipherTextException;
import com.googlecode.gwt.crypto.client.TripleDesCipher;

public class A000 extends Dialog {

	Viewport viewport;

	protected InputBox userName, password, retrievedUser, retrievedPassword;
	protected Button reset, login;
	protected Status status;
	protected boolean userExists;

	private final static String PROCESS = "A000";
	private final static String ENTITY = "UserAccess";

	// Constants
	final int FORM_WIDTH = 350;
	final int LABEL_WIDTH = 60;

	MyFormPanel formLogin;
	MyGeneralForm formContainerLogin;

	RowContainer row;
	MyLabel label;

	String encrypted;
	TripleDesCipher cipher;

	public A000(Viewport viewport) {

		setViewport(viewport);

		cipher = new TripleDesCipher();
		cipher.setKey(Format.GWT_DES_KEY);

		formContainerLogin = new MyGeneralForm(PROCESS);
		formContainerLogin.setReference(new Reference("acc", ENTITY));
		formContainerLogin.setBorders(false);
		formLogin = new MyFormPanel(formContainerLogin, "", FORM_WIDTH);
		formLogin.setHeaderVisible(false);
		formLogin.setFrame(false);
		formLogin.setBorders(false);
		formLogin.setBodyBorder(false);
		formLogin.setStyleAttribute("padding", "0px");
		createForm();
	}

	public Viewport getViewport() {
		return viewport;
	}

	public void setViewport(Viewport viewport) {
		this.viewport = viewport;
	}

	public void createForm() {

		LayoutContainer lc = new LayoutContainer();

		FieldSet fieldSet = new FieldSet();
		fieldSet.setHeading("");
		fieldSet.setCollapsible(false);
		fieldSet.setBorders(false);
		fieldSet.setWidth(270);

		setClosable(false);
		setButtonAlign(HorizontalAlignment.LEFT);
		setButtons("");
		setIcon(IconHelper.createStyle("user"));
		setHeading("Inicio Sesion");
		setModal(true);
		setBodyBorder(true);
		setBodyStyle("padding: 8px;background: none");
		setWidth(300);
		setResizable(false);

		KeyListener keyListener = new KeyListener() {
			public void componentKeyUp(ComponentEvent event) {
				validate();
			}
		};

		row = new RowContainer();
		label = new MyLabel("Usuario:", LABEL_WIDTH);
		row.add(label);

		userName = new InputBox(150, 10, Validate.TEXT);
		userName.setAllowBlank(false);

		userName.addListener(Events.OnBlur, new Listener<FieldEvent>() {
			public void handleEvent(FieldEvent e) {
				retrievedUser.setValue(userName.getValue());
			}
		});

		row.add(userName);

		fieldSet.add(row);

		row = new RowContainer();
		label = new MyLabel("Contraseña:", LABEL_WIDTH);
		row.add(label);

		password = new InputBox(150, 10);
		password.setType(Validate.PASSWORD);
		password.setPassword(true);
		password.setAllowBlank(false);
		password.setFireChangeEventOnSetValue(true);
		password.addKeyListener(keyListener);

		row.add(password);

		fieldSet.add(row);

		KeyListener listener = new KeyListener() {
			@Override
			public void componentKeyPress(ComponentEvent event) {
				if (event.getKeyCode() == 13) {
					login.fireEvent(Events.Select);
				}
				super.componentKeyPress(event);
			}

		};

		password.addKeyListener(listener);
		setFocusWidget(userName);

		lc.add(fieldSet);
		add(lc);

		// Form for retrieving data from user

		retrievedUser = new InputBox(150, 10, Validate.TEXT);
		retrievedUser.setDataSource(new DataSource("acc", "pk_userId", DataSourceType.CRITERION));
		retrievedUser.setFireChangeEventOnSetValue(true);

		retrievedUser.addListener(Events.Change, new Listener<FieldEvent>() {
			public void handleEvent(FieldEvent e) {
				if (e.getValue() != null && userName.getValue() != null) {
					formLogin.queryForm();
				}
			}
		});

		retrievedPassword = new InputBox(150, 300);
		retrievedPassword.setType(Validate.PASSWORD);
		retrievedPassword.setDataSource(new DataSource("acc", "userKey", DataSourceType.RECORD));
		retrievedPassword.setAllowBlank(false);
		retrievedPassword.setVisible(true);

		formLogin.add(retrievedUser);
		formLogin.add(retrievedPassword);
		formLogin.setVisible(false);

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

		reset = new Button("Cancelar");
		reset.addSelectionListener(new SelectionListener<ButtonEvent>() {
			public void componentSelected(ButtonEvent ce) {
				clear();
			}

		});

		login = new Button("Ingresar");
		login.disable();
		login.addSelectionListener(new SelectionListener<ButtonEvent>() {
			public void componentSelected(ButtonEvent ce) {
				onSubmit();
			}
		});

		addButton(reset);
		addButton(login);

	}

	protected void onSubmit() {
		status.show();
		getButtonBar().disable();
		Timer t = new Timer() {

			@Override
			public void run() {
				if (verifyAccess()) {
					showPrincipalPanel();
				} else {

					if (!userExists) {
						Info.display("Error", "Usuario no existe");
					}
					
					Info.display("Informacion", "Acceso Denegado");

					A000.this.show();
					clear();
					cancel();
				}
			}

		};
		t.schedule(700);
	}

	public void showPrincipalPanel() {

		Info.display("Informacion", "Ingreso Exitoso");
		A000.this.hide();
		RootPanel.get().clear();
		RootPanel.get().add(viewport);
	}

	public void clear() {
		userName.clear();
		password.clear();
		retrievedUser.clear();
		retrievedPassword.clear();
		validate();
		userName.focus();
		status.hide();
		getButtonBar().enable();
		reset.enable();
		login.disable();
	}

	protected boolean hasValue(TextField<String> field) {
		return field.getValue() != null && field.getValue().length() > 0;
	}

	protected void validate() {
		login.setEnabled(hasValue(userName) && hasValue(password) && password.getValue().length() > 3);
	}

	public boolean verifyAccess() {

		TripleDesCipher cipher = new TripleDesCipher();
		cipher.setKey(Format.GWT_DES_KEY);

		userExists = true;
		
		try {
			encrypted = cipher.encrypt(password.getValue());
		} catch (DataLengthException e) {
			e.printStackTrace();
		} catch (IllegalStateException e) {
			e.printStackTrace();
		} catch (InvalidCipherTextException e) {
			e.printStackTrace();
		}

		if (retrievedPassword.getValue() == null) {
			userExists = false;
			return false;
		}

		if (encrypted.compareTo(retrievedPassword.getValue()) == 0) {
			return true;
		}

		return false;

	}

}