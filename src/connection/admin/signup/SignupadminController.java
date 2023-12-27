package connection.admin.signup;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import main.ConnectionDB;
import main.Main;

import java.sql.Connection;
import java.sql.Statement;

public class SignupadminController {

    @FXML
    private Button backButton;

    @FXML
    private TextField cinField;

    @FXML
    private Label cinLabel;

    @FXML
    private Button closeButton;

    @FXML
    private PasswordField confirmpwdField;

    @FXML
    private Label confirmpwdLabel;

    @FXML
    private TextField firstnameField;

    @FXML
    private Label firstnameLabel;

    @FXML
    private TextField lastnameField;

    @FXML
    private Label lastnameLabel;

    @FXML
    private Label peronalinformationsLabel;

    @FXML
    private PasswordField pwdField;

    @FXML
    private Label pwdLabel;

    @FXML
    private Button registerButton;

    @FXML
    private TextField usernameField;

    @FXML
    private Label usernameLabel;

    ConnectionDB dbConnection = new ConnectionDB();
    Connection cnx = ConnectionDB.getCnx();
    Main m = new Main();
    @FXML
    void back(ActionEvent event) {
        m.changeScene("/connection/admin/signin/siginadmin.fxml");
    }

    @FXML
    void close(ActionEvent event) {
        Stage stage = (Stage) closeButton.getScene().getWindow();
        stage.close();
    }

    @FXML
    void signup(ActionEvent event) {
        if(firstnameField.getText().isBlank() && lastnameField.getText().isBlank() && usernameField.getText().isBlank()
                && cinField.getText().isBlank() && pwdField.getText().isBlank()){
            peronalinformationsLabel.setText("Please enter your pesonal informations");
            firstnameLabel.setText("");
            lastnameLabel.setText("");
            usernameLabel.setText("");
            cinField.setText("");
            pwdLabel.setText("");
            confirmpwdLabel.setText("");
        } else if (firstnameField.getText().isBlank() && lastnameField.getText().isBlank() &&
                usernameField.getText().isBlank() && cinField.getText().isBlank()) {
            peronalinformationsLabel.setText("");
            firstnameLabel.setText("*");
            lastnameLabel.setText("*");
            usernameLabel.setText("*");
            cinLabel.setText("*");
            pwdLabel.setText("");
            confirmpwdLabel.setText("");
        } else if (firstnameField.getText().isBlank() && lastnameField.getText().isBlank()
                && usernameField.getText().isBlank() && pwdField.getText().isBlank()) {
            peronalinformationsLabel.setText("");
            firstnameLabel.setText("*");
            lastnameLabel.setText("*");
            usernameLabel.setText("*");
            cinLabel.setText("");
            pwdLabel.setText("*");
            confirmpwdLabel.setText("");
        } else if (firstnameField.getText().isBlank() && lastnameField.getText().isBlank()
                && cinField.getText().isBlank() && pwdField.getText().isBlank()) {
            peronalinformationsLabel.setText("");
            firstnameLabel.setText("*");
            lastnameLabel.setText("*");
            usernameLabel.setText("");
            cinLabel.setText("*");
            pwdLabel.setText("*");
            confirmpwdLabel.setText("");
        } else if (firstnameField.getText().isBlank() && usernameField.getText().isBlank()
                && cinField.getText().isBlank() && pwdField.getText().isBlank()) {
            peronalinformationsLabel.setText("");
            firstnameLabel.setText("*");
            lastnameLabel.setText("");
            usernameLabel.setText("*");
            cinLabel.setText("*");
            pwdLabel.setText("*");
            confirmpwdLabel.setText("");
        } else if (lastnameField.getText().isBlank() && usernameField.getText().isBlank()
                && cinField.getText().isBlank() && pwdField.getText().isBlank()) {
            peronalinformationsLabel.setText("");
            firstnameLabel.setText("");
            lastnameLabel.setText("*");
            usernameLabel.setText("*");
            cinLabel.setText("*");
            pwdLabel.setText("*");
            confirmpwdLabel.setText("");
        } else if (firstnameField.getText().isBlank() && usernameField.getText().isBlank()
                && lastnameField.getText().isBlank()) {
            peronalinformationsLabel.setText("");
            firstnameLabel.setText("*");
            lastnameLabel.setText("*");
            usernameLabel.setText("*");
            cinLabel.setText("");
            pwdLabel.setText("");
            confirmpwdLabel.setText("");
        } else if (firstnameField.getText().isBlank() && lastnameField.getText().isBlank()
                && cinField.getText().isBlank()) {
            peronalinformationsLabel.setText("");
            firstnameLabel.setText("*");
            lastnameLabel.setText("*");
            usernameLabel.setText("");
            cinLabel.setText("*");
            pwdLabel.setText("");
            confirmpwdLabel.setText("");
        } else if (firstnameField.getText().isBlank() && usernameField.getText().isBlank()
                && cinField.getText().isBlank()) {
            peronalinformationsLabel.setText("");
            firstnameLabel.setText("*");
            lastnameLabel.setText("");
            usernameLabel.setText("*");
            cinLabel.setText("*");
            pwdLabel.setText("");
            confirmpwdLabel.setText("");
        } else if (firstnameField.getText().isBlank() && pwdField.getText().isBlank()
                && cinField.getText().isBlank()) {
            peronalinformationsLabel.setText("");
            firstnameLabel.setText("*");
            lastnameLabel.setText("");
            usernameLabel.setText("");
            cinLabel.setText("*");
            pwdLabel.setText("*");
            confirmpwdLabel.setText("");
        } else if (lastnameField.getText().isBlank() && usernameField.getText().isBlank()
                && cinField.getText().isBlank()) {
            peronalinformationsLabel.setText("");
            firstnameLabel.setText("");
            lastnameLabel.setText("*");
            usernameLabel.setText("*");
            cinLabel.setText("*");
            pwdLabel.setText("");
            confirmpwdLabel.setText("");
        } else if (lastnameField.getText().isBlank() && pwdField.getText().isBlank()
                && cinField.getText().isBlank()) {
            peronalinformationsLabel.setText("");
            firstnameLabel.setText("");
            lastnameLabel.setText("*");
            usernameLabel.setText("");
            cinLabel.setText("*");
            pwdLabel.setText("*");
            confirmpwdLabel.setText("");
        } else if (usernameField.getText().isBlank() && pwdField.getText().isBlank()
                && cinField.getText().isBlank()) {
            peronalinformationsLabel.setText("");
            firstnameLabel.setText("");
            lastnameLabel.setText("");
            usernameLabel.setText("*");
            cinLabel.setText("*");
            pwdLabel.setText("*");
            confirmpwdLabel.setText("");
        } else if (lastnameField.getText().isBlank() && usernameField.getText().isBlank() &&
                pwdField.getText().isBlank()) {
            peronalinformationsLabel.setText("");
            firstnameLabel.setText("");
            lastnameLabel.setText("*");
            usernameLabel.setText("*");
            cinLabel.setText("");
            pwdLabel.setText("*");
            confirmpwdLabel.setText("");
        } else if (firstnameField.getText().isBlank() && usernameField.getText().isBlank() &&
                pwdField.getText().isBlank()) {
            peronalinformationsLabel.setText("");
            firstnameLabel.setText("*");
            lastnameLabel.setText("");
            usernameLabel.setText("*");
            pwdLabel.setText("*");
            cinLabel.setText("");
            confirmpwdLabel.setText("");
        } else if (firstnameField.getText().isBlank() && lastnameField.getText().isBlank() &&
                pwdField.getText().isBlank()) {
            peronalinformationsLabel.setText("");
            firstnameLabel.setText("*");
            lastnameLabel.setText("*");
            usernameLabel.setText("");
            cinLabel.setText("");
            pwdLabel.setText("*");
            confirmpwdLabel.setText("");
        } else if (usernameField.getText().isBlank() && pwdField.getText().isBlank()) {
            peronalinformationsLabel.setText("");
            firstnameLabel.setText("");
            lastnameLabel.setText("");
            usernameLabel.setText("*");
            pwdLabel.setText("*");
            cinLabel.setText("");
            confirmpwdLabel.setText("");
        } else if (lastnameField.getText().isBlank() && usernameField.getText().isBlank()) {
            peronalinformationsLabel.setText("");
            firstnameLabel.setText("");
            lastnameLabel.setText("*");
            usernameLabel.setText("*");
            cinLabel.setText("");
            pwdLabel.setText("");
            confirmpwdLabel.setText("");
        } else if (lastnameField.getText().isBlank() && pwdField.getText().isBlank()) {
            peronalinformationsLabel.setText("");
            firstnameLabel.setText("");
            lastnameLabel.setText("*");
            usernameLabel.setText("");
            cinLabel.setText("");
            pwdLabel.setText("*");
            confirmpwdLabel.setText("");
        } else if (firstnameField.getText().isBlank() && pwdField.getText().isBlank()) {
            peronalinformationsLabel.setText("");
            firstnameLabel.setText("*");
            lastnameLabel.setText("");
            usernameLabel.setText("");
            cinLabel.setText("");
            pwdLabel.setText("*");
            confirmpwdLabel.setText("");
        } else if (firstnameField.getText().isBlank() && usernameField.getText().isBlank()) {
            peronalinformationsLabel.setText("");
            firstnameLabel.setText("*");
            lastnameLabel.setText("");
            usernameLabel.setText("*");
            cinLabel.setText("");
            pwdLabel.setText("");
            confirmpwdLabel.setText("");
        } else if (firstnameField.getText().isBlank() && lastnameField.getText().isBlank()) {
            peronalinformationsLabel.setText("");
            firstnameLabel.setText("*");
            lastnameLabel.setText("*");
            usernameLabel.setText("");
            cinLabel.setText("");
            pwdLabel.setText("");
            confirmpwdLabel.setText("");
        } else if (firstnameField.getText().isBlank() && cinField.getText().isBlank()) {
            peronalinformationsLabel.setText("");
            firstnameLabel.setText("");
            lastnameLabel.setText("*");
            usernameLabel.setText("");
            cinLabel.setText("*");
            pwdLabel.setText("");
            confirmpwdLabel.setText("");
        } else if (lastnameField.getText().isBlank() && cinField.getText().isBlank()) {
            peronalinformationsLabel.setText("");
            firstnameLabel.setText("");
            lastnameLabel.setText("*");
            usernameLabel.setText("");
            cinLabel.setText("*");
            pwdLabel.setText("");
            confirmpwdLabel.setText("");
        } else if (usernameField.getText().isBlank() && cinField.getText().isBlank()) {
            peronalinformationsLabel.setText("");
            firstnameLabel.setText("");
            lastnameLabel.setText("");
            usernameLabel.setText("*");
            cinLabel.setText("*");
            pwdLabel.setText("");
            confirmpwdLabel.setText("");
        } else if (pwdField.getText().isBlank() && cinField.getText().isBlank()) {
            peronalinformationsLabel.setText("");
            firstnameLabel.setText("");
            lastnameLabel.setText("");
            usernameLabel.setText("");
            cinLabel.setText("*");
            pwdLabel.setText("*");
            confirmpwdLabel.setText("");
        } else if (firstnameField.getText().isBlank()) {
            peronalinformationsLabel.setText("");
            firstnameLabel.setText("*");
            lastnameLabel.setText("");
            usernameLabel.setText("");
            cinLabel.setText("");
            pwdLabel.setText("");
            confirmpwdLabel.setText("");
        } else if (lastnameField.getText().isBlank()) {
            peronalinformationsLabel.setText("");
            firstnameLabel.setText("");
            lastnameLabel.setText("*");
            usernameLabel.setText("");
            cinLabel.setText("");
            pwdLabel.setText("");
            confirmpwdLabel.setText("");
        } else if (usernameField.getText().isBlank()) {
            peronalinformationsLabel.setText("");
            firstnameLabel.setText("");
            lastnameLabel.setText("");
            usernameLabel.setText("*");
            cinLabel.setText("");
            pwdLabel.setText("");
            confirmpwdLabel.setText("");
        } else if (cinField.getText().isBlank()) {
            peronalinformationsLabel.setText("");
            firstnameLabel.setText("");
            lastnameLabel.setText("");
            usernameLabel.setText("");
            cinLabel.setText("*");
            pwdLabel.setText("");
            confirmpwdLabel.setText("");
        } else if (pwdField.getText().isBlank()) {
            peronalinformationsLabel.setText("");
            firstnameLabel.setText("");
            lastnameLabel.setText("");
            usernameLabel.setText("");
            cinLabel.setText("");
            pwdLabel.setText("*");
            confirmpwdLabel.setText("");
        } else if (pwdField.getText().equals(confirmpwdField.getText())) {
            registerAdmin();
            m.changeScene("/connection/admin/signin/siginadmin.fxml");
        }else {
            peronalinformationsLabel.setText("");
            firstnameLabel.setText("");
            lastnameLabel.setText("");
            usernameLabel.setText("");
            cinLabel.setText("");
            pwdLabel.setText("");
            confirmpwdLabel.setText("Password does not match");
        }

    }

    public void registerAdmin(){
        String firstnameFieldText = firstnameField.getText();
        String lastnameFieldText = lastnameField.getText();
        String usernameFieldText = usernameField.getText();
        String pwdFieldText = pwdField.getText();
        String cinFieldText = cinField.getText();

        String queryAdminField = "INSERT INTO admins (firstname, lastname, username, password, cin) VALUES ('";
        String queryAdminValue = firstnameFieldText + "','" + lastnameFieldText + "','" + usernameFieldText + "','" + pwdFieldText + "','" + cinFieldText +"')";
        String queryAdmin = queryAdminField + queryAdminValue;

        String queryUserField = "INSERT INTO users (firstname, lastname, username, password, role, cin) VALUES ('";
        String queryUserValue = firstnameFieldText + "','" + lastnameFieldText + "','" + usernameFieldText + "','" + pwdFieldText + "','" + "Admin" + "','" + cinFieldText +"')";
        String queryUser = queryUserField + queryUserValue;

        try {
            Statement statement = cnx.createStatement();
            statement.executeUpdate(queryAdmin);
            statement.executeUpdate(queryUser);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

}

