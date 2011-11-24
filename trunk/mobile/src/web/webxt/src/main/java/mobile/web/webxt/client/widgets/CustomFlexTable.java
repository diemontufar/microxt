package mobile.web.webxt.client.widgets;

import com.extjs.gxt.ui.client.widget.Html;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HasVerticalAlignment;

public class CustomFlexTable extends FlexTable {

	public CustomFlexTable() {
		// basic parameters:
		// setBorderWidth(1);
		setWidth("100%");
		addWidgets();
	}

	public void addWidgets() {

		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				getCellFormatter().setAlignment(i, j,
						HasHorizontalAlignment.ALIGN_RIGHT,
						HasVerticalAlignment.ALIGN_MIDDLE);
			}
		}

		Html user = new Html("<B>User: </B>ADMIN");
		Html terminal = new Html("<B>Terminal: </B>ADMIN");
		Html role = new Html("<B>Role: </B>Admin");
		Html office = new Html("<B>Office: </B>1");
		Html branch = new Html("<B>Branch: </B>1");
		Html date = new Html("<B>Date: </B>16/09/2011");

		user.addStyleName("header-text");
		terminal.addStyleName("header-text");
		role.addStyleName("header-text");
		office.addStyleName("header-text");
		branch.addStyleName("header-text");
		date.addStyleName("header-text");

		setWidget(0, 0, user);
		setWidget(0, 1, terminal);
		setWidget(0, 2, role);
		setWidget(1, 0, office);
		setWidget(1, 1, branch);
		setWidget(1, 2, date);
	}

}
