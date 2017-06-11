package xfacteur.view;

import javafx.stage.Stage;
import xfacteur.XFacteur;
import javafx.stage.Modality;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.control.Button;
import javafx.geometry.Pos;

public class AboutModal extends Stage {
	protected GridPane grid = new GridPane();
	protected Text header = new Text("X Facteur");
	protected TextArea aboutText = new TextArea();
	protected Button closeBtn = new Button("Fermer");

	public AboutModal() {
		this.setTitle("À propos d’X Facteur");
		this.initModality(Modality.APPLICATION_MODAL);
		this.setScene(new Scene(grid));
		makeLayout();
		addContent();
		makeInteractivity();
		this.getIcons().add(new Image(XFacteur.LOGOPATH));
		this.setResizable(false);
	}

	protected void makeLayout() {
		aboutText.setPrefHeight(300);
		grid.setAlignment(Pos.CENTER);
		grid.add(header, 0, 0);
		grid.add(aboutText, 0, 1);
		grid.add(closeBtn, 0, 2);
	}

	protected void makeInteractivity() {
		closeBtn.setOnAction(e -> {
			this.close();
		});
	}

	protected void addContent() {
		aboutText.setWrapText(true);
		aboutText.setEditable(false);
		aboutText.setText("+--------------------------------------------------------------------------------------------+\n X Facteur est un logiciel libre sous license GNU.\n"
				+ " Crédit :\n Daguin Cléo\n Massy Léo\n Ribac Quentin\n\n\n\n\n\n\n\n\n Créé en 2017, à l'occasion du projet de second semestre de DUT Informatique à l'IUT de Lannion."
				+ "\n+--------------------------------------------------------------------------------------------+");
	}
}

