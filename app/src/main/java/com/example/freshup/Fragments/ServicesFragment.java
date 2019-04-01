package com.example.freshup.Fragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.freshup.Activities.JoinQueueActivity;
import com.example.freshup.Activities.PresentationsActivity;
import com.example.freshup.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class ServicesFragment extends Fragment {
    CardView presentations,join_queue;


    public ServicesFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_services, container, false);
        presentations=view.findViewById(R.id.presentations);
        join_queue=view.findViewById(R.id.join_queue);
        presentations.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getActivity(), PresentationsActivity.class);
                startActivity(intent);
            }
        });
        join_queue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getActivity(), JoinQueueActivity.class);
                startActivity(intent);
            }
        });
        return view;
    }

}
