package test.model;

import java.sql.SQLException;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

import applicationLogic.bean.Utente;
import applicationLogic.exception.DatiNonValidi;
import applicationLogic.exception.DatiOccupati;
import applicationLogic.model.RegistrazioneManager;
import storage.database.UtenteDAO;

public class RegistrazioneManagerTester {
	private final static Utente ORACOLO1 = new Utente("Nome", "Cognome", "tester01", "password", "mail@mail.it",
			"utente");

	private final static Utente ORACOLO2 = new Utente("Nome", "Cognome", "tester02", "password", "mail2@mail.it",
			"utente");

	@BeforeClass
	public static void inizializzaUtente2() throws SQLException {
		UtenteDAO.insert(ORACOLO2);
	}

	@AfterClass
	public static void rimuoviUtente2() throws SQLException {
		UtenteDAO.delete(ORACOLO2);
	}
	
	@Ignore
	@Test
	public void registrazioneUtenteSuccesso() throws DatiNonValidi, DatiOccupati, SQLException {
		Utente input = new Utente();
		input.setNome("Nome");
		input.setCognome("Cognome");
		input.setUsername("tester01");
		input.setPassword("password");
		input.setEmail("mail@mail.it");
		input.setRuolo("utente");
		assert(ORACOLO1.equalsComplete(RegistrazioneManager.aggiungiUtente(input)));
		
		UtenteDAO.delete(ORACOLO1);
	}	
	
	@Test(expected = DatiNonValidi.class)
	public void registrazioneUtenteDatiNonValidi() throws DatiNonValidi, DatiOccupati {
		Utente input = new Utente();
		input.setNome("");
		input.setCognome("");
		input.setUsername("tester01");
		input.setPassword("password");
		input.setEmail("mail@mail.it");
		input.setRuolo("utente");
		
		RegistrazioneManager.aggiungiUtente(input);
	}
	
	@Test(expected = DatiOccupati.class)
	public void registrazioneUtenteDatiOccupati() throws DatiNonValidi, DatiOccupati {
		Utente input = new Utente();
		input.setNome("Nome");
		input.setCognome("Cognome");
		input.setUsername("tester02");
		input.setPassword("password");
		input.setEmail("mail2@mail.it");
		input.setRuolo("utente");
		
		RegistrazioneManager.aggiungiUtente(input);
	}
	
	

}
