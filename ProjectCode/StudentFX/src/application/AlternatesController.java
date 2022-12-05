package application;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

public class AlternatesController implements Initializable {
	//Variables used to switch the current scene back to the "Menu scene"
	private Stage stage;
	private Scene scene;
    @FXML
    private Button btnConfirm;

    @FXML
    private Button btnDelete;

    @FXML
    private Button btnSub;

    @FXML
    private ChoiceBox<String> cbAlt;

    @FXML
    private TextArea taInfo;
    //Array of strings with the options for courses 
	private String[] cbOptions = {"ECE2214","FR1014","CS1303","MATH1503"};
	//Store a collection of elements in "cbOptions"
	private ObservableList<String> aList = FXCollections.observableArrayList(cbOptions);
	
	public void initialize(URL arg0, ResourceBundle arg1) {
	//Add item to the ChoiceBox
	cbAlt.setItems(aList);
	}
	
	//This method is still missing "can't choose more than 2" or "the same option twice"
    @FXML
    void handleButtonAction(ActionEvent event) {
    	if(cbAlt.getSelectionModel().getSelectedItem().equals("ECE2214")) 
    	{
    		taInfo.appendText("ECE2214" + "\nDigital Logic Design" 
    	+ "\nDr.Idris Gadoura"+ "\nPrerequisite: CS 1073 or CS1003" + "\n");
    	}
    	else
    		if(cbAlt.getSelectionModel().getSelectedItem().equals("FR1014")) 
    		{
    			taInfo.appendText("\nFR1014" + "\nBasic French I"  
    		+ "\nMuriel Chaput"+ "\nPrerequisite: NONE" + "\n");
    		}
    		else
    			if(cbAlt.getSelectionModel().getSelectedItem().equals("CS1303")) {
    				taInfo.appendText("\nCS1303" + "\nDiscrete Structures" 
    			+ "\nDr.Prabhat Mahanti" + "\nPrerequisite: NONE"+ "\n");
    			}
    			else
    				if(cbAlt.getSelectionModel().getSelectedItem().equals("MATH1503")) {
    					taInfo.appendText("\nMATH1503" + "\nIntroduction to Linear Algebra" 
    					+ "\nRebecca McKay" + "\nPrerequisite: Pre-calculus A 120 "
    							+ "and Pre-calculus B 120, or equivalent" + "\n");
    					}
    }
    //Switches to the "Menu scene"
    public void switchToMenuScene(ActionEvent event) throws IOException {
		  Parent root = FXMLLoader.load(getClass().getResource("Menu.fxml"));
		  stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		  scene = new Scene(root);
		  stage.setScene(scene);
		  stage.show();
		 }
   
}