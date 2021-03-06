package applicationLogic.model;

import java.sql.SQLException;

import applicationLogic.bean.Utente;
import applicationLogic.exception.DatiNonPresenti;
import applicationLogic.exception.DatiNonValidi;
import storage.database.UtenteDAO;

/**
 * Gestisce tutto ci� che riguarda il collegamento fra l'applicazione ed il
 * database per l'autenticazione
 * 
 * @author Luca
 * @since 1.0
 */
public class AutenticazioneManager {
	private AutenticazioneManager(){/*Costruttore vuoto e privato poich� non istanziabile*/}
	
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
			String user = u.getUsername();
			String pattern1 = "^[a-z A-Z]{2,15}$";
			
			String passwd = u.getPassword(); 
			String pattern2 = "^[a-zA-Z0-9._-]{3,15}$";
		      
			if(!user.matches(pattern1) || !passwd.matches(pattern2))
				throw new DatiNonValidi();
			
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
