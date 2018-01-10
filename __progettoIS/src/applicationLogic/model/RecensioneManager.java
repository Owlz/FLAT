package applicationLogic.model;

import java.sql.SQLException;
import java.util.ArrayList;

import applicationLogic.bean.Film;
import applicationLogic.bean.Recensione;
import applicationLogic.bean.Utente;
import storage.database.RecensioneDAO;

public class RecensioneManager {
	private RecensioneManager() {/*Costruttore vuoto e privato poichè non istanziabile*/}

	public static Recensione addRecensione(Recensione r){
		/* TODO: implementare */
		return null;
	}
	
	public static ArrayList<Recensione> getRecensioniByFilm(Film f) {
		try{
			return RecensioneDAO.getRecensioni(f);
		}catch (SQLException e){
			e.printStackTrace();
			return new ArrayList<Recensione>();
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
}