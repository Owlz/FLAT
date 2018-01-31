package applicationLogic.exception;

/**
 * Classe di errore per quando i dati non sono presenti nel database
 * 
 * @author Luca
 *
 */
public class DatiNonPresenti extends Exception {
	private static final long serialVersionUID = 1L;

	public DatiNonPresenti() {
		super("Account non trovato");
	}
}
