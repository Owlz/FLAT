package storage.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import applicationLogic.bean.Film;
import applicationLogic.bean.FilmLocal;
import applicationLogic.bean.FilmRemote;

public class FilmLocalDAO {
	private static final String INSERT = "INSERT INTO `films` (`id`, `titolo`, `locandina`, `titolo_orig`) VALUES (?, ?, ?, ?);";
	private static final String SELECT = "SELECT * FROM `films` WHERE `id`=?";
	private static final String DELETE = "DELETE FROM `films` WHERE `id`=?";
	
	public static void insert(FilmRemote fOut) throws SQLException {
		Connection con = DBConnection.ottieniConnessione();
		PreparedStatement pst = con.prepareStatement(INSERT);
		pst.setInt(1, fOut.getId());
		pst.setString(2, fOut.getTitolo());
		pst.setString(3, fOut.getLocandina());
		pst.setString(4, fOut.getTitoloOriginale());

		pst.executeUpdate();
		con.commit();

		DBConnection.riaggiungiConnessione(con);
	}

	public static FilmLocal select(Film f) throws SQLException {
		Connection con = DBConnection.ottieniConnessione();
		PreparedStatement pst = con.prepareStatement(SELECT);
		pst.setInt(1, f.getId());
		ResultSet rs = pst.executeQuery();
		con.commit();
	
		FilmLocal fl = new FilmLocal();
		while (rs.next()) {
			fl = new FilmLocal();
			fl.setId(rs.getInt("id"));
			fl.setLocandina(rs.getString("locandina"));
			fl.setTitolo(rs.getString("titolo"));
			fl.setTitoloOriginale(rs.getString("titolo_orig"));
		}

		DBConnection.riaggiungiConnessione(con);
		return fl;
	}

	public static void delete(Film f) throws SQLException {
		Connection con = DBConnection.ottieniConnessione();
		PreparedStatement pst = con.prepareStatement(DELETE);
		pst.setInt(1, f.getId());

		pst.executeUpdate();
		con.commit();

		DBConnection.riaggiungiConnessione(con);
		
	}
}
