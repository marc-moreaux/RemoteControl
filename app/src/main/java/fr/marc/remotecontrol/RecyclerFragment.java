package fr.marc.remotecontrol;

import android.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by marc on 08/10/15.
 */
public class RecyclerFragment extends Fragment {

    RecyclerView mRecyclerView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_button_recycler, container, false);

        mRecyclerView = (RecyclerView) rootView.findViewById(R.id.recyclerList);
        mRecyclerView.setAdapter(new ButtonRecyclerAdapter(getActivity()));
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));


        return rootView;
    }
}
