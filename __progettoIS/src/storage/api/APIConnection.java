package storage.api;

import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Scanner;

import org.json.JSONObject;

import applicationLogic.bean.Film;

/**
 * Gestisce la connesione all'api online
 * 
 * @author Luca
 *
 */
public class APIConnection {
	final static String BASE_URL_MOVIE = "https://api.themoviedb.org/3/movie/";
	final static String BASE_URL_SEARCH = "https://api.themoviedb.org/3/search/movie";
	final static String API_KEY = "api_key=ca9d1ed5bdb0e282100b74e5ea8271ce";
	final static String LANGUAGE = "language=it";
	final static String REGION = "region=it";

	/**
	 * Permette di inviare una request sull'api di TheMovieDatabase
	 * 
	 * @param query
	 *            da inviare
	 * @return JSONObject di dati ottenuti
	 * @throws IOException
	 *             nel caso l'api e/o il nostro server sia offline
	 */
	protected static JSONObject sendRequest(String query) throws IOException {
		URL apiRequest = new URL(query);
		URLConnection connection;
		connection = apiRequest.openConnection();
		connection.setDoOutput(true);

		// utf-8 per evitare problemi con caratteri strani
		Scanner s = new Scanner(apiRequest.openStream(), "UTF-8");

		/*
		 * TODO: Può lanciare una null pointer exception su
		 * apiRequest.openStream() quando il film non viene trovato nel database
		 * (fatto strano poichè viene comunque restituito qualcosa dall'api)
		 */

		// \z significa fino fine richiesta
		String source = s.useDelimiter("\\Z").next();
		s.close();

		return new JSONObject(source);
	}

	/**
	 * Permette di ottenere un film dall'API online
	 * 
	 * @param f
	 *            oggetto film con un id
	 * @return un oggetto JSONObject con tutte le informazioni sul film
	 * @throws IOException
	 *             nel caso l'api e/o il nostro server sia offline
	 */
	protected static JSONObject requestFilm(Film f) throws IOException {
		String url_request = BASE_URL_MOVIE + f.getId() + "?" + API_KEY + "&" + LANGUAGE + "&" + REGION;
		return sendRequest(url_request);

	}

	/**
	 * Permette di ottenere una lista di film dall'API online che corrispondono
	 * ad una query
	 * 
	 * @param query
	 *            una stringa da inviare all' api
	 * @return un oggetto JSONObject con tutte le informazioni sui film trovati
	 * @throws IOException
	 *             nel caso l'api e/o il nostro server sia offline
	 */
	protected static JSONObject requestFilms(String query) throws IOException {
		String url_request = BASE_URL_SEARCH + "?" + API_KEY + "&" + REGION + "&" + LANGUAGE + "&query="
				+ sanitarizza(query);
		return sendRequest(url_request);
	}

	protected static String sanitarizza(String query) {
		return query.replaceAll(" ", "+").replaceAll("è", "&#232;").replaceAll("é", "&#232;").replaceAll("ì", "&#236;")
				.replaceAll("à", "&#224;").replaceAll("ò", "&#242;").replaceAll("ù", "&#249;").replaceAll(">", "&gt;")
				.replaceAll("<", "&lt;").replaceAll("'", "&#39;").replaceAll("\"", "&quot;").replaceAll("&", "&amp;");
	}
}
