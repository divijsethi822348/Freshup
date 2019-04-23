package com.example.freshup.Activities;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.Nullable;
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
import android.widget.TextView;
import android.widget.Toast;

import com.example.freshup.SharedPrefrences.Common;
import com.example.freshup.SharedPrefrences.Login_Logout;
import com.example.freshup.Models.OtpPojo;
import com.example.freshup.Models.SimplePojo;
import com.example.freshup.R;
import com.example.freshup.Util.CommonUtils;
import com.example.freshup.ViewModels.UserRegisterViewModel;

public class OtpVerification extends AppCompatActivity {

    public EditText otp1,otp2,otp3,otp4;
    Button popup;
    RelativeLayout layout;
    PopupWindow popupWindow;
    String otp="";
    TextView resend;
    String id="";
    private UserRegisterViewModel viewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otp_verification);
        resend=findViewById(R.id.resend_tv);
        viewModel= ViewModelProviders.of(this).get(UserRegisterViewModel.class);
        otp1=findViewById(R.id.otp1);
        otp2=findViewById(R.id.otp2);
        otp3=findViewById(R.id.otp3);
        otp4=findViewById(R.id.otp4);
        layout=findViewById(R.id.layout);
        otp1.requestFocus();

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
                }
                else if(s.length()==0)
                {
                    otp3.requestFocus();
                }
            }
        });

        resend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String id=Common.GetToken(OtpVerification.this,"ID");
                 viewModel.resend(OtpVerification.this,id).observe(OtpVerification.this, new Observer<OtpPojo>() {
                     @Override
                     public void onChanged(@Nullable OtpPojo otpPojo) {
                         Toast.makeText(OtpVerification.this, "Otp is "+otpPojo.getDetails().getOtp().toString(), Toast.LENGTH_SHORT).show();
                     }
                 });

            }
        });


    }

    public void verify(View view) {

        id= Common.GetToken(OtpVerification.this,"ID");
        otp=otp1.getText().toString()+otp2.getText().toString()+otp3.getText().toString()+otp4.getText().toString();
        if (otp.length()==4){
            CommonUtils.showProgress(OtpVerification.this);
            viewModel.verification(this,id,otp).observe(this, new Observer<SimplePojo>() {
                @Override
                public void onChanged(@Nullable SimplePojo simplePojo) {
                    if (simplePojo.getSuccess().equalsIgnoreCase("1")){
                        Toast.makeText(OtpVerification.this, "Verified", Toast.LENGTH_SHORT).show();
                        LayoutInflater layoutInflater= (LayoutInflater) OtpVerification.this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                        View  view1=layoutInflater.inflate(R.layout.otp_verification_popup,null);
                        popup=(Button) findViewById(R.id.done);

                        popupWindow=new PopupWindow(view1, LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);

                        popupWindow.showAtLocation(layout, Gravity.CENTER,0,0);
                        CommonUtils.dismiss();
                    }else if (simplePojo.getSuccess().equalsIgnoreCase("0")){
                        CommonUtils.dismiss();
                        Toast.makeText(OtpVerification.this, "Otp does not match", Toast.LENGTH_SHORT).show();
                    }
                }
            });

        }
        else{
            Toast.makeText(this, "Enter Otp", Toast.LENGTH_SHORT).show();
        }


    }

    public void popup_dismiss(View view) {
        popupWindow.dismiss();
        if (Common.GetToken(OtpVerification.this,"ID").equalsIgnoreCase("1") || Login_Logout.GetToken(OtpVerification.this).equalsIgnoreCase("1")){

                Toast.makeText(this, "Number Updated", Toast.LENGTH_SHORT).show();
                Intent intent=new Intent(OtpVerification.this,Profile.class);
                startActivity(intent);

            }

        else
        {
            Intent intent=new Intent(OtpVerification.this, NavigatorActivity.class);
            intent.putExtra("check",0);
            startActivity(intent);
            Toast.makeText(this, "Logged In", Toast.LENGTH_LONG).show();
        }





    }

}

