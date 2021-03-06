package xfacteur.view;

import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.scene.layout.GridPane;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.text.Text;
import javafx.geometry.HPos;
import javafx.geometry.Insets;

import xfacteur.XFacteur;
import xfacteur.model.Shipment;

public class ShipmentEditModal extends Stage {
	protected GridPane gridP = new GridPane();
	protected Text header = new Text("Veuillez remplir tous les champs :");
	protected Label addressStreetL = new Label("N° et rue :");
	protected Label addressCityL = new Label("Ville :");
	protected TextField addressStreet = new TextField();
	protected TextField addressCity = new TextField();
	protected Label drivenL = new Label("Colis :");
	protected CheckBox driven = new CheckBox();
	protected Button okBtn = new Button("OK");
	protected Button cancelBtn = new Button("Annuler");
	protected Shipment value = null;

	public ShipmentEditModal() {
		this.setTitle("Ajouter un envoi — X Facteur");
		this.initModality(Modality.APPLICATION_MODAL);
		this.setScene(new Scene(content()));
		makeInteractivity();
		this.setResizable(false);
		this.getIcons().add(new Image(XFacteur.LOGOPATH));
		this.setMaxWidth(280);
		this.setMaxHeight(300);
	}

	protected GridPane content() {
		gridP.setHgap(8);
		gridP.setVgap(6);
		gridP.setPadding(new Insets(10, 20, 12, 20));
		gridP.add(header, 0, 0, 2, 1);
		gridP.add(addressStreetL, 0, 1);
		gridP.add(addressStreet, 1, 1, 2, 1);
		gridP.add(addressCityL, 0, 2);
		gridP.add(addressCity, 1, 2, 2, 1);
		gridP.add(drivenL, 0, 3);
		gridP.add(driven, 1, 3);
		GridPane.setHalignment(okBtn, HPos.RIGHT);
		GridPane.setHalignment(cancelBtn, HPos.RIGHT);
		okBtn.setPrefWidth(80);
		cancelBtn.setPrefWidth(80);
		gridP.add(okBtn, 1, 4);
		gridP.add(cancelBtn, 2, 4);

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
			this.value = new Shipment(addressStreet.getText(), addressCity.getText(), driven.isSelected());
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
			driven.setSelected(s.isDriven());
			this.setTitle("Éditer un envoi — X Facteur");
			okBtn.setDisable(false);
		} else {
			addressStreet.clear();
			addressCity.clear();
			driven.setSelected(false);
			this.setTitle("Ajouter un envoi — X Facteur");
		}
	}

	public Shipment getValue() { return value; }
}

