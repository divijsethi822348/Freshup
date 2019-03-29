package com.example.freshup.Fragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.example.freshup.Activities.LoginActivity;
import com.example.freshup.Activities.NavigatorActivity;
import com.example.freshup.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class ProfileFragment extends Fragment {
    ImageView edit;
    RelativeLayout logout;
    EditText name,email,number;


    public ProfileFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_profile, container, false);
        edit=view.findViewById(R.id.edit);
        logout=view.findViewById(R.id.logout_button);
        name=view.findViewById(R.id.name_et);
        email=view.findViewById(R.id.emal_et);
        number=view.findViewById(R.id.number_et);


        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                name.setFocusable(true);
                email.setFocusable(true);
                number.setFocusable(true);
            }
        });
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(getActivity(),LoginActivity.class);
                startActivity(intent);
            }
        });


        return view;
    }

}
