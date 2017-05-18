package XFacteur;

import javafx.scene.Group;
import javafx.scene.layout.GridPane;
import javafx.scene.web.WebView;
import javafx.scene.control.Button;

public class MapView extends Group {
	protected GridPane grid = new GridPane();
	protected Button genBtn = new Button("Générer");
	protected WebView wView = new WebView();

	public MapView() {
		grid.setHgap(4);
		grid.setVgap(8);
		genBtn.setOnAction(e -> {
			wView.getEngine().load("http://en.wikipedia.org/wiki/random");
		});
		wView.getEngine().load("http://lipsum.org");
		grid.add(wView, 0, 1);
		grid.add(genBtn, 0, 0);
		this.getChildren().add(grid);
	}
}

