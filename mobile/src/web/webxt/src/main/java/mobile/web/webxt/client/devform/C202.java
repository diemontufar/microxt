package mobile.web.webxt.client.devform;

import java.util.ArrayList;
import java.util.List;

import mobile.common.message.Item;
import mobile.web.webxt.client.data.MyPagingLoader;
import mobile.web.webxt.client.data.MyProcessConfig;
import mobile.web.webxt.client.data.form.DataSource;
import mobile.web.webxt.client.data.form.DataSourceType;
import mobile.web.webxt.client.data.form.Reference;
import mobile.web.webxt.client.form.EntityContentPanel;
import mobile.web.webxt.client.form.MyFormPanel;
import mobile.web.webxt.client.form.MyGeneralForm;
import mobile.web.webxt.client.form.validations.Validate;
import mobile.web.webxt.client.form.widgets.ComboForm;
import mobile.web.webxt.client.form.widgets.InputBox;
import mobile.web.webxt.client.form.widgets.MyDateField;
import mobile.web.webxt.client.form.widgets.MyLabel;
import mobile.web.webxt.client.form.widgets.MyNumberField;
import mobile.web.webxt.client.form.widgets.MyTextArea;
import mobile.web.webxt.client.form.widgets.RowContainer;
import mobile.web.webxt.client.form.widgetsgrid.ArrayColumnData;
import mobile.web.webxt.client.form.widgetsgrid.ComboColumn;
import mobile.web.webxt.client.form.widgetsgrid.EntityEditorGrid;
import mobile.web.webxt.client.form.widgetsgrid.ExpireColumnConfig;
import mobile.web.webxt.client.form.widgetsgrid.GridPagingToolBar;
import mobile.web.webxt.client.form.widgetsgrid.GridToolBar;
import mobile.web.webxt.client.form.widgetsgrid.MyColumnData;
import mobile.web.webxt.client.form.widgetsgrid.NormalColumn;
import mobile.web.webxt.client.util.DatesManager;
import mobile.web.webxt.client.util.TextType;

import com.extjs.gxt.ui.client.Style.HorizontalAlignment;
import com.extjs.gxt.ui.client.Style.Orientation;
import com.extjs.gxt.ui.client.data.BaseModelData;
import com.extjs.gxt.ui.client.data.BaseStringFilterConfig;
import com.extjs.gxt.ui.client.data.FilterConfig;
import com.extjs.gxt.ui.client.data.LoadEvent;
import com.extjs.gxt.ui.client.data.ModelData;
import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.Events;
import com.extjs.gxt.ui.client.event.FieldEvent;
import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.event.LoadListener;
import com.extjs.gxt.ui.client.event.SelectionChangedEvent;
import com.extjs.gxt.ui.client.event.SelectionChangedListener;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.store.Store;
import com.extjs.gxt.ui.client.store.StoreEvent;
import com.extjs.gxt.ui.client.store.StoreListener;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.LayoutContainer;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.form.FieldSet;
import com.extjs.gxt.ui.client.widget.grid.ColumnConfig;
import com.extjs.gxt.ui.client.widget.grid.ColumnModel;
import com.extjs.gxt.ui.client.widget.grid.RowNumberer;
import com.extjs.gxt.ui.client.widget.layout.CenterLayout;
import com.extjs.gxt.ui.client.widget.layout.RowLayout;
import com.google.gwt.user.client.Element;

public class C202 extends LayoutContainer {

	private final static String PROCESS = "C202";
	private final static String ENTITY_GROUP = "PartnerGroup";
	private final static String ENTITY_MEMBER = "PartnerGroupMember";
	
	private int membersNumber = 0;

	ContentPanel panelPrincipal = new ContentPanel();

	// Partner Fields
	InputBox freqDescription;
	MyDateField formationDate;
	MyTextArea activity, description;
	ComboForm asessorCombo, freqCombo, partnerGroupCode;
	MyNumberField meetingDay;
	
	MyFormPanel formMemebersGrid;
	MyGeneralForm formContainerMembersGrid;
	MyFormPanel formGroup;
	MyGeneralForm formContainerGroup;

	final int LABEL_WIDTH = 65;
	final int FORM_WIDTH = 600;

	Button save, cancel;

	@Override
	protected void onRender(Element parent, int index) {
		super.onRender(parent, index);
		setLayout(new CenterLayout());
		getAriaSupport().setPresentation(true);
				
		formContainerGroup = new MyGeneralForm(PROCESS);
		formContainerGroup.setReference(new Reference("par", ENTITY_GROUP));
		formContainerGroup.setBorders(false);
		formGroup = new MyFormPanel(formContainerGroup, "", FORM_WIDTH);
		formGroup.setHeaderVisible(false);
		formGroup.setFrame(false);
		formGroup.setBorders(false);
		formGroup.setBodyBorder(false);
		formGroup.setStyleAttribute("padding", "0px");
		
		formContainerMembersGrid = new MyGeneralForm(PROCESS,true);
		formContainerMembersGrid.setReference(ENTITY_MEMBER);
		formContainerMembersGrid.setBorders(false);
		formMemebersGrid = new MyFormPanel(formContainerMembersGrid, "", FORM_WIDTH);
		formMemebersGrid.setHeaderVisible(false);
		formMemebersGrid.setFrame(false);
		formMemebersGrid.setBodyBorder(false);
		formMemebersGrid.setBorders(false);
		
		createPanel();
	}

	private void createPanel() {
		
		panelPrincipal.setWidth(FORM_WIDTH);
		panelPrincipal.setHeading("Clientes Grupales");
		panelPrincipal.setHeight(465);	
		panelPrincipal.setFrame(true);

		save = new Button("Guardar", new SelectionListener<ButtonEvent>() {
			@Override
			public void componentSelected(ButtonEvent ce) {
				formGroup.commitForm();
			}
		});

		cancel = new Button("Cancelar", new SelectionListener<ButtonEvent>() {
			@Override
			public void componentSelected(ButtonEvent ce) {
				formGroup.clear();
				//clearFields();
			}
		});

		panelPrincipal.addButton(save);
		panelPrincipal.addButton(cancel);
		panelPrincipal.setButtonAlign(HorizontalAlignment.CENTER);
		
		createHeaderForm();
		createGrid();
		
		panelPrincipal.add(formGroup);
		panelPrincipal.add(formMemebersGrid);
		
		add(panelPrincipal);
		
	}


	@SuppressWarnings({ "unchecked", "rawtypes" })
	private void createGrid() {
		
		// Configuration
		final ArrayColumnData cdata = new ArrayColumnData();
		cdata.add(new MyColumnData("pk_partnerGroupMemberId", "Codigo", 70, 20, false));
		cdata.add(new MyColumnData("partnerGroupId", "Grupo", 70, 20, false));
		cdata.add(new MyColumnData("personId", "Persona", 70, 40, false));
		cdata.add(new MyColumnData("responsabilityId", "Responsabilidad", 100, 40, false));
		cdata.add(new MyColumnData("observations", "Observaciones", 200, 40, true));
		formContainerMembersGrid.getConfig().setlDataSource(cdata.getDataSources());

		// Columns
		List<ColumnConfig> configs = new ArrayList<ColumnConfig>();

		configs.add(new RowNumberer());
		configs.add(new NormalColumn(cdata.get(0),TextType.TEXT,Validate.TEXT));
		
		NormalColumn groupColumn = new NormalColumn(cdata.get(1),TextType.TEXT,Validate.TEXT);
		groupColumn.setHidden(true);
		configs.add(groupColumn);

		ComboColumn personComboColumn = new ComboColumn(cdata.get(2));
		Reference refPerson = new Reference("per","Person");
		ArrayColumnData perCdata = new ArrayColumnData();
		perCdata.add(new MyColumnData("per","pk_personId", "Codigo", 40));
		perCdata.add(new MyColumnData("per","identificationNumber", "ID", 40));
		perCdata.add(new MyColumnData("per","name", "Nombre", 120));
		perCdata.add(new MyColumnData("per","lastName", "Apellido", 130));
		personComboColumn.setQueryData(refPerson, perCdata);
		personComboColumn.getComboBox().setPageSize(10);
		configs.add(personComboColumn);
		
		ComboColumn responsabilityColumn = new ComboColumn(cdata.get(3));
		Reference refResponsa = new Reference("res","Responsability");
		ArrayColumnData resCdata = new ArrayColumnData();
		resCdata.add(new MyColumnData("res","pk_responsabilityId", "Codigo", 40));
		resCdata.add(new MyColumnData("res","name", "Nombre", 120));
		responsabilityColumn.setQueryData(refResponsa, resCdata);
		configs.add(responsabilityColumn);

		configs.add(new NormalColumn(cdata.get(4)));

		configs.add(new ExpireColumnConfig());

		ColumnModel cm = new ColumnModel(configs);
		
		// Grid panel
		EntityContentPanel gridPanel = new EntityContentPanel(560, 200);

		// Grid
		final EntityEditorGrid grid = new EntityEditorGrid(formContainerMembersGrid.getStore(), cm);
		grid.setAutoExpandColumn("pk_partnerGroupMemberId");
		grid.setBorders(true);
		//grid.addDependency(partnerGroupCode);
		
		gridPanel.add(grid);
		
		// Top tool bar
		ModelData newItem = new BaseModelData();
		newItem.set(cdata.get(0).getId(), null);
		newItem.set(cdata.get(1).getId(), null);
		newItem.set(cdata.get(2).getId(), null);
		newItem.set(cdata.get(3).getId(), null);
		newItem.set(cdata.get(3).getId(), null);

		final GridToolBar toolBar = new GridToolBar(grid, formContainerMembersGrid.getStore(), newItem);
		enableToolBarButtons(toolBar,false);
		gridPanel.setTopComponent(toolBar);

		// Paging tool bar
		final GridPagingToolBar pagingToolBar = new GridPagingToolBar(grid, 10);
		gridPanel.setBottomComponent(pagingToolBar);
		
		LoadListener filterListener = new LoadListener() {
			public void loaderBeforeLoad(LoadEvent le) {

				MyProcessConfig config = le.getConfig();
				String ffield = "partnerGroupId";

				List<FilterConfig> filters = config.getFilterConfigs();
				if (filters == null) {
					filters = new ArrayList<FilterConfig>();
				}

				boolean existe = false;
				for (FilterConfig fil : filters) {
					if (fil.getField().compareTo(ffield) == 0) {
						existe = true;
						fil.setValue(partnerGroupCode.getValue().get("pk_partnerGroupId"));
					}
				}

				if (!existe) {
					FilterConfig filter = new BaseStringFilterConfig();
					filter.setField(ffield);
					filter.setComparison("=");
					filter.setValue(partnerGroupCode.getValue().get("pk_partnerGroupId"));
					filters.add(filter);
				}

				config.setFilterConfigs(filters);
			}
		};

		formContainerMembersGrid.getLoader().addListener(MyPagingLoader.BeforeLoad, filterListener);
		
		Store store = formContainerMembersGrid.getStore();
		
		store.addStoreListener(new StoreListener() {
		public void handleEvent(StoreEvent se) {
			if (se.getType() == Store.Add) {
				membersNumber=grid.getStore().getCount();
				grid.getStore().getAt(membersNumber-1).set("partnerGroupId", partnerGroupCode.getValue().get("pk_partnerGroupId"));
				grid.getColumnModel().getColumn(2).getEditor().disable();
			}

			if (se.getType() == Store.DataChanged) {
				
//				if(grid.getStore().getCount()>=3 && grid.getStore().getCount()<=15){
//					grid.getStore().commitChanges();
//					System.out.println("ES UN COMMIT!!!!!!!!!!!!!!");
//				}else{
//					grid.getStore().rejectChanges();
//					AlertDialog alert = new AlertDialog("Error", "Debe ingresar entre 3 y 15 miembros al grupo");
//					alert.show();
//					System.out.println("NO ES UN COMMIT!!!!!!!!!!!!!!");
//				}
				
			}
			
			
		}
	});


		// Operations
		partnerGroupCode.addSelectionChangedListener(new SelectionChangedListener<ModelData>() {
			@Override
			public void selectionChanged(SelectionChangedEvent<ModelData> se) {
				if (((ComboForm) se.getSource()).isSomeSelected()) {
					formGroup.queryForm();
					pagingToolBar.refresh();
					enableToolBarButtons(toolBar,true);
				}else{
					grid.getStore().removeAll();
					enableToolBarButtons(toolBar,false);
				}
			}
		});

		formMemebersGrid.add(gridPanel);
		
	}

	private void createHeaderForm() {

		LayoutContainer layoutContainer = new LayoutContainer();
		layoutContainer.setWidth(598);
		layoutContainer.setHeight(160);
		layoutContainer.setBorders(false);
		layoutContainer.setLayout(new RowLayout(Orientation.HORIZONTAL));
		
		FieldSet fieldSetLeft = new FieldSet();
		fieldSetLeft.setBorders(false);
		fieldSetLeft.setWidth(280);

		FieldSet fieldSetRight = new FieldSet();
		fieldSetRight.setBorders(false);
		fieldSetRight.setWidth(280);

		RowContainer row = new RowContainer();
		MyLabel label = new MyLabel("Codigo:", LABEL_WIDTH);
		row.add(label);

		partnerGroupCode = new ComboForm(80);
		partnerGroupCode.setDataSource(new DataSource("par", "pk_partnerGroupId", DataSourceType.CRITERION));
		
		Reference refPartner = new Reference("par1", "PartnerGroup");
		final ArrayColumnData perCdata = new ArrayColumnData();
		perCdata.add(new MyColumnData("par1", "pk_partnerGroupId", "Id", 100));
		partnerGroupCode.setQueryData(refPartner, perCdata);
		partnerGroupCode.setDisplayField("pk_partnerGroupId");
		
		row.add(partnerGroupCode);
		fieldSetLeft.add(row);
		
		
		// GeneratedId
		final InputBox generatedId = new InputBox();
		generatedId.setDataSource(new DataSource(Item.GENERATED_ID, DataSourceType.CONTROL));
		generatedId.setVisible(false);
		generatedId.setFireChangeEventOnSetValue(true);
		generatedId.addListener(Events.Change, new Listener<FieldEvent>() {
			public void handleEvent(FieldEvent e) {
				if (e.getValue() != null) {
					partnerGroupCode.setRawValue((String) e.getValue());
					partnerGroupCode.setLoaded(false);
				}
			}
		});
		row.add(generatedId);
		fieldSetLeft.add(row);
		
		//Description:
		row = new RowContainer();
		label = new MyLabel("Descripción:", LABEL_WIDTH);
		row.add(label);	
		row.setHeight(70);
				
		description=new MyTextArea(180,100);
		description.setDataSource(new DataSource("par", "groupDescription", DataSourceType.RECORD));
		description.setHeight(60);
		description.setEmptyText("Ingrese una descripcion del grupo");
		description.setAllowBlank(false);
		
		row.add(description);
		fieldSetRight.add(row);
				
		
		//Activity:
		row = new RowContainer();
		label = new MyLabel("Actividad:", LABEL_WIDTH);
		row.add(label);
		row.setHeight(70);

		activity = new MyTextArea(180, 300);
		activity.setDataSource(new DataSource("par", "activity", DataSourceType.RECORD));
		activity.setHeight(60);
		activity.setEmptyText("Ingrese las actividades a las que se dedica el grupo");
		
		row.add(activity);
		fieldSetRight.add(row);
		
		//Asessor Combo:
		row = new RowContainer();
		label = new MyLabel("Asesor:", LABEL_WIDTH);
		row.add(label);

		asessorCombo = new ComboForm(80);
		asessorCombo.setDataSource(new DataSource("par", "userId", DataSourceType.RECORD));

		Reference refUserAcco = new Reference("usa", "UserAccount");
		final ArrayColumnData uadata = new ArrayColumnData();
		uadata.add(new MyColumnData("usa", "pk_userId", "Id", 40));
		uadata.add(new MyColumnData("usa", "name", "Nombre", 120));
		asessorCombo.setQueryData(refUserAcco, uadata);
		asessorCombo.setDisplayField("pk_userId");
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
		fieldSetLeft.add(row);
		
		
		//Frecuency:
		row = new RowContainer();
		label = new MyLabel("Frecuencia:", LABEL_WIDTH);
		row.add(label);

		freqCombo = new ComboForm(80);
		freqCombo.setDataSource(new DataSource("par", "frequencyId", DataSourceType.RECORD));

		Reference refFrec = new Reference("fre", "Frequency");
		final ArrayColumnData frdata = new ArrayColumnData();
		frdata.add(new MyColumnData("fre", "pk_frequencyId", "Id", 40));
		frdata.add(new MyColumnData("fre", "description", "Descripcion", 120));
		freqCombo.setQueryData(refFrec, frdata);
		freqCombo.setDisplayField("pk_frequencyId");
		freqCombo.setAllowBlank(false);
		row.add(freqCombo);
		
		freqDescription = new InputBox(80);
		freqDescription.setReadOnly(true);
		freqDescription.setDataSource(new DataSource("Frequency", "description", DataSourceType.DESCRIPTION));
		row.add(freqDescription);
		
		freqCombo.linkWithField(freqDescription, "description");
		
		row.add(freqCombo);
		row.add(freqDescription);
		fieldSetLeft.add(row);
		
		//Reunion Day
		row = new RowContainer();
		label = new MyLabel("Dia reunion:", LABEL_WIDTH);
		row.add(label);

		//Reunion Day		
		row = new RowContainer();
		label = new MyLabel("Dia reunion:", LABEL_WIDTH);
		row.add(label);
		
		meetingDay = new MyNumberField(80);
		meetingDay.setDataSource(new DataSource("par", "meetingDay", DataSourceType.RECORD));
		meetingDay.setMaxValue(7);
		meetingDay.setMinValue(1);
		meetingDay.setToolTip("Dia de la semana entre 1-7");
		meetingDay.setMaxLength(1);
				
		row.add(meetingDay);
		fieldSetLeft.add(row);

		//Formation date
		row = new RowContainer();
		label = new MyLabel("Fecha Formación:", LABEL_WIDTH);
		row.add(label);

		formationDate = new MyDateField(80);
		formationDate.setReadOnly(true);
		formationDate.setHideTrigger(true);
		formationDate.setValue(DatesManager.getCurrentDate());
		formationDate.setDataSource(new DataSource("par", "creationDate", DataSourceType.RECORD));

		row.add(formationDate);
		fieldSetLeft.add(row);

		layoutContainer.add(fieldSetLeft);
		layoutContainer.add(fieldSetRight);

		formGroup.add(layoutContainer);
	}

	private void enableToolBarButtons(GridToolBar toolBar, boolean state){
		
		toolBar.enableAddButton(state);
		toolBar.enableSaveButton(state);
		toolBar.enableResetButton(state);
		
	}

}