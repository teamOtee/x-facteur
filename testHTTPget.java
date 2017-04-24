import java.net.URLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.io.UnsupportedEncodingException;
import java.io.InputStream;
import java.util.Scanner;

public class testHTTPget {
	public static void main(String[] args) {
		//config constants
		final String charset = java.nio.charset.StandardCharsets.UTF_8.name();

		//filling and formatting request
		String baseURL = "https://maps.googleapis.com/maps/api/distancematrix/json";
		String origins = "Lannion|Châlons+en+Champagne",
			destinations = "Lannion|Châlons+en+Champagne",
			language = "fr-FR",
			units = "metric",
			mode = "driving";
		String queryURL = null;
		try {
			queryURL = String.format("%s?origins=%s&destinations=%s&language=%s&units=%s&mode=%s",
				baseURL,
				URLEncoder.encode(origins, charset),
				URLEncoder.encode(destinations, charset),
				URLEncoder.encode(language, charset),
				URLEncoder.encode(units, charset),
				URLEncoder.encode(mode, charset)
			);
		} catch (UnsupportedEncodingException e) {
			System.out.println("Erreur d’encodage des paramètres !");
		}
		
		//opening connection
		URLConnection connection = null;
		InputStream response = null;
		try {
			connection = new URL(queryURL).openConnection();
			connection.setRequestProperty("Accept-Charset", charset);
			response = connection.getInputStream();
		} catch (Exception e) {
			System.out.println("Erreur de connection !");
		}

		//displaying response on stdout
		try (Scanner sc = new Scanner(response)) {
			String responseBody = sc.useDelimiter("\\A").next();
			System.out.println(responseBody);
		}
	}
}

