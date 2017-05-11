package xfacteur2;

import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {

	public void start(Stage primaryStage) {
		primaryStage = new Pageaccueil();
		primaryStage.show();
	}
	
	public static void main(String[] args){
		Application.launch(args);
	}

}
