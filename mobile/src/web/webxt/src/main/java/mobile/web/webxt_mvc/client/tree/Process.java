/*
 * Ext GWT 2.2.5 - Ext for GWT
 * Copyright(c) 2007-2010, Ext JS, LLC.
 * licensing@extjs.com
 * 
 * http://extjs.com/license
 */
package mobile.web.webxt_mvc.client.tree;

import com.extjs.gxt.ui.client.data.BaseTreeModel;

public class Process extends BaseTreeModel {

	private static final long serialVersionUID = 1L;

	public Process() {

	}

	public Process(String name) {
		set("name", name);
	}

	public Process(String name, String author, String genre) {
		set("name", name);
		set("author", author);
		set("genre", genre);
	}

	public String getName() {
		return (String) get("name");
	}

	public String getAuthor() {
		return (String) get("author");
	}

	public String getGenre() {
		return (String) get("genre");
	}

	public String toString() {
		return getName();
	}
}
