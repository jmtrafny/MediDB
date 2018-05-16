/**
 * Lana Adams, James Trafny
 * CS 317 - Final project
 * 
 * This application is designed to store and manage patient medicine information.
 * 
 * This is the pop up which will allow the user to add a new medicine to their regimen
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

public class updateUserInput {

	public static boolean display(String title) {
		
		String url = "jdbc:mysql://localhost:3306/medidb?useSSL=false";
		String user = "root";
		String pswd = "BlueJean1018";
		MediDB mediDB = new MediDB(url, user, pswd);
		
		Stage window = new Stage();
		window.initModality(Modality.APPLICATION_MODAL);
		window.setTitle(title);
		window.setMinWidth(500);
		window.setMinHeight(500);
		
		ChoiceBox<String> choiceBox = new ChoiceBox<>(); //brandname drop down
		
		//Create label, text field, buttons
		Label bnLabel = new Label("Brand Name: ");
		
		ArrayList<String> results = mediDB.getMedicationList();
		for (String med:results) {
			choiceBox.getItems().add(med);
		}
		
		choiceBox.getItems().add("DRUGZ");
		Label doseLabel = new Label("Dosage: ");
		TextField doseInput = new TextField();
		Label mgLabel = new Label("mg");
		Label freqLabel = new Label("Frequency: ");
		TextField freqInput = new TextField();
		Label countLabel = new Label("Count: ");
		TextField countInput = new TextField();
		
		//create the button
		Button addBt = new Button("Add");

		//create the girdpane
		GridPane gridPane = new GridPane();
		gridPane.setVgap(20);
		gridPane.setHgap(20);

		//Add buttons
		gridPane.add(bnLabel, 0, 0);
		gridPane.add(choiceBox, 1, 0);
		gridPane.add(doseLabel, 0, 1);
		gridPane.add(doseInput, 1, 1);
		gridPane.add(mgLabel, 2, 1);
		gridPane.add(freqLabel, 0, 2);
		gridPane.add(freqInput, 1, 2);
		gridPane.add(countLabel, 0, 3);
		gridPane.add(countInput, 1, 3);
		gridPane.add(addBt, 1, 4);
		gridPane.setAlignment(Pos.CENTER);
		Scene scene = new Scene(gridPane);
		scene.getStylesheets().add("stylesheetMDBpopup.css");
		window.setScene(scene);
		window.show();
		
		Preferences up = Preferences.userRoot();
		int user_id = 0;
		int userid = up.getInt("userID", user_id);
		
		

		addBt.setOnAction(e -> {
			int count = Integer.parseInt(countInput.getText());
			int dosage = Integer.parseInt(doseInput.getText());
			mediDB.addToRegimen(userid, choiceBox.getValue(), dosage, freqInput.getText(), count);
			window.close();
		});
		
		return true;
		
		

}

}
