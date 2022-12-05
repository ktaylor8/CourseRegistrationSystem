package application;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class ProfessorController {
	
		@FXML
	    private Button btnDelete;
	    @FXML
	    private Button btnFour;
	    @FXML
	    private Button btnOne;
	    @FXML
	    private Button btnThree;
	    @FXML
	    private Button btnTwo;
	    @FXML
	    private Button btn_signout;
	    @FXML
	    private TableColumn<?, ?> email;
	    @FXML
	    private Label prof_email;
	    @FXML
	    private TableColumn<?, ?> name;
	    @FXML
	    private Label welcome_prof;
	    @FXML
	    private TableView<?> table;
	    
	    public void initialize(URL arg0, ResourceBundle arg1) {
	    	btn_signout.setOnAction(new EventHandler<ActionEvent>(){
				@Override 
				public void handle(ActionEvent event ) {
					ProfessorUtils.changeSceneProf(event, "Menu.fxml","Login!", null, null);
				}
			});
		}
	    
	    public void setProfInfo(String prof_name, String pfemail) {
	    	welcome_prof.setText("Welcome " + prof_name + "!");
	    	prof_email.setText("Email: " + pfemail);
	    }
}


