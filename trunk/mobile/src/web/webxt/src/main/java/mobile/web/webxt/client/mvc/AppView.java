package mobile.web.webxt.client.mvc;

import mobile.web.webxt.client.MobileConfig;
import mobile.web.webxt.client.MobileConstants;
import mobile.web.webxt.client.data.MyHttpProxy;
import mobile.web.webxt.client.devform.A001;

import com.extjs.gxt.ui.client.Registry;
import com.extjs.gxt.ui.client.Style.LayoutRegion;
import com.extjs.gxt.ui.client.event.EventType;
import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.event.MessageBoxEvent;
import com.extjs.gxt.ui.client.mvc.AppEvent;
import com.extjs.gxt.ui.client.mvc.Dispatcher;
import com.extjs.gxt.ui.client.mvc.View;
import com.extjs.gxt.ui.client.util.Margins;
import com.extjs.gxt.ui.client.widget.Component;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.HtmlContainer;
import com.extjs.gxt.ui.client.widget.MessageBox;
import com.extjs.gxt.ui.client.widget.Viewport;
import com.extjs.gxt.ui.client.widget.layout.BorderLayout;
import com.extjs.gxt.ui.client.widget.layout.BorderLayoutData;
import com.extjs.gxt.ui.client.widget.layout.CenterLayout;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.ui.RootPanel;

public class AppView extends View {

	private Viewport viewport;
	private HtmlContainer northPanel;
	private ContentPanel formPanel;

	public AppView(AppController appController) {
		super(appController);
	}

	@Override
	protected void handleEvent(AppEvent event) {
		EventType eventType = event.getType();
		if (eventType.equals(AppEvents.Init)) {
			onInit(event);
		} else if (eventType.equals(AppEvents.NavPanelReady)) {
			onNavPanelReady(event);
		} else if (eventType.equals(AppEvents.FormPanelReady)) {
			onFormPanelReady(event);
		} else if (eventType.equals(AppEvents.StatusToolbarReady)) {
			onStatusToolbarReady(event);
		} else if (eventType.equals(AppEvents.UIReady)) {
			onUIReady(event);
		} else if (eventType.equals(AppEvents.Error)) {
			onError(event);
		} else if (eventType.equals(AppEvents.CloseSession)) {
			onCloseSession(event);
		}
	}

	protected void onInit(AppEvent event) {
		System.out.println("MVC>> AppView on init...");

		if (!MobileConfig.FORM_DEVELOPMENT) {
			showLoggin();
		} else {
			Registry.register(MobileConstants.USER, "ADMIN");
			Registry.register(MobileConstants.HOST, "LOCALHOST");
			Registry.register(MobileConstants.CHANNEL, "PC");
			Registry.register(MobileConstants.PROFILE, "ADM");
			Dispatcher.forwardEvent(AppEvents.UIReady);
		}

		viewport = new Viewport();
		viewport.setLayout(new BorderLayout());

		createNorth();
	}

	private void showLoggin() {
		System.out.println("MVC>> AppView show loggin...");

		Viewport logginviewport;
		logginviewport = new Viewport();
		logginviewport.setLoadingPanelId("loading");
		logginviewport.setVisible(false);

		A001 loginForm = new A001();

		RootPanel.get().add(logginviewport);

		RootPanel.get().add(loginForm);
		loginForm.show();
	}

	protected void onCloseSession(AppEvent event) {
		System.out.println("MVC>> Close session...");

		Boolean showMessage = event.getData();

		if (showMessage) {
			final Listener<MessageBoxEvent> listener = new Listener<MessageBoxEvent>() {
				public void handleEvent(MessageBoxEvent ce) {
					String resp = ce.getButtonClicked().getText();
					if (resp.compareToIgnoreCase("Yes") == 0 || resp.compareToIgnoreCase("Si") == 0) {
						new MyHttpProxy().logout();
						RootPanel.get().clear();

						HtmlContainer hc = new HtmlContainer();
						StringBuilder htmlBuilder = new StringBuilder();
						htmlBuilder
								.append("<p><img src=\"img/logos/microxt_logo.png\" width=\"280\" height=\"80\"></p>");
						htmlBuilder.append("<p style=\"text-align: center;\">Sesión cerrada correctamente</p>");
						hc.setHtml(htmlBuilder.toString());
						hc.setStyleAttribute("border", "1px solid #ccc");
						hc.setStyleAttribute("padding", "15px");
						hc.setStyleAttribute("color", "#444");
						hc.setSize(320, 130);

						Viewport viewport = new Viewport();
						viewport.setLayout(new CenterLayout());
						viewport.add(hc);

						RootPanel.get().add(viewport);
					}
				}
			};
			MessageBox.confirm("Cerrar sesión", "¿Desea cerrar la sesión?", listener);
		} else {
			new MyHttpProxy().logout();
		}

	}

	private void onError(AppEvent event) {
		String message = event.getData();

		MessageBox errorBox = new MessageBox();
		errorBox.setIcon(MessageBox.ERROR);
		errorBox.setTitle("Error");
		errorBox.setButtons(MessageBox.OK);
		errorBox.setMinWidth(400);
		errorBox.setMessage(message);
		errorBox.show();
	}

	private void createNorth() {
		StringBuffer sb = new StringBuffer();
		sb.append("</div><div id=mobile-title><img src=\"img/logos/microxt_logo_header.png\" width=\"110\" height=\"28\"></div>");

		northPanel = new HtmlContainer(sb.toString());
		northPanel.setStateful(false);
		northPanel.setId("mobile-header");
		northPanel.addStyleName("x-small-editor");
		northPanel.setHeight(100);

		BorderLayoutData data = new BorderLayoutData(LayoutRegion.NORTH, 33);
		data.setMargins(new Margins());
		viewport.add(northPanel, data);

	}

	private void onFormPanelReady(AppEvent event) {
		System.out.println("MVC>> AppView on form panel ready...");
		BorderLayoutData data = new BorderLayoutData(LayoutRegion.CENTER);
		data.setMargins(new Margins(5, 5, 5, 0));

		// ContentPanel formPanel = event.getData();
		formPanel = event.getData();
		viewport.add(formPanel, data);
	}

	private void onNavPanelReady(AppEvent event) {
		System.out.println("MVC>> On nav panel ready...");
		BorderLayoutData data = new BorderLayoutData(LayoutRegion.WEST, 250, 150, 350);
		data.setMargins(new Margins(5, 5, 5, 5));
		data.setCollapsible(true);

		ContentPanel panel = event.getData();

		viewport.add(panel, data);
	}

	private void onHideNavPanel() {
		((BorderLayout) viewport.getLayout()).collapse(LayoutRegion.WEST);
	}

	private void onStatusToolbarReady(AppEvent event) {
		System.out.println("MVC>> On status tool bar ready...");
		BorderLayoutData data = new BorderLayoutData(LayoutRegion.SOUTH, 27);
		data.setMargins(new Margins(0, 5, 5, 5));

		Component statusBar = event.getData();

		viewport.add(statusBar, data);
	}

	private void onUIReady(AppEvent event) {
		System.out.println("MVC>> On ui ready...");

		Timer t = new Timer() {
			@Override
			public void run() {
				RootPanel.get().clear();
				RootPanel.get().add(viewport);

				String profile = Registry.get(MobileConstants.PROFILE);
				if (profile != null && profile.compareTo("ASE") == 0) {
					onHideNavPanel();
				}
			}
		};
		t.schedule(500);
	}
}
