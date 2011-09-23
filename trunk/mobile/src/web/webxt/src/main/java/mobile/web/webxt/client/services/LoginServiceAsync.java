package mobile.web.webxt.client.services;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface LoginServiceAsync {

	void sayHello(String sender, AsyncCallback<String> callback);
	void login(String name, String pass, AsyncCallback<Boolean> callback);
		
}

