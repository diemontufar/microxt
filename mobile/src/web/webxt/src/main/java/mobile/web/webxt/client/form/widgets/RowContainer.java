package mobile.web.webxt.client.form.widgets;

import com.extjs.gxt.ui.client.util.Margins;
import com.extjs.gxt.ui.client.widget.LayoutContainer;
import com.extjs.gxt.ui.client.widget.layout.HBoxLayout;
import com.extjs.gxt.ui.client.widget.layout.HBoxLayoutData;
import com.google.gwt.user.client.ui.Widget;

public class RowContainer extends LayoutContainer {

	private final int NORMAL_ROW_HEIGHT = 32;
	
	private HBoxLayoutData hdata = new HBoxLayoutData(new Margins(0, 10, 0, 0)); 
	
	public RowContainer() {
		super();
		HBoxLayout layout = new HBoxLayout();
		setLayout(layout);
		setHeight(NORMAL_ROW_HEIGHT);
	}
	
	@Override
	public boolean add(Widget widget) {
		return super.add(widget, hdata);
	}

}
