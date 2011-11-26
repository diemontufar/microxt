package mobile.web.webxt_mvc.client.mvc;

import mobile.web.webxt_mvc.client.components.FormPanel;
import mobile.web.webxt_mvc.client.devform.A101;
import mobile.web.webxt_mvc.client.devform.G101;

import com.extjs.gxt.ui.client.data.ModelData;
import com.extjs.gxt.ui.client.event.EventType;
import com.extjs.gxt.ui.client.event.Events;
import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.event.TabPanelEvent;
import com.extjs.gxt.ui.client.mvc.AppEvent;
import com.extjs.gxt.ui.client.mvc.Dispatcher;
import com.extjs.gxt.ui.client.mvc.View;
import com.extjs.gxt.ui.client.widget.TabItem;

public class FormView extends View {
	private final FormPanel formPanel = new FormPanel();
	
	public FormView(FormController feedController) {
		super(feedController);
	}

	@Override
	protected void handleEvent(AppEvent event) {
		EventType eventType = event.getType();
		if (eventType.equals(AppEvents.Init)) {
			onInit(event);
		} else if (eventType.equals(AppEvents.ProcessSelected)) {
			onProcessSelected(event);
		}
	}
	
	private void onInit(AppEvent event) {
		Dispatcher.forwardEvent(new AppEvent(AppEvents.FormPanelReady,
				formPanel));
	}

	private void onProcessSelected(AppEvent event) {
		final ModelData process = event.getData();
		TabItem tabItem = new TabItem((String) process.get("id"));
		tabItem.setId((String)process.get("id"));
		tabItem.setData("process", process);
		tabItem.setClosable(true);

		// Load the form  
		String processId = process.get("id");
		
		System.out.println(processId);
		if(processId.compareTo("A101")==0){
			// UserStatus
			final A101 form = new A101();
			tabItem.add(form);	
		}else if(processId.compareTo("G101")==0){
			// Parameters
			final G101 form = new G101();
			tabItem.add(form);	
		}
		
		tabItem.addListener(Events.Select, new Listener<TabPanelEvent>() {
			public void handleEvent(TabPanelEvent be) {
				Dispatcher.forwardEvent(new AppEvent(AppEvents.TabSelected,
						process));
			}
		});
		
		formPanel.addTab(tabItem);
	}
}