package connection.seller.signin.pin;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.stage.Stage;
import main.Main;

public class ConfirmAdminController {

    public Button confirmButton;
    public PasswordField pinField;
    @FXML
    private Button cancelButton;

    @FXML
    private Label invalidLabel;

    @FXML
    private Label pinLabel;

    Main m = new Main();

    @FXML
    void cancel(ActionEvent event) {
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
    }

    public void confirm(ActionEvent actionEvent) {
        checkAdmin();
    }

    public void checkAdmin(){
        try{
            if(pinField.getText().equals("0103")){
                m.changeScene("/connection/admin/signin/siginadmin.fxml");
                Stage stage = (Stage) confirmButton.getScene().getWindow();
                stage.close();
            }else if (pinField.getText().isBlank()){
                pinLabel.setText("Please enter your PIN");
                invalidLabel.setText("");
            } else {
                invalidLabel.setText("Invalid PIN");
                pinLabel.setText("");
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
