package mobile.web.webxt.client.form;

import mobile.web.webxt.client.data.MyHttpProxy;
import mobile.web.webxt.client.data.MyListStore;
import mobile.web.webxt.client.data.MyPagingLoader;
import mobile.web.webxt.client.data.MyProcessConfig;
import mobile.web.webxt.client.data.form.Reference;

import com.extjs.gxt.ui.client.widget.LayoutContainer;
import com.extjs.gxt.ui.client.widget.layout.CenterLayout;
import com.google.gwt.user.client.Element;

public class MyGeneralForm extends LayoutContainer {

	private String process;
	private MyProcessConfig config;
	
	private MyHttpProxy proxy = null;
	private MyPagingLoader loader;
	private MyListStore store;
	
	public MyGeneralForm() {
	}

	public MyGeneralForm(String process) {
		this.proxy = new MyHttpProxy();
		this.process = process;
		this.config = new MyProcessConfig(process);
	}
	
	public MyGeneralForm(String process, boolean hasGrid) {
		this(process);
		if (hasGrid){
			loader = new MyPagingLoader(proxy, config);
			store = new MyListStore(loader);
		}
	}
	
	protected void onRender(Element parent, int index) {
		super.onRender(parent, index);
		setLayout(new CenterLayout());
		getAriaSupport().setPresentation(true);
	}

	public String getProcess() {
		return process;
	}

	public void setProcess(String process) {
		this.process = process;
	}

	public void setReference(String entity) {
		this.config.setReference(new Reference(entity));
	}
	
	public void setReference(Reference reference) {
		this.config.setReference(reference);
	}

	public MyProcessConfig getConfig() {
		return config;
	}

	public void setConfig(MyProcessConfig config) {
		this.config = config;
	}

	public MyHttpProxy getProxy() {
		return proxy;
	}

	public MyPagingLoader getLoader() {
		return loader;
	}

	public void setLoader(MyPagingLoader loader) {
		this.loader = loader;
	}

	public MyListStore getStore() {
		return store;
	}

	public void setStore(MyListStore store) {
		this.store = store;
	}

	public void setProxy(MyHttpProxy proxy) {
		this.proxy = proxy;
	}
	
}
