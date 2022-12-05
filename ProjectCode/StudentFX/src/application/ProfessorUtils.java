package application;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

public class ProfessorUtils {
	
	public static void changeSceneProf(ActionEvent event, String fxmlFile, 
	String title, String prof_name, String prof_email) {
	Parent root = null;
	//creates a scene from a FXML document 
	if (prof_name != null && prof_email != null){
		try {
			FXMLLoader loader = new FXMLLoader(ProfessorUtils.class.getResource(fxmlFile));
			root = loader.load();
			ProfessorController professorController = loader.getController();
			professorController.setProfInfo(prof_name, prof_email);
		}catch (IOException e) {
			e.printStackTrace();
		}
	} else {
		try {
			root = FXMLLoader.load(ProfessorUtils.class.getResource(fxmlFile));
		}catch (IOException e) {
			e.printStackTrace();
		}
	}
	Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
	stage.setTitle(title);
	stage.setScene(new Scene(root, 600, 400));
	stage.show();
}
	
	public static void logInProfessor(ActionEvent event, String prof_email, String prof_pass) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/sys", "root", "Vaseline2096@");
			preparedStatement = connection.prepareStatement("SELECT prof_pass, prof_name FROM professor WHERE prof_email = ?");
			preparedStatement.setString(1, prof_email);
			resultSet = preparedStatement.executeQuery();
			if(!resultSet.isBeforeFirst()) {
				System.out.println("Email not found in the database!");
				Alert alert = new Alert(Alert.AlertType.ERROR);
				alert.setContentText("Provided credentials are incorrect!");
				alert.show();
			}else {
				while(resultSet.next()) {
					String retrievedPassword = resultSet.getString("prof_pass");
					String retrievedName     = resultSet.getString("prof_name");
					if(retrievedPassword.equals(prof_pass)) {
						changeSceneProf(event,"ProfessorMain.fxml", "LogIn", retrievedName, prof_email);
					}else {
						System.out.println("Password did not match!");
						Alert alert = new Alert(Alert.AlertType.ERROR);
						alert.setContentText("The provided credentials are incorrect!");
						alert.show();
					}
				}
				
			}
		}catch (SQLException e){
			e.printStackTrace();
		} finally {
			if(resultSet != null) {
				try {
					resultSet.close();
				}catch (SQLException e) {
					e.printStackTrace();
				}
			}
			}
		if (preparedStatement != null) {
			try {
				preparedStatement.close();
			}catch (SQLException e){
				e.printStackTrace();	
			}
		}
		if (connection != null) {
			try {
				connection.close();
			}catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	public static void signUpProf(ActionEvent event, String prof_name, String prof_email, String prof_pass ) {
		Connection connection = null;
		PreparedStatement psInsert = null;
		PreparedStatement psCheckUserExists = null;
		ResultSet resultSet = null;
		//MySQL connection
		try {
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/sys", "root", "Vaseline2096@");
			psCheckUserExists = connection.prepareStatement("SELECT * FROM professor WHERE prof_name = ?");
			psCheckUserExists.setString(1, prof_name);
			resultSet = psCheckUserExists.executeQuery();
			
			if(resultSet.isBeforeFirst()) {
				System.out.println("User already exists!");
				Alert alert = new Alert(Alert.AlertType.ERROR);
				alert.setContentText("You cannot use this username.");
				alert.show();
			} else {
				psInsert = connection.prepareStatement("INSERT INTO professor (prof_name, prof_email, prof_pass) VALUES (?,?,?)");
				psInsert.setString(1, prof_name);
				psInsert.setString(2, prof_email);
				psInsert.setString(3, prof_pass );
				psInsert.executeUpdate();
				
				changeSceneProf(event,"ProfessorMain.fxml","Welcome!", prof_name, prof_email);
			}
			}catch (SQLException e){
				e.printStackTrace();
			}finally {
				if (resultSet != null) {
					try {
						resultSet.close();
					}catch (SQLException e) {
						e.printStackTrace();
					}
				}
				if(psCheckUserExists != null) {
					try {
						psCheckUserExists.close();
					}catch (SQLException e) {
						e.printStackTrace();
					}
				}
				if(psInsert != null) {
					try {
						psInsert.close();
					}catch (SQLException e) {
						e.printStackTrace();
					}
				}
				if (connection !=null) {
					try {
						connection.close();
					}catch(SQLException e) {
						e.printStackTrace();
					}
				}
			}
		}
}
