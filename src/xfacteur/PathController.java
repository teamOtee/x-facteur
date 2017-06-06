package xfacteur;

import javafx.collections.ObservableList;
import javafx.collections.FXCollections;

import xfacteur.model.Path;
import xfacteur.model.PathGenerator;

public class PathController {
	//inaccessible constructor
	protected PathController() {}

	//attributes
	protected static ObservableList<Path> paths = FXCollections.observableArrayList();

	public static void generate() {
		paths.addAll(PathGenerator.genPaths(MailmanController.getItems(), ShipmentController.getItems()).values());
	}

	public static void display() {
		//do something to mapView perhaps?
		System.out.println("It worked!");
		System.out.println("Paths generated for " + paths.size() + " mailmen");
		for (Path p: paths) {
			System.out.println(p.getMailman() + ": " + p.size() + " shipments, " + p.sumDistance() + " km");
		}
	}
}

