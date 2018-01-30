package test.model;

import java.sql.SQLException;

import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import applicationLogic.bean.Utente;
import applicationLogic.exception.DatiNonValidi;
import applicationLogic.model.GestioneAccountManager;
import storage.database.UtenteDAO;

public class GestioneAccountManagerTester {
	private final static Utente ORACOLO = new Utente("Nome", 
													"Cognome", 
													"tester01", 
													"password", 
													"mail@mail.it",
													"utente");
													

	public GestioneAccountManagerTester() {}

	@Before
	public void inserisiUtenteDiTesting() {
		try {
			UtenteDAO.insert(ORACOLO);
		} catch (SQLException e) {
			System.err.println("Impossibile inserire l'oracolo");
			e.printStackTrace();
		}
	}

	@After
	public void rimuoviUtenteDiTesting() {
		try {
			UtenteDAO.delete(ORACOLO);
		} catch (SQLException e) {
			System.err.println("Impossibile rimuovere l'oracolo");
			e.printStackTrace();
		}
	}

	@Test
	public void ottieniUtenteDalDatabaseSuccesso() {
		Utente input = new Utente();
		input.setUsername("tester01");
		input.setPassword("password");

		assert (ORACOLO.equalsComplete(GestioneAccountManager.getUtente(input)));
	}

	@Test
	public void ottieniUtenteDalDatabaseFallimento() {
		Utente input = new Utente();
		input.setUsername("tester02");

		assert (null == GestioneAccountManager.getUtente(input));
	}

	@Test
	public void modificaDatiPersonaliSuccesso() throws DatiNonValidi {
		Utente input = new Utente();
		input.setNome("NomeNuovo");
		input.setCognome("CognomeNuovo");
		input.setEmail("mail@nuova.it");
		input.setPassword("nuovapassword");

		Utente oracoloModificato = ORACOLO;
		oracoloModificato.setNome("NomeNuovo");
		oracoloModificato.setCognome("CognomeNuovo");
		oracoloModificato.setEmail("mail@nuova.it");
		oracoloModificato.setPassword("nuovapassword");

		assert (oracoloModificato.equalsComplete(GestioneAccountManager.aggiornaUtente(input, ORACOLO)));
	}

	@Test
	public void modificaDatiPersonaliMinimale() throws DatiNonValidi {
		Utente input = new Utente();
		input.setNome("NomeNuovo");

		Utente oracoloModificato = ORACOLO;
		oracoloModificato.setNome("NomeNuovo");

		assert (oracoloModificato.equalsComplete(GestioneAccountManager.aggiornaUtente(input, ORACOLO)));
	}
	
	@Ignore
	@Test(expected = DatiNonValidi.class)
	public void modificaDatiPersonaliFallimento() throws DatiNonValidi {
		Utente input = new Utente();
		input.setEmail("utente1@mail.it");

		Utente oracoloModificato = ORACOLO;
		oracoloModificato.setEmail("utente1@mail.it");
		
		Utente oracoloModificabile = ORACOLO;

		assert (oracoloModificato.equalsComplete(GestioneAccountManager.aggiornaUtente(input, oracoloModificabile)));
	}

}