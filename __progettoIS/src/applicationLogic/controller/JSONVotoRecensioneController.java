package applicationLogic.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import applicationLogic.bean.Recensione;
import applicationLogic.bean.Utente;
import applicationLogic.bean.Voto;
import applicationLogic.model.RecensioneManager;

@WebServlet("/vota")
public class JSONVotoRecensioneController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Utente u = (Utente) request.getSession().getAttribute("utente");
		
		String idRecensione = request.getParameter("id");
		String votoString = request.getParameter("v");
		int votoInt = Integer.parseInt(votoString);
		
		Recensione r = Recensione.generateByStringId(idRecensione);
		Voto v = new Voto(-1, votoInt, u);
		
		response.setContentType("application/json");
		if(RecensioneManager.vota(r, v)){
			response.getWriter().write("succ");
		}else{
			response.getWriter().write("fall");
		}

	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
