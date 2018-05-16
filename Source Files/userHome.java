/**
 * Lana Adams, James Trafny
 * CS 317 - Final project
 * 
 * This application is designed to store and manage patient medicine information.
 * 
 * This is the home stage for the application, the user
 * can choose to update, send, download, view, or manage
 * their medicine list by clicking the corresponding button.
 */

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Popup;
import javafx.stage.Stage;
import javafx.stage.Window;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Tooltip;

public class userHome extends Application{
	////////////////////////////////////////////////////////////////////////////////////
	//grid pane for button options
	private BorderPane borderPane;
	private GridPane centerBPane;
	////////////////////////////////////////////////////////////////////////////////////
	//menu bar set up
	private MenuBar menubar;
	private Menu menuFile, menuHelp, menuOptions;
	//menu items
	private MenuItem miClose, miLogout, miManage, miUpdate, miView, miSend, miDownload;
	private MenuItem miAbout;
	////////////////////////////////////////////////////////////////////////////////////
	/**
	 * Button for user to update their current medicine.
	 */
	private Button updateBt;

	/**
	 * Button for user to view their chart
	 */
	private Button viewBt;

	/**
	 * Button for user to email chart
	 */
	private Button sendBt;

	/**
	 * Button to download PDF of chart
	 */
	private Button downloadBt;

	/**
	 * Button for user to manage their refills
	 */
	private Button manageRXBt;
	////////////////////////////////////////////////////////////////////////////////////

	public userHome() {
		////////////////////////////////////////////////////////////////////////////////
		//instantiate the button pane
		centerBPane = new GridPane();
		centerBPane.setVgap(20);
		centerBPane.setHgap(20);
		centerBPane.setAlignment(Pos.CENTER);

		////////////////////////////////////////////////////////////////////////////////////
		//instantiate buttons
		updateBt = new Button("Update\nMedicine");
		viewBt = new Button("View\nMediChart");
		sendBt = new Button("Send\nMediChart");
		downloadBt = new Button("Download\nTo PDF");
		manageRXBt = new Button("Manage\nRX");

		//add tooltips
		Tooltip tt1 = new Tooltip("Click to add or remove medicine");
		Tooltip.install(updateBt, tt1);
		Tooltip tt2 = new Tooltip("Click to view your weekly MediChart");
		Tooltip.install(viewBt, tt2);
		Tooltip tt3 = new Tooltip("Click to send your medicine list via email");
		Tooltip.install(sendBt, tt3);
		Tooltip tt4 = new Tooltip("Click to download a PDF of your current medicine list");
		Tooltip.install(downloadBt, tt4);
		Tooltip tt5 = new Tooltip("Click to change your medicine information");
		Tooltip.install(manageRXBt, tt5);

		////////////////////////////////////////////////////////////////////////////////
		//layout
		centerBPane.add(updateBt, 1, 0);
		centerBPane.add(viewBt, 3, 0);
		centerBPane.add(sendBt, 5, 0);
		centerBPane.add(downloadBt, 2, 1);
		centerBPane.add(manageRXBt, 4, 1);

		////////////////////////////////////////////////////////////////////////////////
		//menu bar
		menuFile = new Menu("File");
		menuHelp = new Menu("Help");
		menuOptions = new Menu("Options");

		menubar = new MenuBar();

		//menuFile.getItems().addAll(miLogout, miClose);
		//menuHelp.getItems().add(miAbout);
		//menuOptions.getItems().addAll(miManage, miUpdate, miView, miSend, miDownload );

		menubar.getMenus().addAll(menuFile, menuOptions, menuHelp);
		////////////////////////////////////////////////////////////////////////////////


	}

	public void start(Stage userHome) {

		borderPane = new BorderPane();
		Scene scene = new Scene(borderPane, 500, 500);
		scene.getStylesheets().add("stylesheetMDB2.css");
		//menubar
		borderPane.setTop(menubar);
		borderPane.setCenter(centerBPane);
		

		userHome.setScene(scene);
		userHome.setTitle("MediDB");
		userHome.setMaximized(false);
		userHome.setMaximized(true);
		userHome.show();

		/////////////////////////////////////////////////////////////////////////////////
		//login button
		updateBt.setOnAction(e -> {
            boolean result = updatePopUp.display("Update MediChart");
        });

		sendBt.setOnAction(f -> {
			boolean result = sendPopUp.display("Send to Doctor");
		});
		
		manageRXBt.setOnAction(g -> {
			boolean result = manageRXPopup.display("Update Medicine Count");
		});
		
		viewBt.setOnAction(h -> {
			boolean result = viewRegimen.display("Your Regimen");
			
		});

	}

	public static void main(String[] args) {
		launch(args);

	}

}