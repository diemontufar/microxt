package mobile.web.webxt.client.devform;

import java.util.ArrayList;
import java.util.List;

import mobile.web.webxt.client.components.FormPanel;
import mobile.web.webxt.client.devform.B101;
import mobile.web.webxt.client.devform.C102;
import mobile.web.webxt.client.devform.C201;
import mobile.web.webxt.client.devform.C202;
import mobile.web.webxt.client.devform.C301;
import mobile.web.webxt.client.devform.C302;
import mobile.web.webxt.client.resources.Resources;
import mobile.web.webxt.client.util.ShortcutProcess;

import com.extjs.gxt.ui.client.event.SelectionChangedEvent;
import com.extjs.gxt.ui.client.event.SelectionChangedListener;
import com.extjs.gxt.ui.client.store.ListStore;
import com.extjs.gxt.ui.client.widget.LayoutContainer;
import com.extjs.gxt.ui.client.widget.ListView;
import com.extjs.gxt.ui.client.widget.TabItem;
import com.extjs.gxt.ui.client.widget.layout.FitLayout;
import com.google.gwt.user.client.Element;
import com.google.gwt.user.client.ui.Widget;

public class OverviewPage extends LayoutContainer {

	private ListView<ShortcutProcess> dataView;
	FormPanel formPanel;
	TabItem tabItem;
	List<ShortcutProcess> process;

	public OverviewPage(FormPanel formPanel) {
		setFormPanel(formPanel);
	}

	@Override
	protected void onRender(Element parent, int pos) {
		super.onRender(parent, pos);
		setLayout(new FitLayout());
		getAriaSupport().setPresentation(true);

		process = new ArrayList<ShortcutProcess>();
		process.add(new ShortcutProcess("B101","Personas Naturales", new B101(), Resources.IMAGES.person().getHTML()));
		process.add(new ShortcutProcess("C102","Zonas Geograficas", new C102(), Resources.IMAGES.zones().getHTML()));
		process.add(new ShortcutProcess("C201","Socios Individuales", new C201(), Resources.IMAGES.individual().getHTML()));
		process.add(new ShortcutProcess("C202","Socios Grupales", new C202(), Resources.IMAGES.groupal().getHTML()));
		process.add(new ShortcutProcess("C301","Solicitud", new C301(), Resources.IMAGES.solicitude().getHTML()));
		process.add(new ShortcutProcess("C302","Recomendacion", new C302(), Resources.IMAGES.recomendation().getHTML()));

		ListStore<ShortcutProcess> store = new ListStore<ShortcutProcess>();
		store.add(process);

		StringBuffer sb = new StringBuffer();
	    sb.append("<tpl for=\".\">");
	    sb.append("<div class='sample-box' style='padding-top: 4px;border: none'>");
	    sb.append("<div class='thumbd'><center>{image}</center></div>");
	    sb.append("<div><center>{name}</center></div>");
	    sb.append("</div></tpl>");

		dataView = new ListView<ShortcutProcess>();
		dataView.addStyleName("overview-page");
		dataView.setItemSelector(".sample-box");
		dataView.setOverStyle("sample-over");
		dataView.setSelectStyle("none");
		dataView.setBorders(true);
		dataView.setStore(store);
		dataView.setTemplate(sb.toString());
		//dataView.setTemplate(getTemplate());

		dataView.getSelectionModel().addSelectionChangedListener(new SelectionChangedListener<ShortcutProcess>() {

			@Override
			public void selectionChanged(SelectionChangedEvent<ShortcutProcess> se) {
				if (se.getSelectedItem() != null) {

					int processIndex=0;
					
					if(se.getSelectedItem().getProcess().compareTo(process.get(0).getProcess())==0){
						processIndex=0;
					}
					if(se.getSelectedItem().getProcess().compareTo(process.get(1).getProcess())==0){
						processIndex=1;
					}
					if(se.getSelectedItem().getProcess().compareTo(process.get(2).getProcess())==0){
						processIndex=2;
					}
					if(se.getSelectedItem().getProcess().compareTo(process.get(3).getProcess())==0){
						processIndex=3;
					}
					if(se.getSelectedItem().getProcess().compareTo(process.get(4).getProcess())==0){
						processIndex=4;
					}
					if(se.getSelectedItem().getProcess().compareTo(process.get(5).getProcess())==0){
						processIndex=5;
					}					
					
					openTab(se.getSelectedItem().getForm(),processIndex);

					dataView.getSelectionModel().deselectAll();
				}
			}
		});
		add(dataView);
	}

	private native String getTemplate() /*-{

		return [
				'<tpl for=".">',
				'<div class="sample-box" style="border: 1px solid #DDDDDD;float:left;clear:both;position:static;margin:50px 50px 10px 50px; padding:10px;width:210px;">',
				'<div class="thumbd" style="padding-top: 4px;border: none;"><center>{image}</center></div>',
				'<div style="color:green"><center>{name}</center></div>',
				'</div>', '</tpl>', '' ].join("");

	}-*/;

	public FormPanel getFormPanel() {
		return formPanel;
	}

	public void setFormPanel(FormPanel formPanel) {
		this.formPanel = formPanel;
	}
	
	public void openTab(Widget form,int index){
		
			System.out.println(formPanel.getItemByItemId(process.get(index).getProcess()));
			
			if(formPanel.getItemByItemId(process.get(index).getProcess())==null){
				tabItem = new TabItem();
				tabItem.setText(process.get(index).getProcess());
				tabItem.setId(process.get(index).getProcess());
				tabItem.setClosable(true);
				tabItem.add(form);
				formPanel.addTab(tabItem);
			}else{
				formPanel.setTabIndex(formPanel.getItemByItemId(process.get(index).getProcess()).getTabIndex());
			}
		
	}

}
