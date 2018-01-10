package storage.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Gestisce gli oggetti di tipo "Connection" che servono per dialogare 
 * con il database in modo da non doverli creare ad ogni utente e, quindi,
 * migliorare il throughput dell'applicazione
 * @author Luca
 * @since 1.0
 */
public class DBConnection {
	// Variabili
	public static final String NOME_DRIVER = "com.mysql.jdbc.Driver";
	public static final String NOME_DATABASE = "is_progetto";
	public static final String LINK_DATABASE = "jdbc:mysql://localhost/" + NOME_DATABASE
			+ "?verifyServerCertificate=false&useSSL=true";
	public static final String USR_DATABASE = "root";
	public static final String PSW_DATABASE = "password";

	private static ArrayList<Connection> connPool;

	static {
		connPool = new ArrayList<Connection>();

		try {
			Class.forName(NOME_DRIVER);
		} catch (ClassNotFoundException e) {
			System.err.println("Classe del driver non trovata, errore:\n" + e);
		}
	}

	private static Connection creaConnessione() throws SQLException {
		Connection con = DriverManager.getConnection(LINK_DATABASE, USR_DATABASE, PSW_DATABASE);
		return con;
	}
	/**
	 * Ritorna (e all'occorrenza crea) una connessione con il database
	 * @return un oggetto di tipo "Connection"
	 * @throws SQLException nel caso in cui il databsase è spento o non raggiungibile
	 */
	public static synchronized Connection ottieniConnessione() throws SQLException {
		Connection con;

		if (!connPool.isEmpty()) {
			con = (Connection) connPool.get(0);
			DBConnection.connPool.remove(0);
			try {
				if (con.isClosed())
					con = DBConnection.ottieniConnessione();
			} catch (SQLException e) {
				con.close();
				con = DBConnection.ottieniConnessione();
			}
		} else {
			con = DBConnection.creaConnessione();
		}
		con.setAutoCommit(false);
		return con;
	}

	/**
	 * Una volta finito di usare l'oggetto per la connessione, è possibile 
	 * farlo torrnare nel pool così da essere riutilizzato
	 * @param con l'oggetto connessione da far tornare
	 */
	public static synchronized void riaggiungiConnessione(Connection con) {
		DBConnection.connPool.add(con);
	}

}

