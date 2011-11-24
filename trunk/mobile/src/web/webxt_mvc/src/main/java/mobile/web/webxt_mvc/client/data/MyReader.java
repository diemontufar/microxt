package mobile.web.webxt_mvc.client.data;

import java.util.ArrayList;
import java.util.List;

import mobile.web.message.Data;
import mobile.web.message.Field;
import mobile.web.message.Item;
import mobile.web.message.Message;
import mobile.web.message.Parser;
import mobile.web.webxt_mvc.client.windows.AlertDialog;

import com.extjs.gxt.ui.client.data.BaseModelData;
import com.extjs.gxt.ui.client.data.BasePagingLoadResult;
import com.extjs.gxt.ui.client.data.DataReader;
import com.extjs.gxt.ui.client.data.ModelData;
import com.extjs.gxt.ui.client.data.PagingLoadResult;

public class MyReader<D> implements DataReader<D> {

	private final String MSG_TYPE = Parser.JSON;
	
	private Parser parser;
	
	private final String OFFSET = "_pag_offset";
	
	private final String TOTAL_LENGTH = "_pag_total_length";
	
	public MyReader() {
	}

	@SuppressWarnings("unchecked")
	public D read(Object loadConfig, Object data) {
		System.out.println("MyReader.read: " + data.toString());
		ArrayList<ModelData> models = new ArrayList<ModelData>();
		PagingLoadResult<ModelData> paginatedModels = new BasePagingLoadResult<ModelData>(models);
		
		// Config
		MyProcessConfig config = (MyProcessConfig) loadConfig;
		System.out.println(config.toString());
		
		try {
			String strData = data.toString();
			parser = new Parser(strData, MSG_TYPE);
			Message msg = new Message(parser);
			System.out.println("entity: " + config.getEntity());
			Data entityData = msg.getData(config.getEntity());

			System.out.println("reading items");
			for (Item item : entityData.getItemList()) {
				ModelData model = new BaseModelData();
				for (String strField : config.getlFields()) {
					Field field = item.getField(strField);
					// model.set(field.getName().substring(field.getName().lastIndexOf(".")+1),
					// field.getValue().replaceAll("\"", ""));
					
					if (field.getName().compareTo("_expire") == 0) {
						if (field.getValue().compareTo("") == 0
								|| field.getValue().compareTo("0") == 0) {
							model.set(field.getName(), false);
						} else {
							model.set(field.getName(), true);
						}
					} else {
						model.set(field.getName(), field.getValue());
					}
					
				}
				models.add(model);
			}

			// Pagination
			System.out.println("reading pagination");
			if (entityData.getField(OFFSET) != null) {
				paginatedModels.setOffset(Integer.valueOf(entityData.getField(
						OFFSET).getValue()));
			}
			if (entityData.getField(TOTAL_LENGTH) != null) {
				paginatedModels.setTotalLength(Integer.valueOf(entityData
						.getField(TOTAL_LENGTH).getValue()));
			}

		} catch (Exception e) {
			new AlertDialog("MyReader", e.getMessage()).show();
		}
		
		return (D) paginatedModels;
	}

	public Message readMessage(Object loadConfig, Object data){
		System.out.println("MyReader.readMessage" + data.toString());
		
		Message msg = null;
		
		if(!data.toString().startsWith("No message received")){
			try {
				String strData = data.toString();
				System.out.println("Parsing");
				
				parser = new Parser(strData, MSG_TYPE);
				msg = new Message(parser);
				System.out.println("End parsing");
			} catch (Exception e) {
				new AlertDialog("MyReader", e.getMessage()).show();
			}
		}
		
		return msg;
	}
	
	
	@SuppressWarnings("unchecked")
	protected Object createReturnData(Object loadConfig,
			List<ModelData> records, int totalCount) {
		return (D) records;
	}
}