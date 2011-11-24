package mobile.web.webxt_mvc.client.tree;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import mobile.web.message.Message;
import mobile.web.webxt_mvc.client.data.MyHttpProxy;
import mobile.web.webxt_mvc.client.data.MyMessageReader;
import mobile.web.webxt_mvc.client.data.MyProcessConfig;
import mobile.web.webxt_mvc.client.mvc.AppEvents;
import mobile.web.webxt_mvc.client.resources.Resources;
import mobile.web.webxt_mvc.client.windows.AlertDialog;

import com.extjs.gxt.ui.client.data.BaseTreeLoader;
import com.extjs.gxt.ui.client.data.ModelData;
import com.extjs.gxt.ui.client.data.ModelIconProvider;
import com.extjs.gxt.ui.client.data.ModelStringProvider;
import com.extjs.gxt.ui.client.data.TreeLoader;
import com.extjs.gxt.ui.client.data.TreeModel;
import com.extjs.gxt.ui.client.data.TreeModelReader;
import com.extjs.gxt.ui.client.event.Events;
import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.event.TreePanelEvent;
import com.extjs.gxt.ui.client.mvc.Dispatcher;
import com.extjs.gxt.ui.client.store.TreeStore;
import com.extjs.gxt.ui.client.widget.LayoutContainer;
import com.extjs.gxt.ui.client.widget.form.StoreFilterField;
import com.extjs.gxt.ui.client.widget.treepanel.TreePanel;
import com.google.gwt.user.client.Element;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.AbstractImagePrototype;

public class MenuTreePanel extends LayoutContainer {

	private TreePanel<ModelData> tree;
	
	StoreFilterField<ModelData> filter;
	
	public MenuTreePanel(StoreFilterField<ModelData> filter) {
		this.filter = filter;
	}
	
	@Override
	protected void onRender(Element parent, int index) {
		super.onRender(parent, index);
		
		System.out.println("1");
		
		final TreeLoader<ModelData> loader = new BaseTreeLoader<ModelData>(
				new TreeModelReader<List<ModelData>>()) {
			@Override
			public boolean load() {
				System.out.println(BaseTreeLoader.class+".load");
				AsyncCallback<Message> callback = new AsyncCallback<Message>() {
					public void onFailure(Throwable caught) {
						new AlertDialog("FilterTreePanel", caught.getMessage())
								.show();
					}

					public void onSuccess(Message result) {
						onLoadSuccess(null, result);
					}
				};
				MyProcessConfig config = new MyProcessConfig("G001");
				MyHttpProxy<Message> proxy = new MyHttpProxy<Message>();
				proxy.requestMsg(config, callback);
				return true;
			}

			protected void onLoadSuccess(Object loadConfig, Message result) {
				MyMessageReader msgReader = new MyMessageReader(result);
				List<ModelData> subsystemModels = msgReader.getModels("Subsystem");
				List<ModelData> moduleModels = msgReader.getModels("Module");
				List<ModelData> processModels = msgReader.getModels("Process");

				Map<String, Folder> mN1 = new HashMap<String, Folder>();
				Map<String, Folder> mN2 = new HashMap<String, Folder>();
				Map<String, Process> mN3 = new HashMap<String, Process>();
				
				for (ModelData modelData : subsystemModels) {
					Folder folder = new Folder((String) modelData.get("name"));
					folder.set("id", modelData.get("id"));
					mN1.put((String)modelData.get("id"), folder);
				}
				
				for (ModelData modelData : moduleModels) {
					Folder folder = new Folder((String) modelData.get("name"));
					folder.set("id", modelData.get("id"));
					mN2.put((String)modelData.get("id"), folder);
					Folder parent = mN1.get(
							((String)modelData.get("id")).substring(0, 1));
					parent.add(folder);
				}
				
				for (ModelData modelData : processModels) {
					Process process = new Process((String) modelData.get("name"));
					process.set("id", modelData.get("id"));
					mN3.put((String)modelData.get("id"), process);
					Folder parent = mN2.get(
							((String)modelData.get("id")).substring(0, 2));
					parent.add(process);
				}
				
				Folder root = new Folder("root");
				Set<String> keys = mN1.keySet();
				List<String> keys2 = new ArrayList<String>();
				for (String key : keys) {
					keys2.add(key);
				}
				Collections.sort(keys2);
				
				for (String key: keys2) {
					Folder f = mN1.get(key);
					root.add(f);
				}
				this.load(root);
			}

		};
		
		System.out.println("2");
		TreeStore<ModelData> store = new TreeStore<ModelData>(loader);

		System.out.println("3");
		tree = new TreePanel<ModelData>(store);
		tree.setAutoLoad(true);
		tree.setLabelProvider(new ModelStringProvider<ModelData>() {
			public String getStringValue(ModelData model, String property) {
				return model.get("id")+". "+model.get("name");
			}
		});
		tree.setWidth(250);
		
		tree.setIconProvider(new ModelIconProvider<ModelData>() {
			public AbstractImagePrototype getIcon(ModelData model) {
				if (((TreeModel) model).isLeaf()) {
					return Resources.ICONS.form();
				}
				return null;
			}
		});
		
		System.out.println("4");
		System.out.println("5");
		
		filter.bind(store);

		// Add listener
		tree.addListener(Events.OnDoubleClick,
				new Listener<TreePanelEvent<ModelData>>() {
					public void handleEvent(TreePanelEvent<ModelData> be) {
						// If node is leaf (is a process)
						if (be.getNode().isLeaf()) {
							ModelData model = be.getItem();
							if (model != null) {
								Dispatcher.forwardEvent(
										AppEvents.ProcessSelected, model);
							}

						}
					};
				});
		
		this.add(tree);

		System.out.println("7");
	}

	public void selectProcess(ModelData process){
		tree.getSelectionModel().deselectAll();
		tree.getSelectionModel().select(process, true);
	}

	
}