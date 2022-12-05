package application;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;


public class CoursesController implements Initializable{
	
	@FXML
	private Label label;
	@FXML
	private Button btnConfirm;
	@FXML
	private Button button_back;
	@FXML
	private Button btnOk;
	@FXML
	private TextArea taInformation;
	@FXML
	private ChoiceBox<String> cbItems;
	
	private String[] cbTest = {"CS2043","CS1083","CS1073","CS1103"};
	//Store a collection of elements
	private ObservableList<String> list = FXCollections.observableArrayList(cbTest);
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
	//Add item to the ChoiceBox
	//cbItems.setItems(drinkList);
	//cbItems.setItems(dList);
		//Returns to the main menu
	button_back.setOnAction(new EventHandler<ActionEvent>(){
	@Override 
	public void handle(ActionEvent event ) {
	LoggedInUtils.changeScene(event, "Menu.fxml","Menu!", null, null);	
	}
	});
	cbItems.setItems(list);
	}
	
	
	@FXML
	private void handleButtonAction(ActionEvent event) {
	if(cbItems.getSelectionModel().getSelectedItem().equals("CS2043")) 
	{
	taInformation.appendText("CS2043" + "\nIntro to software engineering" 
	+ "\nDr.Prabhat Mahanti"+ "\nPrerequisite: CS 1073" + "\n");
	LoggedInUtils.addCourses(event, cbItems.getValue(), " ");
	}
	else 
		if(cbItems.getSelectionModel().getSelectedItem().equals("CS1083")) 
		{
		taInformation.appendText("\nCS1083" + "\nIntroduction to Computer Programming II"  
		+ "\nDr.Prabhat Mahanti"+ "\nPrerequisite: CS 1073" + "\n");
		LoggedInUtils.addCourses(event, cbItems.getValue(), " ");
		}
		else
			if(cbItems.getSelectionModel().getSelectedItem().equals("CS1073")) {
			taInformation.appendText("\nCS1073" + "\nIntroduction to Computer Programming I" 
			+ "\nDr.Prabhat Mahanti" + "\nPrerequisite: NONE"+ "\n");
			LoggedInUtils.addCourses(event, cbItems.getValue(), " ");
			}
			else
				if(cbItems.getSelectionModel().getSelectedItem().equals("CS1103")) {
					taInformation.appendText("\nCS1103" + "\nIntroduction to Databases" 
					+ "\nDr.Owen Kaser" + "\nPrerequisite: CS 1073"+ "\n");
					LoggedInUtils.addCourses(event, cbItems.getValue(), " ");
				}
	}
}


