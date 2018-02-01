package applicationLogic.bean;

/**
 * Classe che modella gli <strong>Utenti</strong> del sistema È in questa classe
 * che teniamo conto dell'utente che sta utilizzando la piattaforma, con tutte
 * le informazioni del caso (nome, ruolo, etc)
 * 
 * @author Luca
 * @since 1.0
 */
public class Utente {
	private String nome;
	private String cognome;
	private String username;
	private String password;
	private String email;
	private String ruolo = "visitatore"; // visitatore, utente, moderatore.

	/**
	 * Costruttore vuoto
	 */
	public Utente() {
	}

	/**
	 * Costruttore completo
	 * 
	 * @param nome
	 *            dell'utente
	 * @param cognome
	 *            dell'utente
	 * @param username
	 *            dell'utente
	 * @param password
	 *            dell'utente
	 * @param email
	 *            dell'utente
	 * @param ruolo
	 *            dell'utente (può essere uno solo fra "visitatore", "utente" e
	 *            "moderatore")
	 */
	public Utente(String nome, String cognome, String username, String password, String email, String ruolo) {
		this.nome = nome;
		this.cognome = cognome;
		this.username = username;
		this.password = password;
		this.email = email;
		this.ruolo = ruolo;
	}

	public Utente(String username) {
		this.username = username;
	}

	public String getNome() {
		return nome;
	}

	public String getCognome() {
		return cognome;
	}

	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}

	public String getEmail() {
		return email;
	}

	public String getRuolo() {
		return ruolo;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public void setCognome(String cognome) {
		this.cognome = cognome;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setRuolo(String ruolo) {
		this.ruolo = ruolo;
	}

	@Override
	public String toString() {
		return "Utente [" + (nome != null ? "nome=" + nome + ", " : "")
				+ (cognome != null ? "cognome=" + cognome + ", " : "")
				+ (username != null ? "username=" + username + ", " : "")
				+ (password != null ? "password=" + password + ", " : "")
				+ (email != null ? "email=" + email + ", " : "") + (ruolo != null ? "ruolo=" + ruolo : "") + "]";
	}

	/**
	 * Equals utilizzato per testare se l'username coincide È tutto ciò di cui
	 * abbiamo bisogno nella piattaforma per testare l'eguaglianza fra classi
	 * Utente.
	 * 
	 * @see equalsComplete per la versione complea
	 * @param o
	 *            da testare con l'attuale utente
	 * @return true se è uguale, false altrimenti
	 */
	@Override
	public boolean equals(Object o) {
		if (o == null)
			return false;
		if (!(o instanceof Utente))
			return false;
		if (o == this)
			return true;

		Utente u = (Utente) o;
		if (this.username.equals(u.getUsername()))
			return true;
		else
			return false;
	}

	/**
	 * Equals utilizzato per il testing dell'intera classe
	 * 
	 * @param obj
	 *            da testare con l'attuale utente
	 * @return true se è uguale, false altrimenti
	 */
	public boolean equalsComplete(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Utente))
			return false;
		Utente other = (Utente) obj;

		if (cognome == null) {
			if (other.cognome != null)
				return false;
		} else if (!cognome.equals(other.cognome))
			return false;

		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;

		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;

		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;

		if (ruolo == null) {
			if (other.ruolo != null)
				return false;
		} else if (!ruolo.equals(other.ruolo))
			return false;

		if (username == null) {
			if (other.username != null)
				return false;
		} else if (!username.equals(other.username))
			return false;

		return true;
	}

}
