package applicationLogic.exception;

public class DatiNonPresenti extends Exception{
	private static final long serialVersionUID = 1L;

	public DatiNonPresenti() {
		super("Account non trovato");
	}
	
	public DatiNonPresenti(String msg){
		super("Account non trovato: " + msg);
	}

}
