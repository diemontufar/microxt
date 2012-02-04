package mobile.web.webxt.client.data.form;

import java.util.ArrayList;
import java.util.List;

/**
 * Clase que representa un origen de datos.
 * 
 * @author FitBank
 * @version 2.0
 */
public class DataSource {

	private String alias = "";

	private String field = "";

	private DataSourceType type = DataSourceType.NONE;

	private String comparator = "=";

	private List<Dependency> dependencies = new ArrayList<Dependency>();

	public DataSource() {
	}

	public DataSource(String field) {
		this.field = field;
		this.type = DataSourceType.RECORD;
	}
	
	public DataSource(String alias, String field) {
		this.alias = alias;
		this.field = field;
		this.type = DataSourceType.RECORD;
	}

	public DataSource(String alias, String field, DataSourceType type) {
		this.alias = alias;
		this.field = field;
		this.type = type;
	}
	
	public DataSource(String field, DataSourceType type) {
		this.field = field;
		this.type = type;
	}

	// ////////////////////////////////////////////////////////
	// Getters y setters
	// ////////////////////////////////////////////////////////

	public String getAlias() {
		return alias;
	}

	public void setAlias(String alias) {
		this.alias = alias;
	}

	public String getField() {
		return field;
	}

	public void setField(String campo) {
		this.field = campo;
	}

	public DataSourceType getType() {
		return type;
	}

	public void setType(DataSourceType tipo) {
		this.type = tipo;
	}

	public String getComparator() {
		return comparator;
	}

	public void setComparator(String comparator) {
		this.comparator = comparator;
	}

	public List<Dependency> getDependencies() {
		return dependencies;
	}

	public boolean addDependency(Dependency dependency) {
		return dependencies.add(dependency);
	}

	// ////////////////////////////////////////////////////////
	// Métodos varios
	// ////////////////////////////////////////////////////////

	public boolean esCriterio() {
		return getType() == DataSourceType.CRITERION;
	}

	public boolean esRegistro() {
		return getType() == DataSourceType.RECORD || getType() == DataSourceType.CONTROL;
	}

	public boolean esControl() {
		return getType() == DataSourceType.CONTROL;
	}

	public boolean estaVacio() {
		return getType() == DataSourceType.NONE;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("DataSource: " + getAlias() + "." + getField() + " " + getType());
		if (getDependencies().size() > 0) {
			builder.append("\t");
			builder.append("Dependencies: ");
			for (Dependency dep : getDependencies()) {
				builder.append(dep.getField() + ">" + dep.getFromAlias() + "." + dep.getFromField());
				builder.append(";");
			}
		}
		return builder.toString();
	}
}