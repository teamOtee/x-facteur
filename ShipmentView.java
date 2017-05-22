package xfacteur;

import javafx.collections.FXCollections;
import javafx.scene.layout.GridPane;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.Group;
import javafx.scene.Scene;

public class ShipmentView extends Group {
	private GridPane grid = new GridPane();
	private ListView<Mailman> ShipmentList = new ListView<Mailman>();
	private ObservableList<Shipment> ShipmentObsList;
	private Label lastname = new Label("Rue :");
	private Label city = new Label("Ville :");
	private TextField lastnameMailman = new TextField();
	private TextField nameMailman = new TextField();
	private Text scenetitle = new Text("Liste des Adresses");
	private Text scenesubtitle = new Text("Ajout Adresse");
	private Button addbtn = new Button("Ajouter");
	
	public ShipmentView() {
		Shipment ship1 = new Shipment("25 rue de la Famille","Lannion",true);
		Shipment ship2 = new Shipment("36 rue du Chat","Angers",false);
		this.ShipmentObsList = FXCollections.observableArrayList(ship1,ship2);
		this.ShipmentList.setItems(ShipmentObsList);

		//grid
		grid.setAlignment(Pos.CENTER);
		grid.setHgap(10);
		grid.setVgap(10);
		grid.setPadding(new Insets(25, 25, 25, 25));
		scenetitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
		scenesubtitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 14));
		grid.add(scenetitle, 0, 0, 2, 1);
		grid.add(scenesubtitle, 0, 1, 2, 1);
		grid.add(lastname, 0, 2);
		grid.add(lastnameMailman, 1, 2);
		grid.add(city, 0, 3);
		grid.add(nameMailman, 1, 3);
		HBox hbBtn = new HBox(10);
		hbBtn.setAlignment(Pos.BOTTOM_RIGHT);
		hbBtn.getChildren().add(addbtn);
		VBox vbBtn = new VBox(10);
		vbBtn.setAlignment(Pos.TOP_RIGHT);
		vbBtn.getChildren().add(ShipmentList);
		grid.add(vbBtn, 2, 0,2,7);
		grid.add(hbBtn, 1, 5);
		grid.setGridLinesVisible(false);
		this.getChildren().add(grid);
	}
}