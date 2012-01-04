package mobile.web.webxt.client.data;

import mobile.message.message.Message;

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
