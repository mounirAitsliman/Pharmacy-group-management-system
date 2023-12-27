package connection.seller.signin;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import main.Main;
import main.ConnectionDB;
import main.getData;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class SigninController {

    public Button adminButton;
    @FXML
    private Button signinButton;

    @FXML
    private Button cancelButton;

    @FXML
    private Button signupButton;

    @FXML
    private PasswordField pwdField;

    @FXML
    public TextField usernameField;

    @FXML
    private Label wrongLabel;

    ConnectionDB dbConnection = new ConnectionDB();
    Connection cnx = ConnectionDB.getCnx();
    Main m = new Main();

    public void cancel(ActionEvent actionEvent) {
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
    }

    @FXML
    public void signin(ActionEvent event) {

        if(!usernameField.getText().isBlank() && !pwdField.getText().isBlank()){
            checkSignin();

        } else {
            wrongLabel.setText("Please enter your personal details");
        }
    }

    private void checkSignin(){

        try{

            Statement statement = cnx.createStatement();
            ResultSet results = statement.executeQuery("SELECT count(1) FROM sellers WHERE username = '" +
                    usernameField.getText()+"' AND password ='"+pwdField.getText()+"'");


            while (results.next()){
                getData.sellername = usernameField.getText();
                if(results.getInt(1) == 1){
                    m.changeScene("/connection/seller/signin/home/homeSeller.fxml");
                } else{
                    wrongLabel.setText("Invalid login. Please try again");
                }
            }

        }catch (Exception e){
            e.printStackTrace();
        }

    }


    public void signup(ActionEvent actionEvent) {
        m.changeScene("/connection/seller/signup/signup.fxml");
    }

    public void adminconfirm(ActionEvent actionEvent) {
        try{
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/connection/seller/signin/pin/confirmAdmin.fxml"));
            Parent root  = fxmlLoader.load();
            Stage stage = new Stage();
            stage.initStyle(StageStyle.UNDECORATED);
            stage.setScene(new Scene(root));
            stage.show();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
