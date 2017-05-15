import java.util.HashMap;
import java.util.ArrayList;

public class PathGenerator {
	public static HashMap<Mailman, String> genPaths(ArrayList<Mailman> mailmen, ArrayList<Shipment> shipments) {
		//write the algorithm here
		//first get distances and sort them
		ArrayList<String> drivingAddresses = new ArrayList<String>();
		ArrayList<String> walkingAddresses = new ArrayList<String>();

		for (Shipment shipment: shipments) {
			if (shipment.getType().equals("regular")) {
				walkingAddresses.add(shipment.getAddress());
			} else {
				drivingAddresses.add(shipment.getAddress());
			}
		}
		
		double[][] drivingDistances = DistanceMatrixHTTPGetter.getDistanceMatrix(drivingAddresses);
		double[][] walkingDistances = DistanceMatrixHTTPGetter.getDistanceMatrix(walkingAddresses);

		//paths are stored per mailman
		HashMap<Mailman, String> paths = new HashMap<Mailman, String>();

		//count driving and walking mailmen
		int nbDriving = 0;
		int nbWalking = 0;
		int nbMailmen = mailmen.size();
		for (Mailman m: mailmen) {
			if (m.isDriver()) {
				nbDriving++;
			} else {
				nbWalking++;
			}
		}

		//generate paths for driving mailmen
		if (drivingDistances.length > 0) {
			//stuff
		}

		//generate paths for walking mailmen
		if (walkingDistances.length > 0) {
			//stuff
		}

		//return result
		return paths;
	}
}

