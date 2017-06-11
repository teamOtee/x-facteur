package xfacteur.model;

import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;

import xfacteur.PathController;
import xfacteur.ShipmentController;
import xfacteur.MailmanController;

public class PathGenerator {
	// inaccessible constructor
	protected PathGenerator() {}

	// the access point here
	public static Map<Mailman, Path> genPaths() {
		// sort Shipment ArrayList
		List<Shipment> drivingShipments = new ArrayList<Shipment>();
		List<Shipment> walkingShipments = new ArrayList<Shipment>();

		for (Shipment shipment : ShipmentController.getItems()) {
			if (shipment.isDriven()) {
				drivingShipments.add(shipment);
			} else {
				walkingShipments.add(shipment);
			}
		}

		// get the distances
		DistanceMatrix drivingDistances = DistanceMatrixHTTPGetter.getDistanceMatrix(PathController.getDrivingPO(), drivingShipments);
		DistanceMatrix walkingDistances = DistanceMatrixHTTPGetter.getDistanceMatrix(PathController.getWalkingPO(), walkingShipments);

		// sort Mailman ArrayList
		List<Mailman> drivingMailmen = new ArrayList<Mailman>();
		List<Mailman> walkingMailmen = new ArrayList<Mailman>();
		for (Mailman m : MailmanController.getItems()) {
			if (m.isDriver()) {
				drivingMailmen.add(m);
			} else {
				walkingMailmen.add(m);
			}
		}

		// generate the paths for real, for driving and walking mailmen
		Map<Mailman, Path> paths = new HashMap<Mailman, Path>();
		paths.putAll(genPathsForReal(drivingMailmen, drivingShipments, PathController.getDrivingPO(), drivingDistances));
		paths.putAll(genPathsForReal(walkingMailmen, walkingShipments, PathController.getWalkingPO(), walkingDistances));

		// done!
		return paths;
	}

	// basic paths: a simple loop
	protected static Path basicLoop(Mailman mailman, List<Shipment> shipments, Shipment postOffice, DistanceMatrix distances) {
		Path path = new Path(mailman, postOffice);

		while (path.size() < shipments.size() + 1) {
			Shipment closest = null;
			for (Shipment s: shipments) {
				if (!path.contains(s) && ((closest == null) || (distances.get(path.getLast().getShipment(), s) < distances.get(path.getLast().getShipment(), closest)))) {
					closest = s;
				}
			}
			path.add(closest, distances);
		}

		return path;
	}

	// actually generate the paths for each mailman
	protected static Map<Mailman, Path> genPathsForReal(List<Mailman> mailmen, List<Shipment> shipments, Shipment postOffice, DistanceMatrix distances) {
		Map<Mailman, Path> paths = new HashMap<Mailman, Path>();
		paths.put(mailmen.get(0), basicLoop(mailmen.get(0), shipments, postOffice, distances));
		for (Mailman m: mailmen) {
			if (!paths.containsKey(m)) {
				paths.put(m, new Path(m, postOffice));
			}
		}
		return paths;
	}
}

