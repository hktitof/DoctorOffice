package application;


import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


import java.util.ResourceBundle;



import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;

import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class Controller_Diagnostics_Page implements Initializable{
	@FXML
    private Label retour_id;

	@FXML
    private Button add_id;

    @FXML
    private Button update_id;

    @FXML
    private Button delete_id;
    @FXML
    private Button clear_id;
    @FXML
    private Button find_button;
    
    /*------------------- chekbox id declaration ------------------*/
    
    @FXML
    private CheckBox checkbox1_oui;

    @FXML
    private CheckBox checkbox1_non;

    @FXML
    private CheckBox checkbox2_oui;

    @FXML
    private CheckBox checkbox2_non;

    @FXML
    private CheckBox checkbox3_oui;

    @FXML
    private CheckBox checkbox3_non;

    @FXML
    private CheckBox checkbox4_oui;

    @FXML
    private CheckBox checkbox4_non;

    @FXML
    private CheckBox checkbox5_oui;

    @FXML
    private CheckBox checkbox5_non;

    @FXML
    private CheckBox checkbox6_oui;

    @FXML
    private CheckBox checkbox6_non;

    @FXML
    private CheckBox checkbox7_oui;

    @FXML
    private CheckBox checkbox7_non;

    @FXML
    private CheckBox checkbox8_oui;

    @FXML
    private CheckBox checkbox8_non;

    @FXML
    private CheckBox checkbox9_oui;

    @FXML
    private CheckBox checkbox9_non;

    @FXML
    private CheckBox checkbox10_oui;

    @FXML
    private CheckBox checkbox10_non;

    @FXML
    private CheckBox checkbox17_oui;

    @FXML
    private CheckBox checkbox17_non;

    @FXML
    private CheckBox checkbox18_oui;

    @FXML
    private CheckBox checkbox18_non;

    @FXML
    private CheckBox checkbox19_oui;

    @FXML
    private CheckBox checkbox19_non;

    @FXML
    private CheckBox checkbox20_oui;

    @FXML
    private CheckBox checkbox20_non;

    @FXML
    private CheckBox checkbox21_oui;

    @FXML
    private CheckBox checkbox21_non;

    @FXML
    private CheckBox checkbox22_oui;

    @FXML
    private CheckBox checkbox22_non;

    @FXML
    private CheckBox checkbox11_oui;

    @FXML
    private CheckBox checkbox11_non;

    @FXML
    private CheckBox checkbox12_oui;

    @FXML
    private CheckBox checkbox12_non;

    @FXML
    private CheckBox checkbox13_oui;

    @FXML
    private CheckBox checkbox13_non;

    @FXML
    private CheckBox checkbox14_oui;

    @FXML
    private CheckBox checkbox14_non;

    @FXML
    private CheckBox checkbox15_oui;

    @FXML
    private CheckBox checkbox15_non;

    @FXML
    private CheckBox checkbox16_oui;

    @FXML
    private CheckBox checkbox16_non;
    @FXML
    private TextField Textfield_cin_new;
    @FXML
    private TextField Textfield_cin_search;
    @FXML
    private AnchorPane interface_id;
    @FXML
    private Label label_status;
    /*------------------- chekbox id declaration ------------------*/
    
    
    
   
    Boolean choix_1= false ;
    Boolean choix_2= false ;
    Boolean choix_3= false ;
    Boolean choix_4= false ;
    Boolean choix_5= false ;
    Boolean choix_6= false ;
    Boolean choix_7= false ;
    Boolean choix_8= false ;
    Boolean choix_9= false ;
    Boolean choix_10= false ;
    Boolean choix_11= false ;
    Boolean choix_12= false ;
    Boolean choix_13= false ;
    Boolean choix_14= false ;
    Boolean choix_15= false ;
    Boolean choix_16= false ;
    Boolean choix_17= false ;
    Boolean choix_18= false ;
    Boolean choix_19= false ;
    Boolean choix_20= false ;
    Boolean choix_21= false ;
    Boolean choix_22= false ;
    
    
    @Override
	public void initialize(URL location, ResourceBundle arg1) {
    	
    	 	
    	
		
		
			
				

	}
    
    @FXML
    void retour_clicked(MouseEvent event) throws Exception {
    	((Stage)(((Label)event.getSource()).getScene().getWindow())).close(); 
		Stage primaryStage = new Stage();
		Parent root = FXMLLoader.load(getClass().getResource("/application/Gestion_patients.fxml"));
		Scene scene = new Scene(root,900,600);
		
		primaryStage.setScene(scene);
		primaryStage.show();

    }
    
    @FXML
    void action_if_empty_set_labal_to_zero(KeyEvent event) {
    	if(Textfield_cin_search.getText().trim().isEmpty()) {
    		label_status.setText(null);
    	}

    }
    
    
    @FXML
    void action_find_button(ActionEvent event) {
    	if(Textfield_cin_search.getText().trim().isEmpty()) {
    		Alert alert = new Alert(AlertType.ERROR);
    		alert.setTitle("Find Diagnostics Information");
			alert.setHeaderText("Exception");
			alert.setContentText("Text find field is empty!!!");
			alert.showAndWait();
			Textfield_cin_search.requestFocus();
    	}else {
    		String fin_cin = null;
    		try {
    			Connection con = DBConnectionforAdminpage.connect();
    			String sql = "SELECT cin FROM diagnostics WHERE cin=?";
    			PreparedStatement stat ;
    			stat = con.prepareStatement(sql);
    			stat.setString(1, Textfield_cin_search.getText().trim());
    			ResultSet rs = stat.executeQuery();
    			
    			if(rs.next()) {
    				fin_cin = rs.getString(1);
    			}
    			
    			
    			} catch (SQLException e) {
    			e.printStackTrace();
    			
    			}
    		if(fin_cin == null) {
    			Unselect_and_disable_fonction();
    			label_status.setText("Not Found!!!");
    			
    		}else {
    			label_status.setText("Found :)");
    			Boolean[] list_choix = new Boolean[22]; 
    				
    				try {
    					Connection con = DBConnectionforAdminpage.connect();
    					String sql = "select choix_1, choix_2, choix_3, choix_4, choix_5, choix_6, choix_7, choix_8, choix_9, choix_10, choix_11, choix_12, choix_13, choix_14, choix_15, choix_16, choix_17, choix_18, choix_19, choix_20, choix_21, choix_22 FROM diagnostics WHERE cin=?";
    					PreparedStatement stat ;
    					stat = con.prepareStatement(sql);
    					stat.setString(1, Textfield_cin_search.getText().trim());
    					ResultSet rs = stat.executeQuery();
    					
    					
    					while(rs.next()) {
    						/*list_choix.add(rs.getBoolean(1),rs.getBoolean(2),rs.getBoolean(3),rs.getBoolean(4),rs.getBoolean(5),rs.getBoolean(6),rs.getBoolean(7),rs.getBoolean(8),rs.getBoolean(9),rs.getBoolean(10),rs.getBoolean(11),rs.getBoolean(12),rs.getBoolean(13),rs.getBoolean(14),rs.getBoolean(15),rs.getBoolean(16),rs.getBoolean(17),rs.getBoolean(18),rs.getBoolean(19),rs.getBoolean(20),rs.getBoolean(21),rs.getBoolean(22));*/
    					
    						list_choix[0] = rs.getBoolean(1);
    						list_choix[1] = rs.getBoolean(2);
    						list_choix[2] = rs.getBoolean(3);
    						list_choix[3] = rs.getBoolean(4);
    						list_choix[4] = rs.getBoolean(5);
    						list_choix[5] = rs.getBoolean(6);
    						list_choix[6] = rs.getBoolean(7);
    						list_choix[7] = rs.getBoolean(8);
    						list_choix[8] = rs.getBoolean(9);
    						list_choix[9] = rs.getBoolean(10);
    						list_choix[10] = rs.getBoolean(11);
    						list_choix[11] = rs.getBoolean(12);
    						list_choix[12] = rs.getBoolean(13);
    						list_choix[13] = rs.getBoolean(14);
    						list_choix[14] = rs.getBoolean(15);
    						list_choix[15] = rs.getBoolean(16);
    						list_choix[16] = rs.getBoolean(17);
    						list_choix[17] = rs.getBoolean(18);
    						list_choix[18] = rs.getBoolean(19);
    						list_choix[19] = rs.getBoolean(20);
    						list_choix[20] = rs.getBoolean(21);
    						list_choix[21] = rs.getBoolean(22);

    					}
    					
    					} catch (SQLException e) {
    					e.printStackTrace();
    					
    					}
    					
    					Unselect_and_disable_fonction();
    					if(list_choix[0] == true) {
    						checkbox1_oui.setSelected(true);
    						checkbox1_non.setDisable(true);
    						}else {
    						checkbox1_non.setSelected(true);
    						checkbox1_oui.setDisable(true);
    						}
    						if(list_choix[1] == true) {
    						checkbox2_oui.setSelected(true);
    						checkbox2_non.setDisable(true);
    						}else {
    						checkbox2_non.setSelected(true);
    						checkbox2_oui.setDisable(true);
    						}
    						if(list_choix[2] == true) {
    						checkbox3_oui.setSelected(true);
    						checkbox3_non.setDisable(true);
    						}else {
    						checkbox3_non.setSelected(true);
    						checkbox3_oui.setDisable(true);
    						}
    						if(list_choix[3] == true) {
    						checkbox4_oui.setSelected(true);
    						checkbox4_non.setDisable(true);
    						}else {
    						checkbox4_non.setSelected(true);
    						checkbox4_oui.setDisable(true);
    						}
    						if(list_choix[4] == true) {
    						checkbox5_oui.setSelected(true);
    						checkbox5_non.setDisable(true);
    						}else {
    						checkbox5_non.setSelected(true);
    						checkbox5_oui.setDisable(true);
    						}
    						if(list_choix[5] == true) {
    						checkbox6_oui.setSelected(true);
    						checkbox6_non.setDisable(true);
    						}else {
    						checkbox6_non.setSelected(true);
    						checkbox6_oui.setDisable(true);
    						}
    						if(list_choix[6] == true) {
    						checkbox7_oui.setSelected(true);
    						checkbox7_non.setDisable(true);
    						}else {
    						checkbox7_non.setSelected(true);
    						checkbox7_oui.setDisable(true);
    						}
    						if(list_choix[7] == true) {
    						checkbox8_oui.setSelected(true);
    						checkbox8_non.setDisable(true);
    						}else {
    						checkbox8_non.setSelected(true);
    						checkbox8_oui.setDisable(true);
    						}
    						if(list_choix[8] == true) {
    						checkbox9_oui.setSelected(true);
    						checkbox9_non.setDisable(true);
    						}else {
    						checkbox9_non.setSelected(true);
    						checkbox9_oui.setDisable(true);
    						}
    						if(list_choix[9] == true) {
    						checkbox10_oui.setSelected(true);
    						checkbox10_non.setDisable(true);
    						}else {
    						checkbox10_non.setSelected(true);
    						checkbox10_oui.setDisable(true);
    						}
    						if(list_choix[10] == true) {
    						checkbox11_oui.setSelected(true);
    						checkbox11_non.setDisable(true);
    						}else {
    						checkbox11_non.setSelected(true);
    						checkbox11_oui.setDisable(true);
    						}
    						if(list_choix[11] == true) {
    						checkbox12_oui.setSelected(true);
    						checkbox12_non.setDisable(true);
    						}else {
    						checkbox12_non.setSelected(true);
    						checkbox12_oui.setDisable(true);
    						}
    						if(list_choix[12] == true) {
    						checkbox13_oui.setSelected(true);
    						checkbox13_non.setDisable(true);
    						}else {
    						checkbox13_non.setSelected(true);
    						checkbox13_oui.setDisable(true);
    						}
    						if(list_choix[13] == true) {
    						checkbox14_oui.setSelected(true);
    						checkbox14_non.setDisable(true);
    						}else {
    						checkbox14_non.setSelected(true);
    						checkbox14_oui.setDisable(true);
    						}
    						if(list_choix[14] == true) {
    						checkbox15_oui.setSelected(true);
    						checkbox15_non.setDisable(true);
    						}else {
    						checkbox15_non.setSelected(true);
    						checkbox15_oui.setDisable(true);
    						}
    						if(list_choix[15] == true) {
    						checkbox16_oui.setSelected(true);
    						checkbox16_non.setDisable(true);
    						}else {
    						checkbox16_non.setSelected(true);
    						checkbox16_oui.setDisable(true);
    						}
    						if(list_choix[16] == true) {
    						checkbox17_oui.setSelected(true);
    						checkbox17_non.setDisable(true);
    						}else {
    						checkbox17_non.setSelected(true);
    						checkbox17_oui.setDisable(true);
    						}
    						if(list_choix[17] == true) {
    						checkbox18_oui.setSelected(true);
    						checkbox18_non.setDisable(true);
    						}else {
    						checkbox18_non.setSelected(true);
    						checkbox18_oui.setDisable(true);
    						}
    						if(list_choix[18] == true) {
    						checkbox19_oui.setSelected(true);
    						checkbox19_non.setDisable(true);
    						}else {
    						checkbox19_non.setSelected(true);
    						checkbox19_oui.setDisable(true);
    						}
    						if(list_choix[19] == true) {
    						checkbox20_oui.setSelected(true);
    						checkbox20_non.setDisable(true);
    						}else {
    						checkbox20_non.setSelected(true);
    						checkbox20_oui.setDisable(true);
    						}
    						if(list_choix[20] == true) {
    						checkbox21_oui.setSelected(true);
    						checkbox21_non.setDisable(true);
    						}else {
    						checkbox21_non.setSelected(true);
    						checkbox21_oui.setDisable(true);
    						}
    						if(list_choix[21] == true) {
    						checkbox22_oui.setSelected(true);
    						checkbox22_non.setDisable(true);
    						}else {
    						checkbox22_non.setSelected(true);
    						checkbox22_oui.setDisable(true);
    						}

    		}
    		
    	}
    	

		
    }
    
    
    
    @FXML
    void action_add_button(ActionEvent event) {	
    		if(checkbox1_oui.isSelected() || checkbox1_non.isSelected()) {
    		choix_1=true;
    		}else{
    		choix_1=false;
    		}
    		if(checkbox2_oui.isSelected() || checkbox2_non.isSelected()) {
    		choix_2=true;
    		}else{
    		choix_2=false;
    		}
    		if(checkbox3_oui.isSelected() || checkbox3_non.isSelected()) {
    		choix_3=true;
    		}else{
    		choix_3=false;
    		}
    		if(checkbox4_oui.isSelected() || checkbox4_non.isSelected()) {
    		choix_4=true;
    		}else{
    		choix_4=false;
    		}
    		if(checkbox5_oui.isSelected() || checkbox5_non.isSelected()) {
    		choix_5=true;
    		}else{
    		choix_5=false;
    		}
    		if(checkbox6_oui.isSelected() || checkbox6_non.isSelected()) {
    		choix_6=true;
    		}else{
    		choix_6=false;
    		}
    		if(checkbox7_oui.isSelected() || checkbox7_non.isSelected()) {
    		choix_7=true;
    		}else{
    		choix_7=false;
    		}
    		if(checkbox8_oui.isSelected() || checkbox8_non.isSelected()) {
    		choix_8=true;
    		}else{
    		choix_8=false;
    		}
    		if(checkbox9_oui.isSelected() || checkbox9_non.isSelected()) {
    		choix_9=true;
    		}else{
    		choix_9=false;
    		}
    		if(checkbox10_oui.isSelected() || checkbox10_non.isSelected()) {
    		choix_10=true;
    		}else{
    		choix_10=false;
    		}
    		if(checkbox11_oui.isSelected() || checkbox11_non.isSelected()) {
    		choix_11=true;
    		}else{
    		choix_11=false;
    		}
    		if(checkbox12_oui.isSelected() || checkbox12_non.isSelected()) {
    		choix_12=true;
    		}else{
    		choix_12=false;
    		}
    		if(checkbox13_oui.isSelected() || checkbox13_non.isSelected()) {
    		choix_13=true;
    		}else{
    		choix_13=false;
    		}
    		if(checkbox14_oui.isSelected() || checkbox14_non.isSelected()) {
    		choix_14=true;
    		}else{
    		choix_14=false;
    		}
    		if(checkbox15_oui.isSelected() || checkbox15_non.isSelected()) {
    		choix_15=true;
    		}else{
    		choix_15=false;
    		}
    		if(checkbox16_oui.isSelected() || checkbox16_non.isSelected()) {
    		choix_16=true;
    		}else{
    		choix_16=false;
    		}
    		if(checkbox17_oui.isSelected() || checkbox17_non.isSelected()) {
    		choix_17=true;
    		}else{
    		choix_17=false;
    		}
    		if(checkbox18_oui.isSelected() || checkbox18_non.isSelected()) {
    		choix_18=true;
    		}else{
    		choix_18=false;
    		}
    		if(checkbox19_oui.isSelected() || checkbox19_non.isSelected()) {
    		choix_19=true;
    		}else{
    		choix_19=false;
    		}
    		if(checkbox20_oui.isSelected() || checkbox20_non.isSelected()) {
    		choix_20=true;
    		}else{
    		choix_20=false;
    		}
    		if(checkbox21_oui.isSelected() || checkbox21_non.isSelected()) {
    		choix_21=true;
    		}else{
    		choix_21=false;
    		}
    		if(checkbox22_oui.isSelected() || checkbox22_non.isSelected()) {
    		choix_22=true;
    		}else{
    		choix_22=false;
    		}
    		String check_new_cin_emty;
    		if(Textfield_cin_new.getText().trim().isEmpty()) {
    			check_new_cin_emty = "no";
    		}else {
    			check_new_cin_emty = "yes";
    		}
    		if((check_new_cin_emty == "yes") && (choix_1==true) && (choix_2==true) && (choix_3==true) && (choix_4==true) && (choix_5==true) && (choix_6==true) && (choix_7==true) && (choix_8==true) && (choix_9==true) && (choix_10==true) && (choix_11==true) && (choix_12==true) && (choix_13=true) && (choix_14==true) && (choix_15==true) && (choix_16==true) && (choix_17==true) && (choix_18==true) && (choix_19==true) && (choix_20==true) && (choix_21==true) && (choix_22==true)) {
    			String new_cin_check_in_fiche_list = null;
        		try {
    			Connection con = DBConnectionforAdminpage.connect();
    			String sql = "SELECT cin FROM fiche WHERE cin=?";
    			PreparedStatement stat ;
    			stat = con.prepareStatement(sql);
    			stat.setString(1, Textfield_cin_new.getText().trim());
    			ResultSet rs = stat.executeQuery();
    			
    			if(rs.next()) {
    				new_cin_check_in_fiche_list = rs.getString(1);
    			}
    			
    			
    			} catch (SQLException e) {
    			e.printStackTrace();
    			
    			}
        		if(new_cin_check_in_fiche_list == null) {
        			
        			Alert alert = new Alert(AlertType.ERROR);
    				alert.setTitle("ADD Diagnostics Information");
    				alert.setHeaderText("Information");
    				alert.setContentText(Textfield_cin_new.getText().trim()+" is not exist in the fiche list");
    				alert.showAndWait();
    				Textfield_cin_new.requestFocus();
        		}else {
        			String new_cin = null;
        			try {
        				Connection con = DBConnectionforAdminpage.connect();
        				String sql = "SELECT cin FROM diagnostics WHERE cin=?";
        				PreparedStatement stat ;
        				stat = con.prepareStatement(sql);
        				stat.setString(1, Textfield_cin_new.getText().trim());
        				ResultSet rs = stat.executeQuery();
        				
        				if(rs.next()) {
        					new_cin = rs.getString(1);
        				}
        				
        				
        				} catch (SQLException e) {
        				e.printStackTrace();
        				
        				}
        			if (new_cin == null) {
        				Set_les_choix_fonction();
        				Model_Diagnostics_Page rdv = new Model_Diagnostics_Page();
        				rdv.setChoix_1(choix_1);
        				rdv.setChoix_2(choix_2);
        				rdv.setChoix_3(choix_3);
        				rdv.setChoix_4(choix_4);
        				rdv.setChoix_5(choix_5);
        				rdv.setChoix_6(choix_6);
        				rdv.setChoix_7(choix_7);
        				rdv.setChoix_8(choix_8);
        				rdv.setChoix_9(choix_9);
        				rdv.setChoix_10(choix_10);
        				rdv.setChoix_11(choix_11);
        				rdv.setChoix_12(choix_12);
        				rdv.setChoix_13(choix_13);
        				rdv.setChoix_14(choix_14);
        				rdv.setChoix_15(choix_15);
        				rdv.setChoix_16(choix_16);
        				rdv.setChoix_17(choix_17);
        				rdv.setChoix_18(choix_18);
        				rdv.setChoix_19(choix_19);
        				rdv.setChoix_20(choix_20);
        				rdv.setChoix_21(choix_21);
        				rdv.setChoix_22(choix_22);
        				String cin = Textfield_cin_new.getText();
        				rdv.setCin(cin);
        					int status = DBConnectionforAdminpage.add_for_Diagnostics_Page(rdv);
        					if (status>0) {
        					Alert alert = new Alert(AlertType.INFORMATION);
        					alert.setTitle("ADD Diagnostics Information");
        					alert.setHeaderText("Information");
        					alert.setContentText("Diagnostics is successfully added for "+cin);
        					alert.showAndWait();
        					Unselect_and_disable_fonction();
        					check_new_cin_emty = "";
        					
        					}else {
        					Alert alert = new Alert(AlertType.ERROR);
        					alert.setTitle("ADD Diagnostics Information");
        					alert.setHeaderText("Information");
        					alert.setContentText("Diagnostics Information has not beeen added!!!! \nPlease Try again and check Connection to DateBase");
        					alert.showAndWait();
        					
        					}
    				}else {
    					Alert alert = new Alert(AlertType.ERROR);
    					alert.setTitle("ADD Diagnostics Information");
    					alert.setHeaderText("Information");
    					alert.setContentText("Error!!!!! this CIN is Already in the list try to add a new one");
    					alert.showAndWait();
    				}
        				
        				
        				
        				
        				

        			
        		}
    			
    			
    			
    			
    			
    			
    			
    			
    		}else
    		{
    			Alert alert = new Alert(AlertType.ERROR);
				alert.setTitle("ADD Diagnostics Information");
				alert.setHeaderText("Information");
				alert.setContentText("Ooops... there is some Fields missing");
				alert.showAndWait();
    		}

    	

    }
    
    
    @FXML
    void action_update_button(ActionEvent event) {
    	if(Textfield_cin_search.getText().trim().isEmpty()) {
    		Alert alert = new Alert(AlertType.ERROR);
    		alert.setTitle("Find Diagnostics Information");
			alert.setHeaderText("Exception");
			alert.setContentText("find field is empty!!!");
			alert.showAndWait();
			Textfield_cin_search.requestFocus();
    	}else {
    		if(checkbox1_oui.isSelected() || checkbox1_non.isSelected()) {
        		choix_1=true;
        		}else{
        		choix_1=false;
        		}
        		if(checkbox2_oui.isSelected() || checkbox2_non.isSelected()) {
        		choix_2=true;
        		}else{
        		choix_2=false;
        		}
        		if(checkbox3_oui.isSelected() || checkbox3_non.isSelected()) {
        		choix_3=true;
        		}else{
        		choix_3=false;
        		}
        		if(checkbox4_oui.isSelected() || checkbox4_non.isSelected()) {
        		choix_4=true;
        		}else{
        		choix_4=false;
        		}
        		if(checkbox5_oui.isSelected() || checkbox5_non.isSelected()) {
        		choix_5=true;
        		}else{
        		choix_5=false;
        		}
        		if(checkbox6_oui.isSelected() || checkbox6_non.isSelected()) {
        		choix_6=true;
        		}else{
        		choix_6=false;
        		}
        		if(checkbox7_oui.isSelected() || checkbox7_non.isSelected()) {
        		choix_7=true;
        		}else{
        		choix_7=false;
        		}
        		if(checkbox8_oui.isSelected() || checkbox8_non.isSelected()) {
        		choix_8=true;
        		}else{
        		choix_8=false;
        		}
        		if(checkbox9_oui.isSelected() || checkbox9_non.isSelected()) {
        		choix_9=true;
        		}else{
        		choix_9=false;
        		}
        		if(checkbox10_oui.isSelected() || checkbox10_non.isSelected()) {
        		choix_10=true;
        		}else{
        		choix_10=false;
        		}
        		if(checkbox11_oui.isSelected() || checkbox11_non.isSelected()) {
        		choix_11=true;
        		}else{
        		choix_11=false;
        		}
        		if(checkbox12_oui.isSelected() || checkbox12_non.isSelected()) {
        		choix_12=true;
        		}else{
        		choix_12=false;
        		}
        		if(checkbox13_oui.isSelected() || checkbox13_non.isSelected()) {
        		choix_13=true;
        		}else{
        		choix_13=false;
        		}
        		if(checkbox14_oui.isSelected() || checkbox14_non.isSelected()) {
        		choix_14=true;
        		}else{
        		choix_14=false;
        		}
        		if(checkbox15_oui.isSelected() || checkbox15_non.isSelected()) {
        		choix_15=true;
        		}else{
        		choix_15=false;
        		}
        		if(checkbox16_oui.isSelected() || checkbox16_non.isSelected()) {
        		choix_16=true;
        		}else{
        		choix_16=false;
        		}
        		if(checkbox17_oui.isSelected() || checkbox17_non.isSelected()) {
        		choix_17=true;
        		}else{
        		choix_17=false;
        		}
        		if(checkbox18_oui.isSelected() || checkbox18_non.isSelected()) {
        		choix_18=true;
        		}else{
        		choix_18=false;
        		}
        		if(checkbox19_oui.isSelected() || checkbox19_non.isSelected()) {
        		choix_19=true;
        		}else{
        		choix_19=false;
        		}
        		if(checkbox20_oui.isSelected() || checkbox20_non.isSelected()) {
        		choix_20=true;
        		}else{
        		choix_20=false;
        		}
        		if(checkbox21_oui.isSelected() || checkbox21_non.isSelected()) {
        		choix_21=true;
        		}else{
        		choix_21=false;
        		}
        		if(checkbox22_oui.isSelected() || checkbox22_non.isSelected()) {
        		choix_22=true;
        		}else{
        		choix_22=false;
        		}
        		
        		if((choix_1==true) && (choix_2==true) && (choix_3==true) && (choix_4==true) && (choix_5==true) && (choix_6==true) && (choix_7==true) && (choix_8==true) && (choix_9==true) && (choix_10==true) && (choix_11==true) && (choix_12==true) && (choix_13=true) && (choix_14==true) && (choix_15==true) && (choix_16==true) && (choix_17==true) && (choix_18==true) && (choix_19==true) && (choix_20==true) && (choix_21==true) && (choix_22==true)) {
        			String update_cin_check_in_diagnostics_list = null;
            		try {
        			Connection con = DBConnectionforAdminpage.connect();
        			String sql = "SELECT cin FROM diagnostics WHERE cin=?";
        			PreparedStatement stat ;
        			stat = con.prepareStatement(sql);
        			stat.setString(1, Textfield_cin_search.getText().trim());
        			ResultSet rs = stat.executeQuery();
        			
        			if(rs.next()) {
        				update_cin_check_in_diagnostics_list = rs.getString(1);
        			}
        			
        			
        			} catch (SQLException e) {
        			e.printStackTrace();
        			
        			}
            		if(update_cin_check_in_diagnostics_list == null) {
            			
            			Alert alert = new Alert(AlertType.ERROR);
        				alert.setTitle("Update Diagnostics Information");
        				alert.setHeaderText("ERROR");
        				alert.setContentText(Textfield_cin_search.getText().trim()+" is not exist in the Diagnostics list");
        				alert.showAndWait();
        				Textfield_cin_search.requestFocus();
            		}else {
            			Set_les_choix_fonction();
        				Model_Diagnostics_Page rdv = new Model_Diagnostics_Page();
        				rdv.setChoix_1(choix_1);
        				rdv.setChoix_2(choix_2);
        				rdv.setChoix_3(choix_3);
        				rdv.setChoix_4(choix_4);
        				rdv.setChoix_5(choix_5);
        				rdv.setChoix_6(choix_6);
        				rdv.setChoix_7(choix_7);
        				rdv.setChoix_8(choix_8);
        				rdv.setChoix_9(choix_9);
        				rdv.setChoix_10(choix_10);
        				rdv.setChoix_11(choix_11);
        				rdv.setChoix_12(choix_12);
        				rdv.setChoix_13(choix_13);
        				rdv.setChoix_14(choix_14);
        				rdv.setChoix_15(choix_15);
        				rdv.setChoix_16(choix_16);
        				rdv.setChoix_17(choix_17);
        				rdv.setChoix_18(choix_18);
        				rdv.setChoix_19(choix_19);
        				rdv.setChoix_20(choix_20);
        				rdv.setChoix_21(choix_21);
        				rdv.setChoix_22(choix_22);
        				String cin = Textfield_cin_search.getText();
        				rdv.setCin(cin);
        				
        				int status = DBConnectionforAdminpage.update_for_Diagnostics_Page(rdv);
    					if (status>0) {
    					Alert alert = new Alert(AlertType.INFORMATION);
    					alert.setTitle("ADD Diagnostics Information");
    					alert.setHeaderText("Information");
    					alert.setContentText("Diagnostics is successfully Updated for "+cin);
    					alert.showAndWait();
    					Unselect_and_disable_fonction();
    					Textfield_cin_search.setText(null);
    					
    					}else {
    					Alert alert = new Alert(AlertType.ERROR);
    					alert.setTitle("ADD Diagnostics Information");
    					alert.setHeaderText("Information");
    					alert.setContentText("Diagnostics Information has not beeen Updated!!!! \nPlease Try again and check Connection to DateBase");
    					alert.showAndWait();
    					
    					}
            			
            			
            		}
        		}else {
        			Alert alert = new Alert(AlertType.ERROR);
    				alert.setTitle("ADD Diagnostics Information");
    				alert.setHeaderText("Information");
    				alert.setContentText("Ooops... there is some Fields missing");
    				alert.showAndWait();
        			
        		}
    		
    	}

    }
    
    @FXML
    void action_Delete_fbutton(ActionEvent event) {
    	if(Textfield_cin_search.getText().trim().isEmpty()) {
    		Alert alert = new Alert(AlertType.ERROR);
    		alert.setTitle("Find Diagnostics Information");
			alert.setHeaderText("Exception");
			alert.setContentText("find field is empty!!!");
			alert.showAndWait();
			Textfield_cin_search.requestFocus();
    	}else {
    		String delete_cin_check_in_diagnostics_list = null;
    		try {
			Connection con = DBConnectionforAdminpage.connect();
			String sql = "SELECT cin FROM diagnostics WHERE cin=?";
			PreparedStatement stat ;
			stat = con.prepareStatement(sql);
			stat.setString(1, Textfield_cin_search.getText().trim());
			ResultSet rs = stat.executeQuery();
			
			if(rs.next()) {
				delete_cin_check_in_diagnostics_list = rs.getString(1);
			}
			
			
			} catch (SQLException e) {
			e.printStackTrace();
			
			}
    		if(delete_cin_check_in_diagnostics_list == null) {
    			
    			Alert alert = new Alert(AlertType.ERROR);
				alert.setTitle("Delete Diagnostics Information");
				alert.setHeaderText("ERROR");
				alert.setContentText(Textfield_cin_search.getText().trim()+" is not exist in the Diagnostics list");
				alert.showAndWait();
				Textfield_cin_search.requestFocus();
    		}else {
    			Model_Diagnostics_Page rdv = new Model_Diagnostics_Page();
    			String cin = Textfield_cin_search.getText();
				rdv.setCin(cin);
				int status = DBConnectionforAdminpage.delete_for_Diagnostics_Page(rdv);
				if (status>0) {
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("DELETE Diagnostics Information");
				alert.setHeaderText("Information");
				alert.setContentText("Diagnostics is successfully Deleted for "+cin);
				alert.showAndWait();
				Unselect_and_disable_fonction();
				Textfield_cin_search.setText(null);
				
				}else {
				Alert alert = new Alert(AlertType.ERROR);
				alert.setTitle("ADD Diagnostics Information");
				alert.setHeaderText("Information");
				alert.setContentText("Diagnostics Information has not beeen Deleted!!!! \nPlease Try again and check Connection to DateBase");
				alert.showAndWait();
				}
				
    			
    		}
    		
    	}
    	

    }
    
    
    
    
    
    
    public void Unselect_and_disable_fonction() {
    	checkbox1_non.setSelected(false);
		checkbox1_oui.setSelected(false);
		checkbox2_non.setSelected(false);
		checkbox2_oui.setSelected(false);
		checkbox3_non.setSelected(false);
		checkbox3_oui.setSelected(false);
		checkbox4_non.setSelected(false);
		checkbox4_oui.setSelected(false);
		checkbox5_non.setSelected(false);
		checkbox5_oui.setSelected(false);
		checkbox6_non.setSelected(false);
		checkbox6_oui.setSelected(false);
		checkbox7_non.setSelected(false);
		checkbox7_oui.setSelected(false);
		checkbox8_non.setSelected(false);
		checkbox8_oui.setSelected(false);
		checkbox9_non.setSelected(false);
		checkbox9_oui.setSelected(false);
		checkbox10_non.setSelected(false);
		checkbox10_oui.setSelected(false);
		checkbox11_non.setSelected(false);
		checkbox11_oui.setSelected(false);
		checkbox12_non.setSelected(false);
		checkbox12_oui.setSelected(false);
		checkbox13_non.setSelected(false);
		checkbox13_oui.setSelected(false);
		checkbox14_non.setSelected(false);
		checkbox14_oui.setSelected(false);
		checkbox15_non.setSelected(false);
		checkbox15_oui.setSelected(false);
		checkbox16_non.setSelected(false);
		checkbox16_oui.setSelected(false);
		checkbox17_non.setSelected(false);
		checkbox17_oui.setSelected(false);
		checkbox18_non.setSelected(false);
		checkbox18_oui.setSelected(false);
		checkbox19_non.setSelected(false);
		checkbox19_oui.setSelected(false);
		checkbox20_non.setSelected(false);
		checkbox20_oui.setSelected(false);
		checkbox21_non.setSelected(false);
		checkbox21_oui.setSelected(false);
		checkbox22_non.setSelected(false);
		checkbox22_oui.setSelected(false);
		checkbox1_non.setDisable(false);
		checkbox1_oui.setDisable(false);
		checkbox2_non.setDisable(false);
		checkbox2_oui.setDisable(false);
		checkbox3_non.setDisable(false);
		checkbox3_oui.setDisable(false);
		checkbox4_non.setDisable(false);
		checkbox4_oui.setDisable(false);
		checkbox5_non.setDisable(false);
		checkbox5_oui.setDisable(false);
		checkbox6_non.setDisable(false);
		checkbox6_oui.setDisable(false);
		checkbox7_non.setDisable(false);
		checkbox7_oui.setDisable(false);
		checkbox8_non.setDisable(false);
		checkbox8_oui.setDisable(false);
		checkbox9_non.setDisable(false);
		checkbox9_oui.setDisable(false);
		checkbox10_non.setDisable(false);
		checkbox10_oui.setDisable(false);
		checkbox11_non.setDisable(false);
		checkbox11_oui.setDisable(false);
		checkbox12_non.setDisable(false);
		checkbox12_oui.setDisable(false);
		checkbox13_non.setDisable(false);
		checkbox13_oui.setDisable(false);
		checkbox14_non.setDisable(false);
		checkbox14_oui.setDisable(false);
		checkbox15_non.setDisable(false);
		checkbox15_oui.setDisable(false);
		checkbox16_non.setDisable(false);
		checkbox16_oui.setDisable(false);
		checkbox17_non.setDisable(false);
		checkbox17_oui.setDisable(false);
		checkbox18_non.setDisable(false);
		checkbox18_oui.setDisable(false);
		checkbox19_non.setDisable(false);
		checkbox19_oui.setDisable(false);
		checkbox20_non.setDisable(false);
		checkbox20_oui.setDisable(false);
		checkbox21_non.setDisable(false);
		checkbox21_oui.setDisable(false);
		checkbox22_non.setDisable(false);
		checkbox22_oui.setDisable(false);
		Textfield_cin_new.setText(null);
    }
    
    public void Set_les_choix_fonction() {
    	if(checkbox1_oui.isSelected()) {	
			choix_1 = true ;
			}else{
			choix_1 = false ;
			}
			if(checkbox2_oui.isSelected()) {
			choix_2 = true ;
			}else{
			choix_2 = false ;
			}
			if(checkbox3_oui.isSelected()) {
			choix_3 = true ;
			}else{
			choix_3 = false ;
			}
			if(checkbox4_oui.isSelected()) {
			choix_4 = true ;
			}else{
			choix_4 = false ;
			}
			if(checkbox5_oui.isSelected()) {
			choix_5 = true ;
			}else{
			choix_5 = false ;
			}
			if(checkbox6_oui.isSelected()) {
			choix_6 = true ;
			}else{
			choix_6 = false ;
			}
			if(checkbox7_oui.isSelected()) {
			choix_7 = true ;
			}else{
			choix_7 = false ;
			}
			if(checkbox8_oui.isSelected()) {
			choix_8 = true ;
			}else{
			choix_8 = false ;
			}
			if(checkbox9_oui.isSelected()) {
			choix_9 = true ;
			}else{
			choix_9 = false ;
			}
			if(checkbox10_oui.isSelected()) {
			choix_10 = true ;
			}else{
			choix_10 = false ;
			}
			if(checkbox11_oui.isSelected()) {
			choix_11 = true ;
			}else{
			choix_11 = false ;
			}
			if(checkbox12_oui.isSelected()) {
			choix_12 = true ;
			}else{
			choix_12 = false ;
			}
			if(checkbox13_oui.isSelected()) {
			choix_13 = true ;
			}else{
			choix_13 = false ;
			}
			if(checkbox14_oui.isSelected()) {
			choix_14 = true ;
			}else{
			choix_14 = false ;
			}
			if(checkbox15_oui.isSelected()) {
			choix_15 = true ;
			}else{
			choix_15 = false ;
			}
			if(checkbox16_oui.isSelected()) {
			choix_16 = true ;
			}else{
			choix_16 = false ;
			}
			if(checkbox17_oui.isSelected()) {
			choix_17 = true ;
			}else{
			choix_17 = false ;
			}
			if(checkbox18_oui.isSelected()) {
			choix_18 = true ;
			}else{
			choix_18 = false ;
			}
			if(checkbox19_oui.isSelected()) {
			choix_19 = true ;
			}else{
			choix_19 = false ;
			}
			if(checkbox20_oui.isSelected()) {
			choix_20 = true ;
			}else{
			choix_20 = false ;
			}
			if(checkbox21_oui.isSelected()) {
			choix_21 = true ;
			}else{
			choix_21 = false ;
			}
			if(checkbox22_oui.isSelected()) {
			choix_22 = true ;
			}else{
			choix_22 = false ;
			}
    }
    

    
    
    /*==============================*/
    
    @FXML
    void action_checkbox10_non(ActionEvent event) {
    	if(checkbox10_non.isSelected()) {
    		checkbox10_oui.setDisable(true);
    	}else {
    		checkbox10_oui.setDisable(false);
    	}

    }

    @FXML
    void action_checkbox10_oui(ActionEvent event) {
    	if(checkbox10_oui.isSelected()) {
    		checkbox10_non.setDisable(true);
    	}else {
    		checkbox10_non.setDisable(false);
    	}

    }

    @FXML
    void action_checkbox11_non(ActionEvent event) {
    	if(checkbox11_non.isSelected()) {
    		checkbox11_oui.setDisable(true);
    	}else {
    		checkbox11_oui.setDisable(false);
    	}
    	

    }

    @FXML
    void action_checkbox11_oui(ActionEvent event) {
    	if(checkbox11_oui.isSelected()) {
    		checkbox11_non.setDisable(true);
    	}else {
    		checkbox11_non.setDisable(false);
    	}

    }

    @FXML
    void action_checkbox12_non(ActionEvent event) {
    	if(checkbox12_non.isSelected()) {
    		checkbox12_oui.setDisable(true);
    	}else {
    		checkbox12_oui.setDisable(false);
    	}

    }

    @FXML
    void action_checkbox12_oui(ActionEvent event) {
    	if(checkbox12_oui.isSelected()) {
    		checkbox12_non.setDisable(true);
    	}else {
    		checkbox12_non.setDisable(false);
    	}

    }

    @FXML
    void action_checkbox13_non(ActionEvent event) {
    	if(checkbox13_non.isSelected()) {
    		checkbox13_oui.setDisable(true);
    	}else {
    		checkbox13_oui.setDisable(false);
    	}

    }

    @FXML
    void action_checkbox13_oui(ActionEvent event) {
    	if(checkbox13_oui.isSelected()) {
    		checkbox13_non.setDisable(true);
    	}else {
    		checkbox13_non.setDisable(false);
    	}

    }

    @FXML
    void action_checkbox14_non(ActionEvent event) {
    	if(checkbox14_non.isSelected()) {
    		checkbox14_oui.setDisable(true);
    	}else {
    		checkbox14_oui.setDisable(false);
    	}

    }

    @FXML
    void action_checkbox14_oui(ActionEvent event) {
    	if(checkbox14_oui.isSelected()) {
    		checkbox14_non.setDisable(true);
    	}else {
    		checkbox14_non.setDisable(false);
    	}

    }

    @FXML
    void action_checkbox15_non(ActionEvent event) {
    	if(checkbox15_non.isSelected()) {
    		checkbox15_oui.setDisable(true);
    	}else {
    		checkbox15_oui.setDisable(false);
    	}

    }

    @FXML
    void action_checkbox15_oui(ActionEvent event) {
    	if(checkbox15_oui.isSelected()) {
    		checkbox15_non.setDisable(true);
    	}else {
    		checkbox15_non.setDisable(false);
    	}

    }

    @FXML
    void action_checkbox16_non(ActionEvent event) {
    	if(checkbox16_non.isSelected()) {
    		checkbox16_oui.setDisable(true);
    	}else {
    		checkbox16_oui.setDisable(false);
    	}

    }

    @FXML
    void action_checkbox16_oui(ActionEvent event) {
    	if(checkbox16_oui.isSelected()) {
    		checkbox16_non.setDisable(true);
    	}else {
    		checkbox16_non.setDisable(false);
    	}
    }

    @FXML
    void action_checkbox17_non(ActionEvent event) {
    	if(checkbox17_non.isSelected()) {
    		checkbox17_oui.setDisable(true);
    	}else {
    		checkbox17_oui.setDisable(false);
    	}

    }

    @FXML
    void action_checkbox17_oui(ActionEvent event) {
    	if(checkbox17_oui.isSelected()) {
    		checkbox17_non.setDisable(true);
    	}else {
    		checkbox17_non.setDisable(false);
    	}

    }

    @FXML
    void action_checkbox18_non(ActionEvent event) {
    	if(checkbox18_non.isSelected()) {
    		checkbox18_oui.setDisable(true);
    	}else {
    		checkbox18_oui.setDisable(false);
    	}

    }

    @FXML
    void action_checkbox18_oui(ActionEvent event) {
    	if(checkbox18_oui.isSelected()) {
    		checkbox18_non.setDisable(true);
    	}else {
    		checkbox18_non.setDisable(false);
    	}

    }

    @FXML
    void action_checkbox19_non(ActionEvent event) {
    	if(checkbox19_non.isSelected()) {
    		checkbox19_oui.setDisable(true);
    	}else {
    		checkbox19_oui.setDisable(false);
    	}

    }

    @FXML
    void action_checkbox19_oui(ActionEvent event) {
    	if(checkbox19_oui.isSelected()) {
    		checkbox19_non.setDisable(true);
    	}else {
    		checkbox19_non.setDisable(false);
    	}

    }

    @FXML
    void action_checkbox1_non(ActionEvent event) {
    	if(checkbox1_non.isSelected()) {
    		checkbox1_oui.setDisable(true);
    	}else {
    		checkbox1_oui.setDisable(false);
    	}

    }

    @FXML
    void action_checkbox1_oui(ActionEvent event) {
    	if(checkbox1_oui.isSelected()) {
    		checkbox1_non.setDisable(true);
    	}else {
    		checkbox1_non.setDisable(false);
    	}

    }

    @FXML
    void action_checkbox20_non(ActionEvent event) {
    	if(checkbox20_non.isSelected()) {
    		checkbox20_oui.setDisable(true);
    	}else {
    		checkbox20_oui.setDisable(false);
    	}

    }

    @FXML
    void action_checkbox20_oui(ActionEvent event) {
    	if(checkbox20_oui.isSelected()) {
    		checkbox20_non.setDisable(true);
    	}else {
    		checkbox20_non.setDisable(false);
    	}

    }

    @FXML
    void action_checkbox21_non(ActionEvent event) {
    	if(checkbox21_non.isSelected()) {
    		checkbox21_oui.setDisable(true);
    	}else {
    		checkbox21_oui.setDisable(false);
    	}
    }

    @FXML
    void action_checkbox21_oui(ActionEvent event) {
    	if(checkbox21_oui.isSelected()) {
    		checkbox21_non.setDisable(true);
    	}else {
    		checkbox21_non.setDisable(false);
    	}

    }

    @FXML
    void action_checkbox22_non(ActionEvent event) {
    	
    	if(checkbox22_non.isSelected()) {
    		checkbox22_oui.setDisable(true);
    	}else {
    		checkbox22_oui.setDisable(false);
    	}
    	
    }

    @FXML
    void action_checkbox22_oui(ActionEvent event) {
    	if(checkbox22_oui.isSelected()) {
    		checkbox22_non.setDisable(true);
    	}else {
    		checkbox22_non.setDisable(false);
    	}

    }

    @FXML
    void action_checkbox2_non(ActionEvent event) {
    	if(checkbox2_non.isSelected()) {
    		checkbox2_oui.setDisable(true);
    	}else {
    		checkbox2_oui.setDisable(false);
    	}
    }

    @FXML
    void action_checkbox2_oui(ActionEvent event) {
    	if(checkbox2_oui.isSelected()) {
    		checkbox2_non.setDisable(true);
    	}else {
    		checkbox2_non.setDisable(false);
    	}
    }

    @FXML
    void action_checkbox3_non(ActionEvent event) {
    	if(checkbox3_non.isSelected()) {
    		checkbox3_oui.setDisable(true);
    	}else {
    		checkbox3_oui.setDisable(false);
    	}
    }

    @FXML
    void action_checkbox3_oui(ActionEvent event) {
    	if(checkbox3_oui.isSelected()) {
    		checkbox3_non.setDisable(true);
    	}else {
    		checkbox3_non.setDisable(false);
    	}
    }

    @FXML
    void action_checkbox4_non(ActionEvent event) {
    	if(checkbox4_non.isSelected()) {
    		checkbox4_oui.setDisable(true);
    	}else {
    		checkbox4_oui.setDisable(false);
    	}
    }

    @FXML
    void action_checkbox4_oui(ActionEvent event) {
    	if(checkbox4_oui.isSelected()) {
    		checkbox4_non.setDisable(true);
    	}else {
    		checkbox4_non.setDisable(false);
    	}
    }

    @FXML
    void action_checkbox5_non(ActionEvent event) {
    	if(checkbox5_non.isSelected()) {
    		checkbox5_oui.setDisable(true);
    	}else {
    		checkbox5_oui.setDisable(false);
    	}
    }

    @FXML
    void action_checkbox5_oui(ActionEvent event) {
    	if(checkbox5_oui.isSelected()) {
    		checkbox5_non.setDisable(true);
    	}else {
    		checkbox5_non.setDisable(false);
    	}
    }

    @FXML
    void action_checkbox6_non(ActionEvent event) {
    	if(checkbox6_non.isSelected()) {
    		checkbox6_oui.setDisable(true);
    	}else {
    		checkbox6_oui.setDisable(false);
    	}
    }

    @FXML
    void action_checkbox6_oui(ActionEvent event) {
    	if(checkbox6_oui.isSelected()) {
    		checkbox6_non.setDisable(true);
    	}else {
    		checkbox6_non.setDisable(false);
    	}
    }

    @FXML
    void action_checkbox7_non(ActionEvent event) {
    	if(checkbox7_non.isSelected()) {
    		checkbox7_oui.setDisable(true);
    	}else {
    		checkbox7_oui.setDisable(false);
    	}
    }

    @FXML
    void action_checkbox7_oui(ActionEvent event) {
    	if(checkbox7_oui.isSelected()) {
    		checkbox7_non.setDisable(true);
    	}else {
    		checkbox7_non.setDisable(false);
    	}
    }

    @FXML
    void action_checkbox8_non(ActionEvent event) {
    	if(checkbox8_non.isSelected()) {
    		checkbox8_oui.setDisable(true);
    	}else {
    		checkbox8_oui.setDisable(false);
    	}
    }

    @FXML
    void action_checkbox8_oui(ActionEvent event) {
    	if(checkbox8_oui.isSelected()) {
    		checkbox8_non.setDisable(true);
    	}else {
    		checkbox8_non.setDisable(false);
    	}
    }

    @FXML
    void action_checkbox9_non(ActionEvent event) {
    	if(checkbox9_non.isSelected()) {
    		checkbox9_oui.setDisable(true);
    	}else {
    		checkbox9_oui.setDisable(false);
    	}
    }

    @FXML
    void action_checkbox9_oui(ActionEvent event) {
    	if(checkbox9_oui.isSelected()) {
    		checkbox9_non.setDisable(true);
    	}else {
    		checkbox9_non.setDisable(false);
    	}

    }
    
    /*==============================*/
    
    @FXML
    void action_clear_all(ActionEvent event) {
    	Unselect_and_disable_fonction();
    	Textfield_cin_new.setText("");
    	Textfield_cin_search.setText("");

    }
    
    
    
    

    

}
