package applicationLogic.exception;

/**
 * Classe di errore per quando non viene aggiunto un voto alla recensione
 * 
 * @author Luca
 *
 */
public class VotoMancante extends Exception {
	private static final long serialVersionUID = 1L;

	public VotoMancante() {
		super("Voto mancante");
	}

}
