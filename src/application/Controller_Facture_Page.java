package application;
import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ResourceBundle;
import java.sql.Connection;



import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.fxml.Initializable;

public class Controller_Facture_Page implements Initializable{
	@FXML
    private Label retour_label;

    @FXML
    private Button facture_add_button;

    @FXML
    private Button facture_update_button;

    @FXML
    private Button facture_delete_button;
    @FXML
    private Label label_prix_total;
    @FXML
    private TextField textfield_num_patient;

    @FXML
    private TextField textfield_contect_client;

    @FXML
    private TextField textfield_telephone;

    @FXML
    private TextField textfield_total_ht;
    @FXML
    private DatePicker textfield_date;
    @FXML
    private TextField textfield_search;
    
    @FXML
    private TableView<Model_Facture_Page> table;

    @FXML
    private TableColumn<Model_Facture_Page, Integer> col_id;

    @FXML
    private TableColumn<Model_Facture_Page, String> col_num_client;

    @FXML
    private TableColumn<Model_Facture_Page, String> col_contact_client;

    @FXML
    private TableColumn<Model_Facture_Page, Integer> col_telephone;

    @FXML
    private TableColumn<Model_Facture_Page, Integer> col_prix_total;

    @FXML
    private TableColumn<Model_Facture_Page, String> col_date;
    @FXML
    private Button clear_id;
    
    ObservableList<Model_Facture_Page> data = FXCollections.observableArrayList();
    public void initialize(URL location, ResourceBundle arg1) {
    	try {
			Connection con = DBConnectionforAdminpage.connect();
			ResultSet rs = con.createStatement().executeQuery("select * from facure");
			while(rs.next()) {
				data.add(new Model_Facture_Page(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getInt(5),rs.getInt(6)));
			}
		} catch (SQLException e) {
			
			e.printStackTrace();
		}

		col_id.setCellValueFactory(new PropertyValueFactory<>("id"));
		col_num_client.setCellValueFactory(new PropertyValueFactory<>("n_client"));
		col_contact_client.setCellValueFactory(new PropertyValueFactory<>("contact_client"));
		col_telephone.setCellValueFactory(new PropertyValueFactory<>("telephone"));
		col_prix_total.setCellValueFactory(new PropertyValueFactory<>("prix_total"));
		col_date.setCellValueFactory(new PropertyValueFactory<>("date"));
		table.setItems(data);
		filetring();
    	
    		
    	
    	
    }
    
    
    public void filetring() {
    	FilteredList<Model_Facture_Page> filteredData = new FilteredList<>(data, p -> true);

		// 2. Set the filter Predicate whenever the filter changes.
		textfield_search.textProperty().addListener((observable, oldValue, newValue) -> {
			filteredData.setPredicate(Model_Facture_Page -> {
        // If filter text is empty, display all persons.
        if (newValue == null || newValue.isEmpty()) {
            return true;
        }

        // Compare first name and last name field in your object with filter.
        String lowerCaseFilter = newValue.toLowerCase();

        if (String.valueOf(Model_Facture_Page.getTelephone()).toLowerCase().contains(lowerCaseFilter)) {
            return true;
            // Filter matches first name.

        } else if (String.valueOf(Model_Facture_Page.getContact_client()).toLowerCase().contains(lowerCaseFilter)) {
            return true; // Filter matches last name.
        }  else if (String.valueOf(Model_Facture_Page.getN_client()).toLowerCase().contains(lowerCaseFilter)) {
            return true; // Filter matches last name.
        } 

        return false; // Does not match.
			});
		});

		// 3. Wrap the FilteredList in a SortedList. 
		SortedList<Model_Facture_Page> sortedData = new SortedList<>(filteredData);

		// 4. Bind the SortedList comparator to the TableView comparator.
		sortedData.comparatorProperty().bind(table.comparatorProperty());
		// 5. Add sorted (and filtered) data to the table.
		table.setItems(sortedData);
    }
    
    
    public void onEdit() {
        // check the table's selected item and get selected item
        if (table.getSelectionModel().getSelectedItem() != null) {
            Model_Facture_Page selectedPerson = table.getSelectionModel().getSelectedItem();
            textfield_num_patient.setText(selectedPerson.getN_client());
            textfield_contect_client.setText(selectedPerson.getContact_client());
            String date_pick = selectedPerson.getDate();
            String[] dateList = date_pick.split("-");
            String year_string = dateList [0];
            String month_string = dateList [1];
            String dayOfMonth_string = dateList [2];
            int year = Integer.parseInt(year_string);
            int month = Integer.parseInt(month_string);
            int dayOfMonth = Integer.parseInt(dayOfMonth_string);
            		
            textfield_date.setValue(LocalDate.of(year, month, dayOfMonth));
            int telephone_pick = selectedPerson.getTelephone();
            String telephone_string = String.valueOf(telephone_pick);
            textfield_telephone.setText(telephone_string);
            int prix_total_pick = selectedPerson.getPrix_total();
            int prix_total = (prix_total_pick * 100)/120;
            String prix_total_string = String.valueOf(prix_total);
            
            textfield_total_ht.setText(prix_total_string);
            String var_input_String = textfield_total_ht.getText();
        	int var_input_integer = Integer.parseInt(var_input_String);
        	float var_input_float = var_input_integer + ((var_input_integer*20)/100);
        	var_input_String = String.valueOf(var_input_float);
        	var_input_String = var_input_String + " DH"; 
        	label_prix_total.setText(var_input_String);
   
            
        }
    }
    
    
    
    
    
    
    
    @FXML
    void action_add_button(ActionEvent event) throws IOException{
    	LocalDate date_pick = textfield_date.getValue();
		String num_client = textfield_num_patient.getText();
		String contact_client = textfield_contect_client.getText();
		String date = date_pick.toString();
		String telephone_pick = textfield_telephone.getText();
		int telephone = Integer.parseInt(telephone_pick);
		String total_ht_pick = textfield_total_ht.getText();
		int total_ht = Integer.parseInt(total_ht_pick);
		int prix_total = total_ht + (total_ht*20)/100;
		Model_Facture_Page rdv = new Model_Facture_Page();
		rdv.setN_client(num_client);
		rdv.setContact_client(contact_client);
		rdv.setTelephone(telephone);
		rdv.setDate(date);
		rdv.setPrix_total(prix_total);
		int status = DBConnectionforAdminpage.add_for_facture(rdv);
		if (status>0) {
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("ADD Facture");
			alert.setHeaderText("Information");
			alert.setContentText("Facture successfully added");
			alert.showAndWait();
			
		}else {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("ADD Facturee");
			alert.setHeaderText("Information");
			alert.setContentText("Facture has not beeen added!!!! \nPlease Try again ");
			alert.showAndWait();
			
		}
		
		((Stage)(((Button)event.getSource()).getScene().getWindow())).close(); 
		Stage primaryStage = new Stage();
		Parent root = FXMLLoader.load(getClass().getResource("/application/Facture_Page.fxml"));
		Scene scene = new Scene(root,900,600);
		
		primaryStage.setScene(scene);
		primaryStage.show();

    }
    
    
    
    @FXML
    void action_update_button(ActionEvent event) throws IOException{

		if (table.getSelectionModel().getSelectedItem() != null) {
            Model_Facture_Page selectedPerson = table.getSelectionModel().getSelectedItem();
             int selected_id = selectedPerson.getId();
             LocalDate date_pick = textfield_date.getValue();
     		String num_client = textfield_num_patient.getText();
     		String contact_client = textfield_contect_client.getText();
     		String date = date_pick.toString();
     		String telephone_pick = textfield_telephone.getText();
     		int telephone = Integer.parseInt(telephone_pick);
     		String total_ht_pick = textfield_total_ht.getText();
     		int total_ht = Integer.parseInt(total_ht_pick);
     		int prix_total = total_ht + (total_ht*20)/100;
     		Model_Facture_Page rdv = new Model_Facture_Page();
     		rdv.setN_client(num_client);
     		rdv.setContact_client(contact_client);
     		rdv.setTelephone(telephone);
     		rdv.setDate(date);
     		rdv.setPrix_total(prix_total);
     		rdv.setId(selected_id);
     		DBConnectionforAdminpage.update_for_Facture_Page(rdv);
     		((Stage)(((Button)event.getSource()).getScene().getWindow())).close(); 
    		Stage primaryStage = new Stage();
    		Parent root = FXMLLoader.load(getClass().getResource("/application/Facture_Page.fxml"));
    		Scene scene = new Scene(root,900,600);
    		
    		primaryStage.setScene(scene);
    		primaryStage.show();
   
    		
    		
    		
             
        }else {
        	Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Warning");
			alert.setHeaderText("Information");
			alert.setContentText("Please select a Facture in the right table !!!!");
			alert.showAndWait();
        }

    }
    
    
    
    @FXML
    void action_delete_button(ActionEvent event) throws IOException{
    	if (table.getSelectionModel().getSelectedItem() != null) {
            Model_Facture_Page selectedPerson = table.getSelectionModel().getSelectedItem();
             int selected_id = selectedPerson.getId();
             Model_Facture_Page rdv = new  Model_Facture_Page();
     		
     		rdv.setId(selected_id);
     		DBConnectionforAdminpage.delete_for_Facture_Page(rdv);
     		((Stage)(((Button)event.getSource()).getScene().getWindow())).close(); 
    		Stage primaryStage = new Stage();
    		Parent root = FXMLLoader.load(getClass().getResource("/application/Facture_Page.fxml"));
    		Scene scene = new Scene(root,900,600);
    		
    		primaryStage.setScene(scene);
    		primaryStage.show();
     		
    		
             
        }else {
        	Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Warning");
			alert.setHeaderText("Information");
			alert.setContentText("Please select a Facture in the right table !!!!");
			alert.showAndWait();
        }

    }
    
    
    
    
    @FXML
    void set_label_prix_total_method(KeyEvent event) {
    	
    	try {
    		if(textfield_total_ht.getText() != null) {
        		String var_input_String = textfield_total_ht.getText();
            	int var_input_integer = Integer.parseInt(var_input_String);
            	float var_input_float = var_input_integer + ((var_input_integer*20)/100);
            	var_input_String = String.valueOf(var_input_float);
            	var_input_String = var_input_String + " DH"; 
            	label_prix_total.setText(var_input_String);
            	if(textfield_total_ht.getText() == "") {
            		label_prix_total.setText("");
        		}
        		
        	}
			
		} catch (Exception e) {
			
			label_prix_total.setText(null);
		}
    	
    	
    	

    }


    @FXML
    void retour_action(MouseEvent event) throws IOException {
    	((Stage)(((Label)event.getSource()).getScene().getWindow())).close();
		Stage primaryStage = new Stage();
		Parent root = FXMLLoader.load(getClass().getResource("/application/Secretarypage.fxml"));;
		Scene scene = new Scene(root,900,600);
		
		primaryStage.setScene(scene);
		primaryStage.show();

    }
    
    @FXML
    void action_clear_button(ActionEvent event) {
	    textfield_num_patient.setText(null);;
	    textfield_contect_client.setText(null);;
	    textfield_telephone.setText(null);;
	    textfield_total_ht.setText(null);;
	    textfield_date.setValue(null);
	    textfield_search.setText(null);
	    label_prix_total.setText(null);

    }

}
