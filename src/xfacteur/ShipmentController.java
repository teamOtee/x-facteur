package xfacteur;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import java.util.Optional;

import xfacteur.model.Shipment;
import xfacteur.view.ShipmentView;
import xfacteur.view.ShipmentEditModal;

public class ShipmentController {
	/*
	 * A controller class for the shipments Its goal is to manage model (data)
	 * and view for the shipments and be the interface for everything concerning
	 * shipments for the main program. Methods and attributes are static
	 */

	// inaccessible constructor
	protected ShipmentController() {
	}

	// attributes
	protected static ObservableList<Shipment> items = FXCollections
			.observableArrayList(new Shipment("IUT", "Lannion", true), new Shipment("Mairie", "Lannion", false));
	protected static ShipmentView view = new ShipmentView(items);
	protected static ShipmentEditModal editModal = new ShipmentEditModal();

	// simple getters and setters
	public static ObservableList<Shipment> getItems() {
		return items;
	}

	public static Shipment getItem(int i) {
		return items.get(i);
	}

	public static ShipmentView getView() {
		return view;
	}

	public static void setItems(ObservableList<Shipment> items) {
		ShipmentController.items = items;
	}

	public static void setItem(int i, Shipment s) {
		items.set(i, s);
	}

	public static void addItem(Shipment s) {
		items.add(s);
	}

	public static void removeItem(Shipment s) {
		if (deletionConfirmation()) {
			items.remove(s);
		}
	}

	public static void removeItem(int i) {
		removeItem(getItem(i));
	}

	// window opening methods
	public static void openAddModal() {
		editModal.initValue(null);
		editModal.showAndWait();
		Shipment inputS = editModal.getValue();
		if (inputS != null) {
			addItem(inputS);
		}
	}

	public static void openEditModal(Shipment s) {
		editModal.initValue(s);
		editModal.showAndWait();
		Shipment inputS = editModal.getValue();
		if (inputS != null) {
			setItem(items.indexOf(s), inputS);
		}
	}

	public static void openEditModal(int i) {
		openEditModal(getItem(i));
	}

	public static boolean deletionConfirmation() {
		Alert confirmModal = new Alert(Alert.AlertType.WARNING, "Êtes-vous sûr de vouloir supprimer cet envoi ?",
				ButtonType.OK, ButtonType.CANCEL);
		Optional<ButtonType> result = confirmModal.showAndWait();
		return result.isPresent() && result.get() == ButtonType.OK;
	}
}
