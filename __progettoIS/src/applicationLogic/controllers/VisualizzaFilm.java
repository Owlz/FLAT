package applicationLogic.controllers;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import applicationLogic.managers.RicercaManager;
import applicationLogic.models.Film;

@WebServlet("/film")
public class VisualizzaFilm extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String idStringa = request.getParameter("id");
		
		Film film = RicercaManager.ricercaFilm(idStringa);
		
		request.setAttribute("film", film);
		request.getRequestDispatcher("film_view.jsp").forward(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
