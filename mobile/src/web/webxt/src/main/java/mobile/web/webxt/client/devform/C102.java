package mobile.web.webxt.client.devform;

import java.util.HashMap;
import java.util.Map;

import mobile.web.webxt.client.data.MyHttpProxy;
import mobile.web.webxt.client.data.MyProcessConfig;
import mobile.web.webxt.client.form.validations.Validate;
import mobile.web.webxt.client.form.widgets.InputBox;
import mobile.web.webxt.client.form.widgets.MyTextArea;
import mobile.web.webxt.client.mvc.AppEvents;
import mobile.web.webxt.client.windows.AlertDialog;

import com.extjs.gxt.ui.client.Style.HorizontalAlignment;
import com.extjs.gxt.ui.client.Style.LayoutRegion;
import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.Events;
import com.extjs.gxt.ui.client.event.FieldEvent;
import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.mvc.Dispatcher;
import com.extjs.gxt.ui.client.util.Margins;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.HorizontalPanel;
import com.extjs.gxt.ui.client.widget.Html;
import com.extjs.gxt.ui.client.widget.Label;
import com.extjs.gxt.ui.client.widget.LayoutContainer;
import com.extjs.gxt.ui.client.widget.VerticalPanel;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.form.FormPanel;
import com.extjs.gxt.ui.client.widget.form.FormPanel.LabelAlign;
import com.extjs.gxt.ui.client.widget.form.NumberField;
import com.extjs.gxt.ui.client.widget.form.Radio;
import com.extjs.gxt.ui.client.widget.form.RadioGroup;
import com.extjs.gxt.ui.client.widget.layout.BorderLayout;
import com.extjs.gxt.ui.client.widget.layout.BorderLayoutData;
import com.extjs.gxt.ui.client.widget.layout.FormLayout;
import com.google.gwt.maps.client.MapUIOptions;
import com.google.gwt.maps.client.MapWidget;
import com.google.gwt.maps.client.event.MapClickHandler;
import com.google.gwt.maps.client.event.MarkerDragEndHandler;
import com.google.gwt.maps.client.event.PolygonLineUpdatedHandler;
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
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.FlexTable;

public class C102 extends LayoutContainer {

	private final String process = "C102";

	private MyProcessConfig config;

	private Map<String, String> mfield;

	private MyHttpProxy proxy = new MyHttpProxy();

	// Fields for coordinates:
	NumberField longitude = new NumberField();
	NumberField latitude = new NumberField();
	InputBox isNew = new InputBox("", "GeographicZone:_isNew:1", 50,2,Validate.ALFANUMERICO);

	private int MAX_POINTS = 3; // 4 points
	private int MAX_DOWNPANEL_HEIGHT = 420; // pixels
	private int MAX_UPPANEL_HEIGHT = 100; // pixels
	private int MAX_LEFTPANEL_HEIGHT = 250; // pixels
	private double INITIAL_LATITUDE = -2.8989009; // Cuenca-Ecuador
	private double INITIAL_LONGITUDE = -78.997668; // Cuenca-Ecuador
	private int ZOOM_LEVEL_NORMAL = 14;
	private String color = "#FF0000"; // polyline
	private double opacity = 1.0; // polyline
	private int weight = 1; // polyline

	Radio pointRadio = new Radio();
	Radio routeRadio = new Radio();
	Radio polygonRadio = new Radio();

	Button save;
	Button cancel;

	FormPanel fpCodDesc; 
	InputBox code; 
	MyTextArea description;

	private MapWidget map = new MapWidget(LatLng.newInstance(INITIAL_LATITUDE,
			INITIAL_LONGITUDE), ZOOM_LEVEL_NORMAL);

	Marker[] points;
	private static LatLng[] coordinatesPolygon = new LatLng[5];
	private static LatLng[] coordinatesRoute = new LatLng[4];

	private Polyline polyline;
	private Polygon polygon;

	int counter = 0;

	@Override
	protected void onRender(Element parent, int index) {
		super.onRender(parent, index);
		setWidth(800);
		final BorderLayout layout = new BorderLayout();
		config = new MyProcessConfig(process);
		isNew.setValue("1");
		isNew.hide();
		setLayout(layout);
		createPanel();
	}

	private void createPanel() {

		ContentPanel cpMap = new ContentPanel();
		cpMap.setHeight(MAX_DOWNPANEL_HEIGHT);
		cpMap.setHeaderVisible(false);
		final AlertDialog alertZone = new AlertDialog("Error","Debe ingresar codigo de la zona");

		save = new Button("Guardar", new SelectionListener<ButtonEvent>() {
			@Override
			public void componentSelected(ButtonEvent ce) {
				if(code.getValue()==null){
					alertZone.show();
				}else{
					commitForm();
				}
			}
		});

		cancel = new Button("Cancelar", new SelectionListener<ButtonEvent>() {
			@Override
			public void componentSelected(ButtonEvent ce) {
				resetForm();
			}
		});
		
		
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
		formData.setSplit(false);
		formData.setCollapsible(true);
		formData.setMargins(new Margins(0, 5, 0, 0));

		cpMap.add(createMap());
		cpForm.add(createForm());

		add(cpMap, mapData);
		add(cpForm, formData);

	}

	private FormPanel createForm() {

		fpCodDesc = new FormPanel();
		code = new InputBox("", "GeographicZone:pk_geographicZoneId:1", 90,6,Validate.ALFANUMERICO);
		description = new MyTextArea("", "GeographicZone:description:1",90,100);
		
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
		table.setHTML(0, 1,
				"<div style='font-size: 12px; width: 35px'>Coord:</span>");

		// Field positions
		table.setWidget(0, 2, latitude);
		table.setWidget(0, 3, longitude);

		HorizontalPanel hPanel = new HorizontalPanel();
		hPanel.add(new Html(
				"<div style='font-size: 12px; width: 180px'><center><B>DATOS DE LA ZONA<B></center></span>"));


		fpCodDesc.setFrame(false);
		fpCodDesc.setHeaderVisible(false);
		fpCodDesc.setBorders(false);

		FormLayout layoutForm = new FormLayout();
		layoutForm.setLabelAlign(LabelAlign.TOP);
		fpCodDesc.setLayout(layoutForm);
		fpCodDesc.add(hPanel);
		fpCodDesc.add(new Label());
		code.setFieldLabel("Codigo");
		code.setMinLength(1);
		code.setAllowBlank(false);
		fpCodDesc.add(code);
		fpCodDesc.add(new Label());
		description.setFieldLabel("Descripcion");
		description.setEmptyText("Ingrese la descripcion de la zona");
		fpCodDesc.add(description);

		pointRadio.setBoxLabel("Punto");
		routeRadio.setBoxLabel("Ruta");
		polygonRadio.setBoxLabel("Poligono");
		pointRadio.setValue(true);
		final RadioGroup radioGroup = new RadioGroup();
		radioGroup.add(pointRadio);
		radioGroup.add(routeRadio);
		radioGroup.add(polygonRadio);

		
		vPoints.add(new Label());
		vPoints.add(new Label("Tipo de Coordenadas:"));

		radioGroup.addListener(Events.Change, new Listener<FieldEvent>() {
			public void handleEvent(FieldEvent fe) {
				map.clearOverlays();
				counter = 0;
				initializePoints();
				clearFields();
			}
		});

		vPoints.add(radioGroup);
		vPoints.add(new Label());
		isNew.setMinLength(1);
		vPoints.add(isNew);

		panel.add(fpCodDesc);
		panel.add(table);
		panel.add(vPoints);

		return panel;
	}

	private void initializePoints(){
		points = new Marker[MAX_POINTS + 1];

		for (int j = 0; j <= MAX_POINTS; j++) {
			points[j] = null;
		}
	}
	
	private int countNullPoints(){
		int count=0;
		
		for (int j = 0; j <= MAX_POINTS; j++) {
			if(points[j] == null){
				count++;
			}
		}
		
		return count;
	}
	
	private LayoutContainer createMap() {

		initializePoints();

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

				if (pointRadio.getValue()) {
					MAX_POINTS = 0;
				} else if (routeRadio.getValue()) {
					MAX_POINTS = 3;
				} else {
					MAX_POINTS = 3;
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
							if (routeRadio.getValue()) {
								updateCoordinatesRoute();
								map.removeOverlay(polyline);
								polyline = new Polyline(coordinatesRoute);
								createRoute();
							} else if (polygonRadio.getValue()) {
								updateCoordinatesPolygon();
								map.removeOverlay(polygon);
								polygon = new Polygon(coordinatesPolygon);	
								createPolygon();
							}
						}
					});

					sender.addOverlay(marker);
					points[counter] = marker;
					updateFields(marker);
					System.out.println("Punto " + counter + ": "
							+ point.getLatitude() + "," + point.getLongitude());

					if (routeRadio.getValue() && counter == 3) {
						updateCoordinatesRoute();
						polyline = new Polyline(coordinatesRoute);
						createRoute();
					} else if (polygonRadio.getValue() && counter == 3) {
						updateCoordinatesPolygon();
						polygon = new Polygon(coordinatesPolygon);						
						createPolygon();
					}

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
	
	private void resetForm(){
		map.clearOverlays();
		initializePoints();
		clearFields();
		code.clear();
		description.clear();
		pointRadio.setValue(true);
		polyline=null;
		polygon=null;
	}

	private void updateCoordinatesPolygon() {
		for (int i = 0; i < coordinatesPolygon.length; i++) {
			if (i == 4) {
				coordinatesPolygon[i] = coordinatesPolygon[0];
			} else {
				coordinatesPolygon[i] = LatLng.newInstance(points[i]
						.getLatLng().getLatitude(), points[i].getLatLng()
						.getLongitude());
			}
		}
	}

	private void updateCoordinatesRoute() {
		for (int i = 0; i < coordinatesRoute.length; i++) {
			coordinatesRoute[i] = LatLng.newInstance(points[i].getLatLng()
					.getLatitude(), points[i].getLatLng().getLongitude());
		}
	}

	private void createRoute() {

		PolyStyleOptions style = PolyStyleOptions.newInstance(color, weight,opacity);
		map.addOverlay(polyline);
		polyline.setStrokeStyle(style);

		polyline.addPolylineLineUpdatedHandler(new PolylineLineUpdatedHandler() {

			public void onUpdate(PolylineLineUpdatedEvent event) {
				System.out.println("Polyline Updated");
			}
		});

		System.out.println("Bounds=" + polyline.getBounds().getNorthEast()
				+ "," + polyline.getBounds().getSouthWest() + " length="
				+ polyline.getLength() + "m");
	}

	private void createPolygon() {

		PolyStyleOptions style = PolyStyleOptions.newInstance(color, weight,opacity);
		map.addOverlay(polygon);
		polygon.setStrokeStyle(style);

		polygon.addPolygonLineUpdatedHandler(new PolygonLineUpdatedHandler() {

			public void onUpdate(PolygonLineUpdatedEvent event) {
				System.out.println("Polygon Updated");
			}
		});

		System.out.println("Bounds=" + polygon.getBounds().getNorthEast() + ","
				+ polygon.getBounds().getSouthWest() + " area="
				+ polygon.getArea() + "m");

	}

	private void commitForm() {

		AlertDialog alertPoint = new AlertDialog("Error","Debe seleccionar al menos un punto en el mapa");
		AlertDialog alertPoints = new AlertDialog("Error","Debe seleccionar al menos cuatro puntos en el mapa");
		int numPoints=countNullPoints();
		boolean isOK=false;
		boolean fourPoints=false;
		String coordType = "";
		mfield = new HashMap<String, String>();
		
		if (numPoints==4){
			alertPoint.show();
			return;
		}else{
			isOK=true;
		}
		
		
		System.out.println("Numero de puntos con null: "+numPoints);
		
		if (routeRadio.getValue() && numPoints>0){
			alertPoints.show();
			return;
		}else{
			isOK=true;
		}
		
		if (polygonRadio.getValue() && numPoints>0){
			alertPoints.show();
			return;
		}else{
			isOK=true;
		}
	
		mfield.put(isNew.getFieldInfo(), isNew.getValue().toString());
		mfield.put("GeographicZone:pk_geographicZoneId:1", code.getValue().toString());
		mfield.put("GeographicZone:description:1", description.getValue());
		
		if (pointRadio.getValue() && isOK) {
			coordType = "PUNTO";
			mfield.put("GeographicZone:coordinateType:1", coordType);
			mfield.put("GeographicZone:p11:1",String.valueOf(points[0].getLatLng().getLatitude()));
			mfield.put("GeographicZone:p12:1",String.valueOf(points[0].getLatLng().getLongitude()));
		} else if (routeRadio.getValue()  && isOK) {
			coordType = "RUTA";
			fourPoints=true;
		} else if (polygonRadio.getValue()  && isOK){
			coordType = "POLIGONO";
			fourPoints = true;
		}
		
		if (fourPoints){
			mfield.put("GeographicZone:coordinateType:1", coordType);
			mfield.put("GeographicZone:p11:1",
					String.valueOf(points[0].getLatLng().getLatitude()));
			mfield.put("GeographicZone:p12:1",
					String.valueOf(points[0].getLatLng().getLongitude()));
			mfield.put("GeographicZone:p21:1",
					String.valueOf(points[1].getLatLng().getLatitude()));
			mfield.put("GeographicZone:p22:1",
					String.valueOf(points[1].getLatLng().getLongitude()));
			mfield.put("GeographicZone:p31:1",
					String.valueOf(points[2].getLatLng().getLatitude()));
			mfield.put("GeographicZone:p32:1",
					String.valueOf(points[2].getLatLng().getLongitude()));
			mfield.put("GeographicZone:p41:1",
					String.valueOf(points[3].getLatLng().getLatitude()));
			mfield.put("GeographicZone:p42:1",
					String.valueOf(points[3].getLatLng().getLongitude()));
		}
		
		System.out.println("MyForm.commitChanges");
		for (String key : mfield.keySet()) {
			System.out.println(key + ":" + mfield.get(key));
		}

		AsyncCallback<Boolean> callback = new AsyncCallback<Boolean>() {
			public void onSuccess(Boolean result) {
				Dispatcher.forwardEvent(AppEvents.UserNotification,
						"Mantenimiento exitoso");
			}

			public void onFailure(Throwable caught) {
				new AlertDialog("MyFormPanel", caught.getMessage()).show();
			}
		};

		if (isOK){
			proxy.commitForm(config, mfield, callback);
			resetForm();
			map.setCenter(LatLng.newInstance(INITIAL_LATITUDE,INITIAL_LONGITUDE), ZOOM_LEVEL_NORMAL);
		}

	}
}