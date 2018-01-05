package storage.api;

import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Scanner;

import org.json.JSONArray;
import org.json.JSONObject;

import applicationLogic.models.Film; 

public class ApiRequest {
	final static String BASE_URL_MOVIE = "https://api.themoviedb.org/3/movie/";
	final static String BASE_URL_SEARCH = "https://api.themoviedb.org/3/search/movie";
	final static String API_KEY = "api_key=ca9d1ed5bdb0e282100b74e5ea8271ce";
	final static String LANGUAGE = "language=it";
	final static String REGION = "region=it";

	protected static JSONObject sendRequest(String query) throws IOException {
		URL apiRequest = new URL(sanitarizza(query));
		URLConnection connection;
		connection = apiRequest.openConnection();
		connection.setDoOutput(true);

		// utf-8 per evitare problemi con caratteri strani
		Scanner s = new Scanner(apiRequest.openStream(), "UTF-8");
		
		/* TODO: 	Può lanciare una null pointer exception su apiRequest.openStream()
		 * 			quando il film non viene trovato nel database (fatto strano poichè
		 * 			viene comunque restituito qualcosa dall'api)
		 */

		// \z significa fino fine richiesta
		String source = s.useDelimiter("\\Z").next();
		s.close();

		return new JSONObject(source);
	}

	protected static String sanitarizza(String query) {
		return query.replaceAll(" ", "+")
				.replaceAll("è", "&#232;")
				.replaceAll(">", "&gt;")
				.replaceAll("<", "&lt;")
				.replaceAll("'", "&#39;")
				.replaceAll("\"", "&quot;")
				.replaceAll("&", "&amp;");
	}

	public static Film getFilm(Film f) {
		String url_request = BASE_URL_MOVIE + f.getId() + "?" + API_KEY + "&" + LANGUAGE + "&" + REGION;
		JSONObject obj;
		try {
			obj = sendRequest(url_request);
			
			JSONArray generi = obj.getJSONArray("genres");
			ArrayList<String> gen = null;
			if(generi.length() > 0){
				gen = new ArrayList<String>();
				for (int i = 0; i < generi.length(); i++)
					gen.add(generi.getJSONObject(i).optString("name"));
			}
			
			f = new Film(obj.optInt("id"), 
					obj.optString("title"), 
					obj.optString("original_title"),
					obj.optString("poster_path"), 
					gen, 
					obj.optString("original_language"), 
					obj.optString("overview"),
					obj.optString("release_date"), 
					obj.optDouble("vote_average"), 
					obj.optInt("vote_count"),
					obj.optString("backdrop"));
			
			return f;
			
		} catch (IOException e) {
			e.printStackTrace();		// server offline
			return null;
		}
	}

	public static ArrayList<Film> getFilms(String query) {
		String url_request = BASE_URL_SEARCH + "?" + API_KEY + "&" + REGION + "&" + LANGUAGE + "&query=" + query;
		ArrayList<Film> listaFilmFinale = new ArrayList<Film>();

		try {
			JSONObject objRequest = sendRequest(url_request);
			JSONArray arrayObj = objRequest.getJSONArray("results");

			for (int i = 0; i < arrayObj.length(); i++) {
				JSONObject obj = (JSONObject) arrayObj.get(i);
				if(obj.optBoolean("adult", true) == true) continue;
				listaFilmFinale.add(
						new Film(
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
