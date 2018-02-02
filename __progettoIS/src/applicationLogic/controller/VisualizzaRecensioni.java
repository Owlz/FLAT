package applicationLogic.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import applicationLogic.bean.Recensione;
import applicationLogic.model.AmministrazioneManager;
import applicationLogic.model.RecensioneManager;

/**
 * Servlet per visualizzare tutte le recensioni nel sistema
 * 
 * @author Luca
 *
 */
@WebServlet("/mostrarecensioni")
public class VisualizzaRecensioni extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ArrayList<Recensione> listaRecensioni = new ArrayList<Recensione>();
		for (Recensione r : AmministrazioneManager.getAllRecensioni()) {
			listaRecensioni.add(RecensioneManager.getCompleta(r));
		}
		String ord = request.getParameter("ord");

		if (ord != null && ord.equals("segn")) {
			listaRecensioni.sort(Recensione.COMP_BY_SEGNALATA);
		} else if (ord != null && ord.equals("voti")) {
			listaRecensioni.sort(Recensione.COMP_BY_VOTI_NEGATIVI);
		} else {
			listaRecensioni.sort(Recensione.COMP_BY_DATA);
		}

		request.setAttribute("listaRec", listaRecensioni);
		request.getRequestDispatcher("gestioneRecensioni_view.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
