package mobile.web.webxt.client.form.widgetsgrid;

import mobile.common.tools.Format;
import mobile.web.webxt.client.form.widgets.MyNumberField;
import mobile.web.webxt.client.util.NumberType;

import com.extjs.gxt.ui.client.Style.HorizontalAlignment;
import com.extjs.gxt.ui.client.data.ModelData;
import com.extjs.gxt.ui.client.store.ListStore;
import com.extjs.gxt.ui.client.widget.grid.CellEditor;
import com.extjs.gxt.ui.client.widget.grid.ColumnConfig;
import com.extjs.gxt.ui.client.widget.grid.ColumnData;
import com.extjs.gxt.ui.client.widget.grid.Grid;
import com.extjs.gxt.ui.client.widget.grid.GridCellRenderer;
import com.extjs.gxt.ui.client.widget.table.NumberCellRenderer;
import com.google.gwt.i18n.client.NumberFormat;

public class NumericColumn extends ColumnConfig {

	MyNumberField numerfield;

	final NumberFormat currency = NumberFormat.getFormat(Format.CURRENCY);
	final NumberFormat decimal = NumberFormat.getFormat(Format.DECIMAL);
	final NumberFormat integer = NumberFormat.getFormat(Format.INTEGER);
	NumberCellRenderer<Grid<ModelData>> currencyRenderer;
	NumberCellRenderer<Grid<ModelData>> decimalRenderer;
	NumberCellRenderer<Grid<ModelData>> integerRenderer;

	public NumericColumn(MyColumnData columnData) {
		super();
		setId(columnData.getId());
		setHeader(columnData.getName());
		setWidth(columnData.getWidth());

		setAlignment(HorizontalAlignment.RIGHT);
		
		// Create field and formatters
		createField(columnData, NumberType.INTEGER);

		numerfield.setAllowBlank(columnData.isAllowBlank());
		numerfield.setMaxLength(columnData.getMaxLength());
		numerfield.setValidateOnBlur(true);
		setEditor(new CellEditor(numerfield));

		// Hidden
		setHidden(!columnData.isVisible());
	}

	public NumericColumn(MyColumnData columnData, NumberType type) {
		super();
		setId(columnData.getId());
		setHeader(columnData.getName());
		setWidth(columnData.getWidth());

		setAlignment(HorizontalAlignment.RIGHT);

		// Create field and formatters
		createField(columnData, type);

		numerfield.setAllowBlank(columnData.isAllowBlank());
		numerfield.setMaxLength(columnData.getMaxLength());
		numerfield.setValidateOnBlur(true);
		setEditor(new CellEditor(numerfield));
		// Hidden
		setHidden(!columnData.isVisible());
	}

	private void createField(MyColumnData columnData, NumberType type) {

		if (type == NumberType.CURRENCY) {
			System.out.println("Es una columna de tipo currency");
			numerfield = new MyNumberField(NumberType.CURRENCY);

			currencyRenderer = new NumberCellRenderer<Grid<ModelData>>(currency);
			GridCellRenderer<ModelData> gridCurrency = new GridCellRenderer<ModelData>() {
				public String render(ModelData model, String property,
						ColumnData config, int rowIndex, int colIndex,
						ListStore<ModelData> store, Grid<ModelData> grid) {
					return currencyRenderer.render(null, property,
							model.get(property));
				}
			};

			setRenderer(gridCurrency);
		}

		if (type == NumberType.DECIMAL) {

			numerfield = new MyNumberField(NumberType.DECIMAL);

			decimalRenderer = new NumberCellRenderer<Grid<ModelData>>(decimal);
			GridCellRenderer<ModelData> gridDecimal = new GridCellRenderer<ModelData>() {
				public Object render(ModelData model, String property,
						ColumnData config, int rowIndex, int colIndex,
						ListStore<ModelData> store, Grid<ModelData> grid) {
					return decimalRenderer.render(null, property,
							model.get(property));
				}
			};
			setRenderer(gridDecimal);
		}

		if (type == NumberType.INTEGER) {

			numerfield = new MyNumberField(NumberType.INTEGER);

			integerRenderer = new NumberCellRenderer<Grid<ModelData>>(integer);
			GridCellRenderer<ModelData> gridInteger = new GridCellRenderer<ModelData>() {
				public Object render(ModelData model, String property,
						ColumnData config, int rowIndex, int colIndex,
						ListStore<ModelData> store, Grid<ModelData> grid) {
					return integerRenderer.render(null, property,
							model.get(property));
				}
			};
			setRenderer(gridInteger);
		}

	}
}
