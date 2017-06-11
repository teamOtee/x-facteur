package xfacteur.view;

import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.control.TableView;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;

import xfacteur.XFacteur;
import xfacteur.model.Path;
import xfacteur.PathController;

public class PathView extends Stage {
	// attributes
	protected TabPane tabs = new TabPane();

	// constructor
	public PathView() {
		this.setTitle("Affichage d’un chemin — X Facteur");
		this.getIcons().add(new Image(XFacteur.LOGOPATH));
		this.setResizable(false);
		this.setScene(new Scene(tabs));
		makeLayout();
		makeInteractivity();
	}

	// methods
	protected void makeLayout() {
		this.setWidth(800);
		tabs.setTabClosingPolicy(TabPane.TabClosingPolicy.UNAVAILABLE);
		tabs.setTabMinWidth(120);
	}

	public void reload() {
		tabs.getTabs().clear();
		for (Path path: PathController.getItems()) {
			// create one tab per path
			VBox container = new VBox(8);
			container.setPadding(new Insets(10, 20, 12, 20));
			Text header = new Text("" + path.getMailman());

			TableView<Path.PathStep> pathTable = new TableView<Path.PathStep>();	
			pathTable.setItems(FXCollections.observableList(path.getSteps()));
			
			TableColumn<Path.PathStep, String> shipmentCol = new TableColumn<Path.PathStep, String>("Envois");
			shipmentCol.setCellValueFactory(new PropertyValueFactory("shipment"));
			
			TableColumn<Path.PathStep, String> distanceToNextCol = new TableColumn<Path.PathStep, String>("Distances au suivant (km)");
			distanceToNextCol.setCellValueFactory(new PropertyValueFactory("distanceToNext"));
			
			TableColumn<Path.PathStep, String> sumDistanceCol = new TableColumn<Path.PathStep, String>("Distances cumulées (km)");
			sumDistanceCol.setCellValueFactory(new PropertyValueFactory("sumDistance"));
			
			pathTable.getColumns().setAll(shipmentCol, distanceToNextCol, sumDistanceCol);
			
			container.getChildren().addAll(header, pathTable);
			tabs.getTabs().add(new Tab(path.getMailman().getLastName(), container));
		}
	}

	protected void makeInteractivity() {
		// rien pour l’instant
	}
}

