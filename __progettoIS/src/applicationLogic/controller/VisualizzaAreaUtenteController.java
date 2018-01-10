package applicationLogic.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import applicationLogic.bean.Utente;
import applicationLogic.model.GestioneAccountManager;

@WebServlet("/utente")
public class VisualizzaAreaUtenteController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Utente u = new Utente();
		u.setUsername(request.getParameter("id"));
		
		Utente utenteInSessione = (Utente) request.getSession().getAttribute("utente");
		
		if(utenteInSessione != null && utenteInSessione.equals(u)) u = utenteInSessione;
		else u = GestioneAccountManager.getUtente(u);
		
		if(u == null)	// caso in cui l'url portava ad un utente che non è nel database
			response.sendRedirect(request.getContextPath() + "/");
		else{			// caso standard
			request.setAttribute("datiUtente", u);
			request.getRequestDispatcher("utente_view.jsp").forward(request, response);
		}
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException { 
		doGet(request, response);
	}

}
