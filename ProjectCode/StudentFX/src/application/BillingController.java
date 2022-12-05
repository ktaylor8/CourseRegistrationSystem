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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class BillingController implements Initializable{
	//variables used to switch the current scene to the "Menu scene"  
	private Stage stage;
	private Scene scene;	
    @FXML
    private TableColumn<BillingModel, String> course;
    
    @FXML
    private TableColumn<BillingModel, Double> price;
    
    @FXML
    private TableView<BillingModel> table;
    //method used is a reference to the billing model class
    //only accepts Strings and Doubles for the BillingModel parameters
    ObservableList<BillingModel> list = FXCollections.observableArrayList(
    		new BillingModel("CS2043", 1024.95),
			new BillingModel("CS1083", 1024.95),
			new BillingModel("CS1073", 1024.95),
			new BillingModel("CS1103", 1024.95),
			new BillingModel("Total",   4099.8)
	);
    //initialize the variables in each respective columns @course and @price
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
	    course.setCellValueFactory(new PropertyValueFactory<BillingModel, String>("course"));
		price.setCellValueFactory(new PropertyValueFactory<BillingModel, Double>("price"));
		table.setItems(list);
	}
	
	//Goes back to the menu scene
	public void switchToSceneMenu(ActionEvent event) throws IOException {
		  Parent root = FXMLLoader.load(getClass().getResource("Menu.fxml"));
		  stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		  scene = new Scene(root);
		  stage.setScene(scene);
		  stage.show();
	}
	/*
	course.setCellValueFactory(data -> data.getValue().getCourse());
	price.setCellValueFactory(data -> data.getValue().getCourse());
	*/
}
