package mobile.web.webxt_mvc.client.gridtools;

import com.extjs.gxt.ui.client.widget.form.CheckBox;
import com.extjs.gxt.ui.client.widget.grid.CellEditor;
import com.extjs.gxt.ui.client.widget.grid.CheckColumnConfig;

public class ExpireColumnConfig extends CheckColumnConfig{
	public ExpireColumnConfig() {
		super("_expire", "Eliminar", 50);
		CellEditor checkBoxEditor = new CellEditor(new CheckBox());
		this.setEditor(checkBoxEditor);
	}

}
