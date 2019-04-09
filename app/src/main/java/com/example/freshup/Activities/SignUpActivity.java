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

import com.example.freshup.Api;
import com.example.freshup.ApiClient;
import com.example.freshup.Common;
import com.example.freshup.Models.RegisterModel;
import com.example.freshup.R;
import com.example.freshup.ViewModels.UserRegisterViewModel;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignUpActivity extends AppCompatActivity {
    Button signupbutton;
    EditText name,email,number,password,confirm;
    String Name="",Email="",Number="",Password="",Confirm="";
    private UserRegisterViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        viewModel= ViewModelProviders.of(this).get(UserRegisterViewModel.class);
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

       if (Name!=null && Email!=null && Number!=null && Password!=null && Confirm!=null){
           if (Confirm.equals(password.getText().toString())){
               viewModel.userRegister(SignUpActivity.this,Name,Email,Number,Password,"Android","0").observe(this, new Observer<RegisterModel>() {
                   @Override
                   public void onChanged(@Nullable RegisterModel registerModel) {
                       Common.SaveToken(SignUpActivity.this,"ID",registerModel.getDetails().getId());
                       Toast.makeText(SignUpActivity.this, "Otp is "+registerModel.getDetails().getOtp(), Toast.LENGTH_LONG).show();
                       Intent intent=new Intent(SignUpActivity.this, OtpVerification.class);
                       startActivity(intent);
                   }
               });
           }
           else{
               Toast.makeText(this, "Password doesnt match", Toast.LENGTH_SHORT).show();
           }
       }
       else {
           Toast.makeText(this, "Enter All Fields", Toast.LENGTH_SHORT).show();
       }
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent=new Intent(this,LoginActivity.class);
        startActivity(intent);
    }
}
