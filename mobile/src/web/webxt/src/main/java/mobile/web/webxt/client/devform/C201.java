package mobile.web.webxt.client.devform;

import java.util.HashMap;
import java.util.Map;

import mobile.web.webxt.client.data.MyHttpProxy;
import mobile.web.webxt.client.data.MyProcessConfig;
import mobile.web.webxt.client.form.validations.Validate;
import mobile.web.webxt.client.form.widgets.ComboForm;
import mobile.web.webxt.client.form.widgets.InputBox;
import mobile.web.webxt.client.form.widgets.MyLabel;
import mobile.web.webxt.client.form.widgets.MyTextArea;
import mobile.web.webxt.client.form.widgets.RowContainer;
import mobile.web.webxt.client.form.widgetsgrid.ArrayColumnData;
import mobile.web.webxt.client.form.widgetsgrid.MyColumnData;
import mobile.web.webxt.client.form.widgetsgrid.SpecialComboColumn;
import mobile.web.webxt.client.form.widgetsgrid.MyColumnData.ColumnType;
import mobile.web.webxt.client.mvc.AppEvents;
import mobile.web.webxt.client.util.DatesManager;
import mobile.web.webxt.client.windows.AlertDialog;

import com.extjs.gxt.ui.client.Style.HorizontalAlignment;
import com.extjs.gxt.ui.client.Style.Orientation;
import com.extjs.gxt.ui.client.data.BaseStringFilterConfig;
import com.extjs.gxt.ui.client.data.FilterConfig;
import com.extjs.gxt.ui.client.data.ModelData;
import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.SelectionChangedEvent;
import com.extjs.gxt.ui.client.event.SelectionChangedListener;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.mvc.Dispatcher;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.Label;
import com.extjs.gxt.ui.client.widget.LayoutContainer;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.form.FieldSet;
import com.extjs.gxt.ui.client.widget.form.FormPanel;
import com.extjs.gxt.ui.client.widget.form.SimpleComboBox;
import com.extjs.gxt.ui.client.widget.layout.CenterLayout;
import com.extjs.gxt.ui.client.widget.layout.ColumnData;
import com.extjs.gxt.ui.client.widget.layout.RowLayout;
import com.google.gwt.user.client.Element;
import com.google.gwt.user.client.rpc.AsyncCallback;

public class C201 extends LayoutContainer {

	private final String process = "C201";
	
	private MyProcessConfig config;

	private Map<String, String> mfield;

	private MyHttpProxy proxy = new MyHttpProxy();
	
	ContentPanel panelPrincipal = new ContentPanel();  
	FormPanel formPerson = new FormPanel();
	FormPanel formPartner = new FormPanel();
	
	RowContainer row;
	MyLabel label;
	
	//Partner Fields
	InputBox isNew,codigo,asessorAux,freqAux, personAux,freqDescription;
	MyTextArea activity;
	ComboForm asessorCombo,freqCombo,personCombo;
	SimpleComboBox<Integer> diaReunion;
	final int LABEL_WIDTH = 60;
	
	//Person Fields:
	InputBox identificacion,nombre,apellido,edad,sexo,ecivil;
	
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
				
		panelPrincipal.setHeading("Clientes Individuales");  
		panelPrincipal.setFrame(true);  
		panelPrincipal.setSize(600, 410);  
		panelPrincipal.setLayout(new RowLayout(Orientation.VERTICAL));
		
		panelPrincipal.add(createPartnerForm(), new ColumnData(0));
			
		FormPanel fp = new FormPanel();
		fp.setHeaderVisible(false);
		fp.setBorders(false);
		fp.setWidth(400);
		fp.setFieldWidth(300);
		activity=new MyTextArea("Actividad","Partner:activity:1",300,300);
		activity.setHeight(100);
		activity.setEmptyText("Describa las actividades a las que se dedica el cliente");
		fp.add(activity);
		panelPrincipal.add(fp, new ColumnData(0));
		
		
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
	
	private FieldSet createPersonForm(){
		  
		FieldSet fieldSet = new FieldSet();
		fieldSet.setHeading("Datos Persona");
		fieldSet.setCollapsible(false);
		fieldSet.setWidth(280);
		fieldSet.setHeight(220);
		
		formPerson.setFrame(false);
		formPerson.setHeaderVisible(false);
		formPerson.setWidth(240);
		formPerson.setFieldWidth(150);
								
		identificacion= new InputBox("Identificacion","",20,11,Validate.ALFANUMERICO);
		identificacion.setName("identificacion");
		nombre = new InputBox("Nombre","",20,40,Validate.ALFANUMERICO);
		nombre.setName("nombre");
		apellido= new InputBox("Apellido","",40,40,Validate.ALFANUMERICO);
		apellido.setName("apellido");
		edad= new InputBox("Edad","",20,40,Validate.TEXT);
		edad.setName("edad");
		sexo= new InputBox("Sexo","",20,2,Validate.ALFANUMERICO);
		sexo.setName("sexo");
		ecivil= new InputBox("Estado Civil","",20,20,Validate.ALFANUMERICO);
		ecivil.setName("ecivil");
		
		formPerson.add(personAux);
		formPerson.add(identificacion);
		formPerson.add(nombre);
		formPerson.add(apellido);
		formPerson.add(edad);
		formPerson.add(sexo);
		formPerson.add(ecivil);
		
		identificacion.setReadOnly(true);
		nombre.setReadOnly(true);
		apellido.setReadOnly(true);
		edad.setReadOnly(true);
		sexo.setReadOnly(true);
		ecivil.setReadOnly(true);
				
		fieldSet.add(formPerson);

		return fieldSet;
	}
	
	private ContentPanel createPartnerForm(){
				
		ContentPanel cp = new ContentPanel();
		cp.setFrame(false);  
		cp.setSize(598, 225);
		cp.setBorders(false);
		cp.setHeaderVisible(false);
		cp.setLayout(new RowLayout(Orientation.HORIZONTAL));
		
		FieldSet fieldSet = new FieldSet();
		fieldSet.setHeading("Cliente");
		fieldSet.setCollapsible(false);
		fieldSet.setWidth(290);
		fieldSet.setHeight(220);
		
		formPartner.setFrame(false);
		formPartner.setHeaderVisible(false);
		formPartner.setWidth(300);
						
		row = new RowContainer();
		label = new MyLabel("Codigo:", LABEL_WIDTH);
		row.add(label);
				
		isNew = new InputBox("", "Partner:_new_item:1", 50,2,Validate.ALFANUMERICO);
		isNew.setVisible(false);
		isNew.setValue("1");
		codigo= new InputBox("","Partner:pk_partnerId:1",80,10,Validate.ALFANUMERICO);
		codigo.setAllowBlank(false);
		codigo.setMaxLength(10);
		
		row.add(codigo);
		row.add(isNew);
		fieldSet.add(row);
				
		row = new RowContainer();
		label = new MyLabel("Persona:", LABEL_WIDTH);
		row.add(label);
		
		final ArrayColumnData cdataPerson = new ArrayColumnData();
		cdataPerson.add(new MyColumnData("pk_personId", "Persona", 80, 20,false));
		cdataPerson.add(new MyColumnData("identificationNumber", "Identification", 80, 11,false));
		cdataPerson.add(new MyColumnData("name", "Nombre", 80, 40,false));
		cdataPerson.add(new MyColumnData("lastName", "Apellido", 150, 40,false));
		cdataPerson.add(new MyColumnData("dateOfBirth", ColumnType.HIDDEN));
		cdataPerson.add(new MyColumnData("genderTypeId", ColumnType.HIDDEN));
		cdataPerson.add(new MyColumnData("civilStatusId", ColumnType.HIDDEN));
		personCombo = new ComboForm(80,"pk_personId");
		personCombo.setRqData("Person", cdataPerson);
		personCombo.setAllowBlank(false);
		
		final DatesManager dm =new DatesManager();
					
		personCombo.addSelectionChangedListener(new SelectionChangedListener<ModelData>() {
			@SuppressWarnings("static-access")
			@Override
			public void selectionChanged(SelectionChangedEvent<ModelData> se) {
				ModelData selected = se.getSelectedItem();
				int age=0;
				identificacion.setValue(selected.get("identificationNumber").toString());
				nombre.setValue(selected.get("name").toString());
				apellido.setValue(selected.get("lastName").toString());
				age=dm.calculateAge(selected.get("dateOfBirth").toString());
				
				edad.setValue(String.valueOf(age));
				sexo.setValue(selected.get("genderTypeId").toString());
				ecivil.setValue(selected.get("civilStatusId").toString());
			}
		});
		
		personAux = new InputBox("", "Partner:personId:1", 50,100,Validate.ALFANUMERICO);
		personAux.setVisible(false);
		
		row.add(personCombo);
		row.add(personAux);
		fieldSet.add(row);
		
					
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
		
		asessorAux = new InputBox("", "Partner:asessorId:1", 50,100,Validate.ALFANUMERICO);
		asessorAux.setVisible(false);
		asessorCombo.setAllowBlank(false);
		
		row.add(asessorCombo);
		row.add(asessorAux);
		fieldSet.add(row);
		
		row = new RowContainer();
		label = new MyLabel("Frecuencia:", LABEL_WIDTH);
		row.add(label);
		
		final ArrayColumnData cdataFrequency = new ArrayColumnData();
		cdataFrequency.add(new MyColumnData("pk_frequencyId", "Codigo", 40, 20,false));
		cdataFrequency.add(new MyColumnData("description", "Descripcion", 120, 20,false));
		freqCombo = new ComboForm(80,"pk_frequencyId");
		freqCombo.setRqData("Frequency", cdataFrequency);
		
		freqDescription = new InputBox(100);
		freqDescription.setReadOnly(true);
				
		freqCombo.addSelectionChangedListener(new SelectionChangedListener<ModelData>() {
			@Override
			public void selectionChanged(SelectionChangedEvent<ModelData> se) {
				ModelData selected = se.getSelectedItem();
				freqDescription.setValue(selected.get("description").toString());
			}
		});
		
		freqAux = new InputBox("", "Partner:meetingFrequencyId:1", 50,100,Validate.ALFANUMERICO);
		freqAux.setVisible(false);
		
		row.add(freqCombo);
		row.add(freqDescription);
		row.add(freqAux);
		fieldSet.add(row);
		
		row = new RowContainer();
		label = new MyLabel("Dia reunion:", LABEL_WIDTH);
		row.add(label);
		
		diaReunion=new SimpleComboBox<Integer>();
		
		for (int j=1;j<8;j++){
			diaReunion.add(j);
		}
		
		diaReunion.setAllowBlank(true);	
		diaReunion.setWidth(50);
		
		row.add(diaReunion);
		fieldSet.add(row);
		
		row = new RowContainer();
		label = new MyLabel("Actividad:", LABEL_WIDTH);
		row.add(label);
		
		fieldSet.add(formPartner);
		cp.add(fieldSet);
		cp.add(new Label(""));
		cp.add(new Label(""));
		cp.add(createPersonForm());
		
		return cp;
	}
	

	private boolean validateForm(){
				
		//System.out.println("is valid: "+formPartner.isValid());
		//formPerson.isValid(false);
		boolean isOK=true;
			
		if (codigo.getValue() == null || asessorAux.getValue() == null
				|| personAux.getValue() == null) {
			isOK = false;
			return isOK;
		}

		return isOK;
	}
	
	private void clearFields(){
		
		asessorCombo.clearSelections();
		freqCombo.clearSelections();
		personCombo.clearSelections();
		
		identificacion.clear();
		nombre.clear();
		apellido.clear();
		edad.clear();
		sexo.clear();
		ecivil.clear();
		
		codigo.clear();
		asessorAux.clear();
		freqAux.clear();
		diaReunion.clear();
		activity.clear();
		personAux.clear();
		freqDescription.clear();
				
		codigo.setCursorPos(0);
		
	}
	
	private void prepareFields(){
		
		asessorAux.setValue(asessorCombo.getRawValue().toString());
		freqAux.setValue(freqCombo.getRawValue().toString());
		personAux.setValue(personCombo.getRawValue().toString());
		
	}
	
	public void commitForm(){
		
		AlertDialog alertEmpty = new AlertDialog("Error","Debe llenar todos los campos requeridos");
		mfield = new HashMap<String, String>();
		
		prepareFields();
		
		AsyncCallback<Boolean> callback = new AsyncCallback<Boolean>() {
			public void onSuccess(Boolean result) {
				Dispatcher.forwardEvent(AppEvents.UserNotification,
						"Mantenimiento exitoso");
			}

			public void onFailure(Throwable caught) {
				new AlertDialog("MyFormPanel", caught.getMessage()).show();
			}
		};

		if (validateForm()){
			
			mfield.put(isNew.getPersistentInfo(), isNew.getValue().toString());
			mfield.put(codigo.getPersistentInfo(), codigo.getValue().toString());
			mfield.put(personAux.getPersistentInfo(), personAux.getValue().toString());
			
			if (activity.getValue()!=null){
				mfield.put(activity.getPersistentInfo(), activity.getValue().toString());
			}
			
			mfield.put(asessorAux.getPersistentInfo(), asessorAux.getValue().toString());
			
			if (freqAux.getValue()!=null){
				mfield.put(freqAux.getPersistentInfo(), freqAux.getValue().toString());
			}
			
			if (diaReunion.getValue()!=null){
				mfield.put("Partner:meetingDay:1", diaReunion.getRawValue().toString());
			}
			
			proxy.commitForm(config, mfield, callback);
			clearFields();
		}else{
			alertEmpty.show();
		}
	}

}