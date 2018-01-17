package applicationLogic.model;

import java.sql.SQLException;
import java.util.ArrayList;

import applicationLogic.bean.Film;
import applicationLogic.bean.FilmLocal;
import applicationLogic.bean.Recensione;
import applicationLogic.bean.Utente;
import applicationLogic.bean.Voto;
import applicationLogic.exception.DatiTroppoBrevi;
import applicationLogic.exception.VotoMancante;
import storage.database.FilmLocalDAO;
import storage.database.RecensioneDAO;
import storage.database.VotoDAO;

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
	
	public static ArrayList<Recensione> getSegnalate() {
		try{
			return RecensioneDAO.selectBySegnalate();			
		}catch(SQLException e){
			e.printStackTrace();
			return new ArrayList<Recensione>();	
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
	
	public static Recensione getCompleta(Recensione r) {
		r = get(r);
		if(r == null) return null;
		try{
			r.setFilm(FilmLocalDAO.select((FilmLocal) r.getFilm()));
			return r;
		}catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public static ArrayList<Recensione> getCompleta(Utente u) {
		ArrayList<Recensione> lista = get(u);
		ArrayList<Recensione> out = new ArrayList<>();
		
		for(Recensione r: lista) out.add(getCompleta(r));
		
		return out;
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

	public static boolean vota(Recensione r, Voto v) {
		try{
			v = VotoDAO.insert(v, r);
			if(v.getId() != -1)	return true;
			else return false;
		}catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	public static boolean segnala(Recensione r) {
		try{
			RecensioneDAO.updateSegnalazione(r);
			return true;
		}catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	public static ArrayList<Recensione> getAll() {
		try{
			return RecensioneDAO.select();
		}catch(SQLException e){
			e.printStackTrace();
			return new ArrayList<Recensione>();
		}
	}
}