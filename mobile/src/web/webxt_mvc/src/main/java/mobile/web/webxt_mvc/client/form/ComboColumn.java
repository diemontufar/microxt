package mobile.web.webxt_mvc.client.form;

import java.util.List;

import mobile.web.webxt_mvc.client.data.MyHttpProxy;
import mobile.web.webxt_mvc.client.data.MyListStore;
import mobile.web.webxt_mvc.client.data.MyPagingLoader;
import mobile.web.webxt_mvc.client.data.MyProcessConfig;

import com.extjs.gxt.ui.client.data.ModelData;
import com.extjs.gxt.ui.client.data.PagingLoadResult;
import com.extjs.gxt.ui.client.widget.form.ComboBox;
import com.extjs.gxt.ui.client.widget.form.ComboBox.TriggerAction;
import com.extjs.gxt.ui.client.widget.form.TextField;
import com.extjs.gxt.ui.client.widget.grid.CellEditor;
import com.extjs.gxt.ui.client.widget.grid.ColumnConfig;

public class ComboColumn extends ColumnConfig {
	
	private final int MIN_LIST_WIDTH = 250;
	
	private int pageSize = 0;
	
	public ComboColumn(String id, String header, int width, int maxLength, boolean allowBlank) {
		super();
		setId(id);
		setHeader(header);
		setWidth(width);
		TextField<String> text = new TextField<String>();
		text.setAllowBlank(allowBlank);
		text.setMaxLength(maxLength);
	}
	
	public void setRqData(String process, String entity, List<String> lFields) {
		CellEditor editor = getComboEditor(process, entity, lFields);
		setEditor(editor);
	}
	
	private CellEditor getComboEditor(String process, String entity, final List<String> lFields) {
		// Proxy - Loader - Store
		final MyProcessConfig config = new MyProcessConfig(process, entity,
				lFields);
		final MyHttpProxy<PagingLoadResult<ModelData>> proxy = new MyHttpProxy<PagingLoadResult<ModelData>>();
		final MyPagingLoader<PagingLoadResult<ModelData>> loader = new MyPagingLoader<PagingLoadResult<ModelData>>(
				proxy, config);
		final MyListStore<ModelData> store = new MyListStore<ModelData>(loader);

		// Combo
		final ComboBox<ModelData> combo = new ComboBox<ModelData>() {
			@Override
			public void doQuery(String q, boolean forceAll) {
				expand();
			}
		};

		combo.setForceSelection(true);
		combo.setTriggerAction(TriggerAction.ALL);
		combo.setDisplayField(lFields.get(0));
		combo.setStore(store);
		combo.setMinListWidth(MIN_LIST_WIDTH);
		combo.setTemplate(getTemplate());
		
		//	combo.setHideTrigger(true);

		// Load values
		loader.load(0, pageSize);

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
				return ((ModelData) value).get(lFields.get(0));
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
