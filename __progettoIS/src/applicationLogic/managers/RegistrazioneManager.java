package applicationLogic.managers;

import java.sql.SQLException;

import applicationLogic.exception.DatiNonValidi;
import applicationLogic.exception.DatiOccupati;
import applicationLogic.models.Utente;
import storage.UtenteDAO;

/**
 * Gestisce tutto ciò che riguarda il collegamento fra l'applicazione ed il database per la registrazione
 * @author Luca
 * @since 1.0
 */
public class RegistrazioneManager {

	private RegistrazioneManager(){/*Costruttore vuoto e privato poichè non istanziabile*/}
	
	/**
	 * Gesisce la registrazione di un utente
	 * @param utente da inserire nel database
	 * @return l'utente inserito nel database
	 * @throws DatiNonValidi nel caso i dati inseriti non siano validi (campi nulli etc)
	 * @throws DatiOccupati nel caso i dati inseriti siano già occupati nel database
	 */
	public static Utente aggiungiUtente(Utente utente) throws DatiNonValidi, DatiOccupati{
		boolean checkDatiValidi = false;
		
		if(utente.getUsername().equals("") ||
		   utente.getPassword().equals("") || 
		   utente.getNome().equals("") ||
		   utente.getCognome().equals("") ||
		   utente.getEmail().equals(""))
			checkDatiValidi = true;
		
		if(checkDatiValidi){
			throw new DatiNonValidi();
		}else{
			try {
				UtenteDAO.inserisciUtente(utente);
				return utente;
			} catch (SQLException e) {
				String l = e.getMessage().split("'")[3].trim();
				if(l.equals("PRIMARY")) throw new DatiOccupati("nickname"); 
				else if (l.equals("email_UNIQUE")) throw new DatiOccupati("email");
				else throw new DatiOccupati("è esploso tutto");
			}			
		}
	}
}
