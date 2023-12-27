package connection.admin.signin.home.stock;

import main.Medicine;
import main.ConnectionDB;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import main.Main;

import javax.swing.*;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

public class StockController implements Initializable {

    public Label blankLabel;
    ConnectionDB dbConnection = new ConnectionDB();
    Connection cnx = ConnectionDB.getCnx();
    int index = -1;
    public TextField midField;
    public TextField nameField;
    public TextField priceField;
    public TextField qtyField;
    public TextField searchField;
    public ComboBox<String> typeField;
    public TableView<Medicine> tableField;
    public TableColumn<Medicine, Integer>  colID;
    public TableColumn<Medicine, String> colNAME;
    public TableColumn<Medicine, String> colTYPE;
    public TableColumn<Medicine, Long> colQUANTITY;
    public TableColumn<Medicine, Float> colPRICE;

    private Label nameLabel;
    ObservableList<Medicine> dataList;


    public void initialize(URL url, ResourceBundle resourceBundle){
        typeField.setItems(FXCollections.observableArrayList("Antalgique", "Anti-inflammatoire", "Anrti-rhume"
                , "Vitamine", "Antibiotique", "Médicament de la thyroide"));


        loadTableMedicine();
        searchTable();
    }

    public void add(ActionEvent actionEvent) {

        String mid = midField.getText();
        String name = nameField.getText();
        String type = typeField.getSelectionModel().getSelectedItem().toString();
        String price = priceField.getText();
        String quantity = qtyField.getText();

        if (mid.isEmpty() || name.isEmpty() || type == null || price.isEmpty() || quantity.isEmpty()){
            blankLabel.setText("Please fill all the blank fields");
        }else {
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

                        }catch (Exception e){
                            e.printStackTrace();
                        }
                    }
                }

            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    public void delete(ActionEvent actionEvent) {

        String mid = midField.getText();
        String name = nameField.getText();
        String type = typeField.getSelectionModel().getSelectedItem().toString();
        String price = priceField.getText();
        String quantity = qtyField.getText();

        if (mid.isEmpty() || name.isEmpty() || type == null || price.isEmpty() || quantity.isEmpty()){
            blankLabel.setText("Please fill all the blank fields");
        }else {
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

    public void update(ActionEvent actionEvent) {

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
    @FXML
    void getSelected(javafx.scene.input.MouseEvent event){
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


    public void back(ActionEvent actionEvent) {
        Main m = new Main();
        m.changeScene("/connection/admin/signin/home/home.fxml");
    }

}
