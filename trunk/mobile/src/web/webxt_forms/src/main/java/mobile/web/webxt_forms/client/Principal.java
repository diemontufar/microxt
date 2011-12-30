package mobile.web.webxt_forms.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.maps.client.MapOptions;
import com.google.gwt.maps.client.MapTypeId;
import com.google.gwt.maps.client.MapWidget;
import com.google.gwt.maps.client.base.LatLng;
import com.google.gwt.user.client.ui.RootPanel;

public class Principal implements EntryPoint {

	 private MapWidget mapWidget;

	  // GWT module entry point method.
	  public void onModuleLoad() {
	    final MapOptions options = new MapOptions();
	    // Zoom level. Required
	    options.setZoom(8);
	    // Open a map centered on Cawker City, KS USA. Required
	    options.setCenter(new LatLng(39.509, -98.434));
	    // Map type. Required.
	    options.setMapTypeId(new MapTypeId().getRoadmap());
	    
	    // Enable maps drag feature. Disabled by default.
	    options.setDraggable(true);
	    // Enable and add default navigation control. Disabled by default.
	    options.setNavigationControl(true);
	    // Enable and add map type control. Disabled by default.
	    options.setMapTypeControl(true);
	    mapWidget = new MapWidget(options);
	    mapWidget.setSize("800px", "600px");
	    
	    
	    // Add the map to the HTML host page
	    RootPanel.get("mapsTutorial").add(mapWidget);
	  }
}
