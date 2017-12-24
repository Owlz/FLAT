package storage;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import applicationLogic.models.Utente;

public class UtenteDAO {
	private static final String GET_UTENTE = "SELECT * FROM utenti WHERE username = ?";

	public static Utente getUtente(Utente utente) throws SQLException {
		Utente utFinal = new Utente();

		Connection con = DBConnection.ottieniConnessione();
		PreparedStatement pst = con.prepareStatement(GET_UTENTE);
		pst.setString(1, utente.getUsername());
		ResultSet rs = pst.executeQuery();
		con.commit();
		
		while (rs.next()) {
			String username = rs.getString("username");
			
			if (utente.getUsername().equals(username)) {
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

				if (ruolo.equals("admin")) utFinal.setRuolo("admin");
				else utFinal.setRuolo("utente");
			}
		}

		DBConnection.riaggiungiConnessione(con);
		return utFinal;
	}

}
