package xfacteur.model;

import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.util.LinkedList;

public class PathGenerator {
	//inaccessible constructor
	protected PathGenerator() {}
	
	//the access point here
	public static Map<Mailman, Path> genPaths(List<Mailman> mailmen, List<Shipment> shipments) {
		//sort Shipment ArrayList
		List<Shipment> drivingShipments = new ArrayList<Shipment>();
		List<Shipment> walkingShipments = new ArrayList<Shipment>();

		for (Shipment shipment: shipments) {
			if (shipment.isDriving()) {
				drivingShipments.add(shipment);
			} else {
				walkingShipments.add(shipment);
			}
		}
		
		//get the distances
		DistanceMatrix drivingDistances = DistanceMatrixHTTPGetter.getDistanceMatrix(drivingShipments);
		DistanceMatrix walkingDistances = DistanceMatrixHTTPGetter.getDistanceMatrix(walkingShipments);

		//sort Mailman ArrayList
		List<Mailman> drivingMailmen = new ArrayList<Mailman>();
		List<Mailman> walkingMailmen = new ArrayList<Mailman>();
		for (Mailman m: mailmen) {
			if (m.isDriver()) {
				drivingMailmen.add(m);
			} else {
				walkingMailmen.add(m);
			}
		}

		//paths are stored per mailman
		Map<Mailman, Path> drivingPaths = null;
		Map<Mailman, Path> walkingPaths = null;

		//generate paths for driving mailmen
		if ((drivingShipments.size() > 0) && (drivingMailmen.size() > 0)) {
			drivingPaths = initBasicPaths(drivingMailmen, drivingShipments, drivingDistances);
		}

		//generate paths for walking mailmen
		if ((walkingShipments.size() > 0) && (walkingMailmen.size() > 0)) {
			walkingPaths = initBasicPaths(walkingMailmen, walkingShipments, walkingDistances);
		}

		//return result
		Map<Mailman, Path> paths = new HashMap<Mailman, Path>();
		paths.putAll(drivingPaths);
		paths.putAll(walkingPaths);
		return paths;
	}

	//basic paths: a simple loop for every mailman
	protected static Map<Mailman, Path> initBasicPaths(List<Mailman> mailmen, List<Shipment> shipments, DistanceMatrix distances) {
		//an empty path for every mailman
		Map<Mailman, Path> paths = new HashMap<Mailman, Path>();
		for (Mailman m: mailmen) {
			paths.put(m, new Path(m, shipments.get(0)));
		}

		//for every shipment, add it to the currently closest mailman
		for (Shipment s: shipments) {
			Mailman closest = mailmen.get(0);
			double minDist = Double.MAX_VALUE;
			for (Mailman m: mailmen) {
				if (distances.getDistance(paths.get(m).getLastStep().getShipment(), s) < minDist) {
					closest = m;
					minDist = distances.getDistance(paths.get(m).getLastStep().getShipment(), s);
				}
			}
			paths.get(closest).add(s, distances);
		}

		return paths;
	}
}

