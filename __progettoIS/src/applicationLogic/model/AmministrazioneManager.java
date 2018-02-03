package applicationLogic.model;

import java.sql.SQLException;
import java.util.ArrayList;

import applicationLogic.bean.Recensione;
import storage.database.RecensioneDAO;

/**
 * Gestisce tutto ciò che riguarda il collegamento fra l'applicazione ed il
 * database per l'amministrazione della piattaforma
 * 
 * @author Luca
 * @since 1.0
 */
public class AmministrazioneManager {
	private AmministrazioneManager() {
		/* Costruttore vuoto e privato poichè non istanziabile */}

	/**
	 * Permette di ottenere tutte le recensioni da dentro il database
	 * 
	 * @return la lista di tutte le recensioni
	 */
	public static ArrayList<Recensione> getAllRecensioni() {
		try {
			return RecensioneDAO.select();
		} catch (SQLException e) {
			e.printStackTrace();
			return new ArrayList<Recensione>();
		}
	}

	/**
	 * Permette di ottenere una lista di recensioni che sono state segnalate
	 * 
	 * @return una lista di recensioni segnalate
	 */
	public static ArrayList<Recensione> getSegnalate() {
		ArrayList<Recensione> out = new ArrayList<Recensione>();
		try {
			for (Recensione r : RecensioneDAO.selectBySegnalate()) {
				out.add(RecensioneManager.getCompleta(r));
			}
			return out;
		} catch (SQLException e) {
			e.printStackTrace();
			return out;
		}
	}

}
