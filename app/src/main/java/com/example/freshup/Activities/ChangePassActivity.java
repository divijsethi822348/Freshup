package com.example.freshup.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.freshup.R;

public class ChangePassActivity extends AppCompatActivity {
    Button change;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_pass);
        change=findViewById(R.id.change_pass);
        change.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(ChangePassActivity.this, "Pass changed!!", Toast.LENGTH_SHORT).show();
                Intent intent=new Intent(getApplicationContext(),NavigatorActivity.class);
                startActivity(intent);
            }
        });
    }
}
