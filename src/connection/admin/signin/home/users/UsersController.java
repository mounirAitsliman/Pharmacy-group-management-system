package connection.admin.signin.home.users;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import main.ConnectionDB;
import main.Main;
import main.Users;

import javax.swing.*;
import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

public class UsersController implements Initializable {

    public TextField searchField;
    public TableColumn<Users, String> colCIN;
    public TextField cinField;
    public Label blankLabel;
    @FXML
    private Button backButton;

    @FXML
    private TableColumn<Users, String> colFIRSTNAME;

    @FXML
    private TableColumn<Users, Integer> colID;

    @FXML
    private TableColumn<Users, String> colLASTNAME;

    @FXML
    private TableColumn<Users, String> colROLE;

    @FXML
    private TableColumn<Users, String> colUSERNAME;

    @FXML
    private TextField firstnameField;

    @FXML
    private TextField lastnameField;

    @FXML
    private ComboBox<String> roleField;

    @FXML
    private TableView<Users> tableField;

    @FXML
    private TextField uidField;

    @FXML
    private TextField usernameField;
    ObservableList<Users> dataList;

    ConnectionDB dbConnection = new ConnectionDB();
    Connection cnx = ConnectionDB.getCnx();
    int index = -1;
    Main m = new Main();

    public void initialize(URL url, ResourceBundle resourceBundle){
        roleField.setItems(FXCollections.observableArrayList("Admin", "Seller"));

        loadtableUsers();
        search();
    }

    public void loadtableUsers(){
        colID.setCellValueFactory(new PropertyValueFactory<Users, Integer>("uid"));
        colFIRSTNAME.setCellValueFactory(new PropertyValueFactory<Users, String>("firstname"));
        colLASTNAME.setCellValueFactory(new PropertyValueFactory<Users, String>("lastname"));
        colUSERNAME.setCellValueFactory(new PropertyValueFactory<Users, String>("username"));
        colCIN.setCellValueFactory(new PropertyValueFactory<Users, String>("cin"));
        colROLE.setCellValueFactory(new PropertyValueFactory<Users, String>("role"));

        tableField.setItems(ConnectionDB.getDataUsers());
    }

    public void search() {
        colID.setCellValueFactory(new PropertyValueFactory<Users, Integer>("uid"));
        colFIRSTNAME.setCellValueFactory(new PropertyValueFactory<Users, String>("firstname"));
        colLASTNAME.setCellValueFactory(new PropertyValueFactory<Users, String>("lastname"));
        colUSERNAME.setCellValueFactory(new PropertyValueFactory<Users, String>("username"));
        colCIN.setCellValueFactory(new PropertyValueFactory<Users, String>("cin"));
        colROLE.setCellValueFactory(new PropertyValueFactory<Users, String>("role"));

        dataList = ConnectionDB.getDataUsers();
        tableField.setItems(dataList);

        FilteredList<Users> filteredData = new FilteredList<>(dataList, b -> true);
        searchField.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(user -> {

                if (newValue == null || newValue.isEmpty()){
                    return true;    //If filter search is empty, display all products
                }
                String searchKeyword = newValue.toLowerCase();

                if(user.getFirstname().toLowerCase().indexOf(searchKeyword) != -1){
                    return true;    //Filter matches name
                }else if (user.getLastname().toLowerCase().indexOf(searchKeyword) != -1){
                    return true; // Filter matches type
                } else if (String.valueOf(user.getUid()).indexOf(searchKeyword) != -1) {
                    return true;
                } else {
                    return false; // Does not match
                }
            });

        });

        SortedList<Users> sortedData = new SortedList<>(filteredData);
        sortedData.comparatorProperty().bind(tableField.comparatorProperty());
        tableField.setItems(sortedData);
    }
    
    @FXML
    void back(ActionEvent event) {
        m.changeScene("/connection/admin/signin/home/home.fxml");
    }


    //PROBLEM HERE : Don't delete from the sql table

    @FXML
    void delete(ActionEvent event) {
        String uid = uidField.getText();
        String firstname = firstnameField.getText();
        String lastname = lastnameField.getText();
        String cin = cinField.getText();
        String role = roleField.getSelectionModel().getSelectedItem().toString();
        String username = usernameField.getText();

        if (uid.isEmpty() || firstname.isEmpty() || lastname.isEmpty() || cin.isEmpty()|| role == null || username.isEmpty()){
            blankLabel.setText("Please fill all the blank fields");
        } else {
            String queryUser = "DELETE FROM users WHERE username = '"+username+"'";
            try {
                if(role == "Admin"){

                    String queryAdmin = "DELETE FROM admins WHERE username = '"+username+"'";
                    Statement statement = cnx.createStatement();
                    statement.executeUpdate(queryAdmin);
                    statement.executeUpdate(queryUser);

                    JOptionPane.showMessageDialog(null, "User was deleted");

                    uidField.setText("");
                    firstnameField.setText("");
                    lastnameField.setText("");
                    usernameField.setText("");
                    cinField.setText("");
                    loadtableUsers();
                    search();

                } else {

                    String querySeller = "DELETE FROM sellers WHERE username = '"+username+"'";
                    Statement statement = cnx.createStatement();
                    statement.executeUpdate(querySeller);
                    statement.executeUpdate(queryUser);

                    JOptionPane.showMessageDialog(null, "User was deleted");

                    uidField.setText("");
                    firstnameField.setText("");
                    lastnameField.setText("");
                    usernameField.setText("");
                    cinField.setText("");
                    loadtableUsers();
                    search();
                }


            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    @FXML
    void getSelected(MouseEvent event) {
        index = tableField.getSelectionModel().getSelectedIndex();
        if (index <= -1){
            return;
        }
        uidField.setText(colID.getCellData(index).toString());
        firstnameField.setText(colFIRSTNAME.getCellData(index).toString());
        lastnameField.setText(colLASTNAME.getCellData(index).toString());
        usernameField.setText(colUSERNAME.getCellData(index).toString());
        cinField.setText(colCIN.getCellData(index).toString());
        roleField.setValue(colROLE.getCellData(index).toString());
    }

    @FXML
    void update(ActionEvent event) {

        String uid = uidField.getText();
        String firstname = firstnameField.getText();
        String lastname = lastnameField.getText();
        String cin = cinField.getText();
        String role = roleField.getSelectionModel().getSelectedItem().toString();
        String username = usernameField.getText();

        if (uid.isEmpty() || firstname.isEmpty() || lastname.isEmpty() || cin.isEmpty()|| role == null || username.isEmpty()){
            blankLabel.setText("Please fill all the blank fields");
        } else {
            String queryUser = "UPDATE users set firstname = '"+ firstname +"'" +
                    ", lastname = '"+ lastname +"', username = '"+ username +"', cin = '"+ cin +"', role = '"+ role +
                    "' where uid = '"+uid+"'";

            //PROBLEM HERE : Dont update the sql table

            try
            {
                if (role == "Admin"){

                    String queryAdmin = "UPDATE admins set firstname = '"+ firstname +"'" +
                            ", lastname = '"+ lastname +"', username = '"+ username +"', cin = '"+ cin + "' where aid = '"+uid+"'";

                    Statement statement = cnx.createStatement();
                    statement.executeUpdate(queryAdmin);
                    statement.executeUpdate(queryUser);

                    JOptionPane.showMessageDialog(null, "Seller was updated");

                    uidField.setText("");
                    firstnameField.setText("");
                    lastnameField.setText("");
                    usernameField.setText("");
                    cinField.setText("");
                    loadtableUsers();
                    search();

                } else {
                    String querySeller = "UPDATE sellers set firstname = '"+ firstname +"'" +
                            ", lastname = '"+ lastname +"', username = '"+ username +"', cin = '"+ cin + "' where sid = '"+uid+"'";

                    Statement statement = cnx.createStatement();
                    statement.executeUpdate(querySeller);
                    statement.executeUpdate(queryUser);

                    JOptionPane.showMessageDialog(null, "Seller was updated");

                    uidField.setText("");
                    firstnameField.setText("");
                    lastnameField.setText("");
                    usernameField.setText("");
                    cinField.setText("");
                    loadtableUsers();
                    search();
                }

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }


}



