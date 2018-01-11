package applicationLogic.model;

import java.sql.SQLException;
import java.util.ArrayList;

import applicationLogic.bean.Film;
import applicationLogic.bean.Recensione;
import applicationLogic.bean.Utente;
import applicationLogic.exception.DatiTroppoBrevi;
import storage.database.RecensioneDAO;

public class RecensioneManager {
	private RecensioneManager() {/*Costruttore vuoto e privato poichè non istanziabile*/}

	public static void addRecensione(Recensione r) throws DatiTroppoBrevi{
		/* TODO: rendere decente questo controllo */
		int LUNGHEZZA_MINIMA_REVIEW = 20;
		int LUNGHEZZA_MINIMA_TITOLO = 5;
		
		if(!r.getRecensione().equals("") && r.getRecensione().length() < LUNGHEZZA_MINIMA_REVIEW){
			throw new DatiTroppoBrevi("Il testo della recensione non può essere meno di "+ LUNGHEZZA_MINIMA_REVIEW +" caratteri");
		}
		if(!r.getTitolo().equals("") && r.getTitolo().length() < LUNGHEZZA_MINIMA_TITOLO)
			throw new DatiTroppoBrevi("Il titolo della recensione non può essere meno di "+ LUNGHEZZA_MINIMA_TITOLO +" caratteri");
		
		try {
			RecensioneDAO.inserisciRecensione(r);
		} catch (SQLException e) {
			// query mal scritta o dati inseriti non corretti, non dovrebbe mai succedere
			// quindi è corretto non fare nulla 
			e.printStackTrace();	
		}
	}
	

	public static Recensione getRecensione(Utente u, Film f) {
		try{
			return RecensioneDAO.getRecensione(u, f);
		} catch (SQLException e){
			e.printStackTrace();
			return null;
		}
	}

	public static ArrayList<Recensione> getRecensioniByFilm(Film f) {
		try{
			return RecensioneDAO.getRecensioni(f);
		}catch (SQLException e){
			e.printStackTrace();
			return new ArrayList<Recensione>();
		}
	}

	public static ArrayList<Recensione> getRecensioniByUtente(Utente u) {
		try{
			return RecensioneDAO.getRecensioni(u);
		}catch (SQLException e){
			e.printStackTrace();
			return new ArrayList<Recensione>();
		}
	}
}