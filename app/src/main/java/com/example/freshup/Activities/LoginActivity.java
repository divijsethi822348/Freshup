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

import com.example.freshup.Common;
import com.example.freshup.Login_Logout;
import com.example.freshup.Models.GetProfilePojo;
import com.example.freshup.R;
import com.example.freshup.ViewModels.UserRegisterViewModel;

public class LoginActivity extends AppCompatActivity {
    Button signin;
    EditText email,password;
    private UserRegisterViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        email=findViewById(R.id.etemail);
        password=findViewById(R.id.etpassword);
        viewModel= ViewModelProviders.of(this).get(UserRegisterViewModel.class);
        signin=findViewById(R.id.signinbutton);
        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login();

            }
        });
    }

    private void login() {
        String Email=email.getText().toString();
        String Password=password.getText().toString();

        if (Email!="" && Password!=""){
            viewModel.login(LoginActivity.this,Email,Password,"Android","0").observe(LoginActivity.this, new Observer<GetProfilePojo>() {
                @Override
                public void onChanged(@Nullable GetProfilePojo getProfilePojo) {
                    Login_Logout.SaveToken(LoginActivity.this);
                    Common.SaveToken(LoginActivity.this,"ID",getProfilePojo.getDetails().getId().toString());
                    Intent intent=new Intent(LoginActivity.this,NavigatorActivity.class);
                    startActivity(intent);
                }
            });
        }
        else if(Email!="" || Password!=""){
            Toast.makeText(this, "You need to enter both email and password", Toast.LENGTH_SHORT).show();
        }

    }

    public void signup(View view) {
        Intent intent=new Intent(LoginActivity.this, SignUpActivity.class);
        startActivity(intent);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent=new Intent(LoginActivity.this,About.class);
        startActivity(intent);
    }
}
