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
import applicationLogic.exception.DatiTroppoBrevi;
import applicationLogic.exception.VotoMancante;
import applicationLogic.model.RecensioneManager;

/**
 * Servlet che aggiunge la recensione nel sistema in maniera asincrona
 * 
 * @author Luca
 *
 */
@WebServlet("/addrecensione")
public class JSONInserisciRecensione extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// posso fare questo parse senza preoccuparmi di nulla perchè viene
		// passato da una <select>
		int voto = Integer.parseInt(request.getParameter("voto"));
		String titolo = request.getParameter("titolo").trim();
		String testo = request.getParameter("recensione").trim();
		Utente u = (Utente) request.getSession().getAttribute("utente");
		Film f = FilmLocal.generateByStringId(request.getParameter("idFilm"));

		Recensione r = new Recensione();
		r.setFilm(f);
		r.setUtente(u);
		r.setVoto(voto);
		r.setTitolo(titolo);
		r.setTesto(testo);

		response.setContentType("application/json");

		try {
			RecensioneManager.addRecensione(r);
			response.getWriter().write("succ");

		} catch (DatiTroppoBrevi e) {
			response.getWriter().write(e.getMessage());

		} catch (VotoMancante e) {
			response.getWriter().write(e.getMessage());
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
