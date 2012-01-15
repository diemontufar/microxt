package mobile.web.webxt.client.devform;

import mobile.web.webxt.client.form.MyFormPanel;
import mobile.web.webxt.client.form.MyGeneralForm;
import mobile.web.webxt.client.form.widgets.ComboForm;
import mobile.web.webxt.client.form.widgets.InputBox;
import mobile.web.webxt.client.form.widgets.MyTextArea;
import mobile.web.webxt.client.form.widgetsgrid.ArrayColumnData;
import mobile.web.webxt.client.form.widgetsgrid.MyColumnData;

import com.extjs.gxt.ui.client.Style.HorizontalAlignment;
import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.form.FieldSet;
import com.extjs.gxt.ui.client.widget.layout.FormLayout;
import com.google.gwt.user.client.Element;

public class C302 extends MyGeneralForm {
	private final String PROCESS = "C302";

	@Override
	protected void onRender(Element parent, int index) {
		super.onRender(parent, index);
		createForm();
	}

	private void createForm() {
		// Constants
		final int FORM_WIDTH = 380;
		
		// Form panel
		MyFormPanel form = new MyFormPanel(PROCESS, "Recomendacion de Microcrétido", FORM_WIDTH);
		form.setHeight(460);
		form.setFieldWidth(100);

		// Header - solicitude combo
		final ComboForm solicitudeId = new ComboForm(100);
		solicitudeId.setFieldLabel("Solicitud");
		solicitudeId.setDisplayField("pk_solicitudeId");
		solicitudeId.setEditable(false);
		final ArrayColumnData solCdata = new ArrayColumnData();
		solCdata.add(new MyColumnData("pk_solicitudeId", "Id", 100));
		solicitudeId.setRqData("Solicitude", solCdata);
		form.add(solicitudeId);
		
		// Observations field-set
		FieldSet fieldSet = new FieldSet();
		fieldSet.setLayout(new FormLayout());
		fieldSet.setHeading("Observaciones");
		fieldSet.setCollapsible(true);

		// Documents
		final MyTextArea documents = new MyTextArea("Documentos", "", 150, 40, 500);
		fieldSet.add(documents);
		
		// Economic unit
		MyTextArea economicUnit = new MyTextArea("Unidad economica", "", 150,
				40, 500);
		fieldSet.add(economicUnit);
		
		// Family unit
		MyTextArea familyUnit = new MyTextArea("Unidad Familiar", "", 150,
				40, 500);
		fieldSet.add(familyUnit);
		
		// Payment morale
		MyTextArea paymentMorale = new MyTextArea("Moral de pago", "", 150, 40,
				500);
		fieldSet.add(paymentMorale);
		
		// Credit history
		MyTextArea creditHistory = new MyTextArea("Historial de credito", "",
				150, 40, 500);
		fieldSet.add(creditHistory);

		form.add(fieldSet);
		
		// Recommendation field-set
		fieldSet = new FieldSet();
		fieldSet.setLayout(new FormLayout());
		fieldSet.setHeading("Recomendacion");
		fieldSet.setCollapsible(true);				
		
		InputBox recommendation = new InputBox(100);
		recommendation.setFieldLabel("Recomendacion");
		fieldSet.add(recommendation);

		form.add(fieldSet);

		form.setButtonAlign(HorizontalAlignment.CENTER);
		
		form.addButton(new Button("Guardar",new SelectionListener<ButtonEvent>() {
			@Override
			public void componentSelected(ButtonEvent ce) {
			}
		}));

		add(form);
	}
}