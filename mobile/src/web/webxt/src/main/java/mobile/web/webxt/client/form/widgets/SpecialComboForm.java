package mobile.web.webxt.client.form.widgets;

import java.util.ArrayList;
import java.util.List;

import mobile.web.webxt.client.data.MyHttpProxy;
import mobile.web.webxt.client.data.MyListStore;
import mobile.web.webxt.client.data.MyPagingLoader;
import mobile.web.webxt.client.data.MyProcessConfig;
import mobile.web.webxt.client.form.widgetsgrid.ArrayColumnData;
import mobile.web.webxt.client.form.widgetsgrid.MyColumnData;
import mobile.web.webxt.client.form.widgetsgrid.MyColumnData.ColumnType;

import com.extjs.gxt.ui.client.data.FilterConfig;

public class SpecialComboForm extends MyComboBox implements PersistentField {

	private int pageSize = 0;
	private String process = "G201"; // General process for list of values
	private int width = 0; // Calculated from cdata
	private String persistentInfo;
		
	public SpecialComboForm(int width) {
		super();
		setWidth(width);
		setProperties();
	}
	
	public SpecialComboForm(String label) {
		super();
		setFieldLabel(label);
		setProperties();
	}
	
	public SpecialComboForm(int width, String displayField) {
		this(width);
		setDisplayField(displayField);
		setProperties();
	}
	
	public SpecialComboForm(String label, String displayField) {
		this(label);
		setDisplayField(displayField);
		setProperties();
	}
	
	public void setProperties(){
		setForceSelection(true);
		setTriggerAction(TriggerAction.ALL);
		setItemSelector("tr.search-item");
		setPageSize(pageSize);
		setEditable(false);
	}

	public void setRqData(String entity, ArrayColumnData cdata) {
		// Proxy - Loader - Store
		final MyProcessConfig config = new MyProcessConfig(process, entity,cdata.getIdFields());
		final MyHttpProxy proxy = new MyHttpProxy();
		final MyPagingLoader loader = new MyPagingLoader(proxy, config);//loader.load(offset, limit)
		final MyListStore store = new MyListStore(loader);

		// Combo
		setStore(store);
		setTemplate(getTemplate(cdata));
		setMinListWidth(width);
	}
	
	public void addFilter(FilterConfig filter) {
    	MyProcessConfig config = (MyProcessConfig) ((MyPagingLoader) this
				.getStore().getLoader()).getConfig();
    	
    	List<FilterConfig> filters = config.getFilterConfigs(); 
    	if(filters==null){
    		filters  = new ArrayList<FilterConfig>();
    	}
    	
    	boolean exists = false;
    	for (FilterConfig fil : filters) {
			if (fil.getField().compareTo(filter.getField())==0){
				exists = true;
				fil.setValue(filter.getValue());
			}
		}
    	
    	if(!exists){
        	filters.add(filter);
    	}
    	
    	config.setFilterConfigs(filters);
	}

	private String getTemplate(final ArrayColumnData cdata) {
		StringBuilder sb = new StringBuilder();

		sb.append("<table cellpadding=\"10\" cellspacing=\"0\" role=\"presentation\" class=\"x-component\">");
		sb.append("<tbody>");

		sb.append("<tr class=\"x-grid3-hd-row\" role=\"presentation\">");
		width = 0;
		for (int i = 0; i < cdata.size(); i++) {
			MyColumnData columnData = cdata.get(i);

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

	public String getPersistentInfo() {
		return persistentInfo;
	}

	public void setPersistentInfo(String persistentInfo) {
		this.persistentInfo = persistentInfo;
	}

}
