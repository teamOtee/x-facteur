package xfacteur;

import javafx.application.Application;
import javafx.stage.Stage;
import xfacteur.view.AboutModal;

public class XFacteur extends Application {
	public final static String LOGOPATH = "file:media/logo.png";

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
