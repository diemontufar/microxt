package mobile.common.message;

import java.util.ArrayList;
import java.util.List;

public class Message {

	public final static String XML = "XML";
	public final static String JSON = "JSON";

	public final static String controlData = "control";

	private List<Data> dataList = new ArrayList<Data>();

	public Message() {
	}

	public String toString() {
		return this.toXML();
	}

	public String toXML() {
		String message = "<message>";
		for (Data data : getDataList()) {
			message += data.toXML();
		}
		message += "</message>";
		return message;
	}

	public String toJSON() {
		String message = "{\"message\": {";
		if (getDataList().size() > 0) {
			message += "\"data\": [";
		}
		int i = 1;
		int dsize = getDataList().size();
		for (Data data : getDataList()) {
			message += data.toJSON();
			if (dsize > i++) {
				message += ",";
			}
		}
		if (getDataList().size() > 0) {
			message += "]";
		}
		message += "}}";
		return message;
	}

	public void addData(Data data) {
		if (getData(data.getDataId()) == null) {
			dataList.add(data);
		}
	}

	public Data getData(String id) {
		for (Data data : dataList) {
			if ((data.getDataId().compareTo(id)) == 0) {
				return data;
			}
		}
		return null;
	}

	public void removeData(String id) {
		for (Data data : dataList) {
			if ((data.getDataId().compareTo(id)) == 0) {
				dataList.remove(data);
			}
		}
	}

	public List<Data> getDataList() {
		return dataList;
	}

	public EntityData getEntityData(String entity) {
		return toEntityData(getData(entity));
	}

	public List<EntityData> getEntityDataList() {
		List<Data> specialDatas = new ArrayList<Data>();
		List<Data> datas = getDataList();

		specialDatas.add(getRequest());
		specialDatas.add(getResponse());
		specialDatas.add(new Data(controlData));

		datas.removeAll(specialDatas);
		return toEntityData(datas);
	}

	private List<EntityData> toEntityData(List<Data> ldatas) {
		List<EntityData> lentities = new ArrayList<EntityData>();
		for (Data data : ldatas) {
			lentities.add(toEntityData(data));
		}

		return lentities;
	}

	private EntityData toEntityData(Data data) {
		EntityData entityData = new EntityData(data.getDataId());
		entityData.setFieldList(data.getFieldList());
		entityData.setItemList(data.getItemList());
		return entityData;
	}

	public RequestData getRequest() {
		RequestData requestData = null;
		Data rd = getData(RequestData.REQUEST);

		if (rd == null) {
			requestData = new RequestData();
			addData(requestData);
		} else {
			requestData = new RequestData(rd);
		}

		return requestData;
	}

	public ResponseData getResponse() {
		ResponseData responseData = null;
		Data rd = getData(ResponseData.RESPONSE);
		if (rd == null) {
			responseData = new ResponseData();
			addData(responseData);
		} else {
			responseData = new ResponseData(rd);
		}

		return responseData;
	}
	
	public void setControlFieldValue(String name, String value) {
		Data control = getData(controlData); 
		if ( control == null) {
			control = new Data(controlData);
			addData(control);
		}
		control.setFieldValue(name, value);
	}
	
	public String getControlFieldValue(String name) {
		Data control = getData(controlData); 
		if ( control == null) {
			return null;
		}
		return control.getFieldValue(name);
	}
	
}
