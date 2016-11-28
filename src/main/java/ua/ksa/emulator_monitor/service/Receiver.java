package ua.ksa.emulator_monitor.service;

import ua.ksa.emulator_monitor.model.AgentDescriptorWrapper;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.util.Collection;

/**
 * Created by srg on 11/27/16.
 */
public class Receiver extends Thread {
    private Socket socket;
    private PropertyChangeSupport pcs = new PropertyChangeSupport(this);
    private int exceptionCount = 0;


    //Constructor
    public Receiver(Socket socket) {
        this.socket = socket;
        System.out.println("Receiver created");
    }


    //Getters and setters
    public Socket getSocket() {
        return socket;
    }

    public void setSocket(Socket socket) {
        this.socket = socket;
    }


    //Methods
    @Override
    public void run() {
        try {
            Collection<AgentDescriptorWrapper> message;
            ObjectInputStream stream = new ObjectInputStream(socket.getInputStream());
            while (!isInterrupted() && exceptionCount <= 5) {
                System.out.println("connected");
                try {
                    message = read(stream);
                    System.out.println(message);
                    if (message != null) {
                        pcs.firePropertyChange("new_data", null, message);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    exceptionCount++;
                }
            }
            socket.close();
            pcs.firePropertyChange("system_event", null, "disconnected");
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("disconnected");
    }

    private Collection<AgentDescriptorWrapper> read(ObjectInputStream stream) throws IOException, ClassNotFoundException {
        while (true) {
            Object o = stream.readObject();
            return (Collection<AgentDescriptorWrapper>) o;
        }
    }

    public void addListener(PropertyChangeListener listener) {
        pcs.addPropertyChangeListener(listener);
    }

    private void removeListener(PropertyChangeListener listener) {
        pcs.removePropertyChangeListener(listener);

    }

}
