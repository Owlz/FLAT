package applicationLogic.model;

import java.sql.SQLException;

import applicationLogic.bean.Utente;
import applicationLogic.exception.DatiNonValidi;
import storage.database.UtenteDAO;

/**
 * Gestisce tutto ciò che riguarda il collegamento fra l'applicazione ed il
 * database per l'area utente
 * 
 * @author Luca
 * @since 1.0
 */
public class GestioneAccountManager {
	private GestioneAccountManager() {
		/* Costruttore vuoto e privato poichè non istanziabile */}

	/**
	 * Permette di ottenere tutte le informazioni di un utente
	 * 
	 * @param u
	 *            deve contenere l'user da cercare
	 * @return un utente con tutte le info
	 */
	public static Utente getUtente(Utente u) {
		Utente utenteReturn;
		try {
			utenteReturn = UtenteDAO.select(u);
		} catch (SQLException e) {
			e.printStackTrace();
			utenteReturn = null;
		}
		return utenteReturn;
	}

	/**
	 * Permette di aggiornare i dati di un utente nel database
	 * 
	 * @param uNew
	 *            oggetto Utente con i dati nuovi al suo interno
	 * @param uAtt
	 *            oggetto Utente con i dati vecchi dell'utente
	 * @return un oggetto Utente con i dati aggiornati
	 * @throws DatiNonValidi
	 *             nel caso i dati inseriti non sono validi
	 */
	public static Utente aggiornaUtente(Utente uNew, Utente uAtt) throws DatiNonValidi {

		String nome = uNew.getNome();
		String patternNome = "^[a-z A-Z]{2,15}$";

		String cognome = uNew.getCognome();
		String patternCognome = "^[a-z A-Z]{2,15}$";

		String passwd = uNew.getPassword();
		String patternPassword = "^[a-zA-Z0-9._-]{3,15}$";

		if (!nome.matches(patternNome) || !cognome.matches(patternCognome) || !passwd.matches(patternPassword))
			throw new DatiNonValidi();

		if (uNew.getNome() != null && !uNew.getNome().equals(""))
			uAtt.setNome(uNew.getNome());
		if (uNew.getCognome() != null && !uNew.getCognome().equals(""))
			uAtt.setCognome(uNew.getCognome());
		if (uNew.getPassword() != null && !uNew.getPassword().equals(""))
			uAtt.setPassword(uNew.getPassword());

		try {
			Utente u = UtenteDAO.update(uAtt);
			return u;
		} catch (SQLException e) {
			throw new DatiNonValidi();
		}

	}

}
