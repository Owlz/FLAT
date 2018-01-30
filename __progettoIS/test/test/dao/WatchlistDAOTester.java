package test.dao;

import static org.junit.Assert.fail;

import java.sql.SQLException;
import java.util.ArrayList;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import applicationLogic.bean.FilmRemote;
import applicationLogic.bean.Utente;
import storage.database.FilmLocalDAO;
import storage.database.UtenteDAO;
import storage.database.WatchlistDAO;

public class WatchlistDAOTester {
	static FilmRemote fr = new FilmRemote(1, "Titolo Film", "Titolo Originale", "locandina", new ArrayList<String>(),
			"it", "descrizione", "1997-13-06", 10.0D, 10, "backdrop");
	static Utente u = new Utente("nomeTester", "cognomeTester", "tester01", "password", "mail@tester.it", "utente");

	@BeforeClass
	public static void aggiungiOracolo() throws SQLException {
		FilmLocalDAO.insert(fr);
		UtenteDAO.insert(u);
	}

	@AfterClass
	public static void rimuoviOracolo() throws SQLException {
		FilmLocalDAO.delete(fr);
		UtenteDAO.delete(u);
	}

	@Test
	public void testInsertAndRemove() throws SQLException {
		WatchlistDAO.insert(fr, u);
		WatchlistDAO.removeFilm(fr, u);
	}

	@Test
	public void testSelectByUser() throws SQLException {
		WatchlistDAO.insert(fr, u);
		if(WatchlistDAO.selectByUser(u).size() != 1) fail("l'utente ha più di un film in watchlist");
		WatchlistDAO.removeFilm(fr, u);
	}

}
