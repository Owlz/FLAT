package applicationLogic.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Implementazione della gestione della visualizzazione della
 * pagina di Registrazione lato server
 * @author Luca
 * @since 1.0
 */
@WebServlet("/registrazione")
public class VisualizzaRegistrazioneController extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("registrazione_view.jsp").forward(request, response);
	}

protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
