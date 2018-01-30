package test.dao;

import java.sql.SQLException;
import java.util.ArrayList;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import applicationLogic.bean.FilmRemote;
import applicationLogic.bean.Recensione;
import applicationLogic.bean.Utente;
import applicationLogic.bean.Voto;
import storage.database.FilmLocalDAO;
import storage.database.RecensioneDAO;
import storage.database.UtenteDAO;

public class RecensioneDAOTester {
	static FilmRemote fr = new FilmRemote(1, "Titolo Film", "Titolo Originale", "locandina", new ArrayList<String>(),
			"it", "descrizione", "1997-13-06", 10.0D, 10, "backdrop");
	static Utente u = new Utente("nomeTester", "cognomeTester", "tester01", "password", "mail@tester.it", "utente");
	static Utente u1 = new Utente("nomeTester2", "cognomeTester2", "tester02", "password2", "mail@tester2.it",
			"utente");
	static Recensione r = new Recensione(-5, fr, u, 8, "Una recensione",
			"Questo è il testo della recensione che viene inserita", new ArrayList<Voto>(), false);

	@BeforeClass
	public static void aggiungiOracolo() throws SQLException {
		FilmLocalDAO.insert(fr);
		UtenteDAO.insert(u);
		UtenteDAO.insert(u1);
		RecensioneDAO.insert(r);
	}

	@AfterClass
	public static void rimuoviOracolo() throws SQLException {
		RecensioneDAO.delete(r);
		FilmLocalDAO.delete(fr);
		UtenteDAO.delete(u1);
		UtenteDAO.delete(u);
	}

	@Test
	public void testSelectById() throws SQLException {
		assert (r.equals(RecensioneDAO.selectById(r)));
	}

	@Test
	public void testSelectByUtenteFilm() throws SQLException {
		assert (r.equals(RecensioneDAO.selectByUtenteFilm(r)));
	}

	@Test
	public void testSelectByUtente() throws SQLException {
		assert (r.equals(RecensioneDAO.selectByUtente(u).get(0)));
	}

	@Test
	public void testSelectByFilm() throws SQLException {
		assert (RecensioneDAO.selectByFilm(fr).contains(r));
	}

	@Test
	public void testInsert() throws SQLException {
		Recensione r1 = new Recensione(-5, fr, u1, 8, "Una recensione",
				"Questo è il testo della recensione che viene inserita", new ArrayList<Voto>(), false);
		assert (r1.equals(RecensioneDAO.insert(r1)));

		RecensioneDAO.delete(r1);
	}

	@Test
	public void testUpdateSegnalazione() throws SQLException {
		Recensione r1 = new Recensione(-5, fr, u1, 8, "Una recensione",
				"Questo è il testo della recensione che viene inserita", new ArrayList<Voto>(), false);

		RecensioneDAO.insert(r1);
		r1.setSegnalata(true);
		RecensioneDAO.updateSegnalazione(r1);
		assert (r1.equals(RecensioneDAO.selectByUtenteFilm(r1)));
		RecensioneDAO.delete(r1);
	}

	@Test
	public void testSelectBySegnalate() throws SQLException {
		Recensione r1 = new Recensione(-5, fr, u1, 8, "Una recensione",
				"Questo è il testo della recensione che viene inserita", new ArrayList<Voto>(), true);

		RecensioneDAO.insert(r1);
		RecensioneDAO.updateSegnalazione(r1);
		assert (RecensioneDAO.selectBySegnalate().contains(r1));
		RecensioneDAO.delete(r1);

	}
}
