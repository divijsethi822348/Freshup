package com.example.freshup.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import com.example.freshup.Adapters.ServicesTypeAdapter;
import com.example.freshup.R;

import java.util.ArrayList;
import java.util.List;

public class PresentationsActivity extends AppCompatActivity {
    RecyclerView services_type,services_expanded;
    List<String> list=new ArrayList<>();
    Button Continue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_presentations);
        services_type=findViewById(R.id.services_type);
        Continue=findViewById(R.id.continue_presentations);
        list.add("COUPES");
        list.add("COLORATION");
        services_type.setLayoutManager(new LinearLayoutManager(this));
        services_type.setAdapter(new ServicesTypeAdapter(this,list));
        Continue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(PresentationsActivity.this,BookSlotActivity.class);
                startActivity(intent);
            }
        });


    }
}
