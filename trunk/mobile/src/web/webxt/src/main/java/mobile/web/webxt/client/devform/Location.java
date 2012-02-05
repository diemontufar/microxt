package mobile.web.webxt.client.devform;

import mobile.web.webxt.client.data.form.DataSource;
import mobile.web.webxt.client.data.form.DataSourceType;
import mobile.web.webxt.client.data.form.Dependency;
import mobile.web.webxt.client.data.form.Reference;
import mobile.web.webxt.client.form.widgets.ComboForm;
import mobile.web.webxt.client.form.widgets.InputBox;
import mobile.web.webxt.client.form.widgets.MyLabel;
import mobile.web.webxt.client.form.widgets.RowContainer;
import mobile.web.webxt.client.form.widgetsgrid.ArrayColumnData;
import mobile.web.webxt.client.form.widgetsgrid.MyColumnData;

import com.extjs.gxt.ui.client.widget.ContentPanel;
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
				
		// Pais combo
		final ComboForm countryCombo = new ComboForm(50);
		countryCombo.setDataSource(new DataSource("per", "countryId", DataSourceType.RECORD));

		Reference refCountry = new Reference("con", "Country");
		final ArrayColumnData codata = new ArrayColumnData();
		codata.add(new MyColumnData("con", "pk_countryId", "Id", 70));
		codata.add(new MyColumnData("con", "name", "Nombre", 150));
		countryCombo.setQueryData(refCountry, codata);
		countryCombo.setDisplayField("pk_countryId");
		row.add(countryCombo);

		final InputBox descCountry = new InputBox(90);
		descCountry.setReadOnly(true);
		DataSource ds = new DataSource("Country", "name", DataSourceType.DESCRIPTION);
		ds.addDependency(new Dependency("pk_countryId", "per", "country_Id"));
		
		descCountry.setDataSource(new DataSource("Country", "name", DataSourceType.DESCRIPTION));
		row.add(descCountry);
		
		countryCombo.linkWithField(descCountry, "name");
		
		fieldSet.add(row);
		
		//Provincia
		row = new RowContainer();
		label = new MyLabel("Provincia:", LABEL_WIDTH);
		row.add(label);
				
		// Provincia combo
		final ComboForm provinceCombo = new ComboForm(50);
		provinceCombo.setDataSource(new DataSource("per", "provinceId", DataSourceType.RECORD));

		Reference refProvince = new Reference("pro", "Province");
		final ArrayColumnData prdata = new ArrayColumnData();
		prdata.add(new MyColumnData("pro", "pk_provinceId", "Id", 70));
		prdata.add(new MyColumnData("pro", "name", "Nombre", 150));
		provinceCombo.setQueryData(refProvince, prdata);
		provinceCombo.setDisplayField("pk_provinceId");
		row.add(provinceCombo);

		final InputBox descProvince = new InputBox(90);
		descProvince.setReadOnly(true);
		descProvince.setDataSource(new DataSource("Province", "name", DataSourceType.DESCRIPTION));
		row.add(descProvince);
		
		provinceCombo.linkWithField(descProvince, "name");
		provinceCombo.addDependency(countryCombo);
		
		fieldSet.add(row);
		
		//Ciudad
		row = new RowContainer();
		label = new MyLabel("Ciudad:", LABEL_WIDTH);
		row.add(label);
		
		// Ciudad combo
		final ComboForm cityCombo = new ComboForm(50);
		cityCombo.setDataSource(new DataSource("per", "cityId", DataSourceType.RECORD));

		Reference refCity = new Reference("cit", "City");
		final ArrayColumnData cidata = new ArrayColumnData();
		cidata.add(new MyColumnData("cit", "pk_cityId", "Id", 70));
		cidata.add(new MyColumnData("cit", "name", "Nombre", 150));
		cityCombo.setQueryData(refCity, cidata);
		cityCombo.setDisplayField("pk_cityId");
		row.add(cityCombo);

		final InputBox descCity = new InputBox(90);
		descCity.setReadOnly(true);
		descCity.setDataSource(new DataSource("City", "name", DataSourceType.DESCRIPTION));
		row.add(descCity);
		
		cityCombo.linkWithField(descCity, "name");
		cityCombo.addDependency(provinceCombo);
		
		fieldSet.add(row);
		
		//Parroquia
		row = new RowContainer();
		label = new MyLabel("Parroquia:", LABEL_WIDTH);
		row.add(label);
		
		// Ciudad combo
		final ComboForm districtCombo = new ComboForm(50);
		districtCombo.setDataSource(new DataSource("per", "districtId", DataSourceType.RECORD));

		Reference refDistrict = new Reference("dis", "District");
		final ArrayColumnData didata = new ArrayColumnData();
		didata.add(new MyColumnData("dis", "pk_districtId", "Id", 70));
		didata.add(new MyColumnData("dis", "name", "Nombre", 150));
		districtCombo.setQueryData(refDistrict, didata);
		districtCombo.setDisplayField("pk_cityId");
		row.add(districtCombo);

		final InputBox descDistrict = new InputBox(90);
		descDistrict.setReadOnly(true);
		descDistrict.setDataSource(new DataSource("District", "name", DataSourceType.DESCRIPTION));
		row.add(descDistrict);
		
		districtCombo.linkWithField(descDistrict, "name");
		districtCombo.addDependency(cityCombo);
		
		fieldSet.add(row);
		
		panel.add(fieldSet);
		add(panel);

	}
}