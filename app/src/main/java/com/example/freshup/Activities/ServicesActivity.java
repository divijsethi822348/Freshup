package com.example.freshup.Activities;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.freshup.Adapters.ServicesTypeAdapter;
import com.example.freshup.Common;
import com.example.freshup.Models.GetServicesDataModel;
import com.example.freshup.R;
import com.example.freshup.ViewModels.ServicesViewModel;

import java.util.ArrayList;
import java.util.List;

public class ServicesActivity extends AppCompatActivity {
    ServicesViewModel  viewModel;
    RecyclerView services_type,services_expanded;
    List<String> list=new ArrayList<>();
    Button Continue;
    String service_id= "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_services);
        viewModel= ViewModelProviders.of(this).get(ServicesViewModel.class);
        services_type=findViewById(R.id.services_type);
        Continue=findViewById(R.id.continue_presentations);
        list.add("COUPES");
        list.add("COLORATION");
        services_type.setLayoutManager(new LinearLayoutManager(this));
        services_type.setAdapter(new ServicesTypeAdapter(this,list));
        Continue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(ServicesActivity.this,BookSlotActivity.class);
                startActivity(intent);
            }
        });
        service_id=Common.GetToken(this,"service id");
        Log.d("TAG", ""+service_id);
        viewModel.subServices(this,service_id).observe(this, new Observer<GetServicesDataModel>() {
            @Override
            public void onChanged(@Nullable GetServicesDataModel getServicesDataModel) {
                Toast.makeText(ServicesActivity.this, "Successfully fetched: "+getServicesDataModel.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });



    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent=new Intent(this,NavigatorActivity.class);
        startActivity(intent);
    }
}
