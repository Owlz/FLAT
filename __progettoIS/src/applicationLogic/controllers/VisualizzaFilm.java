package applicationLogic.controllers;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import applicationLogic.managers.RicercaManager;
import applicationLogic.managers.WatchlistManager;
import applicationLogic.models.Film;
import applicationLogic.models.Utente;

@WebServlet("/film")
public class VisualizzaFilm extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String idStringa = request.getParameter("id");
		Utente u = (Utente) request.getSession().getAttribute("utente");
		
		Film film = RicercaManager.ricercaFilm(idStringa);
		String watchlist = WatchlistManager.check(idStringa, u) ? "true" : "false";
		
		request.setAttribute("film", film);
		request.setAttribute("inWatchlist", watchlist);
		request.getRequestDispatcher("film_view.jsp").forward(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
