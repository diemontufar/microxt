package mobile.web.webxt.client.devform;

import java.util.ArrayList;
import java.util.List;

import mobile.web.webxt.client.data.form.Reference;
import mobile.web.webxt.client.form.EntityContentPanel;
import mobile.web.webxt.client.form.MyGeneralForm;
import mobile.web.webxt.client.form.validations.Validate;
import mobile.web.webxt.client.form.widgetsgrid.ArrayColumnData;
import mobile.web.webxt.client.form.widgetsgrid.ComboColumn;
import mobile.web.webxt.client.form.widgetsgrid.EntityEditorGrid;
import mobile.web.webxt.client.form.widgetsgrid.ExpireColumnConfig;
import mobile.web.webxt.client.form.widgetsgrid.GridPagingToolBar;
import mobile.web.webxt.client.form.widgetsgrid.GridToolBar;
import mobile.web.webxt.client.form.widgetsgrid.MyColumnData;
import mobile.web.webxt.client.form.widgetsgrid.NormalColumn;
import mobile.web.webxt.client.util.TextType;

import com.extjs.gxt.ui.client.Style.SortDir;
import com.extjs.gxt.ui.client.data.BaseStringFilterConfig;
import com.extjs.gxt.ui.client.data.FilterConfig;
import com.extjs.gxt.ui.client.event.BaseEvent;
import com.extjs.gxt.ui.client.event.Events;
import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.widget.grid.ColumnConfig;
import com.extjs.gxt.ui.client.widget.grid.ColumnModel;
import com.google.gwt.user.client.Element;

public class C105 extends MyGeneralForm {

	private final static String PROCESS = "C105";
	private final static String ENTITY = "ProductAsessor";
	private final Integer PAGE_SIZE = 5;

	public C105() {
		super(PROCESS, true);
		setReference(ENTITY);
	}

	@Override
	protected void onRender(Element parent, int index) {
		super.onRender(parent, index);

		// Configuration
		final ArrayColumnData cdata = new ArrayColumnData();
		cdata.add(new MyColumnData("pk_userId", "Asesor", 70, 20, false));
		cdata.add(new MyColumnData("pk_productId", "Producto", 70, 50, false));
		cdata.add(new MyColumnData("observations", "Observaciones", 200, 50, true));
		getConfig().setlDataSource(cdata.getDataSources());

		// Columns
		List<ColumnConfig> configs = new ArrayList<ColumnConfig>();

		// Combo Asessor
		
		ComboColumn spcComboCol = new ComboColumn(cdata.get(0));
		Reference refAsessor = new Reference("usr", "UserAccount");
		ArrayColumnData cdataComboAsessor = new ArrayColumnData();
		cdataComboAsessor.add(new MyColumnData("usr", "pk_userId", "Asesor", 70));
		cdataComboAsessor.add(new MyColumnData("usr", "userTypeId", "Tipo", 70));
		cdataComboAsessor.add(new MyColumnData("usr", "name", "Nombre", 200));
		spcComboCol.setQueryData(refAsessor, cdataComboAsessor);
		spcComboCol.getComboBox().setPageSize(10);
		
		String filterField = "userTypeId";

		FilterConfig filter = new BaseStringFilterConfig();
		filter.setField(filterField);
		filter.setComparison("=");
		filter.setValue("ASE");
		
		spcComboCol.getComboBox().addFilter(filter);
		configs.add(spcComboCol);
		
		ComboColumn productComboColumn = new ComboColumn(cdata.get(1));
		Reference refProduct = new Reference("prod", "ProductMicrocredit");
		ArrayColumnData cdataComboPrduct = new ArrayColumnData();
		cdataComboPrduct.add(new MyColumnData("prod", "pk_productId", "Producto", 70));
		cdataComboPrduct.add(new MyColumnData("prod", "description", "Descripcion", 150));
		productComboColumn.setQueryData(refProduct, cdataComboPrduct);
		configs.add(productComboColumn);

		configs.add(new NormalColumn(cdata.get(2),TextType.TEXT,Validate.TEXT));

		configs.add(new ExpireColumnConfig());

		ColumnModel cm = new ColumnModel(configs);

		// Content panel
		EntityContentPanel cp = new EntityContentPanel("Productos por Asesor", 400, 230);

		// Grid
		final EntityEditorGrid grid = new EntityEditorGrid(getStore(), cm);
		grid.setAutoExpandColumn("pk_userId");
		cp.add(grid);
		grid.addListener(Events.Attach, new Listener<BaseEvent>() {
			public void handleEvent(BaseEvent be) {
				getStore().sort(cdata.getIdFields().get(0), SortDir.ASC);
			}
		});

		// Top tool bar
		GridToolBar toolBar = new GridToolBar(grid, getStore());
		cp.setTopComponent(toolBar);

		// Paging tool bar
		final GridPagingToolBar pagingToolBar = new GridPagingToolBar(grid, PAGE_SIZE);
		cp.setBottomComponent(pagingToolBar);

		add(cp);
	}
}