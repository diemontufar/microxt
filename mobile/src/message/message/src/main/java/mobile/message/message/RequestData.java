package mobile.message.message;

public class RequestData extends Data {

	public final static String REQUEST = "request";

	public final static String REQUEST_PROCESS = "process";
	public final static String REQUEST_USER = "user";
	public final static String REQUEST_HOST = "host";
	public final static String REQUEST_COMPANY = "company";
	public final static String REQUEST_LANGUAGE = "language";
	public final static String REQUEST_SESSION = "session";
	public final static String REQUEST_CHANNEL = "channel";
	public final static String REQUEST_PROFILE = "profile";
	public final static String REQUEST_EDITABLE = "editable";

	public RequestData() {
		super(REQUEST);
	}

	public RequestData(Data data) {
		super();
		this.setFieldList(data.getFieldList());
	}

	public String getProcess() {
		return (String) getFieldValue(REQUEST_PROCESS);
	}

	public void setProcess(String processId) {
		setFieldValue(REQUEST_PROCESS, processId);
	}

}
