package mobile.web.webxt.client.devform;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import mobile.web.webxt.client.data.MyHttpProxy;
import mobile.web.webxt.client.data.MyListStore;
import mobile.web.webxt.client.data.MyPagingLoader;
import mobile.web.webxt.client.data.MyProcessConfig;
import mobile.web.webxt.client.form.EntityContentPanel;
import mobile.web.webxt.client.form.validations.Validate;
import mobile.web.webxt.client.form.widgets.ComboForm;
import mobile.web.webxt.client.form.widgets.InputBox;
import mobile.web.webxt.client.form.widgets.MyTextArea;
import mobile.web.webxt.client.form.widgetsgrid.ArrayColumnData;
import mobile.web.webxt.client.form.widgetsgrid.ComboColumn;
import mobile.web.webxt.client.form.widgetsgrid.EntityEditorGrid;
import mobile.web.webxt.client.form.widgetsgrid.ExpireColumnConfig;
import mobile.web.webxt.client.form.widgetsgrid.GridPagingToolBar;
import mobile.web.webxt.client.form.widgetsgrid.GridToolBar;
import mobile.web.webxt.client.form.widgetsgrid.MyColumnData;
import mobile.web.webxt.client.form.widgetsgrid.NormalColumn;
import mobile.web.webxt.client.mvc.AppEvents;

import mobile.web.webxt.client.windows.AlertDialog;

import com.extjs.gxt.ui.client.Style.HorizontalAlignment;
import com.extjs.gxt.ui.client.Style.Orientation;
import com.extjs.gxt.ui.client.Style.SortDir;
import com.extjs.gxt.ui.client.binding.FieldBinding;
import com.extjs.gxt.ui.client.binding.FormBinding;
import com.extjs.gxt.ui.client.event.BaseEvent;
import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.Events;
import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.mvc.Dispatcher;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.Html;
import com.extjs.gxt.ui.client.widget.Label;
import com.extjs.gxt.ui.client.widget.LayoutContainer;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.form.DateField;
import com.extjs.gxt.ui.client.widget.form.FormPanel;
import com.extjs.gxt.ui.client.widget.form.FormPanel.LabelAlign;
import com.extjs.gxt.ui.client.widget.grid.ColumnConfig;
import com.extjs.gxt.ui.client.widget.grid.ColumnModel;
import com.extjs.gxt.ui.client.widget.grid.filters.GridFilters;
import com.extjs.gxt.ui.client.widget.grid.filters.StringFilter;
import com.extjs.gxt.ui.client.widget.layout.CenterLayout;
import com.extjs.gxt.ui.client.widget.layout.ColumnData;
import com.extjs.gxt.ui.client.widget.layout.FormLayout;
import com.extjs.gxt.ui.client.widget.layout.RowData;
import com.extjs.gxt.ui.client.widget.layout.RowLayout;
import com.google.gwt.user.client.Element;
import com.google.gwt.user.client.rpc.AsyncCallback;

public class C203 extends LayoutContainer {

	private final String process = "C203";
	
	private final Integer PAGE_SIZE = 5;
	
	private MyProcessConfig config;

	private Map<String, String> mfield;

	private MyHttpProxy proxy = new MyHttpProxy();
	
	ContentPanel panelPrincipal = new ContentPanel();  
	FormPanel formPerson = new FormPanel();
	FormPanel formheaderLeft = new FormPanel();
	FormPanel formheaderRight = new FormPanel();
	FormPanel formheaderBottom = new FormPanel();
	DateField formationDate = new DateField();
	
	//Partner Fields
	InputBox isNew,codigo,asessorAux,freqAux,diaReunion;
	MyTextArea activity, description;
	ComboForm asessorCombo,freqCombo;
	
	Button guardar,cancelar;

	@Override
	protected void onRender(Element parent, int index) {
		super.onRender(parent, index);
		setLayout(new CenterLayout());
		getAriaSupport().setPresentation(true);
		config = new MyProcessConfig(process);
		createPanel();
		add(panelPrincipal);
	}
	
	private void createPanel(){
				
		panelPrincipal.setHeading("Clientes Grupales");  
		panelPrincipal.setFrame(true);  
		panelPrincipal.setSize(600, 410);  
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
	
	private LayoutContainer createGrid(){
		  
		LayoutContainer lc = new LayoutContainer();
		lc.setLayout(new CenterLayout());
		lc.getAriaSupport().setPresentation(true);
		
		// Config
		String entity = "PartnerGroupMember";

		final ArrayColumnData cdata = new ArrayColumnData();
		cdata.add(new MyColumnData("pk_partnerGroupMemberId", "Codigo", 70, 20,false));
		cdata.add(new MyColumnData("personId", "Persona", 70, 40, false));
		cdata.add(new MyColumnData("responsabilityId", "Responsabilidad", 120, 40, false));
		cdata.add(new MyColumnData("observations", "Observaciones", 240, 40, false));
		
		MyProcessConfig config = new MyProcessConfig(process, entity,cdata.getIdFields());
		
		// Proxy - loader - store
		MyHttpProxy proxy = new MyHttpProxy();
		final MyPagingLoader loader = new MyPagingLoader(proxy, config);
		final MyListStore store = new MyListStore(loader);
		
		// Column model
		List<ColumnConfig> configs = new ArrayList<ColumnConfig>();

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
		EntityContentPanel cp = new EntityContentPanel("Miembros del Grupo",580,180);

		// Grid
		final EntityEditorGrid grid = new EntityEditorGrid(store, cm);
		grid.setAutoExpandColumn("pk_partnerGroupMemberId");
		grid.addPlugin(filters);
		cp.add(grid);
		grid.addListener(Events.Attach, new Listener<BaseEvent>() {
			public void handleEvent(BaseEvent be) {
				store.sort(cdata.getIdFields().get(0), SortDir.ASC);
			}
		});
		
		// Top tool bar
		GridToolBar toolBar = new GridToolBar(grid, store);
		cp.setTopComponent(toolBar);

		// Paging tool bar
		final GridPagingToolBar pagingToolBar = new GridPagingToolBar(
				PAGE_SIZE, loader);
		cp.setBottomComponent(pagingToolBar);
				
		lc.add(cp) ;
		
		return lc;
		
	}
	
	private ContentPanel createHeaderForm(){
				
		ContentPanel cp = new ContentPanel();
		cp.setFrame(false);  
		cp.setSize(598, 250);
		cp.setBorders(false);
		cp.setHeaderVisible(false);
		cp.setLayout(new RowLayout(Orientation.HORIZONTAL)); 
		
		formheaderLeft.setFrame(false);
		formheaderLeft.setHeaderVisible(false);
		formheaderLeft.setWidth(290);
		formheaderLeft.setFieldWidth(160);
		
		formheaderRight.setFrame(false);
		formheaderRight.setHeaderVisible(false);
		formheaderRight.setWidth(290);
		formheaderRight.setFieldWidth(120);
		
		formheaderBottom.setFrame(false);
		formheaderBottom.setHeaderVisible(false);
		formheaderBottom.setWidth(580);
		formheaderBottom.setFieldWidth(400);
		
		isNew = new InputBox("", "Partner:_isNew:1", 20,2,Validate.ALFANUMERICO);
		isNew.setVisible(false);
		isNew.setValue("1");
		codigo= new InputBox("Codigo","PartnerGroup:pk_partnerGroupId:1",20,10,Validate.ALFANUMERICO);
		codigo.setAllowBlank(false);
		codigo.setMaxLength(10);
				
		activity=new MyTextArea("Actividad","PartnerGroup:activity:1",20,300);
		activity.setHeight(70);
		
		description=new MyTextArea("Descripcion","PartnerGroup:groupDescription:1",20,100);
		description.setHeight(40);
				
		final ArrayColumnData cdataAsessor = new ArrayColumnData();
		cdataAsessor.add(new MyColumnData("pk_asessorId", "Asesor", 40, 20,false));
		asessorCombo = new ComboForm("Asesor","pk_asessorId");
		asessorCombo.setRqData("Asessor", cdataAsessor);
		asessorAux = new InputBox("", "Partner:asessorId:1", 20,100,Validate.ALFANUMERICO);
		asessorAux.setVisible(false);
		asessorCombo.setAllowBlank(false);
		
		final ArrayColumnData cdataFrequency = new ArrayColumnData();
		cdataFrequency.add(new MyColumnData("pk_meetingFrequencyId", "Codigo", 40, 20,false));
		cdataFrequency.add(new MyColumnData("frequency", "Frecuencia", 70, 20,false));
		freqCombo = new ComboForm("Frecuencia de Reunion","pk_meetingFrequencyId");
		freqCombo.setRqData("MeetingFrequency", cdataFrequency);
		freqAux = new InputBox("", "Partner:meetingFrequencyId:1", 20,100,Validate.ALFANUMERICO);
		freqAux.setVisible(false);
		
		diaReunion= new InputBox("Dia Reunion","Partner:meetingFrequencyId:1",20,10,Validate.NUMERICO);
		diaReunion.setAllowBlank(false);	
		
		formationDate.setFieldLabel("Fecha creacion");
		Date currentDate = new Date();
		formationDate.setValue(currentDate);
		formationDate.setEnabled(false);
		
		formheaderLeft.add(isNew);
		formheaderLeft.add(codigo);
		formheaderLeft.add(description);
		formheaderLeft.add(activity);
				
		formheaderRight.add(asessorCombo);
		formheaderRight.add(asessorAux);
		formheaderRight.add(freqCombo);
		formheaderRight.add(freqAux);
		formheaderRight.add(diaReunion);
		formheaderRight.add(formationDate);
		
		cp.add(formheaderLeft);
		cp.add(formheaderRight);
		
		return cp;
	}
	
	private boolean validateForm(){
		boolean isOK=true;
			
		if (codigo.getValue() == null || asessorAux.getValue() == null) {
			isOK = false;
			return isOK;
		}

		return isOK;
	}
	
	private void clearFields(){
		codigo.clear();
		asessorAux.clear();
		freqAux.clear();
		diaReunion.clear();
		activity.clear();
		description.clear();
		formationDate.clear();
		
		Date currentDate = new Date();
		formationDate.setValue(currentDate);
		
		asessorCombo.clear();
		freqCombo.clear();
		
		codigo.setCursorPos(0);
		
	}
	
	private void prepareFields(){
		
		asessorAux.setValue(asessorCombo.getRawValue().toString());
		freqAux.setValue(freqCombo.getRawValue().toString());
		
	}
	
	public void commitForm(){
		
//		AlertDialog alertEmpty = new AlertDialog("Error","Debe llenar todos los campos requeridos");
//		mfield = new HashMap<String, String>();
//		
//		prepareFields();
//		
//		AsyncCallback<Boolean> callback = new AsyncCallback<Boolean>() {
//			public void onSuccess(Boolean result) {
//				Dispatcher.forwardEvent(AppEvents.UserNotification,
//						"Mantenimiento exitoso");
//			}
//
//			public void onFailure(Throwable caught) {
//				new AlertDialog("MyFormPanel", caught.getMessage()).show();
//			}
//		};
//
//		if (validateForm()){
//			
//			mfield.put(isNew.getFieldInfo(), isNew.getValue().toString());
//			mfield.put(codigo.getFieldInfo(), codigo.getValue().toString());
//			mfield.put(personAux.getFieldInfo(), personAux.getValue().toString());
//			
//			if (activity.getValue()!=null){
//				mfield.put(activity.getFieldInfo(), activity.getValue().toString());
//			}
//			
//			mfield.put(asessorAux.getFieldInfo(), asessorAux.getValue().toString());
//			
//			if (freqAux.getValue()!=null){
//				mfield.put(freqAux.getFieldInfo(), freqAux.getValue().toString());
//			}
//			
//			if (diaReunion.getValue()!=null){
//				mfield.put(diaReunion.getFieldInfo(), diaReunion.getValue().toString());
//			}
//			
//			System.out.println("MyForm.commitChanges");
//			for (String key : mfield.keySet()) {
//				System.out.println(key + ":" + mfield.get(key));
//			}
//			
//			proxy.commitForm(config, mfield, callback);
//			clearFields();
//		}else{
//			alertEmpty.show();
//		}
	}

}