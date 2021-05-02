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
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.fxml.Initializable;







public class Controller_Fiche_Page extends Controller_Login implements Initializable {
	@FXML
    private Label retour_id;

    @FXML
    private Button add_id;

    @FXML
    private Button update_id;

    @FXML
    private Button delete_id;
    @FXML
    private TableView<Model_Fiche_Page> table;
    
    @FXML
    private TableColumn<Model_Fiche_Page, Integer> col_id;

    @FXML
    private TableColumn<Model_Fiche_Page, String> col_prenom;

    @FXML
    private TableColumn<Model_Fiche_Page, String> col_nom;

    @FXML
    private TableColumn<Model_Fiche_Page, String> col_date_naisance;

    @FXML
    private TableColumn<Model_Fiche_Page, String> col_cin;

    @FXML
    private TableColumn<Model_Fiche_Page, String> col_profession;

    @FXML
    private TableColumn<Model_Fiche_Page, String> col_mutuelle;

    @FXML
    private TableColumn<Model_Fiche_Page, String> col_date_creation;

    @FXML
    private TableColumn<Model_Fiche_Page, String> col_commentaire;
    
    @FXML
    private TextField textfield_search;
    @FXML
    private TextField Textfield_prenom;

    @FXML
    private TextField Textfield_nom;

    @FXML
    private TextField Textfield_cin;

    @FXML
    private TextField Textfield_profession;

    @FXML
    private TextField Textfield_mutuelle;

    @FXML
    private DatePicker Textfield_date_naissance;

    @FXML
    private DatePicker Textfield_date_creation;

    @FXML
    private TextArea Textfield_commentaire;
    @FXML
    private Button clear_id;
    
    
    
    
    
    
    
ObservableList<Model_Fiche_Page> data = FXCollections.observableArrayList();
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		try {
			Connection con = DBConnectionforAdminpage.connect();
			ResultSet rs = con.createStatement().executeQuery("select * from fiche");
			while(rs.next()) {
				data.add(new Model_Fiche_Page(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6),rs.getString(7),rs.getString(8),rs.getString(9)));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		col_id.setCellValueFactory(new PropertyValueFactory<>("id"));
		col_prenom.setCellValueFactory(new PropertyValueFactory<>("prenom"));
		col_nom.setCellValueFactory(new PropertyValueFactory<>("nom"));
		col_date_naisance.setCellValueFactory(new PropertyValueFactory<>("date_n"));
		col_cin.setCellValueFactory(new PropertyValueFactory<>("cin"));
		col_profession.setCellValueFactory(new PropertyValueFactory<>("profession"));
		col_mutuelle.setCellValueFactory(new PropertyValueFactory<>("mutuelle"));
		col_date_creation.setCellValueFactory(new PropertyValueFactory<>("date_cre"));
		col_commentaire.setCellValueFactory(new PropertyValueFactory<>("comm"));


		table.setItems(data);
		
		filetring();
		
		table.setOnMouseClicked((MouseEvent event) -> {
            if (event.getClickCount() > 1) {
                onEdit();
            }
        });

		
	}
	
    public void filetring() {
    	FilteredList<Model_Fiche_Page> filteredData = new FilteredList<>(data, p -> true);

		// 2. Set the filter Predicate whenever the filter changes.
		textfield_search.textProperty().addListener((observable, oldValue, newValue) -> {
			filteredData.setPredicate(Model_Fiche_Page -> {
        // If filter text is empty, display all persons.
        if (newValue == null || newValue.isEmpty()) {
            return true;
        }

        // Compare first name and last name field in your object with filter.
        String lowerCaseFilter = newValue.toLowerCase();

        if (String.valueOf(Model_Fiche_Page.getNom()).toLowerCase().contains(lowerCaseFilter)) {
            return true;
            // Filter matches first name.

        } else if (String.valueOf(Model_Fiche_Page.getPrenom()).toLowerCase().contains(lowerCaseFilter)) {
            return true; // Filter matches last name.
        }  else if (String.valueOf(Model_Fiche_Page.getCin()).toLowerCase().contains(lowerCaseFilter)) {
            return true; // Filter matches last name.
        } 

        return false; // Does not match.
			});
		});

		// 3. Wrap the FilteredList in a SortedList. 
		SortedList<Model_Fiche_Page> sortedData = new SortedList<>(filteredData);

		// 4. Bind the SortedList comparator to the TableView comparator.
		sortedData.comparatorProperty().bind(table.comparatorProperty());
		// 5. Add sorted (and filtered) data to the table.
		table.setItems(sortedData);
    }
    
    
    @FXML
    public void onEdit() {
        // check the table's selected item and get selected item
        if (table.getSelectionModel().getSelectedItem() != null) {
            Model_Fiche_Page selectedPerson = table.getSelectionModel().getSelectedItem();
            Textfield_prenom.setText(selectedPerson.getPrenom());
            Textfield_nom.setText(selectedPerson.getNom());
            String date_naissance = selectedPerson.getDate_n();
            String[] dateList_naissance = date_naissance.split("-");
            String year_string = dateList_naissance [0];
            String month_string = dateList_naissance [1];
            String dayOfMonth_string = dateList_naissance [2];
            int year = Integer.parseInt(year_string);
            int month = Integer.parseInt(month_string);
            int dayOfMonth = Integer.parseInt(dayOfMonth_string);
            		
            Textfield_date_naissance.setValue(LocalDate.of(year, month, dayOfMonth));
            Textfield_cin.setText(selectedPerson.getCin());
            Textfield_profession.setText(selectedPerson.getProfession());
            Textfield_profession.setText(selectedPerson.getMutuelle());
            String date_creation = selectedPerson.getDate_cre();
            String[] dateList_creation = date_creation.split("-");
            year_string = dateList_creation [0];
            month_string = dateList_creation [1];
            dayOfMonth_string = dateList_creation [2];
            year = Integer.parseInt(year_string);
            month = Integer.parseInt(month_string);
            dayOfMonth = Integer.parseInt(dayOfMonth_string);
            		
            Textfield_date_creation.setValue(LocalDate.of(year, month, dayOfMonth));
            Textfield_commentaire.setText(selectedPerson.getComm());
            Textfield_mutuelle.setText(selectedPerson.getMutuelle());
            
        }
    }
    
    
    
    
    @FXML
    void action_add_button(ActionEvent event) throws IOException{
    	LocalDate date_cr = Textfield_date_creation.getValue();
    	LocalDate date_naiss = Textfield_date_creation.getValue();
		String prenom = Textfield_prenom.getText();
		String nom = Textfield_nom.getText();
		String date_creation = date_cr.toString();
		String date_naissance = date_naiss.toString();
		String cin_var = Textfield_cin.getText();
		String profession = Textfield_profession.getText();
		String mutuelle = Textfield_mutuelle.getText();
		String comment = Textfield_commentaire.getText();
		Model_Fiche_Page rdv = new Model_Fiche_Page();
		rdv.setPrenom(prenom);
		rdv.setNom(nom);
		rdv.setDate_cre(date_creation);
		rdv.setDate_n(date_naissance);
		rdv.setCin(cin_var);
		rdv.setProfession(profession);
		rdv.setMutuelle(mutuelle);
		rdv.setComm(comment);
		int status = DBConnectionforAdminpage.add_for_fiche(rdv);
		if (status>0) {
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("ADD Fiche");
			alert.setHeaderText("Information");
			alert.setContentText("FIche has beeen added for M/Mme : "+nom);
			alert.showAndWait();
			
		}else {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("ADD Fiche");
			alert.setHeaderText("Information");
			alert.setContentText("Fiche has not beeen added for M/Mme : "+nom+"\nPlease Try again ");
			alert.showAndWait();
			
		}
		
		((Stage)(((Button)event.getSource()).getScene().getWindow())).close(); 
		Stage primaryStage = new Stage();
		Parent root = FXMLLoader.load(getClass().getResource("/application/Fiche_Page.fxml"));
		Scene scene = new Scene(root,900,600);
		
		primaryStage.setScene(scene);
		primaryStage.show();
    	
    	

    }
    
    
    @FXML
    void action_update_button(ActionEvent event) throws IOException{

		if (table.getSelectionModel().getSelectedItem() != null) {
            Model_Fiche_Page selectedPerson = table.getSelectionModel().getSelectedItem();
             int selected_id = selectedPerson.getId();
             LocalDate date_cr = Textfield_date_creation.getValue();
         	LocalDate date_naiss = Textfield_date_creation.getValue();
     		String prenom = Textfield_prenom.getText();
     		String nom = Textfield_nom.getText();
     		String date_creation = date_cr.toString();
     		String date_naissance = date_naiss.toString();
     		String cin_var = Textfield_cin.getText();
     		String profession = Textfield_profession.getText();
     		String mutuelle = Textfield_mutuelle.getText();
     		String comment = Textfield_commentaire.getText();
     		Model_Fiche_Page rdv = new Model_Fiche_Page();
     		rdv.setId(selected_id);
     		rdv.setPrenom(prenom);
     		rdv.setNom(nom);
     		rdv.setDate_n(date_naissance);
     		rdv.setCin(cin_var);
     		rdv.setProfession(profession);
     		rdv.setMutuelle(mutuelle);
     		rdv.setDate_cre(date_creation);
     		rdv.setComm(comment);
     		DBConnectionforAdminpage.update_for_fiche(rdv);
     		((Stage)(((Button)event.getSource()).getScene().getWindow())).close(); 
    		Stage primaryStage = new Stage();
    		Parent root = FXMLLoader.load(getClass().getResource("/application/Fiche_Page.fxml"));
    		Scene scene = new Scene(root,900,600);
    		
    		primaryStage.setScene(scene);
    		primaryStage.show();
   
    		
    		
    		
             
        }else {
        	Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Warning");
			alert.setHeaderText("Information");
			alert.setContentText("Please select a fiche in the above table !!!!");
			alert.showAndWait();
        }

    }
    
    
    @FXML
    void action_delete_button(ActionEvent event) throws IOException{
    	if (table.getSelectionModel().getSelectedItem() != null) {
            Model_Fiche_Page selectedPerson = table.getSelectionModel().getSelectedItem();
             int selected_id = selectedPerson.getId();
             Model_Fiche_Page rdv = new  Model_Fiche_Page();
     		
     		rdv.setId(selected_id);
     		DBConnectionforAdminpage.delete_for_fiche(rdv);
     		((Stage)(((Button)event.getSource()).getScene().getWindow())).close(); 
    		Stage primaryStage = new Stage();
    		Parent root = FXMLLoader.load(getClass().getResource("/application/Fiche_Page.fxml"));
    		Scene scene = new Scene(root,900,600);
    		
    		primaryStage.setScene(scene);
    		primaryStage.show();
     		
    		
             
        }else {
        	Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Warning");
			alert.setHeaderText("Information");
			alert.setContentText("Please select a Fiche in the table left!!!!");
			alert.showAndWait();
        }

    }
    
    
    
    

    @FXML
    void retour_clicked(MouseEvent event) throws IOException {
    	if(varforchecking == 0) {
    		((Stage)(((Label)event.getSource()).getScene().getWindow())).close();
    		Stage primaryStage = new Stage();
    		Parent root = FXMLLoader.load(getClass().getResource("/application/Gestion_patients.fxml"));
    		Scene scene = new Scene(root,900,600);
    		
    		primaryStage.setScene(scene);
    		primaryStage.show();
    		
    	}else {
    		((Stage)(((Label)event.getSource()).getScene().getWindow())).close();
    		Stage primaryStage = new Stage();
    		Parent root = FXMLLoader.load(getClass().getResource("/application/Secretarypage.fxml"));;
    		Scene scene = new Scene(root,900,600);
    		
    		primaryStage.setScene(scene);
    		primaryStage.show();
    		
    	}
    	

    }
    
    @FXML
    void action_clear_button(ActionEvent event) {
	    Textfield_prenom.setText(null);
	    Textfield_nom.setText(null);
	    Textfield_cin.setText(null);
	    Textfield_profession.setText(null);
	    Textfield_mutuelle.setText(null);
	    Textfield_date_naissance.setValue(null);
	    Textfield_date_creation.setValue(null);
	    Textfield_commentaire.setText(null);

    }

}
