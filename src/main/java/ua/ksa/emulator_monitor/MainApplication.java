package ua.ksa.emulator_monitor;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


/**
 * Created by srg on 11/27/16.
 */
public class MainApplication extends Application {


    public static void main(String[] args) throws Exception {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        try {
            FXMLLoader loader = new FXMLLoader();
            Parent parent = loader.load(getClass().getResource("controllers/view.fxml"));
            Scene scene = new Scene(parent);
            primaryStage.setTitle("CTI EMULATOR MONITOR");
            primaryStage.setScene(scene);
            primaryStage.show();
            primaryStage.setOnCloseRequest(event -> {
                System.out.println("closing application");
                Platform.exit();
            });

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
