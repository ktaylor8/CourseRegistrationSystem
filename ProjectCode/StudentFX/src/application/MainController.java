package application;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class MainController implements Initializable {
    @FXML
    private Button button_login;
    @FXML
    private Button button_signup;
    @FXML
    private TextField tf_email;
    @FXML
    private PasswordField tf_password;
    
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		button_login.setOnAction(new EventHandler<ActionEvent>(){
			@Override 
			public void handle(ActionEvent event ) {
				LoggedInUtils.logInUser(event, tf_email.getText(), tf_password.getText());			
			}
	
		});
		button_signup.setOnAction(new EventHandler<ActionEvent>(){
			@Override 
			public void handle(ActionEvent event ) {
				LoggedInUtils.changeScene(event, "sign-up.fxml","Sign-up!",null,null);	
		}
	});
	}
}



