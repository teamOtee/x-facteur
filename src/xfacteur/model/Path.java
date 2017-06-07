package xfacteur.model;

import java.util.LinkedList;

//this class represents a path for a single mailman
public class Path {
	//a nested class
	public static class PathStep {
		protected Shipment shipment;
		protected double distanceToNext;
		
		public PathStep(Shipment shipment, double distanceToNext) {
			this.shipment = shipment;
			this.distanceToNext = distanceToNext;
		}

		public void setShipment(Shipment shipment) {
			this.shipment = shipment;
		}

		public void setDistanceToNext(double distanceToNext) {
			this.distanceToNext = distanceToNext;
		}

		public Shipment getShipment() { return shipment; }
		public double getDistanceToNext() { return distanceToNext; }
	}

	//attributes
	protected Mailman mailman;
	protected LinkedList<PathStep> steps;

	public Path(Mailman mailman, Shipment startPoint) {
		this.mailman = mailman;
		this.steps = new LinkedList<PathStep>();
		steps.add(new PathStep(startPoint, 0));
	}

	public Mailman getMailman() { return mailman; }
	public LinkedList<PathStep> getSteps() { return steps; }
	public Shipment getShipment(int i) { return steps.get(i).getShipment(); }
	public double getDistanceToNext(int i) { return steps.get(i).getDistanceToNext(); }
	public int size() { return steps.size(); }
	public PathStep getLastStep() { return steps.getLast(); }

	public void add(Shipment s, DistanceMatrix distanceMatrix) {
		steps.add(new PathStep(s, distanceMatrix.getDistance(getLastStep().getShipment(), s)));
	}

	public double sumDistance() {
		double ret = 0;
		for (PathStep step: steps) {
			ret += step.getDistanceToNext();
		}
		return ret;
	}

	public void swap(int i1, int i2, DistanceMatrix distanceMatrix) {
		if ((i1 != i2) && (i1 >= 0) && (i2 >= 0) && (i1 < size()) && (i2 < size())) {
			PathStep step1 = steps.get(i1);
			PathStep step2 = steps.get(i2);
			steps.set(i1, step2);
			steps.set(i2, step1);
			if (i1 > 0) {
				steps.get(i1 - 1).setDistanceToNext(distanceMatrix.getDistance(getShipment(i1 - 1), getShipment(i1)));
			}
			if (i1 < size() - 1) {
				steps.get(i1).setDistanceToNext(distanceMatrix.getDistance(getShipment(i1), getShipment(i1 + 1)));
			} else {
				steps.get(i1).setDistanceToNext(distanceMatrix.getDistance(getShipment(i1), getShipment(0)));
			}
			if (i2 > 0) {
				steps.get(i2 - 1).setDistanceToNext(distanceMatrix.getDistance(getShipment(i2), getShipment(i2)));
			}
			if (i2 < size() - 1) {
				steps.get(i2).setDistanceToNext(distanceMatrix.getDistance(getShipment(i2), getShipment(i2 + 1)));
			} else {
				steps.get(i2).setDistanceToNext(distanceMatrix.getDistance(getShipment(i2), getShipment(0)));
			}
		}
	}
}

