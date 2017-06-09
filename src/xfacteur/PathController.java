package xfacteur;

import javafx.collections.ObservableList;
import javafx.collections.FXCollections;

import xfacteur.model.Path;
import xfacteur.model.PathGenerator;
import xfacteur.view.PathView;

public class PathController {
	//inaccessible constructor
	protected PathController() {}

	//attributes
	protected static ObservableList<Path> paths = FXCollections.observableArrayList();
	protected static PathView view = new PathView();

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

	public static PathView getView() { return view; }
}

