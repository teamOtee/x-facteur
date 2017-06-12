package xfacteur.view;

import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.control.TableView;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.Button;
import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.geometry.Pos;
import javafx.scene.layout.Priority;

import xfacteur.XFacteur;
import xfacteur.model.Path;
import xfacteur.PathController;

public class PathView extends Stage {
	// attributes
	protected VBox container = new VBox(8);
	protected TabPane tabs = new TabPane();
	protected Button closeBtn = new Button("Fermer");

	// constructor
	public PathView() {
		this.setTitle("Affichage des chemins — X Facteur");
		this.getIcons().add(new Image(XFacteur.LOGOPATH));
		this.setResizable(false);
		this.setScene(new Scene(container));
		makeLayout();
		makeInteractivity();
	}

	// methods
	protected void makeLayout() {
		this.setWidth(800);
		tabs.setTabClosingPolicy(TabPane.TabClosingPolicy.UNAVAILABLE);
		tabs.setTabMinWidth(120);
		VBox.setVgrow(tabs, Priority.ALWAYS);
		container.setAlignment(Pos.CENTER_RIGHT);
		VBox.setMargin(closeBtn, new Insets(20));
		container.getChildren().addAll(tabs, closeBtn);
	}

	@SuppressWarnings("unchecked")
	public void reload() {
		tabs.getTabs().clear();
		for (Path path: PathController.getItems()) {
			// create one tab per path
			VBox container = new VBox(8);
			container.setPadding(new Insets(20));
			Text header = new Text("" + path.getMailman());

			TableView<Path.PathStep> pathTable = new TableView<Path.PathStep>();	
			pathTable.setItems(FXCollections.observableList(path.getSteps()));
			
			TableColumn<Path.PathStep, String> shipmentCol = new TableColumn<Path.PathStep, String>("Envois");
			shipmentCol.setCellValueFactory(new PropertyValueFactory<Path.PathStep, String>("shipment"));
			
			TableColumn<Path.PathStep, String> distanceToNextCol = new TableColumn<Path.PathStep, String>("Distances au suivant (km)");
			distanceToNextCol.setCellValueFactory(new PropertyValueFactory<Path.PathStep, String>("distanceToNext"));
			
			TableColumn<Path.PathStep, String> sumDistanceCol = new TableColumn<Path.PathStep, String>("Distances cumulées (km)");
			sumDistanceCol.setCellValueFactory(new PropertyValueFactory<Path.PathStep, String>("sumDistance"));
			
			pathTable.getColumns().setAll(shipmentCol, distanceToNextCol, sumDistanceCol);
			
			container.getChildren().addAll(header, pathTable);
			String tabName = path.getMailman().getName().charAt(0) + ". " + path.getMailman().getLastName() + " (" + path.size() + ")";
			tabs.getTabs().add(new Tab(tabName, container));
		}
	}

	protected void makeInteractivity() {
		closeBtn.setOnAction(e -> {
			this.close();
		});
	}
}

