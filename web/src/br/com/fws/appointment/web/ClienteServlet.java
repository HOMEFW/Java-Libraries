package br.com.fws.appointment.web;

import java.io.IOException;
import java.io.Writer;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.fws.appointment.client.Client;
import br.com.fws.appointment.client.ClientList;
import br.com.fws.appointment.client.Exception_Exception;

/**
 * Servlet implementation class clientServlet
 */
@WebServlet("/clientServlet")
public class ClienteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ClienteServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		// response.getWriter().append("Served at:
		// ").append(request.getContextPath());

		Writer resp = response.getWriter();

		// resp.write("teste");

		response.addHeader("application/type", "HTML");
		resp.write("");

		Cliente client = new Cliente();
		try {
			ClientList lista = client.getClients();
			for (Client item : lista.getClient()) {
				resp.write(item.getClientName());
				resp.write("\n");
			}

		} catch (Exception_Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
