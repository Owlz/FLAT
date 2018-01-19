package test.model;

import org.junit.Test;

import applicationLogic.bean.Utente;
import applicationLogic.exception.DatiNonPresenti;
import applicationLogic.exception.DatiNonValidi;
import applicationLogic.model.AutenticazioneManager;

public class AutenticazioneManagerTester {
	public AutenticazioneManagerTester(){}
	
	@Test
    public void inserimentoUtenteNelDatabase() throws DatiNonPresenti, DatiNonValidi {
		Utente input = new Utente();
		input.setUsername("utente1");
		input.setPassword("utente1");
		
		Utente output = new Utente();
		output.setUsername("utente1");
		output.setPassword("utente1");
		output.setRuolo("utente");
		
		assert(output.getRuolo().equals(AutenticazioneManager.autenticaUtente(input).getRuolo()));
		assert(output.equals(AutenticazioneManager.autenticaUtente(input)));
	}
	
	/* mancano i casi dove fallisce */
}
