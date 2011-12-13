package mobile.web.webxt_mvc.client.devform;

import mobile.web.webxt_mvc.client.form.ArrayColumnData;
import mobile.web.webxt_mvc.client.form.ComboForm;
import mobile.web.webxt_mvc.client.form.MyColumnData;
import mobile.web.webxt_mvc.client.widgets.CustomFormPanel;
import mobile.web.webxt_mvc.client.widgets.InputBox;
import mobile.web.webxt_mvc.client.windows.AlertDialog;


import com.extjs.gxt.ui.client.Style.HorizontalAlignment;
import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.Events;
import com.extjs.gxt.ui.client.event.FormEvent;
import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.event.WindowEvent;
import com.extjs.gxt.ui.client.widget.LayoutContainer;
import com.extjs.gxt.ui.client.widget.MessageBox;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.form.DateField;
import com.extjs.gxt.ui.client.widget.form.FormPanel;
import com.extjs.gxt.ui.client.widget.layout.CenterLayout;
import com.google.gwt.user.client.Element;

public class A205 extends FormPanel {

	private final Integer PAGE_SIZE = 5;

	private final String process = "A205";
	
	private final String title = "Acceso Usuarios";
	
	private final String width = "350";

	// fields:
	private InputBox password;
	private InputBox retypePassword;
	private ComboForm userComboColumn;
	private DateField cambio;

	
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
		userComboColumn = new ComboForm("Usuario", "pk_userId");
		ArrayColumnData cdataComboUser = new ArrayColumnData();
		cdataComboUser.add(new MyColumnData("pk_userId", "ID", 40));
		cdataComboUser.add(new MyColumnData("name", "Nombre", 150));
		userComboColumn.setRqData("UserAccount", cdataComboUser);

		password = new InputBox("Password","UserAccess.USER_KEY", 50, "pass");
		retypePassword = new InputBox("Repita Password","UserAccess.USER_KEY", 50, "pass");

		cambio = new DateField();
		cambio.setName("date");
		cambio.setFieldLabel("Ultimo Cambio");

		add(userComboColumn);
		add(password);
		add(retypePassword);
		add(cambio);

		setButtonAlign(HorizontalAlignment.CENTER);
		addButton(new Button("Limpiar",
				new SelectionListener<ButtonEvent>() {
					@Override
					public void componentSelected(ButtonEvent ce) {
						// grid.getStore().rejectChanges();
					}
				}));
		addButton(new Button("Guardar",
				new SelectionListener<ButtonEvent>() {
					@Override
					public void componentSelected(ButtonEvent ce) {
						
//						for (int i=0;i<getItemCount();i++){
//							System.out.println(getFields());
//						}
//						
//						System.out.println("hola");
//						System.out.println(getItemCount());
						//validateForm();
						// grid.getStore().commitChanges();
					}
				}));

	}
	
	

//	private void validateForm() {
//		 if ( password.getValue().toString().compareTo("")==0 ){
//			 new AlertDialog("Advertencia","Algun campo esta vacio o incorrecto");
//		 }
//
//	}

}