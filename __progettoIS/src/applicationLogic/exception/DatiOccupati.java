package applicationLogic.exception;

public class DatiOccupati extends Exception {
	private static final long serialVersionUID = 1L;

	public DatiOccupati() {
		super("Dati già in uso");
	}

	public DatiOccupati(String message) {
		super("Dati già in uso: " + message);
	}

}
