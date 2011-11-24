package mobile.web.console.client;

import com.google.gwt.http.client.Request;
import com.google.gwt.http.client.RequestBuilder;
import com.google.gwt.http.client.RequestCallback;
import com.google.gwt.http.client.RequestException;
import com.google.gwt.http.client.URL;

public class CoreClient {

	@SuppressWarnings("unused")
	public static void send(String message, RequestCallback callback) {
		try {
			String url = "http://127.0.0.1:9090/mobile/Core?message=" + message;
			RequestBuilder builder = new RequestBuilder(RequestBuilder.GET, URL.encode(url));
			Request request = builder.sendRequest(null, callback);
		} catch (RequestException e) {
			e.printStackTrace();
		}
	}
}
