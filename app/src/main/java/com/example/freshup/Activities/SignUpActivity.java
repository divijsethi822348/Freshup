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

import com.example.freshup.SharedPrefrences.Common;
import com.example.freshup.Models.RegisterModel;
import com.example.freshup.R;
import com.example.freshup.Util.CommonUtils;
import com.example.freshup.ViewModels.UserRegisterViewModel;

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

        if (Name.isEmpty()){

            name.setError("Enter Name");
        }else if (Email.isEmpty()){
            email.setError("Enter Email");
        }else if (Number.isEmpty()){
            number.setError("Enter Number");
        }else if (Password.isEmpty()){
            password.setError("Enter Password");
        }
        else
            {
           if (Confirm.equals(password.getText().toString())){

               viewModel.userRegister(SignUpActivity.this,Name,Email,Number,Password,"Android","0").observe(this, new Observer<RegisterModel>() {
                   @Override
                   public void onChanged(@Nullable RegisterModel registerModel) {
                       if (registerModel.getSuccess().equalsIgnoreCase("1")){
                           Common.SaveToken(SignUpActivity.this,"ID",registerModel.getDetails().getId());
                           Toast.makeText(SignUpActivity.this, "Otp is "+registerModel.getDetails().getOtp(), Toast.LENGTH_LONG).show();
                           Intent intent=new Intent(SignUpActivity.this, OtpVerification.class);
                           startActivity(intent);

                       }
                       else if (registerModel.getSuccess().equalsIgnoreCase("0")){

                           Toast.makeText(SignUpActivity.this, "These Credentials already exist", Toast.LENGTH_SHORT).show();
                       }
                   }
               });
           }
           else{
               Toast.makeText(this, "Password doesnt match", Toast.LENGTH_SHORT).show();
           }
       }

    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent=new Intent(this,LoginActivity.class);
        startActivity(intent);
    }
}
