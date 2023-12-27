package connection.seller.signup;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import main.Main;
import main.ConnectionDB;
import java.sql.Connection;
import java.sql.Statement;

public class SignupController {
    public TextField firstnameField;
    public TextField usernameField;
    public PasswordField pwdField;
    public PasswordField confirmpwdField;
    public Button registerButton;
    public Button closeButton;
    public Button backButton;
    public Label pwdLabel;
    public Label confirmpwdLabel;
    public Label usernameLabel;
    public Label lastnameLabel;
    public Label firstnameLabel;
    public TextField lastnameField;
    public Label peronalinformationsLabel;
    public Label cinLabel;
    public TextField cinField;


    ConnectionDB dbConnection = new ConnectionDB();
    Connection cnx = ConnectionDB.getCnx();
    Main m = new Main();

    public void signup(ActionEvent actionEvent) {
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
            registerSeller();
            m.changeScene("/connection/seller/signin/signin.fxml");
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

    public void registerSeller() {
        String firstnameFieldText = firstnameField.getText();
        String lastnameFieldText = lastnameField.getText();
        String usernameFieldText = usernameField.getText();
        String pwdFieldText = pwdField.getText();
        String cinFieldText = cinField.getText();

        String querySellerField = "INSERT INTO sellers (firstname, lastname, username, password, cin) VALUES ('";
        String querySellerValue = firstnameFieldText + "','" + lastnameFieldText + "','" + usernameFieldText + "','" + pwdFieldText + "','" + cinFieldText +"')";
        String querySeller = querySellerField + querySellerValue;

        String queryUserField = "INSERT INTO users (firstname, lastname, username, password, role, cin) VALUES ('";
        String queryUserValue = firstnameFieldText + "','" + lastnameFieldText + "','" + usernameFieldText + "','" + pwdFieldText + "','" + "Seller" + "','" + cinFieldText +"')";
        String queryUser = queryUserField + queryUserValue;

        try {
            Statement statement = cnx.createStatement();
            statement.executeUpdate(querySeller);
            statement.executeUpdate(queryUser);
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    public void close(ActionEvent actionEvent) {
        Stage stage = (Stage) closeButton.getScene().getWindow();
        stage.close();
    }

    public void back(ActionEvent actionEvent) {
        m.changeScene("/connection/seller/signin/signin.fxml");
    }
}
