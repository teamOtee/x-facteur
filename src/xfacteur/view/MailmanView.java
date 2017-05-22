package xfacteur.view;

import javafx.collections.FXCollections;
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

import xfacteur.model.Mailman;

public class MailmanView extends Group {
	protected VBox view = new VBox(8);
	protected GridPane grid = new GridPane();
	protected ListView<Mailman> mailmanList = new ListView<Mailman>();
	protected Label lastnameL = new Label("Nom :");
	protected Label nameL = new Label("Prénom :");
	protected TextField lastname = new TextField();
	protected TextField name = new TextField();
	protected Label drivingL = new Label("Permis :");
	protected CheckBox driving = new CheckBox();
	protected Text formTitle = new Text("Ajouter un facteur");
	protected Button addBtn = new Button("Ajouter");
	
	public MailmanView() {
		Mailman m1 = new Mailman(true,"Garcia","José");
		Mailman m2 = new Mailman(false,"Gonzalo","Michalon");
		mailmanList.setItems(FXCollections.observableArrayList(m1, m2));

		//form grid
		grid.setAlignment(Pos.CENTER);
		grid.setHgap(10);
		grid.setVgap(10);
		grid.setPadding(new Insets(25, 25, 25, 25));
		formTitle.setFont(Font.font("sans-serif", 14));
		grid.add(formTitle, 0, 1, 2, 1);
		grid.add(lastnameL, 0, 2);
		grid.add(lastname, 1, 2);
		grid.add(nameL, 0, 3);
		grid.add(name, 1, 3);
		grid.add(drivingL, 0, 4);
		grid.add(driving, 1, 4);
		grid.add(addBtn, 0, 5);

		//form + list in a vbox
		view.getChildren().addAll(grid, mailmanList);
		this.getChildren().add(view);
	}
}

