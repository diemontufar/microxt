package mobile.web.webxt.server;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



public class XTServlet extends HttpServlet {

	private boolean resultado = false;

	private static final long serialVersionUID = 1L;

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String jsonRecieved = request.getParameter("message");
		String jsonRespuesta = "";

		System.out.println("Json a parsear en server: " + jsonRecieved);

//		//Message msj = null;
//		try {
//			msj = new Message(new Parser(jsonRecieved, Parser.JSON));
//		} catch (Exception e1) {
//			e1.printStackTrace();
//		}
//
//		Data data = new Data();
//		data = msj.getData("login");
//		String usuario = data.getField("user").getValue();
//		String password = data.getField("pwd").getValue();
//
//		// Solo hecho para verificacion del login:
//
//		if (usuario.compareTo("admin") == 0 && password.compareTo("admin") == 0) {
//			resultado = true;
//		} else {
//			resultado = false;
//		}
//
//		data.addField(new Field("msj", String.valueOf(resultado)));
//
//		jsonRespuesta = generateResponseJson(msj);
//
//		PrintWriter writer = response.getWriter();
//		writer.write(jsonRespuesta);
//		writer.flush();
	}

	// Genera respuesta para enviarla al cliente:
//	public String generateResponseJson(Message mensaje) {
//		return mensaje.toJSON().toString();
//
//	}

}
