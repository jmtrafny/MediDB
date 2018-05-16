/**
 * Lana Adams, James Trafny
 * CS 317 - Final project
 * 
 * This application is designed to store and manage patient medicine information.
 * 
 * This is the pop up which will allow the user to view their regimen
 */
import java.util.ArrayList;
import java.util.prefs.Preferences;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class viewRegimen {
	
	static String url = "jdbc:mysql://localhost:3306/medidb?useSSL=false";
	static String user = "root";
	static String pswd = "BlueJean1018";
	static MediDB mediDB = new MediDB(url, user, pswd);

	public static boolean display(String title) {
		Stage window = new Stage();
		window.initModality(Modality.APPLICATION_MODAL);
		window.setTitle(title);
		window.setMinWidth(500);
		window.setMinHeight(300);

		VBox vBox = new VBox();
		vBox.setAlignment(Pos.CENTER);
		Preferences up = Preferences.userRoot();
		int user_id = 0;
		int userid = up.getInt("userID", user_id);
		
		ArrayList<String> results = mediDB.getFullRegimen(userid);
		Label labels[] = new Label[results.size()];
		int index = 0;
		for (String row:results) {
			labels[index] = new Label(row);
			vBox.getChildren().add(labels[index]);
			index++;
		}
		
		
		
		Scene scene = new Scene(vBox);
		scene.getStylesheets().add("stylesheetMDBpopup.css");
		window.setScene(scene);
		
		
		//call to mediDB
		
		window.show();

		
		return true;
	}}
