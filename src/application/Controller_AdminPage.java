package application;
import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;






import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.fxml.Initializable;

public class Controller_AdminPage implements Initializable{
	@FXML
	private TableView<Model_AdminPage> table;
	@FXML
	private TableColumn<Model_AdminPage,String> idcode;
	@FXML
	private TableColumn<Model_AdminPage,String> usernamecode;
	@FXML
	private TableColumn<Model_AdminPage,String> passwordcode;
	@FXML
    private TextField new_password;
	@FXML
    private Button change_button;
	@FXML
    private Label admin_logout_label;
	@FXML
    private Label label_set_user_selected;

	ObservableList<Model_AdminPage> oblist = FXCollections.observableArrayList();
	
	@Override
	public void initialize(URL location, ResourceBundle arg1) {
		
		
			
				try {
					java.sql.Connection con = DBConnector.getConnection();
					ResultSet rs = con.createStatement().executeQuery("select * from users");
					while(rs.next()) {
						oblist.add(new Model_AdminPage(rs.getInt("id_users"),rs.getString("username"),rs.getString("password")));
					}
				} catch (SQLException e) {
					
					e.printStackTrace();
				}
				
		
		 
		
		idcode.setCellValueFactory(new PropertyValueFactory<>("id"));
		usernamecode.setCellValueFactory(new PropertyValueFactory<>("username"));
		passwordcode.setCellValueFactory(new PropertyValueFactory<>("password"));
		
		table.setItems(oblist);
		
	}
	
	
	
	@FXML
    void set_label_method(MouseEvent event) {
		Model_AdminPage Selected_user_object = table.getSelectionModel().getSelectedItem();
		label_set_user_selected.setText(Selected_user_object.getUsername());

    }
	
	
	@FXML
    void update(ActionEvent event) {
		if (table.getSelectionModel().getSelectedItem() != null) {
			Model_AdminPage Selected_user_object = table.getSelectionModel().getSelectedItem();
			
			try {
			  	int id = Selected_user_object.getId();
				String password = new_password.getText();
				int status = DBConnectionforAdminpage.update(id,password);
				if (status>0) {
					Alert  alert = new Alert(AlertType.INFORMATION);
					alert.setTitle("Update Password");
					alert.setHeaderText("Information");
					alert.setContentText("Password is Changed!");
					alert.showAndWait();
					table.getItems().clear();
					try {
						java.sql.Connection con = DBConnector.getConnection();
						ResultSet rs = con.createStatement().executeQuery("select * from users");
						while(rs.next()) {
							oblist.add(new Model_AdminPage(rs.getInt("id_users"),rs.getString("username"),rs.getString("password")));
						}
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
			
			 
			
					idcode.setCellValueFactory(new PropertyValueFactory<>("id"));
					usernamecode.setCellValueFactory(new PropertyValueFactory<>("username"));
					passwordcode.setCellValueFactory(new PropertyValueFactory<>("password"));
			
					table.setItems(oblist);
					new_password.setText(null);
					label_set_user_selected.setText(null);
				}else {
					Alert  alert = new Alert(AlertType.ERROR);
					alert.setTitle("Update Password");
					alert.setHeaderText("Information");
					alert.setContentText("Password is noooot Changed!");
					alert.showAndWait();
				}
				
			
			
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		
			
			
		}else {
        	Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Warning");
			alert.setHeaderText("Information");
			alert.setContentText("Please select a user in the right table!!!!");
			alert.showAndWait();
        }
		
		
		
		
		
		
    }
	@FXML
    void logout_clicked(MouseEvent event) throws IOException {
		((Stage)(((Label)event.getSource()).getScene().getWindow())).close();
		Stage primaryStage = new Stage();
		Parent root = FXMLLoader.load(getClass().getResource("/application/Login.fxml"));
		Scene scene = new Scene(root,500,500);
		
		primaryStage.setScene(scene);
		primaryStage.show();

    }

}
