package main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;


public class Main extends Application{

    private static Stage stg;

    @Override
    public void start(Stage stage){
        try {
            stg = stage;
            Parent root = FXMLLoader.load(getClass().getResource("/connection/seller/signin/signin.fxml"));
            /*
            String javaVersion = System.getProperty("java.version");
            String javafxVersion = System.getProperty("javafx.version");
            Label l = new Label("Hello, JavaFX " + javafxVersion + ", running on Java " + javaVersion + ".");
            */
            stage.initStyle(StageStyle.UNDECORATED);
            stage.setTitle("Pharmacy management");
            stage.setScene(new Scene(root));
            stage.show();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void changeScene(String fxml) {

        try {
            Parent pane = FXMLLoader.load(getClass().getResource(fxml));
            stg.getScene().setRoot(pane);
        }catch (Exception e){
            e.printStackTrace();
        }

    }


    public static void main(String[] args) {
        launch();
    }

}
