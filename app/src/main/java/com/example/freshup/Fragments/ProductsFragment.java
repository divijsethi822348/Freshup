package com.example.freshup.Fragments;


import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.freshup.Activities.ProduitsCapilaire;
import com.example.freshup.Activities.SnekersActivity;
import com.example.freshup.Activities.VetementsActivity;
import com.example.freshup.Adapters.ProductsRecyclerAdapter;
import com.example.freshup.Common;
import com.example.freshup.Models.GetHomeDataModel;
import com.example.freshup.R;
import com.example.freshup.ViewModels.ProductsViewModel;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class ProductsFragment extends Fragment {
    RecyclerView recyclerView;
    ProductsViewModel viewModel;
    List<GetHomeDataModel> list=new ArrayList<>();
    List<GetHomeDataModel.Detail> listmodel=new ArrayList<>();


    public ProductsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_products, container, false);
        viewModel= ViewModelProviders.of(getActivity()).get(ProductsViewModel.class);
        recyclerView=view.findViewById(R.id.products_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        viewModel.products(getActivity()).observe(getActivity(), new Observer<GetHomeDataModel>() {
            @Override
            public void onChanged(@Nullable GetHomeDataModel getHomeDataModel) {
                int size=getHomeDataModel.getDetails().size();
                for (int i=0;i<size;i++){
                    Common.SaveToken(getActivity(),"productd id",getHomeDataModel.getDetails().get(i).getId());
                    GetHomeDataModel model=new GetHomeDataModel();
                    GetHomeDataModel.Detail detail=new GetHomeDataModel.Detail();
                    detail.setTitle(getHomeDataModel.getDetails().get(i).getTitle());
                    detail.setImage1(getHomeDataModel.getDetails().get(i).getImage1());
                    detail.setImage2(getHomeDataModel.getDetails().get(i).getImage2());

                    listmodel.add(detail);
                    model.setDetails(listmodel);
                    list.add(model);

                    recyclerView.setAdapter(new ProductsRecyclerAdapter(list,getContext()));

                }


            }
        });

        return view;
    }

}
