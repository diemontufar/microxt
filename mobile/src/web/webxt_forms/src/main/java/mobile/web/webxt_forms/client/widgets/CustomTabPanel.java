package mobile.web.webxt_forms.client.widgets;

import mobile.web.webxt_forms.shared.Mapper;

import com.extjs.gxt.ui.client.Style.Scroll;
import com.extjs.gxt.ui.client.event.ComponentEvent;
import com.extjs.gxt.ui.client.event.Events;
import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.widget.TabItem;
import com.extjs.gxt.ui.client.widget.TabPanel;
import com.extjs.gxt.ui.client.widget.layout.FitLayout;
import com.google.gwt.user.client.Element;

public class CustomTabPanel extends TabPanel {

	public String tabName;
	public int itemIdx;
	private Mapper mapper = new Mapper();

	public CustomTabPanel() {
		// Set TabPanel Size
		itemIdx = 0;
		setTabIdx(itemIdx);
		this.setStyleAttribute("border", "0px");
	}

	@Override
	protected void onRender(Element parent, int index) {
		super.onRender(parent, index);

		setMinTabWidth(115);
		setHeight(1000);
		setResizeTabs(true);
		setTabScroll(true);
		setAnimScroll(true);
		setPlain(true);
		setBodyBorder(false);
		setBorders(false);

	}

	public String getTabName() {
		return tabName;
	}

	public int getTabIdx() {
		return itemIdx;
	}

	public void setTabName(String tabName) {
		this.tabName = tabName;
	}

	public void setTabIdx(int itemIdx) {
		this.itemIdx = itemIdx;
	}

	public int nItems() {
		return this.getItemCount();
	}

	public Mapper getMapper() {
		return mapper;
	}

	// method to create tabs
	public void addTab(String tabName, final TabItem item) {

		item.setItemId(tabName);
		mapper.addValues(itemIdx, tabName);

		item.addListener(Events.Close, new Listener<ComponentEvent>() {
			public void handleEvent(ComponentEvent be) {
				int ind = mapper.getIndex(item.getItemId());
				mapper.removeItem(ind);
			}
		});

		if (this.getTabIdx() == 0) {
			item.setClosable(false);// not closeable if it's home page
		} else {
			item.setClosable(true);
		}

		item.setText(tabName);// set the name of the tab
		item.setLayout(new FitLayout());

		// select current tab
		add(item);
		item.setScrollMode(Scroll.AUTOX);
		item.setScrollMode(Scroll.AUTOY);
		setSelection(item);

		// increment secuential index
		itemIdx++;
	}

}
