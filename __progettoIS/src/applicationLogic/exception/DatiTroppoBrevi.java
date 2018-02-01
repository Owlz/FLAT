package applicationLogic.exception;

/**
 * Classe di errore per quando i dati immessi sono troppo brevi
 * 
 * @author Luca
 *
 */
public class DatiTroppoBrevi extends Exception {
	private static final long serialVersionUID = 1L;

	public DatiTroppoBrevi() {
		super("Dati inseriti troppo brevi");
	}

	public DatiTroppoBrevi(String msg) {
		super("Dati inseriti troppo brevi: " + msg);
	}

}
