package xfacteur.view;

import javafx.scene.Group;
import javafx.scene.layout.GridPane;
import javafx.scene.web.WebView;
import javafx.scene.control.Button;
import javafx.geometry.Insets;

public class MapView extends Group {
	protected GridPane grid = new GridPane();
	protected Button genBtn = new Button("Générer");
	protected WebView wView = new WebView();

	public MapView() {
		grid.setHgap(4);
		grid.setVgap(8);
		GridPane.setMargin(wView, new Insets(20));
		genBtn.setOnAction(e -> {
			wView.getEngine().load("https://www.google.fr/maps");
		});
		wView.getEngine().load("https://www.google.fr/maps");
		grid.add(wView, 0, 1);
		grid.add(genBtn, 0, 0);
		this.getChildren().add(grid);
	}
}

