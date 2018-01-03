package applicationLogic.managers;

import java.sql.SQLException;
import java.util.ArrayList;

import applicationLogic.models.Film;
import storage.api.ApiRequest;
import storage.database.FilmDAO;

/**
 * Gestisce tutto ciò che riguarda il collegamento fra l'applicazione e la
 * ricerca dei film
 * 
 * @author Luca
 * @since 1.0
 */
public class RicercaManager {

	private RicercaManager() {/* Costruttore vuoto e privato poichè non istanziabile */}

	public static ArrayList<Film> ricercaFilms(String query) {
		String richiesta = query.trim();
		return ApiRequest.getFilms(richiesta);
	}

	public static Film ricercaFilm(String idStringa) {
		int id = -1;
		try {
			if (idStringa != null) id = Math.abs(Integer.parseInt(idStringa));
		} catch (NumberFormatException e) {
			id = -1;
		}

		if (id != -1) {
			Film filmOut =  ApiRequest.getFilm(id);
			
			try {
				FilmDAO.inserisciUtente(filmOut);
			} catch (SQLException e) {/*film già presente*/}
			
			return filmOut;
		} else {
			return new Film(0, 
					"Errore richiesta - Titolo",
					"Errore richiesta - Titolo Lingua Originale",
					"Errore richiesta - Locandina",
					null,
					"Errore richiesta - Lingua Originale",
					"Errore richiesta - Descrizione",
					"Errore richiesta - Data di uscita",
					0d,
					0,
					"Errore richiesta - Backdrop");
		}
	}
	
	
}
