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
import xfacteur.SerializationController;
import xfacteur.XFacteur;
import xfacteur.model.Mailman;

public class SeializationModal extends Stage {
	protected GridPane gridP = new GridPane();
	protected Text header = new Text("Veuillez remplir tous les champs sans extention:");
	protected Label mailmanNameL = new Label("Nom du fichier des facteurs :");
	protected Label shipmentNameL = new Label("Nom du fichier des envois :");
	protected TextField mailmanName = new TextField();
	protected TextField shipmentName = new TextField();
	protected Button okBtn = new Button("Enregistrer");
	protected Button cancelBtn = new Button("Annuler");

	public void MailmanEditModal() {
		this.setTitle("Enregistrer les facteurs et les envois — X Facteur");
		this.initModality(Modality.APPLICATION_MODAL);
		this.getIcons().add(new Image(XFacteur.LOGOPATH));
		this.setMaxWidth(300);
		this.setMaxHeight(300);
		this.setResizable(false);
		this.setScene(new Scene(content()));
		makeInteractivity();
	}

	protected GridPane content() {
		gridP.setHgap(8);
		gridP.setVgap(6);
		gridP.setPadding(new Insets(10, 20, 12, 20));
		gridP.add(header, 0, 0, 2, 1);
		gridP.add(mailmanNameL, 0, 2);
		gridP.add(mailmanName, 1, 2, 2, 1);
		gridP.add(shipmentNameL, 0, 1);
		gridP.add(shipmentName, 1, 1, 2, 1);
		GridPane.setHalignment(okBtn, HPos.RIGHT);
		GridPane.setHalignment(cancelBtn, HPos.RIGHT);
		okBtn.setPrefWidth(80);
		cancelBtn.setPrefWidth(80);
		gridP.add(okBtn, 1, 3);
		gridP.add(cancelBtn, 2, 3);

		return gridP;
	}

	protected void makeInteractivity() {
		okBtn.setDisable(true);
		mailmanName.setOnKeyReleased(e -> {
			okBtn.setDisable(mailmanName.getText().isEmpty() || shipmentName.getText().isEmpty());
		});
		shipmentName.setOnKeyReleased(e -> {
			okBtn.setDisable(mailmanName.getText().isEmpty() || shipmentName.getText().isEmpty());
		});
		okBtn.setOnAction(e -> {
			//SerializationController.serialization(shipmentName.getText(),mailmanName.getText());
			this.close();
		});
		cancelBtn.setOnAction(e -> {
			this.close();
		});
	}

}