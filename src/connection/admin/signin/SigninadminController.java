package connection.admin.signin;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import main.ConnectionDB;
import main.Main;
import main.getData;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class SigninadminController {

    @FXML
    private Button cancelButton;

    @FXML
    private PasswordField pwdField;

    @FXML
    private Button signinButton;

    @FXML
    private Button signupButton;

    @FXML
    private TextField usernameField;

    @FXML
    private Label wrongLabel;
    ConnectionDB dbConnection = new ConnectionDB();
    Connection cnx = ConnectionDB.getCnx();
    Main m = new Main();

    @FXML
    void cancel(ActionEvent event) {
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
    }

    @FXML
    void signin(ActionEvent event) {
        if(!usernameField.getText().isBlank() && !pwdField.getText().isBlank()){
            checkSignin();

        } else {
            wrongLabel.setText("Please enter your personal details");
        }
    }

    void checkSignin(){
        try{

            Statement statement = cnx.createStatement();
            ResultSet results = statement.executeQuery("SELECT count(1) FROM admins WHERE username = '" +
                    usernameField.getText()+"' AND password ='"+pwdField.getText()+"'");


            while (results.next()){
                getData.adminname = usernameField.getText();
                if(results.getInt(1) == 1){
                    m.changeScene("/connection/admin/signin/home/home.fxml");
                } else{
                    wrongLabel.setText("Invalid login. Please try again");
                }
            }

        }catch (Exception e){
            e.printStackTrace();
        }
    }
    @FXML
    void signup(ActionEvent event) {
        m.changeScene("/connection/admin/signup/signupadmin.fxml");
    }

}

