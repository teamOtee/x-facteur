package xfacteur.model;

import java.util.LinkedList;

//this class represents a path for a single mailman
public class Path {
	protected Mailman mailman;
	protected LinkedList<Shipment> shipments;
	protected LinkedList<Double> distancesToNext;

	public Path(Mailman mailman) {
		this.mailman = mailman;
		this.shipments = new LinkedList<Shipment>();
		this.distancesToNext = new LinkedList<Double>();
	}

	public LinkedList<Shipment> getShipments() { return shipments; }
	public LinkedList<Double> getDistancesToNext() { return distancesToNext; }
	public Shipment getShipment(int i) { return shipments.get(i); }
	public double getDistanceToNext(int i) { return distancesToNext.get(i); }
	public int size() { return shipments.size(); }
	public Shipment getLastShipment() { return shipments.get(size() - 1); }

	public void add(Shipment s, DistanceMatrix distanceMatrix) {
		distancesToNext.set(size() - 1, distanceMatrix.getDistance(getLastShipment(), s));
		shipments.add(s);
		distancesToNext.add(distanceMatrix.getDistance(s, getShipment(0)));
	}

	public double sumDistance() {
		double ret = 0;
		for (Double d: distancesToNext) {
			ret += d;
		}
		return ret;
	}

	public void swap(Shipment s1, Shipment s2, DistanceMatrix distanceMatrix) {
		if ((s1 != s2) && shipments.contains(s1) && shipments.contains(s2) && distanceMatrix.getShipments().contains(s1) && distanceMatrix.getShipments().contains(s2)) {
			int i1 = shipments.indexOf(s1);
			int i2 = shipments.indexOf(s2);
			shipments.set(i1, s2);
			shipments.set(i2, s1);
			if (i1 > 0) {
				distancesToNext.set(i1 - 1, distanceMatrix.getDistance(shipments.get(i1 - 1), s2));
			}
			if (i1 < shipments.size() - 1) {
				distancesToNext.set(i1, distanceMatrix.getDistance(s2, shipments.get(i1 + 1)));
			} else {
				distancesToNext.set(i1, distanceMatrix.getDistance(s2, shipments.get(0)));
			}
			if (i2 > 0) {
				distancesToNext.set(i2 - 1, distanceMatrix.getDistance(shipments.get(i2 - 1), s1));
			}
			if (i2 < shipments.size() - 1) {
				distancesToNext.set(i2, distanceMatrix.getDistance(s1, shipments.get(i2 + 1)));
			} else {
				distancesToNext.set(i2, distanceMatrix.getDistance(s1, shipments.get(0)));
			}
		}
	}
}

