package applicationLogic.exception;

public class VotoMancante extends Exception {
	private static final long serialVersionUID = 1L;

	public VotoMancante() {
		super("Voto mancante");
	}

}
