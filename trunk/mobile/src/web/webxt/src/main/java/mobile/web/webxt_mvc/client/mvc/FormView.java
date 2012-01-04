package mobile.web.webxt_mvc.client.mvc;

import mobile.web.webxt_mvc.client.components.FormPanel;
import mobile.web.webxt_mvc.client.devform.A101;
import mobile.web.webxt_mvc.client.devform.A102;
import mobile.web.webxt_mvc.client.devform.A201;
import mobile.web.webxt_mvc.client.devform.A202;
import mobile.web.webxt_mvc.client.devform.A203;
import mobile.web.webxt_mvc.client.devform.A204;
import mobile.web.webxt_mvc.client.devform.A205;
import mobile.web.webxt_mvc.client.devform.A206;
import mobile.web.webxt_mvc.client.devform.G101;
import mobile.web.webxt_mvc.client.form.MyFormPanel;

import com.extjs.gxt.ui.client.data.ModelData;
import com.extjs.gxt.ui.client.event.EventType;
import com.extjs.gxt.ui.client.event.Events;
import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.event.TabPanelEvent;
import com.extjs.gxt.ui.client.mvc.AppEvent;
import com.extjs.gxt.ui.client.mvc.Dispatcher;
import com.extjs.gxt.ui.client.mvc.View;
import com.extjs.gxt.ui.client.widget.LayoutContainer;
import com.extjs.gxt.ui.client.widget.TabItem;
import com.extjs.gxt.ui.client.widget.layout.CenterLayout;

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
		tabItem.setId((String) process.get("id"));
		tabItem.setData("process", process);
		tabItem.setClosable(true);

		// Load the form
		String processId = process.get("id");

		System.out.println(processId);
		if (processId.compareTo("A101") == 0) {
			tabItem.add(new A101());
		} else if (processId.compareTo("A102") == 0) {
			tabItem.add(new A102());
		} else if (processId.compareTo("A201") == 0) {
			tabItem.add(new A201());
		} else if (processId.compareTo("A202") == 0) {
			tabItem.add(new A202());
		} else if (processId.compareTo("A203") == 0) {
			tabItem.add(new A203());
		} else if (processId.compareTo("A204") == 0) {
			tabItem.add(new A204());
		} else if (processId.compareTo("A205") == 0) {
			tabItem.add(centerForm(new A205()));
		} else if (processId.compareTo("A206") == 0) {
			tabItem.add(new A206());
		} else if (processId.compareTo("G101") == 0) {
			tabItem.add(new G101());
		}

		tabItem.addListener(Events.Select, new Listener<TabPanelEvent>() {
			public void handleEvent(TabPanelEvent be) {
				Dispatcher.forwardEvent(new AppEvent(AppEvents.TabSelected,
						process));
			}
		});

		formPanel.addTab(tabItem);
	}

	private LayoutContainer centerForm(MyFormPanel form) {
		LayoutContainer container = new LayoutContainer();
		container.setLayout(new CenterLayout());
		container.getAriaSupport().setPresentation(true);
		container.add(form);
		return container;
	}
}