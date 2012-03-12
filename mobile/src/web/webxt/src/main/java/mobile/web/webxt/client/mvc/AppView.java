package mobile.web.webxt.client.mvc;



import com.extjs.gxt.ui.client.Style.LayoutRegion;
import com.extjs.gxt.ui.client.event.EventType;
import com.extjs.gxt.ui.client.mvc.AppEvent;
import com.extjs.gxt.ui.client.mvc.View;
import com.extjs.gxt.ui.client.util.Margins;
import com.extjs.gxt.ui.client.widget.Component;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.HtmlContainer;
import com.extjs.gxt.ui.client.widget.Viewport;
import com.extjs.gxt.ui.client.widget.layout.BorderLayout;
import com.extjs.gxt.ui.client.widget.layout.BorderLayoutData;
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
		}
	}
	
	protected void onInit(AppEvent event) {
		viewport = new Viewport();
		viewport.setLayout(new BorderLayout());
		
		createNorth();
	}

	private void onError(AppEvent event) {
		
	}

	private void createNorth() {
		StringBuffer sb = new StringBuffer();
		sb.append("</div><div id=mobile-title>Mobile - Microcréditos</div>");

		northPanel = new HtmlContainer(sb.toString());
		northPanel.setStateful(false);
		northPanel.setId("mobile-header");
		northPanel.addStyleName("x-small-editor");

		BorderLayoutData data = new BorderLayoutData(LayoutRegion.NORTH, 33);
		data.setMargins(new Margins());
		viewport.add(northPanel, data);
	}

	private void onFormPanelReady(AppEvent event) {
		BorderLayoutData data = new BorderLayoutData(LayoutRegion.CENTER);
		data.setMargins(new Margins(5, 5, 5, 0));
		
		//ContentPanel formPanel = event.getData();
		formPanel = event.getData();
		viewport.add(formPanel, data);
	}

	private void onNavPanelReady(AppEvent event) {
		BorderLayoutData data = new BorderLayoutData(LayoutRegion.WEST, 250,
				150, 350);
		data.setMargins(new Margins(5, 5, 5, 5));
		data.setCollapsible(true);
		
		ContentPanel panel = event.getData();
		
		viewport.add(panel, data);
	}

	private void onStatusToolbarReady(AppEvent event) {
		BorderLayoutData data = new BorderLayoutData(LayoutRegion.SOUTH,27);
		data.setMargins(new Margins(0, 5, 5, 5));
		
		Component statusBar = event.getData();
		
		viewport.add(statusBar, data);
	}

	private void onUIReady(AppEvent event) {
				RootPanel.get().add(viewport);
	}
}
