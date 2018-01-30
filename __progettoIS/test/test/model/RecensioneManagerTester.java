package test.model;

import java.sql.SQLException;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import applicationLogic.bean.FilmLocal;
import applicationLogic.bean.FilmRemote;
import applicationLogic.bean.Recensione;
import applicationLogic.bean.Utente;
import applicationLogic.exception.DatiTroppoBrevi;
import applicationLogic.exception.VotoMancante;
import applicationLogic.model.RecensioneManager;
import storage.database.FilmLocalDAO;
import storage.database.RecensioneDAO;
import storage.database.UtenteDAO;

public class RecensioneManagerTester {
	public static final Utente UTENTE_ORACOLO = new Utente("Nome", "Cognome", "tester01", "password", "mail@mail.it",
			"utente");
	public static final FilmLocal FILM_ORACOLO = new FilmLocal(1, "Film per Testing", "Testing title", "error");

	@BeforeClass
	public static void aggiungiUtenteFilm() throws SQLException {
		UtenteDAO.insert(UTENTE_ORACOLO);
		FilmRemote fr = new FilmRemote();
		fr.setId(FILM_ORACOLO.getId());
		fr.setTitolo(FILM_ORACOLO.getTitolo());
		fr.setTitoloOriginale(FILM_ORACOLO.getTitoloOriginale());
		fr.setLocandina(FILM_ORACOLO.getLocandina());
		FilmLocalDAO.insert(fr);
	}

	@AfterClass
	public static void rimuoviUtenteFilm() throws SQLException {
		UtenteDAO.delete(UTENTE_ORACOLO);
		FilmLocalDAO.delete(FILM_ORACOLO);
	}

	@Test
	public void inserimentoRecensioneSuccesso() throws DatiTroppoBrevi, VotoMancante, SQLException {
		Recensione r = new Recensione();
		r.setFilm(FILM_ORACOLO);
		r.setUtente(UTENTE_ORACOLO);
		r.setVoto(8);
		r.setTitolo("Una bellissima recensione con un titolo");
		r.setTesto("Una recensione come tutte le altre solo che questa viene usata per il testing");

		try {
			r = RecensioneManager.addRecensione(r);
		} finally {
			RecensioneDAO.delete(r);
		}
	}

	@Test(expected = DatiTroppoBrevi.class)
	public void inserimentoRecensioneDatiBrevi() throws DatiTroppoBrevi, VotoMancante, SQLException {
		Recensione r = new Recensione();
		r.setFilm(FILM_ORACOLO);
		r.setUtente(UTENTE_ORACOLO);
		r.setVoto(8);
		r.setTitolo("Recensione");
		r.setTesto("Una rec");
		
		r = RecensioneManager.addRecensione(r);
	}
	
	@Test(expected = VotoMancante.class)
	public void inserimentoRecensioneVotoMancante() throws DatiTroppoBrevi, VotoMancante, SQLException {
		Recensione r = new Recensione();
		r.setFilm(FILM_ORACOLO);
		r.setUtente(UTENTE_ORACOLO);
		r.setTitolo("Una bellissima recensione con un titolo");
		r.setTesto("Una recensione come tutte le altre solo che questa viene usata per il testing");

		r = RecensioneManager.addRecensione(r);
	}
}
