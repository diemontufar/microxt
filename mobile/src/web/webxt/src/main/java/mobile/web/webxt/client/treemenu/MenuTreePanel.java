package mobile.web.webxt.client.treemenu;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import mobile.common.message.Message;
import mobile.web.webxt.client.data.MyHttpProxy;
import mobile.web.webxt.client.data.MyMessageReader;
import mobile.web.webxt.client.data.MyProcessConfig;
import mobile.web.webxt.client.mvc.AppEvents;
import mobile.web.webxt.client.resources.Resources;
import mobile.web.webxt.client.windows.AlertDialog;

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
import com.extjs.gxt.ui.client.widget.layout.FitLayout;
import com.extjs.gxt.ui.client.widget.treepanel.TreePanel;
import com.google.gwt.user.client.Element;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.AbstractImagePrototype;

public class MenuTreePanel extends LayoutContainer {

	private final String PROCESS = "G001";
	
	private TreePanel<ModelData> tree;
	
	StoreFilterField<ModelData> filter;
	
	public MenuTreePanel(StoreFilterField<ModelData> filter) {
		this.filter = filter;
	}
	
	@Override
	protected void onRender(Element parent, int index) {
		super.onRender(parent, index);
		setLayout(new FitLayout());
		
		final TreeLoader<ModelData> loader = new BaseTreeLoader<ModelData>(
				new TreeModelReader<List<ModelData>>()) {
			@Override
			public boolean load() {
				Dispatcher.forwardEvent(AppEvents.UserNotification,"Cargando menu");
				AsyncCallback<Message> callback = new AsyncCallback<Message>() {
					public void onFailure(Throwable caught) {
						new AlertDialog("FilterTreePanel", caught.getMessage())
								.show();
					}

					public void onSuccess(Message result) {
						onLoadSuccess(null, result);
					}
				};
				MyProcessConfig config = new MyProcessConfig(PROCESS);
				MyHttpProxy proxy = new MyHttpProxy();
				proxy.requestMsg(config, callback);
				return true;
			}

			protected void onLoadSuccess(Object loadConfig, Message result) {
				List<ModelData> subsystemModels = MyMessageReader.getModels(
						result, "Subsystem");
				List<ModelData> moduleModels = MyMessageReader.getModels(
						result, "Module");
				List<ModelData> processModels = MyMessageReader.getModels(
						result, "Process");

				if(subsystemModels.size()==0
						&& moduleModels.size()==0
						&& processModels.size()==0){
					new AlertDialog("Menu Principal", "Error al consultar los elementos del menu.").show();
					Dispatcher.forwardEvent(AppEvents.UserNotification,"Error al cargar el menu");
					return;
				}
				
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
				Dispatcher.forwardEvent(AppEvents.UserNotification,"Listo");
			}

		};
		
		TreeStore<ModelData> store = new TreeStore<ModelData>(loader);

		tree = new TreePanel<ModelData>(store);
		tree.setAutoLoad(true);
		tree.setLabelProvider(new ModelStringProvider<ModelData>() {
			public String getStringValue(ModelData model, String property) {
				return model.get("id")+". "+model.get("name");
			}
		});
		tree.setWidth("100%");
		
		tree.setIconProvider(new ModelIconProvider<ModelData>() {
			public AbstractImagePrototype getIcon(ModelData model) {
				if (((TreeModel) model).isLeaf()) {
					return Resources.ICONS.form();
				}
				return null;
			}
		});
		
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
	}

	public void selectProcess(ModelData process){
		tree.getSelectionModel().deselectAll();
		tree.getSelectionModel().select(process, true);
	}

	
}