package test.dao;

import java.sql.SQLException;
import java.util.ArrayList;

import org.junit.Test;

import applicationLogic.bean.FilmRemote;
import storage.database.FilmLocalDAO;

public class FilmLocalDAOTester {
	public FilmRemote input = new FilmRemote(1, "Titolo Film", "Titolo Originale", "locandina", new ArrayList<String>(),
			"it", "descrizione", "1997-13-06", 10.0D, 10, "backdrop");
	
	@Test
	public void inserisciSelezionaFilm() throws SQLException {
		FilmLocalDAO.insert(input);
		
		assert(input.equals(FilmLocalDAO.select(input)));
		
		FilmLocalDAO.delete(input);
	}
	
	@Test
	public void rimuoviFilm() throws SQLException {
		try {
			FilmLocalDAO.insert(input);
		} catch (SQLException e) {
			System.out.println("Errore durante l'inserimento");
		}

		FilmLocalDAO.delete(input);
	}
}
