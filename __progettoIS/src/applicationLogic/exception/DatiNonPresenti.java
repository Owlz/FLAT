package applicationLogic.exception;

public class DatiNonPresenti extends Exception{
	private static final long serialVersionUID = 1L;
	private String campo;
	
	public DatiNonPresenti() {
		super("Account non trovato");
		campo = "";
	}
	
	public DatiNonPresenti(String msg){
		super("Account non trovato: " + msg);
		this.campo = msg;
	}

	public String getCampo() { return campo; }
	public void setCampo(String campo) { this.campo = campo; }

}
