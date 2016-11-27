package ua.ksa.emulator_monitor;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import ua.ksa.emulator_monitor.controllers.Controller;
import ua.ksa.emulator_monitor.model.AgentDescriptor;


/**
 * Created by srg on 11/27/16.
 */
public class MainApplication extends Application {

    private ObservableList<AgentDescriptor> personData = FXCollections.observableArrayList();

    public static void main(String[] args) throws Exception {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(MainApplication.class.getResource("fxml/view.fxml"));

        Controller controller = loader.getController();
//        controller.

    }
}
