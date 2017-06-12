package xfacteur;

import javafx.application.Application;
import javafx.stage.Stage;

import xfacteur.view.HomePage;
import xfacteur.view.AboutModal;

public class XFacteur extends Application {
	public static final String LOGOPATH = "file:media/logo.png";
	public static final String GOOGLE_API_KEY = "AIzaSyA5FMd2HKX15GZJdhWSbQh7Lu2PzmSkozI"; //DO NOT CHANGE THIS VALUE!

	public void start(Stage primaryStage) {
		primaryStage = new HomePage();
		primaryStage.show();
	}

	public static void main(String[] args) {
		Application.launch(args);
	}

	public static void showAbout() {
		new AboutModal().showAndWait();
	}

}
