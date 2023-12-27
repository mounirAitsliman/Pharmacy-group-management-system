package connection.admin.signin.home.history;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import main.ConnectionDB;
import main.Customer;
import main.Main;
import main.Medicine;

import java.net.URL;
import java.util.ResourceBundle;

public class HistoryController implements Initializable {

    public TableColumn<Customer, String> colDATE;
    public TableColumn<Customer, Float> colTOTAL;
    @FXML
    private TableColumn<Customer, Integer> colID;

    @FXML
    private TableColumn<Customer, String> colNAME;

    @FXML
    private TableColumn<Customer, Float> colPRICE;

    @FXML
    private TableColumn<Customer, Long> colQUANTITY;

    @FXML
    private TableColumn<Customer, String> colTYPE;


    @FXML
    private TextField searchField;

    @FXML
    private TableView<Customer> tableField;

    ObservableList<Customer> dataList;
    Main m = new Main();

    public void initialize(URL url, ResourceBundle resourceBundle){

        loadTableCustomer();
        searchTable();
    }
    @FXML
    void getSelected(MouseEvent event) {
    }

    void loadTableCustomer(){

        colID.setCellValueFactory(new PropertyValueFactory<Customer, Integer>("cid"));
        colNAME.setCellValueFactory(new PropertyValueFactory<Customer, String>("name"));
        colTYPE.setCellValueFactory(new PropertyValueFactory<Customer, String>("type"));
        colPRICE.setCellValueFactory(new PropertyValueFactory<Customer, Float>("price"));
        colQUANTITY.setCellValueFactory(new PropertyValueFactory<Customer, Long>("quantity"));
        colTOTAL.setCellValueFactory(new PropertyValueFactory<Customer, Float>("total"));
        colDATE.setCellValueFactory(new PropertyValueFactory<Customer, String>("date"));

        tableField.setItems(ConnectionDB.getDataCustomers());

    }

    public void searchTable() {

        colID.setCellValueFactory(new PropertyValueFactory<Customer, Integer>("cid"));
        colNAME.setCellValueFactory(new PropertyValueFactory<Customer, String>("name"));
        colTYPE.setCellValueFactory(new PropertyValueFactory<Customer, String>("type"));
        colPRICE.setCellValueFactory(new PropertyValueFactory<Customer, Float>("price"));
        colQUANTITY.setCellValueFactory(new PropertyValueFactory<Customer, Long>("quantity"));
        colTOTAL.setCellValueFactory(new PropertyValueFactory<Customer, Float>("total"));
        colDATE.setCellValueFactory(new PropertyValueFactory<Customer, String>("date"));

        dataList = ConnectionDB.getDataCustomers();
        tableField.setItems(dataList);

        FilteredList<Customer> filteredData = new FilteredList<>(dataList, b -> true);
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
                } else if (medicine.getDate().toLowerCase().indexOf(searchKeyword) != -1) {
                    return true; //Filter matches date
                } else if (String.valueOf(medicine.getCid()).indexOf(searchKeyword) != -1) {
                    return true;
                } else {
                    return false; // Does not match
                }
            });

        });

        SortedList<Customer> sortedData = new SortedList<>(filteredData);
        sortedData.comparatorProperty().bind(tableField.comparatorProperty());
        tableField.setItems(sortedData);
    }

    public void back(ActionEvent actionEvent) {
        m.changeScene("/connection/admin/signin/home/home.fxml");
    }
}

