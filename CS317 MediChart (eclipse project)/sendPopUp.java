/**
 * Lana Adams, James Trafny
 * CS 317 - Final project
 * 
 * This application is designed to store and manage patient medicine information.
 * 
 * This is the pop up which will allow the user to enter an email 
 * to have their medicine list sent to their doctor
 */
import javafx.stage.*;
import javafx.scene.*;
import javafx.scene.layout.*;
import javafx.scene.control.*;
import javafx.geometry.*;
public class sendPopUp {

	public static boolean display(String title) {
		Stage window = new Stage();
		window.initModality(Modality.APPLICATION_MODAL);
		window.setTitle(title);
		window.setMinWidth(500);
		window.setMinHeight(300);

		//Create label, text field, buttons
		Label enterEmail = new Label("Doctor's Email: ");
		TextField drEmail = new TextField();
		Button send = new Button("Send");

		GridPane gridPane = new GridPane();
		gridPane.setVgap(20);
		gridPane.setHgap(20);

		//Add buttons
		gridPane.add(drEmail, 1,	0);
		gridPane.add(send, 2, 0);
		gridPane.add(enterEmail, 0, 0);
		gridPane.setAlignment(Pos.CENTER);
		Scene scene = new Scene(gridPane);
		scene.getStylesheets().add("stylesheetMDBpopup.css");
		window.setScene(scene);
		window.show();
		
		send.setOnAction(e -> {
			window.close();
		});

		return true;

}}
