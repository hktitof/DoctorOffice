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
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.fxml.Initializable;


public class Controller_Rendezvous_Page implements Initializable{
	@FXML
    private AnchorPane rendez_vous_interface;
	
	@FXML
    private TableView<Model_rendezvous> table;

    @FXML
    private Label retour_id;

    @FXML
    private TableColumn<Model_rendezvous, Integer> col_id;

    @FXML
    private TableColumn<Model_rendezvous, String> col_prenom;

    @FXML
    private TableColumn<Model_rendezvous, String> col_nom;

    @FXML
    private TableColumn<Model_rendezvous, Integer> col_telephone;

    @FXML
    private TableColumn<Model_rendezvous, String> col_date;

    @FXML
    private Button add_id;

    @FXML
    private Button update_id;

    @FXML
    private Button delete_id;
    @FXML
    private TextField Textfield_prenom;

    @FXML
    private TextField Textfield_nom;

    @FXML
    private TextField Textfield_telephone;

    @FXML
    private DatePicker Textfield_date;
   
    @FXML
    private TextField textfield_search;
    @FXML
    private Button clear_id;
    @FXML
    private Label label_correct_prenom;

    @FXML
    private Label label_correct_date;

    @FXML
    private Label label_correct_telephone;

    @FXML
    private Label label_correct_nom;

    @FXML
    private Label label_not_correct_prenom;

    @FXML
    private Label label_not_correct_nom;

    @FXML
    private Label label_not_correct_telephone;

    @FXML
    private Label label_not_correct_date;
    
    
    @FXML
    void prenom_typed_action(KeyEvent event) {
    	if(Textfield_prenom.getText().trim().isEmpty()) {
    		label_correct_prenom.setText(null);
    		label_not_correct_prenom.setText(null);
    	}else {
    		
    		if(Textfield_prenom.getText().trim().matches("^[a-zA-Z ]*$")) {
    			label_correct_prenom.setText("✔");
        		label_not_correct_prenom.setText(null);
    		}else {
    			label_correct_prenom.setText(null);
        		label_not_correct_prenom.setText("✗");
    		}
            
    	}

    }
    
    
    @FXML
    void nom_typed_action(KeyEvent event) {
    	if(Textfield_nom.getText().trim().isEmpty()) {
    		label_correct_nom.setText(null);
    		label_not_correct_nom.setText(null);
    	}else {
    		if(Textfield_nom.getText().trim().matches("^[a-zA-Z ]*$")) {
    			label_correct_nom.setText("✔");
        		label_not_correct_nom.setText(null);
    		}else {
    			label_correct_nom.setText(null);
        		label_not_correct_nom.setText("✗");
    		}
    	}
    	
    	

    }
    
    
    
    @FXML
    void telephone_typed_action(KeyEvent event) {
    	if(Textfield_telephone.getText().trim().isEmpty()) {
    		label_correct_telephone.setText(null);
    		label_not_correct_telephone.setText(null);
    	}else {
    		label_correct_telephone.setText("✔");
    		label_not_correct_telephone.setText(null);
    		
            boolean numeric = true;

            try {
                
            } catch (NumberFormatException e) {
                numeric = false;
            }

            if(numeric) {
            	label_correct_telephone.setText("✔");
        		label_not_correct_telephone.setText(null);
            	
            }else {
            	label_correct_telephone.setText(null);
        		label_not_correct_telephone.setText("✗");
            	
            }
            	
    	}
    	

    }
    
    
    
    @FXML
    void Mouse_pressed_on_page(MouseEvent event) {
    	rendez_vous_interface.requestFocus();

    }
    
    
    
    
    @FXML
    void action_Datepicker(ActionEvent event) {
    	if(Textfield_date.getValue() != null) {
    		label_correct_date.setText("✔");
    		label_not_correct_date.setText(null);
    		
    	}else {
    		label_correct_date.setText(null);
    		label_not_correct_date.setText("✗");
    	}

    }
    
    @FXML
    void action_telephone(ActionEvent event) {
    	if(Textfield_telephone.getText().trim().isEmpty()) {
    		label_correct_telephone.setText(null);
    		label_not_correct_telephone.setText("✗");
    	}else {
    		label_correct_telephone.setText("✔");
    		label_not_correct_telephone.setText(null);
    		
            boolean numeric = true;

            try {
                
            } catch (NumberFormatException e) {
                numeric = false;
            }

            if(numeric) {
            	label_correct_telephone.setText("✔");
        		label_not_correct_telephone.setText(null);
            	
            }else {
            	label_correct_telephone.setText(null);
        		label_not_correct_telephone.setText("✗");
            	
            }
            	
    	}

    }
    
    @FXML
    void action_nom(ActionEvent event) {
    	if(Textfield_nom.getText().trim().isEmpty()) {
    		label_correct_nom.setText(null);
    		label_not_correct_nom.setText("✗");
    	}else {
    		if(Textfield_nom.getText().trim().matches("^[a-zA-Z]*$")) {
    			label_correct_nom.setText("✔");
        		label_not_correct_nom.setText(null);
    		}else {
    			label_correct_nom.setText(null);
        		label_not_correct_nom.setText("✗");
    		}
    	}

    }

    @FXML
    void action_prenom(ActionEvent event) {
    	if(Textfield_prenom.getText().trim().isEmpty()) {
    		label_correct_prenom.setText(null);
    		label_not_correct_prenom.setText("✗");
    	}else {
    		
    		if(Textfield_prenom.getText().trim().matches("^[a-zA-Z]*$")) {
    			label_correct_prenom.setText("✔");
        		label_not_correct_prenom.setText(null);
    		}else {
    			label_correct_prenom.setText(null);
        		label_not_correct_prenom.setText("✗");
    		}
            
    	}

    }
   
    
    
    
    
    

    
    
    
    
    
    
    
    
   
    
ObservableList<Model_rendezvous> data = FXCollections.observableArrayList();
	
	@Override
	public void initialize(URL location, ResourceBundle arg1) {
		Textfield_date.getEditor().setDisable(true);
		
		
			
				try {
					Connection con = DBConnectionforAdminpage.connect();
					ResultSet rs = con.createStatement().executeQuery("select * from rendezvous");
					while(rs.next()) {
						data.add(new Model_rendezvous(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getInt(4),rs.getString(5)));
					}
				} catch (SQLException e) {
					
					e.printStackTrace();
				}

		col_id.setCellValueFactory(new PropertyValueFactory<>("id"));
		col_prenom.setCellValueFactory(new PropertyValueFactory<>("prenom"));
		col_nom.setCellValueFactory(new PropertyValueFactory<>("nom"));
		col_telephone.setCellValueFactory(new PropertyValueFactory<>("telephone"));
		col_date.setCellValueFactory(new PropertyValueFactory<>("date"));

		table.setItems(data);
		
		filtering();
        
        table.setOnMouseClicked((MouseEvent event) -> {
            if (event.getClickCount() > 1) {
                onEdit();
            }
        });

	}
	
	
	
	public void filtering() {
		FilteredList<Model_rendezvous> filteredData = new FilteredList<>(data, p -> true);

        // 2. Set the filter Predicate whenever the filter changes.
        textfield_search.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(Model_rendezvous -> {
                // If filter text is empty, display all persons.
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }

                // Compare first name and last name field in your object with filter.
                String lowerCaseFilter = newValue.toLowerCase();

                if (String.valueOf(Model_rendezvous.getNom()).toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                    // Filter matches first name.

                } else if (String.valueOf(Model_rendezvous.getPrenom()).toLowerCase().contains(lowerCaseFilter)) {
                    return true; // Filter matches last name.
                }  else if (String.valueOf(Model_rendezvous.getDate()).toLowerCase().contains(lowerCaseFilter)) {
                    return true; // Filter matches last name.
                } 

                return false; // Does not match.
            });
        });

        // 3. Wrap the FilteredList in a SortedList. 
        SortedList<Model_rendezvous> sortedData = new SortedList<>(filteredData);

        // 4. Bind the SortedList comparator to the TableView comparator.
        sortedData.comparatorProperty().bind(table.comparatorProperty());
        // 5. Add sorted (and filtered) data to the table.
        table.setItems(sortedData);
	}
	
	
	
	public void onEdit() {
        // check the table's selected item and get selected item
        if (table.getSelectionModel().getSelectedItem() != null) {
            Model_rendezvous selectedPerson = table.getSelectionModel().getSelectedItem();
            Textfield_prenom.setText(selectedPerson.getPrenom());
            Textfield_nom.setText(selectedPerson.getNom());
            int telephone = selectedPerson.getTelephone();
            String telephone_to_String = Integer.toString(telephone);
            Textfield_telephone.setText(telephone_to_String);
            String date = selectedPerson.getDate();
            String[] dateList = date.split("-");
            String year_string = dateList [0];
            String month_string = dateList [1];
            String dayOfMonth_string = dateList [2];
            int year = Integer.parseInt(year_string);
            int month = Integer.parseInt(month_string);
            int dayOfMonth = Integer.parseInt(dayOfMonth_string);
            		
            Textfield_date.setValue(LocalDate.of(year, month, dayOfMonth));
            
            /*  1    */
            if(Textfield_prenom.getText().trim().isEmpty()) {
        		label_correct_prenom.setText(null);
        		label_not_correct_prenom.setText("✗");
        	}else {
        		
        		if(Textfield_prenom.getText().trim().matches("^[a-zA-Z ]*$")) {
        			label_correct_prenom.setText("✔");
            		label_not_correct_prenom.setText(null);
        		}else {
        			label_correct_prenom.setText(null);
            		label_not_correct_prenom.setText("✗");
        		}
                
        	}
            /*  2    */
            
            if(Textfield_nom.getText().trim().isEmpty()) {
        		label_correct_nom.setText(null);
        		label_not_correct_nom.setText("✗");
        	}else {
        		if(Textfield_nom.getText().trim().matches("^[a-zA-Z ]*$")) {
        			label_correct_nom.setText("✔");
            		label_not_correct_nom.setText(null);
        		}else {
        			label_correct_nom.setText(null);
            		label_not_correct_nom.setText("✗");
        		}
        	}
            
            /*  3    */

            if(Textfield_telephone.getText().trim().isEmpty()) {
        		label_correct_telephone.setText(null);
        		label_not_correct_telephone.setText("✗");
        	}else {
        		label_correct_telephone.setText("✔");
        		label_not_correct_telephone.setText(null);
        		
                boolean numeric = true;

                try {
                    
                } catch (NumberFormatException e) {
                    numeric = false;
                }

                if(numeric) {
                	label_correct_telephone.setText("✔");
            		label_not_correct_telephone.setText(null);
                	
                }else {
                	label_correct_telephone.setText(null);
            		label_not_correct_telephone.setText("✗");
                	
                }
                	
        	}
            
            
            
            
            
            
            
            
            
            
        }
    }
	
	@FXML
    void action_add_button(ActionEvent event) throws IOException {
		
		if(Textfield_prenom.getText().trim().isEmpty() || Textfield_nom.getText().trim().isEmpty() || Textfield_telephone.getText().trim().isEmpty() || Textfield_date.getValue() == null) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("ADD Rendez-vous");
			alert.setHeaderText("Information");
			alert.setContentText("Missing some Fields..Please try to fill all missing fields");
			alert.showAndWait();
			if(label_correct_nom.getText() !="✔") {
				label_correct_nom.setText(null);
	    		label_not_correct_nom.setText("✗");
				
			}
			if(label_correct_prenom.getText() !="✔") {
				label_correct_prenom.setText(null);
	    		label_not_correct_prenom.setText("✗");
				
			}
			if(label_correct_telephone.getText() !="✔") {
				label_correct_telephone.setText(null);
	    		label_not_correct_telephone.setText("✗");
				
			}
			if(label_correct_date.getText() !="✔") {
				label_correct_date.setText(null);
	    		label_not_correct_date.setText("✗");
				
			}
		}else {
			if((label_correct_date.getText() == "✔") && (label_correct_nom.getText()) == "✔" && (label_correct_prenom.getText() == "✔") && (label_correct_telephone.getText() == "✔")) {
				LocalDate date_rdv = Textfield_date.getValue();
				String prenom = Textfield_prenom.getText();
				String nom = Textfield_nom.getText().trim();
				String date = date_rdv.toString();
				String telephone_pickthis = Textfield_telephone.getText();
				int telephone = Integer.parseInt(telephone_pickthis);
				Model_rendezvous rdv = new Model_rendezvous();
				rdv.setPrenom(prenom);
				rdv.setNom(nom);
				rdv.setTelephone(telephone);
				rdv.setDate(date);
				int status = DBConnectionforAdminpage.add_for_rendezvous(rdv);
				if (status>0) {
					Alert alert = new Alert(AlertType.INFORMATION);
					alert.setTitle("ADD Rendez-vous");
					alert.setHeaderText("Information");
					alert.setContentText("Rendez-vous has beeen added for M/Mme : "+nom);
					alert.showAndWait();
					
				}else {
					Alert alert = new Alert(AlertType.ERROR);
					alert.setTitle("ADD Rendez-vous");
					alert.setHeaderText("Information");
					alert.setContentText("Rendez-vous has not beeen added for M/Mme : "+nom+"\nPlease Try again ");
					alert.showAndWait();
					
				}
				
				((Stage)(((Button)event.getSource()).getScene().getWindow())).close(); 
				Stage primaryStage = new Stage();
				Parent root = FXMLLoader.load(getClass().getResource("/application/Rendez_vousPage.fxml"));
				Scene scene = new Scene(root,900,600);
				
				primaryStage.setScene(scene);
				primaryStage.show();
			}else {
				Alert alert = new Alert(AlertType.ERROR);
				alert.setTitle("ADD Rendez-vous");
				alert.setHeaderText("Information");
				alert.setContentText("Wrong Input...");
				alert.showAndWait();
				
			}
			
			
		}
		
		
		

    }
	
	@FXML
    void action_update_button(ActionEvent event) throws IOException {
		if (table.getSelectionModel().getSelectedItem() != null) {
			if((label_correct_date.getText() == "✔") && (label_correct_nom.getText()) == "✔" && (label_correct_prenom.getText() == "✔") && (label_correct_telephone.getText() == "✔")) {
				Model_rendezvous selectedPerson = table.getSelectionModel().getSelectedItem();
	             int selected_id = selectedPerson.getId();
	            String prenom_col = Textfield_prenom.getText();
	     		String nom_col = Textfield_nom.getText();
	     		LocalDate date_rdv = Textfield_date.getValue();
	     		String date = date_rdv.toString();
	     		String telephone_not_intger = Textfield_telephone.getText();
	     		int telephone = Integer.parseInt(telephone_not_intger);
	     		Model_rendezvous rdv = new Model_rendezvous();
	     		
	     		rdv.setId(selected_id);
	     		rdv.setPrenom(prenom_col);
	     		rdv.setNom(nom_col);
	     		rdv.setTelephone(telephone);
	     		rdv.setDate(date);
	     		DBConnectionforAdminpage.update_for_rendezvous(rdv);
	     		((Stage)(((Button)event.getSource()).getScene().getWindow())).close(); 
	    		Stage primaryStage = new Stage();
	    		Parent root = FXMLLoader.load(getClass().getResource("/application/Rendez_vousPage.fxml"));
	    		Scene scene = new Scene(root,900,600);
	    		
	    		primaryStage.setScene(scene);
	    		primaryStage.show();
			}else {
				Alert alert = new Alert(AlertType.ERROR);
				alert.setTitle("UPDATE Rendez-vous");
				alert.setHeaderText("Information");
				alert.setContentText("Error Input...");
				alert.showAndWait();
				
			}
            
   
    		
    		
    		
             
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
            Model_rendezvous selectedPerson = table.getSelectionModel().getSelectedItem();
             int selected_id = selectedPerson.getId();
     		Model_rendezvous rdv = new Model_rendezvous();
     		
     		rdv.setId(selected_id);
     		DBConnectionforAdminpage.delete_for_rendezvous(rdv);
     		((Stage)(((Button)event.getSource()).getScene().getWindow())).close(); 
    		Stage primaryStage = new Stage();
    		Parent root = FXMLLoader.load(getClass().getResource("/application/Rendez_vousPage.fxml"));
    		Scene scene = new Scene(root,900,600);
    		
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
    void retour_clicked(MouseEvent event) throws Exception{
		((Stage)(((Label)event.getSource()).getScene().getWindow())).close(); 
		Stage primaryStage = new Stage();
		Parent root = FXMLLoader.load(getClass().getResource("/application/Secretarypage.fxml"));
		Scene scene = new Scene(root,900,600);
		
		primaryStage.setScene(scene);
		primaryStage.show();

    }
	
	@FXML
    void action_clear_button(ActionEvent event) {
		Textfield_prenom.setText(null);
	    Textfield_nom.setText(null);
	    Textfield_telephone.setText(null);
	    Textfield_date.setValue(null);
	    textfield_search.setText(null);
	    label_correct_date.setText(null);
	    label_correct_nom.setText(null);
	    label_correct_prenom.setText(null);
	    label_correct_telephone.setText(null);
	    label_not_correct_date.setText(null);
	    label_not_correct_nom.setText(null);
	    label_not_correct_prenom.setText(null);
	    label_not_correct_telephone.setText(null);
		

    }



}
