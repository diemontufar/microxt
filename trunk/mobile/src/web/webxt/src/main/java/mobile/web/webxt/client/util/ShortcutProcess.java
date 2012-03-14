package mobile.web.webxt.client.util;

import com.extjs.gxt.ui.client.data.BaseModel;
import com.google.gwt.user.client.ui.Widget;

public class ShortcutProcess extends BaseModel {
	private static final long serialVersionUID = 1L;

	public ShortcutProcess() {
	}

	public ShortcutProcess(String process,String name,Widget form, String image) {
		set("process", process);
		set("name", name);
		set("form", form);
		set("image", image);
	}

	public String getName() {
		return (String) get("name");
	}

	public String toString() {
		return getName();
	}
	
	public String getProcess() {
		return (String) get("process");
	}

	public Widget getForm() {
		return (Widget) get("form");
	}
	
	public String getImage() {
		return (String) get("image");
	}

}