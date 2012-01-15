package mobile.web.webxt.client.form.widgetsgrid;

import mobile.message.message.Item;

import com.extjs.gxt.ui.client.widget.form.CheckBox;
import com.extjs.gxt.ui.client.widget.grid.CellEditor;
import com.extjs.gxt.ui.client.widget.grid.CheckColumnConfig;

public class ExpireColumnConfig extends CheckColumnConfig{
	public ExpireColumnConfig() {
		super(Item.EXPIRE_ITEM, "Eliminar", 50);
		CellEditor checkBoxEditor = new CellEditor(new CheckBox());
		this.setEditor(checkBoxEditor);
	}

}
