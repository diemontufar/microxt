package mobile.web.webxt_mvc.client.cgrid;

import com.extjs.gxt.ui.client.data.ModelData;
import com.extjs.gxt.ui.client.event.Events;
import com.extjs.gxt.ui.client.event.GridEvent;
import com.extjs.gxt.ui.client.store.Record;
import com.extjs.gxt.ui.client.widget.grid.CellEditor;
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

		System.out.println("EditorSuppor.value");
		System.out.println(value);
		if (grid.fireEvent(Events.ValidateEdit, ge)) {
			if ((ge.getValue() instanceof String)
					&& ge.getValue().toString().indexOf(";") > 0) {
				String[] associations = ge.getValue().toString().split(";");

				boolean isFirst = true;
				for (String assoc : associations) {
					if (isFirst) {
						r.setValid(ge.getProperty(), ed.getField()
								.isValid(true));
						r.set(ge.getProperty(), assoc);
						isFirst = false;
						continue;
					}
					String[] fields = assoc.split(":");
					r.setValid(fields[0], true);
					r.set(fields[0], fields[1]);
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
