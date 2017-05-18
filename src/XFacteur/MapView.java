package XFacteur;

import javafx.scene.Group;
import javafx.scene.shape.Rectangle;
import javafx.scene.paint.Color;
import javafx.scene.web.WebView;

public class MapView extends Group {
	protected WebView wView = new WebView();

	public MapView() {
		this.getChildren().add(wView);
		wView.getEngine().load("https://github.com/ribacq");
	}
}

