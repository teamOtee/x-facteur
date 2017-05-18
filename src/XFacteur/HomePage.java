package XFacteur;

import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.control.TabPane;
import javafx.scene.control.TabPane.TabClosingPolicy;
import javafx.scene.control.Tab;
import javafx.scene.shape.Rectangle;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class HomePage extends Stage {
	private BorderPane bordP = new BorderPane();
	private TabPane right = new TabPane();
	private Text top = new Text("X Facteur");

	public HomePage() {
		//Paramètres Page d'accueil
		this.getIcons().add(new Image("file:media/logo.png"));
		this.setMaximized(true);
		this.setScene(new Scene(content()));
		this.setTitle("X Facteur");
	}

	private BorderPane content() {
		bordP = new BorderPane();

		//top: title
		top.setFont(Font.font("sans-serif", 20));
		bordP.setTop(top);

		//right: MailmanView & ShipmentView
		right.setTabClosingPolicy(TabPane.TabClosingPolicy.UNAVAILABLE);

		Tab mailmanTab = new Tab();
		mailmanTab.setText("Facteurs");
		mailmanTab.setContent(new MailmanView());
		right.getTabs().add(mailmanTab);

		Tab shipmentTab = new Tab();
		shipmentTab.setText("Envois");
		shipmentTab.setContent(new Rectangle(242, 242, Color.PURPLE));
		right.getTabs().add(shipmentTab);

		bordP.setRight(right);
		bordP.setCenter(new Rectangle(400, 400, Color.RED));
		return bordP;
	}
}

