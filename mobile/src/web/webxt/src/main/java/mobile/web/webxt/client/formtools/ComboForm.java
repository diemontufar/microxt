package mobile.web.webxt.client.formtools;

import mobile.web.webxt.client.data.MyHttpProxy;
import mobile.web.webxt.client.data.MyListStore;
import mobile.web.webxt.client.data.MyPagingLoader;
import mobile.web.webxt.client.data.MyProcessConfig;
import mobile.web.webxt.client.gridtools.ArrayColumnData;
import mobile.web.webxt.client.gridtools.ColumnDataInterface;
import mobile.web.webxt.client.gridtools.MyComboBox;

public class ComboForm extends MyComboBox {

	private int pageSize = 0;

	private String process = "G201";

	private int listWidth = 0;
	
	private String fieldLabel = "";
	
	private String displayField = "";

	public ComboForm(String filedLabel, String displayField) {
		super();
		this.fieldLabel=filedLabel;
		this.displayField=displayField;
	}

	public void setRqData(String entity, ArrayColumnData cdata) {
		// Proxy - Loader - Store
		final MyProcessConfig config = new MyProcessConfig(process, entity,cdata.getIdFields());
		final MyHttpProxy proxy = new MyHttpProxy();
		final MyPagingLoader loader = new MyPagingLoader(proxy, config);//loader.load(offset, limit)
		final MyListStore store = new MyListStore(loader);

		// Combo
		setForceSelection(true);
		setTriggerAction(TriggerAction.ALL);
		setDisplayField(cdata.getIdFields().get(0));
		setStore(store);
		setTemplate(getTemplateCombo(cdata));
		setMinListWidth(listWidth);
		setPageSize(pageSize);
		setFieldLabel(fieldLabel);  
	    setDisplayField(displayField); 

		setItemSelector("tr.search-item");
		setEditable(false);
		setForceSelection(true);
	}

	private String getTemplateCombo(ArrayColumnData cdata) {
		StringBuilder sb = new StringBuilder();

		sb.append("<table cellpadding=\"10\" cellspacing=\"0\" role=\"presentation\" class=\"x-component\">");
		sb.append("<tbody>");

		sb.append("<tr class=\"x-grid3-hd-row\" role=\"presentation\">");
		listWidth = 0;
		for (int i = 0; i < cdata.size(); i++) {
			ColumnDataInterface columnData = cdata.get(i);
			sb.append("<td class=\"x-grid3-header x-grid3-hd x-grid3-cell x-grid3-td-name \" role=\"presentation\" align=\"left\" style=\"\">");
			sb.append("<div role=\"columnheader\" aria-haspopup=\"false\" class=\"x-grid3-hd-inner x-component\" style=\"width: "
					+ columnData.getWidth()
					+ "px; height: 14px; \" aria-sort=\"none\">");
			listWidth += columnData.getWidth();
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
