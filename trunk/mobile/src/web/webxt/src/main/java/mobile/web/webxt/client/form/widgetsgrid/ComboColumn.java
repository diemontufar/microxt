package mobile.web.webxt.client.form.widgetsgrid;

import java.util.HashMap;
import java.util.Map;

import mobile.web.webxt.client.data.form.Reference;
import mobile.web.webxt.client.form.widgets.MyComboBox;

import com.extjs.gxt.ui.client.widget.grid.CellEditor;
import com.extjs.gxt.ui.client.widget.grid.ColumnConfig;

public class ComboColumn extends ColumnConfig {

	private MyComboBox combo;
	private Map<String, ColumnConfig> mlinks;

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

	public MyComboBox getComboBox() {
		return combo;
	}

	public Map<String, ColumnConfig> getMlinks() {
		return mlinks;
	}

	public void setMlinks(Map<String, ColumnConfig> mlinks) {
		this.mlinks = mlinks;
	}
}