package applicationLogic.controllers;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import applicationLogic.managers.AreaUtenteManager;
import applicationLogic.models.Utente;

@WebServlet("/utente")
public class VisualizzaAreaUtenteController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Utente u = new Utente();
		u.setUsername(request.getParameter("id"));
		
		Utente utenteInSessione = (Utente) request.getSession().getAttribute("utente");
		
		if(utenteInSessione != null && u.getUsername().equals(utenteInSessione.getUsername())) u = utenteInSessione;
		else u = AreaUtenteManager.getUtente(u);
		
		if(u == null)
			response.sendRedirect(request.getContextPath() + "/");
		else{
			request.setAttribute("datiUtente", u);
			request.getRequestDispatcher("utente_view.jsp").forward(request, response);
		}
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException { 
		doGet(request, response);
	}

}
