package application;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.fxml.Initializable;


public class ProfessorSignUpController implements Initializable{

    @FXML
    private Button button_pflogin;
    @FXML
    private Button button_pfsignup;

    @FXML
    private TextField tf_profemail;

    @FXML
    private TextField tf_profpassword;

    @FXML
    private TextField tf_profusername;
    
     @Override
	 public void initialize(URL arg0, ResourceBundle arg1) {
	 button_pfsignup.setOnAction(new EventHandler<ActionEvent>() {
		 @Override
		 public void handle(ActionEvent event) {
			if(!tf_profemail.getText().trim().isEmpty() && !tf_profpassword.getText().trim().isEmpty()
		       && !tf_profusername.getText().trim().isEmpty()) 
			{
				ProfessorUtils.signUpProf(event, tf_profusername.getText(), tf_profemail.getText(), 
					tf_profpassword.getText());
			}else {
				System.out.println("Please fill in all information");
				Alert alert = new Alert(Alert.AlertType.ERROR);
				alert.setContentText("Please fill in all information to sign up!");
				alert.show();
			}
		}
	});
	//Switches the scene to the logged-interface
	button_pflogin.setOnAction(new EventHandler<ActionEvent>() {
		@Override
		public void handle(ActionEvent event) {
			ProfessorUtils.changeSceneProf(event, "ProfessorLogIn.fxml", "Log in!", null, null);
		}	
	});
	}
}

