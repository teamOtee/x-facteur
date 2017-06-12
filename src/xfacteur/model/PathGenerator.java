package xfacteur.model;

import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import com.google.maps.model.DistanceMatrix;

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
		System.out.print("getting DMs… ");
		DistanceMatrix drivingDistances = DistanceMatrixHTTPGetter.getDistanceMatrix(true);
		DistanceMatrix walkingDistances = DistanceMatrixHTTPGetter.getDistanceMatrix(false);
		System.out.println("done.");

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

	// actually generate the paths for each mailman
	protected static Map<Mailman, Path> genPathsForReal(List<Mailman> mailmen, List<Shipment> shipments, Shipment postOffice, DistanceMatrix distances) {
		Map<Mailman, Path> paths = new HashMap<Mailman, Path>();
		if (!mailmen.isEmpty()) {
			paths.put(mailmen.get(0), basicLoop(mailmen.get(0), shipments, postOffice, distances));
		}
		for (Mailman m: mailmen) {
			if (!paths.containsKey(m)) {
				paths.put(m, new Path(m, postOffice));
			} else {
				paths.put(m, optimize(paths.get(m), distances));
			}
		}
		return paths;
	}

	// basic paths: a simple loop
	protected static Path basicLoop(Mailman mailman, List<Shipment> shipments, Shipment postOffice, DistanceMatrix distances) {
		Path path = new Path(mailman, postOffice);

		while (path.size() < shipments.size() + 1) {
			Shipment closest = null;
			for (Shipment s: shipments) {
				if (!path.contains(s) && ((closest == null) || (PathController.distance(path.getLast().getShipment(), s, distances) < PathController.distance(path.getLast().getShipment(), closest, distances)))) {
					closest = s;
				}
			}
			path.add(closest, distances);
		}

		return path;
	}

	// 2-opt heuristic
	protected static Path optimize(Path path, DistanceMatrix distances) {
		boolean cont = true;
		System.out.println("optimize…");
		// while it is possible to improve
		while (cont) {
			cont = false;
			// for each step i
			for (int i = 0; i < path.size(); i++) {
				// for each step j ≠ i, i-1, i+1
				for (int j = 0; j < path.size(); j++) {
					if (j < i - 1 || j > i + 1) {
						int first = i < j ? i : j;
						int last = i > j ? i : j;
						double currentDistance = path.sumDistance(last) - path.sumDistance(first);
						double testDistance = PathController.distance(path.getShipment(first), path.getShipment(last - 1), distances);
						for (int k = last - 1; k > first + 1; k--) {
							testDistance += PathController.distance(path.getShipment(k), path.getShipment(k - 1), distances);
						}
						testDistance += PathController.distance(path.getShipment(first + 1), path.getShipment(last), distances);
						System.out.println(currentDistance + " // " + testDistance);
						// if useful, swap
						if (testDistance < currentDistance) {
							cont = true;
							System.out.println("swap!");
							for (int k = last - 1; k > (first + last) / 2; k--) {
								path.swap(k, first + (last - k), distances);
							}
						}
					}
				}
			}
		}
		System.out.println("optimized!");
		return path;
	}
}

