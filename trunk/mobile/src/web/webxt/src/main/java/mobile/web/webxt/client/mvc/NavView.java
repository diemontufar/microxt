package mobile.web.webxt.client.mvc;

import mobile.web.webxt.client.MobileConfig;
import mobile.web.webxt.client.components.NavPanel;

import com.extjs.gxt.ui.client.data.ModelData;
import com.extjs.gxt.ui.client.event.EventType;
import com.extjs.gxt.ui.client.mvc.AppEvent;
import com.extjs.gxt.ui.client.mvc.Dispatcher;
import com.extjs.gxt.ui.client.mvc.View;

public class NavView extends View {
	private final NavPanel navPanel = new NavPanel();

	public NavView(NavController navController) {
		super(navController);
	}

	@Override
	protected void handleEvent(AppEvent event) {
		if(MobileConfig.FORM_DEVELOPMENT){
			
		}else{
			EventType eventType = event.getType();
			if (eventType.equals(AppEvents.Init)) {
				Dispatcher.forwardEvent(new AppEvent(AppEvents.NavPanelReady,
						navPanel));
			} else if (eventType.equals(AppEvents.TabSelected)) {
				onTabSelected(event);
			}
		}
	}

	private void onTabSelected(AppEvent event) {
		ModelData process = event.getData();
		navPanel.selectProcess(process);
	}

}