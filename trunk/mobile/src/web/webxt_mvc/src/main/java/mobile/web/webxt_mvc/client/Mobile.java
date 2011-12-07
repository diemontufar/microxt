package mobile.web.webxt_mvc.client;

import mobile.web.webxt_mvc.client.mvc.AppController;
import mobile.web.webxt_mvc.client.mvc.AppEvents;
import mobile.web.webxt_mvc.client.mvc.FormController;
import mobile.web.webxt_mvc.client.mvc.NavController;
import mobile.web.webxt_mvc.client.mvc.StatusController;

import com.extjs.gxt.ui.client.mvc.Dispatcher;
import com.google.gwt.core.client.EntryPoint;

public class Mobile implements EntryPoint {

	public void onModuleLoad() {
		Dispatcher dispatcher = Dispatcher.get();
		dispatcher.addController(new AppController());
		dispatcher.addController(new NavController());
		dispatcher.addController(new FormController());
		dispatcher.addController(new StatusController());
		dispatcher.dispatch(AppEvents.Init);
		dispatcher.dispatch(AppEvents.UIReady);
	}
}