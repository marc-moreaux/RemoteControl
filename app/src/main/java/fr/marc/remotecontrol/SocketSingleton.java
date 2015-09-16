package fr.marc.remotecontrol;

import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * Created by marc on 15/09/15.
 */
public class SocketSingleton {

    // Declare variables
    private static SocketSingleton obj = new SocketSingleton();
    private static boolean alreadyCalled = false;
    static String SERVER_IP = "192.168.1.39";
    static int SERVERPORT = 64696;
    static Socket socket;

    // Make constructor inaccessible
    private SocketSingleton(){}

    // Declare class access function
    public static SocketSingleton getInstance(){
        if(!alreadyCalled)
            new Thread(new ClientThread()).start();
        alreadyCalled = true;
        return obj;
    }

    // Declare my own functions
    private static class ClientThread implements Runnable {
        @Override
        public void run() {
            try {
                InetAddress serverAddr = InetAddress.getByName(SERVER_IP);
                socket = new Socket(serverAddr, SERVERPORT);
            } catch (UnknownHostException e1) {
                e1.printStackTrace();
            } catch (IOException e1) {
                e1.printStackTrace();
            }

        }
    }

    public static Socket getSocket(){
        return socket;
    }

}
