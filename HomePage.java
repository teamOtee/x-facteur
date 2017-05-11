import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Rectangle;
import javafx.scene.paint.Color;

public class HomePage extends Stage {
	private BorderPane bordP = new BorderPane();
	private VBox right = new VBox(8);

	public HomePage() {
		//Paramètres Page d'accueil
		this.getIcons().add(new Image(
				"http://www.reponse-conso.fr/system/application/views/images/visuels/article/logo-de-la-poste-7768_294x294.png"));
		this.setMaximized(true);
		this.setScene(new Scene(content()));
		this.setTitle("X Facteur");
	}

	private BorderPane content() {
		bordP = new BorderPane();
		right.getChildren().addAll(new MailmanView(), new Rectangle(200, 200, Color.BLUE));
		bordP.setRight(right);
		bordP.setCenter(new Rectangle(400, 400, Color.RED));
		return bordP;
	}
}

