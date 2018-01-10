package applicationLogic.bean;

import java.util.ArrayList;

public class FilmRemote extends Film {
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
	
	public FilmRemote(){
		super();
	}
	
	public FilmRemote(int id){
		super(id);
	}
	
	public FilmRemote(int id, String titolo, String titoloOriginale, String locandina, ArrayList<String> generi,
			String linguaOriginale, String descrizione, String dataDiUscita, double voto, int numeroVoti,
			String backdrop) {
		super(id);
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
	
	public static Film generateByStringId(String idString){
		Integer id = null;
		try{
			id = Integer.parseInt(idString);
		}catch(NullPointerException | NumberFormatException e){}
		
		if(id != null) return new FilmRemote(id);
		else return null;
	}

	@Override
	public String toString() {
		return getClass().getName() + " [id = "+ getId() + ", " + (titolo != null ? "titolo=" + titolo + ", " : "")
				+ (titoloOriginale != null ? "titoloOriginale=" + titoloOriginale + ", " : "")
				+ (locandina != null ? "locandina=" + locandina + ", " : "")
				+ (generi != null ? "generi=" + generi + ", " : "")
				+ (linguaOriginale != null ? "linguaOriginale=" + linguaOriginale + ", " : "")
				+ (descrizione != null ? "descrizione=" + descrizione + ", " : "")
				+ (dataDiUscita != null ? "dataDiUscita=" + dataDiUscita + ", " : "") + "voto=" + voto + ", numeroVoti="
				+ numeroVoti + ", " + (backdrop != null ? "backdrop=" + backdrop + ", " : "") + "]";
	}

	
	
	
}
