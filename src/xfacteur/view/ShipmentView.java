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
	protected Text listHeader = new Text(shipmentList.getItems().size() + " envois");
	protected Button addBtn = new Button("Ajouter");
	protected Button editBtn = new Button("Éditer");
	protected Button delBtn = new Button("Supprimer");
	
	public ShipmentView(ObservableList<Shipment> items) {
		shipmentList.setItems(items);
		makeLayout();
		makeInteractivity();
	}

	protected void makeLayout() {
		//form grid
		grid.setAlignment(Pos.CENTER_LEFT);
		grid.setHgap(4);
		grid.setVgap(8);
		grid.setPadding(new Insets(20, 20, 20, 20));
		listHeader.setFont(Font.font("sans-serif", 14));
		grid.add(addBtn, 0, 0);
		grid.add(editBtn, 0, 1);
		grid.add(delBtn, 0, 2);

		//form + list in a vbox
		view.getChildren().addAll(grid, listHeader, shipmentList);
		this.getChildren().add(view);
	}

	protected void makeInteractivity() {
		addBtn.setOnAction(e -> {
			ShipmentController.addItem(ShipmentController.openAddModal());
			listHeader.setText(shipmentList.getItems().size() + " envois");
		});
	}
}

