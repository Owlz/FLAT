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

/**
 * Servlet per visualizzare le segnalazioni presenti nel sistema
 * 
 * @author Luca
 *
 */
@WebServlet("/mostrasegnalate")
public class VisualizzaSegnalazioni extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ArrayList<Recensione> listaSegnalate = AmministrazioneManager.getSegnalate();

		request.setAttribute("listaSegnalate", listaSegnalate);
		request.getRequestDispatcher("gestioneSegnalazioni_view.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
