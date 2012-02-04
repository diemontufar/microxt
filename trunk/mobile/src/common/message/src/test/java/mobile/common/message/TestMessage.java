package mobile.common.message;

import org.junit.Test;

public class TestMessage {

	@Test
	public void test() {
		Message msg = new Message();
		msg.setControlFieldValue("numeroSecuencia", "123123123");
		System.out.println(msg.getControlFieldValue("numero"));
		System.out.println(msg.getControlFieldValue("numeroSecuencia"));
		System.out.println(msg.toXML());
	}

}

