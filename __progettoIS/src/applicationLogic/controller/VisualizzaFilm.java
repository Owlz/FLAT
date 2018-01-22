package applicationLogic.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import applicationLogic.bean.Film;
import applicationLogic.bean.FilmLocal;
import applicationLogic.bean.Recensione;
import applicationLogic.bean.Utente;
import applicationLogic.model.RecensioneManager;
import applicationLogic.model.RicercaManager;
import applicationLogic.model.WatchlistManager;

@WebServlet("/film")
public class VisualizzaFilm extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Utente u = (Utente) request.getSession().getAttribute("utente");

		String idStringa = request.getParameter("id");
		Film f = RicercaManager.ricercaFilm(FilmLocal.generateByStringId(idStringa));
		
		// ottengo una lista di recensioni del film attuale
		ArrayList<Recensione> listaRec = RecensioneManager.get(f);
		
		String watchlist = "false";
		if(u != null){	
			// controllo se il film è nella watchlist dell'utente attuale
			watchlist = WatchlistManager.checkFilmInWatchlist(f, u) ? "true" : "false";
			
			//controllo se l'utente ha recensito il film attuale
			Recensione recUtente = RecensioneManager.get(u, f);
			if(listaRec.contains(recUtente)) request.setAttribute("recUtente", recUtente);
			else request.setAttribute("recUtente", null);
		}
		
		listaRec.sort(Recensione.COMP_BY_VOTI_POSITIVI);
		
		request.setAttribute("inWatchlist", watchlist);
		request.setAttribute("listaRecensioni", listaRec);	// controllare se "size > 0"
		request.setAttribute("film", f);
		
		request.getRequestDispatcher("film_view.jsp").forward(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
