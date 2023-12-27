package connection.admin.signin.home.profile;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import main.ConnectionDB;
import main.Main;
import main.getData;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;


public class ProfileController implements Initializable {


    public Label usernameinfoLabel;
    @FXML
    private Button backButton;
    @FXML
    private Label cinLabel;
    @FXML
    private Label firstnameLabel;
    @FXML
    private Label idLabel;
    @FXML
    private Label lastnameLabel;

    ConnectionDB dbConnection = new ConnectionDB();
    Connection cnx = ConnectionDB.getCnx();

    String user = getData.adminname;
    Main m = new Main();

    public void initialize(URL url, ResourceBundle resourceBundle){
        setFirstnameLabel();
        setLastnameLabel();
        setUsernameLabel();
        setIdLabel();
        setCinLabel();
    }
    @FXML
    void back(ActionEvent event) {
        m.changeScene("/connection/admin/signin/home/home.fxml");
    }


    public void setFirstnameLabel(){
        String firstname = null;
        try{
            PreparedStatement statement = cnx.prepareStatement("SELECT firstname FROM users WHERE username ='"+user+"'");
            ResultSet resultSet = statement.executeQuery();

            while(resultSet.next()){
                firstname = resultSet.getString("firstname");
            }
            firstnameLabel.setText(String.valueOf(firstname));
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public void setLastnameLabel(){
        String lastname = null;
        try{
            PreparedStatement statement = cnx.prepareStatement("SELECT lastname FROM users WHERE username ='"+user+"'");
            ResultSet resultSet = statement.executeQuery();

            while(resultSet.next()){
                lastname = resultSet.getString("lastname");
            }
            lastnameLabel.setText(String.valueOf(lastname));
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public void setIdLabel(){
        int uid = 0;
        try{
            PreparedStatement statement = cnx.prepareStatement("SELECT uid FROM users WHERE username ='"+user+"'");
            ResultSet resultSet = statement.executeQuery();

            while(resultSet.next()){
                uid = resultSet.getInt("uid");
            }
            idLabel.setText(String.valueOf(uid));
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public void setCinLabel(){
        String cin = null;
        try{
            PreparedStatement statement = cnx.prepareStatement("SELECT cin FROM users WHERE username ='"+user+"'");
            ResultSet resultSet = statement.executeQuery();

            while(resultSet.next()){
                cin = resultSet.getString("cin");
            }
            cinLabel.setText(String.valueOf(cin));
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public void setUsernameLabel(){
        String username = null;
        try{
            PreparedStatement statement = cnx.prepareStatement("SELECT username FROM users WHERE username ='"+user+"'");
            ResultSet resultSet = statement.executeQuery();

            while(resultSet.next()){
                username = resultSet.getString("username");
            }
            usernameinfoLabel.setText(String.valueOf(username));
        }catch (Exception e){
            e.printStackTrace();
        }
    }

}

