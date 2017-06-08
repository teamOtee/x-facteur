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

import xfacteur.MailmanController;
import xfacteur.model.Mailman;

public class MailmanView extends Group {
	protected VBox view = new VBox(8);
	protected GridPane grid = new GridPane();
	protected ListView<Mailman> mailmanList = new ListView<Mailman>();
	protected Text listHeader = new Text();
	protected Button addBtn = new Button("Ajouter…");
	protected Button editBtn = new Button("Éditer…");
	protected Button delBtn = new Button("Supprimer");

	public MailmanView(ObservableList<Mailman> items) {
		mailmanList.setItems(items);
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
		view.getChildren().addAll(grid, listHeader, mailmanList);
		this.getChildren().add(view);
	}

	protected void makeInteractivity() {
		// add button
		addBtn.setOnAction(e -> {
			MailmanController.openAddModal();
			updateListHeader();
		});

		// disable edit and delete buttons if selection is empty
		editBtn.setDisable(true);
		delBtn.setDisable(true);
		mailmanList.getSelectionModel().selectedItemProperty().addListener(e -> {
			editBtn.setDisable(mailmanList.getSelectionModel().isEmpty());
			delBtn.setDisable(mailmanList.getSelectionModel().isEmpty());
		});

		// edit button
		editBtn.setOnAction(e -> {
			int selectedIndex = mailmanList.getSelectionModel().getSelectedIndices().get(0);
			MailmanController.openEditModal(selectedIndex);
			updateListHeader();
		});

		// delete button
		delBtn.setOnAction(e -> {
			int selectedIndex = mailmanList.getSelectionModel().getSelectedIndices().get(0);
			MailmanController.removeItem(selectedIndex);
			updateListHeader();
		});
	}

	protected void updateListHeader() {
		listHeader.setText(mailmanList.getItems().size() + " Facteur" + (mailmanList.getItems().size() != 1 ? "s" : ""));
	}
}
