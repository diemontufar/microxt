package mobile.web.webxt.client.form.widgetsgrid;

import java.util.Map;

import mobile.web.webxt.client.data.MyPagingLoader;
import mobile.web.webxt.client.data.MyProcessConfig;
import mobile.web.webxt.client.data.form.DataSource;
import mobile.web.webxt.client.data.form.DataSourceType;

import com.extjs.gxt.ui.client.data.BaseStringFilterConfig;
import com.extjs.gxt.ui.client.data.FilterConfig;
import com.extjs.gxt.ui.client.data.ModelData;
import com.extjs.gxt.ui.client.data.PagingLoader;
import com.extjs.gxt.ui.client.widget.Info;
import com.extjs.gxt.ui.client.widget.grid.Grid;
import com.extjs.gxt.ui.client.widget.toolbar.PagingToolBar;

public class GridPagingToolBar extends PagingToolBar {

	private Grid<ModelData> grid;

	public GridPagingToolBar(Grid<ModelData> grid, int pageSize) {
		super(pageSize);
		this.grid = grid;
		this.bind((PagingLoader<?>) grid.getStore().getLoader());
		this.setReuseConfig(false);
	}

	@Override
	protected void doLoadRequest(int offset, int limit) {
		if (grid instanceof EntityEditorGrid) {
			if(((EntityEditorGrid) grid).validateDependencies()){
				EntityEditorGrid grid = (EntityEditorGrid) this.grid;
				try {
					MyProcessConfig config = (MyProcessConfig) ((MyPagingLoader) grid.getStore().getLoader()).getConfig();
					Map<DataSource, String> map = grid.getDsDependencies();
					if (map != null) {
						for (DataSource ds : map.keySet()) {
							String value = map.get(ds);
							if (ds.getType() == DataSourceType.CRITERION && value != null) {
								FilterConfig filter = new BaseStringFilterConfig();
								filter.setField(ds.getField());
								filter.setComparison(ds.getComparator());
								filter.setValue(value);
								config.addFilter(filter);
							}
						}
					}
					super.doLoadRequest(offset, limit);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}else{
				Info.display("Campos requeridos", "Existe campos requeridos que no han sido ingresados");
			}
		}
	}
}
