package mobile.web.webxt.client.form.widgetsgrid;

import java.util.Map;

import mobile.web.webxt.client.data.form.DataSource;

import com.extjs.gxt.ui.client.widget.form.Field;

public interface DependentGrid {

	/**
	 * Adds a new dependency in the form of a field. 
	 */
	public void addDependency(Field<?> field);

	/**
	 * Validates that dependencies are not empty
	 */
	public boolean validateDependencies();

	/**
	 * Gets information about data source of dependencies
	 */
	public Map<DataSource, String> getDsDependencies();
}
