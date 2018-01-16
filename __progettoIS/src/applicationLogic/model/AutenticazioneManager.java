package applicationLogic.model;

import java.sql.SQLException;

import applicationLogic.bean.Utente;
import applicationLogic.exception.DatiNonPresenti;
import applicationLogic.exception.DatiNonValidi;
import storage.database.UtenteDAO;

/**
 * Gestisce tutto ciò che riguarda il collegamento fra l'applicazione ed il database per l'autenticazione
 * @author Luca
 * @since 1.0
 */
public class AutenticazioneManager {
	private AutenticazioneManager(){/*Costruttore vuoto e privato poichè non istanziabile*/}
	
	/**
	 * Gesisce l'autenticazione di un utente
	 * @param u l'utente da controllare
	 * @return un oggetto di tipo Utente con tutte le informazioni
	 * @throws DatiNonPresenti nel caso in cui i dati non sono nel database
	 * @throws DatiNonValidi nel caso in cui i dati non corrispondono
	 */
	public static Utente autenticaUtente(Utente u) throws DatiNonPresenti, DatiNonValidi{
		Utente uDB;
		try {
			/* TODO: qui si controllano i campi del login */
			uDB = UtenteDAO.select(u);
			
			if(uDB == null)
				throw new DatiNonPresenti();
			
			else if(u == null || !uDB.getPassword().equals(u.getPassword()))
				throw new DatiNonValidi(); 
			
			else
				return uDB;
			
		} catch (SQLException e) {
			 e.printStackTrace();	//db down oppure query scritta male, non deve mai succedere in produzione
			 return null;
		}
	}

}
