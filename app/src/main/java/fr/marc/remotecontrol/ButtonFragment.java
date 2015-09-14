package fr.marc.remotecontrol;

import android.app.Activity;
import android.app.Fragment;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;


/**
 * A simple {@link Fragment} subclass.
 */
public class ButtonFragment extends Fragment implements View.OnClickListener {

    final static String ARG_SOCKET= "position";

    static Socket socket;
    static int SERVERPORT = 64696;
    static String SERVER_IP = "192.168.1.39";


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_buttons, container, false);

        // start Thread for client connection
        new Thread(new ClientThread()).start();

        // Declare actions taken on button click
        Button buttonCopy = (Button) rootView.findViewById(R.id.button_copy);
        buttonCopy.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Log.e("buttonApp", "onclick listener");
                try {
                    PrintWriter out = new PrintWriter(new BufferedWriter(
                            new OutputStreamWriter(socket.getOutputStream())), true);
                    out.println("[['ctrl','c']]");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        Button b = (Button) rootView.findViewById(R.id.button_copy);
        b.setOnClickListener(this);


        return rootView;
    }






    // Own classes
    class ClientThread implements Runnable {
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


    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button_copy:
                try {
                    PrintWriter out = new PrintWriter(new BufferedWriter(
                            new OutputStreamWriter(socket.getOutputStream())), true);
                    out.println("[['ctrl','v']]");
                } catch (Exception e) {e.printStackTrace();}
                break;

            case  R.id.button_paste :

        }
    }


    //
    // The buttons have been declared in the xml
    // Here are the actions corresponding to each of them
    //

    public void do_undo(View v){
        try {
            PrintWriter out = new PrintWriter(new BufferedWriter(
                    new OutputStreamWriter(socket.getOutputStream())), true);
            out.println("[['ctrl','z']]");
        } catch (Exception e) {e.printStackTrace();}
    }

    public void do_redo(View v){
        try {
            PrintWriter out = new PrintWriter(new BufferedWriter(
                    new OutputStreamWriter(socket.getOutputStream())), true);
            out.println("[['ctrl','y']]");
        } catch (Exception e) {e.printStackTrace();}
    }

}
