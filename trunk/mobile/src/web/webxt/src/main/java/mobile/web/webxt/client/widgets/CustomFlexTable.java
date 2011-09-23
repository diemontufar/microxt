package mobile.web.webxt.client.widgets;

import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HasVerticalAlignment;

public class CustomFlexTable extends FlexTable {
	
	public CustomFlexTable() {
		//basic parameters:
		//setBorderWidth(1);
	    setWidth("100%");
	    addWidgets();
	}

	public void addWidgets(){

	
		for (int i=0;i<3;i++){
			for (int j=0;j<3;j++){
				getCellFormatter().setAlignment(i,j,HasHorizontalAlignment.ALIGN_RIGHT,HasVerticalAlignment.ALIGN_MIDDLE);
			}
		}
		
		setText(0, 0, "User: ADMIN");
	    setText(0, 1, "Terminal: ADMIN");
	    setText(0, 2, "Role: Admin");
	    setText(1, 0, "Office: 1");
	    setText(1, 1, "Branch: 1");
	    setText(1, 2, "Date: 12/09/11");
	}
	
}
