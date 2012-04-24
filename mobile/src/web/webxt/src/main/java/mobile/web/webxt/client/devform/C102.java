package mobile.web.webxt.client.devform;

import mobile.common.message.Item;
import mobile.web.webxt.client.data.form.DataSource;
import mobile.web.webxt.client.data.form.DataSourceType;
import mobile.web.webxt.client.data.form.Reference;
import mobile.web.webxt.client.form.MyFormPanel;
import mobile.web.webxt.client.form.MyGeneralForm;
import mobile.web.webxt.client.form.validations.Validate;
import mobile.web.webxt.client.form.widgets.ComboForm;
import mobile.web.webxt.client.form.widgets.InputBox;
import mobile.web.webxt.client.form.widgets.MyLabel;
import mobile.web.webxt.client.form.widgets.MyNumberField;
import mobile.web.webxt.client.form.widgets.MyTextArea;
import mobile.web.webxt.client.form.widgets.RowContainer;
import mobile.web.webxt.client.form.widgetsgrid.ArrayColumnData;
import mobile.web.webxt.client.form.widgetsgrid.MyColumnData;
import mobile.web.webxt.client.windows.MobileError;

import com.extjs.gxt.ui.client.Style.HorizontalAlignment;
import com.extjs.gxt.ui.client.Style.Orientation;
import com.extjs.gxt.ui.client.data.ModelData;
import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.Events;
import com.extjs.gxt.ui.client.event.FieldEvent;
import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.event.SelectionChangedEvent;
import com.extjs.gxt.ui.client.event.SelectionChangedListener;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.util.Margins;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.LayoutContainer;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.form.FieldSet;
import com.extjs.gxt.ui.client.widget.form.Radio;
import com.extjs.gxt.ui.client.widget.form.RadioGroup;
import com.extjs.gxt.ui.client.widget.layout.FitLayout;
import com.extjs.gxt.ui.client.widget.layout.RowData;
import com.extjs.gxt.ui.client.widget.layout.RowLayout;
import com.google.gwt.maps.client.MapUIOptions;
import com.google.gwt.maps.client.MapWidget;
import com.google.gwt.maps.client.event.MapClickHandler;
import com.google.gwt.maps.client.event.MarkerDragEndHandler;
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

public class C102 extends MyGeneralForm {

	private final static String PROCESS = "C102";
	private final static String ENTITY = "GeographicZone";

	// Constants
	final String FORM_WIDTH = "100%";
	final int LABEL_WIDTH = 60;
	
	MyFormPanel form;
	RowContainer row;
	MyLabel label;

	public C102() {
		super(PROCESS);
		setReference(new Reference("geo", ENTITY));
	}

	// Fields for coordinates:
	MyNumberField longitude = new MyNumberField();
	MyNumberField latitude = new MyNumberField();
	MyNumberField longitude2 = new MyNumberField();
	MyNumberField latitude2 = new MyNumberField();
	MyNumberField longitude3 = new MyNumberField();
	MyNumberField latitude3 = new MyNumberField();
	MyNumberField longitude4 = new MyNumberField();
	MyNumberField latitude4 = new MyNumberField();

	private int MAX_POINTS = 3; // 4 points
	private double INITIAL_LATITUDE = -2.8989009; // Cuenca-Ecuador
	private double INITIAL_LONGITUDE = -78.997668; // Cuenca-Ecuador
	private int ZOOM_LEVEL_NORMAL = 14;
	private int ZOOM_LEVEL_CENTERED = 15;
	private String color = "#FF0000"; // polyline
	private double opacity = 1.0; // polyline
	private int weight = 1; // polyline

	Radio pointRadio = new Radio();
	Radio routeRadio = new Radio();
	Radio polygonRadio = new Radio();

	Button save;
	Button cancel;

	InputBox coornidateType;
	ComboForm code;
	MyTextArea description;

	private MapWidget map = new MapWidget(LatLng.newInstance(INITIAL_LATITUDE, INITIAL_LONGITUDE), ZOOM_LEVEL_NORMAL);

	Marker[] points;
	private static LatLng[] coordinatesPolygon = new LatLng[5];
	private static LatLng[] coordinatesRoute = new LatLng[4];

	private Polyline polyline;
	private Polygon polygon;

	int counter = 0;
	boolean isMNT = true;

	@Override
	protected void onRender(Element parent, int index) {
		super.onRender(parent, index);
		setLayout(new FitLayout());
		counter = 0;
		createPanel();
	}

	private void createPanel() {

		// Form panel
		form = new MyFormPanel(this, "Mantenimiento de Zonas Geográficas", FORM_WIDTH);
		form.setLayout(new RowLayout(Orientation.HORIZONTAL));

		ContentPanel left = new ContentPanel();
		left.setHeaderVisible(false);
		left.setBorders(true);

		ContentPanel right = new ContentPanel();
		right.setHeaderVisible(false);
		right.setStyleAttribute("padding", "10");
		right.setBorders(true);
		right.setFrame(false);
		right.setLayout(new FitLayout());
		
		save = new Button("Guardar", new SelectionListener<ButtonEvent>() {
			@Override
			public void componentSelected(ButtonEvent ce) {
				preCommitForm();
			}
		});

		cancel = new Button("Cancelar", new SelectionListener<ButtonEvent>() {
			@Override
			public void componentSelected(ButtonEvent ce) {
				resetForm();
				pointRadio.setValue(true);
				map.setCenter(LatLng.newInstance(INITIAL_LATITUDE, INITIAL_LONGITUDE), ZOOM_LEVEL_NORMAL);
			}
		});

		right.addButton(save);
		right.addButton(cancel);
		right.setButtonAlign(HorizontalAlignment.CENTER);

		left.add(createMap());
		right.add(createForm());

		form.add(left, new RowData(.75, 1, new Margins(3)));
		form.add(right, new RowData(.25, 1, new Margins(3)));
		
		add(form);
	}

	private FieldSet createForm() {

		FieldSet fieldSet = new FieldSet();
		fieldSet.setHeading("Datos de la Zona");
		fieldSet.setCollapsible(false);
		fieldSet.setWidth(240);

		// Code
		row = new RowContainer();
		label = new MyLabel("Codigo:", LABEL_WIDTH);
		row.add(label);

		code = new ComboForm(70);
		code.setDataSource(new DataSource("geo", "pk_geographicZoneId", DataSourceType.CRITERION));
		code.setData("mobile-type", Integer.class);

		Reference refPartner = new Reference("geo1", "GeographicZone");
		final ArrayColumnData perCdata = new ArrayColumnData();
		perCdata.add(new MyColumnData("geo1", "pk_geographicZoneId", "Id", 100));
		code.setQueryData(refPartner, perCdata);
		code.setDisplayField("pk_geographicZoneId");

		code.addSelectionChangedListener(new SelectionChangedListener<ModelData>() {
			@Override
			public void selectionChanged(SelectionChangedEvent<ModelData> se) {
				if (((ComboForm) se.getSource()).isSomeSelected()) {
					isMNT = false;
					form.queryForm();

					if (coornidateType.getValue() != null && coornidateType.getValue().compareTo("PUNTO") == 0) {
						pointRadio.setValue(true);
					} else if (coornidateType.getValue() != null && coornidateType.getValue().compareTo("RUTA") == 0) {
						routeRadio.setValue(true);
					} else if (coornidateType.getValue() != null && coornidateType.getValue().compareTo("ZONA") == 0) {
						polygonRadio.setValue(true);
					}
				}
			}
		});

		row.add(code);
		fieldSet.add(row);

		// GeneratedId
		final InputBox generatedId = new InputBox();
		generatedId.setDataSource(new DataSource(Item.GENERATED_ID, DataSourceType.CONTROL));
		generatedId.setVisible(false);
		generatedId.setFireChangeEventOnSetValue(true);
		generatedId.addListener(Events.Change, new Listener<FieldEvent>() {
			public void handleEvent(FieldEvent e) {
				if (e.getValue() != null) {
					code.setRawValue((String) e.getValue());
					code.setLoaded(false);
				}
			}
		});
		row.add(generatedId);
		fieldSet.add(row);

		// Coordinate Types
		row = new RowContainer();
		row.setHeight(40);
		pointRadio.setBoxLabel("PUNTO");
		routeRadio.setBoxLabel("RUTA");
		polygonRadio.setBoxLabel("ZONA");
		pointRadio.setValue(true);
		final RadioGroup radioGroup = new RadioGroup();
		radioGroup.add(pointRadio);
		radioGroup.add(routeRadio);
		radioGroup.add(polygonRadio);
		
		radioGroup.addListener(Events.Change, new Listener<FieldEvent>() {
			public void handleEvent(FieldEvent fe) {
				
				if (routeRadio.getValue() || polygonRadio.getValue()) {
					visualizeFields(true);
				} else{
					visualizeFields(false);
				}
				
				clearFields();
				map.clearOverlays();
			}
		});

		row.add(radioGroup);
		fieldSet.add(row);

		// Description
		row = new RowContainer();
		label = new MyLabel("Descripción:", LABEL_WIDTH);
		row.add(label);
		fieldSet.add(row);

		row = new RowContainer();
		row.setHeight(70);
		description = new MyTextArea(190, 300);
		description.setDataSource(new DataSource("geo", "description", DataSourceType.RECORD));
		description.setAllowBlank(false);
		description.setEmptyText("Ingrese la descripcion de la zona");

		row.add(description);
		fieldSet.add(row);

		coornidateType = new InputBox(90, 20, Validate.TEXT);
		coornidateType.setVisible(false);
		coornidateType.setFireChangeEventOnSetValue(true);
		coornidateType.setDataSource(new DataSource("geo", "coordinateType", DataSourceType.RECORD));

		coornidateType.addListener(Events.Change, new Listener<FieldEvent>() {
			public void handleEvent(FieldEvent e) {
				if (e.getValue() != null) {
					if (coornidateType.getValue().compareTo("PUNTO") == 0) {
						pointRadio.setValue(true);
					} else if (coornidateType.getValue().compareTo("RUTA") == 0) {
						routeRadio.setValue(true);
					} else if (coornidateType.getValue().compareTo("ZONA") == 0) {
						polygonRadio.setValue(true);
					}
				}
			}
		});

		form.add(coornidateType);
		// Points preview values:

		row = new RowContainer();
		label = new MyLabel("Coordenadas:", LABEL_WIDTH);
		row.add(label);
		fieldSet.add(row);

		final Integer FIELD_WIDTH = 90;
		
		row = new RowContainer();
		latitude = new MyNumberField(FIELD_WIDTH);
		latitude.setDataSource(new DataSource("geo", "p11", DataSourceType.RECORD));
		latitude.setEditable(false);

		longitude = new MyNumberField(FIELD_WIDTH);
		longitude.setDataSource(new DataSource("geo", "p12", DataSourceType.RECORD));
		longitude.setEditable(false);
		longitude.setFireChangeEventOnSetValue(true);

		longitude.addListener(Events.Change, new Listener<FieldEvent>() {
			public void handleEvent(FieldEvent e) {
				if (!isMNT && e.getValue() != null) {

					if (e.getValue() != null && coornidateType.getValue().compareTo("PUNTO") == 0) {
						createPoints(coornidateType.getValue());
					}
				}
			}
		});

		row.add(latitude);
		row.add(longitude);
		fieldSet.add(row);

		row = new RowContainer();
		latitude2 = new MyNumberField(FIELD_WIDTH);
		latitude2.setDataSource(new DataSource("geo", "p21", DataSourceType.RECORD));
		latitude2.setEditable(false);

		longitude2 = new MyNumberField(FIELD_WIDTH);
		longitude2.setDataSource(new DataSource("geo", "p22", DataSourceType.RECORD));
		longitude2.setEditable(false);

		row.add(latitude2);
		row.add(longitude2);
		fieldSet.add(row);

		row = new RowContainer();
		latitude3 = new MyNumberField(FIELD_WIDTH);
		latitude3.setDataSource(new DataSource("geo", "p31", DataSourceType.RECORD));
		latitude3.setEditable(false);

		longitude3 = new MyNumberField(FIELD_WIDTH);
		longitude3.setDataSource(new DataSource("geo", "p32", DataSourceType.RECORD));
		longitude3.setEditable(false);

		row.add(latitude3);
		row.add(longitude3);
		fieldSet.add(row);

		row = new RowContainer();
		latitude4 = new MyNumberField(FIELD_WIDTH);
		latitude4.setDataSource(new DataSource("geo", "p41", DataSourceType.RECORD));
		latitude4.setEditable(false);

		longitude4 = new MyNumberField(FIELD_WIDTH);
		longitude4.setDataSource(new DataSource("geo", "p42", DataSourceType.RECORD));
		longitude4.setEditable(false);
		longitude4.setFireChangeEventOnSetValue(true);

		longitude4.addListener(Events.Change, new Listener<FieldEvent>() {
			public void handleEvent(FieldEvent e) {
				if (!isMNT && e.getValue() != null) {

					createPoints(coornidateType.getValue());
					createCoordinatePoints();
					if (e.getValue() != null && coornidateType.getValue().compareTo("RUTA") == 0) {
						createRoute();
					} else if ((e.getValue() != null && coornidateType.getValue().compareTo("ZONA") == 0)) {
						createPolygon();
					}
				}
			}
		});
		
		row.add(latitude4);
		row.add(longitude4);
		fieldSet.add(row);
		
		return fieldSet;
	}

	private void initializePoints() {
		points = new Marker[MAX_POINTS + 1];

		for (int j = 0; j <= MAX_POINTS; j++) {
			points[j] = null;
		}
	}

	private int countNullPoints() {
		int count = 0;

		for (int j = 0; j <= MAX_POINTS; j++) {
			if (points[j] == null) {
				count++;
			}
		}

		return count;
	}

	private LayoutContainer createMap() {

		initializePoints();

		LayoutContainer lcMap = new LayoutContainer();
		//lcMap.setHeight(MAX_DOWNPANEL_HEIGHT);

		map.setSize("100%", "100%");
		map.setGoogleBarEnabled(false);

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

				if (!isMNT && routeRadio.getValue()) {
					counter = 5;
				}

				if (!isMNT && polygonRadio.getValue()) {
					counter = 5;
				}

				if (overlay != null && overlay instanceof Marker) {
					sender.removeOverlay(overlay);
					clearFields();
					counter--;
				} else if (counter <= MAX_POINTS) {

					final Marker marker = new Marker(point, options);

					marker.addMarkerDragEndHandler(new MarkerDragEndHandler() {
						public void onDragEnd(MarkerDragEndEvent event) {
							// updateFields(marker);
							if (routeRadio.getValue()) {
								updateCoordinatesRoute();
								map.removeOverlay(polyline);
								polyline = new Polyline(coordinatesRoute);
								createVectorRoute();
							} else if (polygonRadio.getValue()) {
								updateCoordinatesPolygon();
								map.removeOverlay(polygon);
								polygon = new Polygon(coordinatesPolygon);
								createVectorPolygon();
							}
							fillCoordinateFields();
						}
					});

					sender.addOverlay(marker);
					points[counter] = marker;
					// updateFields(marker);
					System.out.println("Punto " + counter + ": " + point.getLatitude() + "," + point.getLongitude());

					if (routeRadio.getValue() && counter == 3) {
						updateCoordinatesRoute();
						polyline = new Polyline(coordinatesRoute);
						createVectorRoute();
					} else if (polygonRadio.getValue() && counter == 3) {
						updateCoordinatesPolygon();
						polygon = new Polygon(coordinatesPolygon);
						createVectorPolygon();
					}

					counter++;
					fillCoordinateFields();
				}
			}
		});

		lcMap.add(map);

		return lcMap;
	}

	private void setPointOnField(MyNumberField field, Marker marker, String coordMetric) {
		if (coordMetric.compareTo("lon") == 0) {
			field.setValue(marker.getLatLng().getLongitude());
		} else if (coordMetric.compareTo("lat") == 0) {
			field.setValue(marker.getLatLng().getLatitude());
		}
	}

	private void clearFields() {
		longitude.clear();
		latitude.clear();
		longitude2.clear();
		latitude2.clear();
		longitude3.clear();
		latitude3.clear();
		longitude4.clear();
		latitude4.clear();

	}

	private void resetForm() {
		map.clearOverlays();
		counter = 0;
		clearFields();
		code.clearSelections();
		description.clear();
		initializePoints();
		polyline = null;
		polygon = null;
	}

	private void updateCoordinatesPolygon() {
		for (int i = 0; i < coordinatesPolygon.length; i++) {
			if (i == 4) {
				coordinatesPolygon[i] = coordinatesPolygon[0];
			} else {
				coordinatesPolygon[i] = LatLng.newInstance(points[i].getLatLng().getLatitude(), points[i].getLatLng()
						.getLongitude());
			}
		}
	}

	private void updateCoordinatesRoute() {
		for (int i = 0; i < coordinatesRoute.length; i++) {
			coordinatesRoute[i] = LatLng.newInstance(points[i].getLatLng().getLatitude(), points[i].getLatLng()
					.getLongitude());
		}
	}

	private void createVectorRoute() {

		PolyStyleOptions style = PolyStyleOptions.newInstance(color, weight, opacity);
		map.addOverlay(polyline);
		polyline.setStrokeStyle(style);
	}

	private void createVectorPolygon() {

		PolyStyleOptions style = PolyStyleOptions.newInstance(color, weight, opacity);
		map.addOverlay(polygon);
		polygon.setStrokeStyle(style);
	}

	private void createRoute() {

		polyline = new Polyline(coordinatesRoute);

		PolyStyleOptions style = PolyStyleOptions.newInstance(color, weight, opacity);
		map.addOverlay(polyline);
		polyline.setStrokeStyle(style);
	}

	private void createPolygon() {

		polygon = new Polygon(coordinatesPolygon);

		PolyStyleOptions style = PolyStyleOptions.newInstance(color, weight, opacity);
		map.addOverlay(polygon);
		polygon.setStrokeStyle(style);

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

	private void preCommitForm() {
		int numPoints = countNullPoints();
		boolean isOK = false;
		boolean fourPoints = false;
		String coordType = "";

		if (numPoints == 4 && isMNT) {
			MobileError.report("Debe seleccionar al menos un punto en el mapa");
			return;
		} else {
			isOK = true;
		}

		System.out.println("Numero de puntos con null: " + numPoints);

		if (routeRadio.getValue() && numPoints > 0 && isMNT) {
			MobileError.report("Debe seleccionar al menos cuatro puntos en el mapa");
			return;
		} else {
			isOK = true;
		}

		if (polygonRadio.getValue() && numPoints > 0 && isMNT) {
			MobileError.report("Debe seleccionar al menos cuatro puntos en el mapa");
			return;
		} else {
			isOK = true;
		}

		if (pointRadio.getValue() && isOK) {
			coordType = "PUNTO";
		} else if (routeRadio.getValue() && isOK) {
			coordType = "RUTA";
			fourPoints = true;
		} else if (polygonRadio.getValue() && isOK) {
			coordType = "ZONA";
			fourPoints = true;
		}

		coornidateType.setValue(coordType);

		if (!fourPoints) {
			longitude2.clear();
			latitude2.clear();
			longitude3.clear();
			latitude3.clear();
			longitude4.clear();
			latitude4.clear();
		}

		if (isOK) {
			form.commitForm();
			map.setCenter(LatLng.newInstance(INITIAL_LATITUDE, INITIAL_LONGITUDE), ZOOM_LEVEL_NORMAL);
		}

	}

	public void fillCoordinateFields() {

		latitude.setValue(points[0].getLatLng().getLatitude());
		longitude.setValue(points[0].getLatLng().getLongitude());
		latitude2.setValue(points[1].getLatLng().getLatitude());
		longitude2.setValue(points[1].getLatLng().getLongitude());
		latitude3.setValue(points[2].getLatLng().getLatitude());
		longitude3.setValue(points[2].getLatLng().getLongitude());
		latitude4.setValue(points[3].getLatLng().getLatitude());
		longitude4.setValue(points[3].getLatLng().getLongitude());

	}
	
	public void visualizeFields(boolean state){

			longitude2.setVisible(state);
			latitude2.setVisible(state);
			longitude3.setVisible(state);
			latitude3.setVisible(state);
			longitude4.setVisible(state);
			latitude4.setVisible(state);
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
		options.setDraggable(true);
		options.setClickable(false);

		if (pointRadio.getValue()) {

			LatLng point = LatLng.newInstance((Double) latitude.getValue(), (Double) longitude.getValue());
			Marker marker = new Marker(point, options);
			createMarkerListener(marker, latitude, longitude);
			map.addOverlay(marker);
			map.setCenter(LatLng.newInstance((Double) latitude.getValue(), (Double) longitude.getValue()),
					ZOOM_LEVEL_CENTERED);

		} else if (coornidateType.getValue().compareTo("RUTA") == 0 || coornidateType.getValue().compareTo("ZONA") == 0) {

			LatLng point;
			Marker marker;

			point = LatLng.newInstance((Double) latitude.getValue(), (Double) longitude.getValue());
			marker = new Marker(point, options);
			createMarkerListener(marker, latitude, longitude);
			map.addOverlay(marker);

			point = LatLng.newInstance((Double) latitude2.getValue(), (Double) longitude2.getValue());
			marker = new Marker(point, options);
			createMarkerListener(marker, latitude2, longitude2);
			map.addOverlay(marker);

			point = LatLng.newInstance((Double) latitude3.getValue(), (Double) longitude3.getValue());
			marker = new Marker(point, options);
			createMarkerListener(marker, latitude3, longitude3);
			map.addOverlay(marker);
			map.setCenter(LatLng.newInstance((Double) latitude3.getValue(), (Double) longitude3.getValue()),
					ZOOM_LEVEL_CENTERED);

			point = LatLng.newInstance((Double) latitude4.getValue(), (Double) longitude4.getValue());
			marker = new Marker(point, options);
			createMarkerListener(marker, latitude4, longitude4);
			map.addOverlay(marker);

		}
	}

	private void createMarkerListener(final Marker marker, final MyNumberField lat, final MyNumberField lon) {

		marker.addMarkerDragEndHandler(new MarkerDragEndHandler() {
			public void onDragEnd(MarkerDragEndEvent event) {
				setPointOnField(lat, marker, "lat");
				setPointOnField(lon, marker, "lon");
				createPoints(coornidateType.getValue());
				createCoordinatePoints();
				if (coornidateType.getValue().compareTo("RUTA") == 0) {
					createRoute();
				} else if (coornidateType.getValue().compareTo("ZONA") == 0) {
					createPolygon();
				}
				fillCoordinateFields();
			}
		});
	}
}