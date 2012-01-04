package mobile.web.webxt.client.gridtools;

import java.util.ArrayList;
import java.util.List;

import mobile.web.webxt.client.data.MyHttpProxy;
import mobile.web.webxt.client.data.MyListStore;
import mobile.web.webxt.client.data.MyPagingLoader;
import mobile.web.webxt.client.data.MyProcessConfig;
import mobile.web.webxt.client.gridtools.MyColumnData.ColumnType;

import com.extjs.gxt.ui.client.data.BaseFilterConfig;
import com.extjs.gxt.ui.client.data.BaseStringFilterConfig;
import com.extjs.gxt.ui.client.data.FilterConfig;
import com.extjs.gxt.ui.client.data.ModelData;
import com.extjs.gxt.ui.client.widget.grid.CellEditor;
import com.extjs.gxt.ui.client.widget.grid.ColumnConfig;

public class SpecialComboColumn extends ColumnConfig {

	private int pageSize = 0;

	private String process = "G201"; // General process for list of values

	private int width = 0; // Calculated from cdata

	private MyComboBox combo;

	public SpecialComboColumn(ColumnDataInterface columnData) {
		super(columnData.getId(), columnData.getName(), columnData.getWidth());
	}

	public void setRqData(String entity, ArrayColumnData cdata) {
		CellEditor editor = getComboEditor(entity, cdata);
		setEditor(editor);
	}

	private CellEditor getComboEditor(String entity, final ArrayColumnData cdata) {
		// Proxy - Loader - Store
		final MyProcessConfig config = new MyProcessConfig(process, entity,
				cdata.getIdFields());
		final MyHttpProxy proxy = new MyHttpProxy();
		final MyPagingLoader loader = new MyPagingLoader(proxy, config);//loader.load(offset, limit)
		final MyListStore store = new MyListStore(loader);

		// Combo
		// final MyComboBox combo = new MyComboBox();
		combo = new MyComboBox();

		combo.setForceSelection(true);
		// combo.setTriggerAction(TriggerAction.ALL);
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

				String returnValue = "";

				ModelData model = (ModelData) value;

				boolean isFirst = true;
				for (ColumnDataInterface cd : cdata) {
					if (isFirst) {
						returnValue = model.get(cdata.getIdFields().get(0));
						isFirst = false;
					} else if (cd.getAssociatedField() != null) {
						returnValue = returnValue + ";"
								+ cd.getAssociatedField() + ":"
								+ model.get(cd.getId());
					}
				}

				return returnValue;
			}

		};

		return editor;
	}

	public void setFilter(String fieldId, String filter) {
		MyProcessConfig config = (MyProcessConfig) ((MyPagingLoader) combo
				.getStore().getLoader()).getConfig();

		List<FilterConfig> filters = config.getFilterConfigs();
		if (filters == null) {
			System.out.println("Crear");
			filters = new ArrayList<FilterConfig>();
			config.setFilterConfigs(filters);
		}

		BaseFilterConfig newFilter = new BaseStringFilterConfig("", "=",
				filter);
		newFilter.setField(fieldId);
		
		filters.add(newFilter);
	}

	private String getTemplate(final ArrayColumnData cdata) {
		StringBuilder sb = new StringBuilder();

		sb.append("<table cellpadding=\"10\" cellspacing=\"0\" role=\"presentation\" class=\"x-component\">");
		sb.append("<tbody>");

		sb.append("<tr class=\"x-grid3-hd-row\" role=\"presentation\">");
		width = 0;
		for (int i = 0; i < cdata.size(); i++) {
			ColumnDataInterface columnData = cdata.get(i);

			if (columnData.getColumnType() != null
					&& columnData.getColumnType() == ColumnType.HIDDEN) {
				continue;
			}

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
