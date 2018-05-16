/**
 * Lana Adams, James Trafny
 * CS 317 - Final project
 * 
 * This application is designed to store and manage patient medicine information.
 * 
 * This is the pop up which will allow the user to decide between adding or 
 * removing a medication from his/her list
 */
/**
 * modified version of
 * https://github.com/buckyroberts/Source-Code-
 * from-Tutorials/blob/master/JavaFX/006_communicatingBetweenWindows/ConfirmBox.java
 */

import javafx.stage.*;
import javafx.scene.*;
import javafx.scene.layout.*;
import javafx.scene.control.*;
import javafx.geometry.*;

public class updatePopUp {

	public static boolean display(String title) {
		Stage window = new Stage();
		window.initModality(Modality.APPLICATION_MODAL);
		window.setTitle(title);
		window.setMinWidth(500);
		window.setMinHeight(300);

		//Create two buttons
		Button addMed = new Button("Add\nMedicine");
		Button removeMed = new Button("Remove\nMedicine");

		GridPane gridPane = new GridPane();
		gridPane.setVgap(20);
		gridPane.setHgap(20);

		//Add buttons
		gridPane.add(addMed, 0,	0);
		gridPane.add(removeMed, 1, 0);
		gridPane.setAlignment(Pos.CENTER);
		Scene scene = new Scene(gridPane);
		scene.getStylesheets().add("stylesheetMDBpopup.css");
		window.setScene(scene);
		window.show();
		
		addMed.setOnAction(e -> {
			boolean result = updateUserInput.display("Add New Medicine");
			window.close();
		});
		
		removeMed.setOnAction(f -> {
			boolean result = removeMedicine.display("Remove Medicine");
			window.close();
		});

		return true;
	}

	
}