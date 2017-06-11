package xfacteur.view;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
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
import xfacteur.SerializationController;
import xfacteur.PathController;
import xfacteur.view.ShipmentView;
import xfacteur.view.MailmanView;
import xfacteur.view.MapView;

public class HomePage extends Stage {
	protected BorderPane bordP = new BorderPane();
	protected MenuBar menuBar = new MenuBar();
	protected TabPane tabs = new TabPane();
	protected MailmanView mailmanView = MailmanController.getView();
	protected ShipmentView shipmentView = ShipmentController.getView();
	protected MapView mapView = PathController.getMapView();
	protected FileChooser fileChooser = new FileChooser();

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
		MenuItem openMMI = new MenuItem("Ouvrir des facteurs...");
		MenuItem openSMI = new MenuItem("Ouvrir des envois...");
		MenuItem saveasMMI = new MenuItem("Enregistrer des facteurs sous...");
		MenuItem saveasSMI = new MenuItem("Enregistrer des envois sous...");
		MenuItem quitMI = new MenuItem("Quitter");

		openMMI.setOnAction(e -> {
			fileChooser.setTitle("Ouvrir des facteurs…");
			fileChooser.getExtensionFilters().setAll(new ExtensionFilter("Fichiers facteurs","*.xmen"));
			SerializationController.openMailmen(fileChooser.showOpenDialog(this));
		});

		openSMI.setOnAction(e -> {
			fileChooser.setTitle("Ouvrir des envois…");
			fileChooser.getExtensionFilters().setAll(new ExtensionFilter("Fichiers envois","*.xship"));
			SerializationController.openShipments(fileChooser.showOpenDialog(this));
		});

		saveasMMI.setOnAction(e -> {
			fileChooser.setTitle("Enregistrer des facteurs sous…");
			fileChooser.getExtensionFilters().setAll(new ExtensionFilter("Fichiers facteurs","*.xmen"));
			SerializationController.saveMailmen(fileChooser.showSaveDialog(this));
		});

		saveasSMI.setOnAction(e -> {
			fileChooser.setTitle("Enregistrer des envois sous…");
			fileChooser.getExtensionFilters().setAll(new ExtensionFilter("Fichiers envois","*.xship"));
			SerializationController.saveShipments(fileChooser.showSaveDialog(this));
        });

		quitMI.setOnAction(e -> {
			System.exit(0);
		});
		fileM.getItems().addAll(openMMI, openSMI, saveasMMI, saveasSMI, quitMI);

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
