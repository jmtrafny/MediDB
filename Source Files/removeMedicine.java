/**
 * Lana Adams, James Trafny
 * CS 317 - Final project
 * 
 * This application is designed to store and manage patient medicine information.
 * 
 * This is the pop up which will allow the user to remove a medicine to their regimen
 */

import java.util.ArrayList;
import java.util.prefs.Preferences;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class removeMedicine {

	public static boolean display(String title) {
		
		String url = "jdbc:mysql://localhost:3306/medidb?useSSL=false";
		String user = "root";
		String pswd = "BlueJean1018";
		MediDB mediDB = new MediDB(url, user, pswd);
		
		Stage window = new Stage();
		window.initModality(Modality.APPLICATION_MODAL);
		window.setTitle(title);
		window.setMinWidth(500);
		window.setMinHeight(300);
		
		ChoiceBox<String> choiceBox = new ChoiceBox<>(); //brandname drop down


		//Create label, text field, buttons
		Label bnLabel = new Label("Brand Name: ");
		
		Preferences up = Preferences.userRoot();
		int user_id = 0;
		int userid = up.getInt("userID", user_id);
		ArrayList<String> results = mediDB.getPartRegimen(userid);
		for (String med:results) {
			choiceBox.getItems().add(med);
		}
		
		
		Button deleteBt = new Button("Delete");

		GridPane gridPane = new GridPane();
		gridPane.setVgap(20);
		gridPane.setHgap(20);

		//Add buttons
		gridPane.add(choiceBox, 1,	0);
		gridPane.add(deleteBt, 2, 0);
		gridPane.add(bnLabel, 0, 0);
		gridPane.setAlignment(Pos.CENTER);
		Scene scene = new Scene(gridPane);
		scene.getStylesheets().add("stylesheetMDBpopup.css");
		window.setScene(scene);
		window.show();
		
		deleteBt.setOnAction(e -> {
			mediDB.removeFromRegimen(userid, choiceBox.getValue());
			window.close();
		});

		return true;

}

}
