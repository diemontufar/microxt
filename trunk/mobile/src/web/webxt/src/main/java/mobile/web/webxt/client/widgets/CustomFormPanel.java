package mobile.web.webxt.client.widgets;

import com.extjs.gxt.ui.client.widget.form.FormPanel;

public class CustomFormPanel extends FormPanel {

	public CustomFormPanel(String title, int width){
		setHeading(title);
		setFrame(true);
		setWidth(width);
	}
	
}
