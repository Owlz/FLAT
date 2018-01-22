package applicationLogic.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import applicationLogic.bean.Recensione;
import applicationLogic.model.RecensioneManager;

@WebServlet("/segnala")
public class JSONSegnalaRecensione extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String idRecensione = request.getParameter("id");
		Recensione r = Recensione.generateByStringId(idRecensione);
		r.setSegnalata(true);

		response.setContentType("application/json");
		if (RecensioneManager.segnala(r)) {
			response.getWriter().write("succ");
		} else {
			response.getWriter().write("errore");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
