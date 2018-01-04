package applicationLogic.managers;

import java.sql.SQLException;
import java.util.ArrayList;

import applicationLogic.models.Film;
import applicationLogic.models.Utente;
import storage.database.FilmDAO;
import storage.database.WatchlistDAO;

public class WatchlistManager {
	private WatchlistManager() {/* Costruttore vuoto e privato poichè non istanziabile */}

	public static boolean addWatchlist(String idFilm, Utente u) {
		Integer id = null; 
		
		try{
			id = Integer.parseInt(idFilm); 
			Film f = new Film();
			f.setId(id);
			WatchlistDAO.inserisciFilmWatchlist(f, u);
			return true;
			
		} catch (SQLException e) { // non dovrebbero succedere mai ma provo lo stesso
			e.printStackTrace();
			return false;
			
		} catch (NumberFormatException | NullPointerException e) {
			e.printStackTrace();
			return false;
		}
	}

	public static boolean removeWatchlist(String idFilm, Utente u) {
		Integer id = null; 
		
		try{
			id = Integer.parseInt(idFilm); 
			Film f = new Film();
			f.setId(id);
			WatchlistDAO.rimuoviFilmWatchlist(f, u);
			return true;
			
		} catch (SQLException e) { // non dovrebbero succedere mai ma provo lo stesso
			e.printStackTrace();
			return false;
			
		} catch (NumberFormatException | NullPointerException e) {
			e.printStackTrace();
			return false;
		}
	}

	public static boolean check(String idFilm, Utente u) {
		Integer id = null; 
		
		try{
			id = Integer.parseInt(idFilm); 
			Film f = new Film();
			f.setId(id);
			ArrayList<Film> watchlist = WatchlistDAO.getWatchlistUtente(u);
			
			if(watchlist.contains(f)) return true;
			else return false;
			
		} catch (SQLException e) { // non dovrebbero succedere mai ma provo lo stesso
			e.printStackTrace();
			return false;
			
		} catch (NumberFormatException | NullPointerException e) {
			System.out.println("utente non loggato");
			return false;
		}
	}

	public static ArrayList<Film> getWatchlist(Utente u){
		ArrayList<Film> watchlist;
		ArrayList<Film> out = new ArrayList<Film>();
		
		try{
			watchlist = WatchlistDAO.getWatchlistUtente(u);
			for(Film x: watchlist){
				out.add(FilmDAO.getFilm(x));
			}
			return out;
			
		}catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
}
