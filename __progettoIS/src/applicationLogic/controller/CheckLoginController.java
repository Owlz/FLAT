package applicationLogic.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import applicationLogic.bean.Utente;
import applicationLogic.exception.DatiNonPresenti;
import applicationLogic.exception.DatiNonValidi;
import applicationLogic.model.AutenticazioneManager;

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
		u.setUsername(request.getParameter("username").trim());
		u.setPassword(request.getParameter("password").trim());
		
		try {
			u = AutenticazioneManager.autenticaUtente(u);
			
			request.getSession().setAttribute("utente", u);
			response.sendRedirect(request.getContextPath() + "/utente?id=" + u.getUsername());
			
		} catch (DatiNonPresenti e) {
			request.setAttribute("errore", "login fallito, dati non presenti (username errato)");
			request.getRequestDispatcher("login").forward(request, response);
			
		} catch (DatiNonValidi e) {
			request.setAttribute("errore", "login fallito, dati non validi (username azzeccato ma password errata)");
			request.getRequestDispatcher("login").forward(request, response);
		}
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
