package com.example.freshup.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.example.freshup.R;

public class SettingsActivity extends AppCompatActivity {
    LinearLayout chngepswd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        chngepswd=findViewById(R.id.Change_Pswd);
        chngepswd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(),ChangePassActivity.class);
                startActivity(i);
            }
        });
    }

}
