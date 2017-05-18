package XFacteur;

import java.util.HashMap;
import java.util.ArrayList;
import java.util.LinkedList;

public class PathGenerator {
	public static HashMap<Mailman, LinkedList<Shipment>> genPaths(ArrayList<Mailman> mailmen, ArrayList<Shipment> shipments) {
		//sort Shipment ArrayList
		ArrayList<Shipment> drivingShipments = new ArrayList<Shipment>();
		ArrayList<Shipment> walkingShipments = new ArrayList<Shipment>();

		for (Shipment shipment: shipments) {
			if (shipment.isDriving()) {
				drivingShipments.add(shipment);
			} else {
				walkingShipments.add(shipment);
			}
		}
		
		double[][] drivingDistances = DistanceMatrixHTTPGetter.getDistanceMatrix(drivingShipments);
		double[][] walkingDistances = DistanceMatrixHTTPGetter.getDistanceMatrix(walkingShipments);

		//sort Mailman ArrayList
		ArrayList<Mailman> drivingMailmen = new ArrayList<Mailman>();
		ArrayList<Mailman> walkingMailmen = new ArrayList<Mailman>();
		for (Mailman m: mailmen) {
			if (m.isDriver()) {
				drivingMailmen.add(m);
			} else {
				walkingMailmen.add(m);
			}
		}

		//paths are stored per mailman
		HashMap<Mailman, LinkedList<Shipment>> paths = new HashMap<Mailman, LinkedList<Shipment>>();

		//generate paths for driving mailmen
		if ((drivingShipments.size() > 0) && (drivingMailmen.size() > 0)) {
			paths.put(drivingMailmen.get(0), pathForMailman(drivingMailmen.get(0), drivingShipments, drivingDistances));
		}

		//generate paths for walking mailmen
		if ((walkingShipments.size() > 0) && (walkingMailmen.size() > 0)) {
			paths.put(walkingMailmen.get(0), pathForMailman(walkingMailmen.get(0), walkingShipments, walkingDistances));
		}

		//return result
		return paths;
	}

	private static LinkedList<Shipment> pathForMailman(Mailman mailman, ArrayList<Shipment> shipments, double[][] distances) {
		//here generate one path for a given mailman
		LinkedList<Shipment> path = new LinkedList<Shipment>(shipments);

		//do stuff
		
		//return the generated path
		return path;
	}
}

