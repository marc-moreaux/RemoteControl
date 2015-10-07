package fr.marc.remotecontrol;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.RelativeLayout;

import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;

/**
 * Created by marc on 17/09/15.
 */
public class ButtonAdapter extends BaseAdapter {
    private Context mContext;
    public String[][] actions = {
            {"copy"  , "[['ctrl','c']]"},
            {"paste" , "[['ctrl','v']]"},
            {"undo"  , "[['ctrl','z']]"},
            {"redo"  , "[['ctrl','u']]"} };

    // Gets the context so it can be used later
    public ButtonAdapter(Context c) {
        mContext = c;
    }

    // Total number of things contained within the adapter
    public int getCount() {
        return actions.length;
    }

    // Require for structure, not really used in my code.
    public Object getItem(int position) {
        return null;
    }

    // Require for structure, not really used in my code. Can
    // be used to get the id of an item in the adapter for
    // manual control.
    public long getItemId(int position) {
        return position;
    }

    public View getView(int position, View convertView, ViewGroup parent) {


        // Get the button style
        RelativeLayout mView = (RelativeLayout)
                LayoutInflater.from(mContext).inflate(R.layout.fragment_buttons, null);

        Button mBtn = (Button) mView.findViewById(R.id.general_button);
        mView.removeView(mBtn);

        mBtn.setText(actions[position][0]);
        mBtn.setOnClickListener(new MyOnClickListener(actions[position][1]));
        mBtn.setId(position);



//        NOW USELESS BECAUSE OF LAYOUT INFLATION
//        float scale = mContext.getResources().getDisplayMetrics().density;
//        Button newBtn = new Button(mContext);
//        newBtn.setLayoutParams(new RelativeLayout.LayoutParams(
//                ActionBar.LayoutParams.WRAP_CONTENT,
//                ActionBar.LayoutParams.WRAP_CONTENT));
//
//        RelativeLayout.LayoutParams params = null;
//        params = (RelativeLayout.LayoutParams)newBtn.getLayoutParams();
//        if(params==null)
//            Log.e("remoteControlTag", "Error in null param for button");
//        params.setMargins(0, 5, 0, 0);
//        newBtn.setLayoutParams(params);
//
//        newBtn.setMinHeight((int) (100 * scale + .5f));
//        newBtn.setMinWidth((int) (100 * scale + .5f));
//        newBtn.setText(actions[position][0]);
//        newBtn.setOnClickListener(new MyOnClickListener(actions[position][1]));
//        newBtn.setId(position);


        return mBtn;
    }
}

class MyOnClickListener implements View.OnClickListener
{
    private final String action;
    SocketSingleton mSocketSingleton = SocketSingleton.getInstance();

    public MyOnClickListener(String action) {
        this.action = action;
    }

    public void onClick(View v) {
        Log.d("remoteControlTag", "sending : " + action);
        try {
            PrintWriter out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(
                    mSocketSingleton.getSocket().getOutputStream())), true);
            out.println(action);
        } catch (Exception e) {
            e.printStackTrace();
            Log.e("remoteControlTag", "ERROR: " +action+ " wasn't sent");
        }
    }
}



