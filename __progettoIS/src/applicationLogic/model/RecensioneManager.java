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

/**
 * Gestisce tutto ciò che riguarda il collegamento fra l'applicazione ed il
 * database per le recensioni
 * 
 * @author Luca
 * @since 1.0
 */
public class RecensioneManager {
	private RecensioneManager() {
		/* Costruttore vuoto e privato poichè non istanziabile */}

	/**
	 * Permette di aggiungere una recensione al database
	 * 
	 * @param r
	 *            la recensione da aggiungere
	 * @return la recensione aggiunta
	 * @throws DatiTroppoBrevi
	 *             se il titolo o il testo ha meno caratteri del previsto
	 * @throws VotoMancante
	 *             se il voto non è stato selezionato
	 */
	public static Recensione addRecensione(Recensione r) throws DatiTroppoBrevi, VotoMancante {
		int LUNGHEZZA_MINIMA_REVIEW = 20;
		int LUNGHEZZA_MINIMA_TITOLO = 5;

		if (r.getTesto().length() < LUNGHEZZA_MINIMA_REVIEW)
			throw new DatiTroppoBrevi(
					"Il testo della recensione non può essere meno di " + LUNGHEZZA_MINIMA_REVIEW + " caratteri");

		if (r.getTitolo().length() < LUNGHEZZA_MINIMA_TITOLO)
			throw new DatiTroppoBrevi(
					"Il titolo della recensione non può essere meno di " + LUNGHEZZA_MINIMA_TITOLO + " caratteri");

		if (r.getVoto() == 0)
			throw new VotoMancante();

		try {
			return RecensioneDAO.insert(r);
		} catch (SQLException e) {
			// query mal scritta o dati inseriti non corretti, non dovrebbe mai
			// succedere
			// quindi è corretto non fare nulla
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * Ottiene una recensione specifica
	 * 
	 * @param r
	 *            un oggetto recensione con un id
	 * @return la recensione completa
	 */
	public static Recensione get(Recensione r) {
		try {
			return RecensioneDAO.selectById(r);
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * Permette di ottenere una recensione completa di ogni informazione locale
	 * sul film collegato
	 * 
	 * @param r
	 *            un oggetto recensione con un id
	 * @return una recensione completa
	 */
	public static Recensione getCompleta(Recensione r) {
		r = get(r);
		if (r == null)
			return null;
		try {
			r.setFilm(FilmLocalDAO.select((FilmLocal) r.getFilm()));
			return r;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * Permette di ottenere una lista completa di recensioni di un utente
	 * 
	 * @param u
	 *            oggetto utente con un username
	 * @return la lista di recensioni dell'utetne con i film inclusi
	 */
	public static ArrayList<Recensione> getCompleta(Utente u) {
		ArrayList<Recensione> lista = get(u);
		ArrayList<Recensione> out = new ArrayList<>();

		for (Recensione r : lista)
			out.add(getCompleta(r));

		return out;
	}

	/**
	 * Permette di ottenere una recensione dato un film ed un utente specifico
	 * 
	 * @param u
	 *            oggetto film con un id
	 * @param f
	 *            oggetto utente con un username
	 * @return la recensione di quell'utente su quel film
	 */
	public static Recensione get(Utente u, Film f) {
		try {
			Recensione r = new Recensione();
			r.setFilm(f);
			r.setUtente(u);

			return RecensioneDAO.selectByUtenteFilm(r);

		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * Permette di ottenere una lista di recensioni dato uno specifico film
	 * 
	 * @param f
	 *            oggetto film con un id
	 * @return la lista di tutte le recensioni di quel film
	 */
	public static ArrayList<Recensione> get(Film f) {
		try {
			return RecensioneDAO.selectByFilm(f);
		} catch (SQLException e) {
			e.printStackTrace();
			return new ArrayList<Recensione>();
		}
	}

	/**
	 * Permette di ottenere una lista di recensioni dato un utente
	 * 
	 * @param u
	 *            oggetto utente con un username
	 * @return la lista di recensioni di quell'utente
	 */
	public static ArrayList<Recensione> get(Utente u) {
		try {
			return RecensioneDAO.selectByUtente(u);

		} catch (SQLException e) {
			e.printStackTrace();
			return new ArrayList<Recensione>();
		}
	}

	/**
	 * Permette di rimuovere una recensione dal database
	 * 
	 * @param r
	 *            oggetto recensione con un id
	 * @return true se rimossa con successo, false altrimenti
	 */
	public static boolean rimuovi(Recensione r) {
		try {
			RecensioneDAO.delete(r);
			return true;

		} catch (SQLException e) { // non dovrebbero succedere mai ma provo lo
									// stesso
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * Permette di inserire un voto ad una recensione
	 * 
	 * @param r
	 *            oggetto recensione con un id
	 * @param v
	 *            oggetto voto con utente che ha votato e voto inserito
	 * @return oggetto voto completo
	 */
	public static Voto inserisciVoto(Recensione r, Voto v) {
		try {
			v = VotoDAO.insert(v, r);
			if (v.getId() != -1)
				return v;
			else
				return null;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * Permette di cambiare un voto ad una recensione
	 * 
	 * @param v
	 *            oggetto voto con id
	 * @return true se aggiornata con successo, false altrimenti
	 */
	public static boolean cambiaVoto(Voto v) {
		try {
			VotoDAO.updateById(v);
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * Permette di segnalare una recensione
	 * 
	 * @param r
	 *            oggetto recensione con id e valore "segnalata"
	 * @return true se segnalata con successo, false altrimenti
	 */
	public static boolean segnala(Recensione r) {
		try {
			RecensioneDAO.updateSegnalazione(r);
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * Permette di rimuovere un voto ad una recensione
	 * 
	 * @param v
	 *            oggetto voto con id
	 * @return true se rimosso con successo, false altrimenti
	 */
	public static boolean rimuoviVoto(Voto v) {
		try {
			VotoDAO.remove(v);
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
}