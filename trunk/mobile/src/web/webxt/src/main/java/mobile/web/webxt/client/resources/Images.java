package mobile.web.webxt.client.resources;

import com.google.gwt.user.client.ui.AbstractImagePrototype;
import com.google.gwt.user.client.ui.ImageBundle;

@SuppressWarnings("deprecation")
public interface Images extends ImageBundle {
	
	String path = "";

	@Resource(path + "solicitud.png")
	AbstractImagePrototype solicitude();
	
	@Resource(path + "persona.png")
	AbstractImagePrototype person();
	
	@Resource(path + "individual.png")
	AbstractImagePrototype individual();
	
	@Resource(path + "grupales.png")
	AbstractImagePrototype groupal();
	
	@Resource(path + "zonas.png")
	AbstractImagePrototype zones();
	
	@Resource(path + "address.png")
	AbstractImagePrototype address();
	
	@Resource(path + "phone.png")
	AbstractImagePrototype phone();
	
	@Resource(path + "nodisponible.png")
	AbstractImagePrototype approval();
	
	@Resource(path + "instrumentation.jpg")
	AbstractImagePrototype instrumentation();
	
	@Resource(path + "paycuota.jpg")
	AbstractImagePrototype payment();
	
	@Resource(path + "solquery.jpg")
	AbstractImagePrototype solicitudes();
	
	@Resource(path + "quotaquery.jpg")
	AbstractImagePrototype quotas();
	
	@Resource(path + "movimientos.jpg")
	AbstractImagePrototype movements();

}
