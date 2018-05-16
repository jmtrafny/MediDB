/**
 * Lana Adams, James Trafny
 * CS 317 - Final project
 * 
 * This application is designed to store and manage patient medicine information.
 * 
 * This is the login stage for the application, the user
 * will enter his/her username and password, then click log in
 * to continue to the home page.
 */
import java.util.prefs.Preferences;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class MediChartGUI extends Application{

	String url = "jdbc:mysql://localhost:3306/medidb?useSSL=false";
	String user = "root";
	String pswd = "BlueJean1018";
	MediDB mediDB = new MediDB(url, user, pswd);
	

	//gridpane for the login
	private GridPane loginPane;

	//text fields for user inputs
	static TextField userID = new TextField();
	/**
	 * creates a TextField() to allow user to input password
	 */
	static PasswordField userPassword = new PasswordField();
	
	/**
	 * button to login
	 */
	private Button loginBt;
	
	/**
	 * button to get help
	 */
	private Button helpBt;
	
	/**
	 * button to create account
	 */
	private Button createAccountBt;

	/**
	 * label to show user where to input password
	 */
	private Label pwLabel;
	
	/**
	 * label to show user where to input email

	 */
	private Label userLabel;
		



	public MediChartGUI() {
		//instantiate the login pane
		loginPane = new GridPane();
		loginPane.setVgap(20);
		loginPane.setHgap(20);


		//login labels
		pwLabel = new Label("Password:");
		userLabel = new Label("User Name:");
		

		//instantiate the buttons and text fields 
		loginBt = new Button("Login");
		helpBt = new Button("Help");
		createAccountBt = new Button("Create Account");
		//ToolTips for buttons
		Tooltip tt1 = new Tooltip("Click to log in");
		Tooltip.install(loginBt, tt1);
		Tooltip tt2 = new Tooltip("Click for more information");
		Tooltip.install(helpBt, tt2);
		//layout
		loginPane.setAlignment(Pos.CENTER);
		loginPane.setOpacity(100);
		loginPane.add(userLabel , 0, 0);
		loginPane.add(userID, 1, 0);
		loginPane.add(pwLabel, 0, 1);
		loginPane.add(userPassword, 1, 1);
		loginPane.add(loginBt, 1, 3);
		loginPane.add(helpBt, 1, 5);
		loginPane.add(createAccountBt, 1, 4);
	}

	public void start(Stage MediChartGUI) {

		//Application root
		BorderPane borderPane = new BorderPane();
		borderPane.setCenter(loginPane);


		//Button Handlers
		helpBt.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				//Display help window
				Alert helping = new Alert(AlertType.INFORMATION);
				helping.setTitle("MediDB Help");
				helping.setHeaderText("Enter your 4-digit User ID, your password, and click Login");
				helping.showAndWait();

			}
		});
		

		//Application layout
		Scene scene = new Scene(borderPane, 500,500);
		scene.getStylesheets().add("stylesheetMDB.css");
		MediChartGUI.setTitle("MediDB"); // Set the stage title
		MediChartGUI.setScene(scene); // Place the scene in the stage
		MediChartGUI.show(); // Display the stage


		/////////////////////////////////////////////////////////////////////////////////
		//login button
		userHome userHome = new userHome();

		
		loginBt.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				try {
					//catch input
					String userName = userID.getText();
					int user_id = Integer.parseInt(userName);
					String password = userPassword.getText();
					boolean loginAttempt = mediDB.logIn(user_id, password);		
					if (loginAttempt) {
						Preferences up = Preferences.userRoot();
						up.putInt("userID", user_id);
						userHome.start(MediChartGUI);
						
					}else {
						userPassword.clear();
					}
					
				} catch (Exception e1) {
					// TODO Auto-generated catch block

				}
			}});

		createAccountBt.setOnAction(e -> {
            boolean result = createAccount.display("Create New Account");
        });
        



	}





	public static void main(String[] args) {
		launch(args);
	}




}
