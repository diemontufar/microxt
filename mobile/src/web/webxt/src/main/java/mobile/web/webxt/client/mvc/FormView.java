package mobile.web.webxt.client.mvc;

import mobile.web.webxt.client.components.FormPanel;
import mobile.web.webxt.client.devform.A101;
import mobile.web.webxt.client.devform.A102;
import mobile.web.webxt.client.devform.A103;
import mobile.web.webxt.client.devform.A104;
import mobile.web.webxt.client.devform.A105;
import mobile.web.webxt.client.devform.A201;
import mobile.web.webxt.client.devform.A202;
import mobile.web.webxt.client.devform.A203;
import mobile.web.webxt.client.devform.A204;
import mobile.web.webxt.client.devform.A205;
import mobile.web.webxt.client.devform.A206;
import mobile.web.webxt.client.devform.B001;
import mobile.web.webxt.client.devform.B002;
import mobile.web.webxt.client.devform.B003;
import mobile.web.webxt.client.devform.B004;
import mobile.web.webxt.client.devform.B005;
import mobile.web.webxt.client.devform.B006;
import mobile.web.webxt.client.devform.B007;
import mobile.web.webxt.client.devform.B101;
import mobile.web.webxt.client.devform.B102;
import mobile.web.webxt.client.devform.B103;
import mobile.web.webxt.client.devform.C001;
import mobile.web.webxt.client.devform.C002;
import mobile.web.webxt.client.devform.C003;
import mobile.web.webxt.client.devform.C004;
import mobile.web.webxt.client.devform.C005;
import mobile.web.webxt.client.devform.C101;
import mobile.web.webxt.client.devform.C102;
import mobile.web.webxt.client.devform.C103;
import mobile.web.webxt.client.devform.C104;
import mobile.web.webxt.client.devform.C105;
import mobile.web.webxt.client.devform.C201;
import mobile.web.webxt.client.devform.C202;
import mobile.web.webxt.client.devform.C301;
import mobile.web.webxt.client.devform.C302;
import mobile.web.webxt.client.devform.C401;
import mobile.web.webxt.client.devform.G101;
import mobile.web.webxt.client.devform.G301;
import mobile.web.webxt.client.devform.G302;
import mobile.web.webxt.client.devform.G303;
import mobile.web.webxt.client.devform.G304;

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
		Dispatcher.forwardEvent(new AppEvent(AppEvents.FormPanelReady, formPanel));
	}

	private void onProcessSelected(AppEvent event) {
		final ModelData process = event.getData();
		TabItem tabItem = new TabItem((String) process.get("id"));
		tabItem.setId((String) process.get("id"));
		tabItem.setData("process", process);
		tabItem.setClosable(true);

		// Load the form
		String processId = process.get("id");

		System.out.println("Process seleced>>" + processId);

		if (processId.compareTo("A101") == 0) {
			tabItem.add(new A101());
		} else if (processId.compareTo("A102") == 0) {
			tabItem.add(new A102());
		} else if (processId.compareTo("A103") == 0) {
			tabItem.add(new A103());
		} else if (processId.compareTo("A104") == 0) {
			tabItem.add(new A104());
		} else if (processId.compareTo("A105") == 0) {
			tabItem.add(new A105());
		} else if (processId.compareTo("A201") == 0) {
			tabItem.add(new A201());
		} else if (processId.compareTo("A202") == 0) {
			tabItem.add(new A202());
		} else if (processId.compareTo("A203") == 0) {
			tabItem.add(new A203());
		} else if (processId.compareTo("A204") == 0) {
			tabItem.add(new A204());
		} else if (processId.compareTo("A205") == 0) {
			tabItem.add(new A205());
		} else if (processId.compareTo("A206") == 0) {
			tabItem.add(new A206());
		} else if (processId.compareTo("B001") == 0) {
			tabItem.add(new B001());
		} else if (processId.compareTo("B002") == 0) {
			tabItem.add(new B002());
		} else if (processId.compareTo("B003") == 0) {
			tabItem.add(new B003());
		} else if (processId.compareTo("B004") == 0) {
			tabItem.add(new B004());
		} else if (processId.compareTo("B005") == 0) {
			tabItem.add(new B005());
		} else if (processId.compareTo("B006") == 0) {
			tabItem.add(new B006());
		} else if (processId.compareTo("B007") == 0) {
			tabItem.add(new B007());
		} else if (processId.compareTo("B101") == 0) {
			tabItem.add(new B101());
		} else if (processId.compareTo("B102") == 0) {
			tabItem.add(new B102());
		} else if (processId.compareTo("B103") == 0) {
			tabItem.add(new B103());
		} else if (processId.compareTo("C001") == 0) {
			tabItem.add(new C001());
		} else if (processId.compareTo("C002") == 0) {
			tabItem.add(new C002());
		} else if (processId.compareTo("C003") == 0) {
			tabItem.add(new C003());
		} else if (processId.compareTo("C004") == 0) {
			tabItem.add(new C004());
		} else if (processId.compareTo("C005") == 0) {
			tabItem.add(new C005());
		} else if (processId.compareTo("C101") == 0) {
			tabItem.add(new C101());
		} else if (processId.compareTo("C102") == 0) {
				tabItem.add(new C102());
		} else if (processId.compareTo("C103") == 0) {
			tabItem.add(new C103());
		} else if (processId.compareTo("C104") == 0) {
			tabItem.add(new C104());
		} else if (processId.compareTo("C105") == 0) {
			tabItem.add(new C105());
		} else if (processId.compareTo("C201") == 0) {
			tabItem.add(new C201());
		} else if (processId.compareTo("C202") == 0) {
			tabItem.add(new C202());
		} else if (processId.compareTo("C301") == 0) {
			tabItem.add(new C301());
		} else if (processId.compareTo("C302") == 0) {
			tabItem.add(new C302());
		} else if (processId.compareTo("C401") == 0) {
			tabItem.add(new C401());
		} else if (processId.compareTo("G101") == 0) {
			tabItem.add(new G101());
		} else if (processId.compareTo("G301") == 0) {
			tabItem.add(new G301());
		} else if (processId.compareTo("G302") == 0) {
			tabItem.add(new G302());
		} else if (processId.compareTo("G303") == 0) {
			tabItem.add(new G303());
		} else if (processId.compareTo("G304") == 0) {
			tabItem.add(new G304());
		}

		formPanel.addTab(tabItem);

		tabItem.addListener(Events.Select, new Listener<TabPanelEvent>() {
			public void handleEvent(TabPanelEvent be) {
				Dispatcher.forwardEvent(new AppEvent(AppEvents.TabSelected, process));
			}
		});

	}
}