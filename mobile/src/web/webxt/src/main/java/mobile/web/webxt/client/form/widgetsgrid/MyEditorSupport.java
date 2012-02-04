package mobile.web.webxt.client.form.widgetsgrid;

import java.util.Map;

import com.extjs.gxt.ui.client.data.ModelData;
import com.extjs.gxt.ui.client.event.Events;
import com.extjs.gxt.ui.client.event.GridEvent;
import com.extjs.gxt.ui.client.store.Record;
import com.extjs.gxt.ui.client.widget.grid.CellEditor;
import com.extjs.gxt.ui.client.widget.grid.ColumnConfig;
import com.extjs.gxt.ui.client.widget.grid.EditorSupport;

public class MyEditorSupport extends EditorSupport<ModelData> {

	public MyEditorSupport() {
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
			if(cc instanceof ComboColumn){
				ComboColumn ccC = (ComboColumn) cc;
				r.setValid(ge.getProperty(), ed.getField().isValid(true));
				r.set(ge.getProperty(), ((ModelData)value).get(ccC.getComboBox().getDisplayField()));
				
				// Dependencies
				if(ccC.getMlinks() != null){
					Map<String, ColumnConfig> map= ccC.getMlinks();
					for (String key : map.keySet()) {
						ColumnConfig depCol = map.get(key);
						r.setValid(depCol.getId(), true);
						r.set(depCol.getId(), ((ModelData)value).get(key));
					}
				}
			}else{
				r.setValid(ge.getProperty(), ed.getField().isValid(true));
				r.set(ge.getProperty(), ge.getValue());
			}
			grid.fireEvent(Events.AfterEdit, ge);
		}

		grid.getView().focusCell(ed.row, ed.col, false);
	}
}
