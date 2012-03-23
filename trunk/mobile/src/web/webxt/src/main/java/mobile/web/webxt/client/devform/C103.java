package mobile.web.webxt.client.devform;

import java.util.ArrayList;
import java.util.List;

import mobile.web.webxt.client.data.form.Reference;
import mobile.web.webxt.client.form.EntityContentPanel;
import mobile.web.webxt.client.form.MyGeneralForm;
import mobile.web.webxt.client.form.widgetsgrid.ArrayColumnData;
import mobile.web.webxt.client.form.widgetsgrid.ComboColumn;
import mobile.web.webxt.client.form.widgetsgrid.EntityEditorGrid;
import mobile.web.webxt.client.form.widgetsgrid.ExpireColumnConfig;
import mobile.web.webxt.client.form.widgetsgrid.GridPagingToolBar;
import mobile.web.webxt.client.form.widgetsgrid.GridToolBar;
import mobile.web.webxt.client.form.widgetsgrid.MyColumnData;
import mobile.web.webxt.client.form.widgetsgrid.NormalColumn;

import com.extjs.gxt.ui.client.Style.SortDir;
import com.extjs.gxt.ui.client.data.ModelData;
import com.extjs.gxt.ui.client.event.BaseEvent;
import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.Events;
import com.extjs.gxt.ui.client.event.GridEvent;
import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.store.ListStore;
import com.extjs.gxt.ui.client.widget.BoxComponent;
import com.extjs.gxt.ui.client.widget.Info;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.grid.BufferView;
import com.extjs.gxt.ui.client.widget.grid.ColumnConfig;
import com.extjs.gxt.ui.client.widget.grid.ColumnData;
import com.extjs.gxt.ui.client.widget.grid.ColumnModel;
import com.extjs.gxt.ui.client.widget.grid.Grid;
import com.extjs.gxt.ui.client.widget.grid.GridCellRenderer;
import com.google.gwt.event.logical.shared.AttachEvent;
import com.google.gwt.user.client.Element;

public class C103 extends MyGeneralForm {

	private final static String PROCESS = "C103";
	private final static String ENTITY = "ZoneAsessor";
	private final Integer PAGE_SIZE = 5;

	EntityEditorGrid grid;
	List<String> zonesList;

	public C103() {
		super(PROCESS, true);
		setReference(ENTITY);
	}

	@Override
	protected void onRender(Element parent, int index) {
		super.onRender(parent, index);

		final ArrayColumnData cdata = new ArrayColumnData();
		cdata.add(new MyColumnData("pk_userId", "Asesor", 70, 20, false));
		cdata.add(new MyColumnData("pk_geographicZoneId", "Zona", 70, 6, false));
		cdata.add(new MyColumnData("observations", "Observaciones", 250, 50, true));
		getConfig().setlDataSource(cdata.getDataSources());

		// Column model
		List<ColumnConfig> configs = new ArrayList<ColumnConfig>();

		// Combo Assessor
		final ComboColumn comboAssessor = new ComboColumn(cdata.get(0));
		Reference refProfile = new Reference("ase", "UserAccount");
		ArrayColumnData cdataCombo = new ArrayColumnData();
		cdataCombo.add(new MyColumnData("ase", "pk_userId", "ID", 70));
		cdataCombo.add(new MyColumnData("ase", "name", "Nombre", 200));
		comboAssessor.setQueryData(refProfile, cdataCombo);
		comboAssessor.getComboBox().setPageSize(5);
		comboAssessor.getComboBox().addAttachHandler(new AttachEvent.Handler() {
			public void onAttachOrDetach(AttachEvent event) {
				comboAssessor.getComboBox().addFilter("userTypeId", "ASE");
				comboAssessor.getComboBox().addFilter("userStatusId", "ACT");
			}
		});

		configs.add(comboAssessor);

		ComboColumn profileGeoZone = new ComboColumn(cdata.get(1));
		Reference refGeoZone = new Reference("geo", "GeographicZone");
		ArrayColumnData cdataComboZone = new ArrayColumnData();
		cdataComboZone.add(new MyColumnData("geo", "pk_geographicZoneId", "ID", 40));
		cdataComboZone.add(new MyColumnData("geo", "description", "Descripcion", 150));
		profileGeoZone.setQueryData(refGeoZone, cdataComboZone);
		configs.add(profileGeoZone);

		configs.add(new NormalColumn(cdata.get(2)));

		GridCellRenderer<ModelData> buttonRenderer = new GridCellRenderer<ModelData>() {

			private boolean init;

			public Object render(final ModelData model, String property, ColumnData config, final int rowIndex,
					final int colIndex, ListStore<ModelData> store, final Grid<ModelData> grid) {
				
				if (!init) {
					init = true;
					grid.addListener(Events.ColumnResize, new Listener<GridEvent<ModelData>>() {

						public void handleEvent(GridEvent<ModelData> be) {
							for (int i = 0; i < be.getGrid().getStore().getCount(); i++) {
								if (be.getGrid().getView().getWidget(i, be.getColIndex()) != null
										&& be.getGrid().getView().getWidget(i, be.getColIndex()) instanceof BoxComponent) {
									((BoxComponent) be.getGrid().getView().getWidget(i, be.getColIndex())).setWidth(be
											.getWidth() - 10);
								
								}
							}
						}
					});
				}

				Button b = new Button("Ver", new SelectionListener<ButtonEvent>() {
					@Override
					public void componentSelected(ButtonEvent ce) {

						if (model.get("pk_geographicZoneId") != null && model.get("pk_userId").toString() != null) {
							zonesList = new ArrayList<String>();
							zonesList.add(model.get("pk_geographicZoneId").toString());
							ZonePreview zp = new ZonePreview(model.get("pk_userId").toString(), zonesList);
							zp.show();
						} else {
							Info.display("Error", "Debe colocar informacion completa");
						}

					}
				});
				
				b.setWidth(grid.getColumnModel().getColumnWidth(colIndex) - 10);
				b.setHeight("100%");
				b.setToolTip("Ver Informacion de la Zona Asignada");

				return b;

			}

		};

		ColumnConfig column = new ColumnConfig();
		column.setId("preview");
		column.setHeader("Ver");
		column.setWidth(50);
		column.setRenderer(buttonRenderer);
		configs.add(column);

		configs.add(new ExpireColumnConfig());

		ColumnModel cm = new ColumnModel(configs);

		// Content panel
		EntityContentPanel cp = new EntityContentPanel("Asignacion de Zonas", 400, 230);

		// Grid
		final EntityEditorGrid grid = new EntityEditorGrid(getStore(), cm);
		grid.setAutoExpandColumn("observations");
		
		BufferView view = new BufferView();  
	    view.setRowHeight(23);  
	    grid.setView(view); 
	    
		cp.add(grid);
		grid.addListener(Events.Attach, new Listener<BaseEvent>() {
			public void handleEvent(BaseEvent be) {
				getStore().sort(cdata.getIdFields().get(0), SortDir.ASC);
			}
		});

		
		// Top tool bar
		GridToolBar toolBar = new GridToolBar(grid, getStore());
		cp.setTopComponent(toolBar);

		// Paging tool bar
		final GridPagingToolBar pagingToolBar = new GridPagingToolBar(grid, PAGE_SIZE);
		cp.setBottomComponent(pagingToolBar);

		add(cp);
	}
}