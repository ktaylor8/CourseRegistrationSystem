package application;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
public class LoggedInController implements Initializable {
	
	private Stage stage;
	private Scene scene;
	private Parent root;
	
	@FXML
    private Button button_logout;
    @FXML
    private Label student_email;
    @FXML
    private Label student_welcome;
    
	public void initialize(URL arg0, ResourceBundle arg1) {
		button_logout.setOnAction(new EventHandler<ActionEvent>(){
			@Override 
			public void handle(ActionEvent event ) {
				LoggedInUtils.changeScene(event, "MainFX.fxml","Login!", null, null);
			}
		});
	}
		
	public void setUserInformation(String student_name, String email) {
	student_welcome.setText("Welcome " + student_name + "!");
	student_email.setText("Email: " + email);
	}

	
	//Used in the "sign-out button" and switches to the log-in interface
	public void switchToScene1(ActionEvent event) throws IOException {
		root = FXMLLoader.load(getClass().getResource("MainFX.fxml"));
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	  	}
	//Switches to the "AddCourses scene"
	public void switchToCourseScene(ActionEvent event) throws IOException {
		Parent root = FXMLLoader.load(getClass().getResource("AddCourses.fxml"));
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	  	}
	//Used in the "Back button" and switches to the "Menu Scene"
	public void switchToSceneMenu(ActionEvent event) throws IOException {
		Parent root = FXMLLoader.load(getClass().getResource("Menu.fxml"));
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
		 }
	//Switches to the "Alternates scene"
	public void switchToAlternateScene(ActionEvent event) throws IOException {
		Parent root = FXMLLoader.load(getClass().getResource("Alternates.fxml"));
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
		 }
	//Switches to the "Billing scene"
	public void switchToBillingScene(ActionEvent event) throws IOException {
		Parent root = FXMLLoader.load(getClass().getResource("Billing.fxml"));
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
		}
}
