import javafx.collections.FXCollections;
import javafx.scene.layout.GridPane;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MailmanView extends Group {
	private GridPane grid = new GridPane();
	private Scene scene = new Scene(grid);
	private ListView<Mailman> mailmanList = new ListView<Mailman>();
	private ObservableList<Mailman> mailmanObsList;
	private Label lastname = new Label("Nom du facteur :");
	private Label name = new Label("Prénom du facteur :");
	private TextField lastnameMailman = new TextField();
	private TextField nameMailman = new TextField();
	private Label drivingL = new Label("Permis :");
	private CheckBox driving = new CheckBox();
	private Text scenetitle = new Text("Bienvenue sur X Facteur");
	private Text scenesubtitle = new Text("Ajout des Facteurs");
	private Button addbtn = new Button("Ajouter");
	
	public MailmanView() {
		Mailman p1 = new Mailman(true,"Garcia","José");
		Mailman p2 = new Mailman(false,"Gonzalo","Michalon");
		this.mailmanObsList = FXCollections.observableArrayList(p1,p2);
		this.mailmanList.setItems(mailmanObsList);

		//Paramètres grid
		grid.setAlignment(Pos.CENTER);
		grid.setHgap(10);
		grid.setVgap(10);
		grid.setPadding(new Insets(25, 25, 25, 25));
		scenetitle.setFont(Font.font("sans-serif", 20));
		scenesubtitle.setFont(Font.font("sans-serif", 14));
		grid.add(scenetitle, 0, 0, 2, 1);
		grid.add(scenesubtitle, 0, 1, 2, 1);
		grid.add(lastname, 0, 2);
		grid.add(lastnameMailman, 1, 2);
		grid.add(name, 0, 3);
		grid.add(nameMailman, 1, 3);
		grid.add(drivingL, 0, 4);
		grid.add(driving, 1, 4);
		HBox hbBtn = new HBox(10);
		hbBtn.setAlignment(Pos.BOTTOM_RIGHT);
		hbBtn.getChildren().add(addbtn);
		VBox vbBtn = new VBox(10);
		vbBtn.setAlignment(Pos.TOP_RIGHT);
		vbBtn.getChildren().add(mailmanList);
		grid.add(vbBtn, 2, 0,2,7);
		grid.add(hbBtn, 1, 5);
		grid.setGridLinesVisible(false);
		this.getChildren().add(grid);
	}
}

