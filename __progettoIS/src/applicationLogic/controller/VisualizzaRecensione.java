package applicationLogic.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import applicationLogic.bean.Recensione;
import applicationLogic.model.RecensioneManager;

/**
 * Servlet per visualizzare una specifica recensione
 * 
 * @author Luca
 *
 */
@WebServlet("/recensione")
public class VisualizzaRecensione extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String idRecensione = request.getParameter("id");
		Recensione r = Recensione.generateByStringId(idRecensione);
		r = RecensioneManager.getCompleta(r);

		request.setAttribute("recensione", r);
		request.getRequestDispatcher("recensione_view.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
