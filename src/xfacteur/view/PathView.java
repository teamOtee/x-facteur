package xfacteur.view;

import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.scene.Parent;

public class PathView extends Parent {
	// attributes
	protected HBox container = new HBox(4);
	protected Text header = new Text("Un chemin");

	// constructor
	public PathView() {
		this.getChildren().add(container);
		makeLayout();
		makeInteractivity();
	}

	protected void makeLayout() {
		container.getChildren().add(header);
	}

	protected void makeInteractivity() {
		//
	}
}
