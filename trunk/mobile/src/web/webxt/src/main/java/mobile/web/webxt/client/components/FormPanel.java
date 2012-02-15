package mobile.web.webxt.client.components;

import mobile.web.webxt.client.MobileConfig;
import mobile.web.webxt.client.devform.B101;
import mobile.web.webxt.client.devform.B102;
import mobile.web.webxt.client.devform.B103;
import mobile.web.webxt.client.devform.C102;
import mobile.web.webxt.client.devform.C103;
import mobile.web.webxt.client.devform.C201;
import mobile.web.webxt.client.devform.ObtainPersonInformation;
import mobile.web.webxt.client.devform.ZonePreview;
import mobile.web.webxt.client.resources.Resources;

import com.extjs.gxt.ui.client.Style.Scroll;
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
		if (!MobileConfig.FORM_DEVELOPMENT) {
			add(tabPanel);
		} else {
			// Add here the tested form
			//add(new B103());
			add(new C103());
		}

	}

	public void addTab(TabItem tabItem) {
		tabItem.setLayout(new FitLayout());
		// tabItem.setIcon(Resources.ICONS.rss());
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
}
