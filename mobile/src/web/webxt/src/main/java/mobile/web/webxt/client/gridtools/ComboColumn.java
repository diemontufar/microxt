package mobile.web.webxt.client.gridtools;

import mobile.web.webxt.client.data.MyHttpProxy;
import mobile.web.webxt.client.data.MyListStore;
import mobile.web.webxt.client.data.MyPagingLoader;
import mobile.web.webxt.client.data.MyProcessConfig;

import com.extjs.gxt.ui.client.data.ModelData;
import com.extjs.gxt.ui.client.widget.grid.CellEditor;
import com.extjs.gxt.ui.client.widget.grid.ColumnConfig;

public class ComboColumn extends ColumnConfig {

	private int pageSize = 0;

	private String process = "G201"; // General process for list of values

	private int width = 0; // Calculated from cdata

	public ComboColumn(MyColumnData columnData) {
		super(columnData.getId(),columnData.getName(),columnData.getWidth());
	}

	public void setRqData(String entity, ArrayColumnData cdata) {
		CellEditor editor = getComboEditor(process, entity, cdata);
		setEditor(editor);
	}

	private CellEditor getComboEditor(String process, String entity,
			final ArrayColumnData cdata) {
		// Proxy - Loader - Store
		final MyProcessConfig config = new MyProcessConfig(process, entity,
				cdata.getIdFields());
		final MyHttpProxy proxy = new MyHttpProxy();
		final MyPagingLoader loader = new MyPagingLoader(
				proxy, config);
		final MyListStore store = new MyListStore(loader);

		// Combo
		final MyComboBox combo = new MyComboBox();

		combo.setForceSelection(true);
		//combo.setTriggerAction(TriggerAction.ALL);
		combo.setDisplayField(cdata.getIdFields().get(0));
		combo.setStore(store);
		combo.setTemplate(getTemplate(cdata));
		combo.setMinListWidth(width);
		combo.setPageSize(pageSize);

		combo.setItemSelector("tr.search-item");
		combo.setEditable(false);
		combo.setForceSelection(true);

		// combo.setHideTrigger(true);
		
		// Cell editor
		CellEditor editor = new CellEditor(combo) {
			@Override
			public Object preProcessValue(Object value) {
				System.out.println("ComboColumn. Cell Editor. preProcessValue");
				System.out.println(value);
				
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
				return ((ModelData) value).get(cdata.getIdFields().get(0));
			}
		};
		
		return editor;
	}

	private String getTemplate(final ArrayColumnData cdata) {
		StringBuilder sb = new StringBuilder();

		sb.append("<table cellpadding=\"10\" cellspacing=\"0\" role=\"presentation\" class=\"x-component\">");
		sb.append("<tbody>");

		sb.append("<tr class=\"x-grid3-hd-row\" role=\"presentation\">");
		width = 0;
		for (int i = 0; i < cdata.size(); i++) {
			MyColumnData columnData = cdata.get(i);
			sb.append("<td class=\"x-grid3-header x-grid3-hd x-grid3-cell x-grid3-td-name \" role=\"presentation\" align=\"left\" style=\"\">");
			sb.append("<div role=\"columnheader\" aria-haspopup=\"false\" class=\"x-grid3-hd-inner x-component\" style=\"width: "
					+ columnData.getWidth()
					+ "px; height: 14px; \" aria-sort=\"none\">");
			width += columnData.getWidth();
			sb.append("<span class=\"x-component\">" + columnData.getName()
					+ "</span>");
			sb.append("</div></td>");
		}
		sb.append("</tr>");

		sb.append("<div class=\"x-grid3-row\">");

		sb.append("<tpl for=\".\">");
		sb.append("<tr class=\"search-item x-grid3-row\">");

		for (String field : cdata.getIdFields()) {
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

		return sb.toString();
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public String getProcess() {
		return process;
	}

	public void setProcess(String process) {
		this.process = process;
	}

}
