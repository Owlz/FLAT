package storage.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import applicationLogic.models.Film;

public class FilmDAO {
	private static final String INSERT_FILM = "INSERT INTO `films` (`id`, `titolo`, `locandina`, `titolo_orig`) VALUES (?, ?, ?, ?);";
	private static final String GET_FILM = "SELECT * FROM `films` WHERE `id`=?";
	
	public static Film inserisciFilm(Film f) throws SQLException {
		Connection con = DBConnection.ottieniConnessione();
		PreparedStatement pst = con.prepareStatement(INSERT_FILM);
		pst.setInt(1, f.getId());
		pst.setString(2, f.getTitolo());
		pst.setString(3, f.getLocandina());
		pst.setString(4, f.getTitoloOriginale());

		pst.executeUpdate();
		con.commit();

		DBConnection.riaggiungiConnessione(con);
		
		return f;
	}

	public static Film getFilm(Film f) throws SQLException {
		Connection con = DBConnection.ottieniConnessione();
		PreparedStatement pst = con.prepareStatement(GET_FILM);
		pst.setInt(1, f.getId());
		ResultSet rs = pst.executeQuery();
		con.commit();
	
		while (rs.next()) {
			f = new Film();
			f.setId(rs.getInt("id"));
			f.setLocandina(rs.getString("locandina"));
			f.setTitolo(rs.getString("titolo"));
			f.setTitoloOriginale(rs.getString("titolo_orig"));
		}

		DBConnection.riaggiungiConnessione(con);
		return f;
	}
}
