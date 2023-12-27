package connection.seller.signin.home.stock;

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
import main.Medicine;
import main.getData;

import javax.swing.*;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

public class StockSellerController implements Initializable {

    public Label blankLabel;
    @FXML
    private Button backButton;

    @FXML
    private TableColumn<Medicine, Integer> colID;

    @FXML
    private TableColumn<Medicine, String> colNAME;

    @FXML
    private TableColumn<Medicine, Float> colPRICE;

    @FXML
    private TableColumn<Medicine, Long> colQUANTITY;

    @FXML
    private TableColumn<Medicine, String> colTYPE;

    @FXML
    private TextField midField;

    @FXML
    private TextField nameField;

    @FXML
    private TextField priceField;

    @FXML
    private TextField qtyField;

    @FXML
    private TextField searchField;

    @FXML
    private TableView<Medicine> tableField;

    @FXML
    private ComboBox<String> typeField;

    ConnectionDB dbConnection = new ConnectionDB();
    Connection cnx = ConnectionDB.getCnx();
    int index = -1;
    ObservableList<Medicine> dataList;
    Main m = new Main();

    public void initialize(URL url, ResourceBundle resourceBundle){
        typeField.setItems(FXCollections.observableArrayList("Antalgique", "Anti-inflammatoire", "Anrti-rhume"
                , "Vitamine", "Antibiotique", "Médicament de la thyroide"));


        loadTableMedicine();
        searchTable();
    }
    @FXML
    void add(ActionEvent event) {
        String mid = midField.getText();
        String name = nameField.getText();
        String type = typeField.getSelectionModel().getSelectedItem().toString();
        String price = priceField.getText();
        String quantity = qtyField.getText();

        if (mid.isEmpty() || name.isEmpty() || type == null || price.isEmpty() || quantity.isEmpty()){
            blankLabel.setText("Please fill all the blank fields");
        } else {
            try{

                Statement selectstatement = cnx.createStatement();
                ResultSet results = selectstatement.executeQuery("SELECT count(1) FROM stock WHERE name = '" +
                        nameField.getText()+"'");

                while (results.next()){
                    if(results.getInt(1) == 1){

                        try
                        {
                            PreparedStatement updatestatement = cnx.prepareStatement("UPDATE stock set quantity = " +
                                    "quantity +'"+quantity+"' where name = '"+name+"'");
                            updatestatement.execute();

                            JOptionPane.showMessageDialog(null, "Product was addded");

                            midField.setText("");
                            nameField.setText("");
                            priceField.setText("");
                            qtyField.setText("");
                            loadTableMedicine();
                            searchTable();

                        } catch (SQLException e)
                        {
                            e.printStackTrace();
                        }

                    } else{

                        try {

                            PreparedStatement insertstatement = cnx.prepareStatement("INSERT INTO stock(mid, name, type, price, quantity)"
                                    + "VALUES(?,?,?,?,?);");

                            insertstatement.setString(1, mid);
                            insertstatement.setString(2, name);
                            insertstatement.setString(3, type);
                            insertstatement.setString(4, price);
                            insertstatement.setString(5, quantity);
                            insertstatement.executeUpdate();

                            JOptionPane.showMessageDialog(null, "Product was added successfully");

                            midField.setText("");
                            nameField.setText("");
                            priceField.setText("");
                            qtyField.setText("");
                            loadTableMedicine();
                            searchTable();

                        } catch (Exception e){
                            e.printStackTrace();
                        }
                    }
                }

            } catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    @FXML
    void back(ActionEvent event) {
        m.changeScene("/connection/seller/signin/home/homeSeller.fxml");
    }

    @FXML
    void delete(ActionEvent event) {
        String mid = midField.getText();
        String name = nameField.getText();
        String type = typeField.getSelectionModel().getSelectedItem().toString();
        String price = priceField.getText();
        String quantity = qtyField.getText();

        if (mid.isEmpty() || name.isEmpty() || type == null || price.isEmpty() || quantity.isEmpty()){
            blankLabel.setText("Please fill all the blank fields");
        } else {
            try {

                PreparedStatement statement = cnx.prepareStatement("DELETE FROM stock WHERE mid = ?");
                statement.setString(1, mid);
                statement.executeUpdate();

                JOptionPane.showMessageDialog(null, "Product was deleted");

                midField.setText("");
                nameField.setText("");
                priceField.setText("");
                qtyField.setText("");
                loadTableMedicine();
                searchTable();

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
        midField.setText(colID.getCellData(index).toString());
        nameField.setText(colNAME.getCellData(index).toString());
        typeField.setValue(colTYPE.getCellData(index).toString());
        priceField.setText(colPRICE.getCellData(index).toString());
        qtyField.setText(colQUANTITY.getCellData(index).toString());
    }

    @FXML
    void update(ActionEvent event) {
        String mid = midField.getText();
        String name = nameField.getText();
        String type = typeField.getSelectionModel().getSelectedItem().toString();
        String price = priceField.getText();
        String quantity = qtyField.getText();

        if (mid.isEmpty() || name.isEmpty() || type == null || price.isEmpty() || quantity.isEmpty()){
            blankLabel.setText("Please fill all the blank fields");
        } else {
            try
            {
                PreparedStatement statement = cnx.prepareStatement("UPDATE stock set name = '"+name+"'" +
                        ", type = '"+type+"', price = '"+price+"', quantity= '"+quantity+"' where mid = '"+mid+"'");
                statement.execute();

                JOptionPane.showMessageDialog(null, "Product was updated");

                midField.setText("");
                nameField.setText("");
                priceField.setText("");
                qtyField.setText("");
                loadTableMedicine();
                searchTable();

            } catch (SQLException e)
            {
                e.printStackTrace();
            }
        }
    }

    void loadTableMedicine(){

        colID.setCellValueFactory(new PropertyValueFactory<Medicine, Integer>("mid"));
        colNAME.setCellValueFactory(new PropertyValueFactory<Medicine, String>("name"));
        colTYPE.setCellValueFactory(new PropertyValueFactory<Medicine, String>("type"));
        colPRICE.setCellValueFactory(new PropertyValueFactory<Medicine, Float>("price"));
        colQUANTITY.setCellValueFactory(new PropertyValueFactory<Medicine, Long>("quantity"));

        tableField.setItems(ConnectionDB.getDataMedicine());

    }

    void searchTable(){

        colID.setCellValueFactory(new PropertyValueFactory<Medicine, Integer>("mid"));
        colNAME.setCellValueFactory(new PropertyValueFactory<Medicine, String>("name"));
        colTYPE.setCellValueFactory(new PropertyValueFactory<Medicine, String>("type"));
        colPRICE.setCellValueFactory(new PropertyValueFactory<Medicine, Float>("price"));
        colQUANTITY.setCellValueFactory(new PropertyValueFactory<Medicine, Long>("quantity"));

        dataList = ConnectionDB.getDataMedicine();
        tableField.setItems(dataList);

        FilteredList<Medicine> filteredData = new FilteredList<>(dataList, b -> true);
        searchField.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(medicine -> {

                if (newValue == null || newValue.isEmpty()){
                    return true;    //If filter search is empty, display all products
                }
                String searchKeyword = newValue.toLowerCase();

                if(medicine.getName().toLowerCase().indexOf(searchKeyword) != -1){
                    return true;    //Filter matches name
                }else if (medicine.getType().toLowerCase().indexOf(searchKeyword) != -1){
                    return true; // Filter matches type
                } else if (String.valueOf(medicine.getMid()).indexOf(searchKeyword) != -1) {
                    return true;
                } else {
                    return false; // Does not match
                }
            });

        });

        SortedList<Medicine> sortedData = new SortedList<>(filteredData);
        sortedData.comparatorProperty().bind(tableField.comparatorProperty());
        tableField.setItems(sortedData);
    }

}

