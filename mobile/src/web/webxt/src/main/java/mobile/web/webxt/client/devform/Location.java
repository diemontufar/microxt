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

import com.extjs.gxt.ui.client.widget.form.FieldSet;
import com.google.gwt.user.client.Element;

public class Location extends FieldSet {

	private int labelWidth = 60;
	private int fieldWidth = 50;
	private int descriptionWidth = 115;
	
	public Location() {
	}
	
	public Location(int labelWidth, int fieldWidth, int descriptionWidth) {
		this.labelWidth = labelWidth;
		this.fieldWidth = fieldWidth;
		this.descriptionWidth = descriptionWidth;
	}
	
	@Override
	protected void onRender(Element parent, int index) {
		super.onRender(parent, index);
		setHeading("Localización");

		// Country:
		RowContainer row = new RowContainer();
		MyLabel label = new MyLabel("Pais:", labelWidth);
		row.add(label);

		// Country combo
		final ComboForm countryCombo = new ComboForm(fieldWidth);
		DataSource ds = new DataSource("per", "countryId", DataSourceType.RECORD);
		countryCombo.setDataSource(ds);
		countryCombo.setAllowBlank(false);

		Reference refCountry = new Reference("con", "Country");
		final ArrayColumnData codata = new ArrayColumnData();
		codata.add(new MyColumnData("con", "pk_countryId", "Id", 70));
		codata.add(new MyColumnData("con", "name", "Nombre", 150));
		countryCombo.setQueryData(refCountry, codata);
		countryCombo.setDisplayField("pk_countryId");
		row.add(countryCombo);

		final InputBox descCountry = new InputBox(descriptionWidth);
		descCountry.setReadOnly(true);
		ds = new DataSource("Country", "name", DataSourceType.DESCRIPTION);
		ds.addDependency(new Dependency("pk_countryId", "per", "countryId"));
		descCountry.setDataSource(ds);
		row.add(descCountry);

		countryCombo.linkWithField(descCountry, "name");

		add(row);

		// Province
		row = new RowContainer();
		label = new MyLabel("Provincia:", labelWidth);
		row.add(label);

		// Province combo
		final ComboForm provinceCombo = new ComboForm(fieldWidth);
		provinceCombo.setDataSource(new DataSource("per", "provinceId", DataSourceType.RECORD));

		Reference refProvince = new Reference("pro", "Province");
		final ArrayColumnData prdata = new ArrayColumnData();
		prdata.add(new MyColumnData("pro", "pk_provinceId", "Id", 70));
		prdata.add(new MyColumnData("pro", "name", "Nombre", 150));
		provinceCombo.setQueryData(refProvince, prdata);
		provinceCombo.setDisplayField("pk_provinceId");
		row.add(provinceCombo);

		final InputBox descProvince = new InputBox(descriptionWidth);
		descProvince.setReadOnly(true);
		ds = new DataSource("Province", "name", DataSourceType.DESCRIPTION);
		ds.addDependency(new Dependency("pk_countryId", "per", "countryId"));
		ds.addDependency(new Dependency("pk_provinceId", "per", "provinceId"));
		descProvince.setDataSource(ds);
		row.add(descProvince);

		provinceCombo.linkWithField(descProvince, "name");
		provinceCombo.addDependency(countryCombo, "pk_countryId");

		add(row);

		// City combo
		row = new RowContainer();
		label = new MyLabel("Ciudad:", labelWidth);
		row.add(label);

		final ComboForm cityCombo = new ComboForm(fieldWidth);
		cityCombo.setDataSource(new DataSource("per", "cityId", DataSourceType.RECORD));

		Reference refCity = new Reference("cit", "City");
		final ArrayColumnData cidata = new ArrayColumnData();
		cidata.add(new MyColumnData("cit", "pk_cityId", "Id", 70));
		cidata.add(new MyColumnData("cit", "name", "Nombre", 150));
		cityCombo.setQueryData(refCity, cidata);
		cityCombo.setDisplayField("pk_cityId");
		row.add(cityCombo);

		final InputBox descCity = new InputBox(descriptionWidth);
		descCity.setReadOnly(true);
		ds = new DataSource("City", "name", DataSourceType.DESCRIPTION);
		ds.addDependency(new Dependency("pk_countryId", "per", "countryId"));
		ds.addDependency(new Dependency("pk_provinceId", "per", "provinceId"));
		ds.addDependency(new Dependency("pk_cityId", "per", "cityId"));
		descCity.setDataSource(ds);
		row.add(descCity);

		cityCombo.linkWithField(descCity, "name");
		cityCombo.addDependency(provinceCombo, "pk_provinceId");

		add(row);

		// District
		row = new RowContainer();
		label = new MyLabel("Parroquia:", labelWidth);
		row.add(label);

		final ComboForm districtCombo = new ComboForm(fieldWidth);
		districtCombo.setDataSource(new DataSource("per", "districtId", DataSourceType.RECORD));

		Reference refDistrict = new Reference("dis", "District");
		final ArrayColumnData didata = new ArrayColumnData();
		didata.add(new MyColumnData("dis", "pk_districtId", "Id", 70));
		didata.add(new MyColumnData("dis", "name", "Nombre", 150));
		districtCombo.setQueryData(refDistrict, didata);
		districtCombo.setDisplayField("pk_districtId");
		row.add(districtCombo);

		final InputBox descDistrict = new InputBox(descriptionWidth);
		descDistrict.setReadOnly(true);
		ds = new DataSource("District", "name", DataSourceType.DESCRIPTION);
		ds.addDependency(new Dependency("pk_countryId", "per", "countryId"));
		ds.addDependency(new Dependency("pk_provinceId", "per", "provinceId"));
		ds.addDependency(new Dependency("pk_cityId", "per", "cityId"));
		ds.addDependency(new Dependency("pk_districtId", "per", "districtId"));
		descDistrict.setDataSource(ds);
		row.add(descDistrict);

		districtCombo.linkWithField(descDistrict, "name");
		districtCombo.addDependency(cityCombo, "pk_cityId");

		add(row);

	}
}