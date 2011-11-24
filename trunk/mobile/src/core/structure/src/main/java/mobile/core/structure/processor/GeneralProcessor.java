package mobile.core.structure.processor;

import mobile.core.message.Message;

public interface GeneralProcessor{

	public Message process(Message msg) throws Exception;
	
}
