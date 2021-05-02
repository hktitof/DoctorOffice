package application;





import java.net.URL;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;



import Data.DBConnection;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;

import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;


import javafx.scene.control.Alert;
import javafx.scene.control.Button;

public class Controller_Login implements Initializable{
	@FXML
    private Button login_id;
	@FXML
    private Button quit_button;
	

	@FXML 
	private Label Labelstatus;
	
	@FXML 
	private TextField txtusername;
	
	@FXML 
	private TextField txtpassword;
	@FXML
    private AnchorPane login_interface;
	
	
	public static int varforchecking;
	@Override
	public void initialize(URL location, ResourceBundle arg1) {
		
		
	}
	
	
	
	
	@FXML
    void textfield_password_typing(KeyEvent event) {
		if(event.getCode() == KeyCode.TAB) {
			login_id.requestFocus();
		}

    }
	
	
	
	
	@FXML
    void action_quit_button(ActionEvent event) {
		((Stage)(((Button)event.getSource()).getScene().getWindow())).close();

    }
	
	
	@FXML
    void Mouse_pressed(MouseEvent event) {
		login_interface.requestFocus();
	}

    
	
	
	@FXML
	public void login(ActionEvent event) throws Exception {
		if((txtusername.getText().trim().isEmpty()) || (txtpassword.getText().trim().isEmpty())) {
			Alert  alert = new Alert(AlertType.ERROR);
			alert.setTitle("Login");
			alert.setHeaderText("Information");
			alert.setContentText("Missing Fields!");
			alert.showAndWait();
			if(txtusername.getText().trim().isEmpty()) {
				txtusername.requestFocus();
			}else {
				txtpassword.requestFocus();
			}
		}else {
			int status = DBConnection.checkLogin(txtusername.getText().trim().toLowerCase(), txtpassword.getText());
			switch(status) {
			case 0 : {
				String var_for_user_admin = "admin";
				String var_for_user_medecin = "medecin";
				
				if(txtusername.getText().trim().toLowerCase().matches(var_for_user_admin)) {
					Stage primaryStage = new Stage();
					Parent root = FXMLLoader.load(getClass().getResource("/application/AdminPage.fxml"));
					Scene scene = new Scene(root,900,600);
					
					primaryStage.setScene(scene);
					primaryStage.show();
					
				}else if(txtusername.getText().trim().toLowerCase().matches(var_for_user_medecin)){
					varforchecking=0;
					
					Stage primaryStage = new Stage();
					Parent root = FXMLLoader.load(getClass().getResource("/application/Medecinpage.fxml"));
					Scene scene = new Scene(root,900,600);
					
					primaryStage.setScene(scene);
					primaryStage.show();
				}else {
					varforchecking=1;
					
					Stage primaryStage = new Stage();
					Parent root = FXMLLoader.load(getClass().getResource("/application/Secretarypage.fxml"));
					Scene scene = new Scene(root,900,600);
					
					primaryStage.setScene(scene);
					primaryStage.show();
					
				}
				((Stage)(((Button)event.getSource()).getScene().getWindow())).close();
				
			
				
			}break;
			case -1 : JOptionPane.showMessageDialog(null, "Conenction Failed"); break;
			case 1 : JOptionPane.showMessageDialog(null, "Username or Password is wrong"); break;
			}
			
		}
		
	}
	
	
	
	
	

}
