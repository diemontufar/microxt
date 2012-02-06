package mobile.web.webxt.client.form.widgets;

import java.util.Map;

import mobile.web.webxt.client.data.form.DataSource;

import com.extjs.gxt.ui.client.widget.form.Field;

public interface Dependent {

	/***
	 * Adds a dependency field
	 */
	public void addDependency(Field<?> field, String criterion);

	/***
	 * Returns true if all required fields are ok, false otherwise
	 */
	public boolean validateDependencies();

	/**
	 * Returns a map with dependencies data sources and its values
	 */
	public Map<DataSource, String> getDsDependencies();
	
	/**
	 * Collects all dependencies ds directly and indirectly related 
	 */
	public Map<DataSource, String> getDeepDsDependencies();
	
	/**
	 * Iterative data source recollection
	 */
	public void getIterativeDsDependencies(Map<DataSource, String> mOriginal);

	/**
	 * Returns the list of dependencies
	 */
	public Map<String, Field<?>> getMdependency();

	/**
	 * Sets the list of dependencies
	 */
	public void setMdependency(Map<String, Field<?>> mdependency);

}
