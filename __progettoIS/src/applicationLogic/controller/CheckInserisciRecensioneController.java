package applicationLogic.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import applicationLogic.bean.Film;
import applicationLogic.bean.FilmLocal;
import applicationLogic.bean.Recensione;
import applicationLogic.bean.Utente;

@WebServlet("/inseriscirecensione")
public class CheckInserisciRecensioneController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// posso fare questo parse senza preoccuparmi di nulla perchè viene
		// passato da una <select>
		int voto = Integer.parseInt(request.getParameter("voto"));
		String titolo = request.getParameter("titolo");
		String recensione = request.getParameter("recensione");
		Utente u = (Utente) request.getSession().getAttribute("utente");
		Film f = FilmLocal.generateByStringId(request.getParameter("idFilm"));

		Recensione r = new Recensione(u, f, voto, titolo, recensione);
		/* TODO: continuare questa classe/servlet */

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
