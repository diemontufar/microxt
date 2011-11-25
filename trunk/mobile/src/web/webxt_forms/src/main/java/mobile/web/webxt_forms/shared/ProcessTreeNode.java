package mobile.web.webxt_forms.shared;

import com.extjs.gxt.ui.client.data.BaseTreeModel;

public class ProcessTreeNode extends BaseTreeModel {
	private static final long serialVersionUID = 1L;

	public ProcessTreeNode() {
	}

	public ProcessTreeNode(String process) {
		set("process", process);
	}

	public String getProcess() {
		return (String) get("process");
	}

	public String toString() {
		return getProcess();
	}
}