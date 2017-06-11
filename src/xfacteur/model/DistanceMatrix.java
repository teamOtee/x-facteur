package xfacteur.model;

import java.util.List;

public class DistanceMatrix {
	protected List<Shipment> shipments;
	protected double[][] values;

	public DistanceMatrix(List<Shipment> shipments) {
		this.shipments = shipments;
		this.values = new double[shipments.size()][shipments.size()];
		for (int i = 0; i < shipments.size(); i++) {
			for (int j = 0; j < shipments.size(); j++) {
				values[i][j] = -1;
			}
		}
	}

	public void set(Shipment from, Shipment to, double value) {
		if (shipments.contains(from) && shipments.contains(to)) {
			values[shipments.indexOf(from)][shipments.indexOf(to)] = value;
		}
	}

	public double get(Shipment from, Shipment to) {
		if (shipments.contains(from) && shipments.contains(to)) {
			return values[shipments.indexOf(from)][shipments.indexOf(to)];
		} else {
			return -1;
		}
	}

	public List<Shipment> getShipments() { return shipments; }
}

