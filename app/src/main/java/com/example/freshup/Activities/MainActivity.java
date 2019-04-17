package com.example.freshup.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.example.freshup.SharedPrefrences.Login_Logout;
import com.example.freshup.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Thread thread=new Thread(){
            @Override
            public void run() {
                super.run();
                try {
                    this.sleep(1000);

                    if (Login_Logout.GetToken(MainActivity.this).equalsIgnoreCase("1")){
                        Intent intent=new Intent(MainActivity.this,NavigatorActivity.class);
                        startActivity(intent);
                    }
                    else {
                        Intent intent=new Intent(MainActivity.this, About.class);
                        startActivity(intent);
                    }
                } catch (InterruptedException e) {
                    Toast.makeText(MainActivity.this, ""+e, Toast.LENGTH_SHORT).show();
                }
            }
        };
        thread.start();
    }
}
