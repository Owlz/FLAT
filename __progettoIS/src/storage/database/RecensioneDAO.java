package storage.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import applicationLogic.models.Film;
import applicationLogic.models.Recensione;
import applicationLogic.models.Utente;

public class RecensioneDAO {
	private static final String GET_RECENSIONE_BY_UTENTE = "SELECT * FROM `recensioni` WHERE `usernameUtente`=?";
	private static final String GET_RECENSIONE_BY_FILM = "SELECT * FROM `recensioni` WHERE `idFilm`=?";
	private static final String GET_RECENSIONE_BY_FILM_AND_UTENTE = "SELECT * FROM `recensioni` WHERE `idFilm`=? AND `usernameUtente`=?";
	private static final String INSERT_UTENTE = "INSERT INTO utenti (`username`, `password`, `nome`, `cognome`, `email`, `ruolo`) VALUES (?, ?, ?, ?, ?, ?)";
	private static final String DELETE_RECENSIONE = "DELETE FROM `recensioni` WHERE `idFilm`=? AND`usernameUtente`=?";

	public static ArrayList<Recensione> getRecensioni(Utente u) throws SQLException {
		Connection con = DBConnection.ottieniConnessione();
		PreparedStatement pst = con.prepareStatement(GET_RECENSIONE_BY_UTENTE);
		pst.setString(1, u.getUsername());
		
		ResultSet rs = pst.executeQuery();
		con.commit();

		ArrayList<Recensione> listaRec = new ArrayList<Recensione>();

		while (rs.next()) {
			int idFilm = rs.getInt("idFilm");
			Film f = new Film();
			f.setId(idFilm);
			
			int voto = rs.getInt("voto_recensione");
			String titolo = rs.getString("titolo_recensione");
			String recensione = rs.getString("testo_recensione");
		
			Recensione r = new Recensione(u, f, voto, titolo, recensione);
			listaRec.add(r);
		}

		DBConnection.riaggiungiConnessione(con);
		return listaRec;
	}

	public static ArrayList<Recensione> getRecensioni(Film f) throws SQLException {
		Connection con = DBConnection.ottieniConnessione();
		PreparedStatement pst = con.prepareStatement(GET_RECENSIONE_BY_FILM);
		
		pst.setInt(1, f.getId());
		
		ResultSet rs = pst.executeQuery();
		con.commit();

		ArrayList<Recensione> listaRec = new ArrayList<Recensione>();
		
		while (rs.next()) {
			String username = rs.getString("usernameUtente");
			Utente u = new Utente();
			u.setUsername(username);
			
			int voto = rs.getInt("voto_recensione");
			String titolo = rs.getString("titolo_recensione");
			String recensione = rs.getString("testo_recensione");
		
			Recensione r = new Recensione(u, f, voto, titolo, recensione);
			listaRec.add(r);
		}

		DBConnection.riaggiungiConnessione(con);
		return listaRec;
	}
	
	public static Recensione getRecensione(Utente u, Film f) throws SQLException {
		Connection con = DBConnection.ottieniConnessione();
		PreparedStatement pst = con.prepareStatement(GET_RECENSIONE_BY_FILM_AND_UTENTE);
		pst.setInt(1, f.getId());
		pst.setString(2, u.getUsername());
		
		ResultSet rs = pst.executeQuery();
		con.commit();

		Recensione r = null;

		while (rs.next()) {
			int voto = rs.getInt("voto_recensione");
			String titolo = rs.getString("titolo_recensione");
			String recensione = rs.getString("testo_recensione");
		
			r = new Recensione(u, f, voto, titolo, recensione);
		}

		DBConnection.riaggiungiConnessione(con);
		return r;
	}

	public static Recensione inserisciRecensione(Recensione r) throws SQLException {
		Connection con = DBConnection.ottieniConnessione();
		PreparedStatement pst = con.prepareStatement(INSERT_UTENTE);
		pst.setString(1, r.getUtente().getUsername());
		pst.setInt(2, r.getFilm().getId());
		pst.setInt(3, r.getVoto());
		pst.setString(4, r.getTitolo());
		pst.setString(4, r.getRecensione());
		
		pst.executeUpdate();
		con.commit();
		
		DBConnection.riaggiungiConnessione(con);
		
		return r;
	}

	public static void eliminaRecensione(Recensione r) throws SQLException {
		Connection con = DBConnection.ottieniConnessione();
		PreparedStatement pst = con.prepareStatement(DELETE_RECENSIONE);
		pst.setInt(1, r.getFilm().getId());
		pst.setString(2, r.getUtente().getUsername());

		pst.executeUpdate();
		con.commit();

		DBConnection.riaggiungiConnessione(con);
	}

}
