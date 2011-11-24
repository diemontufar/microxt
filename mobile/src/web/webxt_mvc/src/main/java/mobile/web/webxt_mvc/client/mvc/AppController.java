package mobile.web.webxt_mvc.client.mvc;



import com.extjs.gxt.ui.client.mvc.AppEvent;
import com.extjs.gxt.ui.client.mvc.Controller;

public class AppController extends Controller {

	private AppView appView;

	public AppController() {
		appView = new AppView(this);
		
		registerEventTypes(AppEvents.Init);
		registerEventTypes(AppEvents.Error);
		registerEventTypes(AppEvents.UIReady);
		registerEventTypes(AppEvents.NavPanelReady);
		registerEventTypes(AppEvents.FormPanelReady);
		registerEventTypes(AppEvents.StatusToolbarReady);
		
	}

	@Override
	public void handleEvent(AppEvent event) {
		forwardToView(appView, event);
	}
}
