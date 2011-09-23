package mobile.web.webxt.server;


import mobile.web.webxt.client.services.LoginService;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

public class loginServiceImpl extends RemoteServiceServlet implements
LoginService {

	private static final long serialVersionUID = 1L;

	public String sayHello(String sender) {
		// TODO Auto-generated method stub
		return "Hola Mundo";
	}

	public Boolean login(String name, String pass) {
		// TODO Auto-generated method stub
		
		if(name.compareTo("admin")==0 && pass.compareTo("admin")==0){
			return true;
		}
		
		return false;
	}
		
}
