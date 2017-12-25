package applicationLogic.controllers;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import applicationLogic.exception.DatiNonPresenti;
import applicationLogic.exception.DatiNonValidi;
import applicationLogic.managers.AutenticazioneManager;
import applicationLogic.models.Utente;

/**
 * Implementazione del controllo dati del login lato server
 * @author Luca
 * @since 1.0
 */
@WebServlet("/checklogin")
public class CheckLoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Utente u = new Utente();
		u.setUsername(request.getParameter("username"));
		u.setPassword(request.getParameter("password"));
		
		try {
			u = AutenticazioneManager.autenticaUtente(u);
			request.getSession().setAttribute("utente", u);
			response.sendRedirect(request.getContextPath() + "/");
			
		} catch (DatiNonPresenti e) {
			request.getSession().setAttribute("errore", "login fallito, dati non presenti (username errato)");
			response.sendRedirect(request.getContextPath() + "/login");
			
		} catch (DatiNonValidi e) {
			request.getSession().setAttribute("errore", "login fallito, dati non validi (username azzeccato ma password errata)");
			response.sendRedirect(request.getContextPath() + "/login");
		}
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
