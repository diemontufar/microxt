package mobile.web.webxt.client.mvc;


import com.extjs.gxt.ui.client.data.ModelData;
import com.extjs.gxt.ui.client.event.EventType;
import com.extjs.gxt.ui.client.mvc.AppEvent;
import com.extjs.gxt.ui.client.mvc.Dispatcher;
import com.extjs.gxt.ui.client.mvc.View;
import com.extjs.gxt.ui.client.widget.Status;
import com.extjs.gxt.ui.client.widget.toolbar.ToolBar;

public class StatusView extends View {

	private final Status status = new Status();
	private final ToolBar toolBar = new ToolBar();

	public StatusView(StatusController statusController) {
		super(statusController);
	}

	@Override
	protected void handleEvent(AppEvent event) {
		EventType eventType = event.getType();
		if (eventType.equals(AppEvents.Init)) {
			onInit();
			setStatus("Listo");
		} else if (eventType.equals(AppEvents.ProcessSelected)) {
			ModelData model = event.getData();
			setStatus("Proceso seleccionado - (" + model.get("id") + ")");
		} else if (eventType.equals(AppEvents.TabSelected)) {
			ModelData model = event.getData();
			setStatus("Proceso seleccionado - (" + model.get("id") + ")");
		} else if (eventType.equals(AppEvents.UserNotification)) {
			String message = event.getData();
			setStatus(message);
		}
	}

	private void onInit() {
		status.setWidth("100%");
		status.setBox(true);
		toolBar.add(status);
		Dispatcher.forwardEvent(new AppEvent(AppEvents.StatusToolbarReady,
				toolBar));
	}

	public void setStatus(String message) {
		status.setText(message);
	}
}
