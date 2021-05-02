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

public class Controller_Gestion_patients {
	

	@FXML
    private Button select_fich;

    @FXML
    private Button select_diagnostics;

    @FXML
    private Label retour_id;
    


    @FXML
    void retour_clicked(MouseEvent event) throws Exception {
    	((Stage)(((Label)event.getSource()).getScene().getWindow())).close(); 
		Stage primaryStage = new Stage();
		Parent root = FXMLLoader.load(getClass().getResource("/application/Medecinpage.fxml"));
		Scene scene = new Scene(root,900,600);
		
		primaryStage.setScene(scene);
		primaryStage.show();

    }
    @FXML
    void select_diagnostics_button_action(ActionEvent event) throws IOException {
    	((Stage)(((Button)event.getSource()).getScene().getWindow())).close(); 
		Stage primaryStage = new Stage();
		Parent root = FXMLLoader.load(getClass().getResource("/application/Diagnostics_Page.fxml"));
		Scene scene = new Scene(root,900,600);
		
		primaryStage.setScene(scene);
		primaryStage.show();

    }
    
    @FXML
    void select_fiche_diagnostics_action(ActionEvent event) throws IOException {
    	((Stage)(((Button)event.getSource()).getScene().getWindow())).close(); 
		Stage primaryStage = new Stage();
		Parent root = FXMLLoader.load(getClass().getResource("/application/Fiche_Page.fxml"));
		Scene scene = new Scene(root,900,600);
		
		primaryStage.setScene(scene);
		primaryStage.show();


    }


}
