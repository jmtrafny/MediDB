/**
 * Lana Adams, James Trafny
 * CS 317 - Final project
 * 
 * This application is designed to store and manage patient medicine information.
 * 
 * This is the pop up which will allow the user to create an account
 */

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class createAccount {
	
	

	public static boolean display(String title) {
		String url = "jdbc:mysql://localhost:3306/medidb?useSSL=false";
		String user = "root";
		String pswd = "BlueJean1018";
		MediDB mediDB = new MediDB(url, user, pswd);
		
		Stage window = new Stage();
		window.initModality(Modality.APPLICATION_MODAL);
		window.setTitle(title);
		window.setMinWidth(500);
		window.setMinHeight(600);

		//Create label, text field, buttons
		Label userLabel = new Label("User ID (####):");
		TextField userID = new TextField();
		Label pwLabel = new Label("Password:");
		TextField pwInput = new TextField();
		Label fnLabel = new Label("First Name: ");
		TextField fnInput = new TextField();
		Label lnLabel = new Label("Last Name: ");
		TextField lnInput = new TextField();
		Label dobLabel = new Label("Date Of Birth (YYYY-MM-DD): ");
		TextField dobInput = new TextField();
		Label weightLabel = new Label("Weight: ");
		TextField weightInput = new TextField();
		Label genderLabel = new Label("Gender (m/f): ");
		TextField genderInput = new TextField();



		Button okBt = new Button("OK");

		GridPane gridPane = new GridPane();
		gridPane.setVgap(20);
		gridPane.setHgap(20);

		//Add buttons
		gridPane.add(userLabel, 0, 0);
		gridPane.add(userID, 1, 0);
		gridPane.add(pwLabel, 0, 1);
		gridPane.add(pwInput, 1, 1);
		gridPane.add(fnLabel, 0, 2);
		gridPane.add(fnInput, 1, 2);
		gridPane.add(lnLabel, 0, 3);
		gridPane.add(lnInput, 1, 3);
		gridPane.add(dobLabel, 0, 4);
		gridPane.add(dobInput, 1, 4);
		gridPane.add(weightLabel, 0, 5);
		gridPane.add(weightInput, 1, 5);
		gridPane.add(genderLabel, 0, 6);
		gridPane.add(genderInput, 1, 6);
		gridPane.add(okBt, 1, 7);
		gridPane.setAlignment(Pos.CENTER);
		Scene scene = new Scene(gridPane);
		scene.getStylesheets().add("stylesheetMDBpopup.css");
		window.setScene(scene);
		window.show();
		
		


		
		
		
		okBt.setOnAction(e -> {
			int user_ID = Integer.parseInt(userID.getText());
			int weight = Integer.parseInt(weightInput.getText());
			mediDB.addNewPatient(user_ID, pwInput.getText(), fnInput.getText(), lnInput.getText(), dobInput.getText(), weight, genderInput.getText());	
			window.close();
		});
		
		
		
		
		return true;


	}

}
