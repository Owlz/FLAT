package applicationLogic.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import applicationLogic.bean.Recensione;
import applicationLogic.bean.Utente;
import applicationLogic.model.RecensioneManager;

@WebServlet("/removerecensione")
public class JSONRimuoviRecensione extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Utente utente = (Utente) request.getSession().getAttribute("utente");
		
		String idRecensione = request.getParameter("id");
		Recensione r = Recensione.generateByStringId(idRecensione);
		r = RecensioneManager.get(r);
		
		response.setContentType("application/json");
		if(utente != null && utente.getUsername().equals(r.getUtente().getUsername()) && RecensioneManager.rimuovi(r))
			response.getWriter().write("succ");
		else
			response.getWriter().write("fall");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
