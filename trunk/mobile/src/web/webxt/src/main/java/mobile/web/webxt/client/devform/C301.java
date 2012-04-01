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
import com.extjs.gxt.ui.client.widget.Info;
import com.extjs.gxt.ui.client.widget.LayoutContainer;
import com.extjs.gxt.ui.client.widget.TabItem;
import com.extjs.gxt.ui.client.widget.TabPanel;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.form.FieldSet;
import com.extjs.gxt.ui.client.widget.form.PropertyEditor;
import com.extjs.gxt.ui.client.widget.form.Radio;
import com.extjs.gxt.ui.client.widget.form.RadioGroup;
import com.extjs.gxt.ui.client.widget.layout.CardLayout;
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

	MyFormPanel form;
	RadioGroup debtorRadioGroup;
	Radio individualRadio;
	Radio groupalRadio;
	ComboForm individualId;
	ComboForm groupId;

	@Override
	protected void onRender(Element parent, int index) {
		super.onRender(parent, index);

		// Constants
		final int FORM_WIDTH = 540;
		final int TAB_HEIGHT = 360;
		final int LABEL_WIDTH = 65;

		// ///////////////////////////
		// Form panel
		form = new MyFormPanel(this, "Solicitud de Microcrédito", FORM_WIDTH) {
			@Override
			protected void postQuery() {
				doPostQuery();
			}
		};
		form.setLayout(new FlowLayout());

		// ///////////////////////////
		// Header
		// Solicitude id
		RowContainer row = new RowContainer();
		row.setStyleAttribute("margin-bottom", "5px");
		MyLabel label = new MyLabel("Solicitud:", LABEL_WIDTH);
		row.add(label);

		final ComboForm solicitudeId = new ComboForm(100);
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

		// ///////////////////////////
		// Tab panel
		final TabPanel tabPanel = new TabPanel();
		tabPanel.setHeight(TAB_HEIGHT);

		// ///////////////////////////
		// Basic tab
		TabItem basic = new TabItem();
		basic.setStyleAttribute("padding", "10px");
		basic.setText("Básica");
		basic.setLayout(new FlowLayout());
		basic.setBorders(true);

		// ///////////////////////////
		// Debtor FieldSet
		FieldSet fieldSet = new FieldSet();
		fieldSet.setHeading("Deudor");
		fieldSet.setCollapsible(true);

		row = new RowContainer();

		// Partner type
		label = new MyLabel("Tipo:", LABEL_WIDTH);
		row.add(label);

		individualRadio = new Radio();
		individualRadio.setId("individualRadio");
		individualRadio.setBoxLabel("Individual");
		individualRadio.setValue(true);

		groupalRadio = new Radio();
		groupalRadio.setId("groupalRadio");
		groupalRadio.setBoxLabel("Grupal");

		debtorRadioGroup = new RadioGroup();
		debtorRadioGroup.setId("debtorRadioGroup");
		debtorRadioGroup.add(individualRadio);
		debtorRadioGroup.add(groupalRadio);

		row.add(debtorRadioGroup);
		fieldSet.add(row);

		// Individual debtor
		final CardLayout layout = new CardLayout();
		final LayoutContainer debtorContainer = new LayoutContainer(layout);
		final RowContainer row1 = new RowContainer();
		row1.setAutoHeight(true);

		label = new MyLabel("Deudor:", LABEL_WIDTH);
		row1.add(label);

		individualId = new ComboForm(80);
		individualId.setDataSource(new DataSource("sol", "partnerClientId", DataSourceType.RECORD));
		individualId.setProcess("G202");
		individualId.setPageSize(10);
		individualId.setEditable(true);

		Reference refPartner1 = new Reference("cli1", "Partner");
		final ArrayColumnData combodata = new ArrayColumnData();
		combodata.add(new MyColumnData("cli1", "partnerId", "Id", 50));
		combodata.add(new MyColumnData("cli1", "identificationNumber", "Identificación", 100));
		combodata.add(new MyColumnData("cli1", "name", "Nombre", 200));
		individualId.setQueryData(refPartner1, combodata);
		individualId.setDisplayField("partnerId");
		individualId.setFilteredField("identificationNumber");
		row1.add(individualId);

		InputBox clientDescription = new InputBox(280);
		clientDescription.setReadOnly(true);
		row1.add(clientDescription);

		individualId.linkWithField(clientDescription, "name");

		debtorContainer.add(row1);

		// Group debtor
		final RowContainer row2 = new RowContainer();
		row2.setAutoHeight(true);

		label = new MyLabel("Deudor:", LABEL_WIDTH);
		row2.add(label);

		groupId = new ComboForm(80);
		groupId.setId("groupId");
		groupId.setDataSource(new DataSource("sol", "groupClientId", DataSourceType.RECORD));
		groupId.setPageSize(10);
		groupId.setEditable(false);

		Reference refGroup1 = new Reference("gru1", "PartnerGroup");
		final ArrayColumnData combodata2 = new ArrayColumnData();
		combodata2.add(new MyColumnData("gru1", "pk_partnerGroupId", "Id", 70));
		combodata2.add(new MyColumnData("gru1", "groupDescription", "Descripción", 150));
		groupId.setQueryData(refGroup1, combodata2);
		groupId.setDisplayField("pk_partnerGroupId");
		groupId.setFilteredField("pk_partnerGroupId");
		row2.add(groupId);

		InputBox clientDescription2 = new InputBox(280);
		clientDescription2.setReadOnly(true);
		row2.add(clientDescription2);

		groupId.linkWithField(clientDescription2, "groupDescription");

		debtorContainer.add(row2);

		// Events radio buttons
		debtorRadioGroup.addListener(Events.Change, new Listener<FieldEvent>() {
			public void handleEvent(FieldEvent fe) {
				RadioGroup rg = (RadioGroup) fe.getField();
				if (rg.getValue().getBoxLabel().compareTo("Individual") == 0) {
					layout.setActiveItem(row1);
				} else {
					layout.setActiveItem(row2);
				}
			};
		});

		fieldSet.add(debtorContainer);

		basic.add(fieldSet);

		// ////////////////////////
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
		pcdata.add(new MyColumnData("pro", "rate", false));
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

		final InputBox rate = new InputBox(70);
		rate.setDataSource(new DataSource("ProductMicrocredit", "rate", DataSourceType.DESCRIPTION));
		rate.setReadOnly(true);
		rate.setPropertyEditor(new PropertyEditor<String>() {

			public String getStringValue(String value) {
				String out = null;
				if (value != null) {
					out = value + "%";
				}
				return out;
			}

			public String convertStringValue(String value) {
				String out = null;
				if (value != null) {
					out = value.substring(0, value.length()-1);
				}
				return out;
			}
		});
		row.add(rate);

		fieldSet.add(row);

		row = new RowContainer();

		label = new MyLabel("Monto min:", LABEL_WIDTH);
		row.add(label);

		final InputBox minAmount = new InputBox(100);
		minAmount.setReadOnly(true);
		minAmount.setDataSource(new DataSource("ProductMicrocredit", "minAmount", DataSourceType.DESCRIPTION));
		minAmount.setFireChangeEventOnSetValue(true);

		row.add(minAmount);

		label = new MyLabel("Plazo min:", LABEL_WIDTH);
		row.add(label, new HBoxLayoutData(0, 10, 0, 20));

		final InputBox minPeriod = new InputBox(100);
		minPeriod.setReadOnly(true);
		minPeriod.setDataSource(new DataSource("ProductMicrocredit", "minPeriod", DataSourceType.DESCRIPTION));
		minPeriod.setFireChangeEventOnSetValue(true);
		row.add(minPeriod);

		fieldSet.add(row);

		row = new RowContainer();
		row.setAutoHeight(true);

		label = new MyLabel("Monto max:", LABEL_WIDTH);
		row.add(label);

		final InputBox maxAmount = new InputBox(100);
		maxAmount.setReadOnly(true);
		maxAmount.setDataSource(new DataSource("ProductMicrocredit", "maxAmount", DataSourceType.DESCRIPTION));
		maxAmount.setFireChangeEventOnSetValue(true);
		row.add(maxAmount);

		label = new MyLabel("Plazo max:", LABEL_WIDTH);
		row.add(label, new HBoxLayoutData(0, 10, 0, 20));

		final InputBox maxPeriod = new InputBox(100);
		maxPeriod.setReadOnly(true);
		maxPeriod.setDataSource(new DataSource("ProductMicrocredit", "maxPeriod", DataSourceType.DESCRIPTION));
		maxPeriod.setFireChangeEventOnSetValue(true);
		row.add(maxPeriod);

		productCombo.linkWithField(productDescription, "description");
		productCombo.linkWithField(rate, "rate");
		productCombo.linkWithField(minAmount, "minAmount");
		productCombo.linkWithField(maxAmount, "maxAmount");
		productCombo.linkWithField(minPeriod, "minPeriod");
		productCombo.linkWithField(maxPeriod, "maxPeriod");

		fieldSet.add(row);

		basic.add(fieldSet);

		// /////////////////////
		// Credit data FieldSet
		fieldSet = new FieldSet();
		fieldSet.setHeading("Datos del Crédito");
		fieldSet.setCollapsible(true);
		fieldSet.setLayout(new FlowLayout());

		row = new RowContainer();

		label = new MyLabel("Monto:", LABEL_WIDTH);
		row.add(label);

		final MyNumberField amount = new MyNumberField();
		amount.setWidth(80);
		amount.setAllowBlank(false);
		amount.setDataSource(new DataSource("sol", "amount", DataSourceType.RECORD));
		amount.setPropertyEditorType(Double.class);
		amount.setFormat(NumberFormat.getFormat("#,##0.00"));
		amount.setMaxValue(1000);
		row.add(amount);

		// Min / max validations
		minAmount.addListener(Events.Change, new Listener<FieldEvent>() {
			public void handleEvent(FieldEvent be) {
				InputBox input = (InputBox) be.getSource();
				if (input.getValue() != null && input.getValue().length() > 0) {
					amount.setMinValue(Double.parseDouble(input.getValue()));
					if (amount.getValue() != null) {
						amount.validate();
					}
				}
			}
		});
		maxAmount.addListener(Events.Change, new Listener<FieldEvent>() {
			public void handleEvent(FieldEvent be) {
				InputBox input = (InputBox) be.getSource();
				if (input.getValue() != null && input.getValue().length() > 0) {
					amount.setMaxValue(Double.parseDouble(input.getValue()));
					if (amount.getValue() != null) {
						amount.validate();
					}
				}
			}
		});

		label = new MyLabel("Plazo:", LABEL_WIDTH);
		row.add(label, new HBoxLayoutData(0, 10, 0, 30));

		final MyNumberField term = new MyNumberField();
		term.setWidth(80);
		term.setDataSource(new DataSource("sol", "term", DataSourceType.RECORD));
		term.setPropertyEditorType(Integer.class);
		row.add(term);

		// Min / max validations
		minPeriod.addListener(Events.Change, new Listener<FieldEvent>() {
			public void handleEvent(FieldEvent be) {
				InputBox input = (InputBox) be.getSource();
				if (input.getValue() != null && input.getValue().length() > 0) {
					term.setMinValue(Double.parseDouble(input.getValue()));
					if (term.getValue() != null) {
						term.validate();
					}
				}
			}
		});
		maxPeriod.addListener(Events.Change, new Listener<FieldEvent>() {
			public void handleEvent(FieldEvent be) {
				InputBox input = (InputBox) be.getSource();
				if (input.getValue() != null && input.getValue().length() > 0) {
					term.setMaxValue(Double.parseDouble(input.getValue()));
					if (term.getValue() != null) {
						term.validate();
					}
				}
			}
		});

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
		paymentFreqDesc.setReadOnly(true);
		row.add(paymentFreqDesc);

		paymentFreq.linkWithField(paymentFreqDesc, "description");

		fieldSet.add(row);

		basic.add(fieldSet);

		// //////////////////////
		// Destiny tab
		TabItem destiny = new TabItem();
		// Loan tab
		destiny.setStyleAttribute("padding", "10px");
		destiny.setText("Destino");
		destiny.setLayout(new FlowLayout());
		destiny.setBorders(true);

		// /////////////////////
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
		fdCdata.add(new MyColumnData("fd", "pk_fundsDestinationId", "Id", 100));
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
		destiny.add(fieldSet);

		// ////////////////
		// Decision fieldSet
		fieldSet = new FieldSet();
		fieldSet.setHeading("Estado");
		fieldSet.setCollapsible(true);
		fieldSet.setLayout(new FlowLayout());

		row = new RowContainer();

		// Status
		label = new MyLabel("Estado:", LABEL_WIDTH);
		row.add(label);

		final ComboForm statusCombo = new ComboForm(80);
		statusCombo.setDataSource(new DataSource("sol", "statusId", DataSourceType.RECORD));

		Reference statusRef = new Reference("sta", "SolicitudeStatus");
		final ArrayColumnData statCdata = new ArrayColumnData();
		statCdata.add(new MyColumnData("sta", "pk_statusId", "Id", 50));
		statCdata.add(new MyColumnData("sta", "description", "Descripcion", 200));
		statusCombo.setQueryData(statusRef, statCdata);
		statusCombo.setDisplayField("pk_statusId");
		row.add(statusCombo);

		final InputBox statusDes = new InputBox(150);
		statusDes.setReadOnly(true);
		statusDes.setDataSource(new DataSource("SolicitudeStatus", "description", DataSourceType.DESCRIPTION));
		statusCombo.linkWithField(statusDes, "description");
		row.add(statusDes);

		fieldSet.add(row);

		row = new RowContainer();
		row.setAutoHeight(true);

		label = new MyLabel("Asesor:", LABEL_WIDTH);
		row.add(label);

		ComboForm assessorCombo = new ComboForm(80);
		assessorCombo.setDataSource(new DataSource("sol", "assessor", DataSourceType.RECORD));

		Reference refUserAcco = new Reference("usa", "UserAccount");
		final ArrayColumnData uadata = new ArrayColumnData();
		uadata.add(new MyColumnData("usa", "pk_userId", "Id", 40));
		uadata.add(new MyColumnData("usa", "name", "Nombre", 120));
		assessorCombo.setQueryData(refUserAcco, uadata);
		assessorCombo.setDisplayField("pk_userId");

		FilterConfig filter = new BaseStringFilterConfig();
		filter.setField("userTypeId");
		filter.setComparison("=");
		filter.setValue("ASE");
		assessorCombo.addFilter(filter);

		row.add(assessorCombo);

		label = new MyLabel("Fecha:", LABEL_WIDTH);
		label.setVisible(false);
		row.add(label, new HBoxLayoutData(0, 10, 0, 20));

		final MyDateField solicitudDate = new MyDateField(100);
		solicitudDate.setReadOnly(true);
		solicitudDate.setValue(DatesManager.getCurrentDate());
		solicitudDate.setDataSource(new DataSource("sol", "solicitudeDate", DataSourceType.RECORD));
		solicitudDate.setVisible(false);
		row.add(solicitudDate);

		fieldSet.add(row);

		destiny.add(fieldSet);

		// Add div components
		tabPanel.add(basic);
		tabPanel.add(destiny);

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

		InputBox quotaType = new InputBox();
		quotaType.setDataSource(new DataSource("sol", "quotaTypeId", DataSourceType.RECORD));
		quotaType.setVisible(false);
		quotaType.setValue("AMR");
		form.add(quotaType);

		amount.addListener(Events.Change, new Listener<FieldEvent>() {
			public void handleEvent(FieldEvent be) {
				// TODO Auto-generated method stub

			}
		});

		form.setButtonAlign(HorizontalAlignment.CENTER);
		form.addButton(new Button("Guardar", new SelectionListener<ButtonEvent>() {
			@Override
			public void componentSelected(ButtonEvent ce) {
				if (debtorRadioGroup.getValue().getBoxLabel().compareTo("Individual") == 0
						&& individualId.getValue() == null) {
					Info.display("Requerido", "Ingresar un cliente individual");
					return;
				} else if (debtorRadioGroup.getValue().getBoxLabel().compareTo("Grupal") == 0
						&& groupId.getValue() == null) {
					Info.display("Requerido", "Ingresar un cliente grupal");
					return;
				}

				if (debtorRadioGroup.getValue().getBoxLabel().compareTo("Individual") == 0) {
					groupId.setValue(null);
				} else {
					individualId.setValue(null);
				}
				form.commitForm();
			}
		}));

		add(form);
	}

	private void doPostQuery() {
		if (individualId.getValue() != null) {
			debtorRadioGroup.setValue(individualRadio);
		} else if (groupId.getValue() != null) {
			debtorRadioGroup.setValue(groupalRadio);
		}
	}
}