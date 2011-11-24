package mobile.web.webxt_mvc.client.mvc;


import com.extjs.gxt.ui.client.mvc.AppEvent;
import com.extjs.gxt.ui.client.mvc.Controller;

public class FormController extends Controller {

	private FormView feedView;

	public FormController() {
		registerEventTypes(AppEvents.Init);
		registerEventTypes(AppEvents.ProcessSelected);
	}

	@Override
	public void handleEvent(AppEvent event) {
		forwardToView(feedView, event);
	}

	@Override
	public void initialize() {
		super.initialize();
		feedView = new FormView(this);
	}
}