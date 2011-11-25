package mobile.web.webxt_forms.shared;

import com.google.gwt.user.client.ui.AbstractImagePrototype;
import com.google.gwt.user.client.ui.ImageBundle;

@SuppressWarnings("deprecation")
public interface ExampleIcons extends ImageBundle {

	String path = "mobile/web/webxt_forms/shared/icons/";

	@Resource(path + "table.png")
	AbstractImagePrototype table();

	@Resource(path + "add16.gif")
	AbstractImagePrototype add16();

	@Resource(path + "add24.gif")
	AbstractImagePrototype add24();

	@Resource(path + "add32.gif")
	AbstractImagePrototype add32();

	@Resource(path + "application_side_list.png")
	AbstractImagePrototype side_list();

	@Resource(path + "application_form.png")
	AbstractImagePrototype form();

	@Resource(path + "connect.png")
	AbstractImagePrototype connect();

	@Resource(path + "user_add.png")
	AbstractImagePrototype user_add();

	@Resource(path + "user_delete.png")
	AbstractImagePrototype user_delete();

	@Resource(path + "accordion.gif")
	AbstractImagePrototype accordion();

	@Resource(path + "add.gif")
	AbstractImagePrototype add();

	@Resource(path + "delete.gif")
	AbstractImagePrototype delete();

	@Resource(path + "calendar.gif")
	AbstractImagePrototype calendar();

	@Resource(path + "menu-show.gif")
	AbstractImagePrototype menu_show();

	@Resource(path + "list-items.gif")
	AbstractImagePrototype list_items();

	@Resource(path + "album.gif")
	AbstractImagePrototype album();

	@Resource(path + "text.png")
	AbstractImagePrototype text();

	@Resource(path + "plugin.png")
	AbstractImagePrototype plugin();

	@Resource(path + "music.png")
	AbstractImagePrototype music();

}
