package applicationLogic.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONObject;

import applicationLogic.bean.Film;
import applicationLogic.model.RicercaManager;

/**
 * Servlet che permette di ricercare un film nell'api online in maniera
 * asincrona
 * 
 * @author Luca
 *
 */
@WebServlet("/ricerca")
public class JSONRicerca extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String query = request.getParameter("query");

		ArrayList<Film> films = RicercaManager.ricercaFilms(query);

		JSONArray array = new JSONArray(films);
		JSONObject obj = new JSONObject().put("results", array);

		response.setContentType("application/json");
		response.getWriter().write(obj.toString());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
