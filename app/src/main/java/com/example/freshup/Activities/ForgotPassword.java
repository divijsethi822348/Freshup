package com.example.freshup.Activities;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.freshup.R;
import com.example.freshup.ViewModels.UserRegisterViewModel;

import java.util.Map;

public class ForgotPassword extends AppCompatActivity {
    EditText email;
    Button done;
    String Email="";
    UserRegisterViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel= ViewModelProviders.of(this).get(UserRegisterViewModel.class);
        setContentView(R.layout.activity_forgot_password);
        email=findViewById(R.id.et_email);
        done=findViewById(R.id.change);
        done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Email=email.getText().toString();

                if (Email!=""){
                    forgot();
                }else {
                    Toast.makeText(ForgotPassword.this, "Enter Email", Toast.LENGTH_SHORT).show();
                }

            }
        });



    }

    private void forgot() {
        viewModel.forgot(ForgotPassword.this,Email).observe(ForgotPassword.this, new Observer<Map>() {
            @Override
            public void onChanged(@Nullable Map map) {
                Toast.makeText(ForgotPassword.this, "Your Password sent to your mail ", Toast.LENGTH_SHORT).show();
                Intent intent=new Intent(ForgotPassword.this,LoginActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent=new Intent(this,LoginActivity.class);
        startActivity(intent);
    }
}
