package mobile.entity.manager;

import java.util.ArrayList;
import java.util.List;

import mobile.entity.parameter.Country;
import mobile.entity.parameter.CountryPk;
import mobile.entity.parameter.Parameter;
import mobile.entity.parameter.ParameterPk;
import mobile.entity.parameter.Province;
import mobile.entity.parameter.ProvincePk;
import mobile.entity.schema.GeneralEntity;

import org.junit.Ignore;

/**
 * 
 * Test Objects
 * 
 */
@Ignore
public class TestObjects {
	private List<GeneralEntity> entities;

	public TestObjects() {
		// Define test entities
		Parameter param = new Parameter(new ParameterPk("PRUEBA"), "AS",
				"String", "10", "PARAMTETRO DE PRUEBA");
//		Branch branch = new Branch(new BranchPk("1"), "ASDFASDFASDF");
		Country country = new Country(new CountryPk("AB"), "ASFDASDFASFD");
		Province province = new Province(new ProvincePk("AB", "Cd"),
				"QEWRQEWRQWERQWER");
		// Group entities
		entities = new ArrayList<GeneralEntity>();
		entities.add(param);
//		entities.add(branch);
		entities.add(country);
		entities.add(province);
	}

	public List<GeneralEntity> getEntities() {
		return entities;
	}
}
