package ua.ksa.emulator_monitor.service;

import ua.ksa.emulator_monitor.model.AgentDescriptorWrapper;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;

/**
 * Created by srg on 11/27/16.
 */
public class Receiver {
    private Socket socket;

    private void read() {
        try {
            ObjectInputStream stream = new ObjectInputStream(socket.getInputStream());
            AgentDescriptorWrapper wrapper;
            while (true) {
                wrapper = (AgentDescriptorWrapper) stream.readObject();
            }

        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

}
