package applicationLogic.managers;

import java.sql.SQLException;
import java.util.ArrayList;

import applicationLogic.models.Film;
import applicationLogic.models.Recensione;
import applicationLogic.models.Utente;
import storage.database.RecensioneDAO;

public class RecensioneManager {
	private RecensioneManager() {/*Costruttore vuoto e privato poichè non istanziabile*/}

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