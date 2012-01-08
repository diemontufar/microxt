package mobile.web.webxt.client;

import mobile.web.webxt.client.mvc.AppController;
import mobile.web.webxt.client.mvc.AppEvents;
import mobile.web.webxt.client.mvc.FormController;
import mobile.web.webxt.client.mvc.NavController;
import mobile.web.webxt.client.mvc.StatusController;

import com.extjs.gxt.ui.client.mvc.Dispatcher;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.maps.client.Maps;

public class Mobile implements EntryPoint {

	public void onModuleLoad() {
		
		Maps.loadMapsApi("", "2", false, new Runnable() {
			public void run() {
				createMVC();
			}
		});
	}
	
	private void createMVC(){
		Dispatcher dispatcher = Dispatcher.get();
		dispatcher.addController(new AppController());
		dispatcher.addController(new NavController());
		dispatcher.addController(new FormController());
		dispatcher.addController(new StatusController());
		dispatcher.dispatch(AppEvents.Init);
		dispatcher.dispatch(AppEvents.UIReady);
	}
}
