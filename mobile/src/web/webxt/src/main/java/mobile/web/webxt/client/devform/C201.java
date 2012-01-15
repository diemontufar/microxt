package mobile.web.webxt.client.devform;

import java.util.HashMap;
import java.util.Map;

import mobile.web.webxt.client.data.MyHttpProxy;
import mobile.web.webxt.client.data.MyProcessConfig;
import mobile.web.webxt.client.form.validations.Validate;
import mobile.web.webxt.client.form.widgets.ComboForm;
import mobile.web.webxt.client.form.widgets.InputBox;
import mobile.web.webxt.client.form.widgets.MyTextArea;
import mobile.web.webxt.client.form.widgetsgrid.ArrayColumnData;
import mobile.web.webxt.client.form.widgetsgrid.MyColumnData;
import mobile.web.webxt.client.mvc.AppEvents;
import mobile.web.webxt.client.windows.AlertDialog;

import com.extjs.gxt.ui.client.Style.HorizontalAlignment;
import com.extjs.gxt.ui.client.Style.Orientation;
import com.extjs.gxt.ui.client.binding.FieldBinding;
import com.extjs.gxt.ui.client.binding.FormBinding;
import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.mvc.Dispatcher;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.Html;
import com.extjs.gxt.ui.client.widget.Label;
import com.extjs.gxt.ui.client.widget.LayoutContainer;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.form.FormPanel;
import com.extjs.gxt.ui.client.widget.form.FormPanel.LabelAlign;
import com.extjs.gxt.ui.client.widget.layout.CenterLayout;
import com.extjs.gxt.ui.client.widget.layout.FormLayout;
import com.extjs.gxt.ui.client.widget.layout.RowData;
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
	
	//Partner Fields
	InputBox isNew,codigo,asessorAux,freqAux,diaReunion, personAux;
	MyTextArea activity;
	ComboForm asessorCombo,freqCombo,personCombo;
	
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
		panelPrincipal.setLayout(new RowLayout(Orientation.HORIZONTAL)); 
		
		panelPrincipal.add(createPartnerForm(), new RowData(.4, 1));
		panelPrincipal.add(createPersonForm(), new RowData(.55, 1));
		
		
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
	
	private FormPanel createPersonForm(){
		  
		formPerson.setFrame(false);
		formPerson.setHeaderVisible(false);
		formPerson.setWidth(50);
				
			
		final ArrayColumnData cdataPerson = new ArrayColumnData();
		cdataPerson.add(new MyColumnData("pk_personId", "Persona", 150, 20,false));
		cdataPerson.add(new MyColumnData("name", "Nombre", 150, 20,false));
		cdataPerson.add(new MyColumnData("lastName", "Apellido", 150, 20,false));
		personCombo = new ComboForm("Persona","pk_personId");
		personCombo.setRqData("Person", cdataPerson);
		personCombo.setAllowBlank(false);
		
		personAux = new InputBox("", "Partner:personId:1", 50,100,Validate.ALFANUMERICO);
		personAux.setVisible(false);
		
			
		identificacion= new InputBox("Identificacion","",20,11,Validate.NUMERICO);
		identificacion.setName("identificacion");
		nombre = new InputBox("Nombre","",20,40,Validate.ALFANUMERICO);
		nombre.setName("nombre");
		apellido= new InputBox("Apellido","",40,11,Validate.ALFANUMERICO);
		apellido.setName("apellido");
		edad= new InputBox("Edad","",20,5,Validate.NUMERICO);
		edad.setName("edad");
		sexo= new InputBox("Sexo","",20,2,Validate.ALFANUMERICO);
		sexo.setName("sexo");
		ecivil= new InputBox("Estado Civil","",20,20,Validate.ALFANUMERICO);
		ecivil.setName("ecivil");
		
		formPerson.add(new Html(
				"<div style='font-size: 12px; width: 300px'><center>INFORMACION CLIENTE</center></span>"));
		formPerson.add(new Label());
		formPerson.add(personCombo);
		formPerson.add(personAux);
		formPerson.add(identificacion);
		formPerson.add(nombre);
		formPerson.add(apellido);
		formPerson.add(edad);
		formPerson.add(sexo);
		formPerson.add(ecivil);
		
		identificacion.setEnabled(false);
		nombre.setEnabled(false);
		apellido.setEnabled(false);
		edad.setEnabled(false);
		sexo.setEnabled(false);
		ecivil.setEnabled(false);
		
		FormBinding binding = new FormBinding(formPerson);  
	    // manually add bindings  
	    binding.addFieldBinding(new FieldBinding(identificacion, "identificacion"));  
	    binding.addFieldBinding(new FieldBinding(nombre, "nombre"));
	    binding.addFieldBinding(new FieldBinding(apellido, "apellido"));
	    binding.addFieldBinding(new FieldBinding(edad, "edad"));
	    binding.addFieldBinding(new FieldBinding(sexo, "sexo"));
	    binding.addFieldBinding(new FieldBinding(ecivil, "ecivil"));
	    //binding.addFieldBinding(new SimpleComboBoxFieldBinding(scb, "name"));  
	    
	    
	    System.out.println("Models: "+personCombo.getStore().getModels());
	    System.out.println("Model: "+personCombo.getModel());
	    System.out.println("Models: "+personCombo.getStore().getCount());
	    //System.out.println("Models: "+personCombo.getStore().getModels());
//	    binding.autoBind();  
//	    binding.bind();
		
		
		return formPerson;
		
	}
	
	private FormPanel createPartnerForm(){
				
		FormLayout layoutForm = new FormLayout();
		layoutForm.setLabelAlign(LabelAlign.TOP);
		formPartner.setLayout(layoutForm);
		formPartner.setFrame(false);
		formPartner.setHeaderVisible(false);
		formPartner.setWidth(100);
		
		isNew = new InputBox("", "Partner:_isNew:1", 50,2,Validate.ALFANUMERICO);
		isNew.setVisible(false);
		isNew.setValue("1");
		codigo= new InputBox("Codigo","Partner:pk_partnerId:1",10,10,Validate.ALFANUMERICO);
		codigo.setAllowBlank(false);
		codigo.setMaxLength(10);
				
		activity=new MyTextArea("Actividad","Partner:activity:1",50,300);
		activity.setHeight(110);
				
		final ArrayColumnData cdataAsessor = new ArrayColumnData();
		cdataAsessor.add(new MyColumnData("pk_asessorId", "Asesor", 150, 20,false));
		asessorCombo = new ComboForm("Asesor","pk_asessorId");
		asessorCombo.setRqData("Asessor", cdataAsessor);
		asessorAux = new InputBox("", "Partner:asessorId:1", 50,100,Validate.ALFANUMERICO);
		asessorAux.setVisible(false);
		asessorCombo.setAllowBlank(false);
		
		final ArrayColumnData cdataFrequency = new ArrayColumnData();
		cdataFrequency.add(new MyColumnData("pk_meetingFrequencyId", "Codigo", 150, 20,false));
		cdataFrequency.add(new MyColumnData("frequency", "Frecuencia", 150, 20,false));
		freqCombo = new ComboForm("Frecuencia de Reunion","pk_meetingFrequencyId");
		freqCombo.setRqData("MeetingFrequency", cdataFrequency);
		freqAux = new InputBox("", "Partner:meetingFrequencyId:1", 50,100,Validate.ALFANUMERICO);
		freqAux.setVisible(false);
		
		diaReunion= new InputBox("Dia Reunion","Partner:meetingFrequencyId:1",20,10,Validate.NUMERICO);
		diaReunion.setAllowBlank(false);	
		
		formPartner.add(isNew);
		formPartner.add(codigo);
		formPartner.add(activity);
		formPartner.add(asessorCombo);
		formPartner.add(asessorAux);
		formPartner.add(freqCombo);
		formPartner.add(freqAux);
		formPartner.add(diaReunion);
						
		return formPartner;
	}
	
	private boolean validateForm(){
		boolean isOK=true;
			
		if (codigo.getValue() == null || asessorAux.getValue() == null
				|| personAux.getValue() == null) {
			isOK = false;
			return isOK;
		}

		if (codigo.getValue().isEmpty() || asessorAux.getValue().isEmpty()
				|| personAux.getValue().isEmpty()) {
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
		personAux.clear();
		
		asessorCombo.clear();
		freqCombo.clear();
		personCombo.clear();
		
		identificacion.clear();
		nombre.clear();
		apellido.clear();
		edad.clear();
		sexo.clear();
		ecivil.clear();
		
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
				mfield.put(diaReunion.getPersistentInfo(), diaReunion.getValue().toString());
			}
			
			System.out.println("MyForm.commitChanges");
			for (String key : mfield.keySet()) {
				System.out.println(key + ":" + mfield.get(key));
			}
			
			proxy.commitForm(config, mfield, callback);
			clearFields();
		}else{
			alertEmpty.show();
		}
	}

}