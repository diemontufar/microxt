package mobile.web.webxt.client.devform;

import mobile.common.tools.Format;
import mobile.web.webxt.client.form.MyFormPanel;
import mobile.web.webxt.client.form.MyGeneralForm;
import mobile.web.webxt.client.form.widgets.ComboForm;
import mobile.web.webxt.client.form.widgets.InputBox;
import mobile.web.webxt.client.form.widgets.MyLabel;
import mobile.web.webxt.client.form.widgets.MyNumberField;
import mobile.web.webxt.client.form.widgets.MyTextArea;
import mobile.web.webxt.client.form.widgets.RowContainer;
import mobile.web.webxt.client.form.widgetsgrid.ArrayColumnData;
import mobile.web.webxt.client.form.widgetsgrid.MyColumnData;
import mobile.web.webxt.client.form.widgetsgrid.MyColumnData.ColumnType;
import mobile.web.webxt.client.util.DatesManager;

import com.extjs.gxt.ui.client.Style.HorizontalAlignment;
import com.extjs.gxt.ui.client.data.ModelData;
import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.SelectionChangedEvent;
import com.extjs.gxt.ui.client.event.SelectionChangedListener;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.widget.TabItem;
import com.extjs.gxt.ui.client.widget.TabPanel;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.form.FieldSet;
import com.extjs.gxt.ui.client.widget.form.Radio;
import com.extjs.gxt.ui.client.widget.form.RadioGroup;
import com.extjs.gxt.ui.client.widget.layout.FlowLayout;
import com.extjs.gxt.ui.client.widget.layout.HBoxLayoutData;
import com.google.gwt.user.client.Element;

public class C301 extends MyGeneralForm {
	private final String PROCESS = "C301";

	@Override
	protected void onRender(Element parent, int index) {
		super.onRender(parent, index);
		createForm();
	}

	private void createForm() {
		// Constants
		final int FORM_WIDTH = 540;
		final int TAB_HEIGHT = 290;
		final int LABEL_WIDTH = 65;

		// Form panel
		final MyFormPanel form = new MyFormPanel(PROCESS, "Solicitud de Microcrédito", FORM_WIDTH);
		form.setLayout(new FlowLayout());

		// Header
		// Solicitude id
		RowContainer row = new RowContainer();
		MyLabel label = new MyLabel("Solicitud:", LABEL_WIDTH);
		row.add(label);

		final ComboForm solicitudeId = new ComboForm(100);
		solicitudeId.setPersistentInfo("Solicitude:pk_solicitudeId:1");
		solicitudeId.setDisplayField("pk_solicitudeId");
		final ArrayColumnData solCdata = new ArrayColumnData();
		solCdata.add(new MyColumnData("pk_solicitudeId", "Id", 100));
		solicitudeId.setRqData("Solicitude", solCdata);
		solicitudeId.addSelectionChangedListener(new SelectionChangedListener<ModelData>() {
			@Override
			public void selectionChanged(SelectionChangedEvent<ModelData> se) {
				form.queryForm();
			}
		});
		row.add(solicitudeId);

		form.setNewItem(false);

		// Generated account
		// label = new MyLabel("Cuenta:", LABEL_WIDTH);
		// row.add(label, new HBoxLayoutData(0, 10, 0, 30));
		//
		// InputBox cuenta = new InputBox(100, 20);
		// cuenta.setReadOnly(true);
		// row.add(cuenta);
		//
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

		final ComboForm clientId = new ComboForm(80);
		clientId.setPersistentInfo("Solicitude:partnerClientId	:1");
		clientId.setDisplayField("pk_partnerId");
		final ArrayColumnData combodata = new ArrayColumnData();
		combodata.add(new MyColumnData("pk_partnerId", "Id", 70));
		combodata.add(new MyColumnData("personId", "Nombre", 150));
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
		productCombo.setPersistentInfo("Solicitude:productId:1");
		final ArrayColumnData pcdata = new ArrayColumnData();
		pcdata.add(new MyColumnData("pk_productId", "Id", 50));
		pcdata.add(new MyColumnData("description", "Descripcion", 250));
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

//		productCombo.addSelectionChangedListener(new SelectionChangedListener<ModelData>() {
//			@Override
//			public void selectionChanged(SelectionChangedEvent<ModelData> se) {
//				ModelData selected = se.getSelectedItem();
//				productDescription.setValue(selected.get("description").toString());
//				productCurrency.setValue(selected.get("currencyId").toString());
//				minAmount.setValue(selected.get("minAmount").toString());
//				maxAmount.setValue(selected.get("maxAmount").toString());
//				minPeriod.setValue(selected.get("minPeriod").toString());
//				maxPeriod.setValue(selected.get("maxPeriod").toString());
//			}
//		});

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

		MyNumberField amount = new MyNumberField();
		amount.setWidth(80);
		amount.setPersistentInfo("Solicitude:amount:1");
		row.add(amount);

		label = new MyLabel("Tipo cuota:", LABEL_WIDTH);
		row.add(label, new HBoxLayoutData(0, 10, 0, 30));

		// Quota type combo
		final ComboForm quotaTypeCombo = new ComboForm(80, "pk_quotaTypeId");
		quotaTypeCombo.setPersistentInfo("Solicitude:quotaTypeId:1");
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

		MyNumberField period = new MyNumberField();
		period.setWidth(80);
		period.setPersistentInfo("Solicitude:term:1");
		period.setPropertyEditorType(Integer.class);
		row.add(period);

		label = new MyLabel("Nro cuotas:", LABEL_WIDTH);
		row.add(label, new HBoxLayoutData(0, 10, 0, 30));

		MyNumberField numberQuotas = new MyNumberField();
		numberQuotas.setWidth(80);
		numberQuotas.setPersistentInfo("Solicitude:numberQuotas	:1");
		period.setPropertyEditorType(Integer.class);
		row.add(numberQuotas);

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
		fundsDestinationCombo.setPersistentInfo("Solicitude:fundsDestinationId:1");
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
		description.setPersistentInfo("Solicitude:destinationDescription:1");
		row.add(description);

		fieldSet.add(row);
		loan.add(fieldSet);

		// Add div components
		tabPanel.add(basic);
		tabPanel.add(loan);

		form.add(tabPanel);

		// Hidden fields
		InputBox assessor = new InputBox();
		assessor.setVisible(false);
		assessor.setPersistentInfo("Solicitude:assessor:1");
		assessor.setValue("ADM");
		form.add(assessor);

		InputBox solicitudeDate = new InputBox();
		solicitudeDate.setVisible(false);
		solicitudeDate.setPersistentInfo("Solicitude:solicitudeDate:1");
		solicitudeDate.setValue(DatesManager.getStringCurrentDate(Format.DATE));
		form.add(solicitudeDate);

		InputBox expirationDate = new InputBox();
		expirationDate.setVisible(false);
		expirationDate.setPersistentInfo("Solicitude:expirationDate:1");
		expirationDate.setValue(DatesManager.getStringCurrentDate(Format.DATE));
		form.add(expirationDate);

		InputBox numberRenewal = new InputBox();
		numberRenewal.setVisible(false);
		numberRenewal.setPersistentInfo("Solicitude:numberRenewal:1");
		numberRenewal.setValue("0");
		form.add(numberRenewal);

		InputBox paymentFrequency = new InputBox();
		paymentFrequency.setVisible(false);
		paymentFrequency.setPersistentInfo("Solicitude:paymentFrequencyId:1");
		paymentFrequency.setValue("4");
		form.add(paymentFrequency);

		InputBox status = new InputBox();
		status.setVisible(false);
		status.setPersistentInfo("Solicitude:statusId:1");
		status.setValue("001");
		form.add(status);

		form.setButtonAlign(HorizontalAlignment.CENTER);
		form.addButton(new Button("Guardar", new SelectionListener<ButtonEvent>() {

			@Override
			public void componentSelected(ButtonEvent ce) {
				form.commitForm();
			}
		}));
		form.addButton(new Button("Resetear", new SelectionListener<ButtonEvent>() {

			@Override
			public void componentSelected(ButtonEvent ce) {
				//form.reset();
				form.clear();
			}
		}));

		add(form);
	}
}