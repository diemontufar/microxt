package mobile.web.webxt.client.components;

import mobile.web.webxt.client.MobileConfig;
import mobile.web.webxt.client.devform.B101;
import mobile.web.webxt.client.mvc.AppEvents;
import mobile.web.webxt.client.resources.Resources;

import com.extjs.gxt.ui.client.Style.Scroll;
import com.extjs.gxt.ui.client.data.ModelData;
import com.extjs.gxt.ui.client.mvc.AppEvent;
import com.extjs.gxt.ui.client.mvc.Dispatcher;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.TabItem;
import com.extjs.gxt.ui.client.widget.TabPanel;
import com.extjs.gxt.ui.client.widget.layout.FitLayout;

public class FormPanel extends ContentPanel {

	private final TabPanel tabPanel = new TabPanel();

	public FormPanel() {
		setHeaderVisible(false);
		setBorders(false);
		setLayout(new FitLayout());
		tabPanel.setTabScroll(true);
		tabPanel.setAnimScroll(true);

		if (!MobileConfig.FORM_DEVELOPMENT) {
			add(tabPanel);
		} else {
			// Put here the tested form
			add(new B101());
		}

	}

	public void addTab(TabItem tabItem) {
		tabItem.setLayout(new FitLayout());
		tabItem.setIcon(Resources.ICONS.form());
		tabItem.setScrollMode(Scroll.AUTO);
		String tabId = tabItem.getId();
		TabItem existingTab = tabPanel.findItem(tabId, false);
		if (existingTab == null) {
			tabPanel.add(tabItem);
			tabPanel.setSelection(tabItem);
		} else {
			tabPanel.setSelection(existingTab);
		}
	}

	public void reloadTab() {
		TabItem actual = tabPanel.getSelectedItem();
		if (actual != null) {
			ModelData actualProcess = actual.getData("process"); 
			tabPanel.remove(actual);
			Dispatcher.forwardEvent(new AppEvent(AppEvents.ProcessSelected, actualProcess));
		}
	}
}
