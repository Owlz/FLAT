package applicationLogic.managers;

import java.sql.SQLException;

import applicationLogic.models.Utente;
import storage.UtenteDAO;

/**
 * Gestisce tutto ciò che riguarda il collegamento fra l'applicazione ed il database per l'area utente
 * @author Luca
 * @since 1.0
 */
public class AreaUtenteManager {
	private AreaUtenteManager() {/*Costruttore vuoto e privato poichè non istanziabile*/}

	public static Utente getUtente(Utente u) {
			Utente utenteReturn;
			try{
				utenteReturn = UtenteDAO.getUtente(u);
			} catch (SQLException e){
				e.printStackTrace();
				utenteReturn = null;
			}
			return utenteReturn;
	}
	
	

}
