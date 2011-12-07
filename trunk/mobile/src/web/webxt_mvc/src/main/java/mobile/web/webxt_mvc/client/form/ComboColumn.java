package mobile.web.webxt_mvc.client.form;

import java.util.List;

import mobile.web.webxt_mvc.client.data.MyHttpProxy;
import mobile.web.webxt_mvc.client.data.MyListStore;
import mobile.web.webxt_mvc.client.data.MyPagingLoader;
import mobile.web.webxt_mvc.client.data.MyProcessConfig;

import com.extjs.gxt.ui.client.data.ModelData;
import com.extjs.gxt.ui.client.data.PagingLoadResult;
import com.extjs.gxt.ui.client.widget.form.ComboBox.TriggerAction;
import com.extjs.gxt.ui.client.widget.form.TextField;
import com.extjs.gxt.ui.client.widget.grid.CellEditor;
import com.extjs.gxt.ui.client.widget.grid.ColumnConfig;

public class ComboColumn extends ColumnConfig {
	
	private final int MIN_LIST_WIDTH = 250;
	
	private int pageSize = 0;
	
	private List<String> lFields;
	
	public ComboColumn(MyColumnData columnData) {
		super();
		setId(columnData.getId());
		setHeader(columnData.getName());
		setWidth(columnData.getWidth());
		TextField<String> text = new TextField<String>();
		text.setAllowBlank(columnData.isAllowBlank());
		text.setMaxLength(columnData.getMaxLength());
	}
	
	public void setRqData(String process, String entity, List<String> lFields) {
		this.lFields = lFields; 
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
		final MyComboBox<ModelData> combo = new MyComboBox<ModelData>();
//		final ComboBox<ModelData> combo = new ComboBox<ModelData>() {
//			@Override
//			public void doQuery(String q, boolean forceAll) {
//				expand();
//			}
//		};

		combo.setForceSelection(true);
		combo.setTriggerAction(TriggerAction.ALL);
		combo.setDisplayField(lFields.get(0));
		combo.setStore(store);
		combo.setMinListWidth(MIN_LIST_WIDTH);
		combo.setTemplate(getTemplate());
		combo.setPageSize(pageSize);

	    combo.setItemSelector("tr.search-item");  
		combo.setEditable(false);
		combo.setForceSelection(true); 
		
		//	combo.setHideTrigger(true);

		// Load values
		//loader.load(0, pageSize);

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
		
		sb.append("<table cellpadding=\"10\" cellspacing=\"0\" role=\"presentation\" class=\"x-component\">");
		sb.append("<tbody>"); 
		
		sb.append("<tr class=\"x-grid3-hd-row\" role=\"presentation\">");
		sb.append("<td class=\"x-grid3-header x-grid3-hd x-grid3-cell x-grid3-td-name \" role=\"presentation\" align=\"left\" style=\"\">");
		sb.append("<div role=\"columnheader\" aria-haspopup=\"false\" class=\"x-grid3-hd-inner x-component\" style=\"width: 70px; height: 14px; \" aria-sort=\"none\">");
		sb.append("<span class=\"x-component\">Tipo</span>");
		sb.append("</div></td>");
		sb.append("<td class=\"x-grid3-header x-grid3-hd x-grid3-cell x-grid3-td-name \" role=\"presentation\" align=\"left\" style=\"\">");
		sb.append("<div role=\"columnheader\" aria-haspopup=\"false\" class=\"x-grid3-hd-inner x-component\" style=\"width: 150px; height: 14px; \" aria-sort=\"none\">");
		sb.append("<span class=\"x-component\">Descripcion</span>");
		sb.append("</div></td>");
		sb.append("</tr>");
		
		sb.append("<div class=\"x-grid3-row\">");
		
		sb.append("<tpl for=\".\">");
		sb.append("<tr class=\"search-item x-grid3-row\">");
		
		for (String field : lFields) {
			sb.append("<td class=\"x-grid3-cell \">");
			sb.append("<div style=\"padding: 2px 2px 2px 3px\" >");
			sb.append("{" + field + "}");
			sb.append("</div></td>");
		}
		
		sb.append("</tr>");
		sb.append("</tpl>");
		sb.append("</div>");
		
		sb.append("</tbody>");
		sb.append("</table>");
		//sb.append("</div>");
		
		return sb.toString();
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

}
