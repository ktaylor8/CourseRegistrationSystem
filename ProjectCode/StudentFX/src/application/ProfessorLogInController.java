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

public class ProfessorLogInController implements Initializable{
	    @FXML
	    private Button button_pflogin;
	    @FXML
	    private Button button_pfsignup;
	    @FXML
	    private TextField tf_emailpf;
	    @FXML
	    private PasswordField tf_passwordpf;
	    
	    @Override
		public void initialize(URL arg0, ResourceBundle arg1){
			button_pflogin.setOnAction(new EventHandler<ActionEvent>(){
				@Override 
				public void handle(ActionEvent event ) {
					ProfessorUtils.logInProfessor(event, tf_emailpf.getText(), tf_passwordpf.getText());			
				}
		
			});
			button_pfsignup.setOnAction(new EventHandler<ActionEvent>(){
				@Override 
				public void handle(ActionEvent event ) {
					ProfessorUtils.changeSceneProf(event, "ProfessorSignUp.fxml","Sign-up!",null,null);	
			}
		});
		}
}

