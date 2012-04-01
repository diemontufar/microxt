package mobile.web.webxt.client.form.widgets;

import java.util.HashMap;
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
import com.extjs.gxt.ui.client.event.ComponentEvent;
import com.extjs.gxt.ui.client.event.Events;
import com.extjs.gxt.ui.client.event.FieldEvent;
import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.event.SelectionChangedEvent;
import com.extjs.gxt.ui.client.event.SelectionChangedListener;
import com.extjs.gxt.ui.client.widget.form.ComboBox;
import com.extjs.gxt.ui.client.widget.form.Field;
import com.google.gwt.event.dom.client.KeyCodes;

public class MyComboBox extends ComboBox<ModelData> implements Dependent {
	private String process = "G201";
	private int listWidth = 0;
	private DataSource dataSource;
	private Map<String, Field<?>> mdependency;
	private Map<String, Field<?>> mlinks;
	private boolean isLoaded = false;
	private String filteredField = null;

	public MyComboBox() {
		setForceSelection(true);
		setTriggerAction(TriggerAction.QUERY);
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

		if (isEditable()) {
			if (!q.equals(lastQuery)) {
				lastQuery = q;
				if (filteredField == null) {
					filteredField = getDisplayField();
				}
				//getStore().filter(filteredField, q);
				if(q != null && q.length()>0){
					FilterConfig filter = new BaseStringFilterConfig();
					filter.setField(filteredField);
					filter.setValue(q);
					((MyPagingLoader) getStore().getLoader()).getConfig().addFilter(filter);
				}else{
					FilterConfig filter = new BaseStringFilterConfig();
					filter.setField(filteredField);
					filter.setValue(q);
					((MyPagingLoader) getStore().getLoader()).getConfig().removeFilter(filter);
				}
				((PagingLoader) getStore().getLoader()).load(0, getPageSize());
			}
		} else {
			if (!isLoaded) {
				((PagingLoader) getStore().getLoader()).load(0, getPageSize());
				isLoaded = true;
			} else {
				expand();
			}
		}
	}

	@Override
	protected void onTriggerClick(ComponentEvent ce) {
		if(isEditable()){
			super.clearSelections();
		}		
		super.onTriggerClick(ce);
	}
	
	@Override
	protected void onKeyDown(FieldEvent fe) {
		if (fe.getKeyCode() == KeyCodes.KEY_DELETE) {
			isLoaded = false;
		} else {
			super.onKeyDown(fe);
		}
	};

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
		MyProcessConfig config = (MyProcessConfig) ((MyPagingLoader) getStore().getLoader()).getConfig();
		config.addFilter(field, value);
	}

	public void addFilter(FilterConfig filter) {
		MyProcessConfig config = (MyProcessConfig) ((MyPagingLoader) getStore().getLoader()).getConfig();
		config.addFilter(filter);
	}

	public boolean isSomeSelected() {
		if(isEditable()){
			return getValue() != null;
		}else{
			return getValue() != null && isLoaded();
		}
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

	public void addDependency(Field<?> field, String criterionField) {
		if (mdependency == null) {
			mdependency = new HashMap<String, Field<?>>();
		}
		mdependency.put(criterionField, field);

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
		if (mdependency == null || mdependency.size() < 1) {
			return true;
		}

		boolean valid = true;

		// Directly related
		for (String criterion : mdependency.keySet()) {
			Field<?> f = mdependency.get(criterion);
			if (f.getValue() == null) {
				f.markInvalid("Requerido");
				valid = false;
			}
		}

		// Indirectly related
		for (String criterion : mdependency.keySet()) {
			Field<?> f = mdependency.get(criterion);
			if (f.getValue() == null && f instanceof Dependent) {
				((Dependent) f).validateDependencies();
			}
		}

		return valid;
	}

	public Map<DataSource, String> getDsDependencies() {
		if (mdependency == null || mdependency.size() < 1) {
			return null;
		}

		Map<DataSource, String> mDependencies = new HashMap<DataSource, String>();

		// Directly related
		for (String criterion : mdependency.keySet()) {
			Field<?> f = mdependency.get(criterion);
			if (f.getValue() != null) {
				DataSource ds = new DataSource(criterion, DataSourceType.CRITERION);
				String value = MyFormPanel.getValueFromField(f);
				mDependencies.put(ds, value);
			}
		}

		return mDependencies;
	}

	public void getIterativeDsDependencies(Map<DataSource, String> mOriginal) {
		if (mdependency == null || mdependency.size() < 1) {
			return;
		}

		if (mOriginal == null) {
			mOriginal = new HashMap<DataSource, String>();
		}

		mOriginal.putAll(getDsDependencies());

		// Indirectly related
		for (String criterion : mdependency.keySet()) {
			Field<?> f = mdependency.get(criterion);
			if (f.getValue() != null && f instanceof Dependent) {
				((Dependent) f).getIterativeDsDependencies(mOriginal);
			}
		}
	}

	public Map<DataSource, String> getDeepDsDependencies() {
		Map<DataSource, String> mDependencies = new HashMap<DataSource, String>();
		getIterativeDsDependencies(mDependencies);
		return mDependencies;
	}

	private void initValidateDependencies() {
		addListener(Events.BeforeQuery, new Listener<BaseEvent>() {
			public void handleEvent(BaseEvent be) {
				if (validateDependencies()) {
					MyProcessConfig config = (MyProcessConfig) ((MyPagingLoader) getStore().getLoader()).getConfig();
					Map<DataSource, String> map = getDeepDsDependencies();
					if (map != null) {
						for (DataSource ds : map.keySet()) {
							String value = map.get(ds);
							if (value != null) {
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
						+ (columnData.getWidth() - 10) + "px; height: 14px; \" aria-sort=\"none\">");
				listWidth += columnData.getWidth();
				sb.append("<span class=\"x-component\">" + columnData.getName() + "</span>");
				sb.append("</div></td>");
			}
		}
		listWidth += 2;
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

	public Map<String, Field<?>> getMdependency() {
		return mdependency;
	}

	public void setMdependency(Map<String, Field<?>> mdependency) {
		this.mdependency = mdependency;
	}

	public String getFilteredField() {
		return filteredField;
	}

	public void setFilteredField(String filteredField) {
		this.filteredField = filteredField;
	}
}