package mobile.web.webxt.client.devform;

import java.util.Date;

import mobile.common.tools.Format;
import mobile.web.webxt.client.data.form.DataSource;
import mobile.web.webxt.client.data.form.DataSourceType;
import mobile.web.webxt.client.data.form.Reference;
import mobile.web.webxt.client.form.MyFormPanel;
import mobile.web.webxt.client.form.MyGeneralForm;
import mobile.web.webxt.client.form.validations.Validate;
import mobile.web.webxt.client.form.widgets.InputBox;
import mobile.web.webxt.client.form.widgets.MyDateField;
import mobile.web.webxt.client.form.widgets.MyLabel;
import mobile.web.webxt.client.form.widgets.RowContainer;
import mobile.web.webxt.client.util.DatesManager;

import com.extjs.gxt.ui.client.event.Events;
import com.extjs.gxt.ui.client.event.FieldEvent;
import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.LayoutContainer;
import com.extjs.gxt.ui.client.widget.form.FieldSet;
import com.extjs.gxt.ui.client.widget.layout.CenterLayout;
import com.google.gwt.user.client.Element;

public class ObtainPersonInformation extends LayoutContainer {
	
	String personId;
	RowContainer row;
	MyLabel label;
	InputBox personCode;
	String name,identification, age, civilStatus, profession;
	Date birth;
	
	MyFormPanel formPerson;
	MyGeneralForm formContainerPerson;
	
	private final static String PROCESS_PERSON = "B101";
	private final static String ENTITY_PERSON = "Person";
	
	
	final int LABEL_WIDTH = 40;
	final String FORM_WIDTH = "300";
	
	public ObtainPersonInformation(String personId){
		this.personId=personId;
	}
	
	public ObtainPersonInformation(){
	}
	
	@Override  
	  protected void onRender(Element parent, int pos) {  
	    super.onRender(parent, pos); 
	    setLayout(new CenterLayout());
	    
	    formContainerPerson = new MyGeneralForm(PROCESS_PERSON);
	    formContainerPerson.setReference(new Reference("per", ENTITY_PERSON));
	    formContainerPerson.setWidth(300);
	    formPerson = new MyFormPanel(formContainerPerson, "Datos Persona", FORM_WIDTH);
	    formPerson.setHeaderVisible(false);
	    formPerson.setFrame(false);
	    formPerson.setBorders(false);
	    formPerson.setWidth(300);
	    formPerson.setHeight(300);
	    
	    ContentPanel panel = new ContentPanel(); 
	    panel = new ContentPanel();  
	    panel.setHeaderVisible(false);
	    formPerson.add(createPersonForm());
	    panel.add(formPerson);
	    
	    add(panel);
	}

	public FieldSet createPersonForm(){
		
		FieldSet fieldSet = new FieldSet();
		fieldSet.setHeading("Datos de la Persona");
		fieldSet.setCollapsible(false);
		fieldSet.setWidth(250);
		fieldSet.setHeight(250);
		
		//Code
		row = new RowContainer();
		label = new MyLabel("Codigo:", LABEL_WIDTH);
		row.add(label);
				
		personCode = new InputBox(90, 20, Validate.TEXT);
		personCode.setDataSource(new DataSource("per", "pk_personId", DataSourceType.CRITERION));
		personCode.setReadOnly(true);
		personCode.setFireChangeEventOnSetValue(true);
				
		row.add(personCode);
		fieldSet.add(row);
		
		//Name
		row = new RowContainer();
		final InputBox name = new InputBox(90, 20, Validate.TEXT);
		name.setReadOnly(true);
		name.setFireChangeEventOnSetValue(true);
		name.setDataSource(new DataSource("per", "name", DataSourceType.RECORD));
		row.add(name);
		fieldSet.add(row);
		
		//Last Name
		row = new RowContainer();
		final InputBox lastName = new InputBox(90, 20, Validate.TEXT);
		lastName.setReadOnly(true);
		lastName.setFireChangeEventOnSetValue(true);
		lastName.setDataSource(new DataSource("per", "lastName", DataSourceType.RECORD));
		row.add(lastName);
		fieldSet.add(row);
		
		//Second Last Name
		row = new RowContainer();
		final InputBox lastName2 = new InputBox(90, 20, Validate.TEXT);
		lastName2.setReadOnly(true);
		lastName2.setFireChangeEventOnSetValue(true);
		lastName2.setDataSource(new DataSource("per", "secondLastName", DataSourceType.RECORD));
		
		lastName2.addListener(Events.Change, new Listener<FieldEvent>() {
			public void handleEvent(FieldEvent e) {
				if (e.getValue() != null) {
					String personName;
					personName=lastName.getValue()+ " " + lastName2.getValue()+ " "+name.getValue();
					setPersonName(personName);
					System.out.println("El nombre de la persona es: "+ getPersonName() );
				}
			}
		});
		row.add(lastName2);
		fieldSet.add(row);
		
		//Identidfication
		row = new RowContainer();
		final InputBox identification = new InputBox(90, 40, Validate.TEXT);
		identification.setReadOnly(true);
		identification.setFireChangeEventOnSetValue(true);
		identification.setDataSource(new DataSource("per", "identificationNumber", DataSourceType.RECORD));
		
		identification.addListener(Events.Change, new Listener<FieldEvent>() {
			public void handleEvent(FieldEvent e) {
				if (e.getValue() != null) {
					String id;
					id=identification.getValue();
					setIdentification(id);
					System.out.println("La identificacion de la persona es: "+ getIdentification() );
				}
			}
		});
		row.add(identification);
		fieldSet.add(row);
		
		//Age
		row = new RowContainer();
		final InputBox age = new InputBox(90, 40, Validate.TEXT);
		age.setReadOnly(true);
		age.setFireChangeEventOnSetValue(true);
		
		row.add(age);
		fieldSet.add(row);
		
		//Birth Date
		row = new RowContainer();
		final MyDateField birth = new MyDateField();
		birth.setReadOnly(true);
		birth.setFireChangeEventOnSetValue(true);
		birth.setDataSource(new DataSource("per", "dateOfBirth", DataSourceType.RECORD));
		
		birth.addListener(Events.Change, new Listener<FieldEvent>() {
			public void handleEvent(FieldEvent e) {
				if (e.getValue() != null) {
					Date doBirth;
					doBirth=birth.getValue();
					setBirth(doBirth);
					System.out.println("La Fecha de nacimiento de la persona es: "+ getBirth());
					
					//Set Age VAlue
					int age1=0;
					String date=DatesManager.dateToString(birth.getValue(), Format.DATE);
					age1=DatesManager.calculateAge(date);
					age.setValue(String.valueOf(age1));
					setAge(String.valueOf(age1));
					
					System.out.println("La Edad de la persona es: "+ getAge());
				}
			}
		});
		row.add(birth);
		fieldSet.add(row);
		
		//Profession
		row = new RowContainer();
		final InputBox profession = new InputBox(90, 40, Validate.TEXT);
		profession.setReadOnly(true);
		profession.setFireChangeEventOnSetValue(true);
		profession.setDataSource(new DataSource("per", "professionTypeId", DataSourceType.RECORD));
		
		profession.addListener(Events.Change, new Listener<FieldEvent>() {
			public void handleEvent(FieldEvent e) {
				if (e.getValue() != null) {
					String prof;
					prof=profession.getValue();
					setProfession(prof);
					System.out.println("La profesion de la persona es: "+ getProfession() );
				}
			}
		});
		row.add(profession);
		fieldSet.add(row);
		
		personCode.addListener(Events.Change, new Listener<FieldEvent>() {
			public void handleEvent(FieldEvent e) {
				if (e.getValue() != null) {
					formPerson.queryForm();
				}
			}
		});
		
		return fieldSet;
		
	}
	
	public String getPersonName() {
		return name;
	}

	public String getIdentification() {
		return identification;
	}

	public void setIdentification(String identification) {
		this.identification = identification;
	}

	public Date getBirth() {
		return birth;
	}

	public void setBirth(Date birth) {
		this.birth = birth;
	}

	public String getAge() {
		return age;
	}

	public void setAge(String age) {
		this.age = age;
	}

	public String getCivilStatus() {
		return civilStatus;
	}

	public void setCivilStatus(String civilStatus) {
		this.civilStatus = civilStatus;
	}

	public void setPersonName(String name) {
		this.name = name;
	}
	
	public String getProfession() {
		return profession;
	}

	public void setProfession(String profession) {
		this.profession = profession;
	}
	
	public String getPersonId() {
		return personId;
	}

	public void setPersonId(String personId) {
		this.personId = personId;
		personCode.setValue(this.personId);
	}
	
}
