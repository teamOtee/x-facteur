package xfacteur;

import javafx.stage.Stage;
import javafx.stage.Modality;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.scene.control.TextArea;
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
		aboutText.setText("Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nulla eget semper elit. In sed sapien ultrices, mattis lorem et, consectetur est. Donec lobortis, nisi et pellentesque efficitur, odio velit rhoncus odio, in cursus risus lorem a orci. Ut ullamcorper dolor lorem, eget ultricies elit luctus vitae. Proin nec lacus quis massa venenatis pulvinar. Fusce eu auctor dolor. Nulla justo dolor, rutrum id risus at, ullamcorper lacinia urna. Nulla ut felis vitae diam suscipit dictum a vitae quam. Sed tincidunt in neque vitae congue. Morbi tincidunt nisl vitae sapien suscipit congue. Ut porta dui vitae nisi suscipit, at rutrum ligula dapibus. Nunc at faucibus est, et lobortis tellus. Cras enim ex, sollicitudin a nunc eget, iaculis efficitur magna.\n");

		aboutText.appendText("Proin eget dictum risus, vel consequat elit. Aenean ut eros sed metus porttitor accumsan nec sit amet felis. Etiam sit amet imperdiet tortor, ac tempus erat. Vestibulum vel libero non lacus fermentum hendrerit ut eu sapien. Maecenas ut bibendum elit. Quisque eget ipsum et sapien tincidunt aliquet a at quam. Curabitur eget ipsum eu massa mattis lobortis. Nunc vitae eleifend eros, vitae tempus sem. Curabitur ut mollis libero, nec suscipit tortor. Sed at odio eu odio suscipit lobortis. Maecenas non mi ac orci dictum bibendum. Vivamus volutpat turpis vel ipsum semper faucibus. Maecenas leo diam, aliquet quis lacus nec, vehicula gravida nibh.\n");

		aboutText.appendText("Aliquam volutpat a metus a ultrices. Phasellus semper dapibus sollicitudin. Morbi non iaculis neque. Morbi sollicitudin orci nec massa volutpat sollicitudin commodo sit amet erat. Nunc aliquet egestas risus, et elementum turpis pretium ac. Morbi condimentum eu tortor et faucibus. Pellentesque habitant morbi tristique senectus et netus et malesuada fames ac turpis egestas. Donec ullamcorper nibh id ante luctus, id vulputate magna facilisis. Proin eu magna a felis varius hendrerit. Mauris interdum massa non ipsum lobortis porta. Mauris iaculis metus accumsan massa volutpat tincidunt.\n");

		aboutText.appendText("Donec accumsan ex congue sapien venenatis, eget vulputate nisl bibendum. Integer eu mauris quis diam fermentum auctor quis ac nisl. Vestibulum maximus metus et rhoncus malesuada. Suspendisse quis eros tincidunt, sagittis sem at, iaculis odio. Aliquam tempor leo eu orci tempus, euismod laoreet magna molestie. Nulla id dictum ligula. In dignissim lectus nec semper lacinia. Vestibulum vitae metus sit amet nisl molestie lobortis. Nulla mi ipsum, ornare in egestas quis, elementum ac lorem. Integer erat tellus, ultricies sed bibendum sed, faucibus vitae metus. Pellentesque vulputate imperdiet magna. Donec vel accumsan dui, quis porta mi. Aenean gravida non odio non tristique. Pellentesque nec ex metus. Vestibulum id ullamcorper quam.\n");

		aboutText.appendText("Pellentesque sed nunc vel tortor posuere dapibus. Praesent dapibus mi non ligula rutrum dignissim. Cras ac mollis nulla. Aenean gravida consectetur hendrerit. Mauris iaculis quam eget sodales mollis. Praesent magna nisl, vestibulum et sagittis et, mattis condimentum ipsum. Quisque aliquet fringilla mauris, non laoreet felis malesuada ac. Suspendisse cursus, odio et pellentesque congue, nisi dolor tempor sem, et ultricies velit leo id magna. Etiam ac felis feugiat, ullamcorper arcu non, ultrices nisi. Nullam ipsum dui, auctor id porttitor et, vulputate porta leo. Cras ligula dolor, rutrum non interdum quis, sollicitudin vel metus. Mauris sem dolor, placerat quis velit ac, vestibulum ullamcorper sapien. Fusce gravida semper turpis, ut tempus justo accumsan vitae. Pellentesque malesuada sit amet dolor in interdum. Pellentesque ultrices tortor non sollicitudin eleifend. Nulla ut ipsum vel quam eleifend convallis.\n");
	}
}

