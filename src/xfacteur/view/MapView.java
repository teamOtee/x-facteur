package xfacteur.view;

import javafx.scene.Group;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.web.WebView;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.geometry.Insets;

import xfacteur.PathController;
import xfacteur.model.Shipment;

public class MapView extends Group {
	protected VBox container = new VBox(8);
	protected WebView wView = new WebView();
	protected GridPane form = new GridPane();
	protected Button genBtn = new Button("Générer");
	protected Text formHeader = new Text("Paramètres de l’algorithme");
	protected Label postOfficeStreetL = new Label("Adresse du centre postal (rue)");
	protected Label postOfficeCityL = new Label("Adresse du centre postal (ville)");
	protected TextField postOfficeStreet = new TextField();
	protected TextField postOfficeCity = new TextField();
	protected Label maxTimeL = new Label("Durée maximale de la distribution (heures)");
	protected TextField maxTime = new TextField();

	public MapView() {
		form.setHgap(4);
		form.setVgap(8);
		makeLayout();
		makeInteractivity();
	}

	protected void makeLayout() {
		// top form
		form.add(formHeader, 0, 0, 2, 1);
		form.add(postOfficeStreetL, 0, 1);
		form.add(postOfficeStreet, 1, 1);
		form.add(postOfficeCityL, 0, 2);
		form.add(postOfficeCity, 1, 2);
		form.add(maxTimeL, 0, 3);
		form.add(maxTime, 1, 3);
		maxTime.setPrefWidth(42);
		form.add(genBtn, 0, 4);
		container.getChildren().add(form);

		GridPane.setMargin(wView, new Insets(20));
		container.getChildren().add(wView);
		this.getChildren().add(container);
	}

	protected void makeInteractivity() {
		genBtn.setDisable(postOfficeStreet.getLength() == 0 || postOfficeCity.getLength() == 0 || maxTime.getLength() == 0);
		postOfficeStreet.setOnKeyReleased(e -> {
			genBtn.setDisable(postOfficeStreet.getLength() == 0 || postOfficeCity.getLength() == 0 || maxTime.getLength() == 0);
		});
		postOfficeCity.setOnKeyReleased(e -> {
			genBtn.setDisable(postOfficeStreet.getLength() == 0 || postOfficeCity.getLength() == 0 || maxTime.getLength() == 0);
		});
		maxTime.setOnKeyReleased(e -> {
			genBtn.setDisable(postOfficeStreet.getLength() == 0 || postOfficeCity.getLength() == 0 || maxTime.getLength() == 0);
		});
		genBtn.setOnAction(e -> {
			PathController.setPostOffice(new Shipment(postOfficeStreet.getText(), postOfficeCity.getText(), false));
			PathController.setMaxTime(Double.parseDouble(maxTime.getText()));
			PathController.generate();
			PathController.display();
		});
		wView.getEngine().load("https://www.google.fr/maps");
	}
}

