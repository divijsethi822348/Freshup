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
import com.example.freshup.R;
import com.example.freshup.Util.CommonUtils;
import com.example.freshup.ViewModels.UserRegisterViewModel;

import java.util.Map;

public class ChangePassActivity extends AppCompatActivity {
    Button change;
    EditText old_pass,new_pass,confirm_new_pass;
    private UserRegisterViewModel viewModel;
    String Old="",New="",Confirm="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_pass);
        old_pass=findViewById(R.id.old_password);
        new_pass=findViewById(R.id.new_password);
        viewModel= ViewModelProviders.of(ChangePassActivity.this).get(UserRegisterViewModel.class);
        confirm_new_pass=findViewById(R.id.confirm_new_password);
        change=findViewById(R.id.change_pass);
        change.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changepassword();

            }
        });
    }

    private void changepassword() {
        Old=old_pass.getText().toString();
        New=new_pass.getText().toString();
        Confirm=confirm_new_pass.getText().toString();

        if (Old.equalsIgnoreCase("")){
            old_pass.setError("Enter old password");
        }else if (New.equalsIgnoreCase("")){
            new_pass.setError("Enter new password");
        }else if (Confirm.equalsIgnoreCase("")){
            confirm_new_pass.setError("Enter new password");
        }else {
            if (New.equalsIgnoreCase(Confirm)){
                viewModel.change_pass(ChangePassActivity.this, Common.GetToken(ChangePassActivity.this,"ID"),Old,New).observe(this, new Observer<Map>() {
                    @Override
                    public void onChanged(@Nullable Map map) {
                        Toast.makeText(ChangePassActivity.this, "Password changed", Toast.LENGTH_SHORT).show();
                        Toast.makeText(ChangePassActivity.this, "Pass changed!!", Toast.LENGTH_SHORT).show();
                        Intent intent=new Intent(getApplicationContext(),NavigatorActivity.class);
                        startActivity(intent);
                    }
                });
            }else {
                Toast.makeText(this, "Passwords dont match", Toast.LENGTH_SHORT).show();
            }

        }

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent=new Intent(this,SettingsActivity.class);
        startActivity(intent);
    }
}
