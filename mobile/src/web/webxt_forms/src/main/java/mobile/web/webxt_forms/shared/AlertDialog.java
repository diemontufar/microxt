package mobile.web.webxt_forms.shared;

import com.extjs.gxt.ui.client.Style.Scroll;
import com.extjs.gxt.ui.client.widget.Dialog;

public class AlertDialog extends Dialog {

	public AlertDialog(String title, String text) {
		super();
		this.setHeading(title);
		this.setButtons(Dialog.OK);
		this.setBodyStyleName("pad-text");
		this.addText(text);
		this.getItem(0).getFocusSupport().setIgnore(true);
		this.setScrollMode(Scroll.AUTO);
		this.setHideOnButtonClick(true);
	}
}