package mobile.core.generator.util;

import java.util.ArrayList;
import java.util.List;

/**
 * Class for grouping entity class file data
 */
public class EntityData implements Cloneable {
	private List<String> lGeneralImports;
	private List<String> lPersistenceImports;
	private List<String> lSchemaEntityImports;

	private String strExtends;
	private List<String> lImplements;

	private String className;

	private List<PropertyData> lProperties;

	private boolean hasEmbeddedPk;

	public EntityData() {
		lGeneralImports = new ArrayList<String>();
		lPersistenceImports = new ArrayList<String>();
		lSchemaEntityImports = new ArrayList<String>();

		lImplements = new ArrayList<String>();

		lProperties = new ArrayList<PropertyData>();

		hasEmbeddedPk = false;
	}

	public List<String> getlGeneralImports() {
		return lGeneralImports;
	}

	public void setlGeneralImports(List<String> lGeneralImports) {
		this.lGeneralImports = lGeneralImports;
	}

	public List<String> getlPersistenceImports() {
		return lPersistenceImports;
	}

	public void setlPersistenceImports(List<String> lPersistenceImports) {
		this.lPersistenceImports = lPersistenceImports;
	}

	public List<String> getlSchemaEntityImports() {
		return lSchemaEntityImports;
	}

	public void setlSchemaEntityImports(List<String> lEntityCommonImports) {
		this.lSchemaEntityImports = lEntityCommonImports;
	}

	public String getStrExtends() {
		return strExtends;
	}

	public void setStrExtends(String strExtends) {
		this.strExtends = strExtends;
	}

	public List<String> getlImplements() {
		return lImplements;
	}

	public void setlImplements(List<String> lImplements) {
		this.lImplements = lImplements;
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public List<PropertyData> getlProperties() {
		return lProperties;
	}

	public void setlProperties(List<PropertyData> lProperties) {
		this.lProperties = lProperties;
	}

	public boolean getHasEmbeddedPk() {
		return hasEmbeddedPk;
	}

	public void setHasEmbeddedPk(boolean hasEmbeddedPk) {
		this.hasEmbeddedPk = hasEmbeddedPk;
	}

	public Object clone() throws CloneNotSupportedException {
		return super.clone();
	}

}
