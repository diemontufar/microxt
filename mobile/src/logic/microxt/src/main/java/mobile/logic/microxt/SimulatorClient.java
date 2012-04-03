package mobile.logic.microxt;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import mobile.common.message.Message;
import mobile.tools.common.Config;
import mobile.tools.common.Log;
import mobile.tools.common.Objection;
import mobile.tools.common.enums.ObjectionCode;
import mobile.tools.common.msg.Parser;

import org.apache.log4j.Logger;

public class SimulatorClient {

	private final static String url = Config.get("simulator-url");
	private final static String charset = "UTF-8";
	private final static Logger log = Log.getInstance();
	
	public static Message sendMessage(Message msg) throws Exception {
		log.info("Send message to Simulator: ");

		HttpURLConnection httpConnection = null;
		Message rspMsg = null;
		try {
			log.info("Trying conection with: " + url);
			httpConnection = (HttpURLConnection) new URL(url).openConnection();
			httpConnection.setRequestMethod("POST");

			httpConnection.setDoOutput(true);
			httpConnection.setDoInput(true);
			httpConnection.setUseCaches(false);
			httpConnection.setAllowUserInteraction(false);
			httpConnection.setRequestProperty("Content-type", "text/xml; charset=" + charset);
			httpConnection.setRequestProperty("Accept-Charset", charset);

			OutputStream output = null;
			try {
				output = httpConnection.getOutputStream();
				String message = msg.toXML();
				output.write(message.getBytes(charset));
			} finally {
				if (output != null)
					try {
						output.close();
					} catch (IOException e) {
						log.error(e);
					}
			}

			InputStream responseIn = httpConnection.getInputStream();
			BufferedReader br = new BufferedReader(new InputStreamReader(responseIn));
			String rspMsgStr = br.readLine();
			log.info("Response from Simulator: ");
			log.info(rspMsgStr);
			if(rspMsgStr != null){
				Parser parser = new Parser();
				rspMsg = parser.parseMsg(rspMsgStr, Message.XML);
			}
		} catch (IOException e) {
			throw new Objection(ObjectionCode.SIMULATOR_CONNECTION_ERROR);
		} finally {
			if (httpConnection != null)
				httpConnection.disconnect();
		}

		return rspMsg;
	}
}