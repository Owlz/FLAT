package applicationLogic.bean;

public abstract class Film {
	private int id;
	
	public Film(){}
	
	public Film(int id){
		this.id = id;
	}
	
	public int getId() { return id; }
	public void setId(int id) { this.id = id; }

	public abstract String toString();
	
	
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
