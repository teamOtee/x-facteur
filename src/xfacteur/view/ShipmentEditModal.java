package xfacteur.view;

import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.scene.layout.GridPane;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;

import xfacteur.model.Shipment;

public class ShipmentEditModal extends Stage {
	protected GridPane gridP = new GridPane();
	protected Label addressStreetL = new Label("N° et rue :");
	protected Label addressCityL = new Label("Ville :");
	protected TextField addressStreet = new TextField();
	protected TextField addressCity = new TextField();
	protected Label drivingL = new Label("Colis :");
	protected CheckBox driving = new CheckBox();
	protected Button okBtn = new Button("OK");
	protected Button cancelBtn = new Button("Annuler");
	protected Shipment value = null;

	public ShipmentEditModal() {
		this.setTitle("Ajouter un envoi — X Facteur");
		this.initModality(Modality.APPLICATION_MODAL);
		this.setScene(new Scene(content()));
		makeInteractivity();
	}

	protected GridPane content() {
		gridP.setHgap(8);
		gridP.setVgap(4);
		gridP.add(addressStreetL, 0, 0);
		gridP.add(addressStreet, 1, 0);
		gridP.add(addressCityL, 0, 1);
		gridP.add(addressCity, 1, 1);
		gridP.add(drivingL, 0, 2);
		gridP.add(driving, 1, 2);
		gridP.add(okBtn, 0, 3);
		gridP.add(cancelBtn, 1, 3);

		return gridP;
	}

	protected void makeInteractivity() {
		okBtn.setDisable(true);
		addressStreet.setOnKeyReleased(e -> {
			okBtn.setDisable(addressStreet.getText().isEmpty() || addressCity.getText().isEmpty());
		});
		addressCity.setOnKeyReleased(e -> {
			okBtn.setDisable(addressStreet.getText().isEmpty() || addressCity.getText().isEmpty());
		});
		okBtn.setOnAction(e -> {
			this.value = new Shipment(addressStreet.getText(), addressCity.getText(), driving.isSelected());
			this.close();
		});
		cancelBtn.setOnAction(e -> {
			this.value = null;
			this.close();
		});
	}

	public void initValue(Shipment s) {
		this.value = s;
		if (s != null) {
			addressStreet.setText(s.getStreet());
			addressCity.setText(s.getCity());
			driving.setSelected(s.isDriving());
			this.setTitle("Éditer un envoi — X Facteur");
		} else {
			addressStreet.clear();
			addressCity.clear();
			driving.setSelected(false);
			this.setTitle("Ajouter un envoi — X Facteur");
		}
	}

	public Shipment getValue() { return value; }
}

