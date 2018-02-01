package storage.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import applicationLogic.bean.Film;
import applicationLogic.bean.FilmLocal;
import applicationLogic.bean.FilmRemote;

/**
 * DAO che implementa i metodi <i>CRUD</i> per gli oggetti FilmLocal
 * 
 * @author Luca
 *
 */
public class FilmLocalDAO {
	private static final String INSERT = "INSERT INTO `films` (`id`, `titolo`, `locandina`, `titolo_orig`) VALUES (?, ?, ?, ?);";
	private static final String SELECT = "SELECT * FROM `films` WHERE `id`=?";
	private static final String DELETE = "DELETE FROM `films` WHERE `id`=?";

	/**
	 * Inserisce un oggetto di tipo FilmRemote nel database
	 * 
	 * @param fOut
	 *            un oggetto di tipo FilmRemote con id, titolo, locandina e
	 *            titolo in lingua originale
	 * @throws SQLException
	 *             nel caso in cui la query non vada a buon fine
	 */
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

	/**
	 * Seleziona un oggetto di tipo FilmLocale dal database
	 * 
	 * @param f
	 *            un oggetto Film con un id
	 * @return un oggetto FilmLocal con tutte le informazioni presenti sul
	 *         database locale
	 * @throws SQLException
	 *             nel caso in cui la query non vada a buon fine
	 */
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

	/**
	 * Elimina un oggetto Film dal database
	 * 
	 * @param f
	 *            un oggetto Film con un id
	 * @throws SQLException
	 *             nel caso in cui la query non vada a buon fine
	 */
	public static void delete(Film f) throws SQLException {
		Connection con = DBConnection.ottieniConnessione();
		PreparedStatement pst = con.prepareStatement(DELETE);
		pst.setInt(1, f.getId());

		pst.executeUpdate();
		con.commit();

		DBConnection.riaggiungiConnessione(con);

	}
}
