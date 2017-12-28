package applicationLogic.managers;

import java.sql.SQLException;

import applicationLogic.exception.DatiOccupati;
import applicationLogic.models.Utente;
import storage.database.UtenteDAO;

/**
 * Gestisce tutto ciò che riguarda il collegamento fra l'applicazione ed il database per l'area utente
 * @author Luca
 * @since 1.0
 */
public class AreaUtenteManager {
	private AreaUtenteManager() {/*Costruttore vuoto e privato poichè non istanziabile*/}

	/**
	 * Permette di ottenere tutte le informazioni di un utente
	 * @param u deve contenere l'user da cercare
	 * @return un utente con tutte le info
	 */
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
	
	public static Utente aggiornaUtente(Utente uNew, Utente uAtt) throws DatiOccupati {
		
		if(uNew.getNome() != null && !uNew.getNome().equals("")) uAtt.setNome(uNew.getNome());
		if(uNew.getCognome() != null && !uNew.getCognome().equals("")) uAtt.setCognome(uNew.getCognome());
		if(uNew.getEmail() != null && !uNew.getEmail().equals("")) uAtt.setEmail(uNew.getEmail());
		if(uNew.getPassword() != null && !uNew.getPassword().equals("")) uAtt.setPassword(uNew.getPassword());

		try{
			Utente u = UtenteDAO.aggiornaUtente(uAtt);
			return u;
		}catch (SQLException e) {
			throw new DatiOccupati(e.getMessage());
		}
	}
	
	

}
