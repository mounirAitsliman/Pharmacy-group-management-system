package connection.admin.signin.home;

import com.jfoenix.controls.JFXButton;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
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

public class HomeController implements Initializable {

    public JFXButton button;
    public JFXButton profileButton;
    public JFXButton historyButton;
    @FXML
    private LineChart<?, ?> lineChart;
    public NumberAxis y;
    public CategoryAxis x;
    @FXML
    private Label customersLabel;
    @FXML
    private Label incomeLabel;
    @FXML
    private Label sellersLabel;

    ConnectionDB dbConnection = new ConnectionDB();
    Connection cnx = ConnectionDB.getCnx();
    Main m = new Main();
    public Label username;
    String total = null;

    public void initialize(URL url, ResourceBundle resourceBundle){
        displayUsername();
        chart();
        setSellersLabel();
        setIncomeLabel();
        setCustomerLabel();
    }

    public void setSellersLabel(){
        int count = 0;
        try{
            PreparedStatement statement = cnx.prepareStatement("SELECT COUNT(uid) from users");
            ResultSet resultSet = statement.executeQuery();

            while(resultSet.next()){
                count = resultSet.getInt("COUNT(uid)");
            }
            sellersLabel.setText(String.valueOf(count));
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public void setIncomeLabel(){
        try{
            float total = 0;
            PreparedStatement statement = cnx.prepareStatement("SELECT SUM(total) from customer");
            ResultSet resultSet = statement.executeQuery();

            while(resultSet.next()){
                total = resultSet.getInt("SUM(total)");
            }
            incomeLabel.setText(String.valueOf(total) + "DH");
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public void setCustomerLabel(){
        int count = 0;
        try{
            PreparedStatement statement = cnx.prepareStatement("SELECT COUNT(cid) from customer");
            ResultSet resultSet = statement.executeQuery();

            while(resultSet.next()){
                count = resultSet.getInt("COUNT(cid)");
            }
            customersLabel.setText(String.valueOf(count));
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void chart(){

        try{
            XYChart.Series chartData = new XYChart.Series();
            PreparedStatement statement = cnx.prepareStatement("SELECT date, SUM(total) FROM customer GROUP BY " +
                    "date ORDER BY TIMESTAMP(date) ASC LIMIT 8");

            ResultSet resultSet = statement.executeQuery();

            while(resultSet.next()){
                chartData.getData().add(new XYChart.Data(resultSet.getString(1), resultSet.getInt(2)));
            }

            lineChart.getData().add(chartData);

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @FXML
    void add(ActionEvent event) {
        m.changeScene("/connection/admin/signin/home/stock/stock.fxml");
    }

    @FXML
    void sell(ActionEvent event) {
        m.changeScene("/connection/admin/signin/home/sells/sell.fxml");
    }
    @FXML
    void getUsers(ActionEvent event){
        m.changeScene("/connection/admin/signin/home/users/users.fxml");
    }

    @FXML
    void signout(ActionEvent event) {
        m.changeScene("/connection/seller/signin/signin.fxml");
    }

    public void profile(ActionEvent actionEvent) {
        m.changeScene("/connection/admin/signin/home/profile/profile.fxml");
    }

    public void histrory(ActionEvent actionEvent) { m.changeScene("/connection/admin/signin/home/history/history.fxml");
    }

    public void displayUsername(){
        String user = getData.adminname;
        username.setText(user.substring(0,1).toUpperCase() + user.substring(1));
    }
}

