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
import com.extjs.gxt.ui.client.widget.form.ComboBox;
import com.extjs.gxt.ui.client.widget.form.ComboBox.TriggerAction;
import com.extjs.gxt.ui.client.widget.form.TextField;
import com.extjs.gxt.ui.client.widget.grid.CellEditor;
import com.extjs.gxt.ui.client.widget.grid.ColumnConfig;
import com.extjs.gxt.ui.client.widget.grid.ColumnModel;
import com.extjs.gxt.ui.client.widget.grid.EditorGrid;
import com.extjs.gxt.ui.client.widget.grid.filters.GridFilters;
import com.extjs.gxt.ui.client.widget.grid.filters.StringFilter;
import com.extjs.gxt.ui.client.widget.layout.FitLayout;
import com.extjs.gxt.ui.client.widget.layout.FlowLayout;
import com.google.gwt.user.client.Element;

public class EditableGrid2 extends LayoutContainer {

	private final Integer PAGE_SIZE = 10;

	@Override
	protected void onRender(Element parent, int index) {
		super.onRender(parent, index);
		setLayout(new FlowLayout(10));
		getAriaSupport().setPresentation(true);

		// Config
		String process = "A002";
		String entity = "Parameter";
		final List<String> lfields = new ArrayList<String>();
		lfields.add("pk_parameterId");
		lfields.add("subsystemId");
		lfields.add("dataTypeId");
		lfields.add("parameterValue");
		lfields.add("description");
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
		column.setHeader("Parametro");
		column.setWidth(100);

		TextField<String> text = new TextField<String>();
		text.setAllowBlank(false);
		text.setAutoValidate(true);
		text.setMaxLength(40);
		column.setEditor(new CellEditor(text));
		configs.add(column);

		column = new ColumnConfig();
		column.setId(lfields.get(1));
		column.setHeader("Sub");
		column.setWidth(40);
		text = new TextField<String>();
		text.setAllowBlank(false);
		text.setMaxLength(2);
		column.setEditor(new CellEditor(text));
		configs.add(column);

		CellEditor editor = getDataTypeComboEditor();
		column = new ColumnConfig();
		column.setId(lfields.get(2));
		column.setHeader("Tipo");
		column.setWidth(70);
		column.setEditor(editor);
		configs.add(column);

		column = new ColumnConfig();
		column.setId(lfields.get(3));
		column.setHeader("Valor");
		column.setWidth(100);
		text = new TextField<String>();
		text.setAllowBlank(false);
		column.setEditor(new CellEditor(text));
		configs.add(column);

		column = new ColumnConfig();
		column.setId(lfields.get(4));
		column.setHeader("Descripcion");
		column.setWidth(200);
		text = new TextField<String>();
		text.setAllowBlank(false);
		column.setEditor(new CellEditor(text));
		configs.add(column);

		configs.add(new ExpireColumnConfig());

		ColumnModel cm = new ColumnModel(configs);

		// Filters
		GridFilters filters = new GridFilters();
		StringFilter parameterIdFilter = new StringFilter(lfields.get(0));
		StringFilter subsystemFilter = new StringFilter(lfields.get(1));
		filters.addFilter(parameterIdFilter);
		filters.addFilter(subsystemFilter);

		// Content panel
		ContentPanel cp = new ContentPanel();
		cp.setHeading("Parámetros");
		cp.setBodyBorder(true);  
	    cp.setIcon(Resources.ICONS.table());
	    cp.setButtonAlign(HorizontalAlignment.CENTER);
	    cp.setLayout(new FitLayout());
		cp.setSize(600, 300);
		
		// Grid
		final EditorGrid<ModelData> grid = new EditorGrid<ModelData>(store, cm);
		grid.setBorders(false);  
		grid.setAutoExpandColumn("description");
		grid.getView().setEmptyText("No hay datos");
		grid.addPlugin(filters);
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
		newModel.set(lfields.get(5), null);
		GridToolBar toolBar = new GridToolBar(grid, store, newModel);
		cp.setTopComponent(toolBar);

		// Paging tool bar
		final GridPagingToolBar pagingToolBar = new GridPagingToolBar(PAGE_SIZE,loader);
		cp.setBottomComponent(pagingToolBar);

		add(cp);
	}

	private CellEditor getDataTypeComboEditor() {
		// ListStore<ModelData> store;
		// Store<ModelData> s;

		// Define process
		String process = "A002";

		// Define entity
		String entity = "DataType";
		final List<String> lfields = new ArrayList<String>();
		lfields.add("dataTypeId");
		lfields.add("description");

		// Page size
		final int COMBO_PAGE_SIZE = 0;

		// Proxy - Loader - Store
		final MyProcessConfig config = new MyProcessConfig(process, entity,
				lfields);
		final MyHttpProxy<PagingLoadResult<ModelData>> proxy = new MyHttpProxy<PagingLoadResult<ModelData>>();
		final MyPagingLoader<PagingLoadResult<ModelData>> loader = new MyPagingLoader<PagingLoadResult<ModelData>>(
				proxy, config);
		final MyListStore<ModelData> store = new MyListStore<ModelData>(loader);

		// Combo
		final ComboBox<ModelData> combo = new ComboBox<ModelData>() {
			// @SuppressWarnings("unchecked")
			@Override
			public void doQuery(String q, boolean forceAll) {
				expand();
			}
		};

		combo.setForceSelection(true);
		combo.setTriggerAction(TriggerAction.ALL);
		combo.setDisplayField(lfields.get(0));
		combo.setStore(store);
		// combo.setPageSize(COMBO_PAGE_SIZE);
		// combo.getPagingToolBar().bind(loader);
		combo.setMinListWidth(250);
		combo.setTemplate(getTemplate());

		// Load values
		loader.load(0, COMBO_PAGE_SIZE);

		// Cell editor
		CellEditor editor = new CellEditor(combo) {
			@Override
			public Object preProcessValue(Object value) {
				if (value == null) {
					return value;
				}
				return combo.getModel();
			}

			@Override
			public Object postProcessValue(Object value) {
				if (value == null) {
					return value;
				}
				return ((ModelData) value).get("dataTypeId");
			}
		};

		return editor;
	}

	private String getTemplate() {
		StringBuilder sb = new StringBuilder();
		sb.append("<tpl for=\".\">");
		// sb.append("<div class='x-combo-list-item'><b>{dataTypeId}</b><ul><il>{description}</il></ul></div>");
		sb.append("<div class='x-combo-list-item'>{dataTypeId} : {description}</div>");
		sb.append("</tpl>");
		return sb.toString();
	}

}