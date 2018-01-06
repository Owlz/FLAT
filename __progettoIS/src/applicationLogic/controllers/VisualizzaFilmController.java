package applicationLogic.controllers;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import applicationLogic.managers.RecensioneManager;
import applicationLogic.managers.RicercaManager;
import applicationLogic.managers.WatchlistManager;
import applicationLogic.models.Film;
import applicationLogic.models.Recensione;
import applicationLogic.models.Utente;

@WebServlet("/film")
public class VisualizzaFilmController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String idStringa = request.getParameter("id");
		Utente u = (Utente) request.getSession().getAttribute("utente");
		
		Film f = RicercaManager.ricercaFilm(idStringa);
		String watchlist = WatchlistManager.check(idStringa, u) ? "true" : "false";
		
		ArrayList<Recensione> listaRec = RecensioneManager.getRecensioniByFilm(f);
		
		if(u != null){
			Recensione recUtente = RecensioneManager.getRecensione(u, f);
			if(listaRec.contains(recUtente)) request.setAttribute("recUtente", recUtente);
		}
		
		request.setAttribute("film", f);
		request.setAttribute("inWatchlist", watchlist);
		request.setAttribute("listaRecensioni", listaRec);	// controllare se "size > 0"
		
		request.getRequestDispatcher("film_view.jsp").forward(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
