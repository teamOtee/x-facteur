package XFacteur;

import javafx.application.Application;
import javafx.stage.Stage;

public class XFacteur extends Application {
	public void start(Stage primaryStage) {
		primaryStage = new HomePage();
		primaryStage.show();
	}
	
	public static void main(String[] args){
		Application.launch(args);
	}

}

