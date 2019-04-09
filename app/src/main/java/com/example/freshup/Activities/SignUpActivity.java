package com.example.freshup.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.freshup.R;

public class SignUpActivity extends AppCompatActivity {
    Button signupbutton;
    EditText name,email,number,password,confirm;

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
                Intent intent=new Intent(SignUpActivity.this, OtpVerification.class);
                startActivity(intent);
            }
        });
    }
    public void signin(View view) {
        submit();
        Intent intent=new Intent(SignUpActivity.this, LoginActivity.class);
        startActivity(intent);
    }

    private void submit() {

    }
}
