package mobile.web.webxt.client.resources;

import com.google.gwt.user.client.ui.AbstractImagePrototype;
import com.google.gwt.user.client.ui.ImageBundle;

@SuppressWarnings("deprecation")
public interface Images extends ImageBundle {
	//String path = "img/icons/";
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
	
	@Resource(path + "recomendacion.png")
	AbstractImagePrototype recomendation();

}
