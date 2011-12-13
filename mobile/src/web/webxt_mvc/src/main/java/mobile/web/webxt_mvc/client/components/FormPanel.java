package mobile.web.webxt_mvc.client.components;

import mobile.web.webxt_mvc.client.MobileConfig;
import mobile.web.webxt_mvc.client.devform.A202;
import mobile.web.webxt_mvc.client.devform.A205;
import mobile.web.webxt_mvc.client.devform.A206;
import mobile.web.webxt_mvc.client.devform.FormPanelExample;
import mobile.web.webxt_mvc.client.resources.Resources;

import com.extjs.gxt.ui.client.Style.Scroll;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.LayoutContainer;
import com.extjs.gxt.ui.client.widget.TabItem;
import com.extjs.gxt.ui.client.widget.TabPanel;
import com.extjs.gxt.ui.client.widget.layout.CenterLayout;
import com.extjs.gxt.ui.client.widget.layout.FitLayout;

public class FormPanel extends ContentPanel {

	private final TabPanel tabPanel = new TabPanel();

	public FormPanel() {
		setHeaderVisible(false);
		setBorders(false);
		setLayout(new FitLayout());
		if(!MobileConfig.FORM_DEVELOPMENT){
			add(tabPanel);			
		}else{
			// Add here the tested form
			LayoutContainer lc=new LayoutContainer();
			lc.setLayout(new CenterLayout());
			lc.getAriaSupport().setPresentation(true);
			lc.add(new A205());
			add(lc);
			//add(new G101());
		}

	}

	public void addTab(TabItem tabItem) {
		tabItem.setLayout(new FitLayout());
		//tabItem.setIcon(Resources.ICONS.rss());
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
