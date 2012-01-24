package mobile.web.webxt.client.data;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import mobile.common.message.EntityData;
import mobile.common.message.Field;
import mobile.common.message.Item;
import mobile.common.message.Message;
import mobile.common.message.ResponseData;
import mobile.web.webxt.client.data.MyProcessConfig.ProcessType;
import mobile.web.webxt.client.mvc.AppEvents;
import mobile.web.webxt.client.util.ConvertionManager;
import mobile.web.webxt.client.windows.AlertDialog;

import com.extjs.gxt.ui.client.data.DataProxy;
import com.extjs.gxt.ui.client.data.DataReader;
import com.extjs.gxt.ui.client.data.FilterConfig;
import com.extjs.gxt.ui.client.data.FilterPagingLoadConfig;
import com.extjs.gxt.ui.client.data.ModelData;
import com.extjs.gxt.ui.client.data.PagingLoadResult;
import com.extjs.gxt.ui.client.mvc.Dispatcher;
import com.google.gwt.http.client.Request;
import com.google.gwt.http.client.RequestBuilder;
import com.google.gwt.http.client.RequestCallback;
import com.google.gwt.http.client.Response;
import com.google.gwt.http.client.URL;
import com.google.gwt.user.client.rpc.AsyncCallback;


public class MyHttpProxy implements DataProxy<PagingLoadResult<ModelData>> {

	private final String CONTENT_TYPE = "text/plain; charset=utf-8";
	//private final String CONTENT_TYPE = "application/x-www-form-urlencoded";
	
	protected RequestBuilder builder;
	protected String initUrl;
	private final String url = "http://127.0.0.1:9090/mobile/Core";
	private final MyReader reader;

	public MyHttpProxy() {
		builder = new RequestBuilder(RequestBuilder.POST, URL.encode(url));
		builder.setHeader("Content-Type", CONTENT_TYPE);
		builder.setHeader("Accept", "application/json");
		this.initUrl = builder.getUrl();
		reader = new MyReader();
	}

	public void load(final DataReader<PagingLoadResult<ModelData>> readerNull,
			final Object loadConfig,
			final AsyncCallback<PagingLoadResult<ModelData>> callback) {
		System.out.println("MyHttpProxy.load: " + loadConfig.toString());

		Dispatcher.forwardEvent(AppEvents.UserNotification,
				"Procesando consulta");

		// Configuration
		final MyProcessConfig config = (MyProcessConfig) loadConfig;

		try {
			// Request
			Message msg = new Message();
			msg.getRequest().setProcess(config.getProcess());

			// Entity
			EntityData entityData = new EntityData(config.getEntity());
			entityData.setProcessType(ProcessType.QUERY.getProcessType());
			String queryFields = "";
			int queryFieldsCounter = 0;
			for (String strField : config.getlFields()) {
				if (queryFieldsCounter > 0) {
					queryFields = queryFields + ";";
				}
				queryFields = queryFields + strField;
				queryFieldsCounter++;
			}
			entityData.setQueryFields(queryFields);

			// Entity.pagination
			FilterPagingLoadConfig paginationConfig = (FilterPagingLoadConfig) loadConfig;
			System.out.println("Limit:" + paginationConfig.getLimit());
			System.out.println("Offset:" + paginationConfig.getOffset());
			entityData.setOffset(paginationConfig.getOffset());
			entityData.setLimit(paginationConfig.getLimit());

			// Entity.ordering
			System.out.println("Order:" + paginationConfig.getSortField() + ":"
					+ paginationConfig.getSortDir());
			if (paginationConfig.getSortField() != null) {
				entityData.setOrderBy(paginationConfig.getSortField());
				entityData
						.setOrderDir(paginationConfig.getSortDir().toString());
			}

			// Entity.filtering
			List<FilterConfig> filters = config.getFilterConfigs();
			System.out.println("Filters: " + filters);
			if (filters != null && filters.size() > 0) {
				String strFilters = "";
				int filtersCounter = 0;
				for (FilterConfig filter : filters) {
					System.out.println("Filter:" + filter.getField() + ":"
							+ filter.getComparison() + ":" + filter.getValue());
					String strFilter = filter.getField()
							+ ":"
							+ (filter.getComparison() == null ? "" : filter
									.getComparison()) + ":" + filter.getValue();
					if (filtersCounter > 0) {
						strFilters = strFilters + ";";
					}
					strFilters = strFilters + strFilter;
					filtersCounter++;
				}
				entityData.setFilter(strFilters);
			}

			msg.addData(entityData);

			//String data = "message=" + msg.toJSON();
			String data = msg.toJSON();
			// data = generateUrl(loadConfig);

			System.out.println("Send request...");
			builder.sendRequest(data, new RequestCallback() {
				public void onError(Request request, Throwable exception) {
					callback.onFailure(exception);
				}

				public void onResponseReceived(Request request,
						Response response) {
					try {
						evaluateResponse(response, config);
						String text = response.getText();
						PagingLoadResult<ModelData> data = reader.read(config,
								text);
						callback.onSuccess(data);
					} catch (Exception e) {
						callback.onFailure(e);
					}
				}
			});
		} catch (Exception e) {
			callback.onFailure(e);
			e.printStackTrace();
		}
	}

	public void requestMsg(final MyProcessConfig config,
			final AsyncCallback<Message> callback) {
		System.out.println("MyHttpProxy.requestMsg: " + config.toString());

		try {
			// Request
			Message msg = new Message();
			msg.getRequest().setProcess(config.getProcess());

			System.out.println("Conversion en json");
			//String data = "message=" + msg.toJSON();
			String data = msg.toJSON();

			builder.sendRequest(data, new RequestCallback() {
				public void onError(Request request, Throwable e) {
					callback.onFailure(e);
				}

				public void onResponseReceived(Request request,
						Response response) {
					try {
						Message responseMsg = evaluateResponse(response, config);
						callback.onSuccess(responseMsg);
					} catch (Exception e) {
						showError(e);
					}
				}
			});

		} catch (Exception e) {
			showError(e);
			e.printStackTrace();
		}
	}

	public void queryForm(final MyProcessConfig config,
			Map<String, String> mfields,
			final AsyncCallback<Map<String, String>> callback) {
		System.out.println("MyHttpProxy.queryForm: " + config.toString());

		try {
			// Request
			Message msg = new Message();
			msg.getRequest().setProcess(config.getProcess());

			// Data map
			Map<String, EntityData> mdata = new HashMap<String, EntityData>();

			// Fill datas
			for (String key : mfields.keySet()) {
				System.out.println(key);
				String value = mfields.get(key);
				System.out.println(value);

				String[] kp = key.split(":");
				String entityName = kp[0];
				String fieldName = kp[1];
				int register = Integer.parseInt(kp[2]);
				String property = null;
				if (kp.length > 3) {
					property = kp[3];
				}

				// Data
				EntityData data = mdata.get(entityName);
				if (data == null) {
					data = new EntityData(entityName);
					data.setProcessType(ProcessType.QUERY.getProcessType());
					mdata.put(entityName, data);
				}
				// Filters
				if (property != null && property.trim().length() > 0) {
					String filter = fieldName + ":=:" + value;
					if (data.getFilters() == null) {
						data.setFilter("");
					} else {
						data.setFilter(data.getFilters() + ";");
					}
					data.setFilter(data.getFilters() + filter);
				}
				// Items
				Item item = data.getItem(register);
				if (item == null) {
					item = new Item(register);
					data.addItem(item);
				}
				// Fields
				Field field = new Field(fieldName, null);
				if (value != null && value.trim().length() > 0) {
					field.setValue(value);
				}
				item.addField(field);
			}

			for (String entity : mdata.keySet()) {
				msg.addData(mdata.get(entity));
			}

			// Send message
			System.out.println("Set message in json format...");
			String data = "";
			try {
				//data = "message=" + msg.toJSON();
				data = msg.toJSON();
			} catch (Exception e) {
				e.printStackTrace();
			}

			System.out.println("Send request...");
			builder.sendRequest(data, new RequestCallback() {
				public void onError(Request request, Throwable exception) {
					callback.onFailure(exception);
				}

				public void onResponseReceived(Request request,
						Response response) {
					try {
						Message rspMsg = evaluateResponse(response, config);
						Map<String, String> mrfields = MyMessageReader
								.toMap(rspMsg);
						;
						callback.onSuccess(mrfields);
					} catch (Exception e) {
						callback.onFailure(e);
					}
				}
			});
		} catch (Exception e) {
			callback.onFailure(e);
		}
	}

	private void showError(Throwable e) {
		new AlertDialog("MyHttpProxy", e.getMessage()).show();
	}

	public void commit(final MyProcessConfig commitConfig,
			List<ModelData> lModified,
			final AsyncCallback<PagingLoadResult<ModelData>> callback) {
		System.out.println("MyHttpProxy.commit");

		final MyProcessConfig config = commitConfig;

		try {
			// Request
			Message msg = new Message();
			msg.getRequest().setProcess(config.getProcess());

			if (lModified.size() <= 0) {
				return;
			}

			// Entity changes
			EntityData entityData = new EntityData(config.getEntity());
			entityData.setProcessType(ProcessType.MAINTENANCE.getProcessType());

			// Filters
			// Entity.filtering
			List<FilterConfig> filters = config.getFilterConfigs();
			if (filters != null && filters.size() > 0) {
				String strFilters = "";
				int filtersCounter = 0;
				for (FilterConfig filter : filters) {
					String strFilter = filter.getField()
							+ ":"
							+ (filter.getComparison() == null ? "" : filter
									.getComparison()) + ":" + filter.getValue();
					if (filtersCounter > 0) {
						strFilters = strFilters + ";";
					}
					strFilters = strFilters + strFilter;
					filtersCounter++;
				}
				entityData.setFilter(strFilters);
			}

			msg.addData(entityData);

			int counter = 1;
			for (ModelData modelData : lModified) {
				Item item = new Item(counter++);
				for (String strField : config.getlFields()) {
					if (strField.startsWith("d:")) {
						continue;
					}
					Field field = new Field(strField);
					if (modelData.get(strField) != null
							&& modelData.get(strField).toString().trim()
									.length() > 0) {
						field.setValue(modelData.get(strField).toString());
					}
					item.addField(field);
				}
				if (modelData.get(Item.EXPIRE_ITEM) != null
						&& ConvertionManager.parseBoolean(modelData
								.get(Item.EXPIRE_ITEM))) {
					item.setExpireItem(true);
				}
				if (modelData.get(Item.NEW_ITEM) != null
						&& ConvertionManager.parseBoolean(modelData
								.get(Item.NEW_ITEM))) {
					item.setNewItem(true);
				}
				entityData.addItem(item);
			}

			msg.addData(entityData);

			System.out.println("Set message in json format...");
			String data = "";
			try {
				//data = "message=" + msg.toJSON();
				data = msg.toJSON();
			} catch (Exception e) {
				e.printStackTrace();
			}

			System.out.println("Send request...");
			builder.sendRequest(data, new RequestCallback() {
				public void onError(Request request, Throwable exception) {
					callback.onFailure(exception);
				}

				public void onResponseReceived(Request request,
						Response response) {
					try {
						evaluateResponse(response, config);
						PagingLoadResult<ModelData> data = null;
						callback.onSuccess(data);
					} catch (Exception e) {
						callback.onFailure(e);
					}
				}
			});
		} catch (Exception e) {
			callback.onFailure(e);
		}
	}

	public void commitForm(final MyProcessConfig config,
			Map<String, String> mfields,
			final AsyncCallback<Map<String, String>> callback) {
		System.out.println("MyHttpProxy.commitForm");

		try {
			// Request
			Message msg = new Message();
			msg.getRequest().setProcess(config.getProcess());

			// Data map
			Map<String, EntityData> mdata = new HashMap<String, EntityData>();

			// Fill datas
			for (String key : mfields.keySet()) {
				System.out.println(key);
				String value = mfields.get(key);
				System.out.println(value);

				String[] kp = key.split(":");
				String entityName = kp[0];
				String fieldName = kp[1];
				int register = Integer.parseInt(kp[2]);

				EntityData data = mdata.get(entityName);
				if (data == null) {
					data = new EntityData(entityName);
					data.setProcessType(ProcessType.MAINTENANCE
							.getProcessType());
					mdata.put(entityName, data);
				}
				Item item = data.getItem(register);
				if (item == null) {
					item = new Item(register);
					data.addItem(item);
				}
				Field field = new Field(fieldName, null);
				if (value != null && value.trim().length() > 0) {
					field.setValue(value);
				}
				item.addField(field);
			}

			for (String entity : mdata.keySet()) {
				msg.addData(mdata.get(entity));
			}

			// Send message
			System.out.println("Set message in json format...");
			String data = "";
			try {
				//data = "message=" + msg.toJSON();
				data = msg.toJSON();
			} catch (Exception e) {
				e.printStackTrace();
			}

			System.out.println("Send request...");
			builder.sendRequest(data, new RequestCallback() {
				public void onError(Request request, Throwable exception) {
					callback.onFailure(exception);
				}

				public void onResponseReceived(Request request,
						Response response) {
					try {
						Message rspMsg = evaluateResponse(response, config);
						Map<String, String> mrfields = MyMessageReader
								.toMap(rspMsg);
						;
						callback.onSuccess(mrfields);
					} catch (Exception e) {
						callback.onFailure(e);
					}
				}
			});
		} catch (Exception e) {
			callback.onFailure(e);
		}
	}

	private Message evaluateResponse(Response response, MyProcessConfig config)
			throws Exception {
		// Evaluate response
		if (response.getStatusCode() != Response.SC_OK) {
			if (response.getStatusCode() == 0) {
				throw new RuntimeException(
						"HttpProxy: ERROR DE COMUNICACION. CÓDIGO = "
								+ response.getStatusCode());
			}
			throw new RuntimeException(
					"HttpProxy: CÓDIGO DE ESTATUS NO VÁLIDO = "
							+ response.getStatusCode());
		}

		String text = response.getText();
		if (text.startsWith("No message received")) {
			throw new RuntimeException("HttpProxy: CORE: NO MESSAGE RECEIVED");
		}

		// Evaluate process result
		Message msg = reader.readMessage(config, text);

		if (msg.getResponse().getCode() != null
				&& msg.getResponse().getCode()
						.compareTo(ResponseData.RESPONSE_CODE_OK) != 0) {

			String errorCode = msg.getResponse().getCode();

			String errorMessage = msg.getResponse().getMessage();

			if (errorMessage == null) {
				throw new RuntimeException("ERROR DE PROCESAMIENTO: "
						+ errorCode);
			} else {
				throw new RuntimeException("ERROR DE PROCESAMIENTO: "
						+ errorCode + ".<br/>MENSAJE:<BR/>" + errorMessage);
			}
		}

		return msg;
	}

	protected String generateUrl(Object loadConfig) {
		StringBuffer sb = new StringBuffer();
		if (loadConfig instanceof ModelData) {
			Map<String, Object> map = ((ModelData) loadConfig).getProperties();
			for (String key : map.keySet()) {
				sb.append("&" + key + "=" + map.get(key));
			}
		}
		if (sb.length() > 0) {
			return sb.substring(1, sb.length());
		}
		return sb.toString();
	}

	private native void setUrl(RequestBuilder rb, String url) /*-{
																rb.@com.google.gwt.http.client.RequestBuilder::url = url;
																}-*/;

}
