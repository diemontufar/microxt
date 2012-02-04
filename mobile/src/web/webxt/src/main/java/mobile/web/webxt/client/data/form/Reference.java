package mobile.web.webxt.client.data.form;

import java.util.LinkedHashSet;
import java.util.Set;

/**
 * Reference from a form
 */
public class Reference {
	private String alias = "";

	private String entity = "";

	private final Set<Dependency> dependencies = new LinkedHashSet<Dependency>();

	private boolean queryOnly = false;

	private boolean storeOnly = false;

	private boolean distinct = false;

	public Reference() {
	}
	
	public Reference(String entity) {
		this.entity = entity;
	}

	public Reference(String alias, String entity) {
		this.alias = alias;
		this.entity = entity;
	}

	// ////////////////////////////////////////////////////////
	// Getters and setters
	// ////////////////////////////////////////////////////////

	public String getAlias() {
		return alias;
	}

	public void setAlias(String alias) {
		this.alias = alias;
	}

	public String getEntity() {
		return entity;
	}

	public void setEntity(String entity) {
		this.entity = entity;
	}

	public boolean isQueryOnly() {
		return queryOnly;
	}

	public void setQueryOnly(boolean queryOnly) {
		this.queryOnly = queryOnly;
	}

	public boolean isStoreOnly() {
		return storeOnly;
	}

	public void setStoreOnly(boolean storeOnly) {
		this.storeOnly = storeOnly;
	}

	public boolean isDistinct() {
		return distinct;
	}

	public void setDistinct(boolean distinct) {
		this.distinct = distinct;
	}

	public Set<Dependency> getDependencies() {
		return dependencies;
	}
	
	public void setDependencies(Set<Dependency> dependencies) {
		this.dependencies.clear();
		this.dependencies.addAll(dependencies);
	}

}
