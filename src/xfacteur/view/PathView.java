package xfacteur.view;

import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn;
import javafx.collections.FXCollections;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;

import xfacteur.XFacteur;
import xfacteur.model.Path;

public class PathView extends Stage {
	// attributes
	protected VBox container = new VBox(4);
	protected Text header = new Text("Un chemin");
	protected TableView<Path.PathStep> pathTable = new TableView<Path.PathStep>();
	protected Path path;

	// constructor
	public PathView() {
		this.setTitle("Affichage d’un chemin — X Facteur");
		this.getIcons().add(new Image(XFacteur.LOGOPATH));
		this.setResizable(false);
		this.setScene(new Scene(container));
		makeLayout();
		makeInteractivity();
		this.setWidth(600);
	}

	// methods
	public void setPath(Path path) {
		this.path = path;
		header.setText("" + path.getMailman() + ", " + path.size() + " envois");
		pathTable.setItems(FXCollections.observableList(path.getSteps()));
	}

	protected void makeLayout() {
		pathTable.setItems(FXCollections.emptyObservableList());
		TableColumn<Path.PathStep, String> shipmentCol = new TableColumn<Path.PathStep, String>("Envois");
		shipmentCol.setCellValueFactory(new PropertyValueFactory("shipment"));
		TableColumn<Path.PathStep, String> distanceToNextCol = new TableColumn<Path.PathStep, String>(
				"Distances au suivant (km)");
		distanceToNextCol.setCellValueFactory(new PropertyValueFactory("distanceToNext"));
		TableColumn<Path.PathStep, String> sumDistanceCol = new TableColumn<Path.PathStep, String>(
				"Distances cumulées (km)");
		sumDistanceCol.setCellValueFactory(new PropertyValueFactory("sumDistance"));
		pathTable.getColumns().setAll(shipmentCol, distanceToNextCol, sumDistanceCol);
		container.getChildren().addAll(header, pathTable);
	}

	protected void makeInteractivity() {
		// rien pour l’instant
	}
}

