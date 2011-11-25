package mobile.web.webxt_forms.shared;

import java.io.Serializable;

import com.extjs.gxt.ui.client.data.BaseTreeModel;

public class Folder extends BaseTreeModel implements Serializable {

	private static final long serialVersionUID = 1L;
	private static int ID = 0;

	public Folder() {
		set("id", ID++);
	}

	public Folder(String process) {
		set("id", ID++);
		set("process", process);
	}

	public Folder(String name, BaseTreeModel[] children) {
		this(name);
		for (int i = 0; i < children.length; i++) {
			add(children[i]);
		}
	}

	public Integer getId() {
		return (Integer) get("id");
	}

	public String getProcess() {
		return (String) get("process");
	}

	public String toString() {
		return getProcess();
	}

}
