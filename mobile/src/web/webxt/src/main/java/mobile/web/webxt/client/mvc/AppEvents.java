package mobile.web.webxt.client.mvc;

import com.extjs.gxt.ui.client.event.EventType;

public class AppEvents {
	public static final EventType Init = new EventType();
	public static final EventType Error = new EventType();
	public static final EventType UIReady = new EventType();
	public static final EventType NavPanelReady = new EventType();
	public static final EventType FormPanelReady = new EventType();
	public static final EventType StatusToolbarReady = new EventType();
	public static final EventType CloseSession = new EventType();
	
	public static final EventType ProcessSelected = new EventType();
	public static final EventType TabSelected = new EventType();
	public static final EventType ProcessReloaded = new EventType();
	public static final EventType UserNotification = new EventType();
}