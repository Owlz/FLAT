package applicationLogic.controllers;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import applicationLogic.exception.DatiOccupati;
import applicationLogic.managers.AreaUtenteManager;
import applicationLogic.models.Utente;

@WebServlet("/controllocambiodati")
public class CheckCambioDatiController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Utente uNew = new Utente();
		Utente uAtt = (Utente) request.getSession().getAttribute("utente");
		
		uNew.setPassword(request.getParameter("password"));
		uNew.setNome(request.getParameter("nome"));
		uNew.setCognome(request.getParameter("cognome"));
		uNew.setEmail(request.getParameter("email"));
		
		try {
			uNew = AreaUtenteManager.aggiornaUtente(uNew, uAtt);
			request.getSession().setAttribute("utente", uNew);
			response.sendRedirect(request.getContextPath());
			/* TODO questo deve ridirezionare verso l'area utente */

		} catch (DatiOccupati e) {
			request.getSession().setAttribute("errore", "cambio dati fallito (dati occupati) -> " + e.getCampo());
			response.sendRedirect(request.getContextPath() + "/cambiodati");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
