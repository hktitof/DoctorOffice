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
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;




public class ControllerSecretary {
	@FXML
    private Button select_rendezvous;
	@FXML
    private AnchorPane image_rendezvous;
	@FXML
    private Button select_fich;
	@FXML
    private Button select_factures;
	@FXML
    private Label logout_id;
	
	
	@FXML
    void action_rendez_vous(ActionEvent event) throws Exception{
		((Stage)(((Button)event.getSource()).getScene().getWindow())).close(); 
		Stage primaryStage = new Stage();
		Parent root = FXMLLoader.load(getClass().getResource("/application/Rendez_vousPage.fxml"));
		Scene scene = new Scene(root,900,600);
		
		primaryStage.setScene(scene);
		primaryStage.show();

    }
	@FXML
    void logout_action_clicked(MouseEvent event)throws Exception {
		((Stage)(((Label)event.getSource()).getScene().getWindow())).close();
		Stage primaryStage = new Stage();
		Parent root = FXMLLoader.load(getClass().getResource("/application/Login.fxml"));
		Scene scene = new Scene(root,500,500);
		
		primaryStage.setScene(scene);
		primaryStage.show();

    }
	 @FXML
	    void action_fiche(ActionEvent event) throws IOException {
		 ((Stage)(((Button)event.getSource()).getScene().getWindow())).close(); 
			Stage primaryStage = new Stage();
			Parent root = FXMLLoader.load(getClass().getResource("/application/Fiche_Page.fxml"));
			Scene scene = new Scene(root,900,600);
			
			primaryStage.setScene(scene);
			primaryStage.show();

	    }
	 @FXML
	    void action_facture(ActionEvent event) throws IOException {
		 ((Stage)(((Button)event.getSource()).getScene().getWindow())).close(); 
			Stage primaryStage = new Stage();
			Parent root = FXMLLoader.load(getClass().getResource("/application/Facture_Page.fxml"));
			Scene scene = new Scene(root,900,600);
			
			primaryStage.setScene(scene);
			primaryStage.show();

	    }
	
	
	

}
