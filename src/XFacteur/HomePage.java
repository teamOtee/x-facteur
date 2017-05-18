package XFacteur;

import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.control.TabPane;
import javafx.scene.control.TabPane.TabClosingPolicy;
import javafx.scene.control.Tab;
import javafx.scene.control.MenuBar;
import javafx.scene.control.Menu;

public class HomePage extends Stage {
	protected BorderPane bordP = new BorderPane();
	protected MenuBar top = new MenuBar();
	protected TabPane right = new TabPane();

	public HomePage() {
		//Paramètres Page d'accueil
		this.getIcons().add(new Image("file:media/logo.png"));
		this.setMaximized(true);
		this.setScene(new Scene(content()));
		this.setTitle("X Facteur");
	}

	protected BorderPane content() {
		bordP = new BorderPane();

		//top: MenuBar
		top.getMenus().addAll(new Menu("Fichier"), new Menu("Aide"));
		bordP.setTop(top);

		//right: MailmanView & ShipmentView
		right.setTabClosingPolicy(TabPane.TabClosingPolicy.UNAVAILABLE);

		Tab mailmanTab = new Tab();
		mailmanTab.setText("Facteurs");
		mailmanTab.setContent(new MailmanView());
		right.getTabs().add(mailmanTab);

		Tab shipmentTab = new Tab();
		shipmentTab.setText("Envois");
		shipmentTab.setContent(new ShipmentView());
		right.getTabs().add(shipmentTab);

		bordP.setRight(right);

		//center: MapView
		bordP.setCenter(new MapView());
		return bordP;
	}
}

