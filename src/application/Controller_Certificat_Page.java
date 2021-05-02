package application;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
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
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class Controller_Certificat_Page implements Initializable {
	@FXML
    private Label Retouid;

    @FXML
    private Button add_id;

    @FXML
    private Button update_id;

    @FXML
    private Button delete_id;
    
    @FXML
    private TableView<Model_Certificat_Page> table;

    @FXML
    private TableColumn<Model_Certificat_Page, Integer> col_id;

    @FXML
    private TableColumn<Model_Certificat_Page, String> col_nom;

    @FXML
    private TableColumn<Model_Certificat_Page, String> col_date_naissance;

    @FXML
    private TableColumn<Model_Certificat_Page, String> col_etabli_a;

    @FXML
    private TableColumn<Model_Certificat_Page, String> col_date;
    @FXML
    private TextField Textfield_nom;

    @FXML
    private TextField Textfield_etabli_a;
    @FXML
    private DatePicker Textfield_date_naissance;

    @FXML
    private DatePicker Textfield_date;
    @FXML
    private TextField textfield_search;
    @FXML
    private Button clear_id;
    
    
    
ObservableList<Model_Certificat_Page> data = FXCollections.observableArrayList();
	
	@Override
	public void initialize(URL location, ResourceBundle arg1) {
		
		
			
				try {
					Connection con = DBConnectionforAdminpage.connect();
					ResultSet rs = con.createStatement().executeQuery("select * from certificat");
					while(rs.next()) {
						data.add(new Model_Certificat_Page(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5)));
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

		col_id.setCellValueFactory(new PropertyValueFactory<>("id"));
		col_nom.setCellValueFactory(new PropertyValueFactory<>("nom"));
		col_date_naissance.setCellValueFactory(new PropertyValueFactory<>("date_naiss"));
		col_etabli_a.setCellValueFactory(new PropertyValueFactory<>("etabli_a"));
		col_date.setCellValueFactory(new PropertyValueFactory<>("date"));

		table.setItems(data);
		
		filtering();

	}
	
	
	public void onEdit() {
        // check the table's selected item and get selected item
        if (table.getSelectionModel().getSelectedItem() != null) {
            Model_Certificat_Page selectedPerson = table.getSelectionModel().getSelectedItem();
            Textfield_nom.setText(selectedPerson.getNom());
            
            
            String date = selectedPerson.getDate();
            String[] dateList = date.split("-");
            String year_string = dateList [0];
            String month_string = dateList [1];
            String dayOfMonth_string = dateList [2];
            int year = Integer.parseInt(year_string);
            int month = Integer.parseInt(month_string);
            int dayOfMonth = Integer.parseInt(dayOfMonth_string);		
            Textfield_date.setValue(LocalDate.of(year, month, dayOfMonth));
            String date_naiss = selectedPerson.getDate();
            String[] date_naiss_List = date_naiss.split("-");
            year_string = date_naiss_List [0];
            month_string = date_naiss_List [1];
            dayOfMonth_string = date_naiss_List [2];
            year = Integer.parseInt(year_string);
            month = Integer.parseInt(month_string);
            dayOfMonth = Integer.parseInt(dayOfMonth_string);
            Textfield_date_naissance.setValue(LocalDate.of(year, month, dayOfMonth));		
            Textfield_etabli_a.setText(selectedPerson.getEtabli_a());
    }
	}
    
    
    
    
    
    
    
    public void filtering() {
		FilteredList<Model_Certificat_Page> filteredData = new FilteredList<>(data, p -> true);

        // 2. Set the filter Predicate whenever the filter changes.
        textfield_search.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(Model_Certificat_Page -> {
                // If filter text is empty, display all persons.
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }

                // Compare first name and last name field in your object with filter.
                String lowerCaseFilter = newValue.toLowerCase();

                if (String.valueOf(Model_Certificat_Page.getNom()).toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                    // Filter matches first name.

                }

                return false; // Does not match.
            });
        });

        // 3. Wrap the FilteredList in a SortedList. 
        SortedList<Model_Certificat_Page> sortedData = new SortedList<>(filteredData);

        // 4. Bind the SortedList comparator to the TableView comparator.
        sortedData.comparatorProperty().bind(table.comparatorProperty());
        // 5. Add sorted (and filtered) data to the table.
        table.setItems(sortedData);
	}
    
    
    
    
    
    @FXML
    void action_add_button(ActionEvent event) throws IOException {
		LocalDate date_naiss = Textfield_date_naissance.getValue();
		String nom = Textfield_nom.getText();
		String date_n = date_naiss.toString();
		LocalDate date_pick = Textfield_date.getValue();
		String date = date_pick.toString();
		String etabli_a = Textfield_etabli_a.getText();
		
		Model_Certificat_Page rdv = new Model_Certificat_Page();
		rdv.setNom(nom);
		rdv.setDate(date);
		rdv.setDate_naiss(date_n);
		rdv.setEtabli_a(etabli_a);
		int status = DBConnectionforAdminpage.add_for_Certificat_Page(rdv);
		if (status>0) {
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("ADD Certificat");
			alert.setHeaderText("Information");
			alert.setContentText("Certificat has beeen added for M/Mme : "+nom);
			alert.showAndWait();
			
		}else {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("ADD Certificat");
			alert.setHeaderText("Information");
			alert.setContentText("Certificat has not beeen added for M/Mme : "+nom+"\nPlease Try again ");
			alert.showAndWait();
			
		}
		((Stage)(((Button)event.getSource()).getScene().getWindow())).close(); 
		Stage primaryStage = new Stage();
		Parent root = FXMLLoader.load(getClass().getResource("/application/Certificat_Page.fxml"));
		Scene scene = new Scene(root,900,600);
		
		primaryStage.setScene(scene);
		primaryStage.show();
		
		

    }
    
    
    
    @FXML
    void action_update_button(ActionEvent event) throws IOException {
		if (table.getSelectionModel().getSelectedItem() != null) {
            Model_Certificat_Page selectedPerson = table.getSelectionModel().getSelectedItem();
            int selected_id = selectedPerson.getId();
            LocalDate date_naiss = Textfield_date_naissance.getValue();
    		String nom = Textfield_nom.getText();
    		String date_n = date_naiss.toString();
    		LocalDate date_pick = Textfield_date.getValue();
    		String date = date_pick.toString();
    		String etabli_a = Textfield_etabli_a.getText();
     		Model_Certificat_Page rdv = new Model_Certificat_Page();
     		rdv.setId(selected_id);
     		rdv.setNom(nom);
    		rdv.setDate(date);
    		rdv.setDate_naiss(date_n);
    		rdv.setEtabli_a(etabli_a);
     		DBConnectionforAdminpage.update_for_Certificat_Page(rdv);
     		((Stage)(((Button)event.getSource()).getScene().getWindow())).close(); 
    		Stage primaryStage = new Stage();
    		Parent root = FXMLLoader.load(getClass().getResource("/application/Certificat_Page.fxml"));
    		Scene scene = new Scene(root,900,600);
    		
    		primaryStage.setScene(scene);
    		primaryStage.show();
   
    		
    		
    		
             
        }else {
        	Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Warning");
			alert.setHeaderText("Information");
			alert.setContentText("Please select a Certificat in the right table!!!!");
			alert.showAndWait();
        }
		
 
    }
    
    @FXML
    void action_delete_button(ActionEvent event) throws IOException {
		if (table.getSelectionModel().getSelectedItem() != null) {
            Model_Certificat_Page selectedPerson = table.getSelectionModel().getSelectedItem();
             int selected_id = selectedPerson.getId();
     		Model_Certificat_Page rdv = new Model_Certificat_Page();
     		
     		rdv.setId(selected_id);
     		DBConnectionforAdminpage.delete_for_Certificat_Page(rdv);
     		((Stage)(((Button)event.getSource()).getScene().getWindow())).close(); 
    		Stage primaryStage = new Stage();
    		Parent root = FXMLLoader.load(getClass().getResource("/application/Certificat_Page.fxml"));
    		Scene scene = new Scene(root,900,600);
    		
    		primaryStage.setScene(scene);
    		primaryStage.show();
     		
    		
             
        }else {
        	Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Warning");
			alert.setHeaderText("Information");
			alert.setContentText("Please select a Certificat in the table left!!!!");
			alert.showAndWait();
        }
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
	    Textfield_nom.setText(null);
	    Textfield_etabli_a.setText(null);
	    Textfield_date_naissance.setValue(null);
	    Textfield_date.setValue(null);
	    textfield_search.setText(null);

    }

}
