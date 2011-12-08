package mobile.web.webxt_mvc.client.devform;

import java.util.ArrayList;
import java.util.List;

import mobile.web.webxt_mvc.client.data.MyHttpProxy;
import mobile.web.webxt_mvc.client.data.MyListStore;
import mobile.web.webxt_mvc.client.data.MyPagingLoader;
import mobile.web.webxt_mvc.client.data.MyProcessConfig;
import mobile.web.webxt_mvc.client.data.MyProcessConfig.ProcessType;
import mobile.web.webxt_mvc.client.form.ArrayColumnData;
import mobile.web.webxt_mvc.client.form.ComboColumn;
import mobile.web.webxt_mvc.client.form.MyColumnData;
import mobile.web.webxt_mvc.client.form.MyComboBox;
import mobile.web.webxt_mvc.client.widgets.CustomFormPanel;
import mobile.web.webxt_mvc.client.widgets.InputBox;

import com.extjs.gxt.ui.client.data.ModelData;
import com.extjs.gxt.ui.client.data.PagingLoadResult;
import com.extjs.gxt.ui.client.widget.LayoutContainer;
import com.extjs.gxt.ui.client.widget.form.ComboBox.TriggerAction;
import com.extjs.gxt.ui.client.widget.grid.CellEditor;
import com.extjs.gxt.ui.client.widget.layout.CenterLayout;
import com.google.gwt.user.client.Element;

public class A205 extends LayoutContainer {

private final Integer PAGE_SIZE = 5;
	
	private final String process = "A205";
	

	@Override
	protected void onRender(Element parent, int index) {
		super.onRender(parent, index);
		setLayout(new CenterLayout());
		getAriaSupport().setPresentation(true);
		
		// Config
		String entity = "UserAccess";
				
		CustomFormPanel fp = new CustomFormPanel("Acceso Usuarios", 350);
		
		final List<String> lfields = new ArrayList<String>();
		lfields.add("pk_userId");
		
		MyProcessConfig config = new MyProcessConfig(process, entity, lfields);
		config.setProcessType(ProcessType.QUERY);
		//this.getComboEditor(process, entity, lfields);
		
		// Proxy - Loader - Store

		final MyHttpProxy<PagingLoadResult<ModelData>> proxy = new MyHttpProxy<PagingLoadResult<ModelData>>();
		final MyPagingLoader<PagingLoadResult<ModelData>> loader = new MyPagingLoader<PagingLoadResult<ModelData>>(proxy, config);
		final MyListStore<ModelData> store = new MyListStore<ModelData>(loader);
		
		//InputBox usuario = new InputBox("Usuario", 50, "txt"); //combo
		
		// Combo
		final MyComboBox<ModelData> userCombo = new MyComboBox<ModelData>();

		userCombo.setForceSelection(true);
		userCombo.setFieldLabel("Usuario"); 
		userCombo.setTriggerAction(TriggerAction.ALL);
		userCombo.setDisplayField("userId");
		userCombo.setStore(store);
		//combo.setTemplate(getTemplate());
		//combo.setMinListWidth(width);
		userCombo.setMinListWidth(100);
		userCombo.setPageSize(5);

		userCombo.setItemSelector("tr.search-item");
		userCombo.setEditable(false);
		userCombo.setForceSelection(true);

		
//		ComboBox<Stock> combo = new ComboBox<Stock>();  
//	    combo.setFieldLabel("Usuario");  
//	    combo.setDisplayField("pk_userId");  
//	    combo.setTriggerAction(TriggerAction.ALL);  
//	    combo.setStore(store);
//		
//		
//		ComboColumn personComboColumn = new ComboColumn(cdata.get(0));
//		ArrayColumnData cdataComboPerson = new ArrayColumnData();
//		cdataComboPerson.add(new MyColumnData("pk_userId", "ID", 40));
//		cdataComboPerson.add(new MyColumnData("name", "Nombre", 150));
//		personComboColumn.setRqData("UserAccount", cdataComboPerson);
		
		
		InputBox password = new InputBox("Password", 50, "pass"); 
		InputBox cambio = new InputBox("Ultimo Cambio", 50, "txt-alfanum"); //cambio
		InputBox pregunta = new InputBox("Pregunta", 50, "txt-alfanum"); //cambio
		InputBox respuesta = new InputBox("Respuesta", 50, "txt-alfanum"); //cambio
		
		fp.add(userCombo);
		fp.add(password);
		fp.add(cambio);
		fp.add(pregunta);
		fp.add(respuesta);

		add(fp);
	}
	
		
}