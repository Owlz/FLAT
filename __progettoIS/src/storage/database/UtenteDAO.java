package storage.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import applicationLogic.bean.Utente;

public class UtenteDAO {
	private static final String SELECT = "SELECT * FROM utenti WHERE username = ?";
	private static final String INSERT = "INSERT INTO utenti (`username`, `password`, `nome`, `cognome`, `email`, `ruolo`) VALUES (?, ?, ?, ?, ?, ?)";
	private static final String UPDATE = "UPDATE utenti SET `password` = ?, `nome` = ?, `cognome` = ?, `email` = ? WHERE `username` = ?";

	public static Utente insert(Utente utente) throws SQLException {
		Connection con = DBConnection.ottieniConnessione();
		PreparedStatement pst = con.prepareStatement(INSERT);
		pst.setString(1, utente.getUsername());
		pst.setString(2, utente.getPassword());
		pst.setString(3, utente.getNome());
		pst.setString(4, utente.getCognome());
		pst.setString(5, utente.getEmail());
		pst.setString(6, utente.getRuolo());

		pst.executeUpdate();
		con.commit();

		DBConnection.riaggiungiConnessione(con);
		
		return utente;
	}

	public static Utente select(Utente utente) throws SQLException {
		Connection con = DBConnection.ottieniConnessione();
		PreparedStatement pst = con.prepareStatement(SELECT);
		pst.setString(1, utente.getUsername());
		ResultSet rs = pst.executeQuery();
		con.commit();

		Utente utFinal = null;
		while (rs.next()) {
			String username = rs.getString("username");

			if (utente.getUsername().equals(username)) {
				utFinal = new Utente();

				String password = rs.getString("password");
				String nome = rs.getString("nome");
				String cognome = rs.getString("cognome");
				String mail = rs.getString("email");
				String ruolo = rs.getString("ruolo");

				utFinal.setUsername(username);
				utFinal.setPassword(password);
				utFinal.setNome(nome);
				utFinal.setCognome(cognome);
				utFinal.setEmail(mail);

				if (ruolo.equals("admin"))
					utFinal.setRuolo("admin");
				else
					utFinal.setRuolo("utente");
			}
		}

		DBConnection.riaggiungiConnessione(con);
		if(utFinal == null) return null;
		else return utFinal;
	}

	public static Utente update(Utente utente) throws SQLException {
		Connection con = DBConnection.ottieniConnessione();
		PreparedStatement pst = con.prepareStatement(UPDATE);
		pst.setString(1, utente.getPassword());
		pst.setString(2, utente.getNome());
		pst.setString(3, utente.getCognome());
		pst.setString(4, utente.getEmail());
		pst.setString(5, utente.getUsername());

		pst.executeUpdate();
		con.commit();

		DBConnection.riaggiungiConnessione(con);
		return utente;
	}

}
