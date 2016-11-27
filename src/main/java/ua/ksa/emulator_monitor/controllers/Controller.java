package ua.ksa.emulator_monitor.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import ua.com.smiddle.cti.messages.model.messages.agent_events.AgentStates;
import ua.ksa.emulator_monitor.MainApplication;
import ua.ksa.emulator_monitor.model.AgentDescriptor;
import ua.ksa.emulator_monitor.model.AgentDescriptorWrapper;

/**
 * Created by srg on 11/27/16.
 */
public class Controller {
    @FXML
    TableView<AgentDescriptor> table;
    @FXML
    TableColumn<AgentDescriptorWrapper, String> agentId;
    @FXML
    TableColumn<AgentDescriptorWrapper, String> instrument;
    @FXML
    TableColumn<AgentDescriptorWrapper, AgentStates> state;

    private MainApplication application;


    //Constructors
    public Controller() {
    }


    //Getters and setters
    public MainApplication getApplication() {
        return application;
    }

    public void setApplication(MainApplication application) {
        this.application = application;
        table.setItems(application.);
    }

    //Methods
    @FXML
    private void initialize() {
        // Инициализация таблицы адресатов с двумя столбцами.
        agentId.setCellValueFactory(cellData -> cellData.getValue().getAgentIdProperty());
        instrument.setCellValueFactory(cellData -> cellData.getValue().getInstrumentProperty());
        state.setCellValueFactory(cellData -> cellData.getValue().getStateProperty());
    }


}
