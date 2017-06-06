package xfacteur.model;

import java.util.List;

public class DistanceMatrix {
	protected List<Shipment> shipments;
	protected double[][] values;

	public DistanceMatrix(List<Shipment> shipments, double[][] values) {
		this.shipments = shipments;
		this.values = values;
	}

	public double getDistance(Shipment from, Shipment to) {
		if (shipments.contains(from) && shipments.contains(to)) {
			return values[shipments.indexOf(from)][shipments.indexOf(to)];
		} else {
			return -1;
		}
	}

	public List<Shipment> getShipments() { return shipments; }
}

