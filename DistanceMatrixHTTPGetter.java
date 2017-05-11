import java.net.URLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.io.UnsupportedEncodingException;
import java.io.InputStream;
import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;
import org.json.JSONObject;

public class DistanceMatrixHTTPGetter {
	public static double[][] getDistanceMatrix(List<String> addresses) {
		String origins = String.join("|", addresses).replace(" ", "+");
		String destinations = origins;

		//config constants
		final String charset = java.nio.charset.StandardCharsets.UTF_8.name();

		//filling and formatting request
		String baseURL = "https://maps.googleapis.com/maps/api/distancematrix/json",
			apiKey = "AIzaSyA5FMd2HKX15GZJdhWSbQh7Lu2PzmSkozI",
			language = "fr-FR",
			units = "metric",
			mode = "driving";
		String queryURL = null;
		try {
			queryURL = String.format("%s?key=%s&origins=%s&destinations=%s&language=%s&units=%s&mode=%s",
				baseURL,
				URLEncoder.encode(apiKey, charset),
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
		double[][] distances = new double[addresses.size()][addresses.size()];
		try (Scanner resScan = new Scanner(response)) {
			String responseBody = resScan.useDelimiter("\\A").next();
			JSONObject obj = new JSONObject(responseBody);
			for (int i = 0; i < addresses.size(); i++) {
				for (int j = 0; j < addresses.size(); j++) {
					distances[i][j] = new Double(obj.query("/rows/" + i + "/elements/" + j + "/distance/value").toString()).doubleValue() * 1e-3;
				}
			}
		} catch (Exception e) {
			System.out.println("Erreur de lecture du contenu !");
		} finally {
			return distances;
		}
	}

	public static void CLITest() {
		//get origins and dest from user input
		Scanner sc = new Scanner(System.in);
		System.out.println("Donnez une liste de lieux :");
		List<String> addresses = new ArrayList<String>();
		String place = "";
		while (!(place = sc.nextLine()).isEmpty()) {
			addresses.add(place);
		}

		System.out.println("Listes des distances");
		System.out.println("====================");
		double[][] distances = getDistanceMatrix(addresses);
		for (int i = 0; i < addresses.size(); i++) {
			for (int j = 0; j < addresses.size(); j++) {
				System.out.println(addresses.get(i) + " --> " + addresses.get(j) + ": " + distances[i][j] + " km");
			}
		}
	}
}

