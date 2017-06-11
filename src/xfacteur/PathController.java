package xfacteur;

import javafx.collections.ObservableList;
import javafx.collections.FXCollections;

import xfacteur.model.Path;
import xfacteur.model.PathGenerator;
import xfacteur.model.Shipment;
import xfacteur.view.PathView;
import xfacteur.view.MapView;

public class PathController {
	//inaccessible constructor
	protected PathController() {}

	//attributes
	protected static ObservableList<Path> items = FXCollections.observableArrayList();
	protected static Shipment postOffice = new Shipment("La Poste", "Lannion", false);
	protected static double maxTime = 1.0;
	protected static PathView pathView = new PathView();
	protected static MapView mapView = new MapView();

	public static void generate() {
		items.setAll(PathGenerator.genPaths().values());
	}

	public static void display() {
		//do something to mapView perhaps?
		if (!items.isEmpty()) {
			pathView.reload();
			pathView.show();
		}
	}

	public static MapView getMapView() { return mapView; }
	public static ObservableList<Path> getItems() { return items; }
	public static Shipment getWalkingPO() { return new Shipment(postOffice.getStreet(), postOffice.getCity(), false); }
	public static Shipment getDrivingPO() { return new Shipment(postOffice.getStreet(), postOffice.getCity(), true); }
	public static double getMaxTime() { return maxTime; }
	public static void setPostOffice(Shipment postOffice) {
		PathController.postOffice = postOffice;
	}
	public static void setMaxTime(double maxTime) {
		PathController.maxTime = maxTime;
	}
}

