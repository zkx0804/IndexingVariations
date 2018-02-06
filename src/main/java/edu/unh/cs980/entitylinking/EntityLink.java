package edu.unh.cs980.entitylinking;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class EntityLink {
	String spotlightAPIurl = "http://model.dbpedia-spotlight.org/en/annotate?";

	public ArrayList<Word> getAnootatedWords(String text) {
		ArrayList<Word> results = new ArrayList<Word>();

		String httpUrl = spotlightAPIurl + "text=" + text.replace(" ", "%20");// Remove
																				// spaces.

		String responseStr = getHttpResponse(httpUrl);

		// Use Gson Map the response string into List of linked word.
		//

		return results;
	}

	private String getHttpResponse(String urlStr) {
		try {

			URL url = new URL(urlStr);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");

			conn.setRequestProperty("Accept", "application/json");
			// conn.setReadTimeout(httpRequest_timeout);
			if (conn.getResponseCode() != 200) {
				System.out
						.println("Failed to connect to " + urlStr + " with HTTP error code: " + conn.getResponseCode());
				if (conn.getResponseCode() == 401) {
				}
				return null;
			}

			BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));

			String output = "";// br.readLine();
			String line = "";
			while ((line = br.readLine()) != null) {
				output += line;
			}

			conn.disconnect();
			return output;
		} catch (Exception e) {
			return null;
		}
	}
}
