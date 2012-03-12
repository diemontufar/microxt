package mobile.tools.common.structure;

import org.apache.log4j.Logger;

import mobile.common.message.Message;
import mobile.tools.common.Log;

public abstract interface GeneralProcessor{

	final Logger log = Log.getInstance();
	
	public Message process(Message msg) throws Exception;
	
}
