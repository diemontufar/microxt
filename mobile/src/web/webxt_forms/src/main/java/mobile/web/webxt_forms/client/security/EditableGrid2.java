package mobile.web.webxt_forms.client.security;

import java.util.ArrayList;
import java.util.List;

import mobile.web.webxt_forms.client.data.MyHttpProxy;
import mobile.web.webxt_forms.client.data.MyListStore;
import mobile.web.webxt_forms.client.data.MyPagingLoader;
import mobile.web.webxt_forms.client.data.MyProcessConfig;

import com.extjs.gxt.ui.client.Style.HorizontalAlignment;
import com.extjs.gxt.ui.client.Style.SortDir;
import com.extjs.gxt.ui.client.data.BaseModelData;
import com.extjs.gxt.ui.client.data.ModelData;
import com.extjs.gxt.ui.client.data.PagingLoadResult;
import com.extjs.gxt.ui.client.event.BaseEvent;
import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.Events;
import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.LayoutContainer;
import com.extjs.gxt.ui.client.widget.button.Button;
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
import com.extjs.gxt.ui.client.widget.toolbar.PagingToolBar;
import com.extjs.gxt.ui.client.widget.toolbar.ToolBar;
import com.google.gwt.user.client.Element;

public class EditableGrid2 extends LayoutContainer {

	private final Integer PAGE_SIZE = 10;

	@Override
	protected void onRender(Element parent, int index) {
		super.onRender(parent, index);
		setLayout(new FlowLayout(10));
		getAriaSupport().setPresentation(true);

		// Define process
		String process = "A002";
		
		// Define entity
		String entity = "Parameter";
		final List<String> lfields = new ArrayList<String>();
		lfields.add("pk_parameterId");
		lfields.add("subsystemId");
		lfields.add("dataTypeId");
		lfields.add("parameterValue");
		lfields.add("description");
		
		// Config - proxy - loader - store
		MyProcessConfig config = new MyProcessConfig(process, entity, lfields);
		MyHttpProxy<PagingLoadResult<ModelData>> proxy = new MyHttpProxy<PagingLoadResult<ModelData>>();
		final MyPagingLoader<PagingLoadResult<ModelData>> loader = new MyPagingLoader<PagingLoadResult<ModelData>>(
				proxy, config);
		final MyListStore<ModelData> store = new MyListStore<ModelData>(loader);

		// Columns
		List<ColumnConfig> configs = new ArrayList<ColumnConfig>();

		ColumnConfig column = new ColumnConfig();
		column.setId(lfields.get(0));
		column.setHeader("Parameter");
		column.setWidth(100);
		
		TextField<String> text = new TextField<String>();
		text.setAllowBlank(false);
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
	    column.setHeader("Data type");  
	    column.setWidth(70);  
	    column.setEditor(editor);  
	    configs.add(column);  
	    
		column = new ColumnConfig();
		column.setId(lfields.get(3));
		column.setHeader("Value");
		column.setWidth(100);
		text = new TextField<String>();
		text.setAllowBlank(false);
		column.setEditor(new CellEditor(text));
		configs.add(column);

		column = new ColumnConfig();
		column.setId(lfields.get(4));
		column.setHeader("Description");
		column.setWidth(200);
		text = new TextField<String>();
		text.setAllowBlank(false);
		column.setEditor(new CellEditor(text));
		configs.add(column);

		ColumnModel cm = new ColumnModel(configs);
		
		// Filters
		GridFilters filters = new GridFilters();
		StringFilter parameterIdFilter = new StringFilter(lfields.get(0));
		StringFilter subsystemFilter = new StringFilter(lfields.get(1));
		filters.addFilter(parameterIdFilter);
		filters.addFilter(subsystemFilter);
		
		// Content panel
		ContentPanel cp = new ContentPanel();
		cp.setHeading("Entity: " + entity);
		cp.setFrame(true);
		cp.setSize(600, 300);
		cp.setLayout(new FitLayout());

		// Grid
		final EditorGrid<ModelData> grid = new EditorGrid<ModelData>(store, cm);
		grid.setAutoExpandColumn("description");
		grid.setBorders(true);
		grid.getView().setEmptyText("No data");
		//grid.setClicksToEdit(ClicksToEdit.TWO);
		grid.addPlugin(filters);
		grid.setLoadMask(true);  
		grid.setStripeRows(true);
		cp.add(grid);
		grid.addListener(Events.Attach, new Listener<BaseEvent>() {
			public void handleEvent(BaseEvent be) {
				//loader.load(0, PAGE_SIZE);
				store.sort(lfields.get(1), SortDir.ASC);
			}
		});
		
		//store.sort(lfields.get(1), SortDir.ASC);
		
		
		// Top tool bar
		ToolBar toolBar = new ToolBar();
		Button add = new Button("Add Item");
		add.addSelectionListener(new SelectionListener<ButtonEvent>() {
			@Override
			public void componentSelected(ButtonEvent ce) {
				ModelData newModel = new BaseModelData();
				newModel.set(lfields.get(0), null);
				newModel.set(lfields.get(1), null);
				newModel.set(lfields.get(2), null);
				newModel.set(lfields.get(3), null);
				newModel.set(lfields.get(4), null);
				
				grid.stopEditing();
				store.insert(newModel, store.getCount());
				grid.startEditing(store.indexOf(newModel), 0);
			}
		});
		toolBar.add(add);
		cp.setTopComponent(toolBar);

		// Paging tool bar
		final PagingToolBar pagingToolBar = new PagingToolBar(PAGE_SIZE);
		pagingToolBar.bind(loader);
		pagingToolBar.setReuseConfig(false);
		cp.setBottomComponent(pagingToolBar);

		// Reset button
		cp.setButtonAlign(HorizontalAlignment.CENTER);
		cp.addButton(new Button("Reset", new SelectionListener<ButtonEvent>() {
			@Override
			public void componentSelected(ButtonEvent ce) {
				store.rejectChanges();
			}
		}));
		
		// Save botton
		cp.addButton(new Button("Save", new SelectionListener<ButtonEvent>() {
			@Override
			public void componentSelected(ButtonEvent ce) {
				store.commitChanges();
			}
		}));

		add(cp);
	}

	private CellEditor getDataTypeComboEditor() {
		//ListStore<ModelData> store;
		//Store<ModelData> s;
		
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
		final MyProcessConfig config = new MyProcessConfig(process, entity, lfields);
		final MyHttpProxy<PagingLoadResult<ModelData>> proxy = new MyHttpProxy<PagingLoadResult<ModelData>>();
		final MyPagingLoader<PagingLoadResult<ModelData>> loader = new MyPagingLoader<PagingLoadResult<ModelData>>(
				proxy, config);
		final MyListStore<ModelData> store = new MyListStore<ModelData>(loader);
		
		// Combo
		final ComboBox<ModelData> combo = new ComboBox<ModelData>() {
			//@SuppressWarnings("unchecked")
			@Override
			public void doQuery(String q, boolean forceAll) {
				expand();
			}
		};
		
		combo.setForceSelection(true);  
	    combo.setTriggerAction(TriggerAction.ALL);
		combo.setDisplayField(lfields.get(0));
		combo.setStore(store);
		//combo.setPageSize(COMBO_PAGE_SIZE);
		//combo.getPagingToolBar().bind(loader);
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
		//sb.append("<div class='x-combo-list-item'><b>{dataTypeId}</b><ul><il>{description}</il></ul></div>");	
		sb.append("<div class='x-combo-list-item'>{dataTypeId} : {description}</div>");
		sb.append("</tpl>");
		return sb.toString();
	}
	
}