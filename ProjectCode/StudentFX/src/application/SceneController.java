package application;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class SceneController {
  /*
 @stage: opens the stage
 @scene: opens the scene
 @root:  opens the root
 */
 private Stage stage;
 private Scene scene;
 private Parent root;

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
//
public void switchToProfLogIn(ActionEvent event) throws IOException {
	root = FXMLLoader.load(getClass().getResource("ProfessorLogIn.fxml"));
	stage = (Stage)((Node)event.getSource()).getScene().getWindow();
	scene = new Scene(root);
	stage.setScene(scene);
	stage.show();
	}
}