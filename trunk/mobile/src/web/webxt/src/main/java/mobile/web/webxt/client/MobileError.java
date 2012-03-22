package mobile.web.webxt.client;

import mobile.web.webxt.client.mvc.AppEvents;

import com.extjs.gxt.ui.client.mvc.Dispatcher;

public final class MobileError {

	private MobileError() {
	}

	public static void report(Throwable throwable) {
		Dispatcher.forwardEvent(AppEvents.Error, throwable.getMessage());
	}
	
	public static void report(String message) {
		Dispatcher.forwardEvent(AppEvents.Error, message);
	}
}
