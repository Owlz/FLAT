package applicationLogic.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import applicationLogic.bean.Film;
import applicationLogic.bean.FilmLocal;
import applicationLogic.bean.Utente;
import applicationLogic.model.WatchlistManager;

@WebServlet("/addwatchlist")
public class JSONInserisciWatchlistController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Utente utente = (Utente) request.getSession().getAttribute("utente");

		String idFilm = request.getParameter("id");
		Film f = FilmLocal.generateByStringId(idFilm);
		
		response.setContentType("application/json");
		if(WatchlistManager.addWatchlist(f, utente))
			response.getWriter().write("succ");
		else
			response.getWriter().write("fall");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
