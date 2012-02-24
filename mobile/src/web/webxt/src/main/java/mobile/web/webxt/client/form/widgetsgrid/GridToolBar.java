package mobile.web.webxt.client.form.widgetsgrid;

import mobile.common.message.Item;
import mobile.web.webxt.client.data.MyListStore;
import mobile.web.webxt.client.resources.Resources;

import com.extjs.gxt.ui.client.data.BaseModelData;
import com.extjs.gxt.ui.client.data.ModelData;
import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.grid.Grid;
import com.extjs.gxt.ui.client.widget.toolbar.ToolBar;

public class GridToolBar extends ToolBar {
	private Grid<ModelData> grid;
	private MyListStore store;
	private Button addButton;
	private Button resetButton;
	private Button saveButton;
	private ModelData initModel;

	public int initColumnIndex = 0;

	public GridToolBar(Grid<ModelData> grid, MyListStore store) {
		this(grid, store, new BaseModelData());
	}

	public GridToolBar(Grid<ModelData> grid, MyListStore store, ModelData initModel) {
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
		resetButton = new Button("Resetear", new SelectionListener<ButtonEvent>() {
			@Override
			public void componentSelected(ButtonEvent ce) {
				resetAction();
			}
		});
		resetButton.setIcon(Resources.ICONS.undo());
		this.add(resetButton);

		// Save button
		saveButton = new Button("Guardar", new SelectionListener<ButtonEvent>() {
			@Override
			public void componentSelected(ButtonEvent ce) {
				saveAction();
			}
		});
		saveButton.setIcon(Resources.ICONS.save());
		this.add(saveButton);
	}

	private void addAction() {
		if (grid instanceof EntityEditorGrid) {
			EntityEditorGrid grid = (EntityEditorGrid) this.grid;
			ModelData newModel = new BaseModelData(initModel.getProperties());
			newModel.set(Item.NEW_ITEM, "1");

			grid.stopEditing();
			store.insert(newModel, store.getCount());
			grid.startEditing(store.indexOf(newModel), initColumnIndex);
		}
	}

	private void resetAction() {
		store.rejectChanges();
	}

	private void saveAction() {
		System.out.println("GridToolBar.saveAction");
		store.commitChanges();
	}

	public void enableAddButton(boolean enabled) {
		this.addButton.setEnabled(enabled);
	}

	public void enableResetButton(boolean enabled) {
		this.resetButton.setEnabled(enabled);
	}

	public void enableSaveButton(boolean enabled) {
		this.saveButton.setEnabled(enabled);
	}

}
