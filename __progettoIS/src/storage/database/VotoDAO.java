package storage.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import applicationLogic.bean.Recensione;
import applicationLogic.bean.Utente;
import applicationLogic.bean.Voto;

/**
 * DAO che implementa i metodi <i>CRUD</i> per gli oggetti Voto
 * 
 * @author Luca
 *
 */
public class VotoDAO {
	private static final String SELECT_BY_REVIEW = "SELECT * FROM `voti_recensioni` WHERE `idRecensione`=?";
	private static final String UPDATE_BY_ID = "UPDATE `voti_recensioni` SET `voto`=? WHERE `id_voto`=?";
	private static final String INSERT = "INSERT INTO `voti_recensioni` (`idRecensione`, `usernameUtente`, `voto`) VALUES (?, ?, ?)";
	private static final String DELETE = "DELETE FROM `voti_recensioni` WHERE `id_voto`=?";

	/**
	 * Seleziona tutti i voti di una specifica recensione dal database
	 * 
	 * @param r
	 *            un oggetto Recensione con un id
	 * @return una lista di voti per la recensione in questione
	 * @throws SQLException
	 *             nel caso in cui la query non vada a buon fine
	 */
	public static ArrayList<Voto> selectByIdReview(Recensione r) throws SQLException {
		Connection con = DBConnection.ottieniConnessione();
		PreparedStatement pst = con.prepareStatement(SELECT_BY_REVIEW);
		pst.setInt(1, r.getId());

		ResultSet rs = pst.executeQuery();
		con.commit();

		ArrayList<Voto> listaVoti = new ArrayList<>();
		while (rs.next()) {
			Voto v = new Voto();

			Utente u = new Utente();
			u.setUsername(rs.getString("usernameUtente"));

			v.setUtente(u);
			v.setId(rs.getInt("id_voto"));
			v.setVoto(rs.getInt("voto"));

			listaVoti.add(v);
		}
		DBConnection.riaggiungiConnessione(con);
		return listaVoti;
	}

	/**
	 * Aggiorna un voto nel database
	 * 
	 * @param v
	 *            un oggetto Voto con un id e il nuovo voto
	 * @return l'oggetto voto inserito
	 * @throws SQLException
	 *             nel caso in cui la query non vada a buon fine
	 */
	public static Voto updateById(Voto v) throws SQLException {
		Connection con = DBConnection.ottieniConnessione();
		PreparedStatement pst = con.prepareStatement(UPDATE_BY_ID);
		pst.setInt(1, v.getVoto());
		pst.setInt(2, v.getId());

		pst.executeUpdate();
		con.commit();

		DBConnection.riaggiungiConnessione(con);
		return v;
	}

	/**
	 * Inserisce il nuovo voto nel database
	 * 
	 * @param v
	 *            oggetto Voto da inserire con un id ed un utente
	 * @param r
	 *            oggetto Recensione con un id
	 * @return il voto inserito
	 * @throws SQLException
	 *             nel caso in cui la query non vada a buon fine
	 */
	public static Voto insert(Voto v, Recensione r) throws SQLException {
		Connection con = DBConnection.ottieniConnessione();
		PreparedStatement pst = con.prepareStatement(INSERT, PreparedStatement.RETURN_GENERATED_KEYS);
		pst.setInt(1, r.getId());
		pst.setString(2, v.getUtente().getUsername());
		pst.setInt(3, v.getVoto());

		pst.executeUpdate();
		ResultSet rs = pst.getGeneratedKeys();

		con.commit();

		int id = -1;
		if (rs.next()) {
			id = rs.getInt(1);
		}
		DBConnection.riaggiungiConnessione(con);

		if (id != -1) {
			v.setId(id);
		}
		return v;
	}

	/**
	 * Rimuove il voto dal database
	 * 
	 * @param v
	 *            un oggetto voto con un id
	 * @throws SQLException
	 *             nel caso in cui la query non vada a buon fine
	 */
	public static void remove(Voto v) throws SQLException {
		Connection con = DBConnection.ottieniConnessione();
		PreparedStatement pst = con.prepareStatement(DELETE);
		pst.setInt(1, v.getId());

		pst.executeUpdate();
		con.commit();

		DBConnection.riaggiungiConnessione(con);
	}
}
