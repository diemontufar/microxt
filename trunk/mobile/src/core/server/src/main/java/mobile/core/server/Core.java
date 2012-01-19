package mobile.core.server;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mobile.common.message.Message;
import mobile.core.processor.CoreProcessor;
import mobile.tools.common.Log;
import mobile.tools.common.msg.Parser;

import org.apache.log4j.Logger;

public class Core extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String CONTENT_TYPE = "text/plain; charset=UTF-8";
	private Logger log = Log.getInstance();

	public void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String message = "";
		try {
			log.info(request.getClass());
			log.info(request);
			message = request.getParameter("message");
			log.info("Input message: " + message);

			if (message == null) {
				response.setContentType(CONTENT_TYPE);
				response.setHeader("Access-Control-Allow-Origin",
						"http://127.0.0.1:8888");
				PrintWriter out = response.getWriter();

				// Test message
				out.println("No message received.");
				out.close();
				return;
			}

			// Get data from json
			Parser parser = new Parser();
			Message msg = parser.parseMsg(message, Message.JSON);

			// Process
			CoreProcessor proc = new CoreProcessor();
			proc.process(msg);

			// Return the changes in JSON format
			response.setContentType(CONTENT_TYPE);
			response.setHeader("Access-Control-Allow-Origin",
					"http://127.0.0.1:8888");
			PrintWriter out = response.getWriter();

			out.println(msg.toJSON());
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
