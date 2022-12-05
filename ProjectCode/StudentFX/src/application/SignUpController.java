package application;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class SignUpController implements Initializable{
	@FXML
    private Button button_log_in;
    @FXML
    private Button button_signup;
    @FXML
    private TextField tf_email;
    @FXML 
    private TextField tf_username;
    @FXML
    private TextField tf_password;
    /*Stores the information of a student in the database
      Checks if the user correctly fill the information asked 
    */
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
	 button_signup.setOnAction(new EventHandler<ActionEvent>() {
		 @Override
		 public void handle(ActionEvent event) {
			if(!tf_email.getText().trim().isEmpty() && !tf_password.getText().trim().isEmpty()
		       && !tf_username.getText().trim().isEmpty()) 
			{
			LoggedInUtils.signUpUser(event, tf_username.getText(), tf_email.getText(), 
					tf_password.getText());
			}else {
				System.out.println("Please fill in all information");
				Alert alert = new Alert(Alert.AlertType.ERROR);
				alert.setContentText("Please fill in all information to sign up!");
				alert.show();
			}
		}
	});
	//Switches the scene to the logged-interface
	button_log_in.setOnAction(new EventHandler<ActionEvent>() {
		@Override
		public void handle(ActionEvent event) {
		LoggedInUtils.changeScene(event, "MainFX.fxml", "Log in!", null, null);
		}	
	});
	}
}
