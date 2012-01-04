package mobile.web.webxt.client.components;

import mobile.web.webxt.client.tree.Folder;
import mobile.web.webxt.client.tree.MenuTreePanel;

import com.extjs.gxt.ui.client.data.ModelData;
import com.extjs.gxt.ui.client.store.Store;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.button.IconButton;
import com.extjs.gxt.ui.client.widget.form.StoreFilterField;
import com.extjs.gxt.ui.client.widget.layout.FitLayout;
import com.extjs.gxt.ui.client.widget.toolbar.ToolBar;

public class NavPanel extends ContentPanel {

	private MenuTreePanel menuTreePanel;

	public NavPanel() {
		setHeading("Menu");
		setLayout(new FitLayout());
		
		ToolBar toolBar = new ToolBar();
	    this.setTopComponent(toolBar);
	    
	    IconButton filterBtn = new IconButton("icon-filter");
	    filterBtn.setWidth(20);
	    toolBar.add(filterBtn);
	    
		StoreFilterField<ModelData> filter = new StoreFilterField<ModelData>() {
			@Override
			protected boolean doSelect(Store<ModelData> store,
					ModelData parent, ModelData record, String property,
					String filter) {
				// only match leaf nodes
				if (record instanceof Folder) {
					return false;
				}
				String id = record.get("id");
				String name = record.get("name");
				id = id.toLowerCase();
				name = name.toLowerCase();
				if (id.startsWith(filter.toLowerCase())
						|| name.startsWith(filter.toLowerCase())) {
					return true;
				}
				return false;
			}

		};
		toolBar.add(filter);

	    menuTreePanel = new MenuTreePanel(filter);
	    
		add(menuTreePanel);
	}
	
	public void selectProcess(ModelData process){
		menuTreePanel.selectProcess(process);
	}

}
