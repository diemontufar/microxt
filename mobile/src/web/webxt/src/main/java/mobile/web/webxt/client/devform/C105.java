package mobile.web.webxt.client.devform;

import mobile.web.webxt.client.form.MyGeneralForm;

public class C105 extends MyGeneralForm {
//
//	private final static String PROCESS = "C105";
//	private final static String ENTITY = "ProductAsessor";
//	private final Integer PAGE_SIZE = 5;
//
//	public C105() {
//		super(PROCESS, true);
//		setReference(ENTITY);
//	}
//
//	@Override
//	protected void onRender(Element parent, int index) {
//		super.onRender(parent, index);
//
//		// Configuration
//		final ArrayColumnData cdata = new ArrayColumnData();
//		cdata.add(new MyColumnData("pk_userId", "Asesor", 70, 20, false));
//		cdata.add(new MyColumnData("pk_productId", "Producto", 70, 50, false));
//		cdata.add(new MyColumnData("observations", "Observaciones", 200, 50, true));
//		getConfig().setlDataSource(cdata.getDataSources());
//
//		// Columns
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
//		ComboColumn productComboColumn = new ComboColumn(cdata.get(1));
//		Reference refProduct = new Reference("prod", "ProductMicrocredit");
//		ArrayColumnData cdataComboPrduct = new ArrayColumnData();
//		cdataComboPrduct.add(new MyColumnData("prod", "pk_productId", "Producto", 40));
//		cdataComboPrduct.add(new MyColumnData("prod", "description", "Descripcion", 100));
//		productComboColumn.setQueryData(refProduct, cdataComboPrduct);
//		configs.add(productComboColumn);
//
//		configs.add(new NumericColumn(cdata.get(2)));
//
//		configs.add(new ExpireColumnConfig());
//
//		ColumnModel cm = new ColumnModel(configs);
//
//		// Content panel
//		EntityContentPanel cp = new EntityContentPanel("Productos por Asesor", 400, 230);
//
//		// Grid
//		final EntityEditorGrid grid = new EntityEditorGrid(getStore(), cm);
//		grid.setAutoExpandColumn("pk_userId");
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