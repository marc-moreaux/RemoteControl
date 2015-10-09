package fr.marc.remotecontrol;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

/**
 * Created by marc on 08/10/15.
 */
public class ButtonRecyclerAdapter extends RecyclerView.Adapter<ButtonRecyclerAdapter.MViewHolder> {

    // Variables
    private LayoutInflater inflater;
    public String[][] data = {
            {"copy"  , "[['ctrl','c']]"},
            {"paste" , "[['ctrl','v']]"},
            {"undo"  , "[['ctrl','z']]"},
            {"redo"  , "[['ctrl','u']]"} };

    // Constructor
    public ButtonRecyclerAdapter(Context context){
        inflater = LayoutInflater.from(context);
    }



    @Override
    public MViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View ButtonView = inflater.inflate(R.layout.recycler_single_button, parent, false);
        MViewHolder holder = new MViewHolder(ButtonView);
        return holder;
    }

    @Override
    public void onBindViewHolder(MViewHolder holder, int position) {
        holder.mBtn.setText(data[position][0]);
        holder.mBtn.setOnClickListener(new MyOnClickListener(data[position][1]));
        holder.mBtn.setId(position);
    }

    @Override
    public int getItemCount() {
        return data.length;
    }

    class MViewHolder extends RecyclerView.ViewHolder {
        Button mBtn;
        public MViewHolder(View itemView) {
            super(itemView);
            mBtn = (Button) itemView.findViewById(R.id.general_button2);
        }
    }
}
