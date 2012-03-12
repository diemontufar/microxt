package mobile.web.webxt.client;

import mobile.common.tools.Format;
import mobile.web.webxt.client.mvc.AppController;
import mobile.web.webxt.client.mvc.AppEvents;
import mobile.web.webxt.client.mvc.FormController;
import mobile.web.webxt.client.mvc.NavController;
import mobile.web.webxt.client.mvc.StatusController;

import com.extjs.gxt.ui.client.event.ComponentEvent;
import com.extjs.gxt.ui.client.event.Events;
import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.mvc.Dispatcher;
import com.extjs.gxt.ui.client.widget.Document;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.maps.client.Maps;
import com.google.gwt.user.client.Event;

public class Mobile implements EntryPoint {

	public void onModuleLoad() {

		Maps.loadMapsApi(Format.mapsAPIKey, "2", false, new Runnable() {
			public void run() {
				createMVC();
				disableRightClick();
				enableReloadForm();
			}
		});
	}

	private void createMVC() {
		System.out.println("Starting application");
		Dispatcher dispatcher = Dispatcher.get();
		dispatcher.addController(new AppController());
		dispatcher.addController(new NavController());
		dispatcher.addController(new StatusController());
		dispatcher.addController(new FormController());
		dispatcher.dispatch(AppEvents.Init);
		dispatcher.dispatch(AppEvents.UIReady);
	}

	/**
	 * Disable the browser's default right click response.
	 */
	private void disableRightClick() {

		Document.get().addListener(Events.OnContextMenu, new Listener<ComponentEvent>() {
			public void handleEvent(ComponentEvent be) {
				be.preventDefault();
			}
		});
		Document.get().sinkEvents(Event.ONCONTEXTMENU);
	}

	/**
	 * Enable the reload form function triggered by the F2 key (113)
	 */
	private void enableReloadForm() {
		Document.get().addListener(Events.OnKeyUp, new Listener<ComponentEvent>() {
			public void handleEvent(ComponentEvent ke) {
				if (ke.getKeyCode() == 113) {
					Dispatcher.forwardEvent(AppEvents.ProcessReloaded);
					ke.preventDefault();
				}
			}
		});
		Document.get().sinkEvents(Event.ONKEYUP);
	}
}
