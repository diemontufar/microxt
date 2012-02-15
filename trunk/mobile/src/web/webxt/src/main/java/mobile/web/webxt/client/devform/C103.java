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
import com.extjs.gxt.ui.client.event.EventType;
import com.extjs.gxt.ui.client.event.Events;
import com.extjs.gxt.ui.client.event.GridEvent;
import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.store.ListStore;
import com.extjs.gxt.ui.client.widget.BoxComponent;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.grid.CellSelectionModel;
import com.extjs.gxt.ui.client.widget.grid.ColumnConfig;
import com.extjs.gxt.ui.client.widget.grid.ColumnData;
import com.extjs.gxt.ui.client.widget.grid.ColumnModel;
import com.extjs.gxt.ui.client.widget.grid.Grid;
import com.extjs.gxt.ui.client.widget.grid.GridCellRenderer;
import com.extjs.gxt.ui.client.widget.grid.GridSelectionModel;
import com.google.gwt.event.shared.GwtEvent;
import com.google.gwt.user.client.Element;

public class C103 extends MyGeneralForm {

	private final static String PROCESS = "C103";
	private final static String ENTITY = "ZoneAsessor";
	private final Integer PAGE_SIZE = 5;
	
	EntityEditorGrid grid;
	List<String> zonesList = new ArrayList<String>();

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

		// Combo Asessor
		ComboColumn comboAsessor = new ComboColumn(cdata.get(0));
		Reference refProfile = new Reference("ase", "UserAccount");
		ArrayColumnData cdataCombo = new ArrayColumnData();
		cdataCombo.add(new MyColumnData("ase", "pk_userId", "ID", 70));
		cdataCombo.add(new MyColumnData("ase", "name", "Nombre", 200));
		comboAsessor.setQueryData(refProfile, cdataCombo);
		comboAsessor.getComboBox().setPageSize(5);
		comboAsessor.getComboBox().addFilter("userTypeId", "ASE");
		configs.add(comboAsessor);

		ComboColumn profileGeoZone = new ComboColumn(cdata.get(1));
		Reference refGeoZone = new Reference("geo", "GeographicZone");
		ArrayColumnData cdataComboZone = new ArrayColumnData();
		cdataComboZone.add(new MyColumnData("geo","pk_geographicZoneId", "ID", 40));
		cdataComboZone.add(new MyColumnData("geo","description", "Descripcion", 150));
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
		                  ((BoxComponent) be.getGrid().getView().getWidget(i, be.getColIndex())).setWidth(be.getWidth() - 10);  
		                }  
		              }  
		            }  
		          });  
		        }  
		        
		        Button b = new Button("Ver", new SelectionListener<ButtonEvent>() {  
		          @Override  
		          public void componentSelected(ButtonEvent ce) {  

//		        	  grid.getSelectionModel().deselectAll();
//                      grid.setSelectionModel(new GridSelectionModel<ModelData>());
//
//
//		        	  System.out.println(grid.getSelectionModel().getSelectedItem());
		        	  //grid.sets
		        	  
//					  ModelData val = grid.getModel();
					  //System.out.println(grid.getView());
//		        	  String personCode = val.get("pk_userId");
		        	  zonesList.add("234");
//		        	  Info.display("Informacion","Cod. Usuario: "+personCode + " ; Asesor: "+ zonesList );
		        	  ZonePreview zp =new ZonePreview("DIEGO","2",zonesList);
		        	  zp.show();
		          }  
		        });  
		        b.setWidth(grid.getColumnModel().getColumnWidth(colIndex) - 10);  
		        b.setToolTip("Ver Informacion de la Zona Asignada");  
		        
//		        final GridSelectionModel<ModelData> csm = new CellSelectionModel<ModelData>();
//		        grid.setSelectionModel(csm);        
//		        grid.addListener(Events.CellClick, new Listener<GridEvent>() {            
//		            public void handleEvent(GridEvent ge) {
//		            	System.out.println(csm.getSelectedItem().getProperties());
//		            }
//		        });
		  
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