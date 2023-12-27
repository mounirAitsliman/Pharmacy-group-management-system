package main;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import main.Medicine;

import java.sql.*;

public class ConnectionDB {

    public static Connection getCnx() {

        final String url = "jdbc:mysql://localhost:3306/pharmacy";
        final String username = "root";
        final String password = "7F9K1N3O";
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection cnx = DriverManager.getConnection(url, username, password);
            return cnx;

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }

    }

    public static ObservableList<Medicine> getDataMedicine(){

        Connection conn = getCnx();
        ObservableList<Medicine> list = FXCollections.observableArrayList();
        try {
            PreparedStatement statement = conn.prepareStatement("SELECT * FROM stock");
            ResultSet results = statement.executeQuery();

            while (results.next()){
                list.add(new Medicine(Integer.parseInt(results.getString("mid"))
                        , results.getString("name"), results.getString("type")
                        , Float.parseFloat(results.getString("price"))
                        , Long.parseLong(results.getString("quantity"))));
            }

        }catch (Exception e){
            e.printStackTrace();
        }

        return list;
    }

    public static ObservableList<Users> getDataUsers() {

        Connection conn = getCnx();
        ObservableList<Users> list = FXCollections.observableArrayList();
        try {
            PreparedStatement statement = conn.prepareStatement("SELECT * FROM users");
            ResultSet results = statement.executeQuery();

            while (results.next()){
                list.add(new Users(Integer.parseInt(results.getString("uid"))
                        , results.getString("firstname"), results.getString("lastname")
                        , results.getString("username"), results.getString("password")
                        , results.getString("cin"), results.getString("role")));
            }

        }catch (Exception e){
            e.printStackTrace();
        }

        return list;

    }
    public static ObservableList<Customer> getDataCustomers() {

        Connection conn = getCnx();
        ObservableList<Customer> list = FXCollections.observableArrayList();
        try {
            PreparedStatement statement = conn.prepareStatement("SELECT * FROM customer");
            ResultSet results = statement.executeQuery();

            while (results.next()){
                list.add(new Customer(Integer.parseInt(results.getString("cid"))
                        , results.getString("name"), results.getString("type")
                        , Float.parseFloat(results.getString("price"))
                        , Long.parseLong(results.getString("quantity"))
                        , Float.parseFloat(results.getString("total"))
                        , results.getString("date")));
            }

        }catch (Exception e){
            e.printStackTrace();
        }

        return list;

    }

}
