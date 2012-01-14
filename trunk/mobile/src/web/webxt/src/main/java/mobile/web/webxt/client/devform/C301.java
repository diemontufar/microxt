package mobile.web.webxt.client.devform;

import mobile.web.webxt.client.form.MyFormPanel;
import mobile.web.webxt.client.form.widgets.ComboForm;
import mobile.web.webxt.client.form.widgets.MyLabel;
import mobile.web.webxt.client.form.widgets.MyTextArea;
import mobile.web.webxt.client.form.widgets.InputBox;
import mobile.web.webxt.client.form.widgets.RowContainer;
import mobile.web.webxt.client.form.widgetsgrid.ArrayColumnData;
import mobile.web.webxt.client.form.widgetsgrid.MyColumnData;
import mobile.web.webxt.client.form.widgetsgrid.MyColumnData.ColumnType;

import com.extjs.gxt.ui.client.Style.HorizontalAlignment;
import com.extjs.gxt.ui.client.data.ModelData;
import com.extjs.gxt.ui.client.event.SelectionChangedEvent;
import com.extjs.gxt.ui.client.event.SelectionChangedListener;
import com.extjs.gxt.ui.client.widget.LayoutContainer;
import com.extjs.gxt.ui.client.widget.TabItem;
import com.extjs.gxt.ui.client.widget.TabPanel;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.form.FieldSet;
import com.extjs.gxt.ui.client.widget.form.NumberField;
import com.extjs.gxt.ui.client.widget.form.Radio;
import com.extjs.gxt.ui.client.widget.form.RadioGroup;
import com.extjs.gxt.ui.client.widget.layout.CenterLayout;
import com.extjs.gxt.ui.client.widget.layout.FlowLayout;
import com.extjs.gxt.ui.client.widget.layout.HBoxLayoutData;
import com.google.gwt.user.client.Element;

public class C301 extends LayoutContainer {

	@Override
	protected void onRender(Element parent, int index) {
		super.onRender(parent, index);
		setLayout(new CenterLayout());
		getAriaSupport().setPresentation(true);

		createForm();
	}

	private void createForm() {
		// Constants
		final int FORM_WIDTH = 540;
		final int TAB_HEIGHT = 290;
		final int LABEL_WIDTH = 65;
		
		// Form panel
		MyFormPanel form = new MyFormPanel("Solicitud de Microcrédito", FORM_WIDTH);
		form.setLayout(new FlowLayout());

		// Header
		RowContainer row = new RowContainer();
		
		// Solicitude id
		MyLabel label = new MyLabel("Solicitud:", LABEL_WIDTH);
		row.add(label);
		
		final ComboForm solicitudeId = new ComboForm(100);
		solicitudeId.setDisplayField("pk_solicitudeId");
		solicitudeId.setEditable(true);
		final ArrayColumnData solCdata = new ArrayColumnData();
		solCdata.add(new MyColumnData("pk_solicitudeId", "Id", 100));
		solicitudeId.setRqData("Solicitude", solCdata);
		row.add(solicitudeId);
		
		// Generated account
		label = new MyLabel("Cuenta:", LABEL_WIDTH);
		row.add(label, new HBoxLayoutData(0, 10, 0, 30));
		
		InputBox cuenta = new InputBox(100, 20);
		cuenta.setReadOnly(true);
		row.add(cuenta);
		
		form.add(row);
		
		// Tab panel
		final TabPanel tabPanel = new TabPanel();
		tabPanel.setHeight(TAB_HEIGHT);
		
		// Basic tab
	    TabItem basic = new TabItem();
	    basic.setStyleAttribute("padding", "10px");  
	    basic.setText("Básica");  
	    basic.setLayout(new FlowLayout());
	    basic.setBorders(true);
		
		FieldSet fieldSet = new FieldSet();
		fieldSet.setHeading("Deudor");
		fieldSet.setCollapsible(true);

		row = new RowContainer();
		
		// Partner type
		label = new MyLabel("Tipo:", LABEL_WIDTH);
		row.add(label);
		
		Radio radio = new Radio();
		radio.setBoxLabel("Individual");
		radio.setValue(true);

		Radio radio2 = new Radio();
		radio2.setBoxLabel("Grupal");

		RadioGroup radioGroup = new RadioGroup();
		radioGroup.add(radio);
		radioGroup.add(radio2);
		
		row.add(radioGroup);   
		fieldSet.add(row);
		
		// Debtor
		row = new RowContainer();
		row.setAutoHeight(true);
		
		label = new MyLabel("Deudor:", LABEL_WIDTH);
		row.add(label);
		
//		InputBox clientId = new InputBox(80, 20);
//		clientId.setAllowBlank(false);
		final ComboForm clientId = new ComboForm(80);
		clientId.setDisplayField("pk_partnerId");
		final ArrayColumnData combodata = new ArrayColumnData();
		combodata.add(new MyColumnData("pk_partnerId", "Id", 70));
		combodata.add(new MyColumnData("name", "Nombre", 150));
		clientId.setRqData("Partner", combodata);
		row.add(clientId);
		
		InputBox clientDescription = new InputBox(280);
		clientDescription.setReadOnly(true);
		row.add(clientDescription);
		
		fieldSet.add(row);

		basic.add(fieldSet);

		// Product data
		fieldSet = new FieldSet();
		fieldSet.setHeading("Datos del Producto");
		fieldSet.setCollapsible(true);
		fieldSet.setLayout(new FlowLayout());

		// Product
		row = new RowContainer();
		
		label = new MyLabel("Producto:", LABEL_WIDTH);
		row.add(label);
		
		// Product combo
		final ComboForm productCombo = new ComboForm(80, "pk_productId");
		final ArrayColumnData pcdata = new ArrayColumnData();
		pcdata.add(new MyColumnData("pk_productId", "Id", 70));
		pcdata.add(new MyColumnData("description", "Descripcion", 150));
		pcdata.add(new MyColumnData("currencyId", ColumnType.HIDDEN));
		pcdata.add(new MyColumnData("minAmount", ColumnType.HIDDEN));
		pcdata.add(new MyColumnData("maxAmount", ColumnType.HIDDEN));
		pcdata.add(new MyColumnData("minPeriod", ColumnType.HIDDEN));
		pcdata.add(new MyColumnData("maxPeriod", ColumnType.HIDDEN));
		pcdata.add(new MyColumnData("rate", ColumnType.HIDDEN));
		productCombo.setRqData("ProductMicrocredit", pcdata);
		row.add(productCombo);
		
		final InputBox productDescription = new InputBox(200);
		productDescription.setReadOnly(true);
		row.add(productDescription);

		final InputBox productCurrency = new InputBox(70);
		productCurrency.setReadOnly(true);
		row.add(productCurrency);

		fieldSet.add(row);

		row = new RowContainer();
		
		label = new MyLabel("Monto min:", LABEL_WIDTH);
		row.add(label);

		final InputBox minAmount = new InputBox(100);
		minAmount.setReadOnly(true);
		row.add(minAmount);

		label = new MyLabel("Plazo min:", LABEL_WIDTH);
		row.add(label, new HBoxLayoutData(0, 10, 0, 20));

		final InputBox minPeriod = new InputBox(100);
		minPeriod.setReadOnly(true);
		row.add(minPeriod);

		fieldSet.add(row);

		row = new RowContainer();
		row.setAutoHeight(true);
		
		label = new MyLabel("Monto max:", LABEL_WIDTH);
		row.add(label);

		final InputBox maxAmount = new InputBox(100);
		maxAmount.setReadOnly(true);
		row.add(maxAmount);

		label = new MyLabel("Plazo max:", LABEL_WIDTH);
		row.add(label, new HBoxLayoutData(0, 10, 0, 20));

		final InputBox maxPeriod = new InputBox(100);
		maxPeriod.setReadOnly(true);
		row.add(maxPeriod);

		productCombo.addSelectionChangedListener(new SelectionChangedListener<ModelData>() {
			@Override
			public void selectionChanged(SelectionChangedEvent<ModelData> se) {
				ModelData selected = se.getSelectedItem();
				productDescription.setValue(selected.get("description").toString());
				productCurrency.setValue(selected.get("currencyId").toString());
				minAmount.setValue(selected.get("minAmount").toString());
				maxAmount.setValue(selected.get("maxAmount").toString());
				minPeriod.setValue(selected.get("minPeriod").toString());
				maxPeriod.setValue(selected.get("maxPeriod").toString());
			}
		});
		
		fieldSet.add(row);

		basic.add(fieldSet);

		// Loan tab
		TabItem loan = new TabItem();
		loan.setStyleAttribute("padding", "10px");  
		loan.setText("Microcrédito");  
		loan.setLayout(new FlowLayout());
		loan.setBorders(true);

		fieldSet = new FieldSet();
		fieldSet.setHeading("Datos del Crédito");
		fieldSet.setCollapsible(true);
		fieldSet.setLayout(new FlowLayout());

		row = new RowContainer();
		
		label = new MyLabel("Monto:", LABEL_WIDTH);
		row.add(label);
		
		NumberField amount = new NumberField();
		amount.setWidth(80);
		row.add(amount);
		
		label = new MyLabel("Tipo cuota:", LABEL_WIDTH);
		row.add(label, new HBoxLayoutData(0, 10, 0, 30));
		
		// Quota type combo
		final ComboForm quotaTypeCombo = new ComboForm(80, "pk_quotaTypeId");
		final ArrayColumnData qtCdata = new ArrayColumnData();
		qtCdata.add(new MyColumnData("pk_quotaTypeId", "Id", 50));
		qtCdata.add(new MyColumnData("description", "Descripcion", 200));
		quotaTypeCombo.setRqData("QuotaType", qtCdata);
		row.add(quotaTypeCombo);
		
		fieldSet.add(row);
		
		row = new RowContainer();
		row.setAutoHeight(true);
		
		label = new MyLabel("Plazo:", LABEL_WIDTH);
		row.add(label);
		
		NumberField period = new NumberField();
		period.setWidth(80);
		period.setPropertyEditorType(Integer.class);
		row.add(period);
		
		label = new MyLabel("Nro cuotas:", LABEL_WIDTH);
		row.add(label, new HBoxLayoutData(0, 10, 0, 30));
		
		InputBox input = new InputBox(80);
		row.add(input);
		
		fieldSet.add(row);

		loan.add(fieldSet);
		
		
		// Destination of funds
		fieldSet = new FieldSet();
		fieldSet.setHeading("Destino de los fondos");
		fieldSet.setCollapsible(true);
		fieldSet.setLayout(new FlowLayout());

		row = new RowContainer();
		
		label = new MyLabel("Destino:", LABEL_WIDTH);
		row.add(label);
		
		// Funds destination combo
		final ComboForm fundsDestinationCombo = new ComboForm(80, "pk_fundsDestinationId");
		final ArrayColumnData fdCdata = new ArrayColumnData();
		fdCdata.add(new MyColumnData("pk_fundsDestinationId", "Id", 50));
		fdCdata.add(new MyColumnData("description", "Descripcion", 200));
		fundsDestinationCombo.setRqData("FundsDestination", fdCdata);
		row.add(fundsDestinationCombo);
		
		fieldSet.add(row);
		
		row = new RowContainer();
		row.setHeight(46);
		
		label = new MyLabel("Descripcion:", LABEL_WIDTH);
		row.add(label);
		
		MyTextArea description = new MyTextArea(375, 44, 250);
		row.add(description);
		
		fieldSet.add(row);
		loan.add(fieldSet);
		
		// Add div components
		tabPanel.add(basic);
		tabPanel.add(loan);
		
		form.add(tabPanel);
		
		form.setButtonAlign(HorizontalAlignment.CENTER);
		form.addButton(new Button("Guardar"));

		add(form);
	}
}