package ua.ksa.emulator_monitor.service;

import ua.ksa.emulator_monitor.model.AgentDescriptorWrapper;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.util.Collection;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * Created by srg on 11/27/16.
 */
public class Receiver extends Thread{
    private Socket socket;
    private BlockingQueue queue = new ArrayBlockingQueue(1);

    @Override
    public void run() {
        Collection<AgentDescriptorWrapper>  message = read();
    }

    private Collection<AgentDescriptorWrapper> read() {
        try {
            ObjectInputStream stream = new ObjectInputStream(socket.getInputStream());
            Collection<AgentDescriptorWrapper> wrapper;
            while (true) {
               return wrapper = (Collection<AgentDescriptorWrapper>) stream.readObject();
            }

        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

}
