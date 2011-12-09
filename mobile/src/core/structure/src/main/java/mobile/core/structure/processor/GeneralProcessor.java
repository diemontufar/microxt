package mobile.core.structure.processor;

import mobile.message.cmessage.Message;

public interface GeneralProcessor{

	public Message process(Message msg) throws Exception;
	
}
