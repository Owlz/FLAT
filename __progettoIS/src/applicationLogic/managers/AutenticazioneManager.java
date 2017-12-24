package applicationLogic.managers;

import java.sql.SQLException;

import applicationLogic.models.Utente;
import storage.UtenteDAO;

/**
 * Gestisce tutto ciò che riguarda il collegamento fra l'applicazione ed il database per l'autenticazione
 * @author Luca
 * @since 1.0
 */
public class AutenticazioneManager {

	/**
	 * Gesisce l'autenticazione di un utente
	 * @param utente
	 * @return un oggetto di tipo Utente con tutte le informazioni
	 * @throws SQLException nel caso in cui l'utente non è nel database
	 */
	public static Utente autenticaUtente(Utente utente){
		Utente utDB;
		try {
			utDB = UtenteDAO.getUtente(utente);
			if(utDB.getRuolo().equals("visitatore"))						//utente non trovato nel DB
				return utente;
			else if(!utDB.getUsername().equals(utente.getUsername()) || 
					!utDB.getPassword().equals(utente.getPassword()))		//username e/o password non corrispondono
				return utente;
			else															//corrispondono 
				return utDB;
		} catch (SQLException e) {
			e.printStackTrace();
			return utente;
		}
	}

}
