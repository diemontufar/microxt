package mobile.web.webxt.client.devform;

import mobile.web.webxt.client.data.MyPagingLoader;
import mobile.web.webxt.client.data.MyProcessConfig;
import mobile.web.webxt.client.form.widgets.ComboForm;
import mobile.web.webxt.client.form.widgets.InputBox;
import mobile.web.webxt.client.form.widgets.MyLabel;
import mobile.web.webxt.client.form.widgets.RowContainer;
import mobile.web.webxt.client.form.widgetsgrid.ArrayColumnData;
import mobile.web.webxt.client.form.widgetsgrid.MyColumnData;
import mobile.web.webxt.client.form.widgetsgrid.MyColumnData.ColumnType;

import com.extjs.gxt.ui.client.data.BaseStringFilterConfig;
import com.extjs.gxt.ui.client.data.FilterConfig;
import com.extjs.gxt.ui.client.data.ModelData;
import com.extjs.gxt.ui.client.event.BaseEvent;
import com.extjs.gxt.ui.client.event.Events;
import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.event.SelectionChangedEvent;
import com.extjs.gxt.ui.client.event.SelectionChangedListener;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.Info;
import com.extjs.gxt.ui.client.widget.LayoutContainer;
import com.extjs.gxt.ui.client.widget.form.FieldSet;
import com.extjs.gxt.ui.client.widget.layout.CenterLayout;
import com.google.gwt.user.client.Element;

public class Location extends LayoutContainer {

	FieldSet fieldSet;
	
	RowContainer row;
	MyLabel label;
	String persistentTable="";
	
	final int LABEL_WIDTH = 50;
	final int FIELD_WIDTH = 50;
	final int FIELD_DESC_WIDTH = 115;
	final int widgetWidth = 262;
	final int widgetHeight = 150;
	
	ContentPanel panel=new ContentPanel();
	
	public Location(String persistentTable){
		this.persistentTable=persistentTable;
	}
	
	@Override
	protected void onRender(Element parent, int index) {
		super.onRender(parent, index);
		setLayout(new CenterLayout());
		getAriaSupport().setPresentation(true);
		panel.setWidth(widgetWidth+10);
		panel.setHeight(widgetHeight+10);
		panel.setHeaderVisible(false);
		panel.setBodyBorder(false);
		panel.setLayout(new CenterLayout());
		createForm();
	}
	
	private void createForm(){
		
		fieldSet = new FieldSet();
		fieldSet.setHeading("Localizacion");
		fieldSet.setCollapsible(false);
		fieldSet.setWidth(widgetWidth);
		fieldSet.setHeight(widgetHeight);
		
		//Pais:
		row = new RowContainer();
		label = new MyLabel("Pais:", LABEL_WIDTH);
		row.add(label);
		
		final ComboForm countryCombo = new ComboForm(FIELD_WIDTH, "pk_countryId");
		final ArrayColumnData combodata = new ArrayColumnData();
		combodata.add(new MyColumnData("pk_countryId", "Pais", 70));
		combodata.add(new MyColumnData("name", "Nombre", 150));
		countryCombo.setRqData("Country", combodata);
		row.add(countryCombo);
		
		// Description
		final InputBox descCountry = new InputBox(FIELD_DESC_WIDTH);
		descCountry.setReadOnly(true);
		row.add(descCountry);
		
		countryCombo.addSelectionChangedListener(new SelectionChangedListener<ModelData>() {
			@Override
			public void selectionChanged(SelectionChangedEvent<ModelData> se) {
				ModelData selected = se.getSelectedItem();
				descCountry.setValue(selected.get("name").toString());
			}
		});
		
		fieldSet.add(row);
		
		//Provincia
		row = new RowContainer();
		label = new MyLabel("Provincia:", LABEL_WIDTH);
		row.add(label);
		
		final ComboForm provinceCombo = new ComboForm(FIELD_WIDTH, "pk_provinceId");
		final ArrayColumnData combodata2 = new ArrayColumnData();
		combodata2.add(new MyColumnData("pk_provinceId", "Provincia", 70,20,false));
		combodata2.add(new MyColumnData("name", "Nombre", 150,40,false));
		combodata2.add(new MyColumnData("pk_countryId", ColumnType.HIDDEN));
		provinceCombo.setRqData("Province", combodata2);
		row.add(provinceCombo);
		
		// Description
		final InputBox descProvince = new InputBox(FIELD_DESC_WIDTH);
		descProvince.setReadOnly(true);
		row.add(descProvince);
		
		provinceCombo.addSelectionChangedListener(new SelectionChangedListener<ModelData>() {
			@Override
			public void selectionChanged(SelectionChangedEvent<ModelData> se) {
				ModelData selected = se.getSelectedItem();
				descProvince.setValue(selected.get("name").toString());
			}
		});
		
		fieldSet.add(row);
		
		//Ciudad
		row = new RowContainer();
		label = new MyLabel("Ciudad:", LABEL_WIDTH);
		row.add(label);
		
		final ComboForm cityCombo = new ComboForm(FIELD_WIDTH, "pk_cityId");
		final ArrayColumnData combodata3 = new ArrayColumnData();
		combodata3.add(new MyColumnData("pk_cityId", "Codigo", 70,20,false));
		combodata3.add(new MyColumnData("name", "Nombre", 150,40,false));
		combodata3.add(new MyColumnData("pk_countryId",ColumnType.HIDDEN));
		combodata3.add(new MyColumnData("pk_provinceId",ColumnType.HIDDEN));
		cityCombo.setRqData("City", combodata3);
		row.add(cityCombo);
		
		// Description
		final InputBox descCity = new InputBox(FIELD_DESC_WIDTH);
		descCity.setReadOnly(true);
		row.add(descCity);
		
		cityCombo.addSelectionChangedListener(new SelectionChangedListener<ModelData>() {
			@Override
			public void selectionChanged(SelectionChangedEvent<ModelData> se) {
				ModelData selected = se.getSelectedItem();
				descCity.setValue(selected.get("name").toString());
			}
		});
		
		fieldSet.add(row);
		
		//Parroquia
		row = new RowContainer();
		label = new MyLabel("Parroquia:", LABEL_WIDTH);
		row.add(label);
		
		final ComboForm districtCombo = new ComboForm(FIELD_WIDTH, "pk_districtId");
		final ArrayColumnData combodata4 = new ArrayColumnData();
		combodata4.add(new MyColumnData("pk_districtId", "Codigo", 70,20,false));
		combodata4.add(new MyColumnData("name", "Nombre", 150,40,false));
		combodata4.add(new MyColumnData("pk_countryId",ColumnType.HIDDEN));
		combodata4.add(new MyColumnData("pk_provinceId",ColumnType.HIDDEN));
		combodata4.add(new MyColumnData("pk_cityId",ColumnType.HIDDEN));
		districtCombo.setRqData("District", combodata4);
		row.add(districtCombo);
		
		// Description
		final InputBox descDistrict = new InputBox(FIELD_DESC_WIDTH);
		descDistrict.setReadOnly(true);
		row.add(descDistrict);
		
		districtCombo.addSelectionChangedListener(new SelectionChangedListener<ModelData>() {
			@Override
			public void selectionChanged(SelectionChangedEvent<ModelData> se) {
				ModelData selected = se.getSelectedItem();
				descDistrict.setValue(selected.get("name").toString());
			}
		});
		
		fieldSet.add(row);
		
				
		//LISTENERS:
		
		countryCombo.addSelectionChangedListener(new SelectionChangedListener<ModelData>() {
			@Override
			public void selectionChanged(SelectionChangedEvent<ModelData> se) {

				if (countryCombo.getValue() == null) {
					return;
				}
				
				String filterField = "pk_countryId";

				FilterConfig filter = new BaseStringFilterConfig();
				filter.setField(filterField);
				filter.setComparison("=");
				filter.setValue(countryCombo.getValue().get(filterField).toString());

				provinceCombo.addFilter(filter);
				provinceCombo.setLoaded(false);
				provinceCombo.setValue(null);
				cityCombo.setLoaded(false);
				cityCombo.setValue(null);
				districtCombo.setLoaded(false);
				districtCombo.setValue(null);
			}
		});
		
		provinceCombo.addSelectionChangedListener(new SelectionChangedListener<ModelData>() {
			@Override
			public void selectionChanged(SelectionChangedEvent<ModelData> se) {

				if (provinceCombo.getValue() == null) {
					return;
				}
				
				String filterField = "pk_countryId";
				String filterField2 = "pk_provinceId";

				FilterConfig filter = new BaseStringFilterConfig();
				filter.setField(filterField);
				filter.setComparison("=");
				filter.setValue(countryCombo.getValue().get(filterField).toString());
				
				FilterConfig filter2 = new BaseStringFilterConfig();
				filter.setField(filterField2);
				filter.setComparison("=");
				filter.setValue(provinceCombo.getValue().get(filterField2).toString());

				cityCombo.addFilter(filter);
				cityCombo.addFilter(filter2);
				cityCombo.setLoaded(false);
				cityCombo.setValue(null);
				districtCombo.setLoaded(false);
				districtCombo.setValue(null);
			}
		});
		
		cityCombo.addSelectionChangedListener(new SelectionChangedListener<ModelData>() {
			@Override
			public void selectionChanged(SelectionChangedEvent<ModelData> se) {

				if (cityCombo.getValue() == null) {
					return;
				}
				
				String filterField = "pk_countryId";
				String filterField2 = "pk_provinceId";
				String filterField3 = "pk_cityId";

				FilterConfig filter = new BaseStringFilterConfig();
				filter.setField(filterField);
				filter.setComparison("=");
				filter.setValue(countryCombo.getValue().get(filterField).toString());
				
				FilterConfig filter2 = new BaseStringFilterConfig();
				filter.setField(filterField2);
				filter.setComparison("=");
				filter.setValue(provinceCombo.getValue().get(filterField2).toString());
				
				FilterConfig filter3 = new BaseStringFilterConfig();
				filter.setField(filterField3);
				filter.setComparison("=");
				filter.setValue(cityCombo.getValue().get(filterField3).toString());

				districtCombo.addFilter(filter);
				districtCombo.addFilter(filter2);
				districtCombo.addFilter(filter3);
				districtCombo.setLoaded(false);
				districtCombo.setValue(null);
			}
		});
		
		provinceCombo.addListener(Events.BeforeQuery, new Listener<BaseEvent>() {
			public void handleEvent(BaseEvent be) {
				MyProcessConfig config = (MyProcessConfig) ((MyPagingLoader) provinceCombo
						.getStore().getLoader()).getConfig();
				if (config.getFilterConfigs() == null) {
					be.setCancelled(true);
					Info.display("Advertencia", "Seleccione un Pais");
				}
			}
		});
		
		cityCombo.addListener(Events.BeforeQuery, new Listener<BaseEvent>() {
			public void handleEvent(BaseEvent be) {
				MyProcessConfig config = (MyProcessConfig) ((MyPagingLoader) cityCombo
						.getStore().getLoader()).getConfig();
				if (config.getFilterConfigs() == null) {
					be.setCancelled(true);
					Info.display("Advertencia", "Seleccione una Provincia");
				}
			}
		});
		
		districtCombo.addListener(Events.BeforeQuery, new Listener<BaseEvent>() {
			public void handleEvent(BaseEvent be) {
				MyProcessConfig config = (MyProcessConfig) ((MyPagingLoader) districtCombo
						.getStore().getLoader()).getConfig();
				if (config.getFilterConfigs() == null) {
					be.setCancelled(true);
					Info.display("Advertencia", "Seleccione una Ciudad");
				}
			}
		});
		
		countryCombo.setPersistentInfo(persistentTable+":countryId:1");
		provinceCombo.setPersistentInfo(persistentTable+":provinceId:1");
		cityCombo.setPersistentInfo(persistentTable+":cityId:1");
		districtCombo.setPersistentInfo(persistentTable+":districtId:1");
		
		panel.add(fieldSet);
		add(panel);

	}
}