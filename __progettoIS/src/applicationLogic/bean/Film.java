package applicationLogic.bean;

/**
 * Classe astratta per gestire i film
 * 
 * @author Luca
 *
 */
public abstract class Film {
	private int id;

	public Film() {
	}

	public Film(int id) {
		this.id = id;
	}

	/**
	 * Ritorna l'id del film
	 * 
	 * @return l'id del film
	 */
	public int getId() {
		return id;
	}

	/**
	 * Modifica l'id del film
	 * 
	 * @param id
	 *            del film
	 */
	public void setId(int id) {
		this.id = id;
	}

	public abstract String toString();

	@Override
	public boolean equals(Object o) {
		if (o == null)
			return false;
		if (!(o instanceof Film))
			return false;
		if (o == this)
			return true;

		Film f = (Film) o;
		if (this.id == f.getId())
			return true;
		else
			return false;
	}
}
