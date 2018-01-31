package applicationLogic.exception;

/**
 * Classe di errore per quando i dati immessi non sono validi
 * 
 * @author Luca
 *
 */
public class DatiNonValidi extends Exception {
	private static final long serialVersionUID = 1L;

	public DatiNonValidi() {
		super("Dati inseriti non validi");
	}

	public DatiNonValidi(String msg) {
		super("Dati inseriti non validi:" + msg);
	}

}
