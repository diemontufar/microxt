package mobile.web.webxt.client.form.widgetsgrid;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import mobile.web.webxt.client.data.form.DataSource;
import mobile.web.webxt.client.data.form.DataSourceType;
import mobile.web.webxt.client.data.form.Reference;
import mobile.web.webxt.client.form.widgets.MyComboBox;

import com.extjs.gxt.ui.client.store.Record;
import com.extjs.gxt.ui.client.widget.grid.CellEditor;
import com.extjs.gxt.ui.client.widget.grid.ColumnConfig;
import com.extjs.gxt.ui.client.widget.grid.Grid;
import com.google.gwt.dom.client.Element;

public class ComboColumn extends ColumnConfig {

	private MyComboBox combo;
	private Map<String, ColumnConfig> mlinks;
	private Map<String, ColumnConfig> mdependency;
	private List<ColumnConfig> ldependent;

	public ComboColumn(MyColumnData columnData) {
		super(columnData.getId(), columnData.getName(), columnData.getWidth());

		// Combo
		combo = new MyComboBox();
		combo.setDataSource(columnData.getDataSource());
	}

	public void setQueryData(Reference reference, ArrayColumnData cdata) {
		combo.setQueryData(reference, cdata);
		combo.setDisplayField(cdata.getIdFields().get(0));

		CellEditor editor = new CellEditor(combo) {
			@Override
			public Object preProcessValue(Object value) {
				if (value == null)
					return value;
				return combo.getModel();
			}

			@Override
			public Object postProcessValue(Object value) {
				if (value == null)
					return value;
				// return ((ModelData) value).get(cdata.getIdFields().get(0));
				return value;
			}

		};

		setEditor(editor);
	}

	public void linkWithColumn(String field, ColumnConfig cc) {
		if (mlinks == null) {
			mlinks = new HashMap<String, ColumnConfig>();
		}
		mlinks.put(field, cc);
	}

	public void addDependency(ColumnConfig columnConfig, String criterionField) {
		if (mdependency == null) {
			mdependency = new HashMap<String, ColumnConfig>();
		}
		mdependency.put(criterionField, columnConfig);

		// Other side
		if (columnConfig instanceof ComboColumn) {
			((ComboColumn) columnConfig).addDependent(this);
		}
	}

	public void addDependent(ColumnConfig columnConfig) {
		if (ldependent == null) {
			ldependent = new ArrayList<ColumnConfig>();
		}
		ldependent.add(columnConfig);
	}

	/***
	 * Returns true if all required fields are ok, false otherwise
	 */
	@SuppressWarnings("rawtypes")
	public boolean validateDependencies(Grid grid, Record rec, Integer rowIndex) {
		if (mdependency == null || mdependency.size() < 1) {
			return true;
		}

		boolean valid = true;

		// Directly related
		for (String criterion : mdependency.keySet()) {
			ComboColumn depCC = (ComboColumn) mdependency.get(criterion);
			int i = grid.getColumnModel().indexOf(depCC);
			if (rec.get(depCC.getId()) == null) {
				valid = false;
				Element div = grid.getView().getCell(rowIndex, i);
				div.setAttribute("style", "border-style: solid; border-color: #C30; border-width: 1px;");
			}
		}

		// Indirectly related
		for (String criterion : mdependency.keySet()) {
			ComboColumn depCC = (ComboColumn) mdependency.get(criterion);
			if (rec.get(depCC.getId()) == null) {
				depCC.validateDependencies(grid, rec, rowIndex);
			}
		}

		return valid;
	}

	public Map<DataSource, String> getDsDependencies(Record rec) {
		if (mdependency == null || mdependency.size() < 1) {
			return null;
		}

		Map<DataSource, String> mDependencies = new HashMap<DataSource, String>();

		// Directly related
		for (String criterion : mdependency.keySet()) {
			ComboColumn col = (ComboColumn) mdependency.get(criterion);
			if (rec.get(col.getId()) != null) {
				DataSource ds = new DataSource(criterion, DataSourceType.CRITERION);
				String value = (String) rec.get(col.getId());
				mDependencies.put(ds, value);
			}
		}

		return mDependencies;
	}

	public void getIterativeDsDependencies(Map<DataSource, String> mOriginal, Record rec) {
		if (mdependency == null || mdependency.size() < 1) {
			return;
		}

		if (mOriginal == null) {
			mOriginal = new HashMap<DataSource, String>();
		}

		mOriginal.putAll(getDsDependencies(rec));

		// Indirectly related
		for (String criterion : mdependency.keySet()) {
			ComboColumn cc = (ComboColumn) mdependency.get(criterion);
			if (rec.get(cc.getId()) != null) {
				cc.getIterativeDsDependencies(mOriginal, rec);
			}
		}
	}

	public Map<DataSource, String> getDeepDsDependencies(Record rec) {
		Map<DataSource, String> mDependencies = new HashMap<DataSource, String>();
		getIterativeDsDependencies(mDependencies, rec);
		return mDependencies;
	}

	public void clearCell(Record rec) {
		rec.set(getId(), null);
	}

	public void clearDependent(Record rec) {
		for (ColumnConfig cc : getLdependent()) {
			((ComboColumn) cc).clearCell(rec);
		}
		for (ColumnConfig cc : getLdependent()) {
			((ComboColumn) cc).clearDependent(rec);
		}
	}

	public MyComboBox getComboBox() {
		return combo;
	}

	public Map<String, ColumnConfig> getMlinks() {
		return mlinks;
	}

	public void setMlinks(Map<String, ColumnConfig> mlinks) {
		this.mlinks = mlinks;
	}

	public Map<String, ColumnConfig> getMdependency() {
		return mdependency;
	}

	public void setMdependency(Map<String, ColumnConfig> mdependency) {
		this.mdependency = mdependency;
	}

	public List<ColumnConfig> getLdependent() {
		return ldependent;
	}

	public void setLdependent(List<ColumnConfig> ldependencyNodes) {
		this.ldependent = ldependencyNodes;
	}
}