package connection.seller.signin.home.sells;

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

import javax.swing.*;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class SellersellsController implements Initializable {

    public Button backButton;
    public TextField midField;
    public TextField priceField;
    public ComboBox typeField;
    public TextField searchsellField;
    public TextField midsellField;
    public TextField qtysellField;
    public Label blankLabel;
    @FXML
    private TableColumn<Medicine, Integer> colID;

    @FXML
    private TableColumn<Medicine, Integer> colID1;

    @FXML
    private TableColumn<Medicine, String> colNAME;

    @FXML
    private TableColumn<Medicine, String> colNAME1;

    @FXML
    private TableColumn<Medicine, Float> colPRICE;

    @FXML
    private TableColumn<Medicine, Float> colPRICE1;

    @FXML
    private TableColumn<Medicine, Long> colQUANTITY;

    @FXML
    private TableColumn<Medicine, Long> colQUANTITY1;

    @FXML
    private TableColumn<Medicine, String> colTYPE;

    @FXML
    private TableColumn<Medicine, String> colTYPE1;

    @FXML
    private TextField nameField;

    @FXML
    private TextField qtyField;

    @FXML
    private TextField searchField;

    @FXML
    private TableView<Medicine> tableField;

    @FXML
    private TableView<Medicine> tablesellField;
    ObservableList<Medicine> dataList;
    Main m = new Main();
    int index = -1;
    int indexsell= -1;
    ConnectionDB dbConnection = new ConnectionDB();
    Connection cnx = ConnectionDB.getCnx();

    public void initialize(URL url, ResourceBundle resourceBundle){

        colID1.setCellValueFactory(new PropertyValueFactory<Medicine, Integer>("mid"));
        colNAME1.setCellValueFactory(new PropertyValueFactory<Medicine, String>("name"));
        colTYPE1.setCellValueFactory(new PropertyValueFactory<Medicine, String>("type"));
        colPRICE1.setCellValueFactory(new PropertyValueFactory<Medicine, Float>("price"));
        colQUANTITY1.setCellValueFactory(new PropertyValueFactory<Medicine, Long>("quantity"));

        loadTableMedicine();
        searchTable();
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
            Medicine medicine = new Medicine(Integer.parseInt(mid), name, type, Float.parseFloat(price), Long.parseLong(quantity));
            ObservableList<Medicine> medicines = tablesellField.getItems();
            medicines.add(medicine);
            tablesellField.setItems(medicines);

            updateadd();
        }
    }

    public void delete(ActionEvent actionEvent) {
        int selectedID = tablesellField.getSelectionModel().getSelectedIndex();
        tablesellField.getItems().remove(selectedID);

        updatedelete();
    }

    public void updateadd() {

        String mid = midField.getText();
        Long quantity = Long.parseLong(qtyField.getText());

        try
        {
            PreparedStatement statement = cnx.prepareStatement(" UPDATE stock SET quantity=quantity-'"+ quantity+"'WHERE mid ='" + mid+"'");
            statement.execute();

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

    void updatedelete(){

        String mid = midsellField.getText();
        Long quantity = Long.parseLong(qtysellField.getText());

        try
        {
            PreparedStatement statement = cnx.prepareStatement(" UPDATE stock SET quantity=quantity+'"+ quantity+"'WHERE mid ='" + mid+"'");
            statement.execute();

            midsellField.setText("");
            qtysellField.setText("");
            loadTableMedicine();
            searchTable();

        } catch (SQLException e)
        {
            e.printStackTrace();
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

    public void getsellSelected(MouseEvent mouseEvent) {
        indexsell = tablesellField.getSelectionModel().getSelectedIndex();
        if (indexsell <= -1){

            return;
        }
        midsellField.setText(colID1.getCellData(indexsell).toString());
        qtysellField.setText(colQUANTITY1.getCellData(indexsell).toString());
    }

    public void back(ActionEvent actionEvent) {
        m.changeScene("/connection/seller/signin/home/homeSeller.fxml");
    }

    @FXML
    void imprimer(ActionEvent event) {
        Medicine medicine = new Medicine();
        List<List<String>> arrList = new ArrayList<>();
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime now  = LocalDateTime.now();

        for (int i = 0; i< tablesellField.getItems().size(); i++){
            medicine = tablesellField.getItems().get(i);
            arrList.add(new ArrayList<>());
            arrList.get(i).add(Integer.toString(medicine.getMid()));
            arrList.get(i).add(medicine.getName());
            arrList.get(i).add(medicine.getType());
            arrList.get(i).add(Float.toString(medicine.getPrice()));
            arrList.get(i).add(Long.toString(medicine.getQuantity()));
        }

        try {

            PreparedStatement statement = cnx.prepareStatement("INSERT INTO customer (cid, name, type," +
                    " price, quantity, total, date) VALUES(?,?,?,?,?,price*quantity,?);");


            for(int i=0;i<arrList.size();i++) {

                statement.setString(1, arrList.get(i).get(0));
                statement.setString(2, arrList.get(i).get(1));
                statement.setString(3, arrList.get(i).get(2));
                statement.setString(4, arrList.get(i).get(3));
                statement.setString(5, arrList.get(i).get(4));
                statement.setString(6, String.valueOf(now));

                statement.executeUpdate();
            }

            JOptionPane.showMessageDialog(null, "Successful !");

            loadTableMedicine();
            searchTable();

        }catch (Exception e) {
            e.printStackTrace();
        }
    }
}
