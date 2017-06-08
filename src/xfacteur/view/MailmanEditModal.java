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
import xfacteur.model.Mailman;

public class MailmanEditModal extends Stage {
	protected GridPane gridP = new GridPane();
	protected Text header = new Text("Veuillez remplir tous les champs :");
	protected Label mailmanNameL = new Label("Prénom :");
	protected Label mailmanLastnameL = new Label("Nom :");
	protected TextField mailmanName = new TextField();
	protected TextField mailmanLastname = new TextField();
	protected Label drivingL = new Label("Permis :");
	protected CheckBox driver = new CheckBox();
	protected Button okBtn = new Button("OK");
	protected Button cancelBtn = new Button("Annuler");
	protected Mailman value = null;

	public MailmanEditModal() {
		this.setTitle("Ajouter un facteur — X Facteur");
		this.initModality(Modality.APPLICATION_MODAL);
		this.getIcons().add(new Image(XFacteur.LOGOPATH));
		makeInteractivity();
		this.setMaxWidth(300);
		this.setMaxHeight(300);
		this.setScene(new Scene(content()));
		this.setResizable(false);
	}

	protected GridPane content() {
		gridP.setHgap(8);
		gridP.setVgap(6);
		gridP.setPadding(new Insets(20, 20, 20, 20));
		gridP.add(header, 0, 0, 2, 1);
		gridP.add(mailmanNameL, 0, 2);
		gridP.add(mailmanName, 1, 2, 2, 1);
		gridP.add(mailmanLastnameL, 0, 1);
		gridP.add(mailmanLastname, 1, 1, 2, 1);
		gridP.add(drivingL, 0, 3);
		gridP.add(driver, 1, 3);
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
		mailmanName.setOnKeyReleased(e -> {
			okBtn.setDisable(mailmanName.getText().isEmpty() || mailmanLastname.getText().isEmpty());
		});
		mailmanLastname.setOnKeyReleased(e -> {
			okBtn.setDisable(mailmanName.getText().isEmpty() || mailmanLastname.getText().isEmpty());
		});
		okBtn.setOnAction(e -> {
			this.value = new Mailman(driver.isSelected(), mailmanLastname.getText(), mailmanName.getText());
			this.close();
		});
		cancelBtn.setOnAction(e -> {
			this.value = null;
			this.close();
		});
	}

	public void initValue(Mailman s) {
		this.value = s;
		if (s != null) {
			mailmanLastname.setText(s.getlastname());
			mailmanName.setText(s.getname());
			driver.setSelected(s.isDriver());
			this.setTitle("Éditer un facteur — X Facteur");
			okBtn.setDisable(false);
		} else {
			mailmanName.clear();
			mailmanLastname.clear();
			driver.setSelected(false);
			this.setTitle("Ajouter un facteur — X Facteur");
		}
	}

	public Mailman getValue() {
		return value;
	}
}
