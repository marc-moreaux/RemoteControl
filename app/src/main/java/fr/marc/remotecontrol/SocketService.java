package fr.marc.remotecontrol;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;



// this class will  have to be converted into
// a singleton class
public class SocketService extends Service {

    // Variable declaration
    private final IBinder socketBinder = new LocalBinder();
    static Socket socket;
    static int SERVERPORT = 64696;
    static String SERVER_IP = "192.168.1.39";


    // Service functions
    public SocketService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        return socketBinder;
    }

    public class LocalBinder extends Binder{
        SocketService getService(){
            return SocketService.this;
        }
    }


    // My specific functions
    public void bindSocket(){
        // start Thread for client connection
        new Thread(new ClientThread()).start();


    }

    private class ClientThread implements Runnable {
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


}
