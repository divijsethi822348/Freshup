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

import com.example.freshup.Activities.JoinQueueActivity;
import com.example.freshup.Adapters.ServicesRecyclerAdapter;
import com.example.freshup.Models.GetHomeDataModel;
import com.example.freshup.R;
import com.example.freshup.Util.CommonUtils;
import com.example.freshup.ViewModels.ServicesViewModel;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class ServicesFragment extends Fragment {
    RecyclerView servicesRecycler;
    ServicesViewModel viewModel;
    List<GetHomeDataModel> list=new ArrayList<>();
    List<GetHomeDataModel.Detail> listmodel=new ArrayList<>();
    CardView join_queue;


    public ServicesFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_services, container, false);
        join_queue=view.findViewById(R.id.join_queue_card);
        viewModel= ViewModelProviders.of(this).get(ServicesViewModel.class);
        servicesRecycler=view.findViewById(R.id.services_recycler_view);
        servicesRecycler.setLayoutManager(new LinearLayoutManager(getContext()));

        viewModel.services(getActivity()).observe(getActivity(), new Observer<GetHomeDataModel>() {
            @Override
            public void onChanged(@Nullable GetHomeDataModel getHomeDataModel) {
                int size=getHomeDataModel.getDetails().size();
                for (int i=0;i<size;i++){
                    GetHomeDataModel model=new GetHomeDataModel();
                    GetHomeDataModel.Detail detail=new GetHomeDataModel.Detail();

                    detail.setTitle(getHomeDataModel.getDetails().get(i).getTitle());
                    detail.setImage1(getHomeDataModel.getDetails().get(i).getImage1());
                    detail.setImage2(getHomeDataModel.getDetails().get(i).getImage2());
                    detail.setId(getHomeDataModel.getDetails().get(i).getId());

                    listmodel.add(detail);
                    model.setDetails(listmodel);
                    list.add(model);
                    servicesRecycler.setAdapter(new ServicesRecyclerAdapter(getContext(),list));
                }


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
