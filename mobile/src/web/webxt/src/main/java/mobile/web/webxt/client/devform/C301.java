package mobile.web.webxt.client.devform;

import mobile.common.message.Item;
import mobile.web.webxt.client.data.form.DataSource;
import mobile.web.webxt.client.data.form.DataSourceType;
import mobile.web.webxt.client.data.form.Reference;
import mobile.web.webxt.client.form.MyFormPanel;
import mobile.web.webxt.client.form.MyGeneralForm;
import mobile.web.webxt.client.form.widgets.ComboForm;
import mobile.web.webxt.client.form.widgets.InputBox;
import mobile.web.webxt.client.form.widgets.MyDateField;
import mobile.web.webxt.client.form.widgets.MyLabel;
import mobile.web.webxt.client.form.widgets.MyNumberField;
import mobile.web.webxt.client.form.widgets.MyTextArea;
import mobile.web.webxt.client.form.widgets.RowContainer;
import mobile.web.webxt.client.form.widgetsgrid.ArrayColumnData;
import mobile.web.webxt.client.form.widgetsgrid.MyColumnData;
import mobile.web.webxt.client.util.DatesManager;

import com.extjs.gxt.ui.client.Style.HorizontalAlignment;
import com.extjs.gxt.ui.client.data.ModelData;
import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.Events;
import com.extjs.gxt.ui.client.event.FieldEvent;
import com.extjs.gxt.ui.client.event.Listener;
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
import com.google.gwt.i18n.client.NumberFormat;
import com.google.gwt.user.client.Element;

public class C301 extends MyGeneralForm {
	private final static String PROCESS = "C301";
	private final static String ENTITY = "Solicitude";

	public C301() {
		super(PROCESS);
		setReference(new Reference("sol", ENTITY));
	}

	@Override
	protected void onRender(Element parent, int index) {
		super.onRender(parent, index);

		// Constants
		final int FORM_WIDTH = 540;
		final int TAB_HEIGHT = 350;
		final int LABEL_WIDTH = 65;

		// Form panel
		final MyFormPanel form = new MyFormPanel(this, "Solicitud de Microcrédito", FORM_WIDTH);
		form.setLayout(new FlowLayout());

		// Header
		// Solicitude id
		RowContainer row = new RowContainer();
		row.setStyleAttribute("margin-bottom", "5px");
		MyLabel label = new MyLabel("Solicitud:", LABEL_WIDTH);
		row.add(label);

		final ComboForm solicitudeId = new ComboForm(100);
		solicitudeId.setId("solicitudeId");
		solicitudeId.setDataSource(new DataSource("sol", "pk_solicitudeId", DataSourceType.CRITERION));

		Reference refSolicitude = new Reference("sol1", "Solicitude");
		final ArrayColumnData solCdata = new ArrayColumnData();
		solCdata.add(new MyColumnData("sol1", "pk_solicitudeId", "Id", 100));
		solicitudeId.setQueryData(refSolicitude, solCdata);
		solicitudeId.setDisplayField("pk_solicitudeId");

		solicitudeId.addSelectionChangedListener(new SelectionChangedListener<ModelData>() {
			@Override
			public void selectionChanged(SelectionChangedEvent<ModelData> se) {
				if (((ComboForm) se.getSource()).isSomeSelected()) {
					form.queryForm();
				}
			}
		});
		row.add(solicitudeId);

		form.add(row);

		// GeneratedId
		final InputBox generatedId = new InputBox();
		generatedId.setId("generatedId");
		generatedId.setDataSource(new DataSource(Item.GENERATED_ID, DataSourceType.CONTROL));
		generatedId.setVisible(false);
		generatedId.setFireChangeEventOnSetValue(true);
		generatedId.addListener(Events.Change, new Listener<FieldEvent>() {
			public void handleEvent(FieldEvent e) {
				if (e.getValue() != null) {
					solicitudeId.setRawValue((String) e.getValue());
					solicitudeId.setLoaded(false);
				}
			}
		});
		form.add(generatedId);

		// Tab panel
		final TabPanel tabPanel = new TabPanel();
		tabPanel.setHeight(TAB_HEIGHT);
		// tabPanel.setDeferredRender(false);

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
		clientId.setDataSource(new DataSource("sol", "partnerClientId", DataSourceType.RECORD));

		Reference refPartner1 = new Reference("cli1", "Partner");
		final ArrayColumnData combodata = new ArrayColumnData();
		combodata.add(new MyColumnData("cli1", "pk_partnerId", "Id", 70));
		combodata.add(new MyColumnData("cli1", "personId", "Nombre", 150));
		clientId.setQueryData(refPartner1, combodata);
		clientId.setDisplayField("pk_partnerId");
		row.add(clientId);

		InputBox clientDescription = new InputBox(280);
		clientDescription.setReadOnly(true);
		row.add(clientDescription);

		fieldSet.add(row);

		basic.add(fieldSet);

		// Status FieldSet
		fieldSet = new FieldSet();
		fieldSet.setHeading("Estado");
		fieldSet.setCollapsible(true);
		fieldSet.setLayout(new FlowLayout());

		row = new RowContainer();

		label = new MyLabel("Estado:", LABEL_WIDTH);
		row.add(label);

		final InputBox estado = new InputBox(50);
		estado.setReadOnly(true);
		estado.setDataSource(new DataSource("sol", "statusId", DataSourceType.RECORD));
		// estado.setOriginalValue("001");
		estado.setValue("001");
		row.add(estado);

		final InputBox estadoDes = new InputBox(150);
		estadoDes.setReadOnly(true);
		estadoDes.setDataSource(new DataSource("SolicitudeStatus", "description", DataSourceType.DESCRIPTION));
		row.add(estadoDes);

		fieldSet.add(row);

		row = new RowContainer();
		row.setAutoHeight(true);

		label = new MyLabel("Asesor:", LABEL_WIDTH);
		row.add(label);

		final InputBox assessor = new InputBox(100);
		assessor.setReadOnly(true);
		assessor.setDataSource(new DataSource("sol", "assessor", DataSourceType.RECORD));
		assessor.setValue("ADM");
		row.add(assessor);

		label = new MyLabel("Fecha:", LABEL_WIDTH);
		row.add(label, new HBoxLayoutData(0, 10, 0, 20));

		final MyDateField solicitudDate = new MyDateField(100);
		solicitudDate.setReadOnly(true);
		solicitudDate.setValue(DatesManager.getCurrentDate());
		solicitudDate.setDataSource(new DataSource("sol", "solicitudeDate", DataSourceType.RECORD));
		row.add(solicitudDate);

		fieldSet.add(row);

		basic.add(fieldSet);

		// Product data FieldSet
		fieldSet = new FieldSet();
		fieldSet.setHeading("Datos del Producto");
		fieldSet.setCollapsible(true);
		fieldSet.setLayout(new FlowLayout());

		// Product
		row = new RowContainer();

		label = new MyLabel("Producto:", LABEL_WIDTH);
		row.add(label);

		// Product combo
		final ComboForm productCombo = new ComboForm(80);
		productCombo.setDataSource(new DataSource("sol", "productId", DataSourceType.RECORD));

		Reference refProduct = new Reference("pro", "ProductMicrocredit");
		final ArrayColumnData pcdata = new ArrayColumnData();
		pcdata.add(new MyColumnData("pro", "pk_productId", "Id", 50));
		pcdata.add(new MyColumnData("pro", "description", "Descripcion", 250));
		pcdata.add(new MyColumnData("pro", "currencyId", false));
		pcdata.add(new MyColumnData("pro", "minAmount", false));
		pcdata.add(new MyColumnData("pro", "maxAmount", false));
		pcdata.add(new MyColumnData("pro", "minPeriod", false));
		pcdata.add(new MyColumnData("pro", "maxPeriod", false));
		pcdata.add(new MyColumnData("pro", "rate", false));
		productCombo.setQueryData(refProduct, pcdata);
		productCombo.setDisplayField("pk_productId");
		row.add(productCombo);

		final InputBox productDescription = new InputBox(200);
		productDescription.setReadOnly(true);
		productDescription
				.setDataSource(new DataSource("ProductMicrocredit", "description", DataSourceType.DESCRIPTION));
		row.add(productDescription);

		final InputBox productCurrency = new InputBox(70);
		productCurrency.setReadOnly(true);
		productCurrency.setDataSource(new DataSource("ProductMicrocredit", "currencyId", DataSourceType.DESCRIPTION));
		row.add(productCurrency);

		fieldSet.add(row);

		row = new RowContainer();

		label = new MyLabel("Monto min:", LABEL_WIDTH);
		row.add(label);

		final InputBox minAmount = new InputBox(100);
		minAmount.setReadOnly(true);
		minAmount.setDataSource(new DataSource("ProductMicrocredit", "minAmount", DataSourceType.DESCRIPTION));
		row.add(minAmount);

		label = new MyLabel("Plazo min:", LABEL_WIDTH);
		row.add(label, new HBoxLayoutData(0, 10, 0, 20));

		final InputBox minPeriod = new InputBox(100);
		minPeriod.setReadOnly(true);
		minPeriod.setDataSource(new DataSource("ProductMicrocredit", "minPeriod", DataSourceType.DESCRIPTION));
		row.add(minPeriod);

		fieldSet.add(row);

		row = new RowContainer();
		row.setAutoHeight(true);

		label = new MyLabel("Monto max:", LABEL_WIDTH);
		row.add(label);

		final InputBox maxAmount = new InputBox(100);
		maxAmount.setReadOnly(true);
		maxAmount.setDataSource(new DataSource("ProductMicrocredit", "maxAmount", DataSourceType.DESCRIPTION));
		row.add(maxAmount);

		label = new MyLabel("Plazo max:", LABEL_WIDTH);
		row.add(label, new HBoxLayoutData(0, 10, 0, 20));

		final InputBox maxPeriod = new InputBox(100);
		maxPeriod.setReadOnly(true);
		maxPeriod.setDataSource(new DataSource("ProductMicrocredit", "maxPeriod", DataSourceType.DESCRIPTION));
		row.add(maxPeriod);

		productCombo.linkWithField(productDescription, "description");
		productCombo.linkWithField(productCurrency, "currencyId");
		productCombo.linkWithField(minAmount, "minAmount");
		productCombo.linkWithField(maxAmount, "maxAmount");
		productCombo.linkWithField(minPeriod, "minPeriod");
		productCombo.linkWithField(maxPeriod, "maxPeriod");

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
		amount.setAllowBlank(false);
		amount.setDataSource(new DataSource("sol", "amount", DataSourceType.RECORD));
		amount.setPropertyEditorType(Double.class);
		amount.setFormat(NumberFormat.getFormat("#,##0.00"));
		row.add(amount);

		label = new MyLabel("Tipo cuota:", LABEL_WIDTH);
		row.add(label, new HBoxLayoutData(0, 10, 0, 30));

		// Quota type combo
		final ComboForm quotaTypeCombo = new ComboForm(80);
		quotaTypeCombo.setDataSource(new DataSource("sol", "quotaTypeId", DataSourceType.RECORD));

		Reference refQuotaType = new Reference("qt", "QuotaType");
		final ArrayColumnData qtCdata = new ArrayColumnData();
		qtCdata.add(new MyColumnData("qt", "pk_quotaTypeId", "Id", 50));
		qtCdata.add(new MyColumnData("qt", "description", "Descripcion", 200));
		quotaTypeCombo.setQueryData(refQuotaType, qtCdata);
		quotaTypeCombo.setDisplayField("pk_quotaTypeId");
		row.add(quotaTypeCombo);

		fieldSet.add(row);

		row = new RowContainer();

		label = new MyLabel("Plazo:", LABEL_WIDTH);
		row.add(label);

		MyNumberField term = new MyNumberField();
		term.setWidth(80);
		term.setDataSource(new DataSource("sol", "term", DataSourceType.RECORD));
		term.setPropertyEditorType(Integer.class);
		row.add(term);

		label = new MyLabel("Nro cuotas:", LABEL_WIDTH);
		row.add(label, new HBoxLayoutData(0, 10, 0, 30));

		MyNumberField numberQuotas = new MyNumberField();
		numberQuotas.setWidth(80);
		numberQuotas.setDataSource(new DataSource("sol", "numberQuotas", DataSourceType.RECORD));
		numberQuotas.setPropertyEditorType(Integer.class);
		numberQuotas.setRegex("(\\d){1,9}");
		row.add(numberQuotas);

		fieldSet.add(row);

		row = new RowContainer();
		row.setAutoHeight(true);

		label = new MyLabel("Frecuencia de pago:", LABEL_WIDTH);
		row.add(label);

		final ComboForm paymentFreq = new ComboForm(80);
		paymentFreq.setDataSource(new DataSource("sol", "paymentFrequencyId", DataSourceType.RECORD));

		Reference refPaymentFreq = new Reference("freq", "Frequency");
		final ArrayColumnData freqCdata = new ArrayColumnData();
		freqCdata.add(new MyColumnData("freq", "pk_frequencyId", "Id", 50));
		freqCdata.add(new MyColumnData("freq", "description", "Descripcion", 150));
		paymentFreq.setQueryData(refPaymentFreq, freqCdata);
		paymentFreq.setDisplayField("pk_frequencyId");
		row.add(paymentFreq);

		InputBox paymentFreqDesc = new InputBox(150);
		paymentFreqDesc.setDataSource(new DataSource("Frequency", "description", DataSourceType.DESCRIPTION));
		row.add(paymentFreqDesc);

		paymentFreq.linkWithField(paymentFreqDesc, "description");

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
		final ComboForm fundsDestinationCombo = new ComboForm(80);
		fundsDestinationCombo.setDataSource(new DataSource("sol", "fundsDestinationId", DataSourceType.RECORD));

		Reference refFundsDest = new Reference("fd", "FundsDestination");
		final ArrayColumnData fdCdata = new ArrayColumnData();
		fdCdata.add(new MyColumnData("fd", "pk_fundsDestinationId", "Id", 50));
		fdCdata.add(new MyColumnData("fd", "description", "Descripcion", 200));
		fundsDestinationCombo.setQueryData(refFundsDest, fdCdata);
		fundsDestinationCombo.setDisplayField("pk_fundsDestinationId");
		row.add(fundsDestinationCombo);

		fieldSet.add(row);

		row = new RowContainer();
		row.setHeight(46);

		label = new MyLabel("Descripcion:", LABEL_WIDTH);
		row.add(label);

		MyTextArea fundsDescription = new MyTextArea(375, 44, 250);
		fundsDescription.setDataSource(new DataSource("sol", "destinationDescription", DataSourceType.RECORD));
		row.add(fundsDescription);

		fieldSet.add(row);
		loan.add(fieldSet);

		// Add div components
		tabPanel.add(basic);
		tabPanel.add(loan);

		form.add(tabPanel);

		// Hidden fields
		MyDateField expirationDate = new MyDateField();
		expirationDate.setVisible(false);
		expirationDate.setDataSource(new DataSource("sol", "expirationDate", DataSourceType.RECORD));
		expirationDate.setValue(DatesManager.DEFAULT_EXPIRED_DATE);
		form.add(expirationDate);

		InputBox numberRenewal = new InputBox();
		numberRenewal.setVisible(false);
		numberRenewal.setDataSource(new DataSource("sol", "numberRenewal", DataSourceType.RECORD));
		numberRenewal.setValue("0");
		form.add(numberRenewal);

		form.setButtonAlign(HorizontalAlignment.CENTER);
		form.addButton(new Button("Guardar", new SelectionListener<ButtonEvent>() {
			@Override
			public void componentSelected(ButtonEvent ce) {
				form.commitForm();
			}
		}));
		form.addButton(new Button("Limpiar", new SelectionListener<ButtonEvent>() {
			@Override
			public void componentSelected(ButtonEvent ce) {
				form.clear();
			}
		}));

		add(form);
	}
}