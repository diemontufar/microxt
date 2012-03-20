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

import com.extjs.gxt.ui.client.Style.HorizontalAlignment;
import com.extjs.gxt.ui.client.Style.Orientation;
import com.extjs.gxt.ui.client.data.BaseStringFilterConfig;
import com.extjs.gxt.ui.client.data.FilterConfig;
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
import com.extjs.gxt.ui.client.widget.layout.FlowLayout;
import com.extjs.gxt.ui.client.widget.layout.RowData;
import com.extjs.gxt.ui.client.widget.layout.RowLayout;
import com.google.gwt.user.client.Element;

public class C201 extends MyGeneralForm {

	private final static String PROCESS = "C201";
	private final static String ENTITY = "Partner";
	
	// Constants
	final int FORM_WIDTH = 620;
	final int TAB_HEIGHT = 180;
	final int LABEL_WIDTH = 60;

	MyFormPanel form;
	
	RowContainer row;
	MyLabel label;
	
	//Partner Fields
	InputBox freqDescription;
	MyTextArea activity;
	ComboForm partnerCode,asessorCombo,freqCombo,personCombo;
	MyNumberField meetingDay;
	
	//Person Fields:
	InputBox identification,name,lastName,gender,civilStatus; 
	ContentPanel principalPanel = new ContentPanel();
	
	Button save,clear;
	
	public C201() {
		super(PROCESS);
		setReference(new Reference("par", ENTITY));
	}

	@Override
	protected void onRender(Element parent, int index) {
		super.onRender(parent, index);
		createPanel();
	}
	
	private void createPanel(){
		
		LayoutContainer left = new LayoutContainer();
		left.setWidth(280);
		left.setBorders(false);

		LayoutContainer right = new LayoutContainer();
		right.setWidth(280);
		right.setBorders(false);
		
		principalPanel.setFrame(false);
		principalPanel.setHeaderVisible(false);
		principalPanel.setSize(FORM_WIDTH, TAB_HEIGHT);
		principalPanel.setLayout(new RowLayout(Orientation.HORIZONTAL));
				
					
		// Form panel
		final MyFormPanel form = new MyFormPanel(this, "Clientes Individuales", FORM_WIDTH);
		form.setLayout(new FlowLayout());		
		
		left.add(createPartnerForm());
		right.add(createPersonForm());
		
		principalPanel.add(left, new RowData(.45, 1, new Margins(4)));
		principalPanel.add(right, new RowData(.45, 1, new Margins(4)));
		
		FieldSet fieldSetActivity = new FieldSet();
		fieldSetActivity.setHeading("Actividad");
		fieldSetActivity.setCollapsible(false);
		fieldSetActivity.setWidth(555);
		fieldSetActivity.setHeight(140);
		
		row = new RowContainer();
		row.setHeight(320);
		activity=new MyTextArea(520,300);
		activity.setDataSource(new DataSource("par", "activity", DataSourceType.RECORD));
		activity.setHeight(100);
		activity.setEmptyText("Describa las actividades a las que se dedica el cliente");
		row.add(activity);
		fieldSetActivity.add(row);
		
		form.add(principalPanel);
		
		save = new Button("Guardar", new SelectionListener<ButtonEvent>() {
			@Override
			public void componentSelected(ButtonEvent ce) {
					form.commitForm();
			}
		});
		
		clear = new Button("Limpiar", new SelectionListener<ButtonEvent>() {
			@Override
			public void componentSelected(ButtonEvent ce) {
				form.clear();
			}
		});
		
		form.addButton(save);
		form.addButton(clear);
		form.setButtonAlign(HorizontalAlignment.CENTER);
		form.add(fieldSetActivity);
		add(form);
		
		partnerCode.addSelectionChangedListener(new SelectionChangedListener<ModelData>() {
			@Override
			public void selectionChanged(SelectionChangedEvent<ModelData> se) {
				if (((ComboForm) se.getSource()).isSomeSelected()) {
					form.queryForm();
				}
			}
		});
		
	}
	
	private FieldSet createPersonForm(){
		  
		FieldSet fieldSet = new FieldSet();
		fieldSet.setHeading("Datos Personales");
		fieldSet.setCollapsible(false);
		fieldSet.setWidth(270);
		
		row = new RowContainer();
		label = new MyLabel("Identificación:", LABEL_WIDTH+10);
		row.add(label);								
		identification= new InputBox(150,11,Validate.ALPHANUMERIC);
		row.add(identification);
		fieldSet.add(row);
		
		row = new RowContainer();
		label = new MyLabel("Nombres:", LABEL_WIDTH+10);
		row.add(label);	
		name = new InputBox(150,150,Validate.TEXT);
		row.add(name);
		fieldSet.add(row);
		
		row = new RowContainer();
		label = new MyLabel("Apellido:", LABEL_WIDTH+10);
		row.add(label);	
		lastName= new InputBox(150,40,Validate.TEXT);
		
		row.add(lastName);
		fieldSet.add(row);
		
		row = new RowContainer();
		label = new MyLabel("Género:", LABEL_WIDTH+10);
		row.add(label);	
		gender= new InputBox(100,2,Validate.TEXT);
		row.add(gender);
		fieldSet.add(row);
		
		row = new RowContainer();
		label = new MyLabel("Estado Civil:", LABEL_WIDTH+10);
		row.add(label);	
		civilStatus= new InputBox(100,20,Validate.TEXT);
		
		row.add(civilStatus);
		fieldSet.add(row);
		
		identification.setReadOnly(true);
		name.setReadOnly(true);
		lastName.setReadOnly(true);
		gender.setReadOnly(true);
		civilStatus.setReadOnly(true);
		
		identification.setDataSource(new DataSource("Person", "identificationNumber", DataSourceType.DESCRIPTION));
		name.setDataSource(new DataSource("Person", "name", DataSourceType.DESCRIPTION));
		lastName.setDataSource(new DataSource("Person", "lastName", DataSourceType.DESCRIPTION));
		gender.setDataSource(new DataSource("Person", "genderTypeId", DataSourceType.DESCRIPTION));
		civilStatus.setDataSource(new DataSource("Person", "civilStatusId", DataSourceType.DESCRIPTION));
					
		personCombo.linkWithField(identification, "identificationNumber");
		personCombo.linkWithField(name, "name");
		personCombo.linkWithField(lastName, "lastName");
		personCombo.linkWithField(gender, "genderTypeId");
		personCombo.linkWithField(civilStatus, "civilStatusId");
						
		return fieldSet;
	}
	
	private FieldSet createPartnerForm(){
				
		FieldSet fieldSet = new FieldSet();
		fieldSet.setHeading("Cliente");
		fieldSet.setCollapsible(false);
		fieldSet.setWidth(270);
		
		row = new RowContainer();
		label = new MyLabel("Codigo:", LABEL_WIDTH);
		row.add(label);
		
		partnerCode = new ComboForm(70);
		partnerCode.setDataSource(new DataSource("par", "pk_partnerId", DataSourceType.CRITERION));
		
		Reference refPartner = new Reference("par1", "Partner");
		final ArrayColumnData perCdata = new ArrayColumnData();
		perCdata.add(new MyColumnData("par1", "pk_partnerId", "Id", 100));
		partnerCode.setQueryData(refPartner, perCdata);
		partnerCode.setDisplayField("pk_partnerId");
				
		row.add(partnerCode);
		fieldSet.add(row);
		
		
		// GeneratedId
		final InputBox generatedId = new InputBox();
		generatedId.setDataSource(new DataSource(Item.GENERATED_ID, DataSourceType.CONTROL));
		generatedId.setVisible(false);
		generatedId.setFireChangeEventOnSetValue(true);
		generatedId.addListener(Events.Change, new Listener<FieldEvent>() {
			public void handleEvent(FieldEvent e) {
				if (e.getValue() != null) {
					partnerCode.setRawValue((String) e.getValue());
					partnerCode.setLoaded(false);
				}
			}
		});
		row.add(generatedId);
		fieldSet.add(row);
				
		row = new RowContainer();
		label = new MyLabel("Persona:", LABEL_WIDTH);
		row.add(label);
		
		
		// Person combo
		personCombo = new ComboForm(100);
		personCombo.setDataSource(new DataSource("par", "personId", DataSourceType.RECORD));
		personCombo.setPageSize(10);
		personCombo.setAllowBlank(false);
		personCombo.setEditable(true);

		Reference refPerson = new Reference("per", "Person");
		final ArrayColumnData cdataPerson = new ArrayColumnData();
		cdataPerson.add(new MyColumnData("per","pk_personId", "Persona", 50));
		cdataPerson.add(new MyColumnData("per","identificationNumber", "Identificacion", 80));
		cdataPerson.add(new MyColumnData("per","name", "Nombre", 100));
		cdataPerson.add(new MyColumnData("per","lastName", "Apellido", 200));
		cdataPerson.add(new MyColumnData("per","dateOfBirth", false));
		cdataPerson.add(new MyColumnData("per","genderTypeId", false));
		cdataPerson.add(new MyColumnData("per","civilStatusId", false));
		personCombo.setQueryData(refPerson, cdataPerson);
		personCombo.setDisplayField("pk_personId");
		personCombo.setFilteredField("identificationNumber");
		row.add(personCombo);

		row.add(personCombo);
		fieldSet.add(row);
		
					
		row = new RowContainer();
		label = new MyLabel("Asesor:", LABEL_WIDTH);
		row.add(label);			
				
		asessorCombo = new ComboForm(100);
		asessorCombo.setDataSource(new DataSource("par", "userId", DataSourceType.RECORD));
		asessorCombo.setAllowBlank(false);
		asessorCombo.setEditable(true);

		Reference refUserAcco = new Reference("usa", "UserAccount");
		final ArrayColumnData uadata = new ArrayColumnData();
		uadata.add(new MyColumnData("usa", "pk_userId", "Id", 70));
		uadata.add(new MyColumnData("usa", "name", "Nombre", 150));
		asessorCombo.setQueryData(refUserAcco, uadata);
		asessorCombo.setDisplayField("pk_userId");
		asessorCombo.setFilteredField("pk_userId");
		row.add(asessorCombo);
		
		String filterField = "userTypeId";

		FilterConfig filter = new BaseStringFilterConfig();
		filter.setField(filterField);
		filter.setComparison("=");
		filter.setValue("ASE");

		asessorCombo.addFilter(filter);
		asessorCombo.setLoaded(false);
		
		asessorCombo.setAllowBlank(false);
		
		row.add(asessorCombo);
		fieldSet.add(row);
		
		row = new RowContainer();
		label = new MyLabel("Frecuencia:", LABEL_WIDTH);
		row.add(label);
				
		freqCombo = new ComboForm(70);
		freqCombo.setDataSource(new DataSource("par", "frequencyId", DataSourceType.RECORD));

		Reference refFrec = new Reference("fre", "Frequency");
		final ArrayColumnData frdata = new ArrayColumnData();
		frdata.add(new MyColumnData("fre", "pk_frequencyId", "Id", 40));
		frdata.add(new MyColumnData("fre", "description", "Descripción", 120));
		freqCombo.setQueryData(refFrec, frdata);
		freqCombo.setDisplayField("pk_frequencyId");
		row.add(freqCombo);
		
		freqDescription = new InputBox(90);
		freqDescription.setReadOnly(true);
		freqDescription.setDataSource(new DataSource("Frequency", "description", DataSourceType.DESCRIPTION));
		row.add(freqDescription);
		
		freqCombo.linkWithField(freqDescription, "description");
		
		
		row.add(freqCombo);
		row.add(freqDescription);
		fieldSet.add(row);
		
		row = new RowContainer();
		label = new MyLabel("Día reunión:", LABEL_WIDTH);
		row.add(label);
		
		meetingDay = new MyNumberField(70);
		meetingDay.setDataSource(new DataSource("par", "meetingDay", DataSourceType.RECORD));
		meetingDay.setMaxValue(7);
		meetingDay.setMinValue(1);
		meetingDay.setToolTip("Día de la semana entre 1-7");
		meetingDay.setMaxLength(1);
				
		row.add(meetingDay);
		fieldSet.add(row);
		
		row = new RowContainer();
		label = new MyLabel("Actividad:", LABEL_WIDTH);
		row.add(label);
		
		return fieldSet;
	}
	
}