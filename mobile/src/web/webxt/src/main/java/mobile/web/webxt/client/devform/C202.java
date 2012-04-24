package mobile.web.webxt.client.devform;

import java.util.ArrayList;
import java.util.List;

import mobile.common.message.Item;
import mobile.web.webxt.client.MobileConstants;
import mobile.web.webxt.client.data.form.DataSource;
import mobile.web.webxt.client.data.form.DataSourceType;
import mobile.web.webxt.client.data.form.Reference;
import mobile.web.webxt.client.form.EntityContentPanel;
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
import mobile.web.webxt.client.form.widgetsgrid.ComboColumn;
import mobile.web.webxt.client.form.widgetsgrid.EntityEditorGrid;
import mobile.web.webxt.client.form.widgetsgrid.ExpireColumnConfig;
import mobile.web.webxt.client.form.widgetsgrid.GridPagingToolBar;
import mobile.web.webxt.client.form.widgetsgrid.GridToolBar;
import mobile.web.webxt.client.form.widgetsgrid.MyColumnData;
import mobile.web.webxt.client.form.widgetsgrid.NormalColumn;
import mobile.web.webxt.client.mvc.AppEvents;
import mobile.web.webxt.client.util.DatesManager;

import com.extjs.gxt.ui.client.Registry;
import com.extjs.gxt.ui.client.Style.HorizontalAlignment;
import com.extjs.gxt.ui.client.Style.Orientation;
import com.extjs.gxt.ui.client.data.BaseModelData;
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
import com.extjs.gxt.ui.client.mvc.AppEvent;
import com.extjs.gxt.ui.client.mvc.Dispatcher;
import com.extjs.gxt.ui.client.store.ListStore;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.LayoutContainer;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.form.FieldSet;
import com.extjs.gxt.ui.client.widget.grid.ColumnConfig;
import com.extjs.gxt.ui.client.widget.grid.ColumnData;
import com.extjs.gxt.ui.client.widget.grid.ColumnModel;
import com.extjs.gxt.ui.client.widget.grid.Grid;
import com.extjs.gxt.ui.client.widget.grid.GridCellRenderer;
import com.extjs.gxt.ui.client.widget.grid.RowNumberer;
import com.extjs.gxt.ui.client.widget.layout.RowLayout;
import com.google.gwt.user.client.Element;

public class C202 extends MyGeneralForm {

	private final static String PROCESS = "C202";
	private final static String ENTITY_GROUP = "PartnerGroup";
	private final static String ENTITY_MEMBER = "PartnerGroupMember";

	private final int LABEL_WIDTH = 65;
	private final int FORM_WIDTH = 600;
	// private int membersNumber = 0;
	private int numberOfPresidents, numberOfDeletes;

	// Containers and fields
	ContentPanel panelPrincipal;

	InputBox freqDescription, generatedId;
	MyDateField formationDate;
	MyTextArea activity, description;
	ComboForm asessorCombo, freqCombo, partnerGroupCode;
	MyNumberField meetingDay;

	MyFormPanel formMemebersGrid;
	MyGeneralForm formContainerMembersGrid;
	MyFormPanel formGroup;
	MyGeneralForm formContainerGroup;
	EntityEditorGrid grid;
	GridToolBar toolBar;
	GridPagingToolBar pagingToolBar;

	Button save;

	@Override
	protected void onRender(Element parent, int index) {
		super.onRender(parent, index);

		formContainerGroup = new MyGeneralForm(PROCESS);
		formContainerGroup.setReference(new Reference("par", ENTITY_GROUP));
		formContainerGroup.setBorders(false);
		formGroup = new MyFormPanel(formContainerGroup, "", FORM_WIDTH) {
			@Override
			protected void postMaintenance() {
				// Save members (after save group)
				//grid.getStore().commitChanges();
				pagingToolBar.commitChanges();
			}
		};
		formGroup.setHeaderVisible(false);
		formGroup.setFrame(false);
		formGroup.setBorders(false);
		formGroup.setBodyBorder(false);
		formGroup.setStyleAttribute("padding", "0px");

		formContainerMembersGrid = new MyGeneralForm(PROCESS, true);
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
		panelPrincipal = new ContentPanel();
		panelPrincipal.setWidth(FORM_WIDTH);
		panelPrincipal.setHeading("Clientes Grupales");
		panelPrincipal.setHeight(465);
		panelPrincipal.setFrame(true);

		save = new Button("Guardar", new SelectionListener<ButtonEvent>() {
			@Override
			public void componentSelected(ButtonEvent ce) {
				// Save group (then save the members in the postMaintenance)
				if (validateMembers()) {
					formGroup.commitForm();
				}
			}
		});

		panelPrincipal.addButton(save);
		panelPrincipal.setButtonAlign(HorizontalAlignment.CENTER);

		createHeaderForm();
		createGrid();

		panelPrincipal.add(formGroup);
		panelPrincipal.add(formMemebersGrid);

		add(panelPrincipal);

	}

	private void createGrid() {
		// Configuration
		final ArrayColumnData cdata = new ArrayColumnData();
		cdata.add(new MyColumnData("pk_personId", "Persona", 40, 4, false));
		DataSource ds = new DataSource("Person", "lastName", DataSourceType.DESCRIPTION);
		cdata.add(new MyColumnData(ds, "Apellido", 50));
		ds = new DataSource("Person", "name", DataSourceType.DESCRIPTION);
		cdata.add(new MyColumnData(ds, "Nombre", 50));
		cdata.add(new MyColumnData("responsabilityId", "Responsabilidad", 60, 40, false));
		ds = new DataSource("Responsability", "name", DataSourceType.DESCRIPTION);
		cdata.add(new MyColumnData(ds, "Responsabilidad", 100));
		cdata.add(new MyColumnData("observations", "Observaciones", 120, 40, true));
		formContainerMembersGrid.getConfig().setlDataSource(cdata.getDataSources());

		// Columns
		List<ColumnConfig> configs = new ArrayList<ColumnConfig>();

		configs.add(new RowNumberer());

		ComboColumn personComboColumn = new ComboColumn(cdata.get(0));
		Reference refPerson = new Reference("per", "Person");
		ArrayColumnData perCdata = new ArrayColumnData();
		perCdata.add(new MyColumnData("per", "pk_personId", "Cod", 40));
		perCdata.add(new MyColumnData("per", "identificationNumber", "Id", 100));
		perCdata.add(new MyColumnData("per", "lastName", "Apellido", 100));
		perCdata.add(new MyColumnData("per", "name", "Nombre", 100));
		personComboColumn.setQueryData(refPerson, perCdata);
		personComboColumn.getComboBox().setPageSize(10);
		configs.add(personComboColumn);

		NormalColumn lnameCol = new NormalColumn(cdata.get(1));
		lnameCol.setHidden(true);
		configs.add(lnameCol);
		NormalColumn nameCol = new NormalColumn(cdata.get(2));
		nameCol.setHidden(true);
		configs.add(nameCol);
		personComboColumn.linkWithColumn("lastName", lnameCol);
		personComboColumn.linkWithColumn("name", nameCol);

		GridCellRenderer<ModelData> nameRenderer = new GridCellRenderer<ModelData>() {
			public String render(ModelData model, String property, ColumnData config, int rowIndex, int colIndex,
					ListStore<ModelData> store, Grid<ModelData> grid) {
				String completeName = "";
				if (model.get("Person_lastName") != null && model.get("Person_name") != null) {
					completeName = model.get("Person_lastName") + " " + model.get("Person_name");
				}
				return completeName;
			}
		};
		ColumnConfig completeName = new ColumnConfig("completeName", "Nombre", 200);
		completeName.setRenderer(nameRenderer);
		completeName.setSortable(false);
		configs.add(completeName);

		NormalColumn resposabilityIdCol = new NormalColumn(cdata.get(3));
		resposabilityIdCol.setHidden(true);
		configs.add(resposabilityIdCol);

		ComboColumn responsabilityColumn = new ComboColumn(cdata.get(4));
		Reference refResponsa = new Reference("res", "Responsability");
		ArrayColumnData resCdata = new ArrayColumnData();
		resCdata.add(new MyColumnData("res", "name", "Nombre", 120));
		resCdata.add(new MyColumnData("res", "pk_responsabilityId"));
		responsabilityColumn.setQueryData(refResponsa, resCdata);
		configs.add(responsabilityColumn);

		responsabilityColumn.linkWithColumn("pk_responsabilityId", resposabilityIdCol);

		configs.add(new NormalColumn(cdata.get(5)));

		configs.add(new ExpireColumnConfig());

		ColumnModel cm = new ColumnModel(configs);

		// Grid panel
		EntityContentPanel gridPanel = new EntityContentPanel(560, 200);

		// Grid
		grid = new EntityEditorGrid(formContainerMembersGrid.getStore(), cm);
		grid.setAutoExpandColumn("pk_personId");
		grid.setBorders(true);
		grid.addDependency(partnerGroupCode);

		gridPanel.add(grid);

		// Top tool bar
		toolBar = new GridToolBar(grid, formContainerMembersGrid.getStore());
		toolBar.getSaveButton().setVisible(false);
		gridPanel.setTopComponent(toolBar);

		// Paging tool bar
		pagingToolBar = new GridPagingToolBar(grid, 10);
		gridPanel.setBottomComponent(pagingToolBar);

		// Operations
		partnerGroupCode.addSelectionChangedListener(new SelectionChangedListener<ModelData>() {
			@Override
			public void selectionChanged(SelectionChangedEvent<ModelData> se) {

				if (((ComboForm) se.getSource()).isSomeSelected()) {
					formGroup.queryForm();
					pagingToolBar.refresh();
//				} else {
//					formGroup.queryForm();
//					grid.getStore().removeAll();
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
		partnerGroupCode.setData("mobile-type", Integer.class);
		partnerGroupCode.setDataSource(new DataSource("par", "pk_partnerGroupId", DataSourceType.CRITERION));

		Reference refPartner = new Reference("par1", "PartnerGroup");
		final ArrayColumnData perCdata = new ArrayColumnData();
		perCdata.add(new MyColumnData("par1", "pk_partnerGroupId", "Id", 70));
		perCdata.add(new MyColumnData("par1", "groupDescription", "Descripcion", 200));
		partnerGroupCode.setQueryData(refPartner, perCdata);
		partnerGroupCode.setPageSize(10);
		partnerGroupCode.setDisplayField("pk_partnerGroupId");

		row.add(partnerGroupCode);
		fieldSetLeft.add(row);

		// GeneratedId
		generatedId = new InputBox();
		generatedId.setId("generatedId");
		generatedId.setDataSource(new DataSource(Item.GENERATED_ID, DataSourceType.CONTROL));
		generatedId.setVisible(false);
		generatedId.setFireChangeEventOnSetValue(true);
		generatedId.addListener(Events.Change, new Listener<FieldEvent>() {
			public void handleEvent(FieldEvent e) {
				if (e.getValue() != null) {
					partnerGroupCode.setLoaded(false);
					partnerGroupCode.setRawValue((String) e.getValue());
					ModelData model = new BaseModelData();
					model.set(partnerGroupCode.getDisplayField(), e.getValue());
					partnerGroupCode.setValue(model);
				}
			}
		});
		row.add(generatedId);
		fieldSetLeft.add(row);

		// Description:
		row = new RowContainer();
		label = new MyLabel("Descripción:", LABEL_WIDTH);
		row.add(label);
		row.setHeight(70);

		description = new MyTextArea(180, 100);
		description.setDataSource(new DataSource("par", "groupDescription", DataSourceType.RECORD));
		description.setHeight(60);
		description.setEmptyText("Ingrese una descripcion del grupo");
		description.setAllowBlank(false);

		row.add(description);
		fieldSetRight.add(row);

		// Activity:
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

		// Assessor Combo:
		row = new RowContainer();
		label = new MyLabel("Asesor:", LABEL_WIDTH);
		row.add(label);

		asessorCombo = new ComboForm(80);
		asessorCombo.setDataSource(new DataSource("par", "userId", DataSourceType.RECORD));
		asessorCombo.setAllowBlank(false);

		Reference refUserAcco = new Reference("usa", "UserAccount");
		final ArrayColumnData uadata = new ArrayColumnData();
		uadata.add(new MyColumnData("usa", "pk_userId", "Id", 40));
		uadata.add(new MyColumnData("usa", "name", "Nombre", 120));
		asessorCombo.setQueryData(refUserAcco, uadata);
		asessorCombo.setDisplayField("pk_userId");

		FilterConfig filter = new BaseStringFilterConfig();
		filter.setField("userTypeId");
		filter.setComparison("=");
		filter.setValue("ASE");
		asessorCombo.addFilter(filter);
		
		if (Registry.get(MobileConstants.PROFILE).toString().compareTo("ASE") == 0) {
			asessorCombo.setReadOnly(true);
			asessorCombo.setEditable(false);
			asessorCombo.setEnabled(false);
			ModelData model = new BaseModelData();
			model.set(asessorCombo.getDisplayField(), Registry.get(MobileConstants.USER).toString());
			asessorCombo.setValue(model);
		}
		
		row.add(asessorCombo);

		fieldSetLeft.add(row);

		// Frequency:
		row = new RowContainer();
		label = new MyLabel("Frecuencia de reunión:", LABEL_WIDTH);
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

		// Meeting day
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

		// Formation date
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

	private boolean validateMembers() {
		boolean isValid = true;
		if (grid.getStore().getCount() >= 3 && grid.getStore().getCount() <= 15 && isMemberNumberOk(grid)) {
			if (!isOnePresident(grid)) {
				isValid = false;
				Dispatcher.forwardEvent(new AppEvent(AppEvents.UserNotification, "El grupo debe tener 1 Presidente"));
			}
		} else {
			isValid = false;
			Dispatcher.forwardEvent(new AppEvent(AppEvents.UserNotification,
					"El grupo debe tener entre 3 y 15 miembros"));
		}
		return isValid;
	}

	private boolean isOnePresident(EntityEditorGrid grid) {
		int numberOfRecords = grid.getStore().getCount();
		numberOfPresidents = 0;

		for (int i = 0; i < numberOfRecords; i++) {
			String president = grid.getStore().getAt(i).get("responsabilityId").toString();
			if (president.compareTo("1") == 0) {
				numberOfPresidents++;
			}
		}

		if (numberOfPresidents == 1) {
			return true;
		} else {
			return false;
		}
	}

	private boolean isSomeDeteled(EntityEditorGrid grid) {
		int numberOfRecords = grid.getStore().getCount();
		numberOfDeletes = 0;

		for (int i = 0; i < numberOfRecords; i++) {
			if (grid.getStore().getAt(i).get(Item.EXPIRE_ITEM) != null) {
				boolean delete = Boolean.parseBoolean(grid.getStore().getAt(i).get(Item.EXPIRE_ITEM).toString());

				if (delete) {
					numberOfDeletes++;
				}
			}
		}

		if (numberOfDeletes > 0) {
			return true;
		} else {
			return false;
		}
	}

	private boolean isMemberNumberOk(EntityEditorGrid grid) {
		if (isSomeDeteled(grid)) {
			if (grid.getStore().getCount() - numberOfDeletes < 3) {
				return false;
			} else {
				return true;
			}
		} else {
			return true;
		}
	}

}