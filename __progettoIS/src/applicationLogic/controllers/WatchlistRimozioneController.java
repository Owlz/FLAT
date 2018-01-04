package applicationLogic.controllers;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import applicationLogic.managers.WatchlistManager;
import applicationLogic.models.Utente;

@WebServlet("/removewatchlist")
public class WatchlistRimozioneController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String idFilm = request.getParameter("id");
		Utente utente = (Utente) request.getSession().getAttribute("utente");
		
		response.setContentType("text/json");
		if(WatchlistManager.removeWatchlist(idFilm, utente))
			response.getWriter().write("succ");
		else
			response.getWriter().write("fall");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
