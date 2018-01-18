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
		
		String flag = request.getParameter("flag");
		int voto = Integer.parseInt(request.getParameter("voto"));
		
		response.setContentType("application/json");

		if(flag.equals("rimuovi")){
			int id = Integer.parseInt(request.getParameter("idVoto"));
			Voto v = new Voto(id, voto, u);
			if(RecensioneManager.rimuoviVoto(v)) response.getWriter().write("succ");
			else response.getWriter().write("fall");
		
		}else if(flag.equals("modifica")){
			int id = Integer.parseInt(request.getParameter("idVoto"));
			Voto v = new Voto(id, voto, u);
			
			if(RecensioneManager.cambiaVoto(v)) response.getWriter().write("succ");
			else response.getWriter().write("fall");

		}else if(flag.equals("aggiungi")){
			String idRecensione = request.getParameter("idRecensione");
			Recensione r = Recensione.generateByStringId(idRecensione);
			Voto v = new Voto(-1, voto, u);
			v = RecensioneManager.inserisciVoto(r, v);
			
			if(v != null && v.getId() != -1) response.getWriter().write("succ " + v.getId());
			else response.getWriter().write("fall");
		}
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
