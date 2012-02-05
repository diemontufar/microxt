package mobile.web.webxt.client.form.widgets;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import mobile.web.webxt.client.data.MyHttpProxy;
import mobile.web.webxt.client.data.MyListStore;
import mobile.web.webxt.client.data.MyPagingLoader;
import mobile.web.webxt.client.data.MyProcessConfig;
import mobile.web.webxt.client.data.form.DataSource;
import mobile.web.webxt.client.data.form.DataSourceType;
import mobile.web.webxt.client.data.form.Reference;
import mobile.web.webxt.client.form.MyFormPanel;
import mobile.web.webxt.client.form.widgetsgrid.ArrayColumnData;
import mobile.web.webxt.client.form.widgetsgrid.MyColumnData;

import com.extjs.gxt.ui.client.data.BaseStringFilterConfig;
import com.extjs.gxt.ui.client.data.FilterConfig;
import com.extjs.gxt.ui.client.data.ModelData;
import com.extjs.gxt.ui.client.data.PagingLoader;
import com.extjs.gxt.ui.client.event.BaseEvent;
import com.extjs.gxt.ui.client.event.Events;
import com.extjs.gxt.ui.client.event.FieldEvent;
import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.event.SelectionChangedEvent;
import com.extjs.gxt.ui.client.event.SelectionChangedListener;
import com.extjs.gxt.ui.client.widget.form.ComboBox;
import com.extjs.gxt.ui.client.widget.form.Field;

public class MyComboBox extends ComboBox<ModelData> {
	private String process = "G201";
	private int listWidth = 0;
	private DataSource dataSource;
	private List<Field<?>> ldependency;
	private Map<String, Field<?>> mlinks;
	private boolean isLoaded = false;

	public MyComboBox() {
		setForceSelection(true);
		setTriggerAction(TriggerAction.ALL);
		setItemSelector("tr.search-item");
		setEditable(false);

		initLinks();
		initValidateDependencies();
	}

	@SuppressWarnings("rawtypes")
	public void doQuery(String q, boolean forceAll) {
		FieldEvent fe = new FieldEvent(this);
		fe.setValue(q);
		if (!fireEvent(Events.BeforeQuery, fe)) {
			return;
		}

		if (!isLoaded) {
			((PagingLoader) getStore().getLoader()).load(0, getPageSize());
			isLoaded = true;
		}
		expand();
	}

	@Override
	public ModelData findModel(String property, String value) {
		return super.findModel(property, value);
	}

	public void setQueryData(Reference reference, ArrayColumnData cdata) {
		// Proxy - Loader - Store
		final MyProcessConfig config = new MyProcessConfig(process, reference, cdata.getDataSources());
		final MyHttpProxy proxy = new MyHttpProxy();
		final MyPagingLoader loader = new MyPagingLoader(proxy, config);
		final MyListStore store = new MyListStore(loader);

		// Combo
		setDisplayField(cdata.get(0).getId());
		setStore(store);
		setTemplate(getTemplateCombo(cdata));
		setMinListWidth(listWidth);
	}

	public void addFilter(String field, String value) {
		FilterConfig filter = new BaseStringFilterConfig();
		filter.setField(field);
		filter.setComparison("=");
		filter.setValue(value);
		addFilter(filter);
	}

	public void addFilter(FilterConfig filter) {
		MyProcessConfig config = (MyProcessConfig) ((MyPagingLoader) this.getStore().getLoader()).getConfig();

		List<FilterConfig> filters = config.getFilterConfigs();
		if (filters == null) {
			filters = new ArrayList<FilterConfig>();
		}

		boolean exists = false;
		for (FilterConfig fil : filters) {
			if (fil.getField().compareTo(filter.getField()) == 0) {
				exists = true;
				fil.setValue(filter.getValue());
			}
		}

		if (!exists) {
			filters.add(filter);
		}

		config.setFilterConfigs(filters);
	}

	public boolean isSomeSelected() {
		return getValue() != null && isLoaded();
	}

	public void linkWithField(Field<?> widget, String field) {
		if (mlinks == null) {
			mlinks = new HashMap<String, Field<?>>();
		}
		mlinks.put(field, widget);
	}

	private void initLinks() {
		addSelectionChangedListener(new SelectionChangedListener<ModelData>() {
			@Override
			public void selectionChanged(SelectionChangedEvent<ModelData> se) {
				if (mlinks == null || mlinks.size() < 1) {
					return;
				}

				if (getValue() == null) {
					for (String field : mlinks.keySet()) {
						Field<?> widget = mlinks.get(field);
						widget.clear();
					}
					return;
				}

				ModelData selected = se.getSelectedItem();
				for (String field : mlinks.keySet()) {
					Field<?> widget = mlinks.get(field);
					Object newValue = selected.get(field);
					MyFormPanel.setValueToField(widget, newValue, false);
				}
			}
		});

	}

	public void addDependency(Field<?> field) {
		if (ldependency == null) {
			ldependency = new ArrayList<Field<?>>();
		}
		ldependency.add(field);

		// Listener
		field.addListener(Events.SelectionChange, new Listener<BaseEvent>() {
			public void handleEvent(BaseEvent be) {
				clear();
				setLoaded(false);
			}
		});

	}

	/***
	 * Returns true if all required fields are ok, false otherwise
	 */
	public boolean validateDependencies() {
		if (ldependency == null || ldependency.size() < 1) {
			return true;
		}

		boolean valid = true;

		for (Field<?> f : ldependency) {
			if (f.getValue() == null) {
				f.markInvalid("Requerido");
				valid = false;
			}
		}

		return valid;
	}

	public Map<DataSource, String> getDsDependencies() {
		if (ldependency == null || ldependency.size() < 1) {
			return null;
		}

		Map<DataSource, String> mDependencies = new HashMap<DataSource, String>();
		for (Field<?> f : ldependency) {
			if (f instanceof PersistentField) {
				PersistentField pf = (PersistentField) f;
				if (pf.getDataSource() != null) {
					String value = MyFormPanel.getValueFromField(f);
					mDependencies.put(pf.getDataSource(), value);
				}
			}
		}
		return mDependencies;
	}

	private void initValidateDependencies() {
		addListener(Events.BeforeQuery, new Listener<BaseEvent>() {
			public void handleEvent(BaseEvent be) {
				if (validateDependencies()) {
					MyProcessConfig config = (MyProcessConfig) ((MyPagingLoader) getStore().getLoader()).getConfig();
					Map<DataSource, String> map = getDsDependencies();
					if (map != null) {
						for (DataSource ds : map.keySet()) {
							String value = map.get(ds);
							if (ds.getType() == DataSourceType.CRITERION && value != null) {
								FilterConfig filter = new BaseStringFilterConfig();
								filter.setField(ds.getField());
								filter.setComparison(ds.getComparator());
								filter.setValue(value);
								config.addFilter(filter);
							}
						}
					}
				} else {
					be.setCancelled(true);
				}
			}
		});

	}

	private String getTemplateCombo(ArrayColumnData cdata) {
		StringBuilder sb = new StringBuilder();

		sb.append("<table cellpadding=\"10\" cellspacing=\"0\" role=\"presentation\" class=\"x-component\">");
		sb.append("<tbody>");

		sb.append("<tr class=\"x-grid3-hd-row\" role=\"presentation\">");
		listWidth = 0;
		for (int i = 0; i < cdata.size(); i++) {
			MyColumnData columnData = cdata.get(i);
			if (columnData.isVisible()) {
				sb.append("<td class=\"x-grid3-header x-grid3-hd x-grid3-cell x-grid3-td-name \" role=\"presentation\" align=\"left\" style=\"\">");
				sb.append("<div role=\"columnheader\" aria-haspopup=\"false\" class=\"x-grid3-hd-inner x-component\" style=\"width: "
						+ columnData.getWidth() + "px; height: 14px; \" aria-sort=\"none\">");
				listWidth += columnData.getWidth();
				sb.append("<span class=\"x-component\">" + columnData.getName() + "</span>");
				sb.append("</div></td>");
			}
		}
		sb.append("</tr>");

		sb.append("<div class=\"x-grid3-row\">");

		sb.append("<tpl for=\".\">");
		sb.append("<tr class=\"search-item x-grid3-row\">");

		for (DataSource ds : cdata.getDataSources()) {
			sb.append("<td class=\"x-grid3-cell \">");
			sb.append("<div style=\"padding: 2px 2px 2px 3px\" >");
			if (ds.getType() == DataSourceType.DESCRIPTION) {
				sb.append("{" + ds.getAlias() + "_" + ds.getField() + "}");
			} else {
				sb.append("{" + ds.getField() + "}");
			}

			sb.append("</div></td>");
		}

		sb.append("</tr>");
		sb.append("</tpl>");
		sb.append("</div>");

		sb.append("</tbody>");
		sb.append("</table>");

		return sb.toString();
	}

	public String getProcess() {
		return process;
	}

	public void setProcess(String process) {
		this.process = process;
	}

	public DataSource getDataSource() {
		return dataSource;
	}

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	public boolean isLoaded() {
		return isLoaded;
	}

	public void setLoaded(boolean isLoaded) {
		this.isLoaded = isLoaded;
	}
}