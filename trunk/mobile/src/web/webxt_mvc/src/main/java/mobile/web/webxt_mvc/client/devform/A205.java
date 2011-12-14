package mobile.web.webxt_mvc.client.devform;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import mobile.web.webxt_mvc.client.form.PersistentField;
import mobile.web.webxt_mvc.client.validations.Validate;
import mobile.web.webxt_mvc.client.widgets.InputBox;
import mobile.web.webxt_mvc.client.windows.AlertDialog;

import com.extjs.gxt.ui.client.Style.HorizontalAlignment;
import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.form.Field;
import com.extjs.gxt.ui.client.widget.form.FormPanel;

public class A205 extends FormPanel {

	private final String title = "Cambio de Contraseña";
	
	private final String width = "350";
	
	Map<String,String> map=new HashMap<String, String>();
	
	
	// fields:
	private InputBox user;
	private InputBox oldPassword;
	private InputBox retypePassword;
	private InputBox newPassword;
	//private ComboForm userComboColumn;

	
	public A205() {
				
		setHeading(title);
		setFrame(true);
		setWidth(width);

		// Config
		String entity = "UserAccess";

		
//		fp.addListener(Events.Submit, new Listener<FormEvent>() {
//            public void handleEvent(FormEvent arg0) {
//
//                MessageBox.alert("Result", "Check Forums on how to get an actual result: "
//                        + arg0.resultHtml, new Listener<WindowEvent>(){
//                    public void handleEvent(WindowEvent arg0) {}
//                });
//             }
//         }
		

		// Combo
//		userComboColumn = new ComboForm("Usuario", "pk_userId");
//		ArrayColumnData cdataComboUser = new ArrayColumnData();
//		cdataComboUser.add(new MyColumnData("pk_userId", "ID", 40));
//		cdataComboUser.add(new MyColumnData("name", "Nombre", 150));
//		userComboColumn.setRqData("UserAccount", cdataComboUser);

		user = new InputBox("Usuario",entity,"userId",1, 50, Validate.TEXT);
		oldPassword = new InputBox("Contraseña anterior",entity,"userKey",2, 50, Validate.PASSWORD);
		retypePassword = new InputBox("Repita Contraseña",entity,"userKey",3, 50, Validate.PASSWORD);
		newPassword = new InputBox("Contraseña nueva",entity,"userKey",4, 50, Validate.PASSWORD);

		add(user);
		add(oldPassword);
		add(retypePassword);
		add(newPassword);
		

		setButtonAlign(HorizontalAlignment.CENTER);
		addButton(new Button("Limpiar",
				new SelectionListener<ButtonEvent>() {
					@Override
					public void componentSelected(ButtonEvent ce) {
						user.clear();
						oldPassword.clear();
						retypePassword.clear();
						newPassword.clear();
					}
				}));
		
		addButton(new Button("Guardar",
				new SelectionListener<ButtonEvent>() {
					@Override
					public void componentSelected(ButtonEvent ce) {
						
						List<Field<?>> l = getFields();
						for (Field<?> f : l) {
							if(f instanceof PersistentField){
								String key = ((PersistentField) f).getEntity() + ":" + ((PersistentField) f).getField() +":"+ ((PersistentField) f).getRegister();
								System.out.println(key);
								System.out.println(f.getValue());
								
								map.put(key, f.getValue().toString());
							}
						}
												
						//validateForm();
						// grid.getStore().commitChanges();
					}
				}));

	}
	
	public boolean validateForm(){
		boolean result = false;
		if (	user.getValue().compareTo("")==0
				|| oldPassword.getValue().compareTo("")==0
				|| retypePassword.getValue().compareTo("")==0
				|| newPassword.getValue().compareTo("")==0	){
			result=true;
			new AlertDialog("Advertencia","Algun campo vacio, ingrese los datos correctos");
		}
		
		return result;
	}
		
}