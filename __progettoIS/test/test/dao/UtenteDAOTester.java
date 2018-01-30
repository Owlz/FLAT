package test.dao;

import java.sql.SQLException;

import org.junit.Test;

import applicationLogic.bean.Utente;
import storage.database.UtenteDAO;

public class UtenteDAOTester {
	
	@Test
	public void testInsert() throws SQLException {
		Utente u = new Utente("nomeTester","cognomeTester", "tester01", "password", "mail@tester.it", "utente");
		assert(u.equals(UtenteDAO.insert(u)));
		
		UtenteDAO.delete(u);
	}

	@Test
	public void testSelect() throws SQLException {
		Utente u = new Utente("nomeTester","cognomeTester", "tester01", "password", "mail@tester.it", "utente");
		UtenteDAO.insert(u);
		
		assert(u.equalsComplete(UtenteDAO.select(u)));
		
		UtenteDAO.delete(u);
	}

	@Test
	public void testUpdate() throws SQLException {
		Utente u = new Utente("nomeTester","cognomeTester", "tester01", "password", "mail@tester.it", "utente");
		UtenteDAO.insert(u);
		
		u.setNome("nuovoNomeTester");
		UtenteDAO.update(u);
		
		UtenteDAO.delete(u);
	}

	@Test
	public void testDelete() throws SQLException {
		Utente u = new Utente("nomeTester","cognomeTester", "tester01", "password", "mail@tester.it", "utente");
		UtenteDAO.insert(u);
		
		UtenteDAO.delete(u);
	}

}
