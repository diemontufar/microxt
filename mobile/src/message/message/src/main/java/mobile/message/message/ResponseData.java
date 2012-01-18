package mobile.message.message;

public class ResponseData extends Data{

	public final static String RESPONSE = "response";
	
	public final static String RESPONSE_CODE = "code";
	public final static String RESPONSE_MESSAGE = "message";
	public final static String RESPONSE_ERROR = "error";
	public final static String RESPONSE_NOTES = "notes";
	
	public final static String RESPONSE_CODE_OK = "000";
	public final static String RESPONSE_CODE_ERROR = "001";

	public ResponseData() {
		super(RESPONSE);
	}
	
	public ResponseData(Data data) {
		super();
		this.setFieldList(data.getFieldList());
	}

	public String getCode(){
		return (String) getFieldValue(RESPONSE_CODE);
	}

	public void setCode(String code){
		setFieldValue(RESPONSE_CODE, code);
	}
	
	public String getMessage(){
		return (String) getFieldValue(RESPONSE_MESSAGE);
	}

	public void setMessage(String message){
		setFieldValue(RESPONSE_MESSAGE, message);
	}
}
