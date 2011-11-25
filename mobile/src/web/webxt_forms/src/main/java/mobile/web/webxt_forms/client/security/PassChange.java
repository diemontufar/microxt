package mobile.web.webxt_forms.client.security;

import com.extjs.gxt.ui.client.Style.HorizontalAlignment;
import com.extjs.gxt.ui.client.core.El;
import com.extjs.gxt.ui.client.core.XDOM;
import com.extjs.gxt.ui.client.event.ComponentEvent;
import com.extjs.gxt.ui.client.event.Events;
import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.widget.Component;
import com.extjs.gxt.ui.client.widget.ComponentPlugin;
import com.extjs.gxt.ui.client.widget.LayoutContainer;
import com.extjs.gxt.ui.client.widget.VerticalPanel;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.form.FormButtonBinding;
import com.extjs.gxt.ui.client.widget.form.FormPanel;
import com.extjs.gxt.ui.client.widget.form.TextField;
import com.extjs.gxt.ui.client.widget.layout.FormData;
import com.google.gwt.user.client.Element;
  
public class PassChange extends LayoutContainer {  
    
  private VerticalPanel vp;  
  private FormData formData;  
  
  @Override  
  protected void onRender(Element parent, int index) {  
    super.onRender(parent, index);  
    formData = new FormData("-20");  
    vp = new VerticalPanel();  
    vp.setSpacing(10);  
    createForm1();  
    add(vp);  
  }  
  
  private void createForm1() {  
    FormPanel simple = new FormPanel();  
    simple.setHeading("");  
    simple.setFrame(true);  
    simple.setWidth(350);  
  
    ComponentPlugin plugin = new ComponentPlugin() {  
      public void init(Component component) {  
        component.addListener(Events.Render, new Listener<ComponentEvent>() {  
          public void handleEvent(ComponentEvent be) {  
            El elem = be.getComponent().el().findParent(".x-form-element", 3);  
            // should style in external CSS  rather than directly  
            elem.appendChild(XDOM.create("<div style='color: #615f5f;padding: 1 0 2 0px;'>" + be.getComponent().getData("text") + "</div>"));  
          }  
        });  
      }  
    };  
  
    TextField<String> user = new TextField<String>();  
    user.setFieldLabel("Usuario");  
    user.setAllowBlank(false);  
    user.addPlugin(plugin);  
    user.setData("text", "Ingrese el usuario");  
    simple.add(user, formData);  
  
    TextField<String> Npass = new TextField<String>();  
    Npass.setFieldLabel("Contraseña Nueva");  
    Npass.setAllowBlank(false);  
    Npass.addPlugin(plugin);  
    Npass.setData("text", "Ingrese contraseña(*)");  
    simple.add(Npass, formData);  
    
    TextField<String> Cpass = new TextField<String>();  
    Cpass.setFieldLabel("Confirmar Contraseña");  
    Cpass.setAllowBlank(false);  
    Cpass.addPlugin(plugin);  
    Cpass.setData("text", "");  
    simple.add(Cpass, formData);  
  
    Button b = new Button("Ok");  
    simple.addButton(b);  
    simple.addButton(new Button("Cancelar"));  
  
    simple.setButtonAlign(HorizontalAlignment.CENTER);  
  
    FormButtonBinding binding = new FormButtonBinding(simple);  
    binding.addButton(b);  
  
    vp.add(simple);  
  }  
  
    
}
