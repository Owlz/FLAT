package storage.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import applicationLogic.models.Film;
import applicationLogic.models.Utente;

public class WatchlistDAO {
	private static final String INSERT_FILM_WATCHLIST = "INSERT INTO `watch` (`username_utente`, `id_film`) VALUES (?, ?)";
	private static final String REMOVE_FILM_WATCHLIST = "DELETE FROM `watch` WHERE `username_utente`=? AND `id_film`=?";
	private static final String GET_WATCHLIST_UTENTE = "SELECT * FROM `watch` WHERE `username_utente`=?";

	public static void inserisciFilmWatchlist(Film f, Utente u) throws SQLException {
		Connection con = DBConnection.ottieniConnessione();
		PreparedStatement pst = con.prepareStatement(INSERT_FILM_WATCHLIST);
		pst.setString(1, u.getUsername());
		pst.setInt(2, f.getId());

		pst.executeUpdate();
		con.commit();

		DBConnection.riaggiungiConnessione(con);
	}

	public static void rimuoviFilmWatchlist(Film f, Utente u) throws SQLException {
		Connection con = DBConnection.ottieniConnessione();
		PreparedStatement pst = con.prepareStatement(REMOVE_FILM_WATCHLIST);
		pst.setString(1, u.getUsername());
		pst.setInt(2, f.getId());

		pst.executeUpdate();
		con.commit();

		DBConnection.riaggiungiConnessione(con);
	}

	public static ArrayList<Film> getWatchlistUtente(Utente u) throws SQLException {
		Connection con = DBConnection.ottieniConnessione();
		PreparedStatement pst = con.prepareStatement(GET_WATCHLIST_UTENTE);
		pst.setString(1, u.getUsername());
		ResultSet rs = pst.executeQuery();
		con.commit();

		ArrayList<Film> lista = new ArrayList<>();
		while (rs.next()) {
			Film f = new Film();
			f.setId(rs.getInt("id_film"));
			
			lista.add(f);
		}

		DBConnection.riaggiungiConnessione(con);
		return lista;
	}

}
