package application;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class Controller_Medecinpage {
	@FXML
    private Label logout_id;

    @FXML
    private Button select_gestion_patients;

    @FXML
    private Button select_ordonnance;

    @FXML
    private Button select_certificat_medecal;
    
    @FXML
    void action_select_gestion_patients(ActionEvent event) throws IOException {
    	((Stage)(((Button)event.getSource()).getScene().getWindow())).close(); 
		Stage primaryStage = new Stage();
		Parent root = FXMLLoader.load(getClass().getResource("/application/Gestion_patients.fxml"));
		Scene scene = new Scene(root,900,600);
		
		primaryStage.setScene(scene);
		primaryStage.show();

    }
    
    @FXML
    void logout_action(MouseEvent event) throws IOException {
    	((Stage)(((Label)event.getSource()).getScene().getWindow())).close();
		Stage primaryStage = new Stage();
		Parent root = FXMLLoader.load(getClass().getResource("/application/Login.fxml"));
		Scene scene = new Scene(root,500,500);
		
		primaryStage.setScene(scene);
		primaryStage.show();

    }
    @FXML
    void action_select_ordonnacne(ActionEvent event) throws IOException {
    	((Stage)(((Button)event.getSource()).getScene().getWindow())).close(); 
		Stage primaryStage = new Stage();
		Parent root = FXMLLoader.load(getClass().getResource("/application/Ordonnance_Page.fxml"));
		Scene scene = new Scene(root,1000,600);
		
		primaryStage.setScene(scene);
		primaryStage.show();

    }
    @FXML
    void action_select_certificat_medecal(ActionEvent event) throws IOException {
    	((Stage)(((Button)event.getSource()).getScene().getWindow())).close(); 
		Stage primaryStage = new Stage();
		Parent root = FXMLLoader.load(getClass().getResource("/application/Certificat_Page.fxml"));
		Scene scene = new Scene(root,900,600);
		
		primaryStage.setScene(scene);
		primaryStage.show();
    }
    

}
