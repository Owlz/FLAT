package storage;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import applicationLogic.models.Utente;

public class UtenteDAO {
	private static final String GET_UTENTE = "SELECT * FROM utenti WHERE username = ?";
	private static final String INSERT_UTENTE = "INSERT INTO utenti (`username`, `password`, `nome`, `cognome`, `email`, `ruolo`) VALUES (?, ?, ?, ?, ?, ?)";
	private static final String UPDATE_UTENTE = "UPDATE utenti SET `password` = ?, `nome` = ?, `cognome` = ?, `email` = ? WHERE `username` = ?";

	public static Utente inserisciUtente(Utente utente) throws SQLException {
		Connection con = DBConnection.ottieniConnessione();
		PreparedStatement pst = con.prepareStatement(INSERT_UTENTE);
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

	public static Utente getUtente(Utente utente) throws SQLException {
		Utente utFinal = null;

		Connection con = DBConnection.ottieniConnessione();
		PreparedStatement pst = con.prepareStatement(GET_UTENTE);
		pst.setString(1, utente.getUsername());
		ResultSet rs = pst.executeQuery();
		con.commit();

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
		return utFinal;
	}

	public static Utente aggiornaUtente(Utente utente) throws SQLException {
		Connection con = DBConnection.ottieniConnessione();
		PreparedStatement pst = con.prepareStatement(UPDATE_UTENTE);
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
