package applicationLogic.model;

import java.sql.SQLException;
import java.util.ArrayList;

import applicationLogic.bean.Film;
import applicationLogic.bean.FilmLocal;
import applicationLogic.bean.Utente;
import storage.database.FilmLocalDAO;
import storage.database.WatchlistDAO;

/**
 * Gestisce tutto ciò che riguarda il collegamento fra l'applicazione e la
 * watchlist
 * 
 * @author Luca
 * @since 1.0
 */
public class WatchlistManager {
	private WatchlistManager() {
		/* Costruttore vuoto e privato poichè non istanziabile */}

	/**
	 * Permette di aggiungere un film alla watchlist di un utente
	 * 
	 * @param f
	 *            un oggetto film con un id
	 * @param u
	 *            un oggetto utente con un username
	 * @return true se è stato aggiunto con successo, false altrimenti
	 */
	public static boolean addWatchlist(Film f, Utente u) {
		try {
			WatchlistDAO.insert(f, u);
			return true;

		} catch (SQLException e) { // non dovrebbero succedere mai ma provo lo
									// stesso
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * Permette di rimuovere un film dalla watchlist di un utente
	 * 
	 * @param f
	 *            un oggetto film con un id
	 * @param u
	 *            un oggetto utente con un username
	 * @return true se è stato rimosso con successo, false altrimenti
	 */
	public static boolean removeWatchlist(Film f, Utente u) {
		try {
			WatchlistDAO.removeFilm(f, u);
			return true;

		} catch (SQLException e) { // non dovrebbero succedere mai ma provo lo
									// stesso
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * Permette di sapere se un film è o meno nella watchlist di un utente
	 * 
	 * @param f
	 *            un oggetto film con un id
	 * @param u
	 *            un oggetto utente con un username
	 * @return true se è nella watchlist, false altrimenti
	 */
	public static boolean checkFilmInWatchlist(Film f, Utente u) {
		try {
			ArrayList<Film> watchlist = WatchlistDAO.selectByUser(u);

			if (watchlist.contains(f))
				return true;
			else
				return false;

		} catch (SQLException e) { // non dovrebbero succedere mai ma provo lo
									// stesso
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * Permette di ottenere la watchlist di un utente
	 * 
	 * @param u
	 *            un oggetto utente con un username
	 * @return tutti i film nella watchlist dell'utente
	 */
	public static ArrayList<FilmLocal> getWatchlist(Utente u) {
		ArrayList<Film> watchlistSoloID;
		ArrayList<FilmLocal> out = new ArrayList<FilmLocal>();

		try {
			watchlistSoloID = WatchlistDAO.selectByUser(u);
			for (Film x : watchlistSoloID) {
				FilmLocal attuale = FilmLocalDAO.select((FilmLocal) x);
				if (attuale != null)
					out.add(attuale);
			}
			return out;

		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
}
