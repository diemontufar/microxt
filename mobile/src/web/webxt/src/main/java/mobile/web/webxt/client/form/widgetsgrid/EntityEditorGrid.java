package mobile.web.webxt.client.form.widgetsgrid;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import mobile.web.webxt.client.data.form.DataSource;
import mobile.web.webxt.client.form.MyFormPanel;
import mobile.web.webxt.client.form.widgets.PersistentField;
import mobile.web.webxt.client.util.WebConverter;

import com.extjs.gxt.ui.client.data.ModelData;
import com.extjs.gxt.ui.client.store.ListStore;
import com.extjs.gxt.ui.client.widget.form.Field;
import com.extjs.gxt.ui.client.widget.grid.CheckColumnConfig;
import com.extjs.gxt.ui.client.widget.grid.ColumnConfig;
import com.extjs.gxt.ui.client.widget.grid.ColumnModel;
import com.extjs.gxt.ui.client.widget.grid.EditorGrid;
import com.extjs.gxt.ui.client.widget.grid.EditorSupport;

public class EntityEditorGrid extends EditorGrid<ModelData> implements DependentGrid {

	private List<Field<?>> ldependency;

	public EntityEditorGrid(ListStore<ModelData> store, ColumnModel cm) {
		super(store, cm);
		setBorders(false);
		getView().setEmptyText("No hay datos");
		setLoadMask(true);
		setStripeRows(true);
		setColumnLines(true);
		setFiresEvents(true);
		
		setStateful(true);

		// Set pluggins
		addPlugins();
	}

	@Override
	protected EditorSupport<ModelData> getEditSupport() {
		return new MyEditorSupport();
	}

	private void addPlugins() {
		for (ColumnConfig column : cm.getColumns()) {
			if (column instanceof CheckColumnConfig) {
				this.addPlugin((CheckColumnConfig) column);
			}
		}
	}

	public void addDependency(Field<?> field) {
		if (ldependency == null) {
			ldependency = new ArrayList<Field<?>>();
		}
		ldependency.add(field);
	}

	/***
	 * Returns true if all required fields are ok, false otherwise
	 */
	public boolean validateDependencies() {
		boolean valid = true;

		if (ldependency == null || ldependency.size() < 1) {
			return valid;
		}

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
					if (f.getData("mobile-type") != null) {
						value = WebConverter.completeValue(value, f.getData("mobile-type"));
					}
					mDependencies.put(pf.getDataSource(), value);
				}
			}
		}
		return mDependencies;
	}
}