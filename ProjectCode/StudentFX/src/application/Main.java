package application;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;

public class Main extends Application {
	 /*
	 Starts the first scene 
	 After the button is pressed switches to "SceneMenu" 
	 Controller used: "SceneController"
	 "Missing" log-in error when attempting to use invalid characters or incorrect password
	 */
	@Override
	 public void start(Stage stage) {
		 try {
			   
		Parent root = FXMLLoader.load(getClass().getResource("Options.fxml"));
		Scene scene = new Scene(root);
	    stage.setScene(scene);
		stage.show();
			   
	    } catch(Exception e) {
		e.printStackTrace();
		}
		} 
			 
	    public static void main(String[] args) {
		launch(args);
	    }
}
