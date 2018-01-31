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

	/**
	 * Permette di ricevere una lista di film dall'API online in base alla
	 * request
	 * 
	 * @param query
	 *            da cercare sull'API
	 * @return la lista di film dati la query immessa
	 */
	public static ArrayList<Film> ricercaFilms(String query) {
		String richiesta = (query != null) ? query.trim() : null;

		ArrayList<Film> risultato = FilmRemoteDAO.getFilms(richiesta);

		if (risultato == null) {
			risultato = new ArrayList<Film>();
			risultato.add(new FilmLocal(0, "Errore richiesta", "Errore richiesta", "Errore richiesta"));
		}
		return risultato;

	}

	/**
	 * Permette di trovare un film sull'API online
	 * 
	 * @param f
	 *            oggetto film con un id
	 * @return il film con tutti i dati presi dall'API
	 */
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

	/**
	 * Permette di selezionare un film dal database locale
	 * 
	 * @param f
	 *            un oggetto film con un id
	 * @return il film con tutti i dati presi dal database locale
	 */
	public static FilmLocal ricercaLocale(Film f) {
		try {
			return FilmLocalDAO.select((FilmLocal) f);
		} catch (SQLException e) {
			return null;
		}
	}
}
