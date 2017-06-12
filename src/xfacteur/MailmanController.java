package xfacteur;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import java.util.Optional;

import xfacteur.model.Mailman;
import xfacteur.view.MailmanView;
import xfacteur.view.MailmanEditModal;

public class MailmanController {
	/*
	 * A controller class for the Mailmans Its goal is to manage model (data)
	 * and view for the Mailmans and be the interface for everything concerning
	 * Mailmans for the main program. Methods and attributes are static
	 */

	// inaccessible constructor
	protected MailmanController() {}

	// attributes
	protected static ObservableList<Mailman> items = FXCollections
		.observableArrayList(new Mailman(true, "STALLMAN", "Richard"), new Mailman(false, "GATES", "Bill"));
	protected static MailmanView view = new MailmanView(items);
	protected static MailmanEditModal editModal = new MailmanEditModal();

	// simple getters and setters
	public static ObservableList<Mailman> getItems() {
		return items;
	}

	public static Mailman getItem(int i) {
		return items.get(i);
	}

	public static MailmanView getView() {
		return view;
	}

	public static void setItems(ObservableList<Mailman> items) {
		MailmanController.items.setAll(items);
	}

	public static void setItem(int i, Mailman m) {
		items.set(i, m);
	}

	public static void addItem(Mailman m) {
		items.add(m);
	}

	public static void removeItem(Mailman m) {
		if (deletionConfirmation(m)) {
			items.remove(m);
		}
	}

	public static void removeItem(int i) {
		removeItem(getItem(i));
	}

	// window opening methods
	public static void openAddModal() {
		editModal.initValue(null);
		editModal.showAndWait();
		Mailman inputS = editModal.getValue();
		if (inputS != null) {
			addItem(inputS);
		}
	}

	public static void openEditModal(Mailman m) {
		editModal.initValue(m);
		editModal.showAndWait();
		Mailman inputM = editModal.getValue();
		if (inputM != null) {
			setItem(items.indexOf(m), inputM);
		}
	}

	public static void openEditModal(int i) {
		openEditModal(getItem(i));
	}

	public static boolean deletionConfirmation(Mailman m) {
		Alert confirmModal = new Alert(Alert.AlertType.WARNING, "" + m, ButtonType.OK, ButtonType.CANCEL);
		confirmModal.setHeaderText("Êtes-vous sûr de vouloir supprimer ce facteur ?");
		Optional<ButtonType> result = confirmModal.showAndWait();
		return result.isPresent() && result.get() == ButtonType.OK;
	}
}
