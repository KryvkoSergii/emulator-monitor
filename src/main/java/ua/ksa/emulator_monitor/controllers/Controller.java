package ua.ksa.emulator_monitor.controllers;

import javafx.application.Platform;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import ua.com.smiddle.cti.messages.model.messages.agent_events.AgentStates;
import ua.com.smiddle.emulator.AgentDescriptor;
import ua.ksa.emulator_monitor.MainApplication;
import ua.ksa.emulator_monitor.model.AgentDescriptorWrapper;
import ua.ksa.emulator_monitor.service.Receiver;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.IOException;
import java.net.Socket;
import java.time.format.DateTimeFormatter;
import java.util.Collection;
import java.util.Locale;

/**
 * Created by srg on 11/27/16.
 */
public class Controller implements PropertyChangeListener {
    @FXML
    TableView<AgentDescriptor> table;
    @FXML
    TableColumn<AgentDescriptorWrapper, String> agentId;
    @FXML
    TableColumn<AgentDescriptorWrapper, String> instrument;
    @FXML
    TableColumn<AgentDescriptorWrapper, AgentStates> state;
    @FXML
    Label agentNumber;
    @FXML
    Label remoteAddress;
    @FXML
    Label connectionStatus;
    @FXML
    Label lastUpdate;

    IntegerProperty agentNumberValue = new SimpleIntegerProperty();
    StringProperty remoteAddressValue = new SimpleStringProperty();
    StringProperty connectionStatusValue = new SimpleStringProperty();
    StringProperty lastUpdateValue = new SimpleStringProperty();

    private MainApplication application;
    private ObservableList<AgentDescriptor> data = FXCollections.observableArrayList();
    private Receiver receiver;
    private final static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MMM-yyyy HH:mm:ss.SSS", Locale.ENGLISH);


    //Constructors
    public Controller() {
        System.out.println("Controller started");
    }


    //Getters and setters
    public MainApplication getApplication() {
        return application;
    }

    public void setApplication(MainApplication application) {
        this.application = application;
    }


    //Methods
    @FXML
    void initialize() {
        // Инициализация таблицы адресатов с двумя столбцами.
        agentId.setCellValueFactory(cellData -> cellData.getValue().getAgentIdProperty());
        instrument.setCellValueFactory(cellData -> cellData.getValue().getInstrumentProperty());
        state.setCellValueFactory(cellData -> cellData.getValue().getStateProperty());
        agentNumber.textProperty().bind(agentNumberValue.asString());
        remoteAddress.textProperty().bind(remoteAddressValue);
        connectionStatus.textProperty().bind(connectionStatusValue);
        lastUpdate.textProperty().bind(lastUpdateValue);
        remoteAddressValue.setValue("...");
        connectionStatusValue.setValue("...");
        lastUpdateValue.setValue("...");

    }

    @FXML
    void connect() {
        try {
            Socket socket = new Socket("localhost", 10505);
            receiver = new Receiver(socket);
            receiver.addListener(this);
            receiver.start();
//            connectionStatusValue.setValue("waiting for incoming message...");
            table.setItems(data);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void disconnect() {
        receiver.interrupt();
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        switch (evt.getPropertyName()) {
            case "new_data": {
                Collection<AgentDescriptor> wrappers = (Collection<AgentDescriptor>) evt.getNewValue();
                System.out.println("wrappers=" + wrappers);
                if (wrappers != null) {
                    updating(wrappers);
//                    agentNumberValue.setValue(wrappers.size());
//                    remoteAddressValue.setValue(receiver.getSocket().getRemoteSocketAddress().toString());
//                    lastUpdateValue.setValue(LocalDateTime.now().format(formatter));
                }
                break;
            }
            case "system_event": {
                if (evt.getNewValue() != null)
                    connectionStatusValue.set(evt.getNewValue().toString());
                break;
            }
        }
    }

    private void updating(Collection<AgentDescriptor> agentDescriptors) {
        data.clear();
        agentDescriptors.stream().forEach(agentDescriptor ->
                data.add(new AgentDescriptorWrapper(agentDescriptor)));
        table.refresh();
    }

    @FXML
    void close() {
        Platform.exit();
    }
}
