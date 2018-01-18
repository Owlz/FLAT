package storage.api;

import java.io.IOException;
import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONObject;
import applicationLogic.bean.Film;
import applicationLogic.bean.FilmLocal;
import applicationLogic.bean.FilmRemote;

public class FilmRemoteDAO {
	
	public static FilmRemote getFilm(Film f) {
		JSONObject obj;
		
		try {
			obj = APIConnection.requestFilm(f);
			
			/* ottieni arraylist di generi */
			JSONArray generi = obj.getJSONArray("genres");
			ArrayList<String> gen = null;
			if(generi.length() > 0){
				gen = new ArrayList<String>();
				for (int i = 0; i < generi.length(); i++)
					gen.add(generi.getJSONObject(i).optString("name"));
			}
			
			FilmRemote fOut = new FilmRemote(obj.optInt("id"), 
						obj.optString("title"), 
						obj.optString("original_title"),
						obj.optString("poster_path"), 
						gen, 
						obj.optString("original_language"), 
						obj.optString("overview"),
						obj.optString("release_date"), 
						obj.optDouble("vote_average"), 
						obj.optInt("vote_count"),
						obj.optString("backdrop_path"));
			
			return fOut;
			
		} catch (IOException e) {
			e.printStackTrace();		// server offline
			return null;
		}
	}

	public static ArrayList<Film> getFilms(String query) {
		ArrayList<Film> listaFilmFinale = new ArrayList<Film>();

		if(query == null) return null;
		try {
			JSONObject objRequest = APIConnection.requestFilms(query);
			JSONArray arrayObj = objRequest.getJSONArray("results");

			for (int i = 0; i < arrayObj.length(); i++) {
				JSONObject obj = (JSONObject) arrayObj.get(i);
				if(obj.optBoolean("adult", true) == true) continue;
				listaFilmFinale.add(
						new FilmLocal(
								obj.optInt("id"), 
								obj.optString("title"), 
								obj.optString("original_title"),
								obj.optString("poster_path"))
						);
			}
			return listaFilmFinale;
			
		} catch (IOException e) {
			e.printStackTrace(); // server offline
			return null;
		}
	}
}
