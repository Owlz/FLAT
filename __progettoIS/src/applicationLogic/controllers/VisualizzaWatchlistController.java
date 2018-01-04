package applicationLogic.controllers;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import applicationLogic.managers.WatchlistManager;
import applicationLogic.models.Film;
import applicationLogic.models.Utente;

@WebServlet("/watchlist")
public class VisualizzaWatchlistController extends HttpServlet {
	private static final long serialVersionUID = 1L;
 
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Utente u = (Utente) request.getSession().getAttribute("utente");
		ArrayList<Film> watchlist = WatchlistManager.getWatchlist(u);
		
		request.setAttribute("watchlist", watchlist);
		request.getRequestDispatcher("watchlist_view.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
