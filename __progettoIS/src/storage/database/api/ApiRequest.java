package storage.database.api;

import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Scanner;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import applicationLogic.models.Film;

public class ApiRequest {
	final static String BASE_URL_MOVIE = "https://api.themoviedb.org/3/movie/";
	final static String API_KEY = "api_key=ca9d1ed5bdb0e282100b74e5ea8271ce";
	final static String LANGUAGE = "language=it";
	final static String REGION = "region=it";
	
	protected static JSONObject sendRequest(String query) throws IOException{
		URL apiRequest = new URL(query);
		URLConnection connection;
		connection = apiRequest.openConnection();
		connection.setDoOutput(true);
		
		// utf-8 per evitare caratteri strani
		Scanner s = new Scanner(apiRequest.openStream(), "UTF-8");
		
		// \z significa fino fine richiesta
		String source = s.useDelimiter("\\Z").next();
		s.close();
		
		return new JSONObject(source);
	}
	
	public static Film getFilm(int id) throws IOException{
		String query = BASE_URL_MOVIE + id + "?" + API_KEY + "&" + LANGUAGE + "&" + REGION;
		JSONObject obj = sendRequest(query);
		return null;
		
		/* TODO: questa roba */
		
	}

}
