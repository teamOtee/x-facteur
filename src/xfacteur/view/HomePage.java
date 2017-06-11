package xfacteur.view;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.control.TabPane;
import javafx.scene.control.Tab;
import javafx.scene.control.MenuBar;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;

import xfacteur.XFacteur;
import xfacteur.MailmanController;
import xfacteur.ShipmentController;
import xfacteur.ConfigController;
import xfacteur.view.ShipmentView;
import xfacteur.view.MailmanView;
import xfacteur.view.MapView;

public class HomePage extends Stage {
	protected BorderPane bordP = new BorderPane();
	protected MenuBar menuBar = new MenuBar();
	protected TabPane tabs = new TabPane();
	protected MailmanView mailmanView = MailmanController.getView();
	protected ShipmentView shipmentView = ShipmentController.getView();
	protected MapView mapView = new MapView();

	public HomePage() {
		// Paramètres Page d'accueil
		this.getIcons().add(new Image(XFacteur.LOGOPATH));
		this.setMaximized(true);
		this.setScene(new Scene(content()));
		this.setTitle("X Facteur");
	}

	protected BorderPane content() {
		bordP = new BorderPane();

		// top: MenuBar
		Menu fileM = new Menu("Fichier");
		MenuItem saveMI = new MenuItem("Enregistrer");
		MenuItem quitMI = new MenuItem("Quitter");
		
		
		saveMI.setOnAction(e -> {
			// enregistre les données
		});
		
		
		quitMI.setOnAction(e -> {
			System.exit(0);
		});
		fileM.getItems().addAll( saveMI,quitMI);

		Menu configM = new Menu("Configuration");
		MenuItem proxyMI = new MenuItem("Proxy…");
		proxyMI.setOnAction(e -> {
			ConfigController.openProxyModal();
		});
		configM.getItems().add(proxyMI);

		Menu helpM = new Menu("Aide");
		MenuItem aboutMI = new MenuItem("À propos");
		aboutMI.setOnAction(e -> {
			XFacteur.showAbout();
		});
		helpM.getItems().add(aboutMI);

		menuBar.getMenus().addAll(fileM, configM, helpM);
		bordP.setTop(menuBar);

		// left: MailmanView & ShipmentView
		tabs.setTabClosingPolicy(TabPane.TabClosingPolicy.UNAVAILABLE);
		tabs.setTabMinWidth(120);

		Tab mailmanTab = new Tab("Facteurs", mailmanView);
		Tab shipmentTab = new Tab("Envois", shipmentView);
		tabs.setPadding(new Insets(0, 0, 0, 20));
		tabs.getTabs().addAll(mailmanTab, shipmentTab);

		bordP.setLeft(tabs);

		// center: MapView
		bordP.setCenter(mapView);
		return bordP;
	}
}
