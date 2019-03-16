package com.example.freshup.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.freshup.R;

public class LoginActivity extends AppCompatActivity {
    Button signin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        signin=findViewById(R.id.signinbutton);
        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(LoginActivity.this,NavigatorActivity.class);
                startActivity(intent);

            }
        });
    }

    public void signup(View view) {
        Intent intent=new Intent(LoginActivity.this, SignUpActivity.class);
        startActivity(intent);
    }


}
