package com.example.freshup.Fragments;


import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;


import com.example.freshup.Activities.NavigatorActivity;
import com.example.freshup.Activities.Profile;
import com.example.freshup.Activities.SettingsActivity;
import com.example.freshup.R;


public class HomeFragment extends Fragment {
    Button services,products;
    int color = Color.WHITE;
    int color2=Color.BLACK;
    ImageView profile,settings;


    public HomeFragment() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        SelectFragment(new ServicesFragment());
        services=view.findViewById(R.id.services_button);
        products=view.findViewById(R.id.products_button);
        profile=view.findViewById(R.id.profile_edit);
        settings=view.findViewById(R.id.settings);
        services.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                products.setBackgroundColor(color2);
                products.setTextColor(color);
                services.setBackgroundColor(color);
                services.setTextColor(color2);
                SelectFragment(new ServicesFragment());
            }
        });

        products.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                services.setTextColor(color);
                services.setBackgroundColor(color2);
                products.setTextColor(color2);
                products.setBackgroundColor(color);

                SelectFragment(new ProductsFragment());

            }
        });

        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getActivity(), Profile.class);
                startActivity(intent);
            }
        });

        settings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getActivity(), SettingsActivity.class);
                startActivity(intent);
            }
        });

        return view;


    }
    private void SelectFragment(Fragment fragment) {
        FragmentManager fragmentManager=getActivity().getSupportFragmentManager();
        FragmentTransaction transaction=fragmentManager.beginTransaction();
        transaction.replace(R.id.serv_prod_frag,fragment);
        transaction.commit();

    }
}
