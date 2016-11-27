package ua.ksa.emulator_monitor.model;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import ua.com.smiddle.cti.messages.model.messages.agent_events.AgentStates;

/**
 * Created by srg on 11/27/16.
 */
public class AgentDescriptorWrapper extends AgentDescriptor {
    private StringProperty agentIdProperty;
    private StringProperty instrumentProperty;
    private ObjectProperty<AgentStates> stateProperty;

    public AgentDescriptorWrapper() {
        this.agentIdProperty = new SimpleStringProperty(getAgentID());
        this.instrumentProperty = new SimpleStringProperty(getAgentInstrument());
        this.stateProperty = new SimpleObjectProperty<>(getState());
    }

    public AgentDescriptorWrapper(StringProperty agentIdProperty, StringProperty instrumentProperty, ObjectProperty<AgentStates> stateProperty) {
        this.agentIdProperty = agentIdProperty;
        this.instrumentProperty = instrumentProperty;
        this.stateProperty = stateProperty;
    }

    public StringProperty getAgentIdProperty() {
        return agentIdProperty;
    }

    public StringProperty agentIdPropertyProperty() {
        return agentIdProperty;
    }

    public void setAgentIdProperty(String agentIdProperty) {
        this.agentIdProperty.set(agentIdProperty);
    }

    public StringProperty getInstrumentProperty() {
        return instrumentProperty;
    }

    public StringProperty instrumentPropertyProperty() {
        return instrumentProperty;
    }

    public void setInstrumentProperty(String instrumentProperty) {
        this.instrumentProperty.set(instrumentProperty);
    }

    public ObjectProperty<AgentStates> getStateProperty() {
        return stateProperty;
    }

    public ObjectProperty<AgentStates> statePropertyProperty() {
        return stateProperty;
    }

    public void setStateProperty(AgentStates stateProperty) {
        this.stateProperty.set(stateProperty);
    }
}
