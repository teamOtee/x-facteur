package xfacteur.model;

import javafx.beans.property.StringProperty;
import javafx.beans.property.SimpleStringProperty;
import java.util.LinkedList;
import java.util.Arrays;
import com.google.maps.model.DistanceMatrix;

import xfacteur.PathController;

//this class represents a path for a single mailman
public class Path {
	//a nested class
	public static class PathStep {
		protected final Path path;
		protected Shipment shipment;
		protected double distanceToNext;
		
		public PathStep(Path path, Shipment shipment, double distanceToNext) {
			this.path = path;
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

		public StringProperty shipmentProperty() {
			return new SimpleStringProperty("" + shipment);
		}

		public StringProperty distanceToNextProperty() {
			return new SimpleStringProperty("" + distanceToNext);
		}

		public StringProperty sumDistanceProperty() {
			return new SimpleStringProperty("" + path.sumDistance(this));
		}
	}

	//attributes
	protected Mailman mailman;
	protected LinkedList<PathStep> steps;

	public Path(Mailman mailman, Shipment startPoint) {
		this.mailman = mailman;
		this.steps = new LinkedList<PathStep>();
		steps.add(new PathStep(this, startPoint, 0.0));
	}

	public Mailman getMailman() { return mailman; }
	public LinkedList<PathStep> getSteps() { return steps; }
	public Shipment getShipment(int i) { return steps.get(i % size()).getShipment(); }
	public double getDistanceToNext(int i) { return steps.get(i % size()).getDistanceToNext(); }
	public int size() { return steps.size(); }
	public PathStep getLast() { return steps.getLast(); }

	public boolean contains(Shipment s) {
		for (PathStep step: steps) {
			if (step.getShipment() == s) {
				return true;
			}
		}
		return false;
	}

	public void add(Shipment s, DistanceMatrix distances) {
		steps.getLast().setDistanceToNext(PathController.distance(steps.getLast().getShipment(), s, distances));
		steps.add(new PathStep(this, s, 0.0));
	}

	public double sumDistance() {
		double ret = 0.0;
		for (PathStep step: steps) {
			ret += step.getDistanceToNext();
		}
		return ret;
	}

	public double sumDistance(int lim) {
		double ret = 0.0;
		for (int i = 0; i <= lim && i < steps.size(); i++) {
			ret += steps.get(i).getDistanceToNext();
		}
		return ret;
	}

	public double sumDistance(PathStep limStep) {
		return sumDistance(steps.indexOf(limStep));
	}

	public void swap(int i1, int i2, DistanceMatrix distances) {
		if ((i1 != i2) && (i1 >= 0) && (i2 >= 0) && (i1 < size()) && (i2 < size())) {
			PathStep step1 = steps.get(i1);
			PathStep step2 = steps.get(i2);
			steps.set(i1, step2);
			steps.set(i2, step1);
			if (i1 > 0) {
				steps.get(i1 - 1).setDistanceToNext(PathController.distance(getShipment(i1 - 1), getShipment(i1), distances));
			}
			if (i1 < size() - 1) {
				steps.get(i1).setDistanceToNext(PathController.distance(getShipment(i1), getShipment(i1 + 1), distances));
			} else {
				steps.get(i1).setDistanceToNext(PathController.distance(getShipment(i1), getShipment(0), distances));
			}
			if (i2 > 0) {
				steps.get(i2 - 1).setDistanceToNext(PathController.distance(getShipment(i2), getShipment(i2), distances));
			}
			if (i2 < size() - 1) {
				steps.get(i2).setDistanceToNext(PathController.distance(getShipment(i2), getShipment(i2 + 1), distances));
			} else {
				steps.get(i2).setDistanceToNext(PathController.distance(getShipment(i2), getShipment(0), distances));
			}
		}
	}
}

