package mobile.web.webxt.client;

import mobile.web.webxt.client.forms.LoginDialog;

import com.google.gwt.core.client.EntryPoint;

public class Principal implements EntryPoint {

	public void onModuleLoad() {
		// Call the Login window
		LoginDialog d = new LoginDialog();
		d.show();
		
		//RootPanel.get().add(new EditableGrid2());

	}

}
