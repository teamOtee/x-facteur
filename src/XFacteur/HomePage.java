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
import javafx.scene.control.MenuItem;

public class HomePage extends Stage {
	protected BorderPane bordP = new BorderPane();
	protected MenuBar menuBar = new MenuBar();
	protected TabPane tabs = new TabPane();
	protected MailmanView mailmanView = new MailmanView();
	protected ShipmentView shipmentView = new ShipmentView();
	protected MapView mapView = new MapView();

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
		Menu fileM = new Menu("Fichier");
		MenuItem quitMI = new MenuItem("Quitter");
		quitMI.setOnAction(e -> {
			System.exit(0);
		});
		fileM.getItems().add(quitMI);
		Menu helpM = new Menu("Aide");
		menuBar.getMenus().addAll(fileM, helpM);
		bordP.setTop(menuBar);

		//right: MailmanView & ShipmentView
		tabs.setTabClosingPolicy(TabPane.TabClosingPolicy.UNAVAILABLE);

		Tab mailmanTab = new Tab();
		mailmanTab.setText("Facteurs");
		mailmanTab.setContent(mailmanView);
		tabs.getTabs().add(mailmanTab);

		Tab shipmentTab = new Tab();
		shipmentTab.setText("Envois");
		shipmentTab.setContent(shipmentView);
		tabs.getTabs().add(shipmentTab);

		bordP.setRight(tabs);

		//center: MapView
		bordP.setCenter(mapView);
		return bordP;
	}
}

