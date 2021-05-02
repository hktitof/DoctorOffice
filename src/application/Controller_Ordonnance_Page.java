package application;




import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;


public class Controller_Ordonnance_Page implements Initializable{
	@FXML
    private Label Retouid;

	@FXML
    private Button add_id;

    @FXML
    private Button update_id;

    @FXML
    private Button delete_id;
    
    @FXML
    private TableView<Model_Ordonnance_Page> table;

    @FXML
    private TableColumn<Model_Ordonnance_Page, Integer> col_id;

    @FXML
    private TableColumn<Model_Ordonnance_Page, String> col_nom;

    @FXML
    private TableColumn<Model_Ordonnance_Page, String> col_address;

    @FXML
    private TableColumn<Model_Ordonnance_Page, String> col_qualite;

    @FXML
    private TableColumn<Model_Ordonnance_Page, String> col_denomination;

    @FXML
    private TableColumn<Model_Ordonnance_Page, String> col_forme;

    @FXML
    private TableColumn<Model_Ordonnance_Page, String> col_posologie;

    @FXML
    private TableColumn<Model_Ordonnance_Page, String> col_mode_emploi;
    
    @FXML
    private TextField textfield_nom;

    @FXML
    private TextField textfield_address;

    @FXML
    private TextField textfield_qualite;

    @FXML
    private TextField textfield_denomination;

    @FXML
    private TextField textfield_forme;

    @FXML
    private TextField textfield_posologie;

    @FXML
    private TextField textfield_mode_demploi;
    
    @FXML
    private TextField textfield_search;
    @FXML
    private Button clear_id;

    
    
    ObservableList<Model_Ordonnance_Page> data = FXCollections.observableArrayList();
    public void initialize(URL location, ResourceBundle arg1) {
    	try {
			Connection con = DBConnectionforAdminpage.connect();
			ResultSet rs = con.createStatement().executeQuery("select * from ordonnance");
			while(rs.next()) {
				data.add(new Model_Ordonnance_Page(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6),rs.getString(7),rs.getString(8)));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		col_id.setCellValueFactory(new PropertyValueFactory<>("id"));
		col_nom.setCellValueFactory(new PropertyValueFactory<>("nom"));
		col_address.setCellValueFactory(new PropertyValueFactory<>("address"));
		col_qualite.setCellValueFactory(new PropertyValueFactory<>("qualite"));
		col_denomination.setCellValueFactory(new PropertyValueFactory<>("denomination"));
		col_forme.setCellValueFactory(new PropertyValueFactory<>("forme"));
		col_posologie.setCellValueFactory(new PropertyValueFactory<>("posologie"));
		col_mode_emploi.setCellValueFactory(new PropertyValueFactory<>("mode_emploi"));
		table.setItems(data);
		filetring();
		
    	
    		
    	
    	
    }
    
    
    @FXML
    void action_update_button(ActionEvent event) throws IOException {
		if (table.getSelectionModel().getSelectedItem() != null) {
            Model_Ordonnance_Page selectedPerson = table.getSelectionModel().getSelectedItem();
            int selected_id = selectedPerson.getId();
            
     		Model_Ordonnance_Page rdv = new Model_Ordonnance_Page();
     		
     		rdv.setId(selected_id);
     		rdv.setNom(textfield_nom.getText());
    		rdv.setAddress(textfield_address.getText());
    		rdv.setQualite(textfield_qualite.getText());
    		rdv.setDenomination(textfield_denomination.getText());
    		rdv.setForme(textfield_forme.getText());
    		rdv.setPosologie(textfield_posologie.getText());
    		rdv.setMode_emploi(textfield_mode_demploi.getText());  		
     		DBConnectionforAdminpage.update_for_Ordonnance_Page(rdv);
     		((Stage)(((Button)event.getSource()).getScene().getWindow())).close(); 
    		Stage primaryStage = new Stage();
    		Parent root = FXMLLoader.load(getClass().getResource("/application/Ordonnance_Page.fxml"));
    		Scene scene = new Scene(root,1000,600);
    		
    		primaryStage.setScene(scene);
    		primaryStage.show();
   
    		
    		
    		
             
        }else {
        	Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Warning");
			alert.setHeaderText("Information");
			alert.setContentText("Please select a rendezvous in the table left!!!!");
			alert.showAndWait();
        }
		
 
    }
    
    
    
    @FXML
    void action_delete_button(ActionEvent event) throws IOException {
		if (table.getSelectionModel().getSelectedItem() != null) {
            Model_Ordonnance_Page selectedPerson = table.getSelectionModel().getSelectedItem();
            int selected_id = selectedPerson.getId();
     		Model_Ordonnance_Page rdv = new Model_Ordonnance_Page();
     		
     		rdv.setId(selected_id);
     		DBConnectionforAdminpage.delete_for_Ordonnance_Page(rdv);
     		((Stage)(((Button)event.getSource()).getScene().getWindow())).close(); 
    		Stage primaryStage = new Stage();
    		Parent root = FXMLLoader.load(getClass().getResource("/application/Ordonnance_Page.fxml"));
    		Scene scene = new Scene(root,1000,600);
    		
    		primaryStage.setScene(scene);
    		primaryStage.show();
     		
    		
             
        }else {
        	Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Warning");
			alert.setHeaderText("Information");
			alert.setContentText("Please select an Orodonnance in the right table!!!!");
			alert.showAndWait();
        }
	}
    
    
    
    public void onEdit() {
        // check the table's selected item and get selected item
        if (table.getSelectionModel().getSelectedItem() != null) {
            Model_Ordonnance_Page selectedPerson = table.getSelectionModel().getSelectedItem();
            textfield_nom.setText(selectedPerson.getNom());
            textfield_address.setText(selectedPerson.getAddress());
            textfield_qualite.setText(selectedPerson.getQualite());
            textfield_denomination.setText(selectedPerson.getDenomination());
            textfield_forme.setText(selectedPerson.getForme());
            textfield_posologie.setText(selectedPerson.getPosologie());
            textfield_mode_demploi.setText(selectedPerson.getMode_emploi());
            
   
            
        }
    }
    
    
    
    public void filetring() {
    	FilteredList<Model_Ordonnance_Page> filteredData = new FilteredList<>(data, p -> true);

		// 2. Set the filter Predicate whenever the filter changes.
		textfield_search.textProperty().addListener((observable, oldValue, newValue) -> {
			filteredData.setPredicate(Model_Ordonnance_Page -> {
        // If filter text is empty, display all persons.
        if (newValue == null || newValue.isEmpty()) {
            return true;
        }

        // Compare first name and last name field in your object with filter.
        String lowerCaseFilter = newValue.toLowerCase();

        if (String.valueOf(Model_Ordonnance_Page.getNom()).toLowerCase().contains(lowerCaseFilter)) {
            return true;
            // Filter matches first name.

        } else if (String.valueOf(Model_Ordonnance_Page.getForme()).toLowerCase().contains(lowerCaseFilter)) {
            return true; // Filter matches last name.
        }  

        return false; // Does not match.
			});
		});

		// 3. Wrap the FilteredList in a SortedList. 
		SortedList<Model_Ordonnance_Page> sortedData = new SortedList<>(filteredData);

		// 4. Bind the SortedList comparator to the TableView comparator.
		sortedData.comparatorProperty().bind(table.comparatorProperty());
		// 5. Add sorted (and filtered) data to the table.
		table.setItems(sortedData);
    }
    
    @FXML
    void action_add_button(ActionEvent event) throws IOException{
		Model_Ordonnance_Page rdv = new Model_Ordonnance_Page();
		rdv.setNom(textfield_nom.getText());
		rdv.setAddress(textfield_address.getText());
		rdv.setQualite(textfield_qualite.getText());
		rdv.setDenomination(textfield_denomination.getText());
		rdv.setForme(textfield_forme.getText());
		rdv.setPosologie(textfield_posologie.getText());
		rdv.setMode_emploi(textfield_mode_demploi.getText());
		int status = DBConnectionforAdminpage.add_for_Orfonnance_Page(rdv);
		if (status>0) {
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("ADD Ordonnance");
			alert.setHeaderText("Information");
			alert.setContentText("Ordonnance successfully added");
			alert.showAndWait();
			
		}else {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("ADD Ordonnance");
			alert.setHeaderText("Information");
			alert.setContentText("Ordonnance has not beeen added!!!! \nPlease Try again ");
			alert.showAndWait();
			
		}
		
		((Stage)(((Button)event.getSource()).getScene().getWindow())).close(); 
		Stage primaryStage = new Stage();
		Parent root = FXMLLoader.load(getClass().getResource("/application/Ordonnance_Page.fxml"));
		Scene scene = new Scene(root,1000,600);
		
		primaryStage.setScene(scene);
		primaryStage.show();

    }
    
    
    
    @FXML
    void Retour_label_mouse_clicked(MouseEvent event) throws Exception {
    	((Stage)(((Label)event.getSource()).getScene().getWindow())).close(); 
		Stage primaryStage = new Stage();
		Parent root = FXMLLoader.load(getClass().getResource("/application/Medecinpage.fxml"));
		Scene scene = new Scene(root,900,600);
		
		primaryStage.setScene(scene);
		primaryStage.show();

    }
    @FXML
    void action_clear_button(ActionEvent event) {
	    textfield_nom.setText(null);
	    textfield_address.setText(null);
	    textfield_qualite.setText(null);
	    textfield_denomination.setText(null);
	    textfield_forme.setText(null);
	    textfield_posologie.setText(null);
	    textfield_mode_demploi.setText(null);
	    textfield_search.setText(null);

    }

}
