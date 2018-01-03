package storage.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import applicationLogic.models.Film;

public class FilmDAO {
	private static final String INSERT_UTENTE = "INSERT INTO `films` (`id`, `titolo`, `locandina`, `titolo_orig`) VALUES (?, ?, ?, ?);";
	
	public static Film inserisciUtente(Film film) throws SQLException {
		Connection con = DBConnection.ottieniConnessione();
		PreparedStatement pst = con.prepareStatement(INSERT_UTENTE);
		pst.setInt(1, film.getId());
		pst.setString(2, film.getTitolo());
		pst.setString(3, film.getLocandina());
		pst.setString(4, film.getTitoloOriginale());

		pst.executeUpdate();
		con.commit();

		DBConnection.riaggiungiConnessione(con);
		
		return film;
	}

}
