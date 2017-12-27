package applicationLogic.exception;

public class DatiOccupati extends Exception {
	private static final long serialVersionUID = 1L;
	private String campo = null;

	public DatiOccupati() {
		super("Dati già in uso");
	}

	public DatiOccupati(String message) {
		super("Dati già in uso: " + ottieniCampo(message));
		this.campo = ottieniCampo(message);
		/* TODO: qui le cose si ripetono due volte, fa brutto ma è l'unico modo */
	}

	private static String ottieniCampo(String str) {
		String msg = str.split("'")[3].trim();
		if(msg.equals("PRIMARY_KEY")) msg = "nickname";
		if(msg.equals("email_UNIQUE")) msg = "email";
		return msg;
	}

	public String getCampo() {
		return campo;
	}

	public void setCampo(String campo) {
		this.campo = campo;
	}


}
