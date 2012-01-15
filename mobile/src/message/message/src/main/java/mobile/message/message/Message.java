package mobile.message.message;

import java.util.ArrayList;
import java.util.List;

public class Message {

	private String messageType;

	public final static String XML = "XML";
	public final static String JSON = "JSON";

	private List<Data> dataList = new ArrayList<Data>();

	public Message() {
		setMessageType(null);
	}

	public Message(String meesageType) {
		setMessageType(meesageType);
	}

	public String getMessageType() {
		return messageType;
	}

	public void setMessageType(String type) {
		messageType = XML;
		if (type != null) {
			messageType = type;
		}
	}

	public String toString() {
		String message = "";
		if (getMessageType() != null) {
			if (getMessageType().compareTo(Message.XML) == 0) {
				message = this.toXML();
			} else if (getMessageType().compareTo(Message.JSON) == 0) {
				message = this.toJSON();
			}
		}
		return message;
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
		if (getData(data.getId()) == null) {
			dataList.add(data);
		}
	}

	public Data getData(String id) {
		for (Data data : dataList) {
			if ((data.getId().compareTo(id)) == 0) {
				return data;
			}
		}
		return null;
	}

	public void removeData(String id) {
		for (Data data : dataList) {
			if ((data.getId().compareTo(id)) == 0) {
				dataList.remove(data);
			}
		}
	}

	public List<Data> getDataList() {
		return dataList;
	}

	public RequestData getRequest() {
		RequestData requestData = null;
		Data rd = getData(RequestData.REQUEST); 
		
		if(rd==null){
			requestData = new RequestData();
			addData(requestData);
		}else{
			requestData = new RequestData(rd); 
		}
		
		return requestData;
	}

	public ResponseData getResponse() {
		ResponseData responseData = null;
		Data rd = getData(ResponseData.RESPONSE);
		if(rd==null){
			responseData = new ResponseData();
			addData(responseData);
		}else{
			responseData = new ResponseData(rd); 
		}
		
		return responseData;
	}

//	private Data getCreateData(String id) {
//		Data data = getData(id);
//
//		if(data == null){
//			data = new Data(id);
//			addData(data);
//		}
//		
//		return data;
//	}
}
