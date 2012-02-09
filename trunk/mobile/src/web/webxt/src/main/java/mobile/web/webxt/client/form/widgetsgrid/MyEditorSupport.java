package mobile.web.webxt.client.form.widgetsgrid;

import java.util.Map;

import mobile.web.webxt.client.data.MyPagingLoader;
import mobile.web.webxt.client.data.MyProcessConfig;
import mobile.web.webxt.client.data.form.DataSource;

import com.extjs.gxt.ui.client.data.BaseStringFilterConfig;
import com.extjs.gxt.ui.client.data.FilterConfig;
import com.extjs.gxt.ui.client.data.ModelData;
import com.extjs.gxt.ui.client.event.Events;
import com.extjs.gxt.ui.client.event.GridEvent;
import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.store.Record;
import com.extjs.gxt.ui.client.widget.grid.CellEditor;
import com.extjs.gxt.ui.client.widget.grid.ColumnConfig;
import com.extjs.gxt.ui.client.widget.grid.EditorGrid;
import com.extjs.gxt.ui.client.widget.grid.EditorSupport;
import com.extjs.gxt.ui.client.widget.grid.Grid;

public class MyEditorSupport extends EditorSupport<ModelData> {

	public MyEditorSupport() {
	}

	@SuppressWarnings("rawtypes")
	@Override
	public void bind(Grid grid) {
		super.bind(grid);

		// Validate dependencies and configure combos
		grid.addListener(Events.BeforeEdit, new Listener<GridEvent<ModelData>>() {
			public void handleEvent(GridEvent<ModelData> ge) {
				EditorGrid<ModelData> grid = (EditorGrid<ModelData>) ge.getGrid();
				ColumnConfig cc = grid.getColumnModel().getColumn(ge.getColIndex());

				if (cc instanceof ComboColumn) {
					ComboColumn ccc = (ComboColumn) cc;
					ccc.getComboBox().setLoaded(false);

					if (ccc.validateDependencies(grid, ge.getRecord(), ge.getRowIndex())) {
						// Directly related
						MyProcessConfig config = (MyProcessConfig) ((MyPagingLoader) ccc.getComboBox().getStore()
								.getLoader()).getConfig();
						Map<DataSource, String> map = ccc.getDeepDsDependencies(ge.getRecord());
						if (map != null) {
							if (config.getFilterConfigs() != null) {
								config.getFilterConfigs().clear();
							}
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

					}
				}
			}
		});

		// Clear dependencies on change
		grid.addListener(Events.AfterEdit, new Listener<GridEvent<ModelData>>() {
			public void handleEvent(GridEvent<ModelData> ge) {
				EditorGrid<ModelData> grid = (EditorGrid<ModelData>) ge.getGrid();
				Record rec = ge.getRecord();
				ColumnConfig cc = grid.getColumnModel().getColumn(ge.getColIndex());

				if (cc instanceof ComboColumn) {
					((ComboColumn) cc).clearDependent(rec);
				}
			}
		});

	}

	@Override
	protected void onEditComplete(CellEditor ed, Object value, Object startValue) {
		editing = false;
		activeEditor = null;
		ed.removeListener(Events.SpecialKey, editorListener);
		ed.removeListener(Events.Complete, editorListener);
		ed.removeListener(Events.CancelEdit, editorListener);
		Record r = activeRecord;
		activeRecord = null;

		String field = cm.getDataIndex(ed.col);
		GridEvent<ModelData> ge = new GridEvent<ModelData>(grid);
		ge.setRecord(r);
		ge.setProperty(field);
		ge.setValue(value);
		ge.setStartValue(startValue);
		ge.setRowIndex(ed.row);
		ge.setColIndex(ed.col);

		if (grid.fireEvent(Events.ValidateEdit, ge)) {
			ColumnConfig cc = cm.getColumn(ed.col);
			if (cc instanceof ComboColumn) {
				ComboColumn ccC = (ComboColumn) cc;

				// Basic set
				r.setValid(ge.getProperty(), ed.getField().isValid(true));
				r.set(ge.getProperty(), ((ModelData) value).get(ccC.getComboBox().getDisplayField()));

				// Links
				if (ccC.getMlinks() != null) {
					Map<String, ColumnConfig> map = ccC.getMlinks();
					for (String key : map.keySet()) {
						ColumnConfig linkCol = map.get(key);
						r.setValid(linkCol.getId(), true);
						r.set(linkCol.getId(), ((ModelData) value).get(key));
					}
				}
			} else {
				r.setValid(ge.getProperty(), ed.getField().isValid(true));
				r.set(ge.getProperty(), ge.getValue());
			}
			grid.fireEvent(Events.AfterEdit, ge);
		}

		grid.getView().focusCell(ed.row, ed.col, false);
	}
}
