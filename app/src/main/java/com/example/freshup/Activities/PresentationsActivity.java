package com.example.freshup.Activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.freshup.Adapters.ServicesTypeAdapter;
import com.example.freshup.R;

import java.util.ArrayList;
import java.util.List;

public class PresentationsActivity extends AppCompatActivity {
    RecyclerView services_type,services_expanded;
    List<String> list=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_presentations);
        services_type=findViewById(R.id.services_type);
        list.add("Coupes");
        list.add("Coloration");
        services_type.setLayoutManager(new LinearLayoutManager(this));
        services_type.setAdapter(new ServicesTypeAdapter(this,list));


    }
}
