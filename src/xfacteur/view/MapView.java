package xfacteur.view;

import javafx.scene.Group;
import javafx.scene.layout.GridPane;
import javafx.scene.web.WebView;
import javafx.scene.control.Button;
import javafx.geometry.Insets;

import xfacteur.PathController;

public class MapView extends Group {
	protected GridPane grid = new GridPane();
	protected Button genBtn = new Button("Générer");
	protected WebView wView = new WebView();

	public MapView() {
		System.setProperty("http.proxyHost", "129.20.239.11");
		System.setProperty("http.proxyPort", "3128");
		grid.setHgap(4);
		grid.setVgap(8);
		GridPane.setMargin(wView, new Insets(20));
		genBtn.setOnAction(e -> {
			PathController.generate();
			PathController.display();
		});
		wView.getEngine().load("https://www.google.fr/maps");
		grid.add(wView, 0, 1);
		grid.add(genBtn, 0, 0);
		this.getChildren().add(grid);
	}
}

