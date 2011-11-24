package mobile.web.webxt.client.widgets;

import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.ScrollPanel;
import com.google.gwt.user.client.ui.StackLayoutPanel;
import com.google.gwt.user.client.ui.Widget;

public class StackPanel extends StackLayoutPanel {

	public StackPanel(Unit unit) {
		super(unit);

	}

	public void addWidget(String title, int size, Widget child) {

		ScrollPanel sp = new ScrollPanel();
		sp.add(child);

		add(sp, new HTML(title), size);

	}
}
