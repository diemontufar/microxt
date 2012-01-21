package mobile.web.webxt.client.devform;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import mobile.common.tools.Format;
import mobile.web.webxt.client.data.MyHttpProxy;
import mobile.web.webxt.client.data.MyListStore;
import mobile.web.webxt.client.data.MyPagingLoader;
import mobile.web.webxt.client.data.MyProcessConfig;
import mobile.web.webxt.client.form.EntityContentPanel;
import mobile.web.webxt.client.form.validations.Validate;
import mobile.web.webxt.client.form.widgets.ComboForm;
import mobile.web.webxt.client.form.widgets.InputBox;
import mobile.web.webxt.client.form.widgets.MyLabel;
import mobile.web.webxt.client.form.widgets.MyTextArea;
import mobile.web.webxt.client.form.widgets.RowContainer;
import mobile.web.webxt.client.form.widgetsgrid.ArrayColumnData;
import mobile.web.webxt.client.form.widgetsgrid.ComboColumn;
import mobile.web.webxt.client.form.widgetsgrid.EntityEditorGrid;
import mobile.web.webxt.client.form.widgetsgrid.ExpireColumnConfig;
import mobile.web.webxt.client.form.widgetsgrid.GridPagingToolBar;
import mobile.web.webxt.client.form.widgetsgrid.GridToolBar;
import mobile.web.webxt.client.form.widgetsgrid.MyColumnData;
import mobile.web.webxt.client.form.widgetsgrid.NormalColumn;
import mobile.web.webxt.client.util.DatesManager;

import com.extjs.gxt.ui.client.Style.HorizontalAlignment;
import com.extjs.gxt.ui.client.Style.Orientation;
import com.extjs.gxt.ui.client.Style.SortDir;
import com.extjs.gxt.ui.client.data.BaseStringFilterConfig;
import com.extjs.gxt.ui.client.data.FilterConfig;
import com.extjs.gxt.ui.client.data.ModelData;
import com.extjs.gxt.ui.client.event.BaseEvent;
import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.Events;
import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.event.SelectionChangedEvent;
import com.extjs.gxt.ui.client.event.SelectionChangedListener;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.store.Store;
import com.extjs.gxt.ui.client.store.StoreEvent;
import com.extjs.gxt.ui.client.store.StoreListener;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.LayoutContainer;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.form.FieldSet;
import com.extjs.gxt.ui.client.widget.form.FormPanel;
import com.extjs.gxt.ui.client.widget.form.SimpleComboBox;
import com.extjs.gxt.ui.client.widget.grid.ColumnConfig;
import com.extjs.gxt.ui.client.widget.grid.ColumnModel;
import com.extjs.gxt.ui.client.widget.grid.RowNumberer;
import com.extjs.gxt.ui.client.widget.grid.filters.GridFilters;
import com.extjs.gxt.ui.client.widget.grid.filters.StringFilter;
import com.extjs.gxt.ui.client.widget.layout.CenterLayout;
import com.extjs.gxt.ui.client.widget.layout.ColumnData;
import com.extjs.gxt.ui.client.widget.layout.RowLayout;
import com.google.gwt.user.client.Element;

public class C203 extends LayoutContainer {

	private final String process = "C203";

	private final Integer PAGE_SIZE = 5;

	private MyProcessConfig config;

	private Map<String, String> mfield;

	private MyHttpProxy proxy = new MyHttpProxy();

	private int membersNumber = 0;

	ContentPanel panelPrincipal = new ContentPanel();
	FormPanel formPerson = new FormPanel();
	FormPanel formheaderHeader = new FormPanel();
	FormPanel formheaderBottom = new FormPanel();

	// Partner Fields
	InputBox isNew, codigo, asessorAux, freqAux, freqDescription, formationDate;
	MyTextArea activity, description;
	ComboForm asessorCombo, freqCombo;
	SimpleComboBox<Integer> diaReunion;

	RowContainer row;
	MyLabel label;

	final int LABEL_WIDTH = 65;

	Button guardar, cancelar;

	@Override
	protected void onRender(Element parent, int index) {
		super.onRender(parent, index);
		setLayout(new CenterLayout());
		getAriaSupport().setPresentation(true);
		config = new MyProcessConfig(process);
		createPanel();
		add(panelPrincipal);
	}

	private void createPanel() {

		panelPrincipal.setHeading("Clientes Grupales");
		panelPrincipal.setFrame(true);
		panelPrincipal.setSize(600, 430);
		panelPrincipal.setLayout(new RowLayout(Orientation.VERTICAL));

		panelPrincipal.add(createHeaderForm(), new ColumnData(0));
		panelPrincipal.add(createGrid(), new ColumnData(0));

		guardar = new Button("Guardar", new SelectionListener<ButtonEvent>() {
			@Override
			public void componentSelected(ButtonEvent ce) {
				commitForm();
			}
		});

		cancelar = new Button("Cancelar", new SelectionListener<ButtonEvent>() {
			@Override
			public void componentSelected(ButtonEvent ce) {
				clearFields();
			}
		});

		panelPrincipal.addButton(guardar);
		panelPrincipal.addButton(cancelar);
		panelPrincipal.setButtonAlign(HorizontalAlignment.RIGHT);

	}

	@SuppressWarnings("unchecked")
	private LayoutContainer createGrid() {

		LayoutContainer lc = new LayoutContainer();
		lc.setLayout(new CenterLayout());
		lc.getAriaSupport().setPresentation(true);

		// Config
		String entity = "PartnerGroupMember";

		final ArrayColumnData cdata = new ArrayColumnData();
		cdata.add(new MyColumnData("pk_partnerGroupMemberId", "Codigo", 70, 20, false));
		cdata.add(new MyColumnData("personId", "Persona", 70, 40, false));
		cdata.add(new MyColumnData("responsabilityId", "Responsabilidad", 120, 40, false));
		cdata.add(new MyColumnData("observations", "Observaciones", 240, 40, true));

		MyProcessConfig config = new MyProcessConfig(process, entity, cdata.getIdFields());

		// Proxy - loader - store
		MyHttpProxy proxy = new MyHttpProxy();
		final MyPagingLoader loader = new MyPagingLoader(proxy, config);
		final MyListStore store = new MyListStore(loader);

		// Column model
		List<ColumnConfig> configs = new ArrayList<ColumnConfig>();

		configs.add(new RowNumberer());
		configs.add(new NormalColumn(cdata.get(0)));

		ComboColumn profileComboColumn = new ComboColumn(cdata.get(1));
		ArrayColumnData cdataComboProfile = new ArrayColumnData();
		cdataComboProfile.add(new MyColumnData("pk_personId", "ID", 40));
		cdataComboProfile.add(new MyColumnData("identificationNumber", "Identificacion", 100));
		cdataComboProfile.add(new MyColumnData("name", "Nombre", 110));
		cdataComboProfile.add(new MyColumnData("lastName", "Apellido", 120));
		profileComboColumn.setRqData("Person", cdataComboProfile);
		configs.add(profileComboColumn);

		ComboColumn respComboColumn = new ComboColumn(cdata.get(2));
		ArrayColumnData cdataComboResp = new ArrayColumnData();
		cdataComboResp.add(new MyColumnData("pk_responsabilityId", "Cod.", 40));
		cdataComboResp.add(new MyColumnData("name", "Nombre", 100));
		respComboColumn.setRqData("Responsability", cdataComboResp);
		configs.add(respComboColumn);

		configs.add(new NormalColumn(cdata.get(3)));

		configs.add(new ExpireColumnConfig());

		ColumnModel cm = new ColumnModel(configs);

		// Filters
		GridFilters filters = new GridFilters();
		StringFilter parameterIdFilter = new StringFilter(cdata.getIdFields().get(0));
		StringFilter subsystemFilter = new StringFilter(cdata.getIdFields().get(1));
		filters.addFilter(parameterIdFilter);
		filters.addFilter(subsystemFilter);

		// Content panel
		EntityContentPanel cp = new EntityContentPanel("Miembros del Grupo", 580, 180);

		// Grid
		final EntityEditorGrid grid = new EntityEditorGrid(store, cm);
		grid.setAutoExpandColumn("pk_partnerGroupMemberId");
		grid.addPlugin(filters);
		grid.getAriaSupport().setLabelledBy(cp.getHeader().getId() + "-label");
		cp.add(grid);
		grid.addListener(Events.Attach, new Listener<BaseEvent>() {
			public void handleEvent(BaseEvent be) {
				store.sort(cdata.getIdFields().get(0), SortDir.ASC);
			}
		});

		// Top tool bar
		final GridToolBar toolBar = new GridToolBar(grid, store);
		toolBar.initColumnIndex = 2;
		// toolBar.enableAddButton(false);
		// toolBar.enableSaveButton(false);
		// toolBar.enableResetButton(false);
		cp.setTopComponent(toolBar);

		store.addStoreListener(new StoreListener() {
			public void handleEvent(StoreEvent se) {
				if (se.getType() == Store.Add) {
					System.out.println("Add pressed");
					grid.getStore().getAt(membersNumber).set("pk_partnerGroupMemberId", "1234");
					grid.getColumnModel().getColumn(1).getEditor().disable();
					membersNumber++;
				}
				if (se.getType() == Store.Clear) {
					System.out.println("Reject pressed");
				}
				if (se.getType() == Store.Update) {
					System.out.println("Save pressed");
				}
			}
		});

		// Paging tool bar
		final GridPagingToolBar pagingToolBar = new GridPagingToolBar(PAGE_SIZE, loader);
		cp.setBottomComponent(pagingToolBar);

		lc.add(cp);

		return lc;

	}

	@SuppressWarnings("static-access")
	private ContentPanel createHeaderForm() {

		ContentPanel cp = new ContentPanel();
		cp.setFrame(false);  
		cp.setSize(598, 270);
		cp.setBorders(false);
		cp.setHeaderVisible(false);
		cp.setLayout(new RowLayout(Orientation.HORIZONTAL));
		
		FieldSet fieldSetLeft = new FieldSet();
		fieldSetLeft.setBorders(false);
		fieldSetLeft.setWidth(290);
		fieldSetLeft.setHeight(250);

		FieldSet fieldSetRight = new FieldSet();
		fieldSetRight.setBorders(false);
		fieldSetRight.setWidth(290);
		fieldSetRight.setHeight(250);

		formheaderHeader.setFrame(false);
		formheaderHeader.setHeaderVisible(false);
		formheaderHeader.setWidth(290);
		formheaderHeader.setFieldWidth(160);

		formheaderBottom.setFrame(false);
		formheaderBottom.setHeaderVisible(false);
		formheaderBottom.setWidth(580);
		formheaderBottom.setFieldWidth(400);

		row = new RowContainer();
		label = new MyLabel("Codigo:", LABEL_WIDTH);
		row.add(label);

		isNew = new InputBox("", "Partner:_isNew:1", 20, 2, Validate.ALFANUMERICO);
		isNew.setVisible(false);
		isNew.setValue("1");
		codigo = new InputBox("", "PartnerGroup:pk_partnerGroupId:1", 80, 11, Validate.ALFANUMERICO);
		codigo.setAllowBlank(false);
		codigo.setMaxLength(10);
		row.add(codigo);
		row.add(isNew);
		fieldSetLeft.add(row);
		
		row = new RowContainer();
		label = new MyLabel("Descripcion:", LABEL_WIDTH);
		row.add(label);	
		row.setHeight(70);
				
		description=new MyTextArea("Descripcion","PartnerGroup:groupDescription:1",170,100);
		description.setHeight(60);
		description.setEmptyText("Ingrese una descripcion del grupo");
		
		row.add(description);
		fieldSetRight.add(row);
				
		row = new RowContainer();
		label = new MyLabel("Actividad:", LABEL_WIDTH);
		row.add(label);
		row.setHeight(100);

		activity = new MyTextArea("", "PartnerGroup:activity:1", 170, 300);
		activity.setHeight(90);
		activity.setEmptyText("Ingrese las actividades a las que se dedica el grupo");
		
		row.add(activity);
		fieldSetRight.add(row);
		
		
		row = new RowContainer();
		label = new MyLabel("Asesor:", LABEL_WIDTH);
		row.add(label);

		final ArrayColumnData cdataAsessor = new ArrayColumnData();
		cdataAsessor.add(new MyColumnData("pk_userId", "Asesor", 80, 20,false));
		cdataAsessor.add(new MyColumnData("name", "Nombre", 150, 20,false));
		asessorCombo = new ComboForm(80,"pk_userId");
		asessorCombo.setRqData("UserAccount", cdataAsessor);
		
		String filterField = "userTypeId";

		FilterConfig filter = new BaseStringFilterConfig();
		filter.setField(filterField);
		filter.setComparison("=");
		filter.setValue("ASE");

		asessorCombo.addFilter(filter);
		asessorCombo.setLoaded(false);
		
		asessorAux = new InputBox("", "Partner:asessorId:1", 50, 100, Validate.ALFANUMERICO);
		asessorAux.setVisible(false);
		asessorCombo.setAllowBlank(false);

		row.add(asessorCombo);
		row.add(asessorAux);
		fieldSetLeft.add(row);

		row = new RowContainer();
		label = new MyLabel("Frecuencia:", LABEL_WIDTH);
		row.add(label);

		final ArrayColumnData cdataFrequency = new ArrayColumnData();
		cdataFrequency.add(new MyColumnData("pk_meetingFrequencyId", "Codigo", 40, 20, false));
		cdataFrequency.add(new MyColumnData("frequency", "Frecuencia", 120, 20, false));
		freqCombo = new ComboForm(80, "pk_meetingFrequencyId");
		freqCombo.setRqData("MeetingFrequency", cdataFrequency);

		freqDescription = new InputBox(100);
		freqDescription.setReadOnly(true);

		freqCombo.addSelectionChangedListener(new SelectionChangedListener<ModelData>() {
			@Override
			public void selectionChanged(SelectionChangedEvent<ModelData> se) {
				ModelData selected = se.getSelectedItem();
				freqDescription.setValue(selected.get("frequency").toString());
			}
		});

		freqAux = new InputBox("", "Partner:meetingFrequencyId:1", 50, 100, Validate.ALFANUMERICO);
		freqAux.setVisible(false);
		
		row.add(freqCombo);
		row.add(freqDescription);
		row.add(freqAux);
		fieldSetLeft.add(row);
		
		row = new RowContainer();
		label = new MyLabel("Dia reunion:", LABEL_WIDTH);
		row.add(label);

		diaReunion = new SimpleComboBox<Integer>();

		for (int j = 1; j < 8; j++) {
			diaReunion.add(j);
		}

		diaReunion.setAllowBlank(true);
		diaReunion.setWidth(50);

		row.add(diaReunion);
		fieldSetLeft.add(row);

		row = new RowContainer();
		label = new MyLabel("Fecha Formacion:", LABEL_WIDTH);
		row.add(label);

		formationDate = new InputBox("", "Partner:fomationDate:1", 80, 30, Validate.TEXT);
		formationDate.setValue(DatesManager.getStringCurrentDate(Format.DATE));
		formationDate.setReadOnly(true);

		row.add(formationDate);
		fieldSetLeft.add(row);

		cp.add(fieldSetLeft);
		cp.add(fieldSetRight);

		return cp;
	}

	private boolean validateForm() {
		boolean isOK = true;

		if (codigo.getValue() == null || asessorAux.getValue() == null) {
			isOK = false;
			return isOK;
		}

		return isOK;
	}

	@SuppressWarnings("static-access")
	private void clearFields() {
		codigo.clear();
		asessorAux.clear();
		freqAux.clear();
		diaReunion.clear();
		activity.clear();
		description.clear();
		formationDate.clear();

		formationDate.setValue(DatesManager.getStringCurrentDate(Format.DATE));

		asessorCombo.clear();
		freqCombo.clear();

		codigo.setCursorPos(0);

	}

	private void prepareFields() {

		asessorAux.setValue(asessorCombo.getRawValue().toString());
		freqAux.setValue(freqCombo.getRawValue().toString());

	}

	public void commitForm() {

		// AlertDialog alertEmpty = new
		// AlertDialog("Error","Debe llenar todos los campos requeridos");
		// mfield = new HashMap<String, String>();
		//
		// prepareFields();
		//
		// AsyncCallback<Boolean> callback = new AsyncCallback<Boolean>() {
		// public void onSuccess(Boolean result) {
		// Dispatcher.forwardEvent(AppEvents.UserNotification,
		// "Mantenimiento exitoso");
		// }
		//
		// public void onFailure(Throwable caught) {
		// new AlertDialog("MyFormPanel", caught.getMessage()).show();
		// }
		// };
		//
		// if (validateForm()){
		//
		// mfield.put(isNew.getFieldInfo(), isNew.getValue().toString());
		// mfield.put(codigo.getFieldInfo(), codigo.getValue().toString());
		// mfield.put(personAux.getFieldInfo(),
		// personAux.getValue().toString());
		//
		// if (activity.getValue()!=null){
		// mfield.put(activity.getFieldInfo(), activity.getValue().toString());
		// }
		//
		// mfield.put(asessorAux.getFieldInfo(),
		// asessorAux.getValue().toString());
		//
		// if (freqAux.getValue()!=null){
		// mfield.put(freqAux.getFieldInfo(), freqAux.getValue().toString());
		// }
		//
		// if (diaReunion.getValue()!=null){
		// mfield.put(diaReunion.getFieldInfo(),
		// diaReunion.getValue().toString());
		// }
		//
		// System.out.println("MyForm.commitChanges");
		// for (String key : mfield.keySet()) {
		// System.out.println(key + ":" + mfield.get(key));
		// }
		//
		// proxy.commitForm(config, mfield, callback);
		// clearFields();
		// }else{
		// alertEmpty.show();
		// }
	}

}