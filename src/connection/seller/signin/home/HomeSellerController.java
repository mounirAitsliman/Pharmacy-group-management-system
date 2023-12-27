package connection.seller.signin.home;

import com.jfoenix.controls.JFXButton;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import main.Main;
import main.getData;

import java.net.URL;
import java.util.ResourceBundle;

public class HomeSellerController implements Initializable {

    public Label username;
    @FXML
    private FontAwesomeIcon SIGN;

    @FXML
    private JFXButton addButton;

    @FXML
    private JFXButton profileButton;

    @FXML
    private JFXButton sellButton;

    @FXML
    private Button signoutButton;

    Main m = new Main();

    public void initialize(URL url, ResourceBundle resourceBundle){
        displayUsername();
    }
    @FXML
    void add(ActionEvent event) {
        m.changeScene("/connection/seller/signin/home/stock/stockSeller.fxml");
    }

    @FXML
    void profile(ActionEvent event) {
        m.changeScene("/connection/seller/signin/home/profile/profileseller.fxml");
    }

    @FXML
    void sell(ActionEvent event) {
        m.changeScene("/connection/seller/signin/home/sells/sellersells.fxml");
    }

    public void displayUsername(){
        String user = getData.sellername;
        username.setText(user.substring(0,1).toUpperCase() + user.substring(1));
    }

    @FXML
    void signout(ActionEvent event) {
        m.changeScene("/connection/seller/signin/signin.fxml");
    }

}

