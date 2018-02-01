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
import storage.database.VotoDAO;

public class VotoDAOTester {
	static FilmRemote fr = new FilmRemote(1, "Titolo Film", "Titolo Originale", "locandina", new ArrayList<String>(),
			"it", "descrizione", "1997-13-06", 10.0D, 10, "backdrop");
	static Utente u = new Utente("nomeTester", "cognomeTester", "tester01", "password", "mail@tester.it", "utente");
	static Utente u1 = new Utente("nomeTester2", "cognomeTester2", "tester02", "password2", "mail@tester2.it",
			"utente");
	static Recensione r = new Recensione(-5, fr, u, 8, "Una recensione",
			"Questo è il testo della recensione che viene inserita", new ArrayList<Voto>(), false);
	static Voto v = new Voto(-1, 8, u);

	@BeforeClass
	public static void aggiungiOracolo() throws SQLException {
		FilmLocalDAO.insert(fr);
		UtenteDAO.insert(u);
		UtenteDAO.insert(u1);
		RecensioneDAO.insert(r);
		v.setId(VotoDAO.insert(v, r).getId());
	}

	@AfterClass
	public static void rimuoviOracolo() throws SQLException {
		VotoDAO.remove(v);
		RecensioneDAO.delete(r);
		FilmLocalDAO.delete(fr);
		UtenteDAO.delete(u);
		UtenteDAO.delete(u1);
	}

	@Test
	public void testSelectByIdReview() throws SQLException {
		assert (VotoDAO.selectByIdReview(r).contains(v));
	}

	@Test
	public void testUpdateById() throws SQLException {
		Voto v1 = new Voto(-1, 8, u1);
		v1.setId(VotoDAO.insert(v1, r).getId());

		v1.setVoto(7);
		VotoDAO.updateById(v1);

		for (Voto x : VotoDAO.selectByIdReview(r)) {
			if (x.getId() == v1.getId())
				assert (v1.equals(x));
		}

		VotoDAO.remove(v1);
	}

	@Test
	public void testInsert() throws SQLException {
		Voto v1 = new Voto(-1, 8, u1);
		Voto valoreRitorno = VotoDAO.insert(v1, r);

		v1.setId(valoreRitorno.getId());
		assert (v1.equals(valoreRitorno));
		
		VotoDAO.remove(v1);
	}

	@Test
	public void testRemove() throws SQLException {
		Voto v1 = new Voto(-1, 8, u1);
		v1.setId(VotoDAO.insert(v1, r).getId());

		VotoDAO.remove(v1);
	}

}
