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
	protected static ObservableList<Path> items = FXCollections.observableArrayList();
	protected static PathView view = new PathView();

	public static void generate() {
		items.addAll(PathGenerator.genPaths(MailmanController.getItems(), ShipmentController.getItems()).values());
	}

	public static void display() {
		//do something to mapView perhaps?
		System.out.println("It worked!");
		System.out.println("Paths generated for " + items.size() + " mailmen");
		for (Path p: items) {
			System.out.println(p.getMailman() + ": " + p.size() + " shipments, " + p.sumDistance() + " km");
		}
		if (!items.isEmpty()) {
			view.setPath(items.get(0));
			view.show();
		}
	}

	public static ObservableList<Path> getItems() { return items; }
}

