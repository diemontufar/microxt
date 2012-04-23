package mobile.web.webxt.client.devform;

import mobile.web.webxt.client.data.form.DataSource;
import mobile.web.webxt.client.data.form.DataSourceType;
import mobile.web.webxt.client.data.form.Reference;
import mobile.web.webxt.client.form.MyFormPanel;
import mobile.web.webxt.client.form.MyGeneralForm;
import mobile.web.webxt.client.form.widgets.ComboForm;
import mobile.web.webxt.client.form.widgets.InputBox;
import mobile.web.webxt.client.form.widgets.MyTextArea;
import mobile.web.webxt.client.form.widgets.RowContainer;
import mobile.web.webxt.client.form.widgetsgrid.ArrayColumnData;
import mobile.web.webxt.client.form.widgetsgrid.MyColumnData;

import com.extjs.gxt.ui.client.Style.HorizontalAlignment;
import com.extjs.gxt.ui.client.data.ModelData;
import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.SelectionChangedEvent;
import com.extjs.gxt.ui.client.event.SelectionChangedListener;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.form.FieldSet;
import com.extjs.gxt.ui.client.widget.layout.FormLayout;
import com.google.gwt.user.client.Element;

public class C302 extends MyGeneralForm {
	private final static String PROCESS = "C302";
	private final static String ENTITY = "Recommendation";

	public C302() {
		super(PROCESS);
		setReference(new Reference("rec", ENTITY));
	}

	@Override
	protected void onRender(Element parent, int index) {
		super.onRender(parent, index);

		// Constants
		final int FORM_WIDTH = 450;
		final int LABEL_WIDTH = 100;
		final int WIDGET_WIDTH = 280;
		final int WIDGET_HEIGHT = 40;

		// Form panel
		final MyFormPanel form = new MyFormPanel(this, "Recomendación de Microcrédido", FORM_WIDTH);
		form.setHeight(460);

		// Header - solicitude combo
		RowContainer row = new RowContainer();
		FormLayout layout0 = new FormLayout();
		layout0.setLabelWidth(LABEL_WIDTH);
		row.setLayout(layout0);
		row.setStyleAttribute("margin-bottom", "10px");
		
		final ComboForm solicitudeId = new ComboForm(100);
		solicitudeId.setDataSource(new DataSource("rec", "pk_solicitudeId", DataSourceType.CRITERION));
		solicitudeId.setFieldLabel("Solicitud");
		solicitudeId.setAllowBlank(false);
		solicitudeId.setData("mobile-type", Integer.class);

		Reference refSolicitude = new Reference("sol1", "Solicitude");
		final ArrayColumnData solCdata = new ArrayColumnData();
		solCdata.add(new MyColumnData("sol1", "pk_solicitudeId", "Id", 100));
		solicitudeId.setQueryData(refSolicitude, solCdata);
		solicitudeId.setDisplayField("pk_solicitudeId");

		solicitudeId.addSelectionChangedListener(new SelectionChangedListener<ModelData>() {
			@Override
			public void selectionChanged(SelectionChangedEvent<ModelData> se) {
				if (se.getSelectedItem() != null && solicitudeId.isLoaded()) {
					form.queryForm();
				}
			}
		});

		row.add(solicitudeId);
		form.add(row);
		
		// Observations field-set
		FieldSet fieldSet = new FieldSet();
		FormLayout layout = new FormLayout();
		layout.setDefaultWidth(WIDGET_WIDTH);
		layout.setLabelWidth(LABEL_WIDTH);
		fieldSet.setLayout(layout);
		fieldSet.setHeading("Observaciones");

		// Documents
		final MyTextArea documents = new MyTextArea("Documentos");
		documents.setDataSource(new DataSource("rec", "documents"));
		documents.setHeight(WIDGET_HEIGHT);
		documents.setWidth(WIDGET_WIDTH);
		documents.setAllowBlank(false);
		documents.setMaxLength(500);
		fieldSet.add(documents);

		// Economic unit
		MyTextArea economicUnit = new MyTextArea("Unidad económica");
		economicUnit.setDataSource(new DataSource("rec", "economicUnit"));
		economicUnit.setHeight(WIDGET_HEIGHT);
		economicUnit.setWidth(WIDGET_WIDTH);
		economicUnit.setAllowBlank(false);
		economicUnit.setMaxLength(500);
		fieldSet.add(economicUnit);

		// Family unit
		MyTextArea familyUnit = new MyTextArea("Unidad Familiar");
		familyUnit.setDataSource(new DataSource("rec", "familyUnit"));
		familyUnit.setHeight(WIDGET_HEIGHT);
		familyUnit.setWidth(WIDGET_WIDTH);
		familyUnit.setAllowBlank(false);
		familyUnit.setMaxLength(500);
		fieldSet.add(familyUnit);

		// Payment morale
		MyTextArea paymentMorale = new MyTextArea("Moral de pago");
		paymentMorale.setDataSource(new DataSource("rec", "paymentMorale"));
		paymentMorale.setHeight(WIDGET_HEIGHT);
		paymentMorale.setWidth(WIDGET_WIDTH);
		paymentMorale.setAllowBlank(false);
		paymentMorale.setMaxLength(500);
		fieldSet.add(paymentMorale);

		// Credit history
		MyTextArea creditHistory = new MyTextArea("Historial de credito");
		creditHistory.setDataSource(new DataSource("rec", "creditHistory"));
		creditHistory.setHeight(WIDGET_HEIGHT);
		creditHistory.setWidth(WIDGET_WIDTH);
		creditHistory.setAllowBlank(false);
		creditHistory.setMaxLength(500);
		fieldSet.add(creditHistory);

		form.add(fieldSet);

		// Recommendation field-set
		fieldSet = new FieldSet();
		fieldSet.setLayout(new FormLayout());
		fieldSet.setHeading("Recomendacion");

		InputBox recommendation = new InputBox(100);
		recommendation.setDataSource(new DataSource("rec", "proposal"));
		recommendation.setFieldLabel("Recomendacion");
		recommendation.setAllowBlank(false);
		fieldSet.add(recommendation);

		form.add(fieldSet);

		form.setButtonAlign(HorizontalAlignment.CENTER);

		form.addButton(new Button("Guardar", new SelectionListener<ButtonEvent>() {
			@Override
			public void componentSelected(ButtonEvent ce) {
				form.commitForm();
			}
		}));

		add(form);
	}
}