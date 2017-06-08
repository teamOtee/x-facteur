package xfacteur.view;

import javafx.stage.Stage;
import javafx.stage.Modality;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.image.Image;

public class ProxyModal extends Stage {
	protected GridPane gridP = new GridPane();
	protected Text header = new Text("Veuillez remplir tous les champs :");
	protected Label useProxyL = new Label("Utiliser un proxy :");
	protected CheckBox useProxy = new CheckBox();
	protected Label hostL = new Label("Hôte :");
	protected Label portL = new Label("Port :");
	protected TextField host = new TextField();
	protected TextField port = new TextField();
	protected Button okBtn = new Button("OK");
	protected Button cancelBtn = new Button("Annuler");
	protected String hostValue = "";
	protected String portValue = "";
	protected boolean useProxyValue = false;
	
	public ProxyModal() {
		this.setTitle("Configuration du proxy — X Facteur");
		this.initModality(Modality.APPLICATION_MODAL);
		this.setScene(new Scene(content()));
		makeInteractivity();
		this.setResizable(false);
		this.getIcons().add(new Image("file:media/logo.png"));
		this.setMaxWidth(280);
		this.setMaxHeight(300);
	}

	protected GridPane content() {
		gridP.setHgap(8);
		gridP.setVgap(6);
		gridP.setPadding(new Insets(20, 20, 20, 20));
		gridP.add(header, 0, 0, 2, 1);
		gridP.add(useProxyL, 0, 1);
		gridP.add(useProxy, 1, 1);
		gridP.add(hostL, 0, 2);
		gridP.add(host, 1, 2, 2, 1);
		gridP.add(portL, 0, 3);
		gridP.add(port, 1, 3, 2, 1);
		GridPane.setHalignment(okBtn, HPos.RIGHT);
		GridPane.setHalignment(cancelBtn, HPos.RIGHT);
		gridP.add(okBtn, 1, 4);
		gridP.add(cancelBtn, 2, 4);

		return gridP;
	}

	protected void makeInteractivity() {
		okBtn.setDisable(true);
		host.setDisable(true);
		port.setDisable(true);
		useProxy.setOnAction(e -> {
			okBtn.setDisable(!useProxy.isSelected() || host.getText().isEmpty() || port.getText().isEmpty());
			host.setDisable(!useProxy.isSelected());
			port.setDisable(!useProxy.isSelected());
		});
		host.setOnKeyReleased(e -> {
			okBtn.setDisable(!useProxy.isSelected() || host.getText().isEmpty() || port.getText().isEmpty());
		});
		port.setOnKeyReleased(e -> {
			okBtn.setDisable(!useProxy.isSelected() || host.getText().isEmpty() || port.getText().isEmpty());
		});
		okBtn.setOnAction(e -> {
			useProxyValue = useProxy.isSelected();
			hostValue = host.getText();
			portValue = port.getText();
			this.close();
		});
		cancelBtn.setOnAction(e -> {
			this.close();
		});
	}

	public void initValues(boolean useProxyValue, String hostValue, String portValue) {
		this.useProxyValue = useProxyValue;
		this.hostValue = hostValue;
		this.portValue = portValue;
		useProxy.setSelected(this.useProxyValue);
		host.setText(this.hostValue);
		port.setText(this.portValue);
	}

	public boolean getUseProxyValue() { return useProxyValue; }
	public String getHostValue() { return hostValue; }
	public String getPortValue() { return portValue; }
}

