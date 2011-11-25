package mobile.web.webxt_forms.client.widgets;

import com.extjs.gxt.ui.client.Style.LayoutRegion;
import com.extjs.gxt.ui.client.Style.Scroll;
import com.extjs.gxt.ui.client.util.Margins;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.Viewport;
import com.extjs.gxt.ui.client.widget.layout.BorderLayout;
import com.extjs.gxt.ui.client.widget.layout.BorderLayoutData;
import com.extjs.gxt.ui.client.widget.layout.FitLayout;
import com.google.gwt.user.client.Element;
import com.google.gwt.user.client.ui.Widget;

public class PrincipalPanel extends Viewport {

	public ContentPanel north = new ContentPanel();
	public ContentPanel west = new ContentPanel();
	public ContentPanel center = new ContentPanel();

	BorderLayoutData northData = new BorderLayoutData(LayoutRegion.NORTH, 80);
	BorderLayoutData westData = new BorderLayoutData(LayoutRegion.WEST, 250);
	BorderLayoutData centerData = new BorderLayoutData(LayoutRegion.CENTER);

	protected void onRender(Element target, int index) {
		super.onRender(target, index);

		this.setStyleAttribute("border", "0px");

		final BorderLayout layout = new BorderLayout();
		setLayout(layout);
		setStyleAttribute("padding", "3px");

		north.setLayout(new FitLayout());
		center.setLayout(new FitLayout());
		west.setLayout(new FitLayout());

		// North settings
		north.setHeading("MICRO-XT The microcredit Application");

		northData.setCollapsible(true);
		northData.setFloatable(false);
		northData.setHideCollapseTool(false);
		northData.setSplit(true);
		northData.setMargins(new Margins(0, 0, 1, 0));

		// Center settings
		if (center.isHeaderVisible()) {
			center.setHeaderVisible(false);
		}
		center.setScrollMode(Scroll.AUTOX);
		center.setScrollMode(Scroll.AUTOY);

		centerData.setMargins(new Margins(0));

		// West settings

		westData.setSplit(true);
		westData.setCollapsible(true);
		westData.setMargins(new Margins(0, 1, 0, 0));

		add(north, northData);
		add(west, westData);
		add(center, centerData);

	}

	public void addWidget(String position, Widget child) {
		if (position.compareTo("north") == 0) {
			north.add(child);
		}
		if (position.compareTo("west") == 0) {
			west.add(child);
		}
		if (position.compareTo("center") == 0) {
			center.add(child);
		}

	}
}