package fr.marc.remotecontrol;

import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;


/**
 * A simple {@link Fragment} subclass.
 */
public class ButtonFragment extends Fragment implements View.OnClickListener {

    final static String ARG_SOCKET= "position";
    Socket socket;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_buttons, container, false);

        // Accessor to socket
        SocketSingleton mSocketSingleton = SocketSingleton.getInstance();
        socket = mSocketSingleton.getSocket();

        // set click listener for buttons
        rootView.findViewById(R.id.button_copy ).setOnClickListener(this);
        rootView.findViewById(R.id.button_paste).setOnClickListener(this);
        rootView.findViewById(R.id.button_undo ).setOnClickListener(this);
        rootView.findViewById(R.id.button_redo2).setOnClickListener(this);


        return rootView;
    }






    // Own classes
    public void onClick(View v) {
        int triggeredId = v.getId();


        if (triggeredId == R.id.button_copy)
            sendKey("[['ctrl','c']]");
        else if (triggeredId == R.id.button_paste)
            sendKey("[['ctrl','v']]");
        else if (triggeredId == R.id.button_undo)
            sendKey("[['ctrl','z']]");
        else if (triggeredId == R.id.button_redo2)
            sendKey("[['ctrl','u']]");

    }

    private boolean sendKey(String keys){
        Log.d("MyTag", "sending : "+keys);
        try {
            PrintWriter out = new PrintWriter(new BufferedWriter(
                    new OutputStreamWriter(socket.getOutputStream())), true);
            out.println(keys);
        } catch (Exception e) {
            e.printStackTrace();
            Log.e("MyTag", "ERROR: " + keys+ " wasn't sent");
            return false;
        }
        return true;
    }

}
