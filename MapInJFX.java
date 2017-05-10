//core imports
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;

//groups
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.layout.GridPane;

//final nodes
import javafx.scene.text.Text;
import javafx.scene.Node;
import javafx.scene.control.ListView;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

//enumerations
import javafx.scene.text.Font;
import javafx.geometry.Pos;
import javafx.scene.layout.Priority;

//data types
import javafx.collections.ObservableList;
import javafx.collections.FXCollections;
import javafx.event.EventHandler;
import javafx.event.ActionEvent;
import javafx.scene.paint.Color;

public class MapInJFX extends Application {
	public static void main(String[] args) {
		Application.launch();
	}

	public void start(Stage myWin) {
		ObservableList<String> addresses = FXCollections.observableArrayList();
		addresses.add("IUT de Lannion");
		addresses.add("Mairie de Lannion");
		addresses.add("Angers");
		addresses.add("Rennes");
		addresses.add("Châlons-en-Champagne");
		addresses.add("Mont Saint Michel");
		MapView mapView = new MapView(FXCollections.observableArrayList("IUT de Lannion", "Mairie de Lannion", "Angers", "Rennes", "Châlons-en-Champagne", "Mont Saint Michel"));
		mapView.show();
	}
}

class MapView extends Stage {
	protected ObservableList<String> addresses;
	protected double[][] distances;
	protected BorderPane bordP = new BorderPane();
	protected GridPane center = new GridPane();
	protected Text top = new Text();
	protected VBox right = new VBox();
	protected ListView<String> addressesList = new ListView<String>();
	protected Button rightBtn = new Button();

	public MapView(ObservableList<String> addresses) {
		this.addresses = addresses;
		addressesList.setItems(addresses);
		distances = DistanceMatrixHTTPGetter.getDistanceMatrix(addresses);
		this.setTitle("A map view");
		this.setResizable(true);
		this.setScene(new Scene(content()));
		this.sizeToScene();
	}

	private BorderPane content() {
		//center
		for (int i = 0; i < addresses.size(); i++) {
			center.add(new Text(addresses.get(i)), i + 1, 0);
			center.add(new Text(addresses.get(i)), 0, i + 1);
			for (int j = 0; j < addresses.size(); j++) {
				center.add(new Text(distances[i][j] + "km"), j + 1, i + 1);
			}
		}
		bordP.setCenter(center);

		//top
		top.setText("X Facteur");
		top.setFont(new Font(24));
		bordP.setTop(top);
		BorderPane.setAlignment(top, Pos.CENTER);

		//right
		VBox.setVgrow(addressesList, Priority.ALWAYS);
		rightBtn.setText("OK");
		rightBtn.setOnAction(e -> {
			int focusedIndex = addressesList.getFocusModel().getFocusedIndex();
			for (Node t: center.getChildren()) {
				if ((GridPane.getRowIndex(t) == focusedIndex + 1) || (GridPane.getColumnIndex(t) == focusedIndex + 1)) {
					((Text) t).setFill(Color.RED);
				} else {
					((Text) t).setFill(Color.BLACK);
				}
			}
		});
		right.getChildren().addAll(new Label("Lieux :"), addressesList, rightBtn);
		bordP.setRight(right);

		//end of content generation
		return bordP;
	}
}

