package mobile.web.webxt.client.devform;

import mobile.web.webxt.client.form.MyGeneralForm;

public class C103 extends MyGeneralForm {
//
//	private final static String PROCESS = "C103";
//	private final static String ENTITY = "UserAccount";
//	private final Integer PAGE_SIZE = 5;
//
//	public C103() {
//		super(PROCESS, true);
//		setReference(ENTITY);
//	}
//
//	@Override
//	protected void onRender(Element parent, int index) {
//		super.onRender(parent, index);
//		setLayout(new CenterLayout());
//		getAriaSupport().setPresentation(true);
//
//		// Configuration
//		String entity = "ZoneAsessor";
//
//		final ArrayColumnData cdata = new ArrayColumnData();
//		cdata.add(new MyColumnData("pk_userId", "Asesor", 150, 20, false));
//		cdata.add(new MyColumnData("pk_geographicZoneId", "Zona", 150, 6, false));
//		cdata.add(new MyColumnData("observations", "Observaciones", 200, 50, true));
//		getConfig().setlDataSource(cdata.getDataSources());
//
//		// Column model
//		List<ColumnConfig> configs = new ArrayList<ColumnConfig>();
//
//		// Combo Asessor
//		SpecialComboColumn spcComboCol = new SpecialComboColumn(cdata.get(0));
//		spcComboCol.setPageSize(10);
//		ArrayColumnData cdataCombo = new ArrayColumnData();
//		cdataCombo = new ArrayColumnData();
//		cdataCombo.add(new MyColumnData("pk_userId", "Asesor", 100));
//		cdataCombo.add(new MyColumnData("name", "Nombre	", 220));
//
//		MyColumnData hidden = new MyColumnData("userTypeId", ColumnType.HIDDEN);
//		hidden.setAssociatedField("userTypeId");
//		cdataCombo.add(hidden);
//
//		spcComboCol.setRqData("UserAccount", cdataCombo);
//		spcComboCol.setFilter("userTypeId", "ASE");
//		configs.add(spcComboCol);
//
//		ComboColumn profileComboColumn = new ComboColumn(cdata.get(1));
//		ArrayColumnData cdataComboProfile = new ArrayColumnData();
//		cdataComboProfile.add(new MyColumnData("pk_geographicZoneId", "ID", 40));
//		cdataComboProfile.add(new MyColumnData("description", "Descripcion", 150));
//		profileComboColumn.setRqData("GeographicZone", cdataComboProfile);
//		configs.add(profileComboColumn);
//
//		configs.add(new NormalColumn(cdata.get(2)));
//
//		configs.add(new ExpireColumnConfig());
//
//		// configs.add(new ShowColumnConfig("Ver","",50));
//
//		ColumnModel cm = new ColumnModel(configs);
//
//		// Filters
//		GridFilters filters = new GridFilters();
//		StringFilter parameterIdFilter = new StringFilter(cdata.getIdFields().get(0));
//		StringFilter subsystemFilter = new StringFilter(cdata.getIdFields().get(1));
//		filters.addFilter(parameterIdFilter);
//		filters.addFilter(subsystemFilter);
//
//		// Content panel
//		EntityContentPanel cp = new EntityContentPanel("Asignacion de Zonas", 500, 300);
//
//		// Grid
//		final EntityEditorGrid grid = new EntityEditorGrid(getStore(), cm);
//		grid.setAutoExpandColumn("pk_userId");
//		grid.addPlugin(filters);
//		cp.add(grid);
//		grid.addListener(Events.Attach, new Listener<BaseEvent>() {
//			public void handleEvent(BaseEvent be) {
//				getStore().sort(cdata.getIdFields().get(0), SortDir.ASC);
//			}
//		});
//
//		// Top tool bar
//		GridToolBar toolBar = new GridToolBar(grid, getStore());
//		cp.setTopComponent(toolBar);
//
//		// Paging tool bar
//		final GridPagingToolBar pagingToolBar = new GridPagingToolBar(PAGE_SIZE, getLoader());
//		cp.setBottomComponent(pagingToolBar);
//
//		add(cp);
//	}
}