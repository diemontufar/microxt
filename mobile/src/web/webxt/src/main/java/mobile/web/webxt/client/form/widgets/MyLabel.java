package mobile.web.webxt.client.form.widgets;

import com.extjs.gxt.ui.client.widget.Label;

public class MyLabel extends Label {

	private final int LABEL_WIDTH = 100;
	
	public MyLabel(String text) {
		super(text);
		setStyleName("x-form-item");
		setWidth(LABEL_WIDTH);
	}
	
	public MyLabel(String text, int width) {
		super(text); 
		setStyleName("x-form-item");
		setWidth(width);
	}
}
