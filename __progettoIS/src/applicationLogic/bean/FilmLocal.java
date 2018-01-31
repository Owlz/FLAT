package applicationLogic.bean;

/**
 * Implementazione della classe Film per gestire i film ottenuti dal database
 * locale
 * 
 * @author Luca
 *
 */
public class FilmLocal extends Film {
	private String titolo;
	private String titoloOriginale;
	private String locandina;

	/**
	 * Costruttore vuoto
	 */
	public FilmLocal() {
		super();
	}

	/**
	 * Costruttore con solo l'id
	 * 
	 * @param id
	 *            del film
	 */
	public FilmLocal(int id) {
		super(id);
	}

	/**
	 * Costruttore completo
	 * 
	 * @param id
	 *            del film
	 * @param titolo
	 *            del film
	 * @param titoloOriginale
	 *            del film
	 * @param locandina
	 *            del film
	 */
	public FilmLocal(int id, String titolo, String titoloOriginale, String locandina) {
		super(id);
		this.titolo = titolo;
		this.titoloOriginale = titoloOriginale;
		this.locandina = locandina;
	}

	public String getTitolo() {
		return titolo;
	}

	public String getTitoloOriginale() {
		return titoloOriginale;
	}

	public String getLocandina() {
		return locandina;
	}

	public void setTitolo(String titolo) {
		this.titolo = titolo;
	}

	public void setTitoloOriginale(String titoloOriginale) {
		this.titoloOriginale = titoloOriginale;
	}

	public void setLocandina(String locandina) {
		this.locandina = locandina;
	}

	/**
	 * Permette di generare un oggetto di tipo FilmLocal da una stringa
	 * contentente l'id
	 * 
	 * @param idString
	 *            la stringa con l'id del film
	 * @return un oggettto film con solo l'id dentro
	 */
	public static Film generateByStringId(String idString) {
		Integer id = null;
		try {
			id = Integer.parseInt(idString);
		} catch (NullPointerException | NumberFormatException e) {
		}

		if (id != null)
			return new FilmLocal(id);
		else
			return null;
	}

	@Override
	public String toString() {
		return getClass().getName() + " [id = " + getId() + ", " + (titolo != null ? "titolo=" + titolo + ", " : "")
				+ (titoloOriginale != null ? "titoloOriginale=" + titoloOriginale + ", " : "")
				+ (locandina != null ? "locandina=" + locandina : "") + "]";
	}

}
