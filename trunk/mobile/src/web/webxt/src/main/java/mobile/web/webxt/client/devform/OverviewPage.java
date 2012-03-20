package mobile.web.webxt.client.devform;

import java.util.ArrayList;
import java.util.List;

import mobile.web.webxt.client.mvc.AppEvents;
import mobile.web.webxt.client.resources.Resources;
import mobile.web.webxt.client.util.ShortcutProcess;

import com.extjs.gxt.ui.client.Style.Scroll;
import com.extjs.gxt.ui.client.data.BaseModelData;
import com.extjs.gxt.ui.client.data.ModelData;
import com.extjs.gxt.ui.client.event.SelectionChangedEvent;
import com.extjs.gxt.ui.client.event.SelectionChangedListener;
import com.extjs.gxt.ui.client.mvc.Dispatcher;
import com.extjs.gxt.ui.client.store.ListStore;
import com.extjs.gxt.ui.client.widget.LayoutContainer;
import com.extjs.gxt.ui.client.widget.ListView;
import com.extjs.gxt.ui.client.widget.layout.FitLayout;
import com.google.gwt.user.client.Element;

public class OverviewPage extends LayoutContainer {

	private ListView<ShortcutProcess> dataView;
	List<ShortcutProcess> lProcess;

	public OverviewPage() {
	}

	@Override
	protected void onRender(Element parent, int pos) {
		super.onRender(parent, pos);
		setLayout(new FitLayout());
		setScrollMode(Scroll.AUTO);

		lProcess = new ArrayList<ShortcutProcess>();
		lProcess.add(new ShortcutProcess("B101", "Personas Naturales", Resources.IMAGES.person().getHTML()));
		lProcess.add(new ShortcutProcess("B102", "Dierecciones", Resources.IMAGES.address().getHTML()));
		lProcess.add(new ShortcutProcess("B103", "Telefonos", Resources.IMAGES.phone().getHTML()));
		lProcess.add(new ShortcutProcess("C201", "Socios Individuales", Resources.IMAGES.individual().getHTML()));
		lProcess.add(new ShortcutProcess("C202", "Socios Grupales", Resources.IMAGES.groupal().getHTML()));
		lProcess.add(new ShortcutProcess("C301", "Solicitud", Resources.IMAGES.solicitude().getHTML()));
		lProcess.add(new ShortcutProcess("C201", "Zonas Asignadas", Resources.IMAGES.zones().getHTML()));
		lProcess.add(new ShortcutProcess("C302", "Aprobación", Resources.IMAGES.approval().getHTML()));
		lProcess.add(new ShortcutProcess("C402", "Instrumentación", Resources.IMAGES.instrumentation().getHTML()));
		lProcess.add(new ShortcutProcess("C503", "Pago de Cuotas", Resources.IMAGES.payment().getHTML()));
		lProcess.add(new ShortcutProcess("C401", "Consulta Solicitudes", Resources.IMAGES.solicitudes().getHTML()));
		lProcess.add(new ShortcutProcess("C502", "Consulta de Cuotas", Resources.IMAGES.quotas().getHTML()));
		lProcess.add(new ShortcutProcess("C401", "Movimientos del Día", Resources.IMAGES.movements().getHTML()));
		ListStore<ShortcutProcess> store = new ListStore<ShortcutProcess>();
		store.add(lProcess);

		StringBuffer sb = new StringBuffer();
		sb.append("<tpl for=\".\">");
		sb.append("<div class='sample-box' style='padding-top: 4px;border: none'>");
		sb.append("<div class='thumbd'><center>{image}</center></div>");
		sb.append("<div><center>{name}</center></div>");
		sb.append("</div></tpl>");

		dataView = new ListView<ShortcutProcess>();
		dataView.addStyleName("overview-page");
		dataView.setItemSelector(".sample-box");
		dataView.setOverStyle("sample-over");
		dataView.setSelectStyle("none");
		dataView.setBorders(false);
		dataView.setStore(store);
		dataView.setTemplate(sb.toString());

		dataView.getSelectionModel().addSelectionChangedListener(new SelectionChangedListener<ShortcutProcess>() {

			@Override
			public void selectionChanged(SelectionChangedEvent<ShortcutProcess> se) {
				if (se.getSelectedItem() != null) {
					ModelData model = new BaseModelData();
					model.set("id", se.getSelectedItem().getProcess());
					Dispatcher.forwardEvent(AppEvents.ProcessSelected, model);

					dataView.getSelectionModel().deselectAll();
				}
			}
		});
		add(dataView);
	}
}
