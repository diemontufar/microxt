package mobile.web.webxt_mvc.client.devform;

import java.util.ArrayList;
import java.util.List;

import mobile.web.webxt_mvc.client.cgrid.ExpireColumnConfig;
import mobile.web.webxt_mvc.client.cgrid.GridPagingToolBar;
import mobile.web.webxt_mvc.client.cgrid.GridToolBar;
import mobile.web.webxt_mvc.client.data.MyHttpProxy;
import mobile.web.webxt_mvc.client.data.MyListStore;
import mobile.web.webxt_mvc.client.data.MyPagingLoader;
import mobile.web.webxt_mvc.client.data.MyProcessConfig;
import mobile.web.webxt_mvc.client.resources.Resources;

import com.extjs.gxt.ui.client.Style.HorizontalAlignment;
import com.extjs.gxt.ui.client.data.BaseModelData;
import com.extjs.gxt.ui.client.data.ModelData;
import com.extjs.gxt.ui.client.data.PagingLoadResult;
import com.extjs.gxt.ui.client.event.BaseEvent;
import com.extjs.gxt.ui.client.event.Events;
import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.LayoutContainer;
import com.extjs.gxt.ui.client.widget.form.TextField;
import com.extjs.gxt.ui.client.widget.grid.CellEditor;
import com.extjs.gxt.ui.client.widget.grid.ColumnConfig;
import com.extjs.gxt.ui.client.widget.grid.ColumnModel;
import com.extjs.gxt.ui.client.widget.grid.EditorGrid;
import com.extjs.gxt.ui.client.widget.layout.FitLayout;
import com.extjs.gxt.ui.client.widget.layout.FlowLayout;
import com.google.gwt.user.client.Element;

public class A102 extends LayoutContainer {

	private final Integer PAGE_SIZE = 5;
	
	private final String process = "A102";
	
	private final String entity = "UserType";

	@Override
	protected void onRender(Element parent, int index) {
		super.onRender(parent, index);
		setLayout(new FlowLayout(10));
		getAriaSupport().setPresentation(true);

		// Config
		final List<String> lfields = new ArrayList<String>();
		lfields.add("pk_companyId");
		lfields.add("pk_languageId");
		lfields.add("pk_userTypeId");
		lfields.add("name");
		lfields.add("_expire");
		MyProcessConfig config = new MyProcessConfig(process, entity, lfields);
		
		// Proxy - loader - store
		MyHttpProxy<PagingLoadResult<ModelData>> proxy = new MyHttpProxy<PagingLoadResult<ModelData>>();
		final MyPagingLoader<PagingLoadResult<ModelData>> loader = new MyPagingLoader<PagingLoadResult<ModelData>>(
				proxy, config);
		final MyListStore<ModelData> store = new MyListStore<ModelData>(loader);

		// Columns
		List<ColumnConfig> configs = new ArrayList<ColumnConfig>();

		ColumnConfig column = new ColumnConfig();
		column.setId(lfields.get(0));
		column.setHeader("Compania");
		column.setWidth(100);

		TextField<String> text = new TextField<String>();
		text.setAllowBlank(false);
		text.setAutoValidate(true);
		text.setMaxLength(40);
		column.setEditor(new CellEditor(text));
		configs.add(column);

		column = new ColumnConfig();
		column.setId(lfields.get(1));
		column.setHeader("Idioma");
		column.setWidth(50);
		text = new TextField<String>();
		text.setAllowBlank(false);
		text.setMaxLength(2);
		column.setEditor(new CellEditor(text));
		configs.add(column);

		column = new ColumnConfig();
		column.setId(lfields.get(2));
		column.setHeader("Tipo");
		column.setWidth(100);
		text = new TextField<String>();
		text.setAllowBlank(false);
		text.setMaxLength(15);
		column.setEditor(new CellEditor(text));
		configs.add(column);

		column = new ColumnConfig();
		column.setId(lfields.get(3));
		column.setHeader("Descripcion");
		column.setWidth(150);
		text = new TextField<String>();
		text.setAllowBlank(false);
		column.setEditor(new CellEditor(text));
		configs.add(column);

		configs.add(new ExpireColumnConfig());

		ColumnModel cm = new ColumnModel(configs);

		// Content panel
		ContentPanel cp = new ContentPanel();
		cp.setHeading("Tipos de Usuarios");
		cp.setBodyBorder(true);  
	    cp.setIcon(Resources.ICONS.table());
	    cp.setButtonAlign(HorizontalAlignment.CENTER);
	    cp.setLayout(new FitLayout());
		cp.setSize(600, 300);
		
		// Grid
		final EditorGrid<ModelData> grid = new EditorGrid<ModelData>(store, cm);
		grid.setBorders(false);  
		grid.setAutoExpandColumn("name");
		grid.getView().setEmptyText("No hay datos");
		grid.setLoadMask(true);
		grid.setStripeRows(true);
		cp.add(grid);
		grid.addListener(Events.Attach, new Listener<BaseEvent>() {
			public void handleEvent(BaseEvent be) {
				// loader.load(0, PAGE_SIZE);
				// store.sort(lfields.get(1), SortDir.ASC);
			}
		});

		// Top tool bar
		ModelData newModel = new BaseModelData();
		newModel.set(lfields.get(0), null);
		newModel.set(lfields.get(1), null);
		newModel.set(lfields.get(2), null);
		newModel.set(lfields.get(3), null);
		newModel.set(lfields.get(4), null);
		GridToolBar toolBar = new GridToolBar(grid, store, newModel);
		cp.setTopComponent(toolBar);

		// Paging tool bar
		final GridPagingToolBar pagingToolBar = new GridPagingToolBar(PAGE_SIZE,loader);
		cp.setBottomComponent(pagingToolBar);

		add(cp);
	}
}