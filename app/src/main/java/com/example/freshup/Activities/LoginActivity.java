package com.example.freshup.Activities;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.freshup.Common;
import com.example.freshup.Login_Logout;
import com.example.freshup.Models.GetProfilePojo;
import com.example.freshup.R;
import com.example.freshup.ViewModels.UserRegisterViewModel;

public class LoginActivity extends AppCompatActivity {
    Button signin;
    EditText email, password;
    TextView forgot;
    String Email = "", Password = "";
    private UserRegisterViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        email = findViewById(R.id.etemail);
        password = findViewById(R.id.etpassword);
        forgot = findViewById(R.id.forgot_password);
        viewModel = ViewModelProviders.of(this).get(UserRegisterViewModel.class);
        signin = findViewById(R.id.signinbutton);
        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login();

            }
        });
        forgot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, ForgotPassword.class);
                startActivity(intent);
            }
        });
    }

    private void login() {
        Email = email.getText().toString();
        Password = password.getText().toString();

        if (Email.isEmpty()){
            email.setError("Enter Email");
        }else if(Password.isEmpty()){
            password.setError("Enter Password");
        }else {
            signin();
        }

    }

    private void signin() {

        viewModel.login(LoginActivity.this, Email, Password, "Android", "0").observe(LoginActivity.this, new Observer<GetProfilePojo>() {
            @Override
            public void onChanged(@Nullable GetProfilePojo getProfilePojo) {

                if (getProfilePojo.getSuccess().equalsIgnoreCase("1")) {
                    Login_Logout.SaveToken(LoginActivity.this);
                    Common.SaveToken(LoginActivity.this, "ID", getProfilePojo.getDetails().getId().toString());
                    Intent intent = new Intent(LoginActivity.this, NavigatorActivity.class);
                    startActivity(intent);
                } else if (getProfilePojo.getSuccess().equalsIgnoreCase("0")) {
                    Toast.makeText(LoginActivity.this, "Please Enter Valid Credentials", Toast.LENGTH_SHORT).show();
                } else if (getProfilePojo.getSuccess().equalsIgnoreCase("2")) {
                    Toast.makeText(LoginActivity.this, "Please verify", Toast.LENGTH_SHORT).show();
                    Toast.makeText(LoginActivity.this, "Otp is: " + getProfilePojo.getDetails().getOtp(), Toast.LENGTH_SHORT).show();
                    Common.SaveToken(LoginActivity.this,"email",getProfilePojo.getDetails().getEmail());
                    Intent intent = new Intent(LoginActivity.this, OtpVerification.class);
                    startActivity(intent);
                }
            }
        });
    }

    public void signup(View view) {
        Intent intent = new Intent(LoginActivity.this, SignUpActivity.class);
        startActivity(intent);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(LoginActivity.this, About.class);
        startActivity(intent);
    }
}
