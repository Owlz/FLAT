package applicationLogic.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import applicationLogic.bean.Utente;
import applicationLogic.exception.DatiNonValidi;
import applicationLogic.exception.DatiOccupati;
import applicationLogic.model.RegistrazioneManager;

/**
 * Servlet che controlla la registrazione lato server
 * 
 * @author Luca
 * @since 1.0
 */
@WebServlet("/checkregistrazione")
public class CheckRegistrazione extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Utente u = new Utente();
		u.setUsername(request.getParameter("username").trim());

		if (request.getParameter("password").trim().equals(request.getParameter("confermaPassword").trim()))
			u.setPassword(request.getParameter("password").trim());
		else
			u.setPassword("");

		u.setNome(request.getParameter("nome").trim());
		u.setCognome(request.getParameter("cognome").trim());
		u.setEmail(request.getParameter("email").trim());
		u.setRuolo("utente");

		try {
			u = RegistrazioneManager.aggiungiUtente(u);

			request.getSession().setAttribute("utente", u);
			response.sendRedirect(request.getContextPath() + "/utente?id=" + u.getUsername());

		} catch (DatiNonValidi e) {
			request.setAttribute("errore", "registrazione fallita (dati non validi e/o campi vuoti)");
			request.getRequestDispatcher("registrazione_view.jsp").forward(request, response);

		} catch (DatiOccupati e) {
			request.setAttribute("errore", "registrazione fallita (dati occupati)");
			request.getRequestDispatcher("registrazione_view.jsp").forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
