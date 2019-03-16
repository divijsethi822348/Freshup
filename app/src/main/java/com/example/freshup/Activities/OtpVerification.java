package com.example.freshup.Activities;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.freshup.R;

public class OtpVerification extends AppCompatActivity {

    public EditText otp1,otp2,otp3,otp4;
    Button popup;
    RelativeLayout layout;
    PopupWindow popupWindow;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otp_verification);
        otp1=findViewById(R.id.otp1);
        otp2=findViewById(R.id.otp2);
        otp3=findViewById(R.id.otp3);
        otp4=findViewById(R.id.otp4);
        layout=findViewById(R.id.layout);

        otp1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(s.length()==1)
                {
                    otp2.requestFocus();
                }
                else if(s.length()==0)
                {
                    otp1.clearFocus();
                }
            }
        });

        otp2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(s.length()==1)
                {
                    otp3.requestFocus();
                }
                else if(s.length()==0)
                {
                    otp1.requestFocus();
                }
            }
        });

        otp3.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(s.length()==1)
                {
                    otp4.requestFocus();
                }
                else if(s.length()==0)
                {
                    otp2.requestFocus();
                }
            }
        });

        otp4.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(s.length()==1)
                {
                    otp4.clearFocus();
                }
                else if(s.length()==0)
                {
                    otp3.requestFocus();
                }
            }
        });


    }

    public void verify(View view) {
        LayoutInflater layoutInflater= (LayoutInflater) OtpVerification.this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View  view1=layoutInflater.inflate(R.layout.otp_verification_popup,null);
        popup=(Button) findViewById(R.id.done);

        popupWindow=new PopupWindow(view1, LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);

        popupWindow.showAtLocation(layout, Gravity.CENTER,0,0);




    }

    public void popup_dismiss(View view) {
        popupWindow.dismiss();
        Intent intent=new Intent(OtpVerification.this, LoginActivity.class);
        startActivity(intent);
        Toast.makeText(this, "Please LogIn with your Credentials ", Toast.LENGTH_LONG).show();

    }
}

