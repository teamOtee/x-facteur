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

public class MapInJFX extends Application {
	public static void main(String[] args) {
		Application.launch();
	}

	public void start(Stage myWin) {
		MapView mapView = new MapView();
		mapView.show();
	}
}

class MapView extends Stage {
	protected ObservableList<String> places;
	protected double[][] distances;

	public MapView() {
		this.places = FXCollections.observableArrayList("IUT de Lannion", "Mairie de Lannion", "Hôpital de Lannion");
		this.distances = DistanceMatrixHTTPGetter.getDistanceMatrix(places);
		this.setTitle("A map view");
		this.setResizable(true);
		this.setScene(new Scene(content()));
		this.sizeToScene();
	}

	private BorderPane content() {
		//generate static content here
		BorderPane bordP = new BorderPane();

		//center
		GridPane center = new GridPane();
		for (int i = 0; i < places.size(); i++) {
			center.add(new Text(places.get(i)), i + 1, 0);
			center.add(new Text(places.get(i)), 0, i + 1);
			for (int j = 0; j < places.size(); j++) {
				center.add(new Text(distances[i][j] + "km"), j + 1, i + 1);
			}
		}
		bordP.setCenter(center);

		//top
		Text top = new Text("X Facteur");
		top.setFont(new Font(24));
		bordP.setTop(top);
		BorderPane.setAlignment(top, Pos.CENTER);

		//right
		VBox right = new VBox(5);
		ListView<String> placesList = new ListView<String>(places);
		Button rightBtn = new Button("OK (0)");
		right.getChildren().addAll(new Label("Lieux :"), placesList, rightBtn);
		VBox.setVgrow(placesList, Priority.ALWAYS);
		bordP.setRight(right);

		//now generate the interactions
		RightBtnAction rightBtnAction = new RightBtnAction(rightBtn, placesList);
		rightBtn.setOnAction(rightBtnAction);

		//end of content generation
		return bordP;
	}
}

class RightBtnAction implements EventHandler<ActionEvent> {
	protected int nbPressed;
	protected Button rightBtn;
	protected ListView<String> placesList;
	protected double[][] distances;

	public RightBtnAction(Button rightBtn, ListView<String> placesList) {
		this.rightBtn = rightBtn;
		this.placesList = placesList;
		this.distances = distances;
		this.nbPressed = 0;
	}

	public void handle(ActionEvent evt) {
		String focusedPlace = placesList.getFocusModel().getFocusedItem();
		rightBtn.setText("OK (" + ++nbPressed + ", " + focusedPlace + ")");
	}
}

