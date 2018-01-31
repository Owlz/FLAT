package applicationLogic.exception;

/**
 * Classe di errore per quando i dati immessi sono già in uso da un altro utente
 * 
 * @author Luca
 *
 */
public class DatiOccupati extends Exception {
	private static final long serialVersionUID = 1L;

	public DatiOccupati() {
		super("Dati già in uso");
	}
}
