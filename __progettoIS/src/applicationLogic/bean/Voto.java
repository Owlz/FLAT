package applicationLogic.bean;

/**
 * Classe che modella i voti alle recensioni
 * 
 * @author Luca
 *
 */
public class Voto {
	private int id;
	private int voto;
	private Utente utente;

	public Voto() {
	}

	/**
	 * Costruttore completo
	 * 
	 * @param id
	 *            del voto (-1 se non si conosce)
	 * @param voto
	 *            della recensione (-1 o +1)
	 * @param utente
	 *            che ha votato
	 */
	public Voto(int id, int voto, Utente utente) {
		this.id = id;
		this.voto = voto;
		this.utente = utente;
	}

	public int getId() {
		return id;
	}

	public int getVoto() {
		return voto;
	}

	public Utente getUtente() {
		return utente;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setVoto(int voto) {
		this.voto = voto;
	}

	public void setUtente(Utente utente) {
		this.utente = utente;
	}

	@Override
	public String toString() {
		return "Voto [id=" + id + ", voto=" + voto + ", " + (utente != null ? "utente=" + utente : "") + "]";
	}

	@Override
	public boolean equals(Object o) {
		if (o == null)
			return false;
		if (!(o instanceof Voto))
			return false;
		if (o == this)
			return true;

		Voto v = (Voto) o;
		if (this.id == v.getId())
			return true;
		else
			return false;
	}
}
