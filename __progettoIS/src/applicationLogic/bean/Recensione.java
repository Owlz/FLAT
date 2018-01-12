package applicationLogic.bean;

public class Recensione {
	private Utente utente;
	private Film film;
	private int voto;
	private String titolo;
	private String recensione;
	
	public Recensione() {}

	public Recensione(Utente utente, Film film, int voto, String titolo, String recensione) {
		this.utente = utente;
		this.film = film;
		this.voto = voto;
		this.titolo = titolo;
		this.recensione = recensione;
	}

	public Recensione(Utente u, Film f) {
		this.utente = u;
		this.film = f;
	}

	public Utente getUtente() { return utente; }
	public Film getFilm() { return film; }
	public int getVoto() { return voto; }
	public String getTitolo() { return titolo; }
	public String getRecensione() { return recensione; }
	
	public void setUtente(Utente utente) { this.utente = utente; }
	public void setFilm(Film film) { this.film = film; }
	public void setVoto(int voto) { this.voto = voto; }
	public void setTitolo(String titolo) { this.titolo = titolo; }
	public void setRecensione(String recensione) { this.recensione = recensione; }
	
	@Override
	public String toString() {
		return "Recensione [" + (utente != null ? "utente=" + utente + ", " : "")
				+ (film != null ? "film=" + film + ", " : "") + "voto=" + voto + ", "
				+ (titolo != null ? "titolo=" + titolo + ", " : "")
				+ (recensione != null ? "recensione=" + recensione : "") + "]";
	}

	@Override
	public boolean equals(Object o){
	    if (o == null) return false;
	    if (!(o instanceof Recensione)) return false;
	    if (o == this) return true;
	    
	    Recensione r = (Recensione) o;
	    if (this.film.getId() == r.getFilm().getId() &&
	    	this.utente.getUsername().equals(r.getUtente().getUsername())) return true;
	    else return false;
	}	
}
