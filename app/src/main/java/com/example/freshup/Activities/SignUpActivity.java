package com.example.freshup.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.freshup.Api;
import com.example.freshup.ApiClient;
import com.example.freshup.Models.RegisterModel;
import com.example.freshup.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignUpActivity extends AppCompatActivity {
    Button signupbutton;
    EditText name,email,number,password,confirm;
    String Name="",Email="",Number="",Password="",Confirm="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        signupbutton=findViewById(R.id.signupbutton);
        name=findViewById(R.id.etname);
        email=findViewById(R.id.etemail);
        number=findViewById(R.id.etnumber);
        password=findViewById(R.id.etpassword);
        confirm=findViewById(R.id.etconfirm);

        signupbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                submit();
                Intent intent=new Intent(SignUpActivity.this, OtpVerification.class);
                startActivity(intent);
            }
        });
    }
    public void signin(View view) {
        Intent intent=new Intent(SignUpActivity.this, LoginActivity.class);
        startActivity(intent);
    }

    private void submit() {
        Name=name.getText().toString();
        Email=email.getText().toString();
        Number=number.getText().toString();
        Password=password.getText().toString();
        Confirm=confirm.getText().toString();

        Api api= ApiClient.getApiClient().create(Api.class);
        api.UserRegister(Name,Email,Number,Password,"Android","0001").enqueue(new Callback<RegisterModel>() {
            @Override
            public void onResponse(Call<RegisterModel> call, Response<RegisterModel> response) {
                Toast.makeText(SignUpActivity.this, "Success", Toast.LENGTH_SHORT).show();
                if (response.body().getSuccess().equalsIgnoreCase("1")){
                    String otp=response.body().getDetails().getOtp().toString();
                    Toast.makeText(SignUpActivity.this, "Otp is "+otp, Toast.LENGTH_LONG).show();

                }
            }

            @Override
            public void onFailure(Call<RegisterModel> call, Throwable t) {
                Toast.makeText(SignUpActivity.this, "Failed", Toast.LENGTH_SHORT).show();
            }
        });

    }
}
