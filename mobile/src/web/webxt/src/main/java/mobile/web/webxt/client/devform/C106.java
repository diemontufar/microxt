package mobile.web.webxt.client.devform;

import java.util.ArrayList;
import java.util.List;

import mobile.web.webxt.client.MobileConstants;
import mobile.web.webxt.client.data.form.DataSource;
import mobile.web.webxt.client.data.form.DataSourceType;
import mobile.web.webxt.client.form.EntityContentPanel;
import mobile.web.webxt.client.form.MyFormPanel;
import mobile.web.webxt.client.form.MyGeneralForm;
import mobile.web.webxt.client.form.validations.Validate;
import mobile.web.webxt.client.form.widgets.InputBox;
import mobile.web.webxt.client.form.widgets.MyLabel;
import mobile.web.webxt.client.form.widgets.RowContainer;
import mobile.web.webxt.client.form.widgetsgrid.ArrayColumnData;
import mobile.web.webxt.client.form.widgetsgrid.EntityEditorGrid;
import mobile.web.webxt.client.form.widgetsgrid.GridPagingToolBar;
import mobile.web.webxt.client.form.widgetsgrid.MyColumnData;
import mobile.web.webxt.client.form.widgetsgrid.NormalColumn;
import mobile.web.webxt.client.mvc.AppEvents;

import com.extjs.gxt.ui.client.Registry;
import com.extjs.gxt.ui.client.Style.HorizontalAlignment;
import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.Events;
import com.extjs.gxt.ui.client.event.FieldEvent;
import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.mvc.AppEvent;
import com.extjs.gxt.ui.client.mvc.Dispatcher;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.grid.ColumnConfig;
import com.extjs.gxt.ui.client.widget.grid.ColumnModel;
import com.extjs.gxt.ui.client.widget.layout.FlowLayout;
import com.google.gwt.user.client.Element;

public class C106 extends MyGeneralForm {

	//Zone Preview Form - Used by the Asessor to check for his asigned zones
	
	private final static String PROCESS = "C203";
	private final static String ENTITY = "ZoneAsessor";
	private final Integer PAGE_SIZE = 5;
	
	MyFormPanel form;
	RowContainer row;
	MyLabel label;
	InputBox asessorCode;
	
	public C106() {
		super(PROCESS, true);
		setReference(ENTITY);
	}
	
	@Override
	protected void onRender(Element parent, int index) {
		super.onRender(parent, index);
		createPanel();
		asessorCode.setValue(Registry.get(MobileConstants.USER).toString());
	}
	
	public void createPanel(){
		
		// Constants
		final int FORM_WIDTH = 430;
		final int LABEL_WIDTH = 50;

		// Super Form
		final MyFormPanel form = new MyFormPanel(this, "Zonas Asignadas", FORM_WIDTH);
		form.setLayout(new FlowLayout());
		form.setButtonAlign(HorizontalAlignment.CENTER);

		// Header
		// Filter: Asessor
		row = new RowContainer();
		row.setStyleAttribute("margin-bottom", "10px");
		label = new MyLabel("Asesor:", LABEL_WIDTH);
		row.add(label);
		
		asessorCode = new InputBox(120);
		asessorCode.setDataSource(new DataSource("UserAccess", "pk_userId", DataSourceType.CRITERION));
		asessorCode.setFireChangeEventOnSetValue(true);
		asessorCode.setReadOnly(true);
				
		row.add(asessorCode);
		
		row.add(asessorCode);
		
		form.add(row);
		
		// Main grid
		// Configuration
		final ArrayColumnData cdata = new ArrayColumnData();
		cdata.add(new MyColumnData("pk_userId", "Usuario", 70, 40, false));
		cdata.add(new MyColumnData("pk_geographicZoneId", "Zona", 70, 40, false));
		cdata.add(new MyColumnData("observations", "Observaciones", 150, 40, false));
		getConfig().setlDataSource(cdata.getDataSources());

		// Columns
		List<ColumnConfig> configs = new ArrayList<ColumnConfig>();
		
		NormalColumn code = new NormalColumn(cdata.get(0), Validate.TEXT);
		code.setHidden(true);
		
		configs.add(code);
		configs.add(new NormalColumn(cdata.get(1), Validate.TEXT));
		configs.add(new NormalColumn(cdata.get(2), Validate.TEXT));
		ColumnModel cm = new ColumnModel(configs);

		// Content panel
		EntityContentPanel gridPanel = new EntityContentPanel(400, 230);

		// Grid
		final EntityEditorGrid grid = new EntityEditorGrid(getStore(), cm);
		grid.setAutoExpandColumn("observations");
		grid.setBorders(true);
		grid.addDependency(asessorCode);
		gridPanel.add(grid);

		// Paging tool bar
		final GridPagingToolBar pagingToolBar = new GridPagingToolBar(grid, PAGE_SIZE);
		gridPanel.setBottomComponent(pagingToolBar);

		form.add(gridPanel);

		asessorCode.addListener(Events.Change, new Listener<FieldEvent>() {
			public void handleEvent(FieldEvent e) {
				if (e.getValue() != null) {
					if (asessorCode.getValue()!=null) {
						pagingToolBar.refresh();
					} else {
						grid.getStore().removeAll();
					}
				}
			}
		});
		
		form.addButton(new Button("Ver Mapa", new SelectionListener<ButtonEvent>() {
			
			List<String> list = new ArrayList<String>();
			
			@Override
			public void componentSelected(ButtonEvent ce) {

				for (int i=0;i<grid.getStore().getCount();i++){
					list.add(grid.getStore().getAt(i).get("pk_geographicZoneId").toString());
				}
				
				if (list.isEmpty()){
					Dispatcher.forwardEvent(new AppEvent(AppEvents.UserNotification,
					"No existen Zonas Asignadas al usuario: "+Registry.get(MobileConstants.USER).toString()));
				}else{
					ZonePreview zonesprev = new ZonePreview(asessorCode.getValue(),list);
					zonesprev.show();
				}
			}
		}));

		add(form);
				
	}
}