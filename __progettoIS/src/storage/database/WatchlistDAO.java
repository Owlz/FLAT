package storage.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import applicationLogic.bean.Film;
import applicationLogic.bean.FilmLocal;
import applicationLogic.bean.Utente;

public class WatchlistDAO {
	private static final String INSERT = "INSERT INTO `watch` (`username_utente`, `id_film`) VALUES (?, ?)";
	private static final String REMOVE = "DELETE FROM `watch` WHERE `username_utente`=? AND `id_film`=?";
	private static final String SELECT = "SELECT * FROM `watch` WHERE `username_utente`=?";

	public static void insert(Film f, Utente u) throws SQLException {
		Connection con = DBConnection.ottieniConnessione();
		PreparedStatement pst = con.prepareStatement(INSERT);
		pst.setString(1, u.getUsername());
		pst.setInt(2, f.getId());

		pst.executeUpdate();
		con.commit();

		DBConnection.riaggiungiConnessione(con);
	}

	public static void removeFilm(Film f, Utente u) throws SQLException {
		Connection con = DBConnection.ottieniConnessione();
		PreparedStatement pst = con.prepareStatement(REMOVE);
		pst.setString(1, u.getUsername());
		pst.setInt(2, f.getId());

		pst.executeUpdate();
		con.commit();

		DBConnection.riaggiungiConnessione(con);
	}

	public static ArrayList<Film> selectByUser(Utente u) throws SQLException {
		Connection con = DBConnection.ottieniConnessione();
		PreparedStatement pst = con.prepareStatement(SELECT);
		pst.setString(1, u.getUsername());
		ResultSet rs = pst.executeQuery();
		con.commit();

		ArrayList<Film> lista = new ArrayList<>();
		while (rs.next()) {
			Film f = new FilmLocal();
			f.setId(rs.getInt("id_film"));
			
			lista.add(f);
		}

		DBConnection.riaggiungiConnessione(con);
		return lista;
	}

}
