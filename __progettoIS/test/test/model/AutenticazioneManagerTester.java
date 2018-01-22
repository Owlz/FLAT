package test.model;

import java.sql.SQLException;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import applicationLogic.bean.Utente;
import applicationLogic.exception.DatiNonPresenti;
import applicationLogic.exception.DatiNonValidi;
import applicationLogic.model.AutenticazioneManager;
import storage.database.UtenteDAO;

public class AutenticazioneManagerTester {
	private final static Utente ORACOLO = new Utente("Nome", "Cognome", "tester01", "password", "mail@mail.it",
			"utente");

	public AutenticazioneManagerTester() {
	}

	@BeforeClass
	public static void inserisiUtenteDiTesting() throws SQLException {
		UtenteDAO.insert(ORACOLO);
	}

	@AfterClass
	public static void rimuoviUtenteDiTesting() throws SQLException {
		UtenteDAO.delete(ORACOLO);
	}

	@Test
	public void ControlloUtenteNelDatabaseSuccesso() throws DatiNonPresenti, DatiNonValidi {
		Utente input = new Utente();
		input.setUsername("tester01");
		input.setPassword("password");

		assert(ORACOLO.equalsComplete(AutenticazioneManager.autenticaUtente(input)));
	}

	@Test(expected = DatiNonPresenti.class)
	public void ControlloUtenteNelDatabaseNoUsername() throws DatiNonPresenti, DatiNonValidi {
		Utente input = new Utente();
		input.setPassword("password");

		assert(ORACOLO.equalsComplete(AutenticazioneManager.autenticaUtente(input)));
	}

	@Test(expected = DatiNonPresenti.class)
	public void ControlloUtenteNelDatabaseUsernameInesistente() throws DatiNonPresenti, DatiNonValidi {
		Utente input = new Utente();
		input.setUsername("tester02");
		input.setPassword("password");

		assert(ORACOLO.equalsComplete(AutenticazioneManager.autenticaUtente(input)));
	}

	@Test(expected = DatiNonValidi.class)
	public void ControlloUtenteNelDatabasePasswordErrata() throws DatiNonPresenti, DatiNonValidi {
		Utente input = new Utente();
		input.setUsername("tester01");
		input.setPassword("password2");

		assert(ORACOLO.equalsComplete(AutenticazioneManager.autenticaUtente(input)));
	}
}
