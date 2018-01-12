package applicationLogic.model;

import java.sql.SQLException;
import java.util.ArrayList;

import applicationLogic.bean.Film;
import applicationLogic.bean.FilmLocal;
import applicationLogic.bean.Utente;
import storage.database.FilmLocalDAO;
import storage.database.WatchlistDAO;

public class WatchlistManager {
	private WatchlistManager() {/* Costruttore vuoto e privato poichè non istanziabile */}

	public static boolean addWatchlist(Film f, Utente u) {
		try{
			WatchlistDAO.insert(f, u);
			return true;
			
		} catch (SQLException e) { // non dovrebbero succedere mai ma provo lo stesso
			e.printStackTrace();
			return false;	
		}
	}

	public static boolean removeWatchlist(Film f, Utente u) {		
		try{
			WatchlistDAO.removeFilm(f, u);
			return true;
			
		} catch (SQLException e) { // non dovrebbero succedere mai ma provo lo stesso
			e.printStackTrace();
			return false;
		}
	}

	public static boolean checkFilmInWatchlist(Film f, Utente u) {		
		try{
			ArrayList<Film> watchlist = WatchlistDAO.selectByUser(u);
			
			if(watchlist.contains(f)) return true;
			else return false;
			
		} catch (SQLException e) { // non dovrebbero succedere mai ma provo lo stesso
			e.printStackTrace();
			return false;	
		}
	}

	public static ArrayList<FilmLocal> getWatchlist(Utente u){
		ArrayList<Film> watchlistSoloID;
		ArrayList<FilmLocal> out = new ArrayList<FilmLocal>();
		
		try{
			watchlistSoloID = WatchlistDAO.selectByUser(u);
			for(Film x: watchlistSoloID){
				FilmLocal attuale = FilmLocalDAO.select((FilmLocal) x);
				if(attuale != null)	out.add(attuale);
			}
			return out;
			
		}catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
}
