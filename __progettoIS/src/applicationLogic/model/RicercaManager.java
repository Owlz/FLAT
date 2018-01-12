package applicationLogic.model;

import java.sql.SQLException;
import java.util.ArrayList;

import applicationLogic.bean.Film;
import applicationLogic.bean.FilmLocal;
import applicationLogic.bean.FilmRemote;
import storage.api.FilmRemoteDAO;
import storage.database.FilmLocalDAO;

/**
 * Gestisce tutto ciò che riguarda il collegamento fra l'applicazione e la
 * ricerca dei film
 * 
 * @author Luca
 * @since 1.0
 */
public class RicercaManager {

	private RicercaManager() {
		/* Costruttore vuoto e privato poichè non istanziabile */}

	public static ArrayList<Film> ricercaFilms(String query) {
		String richiesta = (query != null) ? query.trim() : null;
		
		ArrayList<Film> risultato = FilmRemoteDAO.getFilms(richiesta);
		
		if (risultato == null) {
			risultato = new ArrayList<Film>();
			risultato.add(new FilmLocal(0, "Errore richiesta", "Errore richiesta", "Errore richiesta"));
		}
		return risultato;

	}

	public static FilmRemote ricercaFilm(Film f) {
		FilmRemote fOut = null;
		try {
			fOut = FilmRemoteDAO.getFilm(f);
			FilmLocalDAO.insert(fOut);
			return fOut;
		} catch (SQLException e) { /* film già presente nel database locale */
			return fOut;

		} catch (NumberFormatException | NullPointerException e) {
			/* id film errato e/o id non valido */
			e.printStackTrace();
			return new FilmRemote(0, "Errore richiesta - Titolo", "Errore richiesta - Titolo Lingua Originale",
					"Errore richiesta - Locandina", new ArrayList<String>(), "Errore richiesta - Lingua Originale",
					"Errore richiesta - Descrizione", "Errore richiesta - Data di uscita", 0d, 0,
					"Errore richiesta - Backdrop");
		}
	}
}
