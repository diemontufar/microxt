package mobile.web.webxt.client.data;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import mobile.common.message.EntityData;
import mobile.common.message.Field;
import mobile.common.message.Item;
import mobile.common.message.Message;
import mobile.common.tools.Format;
import mobile.web.webxt.client.data.form.DataSource;
import mobile.web.webxt.client.data.form.DataSourceType;
import mobile.web.webxt.client.parser.Parser;
import mobile.web.webxt.client.util.ConvertionManager;
import mobile.web.webxt.client.util.DatesManager;
import mobile.web.webxt.client.windows.AlertDialog;

import com.extjs.gxt.ui.client.data.BaseModelData;
import com.extjs.gxt.ui.client.data.BasePagingLoadResult;
import com.extjs.gxt.ui.client.data.DataReader;
import com.extjs.gxt.ui.client.data.ModelData;
import com.extjs.gxt.ui.client.data.PagingLoadResult;

public class MyReader implements DataReader<PagingLoadResult<ModelData>> {

	private final String MSG_TYPE = Message.JSON;

	public MyReader() {
	}

	public PagingLoadResult<ModelData> read(Object loadConfig, Object data) {
		System.out.println("MyReader.read: " + data.toString());
		ArrayList<ModelData> models = new ArrayList<ModelData>();
		PagingLoadResult<ModelData> paginatedModels = new BasePagingLoadResult<ModelData>(models);

		// Configuration
		MyProcessConfig config = (MyProcessConfig) loadConfig;

		try {
			String strData = data.toString();
			Parser parser = new Parser();
			Message msg = parser.parseMsg(strData, MSG_TYPE);
			System.out.println("entity: " + config.getReference().getEntity());
			EntityData entityData = msg.getEntityData(config.getReference().getEntity());

			System.out.println("reading items");
			for (Item item : entityData.getItemList()) {
				ModelData model = new BaseModelData();
				for (DataSource ds : config.getlDataSource()) {
					// System.out.println("::DS: " + ds.getAlias() + "-" +
					// ds.getField() + "-" + ds.getType());
					Field field = null;
					if (ds.getType() == DataSourceType.RECORD) {
						field = item.getField(ds.getField());
					} else if (ds.getType() == DataSourceType.DESCRIPTION) {
						field = item.getField(ds.getAlias() + "." + ds.getField());
					}
					if (field != null && field.getValue() != null) {
						model.set(ds.getField(), convertToType(field.getValue()));
					}

				}
				models.add(model);
			}

			System.out.println("Modelos");
			for (ModelData model : models) {
				for (String prop : model.getPropertyNames()) {
					System.out.println(prop + ":" + model.get(prop));
				}
			}

			// Pagination
			System.out.println("reading pagination");
			if (entityData.getOffset() != null) {
				paginatedModels.setOffset(entityData.getOffset());
			}
			if (entityData.getTotal() != null) {
				paginatedModels.setTotalLength(entityData.getTotal());
			}

		} catch (Exception e) {
			new AlertDialog("MyReader", e.getMessage()).show();
			e.printStackTrace();
		}

		return (PagingLoadResult<ModelData>) paginatedModels;
	}

	public static Object convertToType(String value) {
		final String INTEGER = "\\(\\(Integer\\)\\)";
		final String BIG_DECIMAL = "\\(\\(BigDecimal\\)\\)";
		final String BOOLEAN = "\\(\\(Boolean\\)\\)";
		final String LONG = "\\(\\(Long\\)\\)";
		final String DATE = "\\(\\(Date\\)\\)";
		final String TIMESTAMP = "\\(\\(Timestamp\\)\\)";

		Object cValue = null;

		// System.out.println("::Valor a convertir" + value);

		if (value == null) {
			return cValue;
		}

		if (value.matches("^(" + INTEGER + "|" + BIG_DECIMAL + "|" + BOOLEAN + "|" + LONG + "|" + DATE + "|"
				+ TIMESTAMP + ").*")) {
			if (value.matches("^(" + INTEGER + ").*")) {
				value = value.replaceAll("(" + INTEGER + ")", "");
				cValue = Integer.parseInt(value);
			} else if (value.matches("^(" + BIG_DECIMAL + ").*")) {
				value = value.replaceAll("(" + BIG_DECIMAL + ")", "");
				cValue = new BigDecimal(value);
			} else if (value.matches("^(" + BOOLEAN + ").*")) {
				value = value.replaceAll("(" + BOOLEAN + ")", "");
				cValue = ConvertionManager.parseBoolean(value);
			} else if (value.matches("^(" + LONG + ").*")) {
				value = value.replaceAll("(" + LONG + ")", "");
				cValue = Long.parseLong(value);
			} else if (value.matches("^(" + DATE + ").*")) {
				value = value.replaceAll("(" + DATE + ")", "");
				cValue = DatesManager.stringToDate(value, Format.DATE);
			} else if (value.matches("^(" + TIMESTAMP + ").*")) {
				value = value.replaceAll("(" + TIMESTAMP + ")", "");
				cValue = DatesManager.stringToDate(value, Format.TIMESTAMP);
			}
		} else {
			cValue = value;
		}
		return cValue;
	}

	public Message readMessage(Object loadConfig, Object data) {
		System.out.println("MyReader.readMessage" + data.toString());

		Message msg = null;

		try {
			String strData = data.toString();

			System.out.println("Parsing");
			Parser parser = new Parser();
			msg = parser.parseMsg(strData, MSG_TYPE);
		} catch (Exception e) {
			new AlertDialog("MyReader", e.getMessage()).show();
		}

		return msg;
	}

	@SuppressWarnings("unchecked")
	protected Object createReturnData(Object loadConfig, List<ModelData> records, int totalCount) {
		return (PagingLoadResult<ModelData>) records;
	}
}