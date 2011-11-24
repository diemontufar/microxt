package mobile.web.webxt_mvc.client.cgrid;

import mobile.web.webxt_mvc.client.data.MyListStore;
import mobile.web.webxt_mvc.client.resources.Resources;

import com.extjs.gxt.ui.client.data.BaseModelData;
import com.extjs.gxt.ui.client.data.ModelData;
import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.grid.EditorGrid;
import com.extjs.gxt.ui.client.widget.toolbar.ToolBar;

public class GridToolBar extends ToolBar {
	private EditorGrid<ModelData> grid;
	private MyListStore<ModelData> store;
	private Button addButton;
	private Button resetButton;
	private Button saveButton;
	private ModelData initModel;

	public GridToolBar(EditorGrid<ModelData> grid,
			MyListStore<ModelData> store, ModelData initModel) {
		this.grid = grid;
		this.store = store;
		this.initModel = initModel;

		// Add button
		addButton = new Button("Agregar");
		addButton.addSelectionListener(new SelectionListener<ButtonEvent>() {
			@Override
			public void componentSelected(ButtonEvent ce) {
				addAction();
			}
		});
		addButton.setIcon(Resources.ICONS.add());
		this.add(addButton);

		// Reset button
		resetButton = new Button("Resetear",
				new SelectionListener<ButtonEvent>() {
					@Override
					public void componentSelected(ButtonEvent ce) {
						resetAction();
					}
				});
		resetButton.setIcon(Resources.ICONS.undo());
		this.add(resetButton);

		// Save button
		saveButton = new Button("Guardar",
				new SelectionListener<ButtonEvent>() {
					@Override
					public void componentSelected(ButtonEvent ce) {
						saveAction();
					}
				});
		saveButton.setIcon(Resources.ICONS.save());
		this.add(saveButton);
	}

	private void addAction() {
		ModelData newModel = new BaseModelData(initModel.getProperties());
		newModel.set("_isNew", "1");

		grid.stopEditing();
		store.insert(newModel, store.getCount());
		grid.startEditing(store.indexOf(newModel), 0);

	}

	private void resetAction() {
		store.rejectChanges();
	}

	private void saveAction() {
		System.out.println("GridToolBar.saveAction");
		store.commitChanges();
	}

}
