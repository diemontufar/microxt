package mobile.web.webxt_mvc.client.data;

import mobile.web.message.Message;

public class MyMessageWrapper {
	private Message msg;

	public MyMessageWrapper() {

	}

	public MyMessageWrapper(Message msg) {
		this.msg = msg;
	}

	public Message getMsg() {
		return msg;
	}

	public void setMsg(Message msg) {
		this.msg = msg;
	}

}
