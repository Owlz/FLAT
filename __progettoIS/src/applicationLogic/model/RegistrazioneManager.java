package applicationLogic.model;

import java.sql.SQLException;

import applicationLogic.bean.Utente;
import applicationLogic.exception.DatiNonValidi;
import applicationLogic.exception.DatiOccupati;
import storage.database.UtenteDAO;

/**
 * Gestisce tutto ciò che riguarda il collegamento fra l'applicazione ed il
 * database per la registrazione
 * 
 * @author Luca
 * @since 1.0
 */
public class RegistrazioneManager {

	private RegistrazioneManager() {
		/* Costruttore vuoto e privato poichè non istanziabile */}

	/**
	 * Gesisce la registrazione di un utente
	 * 
	 * @param utente
	 *            da inserire nel database
	 * @return l'utente inserito nel database
	 * @throws DatiNonValidi
	 *             nel caso i dati inseriti non siano validi (campi nulli etc)
	 * @throws DatiOccupati
	 *             nel caso i dati inseriti siano già occupati nel database
	 */
	public static Utente aggiungiUtente(Utente utente) throws DatiNonValidi, DatiOccupati {
		String nome = utente.getNome();
		String patternNome = "^[a-z A-Z]{2,15}$";

		String cognome = utente.getCognome();
		String patternCognome = "^[a-z A-Z]{2,15}$";

		String email = utente.getEmail();
		String patternEMail = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
				+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

		String user = utente.getUsername();
		String patternUser = "^[a-zA-Z._]{3,15}$";

		String passwd = utente.getPassword();
		String patternPassword = "^[a-zA-Z0-9._-]{3,15}$";

		if (!nome.matches(patternNome) || !cognome.matches(patternCognome) || !email.matches(patternEMail)
				|| !user.matches(patternUser) || !passwd.matches(patternPassword)) {
			throw new DatiNonValidi();
		} else {
			try {
				return UtenteDAO.insert(utente);
			} catch (SQLException e) {
				throw new DatiOccupati();
			}
		}
	}
}
