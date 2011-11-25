package mobile.web.webxt_forms.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.RootPanel;

import mobile.web.webxt_forms.client.security.EditableGrid2;
import mobile.web.webxt_forms.client.security.PassChange;
import mobile.web.webxt_forms.client.security.FormExample;
import mobile.web.webxt_forms.client.security.PositionWidget;

public class Principal implements EntryPoint {

	public void onModuleLoad() {
		// Call the Login window
		PositionWidget pw =new PositionWidget("MANTENIMIENTO DE CONTRASEÑAS");
		EditableGrid2 fe =new EditableGrid2();
		PassChange cfe = new PassChange();
		pw.add(fe);
		//pw.add(cfe);
		//pw.add(new Button("Holi"));
		
		RootPanel.get().add(pw);
	}

}
