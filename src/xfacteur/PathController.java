package xfacteur;

import javafx.collections.ObservableList;
import javafx.collections.FXCollections;

import xfacteur.model.Path;

public class PathController {
	//inaccessible constructor
	protected PathController() {}

	//attributes
	protected static ObservableList<Path> paths = FXCollections.observableArrayList();

	public static void generate() {
		//paths = PathGenerator.genPaths(MailmanController.getItems(), ShipmentController.getItems());
	}

	public static void display() {
		//do something to mapView perhaps?
	}
}

