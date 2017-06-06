package xfacteur.view;

import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import xfacteur.ShipmentController;
import xfacteur.model.Shipment;

public class ShipmentView extends Group {
	protected VBox view = new VBox(8);
	protected GridPane grid = new GridPane();
	protected ListView<Shipment> shipmentList = new ListView<Shipment>();
	protected Text listHeader = new Text();
	protected Button addBtn = new Button("Ajouter…");
	protected Button editBtn = new Button("Éditer…");
	protected Button delBtn = new Button("Supprimer");

	public ShipmentView(ObservableList<Shipment> items) {
		shipmentList.setItems(items);
		makeLayout();
		makeInteractivity();
		updateListHeader();
	}

	protected void makeLayout() {
		// form grid
		grid.setAlignment(Pos.CENTER_LEFT);
		grid.setHgap(4);
		grid.setVgap(8);
		grid.setPadding(new Insets(20));
		listHeader.setFont(Font.font("sans-serif", 14));
		grid.add(addBtn, 0, 0);
		grid.add(editBtn, 1, 0);
		grid.add(delBtn, 2, 0);

		// form + list in a vbox
		view.getChildren().addAll(grid, listHeader, shipmentList);
		this.getChildren().add(view);
	}

	protected void makeInteractivity() {
		// add button
		addBtn.setOnAction(e -> {
			ShipmentController.openAddModal();
			updateListHeader();
		});

		// disable edit and delete buttons if selection is empty
		editBtn.setDisable(true);
		delBtn.setDisable(true);
		shipmentList.getSelectionModel().selectedItemProperty().addListener(e -> {
			editBtn.setDisable(shipmentList.getSelectionModel().isEmpty());
			delBtn.setDisable(shipmentList.getSelectionModel().isEmpty());
		});

		// edit button
		editBtn.setOnAction(e -> {
			int selectedIndex = shipmentList.getSelectionModel().getSelectedIndices().get(0);
			ShipmentController.openEditModal(selectedIndex);
			updateListHeader();
		});

		// delete button
		delBtn.setOnAction(e -> {
			int selectedIndex = shipmentList.getSelectionModel().getSelectedIndices().get(0);
			ShipmentController.removeItem(selectedIndex);
			updateListHeader();
		});
	}

	protected void updateListHeader() {
		listHeader
				.setText(shipmentList.getItems().size() + " envoi" + (shipmentList.getItems().size() != 1 ? "s" : ""));
	}
}
