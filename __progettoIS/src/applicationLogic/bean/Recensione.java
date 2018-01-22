package applicationLogic.bean;

import java.util.ArrayList;
import java.util.Comparator;

public class Recensione {
	private int id;
	private Film film;
	private Utente utente;	
	private int voto;
	private String titolo;
	private String testo;	
	private ArrayList<Voto> voti;
	private boolean segnalata;
	
	public static final Comparator<Recensione> COMP_BY_DATA = new Comparator<Recensione>() {
		@Override
		public int compare(Recensione o1, Recensione o2) {
			return o2.getId() - o1.getId();
		}
	};
	public static final Comparator<Recensione> COMP_BY_SEGNALATA = new Comparator<Recensione>() {
		@Override
		public int compare(Recensione o1, Recensione o2) {
			if(o1.isSegnalata() && o2.isSegnalata()) return 0;
			else if(o1.isSegnalata() && !o2.isSegnalata()) return -1;
			else return 1;
		}
	};

	public static final Comparator<Recensione> COMP_BY_VOTI_NEGATIVI = new Comparator<Recensione>() {
		@Override
		public int compare(Recensione o1, Recensione o2) {
			return o1.getVotiTotali() - o2.getVotiTotali();
		}
	};
	
	public static final Comparator<Recensione> COMP_BY_VOTI_POSITIVI = new Comparator<Recensione>() {
		@Override
		public int compare(Recensione o1, Recensione o2) {
			return o2.getVotiTotali() - o1.getVotiTotali();
		}
	};
	
	public Recensione() {}
	
	public Recensione(int id) {
		this.id = id;
	}
	
	public Recensione(int id, Film film, Utente utente, int voto, String titolo, String testo, ArrayList<Voto> voti, boolean segnalata) {
		this.id = id;
		this.film = film;
		this.utente = utente;
		this.voto = voto;
		this.titolo = titolo;
		this.testo = testo;
		this.voti = voti;
		this.segnalata = segnalata;
	}
	
	public int getId() { return id; }
	public Film getFilm() { return film; }
	public Utente getUtente() { return utente; }
	public int getVoto() { return voto; }
	public String getTitolo() { return titolo; }
	public String getTesto() { return testo; }
	public ArrayList<Voto> getVoti() { return voti; }
	public boolean isSegnalata() { return segnalata; }

	public void setId(int id) { this.id = id; }
	public void setFilm(Film film) { this.film = film; }
	public void setUtente(Utente utente) { this.utente = utente; }
	public void setVoto(int voto) { this.voto = voto; }
	public void setTitolo(String titolo) { this.titolo = titolo; }
	public void setTesto(String testo) { this.testo = testo; }
	public void setVoti(ArrayList<Voto> voti) { this.voti = voti; }
	public void setSegnalata(boolean segnalata) { this.segnalata = segnalata; }

	public int getVotiTotali(){
		int y = 0;
		for(Voto x: this.voti){
			y = y + x.getVoto();
		}
		return y;
	}
	
	@Override
	public String toString() {
		return "Recensione [id=" + id + ", " + (film != null ? "film=" + film + ", " : "")
				+ (utente != null ? "utente=" + utente + ", " : "") + "voto=" + voto + ", "
				+ (titolo != null ? "titolo=" + titolo + ", " : "") + (testo != null ? "testo=" + testo + ", " : "")
				+ (voti != null ? "voti=" + voti + ", " : "") + "segnalata=" + segnalata + "]";
	}

	@Override
	public boolean equals(Object o){
	    if (o == null) return false;
	    if (!(o instanceof Recensione)) return false;
	    if (o == this) return true;
	    
	    Recensione r = (Recensione) o;
	    if (this.film.getId() == r.getFilm().getId() &&
	    	this.utente.getUsername().equals(r.getUtente().getUsername())) return true;
	    else if (this.id == r.getId()) return true;
	    else return false;
	}

	public static Recensione generateByStringId(String idRecensione) {
		Integer id = null;
		try{
			id = Integer.parseInt(idRecensione);
		}catch(NullPointerException | NumberFormatException e){}
		
		if(id != null) return new Recensione(id);
		else return null;
	}
}
