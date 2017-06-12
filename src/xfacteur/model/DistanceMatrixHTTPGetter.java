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
import com.google.maps.GeoApiContext;
import com.google.maps.DistanceMatrixApi;
import com.google.maps.DistanceMatrixApiRequest;
import com.google.maps.model.DistanceMatrix;
import com.google.maps.model.Unit;
import com.google.maps.model.TravelMode;

import xfacteur.XFacteur;
import xfacteur.ShipmentController;
import xfacteur.PathController;

public class DistanceMatrixHTTPGetter {
	public static DistanceMatrix getDistanceMatrix(boolean driving) {
		String[] shipments = new String[1 + ShipmentController.getItems().size()];
		shipments[0] = PathController.getWalkingPO().getAddress();
		for (int i = 0; i < ShipmentController.getItems().size(); i++) {
			shipments[i + 1] = ShipmentController.getItems().get(i).getAddress();
		}

		GeoApiContext context = new GeoApiContext().setApiKey(XFacteur.GOOGLE_API_KEY);
		DistanceMatrixApiRequest req = DistanceMatrixApi.getDistanceMatrix(context, shipments, shipments);
		req.language("fr-FR");
		req.units(Unit.METRIC);
		req.mode(driving ? TravelMode.DRIVING : TravelMode.WALKING);
		
		try {
			return req.await();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}

