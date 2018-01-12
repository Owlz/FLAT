package applicationLogic.model;

import java.sql.SQLException;
import java.util.ArrayList;

import applicationLogic.bean.Film;
import applicationLogic.bean.Recensione;
import applicationLogic.bean.Utente;
import applicationLogic.exception.DatiTroppoBrevi;
import applicationLogic.exception.VotoMancante;
import storage.database.RecensioneDAO;

public class RecensioneManager {
	private RecensioneManager() {/*Costruttore vuoto e privato poichè non istanziabile*/}

	public static void addRecensione(Recensione r) throws DatiTroppoBrevi, VotoMancante{
		/* TODO: rendere decente questo controllo */
		int LUNGHEZZA_MINIMA_REVIEW = 20;
		int LUNGHEZZA_MINIMA_TITOLO = 5;
		
		if(!r.getTesto().equals("") && r.getTesto().length() < LUNGHEZZA_MINIMA_REVIEW)
			throw new DatiTroppoBrevi("Il testo della recensione non può essere meno di "+ LUNGHEZZA_MINIMA_REVIEW +" caratteri");
		
		if(!r.getTitolo().equals("") && r.getTitolo().length() < LUNGHEZZA_MINIMA_TITOLO)
			throw new DatiTroppoBrevi("Il titolo della recensione non può essere meno di "+ LUNGHEZZA_MINIMA_TITOLO +" caratteri");
		
		if(r.getVoto() == 0)
			throw new VotoMancante();
		
		try {
			RecensioneDAO.insert(r);
		} catch (SQLException e) {
			// query mal scritta o dati inseriti non corretti, non dovrebbe mai succedere
			// quindi è corretto non fare nulla 
			e.printStackTrace();	
		}
	}
	
	public static Recensione get(Recensione r) {
		try{			
			return RecensioneDAO.selectById(r);
			
		} catch (SQLException e){
			e.printStackTrace();
			return null;
		}
	}

	public static Recensione get(Utente u, Film f) {
		try{
			Recensione r = new Recensione();
			r.setFilm(f);
			r.setUtente(u);
			
			return RecensioneDAO.selectByUtenteFilm(r);
			
		} catch (SQLException e){
			e.printStackTrace();
			return null;
		}
	}

	public static ArrayList<Recensione> get(Film f) {
		try{
			return RecensioneDAO.selectByFilm(f);
		}catch (SQLException e){
			e.printStackTrace();
			return new ArrayList<Recensione>();
		}
	}

	public static ArrayList<Recensione> get(Utente u) {
		try{
			return RecensioneDAO.selectByUtente(u);
			
		}catch (SQLException e){
			e.printStackTrace();
			return new ArrayList<Recensione>();
		}
	}

	public static boolean rimuovi(Recensione r) {
		try{
			RecensioneDAO.delete(r);
			return true;
			
		} catch (SQLException e) { // non dovrebbero succedere mai ma provo lo stesso
			e.printStackTrace();
			return false;
		}
	}
}