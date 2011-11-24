package mobile.web.webxt_mvc.client.resources;

import com.google.gwt.user.client.ui.AbstractImagePrototype;
import com.google.gwt.user.client.ui.ImageBundle;

@SuppressWarnings("deprecation")
public interface Icons extends ImageBundle {
	//String path = "img/icons/";
	String path = "";

	@Resource(path + "form.png")
	AbstractImagePrototype form();
	
	@Resource(path + "table.png")
	AbstractImagePrototype table();
	
	@Resource(path + "funnel.png")
	AbstractImagePrototype funnel();
	
	@Resource(path + "add.gif")
	AbstractImagePrototype add();

	@Resource(path + "delete.gif")
	AbstractImagePrototype delete();

	@Resource(path + "calendar.gif")
	AbstractImagePrototype calendar();
	
	@Resource(path + "undo.png")
	AbstractImagePrototype undo();
	
	@Resource(path + "disk2.png")
	AbstractImagePrototype save();

}
