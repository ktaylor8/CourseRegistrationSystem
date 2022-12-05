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

public class LoggedInUtils {
	
	public static void changeScene(ActionEvent event, String fxmlFile, 
			String title, String student_name, String student_email) {
	Parent root = null;
	//creates a scene from a FXML document 
	if (student_name != null && student_email != null){
		try {
			FXMLLoader loader = new FXMLLoader(LoggedInUtils.class.getResource(fxmlFile));
			root = loader.load();
			LoggedInController loggedInController = loader.getController();
			loggedInController.setUserInformation(student_name, student_email);
		}catch (IOException e) {
			e.printStackTrace();
		}
	} else {
		try {
			root = FXMLLoader.load(LoggedInUtils.class.getResource(fxmlFile));
		}catch (IOException e) {
			e.printStackTrace();
		}
	}
	Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
	stage.setTitle(title);
	stage.setScene(new Scene(root, 600, 400));
	stage.show();
}
	
	public static void signUpUser(ActionEvent event, String student_name, String student_email, String student_pass ) {
	Connection connection = null;
	PreparedStatement psInsert = null;
	PreparedStatement psCheckUserExists = null;
	ResultSet resultSet = null;
	//MySQL connection
	try {
		connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/sys", "root", "Vaseline2096@");
		psCheckUserExists = connection.prepareStatement("SELECT * FROM student WHERE student_name = ?");
		psCheckUserExists.setString(1, student_name);
		resultSet = psCheckUserExists.executeQuery();
		
		if(resultSet.isBeforeFirst()) {
			System.out.println("User already exists!");
			Alert alert = new Alert(Alert.AlertType.ERROR);
			alert.setContentText("You cannot use this username.");
			alert.show();
		} else {
			psInsert = connection.prepareStatement("INSERT INTO student (student_name, student_email, student_pass) VALUES (?,?,?)");
			psInsert.setString(1, student_name);
			psInsert.setString(2, student_email);
			psInsert.setString(3, student_pass );
			psInsert.executeUpdate();
			
			changeScene(event, "Menu.fxml", "Welcome!", student_name, student_email);
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
	
//Retrieves the log-in info from the MYSQL database 
	public static void logInUser(ActionEvent event, String student_email, String student_pass) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/sys", "root", "Vaseline2096@");
			preparedStatement = connection.prepareStatement("SELECT student_pass, student_name FROM student WHERE student_email = ?");
			preparedStatement.setString(1, student_email);
			resultSet = preparedStatement.executeQuery();
			if(!resultSet.isBeforeFirst()) {
				System.out.println("Email not found in the database!");
				Alert alert = new Alert(Alert.AlertType.ERROR);
				alert.setContentText("Provided credentials are incorrect!");
				alert.show();
			}else {
				while(resultSet.next()) {
					String retrievedPassword = resultSet.getString("student_pass");
					String retrievedName     = resultSet.getString("student_name");
					if(retrievedPassword.equals(student_pass)) {
						changeScene(event, "Menu.fxml", "Welcome!", retrievedName, student_email);
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
	
//adds a course to the course table in MYSQL database
	public static void addCourses(ActionEvent event, String course_name, String department) {
		Connection connection = null;
		PreparedStatement psInsert = null;
		PreparedStatement psCheckUserExists = null;
		ResultSet resultSet = null;
		//MySQL connection
		try {
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/sys", "root", "Vaseline2096@");
			psCheckUserExists = connection.prepareStatement("SELECT * FROM course WHERE course_name = ?");
			psCheckUserExists.setString(1, course_name);
			resultSet = psCheckUserExists.executeQuery();
			
			if(resultSet.isBeforeFirst()) {
				System.out.println("Course already exists!");
				Alert alert = new Alert(Alert.AlertType.ERROR);
				alert.setContentText("Course already selected");
				alert.show();
			} else {
				psInsert = connection.prepareStatement("INSERT INTO course (course_name, department) VALUES (?,?)");
				psInsert.setString(1, course_name);
				psInsert.setString(2, department);
				psInsert.executeUpdate();
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
				