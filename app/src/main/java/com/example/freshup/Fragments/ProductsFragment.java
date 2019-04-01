package com.example.freshup.Fragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.freshup.Activities.ProduitsCapilaire;
import com.example.freshup.Activities.SnekersActivity;
import com.example.freshup.Activities.VetementsActivity;
import com.example.freshup.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class ProductsFragment extends Fragment {
    CardView vetements,snekers,produits_capilaire;


    public ProductsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_products, container, false);
        vetements=view.findViewById(R.id.vetements);
        snekers=view.findViewById(R.id.snekers);
        produits_capilaire=view.findViewById(R.id.produits_capilaire);
        vetements.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getActivity(), VetementsActivity.class);
                startActivity(intent);
            }
        });
        snekers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getActivity(), SnekersActivity.class);
                startActivity(intent);
            }
        });
        produits_capilaire.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getActivity(), ProduitsCapilaire.class);
                startActivity(intent);
            }
        });
        return view;
    }

}
