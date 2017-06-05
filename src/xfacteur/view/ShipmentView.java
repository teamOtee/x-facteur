package xfacteur.view;

import javafx.collections.ObservableList;
import javafx.scene.layout.GridPane;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.Group;

import xfacteur.model.Shipment;
import xfacteur.ShipmentController;

public class ShipmentView extends Group {
	protected VBox view = new VBox(8);
	protected GridPane grid = new GridPane();
	protected ListView<Shipment> shipmentList = new ListView<Shipment>();
	protected Label addressStreetL = new Label("N° et rue :");
	protected Label addressCityL = new Label("Ville :");
	protected TextField addressStreet = new TextField();
	protected TextField addressCity = new TextField();
	protected Label drivingL = new Label("En voiture :");
	protected CheckBox driving = new CheckBox();
	protected Text formTitle = new Text("Ajouter un envoi");
	protected Button addBtn = new Button("Ajouter");
	
	public ShipmentView(ObservableList<Shipment> items) {
		shipmentList.setItems(items);
		makeLayout();
		makeInteractivity();
	}

	protected void makeLayout() {
		//form grid
		grid.setAlignment(Pos.CENTER);
		grid.setHgap(10);
		grid.setVgap(10);
		grid.setPadding(new Insets(25, 25, 25, 25));
		formTitle.setFont(Font.font("sans-serif", 14));
		grid.add(formTitle, 0, 1, 2, 1);
		grid.add(addressStreetL, 0, 2);
		grid.add(addressStreet, 1, 2);
		grid.add(addressCityL, 0, 3);
		grid.add(addressCity, 1, 3);
		grid.add(drivingL, 0, 4);
		grid.add(driving, 1, 4);
		grid.add(addBtn, 0, 5);

		//form + list in a vbox
		view.getChildren().addAll(grid, shipmentList);
		this.getChildren().add(view);
	}

	protected void makeInteractivity() {
		//add button
		addBtn.setDisable(true);
		addressStreet.setOnKeyReleased(e -> {
			addBtn.setDisable(addressStreet.getText().isEmpty() || addressCity.getText().isEmpty());
		});
		addressCity.setOnKeyReleased(e -> {
			addBtn.setDisable(addressStreet.getText().isEmpty() || addressCity.getText().isEmpty());
		});
		addBtn.setOnAction(e -> {
			ShipmentController.addItem(new Shipment(addressStreet.getText(), addressCity.getText(), driving.isSelected()));
			clearForm();
		});

		//when an item is selected
		shipmentList.setEditable(true);
		shipmentList.setOnEditStart(e -> {
			addBtn.setText("Enregistrer");
			addBtn.setDisable(false);
			addressStreet.setText(ShipmentController.getItem(e.getIndex()).getStreet());
			addressCity.setText(ShipmentController.getItem(e.getIndex()).getCity());
		});
	}

	protected void clearForm() {
		addressStreet.clear();
		addressCity.clear();
	}
}

