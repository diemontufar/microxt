package microxt.entity.generator;

import java.util.ArrayList;
import java.util.List;

/**
 * Data class for grouping entity class file data
 */
public class EntityData{
	List<String> lGeneralImports;
	List<String> lPersistenceImports;
	List<String> lEntityCommonImports;

	String strExtends;
	List<String> lImplements;
	
	String className;
	
	List<PropertyData> lProperties;
	
	public EntityData(){
		lGeneralImports = new ArrayList<String>();
		lPersistenceImports = new ArrayList<String>();
		lEntityCommonImports = new ArrayList<String>();

		lImplements = new ArrayList<String>();
		
		lProperties = new ArrayList<PropertyData>();
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

	public List<String> getlEntityCommonImports() {
		return lEntityCommonImports;
	}

	public void setlEntityCommonImports(List<String> lEntityCommonImports) {
		this.lEntityCommonImports = lEntityCommonImports;
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
	
	
}
