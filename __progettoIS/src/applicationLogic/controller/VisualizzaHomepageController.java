package applicationLogic.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import applicationLogic.bean.FilmLocal;
import applicationLogic.bean.Recensione;
import applicationLogic.model.RecensioneManager;
import applicationLogic.model.RicercaManager;

/**
 * Implementazione della gestione della Homepage lato server
 * @author Luca
 * @since 1.0
 */
@WebServlet("")
public class VisualizzaHomepageController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ArrayList<Recensione> listaRecensioni = new ArrayList<Recensione>();
		for(Recensione r: RecensioneManager.getAll()){
			listaRecensioni.add(RecensioneManager.getCompleta(r));
		}
		listaRecensioni.sort(Recensione.COMP_BY_DATA);
		for (int j = 2; j < listaRecensioni.size(); j++) {
		    listaRecensioni.remove(j);
		}
		Integer[] lista = new Integer[]{1359, 680, 808, 489, 429, 652, 4133, 629, 181808, 429189, 68718, 278, 11778, 313369, 389, 18785, 161, 1402, 393, 111, 103, 83542, 603, 293660, 4176, 1116, 55931, 274};
		ArrayList<Integer> listaFilmFavoriti = new ArrayList<Integer>(Arrays.asList(lista));
		int listaFilmScelti[] = new int[9];

		for(int i = 0; i < 9; i++){
			int indice = (int)(Math.floor(Math.random() * listaFilmFavoriti.size()));
			int mom = listaFilmFavoriti.get(indice);
			listaFilmScelti[i] = mom;
			listaFilmFavoriti.remove(indice);
		}
		
		ArrayList<FilmLocal> film = new ArrayList<FilmLocal>();
		for(Integer x: listaFilmScelti){
			film.add(RicercaManager.ricercaLocale(new FilmLocal(x)));
		}
		
		request.setAttribute("listaRec", listaRecensioni);
		request.setAttribute("filmLoc", film);
		request.getRequestDispatcher("home_view.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
