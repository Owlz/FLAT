package applicationLogic.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import applicationLogic.bean.Recensione;
import applicationLogic.bean.Utente;
import applicationLogic.model.GestioneAccountManager;
import applicationLogic.model.RecensioneManager;

/**
 * Servlet per visualizzare l'area utente
 * 
 * @author Luca
 *
 */
@WebServlet("/utente")
public class VisualizzaAreaUtente extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Utente u = new Utente();
		u.setUsername(request.getParameter("id"));
		u = GestioneAccountManager.getUtente(u);

		Utente utenteInSessione = (Utente) request.getSession().getAttribute("utente");

		if (utenteInSessione != null && !utenteInSessione.getRuolo().equals("visitatore") && utenteInSessione.equals(u))
			u = utenteInSessione;

		if (u == null || !u.equals(utenteInSessione)) // caso in cui l'url
														// portava ad un utente
														// che non è nel
														// database
			response.sendRedirect(request.getContextPath() + "/");

		else { // caso standard
			ArrayList<Recensione> listaRecUtente = RecensioneManager.getCompleta(u);

			request.setAttribute("recensioniUtente", listaRecUtente);
			request.setAttribute("datiUtente", u);
			request.getRequestDispatcher("utente_view.jsp").forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
