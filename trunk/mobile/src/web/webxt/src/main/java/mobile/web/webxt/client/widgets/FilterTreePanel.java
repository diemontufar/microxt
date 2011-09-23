package mobile.web.webxt.client.widgets;

 

import java.util.List;

import mobile.web.webxt.client.forms.FormExample;
import mobile.web.webxt.shared.Folder;
import mobile.web.webxt.shared.Mapper;
import mobile.web.webxt.shared.Resources;
import mobile.web.webxt.shared.TestData;

import com.extjs.gxt.ui.client.data.BaseTreeLoader;
import com.extjs.gxt.ui.client.data.ModelData;
import com.extjs.gxt.ui.client.data.ModelIconProvider;
import com.extjs.gxt.ui.client.data.TreeLoader;
import com.extjs.gxt.ui.client.data.TreeModel;
import com.extjs.gxt.ui.client.data.TreeModelReader;
import com.extjs.gxt.ui.client.event.Events;
import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.event.TreePanelEvent;
import com.extjs.gxt.ui.client.store.Store;
import com.extjs.gxt.ui.client.store.TreeStore;
import com.extjs.gxt.ui.client.widget.Html;
import com.extjs.gxt.ui.client.widget.Info;
import com.extjs.gxt.ui.client.widget.LayoutContainer;
import com.extjs.gxt.ui.client.widget.TabItem;
import com.extjs.gxt.ui.client.widget.VerticalPanel;
import com.extjs.gxt.ui.client.widget.form.StoreFilterField;
import com.extjs.gxt.ui.client.widget.treepanel.TreePanel;
import com.google.gwt.user.client.Element;
import com.google.gwt.user.client.ui.AbstractImagePrototype;


public class FilterTreePanel extends LayoutContainer {  
	
	private String subTreeItem;
	public String subsystem;
	public String module;
	public String process;
	
	private CustomTabPanel tabPanel=new CustomTabPanel();
	private Mapper mapper = new Mapper();
	
	public FilterTreePanel(CustomTabPanel tabPanel){
		this.tabPanel=tabPanel;
	}
	
	@Override  
	  protected void onRender(Element parent, int index) {  
	    super.onRender(parent, index);  
	    
	    TreeLoader<ModelData> loader = new BaseTreeLoader<ModelData>(  
	        new TreeModelReader<List<ModelData>>());  
	  
	    TreeStore<ModelData> store = new TreeStore<ModelData>(loader);  
	  
	    final TreePanel<ModelData> tree = new TreePanel<ModelData>(store);  
	    tree.setAutoLoad(true);  
	    tree.setDisplayProperty("process");
	    
	    tree.setWidth(250);
	    
	    
	    tree.setIconProvider(new ModelIconProvider<ModelData>() {  
	        public AbstractImagePrototype getIcon(ModelData model) {  
	          if (((TreeModel) model).isLeaf()) {  
	            return Resources.ICONS.form(); 
	          }  
	          return null;  
	        }  
	      }); 
	    
	    loader.load(TestData.getTreeModel());  
	    
	    StoreFilterField<ModelData> filter = new StoreFilterField<ModelData>() {  
	  
	      @Override  
			protected boolean doSelect(Store<ModelData> store,
					ModelData parent, ModelData record, String property,
					String filter) {
				// only match leaf nodes
				if (record instanceof Folder) {
					return false;
				}
				String name = record.get("process");
				name = name.toLowerCase();
				// System.out.println(name.substring(filter.toLowerCase()));
				// System.out.println(name);
				//System.out.println(name.contains(filter.toLowerCase()));
				if (name.contains(filter.toLowerCase())!=false || name.startsWith(filter.toLowerCase()) || name.endsWith(filter.toLowerCase())) {
					System.out.println("true");
					return true;
				} else {
					return false;
				}
			}

		};	    
		
	    //Evento click de un nodo
	    tree.addListener(Events.OnDoubleClick, new Listener<TreePanelEvent<ModelData>>() {
            public void handleEvent(TreePanelEvent<ModelData> be) {
            	
            	TabItem ti = new TabItem();
            	TabItem aux=new TabItem();
            	mapper=tabPanel.getMapper();
            	
            	            	
            	//If node is leaf (is a process)
            	if(be.getNode().isLeaf()){
	            	subTreeItem=be.getItem().toString();
	                //Show in Console 
	            	System.out.println("hizo click en: " + be.getItem());
	                System.out.println("Subsistema: "+getSubsystem(subTreeItem));
	                System.out.println("Modulo: "+getModule(subTreeItem));
	                System.out.println("Num. Proceso: "+getProcessNum(subTreeItem));
	                System.out.println("Proceso: "+getProcessName(subTreeItem));
	                System.out.println("Codigo: "+getProcessCode(subTreeItem));
            	}
            	
            	//if tabitem exists, just set selection. Otherwise create a new one
            	if (mapper.searchByValue(getProcessCode(subTreeItem).trim())==true){
            		aux=tabPanel.getItem(mapper.getIndex(getProcessCode(subTreeItem)));
            		tabPanel.setSelection(aux);
            	}else{
            		openNewTab(subTreeItem, ti);
            		}
            };
        });
	    
	    filter.bind(store);  
	  
	    VerticalPanel panel = new VerticalPanel();  
	    panel.addStyleName("x-small-editor");  
	    panel.setSpacing(8);  
	  
	    panel.add(new Html("<span class=text>Busqueda:</span>"));  
	    panel.add(filter);  
	    panel.add(tree);  
	  
	    add(panel);  
	  }  
	
	//Obtain sybsystem number
	public String getSubsystem(String subTreeItems){
    	String sub=subTreeItems.substring(0, 1);
    	return sub;
    }
    
	//Obtain module number
    public String getModule(String subTreeItems){
    	String mod=subTreeItems.substring(1, 2);
    	return mod;
    }
    
    //Obtain process number
    public String getProcessNum(String subTreeItems){
    	String procnum=subTreeItems.substring(3, 5);
    	return procnum;
    }
    
   //Obtain process name/title
    public String getProcessName(String subTreeItems){
    	String procname=subTreeItems.substring(6, subTreeItems.length());
    	return procname;
    }
	
  //Obtain process code
    public String getProcessCode(String subTreeItems){
    	String proccod=subTreeItems.substring(0, 5);
    	return proccod;
    }
    
    public void openNewTab(String sTreeItem, TabItem titem){
    	
    	PositionWidget pw = new PositionWidget(getProcessName(sTreeItem));
    	
    	if (getProcessCode(sTreeItem).compareTo("G1-00")==0){
    		
			pw.add(new FormExample());
			titem.add(pw);
    		
    		tabPanel.addTab(getProcessCode(sTreeItem), titem);
    		Info.display("Informacion", "'{0}{1}' fue agregado", "Tab " + tabPanel.getTabIdx(),", Proceso: "+getProcessCode(sTreeItem));
		}
    	
    	if (getProcessCode(sTreeItem).compareTo("G1-01")==0){
  		
    		pw.add(new FormExample());
			titem.add(pw);
    		
    		tabPanel.addTab(getProcessCode(sTreeItem), titem);
    		Info.display("Informacion", "'{0}{1}' fue agregado", "Tab " + tabPanel.getTabIdx(),", Proceso: "+getProcessCode(sTreeItem));
		}
   	
    }
    	
	
	}  