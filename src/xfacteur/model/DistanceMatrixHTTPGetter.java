package xfacteur.model;

import java.net.URLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.io.UnsupportedEncodingException;
import java.io.InputStream;
import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;
import java.nio.charset.StandardCharsets;
import org.json.JSONObject;

public class DistanceMatrixHTTPGetter {
	public static DistanceMatrix getDistanceMatrix(Shipment postOffice, List<Shipment> otherShipments) {
		List<Shipment> shipments = new ArrayList<Shipment>();
		shipments.add(postOffice);
		shipments.addAll(otherShipments);
		int nbShipments = shipments.size();
		String origins = "";
		for (Shipment s : shipments) {
			origins += s.getAddress().replace(" ", "+") + "|";
		}
		origins = origins.substring(0, origins.length() - 1);
		String destinations = origins;

		// config constants
		final String charset = StandardCharsets.UTF_8.name();

		// filling and formatting request
		String baseURL = "https://maps.googleapis.com/maps/api/distancematrix/json",
				apiKey = "AIzaSyA5FMd2HKX15GZJdhWSbQh7Lu2PzmSkozI", // DO NOT CHANGE THIS VALUE!
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

		// opening connection
		URLConnection connection = null;
		InputStream response = null;
		try {
			connection = new URL(queryURL).openConnection();
			connection.setRequestProperty("Accept-Charset", charset);
			response = connection.getInputStream();
		} catch (Exception e) {
			System.out.println("Erreur de connection !");
		}

		// displaying response on stdout
		DistanceMatrix distances = new DistanceMatrix(shipments);
		try (Scanner resScan = new Scanner(response)) {
			String responseBody = resScan.useDelimiter("\\A").next();
			JSONObject obj = new JSONObject(responseBody);
			for (int i = 0; i < shipments.size(); i++) {
				for (int j = 0; j < shipments.size(); j++) {
					distances.set(shipments.get(i), shipments.get(j), new Double(obj.query("/rows/" + i + "/elements/" + j + "/distance/value").toString()).doubleValue()	* 1e-3);
				}
			}
		} catch (Exception e) {
			System.out.println("Erreur de lecture du contenu !");
		} finally {
			return distances;
		}
	}
}

