package mobile.web.webxt.client.devform;

import java.util.ArrayList;
import java.util.List;

import mobile.web.webxt.client.data.form.DataSource;
import mobile.web.webxt.client.data.form.DataSourceType;
import mobile.web.webxt.client.data.form.Reference;
import mobile.web.webxt.client.form.MyFormPanel;
import mobile.web.webxt.client.form.MyGeneralForm;
import mobile.web.webxt.client.form.validations.Validate;
import mobile.web.webxt.client.form.widgets.InputBox;
import mobile.web.webxt.client.form.widgets.MyLabel;
import mobile.web.webxt.client.form.widgets.MyNumberField;
import mobile.web.webxt.client.form.widgets.MyTextArea;
import mobile.web.webxt.client.form.widgets.RowContainer;
import mobile.web.webxt.client.resources.Resources;

import com.extjs.gxt.ui.client.Style.IconAlign;
import com.extjs.gxt.ui.client.Style.LayoutRegion;
import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.Events;
import com.extjs.gxt.ui.client.event.FieldEvent;
import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.util.Margins;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.Dialog;
import com.extjs.gxt.ui.client.widget.Html;
import com.extjs.gxt.ui.client.widget.LayoutContainer;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.form.FieldSet;
import com.extjs.gxt.ui.client.widget.layout.BorderLayout;
import com.extjs.gxt.ui.client.widget.layout.BorderLayoutData;
import com.google.gwt.maps.client.MapUIOptions;
import com.google.gwt.maps.client.MapWidget;
import com.google.gwt.maps.client.event.PolygonLineUpdatedHandler;
import com.google.gwt.maps.client.event.PolylineLineUpdatedHandler;
import com.google.gwt.maps.client.geom.LatLng;
import com.google.gwt.maps.client.geom.Point;
import com.google.gwt.maps.client.geom.Size;
import com.google.gwt.maps.client.overlay.Icon;
import com.google.gwt.maps.client.overlay.Marker;
import com.google.gwt.maps.client.overlay.MarkerOptions;
import com.google.gwt.maps.client.overlay.PolyStyleOptions;
import com.google.gwt.maps.client.overlay.Polygon;
import com.google.gwt.maps.client.overlay.Polyline;
import com.google.gwt.user.client.Element;

public class ZonePreview extends Dialog {

	private final static String PROCESS_ZONE = "C102";
	private final static String ENTITY_ZONE = "GeographicZone";

	private double INITIAL_LATITUDE = -2.8989009; // Cuenca-Ecuador
	private double INITIAL_LONGITUDE = -78.997668; // Cuenca-Ecuador
	private int ZOOM_LEVEL_NORMAL = 14;
	private int ZOOM_LEVEL_CENTERED = 15;
	private String color = "#FF0000"; // polyline
	private double opacity = 1.0; // polyline
	private int weight = 1; // polyline

	private MapWidget map = new MapWidget(LatLng.newInstance(INITIAL_LATITUDE, INITIAL_LONGITUDE), ZOOM_LEVEL_NORMAL);

	MyFormPanel formZone;
	MyGeneralForm formContainerZone;
	RowContainer row;
	MyLabel label, labelPerson, labelAsessor;
	String personName = "";

	InputBox coornidateType, zoneCode;
	MyTextArea description;
	List<String> zonesList = new ArrayList<String>();
	String cAsessor;
	Boolean isMultiPage = false;

	Button prev, next;
	int regNumber = 1;

	private Polyline polyline;
	private Polygon polygon;
	private static LatLng[] coordinatesPolygon = new LatLng[5];
	private static LatLng[] coordinatesRoute = new LatLng[4];

	// Fields for coordinates:
	MyNumberField longitude = new MyNumberField();
	MyNumberField latitude = new MyNumberField();
	MyNumberField longitude2 = new MyNumberField();
	MyNumberField latitude2 = new MyNumberField();
	MyNumberField longitude3 = new MyNumberField();
	MyNumberField latitude3 = new MyNumberField();
	MyNumberField longitude4 = new MyNumberField();
	MyNumberField latitude4 = new MyNumberField();

	final int LABEL_WIDTH = 40;
	final String FORM_WIDTH = "100%";
	ContentPanel principalPanel = new ContentPanel();

	public ZonePreview(String cAsessor,List<String> cZones) {

		this.zonesList = cZones; // List of zones for the current Asessor
		this.cAsessor = cAsessor; // Asessor code

		if (zonesList.size() > 1) {
			isMultiPage = true;
		}

	}

	@Override
	protected void onRender(Element parent, int pos) {
		super.onRender(parent, pos);

		formContainerZone = new MyGeneralForm(PROCESS_ZONE);
		formContainerZone.setReference(new Reference("geo", ENTITY_ZONE));
		formContainerZone.setWidth(300);
		formZone = new MyFormPanel(formContainerZone, "Datos de Zona Geografica", FORM_WIDTH);
		formZone.setHeaderVisible(false);
		formZone.setFrame(false);
		formZone.setBorders(false);
		formZone.setWidth(300);

		setBodyBorder(false);
		setHeading("Zona Geografica Asignada");
		setWidth("80%");
		setHeight(500);
		setHideOnButtonClick(true);
		setResizable(false);
		setBlinkModal(true);

		BorderLayout layout = new BorderLayout();
		setLayout(layout);

		// center
		ContentPanel panel = new ContentPanel();
		panel = new ContentPanel();
		panel.setHeaderVisible(true);
		panel.setBorders(false);
		panel.setBodyBorder(false);
		formZone.add(createZoneForm());
		formZone.setBodyBorder(false);
		formZone.setBorders(false);
		panel.add(formZone);
		
		BorderLayoutData data = new BorderLayoutData(LayoutRegion.CENTER, 300);
		add(panel, data);

		// west
		panel = new ContentPanel();
		panel.setHeaderVisible(false);
		panel.setBorders(false);
		panel.add(createMap());
		data = new BorderLayoutData(LayoutRegion.WEST, 800);
		data.setMargins(new Margins(0, 5, 0, 0));
		add(panel, data);

		prev = new Button("Anterior", new SelectionListener<ButtonEvent>() {
			@Override
			public void componentSelected(ButtonEvent ce) {
				
				regNumber--;
				zoneCode.setValue(zonesList.get(regNumber-1));
			}
		});

		next = new Button("Siguiente", new SelectionListener<ButtonEvent>() {
			@Override
			public void componentSelected(ButtonEvent ce) {
				
				if(regNumber==zonesList.size()){
					return;
				}
				
				zoneCode.setValue(zonesList.get(regNumber));
				regNumber++;
			}
		});
		
		
		prev.setIconAlign(IconAlign.LEFT);
		prev.setIcon(Resources.ICONS.previous());
		prev.setWidth(70);
		next.setIconAlign(IconAlign.RIGHT);
		next.setIcon(Resources.ICONS.next());
		next.setWidth(70);

		addButton(prev);
		addButton(next);

		if (!isMultiPage) {
			prev.disable();
			next.disable();
		}

		if (regNumber == 1) {
			zoneCode.setValue(zonesList.get(0));
			labelAsessor.setText(cAsessor);
			prev.disable();
			next.disable();
		}

	}

	private FieldSet createZoneForm() {

		FieldSet fieldSet = new FieldSet();
		fieldSet.setHeading("Datos de la Zona");
		fieldSet.setCollapsible(false);
		fieldSet.setWidth(250);

		// Asessor Name

		row = new RowContainer();
		Html asesorHtml = new Html("<b>Asesor:</b>");
		labelAsessor = new MyLabel("", LABEL_WIDTH);
		row.add(asesorHtml);
		row.add(labelAsessor);
		fieldSet.add(row);

		// Code
		row = new RowContainer();
		label = new MyLabel("Codigo:", LABEL_WIDTH);
		row.add(label);

		zoneCode = new InputBox(90, 20, Validate.TEXT);
		zoneCode.setDataSource(new DataSource("geo", "pk_geographicZoneId", DataSourceType.CRITERION));
		zoneCode.setData("mobile-type", Integer.class);
		zoneCode.setReadOnly(true);
		zoneCode.setFireChangeEventOnSetValue(true);

		zoneCode.addListener(Events.Change, new Listener<FieldEvent>() {
			public void handleEvent(FieldEvent e) {
				if (e.getValue() != null) {
					formZone.queryForm();
				}
			}
		});

		row.add(zoneCode);
		fieldSet.add(row);

		// Coordinate type
		row = new RowContainer();
		label = new MyLabel("Tipo:", LABEL_WIDTH);
		row.add(label);

		coornidateType = new InputBox(90, 20, Validate.TEXT);
		coornidateType.setReadOnly(true);
		coornidateType.setFireChangeEventOnSetValue(true);
		coornidateType.setDataSource(new DataSource("geo", "coordinateType", DataSourceType.RECORD));
		
		coornidateType.addListener(Events.Change, new Listener<FieldEvent>() {
			public void handleEvent(FieldEvent e) {
				if (e.getValue() != null) {
					if (coornidateType.getValue().toString().compareTo("RUTA")==0 || coornidateType.getValue().toString().compareTo("ZONA")==0) {
						visualizeFields(true);
					} else{
						visualizeFields(false);
					}
				}
			}
		});

		row.add(coornidateType);
		fieldSet.add(row);

		// Description
		row = new RowContainer();
		label = new MyLabel("Descripción:", LABEL_WIDTH);
		row.add(label);
		fieldSet.add(row);

		row = new RowContainer();
		row.setHeight(70);
		description = new MyTextArea(210, 300);
		description.setDataSource(new DataSource("geo", "description", DataSourceType.RECORD));
		description.setAllowBlank(false);
		description.setReadOnly(true);
		description.setEmptyText("Ingrese la descripcion de la zona");

		row.add(description);
		fieldSet.add(row);

		// Points preview values:

		row = new RowContainer();
		label = new MyLabel("Coordenadas:", LABEL_WIDTH);
		row.add(label);
		fieldSet.add(row);

		row = new RowContainer();
		latitude = new MyNumberField(100);
		latitude.setDataSource(new DataSource("geo", "p11", DataSourceType.RECORD));
		latitude.setEditable(false);

		longitude = new MyNumberField(100);
		longitude.setDataSource(new DataSource("geo", "p12", DataSourceType.RECORD));
		longitude.setEditable(false);
		longitude.setFireChangeEventOnSetValue(true);

		longitude.addListener(Events.Change, new Listener<FieldEvent>() {
			public void handleEvent(FieldEvent e) {
				if (e.getValue() != null && coornidateType.getValue().compareTo("PUNTO") == 0) {
					createPoints(coornidateType.getValue());
					labelAsessor.setText(cAsessor);
					
					if (zonesList.size()==1){
						return;
					}
					
					if(zoneCode.getValue().compareTo(zonesList.get(0))==0){
						prev.disable();
						next.enable();
					}else{
						prev.enable();
					}
					
					int last = zonesList.size()-1;
					if(zoneCode.getValue().compareTo(zonesList.get(last))==0){
						next.disable();
						prev.enable();
					}else{
						next.enable();
					}

				}
			}
		});
		
		row.add(latitude);
		row.add(longitude);
		fieldSet.add(row);

		row = new RowContainer();
		latitude2 = new MyNumberField(100);
		latitude2.setDataSource(new DataSource("geo", "p21", DataSourceType.RECORD));
		latitude2.setEditable(false);

		longitude2 = new MyNumberField(100);
		longitude2.setDataSource(new DataSource("geo", "p22", DataSourceType.RECORD));
		longitude2.setEditable(false);

		row.add(latitude2);
		row.add(longitude2);
		fieldSet.add(row);

		row = new RowContainer();
		latitude3 = new MyNumberField(100);
		latitude3.setDataSource(new DataSource("geo", "p31", DataSourceType.RECORD));
		latitude3.setEditable(false);

		longitude3 = new MyNumberField(100);
		longitude3.setDataSource(new DataSource("geo", "p32", DataSourceType.RECORD));
		longitude3.setEditable(false);

		row.add(latitude3);
		row.add(longitude3);
		fieldSet.add(row);

		row = new RowContainer();
		latitude4 = new MyNumberField(100);
		latitude4.setDataSource(new DataSource("geo", "p41", DataSourceType.RECORD));
		latitude4.setEditable(false);

		longitude4 = new MyNumberField(100);
		longitude4.setDataSource(new DataSource("geo", "p42", DataSourceType.RECORD));
		longitude4.setEditable(false);
		longitude4.setFireChangeEventOnSetValue(true);

		longitude4.addListener(Events.Change, new Listener<FieldEvent>() {
			public void handleEvent(FieldEvent e) {
				if (e.getValue() != null && coornidateType.getValue().compareTo("RUTA") == 0
						|| coornidateType.getValue().compareTo("ZONA") == 0) {
					createPoints(coornidateType.getValue());
					createCoordinatePoints();
					if (coornidateType.getValue().compareTo("RUTA") == 0) {
						createRoute();
					} else if (coornidateType.getValue().compareTo("ZONA") == 0) {
						createPolygon();
					}
				}
				
				if (zonesList.size()==1){
					return;
				}
				
				if(zoneCode.getValue().compareTo(zonesList.get(0))==0){
					prev.disable();
					next.enable();
				}else{
					prev.enable();
				}
				
				int last = zonesList.size()-1;
				if(zoneCode.getValue().compareTo(zonesList.get(last))==0){
					next.disable();
					prev.enable();
				}else{
					next.enable();
				}
			}
		});

		row.add(latitude4);
		row.add(longitude4);
		fieldSet.add(row);

		return fieldSet;
	}

	private LayoutContainer createMap() {

		LayoutContainer lcMap = new LayoutContainer();
		lcMap.setWidth("800");

		map.setSize("100%", "500px");
		map.setGoogleBarEnabled(true);

		MapUIOptions opts = map.getDefaultUI();
		opts.setDoubleClick(false);

		map.setUI(opts);

		// Create our "tiny" marker icon
		Icon icon = Icon.newInstance("http://labs.google.com/ridefinder/images/mm_20_red.png");
		icon.setShadowURL("http://labs.google.com/ridefinder/images/mm_20_shadow.png");
		icon.setIconSize(Size.newInstance(12, 20));
		icon.setShadowSize(Size.newInstance(22, 20));
		icon.setIconAnchor(Point.newInstance(6, 20));
		icon.setInfoWindowAnchor(Point.newInstance(5, 1));

		final MarkerOptions options = MarkerOptions.newInstance();
		options.setIcon(icon);
		options.setAutoPan(true);
		options.setDraggable(false);
		options.setClickable(false);

		lcMap.add(map);

		return lcMap;
	}

	private void createPoints(String coordType) {

		map.clearOverlays();

		// Create our "tiny" marker icon
		Icon icon = Icon.newInstance("http://labs.google.com/ridefinder/images/mm_20_red.png");
		icon.setShadowURL("http://labs.google.com/ridefinder/images/mm_20_shadow.png");
		icon.setIconSize(Size.newInstance(12, 20));
		icon.setShadowSize(Size.newInstance(22, 20));
		icon.setIconAnchor(Point.newInstance(6, 20));
		icon.setInfoWindowAnchor(Point.newInstance(5, 1));

		final MarkerOptions options = MarkerOptions.newInstance();
		options.setIcon(icon);
		options.setAutoPan(true);
		options.setDraggable(false);
		options.setClickable(false);

		if (coordType.compareTo("PUNTO") == 0) {

			LatLng point = LatLng.newInstance((Double) latitude.getValue(), (Double) longitude.getValue());
			Marker marker = new Marker(point, options);
			map.addOverlay(marker);
			map.setCenter(LatLng.newInstance((Double) latitude.getValue(), (Double) longitude.getValue()),
					ZOOM_LEVEL_CENTERED);
			
		} else if (coornidateType.getValue().compareTo("RUTA") == 0 || coornidateType.getValue().compareTo("ZONA") == 0) {

			LatLng point = LatLng.newInstance((Double) latitude.getValue(), (Double) longitude.getValue());
			Marker marker = new Marker(point, options);
			map.addOverlay(marker);

			point = LatLng.newInstance((Double) latitude2.getValue(), (Double) longitude2.getValue());
			marker = new Marker(point, options);
			map.addOverlay(marker);

			point = LatLng.newInstance((Double) latitude3.getValue(), (Double) longitude3.getValue());
			marker = new Marker(point, options);
			map.addOverlay(marker);
			map.setCenter(LatLng.newInstance((Double) latitude3.getValue(), (Double) longitude3.getValue()),
					ZOOM_LEVEL_CENTERED);

			point = LatLng.newInstance((Double) latitude4.getValue(), (Double) longitude4.getValue());
			marker = new Marker(point, options);
			map.addOverlay(marker);

		}

		map.removeMapRightClickHandler(null);

	}

	private void createCoordinatePoints() {

		coordinatesPolygon[0] = LatLng.newInstance((Double) latitude.getValue(), (Double) longitude.getValue());
		coordinatesPolygon[1] = LatLng.newInstance((Double) latitude2.getValue(), (Double) longitude2.getValue());
		coordinatesPolygon[2] = LatLng.newInstance((Double) latitude3.getValue(), (Double) longitude3.getValue());
		coordinatesPolygon[3] = LatLng.newInstance((Double) latitude4.getValue(), (Double) longitude4.getValue());
		coordinatesPolygon[4] = coordinatesPolygon[0];

		coordinatesRoute[0] = coordinatesPolygon[0];
		coordinatesRoute[1] = coordinatesPolygon[1];
		coordinatesRoute[2] = coordinatesPolygon[2];
		coordinatesRoute[3] = coordinatesPolygon[3];

	}

	private void createRoute() {

		polyline = new Polyline(coordinatesRoute);

		PolyStyleOptions style = PolyStyleOptions.newInstance(color, weight, opacity);
		map.addOverlay(polyline);
		polyline.setStrokeStyle(style);

		polyline.addPolylineLineUpdatedHandler(new PolylineLineUpdatedHandler() {

			public void onUpdate(PolylineLineUpdatedEvent event) {
				System.out.println("Polyline Updated");
			}
		});

		System.out.println("Bounds=" + polyline.getBounds().getNorthEast() + "," + polyline.getBounds().getSouthWest()
				+ " length=" + polyline.getLength() + "m");
	}

	private void createPolygon() {

		polygon = new Polygon(coordinatesPolygon);

		PolyStyleOptions style = PolyStyleOptions.newInstance(color, weight, opacity);
		map.addOverlay(polygon);
		polygon.setStrokeStyle(style);

		polygon.addPolygonLineUpdatedHandler(new PolygonLineUpdatedHandler() {

			public void onUpdate(PolygonLineUpdatedEvent event) {
				System.out.println("Polygon Updated");
			}
		});

		System.out.println("Bounds=" + polygon.getBounds().getNorthEast() + "," + polygon.getBounds().getSouthWest()
				+ " area=" + polygon.getArea() + "m");
		

	}

	public void visualizeFields(boolean state){

		longitude2.setVisible(state);
		latitude2.setVisible(state);
		longitude3.setVisible(state);
		latitude3.setVisible(state);
		longitude4.setVisible(state);
		latitude4.setVisible(state);
	}
}
