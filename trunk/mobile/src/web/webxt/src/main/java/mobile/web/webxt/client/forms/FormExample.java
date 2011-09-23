package mobile.web.webxt.client.forms;

import mobile.web.webxt.client.widgets.CustomFormPanel;
import mobile.web.webxt.client.widgets.InputBox;

import com.extjs.gxt.ui.client.widget.LayoutContainer;
import com.google.gwt.user.client.Element;


public class FormExample extends LayoutContainer{
	
		@Override 
		protected void onRender(Element parent, int index) {  
		    super.onRender(parent, index); 
			
			CustomFormPanel fp = new CustomFormPanel("Input boxes ejemplo",350);
			InputBox ib1 = new InputBox("Alphabet",50,"txt");
			InputBox ib2 = new InputBox("Txt-alfanum",80,"txt-alfanum");
			InputBox ib3 = new InputBox("Numeric",50,"num");
			InputBox ib4 = new InputBox("Password",80,"pass");
			InputBox ib5 = new InputBox("Email",50,"email");
			
			fp.add(ib1);
			fp.add(ib2);
			fp.add(ib3);
			fp.add(ib4);
			fp.add(ib5);

			add(fp);
		}
}
