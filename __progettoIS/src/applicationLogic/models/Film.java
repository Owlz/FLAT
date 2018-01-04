package applicationLogic.models;

import java.util.ArrayList;

public class Film {
	private int id;
	private String titolo;
	private String titoloOriginale;
	private String locandina;
	private ArrayList<String> generi;
	private String linguaOriginale;
	private String descrizione;
	private String dataDiUscita;
	private double voto;
	private int numeroVoti;
	private String backdrop;
	
	public Film(){}
	
	public Film(int id, String titolo, String titoloOriginale, String locandina) {
		this.id = id;
		this.titolo = titolo;
		this.titoloOriginale = titoloOriginale;
		this.locandina = locandina;
	}

	public Film(int id, String titolo, String titoloOriginale, String locandina, ArrayList<String> generi,
			String linguaOriginale, String descrizione, String dataDiUscita, double voto, int numeroVoti,
			String backdrop) {
		this.id = id;
		this.titolo = titolo;
		this.titoloOriginale = titoloOriginale;
		this.locandina = locandina;
		this.generi = generi;
		this.linguaOriginale = linguaOriginale;
		this.descrizione = descrizione;
		this.dataDiUscita = dataDiUscita;
		this.voto = voto;
		this.numeroVoti = numeroVoti;
		this.backdrop = backdrop;
	}

	public int getId() { return id; }
	public String getTitolo() { return titolo; }
	public String getTitoloOriginale() { return titoloOriginale; }
	public String getLocandina() { return locandina; }
	public ArrayList<String> getGeneri() { return generi; }
	public String getLinguaOriginale() { return linguaOriginale; }
	public String getDescrizione() { return descrizione; }
	public String getDataDiUscita() { return dataDiUscita; }
	public double getVoto() { return voto; }
	public int getNumeroVoti() { return numeroVoti;}
	public String getBackdrop() { return backdrop; }

	public void setId(int id) { this.id = id; }
	public void setTitolo(String titolo) { this.titolo = titolo; }
	public void setTitoloOriginale(String titoloOriginale) { this.titoloOriginale = titoloOriginale; }
	public void setLocandina(String locandina) { this.locandina = locandina; }
	public void setGeneri(ArrayList<String> generi) { this.generi = generi; }
	public void setLinguaOriginale(String linguaOriginale) { this.linguaOriginale = linguaOriginale; }
	public void setDescrizione(String descrizione) { this.descrizione = descrizione; }
	public void setDataDiUscita(String dataDiUscita) { this.dataDiUscita = dataDiUscita; }
	public void setVoto(double voto) { this.voto = voto; }
	public void setNumeroVoti(int numeroVoti) { this.numeroVoti = numeroVoti; }
	public void setBackdrop(String backdrop) { this.backdrop = backdrop; }

	@Override
	public String toString() {
		return "Film [id=" + id + ", " + (titolo != null ? "titolo=" + titolo + ", " : "")
				+ (titoloOriginale != null ? "titoloOriginale=" + titoloOriginale + ", " : "")
				+ (locandina != null ? "locandina=" + locandina + ", " : "")
				+ (generi != null ? "generi=" + generi + ", " : "")
				+ (linguaOriginale != null ? "linguaOriginale=" + linguaOriginale + ", " : "")
				+ (descrizione != null ? "descrizione=" + descrizione + ", " : "")
				+ (dataDiUscita != null ? "dataDiUscita=" + dataDiUscita + ", " : "") + "voto=" + voto + ", numeroVoti="
				+ numeroVoti + ", " + (backdrop != null ? "backdrop=" + backdrop : "") + "]";
	}
	
	@Override
	public boolean equals(Object o){
	    if (o == null) return false;
	    if (!(o instanceof Film)) return false;
	    if (o == this) return true;
	    
	    Film f = (Film) o;
	    if (this.id == f.getId()) return true;
	    else return false;
	}	
}
