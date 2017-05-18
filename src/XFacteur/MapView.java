package XFacteur;

import javafx.scene.Group;
import javafx.scene.shape.Rectangle;
import javafx.scene.paint.Color;

public class MapView extends Group {
	protected Rectangle rect = new Rectangle(420, 420, Color.CYAN);

	public MapView() {
		this.getChildren().addAll(rect);
	}
}

