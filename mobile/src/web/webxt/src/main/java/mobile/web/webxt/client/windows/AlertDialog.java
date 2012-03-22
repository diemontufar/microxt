package mobile.web.webxt.client.windows;

import com.extjs.gxt.ui.client.Style.Scroll;
import com.extjs.gxt.ui.client.widget.Dialog;

public class AlertDialog extends Dialog {

	private final int WIDTH = 500;
	private final int HEIGHT = 200;
	
	public AlertDialog(String title, String text) {
		super();
		this.setHeading(title);
		this.setButtons(Dialog.OK);
		//this.setBodyStyleName("pad-text");
		//this.addText(text);
		//this.getItem(0).getFocusSupport().setIgnore(true);
		this.setScrollMode(Scroll.AUTO);
		this.setHideOnButtonClick(true);
		this.setWidth(WIDTH);  
	    //this.setHeight(HEIGHT);
	}
	
	public AlertDialog(String code, String message, String stackTrace) {
		super();
		this.setHeading("ERROR");
		this.setButtons(Dialog.OK);
		//this.setBodyStyleName("pad-text");
		//this.addText(text);
		//this.getItem(0).getFocusSupport().setIgnore(true);
		this.setScrollMode(Scroll.AUTO);
		this.setHideOnButtonClick(true);
		this.setWidth(WIDTH);  
	    this.setHeight(HEIGHT);
	    
	    this.addText("<bold>ERROR:</bold><br/>"+message);
	}
}