package mobile.web.webxt.client.mvc;


import com.extjs.gxt.ui.client.mvc.AppEvent;
import com.extjs.gxt.ui.client.mvc.Controller;

public class StatusController extends Controller {

	private StatusView statusView;

	public StatusController() {
		registerEventTypes(AppEvents.Init);
		registerEventTypes(AppEvents.Error);
		registerEventTypes(AppEvents.UIReady);
		registerEventTypes(AppEvents.ProcessSelected);
		registerEventTypes(AppEvents.TabSelected);
		registerEventTypes(AppEvents.UserNotification);
	}

	@Override
	public void handleEvent(AppEvent event) {
		forwardToView(statusView, event);
	}

	@Override
	public void initialize() {
		super.initialize();
		statusView = new StatusView(this);
	}
}
