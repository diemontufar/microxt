package mobile.web.webxt.client.devform;

import java.util.ArrayList;
import java.util.List;

import mobile.web.webxt.client.data.form.DataSourceType;
import mobile.web.webxt.client.data.form.Reference;
import mobile.web.webxt.client.form.EntityContentPanel;
import mobile.web.webxt.client.form.MyFormPanel;
import mobile.web.webxt.client.form.MyGeneralForm;
import mobile.web.webxt.client.form.widgetsgrid.ArrayColumnData;
import mobile.web.webxt.client.form.widgetsgrid.ComboColumn;
import mobile.web.webxt.client.form.widgetsgrid.EntityEditorGrid;
import mobile.web.webxt.client.form.widgetsgrid.ExpireColumnConfig;
import mobile.web.webxt.client.form.widgetsgrid.GridPagingToolBar;
import mobile.web.webxt.client.form.widgetsgrid.GridToolBar;
import mobile.web.webxt.client.form.widgetsgrid.MyColumnData;
import mobile.web.webxt.client.form.widgetsgrid.NormalColumn;

import com.extjs.gxt.ui.client.data.ModelData;
import com.extjs.gxt.ui.client.event.SelectionChangedEvent;
import com.extjs.gxt.ui.client.event.SelectionChangedListener;
import com.extjs.gxt.ui.client.widget.grid.ColumnConfig;
import com.extjs.gxt.ui.client.widget.grid.ColumnModel;
import com.extjs.gxt.ui.client.widget.layout.FlowLayout;
import com.google.gwt.user.client.Element;

public class B103 extends MyGeneralForm {

	private final static String PROCESS = "B103";
	private final static String ENTITY = "PersonPhone";

	final Integer PAGE_SIZE = 10;

	public B103() {
		super(PROCESS, true);
		setReference(new Reference("perPho", ENTITY));
	}

	@Override
	protected void onRender(Element parent, int index) {
		super.onRender(parent, index);

		// Constants
		final int FORM_WIDTH = 620;
		final int GRID_HEIGHT = 250;

		// Form panel
		final MyFormPanel form = new MyFormPanel(this, "Teléfonos", FORM_WIDTH);
		form.setLayout(new FlowLayout());

		// Header
		PersonHeader header = new PersonHeader("perPho", "pk_personId", DataSourceType.CRITERION);
		form.add(header);

		// Configuration
		final ArrayColumnData cdata = new ArrayColumnData();
		cdata.add(new MyColumnData("pk_phoneSequence", "Sec.", 40, 3, false));
		cdata.add(new MyColumnData("phoneTypeId", "Tipo", 60, 4, false));
		cdata.add(new MyColumnData("areaCode", "Cod Area", 60, 4, false));
		cdata.add(new MyColumnData("phoneNumber", "Telefono", 150, 40, false));
		getConfig().setlDataSource(cdata.getDataSources());

		// Column model
		List<ColumnConfig> configs = new ArrayList<ColumnConfig>();

		NormalColumn a = new NormalColumn(cdata.get(0));
		// a.getEditor().disable();
		configs.add(a);

		ComboColumn phoneTypeComboColumn = new ComboColumn(cdata.get(1));
		Reference refPhoneType = new Reference("ft", "PhoneType");
		ArrayColumnData cdataPhone = new ArrayColumnData();
		cdataPhone.add(new MyColumnData("ft", "pk_phoneTypeId", "Id", 60));
		cdataPhone.add(new MyColumnData("ft", "name", "Nombre", 150));
		phoneTypeComboColumn.setQueryData(refPhoneType, cdataPhone);
		configs.add(phoneTypeComboColumn);

		configs.add(new NormalColumn(cdata.get(2)));

		configs.add(new NormalColumn(cdata.get(3)));

		configs.add(new ExpireColumnConfig());

		ColumnModel cm = new ColumnModel(configs);

		// Content panel
		EntityContentPanel cp = new EntityContentPanel(FORM_WIDTH - 35, GRID_HEIGHT);

		// Grid
		final EntityEditorGrid grid = new EntityEditorGrid(getStore(), cm);
		grid.setBorders(true);
		grid.setAutoExpandColumn("phoneNumber");
		grid.addDependency(header.getPersonCombo());
		cp.add(grid);

		// Top tool bar
		GridToolBar toolBar = new GridToolBar(grid, getStore());
		// toolBar.initColumnIndex=2;
		cp.setTopComponent(toolBar);

		// Paging tool bar
		final GridPagingToolBar pagingToolBar = new GridPagingToolBar(grid, PAGE_SIZE);
		cp.setBottomComponent(pagingToolBar);

		// Events
		header.getPersonCombo().addSelectionChangedListener(new SelectionChangedListener<ModelData>() {
			@Override
			public void selectionChanged(SelectionChangedEvent<ModelData> se) {
				pagingToolBar.refresh();
			}
		});

		form.add(cp);

		add(form);
	}
}