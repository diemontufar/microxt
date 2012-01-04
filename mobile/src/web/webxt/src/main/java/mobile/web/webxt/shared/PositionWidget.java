package mobile.web.webxt.shared;

import com.extjs.gxt.ui.client.widget.Html;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.Element;
import com.google.gwt.user.client.ui.CellPanel;
import com.google.gwt.user.client.ui.HasAlignment;
import com.google.gwt.user.client.ui.Widget;

public class PositionWidget extends CellPanel {

	Element tr = DOM.createTR();
	Element td = DOM.createTD();

	public PositionWidget() {
		setBorderWidth(0);
		DOM.appendChild(tr, td);
		setSize("100%", "100%");
	}

	public PositionWidget(String title) {
		title = "<BR><CENTER><B>" + title + "</B><center><BR>";
		DOM.appendChild(tr, td);
		setSize("100%", "100%");

		Html htmlTitle = new Html(title);
		htmlTitle.setStyleName("header-text");
		super.add(htmlTitle, td);
	}

	public void add(Widget child) {

		DOM.appendChild(getBody(), tr);
		super.add(child, td);
		super.add(new Html("<BR>"), td);

		setCellHeight(child, "100%");
		setCellWidth(child, "100%");
		setCellHorizontalAlignment(child, HasAlignment.ALIGN_CENTER);
		setCellVerticalAlignment(child, HasAlignment.ALIGN_TOP);
	}

	public void addWithPosition(Widget child, String hAlignment,
			String vAlignment) {

		DOM.appendChild(getBody(), tr);
		super.add(child, td);
		super.add(new Html("<BR>"), td);

		setCellHeight(child, "100%");
		setCellWidth(child, "100%");

		if (hAlignment.compareToIgnoreCase("LEFT") == 0) {
			setCellHorizontalAlignment(child, HasAlignment.ALIGN_LEFT);
		}
		if (hAlignment.compareToIgnoreCase("CENTER") == 0) {
			setCellHorizontalAlignment(child, HasAlignment.ALIGN_CENTER);
		}
		if (hAlignment.compareToIgnoreCase("RIGHT") == 0) {
			setCellHorizontalAlignment(child, HasAlignment.ALIGN_RIGHT);
		}
		if (vAlignment.compareToIgnoreCase("TOP") == 0) {
			setCellVerticalAlignment(child, HasAlignment.ALIGN_TOP);
		}
		if (vAlignment.compareToIgnoreCase("MIDDLE") == 0) {
			setCellVerticalAlignment(child, HasAlignment.ALIGN_MIDDLE);
		}
		if (vAlignment.compareToIgnoreCase("BOTTOM") == 0) {
			setCellVerticalAlignment(child, HasAlignment.ALIGN_BOTTOM);
		}
	}
}
