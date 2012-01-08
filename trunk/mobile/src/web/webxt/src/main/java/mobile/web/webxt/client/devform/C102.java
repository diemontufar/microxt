package mobile.web.webxt.client.devform;

import mobile.web.webxt.client.validations.Validate;
import mobile.web.webxt.client.widgets.InputBox;

import com.extjs.gxt.ui.client.Style.HorizontalAlignment;
import com.extjs.gxt.ui.client.Style.LayoutRegion;
import com.extjs.gxt.ui.client.data.ChangeEvent;
import com.extjs.gxt.ui.client.data.ChangeListener;
import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.EventType;
import com.extjs.gxt.ui.client.event.Events;
import com.extjs.gxt.ui.client.event.FieldEvent;
import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.util.Margins;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.HorizontalPanel;
import com.extjs.gxt.ui.client.widget.Html;
import com.extjs.gxt.ui.client.widget.Label;
import com.extjs.gxt.ui.client.widget.LayoutContainer;
import com.extjs.gxt.ui.client.widget.VerticalPanel;
import com.extjs.gxt.ui.client.widget.Window;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.form.ComboBox;
import com.extjs.gxt.ui.client.widget.form.FormPanel;
import com.extjs.gxt.ui.client.widget.form.FormPanel.LabelAlign;
import com.extjs.gxt.ui.client.widget.form.NumberField;
import com.extjs.gxt.ui.client.widget.form.Radio;
import com.extjs.gxt.ui.client.widget.form.RadioGroup;
import com.extjs.gxt.ui.client.widget.form.SimpleComboBox;
import com.extjs.gxt.ui.client.widget.form.TextArea;
import com.extjs.gxt.ui.client.widget.form.TextField;
import com.extjs.gxt.ui.client.widget.layout.BorderLayout;
import com.extjs.gxt.ui.client.widget.layout.BorderLayoutData;
import com.extjs.gxt.ui.client.widget.layout.CenterLayout;
import com.extjs.gxt.ui.client.widget.layout.FitLayout;
import com.extjs.gxt.ui.client.widget.layout.FormLayout;
import com.google.gwt.maps.client.MapUIOptions;
import com.google.gwt.maps.client.MapWidget;
import com.google.gwt.maps.client.event.MapClickHandler;
import com.google.gwt.maps.client.event.MarkerDragEndHandler;
import com.google.gwt.maps.client.event.PolygonCancelLineHandler;
import com.google.gwt.maps.client.event.PolygonEndLineHandler;
import com.google.gwt.maps.client.event.PolygonLineUpdatedHandler;
import com.google.gwt.maps.client.event.PolylineCancelLineHandler;
import com.google.gwt.maps.client.event.PolylineEndLineHandler;
import com.google.gwt.maps.client.event.PolylineLineUpdatedHandler;
import com.google.gwt.maps.client.geom.LatLng;
import com.google.gwt.maps.client.geom.Point;
import com.google.gwt.maps.client.geom.Size;
import com.google.gwt.maps.client.overlay.Icon;
import com.google.gwt.maps.client.overlay.Marker;
import com.google.gwt.maps.client.overlay.MarkerOptions;
import com.google.gwt.maps.client.overlay.Overlay;
import com.google.gwt.maps.client.overlay.PolyStyleOptions;
import com.google.gwt.maps.client.overlay.Polygon;
import com.google.gwt.maps.client.overlay.Polyline;
import com.google.gwt.user.client.Element;
import com.google.gwt.user.client.Event;
import com.google.gwt.user.client.ui.FlexTable;

public class C102 extends LayoutContainer {

	private final String process = "C101";

	private final Integer PAGE_SIZE = 5;

	// Fields for coordinates:
	NumberField longitude = new NumberField();
	NumberField latitude = new NumberField();

	private int MAX_POINTS = 3; //4 points
	private int MAX_DOWNPANEL_HEIGHT = 400; // pixels
	private int MAX_UPPANEL_HEIGHT = 100; // pixels
	private int MAX_LEFTPANEL_HEIGHT = 250; // pixels
	private double INITIAL_LATITUDE = -2.8989009; // Cuenca-Ecuador
	private double INITIAL_LONGITUDE = -78.997668; // Cuenca-Ecuador
	private int ZOOM_LEVEL_NORMAL = 14;
	private String color = "#FF0000"; // polyline
	private double opacity = 1.0; // polyline
	private int weight = 1; // polyline
	private Polyline lastPolyline = null;
	private Polygon lastPolygon = null;
	private boolean fillFlag = false;
	
	Radio pointRadio = new Radio();  
	Radio routeRadio = new Radio(); 
	Radio polygonRadio = new Radio(); 
	
	Button save = new Button("Guardar");
	Button cancel = new Button("Cancelar");
	
	TextField<String> code =new TextField<String>();
	TextArea description = new TextArea(); 
    

	private MapWidget map = new MapWidget(LatLng.newInstance(INITIAL_LATITUDE,
			INITIAL_LONGITUDE), ZOOM_LEVEL_NORMAL);

	Marker[] points;
	LatLng[] coordinates;

	int counter = 0;

	@Override
	protected void onRender(Element parent, int index) {
		super.onRender(parent, index);
		setWidth(800);
		final BorderLayout layout = new BorderLayout();
		setLayout(layout);
		createPanel();
	}

	private void createPanel() {

		ContentPanel cpMap = new ContentPanel();
		cpMap.setHeight(MAX_DOWNPANEL_HEIGHT);
		cpMap.setHeaderVisible(false);

		ContentPanel cpForm = new ContentPanel();
		cpForm.setHeight(MAX_DOWNPANEL_HEIGHT);
		cpForm.setHeading("Ingreso Zonas Geograficas");
		cpForm.addButton(save);
		cpForm.addButton(cancel);
		cpForm.setButtonAlign(HorizontalAlignment.CENTER);
		cpForm.setFrame(true);

		BorderLayoutData headerData = new BorderLayoutData(LayoutRegion.NORTH,
				MAX_UPPANEL_HEIGHT);
		headerData.setCollapsible(true);
		headerData.setHideCollapseTool(true);
		headerData.setSplit(true);
		headerData.setMargins(new Margins(0, 0, 5, 0));

		BorderLayoutData mapData = new BorderLayoutData(LayoutRegion.CENTER);
		mapData.setCollapsible(true);
		mapData.setFloatable(true);
		mapData.setHideCollapseTool(true);
		mapData.setSplit(true);
		mapData.setMargins(new Margins(0, 0, 5, 0));

		BorderLayoutData formData = new BorderLayoutData(LayoutRegion.EAST,
				MAX_LEFTPANEL_HEIGHT);
		formData.setSplit(true);
		formData.setCollapsible(true);
		formData.setMargins(new Margins(0, 5, 0, 0));

		cpMap.add(createMap());
		cpForm.add(createForm());

		add(cpMap, mapData);
		add(cpForm, formData);

	}

	private FormPanel createForm() {

		final FormPanel panel = new FormPanel();
		panel.setHeaderVisible(false);
		panel.setFrame(false);
		panel.setHeight("600");
		panel.setWidth("250");
		int fieldHeights = 80;

		VerticalPanel vPoints = new VerticalPanel();

		FlexTable table = new FlexTable();
		table.getElement().getStyle().setProperty("margin", "2px");
		table.setCellSpacing(5);
		table.setCellPadding(2);

		// Point Names:
		longitude.setName("longitude");
		latitude.setName("latitude");

		// Point Heights
		longitude.setWidth(fieldHeights);
		latitude.setWidth(fieldHeights);
		longitude.setEditable(false);
		latitude.setEditable(false);

		// Point Labels:
		table.setHTML(0, 1, "<div style='font-size: 12px; width: 35px'>Coord:</span>");

		// Field positions
		table.setWidget(0, 2, latitude);
		table.setWidget(0, 3, longitude);
		
		
		HorizontalPanel hPanel = new HorizontalPanel();
		hPanel.add(new Html("<div style='font-size: 12px; width: 230px'><center><B>INFORMACION<B></center></span>"));
				
		FormPanel fpCodDesc = new FormPanel();
		fpCodDesc.setFrame(false);
		fpCodDesc.setHeaderVisible(false);
		fpCodDesc.setBorders(false);
		
		FormLayout layoutForm = new FormLayout();  
		layoutForm.setLabelAlign(LabelAlign.TOP); 
	    fpCodDesc.setLayout(layoutForm);
		fpCodDesc.add(hPanel);
		fpCodDesc.add(new Label());
		code.setFieldLabel("Codigo");
		code.setWidth(90);
		code.setAllowBlank(false);  
		fpCodDesc.add(code);
		fpCodDesc.add(new Label());
		description.setFieldLabel("Descripcion");
		description.setWidth(90);
		description.setEmptyText("Ingrese la descripcion de la zona");
		fpCodDesc.add(description);
		
		

		Button btnExpand = new Button("Verificar Markers",
				new SelectionListener<ButtonEvent>() {
					@Override
					public void componentSelected(ButtonEvent ce) {
						// AQUI SE PONE EL PROCESO FINAL A EJECUTAR
						// System.out.println("Markers Final:");
						for (int i = 0; i <= MAX_POINTS; i++) {
							if (points[i] != null) {
								System.out.println("Punto " + i + ": "
										+ points[i].getLatLng().getLatitude()
										+ ","
										+ points[i].getLatLng().getLongitude());
							}
						}

						//createPolyline();
						//createPolygon();
					}
				});

		
		vPoints.add(new Label());
		vPoints.add(new Label("Tipo de Coordenadas:"));
		
		pointRadio.setBoxLabel("Punto");
		routeRadio.setBoxLabel("Ruta"); 
		polygonRadio.setBoxLabel("Poligono"); 
	    
		pointRadio.setValue(true);  
	  
	    RadioGroup radioGroup = new RadioGroup();  
	    radioGroup.add(pointRadio);  
	    radioGroup.add(routeRadio);
	    radioGroup.add(polygonRadio);
	    
	    
	    radioGroup.addListener(Events.Change, new Listener<FieldEvent>() {
	        public void handleEvent(FieldEvent fe) {
	            //GWT.log(fe.getField().getName() + " : " + fe.getValue());
	        	
	        	map.clearOverlays();
	        	counter=0;
	        	clearFields();
	        }
	    });
	    
		
		vPoints.add(radioGroup);
		vPoints.add(new Label());
		vPoints.add(btnExpand);

		panel.add(fpCodDesc);
		panel.add(table);
		panel.add(vPoints);

		return panel;
	}

	private Window createMapWindow() {

		final Window window = new Window();
		window.setSize("80%", "600px");
		window.setPlain(true);
		window.setModal(true);
		window.setBlinkModal(true);
		window.setHeading("Hello Window");
		window.setLayout(new FitLayout());
		// window.addWindowListener(new WindowListener() {
		// @Override
		// public void windowHide(WindowEvent we) {
		// Button open = we.getWindow().getData("open");
		// open.focus();
		// }
		// });
		window.addButton(new Button("Close",
				new SelectionListener<ButtonEvent>() {
					@Override
					public void componentSelected(ButtonEvent ce) {
						window.hide();
					}
				}));
		window.add(createMap());

		return window;
	}

	private LayoutContainer createMap() {

		points = new Marker[MAX_POINTS+1];

		for (int j = 0; j <= MAX_POINTS; j++) {
			points[j] = null;
		}

		LayoutContainer lcMap = new LayoutContainer();
		lcMap.setHeight(MAX_DOWNPANEL_HEIGHT);

		map.setSize("100%", "600px");
		map.setGoogleBarEnabled(true);

		MapUIOptions opts = map.getDefaultUI();
		opts.setDoubleClick(false);
		map.setUI(opts);

		// Create our "tiny" marker icon
		Icon icon = Icon
				.newInstance("http://labs.google.com/ridefinder/images/mm_20_red.png");
		icon.setShadowURL("http://labs.google.com/ridefinder/images/mm_20_shadow.png");
		icon.setIconSize(Size.newInstance(12, 20));
		icon.setShadowSize(Size.newInstance(22, 20));
		icon.setIconAnchor(Point.newInstance(6, 20));
		icon.setInfoWindowAnchor(Point.newInstance(5, 1));

		final MarkerOptions options = MarkerOptions.newInstance();
		options.setIcon(icon);
		options.setAutoPan(true);
		options.setDraggable(true);

		map.addMapClickHandler(new MapClickHandler() {
			public void onClick(MapClickEvent e) {
				MapWidget sender = e.getSender();
				Overlay overlay = e.getOverlay();
				final LatLng point = e.getLatLng();
				
				if (pointRadio.getValue()){
					MAX_POINTS=0;
				}else if(routeRadio.getValue()){
					MAX_POINTS=3;
					createRoute();
				}else{
					MAX_POINTS=3;
					createPolygon();
				}
				
				if (overlay != null && overlay instanceof Marker) {
					sender.removeOverlay(overlay);
					clearFields();
					counter--;
				} else if (counter <= MAX_POINTS) {

					final Marker marker = new Marker(point, options);

					marker.addMarkerDragEndHandler(new MarkerDragEndHandler() {
						public void onDragEnd(MarkerDragEndEvent event) {
							updateFields(marker);
						}
					});

					sender.addOverlay(marker);
					points[counter] = marker;
					updateFields(marker);
					System.out.println("Punto " + counter + ": "
							+ point.getLatitude() + "," + point.getLongitude());
					counter++;
				}
			}
		});

		lcMap.add(map);

		return lcMap;
	}

	private void updateFields(Marker marker) {
		longitude.setValue(marker.getLatLng().getLongitude());
		latitude.setValue(marker.getLatLng().getLatitude());
	}

	private void clearFields() {
		longitude.clear();
		latitude.clear();
	}

	private void createRoute() {
		PolyStyleOptions style = PolyStyleOptions.newInstance(color, weight,
				opacity);

		final Polyline poly = new Polyline(new LatLng[0]);
		lastPolyline = poly;
		map.addOverlay(poly);
		poly.setDrawingEnabled();
		poly.setStrokeStyle(style);

		poly.addPolylineLineUpdatedHandler(new PolylineLineUpdatedHandler() {

			public void onUpdate(PolylineLineUpdatedEvent event) {
				System.out.println("Polyline Updated");
			}
		});

		poly.addPolylineCancelLineHandler(new PolylineCancelLineHandler() {

			public void onCancel(PolylineCancelLineEvent event) {
				System.out.println("Line Cancelled");
			}
		});

		poly.addPolylineEndLineHandler(new PolylineEndLineHandler() {

			public void onEnd(PolylineEndLineEvent event) {
				System.out.println("Line End at " + event.getLatLng()
						+ ".  Bounds=" + poly.getBounds().getNorthEast() + ","
						+ poly.getBounds().getSouthWest() + " length="
						+ poly.getLength() + "m");
			}
		});
	}

	private void createPolygon() {

		PolyStyleOptions style = PolyStyleOptions.newInstance(color, weight,
				opacity);

		final Polygon poly = new Polygon(coordinates);
		lastPolygon = poly;
		map.addOverlay(poly);
		// poly.setDrawingEnabled();
		poly.setStrokeStyle(style);

		poly.addPolygonLineUpdatedHandler(new PolygonLineUpdatedHandler() {

			public void onUpdate(PolygonLineUpdatedEvent event) {
				System.out.println("Polygon Updated");
			}
		});

		poly.addPolygonCancelLineHandler(new PolygonCancelLineHandler() {

			public void onCancel(PolygonCancelLineEvent event) {
				System.out.println("Polygon Cancelled");
			}
		});

		poly.addPolygonEndLineHandler(new PolygonEndLineHandler() {

			public void onEnd(PolygonEndLineEvent event) {
				System.out.println("Polygon End at " + event.getLatLng()
						+ ".  Bounds=" + poly.getBounds().getNorthEast() + ","
						+ poly.getBounds().getSouthWest() + " area="
						+ poly.getArea() + "m");
			}
		});
	}

}