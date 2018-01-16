package applicationLogic.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import applicationLogic.bean.Recensione;
import applicationLogic.model.RecensioneManager;

@WebServlet("/mostrarecensioni")
public class VisualizzaRecensioniController extends HttpServlet {
	private static final long serialVersionUID = 1L;
 
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ArrayList<Recensione> listaRecensioni = RecensioneManager.getAll();
		String ord = request.getParameter("ord");
		
		if(ord != null && ord.equals("data")){
			listaRecensioni.sort(Recensione.COMP_BY_DATA);
		}else if(ord != null && ord.equals("segn")){
			listaRecensioni.sort(Recensione.COMP_BY_SEGNALATA);
		}

		request.setAttribute("listaRec", listaRecensioni);
		request.getRequestDispatcher("gestioneRecensioni_view.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
